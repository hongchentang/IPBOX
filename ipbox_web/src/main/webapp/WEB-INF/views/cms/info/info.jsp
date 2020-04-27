<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<div class="easyui-panel" title="信息管理" id="infoList">
	<div id="infoListPage" class="easyui-layout" data-options="fit:true"  style="width:100%;height:493px;overflow:hidden;margin:0;padding:0">   
            <div class="easyui-panel" data-options="region:'west',title:'栏目树',collapsible:false," style="width:25%;height:auto;"  id="infolistTreeDiv">
                <ul id="listTree" style="height:auto">
            	</ul>	
            </div>
            <div  class="asyui-panel" data-options="region:'center',title:'信息管理'" style="width:20%;height:430px;"  id="infoEditTree">
            	<h1 style="margin-top: 100px;margin-left: 40%">请点击左边的树进行操作.</h1>
            </div>
	</div>
</div>
<script type="text/javascript">
   getCurrentTab().find('#infoListPage').layout();
	
   getTrre();
	
   function getTrre(){
		   getCurrentTab().find('#infolistTreeDiv').tree({    
		         url:'${ctx}/cms/channel/getChannelTree.do',  
		         id:'id',
		         text:'name',
		         parentField:'parentId',
		         lines:true,
		         onSelect:function(node){
		        	 if(node.id&&node.id!=""){
		        		 $('#infoEditTree').panel('options').queryParams={};
		         		getCurrentTab().find("#infoEditTree").panel('refresh','${ctx}/cms/info/listInfo.do?tabId=${param.tabId}&paramMap[channelId]='+node.id);
		        	 }
		         }
		   });
   } 
</script>