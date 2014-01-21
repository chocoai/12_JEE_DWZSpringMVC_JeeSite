package com.joe.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.joe.entity.main.Role;

/** 
 * 	
 */

public interface RoleDAO extends JpaRepository<Role, Long> {
	Page<Role> findByNameContaining(String name, Pageable pageable);
}
