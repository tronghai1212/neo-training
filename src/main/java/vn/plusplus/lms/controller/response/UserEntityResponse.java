package vn.plusplus.lms.controller.response;

import lombok.Getter;
import lombok.Setter;
import vn.plusplus.lms.repository.entities.enumerates.Status;

import java.sql.Timestamp;

@Getter @Setter
public class UserEntityResponse {
    private Integer id;
    private String fullName;
    private String avatar;
    private String phone;
    private String email;
    private String address;
    private String userName;
    private Integer achievement;
    private Integer star;
    private Integer commissionPercentage;
    private Status status;
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private String roles;
}
