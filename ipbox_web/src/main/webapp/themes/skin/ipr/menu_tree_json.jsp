<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<%@ page import="com.hcis.ipanther.common.login.vo.LoginUser" %>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<%
	if(SecurityUtils.getSubject().isAuthenticated()){
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		String moduleId=request.getParameter("moduleId");
		if(moduleId!=null){
			if(loginUser.getMenuMap().containsKey(moduleId)){
				out.print(loginUser.getMenuMap().get(moduleId));
			}
		}
		else{
			out.print(loginUser.getMenu());
		}
    }
%>