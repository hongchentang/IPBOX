<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div class="easyui-panel" id="channelAddPanel" data-options="region:'center',title:''">
       <form id="channelAddFrom"  action="${ctx}/cms/channel/${StringUtils:isNotEmpty(cmsChannel.id)?'editChannel':'saveChannel'}.do" method="post"  enctype="multipart/form-data">
            <input type="hidden" value="${cmsChannel.id}" name="id"/>
            <input type="hidden" value="${cmsChannel.siteId}" name="siteId"/>
            <input type="hidden" value="${cmsChannel.parentId}" name="parentId"/>
          	<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
				<tbody>
                <tr>
						<td width="20%">
				          	<label>站点名称</label>  
				        </td>
				        <td width="50%">    
                       		<input type="text" readonly="readonly" value="${StringUtils:isNotEmpty(cmsChannel.siteName)?cmsChannel.siteName:(iprcms:getCmsSite(cmsChannel.siteId).name)}"/>
                        </td>
                    </tr>
                	<tr>
						<td>
				          	<label>栏目名称</label>  
				        </td>
				        <td>    
                       		<input name="name" type="text" class="easyui-textbox" data-options="required:true" value="${cmsChannel.name}"/>
                        </td>
                    </tr>
                    
                    <tr>
						<td>
				          	<label>页面标识</label>  
				        </td>
				        <td>    
                       		<input name="pageMark" type="text" class="easyui-textbox" data-options="required:true" value="${cmsChannel.pageMark}"/>
                        </td>
                    </tr>
                    <tr>
						<td>
				          	<label>是否有效</label>  
				        </td>
				        <td>    
                       		<input name="state" type="radio" class="easyui-validatebox" ${cmsChannel.state eq '01' or  empty cmsChannel.state ?'checked="checked"':''}  value="01"/>是
                        	<input name="state" type="radio" class="easyui-validatebox" ${cmsChannel.state eq '00'?'checked="checked"':''} value="00"/>否
                        </td>
                    </tr>
                    <tr>
						<td>
				          	<label>是否导航</label>  
				        </td>
				        <td>    
                       		<input name="navigation" type="radio" class="easyui-validatebox" ${cmsChannel.navigation eq '01' or empty cmsChannel.navigation?'checked="checked"':''} value="01"/>是
                        	<input name="navigation" type="radio" class="easyui-validatebox" ${cmsChannel.navigation eq '00'?'checked="checked"':''} value="00"/>否
                        </td>
                    </tr>
                    
                     <tr>
						<td>
				          	<label>序号</label>  
				        </td>
				        <td>   
				       	 	<input name="orderNum" type="text" class="easyui-textbox"  value="${cmsChannel.orderNum}"/> 
                        </td>
                    </tr>
                    
                    <tr>
						<td>
				          	<label>链接地址</label>  
				        </td>
				        <td>   
				       	 	<input name="url" type="text" class="easyui-textbox" value="${cmsChannel.url}"/> 
                        </td>
                    </tr>
                    <tr>
						<td>
				          	<label>栏目模版</label>  
				        </td>
				        <td>   
				        	<input name="templetId" type="hidden" id="cmsChannel_templetId"   value="${cmsChannel.templetId}"/> 
				       	 	<input name="templetName" type="text" id="cmsChannel_templetName" onclick="selectFileUrl('cmsChannel_templetId','cmsChannel_templetName')"  readonly="readonly" value="${cmsChannel.templetId}"/> 
                        </td>
                    </tr>
                    <tr>
						<td>
				          	<label>信息模版</label>  
				        </td>
				        <td>   
				        	<input name="contentTempletId"   type="hidden" id="cmsChannel_contentTempletId" value="${cmsChannel.contentTempletId}"/> 
				       	 	<input name="contentTempletName" type="text"   id="cmsChannel_contentTempletName" onclick="selectFileUrl('cmsChannel_contentTempletId','cmsChannel_contentTempletName')" value="${cmsChannel.contentTempletId}"/> 
                        </td>
                    </tr>
                    
                    <tr>
						<td>
				          	<label>栏目图片</label>  
				        </td>
				        <td>   
				       	 	<input name="imgUpload" type="file"/> 
				       	 	<textarea rows="" cols="" name="img" style="display: none;" id="attachment">${cmsChannel.img}</textarea>
	                        <c:if test="${not empty cmsChannel.img and fn:contains(cmsChannel.img,'{')}"> 
							      <c:set value="${ipanthercore:getJSONArrayMap(cmsChannel.img)}" var="list" />
								      <c:forEach items="${list}" var="map">
									      <div id="fileSpanAttachment">
									      	<span id="attachmentName">
									      		<img alt="" src="${ctx}${map.downloadUrl}"/>
									      	</span>
									      	<a href="javascript:void(0)" class="easyui-linkbutton" title="删除栏目图片" onclick="fileDelete('attachmentName','attachment','delElemt','${ctx}/common/attachment/deleteAttachment.do?attachment.id=${map.attachmentId}')">删除</a>
	<%-- 									  	<span id="attachmentName"><a href="${ctx}${map.downloadUrl}" target="download">${map.attachmentName}(点击下载)</a></span>  --%>
										  </div>
									  </c:forEach>
							 </c:if>
                        
                        </td>
                    </tr>
                    
                    <tr>
						<td>
				          	<label>生成静态化最大页数</label>  
				        </td>
				        <td>   
				       	 	<input name="maxPage" type="text" class="easyui-textbox" value="${cmsChannel.maxPage}"/> 
                        </td>
                    </tr>
                    
                    <%-- <tr>
						<td>
				          	<label>当次栏目信息变更后</label>  
				        </td>
				        <td>   
				       	 	<input name="htmlChannelId"    type="checkbox"  ${cmsChannel.htmlChannelId eq '01'?'checked="checked"':''}    value="${cmsChannel.htmlChannelId}"/> 静态化所属栏目页面 <br/>
                       		<input name="htmlChannelOldId" type="checkbox"  ${cmsChannel.htmlChannelOldId eq '01'?'checked="checked"':''} value="${cmsChannel.htmlChannelOldId}"/> 静态化原所属栏目页面<br/>
                       		<input name="htmlParChannelId" type="checkbox"  ${cmsChannel.htmlParChannelId eq '01'?'checked="checked"':''} value="${cmsChannel.htmlParChannelId}"/> 静态化所属栏目的所有父栏目页面<br/>
                       		<input name="htmlSiteId"       type="checkbox"  ${cmsChannel.htmlSiteId eq '01'?'checked="checked"':''}       value="${cmsChannel.htmlSiteId}"/> 静态化首页<br/>
                        </td>
                    </tr> --%>
                    <tr>
						<td>
				          	<label>栏目描述</label> 
				        </td>
				   		<td>
				          	<textarea rows="3" cols="50" name="description">${cmsChannel.description}</textarea>
				        </td>
                    </tr>
                    <tr>
						<td colspan="2" align="center" >
				          <h3 style="color: red;text-align: center;"> <label style="color: black;">注:</label> 
				          		 当前栏目需在首页导航显示 需选择‘是否导航’为‘是’
				          </h3>
				         	
				        </td>
                    </tr>
		     	</tbody>
		    </table>
      </form>
       <div style="text-align:center;padding:5px;">
      	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormEdit()">保存</a>
        <c:if test="${not empty cmsChannel.id}">
        	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitDelete()">删除</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="addSonChannel()">添加下级栏目</a>
            <a href="javascript:void(0)" class="easyui-linkbutton" onclick="changeParentChannel()">改变所属栏目</a>
       	</c:if>
       </div>
</div>
<script type="text/javascript">

 tableVBg('.alter-table-v');

 function submitFormEdit(){
	jQuery.messager.confirm("提示信息","确定${not empty cmsChannel.id?'编辑':'添加'}栏目?",function(isReturn){
	   if(isReturn){
		   getCurrentTab().find('#channelAddFrom').form('submit', { 
		    success: function(data){
				if(data){    
					var json=jQuery.parseJSON(data);
					if(json){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							jQuery.messager.alert("提示信息",message,function(){});
						}else if(parseInt(statusCode)==200){
							jQuery.messager.alert("提示信息",message,function(){});
							easyuiUtils.ajaxClearFormForPanel(getCurrentTabId());
						}
					}else{
						console.error("json is null");
					}
				}else{
						console.error("data is null");
				}
			}    
		}); 
	  }
	}); 
} 
 

 getCurrentTab().find('#channelAddFrom').find(":checkbox").click(function(){
	 if(this.checked){
		 $(this).val("01");
	 }else{
		 $(this).val("00");
	 }
 });
 
 function selectFileUrl(entityId,entityName){
	 var url="${ctx}/cms/templet/getTempletList.do?paramMap[dialogId]=selectTempletWindow&paramMap[contentId]="+getCurrentTabId()+
	 "&paramMap[entityId]="+entityId+"&paramMap[entityName]="+entityName;
	 easyuiUtils.openWindow('selectTempletWindow','选择模版文件',600,450,url,true); 
 }
 
 function submitDelete(){
	easyuiUtils.confirmPostUrl('确认删除栏目 ${cmsChannel.name} 吗？','${ctx}/cms/channel/deleteChannel.do?cmsChannel.id=${cmsChannel.id}');
 }
 
 function showDiv(){
	var display= getCurrentTab().find('#channelAddFrom').find("#description");
	if(jQuery(display).css("visibility")=="hidden"){
		getCurrentTab().find('#channelAddFrom').find("#showElemt").text("点击隐藏");
        jQuery(display).css("visibility","visible");     
    }else{
		getCurrentTab().find('#channelAddFrom').find("#showElemt").text("点击显示");
        jQuery(display).css("visibility","hidden");
	}
 }
 
 function addSonChannel(){
	getCurrentTab().find("#editTree").panel('refresh','${ctx}/cms/channel/addChannel.do?cmsChannel.parentId=${cmsChannel.id}&cmsChannel.siteId=${cmsChannel.siteId}');
 }
 
  function changeParentChannel(){
	 var url="${ctx}/cms/channel/changeChannel.do?paramMap[cmsChannelId]=${cmsChannel.id}&paramMap[channelName]=${cmsChannel.name}&paramMap[dialogId]=selectChannelWindow&paramMap[contentId]="+getCurrentTabId()
	 easyuiUtils.openWindow('selectChannelWindow','选择栏目',600,450,url,true); 
	 
 }
  
  function fileDelete(html,elemt,showElemt,urlValue){
	  $.messager.confirm('提示',"确认删除当前文件?",function(result){    
		var data= $.ajax({
				url:urlValue,
				//data:$("#"+formId).serialize(),
				type:"post",
				async:false,
				success:function(data){
				}
			}).responseText;
		 if(data){
			 var json=jQuery.parseJSON(data);
			 if(json){
				 var message = json.message;
				 var statusCode = json.statusCode;
				 if(parseInt(statusCode)==300){
					 jQuery.messager.alert("提示信息",message,function(){});
			  	 }else if(parseInt(statusCode)==200){
			  		jQuery.messager.alert("提示信息",message,function(){});
					 getCurrentTab().find('#siteForm').find("#"+html).html("");
					 getCurrentTab().find('#siteForm').find("#"+elemt).html("-1");
					 //getCurrentTab().find('#siteForm').find("#"+showElemt).remove();
				 }
			 }else{
				 console.error("data is null");
			 }
		 }else{
			 console.error("json is null");
		 }
	});
  }
</script>
