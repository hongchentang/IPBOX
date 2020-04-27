<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<!-- 替代${param['tabId']} -->
<c:set var="paramTabId" value="flowListTopTab"/>
<div style="margin: 5px;margin-bottom: 27px">
<form id="flow_list_form" action="${ctx }/common/flow/listFlow.do?tabId=${paramTabId}" method="post">

    <div id="listFlowTableSearch" style="margin: 5px">
        <div style="margin: 4px">
				<a href="javascript:void(0)" onclick="deployFlow()" class="easyui-linkbutton" title="流程部署">
						<span class="fa fa-cogs"></span>
						流程部署
				</a>
				<a href="javascript:void(0)" onclick="suspendFlow()" class="easyui-linkbutton" title="挂起">
						<span class="fa fa-lock"></span>
						挂起
				</a>
				<a href="javascript:void(0)" onclick="activeFlow()" class="easyui-linkbutton" title="激活">
						<span class="fa fa-unlock"></span>
						激活
				</a>
				
				<a href="javascript:void(0)" onclick="transToModel()" class="easyui-linkbutton" title="转换模型">
						<span class="fa fa-undo"></span>
						转换模型
				</a>
				
				<a href="javascript:void(0)" onclick="deleteFlow()" class="easyui-linkbutton delete-btn" title="删除">
						<span class="fa fa-times"></span>
						删除
				</a>
        </div>
	</div>
 <table id="flowTable" style="width:98%;height:auto; min-height:400px"
            	toolbar="#${paramTabId} #listFlowTableSearch"
            	rownumbers="true" pagination="true" singleSelect="true" checkOnSelect="true" selectOnCheck="true" nowrap="false" fitColumns="true" > 
			<thead> 
				<tr>
                	<th width="30" data-options="field:'deploymentId',checkbox:true"></th>
                	<th field="processid"  width="30" >ID</th>
					<th field="key" width="30">KEY</th>
					<th field="name" width="30">名称</th>
					<th field="xml"  width="30">XML</th>
					<th field="flow_img" width="30">流程图</th>
					<th field="deploytime" width="30">部署时间</th>
					<th field="state" width="30">状态</th>
					<th data-options="field:'processID',hidden:true">processId</th> 
					<th data-options="field:'suspended',hidden:true">suspended</th> 
				</tr>
			</thead>
			<tbody>   
       	<c:forEach items="${flows }" var="flow">
       		<c:set var="process" value="${flow[0] }" />
			<c:set var="deployment" value="${flow[1] }" />
        <tr>   
           <td>${process.deploymentId }</td>
           <td>${process.id }</td>
           <td>${process.key }</td>
           <td>${process.name }</td>
           <td><a target="_blank" href='${ctx }/common/flow/resource/read.do?processDefinitionId=${process.id}&resourceType=xml'>${process.resourceName }</a></td>
           <td><a target="_blank" href='${ctx }/common/flow/resource/read.do?processDefinitionId=${process.id}&resourceType=image'>${process.diagramResourceName }</a></td>
           <td><fmt:formatDate value="${deployment.deploymentTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
           <td>
           		<c:choose>
           			<c:when test="${process.suspended}">
           				挂起
           			</c:when>
           			<c:otherwise>
           				激活
           			</c:otherwise>
           		</c:choose>
           </td>
           <td>${process.id}</td>
           <td>${process.suspended}</td>
        </tr>   
       </c:forEach>
    </tbody>   
</table> 
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
	<jsp:param name="pageForm" value="flow_list_form" />
    <jsp:param name="tableId" value="flowTable"/>
	<jsp:param name="type" value="easyui" />
	<jsp:param name="divId" value="${paramTabId}" />
</jsp:include>
 </form>
 </div>
<script type="text/javascript">
//发布流程
function deployFlow(){
	easyuiUtils.openWindow('showAddDialog','部署新流程',400,140,'${ctx}/common/flow/deploy.do',true);
}

function isSelectFlow() {
	var objects = getCurrentTab().find('#flowTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条记录');
		return false;
	} else {
		return true;
	}
}
//删除流程
function deleteFlow() {
	if(!isSelectFlow()) {
		return false;
	}
	var objects = getCurrentTab().find('#flowTable').datagrid('getSelections');
	var deploymentId = objects[0].deploymentId;
	$.messager.confirm('提示', '删除部署的流程，会级联删除流程实例，确定删除？', function(r){
		if (r){
			var data=$.ajaxSubmitValue('${ctx }/common/flow/delete.do?deploymentId='+deploymentId);
			var json=jQuery.parseJSON(data);
			var message = json.message;
			$.messager.alert('提示',message,'info');
			jQuery('#${paramTabId}').panel('refresh');
		}
	});
}
//激活流程
function activeFlow() {
	if(!isSelectFlow()) {
		return false;
	}
	var objects = getCurrentTab().find('#flowTable').datagrid('getSelections');
	var processID = objects[0].processID;
	var suspended = objects[0].suspended;
	if("false"==suspended) {
		$.messager.alert('提示',"流程已激活",'info');
		return false;
	}
	$.messager.confirm('提示', '确定激活？', function(r){
		if (r){
			var data=$.ajaxSubmitValue('${ctx }/common/flow/update/active.do?processID='+processID);
			var json=jQuery.parseJSON(data);
			var message = json.message;
			$.messager.alert('提示',message,'info');
			jQuery('#${paramTabId}').panel('refresh');
		}
	});
}
//挂起流程
function suspendFlow() {
	if(!isSelectFlow()) {
		return false;
	}
	var objects = getCurrentTab().find('#flowTable').datagrid('getSelections');
	var processID = objects[0].processID;
	var suspended = objects[0].suspended;
	if("true"==suspended) {
		$.messager.alert('提示',"流程已挂起",'info');
		return false;
	}
	$.messager.confirm('提示', '确定挂起？', function(r){
		if (r){
			var data=$.ajaxSubmitValue('${ctx }/common/flow/update/suspend.do?processID='+processID);
			var json=jQuery.parseJSON(data);
			var message = json.message;
			$.messager.alert('提示',message,'info');
			jQuery('#${paramTabId}').panel('refresh');
		}
	});
}
//转换为模型
function transToModel() {
	if(!isSelectFlow()) {
		return false;
	}
	var objects = getCurrentTab().find('#flowTable').datagrid('getSelections');
	var processDefinitionId = objects[0].processid;
	$.messager.confirm('提示', '确定转换？', function(r){
		if (r){
			var data=$.ajaxSubmitValue('${ctx }/common/flow/transToModel.do?processDefinitionId='+processDefinitionId);
			var json=jQuery.parseJSON(data);
			var message = json.message;
			$.messager.alert('提示',message,'info');
		}
	});
}
</script>