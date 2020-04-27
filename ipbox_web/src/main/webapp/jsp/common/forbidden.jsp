<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib uri="http://icourse.hcis.com" prefix="icourse" %>  --%>
<html>
<head>
<meta charset="utf-8">
<title>非法访问</title>
<%-- <link rel="stylesheet" href="${icourse:getProperty('icourse.css.path')}/default/common.css">
<link rel="stylesheet" href="${icourse:getProperty('icourse.css.path')}/default/base.css">
<link rel="stylesheet" href="${icourse:getProperty('icourse.css.path')}/default/layout.css"> --%>
</head>
<body>
<section class="wrap">
<div class="Prompt">
<div class="titleBar">
<h2>禁止页面</h2><span><a href="" class="close"><img src="${pageContext.request.contextPath }/css/course/style/default/images/ui_close_hover.png" width="15" height="15"></a></span>
</div>
<div class="mContent"><img src="${pageContext.request.contextPath }/css/course/style/default/images/Prompt.png" width="61" height="61">禁止访问  请点取消关闭或点确定<a href="${pageContext.request.contextPath }/jsp/common/logout.jsp">重新登陆</a>！</div>
<div class="mButton"><a class="btn_style02 left10" href="${pageContext.request.contextPath }/jsp/common/logout.jsp"><span>&nbsp;&nbsp;确&nbsp;&nbsp;定&nbsp;&nbsp;</span></a><a class="btn_style04 left10" onclick="window.close();" href="javascript:;"><span>&nbsp;&nbsp;取&nbsp;&nbsp;消&nbsp;&nbsp;</span></a></div>
</div>
</section>
</body>
</html>