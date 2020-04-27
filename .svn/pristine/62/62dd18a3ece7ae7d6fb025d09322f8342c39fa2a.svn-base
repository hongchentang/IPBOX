<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>    
 <div class="easyui-panel" title="栏目静态化" id="channelHtmlList">
            <div class="easyui-panel" data-options="collapsible:false" style="width:auto;height:auto;"  id="channelTreeDiv">
                <ul id="channelListTree" style="height:auto">
            	</ul>	
            </div>
            <div>
           	<form id="channelHtmlFrom"  action="${ctx}/cms/html/htmlChannel.do" method="post">
				<input type="hidden" value="" id="channelIds" name="paramMap[channelIds]"/>  
				<input type="hidden" value="${searchParam.paramMap.siteId}" name="paramMap[siteId]"/> 
				<input type="hidden" id="isAll" name="paramMap[isAll]" value=""/>
          	<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
				
				<tbody>
					<tr align="left">
						<td align="left" width="1%">
						
				        </td>
<!-- 				        <td align="left"> -->
<!-- 				        	<label>是否静态化全部栏目</label>   -->
<!-- 				          	<input type="checkbox" id="isAll" name="paramMap[isAll]" value="01"/>是 -->
<!-- 				        </td> -->
                    </tr>
				</tbody>
			</table>
			</form>
			<div style="text-align:center;padding:5px;">
				<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitChannel('batch')">生成所选栏目</a>
	      		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitChannel('all')">生成全部栏目</a>
	       	</div>
            </div>
</div>
<script type="text/javascript">
tableVBg('.alter-table-v');

getChannelTrre();
	
function getChannelTrre(){
	   if("${searchParam.paramMap.siteId}"!=""){
		   getCurrentTab().find('#channelListTree').tree({    
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

function submitChannel(type){
	if(type=="batch"){
		var nodes = getCurrentTab().find('#channelListTree').tree('getChecked', ['checked','indeterminate']);
		var channelIds="";
		if(nodes&&nodes.length>0){
			for(var i=0;i<nodes.length;i++){
				channelIds=channelIds.trim()+nodes[i].id;
				if(i+1!=nodes.length){
					channelIds=channelIds.trim()+",";
				}
			}
			getCurrentTab().find('#channelHtmlFrom').find("#channelIds").val(channelIds);
			//_isChannel=true;
		}else{
			jQuery.messager.alert("提示信息","请至少选择一个栏目",function(){});
			return false;
		}
	}else if(type=="all"){
		getCurrentTab().find('#channelHtmlFrom').find("#isAll").val("01");
	}
		$.messager.confirm('提示',"确认静态化当前栏目?",function(result){    
			if (result){ 
				 getCurrentTab().find('#channelHtmlFrom').form('submit', {   
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
									getCurrentTab().find("#htmlChannel").panel('refresh');
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