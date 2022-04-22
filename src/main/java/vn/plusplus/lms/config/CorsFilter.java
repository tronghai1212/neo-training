package vn.plusplus.lms.config;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CorsFilter extends OncePerRequestFilter {
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String origin = request.getHeader(HttpHeaders.ORIGIN);
        //TODO check origin in whitelist
        response.setHeader("Access-Control-Allow-Origin", origin);
        if ("OPTIONS".equals(request.getMethod())) {
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "authorization, content-type, credentials");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.getWriter().write("{\"allow\": true}");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
