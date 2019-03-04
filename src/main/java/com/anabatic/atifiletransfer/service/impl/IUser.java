package com.anabatic.atifiletransfer.service.impl;

import java.util.List;

import com.anabatic.atifiletransfer.entities.User;

public interface IUser {
	
	public List<User> findAllUser();
	
	public User findOneUserr(Long userId);
	
	public void saveUser(User User);
	
	public void editUser(User user);
	
	public List<User> findAllUserOrderById();
	
	public void deleteUserById(Long userId);
	
	public User findOneUserByUsername(String username);
	
}
