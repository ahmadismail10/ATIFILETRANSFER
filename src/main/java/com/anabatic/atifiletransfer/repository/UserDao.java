package com.anabatic.atifiletransfer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.anabatic.atifiletransfer.entities.User;

public interface UserDao extends JpaRepository<User, Long> {
	
	@Query("from User order by userId")
	public List<User> findAllUserOrderById();

	@Query("from User where username = :username")
	public User findOneUserByUsername(@Param(value = "username") String username);

}
