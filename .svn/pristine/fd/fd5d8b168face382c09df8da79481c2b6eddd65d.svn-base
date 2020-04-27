<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<%@ include file="/themes/skin/ipr/inc.jsp" %>
<!--start #g-bd 中间 -->
<div id="g-bd">
<form id="infoForm" name="infoForm" action="${ctx}/site${globalRegions}/${type}/list.do" method="post" >
	<!--start #informList 通知公告列表 -->
	<div id="informList">
		<div class="g-page-wrap">
			<div class="g-page-box">
				<div class="m-tt2">
					<div class="m-crm">
						<span class="c-txt">您的位置：</span>
						<a href="${ctx}/site${globalRegions}/index.do">首页</a>
						<span class="line">&gt;</span>
						<span class="z-crt">${type eq 'inform'?'通知要闻':'培训动态' }</span>
					</div>
				</div><!--end .m-tt2-->	
				<div class="m-page-dt">
					<ul class="m-txt-lst1 f-cb-lst">
						<c:forEach items="${infos}" var="info">
							<li class="c-item">
								<a href="${ctx}/site${globalRegions}/${type}/detail.do?id=${info.id}">${info.title}</a>
								<span class="time"><fmt:formatDate value="${info.addTime}" pattern="yyyy-MM-dd"/></span>
							</li>
						</c:forEach>
					</ul><!--end .m-txt-lst1-->
					<jsp:include page="${ctx}/jsp/common/include/manager_page_cms.jsp">
						<jsp:param name="pageForm" value="infoForm"/>
					</jsp:include>
				</div><!--end .m-page-dt-->
			</div><!--end .g-page-box -->
		</div>
	</div>
	<!--end #informList 通知公告列表 -->
</form>
</div>
<!--end #g-bd 中间 -->
<script type="text/javascript">
$(document).ready(function(){
	$.fn.jPicker.defaults.images.clientPath='${ctx}/js/jquery/plugins/jpicker/images/';
	debugger
	setMenuSelect("${type}");
});
</script>