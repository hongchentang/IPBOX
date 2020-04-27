<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/include/taglib.jsp" %>
<style>
ol li {
	list-style:disc;
	margin-left:20px;
}
</style>
<%--不是教师且不再审核中 --%>
<c:set var="canBeTeacher" value="${user.teacherStatus ne '03' and user.teacherStatus ne '02' and user.studentStatus ne '02'}"/>
<%--不是学员且不再审核中 --%>
<c:set var="canBeStudent" value="${user.studentStatus ne '03' and user.studentStatus ne '02' and user.teacherStatus ne '02'}"/>
<div class="index">
	<!-- start .hint 温馨提示模块开始 -->
	<div class="hint mod">
		<div class="po-relative">
			<div class="clearfix">
				<i class="hint-ico ico"></i>
				<em class="title">温馨提示：</em>
				<span class="hint-text">
				<c:set var="notTip" value="true"/><%--没有提示的标识 --%>
				<ol id="tip_ol">
					<c:if test="${canBeTeacher or canBeStudent}">
						<c:choose>
							<c:when test="${canBeTeacher and canBeStudent}">
								<c:set var="notTip" value="false"/>
								<li style="">
									您可以
									<a href="${ctx}/space/user/tabs.do?registerType=student" style="text-decoration: underline;">注册成为学员</a>
									或者
									<a href="${ctx}/space/user/tabs.do?registerType=teacher" style="text-decoration: underline;">注册成为教师</a>
								</li>
							</c:when>
							<c:when test="${canBeTeacher}">
								<li>
									<c:set var="notTip" value="false"/>
									您可以
									<a href="${ctx}/space/user/tabs.do?registerType=teacher" style="text-decoration: underline;">注册成为教师</a>
								</li>
							</c:when>
							<c:when test="${canBeStudent}">
								<li>
									<c:set var="notTip" value="false"/>
									您可以
									<a href="${ctx}/space/user/tabs.do?registerType=student" style="text-decoration: underline;">注册成为学员</a>
								</li>
							</c:when>
						</c:choose>
					</c:if>
				</ol>
				
				<c:if test="${notTip}">
					<script type="text/javascript">
						$("#tip_ol").hide();
					</script>
					暂无提示
				</c:if>
				</span>
			</div>
		</div>
	</div>
	<!-- end .hint 温馨提示模块结束 -->
	
	<!-- start 通知公告模块开始 -->
	<c:if test="${not empty notices }">
		<%-- <div class="notice nc-mod">
			<div class="tit clearfix">
				<h3>通知公告</h3>
				<a href="${ctx}/space/notice/listNotice.do" class="more">更多&gt;&gt;</a>
			</div>
			<div class="con">
				<ul class="notice-list">
					<c:forEach items="${notices}" var="notice" end="4">
						<li>
							<a class="text" title="点击查看明细" href="${ctx}/space/notice/viewNotice.do?id=${notice.id}">${notice.noticeTitle}</a>
							<span class="time">
								<c:choose>
									<c:when test="${not empty notice.startTime }">
										${notice.startTime}
									</c:when>
									<c:otherwise>
										<fmt:formatDate value="${notice.createTime}" pattern="yyyy-MM-dd"/>
									</c:otherwise>
								</c:choose>
							</span>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div> --%>
	</c:if>
	<c:if test="${not empty notices }">
		<div class="notice nc-mod">
			<div class="tit clearfix">
				<h3>培训动态</h3>
				<a href="${ctx}/site/dynamic/list.do" class="more" target="_blank">更多&gt;&gt;</a>
			</div>
			<div class="con">
				<ul class="notice-list">
					<c:forEach items="${cmsInfos}" var="cmsInfo" end="4">
						<li>
							<a class="text" title="点击查看明细" href="${ctx}/site/${cmsInfo.type eq '1'?'inform':'dynamic' }/detail.do?id=${cmsInfo.id}" target="_blank">${cmsInfo.title}</a>
							<span class="time">
								<fmt:formatDate value="${cmsInfo.addTime}" pattern="yyyy-MM-dd"/>
							</span>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</c:if>
	<!-- end 通知公告模块结束 -->
	
	<!-- start 调查问卷模块开始 -->
	<c:if test="${not empty surveies and isStudent}">
		<div class="notice nc-mod">
			<div class="tit clearfix">
				<h3>调查问卷</h3>
				<a href="${ctx}/space/survey/listSurvey.do" class="more">更多&gt;&gt;</a>
			</div>
			<div class="con">
				<ul class="notice-list">
					
					<c:forEach items="${surveies}" var="survey" end="4">
						<li>
							<c:choose>
								<c:when test="${survey.completionStatus eq 'complete'}">
									<a class="text" title="${survey.description}" href="/space/survey/surveyStat.do?id=${survey.id}">${survey.title}</a>
									<span class="time" style="color:green">
										已参与
									</span>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${survey.endTime < now}">
											<a class="text" title="${survey.description}" href="###">${survey.title}</a>
											<span class="time" style="color:grey">
												已过期
											</span>
										</c:when>
										<c:otherwise>
											<a class="text" title="${survey.description}" href="/space/survey/userSurvey.do?id=${survey.id}">${survey.title}</a>
											<span class="time" style="color: red">
												未参与
											</span>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
							
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
	</c:if>
	<!-- end 调查问卷模块结束 -->

</div>
				<!-- end .index 个人中心首页内容结束 -->
		<div class="blackBg" style="display:none;"></div>
		<!-- start .layer 测评弹窗开始 -->