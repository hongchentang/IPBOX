<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>

<div id="findPassword" style="padding-top: 10px;padding-left:0; margin-top: 3%;" class="easyui-panle" data-options="region:'center',title:''">
	<form name="findPasswordForm" method="post" id="findPasswordForm" action="${ctx}/site/getPassword.do" enctype="multipart/form-data">
		<table data-options="" class="table"
			   style="font-size: 12px; margin-left: 10%; margin-right: 10%; border: none;">
			<tr>
				<td style="" class="table_td_left">
					<div style="margin-bottom: 10px;">
						*电子信箱：
					</div>
				</td>
				<td style="" class="table_td_right">
					<div style="margin-bottom: 10px;">
						<input type="text" class="easyui-textbox" id="email" name="email" data-options="width: 200, height: 30, validType:'email'" />
					</div>
				</td>
				<td style="" class="table_td_thr">
                    <span style="color: darkgrey;">
                        请输入有效email
                    </span>
				</td>
			</tr>

			<tr>
				<td style="" class="table_td_left">
					<div style="margin-bottom: 10px;">
						*验证码：
					</div>
				</td>
				<td style="" class="table_td_right">
					<div style="margin-bottom: 10px; display: inline-flex; ">
						<input type="text" class="easyui-textbox" id="checkCode" name="checkCode" data-options="width: 200, height: 30, required:true,missingMessage: ''" />
						<img style="height: 25px; margin-left: 10px; margin-top: 8px;" id="checkCodeImg" src="${ctx}/common/captcha/generateImage.do" onClick="changeCheckCode()" >
					</div>
				</td>
				<td style="" class="table_td_thr">
                    <span style="color: darkgrey;">
                        <a href="javascript: void(0)" onClick="changeCheckCode()" >换一个</a>
                    </span>
				</td>
			</tr>
		</table>
	</form>
</div>

<div class="btn_div" style="">
	<div class="btn_register" style="" onclick="getPassword()">
		<div class="btn_font" style=""> 找回密码 </div>
	</div>
	<div class="btn_close" style="" onclick="closeWindow('findPasswordWin')">
		<div class="btn_font" style="">
			取消
		</div>
	</div>
</div>

<script type="text/javascript">

//更新验证码
function changeCheckCode() {
	jQuery("#checkCodeImg").attr("src","${ctx}/common/captcha/generateImage.do?date=" + new Date().getTime());
}

function getPassword() {

	var email = $('#email').textbox('getValue');
	if(email == null || email == ''){
		$.messager.alert('邮箱错误', '请填写邮箱!');
		return;
	}
	var chekcCode = $('#checkCode').textbox('getValue');
	if(chekcCode == null || chekcCode == ''){
		$.messager.alert('验证码错误', '请填写验证码!');
		return;
	}

	$.messager.confirm('提示', '是否确定发送邮箱？', function(r){
		if(r) {
			$("#findPasswordForm").ajaxSubmit({
				type: 'post',  
				success: function(json){
					if(!$.isEmptyObject(json)){
						var responseMsg = json.message;
						var responseCode = json.statusCode;
						changeCheckCode();
						if("200"==responseCode) {
							//成功
							jQuery.messager.alert("提示信息","密码已经发送到您的邮箱。","info",function() {
								closeWindow('findPasswordWin');
							});
						} else {
							//错误
							jQuery.messager.alert("提示信息",responseMsg,"error");
						}
					}
				}
			});
		}
		
	});
}
</script>

<style type="text/css">

	.table_td_left{
		width: 15%;
		text-align: right;
		border: none;
	}

	.table_td_right{
		width: 30%;
		text-align: left;
		border: none;
	}

	.table_td_thr{
		width: 30%;
		border: none;
		text-align: left;
	}

	.btn_div{
		text-align: center;
		margin-top: 20px;
		display: inline-flex;
		padding-left: 30%;
	}

	.btn_register{
		width: 150px;
		height: 30px;
		border: 1px solid #cccc;
		font-size: 15px;
		border-radius: 9px;
		cursor: pointer;
	}

	.btn_close{
		margin-left: 70px;
		width: 150px;
		height: 30px;
		border: 1px solid #cccc;
		font-size: 15px;
		border-radius: 9px;
		cursor: pointer;
	}

	.btn_font{
		padding-top: 5px;
		height: 30px;
		color: grey;
	}

	.btn_register:hover{
		background-color: #0c7ec0;
	}

	.btn_close:hover{
		background-color: #0c7ec0;
	}

	.btn_font:hover{
		color: #fff0e7;
	}

	.checkCode{
		align: center;
		background: #94bbd4;
		width: 156px;
		height: 30px;
		top: 150px;
		border: 0px;
		cursor: pointer;
		color: #fff;
	}
</style>