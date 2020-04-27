<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<!-- echarts图表 -->
<%--
调用参考：
<div class="m-mod-dt" id="show_student_content">
</div>
<script type="text/javascript">
	$("#show_student_content").load("/cms/train/statisticsIndex.do");
</script>
 --%>
 <div class="g-chart-box f-cb">
 	<%-- <img src="${ctx}/themes/itlt-ppt/images/char.jpg" alt=""> --%>
 	
	<div class="m-chart-figure" id="train_statistics_char">
		
	</div>
	<script type="text/javascript">
	echarts.init($("#train_statistics_char").get(0)).setOption(${option});
	
	</script>
	<div class="m-chart-infor">
		<div class="m-chart-block">
			<h4>培训总体情况</h4>
			<ul class="m-chartInfor-lst">
				<li>
					<span>培训人次：</span>
					<b><fmt:formatNumber value="${sumStatistics.studentCount}"  pattern="#,###"/></b>
				</li>
				<li>
					<span>课程门次：</span>
					<b><fmt:formatNumber value="${sumStatistics.courseCount}"  pattern="#,###"/></b>
				</li>
				<li>
					<span>培训课时：</span>
					<b><fmt:formatNumber value="${sumStatistics.studyHoursCount}"  pattern="#,###"/></b>
				</li>
				<li>
					<span>师资总数：</span>
					<b><fmt:formatNumber value="${sumStatistics.teacherCount}"  pattern="#,###"/></b>
				</li>
			</ul>
		</div>
		<div class="m-chart-block last">
			<h4>上年培训情况</h4>
			<ul class="m-chartInfor-lst">
				<li>
					<span>培训人次：</span>
					<b><fmt:formatNumber value="${lastYearStatistics.studentCount}"  pattern="#,###"/></b>
				</li>
				<li>
					<span>课程门次：</span>
					<b><fmt:formatNumber value="${lastYearStatistics.courseCount}"  pattern="#,###"/></b>
				</li>
				<li>
					<span>培训课时：</span>
					<b><fmt:formatNumber value="${lastYearStatistics.studyHoursCount}"  pattern="#,###"/></b>
				</li>
				<li>
					<span>师资总数：</span>
					<b><fmt:formatNumber value="${lastYearStatistics.teacherCount}"  pattern="#,###"/></b>
				</li>
			</ul>
		</div>
		<div class="m-chart-block"></div>
	</div>
</div>
