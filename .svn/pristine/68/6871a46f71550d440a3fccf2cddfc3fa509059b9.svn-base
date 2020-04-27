<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@include file="/jsp/common/include/taglib.jsp" %>
<c:set var="downloadUrl" value="${ipanthercore:getProperty('attachment.download.url')}"/>
</head>
<body>
<%
	String nameInput=StringUtils.defaultString(request.getParameter("name"),"picName");
	String valueInput=StringUtils.defaultString(request.getParameter("value"),"pic");
%>
<script type="text/javascript">
var result = "{\"attachmentId\":\"${param.attachmentId}\",\"attachmentName\":\"${param.attachmentName}\"}";
var htmls="<img src='${downloadUrl}?attachment.id=${param.attachmentId}' title='${param.attachmentName}' alt='${param.attachmentName}' width='300px' height='300px'/>";
$('#<%=nameInput%>').html(htmls);
$('#<%=valueInput%>').val(result);
// bootbox.hideAll();
closeWindow('fileUpload');
</script>	
</body>
</html>