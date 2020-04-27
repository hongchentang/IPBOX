<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set var="formId" value="check_list_form"/>
<c:set var="searchId" value="check_search"/>
<c:set var="tableId" value="check_list"/>

<form id="${formId}" action="${ctx }/train/effect/check/list.do?tabId=${param.tabId }" method="post">
<div id="${searchId}" style="margin: 5px">
	<div style="margin: 3px">
		标题:
		<input class="easyui-textbox" type="text" name="paramMap[title]" value="${searchParam.paramMap.title}" />
		状态:
		<select id="status" name="paramMap[status]" class="easyui-combobox" style="width: 80px;" editable="false">
			<option value=""></option>
			${dict:getEntryOptionsSelected('CHECK_STATUS',searchParam.paramMap.status) }
		</select>
		<button type="button" class="easyui-linkbutton main-btn" onclick="easyuiQuery('${formId}','${param.tabId}');"><i class="fa fa-search"></i> 查询</button>
		<button type="button" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel('${param.tabId}')"><i class="fa fa-eraser"></i> 重置</button>
	</div>
	<div style="margin: 3px">
		<button type="button" id="viewResultButton" class="easyui-linkbutton" ><i class="fa fa-search"></i>查看评估结果</button>
		<button type="button" id="addButton" class="easyui-linkbutton" ><i class="fa fa-plus"></i>增加</button>
		<button type="button" id="editButton" class="easyui-linkbutton" ><i class="fa fa-pencil"></i>编辑</button>
		<button type="button" id="publishButton" class="easyui-linkbutton" ><i class="fa fa-pencil"></i>发布</button>
		<button type="button" id="delButton" class="easyui-linkbutton delete-btn"><i class="fa fa-minus"></i> 删除</button>
	</div>	
</div>
 <table id="${tableId}" title="抽查列表"	toolbar="#${param.tabId } #${searchId}" pagination="true"
 	rownumbers="true" fitColumns="true" singleSelect="true" checkOnSelect="true" selectOnCheck="true">
			<thead>
				<tr>
                	<th data-options="field:'id',checkbox:true"></th>
					<th field="title" width="50">标题</th>
					<th field="description" width="150">描述</th>
					<th field="createTime" width="20">创建时间</th>
					<th field="publishTime" width="20">发布时间</th>
					<th field="status" width="20">状态</th>
					<th field="statusValue" hidden="true">statusValue</th>
				</tr>
			</thead>
			<tbody>   
       	<c:forEach items="${checks}" var="check">
        <tr>   
           <td>${check.id }</td>
           <td>${check.title }</td>
           <td>${check.remark }</td>
           <td><fmt:formatDate value="${check.createTime}" pattern="yyyy-MM-dd"/></td>
           <td><fmt:formatDate value="${check.publishTime}" pattern="yyyy-MM-dd"/></td>
           <td>${dict:getEntryName('CHECK_STATUS',check.status)}</td>
           <td>${check.status}</td>
        </tr>   
       </c:forEach>
    </tbody>
</table> 
<jsp:include page="/jsp/common/include/manage_page_table.jsp">
	<jsp:param name="pageForm" value="${formId}" />
    <jsp:param name="tableId" value="${tableId}"/>
	<jsp:param name="type" value="easyui" />
	<jsp:param name="divId" value="${param['tabId']}" />
</jsp:include>
 </form>

<script type="text/javascript">
$(document).ready(function(e) {
	//新增
	getCurrentTab().find('#addButton').linkbutton({onClick: function(){
		easyuiUtils.openWindow('showAddDialog','新增抽查',600,400,'${ctx}/train/effect/check/edit.do',true);
	}});
	//修改
	getCurrentTab().find('#editButton').linkbutton({onClick: function(){
		var selectedData = easyuiUtils.getSelectedData('${tableId}');
		if(null!=selectedData) {
			if(selectedData.statusValue=="02") {
				$.messager.alert("提示","抽查已经发布，不能修改","error");
				return false;
			}
			var id = selectedData.id;
			easyuiUtils.openWindow('showAddDialog','修改抽查',600,400,'${ctx}/train/effect/check/edit.do?id='+id,true);
		}
	}});
	//查看评估结果
	getCurrentTab().find('#viewResultButton').linkbutton({onClick: function(){
		var selectedData = easyuiUtils.getSelectedData('${tableId}');
		if(null!=selectedData) {
			if(selectedData.statusValue!="02") {
				$.messager.alert("提示","抽查还未发布，没有结果","error");
				return false;
			}
			var id = selectedData.id;
			easyuiUtils.openWindow('showAddDialog','查看评估结果',850,500,'${ctx}/train/effect/check/listResult.do?id='+id,true);
		}
	}});
	//发布
	getCurrentTab().find('#publishButton').linkbutton({onClick: function(){
			var selectedData = easyuiUtils.getSelectedData('${tableId}');
			if(null!=selectedData) {
				if(selectedData.statusValue=="02") {
					$.messager.alert("提示","当前抽查已发布","error");
					return false;
				}
				$.messager.confirm('提示', '是否确定发布?', function(r){
					if(r) {
						var data=$.ajaxSubmitValue('${ctx }/train/effect/check/publish.do?id='+selectedData.id);
						var json=jQuery.parseJSON(data);
						var responseCode = json.responseCode;
						var responseMsg = json.responseMsg;
						if(parseInt(responseCode)==0){
							jQuery.messager.alert("提示信息","发布成功","info");
							jQuery('#'+getCurrentTabId()).panel('refresh');
						} else {
							jQuery.messager.alert("提示信息",responseMsg,"error");
						}
					}
				});
			}
	}});
	//删除
	getCurrentTab().find('#delButton').linkbutton({onClick: function(){
		var selectedData = easyuiUtils.getSelectedData('${tableId}');
		if(null!=selectedData) {
			if(selectedData.statusValue=="02") {
				$.messager.alert("提示","当前抽查已发布，不能删除","error");
				return false;
			}
			$.messager.confirm('提示', '是否确定删除?', function(r){
				if(r) {
					var data=$.ajaxSubmitValue('${ctx }/train/effect/check/delete.do?id='+selectedData.id);
					var json=jQuery.parseJSON(data);
					var responseCode = json.responseCode;
					if(parseInt(responseCode)==0){
						jQuery.messager.alert("提示信息","删除成功","info");
						jQuery('#'+getCurrentTabId()).panel('refresh');
					} else {
						jQuery.messager.alert("提示信息","删除失败","error");
					}
				}
			});
		}
	}});

});

</script>