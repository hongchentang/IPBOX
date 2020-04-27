<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<form id="courseForm" action="${ctx}/train/talentTrain/listCourse.do" method="post">
<input type="hidden" id="trainType" name="paramMap[trainType]" value="${searchParam.paramMap.trainType }">
<input type="hidden" id="hoursType" name="paramMap[hoursType]" value="${searchParam.paramMap.hoursType }">
<input type="hidden" id="courseLevel" name="paramMap[courseLevel]" value="${searchParam.paramMap.courseLevel }">
<input type="hidden" id="applyJob" name="paramMap[applyJob]" value="${searchParam.paramMap.applyJob }">
<input type="hidden" id="year" name="paramMap[year]" value="${searchParam.paramMap.year }">
<input type="hidden" id="trainId" name="paramMap[declareDept]" value="${searchParam.paramMap.declareDept }">
<input type="hidden" id="courseStage" name="paramMap[courseStage]" value="${searchParam.paramMap.courseStage }">
<input type="hidden" id="openStatus" name="paramMap[openStatus]" value="${searchParam.paramMap.openStatus }">

	<div class="g-talents-lst">
		<ul class="m-talents-lst">
		<!-- 有两个li的课程行，改课程列表显示的时候，同时需同时改 -->
		<li class="b-item">
				<ul class="m-figure-lst m-figure-lst1 f-cb">
					<c:forEach items="${CourseList }" var="course" varStatus="vs" begin="0" end="3" step="1">
					<li class="c-item">
						<div class="turn-before">
							<c:if test="${not empty course.courseImg}">
							     <c:set value="${ipanthercore:getJSONMap(course.courseImg)}" var="map" />
							     <a href="courseDetail.html" target="_blank" class="img">
								 	<img src="${ctx}${map.downloadUrl}" >
								 </a>
							</c:if>
							<c:if test="${empty course.courseImg}">
								 <a href="courseDetail.html" target="_blank" class="img">
									<img src="${ctx}/themes/itlt-ppt/images/figure-img2.jpg" alt="">
								</a>
							</c:if>
							
							<strong class="u-figure-title"><a href="javacript:void(0);" target="_blank" title="最新知识产权人才培训">${course.courseName }</a></strong>
							<p class="c-site">来源：${course.deptName }</p>
							<div class="m-opa2">
								<a href="javascript:void(0)" class="u-view">
									<i class="ico"></i>${course.studyHours }学时
								</a>
								<a href="javascript:void(0)" class="u-num">
									<i class="ico"></i>${course.trainCount }人
								</a>
							</div>
							<div class="m-mt1">
								<c:if test="${searchParam.paramMap.openStatus eq 'willOpen'}">
									<span class="u-type u-type2">即将报名</span>
								</c:if>
								<c:if test="${searchParam.paramMap.openStatus eq 'opening'}">
									<span class="u-type u-type3">报名进行中</span>
								</c:if>
								<c:if test="${searchParam.paramMap.openStatus eq 'notOpen'}">
									<span class="u-type u-typ1">尚未开班</span>
								</c:if>
								<c:if test="${searchParam.paramMap.openStatus eq 'close'}">
								<span class="u-type u-type4"  style="background-color:#969">培训结束</span>
								</c:if>
								<!-- 全部的时候 -->
								<%-- <c:if test="${searchParam.paramMap.openStatus eq 'all'}">
									<c:if test="${empty course.trainStartTime }">
										<span class="u-type u-typ1">尚未开班</span>
									</c:if>
									<c:if test="${course.trainStartTime < now}">
										<span class="u-type u-type3">报名进行中</span>
									</c:if>
									<c:if test="${course.trainStartTime >now}">
										<span class="u-type u-type2">即将报名</span>
									</c:if>									
								</c:if> --%>
							</div>	
						</div>
						<div class="turn-after">
							<div class="c-pd">
								<ul class="m-img-lst">
									<li class="item">
										<a href="javascript:void(0)" class="img">
										<%-- <img src="${ctx}/themes/itlt-ppt/images/headImg30.jpg" alt=""> --%>
												<c:if test="${not empty course.teacherAvatar}">
													<c:set value="${ipanthercore:getJSONMap(course.teacherAvatar)}" var="map" />
													<img src="${ctx}${map.downloadUrl}" >
												</c:if>
												<c:if test="${empty course.teacherAvatar}">
													<img src="${ctx}/themes/itlt-ppt/images/headImg30.jpg" alt="">
												</c:if>
										</a>
										<strong class="name"><a href="javascript:void(0)">${course.teacherName }老师</a></strong>
										<p class="txt"><span>${course.teacherUnit }</span></p>
									</li>
								</ul>
								<p class="c-ex">${course.courseIntro }</p>
								<a href="${ctx}/site${globalRegions}/train/detail.do?id=${course.id}" class="u-turn"><span>最新知识产权人才培训</span> <i class="ico"></i></a>	
							</div>
						</div>
					</li>
					</c:forEach>
				</ul>
			</li>
			<c:if test="${fn:length(CourseList)>4}">
				<li class="b-item">
					<ul class="m-figure-lst m-figure-lst1 f-cb">
						<c:forEach items="${CourseList }" var="course" varStatus="vs" begin="4" end="7" step="1">
						<li class="c-item">
							<div class="turn-before">
								<c:if test="${not empty course.courseImg}">
								     <c:set value="${ipanthercore:getJSONMap(course.courseImg)}" var="map" />
								     <a href="courseDetail.html" target="_blank" class="img">
									 	<img src="${ctx}${map.downloadUrl}" >
									 </a>
								</c:if>
								<c:if test="${empty course.courseImg}">
									 <a href="courseDetail.html" target="_blank" class="img">
										<img src="${ctx}/themes/itlt-ppt/images/figure-img2.jpg" alt="">
									</a>
								</c:if>
								
								<strong class="u-figure-title"><a href="courseDetail.html" target="_blank" title="最新知识产权人才培训">${course.courseName }</a></strong>
								<p class="c-site">来源：${course.deptName }</p>
								<div class="m-opa2">
									<a href="javascript:void(0)" class="u-view">
										<i class="ico"></i>${course.studyHours }学时
									</a>
									<a href="javascript:void(0)" class="u-num">
										<i class="ico"></i>${course.trainCount }人
									</a>
								</div>
								<div class="m-mt1">
									<c:if test="${searchParam.paramMap.openStatus eq 'willOpen'}">
										<span class="u-type u-type2">即将报名</span>
									</c:if>
									<c:if test="${searchParam.paramMap.openStatus eq 'opening'}">
										<span class="u-type u-type3">报名进行中</span>
									</c:if>
									<c:if test="${searchParam.paramMap.openStatus eq 'notOpen'}">
										<span class="u-type u-typ1">尚未开班</span>
									</c:if>
									<c:if test="${searchParam.paramMap.openStatus eq 'close'}">
									<span class="u-type u-type4"  style="background-color:#969">培训结束</span>
									</c:if>
									<!-- 全部的时候 -->
									<%-- <c:if test="${searchParam.paramMap.openStatus eq 'all'}">
										<c:if test="${empty course.trainStartTime }">
											<span class="u-type u-typ1">尚未开班</span>
										</c:if>
										<c:if test="${course.trainStartTime < now}">
											<span class="u-type u-type3">报名进行中</span>
										</c:if>
										<c:if test="${course.trainStartTime >now}">
											<span class="u-type u-type2">即将报名</span>
										</c:if>									
									</c:if> --%>
								</div>	
							</div>
							<div class="turn-after">
								<div class="c-pd">
									<ul class="m-img-lst">
										<li class="item">
											<a href="javascript:void(0)" class="img">
												<c:if test="${not empty course.teacherAvatar}">
													<c:set value="${ipanthercore:getJSONMap(course.teacherAvatar)}" var="map" />
													<img src="${ctx}${map.downloadUrl}" >
												</c:if>
												<c:if test="${empty course.teacherAvatar}">
													<img src="${ctx}/themes/itlt-ppt/images/headImg30.jpg" alt="">
												</c:if>
											</a>
											<strong class="name"><a href="javascript:void(0)">${course.teacherName }老师</a></strong>
											<p class="txt"><span>${course.teacherUnit }</span></p>
										</li>
									</ul>
									<p class="c-ex">${course.courseIntro }</p>
									<a href="${ctx}/site${globalRegions}/train/detail.do?id=${course.id}" class="u-turn"><span>最新知识产权人才培训</span> <i class="ico"></i></a>	
								</div>
							</div>
						</li>
						</c:forEach>
					</ul>
				</li>
			</c:if>
		
		</ul><!--end .m-talents-lst-->	
	</div>
	
	<jsp:include page="${ctx}/jsp/common/include/manager_page_cms.jsp">
		<jsp:param name="pageForm" value="courseForm"/>
		<jsp:param name="divId" value="courseContent"/>
	</jsp:include>
	
</form>
<script type="text/javascript">
$(function(){
	turns($(".m-figure-lst1 .c-item"),180,100);
	})
</script>