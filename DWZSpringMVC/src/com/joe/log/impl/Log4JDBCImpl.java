package com.joe.log.impl;

import java.text.MessageFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import com.google.common.collect.Maps;
import com.joe.entity.main.LogEntity;
import com.joe.log.LogLevel;
import com.joe.service.LogEntityService;
import com.joe.shiro.ShiroDbRealm;

/** 
 * 全局日志等级<包日志等级<类和方法日志等级
 */
public class Log4JDBCImpl extends LogAdapter {
	
	private LogLevel rootLogLevel = LogLevel.ERROR;
	
	private LogEntityService logEntityService;
	
	private Map<String, LogLevel> customLogLevel = Maps.newHashMap();

	/**
	 * 
	 * @param message
	 * @param objects
	 * @param logLevel  
	 * @see com.joe.log.impl.LogAdapter#log(java.lang.String, java.lang.Object[], com.joe.log.LogLevel)
	 */
	@Override
	public void log(String message, Object[] objects, LogLevel logLevel) {	
		
		MessageFormat mFormat = new MessageFormat(message);
		String result = mFormat.format(objects);
		
		if (!StringUtils.isNotBlank(result)) {
			return;
		}
		
		Subject subject = SecurityUtils.getSubject();
		ShiroDbRealm.ShiroUser shiroUser = (ShiroDbRealm.ShiroUser)subject.getPrincipal();
		
		//result = shiroUser.toString() + ":" + result;
		
		LogEntity logEntity = new LogEntity();
		logEntity.setCreateTime(new Date());
		
		logEntity.setUsername(shiroUser.getLoginName());
		logEntity.setMessage(result);
		logEntity.setIpAddress(shiroUser.getIpAddress());
		logEntity.setLogLevel(logLevel);
		
		logEntityService.save(logEntity);
	}

	public void setRootLogLevel(LogLevel rootLogLevel) {
		this.rootLogLevel = rootLogLevel;
	}

	/**   
	 * 
	 * @return  
	 * @see com.joe.log.LogTemplate#getRootLogLevel()  
	 */
	@Override
	public LogLevel getRootLogLevel() {
		return rootLogLevel;
	}
	
	public void setCustomLogLevel(Map<String, LogLevel> customLogLevel) {
		this.customLogLevel = customLogLevel;
	}
	
	@Override
	public Map<String, LogLevel> getCustomLogLevel() {
		return customLogLevel;
	}

	public void setLogEntityService(LogEntityService logEntityService) {
		this.logEntityService = logEntityService;
	}
	
}
