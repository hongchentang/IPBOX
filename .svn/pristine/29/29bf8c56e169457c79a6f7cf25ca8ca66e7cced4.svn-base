<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<div id="oauthPatentBasicDetail">
    <c:set var="patent" value="${oauthPatent}" />
    <table style="font-size: 12px;width: 90%;" class="alter-table-v" cellspacing="0">
        <tr>
            <td>
                专利名称：
            </td>
            <td colspan="3">
                ${patent.title}
            </td>
        </tr>

        <tr>
            <td>
                摘要：
            </td>
            <td colspan="3">
                ${patent.abs}
            </td>
        </tr>
        <tr>
            <td style="width: 10%;">
                专利类型
            </td>
            <td style="width: 40%;">
                ${dict:getEntryName('IPBOX_IPR_PATENT_TYPE',patent.patType)}
            </td>
            <td style="width: 10%;">
                专利权状态
            </td>
            <td style="width: 40%;">
                ${patent.lprs}
            </td>
        </tr>
        <tr>
            <td>
                申请号
            </td>
            <td>
                <c:forEach var="appNumberVal" items="${patent.appNumber}" varStatus="status">
                    <c:if test="${not empty appNumberVal}">
                        <c:if test="${status.index eq 0}">
                            ${appNumberVal}
                        </c:if>
                        <c:if test="${status.index != 0}">
                            ,${appNumberVal}
                        </c:if>
                    </c:if>
                </c:forEach>
            </td>
            <td>
                申请日
            </td>
            <td>
                ${patent.appDate}
            </td>
        </tr>
        <tr>
            <td>
                公开（公告）号
            </td>
            <td>
                <c:forEach var="pubNumberVal" items="${patent.pubNumber}" varStatus="status">
                    <c:if test="${not empty pubNumberVal}">
                        <c:if test="${status.index eq 0}">
                            ${pubNumberVal}
                        </c:if>
                        <c:if test="${status.index != 0}">
                            ,${pubNumberVal}bindWeChat
                        </c:if>

                    </c:if>
                </c:forEach>
            </td>
            <td>
                公开（公告）日
            </td>
            <td>
                ${patent.pubDate}
            </td>
        </tr>
        <tr>
            <td>
                授权日
            </td>
            <td>
                ${patent.grantDate}
            </td>
            <td>
                失效日
            </td>
            <td>
                ${patent.expireDate}
            </td>
        </tr>
        <tr>
            <td>
                申请（专利权）人
            </td>
            <td>
                <c:forEach var="applicantNameVal" items="${patent.applicantName}" varStatus="status">
                    <c:if test="${not empty applicantNameVal}">
                        <c:if test="${status.index eq 0}">
                            ${applicantNameVal}
                        </c:if>
                        <c:if test="${status.index != 0}">
                            ,${applicantNameVal}
                        </c:if>
                    </c:if>
                </c:forEach>
            </td>
            <td>
                申请人类型
            </td>
            <td>
                ${patent.applicantType}
            </td>
        </tr>
        <tr>
            <td>
                发明（设计人）
            </td>
            <td>
                <c:forEach var="inventorNameVal" items="${patent.inventorName}" varStatus="status">
                    <c:if test="${not empty inventorNameVal}">
                        <c:if test="${status.index eq 0}">
                            ${inventorNameVal}
                        </c:if>
                        <c:if test="${status.index != 0}">
                            ,${inventorNameVal}
                        </c:if>
                    </c:if>
                </c:forEach>
            </td>
            <td>
                专利权人
            </td>
            <td>
                <c:forEach var="patenteeVal" items="${patent.patentee}" varStatus="status">
                    <c:if test="${not empty patenteeVal}">
                        <c:if test="${status.index eq 0}">
                            ${patenteeVal}
                        </c:if>
                        <c:if test="${status.index != 0}">
                            ,${patenteeVal}
                        </c:if>
                    </c:if>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>
                国民经济分类
            </td>
            <td>
                <c:forEach var="necVal" items="${patent.nec}" varStatus="status">
                    <c:if test="${not empty necVal}">
                        <c:if test="${status.index eq 0}">
                            ${necVal}
                        </c:if>
                        <c:if test="${status.index != 0}">
                            ,${necVal}
                        </c:if>
                    </c:if>
                </c:forEach>
            </td>
            <td>
                主分类号
            </td>
            <td>
                ${patent.mainIpc}
            </td>
        </tr>
        <tr>
            <td>
                背景关键词
            </td>
            <td>
                <c:forEach var="bakeyVal" items="${patent.bgKey}" varStatus="status">
                    <c:if test="${not empty bakeyVal}">
                        <c:if test="${status.index eq 0}">
                            ${bakeyVal}
                        </c:if>
                        <c:if test="${status.index != 0}">
                            ,${bakeyVal}
                        </c:if>
                    </c:if>
                </c:forEach>
            </td>
            <td>
                独权关键词
            </td>
            <td>
                <c:forEach var="clKeyVal" items="${patent.clKey}" varStatus="status">
                    <c:if test="${not empty clKeyVal}">
                        <c:if test="${status.index eq 0}">
                            ${clKeyVal}
                        </c:if>
                        <c:if test="${status.index != 0}">
                            ,${clKeyVal}
                        </c:if>
                    </c:if>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <%--<td>
                摘要附图存储路径
            </td>
            <td>
                <img src="${patent.draws}">
            </td>--%>
            <td>
                分类号
            </td>
            <td colspan="3">
                <c:forEach var="ipcVal" items="${patent.ipc}" varStatus="status">
                    <c:if test="${not empty ipcVal}">
                        <c:if test="${status.index eq 0}">
                            ${ipcVal}
                        </c:if>
                        <c:if test="${status.index != 0}">
                            ,${ipcVal}
                        </c:if>
                    </c:if>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>
                地址
            </td>
            <td colspan="3">
                ${dict:resolve(patent.address, ' ', 1)}
            </td>
        </tr>
        <tr>
            <td>
                法律状态
            </td>
            <td>
                ${patent.legalStatus}
            </td>
            <td>
                代理人
            </td>
            <td>
                <c:forEach var="agentNameVal" items="${patent.agentName}" varStatus="status">
                    <c:if test="${not empty agentNameVal}">
                        <c:if test="${status.index eq 0}">
                            ${agentNameVal}
                        </c:if>
                        <c:if test="${status.index != 0}">
                            ,${agentNameVal}
                        </c:if>
                    </c:if>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>省市区</td>
            <td>
                ${dict:resolve(patent.addrProvince, ';', 1)}-${dict:resolve(patent.addrCity, ';', 1)}-${dict:resolve(patent.addrCounty, ';', 1)}
            </td>
            <td>
                主权项
            </td>
            <td>
                <c:forEach var="clVal" items="${patent.cl}" varStatus="status">
                    <c:if test="${not empty clVal}">
                        <c:if test="${status.index eq 0}">
                            ${clVal}
                        </c:if>
                        <c:if test="${status.index != 0}">
                            ,${clVal}
                        </c:if>
                    </c:if>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <td>
                优先权
            </td>
            <td>
                ${patent.priority}
            </td>
            <td>
                专利代理机构
            </td>
            <td>
                ${dict:resolve(patent.agencyName, ' ', 1)}
            </td>
        </tr>
        <tr>
            <td>
                名称关键词
            </td>
            <td>
                ${patent.titleKey}
            </td>
            <td>
                同日申请
            </td>
            <td>
                ${patent.sameApp}
            </td>
        </tr>
    </table>
    <%--    啊啊啊：${oauthPatent}--%>
</div>

<script type="text/javascript">
    $(document).ready(function () {
        // $('#bestSearchWin').window('maximize');
        // debugger
    });
</script>