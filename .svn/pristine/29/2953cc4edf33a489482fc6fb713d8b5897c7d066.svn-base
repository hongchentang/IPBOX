<%--
  User: zhw
  Date: 2019/9/2
  Time: 9:05
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>

<div id="addAgency" style="padding-top: 10px;padding-left:0px;" class="easyui-panle" data-options="region:'center',title:''">
    <form name="agencyForm" method="post" id="agencyForm" action="${ctx}/intellectual/agency/add.do">
        <table  cellpadding="4" cellspacing="4" border="0" class="alter-table-h" style="font-size: 12px;width: 90%;">
            <tr>
                <td align="right" class='l-table-edit-td'>
                    <label for="agencyName">代理机构名称<strong style="color:red;">*</strong></label>
                </td>
                <td align="left" class='l-table-edit-td'>
                    <%--放修改时候的ID--%>
                    <input type="text" id="id" name="id" style="display: none;" value='' />

                    <input class="easyui-textbox" type="text"  data-options="required:true" id="agencyName" name="agencyName"  value='' />
                </td>
                <td align="right" class='l-table-edit-td'>代理机构编码<strong style="color: red;">*</strong></td>
                <td align="left" class='l-table-edit-td'>
                    <input id='agencyCode' name='agencyCode' type='text' title="代理机构编码" maxlength="15"  class="easyui-textbox"  data-options="required:true" value='' />
                </td>
            </tr>
            <tr>
                <td align="right" class='l-table-edit-td'>代理机构地址<strong style="color: red;">*</strong></td>
                <td align="left" class='l-table-edit-td'>
                    <input id='agencyAddress' name='agencyAddress' type='text' title="代理机构地址"  class="easyui-textbox"  data-options="" value='' />
                </td>
                <td align="right" class='l-table-edit-td'>代理机构邮箱<strong style="color: red;">*</strong></td>
                <td align="left" class='l-table-edit-td'>
                    <input id='agencyEmail' name='agencyEmail' type='text' title="代理机构邮箱"  class="easyui-textbox"  data-options="" value='' />
                </td>
            </tr>
            <tr>
                <td align="right" class='l-table-edit-td'>代理机构联系方式<strong style="color: red;">*</strong></td>
                <td align="left" class='l-table-edit-td'>
                    <input id='agencyMobile' name='agencyMobile' type='text' title="代理机构联系方式"  class="easyui-textbox"  data-options="" value='' />
                </td>
                <td align="right" class='l-table-edit-td'>代理人<strong style="color: red;">*</strong></td>
                <td align="left" class='l-table-edit-td'>
                    <input id='agencyer' name='agencyer' type='text' title="代理人"  class="easyui-textbox"  data-options="" value='' />
                </td>
            </tr>
            <tr>
                <td align="right" class='l-table-edit-td'>代理人联系方式<strong style="color: red;">*</strong></td>
                <td align="left" class='l-table-edit-td'>
                    <input id='agencyerMobile' name='agencyerMobile' type='text' title="代理人联系方式"  class="easyui-textbox"  data-options="" value='' />
                </td>
            </tr>
        </table>

        <div style="text-align: center;padding-left: 40px;padding-top: 10px; padding-bottom: 5px;">
            <input type="button" value="确认" id="=subBut" onclick="submitForm()" class="easyui-linkbutton l-btn"/>
            <input type="reset"  value="关闭" id="subClose" class="easyui-linkbutton l-btn" onclick="doCloseAgencyWindow()" />
        </div>
    </form>
</div>

<script type="text/javascript">
    
    $(document).ready(function () {

        if('${agency.id}' != null && '${agency.id}' != ''){
            $('#agencyForm').form('load',{
                agencyName:'${agency.agencyName}',
                agencyCode:'${agency.agencyCode}',
                agencyAddress:'${agency.agencyAddress}',
                agencyMobile:'${agency.agencyMobile}',
                agencyEmail:'${agency.agencyEmail}',
                agencyer:'${agency.agencyer}',
                agencyerMobile:'${agency.agencyerMobile}'
            });
        }
    });
    function submitForm() {

        //进行id赋值
        var id = "${agency.id}";
        $("#id").val(id);
        $("#addAgencyWindow,#editAgencyWindow").find('#agencyForm').form('submit', {
            success: function (data) {
                data = JSON.parse(data);
                if (data.code === 200) {
                    /*刷新列表*/
                    refreshAgency();
                    /*关闭窗口*/
                    doCloseAgencyWindow();
                } else {
                    $.messager.alert('错误','保存失败' + data.msg);
                }
            }
        });
    }
</script>
