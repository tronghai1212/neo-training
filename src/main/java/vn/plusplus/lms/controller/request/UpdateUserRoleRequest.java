package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateUserRoleRequest {
    private Integer roleId;
    private Integer userId;
}
