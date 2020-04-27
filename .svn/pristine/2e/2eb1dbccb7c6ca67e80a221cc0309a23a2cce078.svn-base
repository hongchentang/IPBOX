<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>

<c:set var="tabId" value="viewUserQuestionTopTab"/>


<div class="notice nc-mod">
	<div class="tit clearfix">
		<h3>在线留言列表</h3>
	</div>
	<div class="con">
		<div id="${tabId}">
			<form id="userQuestion_list_form" action="${ctx}/space/frontUserQuestion/frontlistUserQuestion.do" method="post">
			<div id="userQuestion_search">
				<div style="margin: 10px">
					
				</div>
				<div style="margin: 10px">
					<button type="button" class="easyui-linkbutton" onclick="viewUserQuestion()"><i class="fa fa-search"></i> 查看</button>
					
				</div>	
			</div>
			
			 <table id="userQuestion_list" title=""	toolbar="#${tabId} #userQuestion_search" pagination="true"
			 	rownumbers="true" fitColumns="true"  singleSelect="true" checkOnSelect="true" selectOnCheck="true">
						<thead>
							<tr>
			                	<th data-options="field:'id',checkbox:true"></th>
								<th field="awardProject" width="20">提交日期</th>
								<th field="awardLevel" width="40">问题描述</th>
								<th field="awardDrade" width="40">问题回复</th>
							</tr>
						</thead>
						<tbody>   
			       	<c:forEach items="${userQuestionList}" var="userQuestion">
			        <tr>   
			           <td>${userQuestion.id }</td>
			           <td>${userQuestion.startTime}</td>
			           <td>${userQuestion.question}</td>
			           <td>${userQuestion.answer}</td>
			        </tr>   
			       </c:forEach>
			    </tbody>   
			</table> 
			<jsp:include page="/jsp/common/include/manage_page_table.jsp">
				<jsp:param name="pageForm" value="userQuestion_list_form" />
			    <jsp:param name="tableId" value="userQuestion_list"/>
				<jsp:param name="type" value="easyui" />
				<jsp:param name="divId" value="${tabId}" />
			</jsp:include>
			 </form>
			</div>
	</div>
</div>
<script type="text/javascript">
$(document).ready(function(e) {
	
});


function viewUserQuestion(){

	var selected = $("#userQuestion_list").datagrid("getSelections");
	if(!selected.length>0){
		$.messager.alert('消息','请选择一条记录','warning');
		return false;
	}
	
	var id = selected[0].id;
	easyuiUtils.openWindow('viewUserQuestionDialog','查看在线留言',700,300,'${ctx}/cms/userQuestion/goViewUserQuestion.do?id='+id,true);
}



</script>