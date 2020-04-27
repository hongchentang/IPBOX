<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/include/taglib.jsp" %>

<div class="mod">
	<div class="tit clearfix">
		<h3>修改密码</h3>
	</div>
</div>
<!-- start .changePassword 修改密码内容开始 -->
				<div class="changePassword">
					<!-- start .password 修改密码模块开始 -->
					<div>
						<div class="con">
							<form id="changePasswordForm" method="post" action="${ctx}/common/user/changePasswordSave.do">
								<input type="hidden" name="user.id" value="${user.id}"/>
							<div class="user-data">
								<div class="user-line clearfix">
									<div class="user-block po-relative">
										<div class="left po-left"><span>原密码</span></div>
										<div class="right">
											<input class="easyui-textbox" type="password" validType="eqOldPwd['#_oldPassword']"  name="oldPassword" id="oldPassword" required value="" />
											<input id="_oldPassword" type="hidden" value="${user.passwordPlain}"/>
										</div>
									</div>
								</div><!-- end user-line -->
								<div class="user-line clearfix">
									<div class="user-block po-relative">
										<div class="left po-left"><span>新密码</span></div>
										<div class="right">
											<input class="easyui-textbox" type="password" name="user.passwordPlain" id="passwordPlain" required value="" validType="minLength[6]"/>
										</div>
									</div>
								</div><!-- end user-line -->
								<div class="user-line clearfix">
									<div class="user-block po-relative">
										<div class="left po-left"><span>确认密码</span></div>
										<div class="right">
											<input class="easyui-textbox" type='password' name="rePassword" validType="eqPwd['#passwordPlain']" id="rePassword"  required value="" />
										</div>
									</div>
								</div><!-- end user-line -->
								<div class="user-line clearfix">
									<div class="user-block po-relative">
										<div class="left po-left"><span></span></div>
										<div class="right">
											<div class="pass-btn">
												<input type="button" value="保存" name="" onclick="return validata('changePasswordForm');" class="save-btn btn" />
											</div>
										</div>
									</div>
								</div><!-- end user-line -->		
							</div>
							</form>
						</div>
					</div>
					<!-- end .password 修改密码结束 -->
				</div>
				<!-- end .changePassword 修改密码内容结束 -->
        
<script type="text/javascript">

$(document).ready(function(){
});

function validata(validForm){
	jQuery('#changePasswordForm').form('submit',{
		onSubmit: function(){
			 return $('#changePasswordForm').form('validate');
		},
	    success: function(data){
	    	var json=jQuery.parseJSON(data);
			if(!jQuery.isEmptyObject(json)){
				var message = json.message;
				var statusCode = json.statusCode;
				console.log(statusCode);
				if(parseInt(statusCode)==300){
					jQuery.messager.alert("提示信息",message,"error");
				} else if(parseInt(statusCode)==200){
					jQuery.messager.alert("提示信息","密码修改成功","info",function() {
						window.location.href='${ctx}/logout.do';
					});
				}
			}
	    }
	});
}
</script>