<%@ page language="java" pageEncoding="UTF-8"%>  
<%@include file="/jsp/common/include/taglib.jsp"%> 
 <div class="modal-dialog modal-full">
 <c:set var="left" value="0"/>
 <c:set var="top" value="0"/>
 <%--结束的边框比较圆 --%>
 <c:choose>
 	<c:when test="${isEnd }">
 		<c:set var="radius" value="100"/>
 	</c:when>
 	<c:otherwise>
 		<c:set var="radius" value="10"/>
 	</c:otherwise>
 </c:choose>
 <div >
 
     <img id="resourceImg" src="${ctx}/common/flow/resource/read.do?processDefinitionId=${processDefinitionId}&resourceType=image" style="position:absolute; left:${left}px; top:${top }px;"/>
     <!-- 给执行的节点加框 -->  
     <div style="opacity: 0.3;background-color: green;position:absolute;border-radius:${radius }px; border:4px solid red;left:${activityXY.x-1+left }px;top:${activityXY.y-1+top }px;width:${activityXY.width-5}px;height:${activityXY.height-5 }px;"></div>
 
 </div>  
</div>