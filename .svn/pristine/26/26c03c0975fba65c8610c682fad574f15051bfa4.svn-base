<%@page import="com.hcis.ipr.cms.common.utils.CmsCommonUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<%String path=CmsCommonUtils.replaceWinSeparator(request.getRealPath("/")); %>
<c:set value="<%=path%>" var="realPath"/>
<c:set value="template" var="template"/>
<c:set value="${StringUtils:isNotEmpty(cmsSite.sourcePath)?cmsSite.sourcePath:cmsSite.id}" var="sourcePath"/>
<div id="selectTempletPanel" class="easyui-panel" data-options="border:false"   >
   <input name="paramMap[entityId]" type="hidden" value="${paramMap.entityId}"/>
   <input name="paramMap[entityName]" type="hidden" value="${paramMap.entityName}"/>
   <input name="paramMap[dialogId]" type="hidden" value="${paramMap.dialogId}"/>
   <input name="paramMap[contentId]" type="hidden" value="${paramMap.contentId}"/>
<div id="selectTempletSearch">
	<div style="margin: 10px">    
		
	</div>
</div>
 <div id="listTempletPage" class="easyui-layout" data-options="fit:true,title:''"  style="width:100%;height:393px;overflow:hidden;margin:0;padding:0">   
    <div class="easyui-panel" data-options="region:'west',title:'${cmsSite.name}',collapsible:false" style="width:25%;height:auto;"  id="listTempletDiv">
        <ul id="listTree" style="height:auto">
        </ul>	
    </div>
    <div  class="asyui-panel" data-options="region:'center',title:'文件列表'" style="width:20%;height:330px;" id="editTempletTree">
    </div>
 </div>   
</div>
<script type="text/javascript">

jQuery("#${paramMap.contentId}").find('#listTempletPage').layout();

jQuery("#${paramMap.dialogId}").find('#listTempletDiv').tree({    
    url:'${ctx}/cms/templet/getTempletDirectoryJson.do?paramMap[realPath]=${realPath}templet/${sourcePath}',  
    id:'id',
    text:'text',
	lines:true,
	attributes:'attributes',
	//children:jQuery.ajaxSubmitValue("${ctx}/cms/templet/getTempletDirectoryJson.do?paramMap[realPath]=${realPath}/template/${sourcePath}"+node.id),
   // parentField:'pid',
    onSelect:function(node){
	var url='${ctx}/cms/templet/listTempletFile.do?paramMap[entityId]=${paramMap.entityId}&paramMap[entityName]=${paramMap.entityName}&'
			+'paramMap[dialogId]=${paramMap.dialogId}&paramMap[contentId]=${paramMap.contentId}&paramMap[realPath]='+node.id;
			jQuery("#${paramMap.dialogId}").find('#editTempletTree').panel('refresh',url);	
 
    }
});
</script>