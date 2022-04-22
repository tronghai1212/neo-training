package vn.plusplus.lms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.exceptions.AppException;
import vn.plusplus.lms.exceptions.ErrorCode;
import vn.plusplus.lms.repository.ApiRepository;
import vn.plusplus.lms.repository.RoleRepository;
import vn.plusplus.lms.repository.TokenRepository;
import vn.plusplus.lms.repository.UserRoleRepository;
import vn.plusplus.lms.repository.entities.ApiEntity;
import vn.plusplus.lms.repository.entities.TokenEntity;
import vn.plusplus.lms.interceptor.Payload;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenService {
    private final static Logger logger = LoggerFactory.getLogger(TokenService.class);

    @Autowired
    TokenRepository tokenRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    ApiRepository apiRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    private static final Map<String, Payload> authenCaching = new ConcurrentHashMap<>();


    public Payload getPayload(String accessToken, ApiEntity apiEntity) {
        //Check token in cache first
        Payload payload = authenCaching.get(accessToken);
        if (payload != null) {
            checkExpiredTime(payload.getExpiredTime());
            if (apiEntity.getShouldCheckPermission()) {
                checkPermissions(payload.getPermissions(), apiEntity.getId());
            }
            return payload;
        }

        //If token is not in cache, check DB and save token to cache
        TokenEntity tokenEntity = tokenRepository.findOneByToken(accessToken);
        if (tokenEntity == null) {
            logger.error("Not found access token [{}]", accessToken);
            throw new AppException(ErrorCode.INVALID_TOKEN);
        }
        checkExpiredTime(tokenEntity.getExpiredTime());

        //TODO check user can call this api
        /*1. Finding role of user
         * 2. Get list API that role can be access
         * 3. Loop to check if this api is in list API access
         * 4. Throw exception if not found*/
        List<Integer> listAPI = userRoleRepository.getListApiIdByUserId(tokenEntity.getUserId());
        List<String> roles = roleRepository.findListRoleName(tokenEntity.getUserId());
        payload = new Payload();
        payload.setUserId(tokenEntity.getUserId());
        payload.setAccessToken(accessToken);
        payload.setExpiredTime(tokenEntity.getExpiredTime());
        payload.setPermissions(listAPI);
        payload.setRoles(roles);

        saveToCache(payload);

        return payload;
    }

    private void checkPermissions(List<Integer> listAPI, Integer apiId) {
        boolean check = false;
        for (Integer i : listAPI) {
            if (i.equals(apiId)) {
                check = true;
                break;
            }
        }
        if (check == false) {
            throw new AppException(ErrorCode.USER_HAS_NO_PERMISSION);
        }
    }

    public void saveToCache(Payload payload) {
        authenCaching.put(payload.getAccessToken(), payload);
    }

    //This function call when logout - after clear token in DB or login - after replace token in DB
    //This can use Redis to caching token with load balancing.
    public void removeTokenFromCache(String accessToken) {
        authenCaching.remove(accessToken);
    }

    private void checkExpiredTime(Timestamp expiredTime) {
        if (expiredTime.before(new Timestamp(System.currentTimeMillis()))) {
            throw new AppException(ErrorCode.ACCESS_TOKEN_EXPIRE);
        }
    }
}
