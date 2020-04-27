<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String globalRegions = (String)request.getSession().getAttribute("globalRegions");
// 	response.sendRedirect("/site"+(null==globalRegions?"":globalRegions)+"/index.do");
	response.sendRedirect("/login.do");
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title></title>
</head>
<body>
</body>
</html>