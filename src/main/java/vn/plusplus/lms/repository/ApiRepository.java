package vn.plusplus.lms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.ApiEntity;

import java.util.List;

@Repository
public interface ApiRepository extends JpaRepository<ApiEntity, Integer> , JpaSpecificationExecutor<ApiEntity> {
    ApiEntity findOneById(Integer id);

    @Query(nativeQuery = true,value = "SELECT * FROM `api` JOIN `role_api` ON `api`.`id` = `api_id` WHERE `role_id` = :roleId")
    List<ApiEntity> findListApi(@Param("roleId") Integer roleId);


}
