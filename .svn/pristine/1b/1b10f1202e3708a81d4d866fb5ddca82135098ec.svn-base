<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<form id="requirementSurveyForm" name="requirementSurveyForm" action="${ctx}/train/requirement/saveSurvey.do" method="post" >
	<div id="p" class="easyui-panel" title="基本信息" collapsible="false">
		<input type="hidden" name="requirementSurvey.id" value="${requirementSurvey.id}"/>
		<input type="hidden" name="survey.id" value="${requirementSurvey.surveyId}"/>
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				<td width="20%">标题</td>
				<td width="80%">
					${requirementSurvey.title}
				</td>
			</tr>
			<tr>
				<td>描述</td>
				<td>
					<textarea style="width:98%" class="easyui-validatebox" readonly='readonly'>${requirementSurvey.description}</textarea>
				</td>
			</tr>
			<tr>
				<td>开始日期</td>
				<td>
					<fmt:formatDate value="${requirementSurvey.startTime}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<td>结束日期</td>
				<td>
					<fmt:formatDate value="${requirementSurvey.endTime}" pattern="yyyy-MM-dd"/>
				</td>
			</tr>
			<tr>
				<td>参与区域</td>
				<td>
					<select id="regionsCodes" name="requirementSurvey.regionsCodes" style="width:300px"></select> 
					<span style="color: #666666">点击下拉可查看完整区域</span>
					<script type="text/javascript">
						jQuery('#requirementSurveyForm').find('#regionsCodes').combotree({
						    data: jQuery.parseJSON('${ipanthercommon:getRegionsJson(true)}'),
						    valueField:'id',    
						    textField:'text',
						    parentField:'pid',
						    panelWidth:200,
						    multiple:true,
						    checkbox:true,
						    cascadeCheck:false,
						    onLoadSuccess:function(){
						    	var value='${requirementSurvey.regionsCodes}';
								value = value.replace(/\"/g,'').replace(/\[/g,'').replace(/\]/g,'');
								if(value!=''){
									  jQuery("#requirementSurveyForm").find("#regionsCodes").combotree('setValues',value);
								}
						    }
						}); 
						
					</script>
				</td>
			</tr>
			<%-- <tr>
				<td>参与项目</td>
				<td>
					<c:forEach items="${projects}" var="project" varStatus="stat">
						<c:set var="projectId" value="\"${project.id}\""/>
						<c:if test='${fn:contains(requirementSurvey.projectIds,projectId)}'>
							${project.projectName}&nbsp;
						</c:if>
					</c:forEach>
				</td>
			</tr> --%>
		</tbody>
		</table>
	</div>
</form>
<c:choose>
	<c:when test="${empty requirementSurvey.id }">
		
	</c:when>
	<c:otherwise>
		<%@include file="../../survey/question/list.jsp" %>
	</c:otherwise>
</c:choose>
<script type="text/javascript">
tableVBg('.alter-table-v');
$(document).ready(function (){
	
});
</script>
