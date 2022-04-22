package vn.plusplus.lms.controller.neo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.neo.request.ProfileUserRequest;
import vn.plusplus.lms.controller.neo.request.SetUserProfileRequest;
import vn.plusplus.lms.controller.users.UserController;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.interceptor.Payload;
import vn.plusplus.lms.repository.entities.BrandEntity;
import vn.plusplus.lms.repository.entities.ProfileUserEntity;
import vn.plusplus.lms.services.neo.NumberUserService;
import vn.plusplus.lms.services.neo.ProfileUserService;

import java.util.List;

@RestController
@RequestMapping(value = "/profile-user")
public class ProfileUserController {
    @Autowired
    ResponseFactory factory;
    @Autowired
    ProfileUserService service;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping()
    public ResponseEntity createProfileUser(@RequestBody ProfileUserRequest request,
                                            @RequestAttribute Payload payload){
        logger.info("Created profileUser name = " + request.getProfileName());
        ProfileUserEntity data = service.createProfileUser(request, payload);
        return factory.success(data, ProfileUserEntity.class);
    }

    @PutMapping()
    public ResponseEntity setUserProfile(@RequestBody SetUserProfileRequest request){
        logger.info("set user profile id with user number id  = " + request.getNumberUserId());
        List<ProfileUserEntity> data = service.setUserProfile(request);
        return factory.success(data, List.class);
    }

}
