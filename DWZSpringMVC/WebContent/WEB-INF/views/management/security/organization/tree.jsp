<%@page import="com.joe.entity.main.Organization"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>
<%!
	public String tree(Organization organization, String basePath) {
		StringBuilder builder = new StringBuilder();
		
		long pid = organization.getParent() == null ? 0:organization.getParent().getId();
		builder.append("{id:" + organization.getId() +  ", pId:" + pid + 
				", name:\"" + organization.getName() + "\", url:\"" + basePath + "/management/security/organization/list/" + organization.getId() + "\", target:\"ajax\"},");
		
		for(Organization o : organization.getChildren()) {
			builder.append(tree(o, basePath));				
		}
		//System.out.println("in tree.jsp, builder.toString():" + builder.toString());
		//{id:1, pId:0, name:"???", url:"/DWZSpringMVC/management/security/organization/list/1", target:"ajax"},
		//{id:2, pId:1, name:"????", url:"/DWZSpringMVC/management/security/organization/list/2", target:"ajax"},
		//{id:4, pId:2, name:"GD", url:"/DWZSpringMVC/management/security/organization/list/4", target:"ajax"},
		//{id:3, pId:1, name:"????", url:"/DWZSpringMVC/management/security/organization/list/3", target:"ajax"},
		return builder.toString();
	}
%>
<%
	Organization organization2 = (Organization)request.getAttribute("organization");
	String orgTree = tree(organization2, request.getContextPath());
	orgTree = orgTree.substring(0, orgTree.length() - 1);
	//{id:1, pId:0, name:"???", url:"/DWZSpringMVC/management/security/organization/list/1", target:"ajax"},
	//{id:2, pId:1, name:"SH HP", url:"/DWZSpringMVC/management/security/organization/list/2", target:"ajax"},
	//{id:4, pId:2, name:"GD", url:"/DWZSpringMVC/management/security/organization/list/4", target:"ajax"},
	//{id:3, pId:1, name:"HP Lease", url:"/DWZSpringMVC/management/security/organization/list/3", target:"ajax"},
	//{id:5, pId:3, name:"HP lease 1", url:"/DWZSpringMVC/management/security/organization/list/5", target:"ajax"}
	System.out.println("in tree.jsp, orgTree:" + orgTree);
%>   

<script type="text/javascript">
var setting = {
	view: {
		//showIcon: false
	},
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: 0
		}
	},
	callback: {
		//treeId:对应 zTree 的 treeId，便于用户操控;
		//treeNode:被点击的节点 JSON 数据对象
		onClick: function(event, treeId, treeNode) {
			//tree_list.jsp里树区域右边的显示本组织的页面
			var $rel = $("#jbsxBox2organizationList");
			$rel.loadUrl(treeNode.url, {}, function(){
				$rel.find("[layoutH]").layoutH();
			});

			event.preventDefault();
		}
	}
};


var zNodes =[<%=orgTree%>];

$(document).ready(function(){
	var t = $("#orgTree");
	//or line 1451 in jquery.ztree.core-3.5.js
	t = $.fn.zTree.init(t, setting, zNodes);
	t.expandAll(true); 
});

</script>
<style>
<!--
#orgTree li span {
	text-align:left;
	float: left;
	display: inline;
} 
-->
</style>
<ul id="orgTree" class="ztree" style="display: block;"></ul>