<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<div class="easyui-panel" title="模版列表" id="siteList">
    <div class="datagrid-toolbar">
		<table cellspacing="0"  cellpadding="0">
			<tbody>
				<tr> 
					<td>&nbsp;&nbsp;&nbsp;&nbsp;站点:&nbsp;&nbsp;</td>
					<td>	
						<input id="siteSelect" style="width: 100px;"/>
					</td>
					<td><div class="datagrid-btn-separator"></div></td>
					<td><a onclick="editTempletShow('${ctx}/cms/templet/addTemplet.do?paramMap[siteId]=','上传模版','')" href="javascript:void(0)" class="easyui-linkbutton"><span class="fa fa-plus"></span> 上传模版</a></td>
				</tr>
			</tbody>
		</table>
	</div>

	<div id="listTempletPage" class="easyui-layout" data-options="fit:true"  style="width:100%;height:393px;overflow:hidden;margin:0;padding:0">   
            <div class="easyui-panel" data-options="region:'west',title:'文件树',collapsible:false" style="width:25%;height:auto;"  id="listTempletTreeDiv">
                <ul id="listTempletTree">
                	<li style="list-style: none;"><h2>请选择站点操作.</h2></li>
            	</ul>	
            </div>
            <div  class="asyui-panel" data-options="region:'center',title:'文件管理'" style="width:20%;height:330px;"  id="editTempletTree">
            	<h1 style="margin-top: 100px;margin-left: 40%">请点击左边的树进行操作.</h1>
            </div>
	</div>
</div>

<script type="text/javascript">
	getCurrentTab().find('#listTempletPage').layout();
	getCurrentTab().find('#siteSelect').combotree({
    	id:'id',
    	text:'text',
    	parentField:'parentField',
        lines:true,
    	url:'${ctx}/cms/site/getSiteJson.do',
        onSelect: function(node){
        	getTempletTrre(node.attributes.sourcePath);
    	},
    	onLoadSuccess:function(){
			var datas=getCurrentTab().find('#siteSelect').combotree("tree").tree('getSelected');
			if(datas&&datas.length>0){
				//$(this).combotree('tree').tree('getSelected');
    			getTempletTrre();
			}
        }
    });
  	
	function editTempletShow(url,title,type){
		var datas=getCurrentTab().find('#siteSelect').combotree("tree").tree('getSelected');
		if(datas!=null){
			url=url+datas.id;
			easyuiUtils.openWindow('editTempletDialog',title,600,360,url,true); 
		}else{
			jQuery.messager.alert("提示信息","请先选择站点",function(){});
		}
	} 
 

   function getTempletTrre(value){
  	   getCurrentTab().find('#listTempletTree').tree({   
  		 	id:'id',
     		text:'text',
     		parentField:'parentField',
     		lines:true,
  	        url:'${ctx}/cms/templet/listTempletFileTree.do?paramMap[root]='+value, 
  	      	onBeforeExpand:function(node,param){  
  	      		if(node.attributes.hasSon=="02"){
  	      			return false;
  	      		}else{
  	      			var  getChildren=getCurrentTab().find('#listTempletTree').tree('getChildren',node.target);
  	      			if(getChildren.length==0){
		  	      		var dataArray=jQuery.ajaxSubmitValue('${ctx}/cms/templet/listTempletFileTree.do?paramMap[root]='+node.parentField+node.id);
		  	      		getCurrentTab().find('#listTempletTree').tree('append', {
			  	      	parent: node.target,
			  	      	data:  eval("("+dataArray+")")});
  	      			}
  	      		}
  	    	},
	        onSelect:function(node){
	        	if(node.attributes.isFile){
	        		getCurrentTab().find("#editTempletTree").panel('refresh','${ctx}/cms/templet/editTempletFile.do?paramMap[root]='+node.parentField+node.id);
	        	}
 		     }
 	   });
   }
</script>