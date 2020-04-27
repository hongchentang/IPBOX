<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div id="deptLayout" class="easyui-layout" style="width:100%;height:100%;">
    <div id="deptTrees" data-options="region:'west',panelWidth:'auto',panelHeight:'auto'" title="企业" style="width:20%;padding:10px;">
        <ul id="deptTree" style="margin-bottom: 24px"> </ul>
    </div>
    <div id="deptContent" data-options="region:'center'">
        <div id="deptDetail" data-options="border:false" style="width:100%;height:100%;">
            <h1 style="margin-top: 100px;margin-left: 40%">请点击左边的树进行操作.</h1>
        </div>
    </div>
</div>

<div id="mm" class="easyui-menu" style="width:120px;">
    <div style="color:#188DD1;" onclick="addDept()" data-options="iconCls:'fa fa-plus'">添加下级部门</div>
    <div style="color:#188DD1;" onclick="editDept()" data-options="iconCls:'fa fa-pencil'">修改当前部门</div>
    <div style="color:#188DD1;" onclick="deleteDept()" data-options="iconCls:'fa fa-minus'">删除当前部门</div>
</div>

<div id="nn" class="easyui-menu" style="width:120px;">
    <div style="color:#188DD1;" onclick="addDept()" data-options="iconCls:'fa fa-plus'">添加下级部门</div>
    <div style="color:#188DD1;" onclick="editDept()" data-options="iconCls:'fa fa-pencil'">修改公司信息</div>
</div>

<script type="text/javascript">

    $('#deptDetail',getCurrentTab()).panel({
        href:"${ctx}/common/dept/detail.do?id=${sessionScope.loginUser.companyId}"
    });

    $("#deptTree",getCurrentTab()).tree({
        data:JSON.parse('${treeStr}'),
        parentField : 'pid',
        panelWidth:'auto',
        panelHeight:'auto',
        onClick:function(node){
            clickDept(node);
        },
        onLoadSuccess:function() {
            //展开第一层区域
            var root = $("#deptTree",getCurrentTab()).tree("getRoot");
            $("#deptTree",getCurrentTab()).tree("expand",root.target);
            $("#deptTree",getCurrentTab()).tree("select",root.target);
        },
        onContextMenu: function (e, node) {
            e.preventDefault();
            $(this).tree('select',node.target);
            clickDept(node);
            var root = $("#deptTree",getCurrentTab()).tree("getRoot");
            var level = node.level;
            if(level < 3){
                if(node.id == root.id){
                    $('#nn').menu('show',{
                        left: e.pageX,
                        top: e.pageY
                    });
                }else {
                    $('#mm').menu('show',{
                        left: e.pageX,
                        top: e.pageY
                    });
                }
            }
        }
    });

    function clickDept(node) {
        var id = $("#deptTree",getCurrentTab()).tree('getSelected').id;
        $('#deptDetail',getCurrentTab()).panel("options").queryParams={};
        $('#deptDetail',getCurrentTab()).panel("refresh","${ctx}/common/dept/detail.do?id=" + id);
    }
    
    function addDept() {
        easyuiUtils.openWindow('showAddDialog','新建部门',800,300,'${ctx}/common/dept/goAdd.do',true);
    }

    function editDept() {
        var selectedData = $("#deptTree",getCurrentTab()).tree('getSelected');
        var rootData = $("#deptTree",getCurrentTab()).tree('getRoot');
        if(null!=selectedData){
            var id = selectedData.id;
            var rootId = rootData.id;
            var title = '修改部门信息';
            if(id == rootId){
                title = '修改企业信息';
            }
            easyuiUtils.openWindow('showAddDialog', title ,800,350,'${ctx}/common/dept/goAdd.do?id='+id,true);
        }
    }

    function deleteDept() {
        var selectedData = $("#deptTree",getCurrentTab()).tree('getSelected');
        var rootData = $("#deptTree",getCurrentTab()).tree('getRoot');
        if (null != selectedData) {
            if(selectedData.id !== rootData.id){
                $.messager.confirm('提示', '确定删除？', function (flag) {
                    if (flag) {
                        var id = selectedData.id;
                        var data = $.ajaxSubmitValue('${ctx }/common/dept/delete.do?id=' + id);
                        var json = jQuery.parseJSON(data);
                        var message = json.message;
                        var statusCode = json.statusCode;
                        jQuery.messager.alert("提示信息", message, "info");
                        if (parseInt(statusCode) == 200) {
                            jQuery('#' + getCurrentTabId()).panel('refresh');
                        }
                    }
                });
            }else {
                $.messager.alert("错误","公司不能删除!");
            }
        }
    }
</script>