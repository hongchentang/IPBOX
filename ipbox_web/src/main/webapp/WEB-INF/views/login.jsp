<%@page import="org.apache.shiro.subject.Subject"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<!DOCTYPE HTML>
<html style="width: 100%;height: 100%;">
<head>
  <title>${ipanthercore:getAppName()}</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta http-equiv="content-type" content="text/html;charset=UTF-8">
  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  <meta http-equiv="keywords" content="">
  <meta http-equiv="description" content="">
  <%@ include file="/themes/skin/ipr/inc.jsp"%>
  <style>
    /*    body{
          width: 100%;
          height: 100%;
          !*background-attachment: fixed;*!
          overflow: hidden;
          background: url(/themes/easyui/themes/skin-blue/images/ipbox_back.png) no-repeat;
          background-size: 100% 100%;
        }*/
    .ipbox-bg {
      width: 100%;
      min-width: 1200px;
      height: 100%;
      background: url(/themes/easyui/themes/skin-blue/images/ipbox_back.png) no-repeat;
      background-size: 100% 100%;
    }
  </style>
</head>
<!-- BEGIN BODY -->
<body id="loginBody" class="login-wrap" style="width: 100%;height: 100%;overflow: hidden;">
<div class="login-content ipbox-bg">
  <div style="padding-left:5%;padding-top:3%;"><img src="/themes/easyui/themes/skin-blue/images/ipbox-logo.png" style="width: 38%"></div>
  <div class="login-auto t-auto">
    <div class="login-mod" id="user_login_loginDialog">
      <div class="login-tit">登录Login</div>
      <form id="loginForm" class="login-form" action="${ctx}/login.do" method="post">
        <div class="login-alert">
          <c:if test="${not empty errorMsg}"> <span class="txt"> ${errorMsg}</span> </c:if>
        </div>
        <label class="login-input-block login-user-block"> <span class="t-bg"></span>
          <input type="text" class="login-input easyui-validatebox" data-options="required:true,missingMessage:'请输入账号'" placeholder="请输入账号..." name="userName" value=""/>
        </label>
        <label class="login-input-block login-password-block"> <span class="t-bg"></span>
          <input type="password" class="login-input easyui-validatebox" data-options="required:true,missingMessage:'请输入密码'" placeholder="请输入密码..." name="password" value=""/>
        </label>
        <div class="btn-block">
          <input type="hidden"  placeholder="验证码" name="captcha" value="1"/>
          <input type="submit" class="login-btn" onclick="return submitForm();" value="登录">
          <div style="margin-top: 5px;text-align: right;font-size: 12px;">
            <div style="float: right;text-decoration: underline;margin-left: 10px;"><a onclick="register()" href="javascript: void(0)"><strong style="color:red;">立即注册</strong></a></div>
            <div style="text-decoration: underline;"><a onclick="findPassword()" href="javascript: void(0)">忘记密码？</a></div>
          </div>
        </div>
      </form>
    </div>
  </div>
  <div style="text-align:center;">Copyright © 2017 广州恒成智道信息科技有限公司<br>版权所有ICP备16003990号-1</div>

</div>

<script type="text/javascript">
  $(document).ready(function(e) {
    //如果是加载在框架中，则重新刷新
    var loginId=$('body').attr('id');
    if(loginId!='loginBody'){
      window.location.href='${ctx}/login.do';
    }
  });
  function submitForm(){
    if($('#loginForm').form('validate')){
      return true;
    }
    else{
      return false;
    }
  }
  function clearForm(){
    $('#loginForm').form('clear');
  }

  function register() {
    easyuiUtils.openWindow('registerWin','注册',800,600,'${ctx}/site/register/goRegister.do',true);
  }

  function findPassword() {
    easyuiUtils.openWindow('findPasswordWin','找回密码',800,300,'${ctx}/site/register/goFindPassword.do',true);
  }
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>

