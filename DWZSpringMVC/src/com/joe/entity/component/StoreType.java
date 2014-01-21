package com.joe.entity.component;

/** 
 * 资源存储类型，存入数据库DB，存入文件FILE。	
 */

public enum StoreType {
	DB("db"), FILE("file");
	
	private String value;
	
	StoreType(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
}
