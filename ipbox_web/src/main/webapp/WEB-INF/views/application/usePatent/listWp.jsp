<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div style="margin: 5px;margin-bottom: 27px">
		<form id="listWpForm" action="${ctx}/application/usePatent/listWp.do" method="post">
			<div class="easyui-panel" title="" data-options="" style="width: 100%;">
				<div class="datagrid-toolbar" float:right>
					<table cellspacin="0" cellpadding="3px">
						<tr>
							<td>专利名称</td>
							<td><input name="paramMap[patentName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.patentName}"></td>
							<td>
								<a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.ajaxPostFormForPanel('listWpForm','viewUsePatentListWp')" iconCls="fa fa-search">查询</a>
							</td>
						</tr>	
					</table>
					<table cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td><a onclick="addUsePatentWp()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-plus"> 新增</i></a></td>
								<td><div class="datagrid-btn-separator"></div></td>								
								<td><a onclick="delUsePatentWp()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-minus"> 删除 </i></a>	</td>
								<td><div class="datagrid-btn-separator"></div></td>
								<td><a onclick="editUsePatentWp()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-pencil"> 修改</i></a></td>
								<td><div class="datagrid-btn-separator"></div></td>						
								<td><a onclick="showDetailWp()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-eye"> 查看 </i></a>	</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div>
					<table id="listWpTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true">
						<thead>
							<tr>
								<th width="30" data-options="field:'id',checkbox:true"></th>
								<th width="150" data-options="field:'patentPublicNumber'">专利申请号</th>
								<th width="250" data-options="field:'patentName'">专利名称</th>
								<th width="100" data-options="field:'wpType'">许可类型</th>
								<th width="200" data-options="field:'wpTechnologyName'">许可项目名称</th>
								<th width="10%" data-options="field:'wpFromName'">专利许可人</th>
								<th width="10%" data-options="field:'wpToName'">被许可人</th>
								<th width="100" data-options="field:'wpStartDate'">许可开始日期</th>
								<th width="100" data-options="field:'wpEndDate'">许可结束日期</th>
								<th width="10%" data-options="field:'wpField'">许可范围</th>
								<th width="100" data-options="field:'apIncome'">金额</th>	
								<th width="100" data-options="field:'createTime'">创建日期</th>									
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listUsePatent}" var="usePatent">
								<tr>
									<td>${usePatent.id }</td>
									<td>${usePatent.patentPublicNumber }</td>
									<td>${usePatent.patentName }</td>
									<td>${usePatent.wpType }</td>
									<td>${usePatent.wpTechnologyName }</td>
									<td>${usePatent.wpFromName }</td>
									<td>${usePatent.wpToName }</td>
									<td><fmt:formatDate value="${usePatent.wpStartDate }"  pattern="yyyy-MM-dd"/></td>
									<td><fmt:formatDate value="${usePatent.wpEndDate }"  pattern="yyyy-MM-dd"/></td>
									<td>${usePatent.wpField }</td>
									<td>${usePatent.apIncome }</td>
									<td><fmt:formatDate value="${usePatent.createTime }"  pattern="yyyy-MM-dd"/></td>					
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<jsp:include page="/jsp/common/include/manage_page_table.jsp">
						<jsp:param value="listWpForm" name="pageForm" />
						<jsp:param value="listWpTable" name="tableId" />
						<jsp:param value="easyui" name="type" />
						<jsp:param value="viewUsePatentListWp" name="divId" />
					</jsp:include>
				</div>
			</div>
		</form>
	</div>

<script>

function showDetailWp(){
	var objects = $('#listWpTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时查看多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('addWindow','查看详细',0,600,'${ctx}/application/usePatent/showDetail.do?id='+id,true); 
}

function addUsePatentWp(){
	openWindow('addWindow','新增专利许可',1200,600,'${ctx}/application/usePatent/goAddUsePatent.do?useType=3',true);
}

function editUsePatentWp(){
	var objects = $('#listWpTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时查看多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('addWindow','修改许可内容',1200,600,'${ctx}/application/usePatent/goAddUsePatent.do?useType=3&id='+id,true); 
}

function delUsePatentWp(){
	var objects = $('#listWpTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时删除多条数据');
		return false;
	}
	var id = objects[0].id;
	$.messager.confirm('确认','确定想要删除此内容？', function(flag){
		if(flag){
			$.ajax({
				url:'${ctx}/application/usePatent/deleteUsePatent.do?id='+id,
				type:'post',
				success:function(data){
					var responseCode = data.statusCode;
					if (parseInt(responseCode) == 200) {
						jQuery.messager.alert("提示信息","删除成功","info");
						jQuery("#viewUsePatentListWp").panel('refresh');
					} else {
						jQuery.messager.alert("提示信息","删除失败","error");
					}
				}
			});
		}
	});
}
</script>