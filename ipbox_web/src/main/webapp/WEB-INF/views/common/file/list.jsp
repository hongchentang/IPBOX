<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes.image')}" var="fileTypes"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes')}" var="fileTypes2"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize2"/>
<div class="easyui-panel" title="" id="fileList">
	<div id="fileListPage" class="easyui-layout" data-options="fit:true"  style="width:100%;height:500px;overflow:hidden;margin:0;padding:0">
	      <div  class="asyui-panel" data-options="region:'center',title:'文件复制整理列表'" style="font-size: 18px;color: red"  id="fileCopyList">
            	请注意：
            	<div>1、此处主要是对文件进行进行迁移。</div>
				<div>2、先整理需要迁移的文件文本。</div>
				<div>3、输入复制文件路径</div>
				<div>4、输入新的文件路径，没有的话，系统进行新建文件夹</div>
				<div style="font-weight: bolder;">5、准备就绪后，需要<a style="text-decoration: underline;color: blue" href="javascript:;" onclick="distributeMenu()">开始复制</a>后才会在进行文件复制</div>
				<div>6、如复制多次未能成功，需要重新迁移的文件名进行管理</div>
				<div height="200px;">
					<form id="file_setFrom" name="file_setFrom" action="${ctx}/common/file/saveFileSet.do" method="post" enctype="multipart/form-data">
						 <table class="alter-table-v" cellspacing="0" cellpadding="0" border="0">          
					          <tr>
									<td >上传批量文件名文本</td>
						            <td colspan="3">
						           		<input type="file" name="uploadFile">
						           			<div>
												     允许上传的文件类型：xls,xlsx<br/>
												    允许上传文件的大小：${fileMaxSize}KB 
											</div>
									</td>
					          </tr>
					          <tr>
							  	<td>当前文件路径</td> 
							    <td colspan="3">
					            	<textarea rows="2" cols="120" id="projectExplain" name="projectExplain" readOnly ></textarea>
							  	</td>
							  </tr>
							  <tr>
							  	<td >复制到文件路径</td>
						        <td colspan="3">
									<textarea rows="2" cols="120" id="projectNotes"  name="projectNotes" readOnly ></textarea>
								</td>
							  </tr>
					
					          <tr style="text-align: center;line-height: 20px">
					            <td colspan="4">
					            <div style="width: 100%;text-align: center;">
					            <button type="button" onclick="javascript:submitThisForm();" style="float: center; margin-right: 20px" class="easyui-linkbutton">
					            	保存
					            </button>
					            </div>
					            </td>
					          </tr>
					        </table>
						</form>
					</div>
            </div>	
		<div>	
</div>

<script type="text/javascript">
	$(document).ready(function(){
		
	});

  function deletemenu(){
	   var node = getCurrentTab().find('#menuListTree').tree('getSelected');
	   if(node){
			jQuery.messager.confirm("提示","确定删除  "+node.text+" ?",function(isReturn){
	        	if(isReturn){
	        		loading.open();
	        		var data=jQuery.ajaxSubmitValue("${ctx}/manage/wechat/menu/delete.do?menu.id="+node.id);
	        		loading.close();
	        		var json=jQuery.parseJSON(data);
	    			if(!jQuery.isEmptyObject(json)){
	    				var responseMsg = json.responseMsg;
	    				var responseCode = json.responseCode;
	    				if(responseCode=="01"){
	    					jQuery.messager.alert("提示","删除失败","error");
	    				}else if(responseCode=="00"){
	    					jQuery.messager.alert("提示","删除成功","info");
	    					getCurrentTab().find('#menuListTree').tree('reload');
	    				}
	    			}else{
	    				console.error("json is null");
	    			}
	        	}
	         });
	   }else{
		   jQuery.messager.alert("提示","请先选择一条数据","warning");
	   }
  }
  
  //新增菜单
  function addMenu() {
	  easyuiUtils.openWindow("editMenuWindow","新增菜单",450,390,"${ctx}/manage/wechat/menu/edit.do",true);
  }
  
  //修改菜单
  function editMenu() {
	  var node = getCurrentTab().find('#menuListTree').tree('getSelected');
	   if(node){
		  easyuiUtils.openWindow("editMenuWindow","修改菜单",450,390,"${ctx}/manage/wechat/menu/edit.do?menu.id="+node.id,true);		   
	   }else{
		   jQuery.messager.alert("提示","请先选择一条数据","warning");
	   }
  }
  
  //发布菜单
  function distributeMenu() {
	  jQuery.messager.confirm("提示","确定发布菜单吗?",function(isReturn){
		  if(isReturn) {
			var data=$.ajaxSubmitValue('${ctx}/manage/wechat/menu/generate.do');
			var json=jQuery.parseJSON(data);
			var responseCode = json.responseCode;
			var responseMsg = json.responseMsg;
			if("00"==responseCode){
				jQuery.messager.alert("提示","发布成功","info");
				getCurrentTab().find('#menuListTree').tree('reload');
			} else {
				jQuery.messager.alert("提示","发布失败。"+responseMsg,"error");
			}			
		  }
	  });
  }
  
  //发布菜单
  function refreshAccessToken() {
	  jQuery.messager.confirm("提示","确定手工刷新AccessToken吗?",function(isReturn){
		  if(isReturn) {
			var data=$.ajaxSubmitValue('${ctx}/manage/wechat/refreshAccessToken.do');
			jQuery.messager.alert("提示",data,"info");		
		  }
	  });
  }
</script>