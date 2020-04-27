<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ipanthercommon" uri="http://ipanther-common.hcis.com" %>
<%@ taglib prefix="ipanthercore" uri="http://ipanther-core.hcis.com" %>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<c:set var="downloadUrl" value="${ipanthercore:getProperty('attachment.download.url')}"/>
</head>
<body>
<%
	String nameInput=StringUtils.defaultString(request.getParameter("name"),"attachmentName");
	String valueInput=StringUtils.defaultString(request.getParameter("value"),"attachment");
%>
<script type="text/javascript">
var result = "{\"attachmentId\":\"${param.attachmentId}\",\"attachmentName\":\"${param.attachmentName}\",\"status\":\"${param.status}\",\"groupId\":\"${param.groupId}\",\"fileId\":\"${param.fileId}\"}";
<%-- /*var api = frameElement.api, W = api.opener;
W.$("#<%=nameInput%>").html("<a href='${downloadUrl}?attachment.id=${param.attachmentId}'>${param.attachmentName}</a>");
W.$("#<%=valueInput%>").val(result);
W.dialog.close();*/ --%>
$("#<%=nameInput%>").html("<a href='${downloadUrl}?attachment.id=${param.attachmentId}' title='点击下载'>${param.attachmentName}(点击下载)</a>");
$("#<%=valueInput%>").html(result);
// bootbox.hideAll();
closeWindow('fileUpload');
</script>	
</body>
</html>