package com.joe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joe.dao.UserRoleDAO;
import com.joe.entity.main.UserRole;
import com.joe.service.UserRoleService;

/** 
 * 	
 */
@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

	private UserRoleDAO userRoleDAO;
	
	/**  
	 * 构造函数
	 * @param jpaRepository  
	 */ 
	@Autowired
	public UserRoleServiceImpl(UserRoleDAO userRoleDAO) {
		this.userRoleDAO = userRoleDAO;
	}
	
	/**   
	 * @param id
	 * @return  
	 * @see com.joe.service.UserRoleService#get(java.lang.Long)  
	 */
	public UserRole get(Long id) {
		System.out.println("in UserRoleServiceImpl.get(), id="+id);
		return userRoleDAO.findOne(id);
	}

	public void save(UserRole userRole) {
		System.out.println("in UserRoleServiceImpl.save()");
		userRoleDAO.save(userRole);
	}

	public void delete(Long userRoleId) {
		System.out.println("in UserRoleServiceImpl.delete(), userRoleId="+userRoleId);
		userRoleDAO.delete(userRoleId);
	}

	/**   
	 * @param userId
	 * @return  
	 * @see com.joe.service.UserRoleService#find(Long)  
	 */
	public List<UserRole> find(Long userId) {
		System.out.println("in UserRoleServiceImpl.find(), userId="+userId);
		return userRoleDAO.findByUserId(userId);
	}

}
