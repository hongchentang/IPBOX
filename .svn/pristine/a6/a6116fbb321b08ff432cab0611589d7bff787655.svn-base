<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<%    
if(request.getHeader("REFERER")==null||request.getHeader("REFERER")==""){
	response.sendRedirect(request.getContextPath()+"/site/teacher/list.do");
	return;
	}
%>
<div id="g-bd">
	<!--start #teacher 培训师资系统 -->
	<div id="teacher">
		<div class="g-page-wrap">
			<div class="g-page-box">
				<div class="m-crm">
					<span class="c-txt">您的位置：</span> <a href="${ctx}/site${globalRegions}/index.do">首页</a>
					<span class="line">&gt;</span>
					<span class="c-txt"><a href="${ctx}/site${globalRegions}/teacher/list.do">师资培训系统</a></span>
					<span class="line">&gt;</span>
					<span class="z-crt"> 教师详情 </span>
				</div>
				<ul class="m-techer-lst">
						<li class="c-item">
							<c:choose>
								<c:when test="${not empty teacher.avatar}">
									<c:set value="${ipanthercore:getJSONMap(teacher.avatar)}" var="map" />
									<span class="img"><img src="${ctx}${map.downloadUrl}" alt="" style="max-width: 120px;max-height:160px" onerror="javascript:this.src='${ctx}/themes/easyui/themes/tms/images/default.jpg';"></span>
								</c:when>
								<c:otherwise>
									<span class="img"><img src="${ctx}/themes/easyui/themes/tms/images/default.jpg" alt=""></span>
								</c:otherwise>
							</c:choose>
							<strong class="name">
							${teacher.realName}
							</strong>
							<p class="c-txt">
									<table class="m-table" width="100%" cellspacing="1" cellpadding="1" border="0">
									  	<tr>
									  		<th width="10%" >性别${id }</th>
									  		<td width="15%" style="text-align: left">${dict:getEntryName('SEX_TYPE',teacher.sex) }</td>
									  		<th width="10%" >专业</th>
									  		<td width="15%" style="text-align: left">${teacher.highCollege}</td>
									  		<th width="10%" >学员类别</th>
									  		<td width="25%" style="text-align: left">${dict:getEntryNameByJson('USER_TYPE',teacher.userType)}</td>
									  	</tr>
									  <tr>
									  	<th >职称</th>
									    <td style="text-align: left">${teacher.title}</td>
									    <th >职务</th>
									    <td style="text-align: left">${teacher.job}</td>
									    <th >研究领域</th>
									    <td style="text-align: left" >${teacher.researchField}</td>
									  </tr>
									   <tr>
									    <th >工作单位</th>
									    <td style="text-align: left">${teacher.belongDeptName}</td>
									    <th >所在地区</th>
									    <td colspan="5" style="text-align: left">
				<%-- 					    ${teacher.regionsName} --%>
									    ${ipanthercommon:getRegionsName(teacher.currentProvince)}
											${ipanthercommon:getRegionsName(teacher.currentCity)}
											${ipanthercommon:getRegionsName(teacher.currentCounties)}
											${teacher.currentStreet }
									    
									    	<c:set var="keyId" value="${teacher.id}parttimes"/>
											<c:set var="keyId2" value="${teacher.id}researchs"/>
									    </td>
									  </tr>
									    <c:forEach items="${dataMap[keyId]}" var="parttimes">
										  <tr>
										  <th > 
										 
											 兼职名称
										  </th>
										  <td colspan="9" style="text-align: left">${parttimes.name}</td>
										 </tr>
										 <tr>
										  <th >兼职类型</th>
										  <td colspan="9" style="text-align: left">
										  	${dict:getEntryName('PARTTIME_TYPE',parttimes.type)}
										  </td>
									  </tr>
										</c:forEach>
									  <c:forEach items="${dataMap[keyId2]}" var="researchs">
									  <tr>
									  
									  		<th >时间</th>
									  		<td colspan="9" style="text-align: left">
									  			<fmt:formatDate value="${researchs.publicationDate}" pattern="yyyy-MM-dd"/>
									  		</td>
									 </tr>  
									 <tr>	
									  		<th >名称</th>
									  		<td colspan="9" style="text-align: left">${researchs.achievementName}</td>
									  </tr>
									   <tr>
									  		<th >职责</th>
									  		<td colspan="9" style="text-align: left">${researchs.selfAffect}</td>
									  </tr>
									  </c:forEach>
									  
									  <tr>
									    <th >个人简介</th>
									    <td colspan="5" style="text-align: left">
									    	${teacher.introduce}
									    </td>
									  </tr>
									</table>
							</p>
						</li>
						<li class="c-item">
							<a id="u-reg-btn1" class="reg1" href="javascript:void(0);" onclick="history.go(-1)">&lt; 返回</a>
						</li>
				 
				</ul>
				</div>
			</div>
		</div>
</div>
<script type="text/javascript">
$(function(){
	setMenuSelect("teacher");	
});
</script>