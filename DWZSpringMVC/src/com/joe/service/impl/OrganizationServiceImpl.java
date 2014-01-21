package com.joe.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joe.dao.OrganizationDAO;
import com.joe.dao.UserDAO;
import com.joe.entity.main.Organization;
import com.joe.exception.ServiceException;
import com.joe.service.OrganizationService;
import com.joe.utils.dwz.Page;
import com.joe.utils.dwz.PageUtils;

/** 
 * 	
 */
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private OrganizationDAO organizationDAO;
	
	public void save(Organization organization) {
		System.out.println("in OrganizationServiceImpl.save()");
		organizationDAO.save(organization);
	}

	public Organization get(Long id) {
		System.out.println("in OrganizationServiceImpl.get(), id=" + id);
		return organizationDAO.findOne(id);
	}

	public void update(Organization organization) {
		System.out.println("in OrganizationServiceImpl.update()");
		organizationDAO.save(organization);
	}

	/**   
	 * @param id
	 * @throws ServiceException  
	 * @see com.joe.service.OrganizationService#delete(java.lang.Long)  
	 */
	public void delete(Long id) throws ServiceException {
		System.out.println("in OrganizationServiceImpl.delete(), id=" + id);
		if (isRoot(id)) {
			throw new ServiceException("不允许删除根组织。");
		}
		
		Organization organization = this.get(id);
		
		//先判断是否存在子模块，如果存在子模块，则不允许删除
		if(organization.getChildren().size() > 0){
			throw new ServiceException(organization.getName() + "组织下存在子组织，不允许删除。");
		}
		
		if (userDAO.findByOrganizationId(id).size() > 0) {
			throw new ServiceException(organization.getName() + "组织下存在用户，不允许删除。");
		}
		
		organizationDAO.delete(organization);
	}

	/**   
	 * @param parentId
	 * @param page
	 * @return  
	 * @see com.joe.service.OrganizationService#find(java.lang.Long, com.joe.utils.dwz.Page)  
	 */
	public List<Organization> find(Long parentId, Page page) {
		System.out.println("in OrganizationServiceImpl.find()");
		org.springframework.data.domain.Page<Organization> springDataPage = 
				organizationDAO.findByParentId(parentId, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}

	/**   
	 * @param parentId
	 * @param name
	 * @param page
	 * @return  
	 * @see com.joe.service.OrganizationService#find(java.lang.Long, java.lang.String, com.joe.utils.dwz.Page)  
	 */
	public List<Organization> find(Long parentId, String name, Page page) {
		System.out.println("in OrganizationServiceImpl.find(), name="+name);
		org.springframework.data.domain.Page<Organization> springDataPage = 
				organizationDAO.findByParentIdAndNameContaining(parentId, name, PageUtils.createPageable(page));
		page.setTotalCount(springDataPage.getTotalElements());
		return springDataPage.getContent();
	}
	
	/**
	 * 判断是否是根组织.
	 */
	private boolean isRoot(Long id) {
		return id == 1;
	}

	/**
	 * 
	 * @return  
	 * @see com.joe.service.OrganizationService#getTree()
	 */
	public Organization getTree() {
		System.out.println("in OrganizationServiceImpl.getTree()---------------");
		List<Organization> list = organizationDAO.findAllWithCache();
		/**
		[id,name,parent]:[1,???,null]	//根组织
		[id,name,parent]:[2,SH HP,Organization{1, ???, null}]
		[id,name,parent]:[3,HP Lease,Organization{1, ???, null}]
		[id,name,parent]:[4,GD,Organization{2, SH HP, ???}]
		[id,name,parent]:[5,HP lease 1,Organization{3, HP Lease, ???}]
		 */
//		for(Organization o:list) {
//			System.out.println("[id,name,parent]:[" + o.getId() + "," + o.getName() + "," + o.getParent() + "]");
//		}
		List<Organization> rootList = makeTree(list);

		//只是为了获得root Node
		return rootList.get(0);
	}

	private List<Organization> makeTree(List<Organization> list) {
		System.out.println("in OrganizationServiceImpl.makeTree()===============");
		List<Organization> parent = new ArrayList<Organization>();
		// get parentId = null;
		for (Organization e : list) {
			//root node
			if (e.getParent() == null) {
				//TODO
				//e.setChildren(new ArrayList<Organization>(0));//?
				System.out.println("***********" + e.getName() + " is added to parent list");
				parent.add(e);
			}
		}
		// 删除parentId = null;
		list.removeAll(parent);
		
		//parent是根节点
		makeChildren(parent, list);
		
//		System.out.println("----------------before returning parent--------------");
//		for (Organization o:parent) {
//			//[id,name,parent]:[1,根组织,null]
//			System.out.println("[id,name,parent]:[" + o.getId() + "," + o.getName() + "," + o.getParent() + "]");
//		}
		return parent;
	}
	
	private void makeChildren(List<Organization> parent, List<Organization> children) {
		System.out.println("      makeChildren()         start.............");
		if (children.isEmpty()) {
			return ;
		}
		
		//TODO 树的创建逻辑好像有问题
		//tmp为已经处理过的组织
		List<Organization> tmp = new ArrayList<Organization>();
		for (Organization c1 : parent) {
			for (Organization c2 : children) {
				//c2.setChildren(new ArrayList<Organization>(0));//?
				if (c1.getId().equals(c2.getParent().getId())) {
					System.out.println(".................." + c2.getName() + " is added to tmp list");
					c1.getChildren().add(c2);
					tmp.add(c2);
				}
			}
		}
		
		System.out.println(".....before removeAll(tmp).......");
		children.removeAll(tmp);
		
		//每个子节点再去生成树
		makeChildren(tmp, children);
		System.out.println("      makeChildren()         end.............");
	}
}
