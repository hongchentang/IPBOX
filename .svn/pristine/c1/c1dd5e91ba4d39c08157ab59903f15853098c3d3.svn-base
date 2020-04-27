<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<script type="text/javascript" src="${ctx}/js/common/easyui-common.js"></script>
<style>
    *{margin: 0;padding: 0;}
    .emailviewIn-l-top, .emailviewIn-l-liTop,.emailviewIn-l-li2{padding-left:10px;}
    .emailviewIn{display: flex;}
    .emailviewIn-l{width: 72%;height: 100%;display: flex;flex-direction: column;}
    .emailviewIn-l-top{height: 65px;border-bottom: #ccc solid 2px;line-height: 65px;}
    .emailviewIn-l-bottom{height: 260px;display: flex;flex-direction: column;overflow: hidden;}
    .emailviewIn-l-liTop{height: 45px;border-bottom: #ccc dashed 1px;line-height: 45px;}
    .emailviewIn-l-li2{width: 100%;height: 30px;border-bottom: #ccc dashed 1px;line-height: 30px;vertical-align:middle}
    .emailviewIn-r{width: 28%;border-left: #ccc solid 2px;overflow: hidden}
    .emailview-button{display: flex;justify-content: center;margin: 0 10px;width: auto;height: 25px;margin-top: 8px}
    .emailviewIn-checkbox{display: inline-block;vertical-align: middle;margin-right: 5px}
</style>
<div id="emailview" style="" class="easyui-panel" data-options="region:'center'" >
    <div class="emailviewIn">
        <div class="emailviewIn-l" style="">
            <div class="emailviewIn-l-top" style="">
                <span style="font-weight: bold;font-size: 14px">收件人邮箱地址</span>
                <input class="easyui-textbox" style="margin-left: 10px;width: 250px;height: 25px" placeholder="如：xxxx@163.com" data-options="validType: 'email'" >

            </div>
            <ul class="emailviewIn-l-bottom" style="">
                <li class="emailviewIn-l-liTop" style="font-weight: bold;font-size: 14px">客户列表 <input type="text" placeholder="客户名查找"><button style="margin-left:10px;width:60px;color: #999" onclick="searchEmaill()">搜索</button></li>
                <li class="emailviewIn-l-li2">
                    <label><input class="emailviewIn-checkbox" type="checkbox"><span>123456</span></label>
                </li>
                <li class="emailviewIn-l-li2">
                    <label><input class="emailviewIn-checkbox" type="checkbox"><span>123456</span></label>
                </li>
                <li class="emailviewIn-l-li2">
                    <label><input class="emailviewIn-checkbox" type="checkbox"><span>123456</span></label>
                </li>
                <li class="emailviewIn-l-li2">
                    <label><input class="emailviewIn-checkbox" type="checkbox"><span>123456</span></label>
                </li>
                <li class="emailviewIn-l-li2">
                    <label><input class="emailviewIn-checkbox" type="checkbox"><span>123456</span></label>
                </li>
            </ul>
        </div>

        <ul class="emailviewIn-r" style="">
            <li style="font-weight: bold;font-size: 14px;padding-left: 10px;width: 100%;height: 35px;line-height: 35px;border-bottom: #ccc solid 2px">最近联系人:</li>
            <li class="emailviewIn-l-li2">
                <label><input class="emailviewIn-checkbox" type="checkbox"><span>123456</span></label>
            </li>
            <li class="emailviewIn-l-li2">
                <label><input class="emailviewIn-checkbox" type="checkbox"><span>123456</span></label>
            </li>
            <li class="emailviewIn-l-li2">
                <label><input class="emailviewIn-checkbox" type="checkbox"><span>123456</span></label>
            </li>
            <li class="emailviewIn-l-li2">
                <label><input class="emailviewIn-checkbox" type="checkbox"><span>123456</span></label>
            </li>
        </ul>
    </div>
</div>
<div class="emailview-button" style="text-align: center;">
    <input class="easyui-linkbutton" style="margin-right:10px;width: 100px" type="button" value="发送" id="=subBut" onclick="submitForm()" />
    <input class="easyui-linkbutton" style="margin-left:10px;width: 100px" type="reset"  value="取消" id="subClose" onclick="doClosePayable()" />
</div>

<script type="text/javascript">

    function submitForm(e) {
        $.messager.defaults={ok:"去完善",cancel:"不了，谢谢"};

        $.messager.confirm("操作提示", "邮件发送成功！您是否需要将xxxx@qq.com完善至客户列表中？", function(data) {
            if(data) {
               easyuiUtils.openWindow('offPerfectWindows','完善客户信息',400,300,
                    '${ctx}/intellectual/patent/cost/goPerfect.do',true);
            }else {
            }
        });


        <%--openWindow('emailViewWindows','转发邮件提醒',700,410,'${ctx}/intellectual/patent/cost/addsendEmailage.do?id=' + id + '',true);--%>
        /*$('#emailview').form('submit', {
            success: function (data) {
                if (data.code === 200) {
                } else {
                    $.messager.alert("错误","保存失败" + data.msg);
                }
            }
        });*/
    }
    // 点击取消关闭窗口
    function doClosePayable() {
        closeWindow("emailViewWindows");
    }
    // 邮箱地址搜索
    function searchEmaill() {
        alert(2);
    }
</script>

<%--发送后跳转--%>
<%--openWindow('emailViewWindows','转发邮件提醒',700,410,'${ctx}/intellectual/patent/cost/emailView.do?id=' + id + '',true);--%>
<%--<div id="addPatentCost" style="display: none" class="easyui-panel" data-options="region:'center'">--%>
        <%--<p style="width: 100%;height: 120px;font-size: 15px;font-weight: bold;">--%>
            <%--邮件发送成功！<br>--%>
            <%--您是否需要将 <span>xxxx</span>@ <span>qq</span>.com完善至客户列表中？--%>
        <%--</p>--%>
        <%--<div style="display: flex;justify-content: center;width: 100%;height: 30px">--%>
            <%--<button style="margin-right: 10px;" type="button" value="去完善" onclick="goPerfect()" class="easyui-linkbutton">去完善</button>--%>
            <%--<button style="margin-left: 10px" type="reset"  value="不了,谢谢!"  onclick="doCloseCostWin()" class="easyui-linkbutton">不了,谢谢!</button>--%>
        <%--</div>--%>
<%--</div>--%>



<%--&lt;%&ndash;去完善弹出&ndash;%&gt;--%>
<%--<style>--%>
    <%--.goPerfectIn{margin: 8px 0}--%>
<%--</style>--%>
<%--<form id="ff" method="post" style="padding: 0 20%;font-size: 14px;font-weight: bold">--%>
    <%--<div class="goPerfectIn" style="">--%>
        <%--<input style="width: 300px" class="easyui-textbox" name="name"  data-options="label:'客户姓名 : ',required:true">--%>
    <%--</div>--%>
    <%--<div class="goPerfectIn" style="">--%>
        <%--<input style="width: 300px" class="easyui-textbox" name="email"  data-options="label:'邮箱地址 : ',required:true,validType:'email'" placeholder="如：xx@163.com">--%>
    <%--</div>--%>
    <%--<div class="goPerfectIn" style="">--%>
        <%--<input style="width: 300px" class="easyui-textbox" name="subject"  data-options="label:'所属公司 : ',required:true">--%>
    <%--</div>--%>
    <%--<div class="goPerfectIn" style="">--%>
        <%--<input style="width: 300px;height: 25px" class="easyui-textbox" name="message" data-options="label:'联系电话 : ',multiline:true,required:true">--%>
    <%--</div>--%>
<%--</form>--%>
<%--<div style="text-align:center;padding:5px 0">--%>
    <%--<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()" style="width:80px;margin-right: 10px">保存</a>--%>
    <%--<a href="javascript:void(0)" class="easyui-linkbutton" onclick="doCloseCostWin()" style="width:80px;margin-left: 10px">取消</a>--%>
<%--</div>--%>
<%--<script>--%>
    <%--function submitForm(){--%>
        <%--$('#ff').form('submit');--%>
    <%--}--%>
<%--</script>--%>
