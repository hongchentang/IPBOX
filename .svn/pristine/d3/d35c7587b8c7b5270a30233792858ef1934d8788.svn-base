<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="attachmentDetail">
    <table  style="font-size: 12px;width: 90%;" class="alter-table-v" cellspacing="0">
        <tr>
            <td>外观图：</td>
            <td>
                <div id="drawsFileDiv" style="">
                    <c:set var="file" value="${url}"/>
                    <c:if test="${not empty file}">
                        <img src="${file}">
                    </c:if>
                    <c:if test="${empty file}">
                        <div>没有这个哦!</div>
                    </c:if>
                </div>
            </td>
        </tr>
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