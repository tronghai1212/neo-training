package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.enumerates.Status;
import vn.plusplus.lms.repository.entities.UserEntity;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {
    UserEntity findOneByUserName(String userName);
    UserEntity findOneByEmail(String email);
    UserEntity findOneById(Integer id);
//
//    @Query(nativeQuery = true,value = "SELECT * FROM `user` \n" +
//            "JOIN `user_course` ON `user`.id = `user_course`.user_id\n" +
//            "WHERE `user_course`.type = 'NO_ROOM' AND `user`.status='ACTIVE'" +
//            "AND `user_course`.course_id = (SELECT course_id FROM room WHERE id = :roomId);")
//    List<UserEntity> getAllUserForRoom(@Param("roomId") Integer roomId);

    List<UserEntity> findAllByIdInAndStatus(List<Integer> userIds, Status status);

    List<UserEntity> findAllByIdIn(List<Integer> userIds);
//
//    @Query(nativeQuery = true,value = "SELECT * FROM `user` \n" +
//            "JOIN `user_role` ON `user`.id = user_id\n" +
//            "JOIN `role` ON `user_role`.role_id = `role`.id WHERE role_name <> 'STUDENT';")
//    List<UserEntity> getAllUserMentor();
//
//    @Query(nativeQuery = true,value = "SELECT * FROM `user` \n" +
//            "JOIN `user_role` ON `user`.id = user_id\n" +
//            "JOIN `role` ON `user_role`.role_id = `role`.id WHERE role_name = 'STUDENT' AND `user`.status ='ACTIVE'")
//    List<UserEntity> getAllUserStudent();
//
//    UserEntity findByUserNameAndAccountType(String uuid, AccountType type);
//
//    @Query(nativeQuery = true, value = "SELECT count(*) FROM `user` WHERE type = \"STUDENT\" AND status = \"ACTIVE\"")
//    Integer totalStudent();
//    @Query(nativeQuery = true, value = "SELECT count(*) FROM `user` WHERE type = \"MENTOR\" AND status = \"ACTIVE\"")
//    Integer totalMentor();
}
