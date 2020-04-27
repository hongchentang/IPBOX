<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set var="suffix" value="${param.tabId}"/><!-- 用于jsp被多次同时用到时区分各个DIV 的ID和函数名称 -->
<c:set var="formId" value="audit_teacher_list_form${suffix}"/>
<c:set var="searchId" value="audit_teacher_search${suffix}"/>
<c:set var="tableId" value="audit_teacher_list${suffix}"/>

<c:set var="currentDeptId" value="${sessionScope.loginUser.deptId}"/>
<c:set var="isAdminCompetent" value="${ipanthercommon:isAdminCompetent(sessionScope.loginUser.deptType)}"/>
<form id="${formId}" action="${ctx }/common/user/teacher/${request}.do?tabId=${param.tabId }" method="post">
<input type="hidden" id="action" name="paramMap[action]" value=""/><!-- 区别查询还是导出，导出为：expoert -->
<div id="${searchId}" style="margin: 5px">
	<div style="margin: 3px">
		姓名:
		<input class="easyui-textbox" type="text" name="paramMap[realName]" value="${searchParam.paramMap.realName}" style="width:100px;"/>
		区域:
		<select id="regionsCode" name="paramMap[regionsCode]" style="width:100px"></select>
		<script type="text/javascript">
			$(function(){
				getCurrentTab().find('#${searchId}').find('#regionsCode').combotree({    
				    data: jQuery.parseJSON('${ipanthercommon:getRegionsJson(true)}'),    
				    valueField:'id',    
				    textField:'text',
				    parentField:'pid',
				    panelWidth:200,
				    onLoadSuccess:function(){
				    	if('${searchParam.paramMap.regionsCode}'!=''){
				    		getCurrentTab().find('#${searchId}').find('#regionsCode').combotree('setValue','${searchParam.paramMap.regionsCode}');
						}
				    }
				}); 
			});
		</script>
		单位:
		<input class="easyui-textbox" type="text" name="paramMap[deptName]" value="${searchParam.paramMap.deptName}" style="width:100px;"/>
		专家类别:
		<select name="paramMap[expertType]" class="easyui-combobox">
		<option value="">-请选择-</option>
		${dict:getEntryOptionsSelected('EXPERT_TYPE',searchParam.paramMap.expertType) }
		</select>
		年龄:
		<input type="text" class="easyui-numberbox" name="paramMap[ageMin]" value="${searchParam.paramMap.ageMin}" style="width:30px"/>
		-
		<input type="text" class="easyui-numberbox" name="paramMap[ageMax]" value="${searchParam.paramMap.ageMax}" style="width:30px"/>
		职称:
		<input class="easyui-textbox" type="text" name="paramMap[title]" value="${searchParam.paramMap.title}" style="width:100px;"/>
		研究领域:<input type="text" class="ipt" name="paramMap[researchField]" value="${searchParam.paramMap.researchField}" style="width:100px;">
		<button type="button" class="easyui-linkbutton main-btn" onclick="javascript:easyuiQuery('${formId}','${param.tabId}');"><i class="fa fa-search"></i> 查询</button>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel('${param.tabId}')" ><i class="fa fa-eraser"></i> 重置</a>
	</div>
	<div style="margin: 3px">
		<button type="button" id="viewButton" class="easyui-linkbutton" ><i class="fa fa-search"></i>查看</button>
		<c:if test="${type eq 'Todo' }">
	  		<button type="button" id="auditButton" class="easyui-linkbutton" ><i class="fa fa-pencil"></i>审核</button>
	  	</c:if>
	</div>	
</div>
 <table id="${tableId}" title=""	toolbar="#${param.tabId } #${searchId}" pagination="true"
 	rownumbers="true" fitColumns="true"  singleSelect="true" checkOnSelect="true" selectOnCheck="true">
			<thead>
				<tr>
                	<th data-options="field:'id',checkbox:true"></th>
					<th field="userName" width="25">用户名</th>
					<th field="realName" width="25">姓名</th>
					<th field="regionsName" width="15">区域</th>
					<th field="deptName" width="40">单位</th>
					<th field="job" width="15">职务</th>
					<th field="title" width="15">职称</th>
					<th field="researchField" width="15">研究领域</th>
					<th field="mobilePhone" width="15">联系电话</th>
					<th field="taskName" width="45">当前环节</th>
					<th field="regionsCode" data-options="hidden:true">区域编码</th>
					<th field="deptId" data-options="hidden:true">deptId</th>
					<th field="registerId" data-options="hidden:true">registerId</th>
					<th field="taskId" data-options="hidden:true">taskId</th>
					<th field="procInstId" data-options="hidden:true">procInstId</th>
					<th field="procDefId" data-options="hidden:true">procDefId</th>
				</tr>
			</thead>
			<tbody>   
       	<c:forEach items="${users }" var="user">
        <tr>   
           <td>${user.id }</td>
           <td>${user.userName }</td>
           <td>${user.realName }</td>
           <td>${ipanthercommon:getRegionsNameFull(user.regionsCode)}</td>
           <td>${user.deptName }</td>
           <td>${user.job }</td>
           <td>${user.title }</td>
           <td>${user.researchField }</td>
           <td>${user.mobilePhone }</td> 
           <td>${user.taskName}</td> 
           <td>${user.regionsCode }</td>
           <td>${user.deptId }</td>
           <td>${user.registerId }</td>
           <td>${user.taskId }</td>
           <td>${user.procInstId }</td>
           <td>${user.procDefId }</td>
        </tr>   
       </c:forEach>
    </tbody>   
</table> 
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
	<jsp:param name="pageForm" value="${formId}" />
    <jsp:param name="tableId" value="${tableId}"/>
	<jsp:param name="type" value="easyui" />
	<jsp:param name="divId" value="${param['tabId']}" />
</jsp:include>
 </form>

<script type="text/javascript">
$(document).ready(function(e) {
	//查看
	getCurrentTab().find("#${param.tabId}").find('#viewButton').linkbutton({onClick: function(){
		var selectedData = easyuiUtils.getSelectedData('${tableId}');
		if(null!=selectedData) {
			var id = selectedData.id;
			var procInstId = selectedData.procInstId;
			var procDefId = selectedData.procDefId;
			var registerId = selectedData.registerId;
			easyuiUtils.openWindow('showAddDialog','查看师资信息',900,630,'${ctx}/common/user/teacher/viewTabs.do?id='+id+"&procInstId="+procInstId+"&procDefId="+procDefId+"&registerId="+registerId,true);
		}
	}});
	
	//审核
	getCurrentTab().find("#${param.tabId}").find('#auditButton').linkbutton({onClick: function(){
		var selectedData = easyuiUtils.getSelectedData('${tableId}');
		if(null!=selectedData) {
			var id = selectedData.id;
			var registerId = selectedData.registerId;
			var taskId = selectedData.taskId;
			easyuiUtils.openWindow('showAddDialog','审核师资',900,630,'${ctx}/common/user/teacher/goAudit.do?id='+id+'&registerId='+registerId+'&taskId='+taskId,true);
		}
	}});
});

</script>