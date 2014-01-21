package com.joe.service;

import java.util.List;

import com.joe.entity.main.Role;
import com.joe.utils.dwz.Page;

/** 
 * 	
 */

public interface RoleService {
	
	List<Role> find(Page page, String name);

	void save(Role role);

	Role get(Long id);

	void update(Role role);

	void delete(Long id);

	List<Role> findAll(Page page);
}
