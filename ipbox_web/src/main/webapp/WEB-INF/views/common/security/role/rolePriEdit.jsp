<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
	<style>
 #rolePriTree .tree-title{
 	font-size:14px;
 }
</style>
 <div class="easyui-panel" id="rolePriList">
 	<div class="datagrid-toolbar">
		<table cellspacing="0"  cellpadding="0">
			<tbody>
				<tr> 
					<td>&nbsp;&nbsp;&nbsp;&nbsp;模块:&nbsp;&nbsp;</td>
					<td>	
						<select id="rolePriModuleSelect" style="width: 100px;">
					 	</select>
					</td>
					<td>	
						  &nbsp;&nbsp;&nbsp;&nbsp;当前角色:&nbsp;&nbsp;&nbsp;&nbsp;<b>${role.name}</b>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="rolePriPage" class="easyui-panel"  style="width:100%;height:300px;;margin:0;padding:0;">   
       	<form id="rolePriForm" action="${ctx }/common/security/rolePri/update.do" method="post">
       	  	<input type="hidden" value="${role.id}" name="paramMap[roleId]">
       	  	<input type="hidden" value="" id="privilegeId" name="paramMap[privilegeId]">
       	  	<input type="hidden" value="" id="module" name="paramMap[moduleId]">
	       	<ul id="rolePriTree" data-options="region:'center',title:''">
	       	</ul>	
       	</form>
	</div>
	<div style="text-align:center;padding:5px;">
      	<a href="javascript:void(0)" class="easyui-linkbutton"  onclick="submitRolePriFrom()">确定</a>
    	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="closeWindow('rolePriEditDialog');">取消</a>
    </div>
</div>
<script type="text/javascript">
jQuery('#rolePriEditDialog').find('#rolePriModuleSelect').combobox({
    	value:'IPBOX-ALL',
    	valueField:'id',
    	textField:'name',
    	url:'${ctx}/common/security/privilege/listJson.do',
        onSelect: function(node){
    		var moduleId = node.id;
			getTree(moduleId);
    	},
    	onLoadSuccess:function(){
        	var module= jQuery('#rolePriModuleSelect').combobox('getValue');
        	if(module){
				getTree(module);
    		}
        }
    });

   function getTree(moduleId){
	// var url= ';
	    jQuery('#rolePriEditDialog').find('#rolePriTree').tree({    
	         url:'${ctx}/common/security/rolePri/priByModule.do?role.id=${role.id}&role.module='+moduleId,
	         id:'id',
	         text:'text',
	         parentField:'pid',
	         checkbox:true,
	         lines:true,
	         cascadeCheck:false,
	   }); 
   }
 
   function submitRolePriFrom(){
		jQuery.messager.confirm("提示信息","确定 授予当前权限给角色  ${role.name} ?",function(isReturn){
        	if(isReturn){
            	var checked=jQuery("#rolePriTree").tree("getChecked");
            	var privilegeIds="";
            	var module= jQuery('#rolePriEditDialog').find('#rolePriModuleSelect').combobox('getValue');
            	var root = jQuery('#rolePriEditDialog').find('#rolePriTree').tree("getRoot");
            	if(module){
                	jQuery("#module").val(module);
                }
            	if((checked)&&checked.length>0){
	            	 jQuery.each(checked,function(i){
	                	 if(i<checked.length-1){
	            			 privilegeIds=privilegeIds+checked[i].id+";";
	                	 }else{
	                		 privilegeIds=privilegeIds+checked[i].id;
	                    }
	                 });
	            	 jQuery('#rolePriEditDialog').find("#privilegeId").val(jQuery.trim(privilegeIds));
	                 jQuery('#rolePriEditDialog').find('#rolePriForm').form('submit', {   
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
	             					closeWindow('rolePriEditDialog');
		             			}
	             			}else{
	             				console.error("json is null");
	             			}
	             		}    
	            	});
            	}else{
            		jQuery('#rolePriEditDialog').find('#rolePriForm').form('submit', {   
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
	             					closeWindow('rolePriEditDialog');
		             			}
	             			}else{
	             				console.error("json is null");
	             			}
	             		}    
	            	});
            	}
        	}
        });
   }
</script>
