<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes')}" var="fileTypes"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize"/>
<c:set value="0" var="size"/>
<!-- 教师上传课程资源 -->
 <form id="saveCourseFileFrom" name="saveCourseFileFrom" action="${ctx}/space/archive/noskin/saveCourseFile.do" method="post" enctype="multipart/form-data">
 	<input type="hidden" name="paramMap[trainCourseId]" value="${searchParam.paramMap.trainCourseId }">
 	<div id="p" class="easyui-panel" title=""  style="width:0;height:0;">   
		    <table class="alter-table-v" cellspacing="0" cellpadding="0" border="0">
			  <tr>
				  <td width="30px"></td>
				   <td colspan="3"><font color="red">(注：一次只能上传不超过5个附件 ，允许上传的文件类型：${fileTypes} ，允许每个上传文件的大小：${fileMaxSize}KB)</font></td>
			  </tr>
			  <tr>
				  <td>附件</td>
				  <td colspan="3" id="fileDiv">
				  
				  </td>
			  </tr>
			  
			  <tr>
			  	 <td>操作</td>
			  		<td colspan="3">
				  		<div style="width: 70%;text-align: center;">
				  			<button type="button" onclick="uploadCourseFile()" style="float: center; margin-right: 20px" class="easyui-linkbutton">
				            	添加
				            </button>
				            <button type="button" onclick="saveCourseFiel('saveCourseFileFrom')" style="float: center; margin-right: 20px" class="easyui-linkbutton">
				            	保存
				            </button>
				 		 </div>
			 		 </td>
			  </tr>
		  </table>
	</div>
</form>

<script type="text/javascript">
tableVBg('.alter-table-v');
$(document).ready(function (){
});
var size=0+parseInt("${size}");
function uploadCourseFile(){
	 if(jQuery("#fileDiv").find("div").size()>=5){
		 jQuery.messager.alert("提示信息","只能上传不超过5个附件",function(){});
	 	 return false;
    }
	 size++;
	 jQuery("#fileDiv").append('<div id="fileSpan'+(size)+'">'+
			  '<input  name="upload" value="选择" class="easyui-validatebox" type="file" />'+
			  '<a onclick="javascript:delFileSpan(\'\',\''+(size)+'\')" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-times"></i> 删除</a>'+
			  '</div>');
}
function delFileSpan(url,elemtId){
	jQuery.messager.confirm("提示信息","确认删除当前文件?",function(isReturn){
		if(isReturn){
			if(url!=""){
				var data=jQuery.ajaxSubmitValue(url);
				var json=jQuery.parseJSON(data);
				if(json){
					var message = json.message;
					var statusCode = json.statusCode;
					if(parseInt(statusCode)==300){
						jQuery.messager.alert("提示信息",message,function(){});
					}else if(parseInt(statusCode)==200){
						jQuery.messager.alert("提示信息",message,function(){});
						jQuery("#fileDiv").find("#fileSpan"+elemtId).remove();
					}
				}else{
					console.error("json is null");
				}
			}else{
	 			jQuery("#fileDiv").find("#fileSpan"+elemtId).remove();
			}
		}
	});
 }
function saveCourseFiel(formId){
	if(jQuery("#fileDiv").find("div").size()<=0){
		$.messager.alert('提示','请添加需要上传的文件！');
		return false;
	}
	$.messager.confirm('提示', '是否确定保存上传的文件？', function(r){
		if(r) {
			jQuery('#'+formId).form('submit',{
				 onSubmit: function(){
					 return $('#'+formId).form('validate');
				},
			    success: function(data){
			    	console.log(data);
			    	
			    	var json=eval("("+data+")");
			    	console.log(json);
					if(json.statusCode==300){
						$.messager.alert('提示',json.message);
					}else if(json.statusCode==200){
						closeWindow('uploadWindow');
						$("#trainCourseTab").panel('refresh')
						$.messager.alert('提示',json.message);
					}
			    }
			}); 
		}
	});
}
</script>