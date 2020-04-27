<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<c:set var="toDoCount" value=""></c:set>
<c:if test="${searchParam.pagination.rowCount>0}">
<c:set var="toDoCount" value="<font color='red'>(${searchParam.pagination.rowCount})</font>"></c:set>
</c:if>
<div id="viewProjectPanel" class="easyui-panel" data-options="" style="height: 95%">
	<div id="viewProjectListTabs" class="easyui-tabs" data-options="tabPosition:'top',height:getHeight('viewProjectPanel',100),cache:true">
		<div id="viewProjectListAll" data-options="href:'${ctx}/train/project/listAll.do'" title="所有列表" ></div>
		<div id="viewProjectListEdit" data-options="href:'${ctx}/train/project/listEdit.do'" title="编辑中" ></div>
 		<div id="viewProjectListTodo" data-options="href:'${ctx}/train/project/listTodo.do'" title="待办理${toDoCount }" ></div>
<%--  		<div id="viewProjectListDone" data-options="href:'${ctx}/train/project/listDone.do'" title="已办理" ></div> --%>
		<div id="viewProListY" data-options="href:'${ctx}/train/project/listPass.do'" title="已通过" ></div>
		<div id="viewProListN" data-options="href:'${ctx}/train/project/listUnPass.do'" title="未通过" ></div>
	</div>
</div>
