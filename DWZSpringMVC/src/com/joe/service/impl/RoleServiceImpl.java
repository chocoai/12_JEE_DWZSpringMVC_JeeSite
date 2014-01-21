package com.joe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joe.dao.RoleDAO;
import com.joe.entity.main.Role;
import com.joe.service.RoleService;
import com.joe.shiro.ShiroDbRealm;
import com.joe.utils.dwz.Page;
import com.joe.utils.dwz.PageUtils;

/** 
 * 	
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired(required = false)
	private ShiroDbRealm shiroRealm;
	
	public void save(Role role) {
		roleDAO.save(role);
	}

	public Role get(Long id) {
		return roleDAO.findOne(id);
	}
	
	public List<Role> findAll(Page page) {
		System.out.println("in RoleServiceImpl.findAll() ");
		org.springframework.data.domain.Page<Role> springDataPage = roleDAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	/**   
	 * @param role  
	 * @see com.joe.service.RoleService#update(com.joe.entity.main.Role)  
	 */
	public void update(Role role) {
		System.out.println("in RoleServiceImpl.update() ");
		roleDAO.save(role);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	/**   
	 * @param id  
	 * @see com.joe.service.RoleService#delete(java.lang.Long)  
	 */
	public void delete(Long id) {
		System.out.println("in RoleServiceImpl.delete() ");
		roleDAO.delete(id);
		shiroRealm.clearAllCachedAuthorizationInfo();
	}

	/**   
	 * @param page
	 * @param name
	 * @return  
	 * @see com.joe.service.RoleService#find(com.joe.utils.dwz.Page, java.lang.String)  
	 */
	public List<Role> find(Page page, String name) {
		System.out.println("in RoleServiceImpl.find(), name="+name);
		org.springframework.data.domain.Page<Role> springDataPage = 
				(org.springframework.data.domain.Page<Role>)roleDAO.findByNameContaining(name, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

}
