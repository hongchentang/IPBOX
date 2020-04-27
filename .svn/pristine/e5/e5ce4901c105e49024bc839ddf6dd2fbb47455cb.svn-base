<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<div id="selectChannelPanel" class="easyui-panel" data-options="border:false"   >
   <input name="paramMap[entityId]" type="hidden" value="${paramMap.entityId}"/>
   <input name="paramMap[entityName]" type="hidden" value="${paramMap.entityName}"/>
   <input name="paramMap[dialogId]" type="hidden" value="${paramMap.dialogId}"/>
   <input name="paramMap[contentId]" type="hidden" value="${paramMap.contentId}"/>
   <input name="paramMap[siteId]" type="hidden" value="${paramMap.siteId}"/>
<div class="easyui-panel" data-options="title:'栏目树',"  id="listChannelDiv">
         <ul id="listTree" style="height:auto">
     	</ul>	
 </div>
</div>
<script type="text/javascript">
function getValues(returnId,returnValue){
	  if(("${paramMap.entityId}"!="")&&("${paramMap.entityName}"!="")){
          jQuery("#${paramMap.contentId}").find("#${paramMap.entityId}").val(returnId);
          jQuery("#${paramMap.contentId}").find("#${paramMap.entityName}").val(returnValue);
          closeWindow("${paramMap.dialogId}");
	  }
}

jQuery("#${paramMap.dialogId}").find('#listChannelDiv').tree({    
    url:'${ctx}/cms/channel/getChannelTree.do?paramMap[siteId]=${paramMap.siteId}',  
    id:'id',
    text:'text',
	lines:true,
	attributes:'attributes',
    parentField:'parentField',
    onSelect:function(node){
		getValues(node.id,node.text);
    }
});
</script>