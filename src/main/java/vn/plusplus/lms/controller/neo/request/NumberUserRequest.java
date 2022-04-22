package vn.plusplus.lms.controller.neo.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NumberUserRequest {
    private String numberPhone;
    private Date timeRegistration;
    private Date dateOfBirth;
    private Boolean sex;
    private Integer brandId;

}
