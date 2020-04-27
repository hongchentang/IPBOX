<%--
  User: zhw
  Date: 2019/9/3
  Time: 9:16
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>

<c:set var="monitorId" value="viewPatentCostMonitor1" />
<c:set var="gMonitorId" value="viewPatentCostMonitor2" />
<c:set var="deadMonitorId" value="viewPatentCostDeadMonitor1" />
<c:set var="gDeadMonitorId" value="viewPatentCostDeadMonitor2" />
<c:set var="unPayId" value="viewPatentCostUnPay${type}" />
<c:set var="payId" value="viewPatentCostPay${type}" />
<c:set var="paySummaryId" value="viewPatentCostPaySummary${type}" />

 
<div id="viewPatentCostPanel" class="easyui-panel" data-options="" style="height: 95%">
    <div id="viewPatentCostTopTabs" class="easyui-tabs" data-options="tabPosition:'top',height:getHeight('viewPatentCostPanel',100),cache:false">
        <c:if test="${type eq 1 or type eq 2}">
            <div id="${monitorId}" data-options="href:'${ctx}/intellectual/patent/cost/monitor/list.do?paramMap[type]=1&tabId=${monitorId}&paramMap[status]=0'"
                 title="年费监控"></div>
            <div id="${gMonitorId}" data-options="href:'${ctx}/intellectual/patent/cost/monitor/list.do?paramMap[type]=2&tabId=${gMonitorId}&paramMap[status]=0'"
                 title="其他官费监控"></div>
            <div id="${deadMonitorId}" data-options="href:'${ctx}/intellectual/patent/cost/monitor/list.do?paramMap[type]=1&tabId=${deadMonitorId}&paramMap[status]=1'"
                 title="年费终止列表"></div>
            <div id="${gDeadMonitorId}" data-options="href:'${ctx}/intellectual/patent/cost/monitor/list.do?paramMap[type]=2&tabId=${gDeadMonitorId}&paramMap[status]=1'"
                 title="其他官费终止列表"></div>
        </c:if>
        <div id="${unPayId}" data-options="href:'${ctx}/intellectual/patent/cost/list.do?paramMap[type]=${type}&tabId=${unPayId}&paramMap[status]=0'"
             title="${typeName}待缴清单"></div>
        <div id="${payId}" data-options="href:'${ctx}/intellectual/patent/cost/list.do?paramMap[type]=${type}&tabId=${payId}&paramMap[status]=1'"
             title="${typeName}已缴清单"></div>
        <div id="${paySummaryId}" data-options="href:'${ctx}/intellectual/patent/cost/list.do?paramMap[type]=${type}&tabId=${paySummaryId}&paramMap[status]=-1'"
             title="${typeName}汇总清单"></div>
    </div>

    <input id="type" type="text" style="display: none;" value="${type}">
    <input id="typeName" type="text" style="display: none;" value="${typeName}">
</div> 

<script type="text/javascript">

    function getType() {
    	
        var tabId = getCurrentTabId();
        var type = $('#' + tabId).find("div[id^=viewPatentCostPanel]").find('input[id^=type]').val();
        return parseInt(type);
    }

    function getTypeName() {
        var tabId = getCurrentTabId();
        return  $('#' + tabId).find("div[id^=viewPatentCostPanel]").find('input[id^=typeName]').val();
    }

    function getThisSelectTabId() {
        var tabId = getCurrentTabId();
        return  $('#' + tabId).find('div[id^=viewPatentCostTopTabs]').tabs('getSelected').attr('id');
    }

    function getFormId() {
        var tabId = getCurrentTabId();
        var thirdTab = $('#' + tabId).find('div[id^=viewPatentCostTopTabs]').tabs('getSelected');
        return  thirdTab.find('form').attr('id');
    }

    function getTableId() {
        var tabId = getCurrentTabId();
        var thirdTab = $('#' + tabId).find('div[id^=viewPatentCostTopTabs]').tabs('getSelected');
        var formId = thirdTab.find('form').attr('id');
        return formId.replace('PCF', 'PCT', formId);
    }

    function setType(type) {
        var tabId = getCurrentTabId();
        $('#' + tabId).find("div[id^=viewPatentCostPanel]").find('input[id^=type]').val(type);
        var typeName = '';
        if(type == 0){
            typeName = '代理服务费';
        }else if(type == 1 || type == 2){
            typeName = '官费';
        }else if(type == 3){
            typeName = '其他费用';
        }
        $('#' + tabId).find("div[id^=viewPatentCostPanel]").find('input[id^=typeName]').val(typeName);
    }
</script>