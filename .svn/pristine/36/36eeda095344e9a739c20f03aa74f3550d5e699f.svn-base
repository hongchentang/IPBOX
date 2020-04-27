<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp" %>
当前目录  <b>${currFolder}</b>
<table id="listSiteTable" style="width:99%;height:auto; min-height:400px">
       <c:if test="${not empty resultList}">
        <tbody>
            <c:forEach var="list" items="${resultList }" varStatus="status">
            <tr>
                <td> 
	                <c:if test="${currFolder eq '根目录'}">
	                	<a href="javascript:void(0);" title="${list.name}" onclick="getValues('${list.name}','${list.name}')" style="text-decoration:underline;">${list.name}</a>
	                </c:if>
	                <c:if test="${currFolder ne '根目录'}">
	                	<a href="javascript:void(0);" title="${list.name}" onclick="getValues('${currFolder}/${list.name}','${currFolder}/${list.name}')" style="text-decoration:underline;">${list.name}</a>
	                </c:if>
                </td>
            </tr>
            </c:forEach>
    </tbody>
        </c:if>
</table>
<script type="text/javascript">
function getValues(returnId,returnValue){
	  if(("${paramMap.entityId}"!="")&&("${paramMap.entityName}"!="")){
        jQuery("#${paramMap.contentId}").find("#${paramMap.entityId}").val(returnId);
        jQuery("#${paramMap.contentId}").find("#${paramMap.entityName}").val(returnValue);
        closeWindow("${paramMap.dialogId}");
	  }
}
</script>