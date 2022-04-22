package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;
import vn.plusplus.lms.repository.entities.enumerates.Status;

import java.util.List;

@Getter @Setter
public class AdminUpdateUserRequest {
    private String password;
    private Status status;
    private String fullName;
    private String phone;
    private String avatar;
    private String email;
    private String address;
    private List<Integer> roleIds;
}
