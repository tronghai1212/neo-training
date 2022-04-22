package vn.plusplus.lms.interceptor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter @NoArgsConstructor
public class Payload {
    private Integer userId;

    private String accessToken;

    private UserTypeEnum userType;

    private List<Integer> permissions;

    private Timestamp expiredTime;

    private List<String> roles = new ArrayList<>();
/*
    private List<String> permissions;*/
}
