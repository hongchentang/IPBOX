<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div style="margin-top:5px;margin-bottom: 27px">
<form id="seqForm"  action="${ctx}${currentRequestUrl}?tabId=${param['tabId']}" method="post">
    <div id="seqTableSearch">
		<div>
			<a onclick="addSeq()" href="javascript:void(0)" class="easyui-linkbutton"><span class="fa fa-plus"></span>新增</a>
			<a onclick="modifySeq()" href="javascript:void(0)" class="easyui-linkbutton"><span class="fa fa-pencil"></span>修改</a>
		</div>
	</div>
 <table id="seqTable" style="width:98%;height:auto; min-height:400px"
        toolbar="#${param['tabId']} #seqTableSearch"
        title="年度评定 "
        rownumbers="true" pagination="true" singleSelect="false" checkOnSelect="true" selectOnCheck="true" nowrap="false" fitColumns="true">
			<thead>
				<tr>
					<th data-options="field:'id',checkbox:true"></th>
					<th field="name" width="50">名称</th>
					<th width="50" data-options="field:'code'">代码</th>
					<th field="currentNul" width="50">当前序号</th>
					<th field="netNum" width="50">下一序号</th>
					<th field="step" width="50">步长</th>
				</tr>
			</thead>
			<tbody>   
     <c:forEach items="${seqs }" var="seq">
        <tr>   
           <td>${seq.id}</td>
           <td>${seq.seqName}</td>
           <td>${seq.seqCode}</td>
           <td>${seq.currentNum}</td>
           <td>${seq.nextNum}</td>
           <td>${seq.stepNum}</td>
        </tr>   
       </c:forEach> 
    </tbody>   
</table> 
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
   <jsp:param name="pageForm" value="seqForm" />
   <jsp:param name="tableId" value="seqTable" />
   <jsp:param name="type" value="easyui" />
   <jsp:param name="divId" value="${param['tabId']}" />
</jsp:include>
 </form>
 </div>
<script type="text/javascript">
var row='';
$(document).ready(function(e) {
	
});

function addSeq() {
	easyuiUtils.openWindow('showAddDialog',"新增序列",400,250,'${ctx}/common/seq/add.do',true);
}

function modifySeq() {
	var objects = getCurrentTab().find('#seqTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条记录');
		return false;
	} else {
		var id = objects[0].id;
		easyuiUtils.openWindow('showAddDialog',"编辑序列",400,320,'${ctx}/common/seq/add.do?id='+id,true);
	}
}
</script>