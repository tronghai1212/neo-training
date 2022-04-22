package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateAdminPasswordRequest {
    private String oldPassword;
    private String newPassword;
}
