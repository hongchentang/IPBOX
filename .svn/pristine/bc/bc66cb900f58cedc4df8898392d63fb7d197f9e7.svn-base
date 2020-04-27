<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<c:set var="toDoCount" value=""></c:set>
<c:if test="${searchParam.pagination.rowCount>0}">
<c:set var="toDoCount" value="<font color='red'>(${searchParam.pagination.rowCount})</font>"></c:set>
</c:if>
<div id="viewTeacherTopPanel" class="easyui-panel" data-options="" style="height:98%">
	<div id="viewTeacherTopListTabs" class="easyui-tabs" data-options="tabPosition:'top',height:getHeight('viewTeacherTopPanel',100),cache:false">
	<%--只有单位管理员才可以编辑用户 --%>
	<c:if test="${ipanthercommon:isDeptAdmin(sessionScope.loginUser.id)}">
		<div id="viewTeacherAllBaseTopTab" data-options="href:'${ctx}/common/user/teacher/list.do?tabId=viewTeacherAllBaseTopTab'" title="全部" ></div>
		<div id="viewTeacherEditBaseTopTab" data-options="href:'${ctx}/common/user/teacher/auditListEdit.do?tabId=viewTeacherEditBaseTopTab'" title="编辑中" ></div>
	</c:if>	
		<div id="viewTeacherTodoBaseTopTab" data-options="href:'${ctx}/common/user/teacher/auditListTodo.do?tabId=viewTeacherTodoBaseTopTab'" title="待办理${toDoCount }" ></div>
		<div id="viewTeacherDoneBaseTopTab" data-options="href:'${ctx}/common/user/teacher/auditListDone.do?tabId=viewTeacherDoneBaseTopTab'" title="已办理" ></div>
	</div>
	
</div>