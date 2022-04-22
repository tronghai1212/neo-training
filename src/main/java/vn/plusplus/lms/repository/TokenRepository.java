package vn.plusplus.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.plusplus.lms.repository.entities.TokenEntity;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity,Integer> {
    TokenEntity findOneById(Integer id);
    TokenEntity findOneByUserId(Integer userId);
    TokenEntity findOneByToken(String token);
}
