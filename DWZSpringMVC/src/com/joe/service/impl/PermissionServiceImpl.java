package com.joe.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joe.dao.PermissionDAO;
import com.joe.entity.main.Permission;
import com.joe.service.PermissionService;

/** 
 * 	
 */
@Service
@Transactional
public class PermissionServiceImpl implements PermissionService{
	
	@Autowired
	private PermissionDAO permissionDAO;

	/**   
	 * @param permission  
	 * @see com.joe.service.PermissionService#save(com.joe.entity.main.Permission)  
	 */
	public void save(Permission permission) {
		System.out.println("in PermissionServiceImpl.save()");
		permissionDAO.save(permission);
	}

	/**   
	 * @param id
	 * @return  
	 * @see com.joe.service.PermissionService#get(java.lang.Long)  
	 */
	public Permission get(Long id) {
		System.out.println("in PermissionServiceImpl.get(), id=" + id);
		return permissionDAO.findOne(id);
	}

	/**   
	 * @param permission  
	 * @see com.joe.service.PermissionService#update(com.joe.entity.main.Permission)  
	 */
	public void update(Permission permission) {
		System.out.println("in PermissionServiceImpl.update()");
		permissionDAO.save(permission);
	}

	/**   
	 * @param id  
	 * @see com.joe.service.PermissionService#delete(java.lang.Long)  
	 */
	public void delete(Long id) {
		System.out.println("in PermissionServiceImpl.delete(), id=" + id);
		permissionDAO.delete(id);
	}
}
