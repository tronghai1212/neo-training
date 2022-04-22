package vn.plusplus.lms.controller.neo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.neo.request.NumberUserRequest;
import vn.plusplus.lms.controller.request.NewUserRequest;
import vn.plusplus.lms.controller.users.UserController;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.interceptor.Payload;
import vn.plusplus.lms.repository.entities.BrandEntity;
import vn.plusplus.lms.repository.entities.NumberUserEntity;
import vn.plusplus.lms.services.neo.NumberUserService;

@RestController
@RequestMapping(value = "/number-user")
public class NumberUserController {
    @Autowired
    ResponseFactory factory;
    @Autowired
    NumberUserService service;


    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping()
    public ResponseEntity createNumberUser(@RequestBody NumberUserRequest request){
        logger.info("Created NumberUser with numberphone = " + request.getNumberPhone());
        NumberUserEntity data = service.createNumberUser(request);
        return factory.success(data, NumberUserEntity.class);
    }
}
