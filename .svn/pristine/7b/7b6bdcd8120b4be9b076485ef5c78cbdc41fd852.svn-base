<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set var="tabId" value="showAddDialog"/>
<c:set var="formId" value="check_result_list_form"/>
<c:set var="searchId" value="check_result_search"/>
<c:set var="tableId" value="check_result_list"/>

<form id="${formId}" action="${ctx }/train/effect/check/listResult.do?tabId=${tabId }" method="post">
<div id="${searchId}" style="margin: 5px">
	<div style="margin: 3px">
		项目名称:
		<input class="easyui-textbox" type="text" name="paramMap[projectName]" value="${searchParam.paramMap.projectName}" />
		专家名称:
		<input class="easyui-textbox" type="text" name="paramMap[teacherName]" value="${searchParam.paramMap.teacherName}" />
		状态:
		<select id="status" name="paramMap[status]" class="easyui-combobox" style="width: 158px;" editable="false">
			<option value=""></option>
			${dict:getEntryOptionsSelected('CHECK_RESULT_STATUS',searchParam.paramMap.status) }
		</select>
		<button type="button" class="easyui-linkbutton main-btn" onclick="easyuiQuery('${formId}','${tabId}');"><i class="fa fa-search"></i> 查询</button>
		<button type="button" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel('${tabId}')"><i class="fa fa-eraser"></i> 重置</button>
	</div>
	<div style="margin: 3px">
	
	</div>	
</div>
 <table id="${tableId}" title=""	toolbar="#${tabId } #${searchId}" pagination="true"
 	rownumbers="true" fitColumns="true" singleSelect="true" checkOnSelect="true" selectOnCheck="true">
			<thead>
				<tr>
                	<th data-options="field:'id',checkbox:true"></th>
					<th field="projectName" width="50">项目名称</th>
					<th field="teacherName" width="30">专家名称</th>
					<th field="status" width="20">状态</th>
					<th field="result" width="150">评估结果</th>
				</tr>
			</thead>
			<tbody>   
       	<c:forEach items="${checkResults}" var="checkResult">
        <tr>   
           <td>${checkResult.id }</td>
           <td>${checkResult.projectName }</td>
           <td>${checkResult.teacherName }</td>
           <td>
           		<span style="color:${checkResult.status eq '02'?'green':'red'}">${dict:getEntryName('CHECK_RESULT_STATUS',checkResult.status)}</span>
           		
           </td>
           <td>${checkResult.result }</td>
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

<script type="text/javascript">
$(document).ready(function(e) {
	
});

</script>