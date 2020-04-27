<%@page import="com.hcis.ipr.cms.common.utils.CmsCommonUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<div id="selectTempletPanel" class="easyui-panel" data-options="border:false"   >
   <input name="paramMap[entityId]" type="hidden" value="${paramMap.entityId}"/>
   <input name="paramMap[entityName]" type="hidden" value="${paramMap.entityName}"/>
   <input name="paramMap[dialogId]" type="hidden" value="${paramMap.dialogId}"/>
   <input name="paramMap[contentId]" type="hidden" value="${paramMap.contentId}"/>
 <div id="selectTempletPage" class="easyui-layout" data-options="fit:true,title:''"  style="width:100%;height:393px;overflow:hidden;margin:0;padding:0">   
    <div class="easyui-panel" data-options="region:'west',title:'当前站点:${cmsSite.name}',collapsible:false" style="width:100%;height:auto;"  id="selectTempletDiv">
        <ul id="selectTree" style="height:auto">
        </ul>	
    </div>
 </div>   
</div>
<script type="text/javascript">

jQuery("#${paramMap.contentId}").find('#selectTempletPage').layout();

jQuery("#${paramMap.dialogId}").find('#selectTree').tree({    
    url:'${ctx}/cms/templet/templetDirectory.do?paramMap[siteId]=${paramMap.siteId}',  
   // id:'id',
   // text:'text',
	lines:true,
	//attributes:'attributes',
	onClick:function(node){
    	if(node.id!=''){
	    	if(("${paramMap.entityId}"!="")&&("${paramMap.entityName}"!="")){
	            jQuery("#${paramMap.contentId}").find("#${paramMap.entityId}").val(node.id);
	            jQuery("#${paramMap.contentId}").find("#${paramMap.entityName}").val(node.id);
	            easyuiUtils.closeWindow("${paramMap.dialogId}");
	        }
    	}
    }
});
</script>