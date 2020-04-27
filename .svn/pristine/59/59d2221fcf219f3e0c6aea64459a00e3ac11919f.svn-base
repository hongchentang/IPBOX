<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="deptLayout" class="easyui-layout" style="width:100%;height:100%;">
    <div id="deptTrees" data-options="region:'west',panelWidth:'auto',panelHeight:'auto'" title="企业" style="width:20%;padding:10px;">
        <ul id="deptTree" style="margin-bottom: 24px"> </ul>
    </div>
    <div id="userContent" data-options="region:'center'">
        <div id="user_list_div" data-options="border:false" style="width:100%;height:100%;">
        	<h1 style="margin-top: 100px;margin-left: 40%">请点击左边的树进行操作.</h1>
        </div>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(e) {
  
	//读取默认当前登录人的区域
	$('#user_list_div',getCurrentTab()).panel({
		href:"${ctx}/common/user/employees/list.do?tabId=${param['tabId']}&paramMap[deptId]=${sessionScope.loginUser.firstDeptId}",
	});
	
	$("#deptTree",getCurrentTab()).tree({
		data:JSON.parse('${treeStr}'),
		parentField : 'pid',
		panelWidth:'auto', 
		panelHeight:'auto', 
		onClick:function(node){
			$('#user_list_div',getCurrentTab()).panel("options").queryParams={};
			$('#user_list_div',getCurrentTab()).panel("refresh","${ctx}/common/user/employees/list.do?tabId=${param['tabId']}&paramMap[deptId]="+node.id);
		},
		onLoadSuccess:function() {
			//展开第一层区域
			var root = $("#deptTree",getCurrentTab()).tree("getRoot");
			$("#deptTree",getCurrentTab()).tree("expand",root.target);
		}
	});
	$("#deptTree",getCurrentTab()).tree('select',$("#deptTree",getCurrentTab()).tree('find','${sessionScope.loginUser.firstDeptId}').target);
	
});
</script>
