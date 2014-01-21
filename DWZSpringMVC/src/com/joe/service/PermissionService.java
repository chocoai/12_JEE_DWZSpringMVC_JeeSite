package com.joe.service;

import com.joe.entity.main.Permission;

/** 
 * 	
 */

public interface PermissionService {
	
	void save(Permission permission);
	
	Permission get(Long id);
	
	void update(Permission permission);
	
	void delete(Long id);
}
