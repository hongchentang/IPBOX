<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<%@ page import="java.util.Date" %>
	<ul class="m-figure-lst f-cb">
	<c:forEach items="${CourseList }" var="course" >
		<li class="c-item" style="margin-top: 9px;">
			<div class="turn-before">
			<c:if test="${not empty course.courseImg}">
			     <c:set value="${ipanthercore:getJSONMap(course.courseImg)}" var="map" />
			     <a href="javascript:void(0)" target="_blank" class="img">
				 	<img src="${ctx}${map.downloadUrl}" >
				 </a>
			</c:if>
			<c:if test="${empty course.courseImg}">
				 <a href="javascript:void(0)" target="_blank" class="img">
					<img src="${ctx}/themes/itlt-ppt/images/figure-img2.jpg" alt="">
				</a>
			</c:if>
				<strong class="u-figure-title"><a href="javascript:void(0)" target="_blank" title="最新知识产权人才培训">${course.courseName }</a></strong>
				<p class="c-site">来源：${course.deptName }</p>
				<div class="m-opa2">
					<a href="javascript:void(0)" class="u-view">
						<i class="ico"></i>${not empty course.studyHours?course.studyHours:0 }学时
					</a>
					<a href="javascript:void(0)" class="u-num">
						<i class="ico"></i>${course.trainCount }人
					</a>
				</div>
				<div class="m-mt1">
                
                <c:if test="${course.openCount>0 and course.openingCount==0}">
                	<span class="u-type u-type2">即将报名</span>
                </c:if>
                <c:if test="${course.openingCount>0 }">
                	<span class="u-type u-type3">报名进行中</span>
                </c:if>
                 <c:if test="${course.endCount>0 and course.openingCount==0 and course.openCount==0}">
                 	<span class="u-type u-type4"  style="background-color:#969">培训结束</span>
                 </c:if>
                 <c:if test="${course.openCount==0 and course.openingCount==0 and course.endCount==0 }">
                 		<span class="u-type u-typ1">尚未开班</span>
                  </c:if>
				<!--<c:set value="<%=new Date() %>" var="date"/>
				<c:if test="${empty course.trainStartTime }">
					<span class="u-type u-typ1">尚未开班</span>
				</c:if>
				<c:if test="${not empty course.trainStartTime and  course.trainStartTime> date}">
					<span class="u-type u-type2">即将报名</span>
				</c:if>
				<c:if test="${not empty course.trainStartTime and  course.trainStartTime< date}">
					<span class="u-type u-type3">报名进行中</span>
				</c:if> -->
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
							<strong class="name"><a href="javascript:void(0)">${course.realName }老师</a></strong>
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
<script type="text/javascript">
	turns($(".m-figure-lst .c-item"),218,100);
</script>