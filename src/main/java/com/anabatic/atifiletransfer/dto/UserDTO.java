package com.anabatic.atifiletransfer.dto;

import java.util.List;

public class UserDTO {

	private Long userId;
	
	private String username;
	
	private String password;
	
	private Boolean userStatus;
	
	private List<CompanyDTO> companies;

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

	public List<CompanyDTO> getCompanies() {
		return companies;
	}

	public void setCompanies(List<CompanyDTO> companies) {
		this.companies = companies;
	}
	
}
