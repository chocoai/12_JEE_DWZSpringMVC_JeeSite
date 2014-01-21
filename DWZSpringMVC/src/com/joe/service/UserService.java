package com.joe.service;

import java.util.List;

import com.joe.entity.main.User;
import com.joe.exception.ExistedException;
import com.joe.exception.ServiceException;
import com.joe.utils.dwz.Page;

/** 
 * 	
 */

public interface UserService {
	
	User get(String username);
	
	List<User> find(Page page, String name);

	void update(User user);
	
	void updatePwd(User user, String newPwd) throws ServiceException;

	void save(User user) throws ExistedException;

	User get(Long id);

	void delete(Long id) throws ServiceException;

	List<User> findAll(Page page);
}
