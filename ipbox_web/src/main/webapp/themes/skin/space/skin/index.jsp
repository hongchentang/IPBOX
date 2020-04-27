<%@page import="com.hcis.ipr.space.utils.SpaceUtils"%>
<%@page import="com.hcis.ipanther.common.user.entity.User"%>
<%@page import="com.hcis.ipanther.common.user.utils.UserUtils"%>
<%@page import="com.hcis.ipanther.common.login.vo.LoginUser"%>
<%@page import="com.hcis.ipanther.core.utils.AppConfig"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="page" uri="http://www.opensymphony.com/sitemesh/page" %>
<%@ include file="/jsp/common/include/taglib.jsp" %>
<%
	LoginUser loginUser=LoginUser.loginUser(request);
	if(null==loginUser){//未登录
		response.sendRedirect(request.getContextPath()+AppConfig.getProperty("app","space.loginUrl"));
		return;
	} else if(!UserUtils.isSpace(loginUser.getRoleCode())) {//如果用户不具备个人端的权限，则跳回首页
		//response.sendRedirect(request.getContextPath()+"/");
		out.print("<script>window.location.href='"+request.getContextPath()+"/';</script>");
		return;
	}
%>
<%--获取要被选中的菜单ID --%>
<c:set var="selectedMenuId" value="<%=SpaceUtils.getSelectedMenuId(request)%>"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8" />
<title>广东知识产权人才信息化系统|个人中心</title>
<link type="text/css" rel="stylesheet" href="${ctx}/themes/skin/space/css/set.css">
<link type="text/css" rel="stylesheet" href="${ctx}/themes/skin/space/css/function.css">
<link type="text/css" rel="stylesheet" href="${ctx}/themes/skin/space/css/user.css">
<link type="text/css" rel="stylesheet" href="${ctx}/js/My97DatePicker/skin/default/datepicker.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/themes/easyui/themes/icon.css"/>
<%-- font awesome --%>
<link rel="stylesheet" type="text/css" href="${ctx}/js/font-awesome/css/font-awesome.min.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/themes/easyui/themes/skin-orange/css/alter-easyUI.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/themes/easyui/themes/skin-orange/css/color.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/themes/easyui/themes/skin-orange/css/diy-style.css"/>

<script type="text/javascript" src="${ctx}/js/jquery/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="${ctx}/themes/easyui/jquery.easyui.min.js" charset="utf-8"></script>

<script type="text/javascript" src="${ctx}/themes/easyui/locale/easyui-lang-zh_CN.js" charset="utf-8"></script>
<%-- 自己定义的样式和JS扩展 --%>
<script type="text/javascript" src="${ctx}/js/common/ipanther-common.js"></script>
<script type="text/javascript" src="${ctx}/js/common/easyui-util.js"></script>
<script type="text/javascript" src="${ctx}/js/common/easyui-common.js"></script>
<script type="text/javascript" src="${ctx}/js/common/common.js"></script>
<script type="text/javascript" src="${ctx}/js/common/upload.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery/jquery.form.js"></script>

<script type="text/javascript" src="${ctx}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/themes/skin/space/js/function.js"></script>
<style type="text/css">
body,td,th {
	font-family: Arial, "微软雅黑";
}
</style>
</head>
<body style="background-color: white;">
		<div id="wrap" class="wrap">
		<!-- start #top 头部开始 -->
		<div id="top">
			<div class="top-Nav">
				<div class="top-auto auto clearfix">
					<h1><a href="${ctx}/">广东知识产权人才信息化系统</a></h1>
					<p class="this-page"><span class="triangle"><i>◆</i></span><a href="###">个人中心2</a></p>
				</div>
			</div>
		</div>
		<!-- end #top 头部结束 -->
		<!-- start #content 中间开始 -->
		<div id="content" class="auto clearfix">
			<!-- start .l-frame 左边框架开始 -->
			<div class="l-frame">
				<div class="left-nav">
					<div class="user clearfix">
						<div class="head-img">
							<a href="${ctx}/space/index.do">
							<c:choose>
							    <c:when test="${not empty sessionScope.loginUser.avatar}">
							    	<c:set value="${ipanthercore:getJSONMap(sessionScope.loginUser.avatar)}" var="map" />
									<img src="${ctx}${map.downloadUrl}" border="0" style="max-width:120px;max-height:160px;min-height:150px;max-width:120px;">
                                </c:when> 
                                <c:otherwise>
                                	<img src="${ctx}/themes/easyui/themes/tms/images/default.jpg">
                                </c:otherwise>
							</c:choose>
							</a>
						</div>
						<span style="font-weight:bolder;">
							${sessionScope.loginUser.realName }(<span style="color:#FF8400">${ipanthercommon:getRoleName(sessionScope.loginUser.roleCode)}</span>)
						</span>
						<div style="margin-top:5px;">
							${sessionScope.loginUser.deptName}
						</div>
						<div style="margin-top:5px;">
							${ipanthercommon:getRegionsNameFull(sessionScope.loginUser.regionsCode)}
						</div>
					</div>
					<%--
						注意：
						1，菜单的ID不能重复；
						2，菜单的ID与请求地址的最后一段相同，如/xxxx/yyy/zzz.do，则ID为zzz
						3，增加、修改菜单后，要相应地在common.properties的space.menu中更新
					 --%>
					<ul class="menu-list nav-list">
						<li><a id="index" href="${ctx}/space/index.do"><i class="home-ico ico"></i>我的主页</a></li>
					</ul>
					<c:if test="${isStudent}">
						<ul class="menu-list nav-list">
							<li><a id="listSurvey" href="${ctx}/space/survey/listSurvey.do"><i class="file-ico ico"></i>调查问卷</a></li>
							<li><a id="studentArchive" href="${ctx}/space/archive/studentArchive.do"><i class="work-ico ico"></i>学习档案</a></li>
						</ul>
					</c:if>
					<c:if test="${isTeacher}">
						<ul class="menu-list nav-list">
							<%--教师才有可以对抽查进行评估 --%>
							<li><a id="listCheck" href="${ctx}/space/check/listCheck.do"><i class="time-ico ico"></i>抽查评估</a></li>
							<li><a id="teacherArchive" href="${ctx}/space/archive/teacherArchive.do"><i class="work-ico ico"></i>授课档案</a></li>
						</ul>
					</c:if>
					<ul class="user-list nav-list">
						<li><a id="tabs" href="${ctx}/space/user/tabs.do"><i class="user-ico ico"></i>个人资料</a></li>
						<li><a id="changePassword" href="${ctx}/space/user/changePassword.do"><i class="alterPs-ico ico"></i>修改密码</a></li>
						
						<!-- 在线留言 -->
						<li><a id="frontlistUserQuestion" href="${ctx}/space/frontUserQuestion/frontlistUserQuestion.do"><i class="file-ico ico"></i>在线留言</a></li>
						
						<li><a href="${ctx}/site${globalRegions}/index.do"><i class="home-ico ico"></i>回到门户</a></li>
						<li><a id="logout" href="${ctx}/logout.do"><i class="exit-ico ico"></i>退出</a></li>
					</ul>
				</div>
			</div>
			<!-- end .l-frame 左边框架结束 -->
			<!-- start .r-frame 右边框架开始 -->
			<div class="r-frame" >
			
            	<decorator:body/>
			
			</div>
			<!-- end .r-frame 右边框架结束 -->
		</div>
		<!-- end #content 中间结束 -->
		<div id="footer">
			<div class="auto">
                <p class="copyright">
                    <span>广东省知识产权局</span>
                    <span>版权所有</span>
                    <span>CopyRight©2015</span>
                </p>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
$(document).ready(function (){
	//设置链接样式
	$("#${selectedMenuId}").addClass("inIs");
});
</script>
</html>