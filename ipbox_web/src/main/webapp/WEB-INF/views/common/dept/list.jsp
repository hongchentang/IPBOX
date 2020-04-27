<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<form id="dept_list_form" action="${ctx}/common/dept/list.do?tabId=${param['tabId']}&paramMap[regionsCode]=${searchParam.paramMap.regionsCode }" method="post">
<input type="hidden" id="regionsCode" value="${searchParam.paramMap.regionsCode }"/>
<input type="hidden" id="action" name="paramMap[action]" value=""/><!-- 区别查询还是导出，导出为：expoert -->
<div id="dept_search">
	<div style="margin: 10px">    
		机构名称:
		<input class="easyui-textbox" type="text" name="paramMap[deptName]" value="${searchParam.paramMap.deptName}"></input>
		<button type="button" class="easyui-linkbutton main-btn" onclick="easyuiQuery('dept_list_form', 'dept_list_div')" ><i class="fa fa-search"></i> 查询</button>
		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel('dept_list_div')" ><i class="fa fa-eraser"></i> 重置</a>
	</div>
	<div style="margin: 10px">
		<button type="button" onclick="showDept()" class="easyui-linkbutton"><i class="fa fa-search"></i> 查看</button>
	  	<button type="button" onclick="addDept()" class="easyui-linkbutton"><i class="fa fa-plus"></i> 新增</button>
	  	<button type="button" onclick="editDept()" class="easyui-linkbutton"><i class="fa fa-pencil"></i>修改</button>
		<button type="button" onclick="deleteDept()" class="easyui-linkbutton delete-btn"><i class="fa fa-minus"></i>注销</button>
		<button type="button" onclick="printDept()" class="easyui-linkbutton"><i class="fa fa-print"></i>打印</button>
		<button type="button" onclick="exportDept()" class="easyui-linkbutton"><i class="fa fa-excel"></i>导出结果</button>
		<button type="button" onclick="listAdmin()" class="easyui-linkbutton"><i class="fa fa-users"></i>机构所属管理员</button>
		<button type="button" onclick="listOhersDept()" class="easyui-linkbutton"><i class="fa fa-pencil"></i>机构附属情况</button>
	</div>	
</div>
 <table id="dept_list_table" style="width:98%;height:auto; min-height:400px"
 		toolbar="#${param['tabId']} #dept_list_div #dept_search" title="${areaName }列表"
        rownumbers="true" pagination="true" singleSelect="true" checkOnSelect="true" selectOnCheck="true" nowrap="false" fitColumns="true">
			<thead>
				<tr>
					<th field="id" data-options="checkbox:true">id</th>
					<th field="deptName" width="50">机构名称</th>
					<th field="deptCode" width="50">组织机构代码</th>
					<th field="deptType" width="50">机构类型</th>
					<th field="deptCategory" width="50">机构类别</th>
				</tr>
			</thead>
			<tbody>   
       	<c:forEach items="${list }" var="list">
        <tr>   
           <td>${list.id }</td>
           <td>${list.deptName }</td>
           <td>${list.deptCode }</td>
           <td>${dict:getEntryName('DEPT_TYPE',list.deptType)}</td> 
           <td>${dict:getEntryName('DEPT_CATEGORY',list.deptCategory)}</td> 
        </tr>   
       </c:forEach>
    </tbody>   
</table> 
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
	<jsp:param name="pageForm" value="dept_list_form" />
	<jsp:param name="tableId" value="dept_list_table" />
	<jsp:param name="type" value="easyui" />
	<jsp:param name="divId" value="${param['tabId']} #dept_list_div" />
</jsp:include>
 </form>
<script type="text/javascript">
$(document).ready(function(e) {
});

function addDept() {
	var regionsCode=$("#regionsCode",getCurrentTab()).val();
	easyuiUtils.openWindow('showAddDialog','新建机构',800,500,'${ctx}/common/dept/goAdd.do?paramMap[regionsCode]='+regionsCode,true);
}

function editDept() {
	var selectedData = easyuiUtils.getSelectedData('dept_list_table');
	if(null!=selectedData){
		var id = selectedData.id;
		easyuiUtils.openWindow('showAddDialog','修改机构',800,500,'${ctx}/common/dept/edit.do?dept.id='+id,true);
	}
}

function showDept() {
	var selectedData = easyuiUtils.getSelectedData('dept_list_table');
	if(null!=selectedData){
		var id = selectedData.id;
		easyuiUtils.openWindow('showAddDialog','查看机构',800,500,'${ctx}/common/dept/view.do?dept.id='+id,true);
	}
}

function deleteDept() {
	var selectedData = easyuiUtils.getSelectedData('dept_list_table');
	if(null!=selectedData) {
		$.messager.confirm('提示', '确定注销？', function(r){
			if(r) {
				var id = selectedData.id;
				var data=$.ajaxSubmitValue('${ctx }/common/dept/delete.do?id='+id);
				var json=jQuery.parseJSON(data);
				var message = json.message;
				var statusCode = json.statusCode;
				jQuery.messager.alert("提示信息",message,"info");
				if(parseInt(statusCode)==200){
					jQuery('#'+getCurrentTabId()).panel('refresh');
				}
			}
		});
	}
}

function listAdmin(){
	var selectedData = easyuiUtils.getSelectedData('dept_list_table');
	if(null!=selectedData) {
		layout_center_addTabFun({
			id : selectedData.id,
			title : selectedData.deptName+'管理员列表',
			closable : true,
			href : '${ctx}/common/user/listAdmin.do?tabId='+selectedData.id+'&paramMap[deptId]='+selectedData.id
		}); 
	}
}

function printDept() {
	var selectedData = easyuiUtils.getSelectedData('dept_list_table');
	if(null!=selectedData) {
		var id = selectedData.id;
		window.open('${ctx }/common/dept/print.do?id='+id);
	}
}

function exportDept() {
	$.messager.show({
		title:'提示',
		msg:'系统正在导出，请稍后',
		timeout:2000,
		showType:'slide'
	});
	$("#dept_list_form").find(":hidden[id='action']").val("export");
	jQuery('#dept_list_form').form('submit',{
	    success: function(data){}
	}); 
	$("#dept_list_form").find(":hidden[id='action']").val("");
}

function listOhersDept(){
	var selectedData = easyuiUtils.getSelectedData('dept_list_table');
	easyuiUtils.openWindow('showAddDialog','完善机构信息',900,630,'${ctx}/common/dept/tabListDetails.do?tabId='+selectedData.id+'&paramMap[departmentId]='+selectedData.id,true);
}
</script>