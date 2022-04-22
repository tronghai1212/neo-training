package vn.plusplus.lms.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class UserAdminInfo {
    private Integer id;
    private String userName;
    private String fullName;
    private String avatar;
    private String phone;
    private String email;
    private List<String> listRoles;
    private Integer totalPermissions;
    private List<String> listPermissions;
}
