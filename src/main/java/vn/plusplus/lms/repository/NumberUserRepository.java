package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.plusplus.lms.repository.entities.NumberUserEntity;

public interface NumberUserRepository extends JpaRepository<NumberUserEntity, Integer> {

}
