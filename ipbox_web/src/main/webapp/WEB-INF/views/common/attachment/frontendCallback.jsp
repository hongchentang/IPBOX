<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@include file="/jsp/common/include/taglib.jsp" %>
<c:set var="downloadUrl" value="${ipanthercore:getProperty('attachment.download.url')}"/>
</head>
<body>
<%
	String nameInput=StringUtils.defaultString(request.getParameter("name"),"attachmentName");
	String valueInput=StringUtils.defaultString(request.getParameter("value"),"attachment");
%>
<script type="text/javascript">
var result = "{\"attachmentId\":\"${param.attachmentId}\",\"attachmentName\":\"${param.attachmentName}\",\"status\":\"${param.status}\",\"groupId\":\"${param.groupId}\",\"fileId\":\"${param.fileId}\"}";
var htmls="<img src='${downloadUrl}?attachment.id=${param.attachmentId}' alt='${param.attachmentName}' width='100' height='100'/>";
$('#<%=nameInput%>').html(htmls);
$('#<%=valueInput%>').val(result);
// bootbox.hideAll();
closeWindow('fileUpload');
</script>	
</body>
</html>