package vn.plusplus.lms.model;

import lombok.Getter;
import lombok.Setter;
import vn.plusplus.lms.repository.entities.enumerates.Status;

import java.sql.Timestamp;


@Getter
@Setter
public class UserProfileInfo {
    private Integer id;
    private String fullName;
    private String avatar;
    private String phone;
    private String email;
    private String address;
    private String userName;
    private String password;
    private Integer achievement;
    private Long wallet;
    private Integer star;
    private Status status;
    private Timestamp createdTime;
    private Timestamp updatedTime;
}
