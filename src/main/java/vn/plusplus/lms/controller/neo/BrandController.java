package vn.plusplus.lms.controller.neo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.plusplus.lms.controller.neo.request.BrandRequest;
import vn.plusplus.lms.controller.request.NewUserRequest;
import vn.plusplus.lms.controller.users.UserController;
import vn.plusplus.lms.factory.ResponseFactory;
import vn.plusplus.lms.interceptor.Payload;
import vn.plusplus.lms.repository.entities.BrandEntity;
import vn.plusplus.lms.repository.entities.UserEntity;
import vn.plusplus.lms.services.neo.BrandService;

import java.sql.ResultSet;

@RestController
@RequestMapping(value = "/brand")
public class BrandController {

    @Autowired
    BrandService brandService;
    @Autowired
    ResponseFactory factory;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @PostMapping()
    public ResponseEntity createBrand(@RequestBody BrandRequest request,
                                       @RequestAttribute Payload payload) {
        logger.info("Created brand_name = " + request.getBrandName());
        BrandEntity data = brandService.createBrand(request,payload);
        return factory.success(data, BrandEntity.class);
    }

    @PutMapping("/{brandId}")
    public ResponseEntity updateBrand(@RequestBody BrandRequest request,
                                      @RequestAttribute Payload payload,
                                      @PathVariable("brandId") Integer brandId){
        logger.info("Updated brand_id = " + brandId);
        BrandEntity data = brandService.updateBrand(request,payload, brandId);
        return factory.success(data, BrandEntity.class);
    }
}
