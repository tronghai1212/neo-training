package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateUserPasswordRequest {
    private String oldPassword;
    private String newPassword;
}
