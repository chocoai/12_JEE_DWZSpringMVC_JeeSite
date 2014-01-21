package com.sample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joe.utils.dwz.Page;
import com.joe.utils.dwz.PageUtils;
import com.sample.dao.RealEstateTaskDAO;
import com.sample.entity.RealEstateTask;
import com.sample.service.RealEstateTaskService;

@Service
@Transactional
public class RealEstateTaskServiceImpl implements RealEstateTaskService {
	
	@Autowired
	private RealEstateTaskDAO realEstateTaskDAO;
	
	public void save(RealEstateTask task) {
		realEstateTaskDAO.save(task);
	}

	public void delete(Long id) {
		realEstateTaskDAO.delete(id);
	}

	public RealEstateTask get(Long id) {
		return realEstateTaskDAO.findOne(id);
	}

	public void update(RealEstateTask task) {
		realEstateTaskDAO.save(task);
	}

	public List<RealEstateTask> find(Page page, String taskName) {
		org.springframework.data.domain.Page<RealEstateTask> springDataPage = 
				(org.springframework.data.domain.Page<RealEstateTask>)realEstateTaskDAO.findByTaskNameContaining(taskName, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	public List<RealEstateTask> findAll(Page page) {
		org.springframework.data.domain.Page<RealEstateTask> springDataPage = realEstateTaskDAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

}
