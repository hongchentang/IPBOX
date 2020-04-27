<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="ipanthercommon" uri="http://ipanther-common.hcis.com" %>
<%@ taglib prefix="ipanthercore" uri="http://ipanther-core.hcis.com" %>
<%@ page import="java.util.*"%>
<script language="javascript">
function loadContent(url){
	$('#content').load(url);
}
</script>

<div class="accordion-group">
<div class="accordion-heading">
<a class="accordion-toggle" href="#" onclick="javascript:loadContent('/ireview/modle/listTestModle.do')">
    <i class="icon-folder-close"></i>
    TestModle
</a>
</div>
</div>
</div>
