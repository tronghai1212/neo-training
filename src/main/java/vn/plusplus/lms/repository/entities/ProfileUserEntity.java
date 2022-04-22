package vn.plusplus.lms.repository.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "profile_user")
public class ProfileUserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name_profile")
    private String nameProfile;

    @Column(name = "description")
    private String description;

    @Column(name = "user_id_created")
    private Integer userIdCreated;

    @Column(name = "created_time")
    private Timestamp createdTime;

    @Column(name = "updated_time")
    private Timestamp updatedTime;

    @Column(name = "number_user_id")
    private Integer numberUserId;
}
