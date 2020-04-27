<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/8/18
  Time: 12:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/include/taglib.jsp" %>

<div class="easyui-panel" style="width:100%; height: 80%">
    <div class="easyui-layout" data-options="fit:true">
        <div data-options="region:'north',split:true" style="width:100%;height: 80%">
            <img src="${ctx}${cost.attachmentPath}"  />
        </div>
        <div data-options="region:'south'" style="height: 20%; text-align: center">
            <c:if test="${not empty cost.attachment}">
                <div style="padding-top: 25px;">
                    <span><a href="${ctx}${cost.attachmentPath}"
                                   target="download">${cost.attachmentName}(点击下载)</a></span>
                </div>
            </c:if>
            <c:if test="${empty cost.attachment}">
                <div id="" style="padding-top: 25px;">没有附件</div>
            </c:if>
        </div>
    </div>
</div>
