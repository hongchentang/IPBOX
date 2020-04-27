<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp" %>
  <form id="changePasswordForm" name="changePasswordForm" class="form-horizontal" role="form" action="/common/user/changePasswordSave.do" method="post">
       <table align="center" style="line-height: 40px;width: 90%">
          <tr>
            <td height="10"></td>
            <td></td>
          </tr>
          <tr>
            <td style="text-align:right;">原密码：</td>
            <td>
            <input class="easyui-textbox" id="inputOldPsd" type="password" validType="eqOldPwd['#oldPassword']" data-options="required:true" style="width: 200px"/>
            <input id="oldPassword" type="hidden" value="${user.passwordPlain}"/>
            </td>
          </tr>
          <tr>
            <td style="text-align:right;">新密码：</td>
            <td>
	            <input id="passwordPlain" class="easyui-textbox" data-options="required:true" type="password" style="width: 200px" name="user.passwordPlain" validType="minLength[6]"/>
            </td>
          </tr>
         
          <tr>
            <td style="text-align:right;">密码确认：</td>
            <td><input class="easyui-textbox" type="password" style="width: 200px" data-options="required:true" validType="eqPwd['#passwordPlain']"/></td>
          </tr>
          <tr><td colspan="2" align="center">
		          <input name="user.id" type="hidden" value="${user.id }">
		          <input name="user.roleCode" type="hidden" value="${user.roleCode}">
				   <button class="easyui-linkbutton" type="button" onclick="javascript:editUserInfo('changePasswordForm');">
				       <i class="fa fa-save"></i>保存
				   </button>
				   <button class="easyui-linkbutton" type="button" onclick="javascript:closeSelf()">
				       <i class="fa fa-times"></i>关闭
				   </button>
          </td></tr>
          </table>
  </form>
<script language="javascript">

function closeSelf(){
$('#editUserInfoWindow').window('close');
}

function editUserInfo(formId){

    //新密码不能和旧密码一样咯
    var inputOldPsd = $('#inputOldPsd').textbox('getValue');
    var passwordPlain= $('#passwordPlain').textbox('getValue');
    if(inputOldPsd == passwordPlain){
        $.messager.alert("错误","新密码和原密码不能一样!");
        return;
    }

	$('#'+formId).form('submit', {    
	    onSubmit: function(){    
	    	return $('#'+formId).form('validate'); 
	    },    
	    success:function(data){
	        var json=jQuery.parseJSON(data);
	    	var message = json.message;
	    	$.messager.alert('提示',message,'info');
	    	$('#editUserInfoWindow').window('close');
	    }    
	});  

}
</script>