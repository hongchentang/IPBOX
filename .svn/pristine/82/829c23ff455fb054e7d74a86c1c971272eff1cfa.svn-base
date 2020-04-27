<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set var="activitiExplorerUrl" value="${ipanthercore:getProperty('activiti.explorer.url')}"/>
<!-- 替代${param['tabId']} -->
<c:set var="paramTabId" value="modelListTopTab"/>
<div style="margin: 5px;margin-bottom: 27px">
<form id="model_list_form" action="${ctx }/common/flow/listModel.do?tabId=${paramTabId}" method="post">

    <div id="listModelTableSearch" style="margin: 5px">
        <div style="margin: 4px">
        		<a href="javascript:void(0)" onclick="createModel()" class="easyui-linkbutton" title="创建">
						<span class="fa fa-plus"></span>
						创建
				</a>
				<a href="javascript:void(0)" onclick="editModel()" class="easyui-linkbutton" title="编辑">
						<span class="fa fa-pencil"></span>
						编辑
				</a>
				<a href="javascript:void(0)" onclick="deployModel()" class="easyui-linkbutton" title="发布">
						<span class="fa fa-cogs"></span>
						发布
				</a>
				<a href="javascript:void(0)" onclick="deleteModel()" class="easyui-linkbutton delete-btn" title="删除">
						<span class="fa fa-times"></span>
						删除
				</a>
        </div>
	</div>
 <table id="modelTable" style="width:98%;height:auto; min-height:400px"
            	toolbar="#${paramTabId} #listModelTableSearch"
            	rownumbers="true" pagination="true" singleSelect="true" checkOnSelect="true" selectOnCheck="true" nowrap="false" fitColumns="true" > 
			<thead> 
				<tr>
                	<th width="30" data-options="field:'id',checkbox:true"></th>
                	<th field="modelId"  width="30" >ID</th>
					<th field="key" width="30">KEY</th>
					<th field="name" width="30">名称</th>
					<th field="createTime" width="30">创建时间</th>
					<th field="lastUpdateTime" width="30">最后更新时间</th>
				</tr>
			</thead>
			<tbody>     
           <c:forEach items="${models }" var="model">
				<tr>
					<td>${model.id }</td>
					<td>${model.id }</td>
					<td>${model.key }</td>
					<td>${model.name}</td>
					<td><fmt:formatDate value="${model.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td><fmt:formatDate value="${model.lastUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>
			</c:forEach>
    </tbody>   
</table> 
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
	<jsp:param name="pageForm" value="model_list_form" />
    <jsp:param name="tableId" value="modelTable"/>
	<jsp:param name="type" value="easyui" />
	<jsp:param name="divId" value="${paramTabId}" />
</jsp:include>
 </form>
 </div>
<script type="text/javascript">

function isSelectModel() {
	var objects = getCurrentTab().find('#modelTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条记录');
		return false;
	} else {
		return true;
	}
}

//发布模型
function deployModel() {
	if(!isSelectModel()) {
		return false;
	}
	var objects = getCurrentTab().find('#modelTable').datagrid('getSelections');
	var modelId = objects[0].id;
	$.messager.confirm('提示', '确定发布模型？', function(r){
		if (r){
			var data=$.ajaxSubmitValue('${ctx }/common/flow/deployModel.do?modelId='+modelId);
			var json=jQuery.parseJSON(data);
			var message = json.message;
			$.messager.alert('提示',message,'info');
			jQuery('#${paramTabId}').panel('refresh');
		}
	});
}

//编辑模型
function editModel() {
	if(!isSelectModel()) {
		return false;
	}
	var objects = getCurrentTab().find('#modelTable').datagrid('getSelections');
	var modelId = objects[0].id;
	var data = $.ajaxSubmitValue('${ctx }/common/flow/getActivitiExplorerURL.do?modelId='+modelId);
	var _json=jQuery.parseJSON(data);
	var json = jQuery.parseJSON(_json);
	var url = json.url;
	var app = json.app;
	openPostWindow(url,"编辑模型","app",app);
}

//新增模型
function createModel() {
	easyuiUtils.openWindow('showAddDialog','创建新模型',500,300,'${ctx}/common/flow/createModel.do',true);
}

//删除模型
function deleteModel() {
	if(!isSelectModel()) {
		return false;
	}
	var objects = getCurrentTab().find('#modelTable').datagrid('getSelections');
	var modelId = objects[0].id;
	$.messager.confirm('提示', '确定删除模型？', function(r){
		if (r){
			var data=$.ajaxSubmitValue('${ctx }/common/flow/deleteModel.do?modelId='+modelId);
			var json=jQuery.parseJSON(data);
			var message = json.message;
			$.messager.alert('提示',message,'info');
			jQuery('#${paramTabId}').panel('refresh');
		}
	});
}

//window.open 用post提交
function openPostWindow(url,windowName,argName,argValue) {
    var tempForm = document.createElement("form");  
    tempForm.id="tempForm";
    tempForm.method="post";
    tempForm.action=url;
    tempForm.target=windowName;
    
    var hideInput = document.createElement("input");
    hideInput.type="hidden";
    hideInput.name= argName;
    hideInput.value= argValue;
    tempForm.appendChild(hideInput);
    
    if (tempForm.addEventListener) {
    	tempForm.addEventListener("onsubmit", function(){ window.open('about:blank',windowName); }, false);
	}
	else if (tempForm.attachEvent) {
		tempForm.attachEvent("ononsubmit", function(){ window.open('about:blank',windowName); });
	}
    
    document.body.appendChild(tempForm);
    tempForm.submit();
    document.body.removeChild(tempForm);
}
</script>