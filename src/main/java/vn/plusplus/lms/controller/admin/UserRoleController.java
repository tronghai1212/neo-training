package vn.plusplus.lms.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.request.NewUserRoleRequest;
import vn.plusplus.lms.controller.request.UpdateUserRoleRequest;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.repository.entities.UserRoleEntity;
import vn.plusplus.lms.services.UserRoleService;

@RestController
@RequestMapping("/admin/user_role")
public class UserRoleController extends BaseSearchController<UserRoleEntity>{
    private static final Logger logger = LoggerFactory.getLogger(UserRoleController.class);

    @Autowired
    UserRoleService service;
    @Autowired
    ResponseFactory factory;

    @PostMapping
    public ResponseEntity addUserRole(@RequestBody NewUserRoleRequest request){
        UserRoleEntity data = service.addUserRole(request);
        return factory.success(data,UserRoleEntity.class);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity updateUserRole(@PathVariable(name = "id") Integer id,
                                         @RequestBody UpdateUserRoleRequest request){
        UserRoleEntity data = service.updateUserRole(id , request);
        return factory.success(data,UserRoleEntity.class);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteUserRole(@PathVariable(name = "id") Integer id){
        String data = service.deleteUserRole(id);
        return factory.success(data,String.class);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity getUserRole(@PathVariable(name = "id") Integer id){
        UserRoleEntity data = service.getUserRoleById(id);
        return factory.success(data,UserRoleEntity.class);
    }

}
