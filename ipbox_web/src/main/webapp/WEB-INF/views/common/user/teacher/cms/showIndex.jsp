<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<%--
调用参考：
<div class="m-mod-dt" id="show_teacher_content">
	
</div>
<script type="text/javascript">
	$("#show_teacher_content").load("/cms/teacher/showIndex.do");
</script>
 --%>
	<a href="javascript:void(0);" class="u-np u-prev"></a>
	<a href="javascript:void(0);" class="u-np u-next"></a>
	<div class="g-uc-of" >
		<ul class="m-user-cards f-cb">
			<c:forEach items="${teachers}" var="teacher" end="9">
				<li class="c-item">
					<c:choose>
						<c:when test="${not empty teacher.avatar}">
							<c:set value="${ipanthercore:getJSONMap(teacher.avatar)}" var="map" />
							<span class="img"><img src="${ctx}${map.downloadUrl}" alt="" onerror="javascript:this.src='/themes/easyui/themes/tms/images/default.jpg'
							"></span>
						</c:when>
						<c:otherwise>
							<span class="img"><img src="${ctx}/themes/easyui/themes/tms/images/default.jpg" alt=""></span>
						</c:otherwise>
					</c:choose>
					<strong class="name">${teacher.realName}</strong>
					
					<p class="c-txt" title="${teacher.introduce}">
						${teacher.abbreviateIntroduce}
					</p>
				</li>
			</c:forEach>
		</ul>
	</div>
<script type="text/javascript">
$('.g-user-cards').rollpic({
	prev : ".u-prev",
	next : ".u-next",
	cont : ".m-user-cards",
	li : "li",
    pause:3000,
    nspd:1000,
    uspd:500,
    vnum:5,
    snum:1,
    //start:0,
    isH:true,
    auto:true
});
</script>
