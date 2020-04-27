<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div style="margin: 0px;margin-bottom: 27px">
		<form id="listPlepForm" action="${ctx}/application/usePatent/listPlep.do" method="post">
			<div class="easyui-panel" title="" data-options="" style="width: 100%;">
				<div class="datagrid-toolbar" float:right>
					<table cellspacin="0" cellpadding="3px">
						<tr>
							<td>专利名称</td>
							<td><input name="paramMap[patentName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.patentName}"></td>
							<td>
								<a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.ajaxPostFormForPanel('listPlepForm','viewUsePatentListPlep')" iconCls="fa fa-search">查询</a>
							</td>
						</tr>	
					</table>
					<table cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td><a onclick="addUsePatentPlep()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-plus"> 新增</i></a></td>
								<td><div class="datagrid-btn-separator"></div></td>								
								<td><a onclick="delUsePatentPlep()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-minus"> 删除 </i></a>	</td>
								<td><div class="datagrid-btn-separator"></div></td>
								<td><a onclick="editUsePatentPlep()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-pencil"> 修改</i></a></td>
								<td><div class="datagrid-btn-separator"></div></td>						
								<td><a onclick="showDetailPlep()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-eye"> 查看 </i></a>	</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div>
					<table id="listPlepTable" rownumbers="true" striped="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true">
						<thead>
							<tr>
								<th width="30" data-options="field:'id',checkbox:true"></th>
								<th width="120" data-options="field:'patentPublicNumber'">专利申请号</th>
								<th width="250" data-options="field:'patentName'">专利名称</th>
								<th width="100" data-options="field:'plepStuta'">转让类型</th>
								<th width="15%" data-options="field:'plepMortgager'">抵押人</th>
								<th width="15%" data-options="field:'plepPledgor'">质押人</th>
								<th width="10%" data-options="field:'plepCreateDate'">质押开始日期</th>
								<th width="10%" data-options="field:'plepEndDate'">质押结束日期</th>
								<th width="10%" data-options="field:'plepDebtNumber'">质押金额</th>
								<th width="10%" data-options="field:'createTime'">创建时间</th>	
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listUsePatent}" var="usePatent">
								<tr>
									<td>${usePatent.id }</td>
									<td>${usePatent.patentPublicNumber }</td>
									<td>${usePatent.patentName }</td>
									<td>${usePatent.plepStuta }</td>
									<td>${usePatent.plepMortgager }</td>
									<td>${usePatent.plepPledgor }</td>
									<td><fmt:formatDate value="${usePatent.plepCreateDate }"  pattern="yyyy-MM-dd"/></td>
									<td><fmt:formatDate value="${usePatent.plepEndDate }"  pattern="yyyy-MM-dd"/></td>
									<td>${usePatent.plepDebtNumber }</td>
									<td><fmt:formatDate value="${usePatent.createTime}"  pattern="yyyy-MM-dd"/></td>				
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<jsp:include page="/jsp/common/include/manage_page_table.jsp">
						<jsp:param value="listPlepForm" name="pageForm" />
						<jsp:param value="listPlepTable" name="tableId" />
						<jsp:param value="easyui" name="type" />
						<jsp:param value="viewUsePatentListPlep" name="divId" />
					</jsp:include>
				</div>
			</div>
		</form>
	</div>

<script>
function showDetailPlep(){
	var objects = $('#listPlepTable').datagrid('getSelections');
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

function addUsePatentPlep(){
	openWindow('addWindow','新增专利质押',1200,600,'${ctx}/application/usePatent/goAddUsePatent.do?useType=2',true);
}

function editUsePatentPlep(){
	var objects = $('#listPlepTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时查看多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('addWindow','修改质押内容',1200,600,'${ctx}/application/usePatent/goAddUsePatent.do?useType=2&id='+id,true); 
}


function delUsePatentPlep(){
	var objects = $('#listPlepTable').datagrid('getSelections');
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
						jQuery("#viewUsePatentListPlep").panel('refresh');
					} else {
						jQuery.messager.alert("提示信息","删除失败","error");
					}
				}
			});
		}
	});
}

</script>