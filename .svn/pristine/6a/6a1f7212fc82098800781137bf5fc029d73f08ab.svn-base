<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<div class="easyui-panel" title="站点管理" id="siteList">
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
					<td><a onclick="addDialogShow('${ctx}/cms/site/siteAdmin.do','新增站点','')" href="javascript:void(0)" class="easyui-linkbutton"><span class="fa fa-plus"></span> 新增一级站点</a></td>
					<td><div class="datagrid-btn-separator"></div></td>
					<td><a onclick="refreshSite()" href="javascript:void(0)" class="easyui-linkbutton" onblur="" o><i class="fa fa-refresh"></i> </a></td>
				</tr>
			</tbody>
		</table>
	</div>

	<div id="listSitePage" class="easyui-layout" data-options="fit:true"  style="width:100%;height:393px;overflow:hidden;margin:0;padding:0">   
            <div class="easyui-panel" data-options="region:'west',title:'站点树',collapsible:false,tools:'#listTreeDiv_toolbar'" style="width:25%;height:auto;"  id="listTreeDiv">
                <ul id="listSiteTree" style="height:auto">
            	</ul>	
            </div>
            <div  class="asyui-panel" data-options="region:'center',title:'站点管理'" style="width:20%;height:330px;"  id="editSiteTree">
            	<h1 style="margin-top: 100px;margin-left: 40%">请点击左边的树进行操作.</h1>
            </div>
	</div>
</div>
<script type="text/javascript">
   getCurrentTab().find('#listSitePage').layout();
	
   getSiteTrre();
	
   function getSiteTrre(){
		   getCurrentTab().find('#listSiteTree').tree({    
		         url:'${ctx}/cms/site/getSiteTree.do',  
		         id:'id',
		         text:'name',
		         parentField:'parentId',
		         lines:true,
		         onSelect:function(node){
		         	getCurrentTab().find("#editSiteTree").panel('refresh','${ctx}/cms/site/siteAdmin.do?cmsSite.id='+node.id);
			     }
		   });
   }
   
   function addDialogShow(url,title,type){
		   getCurrentTab().find("#editSiteTree").panel('refresh',url);	
   }
   
   function refreshSite(){
	   getCurrentTab().find('#listTree').tree('reload');
   }
   
 
</script>