<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<!-- 专题专家与教师分配共用于同一个页面 -->
<form id="divideCourseForm" action="${ctx}/cms/info/chooseCourse.do" method="post">
<%-- <input type="hidden" name="paramMap[devideType]" value="${searchParam.paramMap.devideType }"> --%>
<%-- <input type="hidden" name="paramMap[teacherIds]" value="${searchParam.paramMap.teacherIds }"> --%>
	<div class="easyui-panel" title="" data-options="" style="width: 100%;">
		<div class="datagrid-toolbar" float:right>
			<table cellspacin="0" cellpadding="3px">
				<tr>
					<td style="width: 80px">课程选择：</td>
					<td><input name="paramMap[courseName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.courseName }"></td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.ajaxPostFormForPanel('divideCourseForm','divideCourseWindow')" iconCls="fa fa-search">查询</a>
							<a onclick="addTeacher()" href="javascript:void(0)" class="easyui-linkbutton">选择</a>
					</td>
				</tr>	
			</table>
		</div>
		<div>
			<%-- <table id="divideCourseTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" <c:if test="${not empty searchParam.paramMap.devideType}">singleSelect="true"</c:if>> --%>
			<table id="divideCourseTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true">
				<thead>
					<tr>
						<th width="30" data-options="field:'id',checkbox:true"></th>
						<th width="100" data-options="field:'courseName'">课程名称</th>
						<th width="100" data-options="field:'teacherName'">主讲教师</th>
						<th width="100" data-options="field:'courseIntro'">课程简介</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listCourses }" var="c">
						<tr>
							<td>${c.id }</td>
							<td>${c.courseName }</td>
							<td>${c.teacherName }</td>
							<td>${c.courseIntro }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<jsp:include page="/jsp/common/include/manage_page_table.jsp">
				<jsp:param value="divideCourseForm" name="pageForm" />
				<jsp:param value="divideCourseTable" name="tableId" />
				<jsp:param value="easyui" name="type" />
				<jsp:param value="divideCourseWindow" name="divId" />
			</jsp:include>
		</div>
	</div>
</form>
<script>

function addTeacher(){
	var teacher=$('#divideCourseTable').datagrid('getSelections');
	if(teacher.length==0){
		$.messager.alert('提示','请勾选所选课程！','warning');
	}else{
		for(var i=0;i<teacher.length;i++){
			$("#courseContent").html("<span id='course_id_"+teacher[i].id+"'><input checked='checked' type='checkbox' name='courseId' value='"+teacher[i].id+"'>"+teacher[i].courseName+"<button type='button' class='easyui-linkbutton delete-btn' onclick=\"$('#course_id_"+teacher[i].id+"').remove()\"> 删除</button> </span>");
			/* $("#teacherContent").append("<span id='teacher_id_"+teacher[i].teacherId+"'><input checked='checked' type='checkbox' name='teacherId' value='"+teacher[i].teacherId+"'>"+teacher[i].realName+"<button type='button' class='easyui-linkbutton delete-btn' onclick=\"$('#teacher_id_"+teacher[i].teacherId+"').remove()\"> 删除</button> </span>") */
		}
		$.parser.parse("#divideViewFrom");
		closeWindow("divideCourseWindow");
	}
}

function addExpert(){
	var teacher=$('#divideCourseTable').datagrid('getSelections');
	if(teacher.length==0){
		$.messager.alert('提示','请勾选所选课程！','warning');
	}else{
		for(var i=0;i<teacher.length;i++){
			$("#courseContent").html("<span id='teacher_id_"+teacher[i].teacherId+"'><input checked='checked' type='checkbox' name='teacherId' value='"+teacher[i].teacherId+"'>"+teacher[i].realName+"<button type='button' class='easyui-linkbutton delete-btn' onclick=\"$('#teacher_id_"+teacher[i].teacherId+"').remove()\"> 删除</button> </span>")
		}
		$.parser.parse("#divideViewFrom");
		closeWindow("divideCourseWindow");
	}
}
</script>