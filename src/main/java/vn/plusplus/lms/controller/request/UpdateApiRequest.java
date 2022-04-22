package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;
import vn.plusplus.lms.repository.entities.enumerates.Status;

@Getter @Setter
public class UpdateApiRequest {
    private Integer id;
    private String name;
    private String httpMethod;
    private String pattern;
    private Boolean isRequiredAccessToken;
    private Boolean shouldCheckPermission;
    private Status status;

    @Override
    public String toString() {
        return "UpdateApiRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", httpMethod='" + httpMethod + '\'' +
                ", pattern='" + pattern + '\'' +
                ", isRequiredAccessToken=" + isRequiredAccessToken +
                ", shouldCheckPermission=" + shouldCheckPermission +
                '}';
    }
}
