<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set var="formId" value="requirement_survey_stat_list_form"/>
<c:set var="searchId" value="requirement_survey_stat_search"/>
<c:set var="tableId" value="requirement_survey_stat_list"/>

<form id="${formId}" action="${ctx }/train/requirement/listSurveyStat.do?tabId=${param.tabId }" method="post">
<div id="${searchId}" style="margin: 5px">
	<div style="margin: 3px">
		标题:
		<input class="easyui-textbox" type="text" name="paramMap[title]" value="${searchParam.paramMap.title}" />
		<button type="button" class="easyui-linkbutton main-btn" onclick="easyuiQuery('${formId}','${param.tabId}');"><i class="fa fa-search"></i> 查询</button>
		<button type="button" class="easyui-linkbutton" onclick="easyuiUtils.ajaxClearFormForPanel('${param.tabId}')"><i class="fa fa-eraser"></i> 重置</button>
	</div>
	<div style="margin: 3px">
		<button type="button" id="statButton" class="easyui-linkbutton" ><i class="fa fa-search"></i>统计分析</button>
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
					<th field="author" width="20">创建人</th>
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
           <td>${ipanthercommon:getUserRealName(requirementSurvey.creator)}</td>
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
	//查看调查结果
	getCurrentTab().find('#statButton').linkbutton({onClick: function(){
		var selectedData = easyuiUtils.getSelectedData('${tableId}');
		if(null!=selectedData) {
			easyuiUtils.openWindow('showAddDialog','统计分析',900,650,'${ctx}/survey/surveyStat.do?id='+selectedData.surveyId,true);
		}
	}});

});

</script>