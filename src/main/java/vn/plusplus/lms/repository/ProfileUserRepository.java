package vn.plusplus.lms.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import vn.plusplus.lms.repository.entities.ProfileUserEntity;

public interface ProfileUserRepository extends JpaRepository<ProfileUserEntity, Integer> {

    ProfileUserEntity findOneById(Integer id);
}
