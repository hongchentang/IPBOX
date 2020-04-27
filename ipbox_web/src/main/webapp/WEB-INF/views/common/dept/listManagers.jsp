<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes.image')}" var="fileTypes"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize"/>

	
<!-- 	<div id="tb">   -->
<!--     		<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="javascript:alert('Add')">Add</a>   -->
<!--     		<a href="#" class="easyui-linkbutton" iconCls="icon-cut" plain="true" onclick="javascript:alert('Cut')">Cut</a>   -->
<!--     		<a href="#" class="easyui-linkbutton" iconCls="icon-save" plain="true" onclick="javascript:alert('Save')">Save</a>   -->
<!-- 	</div> -->
	<div class="easyui-panel" title="单位管理人信息" collapsible="true">
		<table id="dgManagers"></table>
	</div>
	
<script type="text/javascript">
tableVBg('.alter-table-v');
$(function() { 
	var datagrid; //定义全局变量datagrid
    var editRow = undefined; //定义全局变量：当前编辑的行
    datagrid =$('#dgManagers').datagrid({  
        title: '',  
        iconCls: 'icon-edit',  
        width: '700px',  
        //height: auto, 
        rownumbers:"true",
        pagination:"true",
        singleSelect: true,  
        idField: 'id',  
        url: '${ctx}/common/dept/listFundsGrid.do', 
        columns:[[
        			{field: 'ID', title: '编号',sortable: true, checkbox: true},
                	{field: 'departmentId',title: '部门 ID',hidden:'true'},  
                	{field: 'manType',title: '人员类型',width: '15%',align: 'center',editor: {  
                        type: 'combobox',  
                        options:{  
        	                 valueField: 'dictValue',
        	                 textField: 'dictName',  
        	                 editable: false,
        	                 method: 'get',  
        	                 url: '${ctx}/common/datadict/getDictName.do?paramMap[dictTypeCode]=IPR_TECHNOLOGY_TYPE',
         	                 required: true  
        	             	}  
        				}  
                	},  
                	{field: 'manName',title: '姓名',width: '15%',align: 'center',editor: 'text'},  
                	{field: 'manEducation',title: '学历',width: '15%',align: 'center',editor: 'text'},  
                	{field: 'manCardType',title: '证件类型',width: '15%',align: 'center',editor:{
                		type: 'combobox',  
                        options:{  
        	                 valueField: 'dictValue',
        	                 textField: 'dictName',  
        	                 method: 'get',  
        	                 url: '${ctx}/common/datadict/getDictName.do?paramMap[dictTypeCode]=PAPERWORK_TYPE',
         	                 required: true  
        	             	}  
        				}
                	},
                	{field: 'manCardNo',title: '证件号',width: '20%',align: 'center',editor: 'text'}, 
                	{field: 'manJob',title: '职务',width: '15%',align: 'center',editor: 'text'}
        ]], 
        toolbar: [{ text: '添加', iconCls: 'icon-add', handler: function () {//添加列表的操作按钮添加，修改，删除等
            //添加时先判断是否有开启编辑的行，如果有则把开户编辑的那行结束编辑
            if (editRow != undefined) {
                datagrid.datagrid("endEdit", editRow);
            }
            //添加时如果没有正在编辑的行，则在datagrid的第一行插入一行
            if (editRow == undefined) {
                datagrid.datagrid("insertRow", {
                    index: 0, // index start with 0
                    row: {

                    }
                });
                //将新插入的那一行开户编辑状态
                datagrid.datagrid("beginEdit", 0);
                //给当前编辑的行赋值
                editRow = 0;
            }

        }
        }, '-',
         { text: '删除', iconCls: 'icon-remove', handler: function () {
             //删除时先获取选择行
             var rows = datagrid.datagrid("getSelections");
             //选择要删除的行
             if (rows.length > 0) {
                 $.messager.confirm("提示", "你确定要删除吗?", function (r) {
                     if (r) {
                         var ids = [];
                         for (var i = 0; i < rows.length; i++) {
                             ids.push(rows[i].ID);
                         }
                         //将选择到的行存入数组并用,分隔转换成字符串，
                         //本例只是前台操作没有与数据库进行交互所以此处只是弹出要传入后台的id
                         alert(ids.join(','));
                     }
                 });
             }
             else {
                 $.messager.alert("提示", "请选择要删除的行", "error");
             }
         }
         }, '-',
         { text: '修改', iconCls: 'icon-edit', handler: function () {
             //修改时要获取选择到的行
             var rows = datagrid.datagrid("getSelections");
             //如果只选择了一行则可以进行修改，否则不操作
             if (rows.length == 1) {
                 //修改之前先关闭已经开启的编辑行，当调用endEdit该方法时会触发onAfterEdit事件
                 if (editRow != undefined) {
                     datagrid.datagrid("endEdit", editRow);
                 }
                 //当无编辑行时
                 if (editRow == undefined) {
                     //获取到当前选择行的下标
                     var index = datagrid.datagrid("getRowIndex", rows[0]);
                     //开启编辑
                     datagrid.datagrid("beginEdit", index);
                     //把当前开启编辑的行赋值给全局变量editRow
                     editRow = index;
                     //当开启了当前选择行的编辑状态之后，
                     //应该取消当前列表的所有选择行，要不然双击之后无法再选择其他行进行编辑
                     datagrid.datagrid("unselectAll");
                 }
             }
         }
         }, '-',
         { text: '保存', iconCls: 'icon-save', handler: function () {
             //保存时结束当前编辑的行，自动触发onAfterEdit事件如果要与后台交互可将数据通过Ajax提交后台
             datagrid.datagrid("endEdit", editRow);
         }
         }, '-',
         { text: '取消编辑', iconCls: 'icon-redo', handler: function () {
             //取消当前编辑行把当前编辑行罢undefined回滚改变的数据,取消选择的行
             editRow = undefined;
             datagrid.datagrid("rejectChanges");
             datagrid.datagrid("unselectAll");
         }
         }, '-'],
        onAfterEdit: function (rowIndex, rowData, changes) {
            //endEdit该方法触发此事件
            console.info(rowData);
            for(i = 0;i < rows.length;i++)
            {
               entities = entities  + JSON.stringify(rows[i]);  
            }
            $.ajax({
            	url: getRootPath()+'/labour/update.do',
                type: post,
                async: true,
                dataType: 'json',
                data: {'entities': entities},
                success: function (data) {
                    if(data.message==操作成功！){
                           alert(data.message);
                       }else{
                           alert(data.message);
                           return;
                       }
                    $('#dg').datagrid('reload');
                }
            });

            editRow = undefined;
        },
        onDblClickRow: function (rowIndex, rowData) {
        //双击开启编辑行
            if (editRow != undefined) {
                datagrid.datagrid("endEdit", editRow);
            }
            if (editRow == undefined) {
                datagrid.datagrid("beginEdit", rowIndex);
                editRow = rowIndex;
            }
        }, 
    });  	
});


// function updateActions(index) {  
//     $('#dgManagers').datagrid('updateRow', {  
//         index: index,  
//         row: {}  
//     });  
// } 

// function getRowIndex(target) {  
//     var tr = $(target).closest('tr.datagrid-row');  
//     return parseInt(tr.attr('datagrid-row-index'));  
// }  
// function editrow(target) {  
//     $('#dgManagers').datagrid('beginEdit', getRowIndex(target));  
// }  
// function deleterow(target) {  
//     $.messager.confirm('Confirm', 'Are you sure?',  
//     function(r) {  
//         if (r) {  
//             $('#dgManagers').datagrid('deleteRow', getRowIndex(target));  
//         }  
//     });  
// }  
// function saverow(target) {  
//     $('#dgManagers').datagrid('endEdit', getRowIndex(target));  
// }  
// function cancelrow(target) {  
//     $('#dgManagers').datagrid('cancelEdit', getRowIndex(target));  
// } 
</script>









