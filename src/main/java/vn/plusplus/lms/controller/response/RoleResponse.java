package vn.plusplus.lms.controller.response;

import lombok.Getter;
import lombok.Setter;
import vn.plusplus.lms.repository.entities.ApiEntity;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class RoleResponse {
    private String roleName;
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private List<ApiEntity> listApi;
}
