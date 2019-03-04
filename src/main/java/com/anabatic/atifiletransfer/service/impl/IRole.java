package com.anabatic.atifiletransfer.service.impl;

import java.util.List;

import com.anabatic.atifiletransfer.entities.Role;

public interface IRole {

	public List<Role> findAllRole();
	
	public Role findOneRole(Long roleId);
	
	public void saveRole(Role role);
	
	public void editRole(Role role);
	
	public void deleteRoleById(Long roleId);
	
}
