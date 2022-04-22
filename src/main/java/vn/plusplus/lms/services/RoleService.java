package vn.plusplus.lms.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.controller.response.RoleResponse;
import vn.plusplus.lms.repository.entities.UserRoleEntity;
import vn.plusplus.lms.repository.entities.enumerates.Status;
import vn.plusplus.lms.config.AppConstants;
import vn.plusplus.lms.controller.request.NewRoleRequest;
import vn.plusplus.lms.controller.request.UpdateRoleRequest;
import vn.plusplus.lms.exceptions.AppException;
import vn.plusplus.lms.exceptions.ErrorCode;
import vn.plusplus.lms.repository.ApiRepository;
import vn.plusplus.lms.repository.RoleApiRepository;
import vn.plusplus.lms.repository.RoleRepository;
import vn.plusplus.lms.repository.UserRoleRepository;
import vn.plusplus.lms.repository.entities.ApiEntity;
import vn.plusplus.lms.repository.entities.RoleApiEntity;
import vn.plusplus.lms.repository.entities.RoleEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleService extends BaseSearchService<RoleEntity> {
    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoleApiRepository roleApiRepository;
    @Autowired
    ApiRepository apiRepository;
    @Autowired
    UserRoleRepository userRoleRepository;

    public RoleEntity addRole(NewRoleRequest request,List<String> roles) {
        if(roles.size() == 0 || !roles.contains(AppConstants.ROLE.SUPER_ADMIN)){
            logger.info("Bạn không có quyền thực thi");
            throw new AppException(ErrorCode.USER_HAS_NO_PERMISSION);
        }
        RoleEntity entity = new RoleEntity();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        entity.setRoleName(request.getRoleName());
        entity.setCreatedTime(now);
        entity.setUpdatedTime(now);
        entity.setStatus(request.getStatus());

        roleRepository.save(entity);

        if (request.getListApiIds() != null && !request.getListApiIds().isEmpty()) {
            List<RoleApiEntity> listRoleApi = new ArrayList<>();

            for (Integer api_id : request.getListApiIds()) {
                RoleApiEntity roleApi = new RoleApiEntity();

                roleApi.setRoleId(entity.getId());
                roleApi.setApiId(api_id);
                roleApi.setCreatedTime(now);
                roleApi.setUpdatedTime(now);

                listRoleApi.add(roleApi);
            }
            logger.info("Add permission for role id [{}]", entity.getId());
            roleApiRepository.saveAll(listRoleApi);
        }
        return entity;
    }

    public RoleEntity updateRole(Integer roleId, UpdateRoleRequest request,List<String> roles) {
        if(roles.size() == 0 || !roles.contains(AppConstants.ROLE.SUPER_ADMIN)){
            logger.info("Bạn không có quyền thực thi");
            throw new AppException(ErrorCode.USER_HAS_NO_PERMISSION);
        }
        RoleEntity entity = roleRepository.findOneById(roleId);
        if (entity == null) {
            throw new AppException(ErrorCode.ENTITY_NOT_EXIST);
        }
        BeanUtils.copyProperties(request, entity);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        entity.setUpdatedTime(now);

        roleRepository.save(entity);

        if (request.getListApiIds() != null && !request.getListApiIds().isEmpty()) {
            List<RoleApiEntity> oldListApiId = roleApiRepository.findAllByRoleId(roleId);
            oldListApiId.forEach(e -> {
                if (!request.getListApiIds().contains(e)) {
                    roleApiRepository.delete(e);
                }
            });
            List<RoleApiEntity> roleApiEntityList = new ArrayList<>();
            request.getListApiIds().forEach(e -> {
                if (!oldListApiId.contains(e)) {
                    RoleApiEntity roleApi = new RoleApiEntity();

                    roleApi.setRoleId(entity.getId());
                    roleApi.setApiId(e);
                    roleApi.setCreatedTime(now);
                    roleApi.setCreatedTime(now);
                    roleApiEntityList.add(roleApi);
                }
                roleApiRepository.saveAll(roleApiEntityList);
            });
        }
        return entity;
    }

    public String deleteRole(Integer roleId,List<String> roles) {
        if(roles.size() == 0 || !roles.contains(AppConstants.ROLE.SUPER_ADMIN)){
            logger.info("Bạn không có quyền thực thi");
            throw new AppException(ErrorCode.USER_HAS_NO_PERMISSION);
        }
        RoleEntity entity = roleRepository.findOneById(roleId);
        if (entity == null) {
            throw new AppException(ErrorCode.ENTITY_NOT_EXIST);
        }
        List<RoleApiEntity> listRoleApi = roleApiRepository.findAllByRoleId(roleId);
        if (listRoleApi != null && !listRoleApi.isEmpty()) {
            listRoleApi.forEach(e -> {
                roleApiRepository.delete(e);
            });
        }
        List<UserRoleEntity> userRoles = userRoleRepository.findAllByRoleId(roleId);
        userRoles.forEach(e -> {
            userRoleRepository.delete(e);
        });
        entity.setStatus(Status.DELETED);
        entity.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
        roleRepository.save(entity);
        return "DELETED";
    }

    public RoleResponse adminGetRoleDetail(Integer roleId) {
        RoleEntity entity = roleRepository.findOneById(roleId);
        if (entity == null) {
            throw new AppException(ErrorCode.ENTITY_NOT_EXIST);
        }
        List<ApiEntity> listApi = apiRepository.findListApi(roleId);
        RoleResponse response = new RoleResponse();
        BeanUtils.copyProperties(entity, response);
        response.setListApi(listApi);
        return response;
    }

    public Map<Integer, String> getListRoleForUserIds(List<Integer> userIds){
        Map<Integer, String> response = new HashMap<>();
        for(Integer id : userIds){
            List<RoleEntity> role = roleRepository.findListRole(id);
            String roleList = role.stream().map(e -> e.getRoleName()).collect(Collectors.joining(","));
            response.put(id, roleList);
        }
        return response;
    }
}
