<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>

<form id="listTrademarkForm" action="${ctx}/intellectual/tradeMark/list.do?tabId=${param.tabId}" method="post">
	<div class="easyui-panel" title="" data-options="" style="width:100%;">
		<div class="datagrid-toolbar" float:right>
			<table cellspacin="0" cellpadding="3px" class="model_interval">
				<tr>
					<td style="width: auto;">商标名称：</td>
					<td><input type='text' name="paramMap[registeredName]" class="easyui-validatebox"  value="${searchParam.paramMap.registeredName}"></td>
					<td style="width: auto;">商标注册号：</td>
					<td><input type='text' name="paramMap[registeredNumber]" class="easyui-validatebox"  value="${searchParam.paramMap.registeredNumber}"></td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" onclick="easyuiUtils.ajaxPostFormForPanel('listTrademarkForm','${param.tabId}')" iconCls="fa fa-search">查询</a>
					</td>
				</tr>
			</table>
			<table cellspacing="0" cellpadding="0" class="">
				<tbody>
					<tr>
						
						<td><a onclick="addTrademark()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-plus"> 新增</i></a></td>
						<td><div class="datagrid-btn-separator"></div></td>						
						<td><a onclick="deleteTrademark()" href="javascript:void(0)" class="easyui-linkbutton delete-btn"><i class="fa fa-minus"> 删除</i></a></td>
						<td><div class="datagrid-btn-separator"></div></td>
						<td><a onclick="editTrademark()" href="javascript:void(0)" class="easyui-linkbutton"><i class="fa fa-pencil"> 修改</i></a></td>
						<td><div class="datagrid-btn-separator"></div></td>
						<td><a onclick="viewTrademark()" href="javascript:void(0)" class="easyui-linkbutton">查看</a></td>
						<td><div class="datagrid-btn-separator"></div></td>
						
					</tr>
				</tbody>
			</table>
		</div>
		<div>
			<table id="listTrademarkTable" rownumbers="true" pagination="true" checkOnSelect="true" selectOnCheck="true" nowrap="false" singleSelect="true">
				<thead>
					<tr>
						<th width="30" data-options="field:'id',checkbox:true"></th>
						<th width="100" data-options="field:'registeredName'">商标名称</th>
						<%--<th width="100" data-options="field:'registerType'">商标注册类型</th>--%>
						<th width="100" data-options="field:'registeredNumber'">申请号/注册号</th>
						<th width="80" data-options="field:'classify'">国际分类</th>
						<th width="80" data-options="field:'classifyLike'">类似群</th>
						<th width="80" data-options="field:'firstPublicNumber'">初审公告号</th>
						<th width="100" data-options="field:'firstPublicDate'">初审公告日期</th>
						<%--<th width="80" data-options="field:'registerPublicNumber'">注册公告号</th>--%>
						<th width="100" data-options="field:'registerPublicDate'">注册公告日期</th>
						<th width="80" data-options="field:'trademarkType'">商标类型</th>
						<th width="170" data-options="field:'validDate'">权属期限</th><%--商标生效日期--%>
						<%--<th width="100" data-options="field:'registeredImage'">商标证书</th>--%>
						<th width="400" data-options="field:'trademarkState'">商标状态</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listTrademarks}" var="trademark">
						<tr>
							<td>${trademark.id }</td>
							<td>${trademark.registeredName }</td>
							<%--<td>${dict:getEntryName('IPBOX_TRATEMARK_RETYPE',trademark.registerType)}</td>--%>
							<td>${trademark.registeredNumber}</td>
							<td>${dict:getEntryName('IPBOX_TRATEMARK_CLASS',trademark.classify)}</td>
							<td>${trademark.classifyLike}</td>
							<td>${trademark.firstPublicNumber}</td>
							<td><fmt:formatDate value="${trademark.firstPublicDate }"  pattern="yyyy-MM-dd"/></td>
							<%--<td>${trademark.registerPublicNumber}</td>--%>
							<td><fmt:formatDate value="${trademark.registerPublicDate }"  pattern="yyyy-MM-dd"/></td>
							<td>${dict:getEntryName('IPBOX_TRATEMARK_TYPE',trademark.trademarkType)}</td>
							<td>
								<c:if test="${not empty trademark.validDate}">
									<fmt:formatDate value="${trademark.validDate }"  pattern="yyyy-MM-dd"/>
									&nbsp;至&nbsp;
									<fmt:formatDate value="${trademark.validDateEnd }"  pattern="yyyy-MM-dd"/>
								</c:if>
							</td>
							<%--							<td>
								<c:if test="${not empty trademark.registeredImage}">
				      				<c:set value="${ipanthercore:getJSONMap(trademark.registeredImage)}" var="map" />
				      				<div id="fileSpanAttachment">
					  					<span id="attachmentName"><a href="${ctx}${map.downloadUrl}" target="download">${map.attachmentName}(点击下载)</a></span> 
					  				</div>
					    		</c:if>
					    	</td>--%>
							<td>${trademark.trademarkState }</td>
<%-- 							<td>${dict:getEntryName('IPR_PATENT_TYPE',trademark.patentType)}</td> --%>
<%-- 							<td>${trademark.isWnoer eq 1 ? "是" : "否"}</td> --%>
							
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<jsp:include page="/jsp/common/include/manage_page_table.jsp">
				<jsp:param value="listTrademarkForm" name="pageForm" />
				<jsp:param value="listTrademarkTable" name="tableId" />
				<jsp:param value="easyui" name="type" />
				<jsp:param value='${param.tabId}' name="divId" />
			</jsp:include>
		</div>
	</div>
</form>

<style type="text/css">
	.model_interval{
		padding-top: 5px;
		padding-bottom: 5px;
	}
	.xu_interval{
		padding-right: 5px;
	}
</style>

<script>

function addTrademark(){
	openWindow('addTrademarkWindow','新增商标数据','100%','100%','${ctx}/intellectual/tradeMark/goAddTrademark.do',true);
}

function editTrademark(){
	var objects = $('#listTrademarkTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('addTrademarkWindow','编辑商标数据','100%','100%','${ctx}/intellectual/tradeMark/goAddTrademark.do?id='+id,true);
}

function viewTrademark(){
	var objects = $('#listTrademarkTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	var id = objects[0].id;
	openWindow('viewTrademarkWindow','查看专利信息','100%','100%','${ctx}/intellectual/tradeMark/view.do?id='+id,true);
}

function deleteTrademark(){
	var objects = $('#listTrademarkTable').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择要删除的数据');
		return false;
	}
	var id = objects[0].id;
	$.messager.confirm('确认','确定想要删除选中的记录吗？', function(flag){
		if(flag){
			$.ajax({
				url:'${ctx}/intellectual/tradeMark/delete.do?id='+id,
				type:'post',
				success:function(){
					$.messager.alert('提示','操作成功！');
					jQuery('#'+getCurrentTabId()).panel('refresh');
				}
			});
		}
	});
}
</script>