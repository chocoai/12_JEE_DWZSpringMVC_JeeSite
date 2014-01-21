package com.joe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joe.dao.LogEntityDAO;
import com.joe.entity.main.LogEntity;
import com.joe.log.LogLevel;
import com.joe.service.LogEntityService;
import com.joe.utils.dwz.Page;
import com.joe.utils.dwz.PageUtils;

/** 
 * 	
 */
@Service
@Transactional
public class LogEntityServiceImpl implements LogEntityService {
	
	@Autowired
	private LogEntityDAO logEntityDAO;

	/**   
	 * @param logEntity  
	 * @see com.joe.service.LogEntityService#save(com.joe.entity.main.LogEntity)  
	 */
	public void save(LogEntity logEntity) {
		System.out.println("in LogEntityServiceImpl.save()");
		logEntityDAO.save(logEntity);
	}

	/**   
	 * @param id
	 * @return  
	 * @see com.joe.service.LogEntityService#get(java.lang.Long)  
	 */
	public LogEntity get(Long id) {
		System.out.println("in LogEntityServiceImpl.get()");
		return logEntityDAO.findOne(id);
	}

	/**   
	 * @param logEntity  
	 * @see com.joe.service.LogEntityService#update(com.joe.entity.main.LogEntity)  
	 */
	public void update(LogEntity logEntity) {
		System.out.println("in LogEntityServiceImpl.update()");
		logEntityDAO.save(logEntity);
	}

	/**   
	 * @param id  
	 * @see com.joe.service.LogEntityService#delete(java.lang.Long)  
	 */
	public void delete(Long id) {
		System.out.println("in LogEntityServiceImpl.delete()");
		logEntityDAO.delete(id);
	}

	/**
	 * 
	 * @param logLevel
	 * @param page
	 * @return  
	 * @see com.joe.service.LogEntityService#findByLogLevel(com.joe.log.LogLevel, com.joe.utils.dwz.Page)
	 */
	public List<LogEntity> findByLogLevel(LogLevel logLevel, Page page) {
		System.out.println("in LogEntityServiceImpl.findByLogLevel()");
		org.springframework.data.domain.Page<LogEntity> springDataPage = 
				logEntityDAO.findByLogLevel(logLevel, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	/**   
	 * @return  
	 * @see com.joe.service.LogEntityService#findAll()  
	 */
	public List<LogEntity> findAll() {
		System.out.println("in LogEntityServiceImpl.findAll()");
		return logEntityDAO.findAll();
	}

	/**
	 * 
	 * @param specification
	 * @param page
	 * @return  
	 * @see com.joe.service.LogEntityService#findByExample(org.springframework.data.jpa.domain.Specification, com.joe.utils.dwz.Page)
	 */
	public List<LogEntity> findByExample(
			Specification<LogEntity> specification, Page page) {
		System.out.println("in LogEntityServiceImpl.findByExample()");
		org.springframework.data.domain.Page<LogEntity> springDataPage = 
				logEntityDAO.findAll(specification, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
}
