package com.joe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joe.dao.RolePermissionDAO;
import com.joe.entity.main.RolePermission;
import com.joe.service.RolePermissionService;

/** 
 * 	
 */
@Service
@Transactional
public class RolePermissionServiceImpl implements RolePermissionService {
	
	@Autowired
	private RolePermissionDAO rolePermissionDAO;

	/**   
	 * @param rolePermission  
	 * @see com.joe.service.RolePermissionService#save(com.joe.entity.main.RolePermission)  
	 */
	public void save(RolePermission rolePermission) {
		System.out.println("in RolePermissionServiceImpl.save()");
		rolePermissionDAO.save(rolePermission);
	}

	/**   
	 * @param id
	 * @return  
	 * @see com.joe.service.RolePermissionService#get(java.lang.Long)  
	 */
	public RolePermission get(Long id) {
		System.out.println("in RolePermissionServiceImpl.get()");
		return rolePermissionDAO.findOne(id);
	}

	/**   
	 * @param rolePermission  
	 * @see com.joe.service.RolePermissionService#update(com.joe.entity.main.RolePermission)  
	 */
	public void update(RolePermission rolePermission) {
		System.out.println("in RolePermissionServiceImpl.update()");
		rolePermissionDAO.save(rolePermission);
	}

	/**   
	 * @param id  
	 * @see com.joe.service.RolePermissionService#delete(java.lang.Long)  
	 */
	public void delete(Long id) {
		System.out.println("in RolePermissionServiceImpl.delete(), id=" + id);
		rolePermissionDAO.delete(id);
	}

	/**   
	 * @param roleId
	 * @return  
	 * @see com.joe.service.RolePermissionService#findByRoleId(java.lang.Long)  
	 */
	public List<RolePermission> findByRoleId(Long roleId) {
		System.out.println("in RolePermissionServiceImpl.findByRoleId(), roleId=" + roleId);
		return rolePermissionDAO.findByRoleId(roleId);
	}

}
