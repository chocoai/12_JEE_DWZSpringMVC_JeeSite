<%@page import="com.joe.entity.main.Permission"%>
<%@page import="com.joe.entity.main.Module"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/WEB-INF/views/include.inc.jsp"%>

<%!
public String role_tree(Module module, Integer[] moduleIndex) {
	StringBuilder explandBuilder = new StringBuilder("<span class='inputValueRole'>");
	for(Permission permission : module.getPermissions()) {
		explandBuilder.append(permission.getName() + "<input type='checkbox' name='rolePermissions[" +  moduleIndex[0] + "].permission.id' value='" + permission.getId() + "'/>");
		moduleIndex[0] = moduleIndex[0] + 1;
	}
	explandBuilder.append("</span>");

	StringBuilder builder = new StringBuilder();
	long pid = module.getParent() == null ? 0:module.getParent().getId();
	builder.append("{id:" + module.getId() +  ", pId:" + pid + 
			", name:\"" + module.getName() + "\", expland:\"" + explandBuilder.toString() + "\"},");
	
	for(Module o : module.getChildren()) {
		builder.append(role_tree(o, moduleIndex));				
	}
	
	return builder.toString();
}
%>
<%
	Module module2 = (Module)request.getAttribute("module");
	String cPermissonTree = role_tree(module2, new Integer[]{0});
	cPermissonTree = cPermissonTree.substring(0, cPermissonTree.length() - 1);
%>
<style>
<!--
.setAll{
vertical-align:top;margin-right:3px;
}
span.inputValueRole input {
vertical-align:middle;margin-right:15px;
}
-->
</style>

<script type="text/javascript">
<!--
// top

var IDMark_Switch = "_switch",
IDMark_Icon = "_ico",
IDMark_Span = "_span",
IDMark_Input = "_input",
IDMark_Check = "_check",
IDMark_Edit = "_edit",
IDMark_Remove = "_remove",
IDMark_Ul = "_ul",
IDMark_A = "_a";

var setting = {
	data: {
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "pId",
			rootPId: ""
		}
	},		
	check: {
		enable: true,
		autoCheckTrigger: true
	},
	view: {
		addDiyDom: addDiyDom,
		showIcon: false
	},
	callback: {
		onCheck: selectPermission
	}
};

var zNodes =[<%=cPermissonTree %>];

function addDiyDom(treeId, treeNode) {
	var cObj = $("#" + treeNode.tId + IDMark_Check);
	var cEditStr = '<input type="checkbox" class="setAll"/>';
	cObj.after(cEditStr);

	var aObj = $("#" + treeNode.tId + IDMark_A);
	var aEditStr = treeNode.expland;
	aObj.after(aEditStr);
}

function selectPermission(event, treeId, treeNode) {
	var cObj = $("#" + treeNode.tId + IDMark_Check);
	var isChecked = treeNode.checked;
	
	var $setAll = cObj.next(".setAll");
	var $inputSpan = $(cObj).nextAll(".inputValueRole");
	
	if (isChecked == true) {
		var manualLength = $("input:checked", $inputSpan).length;
		if (manualLength == 0) {// 判断是否手动选取，true不是
			$setAll.attr("checked", "checked");
			$("input[type=checkbox]", $inputSpan).each(function(){
				$(this).attr("checked", "checked");
			});
		}
	} else {
		if ($setAll.is(":checked") == true) {// 判断是否手动选取，true不是
			$setAll.removeAttr("checked");		
			$("input[type=checkbox]", $inputSpan).each(function(){
				$(this).removeAttr("checked");
			});
		}
	}
} 

$(document).ready(function(){
	var t = $("#cPermissonTree");
	t = $.fn.zTree.init(t, setting, zNodes);
	t.expandAll(true); 
	
	// 给全选加入事件
    $(".setAll").click(function(){
    	var isChecked = $(this).is(":checked");
    	var $inputSpan = $(this).nextAll(".inputValueRole");
		$("input[type=checkbox]", $inputSpan).each(function(){
			if (isChecked == true) {
				$(this).attr("checked", "checked");
			} else {
				$(this).removeAttr("checked");
			}
		});
    });
	
	$(".inputValueRole input[type=checkbox]").click(function(){
		var isChecked = $(this).is(":checked");
		if (isChecked == false) {
			$(this).parent().prevAll(".setAll").removeAttr("checked");
		} 
	});	
});

//-->
</script>

<div class="pageContent">
<form method="post" action="${contextPath }/management/security/role/create" class="required-validate pageForm" onsubmit="return validateCallback(this, dialogReloadNavTab);">
	<div class="pageFormContent" layoutH="58">
	<dl>
		<dt>角色名称：</dt>
		<dd>
			<input type="text" name="name" class="validate[required,maxSize[32]] required" size="30" maxlength="32" alt="请输入角色名称"/>
		</dd>
	</dl>
	<dl>
		<dt>描述：</dt>
		<dd>
			<input type="text" name="validate[maxSize[255]] description" size="30" maxlength="255" alt="请输入描述"/>
		</dd>
	</dl>	
	<div class="divider"></div>
		<ul id="cPermissonTree" class="ztree"></ul>	
	</div>
	
	<div class="formBar">
		<ul>
			<li><div class="button"><div class="buttonContent"><button type="submit">确定</button></div></div></li>
			<li><div class="button"><div class="buttonContent"><button type="button" class="close">关闭</button></div></div></li>
		</ul>
	</div>
</form>
</div>