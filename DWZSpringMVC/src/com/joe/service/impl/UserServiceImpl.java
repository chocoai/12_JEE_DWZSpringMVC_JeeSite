package com.joe.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joe.dao.UserDAO;
import com.joe.entity.main.User;
import com.joe.exception.ExistedException;
import com.joe.exception.ServiceException;
import com.joe.service.UserService;
import com.joe.shiro.ShiroDbRealm;
import com.joe.shiro.ShiroDbRealm.HashPassword;
import com.joe.utils.dwz.Page;
import com.joe.utils.dwz.PageUtils;

/** 
 * 	Service annotation 注册这个实现类给Spring容器管理
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private UserDAO userDAO;
	
	@Autowired
	private ShiroDbRealm shiroRealm;
	
	/**  
	 * 构造函数
	 * @param jpaRepository  
	 */ 
	@Autowired
	public UserServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public User get(Long id) {
		return userDAO.findOne(id);
	}

	public List<User> findAll(Page page) {
		System.out.println("in UserServiceImpl.findAll()");
		org.springframework.data.domain.Page<User> springDataPage = userDAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
	
	/**
	 * 
	 * @param user
	 * @throws ExistedException  
	 * @see com.joe.service.UserService#save(com.joe.entity.main.User)
	 */
	public void save(User user) throws ExistedException {
		System.out.println("in UserServiceImpl.save()");
		if (userDAO.findByUsername(user.getUsername()) != null) {
			throw new ExistedException("用户添加失败，登录名：" + user.getUsername() + "已存在。");
		}
		
		if (userDAO.findByRealname(user.getRealname()) != null) {
			throw new ExistedException("用户添加失败，真实名：" + user.getRealname() + "已存在。");
		}
		
		//设定安全的密码，使用passwordService提供的salt并经过1024次 sha-1 hash
		if (StringUtils.isNotBlank(user.getPlainPassword()) && shiroRealm != null) {
			HashPassword hashPassword = ShiroDbRealm.encryptPassword(user.getPlainPassword());
			user.setSalt(hashPassword.salt);
			user.setPassword(hashPassword.password);
		}
		
		userDAO.save(user);
		shiroRealm.clearCachedAuthorizationInfo(user.getUsername());
	}

	/**   
	 * @param user  
	 * @see com.joe.service.UserService#update(com.joe.entity.main.User)  
	 */
	public void update(User user) {
		System.out.println("in UserServiceImpl.update()");
		userDAO.save(user);
		shiroRealm.clearCachedAuthorizationInfo(user.getUsername());
	}
	
	/**
	 * 
	 * @param user
	 * @param newPwd
	 * @throws ServiceException  
	 * @see com.joe.service.UserService#updatePwd(com.joe.entity.main.User, java.lang.String)
	 */
	public void updatePwd(User user, String newPwd) throws ServiceException {
		System.out.println("in UserServiceImpl.updatePwd()");
		//if (isSupervisor(user.getId())) {
		//	logger.warn("操作员{},尝试修改超级管理员用户", SecurityUtils.getSubject().getPrincipal());
		//	throw new ServiceException("不能修改超级管理员用户");
		//}
		//设定安全的密码，使用passwordService提供的salt并经过1024次 sha-1 hash
		boolean isMatch = ShiroDbRealm.validatePassword(user.getPlainPassword(), user.getPassword(), user.getSalt());
		if (isMatch) {
			HashPassword hashPassword = ShiroDbRealm.encryptPassword(newPwd);
			user.setSalt(hashPassword.salt);
			user.setPassword(hashPassword.password);
			
			userDAO.save(user);
			shiroRealm.clearCachedAuthorizationInfo(user.getUsername());
			
			return; 
		}
		
		throw new ServiceException("用户密码错误！");
	}

	/**   
	 * @param id  
	 * @see com.joe.service.UserService#delete(java.lang.Long)  
	 */
	public void delete(Long id) throws ServiceException {
		System.out.println("in UserServiceImpl.delete(), id=" + id);
		if (isSupervisor(id)) {
			logger.warn("操作员{}，尝试删除超级管理员用户", SecurityUtils.getSubject()
					.getPrincipal() + "。");
			throw new ServiceException("不能删除超级管理员用户。");
		}
		User user = userDAO.findOne(id);
		userDAO.delete(user.getId());
		
		shiroRealm.clearCachedAuthorizationInfo(user.getUsername());
	}

	/**   
	 * @param username
	 * @return  
	 * @see com.joe.service.UserService#get(java.lang.String)  
	 */
	public User get(String username) {
		return userDAO.findByUsername(username);
	}

	/**   
	 * @param page
	 * @param name
	 * @return  
	 * @see com.joe.service.UserService#find(com.joe.utils.dwz.Page, java.lang.String)  
	 */
	public List<User> find(Page page, String name) {
		System.out.println("in UserServiceImpl.find(), name=" + name);
		org.springframework.data.domain.Page<User> springDataPage = 
				userDAO.findByUsernameContaining(name, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	/**
	 * 判断是否超级管理员.
	 */
	private boolean isSupervisor(Long id) {
		return id == 1;
	}
}
