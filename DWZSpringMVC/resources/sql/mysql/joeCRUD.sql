--选择合适的数据库
--step 1:创建数据库
CREATE DATABASE dwzspringmvc DEFAULT CHARACTER SET utf8;	
--use dwzspringmvc;

--step 2: run this project, and all tables will be created automatically.
--applicationContext.xml的entityManagerFactory里jpaProperties的hibernate.hbm2ddl.auto设置

--step 3: 插入一些必要的数据
--组织
insert into security_organization(id, description,name,parent_id) values(1,'不能删除','根组织',null);
insert into security_organization(id, description,name,parent_id) values(2,'HP','上海惠普',1);
--用户	admin/123456
insert into security_user(id, email, password, phone, realname, salt,status,username,org_id) values(1,'xiaojunshcn@hotmail.com','7a8f27edd04296d1a2f484cca71c6834a87356b6',null,'BIG JOE','9754469b0353a6a7','enabled','admin',2);
--模块 	登录后左边应该出来哪些菜单（Sn是和页面权限判断对应的字符串）
insert into security_module(id,description,name,priority,sn,url,parent_id) values(1,'所有模块的根节点，不能删除。','根模块',1,'Root','#',null);
insert into security_module(id,description,name,priority,sn,url,parent_id) values(2,'管理设置:包含权限管理、模块管理等。','管理设置',99,'Security','#',1);
insert into security_module(id,description,name,priority,sn,url,parent_id) values(3,null,'用户管理',99,'User','/management/security/user/list',2);
insert into security_module(id,description,name,priority,sn,url,parent_id) values(4,null,'角色管理',99,'Role','/management/security/role/list',2);
insert into security_module(id,description,name,priority,sn,url,parent_id) values(5,null,'模块管理',99,'Module','/management/security/module/tree_list',2);
insert into security_module(id,description,name,priority,sn,url,parent_id) values(6,'一个开发使用的简单示例。','开发实例',99,'Sample','#',1);
insert into security_module(id,description,name,priority,sn,url,parent_id) values(7,null,'简单任务实例',99,'Task','/management/sample/task/list',6);
insert into security_module(id,description,name,priority,sn,url,parent_id) values(8,null,'缓存管理',99,'CacheManage','/management/security/cacheManage/index',6);
insert into security_module(id,description,name,priority,sn,url,parent_id) values(9,'组件管理','组件管理',99,'Component','#',1);
insert into security_module(id,description,name,priority,sn,url,parent_id) values(10,null,'资源管理',99,'Resource','/management/component/resource/list',9);
insert into security_module(id,description,name,priority,sn,url,parent_id) values(11,null,'系统图标',99,'Icon','/management/component/icon/list',9);
insert into security_module(id,description,name,priority,sn,url,parent_id) values(12,null,'打开外部链接',99,'Baidu','http://www.baidu.com',9);
insert into security_module(id,description,name,priority,sn,url,parent_id) values(13,null,'日志管理',99,'logEntity','/management/security/logEntity/list',9);
insert into security_module(id,description,name,priority,sn,url,parent_id) values(14,null,'组织管理',99,'Organization','/management/security/organization/tree_list',2);	
--定义permission,每个Module Id都应该定义好对应的CRUD权限
insert into security_permission(id, description,name,short_name,module_id) values(1,null,'增','save',2);
insert into security_permission(id, description,name,short_name,module_id) values(2,null,'删','delete',2);
insert into security_permission(id, description,name,short_name,module_id) values(3,null,'改','edit',2);
insert into security_permission(id, description,name,short_name,module_id) values(4,null,'查','view',2);
insert into security_permission(id, description,name,short_name,module_id) values(5,null,'增','save',3);
insert into security_permission(id, description,name,short_name,module_id) values(6,null,'删','delete',3);
insert into security_permission(id, description,name,short_name,module_id) values(7,null,'改','edit',3);
insert into security_permission(id, description,name,short_name,module_id) values(8,null,'查','view',3);
insert into security_permission(id, description,name,short_name,module_id) values(9,'重置密码、更新状态','重置','reset',3);
insert into security_permission(id, description,name,short_name,module_id) values(10,'分配、撤销角色','授权','assign',3);
insert into security_permission(id, description,name,short_name,module_id) values(11,null,'增','save',4);
insert into security_permission(id, description,name,short_name,module_id) values(12,null,'删','delete',4);
insert into security_permission(id, description,name,short_name,module_id) values(13,null,'改','edit',4);
insert into security_permission(id, description,name,short_name,module_id) values(14,null,'查','view',4);
insert into security_permission(id, description,name,short_name,module_id) values(15,null,'增','save',5);
insert into security_permission(id, description,name,short_name,module_id) values(16,null,'删','delete',5);
insert into security_permission(id, description,name,short_name,module_id) values(17,null,'改','edit',5);
insert into security_permission(id, description,name,short_name,module_id) values(18,null,'查','view',5);
insert into security_permission(id, description,name,short_name,module_id) values(19,null,'增','save',6);
insert into security_permission(id, description,name,short_name,module_id) values(20,null,'删','delete',6);
insert into security_permission(id, description,name,short_name,module_id) values(21,null,'改','edit',6);
insert into security_permission(id, description,name,short_name,module_id) values(22,null,'查','view',6);
insert into security_permission(id, description,name,short_name,module_id) values(23,null,'增','save',7);
insert into security_permission(id, description,name,short_name,module_id) values(24,null,'删','delete',7);
insert into security_permission(id, description,name,short_name,module_id) values(25,null,'改','edit',7);
insert into security_permission(id, description,name,short_name,module_id) values(26,null,'查','view',7);
insert into security_permission(id, description,name,short_name,module_id) values(27,null,'增','save',8);
insert into security_permission(id, description,name,short_name,module_id) values(28,null,'删','delete',8);
insert into security_permission(id, description,name,short_name,module_id) values(29,null,'改','edit',8);
insert into security_permission(id, description,name,short_name,module_id) values(30,null,'查','view',8);
insert into security_permission(id, description,name,short_name,module_id) values(31,null,'增','save',9);
insert into security_permission(id, description,name,short_name,module_id) values(32,null,'删','delete',9);
insert into security_permission(id, description,name,short_name,module_id) values(33,null,'改','edit',9);
insert into security_permission(id, description,name,short_name,module_id) values(34,null,'查','view',9);
insert into security_permission(id, description,name,short_name,module_id) values(35,null,'增','save',10);
insert into security_permission(id, description,name,short_name,module_id) values(36,null,'删','delete',10);
insert into security_permission(id, description,name,short_name,module_id) values(37,null,'改','edit',10);
insert into security_permission(id, description,name,short_name,module_id) values(38,null,'查','view',10);
insert into security_permission(id, description,name,short_name,module_id) values(39,null,'增','save',11);
insert into security_permission(id, description,name,short_name,module_id) values(40,null,'删','delete',11);
insert into security_permission(id, description,name,short_name,module_id) values(41,null,'改','edit',11);
insert into security_permission(id, description,name,short_name,module_id) values(42,null,'查','view',11);
insert into security_permission(id, description,name,short_name,module_id) values(43,null,'增','save',12);
insert into security_permission(id, description,name,short_name,module_id) values(44,null,'删','delete',12);
insert into security_permission(id, description,name,short_name,module_id) values(45,null,'改','edit',12);
insert into security_permission(id, description,name,short_name,module_id) values(46,null,'查','view',12);
insert into security_permission(id, description,name,short_name,module_id) values(47,null,'增','save',13);
insert into security_permission(id, description,name,short_name,module_id) values(48,null,'删','delete',13);
insert into security_permission(id, description,name,short_name,module_id) values(49,null,'改','edit',13);
insert into security_permission(id, description,name,short_name,module_id) values(50,null,'查','view',13);
insert into security_permission(id, description,name,short_name,module_id) values(51,null,'增','save',14);
insert into security_permission(id, description,name,short_name,module_id) values(52,null,'删','delete',14);
insert into security_permission(id, description,name,short_name,module_id) values(53,null,'改','edit',14);
insert into security_permission(id, description,name,short_name,module_id) values(54,null,'查','view',14);

--run this application, user (admin/123456) can login the system. 

--为了添加Activiti工作流，需要新增Activiti的表










--新增自己的组织
--insert into security_organization(id, description,name,parent_id) values(17,'HP','上海惠普',1);

--新的组织暂不设置角色 
--暂不向security_organization_role里设置和角色的关联

--新增自己的登录用户 security_user
--因为此表中的密码是加密过的，还有salt字段。不直接用sql命令插入数据。而是在界面上新建用户方式。
--新纪录的user id：18，org_id: 17	
--xiaojunshcn/123456

--给此新用户赋角色。
--insert into security_user_role(id,priority,role_id,user_id) values(28,99,3,18);

--新建一个module，练习用
--sn:RealEstate 对应到controller里的RequiresPermissions
--insert into security_module(id,description,name,priority, sn, url, parent_id) values(66, 'Real Estate','地产行业',99,'RealEstate','#',1);
--insert into security_module(id,description,name,priority, sn, url, parent_id) values(67, null,'测试一',99,'Test','/management/realestate/task/list',66);

--针对新增加的module，定义CRUD permission
--insert into security_permission(id, description,name,short_name,module_id) values(185,null,'增','save',66);
--insert into security_permission(id, description,name,short_name,module_id) values(186,null,'删','delete',66);
--insert into security_permission(id, description,name,short_name,module_id) values(187,null,'改','edit',66);
--insert into security_permission(id, description,name,short_name,module_id) values(188,null,'查','view',66);
--insert into security_permission(id, description,name,short_name,module_id) values(189,null,'增','save',67);
--insert into security_permission(id, description,name,short_name,module_id) values(190,null,'删','delete',67);
--insert into security_permission(id, description,name,short_name,module_id) values(191,null,'改','edit',67);
--insert into security_permission(id, description,name,short_name,module_id) values(192,null,'查','view',67);

--security_role_permission里，把这些permission和role关联起来，这样user就能访问这些新增的Module
--insert into security_role_permission(id,permission_id,role_id) values(101,185,3);
--insert into security_role_permission(id,permission_id,role_id) values(102,186,3);
--insert into security_role_permission(id,permission_id,role_id) values(103,187,3);
--insert into security_role_permission(id,permission_id,role_id) values(104,188,3);
--insert into security_role_permission(id,permission_id,role_id) values(105,189,3);
--insert into security_role_permission(id,permission_id,role_id) values(106,190,3);
--insert into security_role_permission(id,permission_id,role_id) values(107,191,3);
--insert into security_role_permission(id,permission_id,role_id) values(108,192,3);

--新建real estata相关的表，表结构暂和ss_task一致,字段名不一致
-- ----------------------------
-- Table structure for `tb_realestate_task`
-- ----------------------------
--DROP TABLE IF EXISTS tb_realestate_task;
--CREATE TABLE tb_realestate_task (
--  id bigint(20) NOT NULL AUTO_INCREMENT,
--  description varchar(255) DEFAULT NULL,
--  task_name varchar(32) NOT NULL,
--  PRIMARY KEY (id)
--) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_realestate_task
-- ----------------------------
--INSERT INTO tb_realestate_task(id,description,task_name) VALUES ('1', null, '房屋销售');
--INSERT INTO tb_realestate_task(id,description,task_name) VALUES ('2', null, '房屋出租');
--INSERT INTO tb_realestate_task(id,description,task_name) VALUES ('3', null, '客户信息登记');
--INSERT INTO tb_realestate_task(id,description,task_name) VALUES ('4', null, '房源管理');
