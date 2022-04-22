package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.plusplus.lms.repository.entities.BrandEntity;

public interface BrandRepository extends JpaRepository<BrandEntity, Integer> {

    BrandEntity findOneById(Integer id);
}
