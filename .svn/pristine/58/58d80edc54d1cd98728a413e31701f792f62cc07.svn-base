<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div style="margin: 0px;margin-bottom: 27px">
		<form id="listApForm" action="${ctx}/application/usePatent/listAp.do" method="post">
			<div class="easyui-panel" title="" data-options="" style="width: 100%;">
				<div class="datagrid-toolbar" float:right>
					<table cellspacin="0" cellpadding="3px">
						<tr>
							<td>专利名称</td>
							<td><input name="paramMap[patentName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.patentName}"></td>
							<td>
								<a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.ajaxPostFormForPanel('listApForm','viewUsePatentListAp')" iconCls="fa fa-search">查询</a>
							</td>
						</tr>	
					</table>
					<table cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td><a onclick="addUsePatentAP()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-plus"> 新增</i></a></td>
								<td><div class="datagrid-btn-separator"></div></td>								
								<td><a onclick="delUsePatentAP()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-minus"> 删除 </i></a>	</td>
								<td><div class="datagrid-btn-separator"></div></td>
								<td><a onclick="editUsePatentAP()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-pencil"> 修改</i></a></td>
								<td><div class="datagrid-btn-separator"></div></td>						
								<td><a onclick="showDetailAP()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-eye"> 查看 </i></a>	</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div>
					<table id="listApTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true" >
						<thead>
							<tr>
								<th width="30px" data-options="field:'id',checkbox:true"></th>
								<th width="120px" data-options="field:'patentPublicNumber'">专利申请号</th>
								<th width="150px" data-options="field:'patentName'">专利名称</th>
								<th width="80px" data-options="field:'apType'">转让类型</th>
								<th width="80px" data-options="field:'apDate'">转让时间</th>
								<th width="10%" data-options="field:'apFromName'">转让方代表</th>
								<th width="10%" data-options="field:'apFromAddress'">转让方地址</th>
								<th width="10%" data-options="field:'apFromMan'">转让方</th>
								<th width="10%" data-options="field:'apToName'">受让方代表</th>
								<th width="10%" data-options="field:'apToMan'">受让方</th>
								<th width="10%" data-options="field:'apToAddress'">受让人地址</th>
								<th width="10%" data-options="field:'apIncome'">金额</th>	
								<th width="10%" data-options="field:'createTime'">创建时间</th>	
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listUsePatent}" var="usePatent">
								<tr>
									<td>${usePatent.id }</td>
									<td>${usePatent.patentPublicNumber }</td>
									<td>${usePatent.patentName }</td>
									<td>${dict:getEntryName('IPR_PATENT_TYPE',usePatent.apType)}</td>
									<td><fmt:formatDate value="${usePatent.apDate }"  pattern="yyyy-MM-dd"/></td>
									<td>${usePatent.apFromName }</td>
									<td>${usePatent.apFromAddress }</td>
									<td>${usePatent.apFromMan }</td>
									<td>${usePatent.apToName }</td>
									<td>${usePatent.apToMan }</td>
									<td>${usePatent.apToAddress }</td>
									<td>${usePatent.apIncome }</td>
									<td><fmt:formatDate value="${usePatent.createTime}" pattern="yyyy-MM-dd"/></td> 					
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<jsp:include page="/jsp/common/include/manage_page_table.jsp">
						<jsp:param value="listApForm" name="pageForm" />
						<jsp:param value="listApTable" name="tableId" />
						<jsp:param value="easyui" name="type" />
						<jsp:param value="viewUsePatentListAp" name="divId" />
					</jsp:include>
				</div>
			</div>
		</form>
	</div>

<script>


function addUsePatentAP(){
	openWindow('addWindow','新增专利转让',1200,600,'${ctx}/application/usePatent/goAddUsePatent.do?useType=1',true);
}

function editUsePatentAP(){
	var objects = $('#listApTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时查看多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('addWindow','修改转让内容',1200,600,'${ctx}/application/usePatent/goAddUsePatent.do?useType=1&id='+id,true); 
}


function delUsePatentAP(){
	var objects = $('#listApTable').datagrid('getSelections');
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
						jQuery("#viewUsePatentListAp").panel('refresh');
					} else {
						jQuery.messager.alert("提示信息","删除失败","error");
					}
				}
			});
		}
	});
}

function showDetailAP(){
	var objects = $('#listApTable').datagrid('getSelections');
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
</script>