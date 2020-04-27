<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set var="isSystemAdmin" value="${searchParam.paramMap.isSystemAdmin}"/>
<form id="user_list_form" action="${ctx }/common/user/list.do?tabId=${param['tabId']}" method="post">
<div id="user_search">
	<div style="margin: 10px">    
		姓名:
		<input class="easyui-textbox" type="text" name="paramMap[userName]" value="${paramMap.userName}"></input>
		<c:if test="${isSystemAdmin}">
			区域:
			<select id="regionsCode" name="paramMap[regionsCode]" style="width:100px"></select>
			<script type="text/javascript">
				$(function(){
					getCurrentTab().find('#user_search').find('#regionsCode').combotree({    
					    data: jQuery.parseJSON('${ipanthercommon:getRegionsJson(true)}'),    
					    valueField:'id',    
					    textField:'text',
					    parentField:'pid',
					    panelWidth:200,
					    onLoadSuccess:function(){
					    	if('${searchParam.paramMap.regionsCode}'!=''){
					    		getCurrentTab().find('#user_search').find('#regionsCode').combotree('setValue','${searchParam.paramMap.regionsCode}');
							}
					    }
					}); 
				});
			</script>
			单位:
			<input class="easyui-textbox" type="text" name="paramMap[deptName]" value="${paramMap.deptName}"></input>
		</c:if>
		<button type="button" class="easyui-linkbutton main-btn" onclick="easyuiQuery('user_list_form',getCurrentTabId());"><i class="fa fa-search"></i> 查询</button>
   		<a href="#" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel(getCurrentTabId())" ><i class="fa fa-eraser"></i> 重置</a>
	</div>
	<div style="margin: 10px">
		<button id="user_addBtn" type="button" class="easyui-linkbutton"><span class="fa fa-plus"></span> 增加</button>
	  	<button id="user_editBtn" type="button" class="easyui-linkbutton"><span class="fa fa-pencil"></span> 修改</button>
		<button id="user_delBtn" type="button" class="easyui-linkbutton delete-btn"><span class="fa fa-minus"></span> 删除</button>
		<button id="user_resetPassword" type="button" class="easyui-linkbutton"><span class="fa fa-undo"></span> 密码重置</button>
		<button id="user_roleConfigBtn" type="button" class="easyui-linkbutton"><span class="fa fa-unlock-alt"></span> 角色授权</button>
		<!-- <button id="user_regionsConfigBtn" type="button" class="easyui-linkbutton"><span class="fa fa-unlock-alt"></span> 数据授权</button> -->
		<button id="user_lock" type="button" class="easyui-linkbutton"><span class="fa fa-lock"></span> 账号锁定</button>
		<button id="user_unlock" type="button" class="easyui-linkbutton"><span class="fa fa-unlock"></span> 账号解锁</button>
	</div>	
</div>
 <table id="user_list" title="用户列表"	
 	rownumbers="true" fitColumns="true"  pagination="true" toolbar="#user_search" 
    singleSelect="true" checkOnSelect="true" selectOnCheck="true">
			<thead>
				<tr>
                	<th width="30" data-options="field:'id',checkbox:true"></th>
					<th field="userName" width="25">用户名</th>
					<th field="roleCode" data-options="hidden:true">角色代码</th>
					<th field="paperworkNo" data-options="hidden:true">身份证号码</th>
					<th field="realName" width="25">姓名</th>
					<th field="sex" width="8" align="center">性别</th>
					<c:if test="${isSystemAdmin}">
						<th field="deptName" width="40">单位</th>
					</c:if>
					<th field="mobilePhone" width="25">联系电话</th>
					<th field="email" width="50">邮箱</th>
					<th field="status" width="50">状态</th>
					<th field="statusValue" data-options="hidden:true" width="50">statusValue</th>
				</tr>
			</thead>
			<tbody>   
       	<c:forEach items="${listUser }" var="list">
        <tr>   
           <td>${list.id }</td>
           <td>${list.userName }</td>
           <td>${list.roleCode }</td>
            <td>${list.paperworkNo }</td>
           <td>${list.realName }</td>
           <td>
           		${dict:getEntryName('SEX_TYPE',list.sex) }
           </td>
           <c:if test="${isSystemAdmin}">
           		<td>${list.deptName }</td>
           </c:if>
           <td>${list.mobilePhone }</td> 
            <td>${list.email }</td> 
            <td>
            	<c:set var="color" value="black"/>
            	<c:choose>
            		<c:when test="${list.status eq '-1'}">
            			<c:set var="color" value="red"/>
            		</c:when>
            		<c:when test="${list.status eq '2'}">
            			<c:set var="color" value="green"/>
            		</c:when>
            	</c:choose>
            	<span style="color: ${color}">${dict:getEntryName('USER_STATUS',list.status) }</span>
            </td> 
            <td>${list.status }</td>
        </tr>   
       </c:forEach>
    </tbody>   
</table> 
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
	<jsp:param name="pageForm" value="user_list_form" />
    <jsp:param name="tableId" value="user_list"/>
	<jsp:param name="type" value="easyui" />
	<jsp:param name="divId" value="${param['tabId']}" />
</jsp:include>
 </form>
<script type="text/javascript">
$(document).ready(function(e) {
	
	getCurrentTab().find('#user_addBtn').linkbutton({
		onClick:function(){
			easyuiUtils.openWindow('showAddDialog','新增用户',900,400,'${ctx }/common/user/edit.do',true);
		}
	}); 
	
	getCurrentTab().find('#user_editBtn').linkbutton({    
		onClick:function(){
			var userRow=getCurrentTab().find('#user_list').datagrid('getSelected');
			if(userRow==null){
				$.messager.alert('提示','请选择一行数据进行操作！','warning');
			}else{
				var regionsCode=$("#regionsCode").val();
				easyuiUtils.openWindow('showAddDialog','编辑用户',900,400,'${ctx }/common/user/edit.do?id='+userRow.id,true);
			}
		}
	}); 
	getCurrentTab().find('#user_delBtn').linkbutton({    
		onClick:function(){
			var userRow=getCurrentTab().find('#user_list').datagrid('getSelected');
			if(userRow==null){
				$.messager.alert('提示','请选择一行数据进行操作！','warning');
			}else{
				easyuiUtils.confirmPostUrl('是否确定删除‘'+userRow.userName+'’用户?','${ctx }/common/user/delete.do?user.id='+userRow.id);
			}
		}
	}); 
	getCurrentTab().find('#user_resetPassword').linkbutton({    
		onClick:function(){
			var userRow=getCurrentTab().find('#user_list').datagrid('getSelected');
			if(userRow==null){
				$.messager.alert('提示','请选择一行数据进行操作！','warning');
			}else{
				$.messager.confirm('提示', '是否重置用户‘'+userRow.userName+'’的密码?', function(r){
					if (r){
						var data=$.ajaxSubmitValue('${ctx }/common/user/resetPassword.do?user.id='+userRow.id+"&user.roleCode="+userRow.roleCode+"&user.paperworkNo="+userRow.paperworkNo);
						console.log(data);
						var json=jQuery.parseJSON(data);
						var message = json.responseMsg;
						$.messager.alert('提示',message,'info');
						/* $('#userPanel').panel('refresh',"${ctx }/common/user/list.do"); */
					}
				});
			}
		}
	});
	getCurrentTab().find('#user_roleConfigBtn').linkbutton({
		onClick: function(){
			var userRow=getCurrentTab().find('#user_list').datagrid('getSelected');
			if(userRow==null){
				jQuery.messager.alert('提示','请选择一行数据进行操作！','warning');
			}else{
				easyuiUtils.openWindowWithParams('editRoleDialog','授权角色',400,340,'${ctx}/common/user/roleConfig.do',true,{'user.id':userRow.id,'user.realName':userRow.realName});
			}
		}
	});
	getCurrentTab().find('#user_regionsConfigBtn').linkbutton({
		onClick: function(){
			var userRow=getCurrentTab().find('#user_list').datagrid('getSelected');
			if(userRow==null){
				jQuery.messager.alert('提示','请选择一行数据进行操作！','warning');
			}else{
				easyuiUtils.openWindow('editRegionsDialog','数据授权',420,420,'${ctx}/common/user/regionsConfig.do?user.id='+userRow.id+'&user.realName='+userRow.realName,true);
			}
		}
	});
	getCurrentTab().find('#user_unlock').linkbutton({
		onClick: function(){
			var userRow=getCurrentTab().find('#user_list').datagrid('getSelected');
			if(userRow==null){
				jQuery.messager.alert('提示','请选择一行数据进行操作！','warning');
			} else if(userRow.statusValue=='2') {
				jQuery.messager.alert('提示','用户目前不能进行该操作！','warning');
			} else if(userRow.statusValue=='1'){
				jQuery.messager.alert('提示','用户目前不处于锁定状态！','warning');
			} else{
				easyuiUtils.confirmPostUrl('是否将用户‘'+userRow.userName+'’解锁?','${ctx }/common/user/lock.do?user.id='+userRow.id+"&user.status=1");
			}
		}
	});
	getCurrentTab().find('#user_lock').linkbutton({
		onClick: function(){
			var userRow=getCurrentTab().find('#user_list').datagrid('getSelected');
			if(userRow==null){
				jQuery.messager.alert('提示','请选择一行数据进行操作！','warning');
			} else if(userRow.statusValue=='2') {
				jQuery.messager.alert('提示','用户目前不能进行该操作！','warning');
			} else if(userRow.statusValue=='-1'){
				jQuery.messager.alert('提示','用户目前已经处于锁定状态！','warning');
			} else{
				easyuiUtils.confirmPostUrl('是否将用户‘'+userRow.userName+'’锁定?','${ctx }/common/user/lock.do?user.id='+userRow.id+"&user.status=-1");
			}
		}
	});
});

function setRole(){
}

</script>