package com.joe.service;

import java.util.List;

import com.joe.entity.main.Module;
import com.joe.exception.ExistedException;
import com.joe.exception.ServiceException;
import com.joe.utils.dwz.Page;


/** 
 * 	
 */

public interface ModuleService {
	void save(Module module) throws ExistedException;
	
	Module get(Long id);
	
	void update(Module module);
	
	void delete(Long id) throws ServiceException;
	
	Module getTree();
	
	List<Module> findAll();
	
	List<Module> find(Long parentId, Page page);
	
	List<Module> find(Long parentId, String name, Page page);
}
