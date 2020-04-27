<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<%@ include file="/themes/skin/ipr/inc.jsp" %>
<style type="text/css">
body{
	background-color: white;
}
.td_right{
width: 30%;
text-align: right
}
.td_left{
width:70%;
text-align: left
}
.tip{
font-size: 12px;
color:#666666
}
</style>
<!--start #g-bd 中间 -->
<div id="g-bd">
	<div >
		<div class="g-page-wrap">
			<div class="g-page-box">
				<div class="m-tt2">
					<div class="m-crm">
						<span class="c-txt">您的位置：</span>
						<a href="${ctx}/site${globalRegions}/index.do">首页</a>
						<span class="line">></span>
						<span class="z-crt">
						找回密码
						</span>
					</div>
				</div><!--end .m-tt2-->	
				<div class="m-page-dt">
					<form id="findPasswordFrom" name="findPasswordFrom" action="${ctx}/getPassword.do" method="post">
						<div style="width:95%;text-align: center;margin: 0 auto;">
								 <table style="line-height: 40px;width: 100%">
							          <tr>
							            <td class="td_right"><span style="color: red">*</span>电子邮箱:</td>
							            <td class="td_left">
							            	<input type="text" class="easyui-textbox" name="user.email" required data-options="validType:'email'" style="width: 200px"/>
							            	<span class="tip">请输入有效Email</span>
							            </td>
							          </tr>
							        
							          <tr >
							            <td class="td_right">
							            	<span style="color: red">*</span>验证码:
							            </td>
							            <td class="td_left">
							            	<input type="text" class="easyui-textbox" name="checkCode" id="checkCode" required/>
							            	<a style="font-size: 12px;text-decoration: underline;" href="javascript:void(0)" onClick="changeCheckCode()">换一个</a>
							            </td>
							          </tr>
							          
							          <tr >
							            <td class="td_right">
							            	
							            </td>
							            <td class="td_left">
							            	<img id="checkCodeImg" src="${ctx}/common/captcha/generateImage.do" onClick="changeCheckCode()">
							            </td>
							          </tr>
							     </table>
							</div>
							<div class="m-btn-block m-btn-big">
								<button class="u-confirm-btn u-btn" type="button" onclick="regist()" style="height: 30px;line-height: 20px">发送邮箱</button>
								<button class="u-cancel-btn u-btn" type="button" onclick="window.location.href='${ctx}/login.do'" style="height: 30px;line-height: 20px">返回</button>
							</div>
						</form>
				</div><!--end .m-page-dt-->
			</div><!--end .g-page-box -->
		</div>
	</div>
</div>
<!--end #g-bd 中间 -->
<script type="text/javascript">
$(document).ready(function(){
	$(".g-nvc").children().removeClass("z-crt");//移除菜单选中效果
});

//更新验证码
function changeCheckCode() {
	jQuery("#checkCodeImg").attr("src","${ctx}/common/captcha/generateImage.do?date="+new Date().getTime());
}

function regist() {
	if(!$('#findPasswordFrom').form('validate')) {
		return false;
	}
	$.messager.confirm('提示', '是否确定发送邮箱？', function(r){
		if(r) {
			$("#findPasswordFrom").ajaxSubmit({ 
				type: 'post',  
				success: function(json){
					if(!$.isEmptyObject(json)){
						var responseMsg = json.message;
						var responseCode = json.statusCode;
						changeCheckCode();
						if("200"==responseCode) {//成功
							jQuery.messager.alert("提示信息","密码已经发送到您的邮箱。","info",function() {
								window.location.href="${ctx}/site${globalRegions}/index.do";
							});
						} else {//错误
							jQuery.messager.alert("提示信息",responseMsg,"error");
						}
					}
				}
			});
		}
		
	});
}
</script>