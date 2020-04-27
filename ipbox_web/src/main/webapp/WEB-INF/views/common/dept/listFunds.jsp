<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes.image')}" var="fileTypes"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize"/>

	<div class="easyui-panel" title="单位受资助情况" collapsible="true">
		<table id="dgFunds"></table>
	</div>
	<div id="tb">  
    		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:alert('Add')">Add</a>  
    		<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="javascript:alert('Cut')">Cut</a>  
    		<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:alert('Save')">Save</a>  
	</div>
	
<script type="text/javascript">
tableVBg('.alter-table-v');
$(function() {  
    $('#dgFunds').datagrid({  
        title: 'Editable DataGrid',  
        iconCls: 'icon-edit',  
        width: 700,  
        //height: auto,  
        singleSelect: true,  
        idField: 'id',  
        url: '${ctx}/common/dept/listFundsGrid.do', 
        toolbar:"#tb",
        columns:[[
        	{field: 'departmentId',title: '部门 ID',width: 60},  
        	{field: 'praticularYear',title: '年份',width: 80,editor:'text'},  
        	{field: 'nationalCount',title: '国家级项目',width: 50,align: 'right',editor: 'text'},  
        	{field: 'provinceCount',title: '省级项目',width: 50,align: 'right',editor: 'text'},  
        	{field: 'cityCount',title: '市级项目',width: 50,editor: 'text'},  
        	{field: 'countiesCount',title: '县级项目',width: 50,align: 'center',editor: 'text'},  
        	{field: 'othersCount',title: '其他项目数',width: 70,align: 'center',editor: 'text'},
//             formatter: function(value, row, index) {  
//                 if (row.editing) {  
//                     var s = '<a href="#" onclick="saverow(this)">Save</a> ';  
//                     var c = '<a href="#" onclick="cancelrow(this)">Cancel</a>';  
//                     return s + c;  
//                 } else {  
//                     var e = '<a href="#" onclick="editrow(this)">Edit</a> ';  
//                     var d = '<a href="#" onclick="deleterow(this)">Delete</a>';  
//                     return e + d;  
//                 }  
//             }  
//         }
        {  
            field: 'totalCount',  
            title: '总项目数',  
            width: 50,  
            align: 'center',  
            editor: 'text'  
        },
        {  
            field: 'nationalAmount',  
            title: '国家级项目金额',  
            width: 50,  
            align: 'center',  
            editor: 'text'  
        },
        {  
            field: 'provinceAmount',  
            title: '省级项目金额',  
            width: 50,  
            align: 'center',  
            editor: 'text'  
        },
        {  
            field: 'cityAmount',  
            title: '市级项目金额',  
            width: 50,  
            align: 'center',  
            editor: 'text'  
        },
        {  
            field: 'countiesAmount',  
            title: '市级项目金额',  
            width: 50,  
            align: 'center',  
            editor: 'text'  
        },
        {  
            field: 'othersAmount',  
            title: '其他项目金额',  
            width: 50,  
            align: 'center',  
            editor: 'text'  
        },
        {  
            field: 'totalAmount',  
            title: '总项目金额',  
            width: 50,  
            align: 'center',  
            editor: 'text'  
        },
        {  
            field: 'action',  
            title: 'Action',  
            width: 70,  
            align: 'center',  
            formatter: function(value, row, index) {  
                if (row.editing) {  
                    var s = '<a href="#" onclick="saverow(this)">Save</a> ';  
                    var c = '<a href="#" onclick="cancelrow(this)">Cancel</a>';  
                    return s + c;  
                } else {  
                    var e = '<a href="#" onclick="editrow(this)">Edit</a> ';  
                    var d = '<a href="#" onclick="deleterow(this)">Delete</a>';  
                    return e + d;  
                }  
            }  
        }
        ]],  
        rownumbers:"true",
        pagination:"true",
        onBeforeEdit: function(index, row) {  
            row.editing = true;  
            updateActions(index);  
        },  
        onAfterEdit: function(index, row) {  
            row.editing = false;  
            updateActions(index);  
        },  
        onCancelEdit: function(index, row) {  
            row.editing = false;  
            updateActions(index);  
        }  
    });  	
});


function updateActions(index) {  
    $('#dgFunds').datagrid('updateRow', {  
        index: index,  
        row: {}  
    });  
} 

function getRowIndex(target) {  
    var tr = $(target).closest('tr.datagrid-row');  
    return parseInt(tr.attr('datagrid-row-index'));  
}  
function editrow(target) {  
    $('#dgFunds').datagrid('beginEdit', getRowIndex(target));  
}  
function deleterow(target) {  
    $.messager.confirm('Confirm', 'Are you sure?',  
    function(r) {  
        if (r) {  
            $('#dgFunds').datagrid('deleteRow', getRowIndex(target));  
        }  
    });  
}  
function saverow(target) {  
    $('#dgFunds').datagrid('endEdit', getRowIndex(target));  
}  
function cancelrow(target) {  
    $('#dgFunds').datagrid('cancelEdit', getRowIndex(target));  
} 
</script>
