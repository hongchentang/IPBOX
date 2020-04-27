<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%
	String monitor = request.getParameter("monitor");
	if(monitor!=null&&monitor.equals("true")){
		ApplicationContext context = WebApplicationContextUtils
				.getWebApplicationContext(application);
		org.apache.commons.dbcp.BasicDataSource dataSource = (org.apache.commons.dbcp.BasicDataSource) context
				.getBean("dataSource");
		java.sql.Connection connection = null;
		java.sql.PreparedStatement pstm = null;
		try {
			connection = dataSource.getConnection();
			pstm = connection.prepareStatement("select 1 from dual");
			if (pstm.execute()) {
				out.println("success");
			} else {
				out.println("fail");
			}
		} catch (Exception e) {
			out.println("fail");
		} finally {
			pstm.close();
			connection.close();
		}
	}
%>