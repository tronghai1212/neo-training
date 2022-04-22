package vn.plusplus.lms.interceptor;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import vn.plusplus.lms.exceptions.AppException;
import vn.plusplus.lms.exceptions.ErrorCode;
import vn.plusplus.lms.repository.ApiRepository;
import vn.plusplus.lms.repository.entities.ApiEntity;
import vn.plusplus.lms.services.TokenService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Service
public class GatewayInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LogManager.getLogger(GatewayInterceptor.class);

    private static final List<ApiEntity> apiList = new ArrayList<>();

    @Autowired
    ApiRepository apiRepository;

    @Autowired
    TokenService tokenService;

    public void initApiCache() {
        List<ApiEntity> apiEntities = apiRepository.findAll();
        for (ApiEntity api : apiEntities) {
            this.apiList.add(api);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute(GatewayConstant.START_PROCESSING_TIME, System.currentTimeMillis());
        String sessionId = createSessionId();
        ThreadContext.put(GatewayConstant.CORRELATION_ID_HEADER, sessionId);
        logger.info("========== Start process request [{}]:[{}]", request.getMethod(), request.getServletPath());
        return verifyRequest(request);
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView
            modelAndView) {
        Long startProcessingTime = (Long) request.getAttribute(GatewayConstant.START_PROCESSING_TIME);
        Long endProcessingTime = System.currentTimeMillis();

        logger.info("========== End to process request [{}]:[{}] with [{}]. Processing time: [{}] milliseconds ==========", request.getMethod(), request.getServletPath(), response.getStatus(), endProcessingTime - startProcessingTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    private boolean verifyRequest(HttpServletRequest request) {
        printHeader(request);
        String httpMethod = request.getMethod();
        String servletPath = request.getServletPath();
        if (servletPath.contains("swagger")) {
            return true;
        }
        Payload payload = new Payload();
        ApiEntity apiEntity = getMatchingAPI(httpMethod, servletPath);
        if (apiEntity.getIsRequiredAccessToken()) {
            String accessToken = request.getHeader(GatewayConstant.AUTHORIZATION_HEADER);
            if (accessToken != null) {
                if (StringUtils.isEmpty(accessToken)) {
                    logger.error("Authorization field in header is null or empty");
                    throw new AppException(ErrorCode.AUTHORIZATION_FIELD_MISSING);
                }
                payload = tokenService.getPayload(accessToken, apiEntity);
            }else {
                logger.error("No authen");
                throw new AppException(ErrorCode.AUTHORIZATION_FIELD_MISSING);
            }

        }
        request.setAttribute(GatewayConstant.PAYLOAD, payload);
        logger.info("Request validated. Start forward request to backend");
        return true;
    }

    private String createSessionId() {
        String sessionToken = RandomStringUtils.randomAlphanumeric(7).toUpperCase();
        return sessionToken;
    }

    private ApiEntity getMatchingAPI(String httpMethod, String path) {
        path = path.trim();
        if (path.endsWith("/")) {
            path = path.substring(0, path.lastIndexOf("/"));
        }

        AntPathMatcher matcher = new AntPathMatcher();
        for (ApiEntity apiEntity : apiList) {
            if (matcher.match(apiEntity.getPattern(), path) && httpMethod.equals(apiEntity.getHttpMethod())) {
                logger.info("Found apiId [{}], pattern [{}] matched", apiEntity.getId(), apiEntity.getPattern());
                return apiEntity;
            }
        }
        throw new AppException(ErrorCode.API_NOT_FOUND);
    }

    private void printHeader(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String header = headerNames.nextElement();
            logger.debug("Header: {} - {}", header, request.getHeader(header));
        }
    }

    public void addApi(ApiEntity apiEntity) {
        this.apiList.add(apiEntity);
    }

    public void removeApi(ApiEntity apiEntity) {
        this.apiList.remove(apiEntity);
    }

}