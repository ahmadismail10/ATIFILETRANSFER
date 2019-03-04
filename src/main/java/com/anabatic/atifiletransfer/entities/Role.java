package com.anabatic.atifiletransfer.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Type;

@Entity
@SuppressWarnings("serial")
public class Role implements Serializable {
	
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long roleId;
	
	@Column(nullable = false, length = 15)
	private String roleName;

	@Column(nullable = false)
	@Type(type = "text")
	private String roleDescription;

	@OneToMany(mappedBy = "role")
	private List<User> users;

	public Role(String roleName, String roleDescription) {
		this.roleName = roleName;
		this.roleDescription = roleDescription;
	}
	
	public Role() {
		
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDescription() {
		return roleDescription;
	}

	public void setRoleDescription(String roleDescription) {
		this.roleDescription = roleDescription;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
