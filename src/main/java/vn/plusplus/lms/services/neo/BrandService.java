package vn.plusplus.lms.services.neo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.config.AppConstants;
import vn.plusplus.lms.controller.neo.request.BrandRequest;
import vn.plusplus.lms.exceptions.AppException;
import vn.plusplus.lms.exceptions.ErrorCode;
import vn.plusplus.lms.interceptor.Payload;
import vn.plusplus.lms.repository.BrandRepository;
import vn.plusplus.lms.repository.entities.BrandEntity;
import vn.plusplus.lms.services.ApiService;

import java.sql.Timestamp;

@Service
public class BrandService {
    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);
    Timestamp now = new Timestamp(System.currentTimeMillis());
    @Autowired
    BrandRepository brandRepository;


    public BrandEntity createBrand(BrandRequest request, Payload payload){

        logger.info("Create brand with request [{}]", request);
        BrandEntity entity = new BrandEntity();
        entity.setBrandName(request.getBrandName());
        entity.setDescription(request.getDescription());
        entity.setUserIdCreated(payload.getUserId());
        entity.setCreatedTime(now);
        entity.setUpdatedTime(now);
        return brandRepository.save(entity);
    }

    public BrandEntity updateBrand(BrandRequest request, Payload payload, Integer brandId){
        logger.info("Update brand id ="+ brandId +" with request [{}]", request);
        BrandEntity entity = brandRepository.findOneById(brandId);
        if (payload.getUserId() != entity.getUserIdCreated()&& !payload.getRoles().contains(AppConstants.ROLE.SUPER_ADMIN)){
            logger.info("Bạn không có quyền thực thi");
            throw new AppException(ErrorCode.USER_HAS_NO_PERMISSION);
        }
        entity.setBrandName(request.getBrandName());
        entity.setDescription(request.getDescription());
        entity.setUserIdCreated(payload.getUserId());
        entity.setUpdatedTime(now);
        return brandRepository.save(entity);
    }
}
