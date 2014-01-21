package com.joe.log.impl;

import java.util.Map;
import com.google.common.collect.Maps;
import com.joe.log.LogAPI;
import com.joe.log.LogLevel;

/** 
 * 	
 */

public class LogAdapter implements LogAPI {

	/**   
	 * @param message
	 * @param logLevel  
	 * @see com.joe.log.LogAPI#log(java.lang.String, com.joe.log.LogLevel)  
	 */
	public void log(String message, LogLevel logLevel) {
		log(message, null, logLevel);
	}

	/**   
	 * @param message
	 * @param objects
	 * @param logLevel  
	 * @see com.joe.log.LogAPI#log(java.lang.String, java.lang.Object[], com.joe.log.LogLevel)  
	 */
	public void log(String message, Object[] objects, LogLevel logLevel) {
		
	}
	
	/**   
	 * @return  
	 * @see com.joe.log.LogAPI#getRootLogLevel()  
	 */
	public LogLevel getRootLogLevel() {
		return LogLevel.ERROR;
	}

	/**   
	 * @return  
	 * @see com.joe.log.LogAPI#getCustomLogLevel()  
	 */
	public Map<String, LogLevel> getCustomLogLevel() {
		return Maps.newHashMap();
	}
}
