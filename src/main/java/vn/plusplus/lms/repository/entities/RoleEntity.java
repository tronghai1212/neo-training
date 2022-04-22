package vn.plusplus.lms.repository.entities;

import lombok.Getter;
import lombok.Setter;
import vn.plusplus.lms.repository.entities.enumerates.Status;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "role")
@Getter
@Setter
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "updated_time")
    private Timestamp updatedTime;
}
