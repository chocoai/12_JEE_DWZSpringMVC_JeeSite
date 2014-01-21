package com.joe.service;

import java.util.List;

import com.joe.entity.main.RolePermission;

/** 
 * 	
 */

public interface RolePermissionService {
	void save(RolePermission rolePermission);
	
	RolePermission get(Long id);
	
	void update(RolePermission rolePermission);
	
	void delete(Long id);

	List<RolePermission> findByRoleId(Long roleId);
}
