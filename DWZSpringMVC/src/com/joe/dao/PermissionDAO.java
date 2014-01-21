package com.joe.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joe.entity.main.Permission;

/** 
 * 	
 */

public interface PermissionDAO extends JpaRepository<Permission, Long> {

}
