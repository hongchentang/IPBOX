<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<form id="selectCourseForm" action="${ctx}/train/train/selectCourse.do?paramMap[paramCourseIds]=${searchParam.paramMap.paramCourseIds }" method="post">
<input type="hidden" name="paramMap[projectId]" value="${searchParam.paramMap.projectId }">
	<div class="easyui-panel" title="" data-options="" style="width: 100%;">
		<div class="datagrid-toolbar" float:right>
			<table cellspacin="0" cellpadding="3px">
				<tr>
					<td style="width: 80px">课程选择：</td>
					<td><input name="paramMap[courseName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.courseName }"></td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.ajaxPostFormForPanel('selectCourseForm','selectCourseWindow')" iconCls="fa fa-search">查询</a>
						<a onclick="addCourses()" href="javascript:void(0)" class="easyui-linkbutton">选择</a>
					</td>
				</tr>	
			</table>
		</div>
		<div>
			<table id="selectCourseTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="false">
				<thead>
					<tr>
						<th width="30" data-options="field:'id',checkbox:true"></th>
						<th width="100" data-options="field:'courseCode'">课程编号</th>
						<th width="100" data-options="field:'courseName'">课程名称</th>
						<th width="100" data-options="field:'studyHours'">学时数</th>
						<th width="100" data-options="field:'hoursType'">学时类型</th>
						<th width="100" data-options="field:'courseType'">课程类别</th>
						<th width="100" data-options="field:'trainType'">培训形式</th>
						<!-- <th width="100" data-options="field:'teacherName'">主讲教师姓名</th> -->
						
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listCourse }" var="course">
						<tr>
							<td>${course.id }</td>
							<td>${course.courseCode }</td>
							<td>${course.courseName }</td>
							<td>${course.studyHours }</td>
							<td>${course.hoursType }</td>
							<td>${course.courseType }</td>
							<td>${course.trainType }</td>
							<%-- <td>${course.teacherName }</td> --%>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<jsp:include page="/jsp/common/include/manage_page_table.jsp">
				<jsp:param value="selectCourseForm" name="pageForm" />
				<jsp:param value="selectCourseTable" name="tableId" />
				<jsp:param value="easyui" name="type" />
				<jsp:param value="selectCourseWindow" name="divId" />
			</jsp:include>
		</div>
	</div>
</form>
<script>

function addCourses(){
	var course=$('#selectCourseTable').datagrid('getSelections');
	if(course.length==0){
		$.messager.alert('提示','请勾选课程！','warning');
	}else{
		for(var i=0;i<course.length;i++){
			$("#courseContent").append("<span id='course_id_"+course[i].id+"'><input checked='checked' type='checkbox' name='courseId' value='"+course[i].id+"'>"+course[i].courseName+"<button type='button' class='easyui-linkbutton delete-btn' onclick=\"$('#course_id_"+course[i].id+"').remove()\"> 删除</button> </span>")
		}
		$.parser.parse("#train_saveFrom");
		closeWindow("selectCourseWindow");
	}
}

</script>