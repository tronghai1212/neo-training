package vn.plusplus.lms.controller.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.request.LoginResponse;
import vn.plusplus.lms.controller.request.LoginUserRequest;
import vn.plusplus.lms.controller.request.NewUserRequest;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.model.UserProfileInfo;
import vn.plusplus.lms.interceptor.Payload;
import vn.plusplus.lms.repository.UserRepository;
import vn.plusplus.lms.repository.entities.UserEntity;
import vn.plusplus.lms.services.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    ResponseFactory factory;

    @Autowired
    UserRepository userRepository;



    @GetMapping(value = "/profile")
    public ResponseEntity getUserProfile(@RequestAttribute Payload payload) {
        logger.info("Get profile user [{}]", payload.getUserId());
        UserProfileInfo data = userService.getProfileByUserId(payload.getUserId());
        return factory.success(data, UserProfileInfo.class);
    }

    @PostMapping(value = "/login")
    public ResponseEntity loginUser(@RequestBody LoginUserRequest request) {
        logger.info("Login with user's name: " + request.getUserName());
        LoginResponse data = userService.loginUser(request);
        return factory.success(data, LoginResponse.class);
    }

    @PostMapping(value = "/register")
    public ResponseEntity registerUser(@RequestBody NewUserRequest request) {
        logger.info("Register user " + request.getUserName());
        UserEntity data = userService.registerUser(request);
        return factory.success(data, UserEntity.class);
    }



}
