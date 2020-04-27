<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<c:set var="toDoCount" value=""></c:set>
<c:if test="${searchParam.pagination.rowCount>0}">
<c:set var="toDoCount" value="<font color='red'>(${searchParam.pagination.rowCount})</font>"></c:set>
</c:if>
<div id="viewPanel" class="easyui-panel" data-options="" style="height: 95%">
	<div id="viewListTabs" class="easyui-tabs" data-options="tabPosition:'top',height:getHeight('viewPanel',100),cache:false">
 		<div id="viewListAll" data-options="href:'${ctx}/train/course/listAll.do'" title="所有列表" ></div>
 		<div id="viewListEdit" data-options="href:'${ctx}/train/course/listEdit.do?paramMap[status]=00,02'" title="编辑中" ></div>
<%-- 		<div id="viewListTodo" data-options="href:'${ctx}/train/course/listTodo.do'" title="待办理${toDoCount }" onclick="setTitle()"></div> --%>
<%--  		<div id="viewListDone" data-options="href:'${ctx}/train/course/listDone.do'" title="已办理" ></div> --%>
<%-- 		<div id="viewListY" data-options="href:'${ctx}/train/course/listPass.do'" title="已通过" ></div> --%>
<%-- 		<div id="viewListN" data-options="href:'${ctx}/train/course/listUnPass.do'" title="未通过" ></div> --%>
		
	</div>
</div>
<script type="text/javascript">
/* function setTitle(){
	$('#viewListTabs').tabs('update', {
		tab: $("#viewListTodo"),
		options: {
			title: "待办理" 
		}
	});
} */
</script>