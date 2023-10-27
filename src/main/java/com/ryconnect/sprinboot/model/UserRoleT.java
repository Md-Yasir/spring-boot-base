package com.ryconnect.sprinboot.model;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name="user_role_t")
@NamedQuery(name="UserRoleT.findAll", query="SELECT u FROM UserRoleT u")
public class UserRoleT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="role_id")
	private Integer roleId;

	@Column(nullable = false, columnDefinition = "TINYINT(1)")
	private boolean active;

	private String description;

	@Column(name="role_name")
	private String roleName;
	
	//bi-directional many-to-one association to UserRoleLinkT
	@OneToMany(mappedBy="userRoleT")
	private List<UserRoleLinkT> userRoleLinkTs;

	public UserRoleT() {
	}
	
	public List<UserRoleLinkT> getUserRoleLinkTs() {
		return userRoleLinkTs;
	}

	public void setUserRoleLinkTs(List<UserRoleLinkT> userRoleLinkTs) {
		this.userRoleLinkTs = userRoleLinkTs;
	}

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public boolean getActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}