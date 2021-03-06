<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>

<form id="listPatentForm" action="${ctx}/intellectual/patent/list.do?tabId=${param.tabId}" method="post">
	<div class="easyui-panel" title="" data-options="" style="width:100%;">
		<div class="datagrid-toolbar" float:right>
			<table cellspacin="0" cellpadding="3px" class="model_interval">
				<tr>
					<td style="width: auto;">专利名称：</td>
					<td><input type='text' name="paramMap[patentName]" class="easyui-validatebox"  value="${searchParam.paramMap.patentName}"></td>
					<td style="width: auto;">专利号/申请号：</td>
					<td><input type='text' name="paramMap[appNumber]" class="easyui-validatebox"  value="${searchParam.paramMap.appNumber}"></td>
					<td style="width: auto;">专利权人：</td>
					<td><input type='text' name="paramMap[applyer]" class="easyui-validatebox"  value="${searchParam.paramMap.applyer}"></td>
					<%--<td><input type='text' style="display: initial;" id="pagination.isChange" name="pagination.isChange" value="false"></td>--%>

				<%--<td style="width: auto;">申请日：</td>
					<td><input name="paramMap[appDate]" type="text" class="easyui-validatebox Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" value="${searchParam.paramMap.appDate}"/></td>
					<td style="width: auto;">发明人：</td>
					<td><input type='text' name="paramMap[inventor]" class="easyui-validatebox"  value="${searchParam.paramMap.inventor}"></td>--%>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="refreshPatent()" iconCls="fa fa-search">查询</a>
					</td>
				</tr>
			</table>
			<table cellspacing="0" cellpadding="0" class="">
				<tbody>
					<tr>
					    <td><a onclick="imports()" href="javascript:void(0)" class="easyui-linkbutton">导入</a></td>
					    <td><div class="datagrid-btn-separator"></div></td>
					      <td> <a onclick="outsub()" href="javascript:void(0)" class="easyui-linkbutton" plain="false" > 导出</a></td>
					    <td><div class="datagrid-btn-separator"></div></td>
					    <td><a onclick="load()" href="javascript:void(0)" class="easyui-linkbutton"><i class="easyui-linkbutton">下载导入模板</i></a></td>
					    <td><div class="datagrid-btn-separator"></div></td>
				        <td><a onclick="addPatentFee()" href="javascript:void(0)" class="easyui-linkbutton"><i class="">标准费用生成</i></a></td>
						<td><div class="datagrid-btn-separator"></div></td>
						<td><a onclick="refreshLegalStatus()" href="javascript:void(0)" class="easyui-linkbutton"><i class="">更新法律状态</i></a></td>
						<td><div class="datagrid-btn-separator"></div></td>
						<td><a onclick="downloadFile()" href="javascript:void(0)" class="easyui-linkbutton"><i class="">下载上传文件</i></a></td>
						<td><div class="datagrid-btn-separator"></div></td>
						<td><a onclick="addPatent()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-plus"> 新增</i></a></td>
						<td><div class="datagrid-btn-separator"></div></td>
						<td><a onclick="viewPatent()" href="javascript:void(0)" class="easyui-linkbutton">查看</a></td>
						<td><div class="datagrid-btn-separator"></div></td>
						<td><a onclick="editPatent()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-pencil"> 修改</i></a></td>
						<td><div class="datagrid-btn-separator"></div></td>
						<td><a onclick="deletePatent()" href="javascript:void(0)" class="easyui-linkbutton delete-btn"><i class="fa fa-minus"> 删除</i></a></td>
						<td><div class="datagrid-btn-separator"></div></td>
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<table id="listPatentTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="true" singleSelect="false" fitColumns="false" >
				<thead>
					<tr>
						<th width="30" data-options="field:'id',checkbox:true"></th>
						<th width="300" data-options="field:'patentName', align:'center'">专利名称</th>
						<th width="150" data-options="field:'appNumber', align:'center'">专利号/申请号</th>
						<th width="100" data-options="field:'appDate', align:'center'">申请日期</th>
						<th width="200" data-options="field:'applyer', align:'center'">申请人/专利权人</th>
						<th width="200" data-options="field:'inventor', align:'center'">发明人</th>
						<th width="100" data-options="field:'patentType', align:'center'">专利类型</th>
						<th width="100" data-options="field:'authorCountry', align:'center'">申请国家</th>
						<th width="100" data-options="field:'legalStatus', align:'center'">法律状态</th>
					</tr>                                 
				</thead>
				<tbody>
					<c:forEach items="${listPatent}" var="patents">
						<tr>
							<td>${patents.id }</td>
							<td >                                                                      
								<div style="width:100%; word-break: break-all;"><c:if test="${patents.isCost =='Y'}"><img src="/themes/easyui/themes/icons/ok.png" title="说明：专利费用已生成"  /></c:if>  ${patents.patentName}</div>
							</td>
							<td>${patents.appNumber }</td>
							<td><fmt:formatDate value="${patents.appDate }"  pattern="yyyy-MM-dd"/></td>
							<td>${patents.applyer }</td>
							<td>${patents.inventor }   </td>
							<td>${dict:getEntryName('IPR_PATENT_TYPE',patents.patentType)}</td>
							<td>${dict:getEntryName('IPBOX_PATENT_AUTHOR_COUNTRY',patents.authorCountry)}</td>
							<td>${dict:getEntryName('IPBOX_LEGAL_STATUS', patents.legalStatus) }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<jsp:include page="/jsp/common/include/manage_page_table.jsp">
				<jsp:param value="listPatentForm" name="pageForm" />
				<jsp:param value="listPatentTable" name="tableId" />
				<jsp:param value="easyui" name="type" />
				<jsp:param value='${param.tabId}' name="divId" />
			</jsp:include>
		</div>
	</div>
</form>

<div style="display: none;">
	<form id="outsubForm" action="${ctx}/intellectual/patent/outsub.do" method="post">
		<input type="text" id="selectIds" name="paramMap[selectIds]" value="">
	</form>
</div>

<style type="text/css">
	.model_interval{
		padding-top: 5px;
		padding-bottom: 5px;
	}
	.xu_interval{
		padding-right: 5px;
	}
	.textCenter{
		text-align: center;
	}
</style>

<script>

$(document).ready(function () {
	createPatentListTooltip();
});

$('input').change(function () {
	var currentTab = getCurrentTab();
	var isChange = currentTab.find('input[id^="pagination.isChange"]');
	isChange.val("true");
	
});

function addPatent(){
	openWindow('addPatentWindow','新增专利数据',800,600,'${ctx}/intellectual/patent/goAddPatent.do',true);
}

function downloadFile() {

	var objects = $('#listPatentTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	}else if(objects.length > 1){
		$.messager.alert('消息','不能同时操作多条数据');
		return false;
	}

	var id = objects[0].id;
	var urls = [];
	$.ajax({
		url: '${ctx}/intellectual/patent/getAllFile.do?id=' + id,
		method: 'get',
		type: 'json',
		async: false,
		success: function (data) {
			if(data.code == '200'){
				if(data.data.length > 0){
					urls = data.data;
				}else {
					alert("没有可下载的文件");
				}
			}else {
				alert("出错了：" + data.msg);
		}
		},
		error: function (data) {
			alert("系统错误！");
		}
	});

	
	for (var index = 0; index < urls.length; index++) {
		
		var fileName = "第" + index + "个文件";
		download(fileName, urls[index]);
	}
}

function imports(){
	openWindow('importPatentWindow','导入专利数据',800,600,'${ctx}/intellectual/patent/goimportPatent.do',true);
}

function editPatent(){
	var objects = $('#listPatentTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('editPatentWindow','编辑专利数据',800,600,'${ctx}/intellectual/patent/goAddPatent.do?id='+id,true);
}

function viewPatent(){
	var objects = $('#listPatentTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时查看多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('viewPatentWindow','查看专利信息',800,600,'${ctx}/intellectual/patent/detail.do?id='+id,true);
}

function deletePatent(){
		var objects = $('#listPatentTable').datagrid('getSelections');
		if(objects.length == 0){
			$.messager.alert('消息','请选择要删除的数据');
			return false;
		}
		var idStr = '';
		$.each(objects, function (idx, val) {
			if (idStr == ''){
				idStr = val.id;
			}else {
				idStr = idStr + '-' + val.id;
			}
		});

		var queryData = 'idStr=' + idStr;
		$.messager.confirm('确认','确定想要删除选中的记录吗？', function(flag){
			if(flag){
				$.ajax({
					url:'${ctx}/intellectual/patent/deletePatent.do',
					type:'post',
					data: queryData,
					success:function(){
						$.messager.alert('提示','操作成功！');
						jQuery('#'+getCurrentTabId()).panel('refresh');
					}
				});
			}
		});
}
function addPatentFee(){
	var objects = $('#listPatentTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('提示','请选择要操作的数据');
		return false;
	}
	var ids = new Array(objects.length);
	$.each(objects, function (idx, obj) {
		ids[idx] = obj.id;
	});
	$.messager.confirm('确认','确定生成费用？', function(flag){
		if(flag){
			$.ajax({
				url:'${ctx}/intellectual/patent/addPatentFee.do',
				type:'POST',
				async : true,
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(ids),
				dataType:'json',
				success:function(data){
					if(data.code == 200){
						var successStr = '';
						var failStr = '';
						$.each(data.data.successPatents, function (idx, appNumber) {
							successStr += appNumber + ' <br>';
						});

						$.each(data.data.failPatents, function (idx, appNumber) {
							failStr += appNumber + ' <br>';
						});
						var resultView = "操作成功！<br> 成功的专利：<br>" + successStr + "<br> 失败的专利（重复生成）：<br>" + failStr;
						$.messager.alert('提示', resultView);
						jQuery('#'+getCurrentTabId()).panel('refresh');
					}else {
						$.messager.alert('错误', data.msg);
						jQuery('#'+getCurrentTabId()).panel('refresh');
					}
				},
				error: function() {
					$.messager.alert('错误提示','接口报错！');
					jQuery('#'+getCurrentTabId()).panel('refresh');
				}
			});
		}
	});
}
/*关闭子窗口*/
function doClose(){
	closeWindow("addPatentWindow");
	closeWindow("editPatentWindow");
	closeWindow("viewPatentWindow");
}

/*刷新*/
function refreshPatent() {
	console.log(">>>>费用刷新V1！");
	var tabId = $("#layout_center_tabs").tabs('getSelected').attr('id');
	easyuiUtils.ajaxPostFormForPanel('listPatentForm', tabId);
}

function createPatentListTooltip() {

	var bg = $('#listPatentTable');
	var url  = '${ctx}/intellectual/patent/detailInfo.do';

	//createTooltip(bg, url, 800, 600);
}

function refreshLegalStatus() {
	var objects = $('#listPatentTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	}

	var ids = new Array(objects.length);
	$.each(objects, function (idx, obj) {
		ids[idx] = obj.id;
	});

	$.messager.confirm('确认','确定更新法律状态？', function(flag){
		if(flag){
			$.ajax({
				url:'${ctx}/intellectual/patent/refreshLegalStatus.do',
				type:'POST',
				async : true,
				contentType: "application/json; charset=utf-8",
				data: JSON.stringify(ids),
				dataType:'json',
				success:function(data){

					$.messager.alert('提示','操作成功！');
					jQuery('#'+getCurrentTabId()).panel('refresh');
				},
				error: function() {
					$.messager.alert('错误');
					jQuery('#'+getCurrentTabId()).panel('refresh');
				}
			});
		}
	});
}

function test() {
	$.ajax({
		url: '${ctx}/intellectual/patent/test2.do',
		method: 'get',
		dataType: 'json',
		success: function (data) {
			if(data.code == 200){
				var arr = data.data;
				$.each(arr, function (i, item) {
					console.log("--->" + item.id + "<---" + item.patentName);
				});
			}else {

			}
		},
		error: function (data) {

		}
	});
}


function load(){
	window.open("patent_template.xls");
}

function outsub(){

	var objects = $('#listPatentTable').datagrid('getSelections');

	var idStr = '';
	$.each(objects, function (idx, obj) {
		if(idStr == ''){
			idStr = idStr + obj.id;
		}else {
			idStr = idStr + ',' + obj.id;
		}
	});
	debugger
	$('#selectIds').val(idStr);
	console.log('-->' + $('#selectIds').val());
	$('#outsubForm').submit();
}

/*function downPatentFile(url, method){
	// 获得url和data
	
	if( url ){
		// request发送请求
		jQuery('<form action="${ctx}'+ url +'" method="'+ (method||'post') +'"></form>')
				.appendTo('body').submit().remove();
	}
}*/

function download(name, href) {
	
	//创建a标签
	var a = document.createElement("a"),
	//创建鼠标事件对象
	e = document.createEvent("MouseEvents");
	//初始化事件对象
	e.initEvent("click", false, false);
	//设置下载地址
	a.href = href;
	//设置下载文件名
	a.download = name;
	//给指定的元素，执行事件click事件
	a.dispatchEvent(e);
}


</script>