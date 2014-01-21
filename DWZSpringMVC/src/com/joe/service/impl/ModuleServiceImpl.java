package com.joe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joe.dao.ModuleDAO;
import com.joe.entity.main.Module;
import com.joe.exception.ExistedException;
import com.joe.exception.ServiceException;
import com.joe.service.ModuleService;
import com.joe.utils.dwz.Page;
import com.joe.utils.dwz.PageUtils;

/** 
 * 	
 */
@Service
@Transactional
public class ModuleServiceImpl implements ModuleService {
	@Autowired
	private ModuleDAO moduleDAO;
	
	public void save(Module module) throws ExistedException {
		if (moduleDAO.findBySn(module.getSn()).size() > 0) {
			throw new ExistedException("已存在sn=" + module.getSn() + "的模块。");
		}
		moduleDAO.save(module);
	}

	public Module get(Long id) {
		return moduleDAO.findOne(id);
	}

	public void update(Module module) {
		moduleDAO.save(module);
	}

	/**   
	 * @param id
	 * @throws ServiceException  
	 * @see com.joe.service.ModuleService#delete(int)  
	 */
	public void delete(Long id) throws ServiceException {
		if (isRoot(id)) {
			throw new ServiceException("不允许删除根模块。");
		}
		
		Module module = this.get(id);
		
		//先判断是否存在子模块，如果存在子模块，则不允许删除
		if(module.getChildren().size() > 0){
			throw new ServiceException(module.getName() + "模块下存在子模块，不允许删除。");
		}
		
		moduleDAO.delete(module);
	}

	/**   
	 * @param parentId
	 * @param page
	 * @return  
	 * @see com.joe.service.ModuleService#find(java.lang.Long, com.joe.utils.dwz.Page)  
	 */
	public List<Module> find(Long parentId, Page page) {
		System.out.println("in ModuleServiceImpl.find(), parentId="+parentId);
		org.springframework.data.domain.Page<Module> springDataPage = 
				moduleDAO.findByParentId(parentId, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	/**   
	 * @param parentId
	 * @param name
	 * @param page
	 * @return  
	 * @see com.joe.service.ModuleService#find(java.lang.Long, java.lang.String, com.joe.utils.dwz.Page)  
	 */
	public List<Module> find(Long parentId, String name, Page page) {
		System.out.println("in ModuleServiceImpl.find(), name="+name);
		org.springframework.data.domain.Page<Module> springDataPage = 
				moduleDAO.findByParentIdAndNameContaining(parentId, name, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
	
	/**   
	 * @return  
	 * @see com.joe.service.ModuleService#findAll()  
	 */
	public List<Module> findAll() {
		System.out.println("in ModuleServiceImpl.findAll()");
		return moduleDAO.findAll();
	}

	/**
	 * 判断是否是根模块.
	 */
	private boolean isRoot(Long id) {
		return id == 1;
	}

	/**
	 * 
	 * @return  
	 * @see com.joe.service.ModuleService#getTree()
	 */
	public Module getTree() {
		System.out.println("in ModuleServiceImpl.getTree()");
		List<Module> list = moduleDAO.findAllWithCache();
		
		List<Module> rootList = makeTree(list);
				
		return rootList.get(0);
	}

	private List<Module> makeTree(List<Module> list) {
		System.out.println("in ModuleServiceImpl.makeTree()");
		List<Module> parent = new ArrayList<Module>();
		// get parentId = null;
		for (Module e : list) {
			if (e.getParent() == null) {
				e.setChildren(new ArrayList<Module>(0));
				parent.add(e);
			}
		}
		// 删除parentId = null;
		list.removeAll(parent);
		
		makeChildren(parent, list);
		
		return parent;
	}
	
	private void makeChildren(List<Module> parent, List<Module> children) {
		System.out.println("in ModuleServiceImpl.makeChildren()");
		if (children.isEmpty()) {
			return ;
		}
		
		List<Module> tmp = new ArrayList<Module>();
		for (Module c1 : parent) {
			for (Module c2 : children) {
				c2.setChildren(new ArrayList<Module>(0));
				if (c1.getId().equals(c2.getParent().getId())) {
					c1.getChildren().add(c2);
					tmp.add(c2);
				}
			}
		}
		
		children.removeAll(tmp);
		
		makeChildren(tmp, children);
	}
	
}
