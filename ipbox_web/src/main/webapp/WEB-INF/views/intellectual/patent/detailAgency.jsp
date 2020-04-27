<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="patentDetail">
    <c:set value="${patentDetail}" var="patent"/>
    <table style="font-size: 12px;width: 90%;" class="alter-table-v-space" cellspacing="0">

        <tr>
            <td width="25%">委案日期：</td>
            <td width="25%"><fmt:formatDate value="${patent.entrustDate}" pattern="yyyy-MM-dd" /></td>
            <td width="25%">起始阶段：</td>
            <td width="25%">
                ${dict:getEntryName('IPBOX_AGENCY_START_STAGE',patent.entrustStage)}
            </td>
        </tr>

        <tr>
            <td> 案件承办部门：</td>
            <td>${patent.entrustDept}</td>
            <td>代理人：</td>
            <td>${patent.agency}</td>
        </tr>

        <tr>
            <td> 案件处理人：</td>
            <td>${patent.entrustHandler}</td>
            <td>业务助理：</td>
            <td>${patent.assistantHandler}</td>
        </tr>

        <tr>
            <td> 备注：</td>
            <td>${patent.agencyRemark}</td>
        </tr>
    </table>
</div>