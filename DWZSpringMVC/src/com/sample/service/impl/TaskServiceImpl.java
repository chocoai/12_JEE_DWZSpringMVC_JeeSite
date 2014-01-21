package com.sample.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joe.utils.dwz.Page;
import com.joe.utils.dwz.PageUtils;
import com.sample.dao.TaskDAO;
import com.sample.entity.Task;
import com.sample.service.TaskService;

/** 
 * 	
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {
	
	@Autowired
	private TaskDAO taskDAO;

	/**   
	 * @param page
	 * @param name
	 * @return  
	 * @see com.sample.service.TaskService#find(com.ketayao.ketacustom.util.dwz.Page, java.lang.String)  
	 */
	public List<Task> find(Page page, String title) {
		org.springframework.data.domain.Page<Task> springDataPage = 
				(org.springframework.data.domain.Page<Task>)taskDAO.findByTitleContaining(title, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	/**   
	 * @param task  
	 * @see com.sample.service.TaskService#save(com.sample.entity.Task)  
	 */
	public void save(Task task) {
		taskDAO.save(task);
	}

	/**   
	 * @param id
	 * @return  
	 * @see com.sample.service.TaskService#get(java.lang.Long)  
	 */
	public Task get(Long id) {
		return taskDAO.findOne(id);
	}

	/**   
	 * @param task  
	 * @see com.sample.service.TaskService#update(com.sample.entity.Task)  
	 */
	public void update(Task task) {
		taskDAO.save(task);
	}

	/**   
	 * @param id  
	 * @see com.sample.service.TaskService#delete(java.lang.Long)  
	 */
	public void delete(Long id) {
		taskDAO.delete(id);
	}

	/**   
	 * @param page
	 * @return  
	 * @see com.sample.service.TaskService#findAll(com.ketayao.ketacustom.util.dwz.Page)  
	 */
	public List<Task> findAll(Page page) {
		org.springframework.data.domain.Page<Task> springDataPage = taskDAO.findAll(PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
}