<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="${ctx}/js/ueditor/lang/zh-cn/zh-cn.js"></script>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes')}" var="fileTypes"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize"/>
<c:set value="${StringUtils:isNotEmpty(cmsInfo.type)?cmsInfo.type:searchParam.paramMap.type}" var="type"/>
<c:set value="0" var="size"/>
<c:set value="5" var="maxSize"/>
<div class="easyui-panel" id="infoAddPanel" data-options="region:'center',title:''">
       <form id="infoAddFrom"  action="${ctx}/cms/info/${StringUtils:isNotEmpty(cmsInfo.id)?'editInfo':'saveInfo'}.do " method="post" enctype="multipart/form-data">
            <input name="type" id="type" type="hidden"  value="${StringUtils:isNotEmpty(cmsInfo.type)?cmsInfo.type:searchParam.paramMap.type}"/>
          	<table border="0" cellspacing="1" cellpadding="1"  class="alter-table-v">
				<tbody>
                    <tr>
						 <td width="25%" >
				          	<label>标题</label>  
				        </td>
				         <td width="75%"> 
                       		<input name="title"  type="text" class="easyui-textbox" data-options="required:true" 
                       		style="width:400px;" value="${cmsInfo.title}"/>
                        </td>
                    </tr>
                    <tr>
						 <td >
				          	<label>来源</label>  
				        </td>
				         <td> 
                            <input name="source" type="text" class="easyui-textbox" style="width:400px;" value="${cmsInfo.source}"/>
                        </td>
                    </tr>
                    <tr>
						 <td >
				          	<label>作者</label>  
				        </td>
				         <td> 
                            <input name="author" type="text" class="easyui-textbox" style="width:400px;" value="${cmsInfo.author}"/>
                        </td>
                    </tr>
                    <c:if test="${type eq 2 }">
				<tr>
					<td>操作</td>
					<td><a onclick="divide_course()" href="javascript:void(0)"
						class="easyui-linkbutton">选择课程</a></td>
				</tr>
				<tr>
					<td>已选课程</td>
					<td><div id="courseContent">
							<c:if test="${not empty course }">
								<span id="course_id_${course.id }"><input
									checked="checked" type="checkbox" name="courseId"
									value="${course.id }">${course.courseName}<button type="button" class="easyui-linkbutton delete-btn"
										onclick="$('#course_id_${course.id}').remove()">删除</button> </span>
							</c:if>
						</div></td>
				</tr>
				</c:if>
				<tr>
						 <td >
				          	<label>摘要</label>  
				        </td>
				         <td> 
                            <textarea name="description" rows="3" cols="50">${cmsInfo.description}</textarea>
                            <br/>
                            (默认截取内容文本前100字)
                        </td>
                    </tr>
                    <tr>
						 <td >
				          	<label>信息内容</label>  
				        </td>
                        <td></td>
                    </tr>
                      <tr>
                        <td></td>
                        <td>
                        <script id="content" name="content" style="width:624px;height:500px;"></script>
                        <!--<textarea id="postContent.postContent" class="edit_input"></textarea>-->
                        <input type="hidden" id="ueditorContent" name="ueditorContent" value=""/>
                        </td>
                    </tr>
                    

                    <tr>
						 <td >
				          	<label>Tag标签</label>  
				        </td>
				         <td> 
 							<input type="text" name="tags" value="${cmsInfo.tags}" style="width:400px;" class="easyui-textbox" /> 多个标签用 , 隔开
                        </td>
                    </tr>
                    <tr>
						 <td >
				          	<label>外部链接</label>  
				        </td>
				         <td> 
 							<input type="text" name="url" value="${cmsInfo.url}" style="width:400px;" class="easyui-textbox" />
                        </td>
                    </tr>
                    <tr>
						 <td >
				          	<label>信息图片</label>  
				        </td>
				         <td> 
			             	<textarea  name="img" id="attachmentImg"   style="display: none;">${cmsInfo.img}</textarea>
							<c:if test="${StringUtils:isNotEmpty(cmsInfo.id)}">
								<c:set var="uuid" value="${cmsInfo.id}"/>
							</c:if>
							<c:if test="${StringUtils:isEmpty(cmsInfo.id)}">
								<c:set var="uuid" value="${ipanthercore:uuid()}"/>
							</c:if>
							<input name="id"  type="hidden" value="${uuid}"/>
						 	<a  class="easyui-linkbutton"  onclick="uploadImg('${uuid}','attachmentImgName','attachmentImg','img','上传图片')"><span>上传图片</span></a>
							<input type="hidden" id="attachmentParam_img_${uuid}" value="${ipanthercommon:getAttachmentParamImage('',uuid,'/WEB-INF/views/common/attachment/callbackImg.jsp','/upload/cms/info/img/')}">
						 	<c:if test="${StringUtils:isNotEmpty(cmsInfo.img)}"> 
								      <c:set value="${ipanthercore:getJSONMap(cmsInfo.img)}" var="map" />
									      <div id="fileSpanAttachmentImg">
									  		<span id="attachmentImgName" style="margin-top: 8px;"><img alt="" width="50px;" height="50px;" src="${ctx}${map.downloadUrl}" id="attachmentName"/>${map.attachmentName}</span> 
										  	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="delImgFile('attachmentImgName','attachmentImg','delElemt','${ctx}/common/attachment/deleteAttachment.do?attachment.id=')" title="删除文件">删除</a>
										  </div>
										  
							</c:if>
							<c:if test="${StringUtils:isEmpty(cmsInfo.img)}"> 
							      <div id="fileSpanAttachmentImg">
							  		<span id="attachmentImgName"></span> 
								  </div>
							</c:if>
							<br/>
                        </td>
                    </tr>
                    <tr>
						 <td >
				          	<label>附件</label>  
				        </td>
				         <td> 
				         <a  class="easyui-linkbutton"  onclick="uploadAttchs()"><span>上传附件</span></a>
				           <c:if test="${not empty cmsInfo.attchs}">
						      <textarea  name="attchs" style="display: none;">${cmsInfo.attchs}</textarea>
						   </c:if>
						   <div id="fileDiv">
						    <c:if test="${not empty cmsInfo.attchs}">
						      <c:forEach items="${ipanthercore:getJSONArrayMap(cmsInfo.attchs)}" var="map" varStatus="i">
						       <c:set value="${i.index+1}" var="size"/>
						      <div id="fileSpan${i.index}">
							  	<span id="attachmentName"><a href="${ctx}${map.downloadUrl}" target="download">${map.attachmentName}(点击下载)</a></span> 
							  	<a onclick="javascript:delFileSpan('${ctx}/common/attachment/deleteAttachment.do?attachment.id=${map.attachmentId}','${i.index}')" href="javascript:void(0)" class="easyui-linkbutton">
                                <i class="fa fa-minus"></i> 删除</a>
							  </div>
						      </c:forEach>
						    </c:if>
						   </div>
						   	<span><b>只能上传不超过${maxSize}个附件</b><br/>
								     <%-- 允许上传的文件类型：<b>${fileTypes}</b><br/> --%>
								   允许上传文件的大小：<b>${fileMaxSize}KB</b> 
							</span>
                        </td>
                    </tr>
		     	</tbody>
		    </table>
      </form>
       <div style="text-align:center;padding:5px;">
      	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormEdit()">保存</a>
       	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="returnList()">关闭</a>
       </div>
</div>
<div id="insertHtml" style="display: none;">
${cmsInfo.content}
</div>
<script type="text/javascript">

 tableVBg('.alter-table-v');

   $(function(){
        $.fn.jPicker.defaults.images.clientPath='${ctx}/js/jquery/plugins/jpicker/images/';
			   //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用		UE.getEditor('editor')就能拿到相关的实例
		var editor=UE.getEditor('content');
		editor.addListener('contentchange',function(){
			this.sync();
		});
	editor.ready( function( editor ) {
    	var insertHtml=$("#infoMsgWindow").find("#insertHtml").html();
	    if(insertHtml!=""){
	    	UE.getEditor('content').setContent(insertHtml,false);
	    }else{ 
			console.log("insertHtml is null");
		} 
    });
   });

 function submitFormEdit(){
	jQuery.messager.confirm("提示信息","确定${not empty cmsInfo.id?'编辑':'添加'}信息?",function(isReturn){
	   if(isReturn){
		   $("#infoMsgWindow").find('#infoAddFrom').form('submit', { 
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
							closeWindow('infoMsgWindow');
							easyuiUtils.ajaxClearFormForPanel(getCurrentTabId());
						}
					}else{
						console.error("json is null");
					}
				}else{
						console.error("data is null");
				}
			},	 
			onSubmit:function(){  
				 if($(this).form('validate')){
					var editor=UE.getEditor('content');
						editor.sync();       //同步内容
					}
				return $(this).form('validate');  
           }, 
			
				/*	*/
		}); 
	  }
	}); 
} 
 
//----------------------------------颜色选择器-----------------------------------
$("#infoMsgWindow").find('#infoAddFrom').find("#titleColor").jPicker({
	title:'颜色选择',
	localization:{
    text:{
      title: '颜色选择',
      newColor: '选择颜色',
      currentColor: '默认颜色',
      ok: '确定',
      cancel: '取消'
    },
	tooltips:{
      colors:
      {
        newColor: '当前选中颜色 点击确定确认选择',
        currentColor: '默认颜色'
      },
      buttons:
      {
        ok: '确认选择',
        cancel: '取消选择'
      },
	},
	}
});
//---------------------------------------------------------------------------
function selectChannel(entityId,entityName){
		 var url="${ctx}/cms/channel/getChannel.do?paramMap[siteId]=${cmsInfo.siteId}&paramMap[dialogId]=selectChannelWindow&paramMap[contentId]="+getCurrentTabId()+
	 "&paramMap[entityId]="+entityId+"&paramMap[entityName]="+entityName;
	 easyuiUtils.openWindow('selectChannelWindow','选择栏目',600,450,url,true); 
}



function selectTemplet(entityId,entityName){
	 var url="${ctx}/cms/templet/getTempletList.do?paramMap[siteId]=${cmsInfo.siteId}&paramMap[dialogId]=selectTempletWindow&paramMap[contentId]="+getCurrentTabId()+
	 "&paramMap[entityId]="+entityId+"&paramMap[entityName]="+entityName;
	 easyuiUtils.openWindow('selectTempletWindow','选择模版',600,450,url,true); 
	
	}
 $("#infoMsgWindow").find('#infoAddFrom').find("input[name='cmsInfo\\.isTop']").click(function(){
	 if(jQuery(this).val()=="01"){
 			$("#infoMsgWindow").find('#infoAddFrom').find("#topEndTime").show();
	 }else{
 			$("#infoMsgWindow").find('#infoAddFrom').find("#topEndTime").hide();
	 }
 });
 
 if("${cmsInfo.isTop}"=="01"){
 	$("#infoMsgWindow").find('#infoAddFrom').find("#topEndTime").show();
 }
 
 
 
 function returnList(){
	 closeWindow('infoMsgWindow');
 }
 
 
 function uploadImg(uuid,attachmentName,attachment,type,title){
		var attachParam= $("#infoMsgWindow").find('#infoAddFrom').find('#attachmentParam_img_'+uuid).val();
		 uploadFile(attachParam,attachmentName,attachment,type,title);
 }
	 
 if('${cmsInfo.img}'==''){
	 $("#infoMsgWindow").find('#infoAddFrom').find("#attachmentImgName").bind('DOMNodeInserted', function(e) {
		var elemt= "<a id=\"delElemtImg\" href=\"javascript:void(0)\"  onclick=\"delImgFile('attachmentImgName','attachmentImg','delElemtImg','${ctx}/common/attachment/deleteAttachment.do?attachment.id=')\" title=\"删除文件\">删除</a>";
		if($("#infoMsgWindow").find('#infoAddFrom').find('#delElemtImg').length==0){
			$("#infoMsgWindow").find('#infoAddFrom').find("#fileSpanAttachmentImg").append(elemt);
			$("#infoMsgWindow").find('#infoAddFrom').find('#delElemtImg').linkbutton({});  
		}
	 })
 }
	 
 function delImgFile(html,elemt,showElemt,urlValue){
		  $.messager.confirm('提示',"确认删除当前文件?",function(result){    
					if (result){  
			  var js= $("#infoMsgWindow").find('#infoAddFrom').find("#"+elemt).val();
				 if(js){
					var vo= jQuery.parseJSON(js);
					if(vo){
						urlValue=urlValue+vo.attachmentId;
					 }else{
						 console.error("vo is null");
					 }
				 }else{
					 console.error("js is null");
				 }
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
						 $("#infoMsgWindow").find('#infoAddFrom').find("#"+html).html("");
						 $("#infoMsgWindow").find('#infoAddFrom').find("#"+elemt).html("-1");
						 $("#infoMsgWindow").find('#infoAddFrom').find("#"+showElemt).remove();
					 }
				 }else{
					 console.error("data is null");
				 }
			 }else{
				 console.error("json is null");
			 }
			}
		 });
	 }

 
 var size=0+parseInt("${size}");
 var maxFileSize=parseInt("${maxSize}");
 function uploadAttchs(){
	 if($("#infoMsgWindow").find('#infoAddFrom').find("#fileDiv").find("div").size()>=maxFileSize){
		 jQuery.messager.alert("提示信息","只能上传不超过${maxSize}个附件",function(){});
	 	 return false;
     }
	 size++;
	 $("#infoMsgWindow").find('#infoAddFrom').find("#fileDiv").append('<div id="fileSpan'+(size)+'">'+
			  '<input  name="upload" value="选择" class="easyui-validatebox" type="file" />'+
			  '<a onclick="javascript:delFileSpan(\'\',\''+(size)+'\')" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-times"></i> 删除</a>'+
			  '</div>');
 }


 function delFileSpan(url,elemtId){
	jQuery.messager.confirm("提示信息","确认删除当前文件?",function(isReturn){
		if(isReturn){
			jQuery.messager.alert("提示",'操作成功',function(){});
			$("#infoMsgWindow").find('#infoAddFrom').find("#fileDiv").find("#fileSpan"+elemtId).remove();
			/* if(url!=""){
				var data=jQuery.ajaxSubmitValue(url);
				var json=jQuery.parseJSON(data);
				if(json){
					var message = json.message;
					var statusCode = json.statusCode;
					if(parseInt(statusCode)==300){
						jQuery.messager.alert("提示信息",message,function(){});
					}else if(parseInt(statusCode)==200){
						jQuery.messager.alert("提示信息",message,function(){});
						$("#infoMsgWindow").find('#infoAddFrom').find("#fileDiv").find("#fileSpan"+elemtId).remove();
					}
				}else{
					console.error("json is null");
				}
			}else{
				$("#infoMsgWindow").find('#infoAddFrom').find("#fileDiv").find("#fileSpan"+elemtId).remove();
			} */
		}
	});
 }
 function divide_course(){
		openWindow('divideCourseWindow','选择课程',800,500,'${ctx}/cms/info/chooseCourse.do',true);
	}
</script>
