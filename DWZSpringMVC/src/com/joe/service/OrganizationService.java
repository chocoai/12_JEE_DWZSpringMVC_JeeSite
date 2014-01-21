package com.joe.service;

import java.util.List;

import com.joe.entity.main.Organization;
import com.joe.exception.ServiceException;
import com.joe.utils.dwz.Page;

/** 
 * 	
 */

public interface OrganizationService {
	
	List<Organization> find(Long parentId, Page page);
	
	List<Organization> find(Long parentId, String name, Page page);
	
	Organization getTree();

	void save(Organization organization);

	Organization get(Long id);

	void update(Organization organization);

	void delete(Long id) throws ServiceException;
}
