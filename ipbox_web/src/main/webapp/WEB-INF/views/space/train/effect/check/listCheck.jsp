<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set var="tabId" value="check_list_tabid"/>
<c:set var="formId" value="check_list_form"/>
<c:set var="searchId" value="check_search"/>
<c:set var="tableId" value="check_list"/>
<c:if test="${!noskin}">
	<div class="mod">
		<div class="tit clearfix">
			<h3>抽查评估</h3>
		</div>
	</div>
</c:if>
<div id="${tabId}" class="easyui-panel" style="border: 0px;">
<form id="${formId}" action="${ctx }/space/check/noskin/listCheck.do?tabId=${tabId}" method="post">
<div id="${searchId}" style="margin: 5px">
	<div style="margin: 3px">
	</div>
	<div style="margin: 3px">
	</div>	
</div>
 <table id="${tableId}" title=""	toolbar="#${tabId} #${searchId}" pagination="true"
 	rownumbers="true" fitColumns="true" singleSelect="true" checkOnSelect="true" selectOnCheck="true">
			<thead>
				<tr>
                	<th data-options="field:'id',hidden:true"></th>
					<th field="projectName" width="75%">项目名称</th>
					<th field="status" width="10%">状态</th>
					<th field="opr" width="10%"></th>
				</tr>
			</thead>
			<tbody>   
       	<c:forEach items="${checkResults}" var="checkResult">
        <tr>
           <td>${checkResult.id}</td>   
           <td>${checkResult.projectName}</td>
           <td>
           		<span style="color:${checkResult.status eq '02'?'green':'red'}">
           			${dict:getEntryName('CHECK_RESULT_STATUS',checkResult.status)}
           		</span>	
           	</td>
           <td>
           		<c:choose>
           			<c:when test="${checkResult.status eq '02'}">
           				<a style="text-decoration: underline;" href="${ctx}/space/check/checkResult.do?id=${checkResult.id}">查看结果</a>
           			</c:when>
           			<c:otherwise>
           				<a style="text-decoration: underline;" href="${ctx}/space/check/check.do?id=${checkResult.id}">立即评估</a>
           			</c:otherwise>
           		</c:choose>
           </td>
        </tr>   
       </c:forEach>
    </tbody>
</table> 
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
	<jsp:param name="pageForm" value="${formId}" />
    <jsp:param name="tableId" value="${tableId}"/>
	<jsp:param name="type" value="easyui" />
	<jsp:param name="divId" value="${tabId}" />
</jsp:include>
 </form>
	
</div>
<script type="text/javascript">
$(document).ready(function(e) {

});

</script>