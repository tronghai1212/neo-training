package vn.plusplus.lms.controller.request;

import lombok.Getter;
import lombok.Setter;
import vn.plusplus.lms.repository.entities.enumerates.Status;

import java.util.List;

@Getter
@Setter
public class UpdateRoleRequest {
    private String roleName;
    private Status status;
    private List<Integer> listApiIds;
}
