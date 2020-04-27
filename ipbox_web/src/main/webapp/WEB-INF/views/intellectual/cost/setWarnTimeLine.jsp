<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="costPayable" style="padding:10px;" class="easyui-panel" data-options="region:'center'">
    <form id="costPayableForm" name="costPayableForm" method="post" action="${ctx}/intellectual/patent/cost/monitor/updateDeadlineWarn.do">
        <table  cellpadding="2" cellspacing="2" class="alter-table-h" style="font-size: 12px;width: 90%;">
            <c:if test="${type eq 1}" >
                <tr>
                    <td>年费预警时限（天）：</td>
                    <td>
                        <input id='oneTimeLine' name='oneTimeLine' type="text" title="年费预警时限（天）" class="easyui-textbox"  data-options="required:true" value='${warnTimeLine.oneTimeLine}' />
                    </td>
                </tr>
            </c:if>
            <c:if test="${type eq 2}" >
                <tr>
                    <td>官费预警时限（天）：</td>
                    <td>
                        <input id='twoTimeLine' name='twoTimeLine' type="text" title="官费预警时限（天）" class="easyui-textbox"  data-options="required:true" value='${warnTimeLine.twoTimeLine}' />
                    </td>
                </tr>
            </c:if>
            <input id='companyId' name='companyId' type="text" style="display: none;" value='${warnTimeLine.companyId}' />
        </table>
    </form>

    <div style="text-align: center;padding-left: 40px;padding-top: 10px; padding-bottom: 5px;">
        <input type="button" value="确认" id="=subBut" onclick="submitForm()" class="easyui-linkbutton"/>
        <input type="reset"  value="关闭" id="subClose" class="easyui-linkbutton" onclick="closeMonitorWin()" />
    </div>
</div>

<script type="text/javascript">

    function submitForm(message) {

        //进行id赋值
        $("#companyId").val('');
        $('#costPayableForm').form('submit', {
            success: function (data) {
                data = JSON.parse(data);
                if (data.code === 200) {
                    /*刷新列表*/
                    refreshCostMonitor();
                    /*关闭窗口*/
                    closeMonitorWin()
                } else {
                    $.messager.alert("错误","保存失败" + data.msg);
                }
            }
        });
    }

</script>