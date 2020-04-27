<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<c:set var="isSystemAdmin" value="${searchParam.paramMap.isSystemAdmin}"/>
<form id="user_list_form" action="${ctx }/common/user/listEmail.do?tabId=${param['tabId']}" method="post">
<div id="customeruser_searchopp">
	<div style="margin: 10px">    

	</div>
	<div style="margin: 10px">
		<button id="user_addBtn" type="button" class="easyui-linkbutton"><span class="fa fa-plus"></span> 增加</button>
	  	<button id="user_editBtn" type="button" class="easyui-linkbutton"><span class="fa fa-pencil"></span> 修改</button>
		<button id="user_delBtn" type="button" class="easyui-linkbutton delete-btn"><span class="fa fa-minus"></span> 删除</button>
<!-- 		<button id="user_resetPassword" type="button" class="easyui-linkbutton"><span class="fa fa-undo"></span> 密码重置</button>
		<button id="user_roleConfigBtn" type="button" class="easyui-linkbutton"><span class="fa fa-unlock-alt"></span> 角色授权</button>
		<button id="user_regionsConfigBtn" type="button" class="easyui-linkbutton"><span class="fa fa-unlock-alt"></span> 数据授权</button>
		<button id="user_lock" type="button" class="easyui-linkbutton"><span class="fa fa-lock"></span> 账号锁定</button>
		<button id="user_unlock" type="button" class="easyui-linkbutton"><span class="fa fa-unlock"></span> 账号解锁</button> -->
	</div>	
</div>
 <table id="user_list" title="邮箱管理"	
 	rownumbers="true" fitColumns="true"  pagination="true" toolbar="#customeruser_search" 
    singleSelect="true" checkOnSelect="true" selectOnCheck="true">
			<thead>
				<tr>
                	<th width="30" data-options="field:'id',checkbox:true"></th>
                	<th field="email" width="25">邮箱</th>
					<th field="userName" width="25">持有者姓名</th>

				</tr>
			</thead>
			<tbody>   
       	<c:forEach items="${listUser }" var="list">
        <tr>   
           <td>${list.id }</td>
             <td>${list.userEmail }</td>
           <td>${list.name}</td>

     <%--        <td>
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
            </td>  --%>
           <%--  <td>${list.status }</td> --%>
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
			var size="${fn:length(listUser)}";
			if(size<3){
				easyuiUtils.openWindow('showAddDialog','新增邮箱',900,400,'${ctx }/common/user/addUserEmail.do',true);
			}else{
				$.messager.alert('提示','添加邮箱不能超过3个！');
			}
			
          
		}
	}); 
	
	getCurrentTab().find('#user_editBtn').linkbutton({    
		onClick:function(){
			var userRow=getCurrentTab().find('#user_list').datagrid('getSelected');
			if(userRow==null){
				$.messager.alert('提示','请选择一行数据进行操作！','warning');
			}else{
				var regionsCode=$("#regionsCode").val();
				easyuiUtils.openWindow('showAddDialog','修改邮箱',900,400,'${ctx }/common/user/editUseremail.do?id='+userRow.id,true);
			}
		}
	}); 
	getCurrentTab().find('#user_delBtn').linkbutton({    
		onClick:function(){
			var userRow=getCurrentTab().find('#user_list').datagrid('getSelected');
			
			if(userRow==null){
				$.messager.alert('提示','请选择一行数据进行操作！','warning');
			}else{                                                                                   
				easyuiUtils.confirmPostUrl('是否确定删除‘'+userRow.userName+'’用户?','${ctx }/common/user/deleteUserEmail.do?user.id='+userRow.id);
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