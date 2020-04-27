<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="patentDetail">
    <c:set value="${patentDetail}" var="patent"/>
    <table style="font-size: 12px;width: 90%;" class="alter-table-v" cellspacing="0">
        <tr>
            <td style="width: 25%;">专利名称：</td>
            <td style="width: 75%;" colspan="3">${patent.patentName}</td>
        </tr>
        <tr>
            <td style="width: 25%">英文名称：</td>
            <td style="width: 40%">${patent.patentEnglishName  }</td>
            <td>专利类型：</td>
            <td>${dict:getEntryName('IPR_PATENT_TYPE',patent.patentType)}</td>
        </tr>
        <tr>
            <td >专利号/申请号：</td>
            <td >${patent.appNumber  }</td>
            <td>申请日：</td>
            <td><fmt:formatDate value="${patent.appDate }"  pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
            <td>申请人/专利权人：</td>
            <td>${patent.applyer}</td>
            <td> 申请人文号：</td>
            <td>${patent.pctApplyNumber}</td>
        </tr>
        <tr>
            <td> 申请人地址：</td>
            <td colspan="3">${patent.applyerAddress}</td>
        </tr>
        <tr>
            <td>申请国家：</td>
            <td>${dict:getEntryName('IPBOX_PATENT_AUTHOR_COUNTRY',patent.authorCountry)}</td>
            <td>法律状态：</td>
            <td>${dict:getEntryName('IPBOX_LEGAL_STATUS',patent.legalStatus)}</td>
        </tr>
        <tr>
            <td>发明人：</td>
            <td>${patent.inventor}</td>
            <td>第一发明人：</td>
            <td>${patent.firstInventor  }</td>
        </tr>

        <tr>
            <td>公开号：</td>
            <td>${patent.publicationNumber}</td>
            <td>公开日：</td>
            <td><fmt:formatDate value="${patent.publicationDate}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
            <td>公告号：</td>
            <td>${patent.announcementNumber}</td>
            <td>公告日：</td>
            <td><fmt:formatDate value="${patent.announcementDate}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
            <td>是否授权：</td>
            <td>
                <c:if test="${not empty patent.isAuthorize}">
                    ${patent.isAuthorize eq 0 ? "是":"否"}
                </c:if>
            </td>
            <td>授权日期：</td>
            <td><fmt:formatDate value="${patent.authorizeDate}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
            <td>证书号：</td>
            <td>${patent.certificateNumber}</td>
            <td>届满日：</td>
            <td><fmt:formatDate value="${patent.expirationDate}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
            <td>进入实审日：</td>
            <td><fmt:formatDate value="${patent.sseDate}" pattern="yyyy-MM-dd"/></td>
            <td>开卷日：</td>
            <td><fmt:formatDate value="${patent.openBookDate}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
            <td>优先权号：</td>
            <td>${patent.priorityNumber}</td>
            <td>优先权日：</td>
            <td><fmt:formatDate value="${patent.priorityDate}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
            <td>pct申请号：</td>
            <td>${patent.pctApplyNumber}</td>
            <td>pct公布号：</td>
            <td>${patent.pctAnnouncementNumber}</td>
        </tr>
        <tr>
            <td>pct公布人日：</td>
            <td><fmt:formatDate value="${patent.pctAnnouncementDate}" pattern="yyyy-MM-dd"/></td>
        </tr>
    </table>

</div>