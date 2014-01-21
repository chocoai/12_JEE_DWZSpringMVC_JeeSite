package com.joe.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.joe.dao.OrganizationRoleDAO;
import com.joe.entity.main.OrganizationRole;
import com.joe.service.OrganizationRoleService;

/** 
 * 	
 */
@Service
@Transactional
public class OrganizationRoleServiceImpl implements OrganizationRoleService {
	
	private OrganizationRoleDAO organizationRoleDAO;
	
	/**
	 * 
	 * 构造函数
	 * @param organizationRoleDAO
	 */
	@Autowired
	public OrganizationRoleServiceImpl(OrganizationRoleDAO organizationRoleDAO) {
		this.organizationRoleDAO = organizationRoleDAO;
	}
	
	/**   
	 * @param id
	 * @return  
	 * @see com.joe.service.OrganizationRoleService#get(java.lang.Long)  
	 */
	public OrganizationRole get(Long id) {
		System.out.println("in OrganizationRoleServiceImpl.get()");
		return organizationRoleDAO.findOne(id);
	}

	/**   
	 * @param organizationId
	 * @return  
	 * @see com.joe.service.OrganizationRoleService#find(java.lang.Long)  
	 */
	public List<OrganizationRole> find(Long organizationId) {
		System.out.println("in OrganizationRoleServiceImpl.find(), organizationId="+organizationId);
		return organizationRoleDAO.findByOrganizationId(organizationId);
	}

	/**   
	 * @param organizationRole  
	 * @see com.joe.service.OrganizationRoleService#save(com.joe.entity.main.OrganizationRole)  
	 */
	public void save(OrganizationRole organizationRole) {
		System.out.println("in OrganizationRoleServiceImpl.save()");
		organizationRoleDAO.save(organizationRole);
	}

	/**   
	 * @param organizationRoleId  
	 * @see com.joe.service.OrganizationRoleService#delete(java.lang.Long)  
	 */
	public void delete(Long organizationRoleId) {
		System.out.println("in OrganizationRoleServiceImpl.delete(), organizationRoleId="+organizationRoleId);
		organizationRoleDAO.delete(organizationRoleId);
	}

}
