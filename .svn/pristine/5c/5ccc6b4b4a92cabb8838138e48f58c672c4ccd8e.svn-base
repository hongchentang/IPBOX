<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<c:set var="tabId" value="chooseProjectWindow"/>
<c:set var="formId" value="choose_project_list_form"/>
<c:set var="searchId" value="choose_project_search"/>
<c:set var="tableId" value="choose_project_list"/>
<form id="${formId}" action="${ctx}/train/project/chooseProject.do?paramMap[notIds]=${searchParam.paramMap.notIds}" method="post">
		<div id="${searchId}" style="margin: 5px">
			<div style="margin: 3px">
				项目名称:
				<input class="easyui-textbox" type="text" name="paramMap[projectName]" value="${searchParam.paramMap.projectName }">
				<button type="button" class="easyui-linkbutton main-btn" onclick="easyuiQuery('${formId}','${tabId}');"><i class="fa fa-search"></i> 查询</button>
				<button type="button" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel('${tabId}')"><i class="fa fa-eraser"></i> 重置</button>
			</div>
			<div style="margin: 3px">
				<button type="button" onclick="confirmProject()" class="easyui-linkbutton main-btn" ><i class="fa fa-plus"></i>选择</button>
			</div>	
		</div>
		<table id="${tableId}" title="" idField="id" toolbar="#${tabId} #${searchId}" pagination="true"
 	rownumbers="true" fitColumns="true" singleSelect="false" checkOnSelect="true" selectOnCheck="true">
			<thead>
				<tr>
					<th width="30" data-options="field:'id',checkbox:true"></th>
					<th width="100" data-options="field:'projectCode'">项目编号</th>
					<th width="100" data-options="field:'projectName'">项目名称</th>
					<th width="100" data-options="field:'year'">项目实施年度</th>
					<th width="100" data-options="field:'expertId'">首席专家</th>
					<th width="100" data-options="field:'headId'">部门负责人</th>
					<th width="100" data-options="field:'headUnit'">项目执行部门</th>
					<th width="100" data-options="field:'projectSubject'">学科（领域）</th>
					<th width="100" data-options="field:'cash'">项目总经费</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${projects }" var="project">
					<tr>
						<td>${project.id }</td>
						<td>${project.projectCode }</td>
						<td>${project.projectName }</td>
						<td>${project.year }</td>
						<td>${project.expertId }</td>
						<td>${project.headId }</td>
						<td>${project.headUnit }</td>
						<td>${project.projectSubject }</td>
						<td>${project.cash }</td>
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

<script>
//确认选择，回调
function confirmProject() {
	var selectedData = easyuiUtils.getSelectedData('${tableId}',true,'${tabId}');
	if(null!=selectedData) {
		var result = "";
		for(var i =0;i<selectedData.length;i++) {
			var id = selectedData[i].id;
			var projectName = selectedData[i].projectName;
			result += "★"+id+"☆"+projectName;
		}
		if(""!=result) {
			addProject(result.substr(1));
		}
		closeWindow('${tabId}');
	}
}

</script>