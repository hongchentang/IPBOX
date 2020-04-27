<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<style>
ol li {
	list-style:disc;
	margin-left:20px;
}
</style>
<div class="mod">
	<div class="tit clearfix">
		<h3>个人资料</h3>
	</div>
</div>
<%--不是教师且不再审核中 --%>
<c:set var="canBeTeacher" value="${user.teacherStatus ne '03' and user.teacherStatus ne '02' and user.studentStatus ne '02'}"/>
<%--不是学员且不再审核中 --%>
<c:set var="canBeStudent" value="${user.studentStatus ne '03' and user.studentStatus ne '02' and user.teacherStatus ne '02'}"/>
<!-- start .hint 温馨提示模块开始 -->
<div class="hint mod" id="tip_div">
	<div class="po-relative">
		<div class="clearfix">
			<i class="hint-ico ico"></i>
			<em class="title">温馨提示：</em>
			<span class="hint-text">
			<c:set var="notTip" value="true"/><%--没有提示的标识 --%>
			<ol id="tip_ol">
				<c:if test="${canBeTeacher or canBeStudent }">
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
					$("#tip_div").hide();
				</script>
			</c:if>
			</span>
		</div>
	</div>
</div>
<!-- end .hint 温馨提示模块结束 -->

<c:set var="registerType" value="${param.registerType}"/><%--注册类型 --%>
<div id="viewUserTopListTabs" class="easyui-tabs" data-options="tabPosition:'top',cache:false" >
	<div id="viewUserBaseTopTab" data-options="href:'${ctx}/space/user/noskin/edit.do?registerType=${registerType}'" title="基本信息"></div>
	<div id="viewUserRewardTopTab" data-options="href:'${ctx}/common/user/reward/list.do?paramMap[userId]=${user.id}&action=space'" title="奖励信息"></div>
	<div id="viewUserResearchTopTab" data-options="href:'${ctx}/common/user/research/list.do?paramMap[userId]=${user.id}&action=space'" title="研究信息" ></div>
	<div id="viewUserParttimeTopTab" data-options="href:'${ctx}/common/user/parttime/list.do?paramMap[userId]=${user.id}&action=space'" title="兼职信息" ></div>
	<c:if test="${isStudent}">
		<div id="viewUserHisTopTab" data-options="href:'${ctx}/common/user/student/listHis.do?paramMap[userId]=${user.id}'" title="人才修改历史" ></div>
	</c:if>
	<div id="viewFlowLogTopTab" data-options="href:'${ctx}/common/user/listLog.do?paramMap[userId]=${user.id}'" title="流程日志" ></div>
	<c:if test="${not empty procInstId}">
		<div id="viewUserFlowTopTab" data-options="href:'${ctx}/common/flow/process/goTrace.do?procInstId=${procInstId}&height=800'" title="教师流程图" ></div>
	</c:if>
</div>