package com.joe.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.joe.entity.main.LogEntity;
import com.joe.log.LogLevel;
import com.joe.utils.dwz.Page;

/** 
 * 	
 */

public interface LogEntityService {
	void save(LogEntity logEntity);
	
	LogEntity get(Long id);
	
	void update(LogEntity logEntity);
	
	void delete(Long id);
	
	List<LogEntity> findByLogLevel(LogLevel logLevel, Page page);
	
	List<LogEntity> findAll();
	
	List<LogEntity> findByExample(Specification<LogEntity> specification, Page page);
}
