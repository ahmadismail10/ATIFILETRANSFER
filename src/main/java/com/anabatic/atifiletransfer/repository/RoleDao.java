package com.anabatic.atifiletransfer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anabatic.atifiletransfer.entities.Role;

public interface RoleDao extends JpaRepository<Role, Long> {

}
