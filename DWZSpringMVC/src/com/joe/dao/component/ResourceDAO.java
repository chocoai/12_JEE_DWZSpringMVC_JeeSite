package com.joe.dao.component;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.joe.entity.component.Resource;

/** 
 * 	
 */

public interface ResourceDAO extends JpaRepository<Resource, Long> {
	Resource getByUuid(String uuid);
	
	Page<Resource> findByNameContaining(Pageable pageable, String name); 
}
