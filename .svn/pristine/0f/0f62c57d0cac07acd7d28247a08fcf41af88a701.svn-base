<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div style="margin: 5px;margin-bottom: 27px">
		<form id="listCourseForm" action="${ctx}/space/archive/noskin/listExpertCourse.do" method="post">
			<div class="easyui-panel" title="" data-options="" style="width: 100%;">
				<!-- <div class="datagrid-toolbar" float:right>
						<table cellspacing="0" cellpadding="0">
							<tbody>
								<tr>
									<td><a onclick="showDetail_course()" href="javascript:void(0)" class="easyui-linkbutton">查看详细bbb</a></td>
								</tr>
							</tbody>
						</table>
				</div> -->
				<div>
					<table id="listCourseTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true">
						<thead>
							<tr>
								<th width="30" data-options="field:'procInstId',hidden:true"></th>
								<th width="30" data-options="field:'id',checkbox:true"></th>
								<th width="100" data-options="field:'courseCode'">课程编号</th>
								<th width="100" data-options="field:'courseName'">课程名称</th>
								<th width="100" data-options="field:'courseType'">课程类别</th>
								<th width="100" data-options="field:'trainType'">培训形式</th>
								<th width="200" data-options="field:'allTrainName'">培训班</th>
								<th width="60" data-options="field:'handels'"></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listCourse }" var="course">
								<tr>
									<td>${course.procInstId }</td>
									<td>${course.id }</td>
									<td>${course.courseCode }</td>
									<td>${course.courseName }</td>
									<td>${dict:getEntryName('RKKCLB',course.courseType) }</td>
									<td>${dict:getEntryName('STUDY_TYPE',course.trainType)}</td>
									<td>${course.allTrainName }</td>
									<td><a href="javascript:void(0)" style="text-decoration: underline;" onclick="showDetail_course('${course.id }')">查看详细</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<jsp:include page="/jsp/common/include/manage_page_table.jsp">
						<jsp:param value="listCourseForm" name="pageForm" />
						<jsp:param value="listCourseTable" name="tableId" />
						<jsp:param value="easyui" name="type" />
						<jsp:param value="expertTab" name="divId" />
					</jsp:include>
				</div>
			</div>
		</form>
	</div>

<script>
/* function showDetail_course(){
	var objects = $('#listCourseTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('showDetailWindow','详细查看',0,600,'${ctx}/space/archive/noskin/courseTab.do?showType=expert&paramMap[courseId]='+id,true);
} */
function showDetail_course(id){
	openWindow('showDetailWindow','详细查看',0,600,'${ctx}/space/archive/noskin/courseTab.do?showType=expert&paramMap[courseId]='+id,true);
}
</script>