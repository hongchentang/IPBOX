<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<%    
if(request.getHeader("REFERER")==null||request.getHeader("REFERER")==""){
	response.sendRedirect("/");
	return;
	}
%>
 
<!--start #g-bd 中间 -->
<div id="g-bd">
	<!--start #contactUs 联系我们 -->
	<div id="contactUs">
		<div class="g-page-wrap">
			<div class="g-page-box">
				<div class="m-tt2">
					<div class="m-crm">
						<span class="c-txt">您的位置：</span>
						<a href="${ctx}/site${globalRegions}/index.do">首页</a>
						<span class="line">></span>
						<a href="${ctx}/site${globalRegions}/${type}/list.do">${type eq 'inform'?'通知要闻':'培训动态'}</a>
						<span class="line">></span>
						<span class="z-crt">
						 ${type eq 'inform'?'要闻':'动态'}正文
						</span>
					</div>
				</div><!--end .m-tt2-->	
				<div class="m-page-dt">
				    <div class="m-page-dt">
						<div class="g-text-dt">
							<h1>${info.title}</h1>
							<div class="m-info">
								<span class="time">发布日期：<fmt:formatDate value="${info.addTime}" pattern="yyyy-MM-dd"/></span>
								<span class="num">浏览次数：${info.clickNum}</span>
							</div>
							<c:if test="${not empty info.img }">
								<c:set value="${ipanthercore:getJSONMap(info.img)}" var="img" />
								<p class="c-txt" style="text-align: center"><img src="${img.fileId}" alt=""></p>
							</c:if>
							<p class="c-txt">${info.content}</p>
							<c:if test="${not empty info.attchs }">
								<p class="c-txt"> 
									<div>
										<c:forEach items="${ipanthercore:getJSONArrayMap(info.attchs)}" var="attch">
									  		<span id="attachmentName"><a href="${attch.downloadUrl}" target="download">${attch.attachmentName}(点击下载)</a></span>											
										</c:forEach>
									 </div>
								</p>
							</c:if>
						</div>
						<c:if test="${not empty info.courseId}">
							<div>
							<a href="${ctx}/site/train/detail.do?id=${info.courseId}" style="text-decoration: underline;">报名入口</a>
							</div>
						</c:if>
					</div><!--end .m-page-dt-->
				</div>
			</div><!--end .g-page-box -->
		</div>
	</div>
	<!--end #contactUs 联系我们 -->
</div>
<!--end #g-bd 中间 -->
<script type="text/javascript">
$(document).ready(function(){
	setMenuSelect("${type}");
  	jQuery("#content").load("/train/talentTrain/registerStatistics.do");
  	jQuery("#content_train").load("/cms/train/statisticsTrainYear.do");
  	jQuery("#content_train_m").load("/cms/train/statisticsTrainMonth.do");
  	jQuery("#content_r_m").load("/cms/train/statisticsRegisterCount.do");
  	jQuery("#content_region").load("/cms/train/statisticsRegionCount.do");
});
</script>
<script type="text/javascript">
	//增加图片样式，避免图片过长
	$("img").css("max-width","800px");
</script>