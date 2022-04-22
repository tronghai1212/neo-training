package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class LoginResponse {
    private String token;
    private Timestamp expiredTime;
}
