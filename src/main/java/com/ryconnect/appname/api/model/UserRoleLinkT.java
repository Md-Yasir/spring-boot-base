package com.ryconnect.appname.api.model;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name="user_role_link_t")
@NamedQuery(name="UserRoleLinkT.findAll", query="SELECT u FROM UserRoleLinkT u")
public class UserRoleLinkT implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="user_role_id")
	private int userRoleId;

	@Column(name="user_id")
	private Integer userId;
	
	//bi-directional many-to-one association to UserT
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName="id", insertable = false, updatable = false)
	private UserT userT;
	
	@Column(name="role_id")
	private Integer roleId;
	
	//bi-directional many-to-one association to UserRoleT
	@ManyToOne
	@JoinColumn(name="role_id", referencedColumnName="role_id", insertable = false, updatable = false)
	private UserRoleT userRoleT;

	public UserRoleLinkT() {
	}

	public Integer getRoleId() {
		return roleId;
	}


	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}


	public int getUserRoleId() {
		return this.userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public UserT getUserT() {
		return this.userT;
	}

	public void setUserT(UserT userT) {
		this.userT = userT;
	}

	public UserRoleT getUserRoleT() {
		return this.userRoleT;
	}

	public void setUserRoleT(UserRoleT userRoleT) {
		this.userRoleT = userRoleT;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	

}