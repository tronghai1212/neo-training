package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.RoleEntity;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Integer> , JpaSpecificationExecutor<RoleEntity> {
    @Query(nativeQuery = true,value = "SELECT * FROM `role` JOIN `user_role` ON `role`.`id` = `role_id` WHERE `user_id` = :userId")
    List<RoleEntity> findListRole(@Param("userId") Integer userId);

    @Query(nativeQuery = true,value = "SELECT role_name FROM `role` JOIN `user_role` ON `role`.`id` = `role_id` WHERE `user_id` = :userId")
    List<String> findListRoleName(@Param("userId") Integer userId);

    RoleEntity findOneById(Integer roleName);

    RoleEntity findOneByRoleName(String roleName);

    List<RoleEntity> findAllByIdIn(List<Integer> ids);
}
