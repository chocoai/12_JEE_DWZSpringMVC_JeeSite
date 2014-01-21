package com.sample.service;

import java.util.List;

import com.joe.utils.dwz.Page;
import com.sample.entity.Task;

/** 
 * 	
 */

public interface TaskService {
	
	void save(Task task);
	
	void delete(Long id);

	Task get(Long id);

	void update(Task task);
	
	List<Task> find(Page page, String title);

	List<Task> findAll(Page page);
}
