<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="deptLayout" class="easyui-layout" style="width:100%;height:100%;">
    <div id="p" data-options="region:'west',panelWidth:'auto',panelHeight:'auto'" title="区域" style="width:20%;padding:10px;">
        <ul id="areaTree" style="margin-bottom: 24px"> </ul>
    </div>
    <div id="deptContent" data-options="region:'center'">
        <div id="dept_list_div" data-options="border:false" style="width:100%;height:100%;">
        	<h1 style="margin-top: 100px;margin-left: 40%">请点击左边的树进行操作.</h1>
        </div>
    </div>
</div>
<script type="text/javascript">
$(document).ready(function(e) {
  
	//读取默认当前登录人的区域
	$('#dept_list_div',getCurrentTab()).panel({
		href:"${ctx}/common/dept/list.do?tabId=${param['tabId']}&paramMap[regionsCode]=${sessionScope.loginUser.regionsCode}",
	});
	
	$("#areaTree",getCurrentTab()).tree({
		data:JSON.parse('${treeStr}'),
		parentField : 'pid',
		panelWidth:'auto', 
		panelHeight:'auto', 
		onClick:function(node){
			$('#dept_list_div',getCurrentTab()).panel("options").queryParams={};
			$('#dept_list_div',getCurrentTab()).panel("refresh","${ctx}/common/dept/list.do?tabId=${param['tabId']}&paramMap[regionsCode]="+node.id);
		},
		onLoadSuccess:function() {
			//展开第一层区域
			var root = $("#areaTree",getCurrentTab()).tree("getRoot");
			$("#areaTree",getCurrentTab()).tree("expand",root.target);
		}
	});
	$("#areaTree",getCurrentTab()).tree('select',$("#areaTree",getCurrentTab()).tree('find','${sessionScope.loginUser.regionsCode}').target);
	
});
</script>
