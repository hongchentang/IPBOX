<%-- <%@ page import="com.hcis.tms.organization.utils.OrganizationConstants"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ipanthercore" uri="http://ipanther-core.hcis.com" %>
<%@ taglib prefix="iprcms" uri="http://ipr-cms.hcis.com" %>
<%@ taglib prefix="ipanthercommon" uri="http://ipanther-common.hcis.com" %>
<%@ taglib prefix="StringUtils" uri="http://commons.apache.org/lang/StringUtils" %>
<%@ taglib prefix="esc" uri="http://commons.apache.org/lang/StringEscapeUtils" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<%@ taglib prefix="dict" uri="http://dictionary.hcis.com" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="currentRequestUrl" value="${requestScope['org.springframework.web.servlet.HandlerMapping.pathWithinHandlerMapping']}"/>
<c:set var="downloadUrl" value="${ipanthercore:getProperty('attachment.download.url')}"/>
<jsp:useBean id="now" class="java.util.Date" />
<%--<fmt:formatDate var="nowYear" value="${now}" type="both" dateStyle="long" pattern="yyyy" />
<fmt:formatDate var="nowDate" value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd" />
<fmt:formatDate var="nowDateTime" value="${now}" type="both" dateStyle="long" pattern="yyyy-MM-dd HH:mm:ss" />--%>
<%-- Internationalization --%>
<c:set var="nowLocale" value="${ipanthercommon:getLocale(pageContext.request)}"/>
<fmt:setLocale value="${nowLocale}"/>
<fmt:setBundle basename="i18n/messages" var="resourceBundle"/>
