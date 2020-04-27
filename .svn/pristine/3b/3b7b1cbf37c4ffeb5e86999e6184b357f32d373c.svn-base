<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set var="formId" value="requirement_survey_list_form"/>
<c:set var="searchId" value="requirement_survey_search"/>
<c:set var="tableId" value="requirement_survey_list"/>

<form id="${formId}" action="${ctx }/train/requirement/listSurvey.do?tabId=${param.tabId }" method="post">
<div id="${searchId}" style="margin: 5px">
	<div style="margin: 3px">
		标题:
		<input class="easyui-textbox" type="text" name="paramMap[title]" value="${searchParam.paramMap.title}" />
		状态:
		<select id="state" name="paramMap[state]" class="easyui-combobox" style="width: 80px;" editable="false">
			<option value=""></option>
			${dict:getEntryOptionsSelected('SURVEY_STATE',searchParam.paramMap.state) }
		</select>
		<button type="button" class="easyui-linkbutton main-btn" onclick="easyuiQuery('${formId}','${param.tabId}');"><i class="fa fa-search"></i> 查询</button>
		<button type="button" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel('${param.tabId}')"><i class="fa fa-eraser"></i> 重置</button>
	</div>
	<div style="margin: 3px">
		<button type="button" id="preViewButton" class="easyui-linkbutton main-btn" ><i class="fa fa-search"></i>预览</button>
		<button type="button" id="viewButton" class="easyui-linkbutton" ><i class="fa fa-search"></i>查看</button>
		<button type="button" id="addButton" class="easyui-linkbutton" ><i class="fa fa-plus"></i>增加</button>
		<button type="button" id="editButton" class="easyui-linkbutton" ><i class="fa fa-pencil"></i>编辑</button>
		<button type="button" id="publishButton" class="easyui-linkbutton" ><i class="fa fa-pencil"></i>发布</button>
		<button type="button" id="delButton" class="easyui-linkbutton delete-btn"><i class="fa fa-minus"></i> 删除</button>
	</div>	
</div>
 <table id="${tableId}" title="问卷列表"	toolbar="#${param.tabId } #${searchId}" pagination="true"
 	rownumbers="true" fitColumns="true" singleSelect="true" checkOnSelect="true" selectOnCheck="true">
			<thead>
				<tr>
                	<th data-options="field:'id',checkbox:true"></th>
					<th field="title" width="50">标题</th>
					<th field="description" width="150">描述</th>
					<th field="startTime" width="20">开始时间</th>
					<th field="endTime" width="20">结束时间</th>
					<th field="state" width="20">状态</th>
					<th field="author" width="20">创建人</th>
					<th field="stateValue" hidden="true">stateValue</th>
					<th field="surveyId" hidden="true">surveyId</th>
				</tr>
			</thead>
			<tbody>   
       	<c:forEach items="${requirementSurveies}" var="requirementSurvey">
        <tr>   
           <td>${requirementSurvey.id }</td>
           <td>${requirementSurvey.title }</td>
           <td>${requirementSurvey.description }</td>
           <td><fmt:formatDate value="${requirementSurvey.startTime}" pattern="yyyy-MM-dd"/></td>
           <td><fmt:formatDate value="${requirementSurvey.endTime}" pattern="yyyy-MM-dd"/></td>
           <td>${dict:getEntryName('SURVEY_STATE',requirementSurvey.state)}</td>
           <td>${ipanthercommon:getUserRealName(requirementSurvey.creator)}</td>
           <td>${requirementSurvey.state}</td>
           <td>${requirementSurvey.surveyId}</td>
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
		easyuiUtils.openWindow('showAddDialog','新增问卷',600,350,'${ctx}/train/requirement/editSurvey.do',true);
	}});
	//修改
	getCurrentTab().find('#editButton').linkbutton({onClick: function(){
		var selectedData = easyuiUtils.getSelectedData('${tableId}');
		if(null!=selectedData) {
			if(selectedData.stateValue=="2") {
				$.messager.alert("提示","问卷已经发布，不能修改","error");
				return false;
			}
			var id = selectedData.id;
			easyuiUtils.openWindow('showAddDialog','编辑问卷',900,650,'${ctx}/train/requirement/editSurvey.do?id='+id,true);
		}
	}});
	//查看
	getCurrentTab().find('#viewButton').linkbutton({onClick: function(){
		var selectedData = easyuiUtils.getSelectedData('${tableId}');
		if(null!=selectedData) {
			var id = selectedData.id;
			easyuiUtils.openWindow('showAddDialog','查看问卷',900,650,'${ctx}/train/requirement/viewSurvey.do?id='+id,true);
		}
	}});
	//发布
	getCurrentTab().find('#publishButton').linkbutton({onClick: function(){
			var selectedData = easyuiUtils.getSelectedData('${tableId}');
			if(null!=selectedData) {
				if(selectedData.stateValue=="2") {
					$.messager.alert("提示","当前问卷已发布","error");
					return false;
				}
				$.messager.confirm('提示', '是否确定发布?', function(r){
					if(r) {
						var data=$.ajaxSubmitValue('${ctx }/survey/publish.do?id='+selectedData.surveyId);
						var json=jQuery.parseJSON(data);
						var responseCode = json.responseCode;
						if(parseInt(responseCode)==0){
							jQuery.messager.alert("提示信息","发布成功","info");
							jQuery('#'+getCurrentTabId()).panel('refresh');
						} else {
							jQuery.messager.alert("提示信息","发布失败","error");
						}
					}
				});
			}
	}});
	//预览问卷
	getCurrentTab().find('#preViewButton').linkbutton({onClick: function(){
		var selectedData = easyuiUtils.getSelectedData('${tableId}');
		if(null!=selectedData) {
			var surveyId = selectedData.surveyId;
			easyuiUtils.openWindow('showAddDialog','问卷预览',900,630,'${ctx}/survey/preview.do?id='+surveyId,true);
		}
	}});
	//删除
	getCurrentTab().find('#delButton').linkbutton({onClick: function(){
		var selectedData = easyuiUtils.getSelectedData('${tableId}');
		if(null!=selectedData) {
			if(selectedData.stateValue=="2") {
				$.messager.alert("提示","问卷已经发布，不能删除","error");
				return false;
			}
			$.messager.confirm('提示', '是否确定删除?', function(r){
				if(r) {
					var data=$.ajaxSubmitValue('${ctx }/train/requirement/deleteSurvey.do?id='+selectedData.id);
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