package vn.plusplus.lms.services.neo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.plusplus.lms.controller.neo.request.ProfileUserRequest;
import vn.plusplus.lms.controller.neo.request.SetUserProfileRequest;
import vn.plusplus.lms.interceptor.Payload;
import vn.plusplus.lms.repository.BrandRepository;
import vn.plusplus.lms.repository.ProfileUserRepository;
import vn.plusplus.lms.repository.entities.BrandEntity;
import vn.plusplus.lms.repository.entities.ProfileUserEntity;
import vn.plusplus.lms.services.ApiService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfileUserService {
    private static final Logger logger = LoggerFactory.getLogger(ApiService.class);
    Timestamp now = new Timestamp(System.currentTimeMillis());
    @Autowired
    ProfileUserRepository profileUserRepository;

    public ProfileUserEntity createProfileUser(ProfileUserRequest request, Payload payload){
        logger.info("Create profileUser with request [{}]", request);
        ProfileUserEntity entity = new ProfileUserEntity();
        entity.setNameProfile(request.getProfileName());
        entity.setDescription(request.getDescription());
        entity.setUserIdCreated(payload.getUserId());
        entity.setCreatedTime(now);
        entity.setUpdatedTime(now);
        return profileUserRepository.save(entity);
    }

    public List<ProfileUserEntity> setUserProfile(SetUserProfileRequest request){
        logger.info("Set profile user with user id = [{}]", request.getNumberUserId());
        List<ProfileUserEntity> data = new ArrayList<>();
        for (int id:request.getListIdProfiles()){
            ProfileUserEntity entity = profileUserRepository.findOneById(id);
            entity.setNumberUserId(request.getNumberUserId());
            data.add(entity);
        }
        return profileUserRepository.saveAll(data);
    }
}
