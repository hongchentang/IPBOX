<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes.image')}" var="fileTypes"/>
<div id="register" style="padding-top: 10px;padding-left:0;" class="easyui-panle" data-options="region:'center',title:''">
    <form name="registerForm" method="post" id="registerForm" action="${ctx}/site/unitRegister.do" enctype="multipart/form-data">
        <table data-options="" class="table"
               style="font-size: 12px; margin-left: 10%; margin-right: 10%; border: none;">
            <tr>
                <td style="" class="table_td_left">
                    <div style="margin-bottom: 10px;">
                        *账号名称：
                    </div>
                </td>
                <td style="" class="table_td_right">
                    <div style="margin-bottom: 10px;">
                        <input type="text" class="easyui-textbox" id="userName" name="userName" data-options="width: 200, height: 30, required:true" />
                    </div>
                </td>
                <td style="" class="table_td_thr">
                    <span style="color: darkgrey;">
                        注册后不可修改，英文字母、数字、下划线等组成，第一位必须为字母，6-16字符
                    </span>
                </td>
            </tr>

            <tr>
                <td style="" class="table_td_left">
                    <div style="margin-bottom: 10px;">
                        *真实姓名：
                    </div>
                </td>
                <td style="" class="table_td_right">
                    <div style="margin-bottom: 10px;">
                        <input type="text" class="easyui-textbox" id="realName" name="realName" data-options="width: 200, height: 30, required:true" />
                    </div>
                </td>
                <td style="" class="table_td_thr">
                    <span style="color: darkgrey;">
                    </span>
                </td>
            </tr>

            <tr>
                <td style="" class="table_td_left">
                    <div style="margin-bottom: 10px;">
                        *设置密码：
                    </div>
                </td>
                <td style="" class="table_td_right">
                    <div style="margin-bottom: 10px;">
                        <input type="password" class="easyui-textbox" id="password" name="password" data-options="width: 200, height: 30, required:true" />
                    </div>
                </td>
                <td style="" class="table_td_thr">
                    <span style="color: darkgrey;">
                        登录时使用的密码
                    </span>
                </td>
            </tr>

            <tr>
                <td style="" class="table_td_left">
                    <div style="margin-bottom: 10px;">
                        *确认密码：
                    </div>
                </td>
                <td style="" class="table_td_right">
                    <div style="margin-bottom: 10px;">
                        <input type="password" class="easyui-textbox" id="confirmPassword" name="confirmPassword" validType="equalTo['password']" data-options="width: 200, height: 30, required:true" />
                    </div>
                </td>
                <td style="" class="table_td_thr">
                    <span style="color: darkgrey;">
                        请重复输入密码
                    </span>
                </td>
            </tr>

            <tr>
                <td style="" class="table_td_left">
                    <div style="margin-bottom: 10px;">
                        企业名称：
                    </div>
                </td>
                <td style="" class="table_td_right">
                    <div style="margin-bottom: 10px;">
                        <input type="text" class="easyui-textbox" id="unitName" name="unitName" data-options="width: 200, height: 30, required:true" />
                    </div>
                </td>
                <td style="" class="table_td_thr">
                    <span style="color: darkgrey;">

                    </span>
                </td>
            </tr>

            <tr>
                <td style="" class="table_td_left">
                    <div style="margin-bottom: 10px;">
                        企业地址：
                    </div>
                </td>
                <td style="" class="table_td_right">
                    <div style="margin-bottom: 10px;">
                        <input type="text" class="easyui-textbox" id="unitAddress" name="unitAddress" data-options="width: 200, height: 30" />
                    </div>
                </td>
                <td style="" class="table_td_thr"></td>
            </tr>

            <tr>
                <td style="" class="table_td_left">
                    <div style="margin-bottom: 10px;">
                        移动电话：
                    </div>
                </td>
                <td style="" class="table_td_right">
                    <div style="margin-bottom: 10px;">
                        <input type="text" class="easyui-textbox" id="phone" name="phone" validType="mobilePhone" data-options="width: 200, height: 30,required:true" />
                    </div>
                </td>
                <td style="" class="table_td_thr">
                    <span style="color: darkgrey;">
                        填入正确的手机号
                    </span>
                </td>
            </tr>

            <tr>
                <td style="" class="table_td_left">
                    <div style="margin-bottom: 10px;">
                        传真电话：
                    </div>
                </td>
                <td style="" class="table_td_right">
                    <div style="margin-bottom: 10px;">
                        <input type="text" class="easyui-textbox" id="unitPhone" name="unitPhone" data-options="width: 200, height: 30" />
                    </div>
                </td>
                <td style="" class="table_td_thr"></td>
            </tr>

            <tr>
                <td style="" class="table_td_left">
                    <div style="margin-bottom: 10px;">
                        *电子信箱：
                    </div>
                </td>
                <td style="" class="table_td_right">
                    <div style="margin-bottom: 10px;">
                        <input type="text" class="easyui-textbox" id="unitEmail" name="unitEmail" 
                        data-options="width: 200, height: 30, validType: 'email', required:true" />
                    </div>
                </td>
                <td style="" class="table_td_thr">
                    <span style="color: darkgrey;">
                        如xx@qq.com
                    </span>
                </td>
            </tr>

            <tr>
                <td style="" class="table_td_left">
                    <div style="margin-bottom: 10px;">
                        企业LOGO：
                    </div>
                </td>
                <td style="" class="table_td_right">
                    <div style="margin-bottom: 10px;">
                        <input type="file" id="logo" name="logo" />
                    </div>
                </td>
                <td style="" class="table_td_thr">
                     <span style="color: darkgrey;">
                         允许的文件类型：${fileTypes}
                     </span>
                </td>
            </tr>

            <tr>
                <td style="" class="table_td_left">
                    <div style="margin-bottom: 10px;">
                        统一社会信用代码：
                    </div>
                </td>
                <td style="" class="table_td_right">
                    <div style="margin-bottom: 10px;">
                        <input type="text" class="easyui-textbox" id="code" name="code" data-options="width: 200, height: 30" />
                    </div>
                </td>
                <td style="" class="table_td_thr">
                    <span style="color: darkgrey;">

                    </span>
                </td>
            </tr>

            <tr>
                <td style="" class="table_td_left">
                    <div style="margin-bottom: 10px;">
                        验证码：
                    </div>
                </td>
                <td style="" class="table_td_right">
                    <div style="margin-bottom: 10px; display: inline-flex;">
                        <input type="text" class="easyui-textbox" id="orgCheckCode" name="orgCheckCode" data-options="width: 200, height: 30, prompt: '验证码区分大小写'" />
                    </div>
                </td>
                <td style="" class="table_td_thr">
                    <input type="button" class="checkCode" id="btn" value="获取验证码" onclick="getMailCheckCode()" />
                </td>
            </tr>
        </table>

        <div class="btn_div" style="">
            <div class="btn_register" style="" onclick="submit()">
                <div class="btn_font" style=""> 立即注册 </div>
            </div>
            <div class="btn_close" style="" onclick="closeWindow('registerWin')">
                <div class="btn_font" style="">
                    取消
                </div>
            </div>
        </div>
    </form>
</div>
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
        padding-left: 31%;
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

<script>
    var countdown=60;
    var checkCode = '';
    function getMailCheckCode() {
        var email = $('#unitEmail').validatebox();
        var userName = $('#userName').val();
        var emailVal = email.val();
        if(email.validatebox('isValid')){
            if(emailVal != ''){
                $.ajax({
                    url:'${ctx}/site/register/getCheckCode.do?email=' + emailVal + '&userName=' + userName,
                    method:'GET',
                    dataType:'json',
                    success: function (data) {
                        if(data.code == 200){
                            $.messager.alert('成功', '邮件已发送到：' + emailVal);
                            checkCode = data.checkCode;
                            setTimeOut($('#btn'));
                        }else {
                            $.messager.alert('失败', data.msg);
                        }

                    },
                    error: function (data) {
                        $.messager.alert('错误', '发送失败!')
                    }
                });
            }else {
                $.messager.alert('错误', '请输入您的邮箱!')
            }
        }else {
            $.messager.alert('错误', '邮箱格式有误!');
        }
    }

    function setTimeOut(obj) {

        if (countdown == 0) {
            obj.removeAttr("disabled");
            obj.val("获取验证码");
            countdown = 60;
            return;
        } else {
            obj.attr("disabled", true);
            obj.val("重新发送(" + countdown + ")");
            countdown--;
            if(countdown == 0){
                setTimeout(function() {
                         }
                    ,1000*60*5)
            }
        }
        setTimeout(function() {
                setTimeOut(obj) }
            ,1000)
    }

    function submit() {

        //校验验证码
        var checkCodeInput = $('#orgCheckCode').textbox('getValue');
        if(checkCode == ''){
            $.messager.alert('错误', '还没有获取验证码！');
            return;
        }else if(checkCodeInput != checkCode){
            $.messager.alert('错误', '验证码错误！');
            return;
        }

        //校验用户名
        var  userName = $('#userName').textbox('getValue');
        checkUserRegex(userName);

        $('#registerForm').form('submit', {
            success: function (data) {
                $.messager.alert('成功', '用户注册成功!');
                closeWindow('registerWin');
            },
            error: function (data) {

            }
        })
    }

    $('#userName').textbox({
        onChange: function (newVal, oldVal) {
            checkUserRegex(newVal);
        }
    });
    
    function checkUserRegex(newVal) {
        if(newVal.length > 0 && newVal.length < 6){
            $('#userName').textbox('clear');
            $.messager.alert('用户名错误','请输入大于6个字符的标题');
        }else if(newVal.length > 16){
            $('#userName').textbox('clear');
            $.messager.alert('用户名错误','请输入小于于16个字符的标题');
        }else if (newVal.length > 0) {
            //只能输入应用数组和下划线
            var regex = /^[a-zA-Z]([-_a-zA-Z0-9]{6,16})$/;

            if(newVal.match(regex) == '' || newVal.match(regex) == null){
                $('#userName').textbox('clear');
                $.messager.alert('用户名错误','错误的用户名:' + newVal + ' ,请按规则输入');
            }
        }
    }
</script>