<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<div style="height: 25px; vertical-align:middle"> <span>当前用户:</span>&nbsp;&nbsp;&nbsp;&nbsp;<b >${map.realName}</b></div>
<div class="easyui-panel" id="regionsConfigPanel" data-options="region:'center',title:'区域列表'" style="height: 300px;">
  <form id="regionsConfigFrom"  action="${ctx}/common/user/regionsConfigSave.do" method="post">
    <input type="hidden" value="${map.id }" name="userRegions.userId">
    <input type="hidden" value="" id="userRegionsCode" name="userRegions.regionsCode">
    <input type="hidden" value="" id="userRegionsHasChild" name="userRegions.hasChild">
    <ul id="regionsConfigTree" title="" style="width:100%">
    </ul>
  </form>
</div>
<div style="text-align:center;padding:5px"> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitregionsConfigFrom()">确定</a> <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:closeWindow('editRegionsDialog')">关闭</a> </div>
<script type="text/javascript">
		jQuery('#editRegionsDialog').find('#regionsConfigTree').tree({    
			    //url:'${ctx}/common/user/regionsConfigTree.do?user.id=${user.id}',  
				data:${regionsTreeData},
			    id:'id',
			    text:'text',
			    parentField:'pid',
			    checkbox:true,
			    lines:true,
			    cascadeCheck:true,
				formatter:function(node){
					var tableNode="<table width='100%'>";
					tableNode+="<tr><td>";
					tableNode+=node.text;
					tableNode+="</td>";
					tableNode+="<td align='right'>";
					tableNode+="(<input type='checkbox' name='hasChild_"+node.id+"' "+(node.attributes.hasChild=='1'?'checked':'')+" value='"+node.id+"'>包括下属区域)";
					tableNode+="</td></tr>";
					tableNode+="</table>";
					return tableNode;
				}

			});
		
function submitregionsConfigFrom(){
	jQuery.messager.confirm("提示信息","确定授权当前区域数据给用户  ${user.realName} ?",function(isReturn){
    	if(isReturn){
        	var checked=jQuery('#editRegionsDialog').find("#regionsConfigTree").tree('getChecked', ['checked','indeterminate']);
        	var regionsCodes="";
			var regionsHasChilds="";
        	if((checked)&&checked.length>0){
            	 jQuery.each(checked,function(i){
                	 regionsCodes=regionsCodes+checked[i].id+";";
					 //取包括下属区域的checkbox
					 if(jQuery('#editRegionsDialog').find("input[name='hasChild_"+checked[i].id+"']").prop("checked")==true){
					 	regionsHasChilds=regionsHasChilds+checked[i].id+";";
					 }
                 });
            	 jQuery('#editRegionsDialog').find("#userRegionsCode").val(jQuery.trim(regionsCodes));
            	 jQuery('#editRegionsDialog').find("#userRegionsHasChild").val(jQuery.trim(regionsHasChilds));
                 jQuery('#editRegionsDialog').find('#regionsConfigFrom').form('submit', {   
             	    success: function(data){    
             			var json=jQuery.parseJSON(data);
             			if(!jQuery.isEmptyObject(json)){
             				var message = json.message;
             				var statusCode = json.statusCode;
             				if(parseInt(statusCode)==300){
             					jQuery.messager.alert("提示信息",message,function(){});
             				}else if(parseInt(statusCode)==200){
             					jQuery.messager.alert("提示信息",message,function(){});
             					closeWindow('editRegionsDialog');
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
