改编自keta-custom开源项目。


运行URL：
http://localhost:8080/DWZSpringMVC

-------------------------------------------
目前我看到有这两个
zhenglida@sina.com

C:\JavaDevelopment\apache-maven-3.1.1\bin目录下，运行此命令下载jar包：
pom.xml文件已经在此目录下了
mvn clean install -DskipTests

 
http://jeesite.com/ 这个有工作流，但有时候有些点击会卡住，而且感觉比较慢
 
http://ketayao.com/keta-custom/login 还有个这个，感觉这个界面好些
 
可以考虑以第二个为准，把第一个里面好的移植过来
 
供你参考，或者你看看有没有更好的选择

-----------------------------------------------------------------
1)	servlet-api.jar 只是编译时需要，发布时不需要加在项目里

2)	创建mysql database;
	src/jdbc.properties里指定了mysql的数据连接

	第一种方式：在未连接数据库的情况下，输入 mysql -h localhost -u root -p 123456  < d:\book.sql 回车即可；
	
	第二种方式：在已连接数据库的情况下，此时命令提示符为mysql>，输入 source d:\book.sql  或者 \. d:\book.sql 回车即可。
	
	e.g.	mysql>\. d:\book.sql 


3)	加入mysql的驱动jar，就可以发布这个应用到tomcat了

4) 	http://localhost:8080/dwzmvc/passport
	http://localhost:8080/dwzmvc/management 是系统入口
	dwz.web.management.IndexController.java里能看到配置

	
	
	
===========================
可运行的：
http://localhost:8080/keta-custom/


http://localhost:8080/JeeSite

type Exception report
message /WEB-INF/views/modules/sys/sysLogin.jsp(3,0) /WEB-INF/views/include/taglib.jsp(2,62) Unable to read TLD "META-INF/c.tld" from JAR file "file:/C:/JavaDevelopment/xiaojunproject/osDWZWS/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/JeeSite/WEB-INF/lib/jstl-1.2.jar": org.apache.jasper.JasperException: Failed to load or instantiate TagLibraryValidator class: org.apache.taglibs.standard.tlv.JstlCoreTLV
description The server encountered an internal error that prevented it from fulfilling this request.
exception 
org.apache.jasper.JasperException: /WEB-INF/views/modules/sys/sysLogin.jsp(3,0) /WEB-INF/views/include/taglib.jsp(2,62) Unable to read TLD "META-INF/c.tld" from JAR file "file:/C:/JavaDevelopment/xiaojunproject/osDWZWS/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/JeeSite/WEB-INF/lib/jstl-1.2.jar": org.apache.jasper.JasperException: Failed to load or instantiate TagLibraryValidator class: org.apache.taglibs.standard.tlv.JstlCoreTLV
	org.apache.jasper.compiler.DefaultErrorHandler.jspError(DefaultErrorHandler.java:40)




http://16.165.14.207:8080/keta-custom/


mysql> show tables;
+----------------------------+
| Tables_in_keta_custom      |
+----------------------------+
| component_resource         |
| security_log_entity        |
| security_module            |
| security_organization      |
| security_organization_role |
| security_permission        |
| security_role              |
| security_role_permission   |
| security_user              |
| security_user_role         |
| ss_task                    |
+----------------------------+
11 rows in set (0.00 sec)

mysql>

================================
Xiao, Jun (Joe, SH-CN) [13:37 PM]: 
如果有个maven的pom文件，能直接下载这文件里的所有jar包到本地吗？
Zheng, Li-Da (Tony, ES-Apps-GD-China-SH) [13:38 PM]: 
能
你只要你机器上安装好maven
Xiao, Jun (Joe, SH-CN) [13:39 PM]: 
我下载了个apache-maven-3.1.1-bin.zip, 设置了MAVEN_HOME环境变量
然后就不知道如何做了
用什么命令？
Zheng, Li-Da (Tony, ES-Apps-GD-China-SH) [13:41 PM]: 
然后bin 目录也设置一下
然后用mvn clean install -DskipTest
skipTests
Xiao, Jun (Joe, SH-CN) [13:41 PM]: 
path里也设置了
Zheng, Li-Da (Tony, ES-Apps-GD-China-SH) [13:41 PM]: 
刚才打错了
然后再用mvn eclipse:eclipse
直接能创建出eclipse 工程
第一步是拉lib和打包
Xiao, Jun (Joe, SH-CN) [13:42 PM]: 
pom.xml文件需要放在什么指定目录下吗？
Zheng, Li-Da (Tony, ES-Apps-GD-China-SH) [13:42 PM]: 
项目根目录
Xiao, Jun (Joe, SH-CN) [13:42 PM]: 
我没下载for eclipse的maven插件
[13:44 PM] You've requested to send file "pom.xml" to Zheng, Li-Da (Tony, ES-Apps-GD-China-SH). 
[13:44 PM] Transfer of "pom.xml" is complete. 
Xiao, Jun (Joe, SH-CN) [13:44 PM]: 
你帮我下一下jar包？:$
Zheng, Li-Da (Tony, ES-Apps-GD-China-SH) [13:45 PM]: 
你直接试一下
在那个根目录下面
mvn clean install -DskipTests
哦，可能你还要设下代理
公司可能要设的
Xiao, Jun (Joe, SH-CN) [13:46 PM]: 
是要在pom所在目录上敲这命令？

=============================================
自己做的例子步骤：
1)	数据库加一些纪录:organization, user, module, permission


2)	WEB-INF/views/management/下新增realestate目录及task子目录，把sample目录下的4个jsp文件拷到新增的task子目录下
	并把4个jsp里URL对应的原sample值改为realestate
3)	在com.sample包里编写对real estate有关的controller,service,entity,dao


--------------------
activiti 表说明
 RE：RepositoryService接口所操作的表
ACT_RE_DEPLOYMENT部署流程定义时需要被持久化保存下来的信息
 ACT_RE_PROCDEF   流程定义数据表
 
ID：IdentityService接口所操作的表
 ACT_ID_INFO  用户扩展信息表
 ACT_ID_MEMBERSHIP  用户与分组对应信息表
 ACT_ID_GROUP  用户组信息 
 ACT_ID_USER  用户信息
 
RU：运行时表 - RuntimeService
 ACT_RU_VARIABLE  运行时流程变量表 
 ACT_RU_TASK  任务节点表 
 ACT_RU_JOB  定时任务表 
 ACT_RU_IDENTITYLINK  任务参与者表。主要存储任务节点与参与者的相关信息
 ACT_RU_EXECUTION  流程执行路径信息
 
HI：历史数据表，HistoryService
 ACT_HI_PROCINST  历史流程实例表
 ACT_HI_ATTACHMENT  历史附件表 
 ACT_HI_COMMENT  历史注释表 
 ACT_HI_DETAIL  历史详情表 
 ACT_HI_TASKINST  历史任务实例表
 ACT_HI_ACTINST  历史执行路径信息 
 
GE：全局数据
 ACT_GE_BYTEARRAY  二进制数据表：流程定义图片和xml、Serializable的变量
 ACT_GE_PROPERTY  属性数据表。存储整个流程引擎级别的数据
 
注：由于Activiti会在任务或者流程结束时，删除其所在的运行时数据，存入历史数据表。所以保证了运行时表小且快。不会有性能问题。

--------------------2013-11-01-------------------
解压eclipse到C:\JavaDevelopment\withzld下
启动eclipse, install new software
1)	Maven	
	http://download.eclipse.org/technology/m2e/releases
2)	Activiti
	http://activiti.org/designer/update/



