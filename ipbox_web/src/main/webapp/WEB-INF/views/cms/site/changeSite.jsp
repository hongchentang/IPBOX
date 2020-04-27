<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<div id="selectSitePanel" class="easyui-panel" data-options="border:false"   >
   <input name="paramMap[dialogId]" type="hidden" value="${paramMap.dialogId}"/>
   <input name="paramMap[contentId]" type="hidden" value="${paramMap.contentId}"/>
<div class="easyui-panel" data-options="title:'栏目树',"  id="listSiteDiv">
    <ul id="listTree" style="height:auto">
    </ul>	
 </div>
</div>
<script type="text/javascript">
function submitChannel(returnId,name){  
 $.messager.confirm('提示','确认将当前站点修改为 '+name+' 栏目下',function(result){    
			if (result){  
				var url="${ctx}/cms/site/updateParentId.do?cmsSite.id=${paramMap.cmsSiteId}&cmsSite.parentId="+returnId;
				var data= jQuery.ajaxSubmitValue(url);
					if(data){
						var json=jQuery.parseJSON(data);
						if(json){
							var msg = json.message;
							var statusCode = json.statusCode;
							//Response转为AjaxReturnObject
							if(json.responseCode!=undefined){
								msg=json.responseMsg;
								statusCode=(json.responseCode=='00'?200:300);
							}
							if(parseInt(statusCode)==300){
								$.messager.alert('提示',msg,'error');
							}
							else if(parseInt(statusCode)==200){
								$.messager.alert('提示',msg,'info',function(){
									closeWindow("${paramMap.dialogId}");
									getCurrentTab().panel('refresh');
								});
							}
						}else{
								$.messager.alert('提示','返回数据解析错误！','error');
						}
				 }
				 else{
					$.messager.alert('提示','返回数据错误！','error');
				}
			}
		});
 
}

jQuery("#${paramMap.dialogId}").find('#listSiteDiv').tree({      
         url:'${ctx}/cms/site/getSiteTree.do',  
         id:'id',
         text:'name',
         parentField:'parentId',
         lines:true,
         onSelect:function(node){
		if('${paramMap.cmsSiteId}'!=node.id){
			submitChannel(node.id,node.text);
		}else{
			$.messager.alert('提示','无法将当前站点 归属到 '+node.text+' 栏目！','error');
		}
    }
});
</script>