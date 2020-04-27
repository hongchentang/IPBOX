<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div style="margin: 5px;margin-bottom: 27px">
	<!-- <div class="easyui-panel" title="课程管理" style="width: 100%;" > -->
		<form id="listTodoForm" action="${ctx}/train/course/listTodo.do" method="post">
			<div class="easyui-panel" title="" data-options="" style="width: 100%;">
				<div class="datagrid-toolbar" float:right>
					<table cellspacin="0" cellpadding="3px">
						<tr>
							<td>课程名称</td>
							<td><input name="paramMap[courseName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.courseName }"></td>
							<td>
								<a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:easyuiQuery('listTodoForm','viewListTodo');" iconCls="fa fa-search">查询</a>
							</td>
						</tr>	
					</table>
						<table cellspacing="0" cellpadding="0">
							<tbody>
								<tr>
									<td><div class="datagrid-btn-separator"></div></td>
									<td><a onclick="showDetail_todo_c()" href="javascript:void(0)" class="easyui-linkbutton">查看详细</a>	</td>
									<td><div class="datagrid-btn-separator"></div></td>
									<td><a onclick="editCourse()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-pencil"> 修改</i></a></td>
									<td><div class="datagrid-btn-separator"></div></td>
									<td><a onclick="deleteCourse()" href="javascript:void(0)" class="easyui-linkbutton delete-btn"><i class="fa fa-minus"> 删除</i></a></td>
									<td><div class="datagrid-btn-separator"></div></td>
									<td><a onclick="audit()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-pencil">办理</i></a>	</td>
									<td><div class="datagrid-btn-separator"></div></td>
									<td><a onclick="declareCourse()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-pencil">申报</i></a></td>	
								</tr>
							</tbody>
						</table>
				</div>
				<div>
					<table id="listTodoTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true">
						<thead>
							<tr>
								<th width="30" data-options="field:'procInstId',hidden:true"></th>
								<th width="30" data-options="field:'id',checkbox:true"></th>
								<th width="100" data-options="field:'courseCode'">课程编号</th>
								<th width="100" data-options="field:'courseName'">课程名称</th>
								<th width="100" data-options="field:'startTime'">上线日期</th>
								<th width="100" data-options="field:'endTime'">下线日期</th>
								<!-- <th width="100" data-options="field:'studyHours'">学时数</th>
								<th width="100" data-options="field:'hoursType'">学时类型</th> -->
								<th width="100" data-options="field:'courseType'">课程类别</th>
								<th width="100" data-options="field:'trainType'">培训形式</th>
								<!-- <th width="100" data-options="field:'teacherName'">主讲教师姓名</th> -->
								<th width="100" data-options="field:'status'">状态</th>
								<th width="100" data-options="field:'applyCount'">已申请次数</th>
								<th width="100" data-options="field:'maxApplyCount'">最大申请次数</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listCourse }" var="course">
								<tr>
									<td>${course.procInstId }</td>
									<td>${course.id }</td>
									<td>${course.courseCode }</td>
									<td>${course.courseName }</td>
									<td><fmt:formatDate value="${course.startTime }" type="date" pattern="yyyy-MM-dd"/></td>
									<td> <fmt:formatDate value="${course.endTime }" type="date" pattern="yyyy-MM-dd"/></td>
									<%-- <td>${course.studyHours }</td>
									<td>${course.hoursType }</td> --%>
									<td>${dict:getEntryName('RKKCLB',course.courseType) }</td>
									<td>${dict:getEntryName('STUDY_TYPE',course.trainType)}</td>
									<%-- <td>${course.teacherName }</td> --%>
									<td>${dict:getEntryName('COURSE_STATUS',course.status) }</td>
									<td>${course.applyCount }</td>
									<td>${course.maxApplyCount }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<jsp:include page="/jsp/common/include/manage_page_table.jsp">
						<jsp:param value="listTodoForm" name="pageForm" />
						<jsp:param value="listTodoTable" name="tableId" />
						<jsp:param value="easyui" name="type" />
						<jsp:param value="viewListTodo" name="divId" />
					</jsp:include>
				</div>
			</div>
		</form>
	</div>

<script>
function editCourse(){
	var objects = $('#listTodoTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects[0].status!='已退回'){
		$.messager.alert('消息','审核中或审核结束的课程不能进行修改、删除操作');
		return false;
	};
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('addCourseWindow','编辑课程',0,600,'${ctx}/train/course/goAddCourse.do?id='+id+'&paramMap[rePanel]=viewListTodo',true);
}


function deleteCourse(){
	var objects = $('#listTodoTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择要删除的数据');
		return false;
	}
	if(objects[0].status!='已退回'){
		$.messager.alert('消息','该列表只能删除状态为已退回的课程!');
		return false;
	};
	var id = objects[0].id;
	var procInstId=objects[0].procInstId;
	$.messager.confirm('确认','确定想要删除选中的记录吗？', function(flag){
		if(flag){
			$.ajax({
				url:'${ctx}/train/course/deleteCourse.do?id='+id+"&procInstId="+procInstId,
				type:'post',
				/* //data:$('#listJSJBDYAllForm').serialize(), */
				success:function(data){
					$.messager.alert('提示',data.message);
					$("#viewListTodo").panel('refresh');
				}
			}
				);
		}
	});
}

function declareCourse(){
	var objects = $('#listTodoTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择要删除的数据');
		return false;
	}
	if(objects[0].status!='已退回'){
		$.messager.alert('消息','审核中或审核结束的课程不能进行申报操作！');
		return false;
	};
	var id = objects[0].id;
	var procInstId=objects[0].procInstId;
	$.messager.confirm('确认','是否确定申报"'+name+'"课程？', function(flag){
		if(flag){
			 $.ajax({
				url:'${ctx}/train/course/declareCourse.do?id='+id+"&procInstId="+procInstId,
				type:'post',
				 //data:$('#listJSJBDYAllForm').serialize(), 
				success:function(){
					$.messager.alert('提示','操作成功！');
					$("#viewListTodo").panel('refresh');
				}
			}); 
		}
	});
} 
function audit(){
	var objects = $('#listTodoTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects[0].status!='待审核'){
		$.messager.alert('消息','该课程还不能办理！');
		return false;
	};
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('auditCourseWindow','课程审核',0,600,'${ctx}/train/course/goAudit.do?id='+id,true);
}
function showDetail_todo_c(){
	var objects = $('#listTodoTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('showDetailWindow','详细查看',0,600,'${ctx}/train/course/showDetail.do?id='+id,true);
}
</script>