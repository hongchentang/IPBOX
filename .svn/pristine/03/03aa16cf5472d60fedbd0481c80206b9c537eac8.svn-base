<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set var="tabId" value="survey_list_tabid"/>
<c:set var="formId" value="survey_list_form"/>
<c:set var="searchId" value="survey_search"/>
<c:set var="tableId" value="survey_list"/>
<c:if test="${!noskin}">
	<div class="mod">
		<div class="tit clearfix">
			<h3>调查问卷</h3>
		</div>
	</div>
</c:if>
<div id="${tabId}" class="easyui-panel" style="border: 0px;">
<form id="${formId}" action="${ctx }/space/survey/noskin/listSurvey.do?tabId=${tabId}" method="post">
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
					<th field="title" width="75%">标题</th>
					<th field="state" width="10%">状态</th>
					<th field="opr" width="10%"></th>
				</tr>
			</thead>
			<tbody>   
       	<c:forEach items="${surveies}" var="survey">
        <tr>   
           <td>${survey.id }</td>
           <td>${survey.title }</td>
           <td>
           		<c:choose>
           			<c:when test="${survey.completionStatus eq 'complete'}">
           				<span style="color: green">已参与</span>
           			</c:when>
           			<c:otherwise>
           				<span style="color: red">未参与</span>
           			</c:otherwise>
           		</c:choose>
           </td>
           <td>
           		<c:choose>
           			<c:when test="${survey.completionStatus eq 'complete'}">
           				<a style="text-decoration: underline;" href="${ctx}/space/survey/surveyStat.do?id=${survey.id}">查看结果</a>
           			</c:when>
           			<c:otherwise>
           				<a style="text-decoration: underline;" href="${ctx}/space/survey/userSurvey.do?id=${survey.id}">立即参与</a>
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