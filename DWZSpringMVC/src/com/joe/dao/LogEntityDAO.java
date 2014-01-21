package com.joe.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.joe.entity.main.LogEntity;
import com.joe.log.LogLevel;

/** 
 * 	
 */

public interface LogEntityDAO extends JpaRepository<LogEntity, Long>, JpaSpecificationExecutor<LogEntity>{
	Page<LogEntity> findByLogLevel(LogLevel level, Pageable pageable);
}
