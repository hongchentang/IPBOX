<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp" %>
<script src="${ctx}/js/jquery/plugins/validation/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/js/jquery/plugins/validation/localization/messages_cn.js" type="text/javascript"></script>
<style>
form.cmxform label.error,label.error {
	color: red;
	font-style: italic
}
</style>
<%
/**
文件上传通用组件，只支持单文件上传
配合/js/common/upload.js调用此页面
支持页面上多个不同的文件上传框
requestParam.displayName 上传后返回显示的文件名和下载链接
requestParam.displayValue 上传后返回页面上需要保存到数据库的内容(json)
*/
%>
<c:choose>
	<c:when test="${not empty message.decryptFail}">
	<script type="text/javascript">
		jQuery.messager.alert("提示信息","${message.decryptFail}",function(){});
	</script>
			<%-- <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>文件上传</h3>
			</div>
			<div class="modal-body">
				<div class="row-fluid">
					${message.decryptFail}
				</div>
			</div> --%>
	</c:when>
	<c:when test="${not empty message.validateFail}">
			<%-- <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>文件上传</h3>
			</div>
			<div class="modal-body">
				<div class="row-fluid">
					${message.validateFail}
				</div>
			</div> --%>
		<script type="text/javascript">
			jQuery.messager.alert("提示信息","${message.validateFail}",function(){});
		</script>
	</c:when>
	<c:when test="${not empty message.uploadFail}">
			<%-- <div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h3>文件上传</h3>
			</div>
			<div class="modal-body">
				<div class="row-fluid">
					${message.uploadFail}
				</div>
			</div> --%>
			<script type="text/javascript">
				jQuery.messager.alert("提示信息","${message.uploadFail}",function(){});
			</script>
	</c:when>
	<c:when test="${not empty sendParam}">
	   <span style="display: none;">
		<c:set var="downloadUrl" value="${ipanthercore:getProperty('attachment.download.url')}"/>
		${searchParam.paramMap.displayName}||
		<a href='${downloadUrl}?attachment.id=${sendParam.attachmentId}'>${sendParam.attachmentName}</a>||
		${searchParam.paramMap.displayValue}||
		{"attachmentId":"${sendParam.attachmentId}","attachmentName":"${sendParam.attachmentName}"}
		</span>
		<jsp:include page="${receiveParam.callbackUrl}?name=${searchParam.paramMap.displayName}&value=${searchParam.paramMap.displayValue}">
			<jsp:param name="attachmentId" value="${sendParam.attachmentId}"/>
			<jsp:param name="attachmentName" value="${sendParam.attachmentName}"/>
			<jsp:param name="status" value="${sendParam.status}"/>
			<jsp:param name="groupId" value="${sendParam.groupId}"/>
			<jsp:param name="fileId" value="${sendParam.fileId}"/>
			<jsp:param name="result" value="{'attachmentId':'${sendParam.attachmentId}','attachmentName':'${sendParam.attachmentName}'}"/>
		</jsp:include>
		
		
		<%--<script language="javascript">
		/*
			上传完毕，处理结果
		*/
		var result = "{\"attachmentId\":\"${sendParam.attachmentId}\",\"attachmentName\":\"${sendParam.attachmentName}\"}";
		$("#${requestParam.displayName[0]}").html("<a href='${downloadUrl}?attachment.id=${sendParam.attachmentId}'>${sendParam.attachmentName}</a>");
		$("#${requestParam.displayValue[0]}").val(result);
		bootbox.hideAll();
		</script>--%>
	</c:when>
	<c:otherwise>
                  <div class="modal-header">
                    <h4 class="modal-title">文件上传</h4>
                  </div>
                  <div class="modal-body">
				<div class="row-fluid">
					<div class="span12">
						<form id="uploadForm" method="post" 
						action="${pageContext.request.contextPath}/common/attachment/uploadAttachment.do" 
						enctype="multipart/form-data">
							<c:if test="${not empty receiveParam}">
								<input type="hidden" name="paramMap[fileTypes]" value="${receiveParam.fileTypes}"/>
								<input type="hidden" name="paramMap[fileMaxSize]" value="${receiveParam.fileMaxSize}"/>
								<input type="hidden" name="paramMap[fileDir]" value="${receiveParam.fileDir}"/>
								<input type="hidden" name="paramMap[realName]" value="${receiveParam.realName}"/>
								<input type="hidden" name="paramMap[callbackUrl]" value="${receiveParam.callbackUrl}"/>
								<input type="hidden" name="paramMap[billId]" value="${receiveParam.billId}"/>
								<input type="hidden" name="paramMap[displayValue]" value="${searchParam.paramMap.displayValue}"/>
								<input type="hidden" name="paramMap[displayName]" value="${searchParam.paramMap.displayName}"/>
								<c:if test="${not empty receiveParam.attachmentId}">
									<input type="hidden" name="paramMap[attachmentId]" value="${receiveParam.attachmentId}"/>
								</c:if>
							</c:if>
							<fieldset>
								<div class="control-group">
									<label class="control-label" for="upload">选择文件:</label>
									<div class="controls">
										<input name="upload" id="upload" type="file" required/>
										<p class="help-block"> 
											<strong>温馨提示：</strong>请您点击&quot;浏览&quot;,选择您需要上传的文件,然后点击&quot;确定&quot;完成您的文件上传! <br/>
											<c:if test="${not empty receiveParam}">
												<c:if test="${not empty receiveParam.fileTypes}">
													允许上传的文件类型：<c:out value="${receiveParam.fileTypes}" /><br/>
												</c:if>
												<c:if test="${not empty receiveParam.fileMaxSize}"> 
													允许上传文件的大小：<c:out value="${receiveParam.fileMaxSize}"/>KB 
												</c:if>
											</c:if>
										</p>
									</div>
								</div>
								<div class="form-actions" style="text-align: center;">
									<button type="button"  class="easyui-linkbutton" onclick="closeWindow('fileUpload');">取消 </button>
									<c:if test="${empty message.decryptFail and  empty sendParam}">
<!-- 										<button type="button" class="btn btn-primary"  onclick="fileUplaodFormSubmit()"> 确 定 </button> -->
										<button type="button"  class="easyui-linkbutton" onclick="fileUplaodFormSubmit();">确 定 </button>
									</c:if>
								</div>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
			<!--//按钮区域-->
			<form id="callbackForm" name="callbackForm" method="post" action="${receiveParam.callbackUrl}">
				<input type="hidden" name="paramMap[billId]" value="${receiveParam.billId}"/>
				<input type="hidden" id="paramMap[attachmentId]" name="attachmentId" value="${sendParam.attachmentId}"/>
				<input type="hidden" id="paramMap[attachmentName]" name="attachmentName" value="${sendParam.attachmentName}"/>
				<input type="hidden" name="paramMap[displayValue]" value="${searchParam.paramMap.displayValue}"/>
				<input type="hidden" name="paramMap[displayName]" value="${searchParam.paramMap.displayName}"/>
                <input type="hidden" id="status" name="status" value="${sendParam.status}"/>
                <input type="hidden" id="groupId" name="groupId" value="${sendParam.groupId}"/>
                <input type="hidden" id="fileId" name="fileId" value="${sendParam.fileId}"/>
                ${sendParam}
			</form>
			<div id="updateResult"></div>
		<script type="text/javascript">
		$(document).ready(function(){
			//var formOptions = { 
			//	target:        '#updateResult',   // target element(s) to be updated with server response 
				//beforeSubmit:  showRequest,  // pre-submit callback 
			//	success:       showResponse  // post-submit callback 
				// other available options: 
				//url:       url         // override for form's 'action' attribute 
				//type:      type        // 'get' or 'post', override for form's 'method' attribute 
				//dataType:  null        // 'xml', 'script', or 'json' (expected server response type) 
				//clearForm: true        // clear all form fields after successful submit 
				//resetForm: true        // reset the form after successful submit 
		 
				// $.ajax options can be used here too, for example: 
				//timeout:   3000 
			//}; 
			//$('#uploadForm').ajaxForm(formOptions);
		});
		
		function fileUplaodFormSubmit(){
			//var submit1Html=$.ajaxSubmit('uploadForm'); 
	 
				
					if (!jQuery('#fileUpload').find('#uploadForm').valid()) {
						return false;
					}else{
						var formOptions = { 
						target:        '#updateResult',   // target element(s) to be updated with server response 
						//beforeSubmit:  showRequest,  // pre-submit callback 
						success:       showResponse  // post-submit callback 
						// other available options: 
						//url:       url         // override for form's 'action' attribute 
						//type:      type        // 'get' or 'post', override for form's 'method' attribute 
						//dataType:  null        // 'xml', 'script', or 'json' (expected server response type) 
						//clearForm: true        // clear all form fields after successful submit 
						//resetForm: true        // reset the form after successful submit 
				 
						// $.ajax options can be used here too, for example: 
						//timeout:   3000 
						}; 
						jQuery('#fileUpload').find('#uploadForm').ajaxSubmit(formOptions);
					}
		}
 
		// pre-submit callback 
		function showRequest(formData, jqForm, options) { 
			// formData is an array; here we use $.param to convert it to a string to display it 
			// but the form plugin does this for you automatically when it submits the data 
			//var queryString = $.param(formData); 
		 
			// jqForm is a jQuery object encapsulating the form element.  To access the 
			// DOM element for the form do this: 
			// var formElement = jqForm[0]; 
		 
			//alert('About to submit: \n\n' + queryString); 
		 
			// here we could return false to prevent the form from being submitted; 
			// returning anything other than false will allow the form submit to continue 
			return true; 
		} 
		 
		// post-submit callback 
		function showResponse(responseText, statusText, xhr, $form) {
			
			//接收返回值，然后赋值给主页面
 		/* 	var resultArray=$.trim(responseText).split("||");
			$("#"+$.trim(resultArray[0])).html($.trim(resultArray[1]));
			$("#"+$.trim(resultArray[2])).val($.trim(resultArray[3])); 
			 bootbox.hideAll();  */
			 
		} 
		</script>
	</c:otherwise>
</c:choose>


