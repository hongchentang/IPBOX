<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="viewDetailPanel" class="easyui-panel" data-options="" style="height: 95%">
<div id="viewCourseTabs" class="easyui-tabs" data-options="tabPosition:'left',height:getHeight('viewDetailPanel',100),cache:false" >
	<div id="courseViewTab" data-options="href:'${ctx}/train/course/view.do?paramMap[courseId]=${searchParam.paramMap.courseId }'" title="课程详情"></div>
	<c:if test="${not empty param.showType }">
		<div id="fileTab" data-options="href:'${ctx}/space/archive/noskin/listExpertAttachment.do?paramMap[courseId]=${searchParam.paramMap.courseId }'" title="专题资源"></div>
	</c:if>
	<c:if test="${empty param.showType }">
		<div id="trainCourseTab" data-options="href:'${ctx}/space/archive/noskin/listCourseAttachment.do?paramMap[trainCourseId]=${searchParam.paramMap.trainCourseId }'" title="课程资源"></div> 
	</c:if>
</div>
</div>