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
@Table(name = "number_user")
public class NumberUserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number_phone")
    private String numberPhone;

    @Column(name = "time_registration_sv")
    private Date timeRegistrationSv;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name="sex")
    private Boolean sex;

    @Column(name = "brand_id")
    private Integer brandId;


}
