package com.anabatic.atifiletransfer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anabatic.atifiletransfer.entities.User;
import com.anabatic.atifiletransfer.repository.UserDao;
import com.anabatic.atifiletransfer.service.impl.IUser;

@Service
public class UserService implements IUser {

	@Autowired
	UserDao userDao;
	
	@Override
	public List<User> findAllUser() {
		return userDao.findAll();
	}

	@Override
	public User findOneUserr(Long idUser) {
		return userDao.findOne(idUser);
	}

	@Override
	public void saveUser(User user) {
		userDao.saveAndFlush(user);
	}

	@Override
	public void editUser(User user) {
		User userTemp = userDao.findOne(user.getUserId());
		userTemp.setUsername(user.getUsername());
		userTemp.setUserStatus(user.getUserStatus());
		userTemp.setPassword(user.getPassword());
		userTemp.setRole(user.getRole());
		userDao.saveAndFlush(userTemp);
	}

	@Override
	public List<User> findAllUserOrderById() {
		return userDao.findAllUserOrderById();
	}

	@Override
	public void deleteUserById(Long idUser) {
		userDao.delete(idUser);
	}

	@Override
	public User findOneUserByUsername(String username) {
		return userDao.findOneUserByUsername(username);
	}

}
