package com.joe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joe.entity.main.UserRole;

/** 
 * 	
 */

public interface UserRoleDAO extends JpaRepository<UserRole, Long> {
	List<UserRole> findByUserId(Long userId);
}
