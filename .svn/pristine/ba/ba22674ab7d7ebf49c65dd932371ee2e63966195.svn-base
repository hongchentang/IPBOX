<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/jsp/common/include/taglib.jsp" %>

<c:set var="formId" value="PCF${param.tabId}" />
 <c:set var="tableId" value="PCT${param.tabId}" /> 
<c:set var="tabId" value="${param.tabId}" />
<c:set var="type" value="${searchParam.paramMap.type}" />
<c:set var="status" value="${searchParam.paramMap.status}" />

<form id="${formId}" action="${ctx}/intellectual/patent/cost/monitor/list.do?tabId=${param.tabId}"
      method="post">
    <input style="display: none;" type="text" id="paramMap[type]" name="paramMap[type]" value="${searchParam.paramMap.type}" >
    <input style="display: none;" type="text" id="paramMap[status]" name="paramMap[status]" value="${searchParam.paramMap.status}" >
    <input style="display: none;" type="text" id="paramMap[companyId]" name="paramMap[companyId]" value="${searchParam.paramMap.companyId}" >
    <div class="easyui-panel" title="" data-options="" style="width: 100%; padding-top: 5px;">
        <div class="datagrid-toolbar" float:right>
            <table cellspacin="0" cellpadding="3px" class="model_interval">
                <tr>
                    <td style="width: auto;">专利名称：</td>
                    <td>
                        <input type='text' name="paramMap[patentName]" class="easyui-validatebox"
                               value="${searchParam.paramMap.patentName}">
                    </td>
                    <td style="width: auto;">专利号/申请号：</td>
                    <td>
                        <input type='text' name="paramMap[appNumber]" class="easyui-validatebox"
                               value="${searchParam.paramMap.appNumber}">
                    </td>
                    <td style="width: auto;">专利权人：</td>
                    <td>
                        <input type='text' name="paramMap[applyer]" class="easyui-validatebox"
                               value="${searchParam.paramMap.applyer}">
                    </td>
                    <td>
                        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="refreshCostMonitor()" iconCls="fa fa-search">查询</a>
                    </td>
                </tr>
            </table>

            <table cellspacing="0" cellpadding="0" class="">
                <tbody>
                <tr>
                    <c:if test="${status eq  0}">
                        <td><a onclick="changMonitorStatus(1)" href="javascript:void(0)" class="easyui-linkbutton"> 终止监控</a></td>
                    </c:if>
                    <c:if test="${status eq 1}">
                        <td><a onclick="changMonitorStatus(0)" href="javascript:void(0)" class="easyui-linkbutton"> 启动监控</a></td>
                    </c:if>
                    <td><div class="datagrid-btn-separator"></div></td>
                    <td><a onclick="refreshMonitor()" href="javascript:void(0)" class="easyui-linkbutton"> 手动刷新</a></td>
                    <td><div class="datagrid-btn-separator"></div></td>
                    <td><a onclick="setWarnLine()" href="javascript:void(0)" class="easyui-linkbutton"> 预警时间设置</a></td>
                    <td><div class="datagrid-btn-separator"></div></td>
                </tr>
                </tbody>
            </table>
        </div>

        <div>
            <table id="${tableId}" rownumbers="true" pagination="true" checkOnSelect="true"
                   selectOnCheck="true" nowrap="true" singleSelect="true" fitColumns="false">
                <thead>
                <tr>
                    <th width="30" data-options="field:'id',checkbox:true"></th>
                    <c:if test="${status eq 0}">
                        <th width="100" data-options="field:'deadDays', align:'center'">距离期限天数</th>
                    </c:if>
                    <th width="150" data-options="field:'appNumber', align:'center'">专利号/申请号</th>
                    <th width="200" data-options="field:'patentName', align:'center'">案件/专利名称</th>
                    <th width="150" data-options="field:'costName', align:'center'">费用名称</th>
                    <th width="100" data-options="field:'costStatus', align:'center'">缴费状态</th>
                    <th width="100" data-options="field:'feePayableDate', align:'center'">费用开始日期</th>
                    <th width="100" data-options="field:'feePayableDueDate', align:'center'">费用绝限日期</th>
                    <th width="100" data-options="field:'monitorStatus', align:'center'">期限状态</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="monitor" items="${list}">
                    <tr>
                            <td>${monitor.id}</td>
                            <c:if test="${status eq 0}">
                                <td>
                                    <c:if test="${type eq 1}">
                                        <c:if test="${monitor.deadDays > 30}">
                                            <div class='badge badge-green'>${monitor.deadDays}</div>
                                        </c:if>
                                        <c:if test="${monitor.deadDays <= 30 and monitor.deadDays > -30}">
                                            <div class='badge badge-red'>${monitor.deadDays}</div>
                                        </c:if>
                                        <c:if test="${monitor.deadDays <= -30 and monitor.deadDays > -180}">
                                            <div class='badge badge-yellow'>${monitor.deadDays}</div>
                                        </c:if>
                                        <c:if test="${monitor.deadDays <= -180 and monitor.deadDays > -300}">
                                            <div class='badge'>${monitor.deadDays}</div>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${type eq 2}">
                                        <c:if test="${monitor.deadDays > 0}">
                                            <div class='badge badge-red'>${monitor.deadDays}</div>
                                        </c:if>
                                        <c:if test="${monitor.deadDays <= 0}">
                                            <div class='badge'>${monitor.deadDays}</div>
                                        </c:if>
                                    </c:if>
                                </td>
                            </c:if>
                            <td>${monitor.appNumber}</td>
                            <td>${monitor.patentName}</td>
                            <td>${monitor.costName}</td>
                            <td>
                                <c:if test="${monitor.costStatus eq 0}">
                                    未缴费
                                </c:if>
                                <c:if test="${monitor.costStatus eq 1}">
                                    已缴费
                                </c:if>
                            </td>
                            <td>
                                <fmt:formatDate value="${monitor.feePayableDate}" pattern="yyyy-MM-dd"/>
                            </td>
                            <td>
                                <fmt:formatDate value="${monitor.standardDate}" pattern="yyyy-MM-dd"/>
                            </td>
                            <td>
                                <c:if test="${status eq 0}">
                                    监控中
                                </c:if>
                                <c:if test="${status eq 1}">
                                    已终止
                                </c:if>
                            </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
             <jsp:include page="/jsp/common/include/manage_page_table.jsp">
                 <jsp:param value="${formId}" name="pageForm" />
                 <jsp:param value="${tableId}" name="tableId" />
                 <jsp:param value="easyui" name="type" />
                 <jsp:param value='${tabId}' name="divId" />
             </jsp:include>
        </div>
    </div>
</form>

<style type="text/css">
    .badge {
        display: inline-block;
        padding: 2px 4px;
        font-size: 11.844px;
        font-weight: bold;
        line-height: 14px;
        color: #FFF;
        text-shadow: 0px -1px 0px rgba(0, 0, 0, 0.25);
        white-space: nowrap;
        vertical-align: baseline;
        background-color: #999;
        padding-right: 9px;
        padding-left: 9px;
        border-radius: 9px;
    }

    .badge-red {
        background-color: #B94A48;
    }

    .badge-yellow {
        background-color: #F89406;
    }

    .badge-green {
        background-color: #468847;
    }

    .badge-gray {
        background-color: #666666;
    }

</style>
<script type="text/javascript">
    $(document).ready(function () {
    });

    function changMonitorStatus(status) {
        //确定费用类型
        var table = $('#' + getTableId());
        var objects = table.datagrid('getSelections');
        var tip = '';
        if(status == 1){
            tip = '终止';
        }else if(status == 0){
            tip = '启动';
        }

        if(objects.length == 0){
            $.messager.alert('消息','请选择要终止监控的数据');
            return false;
        }
        var id = objects[0].id;

        $.ajax({
            url:'${ctx}/intellectual/patent/cost/monitor/updateSelective.do?id=' + id + '&status=' + status,
            type:'post',
            success:function(){
                $.messager.alert('提示',tip + '成功！');
                jQuery('#'+ getThisSelectTabId()).panel('refresh');
            }
        });

    }

    function refreshMonitor() {

        $.ajax({
            url:'${ctx}/intellectual/patent/cost/monitor/setMonitorFresh.do?companyId=',
            type:'GET',
            success:function(data){
                if(data.code == 200){
                    $.messager.alert('提示','已刷新！');
                    jQuery('#'+getThisSelectTabId()).panel('refresh');
                }else {
                    $.messager.alert('提示','刷新失败！');
                }
            }
        });
    }

    function setWarnLine() {
        var tabSelectType = $('#' + getThisSelectTabId()).find('input[id^="paramMap[type]"]').val();
        var companyId = '';
        openWindow('setMonitorWindow','费用预警监控设定',330,230,'${ctx}/intellectual/patent/cost/monitor/godSetMonitor.do?companyId='+ companyId +'&type=' + tabSelectType,true);
    }


    function refreshCostMonitor() {
        var formId = getFormId();
        var thirdTabId = getThisSelectTabId();
        easyuiUtils.ajaxPostFormForPanel(formId, thirdTabId)
    }
    
    function closeMonitorWin() {
        closeWindow('setMonitorWindow');
    }
</script>
