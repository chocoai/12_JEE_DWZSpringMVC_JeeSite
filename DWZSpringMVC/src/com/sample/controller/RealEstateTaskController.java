package com.sample.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.modules.beanvalidator.BeanValidators;

import com.joe.log.Log;
import com.joe.log.LogLevel;
import com.joe.log.LogMessageObject;
import com.joe.log.impl.LogUitl;
import com.joe.utils.dwz.AjaxObject;
import com.joe.utils.dwz.Page;
import com.sample.entity.RealEstateTask;
import com.sample.service.RealEstateTaskService;

@Controller
@RequestMapping("/management/realestate/task")
public class RealEstateTaskController {

	@Autowired
	private RealEstateTaskService realEstateTaskService;
	
	@Autowired
	private Validator validator;
	
	private static final String CREATE = "management/realestate/task/create";
	private static final String UPDATE = "management/realestate/task/update";
	private static final String LIST = "management/realestate/task/list";
	private static final String VIEW = "management/realestate/task/view";
	
	//RequiresPermissions:RealEstate is saved in security_module table 
	@RequiresPermissions("RealEstate:save")
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String preCreate(Map<String, Object> map) {
		return CREATE;
	}
	
	/**
	 * LogMessageObject的write用法实例。
	 */
	@Log(message="添加了{0}任务，LogMessageObject的isWritten为true。", level=LogLevel.INFO)
	@RequiresPermissions("RealEstate:save")
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public @ResponseBody String create(RealEstateTask task) {
		BeanValidators.validateWithException(validator, task);
		realEstateTaskService.save(task);
		
		// 加入一个LogMessageObject，该对象的isWritten为true，会记录日志。
		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{task.getTaskName()}));
		return AjaxObject.newOk("任务添加成功！").toString();
	}
	
	/**
	 * LogMessageObject的ignore用法实例，ignore不会记录日志。
	 */
	@Log(message="你永远不会看见该日志，LogMessageObject的isWritten为false。", level=LogLevel.INFO)
	@RequiresPermissions("RealEstate:edit")
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
		RealEstateTask task = realEstateTaskService.get(id);
		
		map.put("task", task);
		
		// 加入一个LogMessageObject，该对象的isWritten为false，不会记录日志。
		LogUitl.putArgs(LogMessageObject.newIgnore());
		return UPDATE;
	}
	
	/**
	 * Log的level用法实例
	 * 1.level分为三层，默认包层（rootLogLevel默认值TRACE），自定义包层（customLogLevel），具体方法层（@Log默认值TRACE）
	 * 2.参考顺序：默认包层->自定义包层->具体方法层->LogMessageObject
	 * 3.有自定义包层的level等级会忽略默认包层
	 * 4.@Log的level大于等于自定义包层或者默认的level会输出日志；小于则不会。
	 */
	@Log(message="Log的level用法实例，LogLevel.TRACE小于自定义包层LogLevel.INFO，不会输出日志。", level=LogLevel.TRACE)
	@RequiresPermissions("RealEstate:edit")
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public @ResponseBody String update(RealEstateTask task) {
		BeanValidators.validateWithException(validator, task);
		realEstateTaskService.update(task);

		return AjaxObject.newOk("任务修改成功！").toString();
	}

	@RequiresPermissions("RealEstate:delete")
	@RequestMapping(value="/delete/{id}", method=RequestMethod.POST)
	public @ResponseBody String delete(@PathVariable Long id) {
		realEstateTaskService.delete(id);

		return AjaxObject.newOk("任务删除成功！").setCallbackType("").toString();
	}
	
	
	/**
	 * Log的override用法实例
	 * 假如override为true，会忽略掉level
	 * 
	 * 批量删除展示
	 */
	@Log(message="Log的override用法实例，override为true，会忽略掉level。删除了{0}任务。", level=LogLevel.TRACE, override=true)
	@RequiresPermissions("RealEstate:delete")
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public @ResponseBody String deleteMany(Long[] ids) {
		String[] titles = new String[ids.length];
		for (int i = 0; i < ids.length; i++) {
			RealEstateTask task = realEstateTaskService.get(ids[i]);
			realEstateTaskService.delete(task.getId());
			
			titles[i] = task.getTaskName();
		}
		
		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{Arrays.toString(titles)}));
		return AjaxObject.newOk("任务删除成功！").setCallbackType("").toString();
	}

	@RequiresPermissions("RealEstate:view")
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Page page, String keywords, Map<String, Object> map) {
		System.out.println("in RealEstateTaskController.list(), keywords="+keywords);
		List<RealEstateTask> tasks = null;
		if (StringUtils.isNotBlank(keywords)) {
			tasks = realEstateTaskService.find(page, keywords);
		} else {
			tasks = realEstateTaskService.findAll(page);
		}

		map.put("page", page);
		map.put("tasks", tasks);
		map.put("keywords", keywords);

		return LIST;
	}
	
	/**
	 * 自定look权限，实例。
	 * 描述
	 * @param id
	 * @param map
	 * @return
	 */
	@RequiresPermissions("Task:look")
	@RequestMapping(value="/view/{id}", method={RequestMethod.GET})
	public String view(@PathVariable Long id, Map<String, Object> map) {
		RealEstateTask task = realEstateTaskService.get(id);
		map.put("task", task);
		return VIEW;
	}
}
