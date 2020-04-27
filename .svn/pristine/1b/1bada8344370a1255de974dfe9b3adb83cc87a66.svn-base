<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set var="tabId" value="${param.tabId}"/><!-- 用于jsp被多次同时用到时区分各个DIV 的ID和函数名称 -->
<c:set var="formId" value="student_list_form${tabId}"/>
<c:set var="searchId" value="student_search${tabId}"/>
<c:set var="tableId" value="student_list${tabId}"/>
<form id="${formId}" action="${ctx }/common/user/student/${request}.do?tabId=${param.tabId }" method="post">
<div id="${searchId}">
	<div style="margin: 10px">
		姓名:
		<input class="easyui-textbox" type="text" name="paramMap[realName]" value="${searchParam.paramMap.realName}" />
		单位:
		<input class="easyui-textbox" type="text" name="paramMap[deptName]" value="${searchParam.paramMap.deptName}" />
		
		<button type="button" class="easyui-linkbutton main-btn" onclick="javascript:easyuiQuery('${formId}','${tabId}');"><i class="fa fa-search"></i> 查询</button>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel('${param.tabId}')" ><i class="fa fa-eraser"></i> 重置</a>
	</div>
	<div style="margin: 10px">
		<button type="button" id="viewButton" class="easyui-linkbutton" ><i class="fa fa-search"></i>查看</button>
		<c:if test="${request eq 'auditListTodo' }">
	  	<button type="button" class="easyui-linkbutton" id="auditButton"><i class="fa fa-pencil"></i>审核</button>
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
					<th field="deptName" width="40">单位</th>
					<th field="mobilePhone" width="25">联系电话</th>
					<th field="email" width="25">邮箱</th>
					<th field="studentStatus" width="25">状态</th>
					<th field="regionsCode" data-options="hidden:true">区域编码</th>
					<th field="deptId" data-options="hidden:true">deptId</th>
					<th field="taskId" data-options="hidden:true">taskId</th>
				</tr>
			</thead>
			<tbody>   
       	<c:forEach items="${users }" var="user">
        <tr>   
           <td>${user.id }</td>
           <td>${user.userName }</td>
           <td>${user.realName }</td>
           <td>${user.deptName }</td>
           <td>${user.mobilePhone }</td> 
           <td>${user.email }</td> 
           <td>${dict:getEntryName('STUDENT_STATUS',user.studentStatus)}</td> 
           <td>${user.regionsCode }</td>
           <td>${user.deptId }</td>
           <td>${user.taskId }</td>
        </tr>   
       </c:forEach>
    </tbody>   
</table> 
<input id="deptId" type="hidden" name="paramMap[deptId]" value="${searchParam.paramMap.deptId }">

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
	getCurrentTab().find('#${tabId}').find('#viewButton').linkbutton({onClick: function(){
		var selectedData = easyuiUtils.getSelectedData('${tableId}');
		if(null!=selectedData) {
			var id = selectedData.id;
			easyuiUtils.openWindow('showAddDialog','查看人才信息',900,630,'${ctx}/common/user/student/viewTabs.do?id='+id,true);
		}
	}});
	//审核
	getCurrentTab().find('#${tabId}').find('#auditButton').linkbutton({onClick: function(){
		var selectedData = easyuiUtils.getSelectedData('${tableId}');
		if(null!=selectedData) {
			var id = selectedData.id;
			easyuiUtils.openWindow('showAddDialog','审核人才',900,630,'${ctx}/common/user/student/goAudit.do?id='+id,true);
		}
	}});
});

</script>