<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<%    
if(request.getHeader("REFERER")==null||request.getHeader("REFERER")==""){
	response.sendRedirect(request.getContextPath()+"/site/train/list.do");
	return;
	}
%>
<%-- <link rel="stylesheet" href="${ctx}/themes/itlt-ppt/css/reset.css">
<link rel="stylesheet" href="${ctx}/themes/itlt-ppt/css/style.css"> --%>
	<!--start #g-bd 中间 -->
	<div id="g-bd">
		<!--start #courseDetail 课程详情 -->
		<div id="courseDetail">
			<div class="g-page-wrap">
				<div class="g-page-box">
					<div class="m-tt2">
						<div class="m-crm">
							<span class="c-txt">您的位置：</span>
							<a href="${ctx}/">首页</a>
							<span class="line">&gt;</span>
							<span ><a href="${ctx}/site${globalRegions}/train/list.do">人才培训系统</a></span>
							<span class="line">&gt;</span>
							<span class="z-crt">${course.courseName}</span>
						</div>
					</div><!--end .m-tt2-->	
					<div class="m-page-dt">
						<div class="g-cd-box">
							<div class="g-figure-panel">
								<div class="t-img">
									<%-- <img src="${ctx}/themes/itlt-ppt/images/courseImg.jpg" alt="">
									<a href="#" class="u-play">观看视频简介</a> --%>
									<c:if test="${not empty CourseList[0].courseImg}">
									     <c:set value="${ipanthercore:getJSONMap(CourseList[0].courseImg)}" var="map" />
										 <img src="${ctx}${map.downloadUrl}" style="width: 100%;height: 100%">
									</c:if>
									<c:if test="${empty CourseList[0].courseImg}">
											<img src="${ctx}/themes/itlt-ppt/images/figure-img2.jpg" alt="" style="width: 100%;height: 100%">
									</c:if>
								</div>
								<div class="m-figure-info">
									<strong class="u-figure-title">${course.courseName }</strong>
									<div class="m-opa2">
										<span class="u-view">
											<i class="ico"></i>${course.studyHours}学时
										</span>
										<span class="u-num">
											<i class="ico"></i>${CourseList[0].trainCount}人
										</span>
									</div>
									<p class="intor">${course.courseIntro } </p>
									<ul class="m-img-lst" style="margin-top: 5px; border-top: 1px solid #e8e8e8">
										<li class="item">
											<a href="#" class="img">
											
											
												<c:if test="${not empty CourseList[0].teacherAvatar}">
													<c:set value="${ipanthercore:getJSONMap(CourseList[0].teacherAvatar)}" var="map" />
													<img src="${ctx}${map.downloadUrl}" >
												</c:if>
												<c:if test="${empty CourseList[0].teacherAvatar}">
													<img src="${ctx}/themes/itlt-ppt/images/headImg30.jpg" alt="">
												</c:if>
											</a>
											<strong class="name"><a href="#">${course.teacherName }老师</a></strong>
											<p class="txt"><span>${course.teacherUnit }</span><span>${course.teacherJob }</span><span></span></p>
										</li>
									</ul>
								</div>
							</div><!--end .g-figure-panel -->
							<div class="g-frame f-cb">
								<div class="g-frame-main">
									<div class="g-frame-mod">
										<div class="m-frame-tt">
											<h3><span class="b"></span>课程报名</h3>
										</div>
										<div class="g-table" id="trainCourseContent">
											努力加载中。。。
										</div>
									</div><!--end .g-frame-mod -->
									<div class="g-frame-mod">
										<div class="m-frame-tt">
											<h3><span class="b"></span>课程概述</h3>
										</div>
										<div class="m-text">
											<p>${course.courseIntro }</p>
										</div>
									</div><!--end .g-frame-mod -->
								</div><!--end .g-frame-main -->
								
							</div><!--end .g-frame -->
							
						</div><!--end .g-cd-box-->
					</div><!--end .m-page-dt-->
				</div><!--end .g-page-box -->
			</div>
		</div>
		<!--end #courseDetail 课程详情 -->
	</div>
	<!--end #g-bd 中间 -->
	<div class="m-blackbg"></div>
<script type="text/javascript">
$(function(){
	setMenuSelect("train");
	$("#trainCourseContent").load("${ctx}/train/talentTrain/listCourseTrain.do?paramMap[courseId]=${course.id}");
})
</script>
