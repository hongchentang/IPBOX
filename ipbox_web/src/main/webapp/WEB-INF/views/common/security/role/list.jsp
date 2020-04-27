<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>

 <form id="searchRoleForm" action="${ctx}/common/security/role/list.do?tabId=${param.tabId}" method="post"> 
    <div id="listRoleTableSearch" style="margin: 5px">
    	<div style="margin: 3px">
			     角色名称:<input name="paramMap[name]" title="输入查询姓名" class="easyui-textbox" value="${searchParam.paramMap.name}"/>  &nbsp;&nbsp;
			   <a href="#" class="easyui-linkbutton main-btn" onclick="easyuiUtils.ajaxPostFormForPanel(getCurrentTabComId('searchRoleForm'),getCurrentTabId())" ><span class="fa fa-search"></span>查询</a>
			   <a href="#" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel(getCurrentTabId())"><span class="fa fa-eraser"></span>重置</a>
        </div>
        <div style="margin: 4px">
                
                <a href="javascript:void(0)" onclick="role_addBut()" class="easyui-linkbutton" ><span class="fa fa-plus"></span> 新增角色 </a>
                <a href="javascript:void(0)" onclick="role_viewBut()" class="easyui-linkbutton" title="查看角色"><span class="fa fa-search"></span> 查看</a>
				<a href="javascript:void(0)" onclick="role_editBut()" class="easyui-linkbutton"  title="编辑角色"><span class="fa fa-pencil"></span> 编辑</a>
				<a href="javascript:void(0)" onclick="role_delBut()" class="easyui-linkbutton delete-btn" title="删除角色"><span class="fa fa-times"></span> 删除</a>
				<a href="javascript:void(0)" onclick="role_config()" class="easyui-linkbutton" title="权限配置">
						<span class="fa fa-cogs"></span>
						权限配置
				</a>
        </div>
	</div>
	<table id="roleListTable" style="width:98%;height:auto; min-height:400px"
            	toolbar="#${param['tabId']} #listRoleTableSearch"
            	title="角色管理"
            	rownumbers="true" pagination="true" singleSelect="true" checkOnSelect="true" selectOnCheck="true" nowrap="false" fitColumns="true" > 
	  <thead>
						<tr>
							<th width="30" data-options="field:'id',checkbox:true"></th>
							<th data-options="field:'name',width:'15%'"  >角色名</th>   
            				<th data-options="field:'description',width:'50%'">描述</th>   
            				<th data-options="field:'roleCode',width:'10%'" >角色标示</th>  
						</tr>
					</thead>
					<tbody>
					<c:if test="${not empty list}">
					<c:forEach items="${list }" var="list">
								<tr>
									<td>${list.id }</td>
									<td>${list.name }</td>
									<td>${list.description }</td>
									<td>${list.roleCode}</td>
								</tr>
					</c:forEach>
					</c:if>
					</tbody>
    		</table>
    		<jsp:include page="/jsp/common/include/manage_page_table.jsp">
               <jsp:param name="pageForm" value="searchRoleForm" />
               <jsp:param name="tableId" value="roleListTable" />
               <jsp:param name="type" value="easyui" />
               <jsp:param name="divId" value="${param['tabId']}" />
			</jsp:include>
	</form>
<script type="text/javascript">
	function delRole(url,message){
		jQuery.messager.confirm("提示信息","确定删除角色 "+message+" ?",function(isReturn){
			if(isReturn){
				var data=jQuery.ajaxSubmitValue(url);
				var json=jQuery.parseJSON(data);
				if(!jQuery.isEmptyObject(json)){
					var message = json.message;
					var statusCode = json.statusCode;
					if(parseInt(statusCode)==300){
						jQuery.messager.alert("提示信息",message,function(){});
					}else if(parseInt(statusCode)==200){
						jQuery.messager.alert("提示信息",message,function(){});
						jQuery('#'+getCurrentTabId()).panel('refresh');
					}
				}else{
					console.error("json is null");
				}
			}
		});
	}
	
	function role_viewBut(){
		var selectRow=getCurrentTab().find('#roleListTable').datagrid('getSelected');
		if(selectRow==null){
			$.messager.alert('提示','请选择要查看的角色！');
		}else{
			easyuiUtils.openWindow('showDialog','角色查看',400,250,'${ctx}/common/security/role/view.do?role.id='+selectRow.id,true)
		}
	}
	function role_addBut(){
		easyuiUtils.openWindow('showAddDialog','角色新增',400,250,'${ctx}/common/security/role/add.do',true)
	}
	function role_editBut(){
		var selectRow=getCurrentTab().find('#roleListTable').datagrid('getSelected');
		if(selectRow==null){
			$.messager.alert('提示','请选择要修改的角色！');
		}else{
			easyuiUtils.openWindow('showEditDialog','角色编辑',400,250,'${ctx}/common/security/role/edit.do?role.id='+selectRow.id,true)
		}
	}
	function role_delBut(){
		var selectRow=getCurrentTab().find('#roleListTable').datagrid('getSelected');
		if(selectRow==null){
			$.messager.alert('提示','请选择要删除的角色！');
		}else{
			delRole('${ctx}/common/security/role/delete.do?role.id='+selectRow.id,selectRow.name);
		}
	}
	function role_config(){
		var selectRow=getCurrentTab().find('#roleListTable').datagrid('getSelected');
		if(selectRow==null){
			$.messager.alert('提示','请选择要配置权限的角色！');
		}else{
			easyuiUtils.openWindow('rolePriEditDialog','权限配置',500,426,'${ctx}/common/security/rolePri/rolePriEdit.do?role.id='+selectRow.id,true);
		}
	}
</script>