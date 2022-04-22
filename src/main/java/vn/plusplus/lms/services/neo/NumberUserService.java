package vn.plusplus.lms.services.neo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.controller.neo.request.NumberUserRequest;
import vn.plusplus.lms.interceptor.Payload;
import vn.plusplus.lms.repository.BrandRepository;
import vn.plusplus.lms.repository.NumberUserRepository;
import vn.plusplus.lms.repository.entities.NumberUserEntity;
import vn.plusplus.lms.services.ApiService;

import java.sql.Timestamp;

@Service
public class NumberUserService {
    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);
    Timestamp now = new Timestamp(System.currentTimeMillis());
    @Autowired
    NumberUserRepository numberUserRepository;

    public NumberUserEntity createNumberUser(NumberUserRequest request){
        logger.info("Create numberUser with request [{}]", request);
        NumberUserEntity entity = new NumberUserEntity();
        entity.setNumberPhone(request.getNumberPhone());
        entity.setSex(request.getSex());
        entity.setDateOfBirth(request.getDateOfBirth());
        entity.setTimeRegistrationSv(request.getTimeRegistration());
        entity.setBrandId(request.getBrandId());
        return numberUserRepository.save(entity);
    }
}
