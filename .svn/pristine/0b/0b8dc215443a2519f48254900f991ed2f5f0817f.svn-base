<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes')}" var="fileTypes2"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize2"/>

<form id="userQuestion_saveFrom" name="userQuestion_saveFrom"  method="post" enctype="multipart/form-data">
	 <input type="hidden" name="id" value="${userQuestion.id }">
	 
	 <table class="alter-table-v" cellspacing="0" cellpadding="0" border="0">
          
          <tr>
          	  <td width="200px"><font color="red">*</font>姓名</td>
	          <td>
				   <input readonly="true" type="text" class="easyui-textbox"  data-options="required:true" id="name" name="name" value="${userQuestion.name}"/>
			  </td>
          </tr>
          
          <tr>
          	  <td width="200px"><font color="red">*</font>邮箱</td>
	          <td>
				   <input readonly="true" type="text" class="easyui-textbox"  data-options="required:true" id="email" name="email" value="${userQuestion.email}"/>
			  </td>
          </tr>
          
          <tr>
          	  <td width="200px"><font color="red">*</font>手机号码</td>
	          <td>
				   <input readonly="true" type="text" class="easyui-textbox"  data-options="required:true" id="phone" name="phone" value="${userQuestion.phone}"/>
			  </td>
          </tr>
          
          <tr>
            <td width="200px"><font color="red">*</font>提出问题</td>
            <td colspan="3">
            <textarea readonly="true" rows="5" cols="80" id="question" name="question">${userQuestion.question}</textarea>
			</td>
          </tr>
          
          <tr>
            <td >问题反馈</td>
            <td colspan="3">
            	<textarea class="easyui-textbox" id="answer" name="answer" data-options="required:true,multiline:true" style="width:100%;height:100px">${userQuestion.answer}</textarea>
			</td>
          </tr>
          
          
          
          
          <tr style="text-align: center;line-height: 20px">
            <td colspan="4">
            <div style="width: 100%;text-align: center;">
            <button type="button" onclick="javascript:submitForm('edit');" style="float: center; margin-right: 20px" class="easyui-linkbutton">
            	保存
            </button>
            <button type="button" onclick="javascript:submitForm('editAndSend');" style="float: center; margin-right: 20px" class="easyui-linkbutton">
            	保存并发送
            </button>
            </div>
            </td>
          </tr>
        </table>
</form>

<script type="text/javascript">
tableVBg('.alter-table-v');
$(document).ready(function (){
});


function submitForm(type){
	
	if(!$("#userQuestion_saveFrom").form('validate')){
		return false;
	}
	
	$.messager.confirm('确认','确认保存吗？',function(result){    
		if (result){  
			$('#userQuestion_saveFrom').form('submit',{
				url:"${ctx}/cms/userQuestion/editUserQuestion.do?type="+type,
				onSubmit: function(){
				},
				success: function(data){
					//转换为Json对象
					var data = eval("("+data+")");
					//console.log(data);
					if(data.flag){
						closeWindow("regUserQuestion");
						$.messager.alert('提示',data.msg);
						getCurrentTab().panel("refresh");
						//$('#${searchParam.paramMap.rePanel }').panel('refresh');
					}else{
						$.messager.alert('提示',data.msg);
					}
				}
			});
		}
	});
}
</script>
