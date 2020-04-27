<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://ipanther-core.hcis.com" prefix="ipanthercore" %> 
<%
	session.invalidate();
%>
<script>
	window.location.href="${ipanthercore:getProperty('logout.url')}";
</script>