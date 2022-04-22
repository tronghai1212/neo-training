package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.RoleApiEntity;

import java.util.List;

@Repository
public interface RoleApiRepository extends JpaRepository<RoleApiEntity,Integer> {
    List<Integer> findListApiIdByRoleId(Integer roleId);
    List<RoleApiEntity> findAllByRoleId(Integer roleId);
    List<RoleApiEntity> findAllByApiId(Integer apiId);
}
