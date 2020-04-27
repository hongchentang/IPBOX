<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div style="margin: 5px;margin-bottom: 27px">
	<!-- <div class="easyui-panel" title="课程管理" style="width: 100%;" > -->
		<form id="listPreForm" action="${ctx}/train/project/performanceTarget.do?tabId=${param['tabId']}" method="post">
			<div class="easyui-panel" title="" data-options="" style="width: 100%;">
				<div class="datagrid-toolbar" float:right>
					<table cellspacin="0" cellpadding="3px">
						<tr>
							<td>项目名称</td>
							<td><input name="paramMap[projectName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.projectName }"></td>
							<td>
								<a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.ajaxPostFormForPanel('listPreForm','${param.tabId}')"><i class="fa fa-search"></i> 查询</a>
							</td>
						</tr>	
					</table>
					<table cellspacing="0" cellpadding="0">
							<tbody>
								<tr>
									<td><a onclick="addFile()" href="javascript:void(0)" class="easyui-linkbutton" title="上传自评报告"><i class="fa fa-plus"> 自评报告</i></a></td>
									<td><div class="datagrid-btn-separator"></div></td>
								</tr>
							</tbody>
						</table>
				</div>
				<div>
					<table style="width: 100%;" id="listPreTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true">
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
								<th width="100" data-options="field:'isAdd'">项目绩效目标</th>
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
									<td>
										<a onclick="showPerformanceTarge('${project.id }','查看项目绩效目标','add')" href="javascript:void(0)" class="easyui-linkbutton" title="新增项目绩效目标">查看</a>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<jsp:include page="/jsp/common/include/manage_page_table.jsp">
						<jsp:param value="listPreForm" name="pageForm" />
						<jsp:param value="listPreTable" name="tableId" />
						<jsp:param value="easyui" name="type" />
						<jsp:param value="${param['tabId']}" name="divId" />
					</jsp:include>
				</div>
			</div>
		</form>
	</div>

<script>
function showPerformanceTarge(id,title,type){
		openWindow('showEvaluateWindow',title,0,700,'${ctx}/evalua/performanceTarget/add.do?projectPerformanceTarget.projectId='+id,true); 
}

function addFile(){
	var objects = $('#listPreTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	var id = objects[0].id;
	var tabId="${param['tabId']}";
	openWindow('addFileWindow','上传自评报告',400,300,"${ctx}/evalua/performanceTarget/addAttachment.do?tabId="+tabId+"&projectPerformanceTarget.projectId="+id,true);
}

</script>