<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="/jsp/common/include/taglib.jsp"%>
 <form id="searchListForm" name="searchListForm" action="${ctx}${regions}/site/search.do" method="post">
 <input type="hidden" name="paramMap[keyword]" value="${searchParam.paramMap.keyword}"/>
<!--start #informList 通知公告列表 -->
<div id="informList">
	<div class="g-page-wrap">
		<div class="g-page-box">
			<div class="m-tt2">
				<div class="m-crm">
					<span class="c-txt">您的位置：</span>
					<a href="${ctx}/site${globalRegions}/index.do">首页</a>
					<span class="line">&gt;</span>
					<span class="z-crt">
					搜索结果
					</span>
				</div>
			</div><!--end .m-tt2-->	
			<div class="m-page-dt">
				<ul class="m-txt-lst1 f-cb-lst">
					<c:forEach items="${infos}" var="info">
						<li class="c-item">
							<c:choose>
								<c:when test="${info.channelPageMark eq 'dynamic'}">
									<a href="${ctx}/site${globalRegions}/dynamic/detail.do?id=${info.id}" title="${info.title}" target="_blank">【培训动态】${info.title}</a>
									<span class="time"><fmt:formatDate value="${info.addTime}" pattern="yyyy-MM-dd"/></span>
								</c:when>
								<c:when test="${info.channelPageMark eq 'inform'}">
									<a href="${ctx}/site${globalRegions}/inform/detail.do?id=${info.id}" title="${info.title}" target="_blank">【通知要闻】${info.title}</a>
									<span class="time"><fmt:formatDate value="${info.addTime}" pattern="yyyy-MM-dd"/></span>
								</c:when>
								<c:when test="${info.channelPageMark eq 'course'}">
									<a href="${ctx}/site${globalRegions}/train/detail.do?id=${info.id}" title="${info.title}" target="_blank">【培训课程】${info.title}</a>
									<span class="time"><fmt:formatDate value="${info.addTime}" pattern="yyyy-MM-dd"/></span>
								</c:when>
							</c:choose>
						</li>
					</c:forEach>
				</ul><!--end .m-txt-lst1-->
			</div><!--end .m-page-dt-->
		</div><!--end .g-page-box -->
	</div>
	<!--end #informList 通知公告列表 -->
<jsp:include page="${ctx}/jsp/common/include/manager_page_cms.jsp">
	<jsp:param name="pageForm" value="searchListForm"/>
</jsp:include>
</div>
</form>
<script type="text/javascript">
$(function(){
	$(".g-nvc").children().removeClass("z-crt");//移除菜单选中效果
})
</script>