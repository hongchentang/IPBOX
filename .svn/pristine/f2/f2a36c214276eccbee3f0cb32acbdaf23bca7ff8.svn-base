<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes.image')}" var="fileTypes"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize"/>
<form id="deptForm" name="deptForm" action="${ctx}/common/dept/save.do" method="post" enctype="multipart/form-data">
	
	<div class="easyui-panel" title="单位知识产权情况" collapsible="true">
		<table id="dgIntellectuals"></table>
	</div>
	<div id="tb">  
    		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:alert('Add')">Add</a>  
    		<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="javascript:alert('Cut')">Cut</a>  
    		<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:alert('Save')">Save</a>  
	</div>

<script type="text/javascript">
tableVBg('.alter-table-v');
$(function() {  
    $('#dgIntellectuals').datagrid({  
        title: 'Editable DataGrid',  
        iconCls: 'icon-edit',  
        width: 700,  
        //height: auto,  
        singleSelect: true,  
        idField: 'id',  
        url: '${ctx}/common/dept/listIntellectualsGrid.do', 
        toolbar:"#tb",
        columns:[[
        	{field: 'departmentId',title: '部门 ID',width: 60},  
        	{field: 'praticularYear',title: '年份',width: 80,editor:'text'},  
        	{field: 'applyTotalCount',title: '申请总数',width: 50,align: 'right',editor: 'text'},  
        	{field: 'authorizeTotalCount',title: '中授权数',width: 50,align: 'right',editor: 'text'},  
        	{field: 'patentApply',title: '专利申请数',width: 50,editor: 'text'},  
        	{field: 'patentAuthorize',title: '专利授权数',width: 50,align: 'center',editor: 'text'},  
        {  
            field: 'action',  
            title: '操作',  
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
    $('#dgIntellectuals').datagrid('updateRow', {  
        index: index,  
        row: {}  
    });  
} 

function getRowIndex(target) {  
    var tr = $(target).closest('tr.datagrid-row');  
    return parseInt(tr.attr('datagrid-row-index'));  
}  
function editrow(target) {  
    $('#dgIntellectuals').datagrid('beginEdit', getRowIndex(target));  
}  
function deleterow(target) {  
    $.messager.confirm('Confirm', 'Are you sure?',  
    function(r) {  
        if (r) {  
            $('#dgIntellectuals').datagrid('deleteRow', getRowIndex(target));  
        }  
    });  
}  
function saverow(target) {  
    $('#dgIntellectuals').datagrid('endEdit', getRowIndex(target));  
}  
function cancelrow(target) {  
    $('#dgIntellectuals').datagrid('cancelEdit', getRowIndex(target));  
} 
</script>
