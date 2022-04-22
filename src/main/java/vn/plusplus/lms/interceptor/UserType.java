package vn.plusplus.lms.interceptor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserType {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("name")
    private String name;
}