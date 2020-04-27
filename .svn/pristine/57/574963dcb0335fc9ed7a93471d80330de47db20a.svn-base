<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
 <div class="easyui-panel" id="addRolePanel" data-options="region:'center',title:''">
       <form id="roleAddFrom"  action="${ctx}/common/security/role/save.do" method="post">
       		<input type="hidden" name="role.id" value="">
          	<table  cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
				<tbody>
					<tr>
						<td>
				          	<label for="role.name">角色名称</label>  
				        </td>
				        <td>
				        	<input class="easyui-textbox" type="text"  data-options="required:true" id="role.name" name="role.name"  value="" />
				        </td>	
           			</tr>
		    		<tr>
		    			<td><label for="role.roleCode">角色标示</label></td>
		    			<td>
		    				<input class="easyui-textbox" type="text"  id="role.roleCode" name="role.roleCode"  data-options="required:true"  value=""/>
		  				</td>
		    		</tr>
		     		<tr>
		     			<td><label for="role.description">描述 </label></td>
		     			<td>
		     				<textarea class="easyui-validatebox" name="role.description" data-options="required:true" rows="3"></textarea>
		     			</td>
		     		</tr>
		     	</tbody>
		    </table>
      </form>
       <div style="text-align:left; padding:5px;margin-left: 25%;">
      	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitRoleAddFrom()">确定</a>
       	        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('showAddDialog');">关闭</a>
       </div>
</div>
<script type="text/javascript">

 tableVBg('.alter-table-v');

 function submitRoleAddFrom(){
	 jQuery("#showAddDialog").find('#roleAddFrom').form('submit', {   
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
					closeWindow('showAddDialog');
				}
			}else{
				console.error("json is null");
			}
		}    
	}); 
} 
</script>