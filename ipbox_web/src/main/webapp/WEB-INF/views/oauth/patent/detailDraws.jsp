<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="attachmentDetail">
    <table  style="font-size: 12px;width: 90%;" class="alter-table-v" cellspacing="0">
        <c:if test="${type eq 1}">
            <tr>
                <td>摘要附图：</td>
                <td>
                    <div id="drawsFileDiv" style="">
                        <c:set var="file" value="${oauthPatent.draws}"/>
                        <c:if test="${not empty file}">
                            <img src="${file}">
                        </c:if>
                        <c:if test="${empty file}">
                            <div>没有这个哦!</div>
                        </c:if>
                    </div>
                </td>
            </tr>
        </c:if>
        <c:if test="${type eq 2}">
            <c:set var="drawsPics" value="${oauthPatent.drawsPic}"/>
            <c:if test="${not empty drawsPics}">
                <c:forEach varStatus="status" items="${drawsPics}" var="pic">
                    <tr>
                        <td>
                            说明书附图：第${status.index + 1}张
                        </td>
                        <td>
                            <img src="${pic}">
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty drawsPics}">
                <div>没有这个哦!</div>
            </c:if>
        </c:if>
    </table >
</div>
<style type="text/css">
    .file_name{
        padding-top: 5px;
        padding-bottom: 5px;
    }
    .file_name :hover{
        color: blue;
        text-decoration: underline;
    }

</style>