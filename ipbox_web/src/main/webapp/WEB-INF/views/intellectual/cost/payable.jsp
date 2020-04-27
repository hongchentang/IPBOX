<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="costPayable" style="padding:10px;" class="easyui-panel" data-options="region:'center'">
    <form id="costPayableForm" name="costPayableForm" method="post" action="${ctx}/intellectual/patent/cost/payable.do">
        <table  cellpadding="2" cellspacing="2" class="alter-table-h" style="font-size: 12px;width: 90%;">
            <tr>
                <td>实缴金额*</td>
                <td>
                    <input id='paymentAmount' name='paymentAmount' type="text" title="实缴金额" class="easyui-textbox"  data-options="required:true" value='' />
                    <input id='patentCostId' name='patentCostId' type="text" style="display: none;" value='' />
                </td>
            </tr>
            <tr>
                <td>实缴日期*</td>
                <td>
                    <input id='paymentDate' name='paymentDate' type="text" onclick="WdatePicker({ dateFmt: 'yyyy-MM-dd'});"
                           class="easyui-validatebox Wdate" data-options="required:true" title="实缴日期"  value='' />
                </td>
            </tr>
            <tr>
                <td>费用填报人</td>
                <td>
                    <input id='paymenter' name='paymenter' type="text" title="费用填报人" class="easyui-textbox"  data-options="required:true" value='' />
                </td>
            </tr>
        </table>
    </form>

    <div style="text-align: center;padding-left: 40px;padding-top: 10px; padding-bottom: 5px;">
        <input type="button" value="确认" id="=subBut" onclick="submitForm()" class="easyui-linkbutton"/>
        <input type="reset"  value="关闭" id="subClose" class="easyui-linkbutton" onclick="doClosePayable()" />
    </div>
</div>

<script type="text/javascript">

    function submitForm(message) {

        //进行id赋值
        var id = "${id}";
        $("#patentCostId").val(id);
        $('#costPayableForm').form('submit', {
            success: function (data) {
                data = JSON.parse(data);
                if (data.code === 200) {
                    /*刷新列表*/
                    refreshPatentCost();
                    /*关闭窗口*/
                    doClosePayable();
                } else {
                    $.messager.alert("错误","保存失败" + data.msg);
                }
            }
        });
    }

    function doClosePayable() {
        closeWindow("patentCostPayableWindow");
    }

</script>