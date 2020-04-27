<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="attachmentDetail">
    <table  style="font-size: 12px;width: 90%;" class="alter-table-v" cellspacing="0">
        <tr>
            <td>申请原文件：</td>
            <td>
                <div id="sourceFileDiv" style="">
                    <c:set var="sourceFile" value="${attachments.sourceFile}"/>
                    <c:if test="${not empty sourceFile}">
                        <c:forEach items="${sourceFile}" var="file">
                            <div class="file_name">
                                <a href="${ctx}${file.filePath}"
                                   target="download">${file.fileFullName}(点击下载)
                                </a>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </td>
        </tr>
        <tr>
            <td style="width: 20%;">受理通知书：</td>
            <td style="width: 80%;">
                <div id="authorizationFileDiv" style="">
                    <c:set var="authorizationFile" value="${attachments.authorizationFile}"/>
                    <c:if test="${not empty authorizationFile}">
                        <c:forEach items="${authorizationFile}" var="file">
                            <div class="file_name">
                                <a href="${ctx}${file.filePath}"
                                   target="download">${file.fileFullName}(点击下载)
                                </a>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </td>
        </tr>
        <tr>
            <td>专利证书：</td>
            <td>
                <div id="patentFileDiv" style="">
                    <c:set var="patentFile" value="${attachments.patentFile}"/>
                    <c:if test="${not empty patentFile}">
                        <c:forEach items="${patentFile}" var="file">
                            <div class="file_name">
                                <a href="${ctx}${file.filePath}"
                                   target="download">${file.fileFullName}(点击下载)
                                </a>
                            </div>
                        </c:forEach>
                    </c:if>
                </div>
            </td>
        </tr>
        <tr>
            <td>检索报告：</td>
            <td>
                <div id="searchFileDiv" style="">
                    <c:set var="searchFile" value="${attachments.searchFile}"/>
                    <c:if test="${not empty searchFile}">
                        <c:forEach items="${searchFile}" var="file">
                            <div class="file_name">
                                <a href="${ctx}${file.filePath}"
                                   target="download">${file.fileFullName}(点击下载)
                                </a>
                            </div>
                        </c:forEach>

                    </c:if>
                </div>
            </td>
        </tr>
        <tr>
            <td>代理合同：</td>
            <td>
                <div id="agencyFileDiv" style="">
                    <c:set var="agencyFile" value="${attachments.agencyFile}"/>
                    <c:if test="${not empty agencyFile}">
                        <c:forEach items="${agencyFile}" var="file" >
                            <div class="file_name">
                                <a href="${ctx}${file.filePath}"
                                   target="download">${file.fileFullName}(点击下载)
                                </a>
                            </div>
                        </c:forEach>
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