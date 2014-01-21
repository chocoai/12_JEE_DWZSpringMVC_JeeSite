package com.joe.service.component;

import java.util.List;

import com.joe.entity.component.Resource;
import com.joe.utils.dwz.Page;

/** 
 * 	
 */

public interface ResourceService {
	void save(Resource resource);
	
	void update(Resource resource);
	
	void delete(Long id);
	
	Resource get(Long id);
	
	Resource get(String uuid);
	
	List<Resource> findAll(Page page);
	
	List<Resource> findByName(Page page, String name);
	
	List<Resource> find(Page page);
}
