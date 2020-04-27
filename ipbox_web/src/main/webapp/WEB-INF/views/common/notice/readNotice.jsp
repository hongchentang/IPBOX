<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>

<div style="margin:5px">
	  <div>
        <h3 style="text-align: center;">
        	${notice.noticeTitle} 
            <br/>
        	<small> 发布时间：<fmt:formatDate value="${notice.createTime}" pattern="yyyy-MM-dd"/></small> 
        </h3>
      </div>
	  <div style="vertical-align:top; min-height:200px">
      	${notice.noticeContent}
      </div>
      <%--<c:choose>
        <c:when test="${StringUtils:isNotEmpty(notice.attachment)}">
          <div class="well">
            <p>
              <c:set var="fileUploadResult" value="${ipanthercore:getJSONMap(notice.attachment)}"/>
              <span id="attachmentName"><a href="${downloadUrl}?attachment.id=${fileUploadResult.attachmentId}" target="download">${fileUploadResult.attachmentName}(点击下载)</a></span> </p>
          </div>
          <hr/>
        </c:when>
        <c:otherwise> <span id="attachmentName"></span>
          <hr/>
        </c:otherwise>
      </c:choose>--%>
    <div style="text-align:center">
      <button type="button" class="easyui-linkbutton" onClick="easyuiUtils.closeWindow('readNoticeWindow')">关闭</button>
    </div>
  </div>
</div>