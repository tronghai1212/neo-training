package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateUserInfoRequest {
    private String fullName;
    private String avatar;
    private String phone;
    private String email;
    private String address;

    @Override
    public String toString() {
        return "UpdateUserInfoRequest{" +
                "fullName='" + fullName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
