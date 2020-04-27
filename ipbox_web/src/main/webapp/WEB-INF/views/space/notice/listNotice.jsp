<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set var="tabId" value="notice_list_tabid"/>
<c:set var="formId" value="notice_list_form"/>
<c:set var="searchId" value="notice_search"/>
<c:set var="tableId" value="notice_list"/>
<c:if test="${!noskin}">
	<div class="mod">
		<div class="tit clearfix">
			<h3>通知公告</h3>
		</div>
	</div>
</c:if>
<div id="${tabId}" class="easyui-panel" style="border: 0px;">
<form id="${formId}" action="${ctx }/space/notice/noskin/listNotice.do?tabId=${tabId}" method="post">
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
					<th field="title" width="80%">标题</th>
					<th field="createTime" width="10%">新增时间</th>
					<th field="opr" width="5%"></th>
				</tr>
			</thead>
			<tbody>   
       	<c:forEach items="${notices}" var="notice">
        <tr>   
           <td>${notice.id }</td>
           <td>${notice.noticeTitle }</td>
           <td>
	           	<c:choose>
					<c:when test="${not empty notice.startTime }">
						${notice.startTime}
					</c:when>
					<c:otherwise>
						<fmt:formatDate value="${notice.createTime}" pattern="yyyy-MM-dd"/>
					</c:otherwise>
				</c:choose>
           </td>
           <td><a style="text-decoration: underline;" href="${ctx}/space/notice/viewNotice.do?id=${notice.id}">查看</a></td>
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