package vn.plusplus.lms.repository.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.plusplus.lms.repository.entities.enumerates.Status;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(name = "api")
public class ApiEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "name")
	private String name;
	
	@Column(name = "http_method")
	private String httpMethod;

	@Column(name = "pattern")
	private String pattern;

	@Column(name = "permission_name")
	private String permissionName;
	
	@Column(name = "is_required_access_token")
	private Boolean isRequiredAccessToken;
	
	@Column(name="should_check_permission")
	private Boolean shouldCheckPermission;

	@Column(name = "created_time")
	private Timestamp createdTime;

	@Column(name = "updated_time")
	private Timestamp updatedTime;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public Boolean getIsRequiredAccessToken() {
		return isRequiredAccessToken;
	}

	public void setIsRequiredAccessToken(Boolean requiredAccessToken) {
		isRequiredAccessToken = requiredAccessToken;
	}
}
