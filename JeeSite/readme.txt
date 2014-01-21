来自JeeSite开源项目。

resources/deployments/*.bar 文件可以直接用winrar打开。
http://www.open-open.com/lib/view/open1350460225367.html
ant的脚本例子：
<?xml version="1.0" encoding="UTF-8"?>
<project name="foo">
    <property name="workflow.definition" value="foo-common-core/src/main/resources/diagrams" />
    <property name="workflow.deployments" value="foo-common-core/src/main/resources/deployments" />
	<target name="workflow.package.oa.leave">
		<echo>打包流程定义及流程图::OA-请假</echo>
		<zip destfile="${workflow.deployments}/oa/leave.zip" basedir="${workflow.definition}/oa/leave" update="true"
			includes="*.xml,*.png" />
	</target>
</project>
这样当修改流程定义文件后只要运行ant命令就可以打包了：
ant workflow.package.oa.leave
 
-------------------------------------------------
1、	BeanMapper：Bean与Bean，Bean与Conllection的互转
2、	JaxbMapper：XML与Object互转。
3、	JsonMapper：JSON与Object互转。
4、	Cryptos、Digests：密钥工具类SHA1、MD5
5、	ExcelExcel导入导出：http://thinkgem.iteye.com/blog/1833431
6、	CacheUtils：系统Cache工具类。
7、	Collections3：集合对象工具类。
8、	CookieUtils：Cookie操作工具类
9、	DateUtils：日期时间工具类。
10、	Encodes：各种编码转换工具类。
11、	Exceptions：异常工具类。
12、	FileUtils：文件操作工具类。
13、	FreeMarkers：FreeMarkers模板工具类。
14、	Identities：唯一标识生成算法工具类（uuid、random）
15、	PropertiesLoader：属性文件操作工具类
16、	Reflections：Java对象操作反射工具类。
17、	StringUtils：字符串操作工具类。
18、	Threads：线程相关操作工具类。
19、	UserUtils：当前用户相关操作工具类。
20、	CmsUtils：内容管理相关操作工具类。
3.2.	Taglib
1、	ckeditor.tag：HTML在线编辑器。
2、	ckfinder.tag：在线文件管理。
3、	iconselect.tag：Icon图标选择。
4、	message.tag：消息弹框。
5、	validateCode.tag：验证码。
3.3.	EL Function
1、	fns.tld：系统相关EL函数。
2、	fnc.tld：内容管理相关EL函数。
3.4.	JavaScript
Jquery、Jbox、Jquery Validate、zTree、My7DatePicker、treeTable、CKEditor、CKFinder
4.	快速体验
4.1.	在线体验
在线体验地址（感谢刘杰提供）：http://demo.jeesite.com:1234/jeesite

用户名：admin		密码：admin
4.2.	本地体验
1、	具备运行环境：JDK1.6、Maven3.0、MySql。
2、	修改src\main\resources\application.properties文件中的数据库设置参数。
3、	根据修改参数创建对应MySql数据库（数据库编码：UTF-8）如果使用其它数据库请参考JeeSite Developer.docx文档。
4、	运行bin\resresh-db\refresh-db.bat脚本，即可导入表结构及演示数据
5、	运行bin\jetty.bat，启动Web服务器（第一次运行，需要下载依赖jar包，请耐心等待）。
6、	最高管理员账号，用户名：thinkgem 密码：admin

运行时在浏览器里输入：
http://localhost:8080/JeeSite
