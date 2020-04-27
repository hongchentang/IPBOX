<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/include/taglib.jsp" %>
<div class="mod">
		<div class="tit clearfix">
			<h3>通知公告</h3>
		</div>
	</div>
<div id="content" class="auto">
	<div class="text-content clearfix">
		<div class="cont">
			<div class="frame-left">
				<div class="text-left inside-mod">
					<div class="text-details">
						<div class="text-top">
							<h3>${notice.noticeTitle}</h3>
							<div class="time">发布日期：<fmt:formatDate value="${notice.createTime}" pattern="yyyy-MM-dd"/></div>
						</div>
						<div class="text-middle-cont">
							<p>${notice.noticeContent}</p>
						</div>
					</div>
				</div>
			</div>
	
		</div>
	</div>
</div>
