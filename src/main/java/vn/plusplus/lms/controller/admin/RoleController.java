package vn.plusplus.lms.controller.admin;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.request.NewRoleRequest;
import vn.plusplus.lms.controller.request.UpdateRoleRequest;
import vn.plusplus.lms.controller.response.RoleResponse;
import vn.plusplus.lms.factory.PageResponse;
import vn.plusplus.lms.factory.PageResponseUtil;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.repository.entities.RoleEntity;
import vn.plusplus.lms.services.RoleService;
import vn.plusplus.lms.interceptor.Payload;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping(value = "/admin/role")
public class RoleController extends BaseSearchController<RoleEntity> {
    private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    RoleService service;
    @Autowired
    ResponseFactory factory;

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity findAllByRsql(
            @RequestParam(value = "query", required = false) String query,
            @RequestParam(value = "size", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "sort", required = false) String sort,
            @RequestParam(value = "direction", required = false) String direction) throws UnsupportedEncodingException {

        logger.info("query= " + query);
        if (StringUtils.isBlank(sort)) {
            sort = "updatedTime";
        }
        if (StringUtils.isBlank(direction)) {
            direction = Sort.Direction.DESC.name();
        }
        query = URLDecoder.decode(query, StandardCharsets.UTF_8.name());
        if (query.trim().isEmpty()) {
            query += "status!=DELETED";
        } else query += ";status!=DELETED";
        PageRequest pageRequest = service.getPageRequestParam(size, page, true, sort, direction);
        Page pageResult = service.findAllByRsql(query, pageRequest);
        List<Object> collect = pageResult.getContent();
        PageResponse pageResponse = PageResponseUtil.buildPageResponse(pageResult);
        ResponseEntity responseEntity = ResponseFactory.success(collect, pageResponse);
        return responseEntity;
    }

    @PostMapping
    public ResponseEntity addRole(@RequestBody NewRoleRequest request,
                                  @RequestAttribute Payload payload){
        logger.info("Add new role [{}]",request.getRoleName());
        RoleEntity data = service.addRole(request,payload.getRoles());
        return factory.success(data,RoleEntity.class);
    }

    @PutMapping(value = "/{roleId}")
    public ResponseEntity updateRole(@PathVariable("roleId") Integer roleId,
                                     @RequestBody UpdateRoleRequest request,
                                     @RequestAttribute Payload payload){
        logger.info("Update role ID [{}]",roleId);
        RoleEntity data = service.updateRole(roleId,request, payload.getRoles());
        return factory.success(data,RoleEntity.class);
    }

    @GetMapping("/{roleId}")
    public  ResponseEntity getRoleDetail(@PathVariable("roleId") Integer roleId){
        logger.info("Get role detail Id [{}]",roleId);
        RoleResponse data = service.adminGetRoleDetail(roleId);
        return factory.success(data,RoleResponse.class);
    }

    @DeleteMapping("/{roleId}")
    public  ResponseEntity deleteRole(@PathVariable("roleId") Integer roleId,
                                      @RequestAttribute Payload payload) {
        logger.info("Delete role Id [{}]",roleId);
        String data = service.deleteRole(roleId, payload.getRoles());
        return factory.success(data,String.class);
    }

}

