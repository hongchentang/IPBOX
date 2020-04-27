<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
       <form id="linkAddFrom"  action="${ctx}/cms/link/${StringUtils:isNotEmpty(cmsLink.id)?'editLink':'saveLink'}.do " method="post">
            <input type="hidden" value="${cmsLink.siteId}" name="cmsLink.siteId"/>
          	<table border="0" cellspacing="1" cellpadding="1"  class="alter-table-v">
				<tbody>
                    <tr>
						 <td >
				          	<label>链接名称</label>  
				        </td>
				         <td> 
                       		<input name="cmsLink.name"  type="text" class="easyui-textbox" data-options="required:true" value="${cmsLink.name}"/>
                        </td>
                    </tr>
                    <tr>
						 <td >
				          	<label>链接url</label>  
				        </td>
				         <td> 
                       		<input name="cmsLink.url"  type="text" class="easyui-textbox" data-options="required:true" value="${cmsLink.url}"/>
                        </td>
                    </tr>
                    <tr>
						 <td >
				          	<label>链接类型</label>  
				        </td>
				         <td> 
				         <select name="cmsLink.type" style="width: 155px;" data-options="panelWidth:'150px',panelHeight:'auto',required:true"  class="easyui-combobox" >
							<option value="">--请选择--</option>
							${dict:getEntryOptionsSelected('LINK_TYPE',cmsLink.type)}
				         </select>
                        </td>
                    </tr>
                    <tr>
						 <td >
				          	<label>是否有效</label>  
				        </td>
				         <td> 
				         <input type="radio" value="01" name="cmsLink.isok" ${cmsLink.isok eq '01' or cmsLink.isok==null?'checked="checked"':''} /> 是
				         <input type="radio" value="02" name="cmsLink.isok" ${cmsLink.isok eq '02'?'checked="checked"':''}/> 否
                        </td>
                    </tr>
                    <tr>
						 <td >
				          	<label>排序号</label>  
				        </td>
				        <td> 
				         <input type="text" value="${cmsLink.orderNum}" name="cmsLink.orderNum" class="easyui-numberbox"  data-options="min:0,precision:0"/>
                        </td>
                    </tr>
                     <tr>
						 <td >
				          	<label>图片</label>  
				        </td>
				        <td> 
				        	<textarea id="attachment" rows="" cols="" name="cmsLink.img" style="display: none;">${cmsLink.img}</textarea>
                        
                        <c:if test="${StringUtils:isNotEmpty(cmsLink.id)}">
							<c:set var="uuid" value="${cmsLink.id}"/>
						</c:if>
						<c:if test="${StringUtils:isEmpty(cmsLink.id)}">
							<c:set var="uuid" value="${ipanthercore:uuid()}"/>
						</c:if>
						<input name="cmsLink.id"  type="hidden" value="${uuid}"/>
					 	<a  class="easyui-linkbutton"  onclick="uploadLinkImg('${uuid}','attachmentName','attachment','img')"><span>上传</span></a>
						<input type="hidden" id="attachmentParam_${uuid}" value="${ipanthercommon:getAttachmentParamImage('',uuid,'/WEB-INF/views/common/attachment/callbackImg.jsp','cms/cmsLink/')}">
					 	<c:if test="${StringUtils:isNotEmpty(cmsSite.img)}"> 
							      <c:set value="${ipanthercore:getJSONMap(cmsSite.img)}" var="map" />
								      <div id="fileSpanAttachment">
								  		<span id="attachmentName" style="margin-top: 8px;"><img alt="" width="50px;" height="50px;" src="${ctx}${map.downloadUrl}" id="attachmentName"/></span> 
									  	<a href="javascript:void(0)" id="delElemt" class="easyui-linkbutton" onclick="delFile('attachmentName','attachment','delElemt','${ctx}/common/attachment/deleteAttachment.do?attachment.id=')" title="删除文件">删除</a>
									  </div>
									  
						</c:if>
						<c:if test="${StringUtils:isEmpty(cmsLink.img)}"> 
						      <div id="fileSpanAttachment">
						  		<span id="attachmentName"></span> 
							  </div>
						</c:if>
						<br/>
                        </td>
                    </tr>
                    <tr>
						 <td >
				          	<label>链接标识</label>  
				        </td>
				         <td> 
				         <input type="text" value="${cmsLink.pageMark}" name="cmsLink.pageMark" class="easyui-textbox"  />
                        </td>
                    </tr>
		     	</tbody>
		    </table>
      </form>
       <div style="text-align:center;padding:5px;">
      	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormLink()">保存</a>
       	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.closeWindow('linkWindow')">取消</a>
       </div>
<script type="text/javascript">

 tableVBg('.alter-table-v');

 function submitFormLink(){
	jQuery.messager.confirm("提示信息","确定${not empty cmsLink.id?'编辑':'添加'}信息?",function(isReturn){
	   if(isReturn){
		   jQuery('#linkWindow').find('#linkAddFrom').form('submit', { 
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
							easyuiUtils.closeWindow("linkWindow");
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
				 }
				return $(this).form('validate');  
           }, 
			
				/*	*/
		}); 
	  }
	}); 
} 
 
 

 function delFile(html,elemt,showElemt,urlValue){
  $.messager.confirm('提示',"确认删除当前文件?",function(result){    
			if (result){  
	  var js= jQuery('#linkWindow').find('#linkAddFrom').find("#"+elemt).val();
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
				 jQuery('#linkWindow').find('#linkAddFrom').find("#"+html).html("");
				 jQuery('#linkWindow').find('#linkAddFrom').find("#"+elemt).html("-1");
				 jQuery('#linkWindow').find('#linkAddFrom').find("#"+showElemt).remove();
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
 
 function uploadLinkImg(uuid,attachmentName,attachment,type){
		var attachParam= jQuery('#linkWindow').find('#linkAddFrom').find('#attachmentParam_'+uuid).val();
		 uploadFile(attachParam,attachmentName,attachment,type,'图片上传');
 }
	 
 jQuery('#linkWindow').find('#linkAddFrom').find("#attachmentName").bind('DOMNodeInserted', function(e) {
	if(jQuery('#linkWindow').find('#linkAddFrom').find('#delElemt').length==0){
	 	var elemt= "<a id=\"delElemt\" href=\"javascript:void(0)\"  onclick=\"delFile('attachmentName','attachment','delElemt','${ctx}/common/attachment/deleteAttachment.do?attachment.id=')\" title=\"删除文件\">删除</a>";
		jQuery('#linkWindow').find('#linkAddFrom').find("#fileSpanAttachment").append(elemt);
		jQuery('#linkWindow').find('#linkAddFrom').find('#delElemt').linkbutton({});  
	}
 }); 
 
</script>
