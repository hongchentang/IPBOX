<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div style="margin: 5px;margin-bottom: 27px">
		<form id="listSpForm" action="${ctx}/application/usePatent/listSp.do" method="post">
			<div class="easyui-panel" title="" data-options="" style="width: 100%;">
				<div class="datagrid-toolbar" float:right>
					<table cellspacin="0" cellpadding="3px">
						<tr>
							<td>专利名称</td>
							<td><input name="paramMap[patentName]" class="easyui-validatebox" style="width:100px" value="${searchParam.paramMap.patentName}"></td>
							<td>
								<a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.ajaxPostFormForPanel('listSpForm','viewUsePatentListSp')" iconCls="fa fa-search">查询</a>
							</td>
						</tr>	
					</table>
					<table cellspacing="0" cellpadding="0">
						<tbody>
							<tr>
								<td><a onclick="addUsePatentSp()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-plus"> 新增</i></a></td>
								<td><div class="datagrid-btn-separator"></div></td>								
								<td><a onclick="delUsePatentSp()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-minus"> 删除 </i></a>	</td>
								<td><div class="datagrid-btn-separator"></div></td>
								<td><a onclick="editUsePatentSp()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-pencil"> 修改</i></a></td>
								<td><div class="datagrid-btn-separator"></div></td>						
								<td><a onclick="showDetailSp()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-eye"> 查看 </i></a>	</td>
							</tr>
						</tbody>
					</table>
				</div>
				<div>
					<table id="listSpTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="true">
						<thead>
							<tr>
								<th width="30" data-options="field:'id',checkbox:true"></th>
								<th width="150" data-options="field:'patentPublicNumber'">专利申请号</th>
								<th width="250" data-options="field:'patentName'">专利名称</th>
								<th width="100" data-options="field:'spShareType'">入股类别</th>
								<th width="250" data-options="field:'spShareName'">入股企业</th>
								<th width="10%" data-options="field:'spShareScale'">入股比例</th>
								<th width="10%" data-options="field:'spShareDate'">入股日期</th>
								<th width="10%" data-options="field:'spShareMoney'">入股金额</th>
								<th width="10%" data-options="field:'spLinkMan'">入股联系人</th>
								<th width="10%" data-options="field:'createTime'">创建时间</th>								
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${listUsePatent}" var="usePatent">
								<tr>
									<td>${usePatent.id }</td>
									<td>${usePatent.patentPublicNumber }</td>
									<td>${usePatent.patentName }</td>
									<td>${usePatent.spShareType }</td>
									<td>${usePatent.spShareName }</td>
									<td>${usePatent.spShareScale }</td>
									<td><fmt:formatDate value="${usePatent.spShareDate }"  pattern="yyyy-MM-dd"/></td>
									<td>${usePatent.spShareMoney }</td>
									<td>${usePatent.spLinkMan }</td>
									<td><fmt:formatDate value="${usePatent.createTime }"  pattern="yyyy-MM-dd"/></td>			
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<jsp:include page="/jsp/common/include/manage_page_table.jsp">
						<jsp:param value="listSpForm" name="pageForm" />
						<jsp:param value="listSpTable" name="tableId" />
						<jsp:param value="easyui" name="type" />
						<jsp:param value="viewUsePatentListSp" name="divId" />
					</jsp:include>
				</div>
			</div>
		</form>
	</div>

<script>

function showDetailSp(){
	var objects = $('#listSpTable').datagrid('getSelections');
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

function addUsePatentSp(){
	openWindow('addWindow','新增专利入股',1200,600,'${ctx}/application/usePatent/goAddUsePatent.do?useType=4',true);
}

function editUsePatentSp(){
	var objects = $('#listSpTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时查看多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('addWindow','修改入股内容',1200,600,'${ctx}/application/usePatent/goAddUsePatent.do?useType=4&id='+id,true); 
}


function delUsePatentSp(){
	var objects = $('#listSpTable').datagrid('getSelections');
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
						jQuery("#viewUsePatentListSp").panel('refresh');
					} else {
						jQuery.messager.alert("提示信息","删除失败","error");
					}
				}
			});
		}
	});
}
</script>