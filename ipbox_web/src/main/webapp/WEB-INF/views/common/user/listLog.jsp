<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set var="tabId" value="viewFlowLogTopTab"/>
<form id="flowLog_list_form" action="${ctx}/common/user/listLog.do?paramMap[userId]=${searchParam.paramMap.userId}" method="post">
<div id="flowLog_search">
	<div style="margin: 10px">
		
	</div>
	<div style="margin: 10px">
		
	</div>	
</div>
 <table id="flowLog_list" title="日志列表" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
			<thead>
				<tr>
                	<th width="80" data-options="field:'taskType'">类型</th>
                	<th width="130" data-options="field:'taskName'">环节</th>
					<th width="80" data-options="field:'auditUserName'">审核人</th>
					<th width="140" data-options="field:'auditDeptName'">审核单位</th>
					<th width="120" data-options="field:'auditTime'">审核时间</th>
					<th width="70" data-options="field:'auditResult'">审核结果</th>
					<th width="300" data-options="field:'auditContent'">审核意见</th>
				</tr>
			</thead>
			<tbody>   
       	<c:forEach items="${logs }" var="log">
        <tr>
        	<td>
        		<c:choose>
        			<c:when test="${log.taskId eq '01'}">
        				人才申请
        			</c:when>
        			<c:otherwise>
        				教师申请
        			</c:otherwise>
        		</c:choose>
        	</td>
        	<td>${log.taskName}</td>
			<td>${ipanthercommon:getUserRealName(log.auditUser)}</td>
			<td>${ipanthercommon:getDeptName(log.auditDept)}</td>
			<td><fmt:formatDate value="${log.auditTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
			<td>${dict:getEntryName('FLOW_AUDIT_RESULT',log.auditResult)}</td>
			<td>${log.auditContent}</td>
        </tr>   
       </c:forEach>
    </tbody>   
</table> 
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
	<jsp:param name="pageForm" value="flowLog_list_form" />
    <jsp:param name="tableId" value="flowLog_list"/>
	<jsp:param name="type" value="easyui" />
	<jsp:param name="divId" value="${tabId}" />
</jsp:include>
 </form>
<script type="text/javascript">
$(document).ready(function(e) {
	
});
</script>