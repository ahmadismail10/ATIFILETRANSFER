package com.anabatic.atifiletransfer.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

@Entity
@SuppressWarnings("serial")
public class User implements Serializable {

	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userId;
	
	@Column(nullable = false, length = 15, unique = true)
	private String username;
	
	@Column(nullable = false, length = 75)
	@NotNull
	private String password;
	
	@Column(nullable = false)
	private Boolean userStatus;

	@OneToMany(mappedBy="user")
	private List<Company> companies;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Role role;

	@PrePersist
	public void prePersist() {
	    if(userStatus == null) 
	    	userStatus = false;
	}
	
	public User() {
		
	}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Boolean userStatus) {
		this.userStatus = userStatus;
	}

	public List<Company> getCompanies() {
		return companies;
	}

	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}
