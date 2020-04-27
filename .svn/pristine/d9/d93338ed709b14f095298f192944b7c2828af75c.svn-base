<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/include/taglib.jsp" %>
<style>
    @media screen and (max-width: 1012px) {
        .u-infor{
            display: none;
        }
    }
</style>
<div id="header-wrap">
    <div class="hd-wrap-l">
        <div id="logoDiv">

        </div>
    </div>

    <%-- 	<shiro:user>
                <div class="u-infor" >
                    <span class="u-txt">
                        <strong>当前用户</strong>：<shiro:principal property="realName"/>
                    </span>
                    <span class="u-line">/</span>
                    <span class="u-txt">
                        <strong>机构</strong>：
                        ${sessionScope.loginUser.deptName}
                    </span>
                    <span class="u-line">/</span>
                    <span class="u-txt">
                        <fmt:formatDate value="${now}" type="both" dateStyle="default" pattern="yyyy年MM月dd日 " />
                    </span>
                </div>
        </shiro:user> --%>

    <div id="menu-wrap" style="float:right">
        <div id="menu-wrap">
            <shiro:user>
                <div class="u-infor" style="margin-left: -500px;">
		    	<span class="u-txt">
		    		<strong>当前用户</strong>：<shiro:principal property="realName"/>
		    	</span>
                    <span class="u-line">/</span>
                    <span class="u-txt">
		    		<%--<strong>机构</strong>：--%>
	                ${sessionScope.loginUser.deptName}        
		    	</span>
                    <span class="u-line">/</span>
                    <span class="u-txt">
		    		<fmt:formatDate value="${now}" type="both" dateStyle="default" pattern="yyyy年MM月dd日 "/>
		    	</span>
                </div>
            </shiro:user>
            <shiro:user>

       <%--          <a href="${ctx}/site${globalRegions}/index.do" class="menu-base menu-quit">
                    <i class="menu-ico" title="返回门户首页"></i>
                </a> --%>
                <a href="#" class="menu-base m-control-panel" onclick="">
                    <i class="menu-tab10 menu-ico"></i>
                    <div class="menu-option">
    			<span href="#" class="menu-option-line menu-option-tab1" onClick="changeUserInfoV2()">
    				<span class="t-ico"></span>
    				<span class="t-txt">个人信息</span>
    			</span>
                <span href="#" class="menu-option-line menu-option-tab2" onClick="changePassword()">
    				<span class="t-ico"></span>
    				<span class="t-txt">修改密码</span>
    			</span>
                <%--<span href="#" class="menu-option-line menu-option-tab2" onClick="bindWeChat()">
    				<span class="t-ico"></span>
    				<span class="t-txt">微信绑定审核</span>
    			</span>--%>

                    </div>
                </a>
                <a href="#" class="menu-base menu-quit" onClick="logoutFun()">
                    <i class="menu-tab5 menu-ico" title="退出"></i>
                </a>
            </shiro:user>
        </div>


    </div>
</div>
<!--end #header-wrap -->
<script type="text/javascript" charset="utf-8">
    $(document).ready(function () {
        headerSize();
    });
    $(window).resize(function () {
        headerSize();
    });
    <!-- 防止按退格键返回backspace  CtrlN,CtrlR  -->
    document.onkeydown = check;

    function check(e) {
        var code;
        if (!e) var e = window.event;
        if (e.keyCode) code = e.keyCode;
        else if (e.which) code = e.which;

        if (((event.keyCode == 8) &&                                                    //BackSpace
            ((event.srcElement.type != "text" &&
                event.srcElement.type != "textarea" &&
                event.srcElement.type != "password") ||
                event.srcElement.readOnly == true)) ||
            ((event.ctrlKey) && ((event.keyCode == 78) || (event.keyCode == 82))) ||    //CtrlN,CtrlR
            (event.keyCode == 116)) {                                                   //F5
            event.keyCode = 0;
            event.returnValue = false;
        }

        return true;
    }

    <!-- 防止按退格键返回END  -->


    function logoutFun() {
        window.location.href = '${ctx}/logout.do';
    }

    function lockScreenFun() {
        $('#sessionInfoDiv').html('');
        $('#user_login_loginDialog').dialog('open');
        $('#layout_east_onlineDatagrid').datagrid('load', {});
    }

    function changeUserRole() {
        //window.location.href="${ctx}/entrance.do";
    }

    function changeUserInfo() {
        //openWindow('editUserInfoWindow', '个人信息修改', 900, 200, '${ctx}/common/user/goChangeUserInfo.do', true);
    }
    function changeUserInfoV2() {
        openWindow('editUserInfoWindow', '个人信息修改', 900, 280, '${ctx}/common/user/goChangeUserInfo.do', true);
        console.log('changV2');
    }


    function changePassword() {
        openWindow('editUserInfoWindow', '密码修改', 450, 280, '${ctx }/common/user/goChangePassword.do', true);
    }

    function selectTopMenu(btn) {
        $('#menu-wrap > a').each(function (index) {
            $(this).removeClass('current');
        });
        $(btn).addClass('current');
    }

    function headerSize() {
        var windowWidth = $(window).width();
        var $header = $('#header-wrap');
        if (windowWidth < 1280) {
            $header.addClass('size-small');
        } else {
            $header.removeClass('size-small');
        }
        //alert(typeof windowWidth);
    }

    function bindWeChat(){
        openWindow('bindWeChat', '微信绑定审核', 450, 280, '${ctx }/common/user/bindWeChat.do', true);
    }
</script>

