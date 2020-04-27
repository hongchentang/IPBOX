<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>    
 <div class="easyui-panel" title="信息静态化" id="infoHtmlList">
            <div class="easyui-panel" data-options="collapsible:false" style="width:auto;height:auto;"  id="infoListTreeDiv">
                <ul id="infoListTree" style="height:auto">
            	</ul>	
            </div>
            <div>
           	<form id="infoHtmlFrom"  action="${ctx}/cms/html/htmlInfo.do" method="post">
				<input type="hidden" value="" id="channelIds" name="paramMap[channelIds]"/>  
				<input type="hidden" value="${searchParam.paramMap.siteId}" name="paramMap[siteId]"/> 
          	<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
				<tbody>
					<tr align="left">
						<td align="left" width="20%">
							<label>是否静态化全部栏目信息</label>
				        </td>
				        <td align="left">
				        	  <input type="radio" name="paramMap[isAll]" value="01" checked="checked"/> 否
				        	  <input type="radio" name="paramMap[isAll]" value="02"/> 是
				        </td>
                    </tr>
				</tbody>
			</table>
			</form>
			<div style="text-align:center;padding:5px;">
	      		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormHtml('batch')">生成所选栏目信息</a>
	       	</div>
            </div>
</div>
<script type="text/javascript">
tableVBg('.alter-table-v');

getTrre();
	
function getTrre(){
	   if("${searchParam.paramMap.siteId}"!=""){
		   getCurrentTab().find('#infoListTree').tree({    
		         url:'${ctx}/cms/channel/getChannelTree.do?paramMap[siteId]=${searchParam.paramMap.siteId}',  
		         id:'id',
		         text:'name',
		         parentField:'parentId',
		         lines:true,
		         checkbox:true,
		         onSelect:function(node){
		         	//getCurrentTab().find("#editTree").panel('refresh','${ctx}/cms/channel/addChannel.do?cmsChannel.id='+node.id);
			     }
		   });
	     }else{
	    	 console.error("siteId is null 站点参数错误!");
	     }
}

function submitFormHtml(type){
		var nodes = getCurrentTab().find('#infoListTree').tree('getChecked', ['checked','indeterminate']);
		var channelIds="";
		var isCheched=false;
		if(nodes&&nodes.length>0){
			for(var i=0;i<nodes.length;i++){
				channelIds=channelIds.trim()+nodes[i].id;
				if(i+1!=nodes.length){
					channelIds=channelIds.trim()+",";
				}
			}
			getCurrentTab().find('#infoHtmlFrom').find("#channelIds").val(channelIds);
			//_isChannel=true;
		}else{
			getCurrentTab().find('#infoHtmlFrom').find("input[type='radio']").each(function(){
				if(jQuery(this).is(":checked")){
					if(jQuery(this).val()=='02'){
						isCheched=true;
					}
				}
			});
			if(!isCheched){
				jQuery.messager.alert("提示信息","请至少选择一个栏目",function(){});
				return false;
			}
		}
		var title="确认静态化当前栏目?";
		if(isCheched){
			title="确认静态化全部栏目?";
		}
		$.messager.confirm('提示',title,function(result){    
			if (result){ 
				 getCurrentTab().find('#infoHtmlFrom').form('submit', {   
					    success: function(data){    
							var json=jQuery.parseJSON(data);
							if(json){
								var message = json.message;
								var statusCode = json.statusCode;
								if(parseInt(statusCode)==300){
									jQuery.messager.alert("提示信息",message,function(){});
								}else if(parseInt(statusCode)==200){
									jQuery.messager.alert("提示信息",message,function(){});
									//getCurrentTab().panel('refresh');
									getCurrentTab().find("#htmlInfo").panel('refresh');
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