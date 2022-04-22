package vn.plusplus.lms.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.controller.request.NewUserRoleRequest;
import vn.plusplus.lms.controller.request.UpdateUserRoleRequest;
import vn.plusplus.lms.repository.UserRoleRepository;
import vn.plusplus.lms.repository.entities.UserRoleEntity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Service
public class UserRoleService extends BaseSearchService<UserRoleEntity> {
    @Autowired
    UserRoleRepository userRoleRepository;

    public UserRoleEntity addUserRole(NewUserRoleRequest request) {
        UserRoleEntity entity = new UserRoleEntity();

        entity.setUserId(request.getUserId());
        entity.setRoleId(request.getRoleId());
        entity.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
        entity.setUpdatedTime(Timestamp.valueOf(LocalDateTime.now()));

        return userRoleRepository.save(entity);
    }

    public UserRoleEntity updateUserRole(Integer id, UpdateUserRoleRequest request) {
        UserRoleEntity entity = userRoleRepository.findOneById(id);
        if (entity == null) {
            entity = new UserRoleEntity();
            entity.setCreatedTime(Timestamp.valueOf(LocalDateTime.now()));
        }
        if (request.getUserId() != null) {
            entity.setUserId(request.getUserId());
        }
        if (request.getRoleId() != null) {
            entity.setRoleId(request.getRoleId());
        }
        entity.setUpdatedTime(Timestamp.valueOf(LocalDateTime.now()));

        return userRoleRepository.save(entity);
    }

    public String deleteUserRole(Integer id) {
        userRoleRepository.deleteById(id);
        return "Deleted user role";
    }

    public UserRoleEntity getUserRoleById(Integer id) {
        return userRoleRepository.findOneById(id);
    }
}
