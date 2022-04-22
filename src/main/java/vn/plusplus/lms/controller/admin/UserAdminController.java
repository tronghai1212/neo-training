package vn.plusplus.lms.controller.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.request.AdminAddUserRequest;
import vn.plusplus.lms.controller.request.AdminUpdateUserRequest;
import vn.plusplus.lms.controller.request.LoginResponse;
import vn.plusplus.lms.controller.request.LoginUserRequest;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.repository.entities.UserEntity;
import vn.plusplus.lms.services.RoleService;
import vn.plusplus.lms.services.UserService;

@RestController
@RequestMapping(value = "/admin/user")
public class UserAdminController extends BaseSearchController<UserEntity> {

    private static final Logger logger = LoggerFactory.getLogger(UserAdminController.class);

    @Autowired
    UserService userService;
    @Autowired
    ResponseFactory factory;

    @Autowired
    RoleService roleService;

//    @RequestMapping(method = RequestMethod.GET, value = "/search")
//    public ResponseEntity findAllByRsql(
//            @RequestParam(value = "query", required = false) String query,
//            @RequestParam(value = "size", required = false) Integer size,
//            @RequestParam(value = "page", required = false) Integer page,
//            @RequestParam(value = "sort", required = false) String sort,
//            @RequestParam(value = "direction", required = false) String direction) throws UnsupportedEncodingException {
//
//        logger.info("query= " + query);
//        if (StringUtils.isBlank(sort)) {
//            sort = "updatedTime";
//        }
//        if (StringUtils.isBlank(direction)) {
//            direction = Sort.Direction.DESC.name();
//        }
//
//        query = URLDecoder.decode(query, StandardCharsets.UTF_8.name());
//        if (query.trim().isEmpty()) {
//            query += "status!=DELETED";
//        } else query += ";status!=DELETED";
//        PageRequest pageRequest = service.getPageRequestParam(size, page, true, sort, direction);
//        Page pageResult = service.findAllByRsql(query, pageRequest);
//        List<Object> collect = pageResult.getContent();
//        List<Integer> userIds = collect.stream().map(e -> ((UserEntity) e).getId()).collect(Collectors.toList());
//        Map<Integer, String> roleMap = roleService.getListRoleForUserIds(userIds);
//        List<UserEntityResponse> userEntities = new ArrayList<>();
//        collect.forEach(e -> {
//            UserEntity user = (UserEntity) e;
//            UserEntityResponse item = new UserEntityResponse();
//            AppUtils.copyPropertiesIgnoreNull(user, item);
//            String role = roleMap.get(user.getId());
//            item.setRoles(role);
//            userEntities.add(item);
//        });
//        PageResponse pageResponse = PageResponseUtil.buildPageResponse(pageResult);
//        return factory.successUser(userEntities, pageResponse);
//    }

    @DeleteMapping(value = "/{user_id}")
    public ResponseEntity deleteUser(@PathVariable(name = "user_id") Integer userId) {
        logger.info("Delete user [{}]", userId);
        String data = userService.deleteUserById(userId);
        return factory.success(data, String.class);
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody AdminAddUserRequest request) {
        logger.info("Admin add user [{}]", request.getUserName());
        UserEntity data = userService.adminAddUser(request);
        return factory.success(data, UserEntity.class);
    }



    @PutMapping(value = "/{user_id}")
    public ResponseEntity adminUpdateUser(@PathVariable(name = "user_id") Integer userId,
                                          @RequestBody AdminUpdateUserRequest request) {
        logger.info("Admin update userId detail [{}]", userId);
        UserEntity response = userService.adminUpdateUser(userId, request);
        return factory.success(response, UserEntity.class);
    }


    @PostMapping(value = "/login")
    public ResponseEntity loginAdminUser(@RequestBody LoginUserRequest request) {
        logger.info("System user login username [{}] " + request.getUserName());
        LoginResponse data = userService.loginAdminUser(request);
        return factory.success(data, LoginResponse.class);
    }

}
