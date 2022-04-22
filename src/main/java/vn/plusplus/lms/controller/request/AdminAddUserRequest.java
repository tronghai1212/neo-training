package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;
import vn.plusplus.lms.repository.entities.enumerates.Status;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter @Setter
public class AdminAddUserRequest {
    @NotNull
    private String userName;
    @NotNull
    private String password;
    private Status status;
    private String fullName;
    private String phone;
    private String avatar;
    private String email;
    private String address;

    @NotNull
    private List<Integer> roleIds;
}
