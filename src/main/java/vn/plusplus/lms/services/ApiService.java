package vn.plusplus.lms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.controller.request.NewApiRequest;
import vn.plusplus.lms.controller.request.UpdateApiRequest;
import vn.plusplus.lms.exceptions.AppException;
import vn.plusplus.lms.exceptions.ErrorCode;
import vn.plusplus.lms.interceptor.GatewayInterceptor;
import vn.plusplus.lms.repository.ApiRepository;
import vn.plusplus.lms.repository.RoleApiRepository;
import vn.plusplus.lms.repository.entities.ApiEntity;
import vn.plusplus.lms.repository.entities.RoleApiEntity;
import vn.plusplus.lms.repository.entities.enumerates.Status;
import vn.plusplus.lms.utils.AppUtils;
import vn.plusplus.lms.config.AppConstants;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ApiService extends BaseSearchService<ApiEntity> {
    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);

    @Autowired
    ApiRepository apiRepository;
    @Autowired
    RoleApiRepository roleApiRepository;
    @Autowired
    private GatewayInterceptor gatewayInterceptor;

    public ApiEntity addApi(NewApiRequest request, List<String> roles) {
        if (!roles.contains(AppConstants.ROLE.SUPER_ADMIN)) {
            logger.info("Bạn không có quyền thực thi");
            throw new AppException(ErrorCode.USER_HAS_NO_PERMISSION);
        }
        logger.info("Add api with request [{}]", request);
        ApiEntity entity = new ApiEntity();
        AppUtils.copyPropertiesIgnoreNull(request, entity);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        entity.setCreatedTime(now);
        entity.setUpdatedTime(now);
        apiRepository.save(entity);
        gatewayInterceptor.addApi(entity);
        return entity;
    }

    public ApiEntity updateApi(Integer id, UpdateApiRequest request, List<String> roles) {
        if (!roles.contains("SUPER_ADMIN")) {
            logger.info("Bạn không có quyền thực thi");
            throw new AppException(ErrorCode.USER_HAS_NO_PERMISSION);
        }
        logger.info("Update apiId [{}] with request [{}]", id, request);
        ApiEntity entity = apiRepository.findOneById(id);
        if (entity == null) {
            throw new AppException(ErrorCode.ENTITY_NOT_EXIST);
        }
        gatewayInterceptor.removeApi(entity);
        AppUtils.copyPropertiesIgnoreNull(request, entity);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        entity.setUpdatedTime(now);
        gatewayInterceptor.addApi(entity);
        return apiRepository.save(entity);
    }

    public String deleteApi(Integer id, List<String> roles) {
        if (!roles.contains("SUPER_ADMIN")) {
            logger.info("Bạn không có quyền thực thi");
            throw new AppException(ErrorCode.USER_HAS_NO_PERMISSION);
        }
        logger.info("Delete api with id [{}]", id);
        ApiEntity entity = apiRepository.findOneById(id);
        if (entity == null) {
            throw new AppException(ErrorCode.ENTITY_NOT_EXIST);
        }
        //Remove role_api relation
        List<RoleApiEntity> roleApis = roleApiRepository.findAllByApiId(id);
        roleApis.forEach(e -> {
            roleApiRepository.delete(e);
        });
        entity.setStatus(Status.DELETED);
        entity.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
        apiRepository.save(entity);
        gatewayInterceptor.removeApi(entity);
        return "DELETED";
    }
}
