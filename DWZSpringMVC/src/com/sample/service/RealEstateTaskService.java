package com.sample.service;

import java.util.List;
import com.joe.utils.dwz.Page;
import com.sample.entity.RealEstateTask;;

public interface RealEstateTaskService {

	
	void save(RealEstateTask task);
	
	void delete(Long id);

	RealEstateTask get(Long id);

	void update(RealEstateTask task);
	
	List<RealEstateTask> find(Page page, String taskName);

	List<RealEstateTask> findAll(Page page);
}
