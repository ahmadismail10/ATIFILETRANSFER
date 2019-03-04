package com.anabatic.atifiletransfer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.anabatic.atifiletransfer.entities.Role;
import com.anabatic.atifiletransfer.repository.RoleDao;
import com.anabatic.atifiletransfer.service.impl.IRole;

@Service
public class RoleService implements IRole {
	
	@Autowired
	RoleDao roleDao;

	@Override
	public List<Role> findAllRole() {
		return roleDao.findAll();
	}

	@Override
	public Role findOneRole(Long roleId) {
		return roleDao.findOne(roleId);
	}

	@Override
	public void saveRole(Role role) {
		roleDao.saveAndFlush(role);
	}

	@Modifying
	@Override
	public void editRole(Role role) {
		Role roleTemp = roleDao.findOne(role.getRoleId());
		roleTemp.setRoleName(role.getRoleName());
		roleTemp.setRoleDescription(role.getRoleDescription());
		roleDao.saveAndFlush(roleTemp);
	}

	@Override
	public void deleteRoleById(Long roleId) {
		roleDao.delete(roleId);
	}

}
