package com.joe.service.impl.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joe.dao.component.ResourceDAO;
import com.joe.entity.component.Resource;
import com.joe.service.component.ResourceService;
import com.joe.utils.dwz.Page;
import com.joe.utils.dwz.PageUtils;

/** 
 * 	
 */
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceDAO resourceDAO;

	/**   
	 * @param resource  
	 * @see com.joe.service.component.ResourceService#save(com.joe.entity.component.Resource)  
	 */
	public void save(Resource resource) {
		System.out.println("in ResourceServiceImpl.save()");
		resourceDAO.save(resource);
	}

	/**   
	 * @param resource  
	 * @see com.joe.service.component.ResourceService#update(com.joe.entity.component.Resource)  
	 */
	public void update(Resource resource) {
		System.out.println("in ResourceServiceImpl.update()");
		resourceDAO.save(resource);
	}

	/**   
	 * @param id  
	 * @see com.joe.service.component.ResourceService#delete(java.lang.Long)  
	 */
	public void delete(Long id) {
		System.out.println("in ResourceServiceImpl.delete()");
		resourceDAO.delete(id);
	}

	/**   
	 * @param id
	 * @return  
	 * @see com.joe.service.component.ResourceService#get(java.lang.Long)  
	 */
	public Resource get(Long id) {
		System.out.println("in ResourceServiceImpl.get(), id="+id);
		return resourceDAO.findOne(id);
	}
	
	/**   
	 * @param uuid
	 * @return  
	 * @see com.joe.service.component.ResourceService#get(java.lang.String)  
	 */
	public Resource get(String uuid) {
		System.out.println("in ResourceServiceImpl.get(), uuid="+uuid);
		return resourceDAO.getByUuid(uuid);
	}

	/**   
	 * @param page
	 * @return  
	 * @see com.joe.service.component.ResourceService#findAll(com.joe.utils.dwz.Page)  
	 */
	public List<Resource> findAll(Page page) {
		System.out.println("in ResourceServiceImpl.findAll()");
		org.springframework.data.domain.Page<Resource> springDataPage = resourceDAO.findAll(PageUtils.createPageable(page)); 
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
	
	/**
	 * 
	 * @param page
	 * @param name
	 * @return  
	 * @see com.joe.service.component.ResourceService#findByName(com.joe.utils.dwz.Page, java.lang.String)
	 */
	public List<Resource> findByName(Page page, String name) {
		System.out.println("in ResourceServiceImpl.findByName(), name="+name);
		org.springframework.data.domain.Page<Resource> springDataPage = resourceDAO.findByNameContaining(PageUtils.createPageable(page), name); 
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	/**
	 * 
	 * @param page
	 * @return  
	 * @see com.joe.service.component.ResourceService#find(com.joe.utils.dwz.Page)
	 */
	public List<Resource> find(Page page) {
		System.out.println("in ResourceServiceImpl.find()");
		org.springframework.data.domain.Page<Resource> springDataPage = resourceDAO.findAll(PageUtils.createPageable(page)); 
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

}
