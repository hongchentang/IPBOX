<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set var="tabId" value="train_list_tabid"/>
<c:set var="formId" value="train_list_form"/>
<c:set var="searchId" value="train_search"/>
<c:set var="tableId" value="train_list"/>
<div id="${tabId}" class="easyui-panel" style="border: 0px;">
<form id="${formId}" action="${ctx}/space/archive/noskin/listTrainRegister.do?tabId=${tabId}" method="post">
<div id="${searchId}" style="margin: 5px">
	<div style="margin: 3px">
	</div>
</div>
 <table id="${tableId}" title=""	toolbar="#${tabId} #${searchId}" pagination="true"
 	rownumbers="true" fitColumns="true" singleSelect="true" checkOnSelect="true" selectOnCheck="true">
			<thead>
				<tr>
                	<th data-options="field:'id',hidden:true">id</th>
					<th field="trainName" width="25%">所报培训班</th>
					<th field="createTime" width="10%">报名时间</th>
					<th field="status" width="6%">状态</th>
					<th field="auditContent" width="40%">审核意见</th>
					<th field="opr" ></th>
					<th data-options="hidden:true" field="trainId" >培训班编码</th>
					
				</tr>
			</thead>
			<tbody>   
       	<c:forEach items="${listTrainRegister}" var="l">
        <tr>   
           <td>${l.id }</td>
           <td>${l.trainName }</td>
           <td><fmt:formatDate value="${l.createTime }" pattern="yyyy-MM-dd"/>
           </td>
           <td>${dict:getEntryName('IPR_TRAIN_STATUS',l.status) }</td>
           <td>${l.auditContent }</td>
           <td>
           	<a style="text-decoration: underline;" href="javascript:void(0);" onclick="viewTrain('${l.trainId}')">培训班详情</a>
<%--            	<a style="text-decoration: underline;color: red" href="${ctx}/space/evaluate/evaluate.do?id=${l.trainId}" title="点击评价">待评价</a> --%>
           	<%--审批通过且培训班已经结束，则可以评价 --%>
           	<c:if test="${l.status eq '01' and l.endTime < now }">
           	<c:if test="${l.courseEvaluaCount==0&&l.teachingEvaluaCount==0}">
           		<a style="text-decoration: underline;color: red" href="${ctx}/space/evaluate/evaluate.do?id=${l.trainId}" title="点击评价">待评价</a>
           	</c:if>
           	<c:if test="${l.courseEvaluaCount>0&&l.teachingEvaluaCount>0}">
           		<a style="text-decoration: underline;color: red" href="${ctx}/space/evaluate/readEvaluate.do?id=${l.trainId}" title="点击查看评价">查看评价</a>
           	</c:if>
           	</c:if>
           </td>
           <td>${l.trainId }</td>
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

function viewTrain(trainId) {
	easyuiUtils.openWindow('showTrainDialog','培训班详情',900,530,'${ctx}/train/train/view.do?id='+trainId,true);
} 

</script>