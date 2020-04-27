<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
 <div class="easyui-panel" id="editRolePanel" data-options="region:'center'">
       <form id="roleEditFrom"  action="${ctx}/common/security/role/save.do" method="post">
       		<input type="hidden" name="role.id" value="${role.id}">
       		<input type="hidden" name="oldRoleName" value="${role.name}">
          	<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
				<tbody>
					<tr>
						<td>
				          	<label for="role.name">角色名称</label>  
				        </td>
				        <td>
				        	<input class="easyui-textbox" type="text"  data-options="required:true" id="role.name" name="role.name"  value="${role.name}" />
				        </td>	
           			</tr>
		    		<tr>
		    			<td><label for="role.roleCode">角色标示</label></td>
		    			<td>
		    				<input class="easyui-textbox" type="text"  id="role.roleCode" name="role.roleCode"  data-options="required:true"  value="${role.roleCode}"/>
		  				</td>
		    		</tr>
		     		<tr>
		     			<td><label for="role.description">描述 </label></td>
		     			<td>
		     				<textarea class="easyui-validatebox" name="role.description" data-options="required:true" rows="3">${role.description}</textarea>
		     			</td>
		     		</tr>
		     	</tbody>
		    </table>
      </form>
       <div style="text-align:left; padding:5px;margin-left: 25%;">
      	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitRoleEditFrom()">确定</a>
       	        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('showEditDialog');">关闭</a>
       </div>
</div>
<script type="text/javascript">

 tableVBg('.alter-table-v');
 
 function submitRoleEditFrom(){
	 jQuery("#showEditDialog").find('#roleEditFrom').form('submit', {   
	    success: function(data){    
			var json=jQuery.parseJSON(data);
			if(!jQuery.isEmptyObject(json)){
				var message = json.message;
				var statusCode = json.statusCode;
				if(parseInt(statusCode)==300){
					jQuery.messager.alert("提示信息",message,function(){});
				}else if(parseInt(statusCode)==200){
					jQuery.messager.alert("提示信息",message,function(){});
					jQuery('#'+getCurrentTabId()).panel('refresh');
					closeWindow('showEditDialog');
				}
			}else{
				console.error("json is null");
			}
		}    
	}); 
} 
</script>