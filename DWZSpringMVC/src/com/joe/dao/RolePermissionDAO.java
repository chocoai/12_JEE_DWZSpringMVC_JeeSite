package com.joe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joe.entity.main.RolePermission;

/** 
 * 	
 */

public interface RolePermissionDAO extends JpaRepository<RolePermission, Long> {
	List<RolePermission> findByRoleId(Long roleId);
}
