package vn.plusplus.lms.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.util.StringUtils;
import vn.plusplus.lms.exceptions.AppException;
import vn.plusplus.lms.exceptions.ErrorCode;
import vn.plusplus.lms.model.UserAdminInfo;
import vn.plusplus.lms.model.UserProfileInfo;
import vn.plusplus.lms.repository.entities.enumerates.Status;
import vn.plusplus.lms.utils.AppUtils;
import vn.plusplus.lms.config.AppConstants;
import vn.plusplus.lms.controller.request.*;
import vn.plusplus.lms.interceptor.Payload;
import vn.plusplus.lms.repository.*;
import vn.plusplus.lms.repository.entities.*;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService extends BaseSearchService<UserEntity> {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    ObjectMapper objectMapper;



    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    TokenService tokenService;


    @Autowired
    RoleRepository roleRepository;

    @Autowired
    ApiRepository apiRepository;

    @Autowired
    BCryptPasswordEncoder encoder;



    @Value("${token.expired.time.in.minutes}")
    private Integer expiredTimeInMinutes;

    public UserEntity adminAddUser(AdminAddUserRequest request) {
        checkAddUser(request.getUserName());
        List<RoleEntity> listRole = roleRepository.findAllByIdIn(request.getRoleIds());
        List<String> roleNames = listRole.stream().map(e -> e.getRoleName()).collect(Collectors.toList());
        if (roleNames.contains(AppConstants.ROLE.USER) && listRole.size() > 1) {
            logger.info("Không thể phân quyền");
            throw new AppException(ErrorCode.GENERAL_ERROR);
        }
        UserEntity entity = new UserEntity();

        BeanUtils.copyProperties(request, entity);
        String password = encoder.encode(request.getPassword());
        entity.setPassword(password);
        entity.setStatus(request.getStatus());
        Timestamp now = new Timestamp(System.currentTimeMillis());
        entity.setCreatedTime(now);
        entity.setUpdatedTime(now);
        entity = userRepository.save(entity);


        if (request.getRoleIds() != null && !request.getRoleIds().isEmpty()) {
            //Add role for user
            List<UserRoleEntity> roles = new ArrayList<>();
            for (Integer roleId : request.getRoleIds()) {
                UserRoleEntity userRoleEntity = new UserRoleEntity();
                userRoleEntity.setUserId(entity.getId());
                userRoleEntity.setRoleId(roleId);
                userRoleEntity.setCreatedTime(now);
                userRoleEntity.setUpdatedTime(now);
                roles.add(userRoleEntity);
            }
            logger.info("Add [{}] roles for user [{}]", request.getRoleIds().size(), request.getUserName());
            userRoleRepository.saveAll(roles);
        }

        return entity;
    }



    public UserEntity adminUpdateUser(Integer userId, AdminUpdateUserRequest request) {
        UserEntity userEntity = userRepository.findOneById(userId);
        if (userEntity == null) {
            logger.error("UserId [{}] is not existed", userId);
            throw new AppException(ErrorCode.USER_NOT_VALID);
        }
        List<RoleEntity> listRole = roleRepository.findAllByIdIn(request.getRoleIds());
        List<String> roleNames = listRole.stream().map(e -> e.getRoleName()).collect(Collectors.toList());
        if (roleNames.contains(AppConstants.ROLE.USER) && listRole.size() > 1) {
            logger.info("Không thể phân quyền");
            throw new AppException(ErrorCode.GENERAL_ERROR);
        }
        AppUtils.copyPropertiesIgnoreNull(request, userEntity, "password");
        if (!StringUtils.isEmpty(request.getPassword())) {
            String password = encoder.encode(request.getPassword());
            userEntity.setPassword(password);
        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (request.getRoleIds() != null && !request.getRoleIds().isEmpty()) {
            List<UserRoleEntity> oldUserRoles = userRoleRepository.findAllByUserId(userId);
            //Remove old user role not in list new user role
            oldUserRoles.forEach(e -> {
                if (!request.getRoleIds().contains(e.getRoleId())) {
                    userRoleRepository.delete(e);
                }
            });
            //Add role not in list existed role
            List<Integer> oldRoleId = oldUserRoles.stream().map(e -> e.getRoleId()).collect(Collectors.toList());
            List<UserRoleEntity> roles = new ArrayList<>();
            request.getRoleIds().forEach(e -> {
                if (!oldRoleId.contains(e)) {
                    UserRoleEntity userRoleEntity = new UserRoleEntity();
                    userRoleEntity.setUserId(userId);
                    userRoleEntity.setRoleId(e);
                    userRoleEntity.setCreatedTime(now);
                    userRoleEntity.setUpdatedTime(now);
                    roles.add(userRoleEntity);
                }
            });
            userRoleRepository.saveAll(roles);
        }
        userEntity = userRepository.save(userEntity);
//        if (roleNames.contains(AppConstants.ROLE.STUDENT) && roleNames.size() == 1) {
//            StudentEntity studentEntity = studentRepository.findOneByUserId(userId);
//            if (studentEntity == null) {
//                MentorEntity mentorEntity = mentorRepository.findOneByUserId(userId);
//                if (mentorEntity != null) {
//                    mentorRepository.delete(mentorEntity);
//                }
//                studentEntity = new StudentEntity();
//                AppUtils.copyPropertiesIgnoreNull(userEntity,studentEntity);
//                studentRepository.save(studentEntity);
//            }
//        }
        return userEntity;
    }

    private void checkAddUser(String username) {
        if (StringUtils.isEmpty(username)) {
            logger.error("Username must be required");
            throw new AppException(ErrorCode.USER_NOT_VALID);
        }

        UserEntity userEntity = userRepository.findOneByUserName(username);
        if (userEntity != null) {
            logger.error("Username [{}] is existed", username);
            throw new AppException(ErrorCode.USER_EXISTED);
        }
    }

    private void checkUserInfo(NewUserRequest request) {
        if (StringUtils.isEmpty(request.getUserName())) {
            logger.error("Username must be required");
            throw new AppException(ErrorCode.USER_NOT_VALID);
        }
        request.checkRegex(request);

        UserEntity userEntity = userRepository.findOneByUserName(request.getUserName());
        if (userEntity != null) {
            logger.error("Username [{}] is existed", request.getUserName());
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        if (StringUtils.isEmpty(request.getEmail())) {
            logger.error("Email must be required");
            throw new AppException(ErrorCode.EMAIL_MUST_REQUIRED);
        }
        UserEntity userEntity1 = userRepository.findOneByEmail(request.getEmail());
        if (userEntity1 != null) {
            logger.error("Email [{}] is existed", request.getEmail());
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        }

        if (StringUtils.isEmpty(request.getFullName())) {
            logger.error("Full name must be required");
            throw new AppException(ErrorCode.FULLNAME_MUST_REQUIRED);
        }
        if (StringUtils.isEmpty(request.getPassword())) {
            logger.error("Password must be required");
            throw new AppException(ErrorCode.PASS_MUST_REQUIRED);
        }
    }


    public UserEntity registerUser(NewUserRequest request) {
        checkUserInfo(request);
        UserEntity entity = new UserEntity();
        entity.setUserName(request.getUserName());
        String password = encoder.encode(request.getPassword());
        entity.setPassword(password);
        entity.setFullName(request.getFullName());
        entity.setEmail(request.getEmail());
        entity.setStatus(Status.UNVERIFIED);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        entity.setCreatedTime(now);
        entity.setUpdatedTime(now);
        entity = userRepository.save(entity);
        logger.info("Saved new userId [{}]", entity.getId());
        RoleEntity role = roleRepository.findOneByRoleName("STUDENT");
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserId(entity.getId());
        userRoleEntity.setRoleId(role.getId());
        userRoleEntity.setCreatedTime(now);
        userRoleEntity.setUpdatedTime(now);
        userRoleRepository.save(userRoleEntity);
        return entity;
    }






    public LoginResponse loginUser(LoginUserRequest request) {
        LoginResponse data = new LoginResponse();
        UserEntity entity = userRepository.findOneByUserName(request.getUserName());
        if (entity == null) {
            throw new AppException(ErrorCode.USER_NOT_FOUND, "Người dùng không tồn tại");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Boolean check = encoder.matches(request.getPassword(), entity.getPassword());
        if (check == false) {
            throw new AppException(ErrorCode.USER_NOT_VALID, "Sai mật khẩu hoặc tên đăng nhập");
        }

        //Check role must be STUDENT
//        checkRoleStudent(entity);

        if(entity.getStatus().equals(Status.UNVERIFIED)){
            throw new AppException(ErrorCode.UNVERIFIED, "Tài khoản chưa được xác minh");
        }

        List<RoleEntity> listRoless = roleRepository.findListRole(entity.getId());
        List<String> roless = listRoless.stream().map(e -> e.getRoleName()).collect(Collectors.toList());
        if (roless.contains(AppConstants.ROLE.USER)) {
            long deltaTime = 60 * 1000 * expiredTimeInMinutes;
            String token = "user-" + UUID.randomUUID().toString();
            TokenEntity tokenEntity = tokenRepository.findOneByUserId(entity.getId());
            long now = System.currentTimeMillis();
            Timestamp timestamp = new Timestamp(now);
            if (tokenEntity == null) {
                tokenEntity = new TokenEntity();
                tokenEntity.setCreatedTime(timestamp);
            } else {
                logger.info("Remove token [{}] in cache", tokenEntity.getToken());
                tokenService.removeTokenFromCache(tokenEntity.getToken());
            }
            tokenEntity.setToken(token);
            tokenEntity.setUpdatedTime(timestamp);
            tokenEntity.setExpiredTime(new Timestamp(now + deltaTime));
            tokenEntity.setUserId(entity.getId());

            tokenRepository.save(tokenEntity);
            // Tạo payload lưu vào authenCaching
            List<Integer> listApi = userRoleRepository.getListApiIdByUserId(entity.getId());
            List<RoleEntity> listRoles = roleRepository.findListRole(entity.getId());
            List<String> roles = listRoles.stream().map(e -> e.getRoleName()).collect(Collectors.toList());
            Payload payload = new Payload();
            payload.setAccessToken(token);
            payload.setPermissions(listApi);
            payload.setUserId(entity.getId());
            payload.setRoles(roles);
            payload.setExpiredTime(new Timestamp(now + deltaTime));

            tokenService.saveToCache(payload);

            data.setToken(token);
            data.setExpiredTime(new Timestamp(now + deltaTime));
            return data;
        }
        throw new AppException(ErrorCode.USER_NOT_VALID, "Tài khoản không hợp lệ");

    }

    public LoginResponse loginAdminUser(LoginUserRequest request) {
        UserEntity entity = userRepository.findOneByUserName(request.getUserName());
        if (entity == null) {
            throw new AppException(ErrorCode.USER_NOT_VALID, "Người dùng không tồn tại");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Boolean check = encoder.matches(request.getPassword(), entity.getPassword());
        if (check == false) {
            throw new AppException(ErrorCode.USER_NOT_VALID, "Sai mật khẩu hoặc tên đăng nhập");
        }

        //Check role must not contain STUDENT
        checkRoleAdmin(entity);

        long deltaTime = 60 * 1000 * expiredTimeInMinutes;
        String token = "admin-" + UUID.randomUUID().toString();
        TokenEntity tokenEntity = tokenRepository.findOneByUserId(entity.getId());
        long now = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(now);
        if (tokenEntity == null) {
            tokenEntity = new TokenEntity();
            tokenEntity.setCreatedTime(timestamp);
        } else {
            logger.info("Remove token [{}] in cache", tokenEntity.getToken());
            tokenService.removeTokenFromCache(tokenEntity.getToken());
        }
        tokenEntity.setToken(token);
        tokenEntity.setUpdatedTime(timestamp);
        tokenEntity.setExpiredTime(new Timestamp(now + deltaTime));
        tokenEntity.setUserId(entity.getId());

        tokenRepository.save(tokenEntity);
        // Tạo payload lưu vào authenCaching
        List<Integer> listApi = userRoleRepository.getListApiIdByUserId(entity.getId());
        List<RoleEntity> listRoles = roleRepository.findListRole(entity.getId());
        List<String> roles = listRoles.stream().map(e -> e.getRoleName()).collect(Collectors.toList());
        Payload payload = new Payload();
        payload.setAccessToken(token);
        payload.setPermissions(listApi);
        payload.setUserId(entity.getId());
        payload.setRoles(roles);
        payload.setExpiredTime(new Timestamp(now + deltaTime));

        tokenService.saveToCache(payload);

        LoginResponse data = new LoginResponse();
        data.setToken(token);
        data.setExpiredTime(new Timestamp(now + deltaTime));
        return data;

    }

    private void checkRoleAdmin(UserEntity entity) {
        List<RoleEntity> listRoles = roleRepository.findListRole(entity.getId());
        List<String> roles = listRoles.stream().map(e -> e.getRoleName()).collect(Collectors.toList());
        if (roles.contains(AppConstants.ROLE.USER)) {
            throw new AppException(ErrorCode.USER_NOT_VALID, "User không hợp lệ");
        }
    }

    private void checkRoleStudent(UserEntity entity) {
        List<RoleEntity> listRoles = roleRepository.findListRole(entity.getId());
        List<String> roles = listRoles.stream().map(e -> e.getRoleName()).collect(Collectors.toList());
        if (roles.size() > 1 || !roles.get(0).equals(AppConstants.ROLE.USER)) {
            throw new AppException(ErrorCode.USER_NOT_VALID, "User không hợp lệ");
        }
    }

    private void checkRoleAdmin(Payload payload) {
        List<String> roles = payload.getRoles();
        if (roles.contains(AppConstants.ROLE.USER)) {
            throw new AppException(ErrorCode.USER_NOT_VALID, "User không hợp lệ");
        }
    }

    private void checkRoleStudent(Payload payload) {
        List<String> roles = payload.getRoles();
        if (roles.size() > 1 || !roles.get(0).equals(AppConstants.ROLE.USER)) {
            throw new AppException(ErrorCode.USER_NOT_VALID, "User không hợp lệ");
        }
    }


    public String logout(Integer userId) {
        TokenEntity tokenEntity = tokenRepository.findOneByUserId(userId);
        tokenService.removeTokenFromCache(tokenEntity.getToken());
        tokenRepository.deleteById(tokenEntity.getId());
        return "Logout user id " + userId;
    }

    public String deleteUserById(Integer id) {
        UserEntity entity = userRepository.findOneById(id);
        if (entity == null) {
            throw new AppException(ErrorCode.ENTITY_NOT_EXIST);
        }
        entity.setStatus(Status.DELETED);
        userRepository.save(entity);
        return "Delete user id =" + id;
    }

    public UserAdminInfo adminGetProfileByUserId(Integer userId) {
        UserEntity entity = userRepository.findOneById(userId);
        List<RoleEntity> roleEntities = roleRepository.findListRole(userId);
        List<String> listRole = roleEntities.stream().map(e -> e.getRoleName()).collect(Collectors.toList());
        Set<String> listPermission = new HashSet<>();
        for (RoleEntity role : roleEntities) {
            List<ApiEntity> apiTmp = apiRepository.findListApi(role.getId());
            apiTmp.forEach(e -> {
                listPermission.add(e.getPermissionName());
            });
        }
        UserAdminInfo data = new UserAdminInfo();
        AppUtils.copyPropertiesIgnoreNull(entity, data);
        data.setListRoles(listRole);
        data.setListPermissions(listPermission.stream().collect(Collectors.toList()));
        data.setTotalPermissions(listPermission.size());
        return data;
    }

    public UserProfileInfo getProfileByUserId(Integer userId) {
        UserEntity entity = userRepository.findOneById(userId);
        UserProfileInfo userProfileInfo = new UserProfileInfo();
        entity.setPassword("******");
        AppUtils.copyPropertiesIgnoreNull(entity, userProfileInfo);
        return userProfileInfo;
    }



    public UserEntity changeUserInfo(Payload payload, Integer userId, UpdateUserInfoRequest request) {
        //Check role must be STUDENT
//        checkRoleStudent(payload);
        UserEntity entity = userRepository.findOneById(userId);
        if (entity == null) {
            logger.info("User Id [{}] not exist", userId);
            throw new AppException(ErrorCode.ENTITY_NOT_EXIST);
        }
        //Check is owner first
        if (!payload.getUserId().equals(userId)) {
            logger.info("Login userId [{}] is not owner for userId [{}]", payload.getUserId(), userId);
            throw new AppException(ErrorCode.NOT_OWNER_PERMISSION);
        }
        AppUtils.copyPropertiesIgnoreNull(request, entity);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        entity.setUpdatedTime(now);
        entity = userRepository.save(entity);
        logger.info("UserId [{}] change their info success", payload.getUserId());
        entity.setPassword("******");
        return entity;
    }

    public UserEntity changeAdminInfo(Payload payload, Integer userId, UpdateAdminInfoRequest request) {
        //Check role admin
        checkRoleAdmin(payload);
        UserEntity entity = userRepository.findOneById(userId);
        if (entity == null) {
            logger.info("User id [{}] not exist", userId);
            throw new AppException(ErrorCode.ENTITY_NOT_EXIST);
        }
        //Check is owner first
//        if(payload.getUserId() != userId){
//            logger.info("Login userId [{}] is not owner for userId [{}]", payload.getUserId(), userId);
//            throw new AppException(ErrorCode.NOT_OWNER_PERMISSION);
//        }
        Timestamp now = new Timestamp(System.currentTimeMillis());
        AppUtils.copyPropertiesIgnoreNull(request, entity);
        entity.setUpdatedTime(now);
        entity = userRepository.save(entity);
        logger.info("Admin updated user info for userId [{}]", userId);
        entity.setPassword("******");
        return entity;
    }

    public UserEntity changeUserPassword(Payload payload, Integer userId, UpdateUserPasswordRequest request) {
        //Check role must be STUDENT
//        checkRoleStudent(payload);
        UserEntity entity = userRepository.findOneById(userId);
        if (entity == null) {
            logger.info("User id [{}] not exist", userId);
        }
        //Check is owner first
        if (!payload.getUserId().equals(userId)) {
            logger.info("Login userId [{}] is not owner for userId [{}]", payload.getUserId(), userId);
            throw new AppException(ErrorCode.NOT_OWNER_PERMISSION);
        }
        Boolean check = encoder.matches(request.getOldPassword(), entity.getPassword());
        if (check == false) {
            logger.info("Old password is not correct");
            throw new AppException(ErrorCode.USER_NOT_VALID, "Sai mật khẩu ");
        }
        String password = encoder.encode(request.getNewPassword());
        entity.setPassword(password);
        entity.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
        entity = userRepository.save(entity);
        entity.setPassword("******");
        return entity;
    }

    public UserEntity changeAdminPassword(Payload payload, Integer userId, UpdateAdminPasswordRequest request) {
        //Check role admin
        checkRoleAdmin(payload);
        UserEntity entity = userRepository.findOneById(userId);
        if (entity == null) {
            logger.info("User id [{}] not exist", userId);
        }
        //Check is owner first
//       if(payload.getUserId() != userId){
//             logger.info("Login userId [{}] is not owner for userId [{}]", payload.getUserId(), userId);
//            throw new AppException(ErrorCode.NOT_OWNER_PERMISSION);
//        }
        Boolean check = encoder.matches(request.getOldPassword(), entity.getPassword());
        if (check == false) {
            logger.info("Old password is not correct");
            throw new AppException(ErrorCode.USER_NOT_VALID, "Sai mật khẩu ");
        }
        String password = encoder.encode(request.getNewPassword());
        entity.setPassword(password);
        entity.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
        entity = userRepository.save(entity);
        entity.setPassword("******");
        logger.info("Admin updated password for userId [{}]", userId);
        return entity;
    }
}
