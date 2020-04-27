<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div style="margin: 5px;margin-bottom: 27px">
	<!-- <div class="easyui-panel" title="课程管理" style="width: 100%;" > -->
		<form id="listDoneForm" action="${ctx}/train/course/listDone.do" method="post">
			<div class="easyui-panel" title="" data-options="" style="width: 100%;">
				<div class="datagrid-toolbar" float:right>
					<table cellspacin="0" cellpadding="3px">
						<tr>
							<td>课程名称</td>
							<td><input name="paramMap[courseName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.courseName }"></td>
							<td>
								<a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.ajaxPostFormForPanel('listDoneForm','viewListDone')" iconCls="fa fa-search">查询</a>
							</td>
						</tr>	
					</table>
					<table cellspacing="0" cellpadding="0">
							<tbody>
								<tr>
									<td><a onclick="showDetail_done_c()" href="javascript:void(0)" class="easyui-linkbutton">查看详细</a>	</td>
									<td><a onclick="deleteCourse()" href="javascript:void(0)" class="easyui-linkbutton delete-btn"><i class="fa fa-minus"> 删除</i></a></td>
								</tr>
							</tbody>
						</table>
				</div>
				<div>
					<table id="listDoneTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true">
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
									<%-- <td>
										<c:forTokens items="${jsjbdy.wxyj }" delims="," var="item">
											${dict:getEntryName('WXYJ',item) }
										</c:forTokens>
									</td> --%>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<jsp:include page="/jsp/common/include/manage_page_table.jsp">
						<jsp:param value="listDoneForm" name="pageForm" />
						<jsp:param value="listDoneTable" name="tableId" />
						<jsp:param value="easyui" name="type" />
						<jsp:param value="viewListDone" name="divId" />
					</jsp:include>
				</div>
			</div>
		</form>
	</div>

<script>
function showDetail_done_c(){
	var objects = $('#listDoneTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时查看多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('showDetailWindow','课程详细',0,600,'${ctx}/train/course/showDetail.do?id='+id,true);
}
function deleteCourse(){
	var objects = $('#listDoneTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择要删除的数据');
		return false;
	}
	/* if(objects[0].status!='已退回'){
		$.messager.alert('消息','审核中或审核结束的课程不能进行修改、删除操作');
		return false;
	}; */
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
					$("#viewListDone").panel('refresh');
				}
			});
		}
	});
}
</script>