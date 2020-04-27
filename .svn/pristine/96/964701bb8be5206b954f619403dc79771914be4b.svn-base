<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<script type="text/javascript">
		jQuery('#editRoleDialog').find('#urerRoleConfigTree').tree({    
			    url:'${ctx}/common/user/roleConfigTree.do?user.id=${user.id}',  
			    id:'id',
			    text:'text',
			   // parentField:'pid',
			    checkbox:true,
			    lines:true,
			    cascadeCheck:true,
			});
		
function submitRoleConfigFrom(){
	jQuery.messager.confirm("提示信息","确定授权当前角色给用户  ${user.realName} ?",function(isReturn){
    	if(isReturn){
        	var checked=jQuery('#editRoleDialog').find("#urerRoleConfigTree").tree("getChecked",['checked','indeterminate']);
        	var roleIds="";
        	if((checked)&&checked.length>0){
            	 jQuery.each(checked,function(i){
                	 if(i<checked.length-1){
                		 roleIds=roleIds+checked[i].id+";";
                	 }else{
                		 roleIds=roleIds+checked[i].id;
                    }
                 });
        	}
           	jQuery('#editRoleDialog').find("#userRole_roleId").val(jQuery.trim(roleIds));
                jQuery('#editRoleDialog').find('#roleConfigFrom').form('submit', {   
            	    success: function(data){    
            			var json=jQuery.parseJSON(data);
            			if(!jQuery.isEmptyObject(json)){
            				var message = json.message;
            				var statusCode = json.statusCode;
            				if(parseInt(statusCode)==300){
            					jQuery.messager.alert("提示信息",message,function(){});
            				}else if(parseInt(statusCode)==200){
            					jQuery.messager.alert("提示信息",message,function(){});
            					closeWindow('editRoleDialog');
            				}
            			}else{
            				console.error("json is null");
            			}
            		}    
           	});
        	
    	}
    });
}
</script>

<div style="height:25px;font-size:20px;margin-left:8px;">当前用户:<b >${user.realName}</b></div>
<div class="easyui-panel" id="roleConfigPanel" data-options="region:'center',title:'角色列表'" style="height: 230px;">
  <form id="roleConfigFrom"  action="${ctx}/common/user/saveRoleConfig.do" method="post">
    <input type="hidden" value="${user.id }" name="user.id">
    <input type="hidden" value="" id="userRole_roleId" name="userRole.roleId">
    <ul id="urerRoleConfigTree" title="">
    </ul>
  </form>
</div>
<div style="text-align:center;padding:5px"> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitRoleConfigFrom()">确定</a> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:closeWindow('editRoleDialog')">关闭</a> </div>

