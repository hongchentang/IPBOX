<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div class="mod">
	<div class="tit clearfix">
		<h3>教师记录</h3>
	</div>
</div>
<div id="viewTeacherTabs" class="easyui-tabs" data-options="tabPosition:'top',cache:false" >
	<div id="expertTab" data-options="href:'${ctx}/space/archive/noskin/listExpertCourse.do'" title="专题分配记录"></div>
	<div id="teacherTab" data-options="href:'${ctx}/space/archive/noskin/listTeacherCourse.do'" title="课程分配记录"></div>
</div>