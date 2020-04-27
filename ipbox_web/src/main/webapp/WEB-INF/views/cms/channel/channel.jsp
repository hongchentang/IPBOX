<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<div class="easyui-panel" title="栏目管理" id="channelList">
    <div class="datagrid-toolbar">
		<table cellspacing="0"  cellpadding="0">
			<tbody>
				<tr> 
<!-- 					<td>&nbsp;&nbsp;&nbsp;&nbsp;模块:&nbsp;&nbsp;</td>
					<td>	
						<select id="moduleSelect" style="width: 100px;">
					 	</select>
					</td> -->
					<td><div class="datagrid-btn-separator"></div></td>
					<td><a onclick="addDialogShow('${ctx}/cms/channel/addChannel.do?cmsChannel.siteId=','新增栏目','')" href="javascript:void(0)" class="easyui-linkbutton"><span class="fa fa-plus"></span> 新增一级栏目</a></td>
					<td><div class="datagrid-btn-separator"></div></td>
					<td><a onclick="refresh()" href="javascript:void(0)" class="easyui-linkbutton" onblur="" o><i class="fa fa-refresh"></i> </a></td>
				</tr>
			</tbody>
		</table>
	</div>

	<div id="listPage" class="easyui-layout" data-options="fit:true"  style="width:100%;height:393px;overflow:hidden;margin:0;padding:0">   
            <div class="easyui-panel" data-options="region:'west',title:'栏目树',collapsible:false,tools:'#listTreeDiv_toolbar'" style="width:25%;height:auto;"  id="listTreeDiv">
                <ul id="listTree" style="height:auto">
            	</ul>	
            </div>
            <div  class="asyui-panel" data-options="region:'center',title:'栏目管理'" style="width:20%;height:330px;"  id="editTree">
            	<h1 style="margin-top: 100px;margin-left: 40%">请点击左边的树进行操作.</h1>
            </div>
	</div>
</div>
<script type="text/javascript">
   getCurrentTab().find('#listPage').layout();
	
   getTrre();
	
   function getTrre(){
	   if("${searchParam.paramMap.siteId}"!=""){
		   getCurrentTab().find('#listTree').tree({    
		         url:'${ctx}/cms/channel/getChannelTree.do?paramMap[siteId]=${searchParam.paramMap.siteId}',  
		         id:'id',
		         text:'name',
		         parentField:'parentId',
		         lines:true,
		         onSelect:function(node){
		         	getCurrentTab().find("#editTree").panel('refresh','${ctx}/cms/channel/addChannel.do?cmsChannel.id='+node.id);
			     }
		   });
	     }else{
	    	 console.error("siteId is null 站点参数错误!");
	     }
   }
   
   function addDialogShow(url,title,type){
	   //var mainSite=jQuery("#index").find('#mainSite').val();
	   if("${searchParam.paramMap.siteId}"!=""){
		   getCurrentTab().find("#editTree").panel('refresh',url+"${searchParam.paramMap.siteId}");	
	   }else{
		   jQuery.messager.alert("提示信息","请先选择站点",function(){});
	   }
	   
   }
   
   function refresh(){
	   getCurrentTab().find('#listTree').tree('reload');
   }
   
 
</script>