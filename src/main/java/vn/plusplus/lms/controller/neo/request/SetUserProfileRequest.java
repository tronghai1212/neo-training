package vn.plusplus.lms.controller.neo.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SetUserProfileRequest {

    private List<Integer> listIdProfiles;
    private Integer NumberUserId;
}
