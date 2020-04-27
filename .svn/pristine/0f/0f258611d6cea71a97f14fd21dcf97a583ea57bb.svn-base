<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<form id="requirementSurveyForm" name="requirementSurveyForm" action="${ctx}/train/requirement/saveSurvey.do" method="post" >
	<div id="p" class="easyui-panel" title="基本信息" collapsible="false">
		<input type="hidden" name="requirementSurvey.id" value="${requirementSurvey.id}"/>
		<input type="hidden" name="survey.id" value="${requirementSurvey.surveyId}"/>
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				<td width="20%"><span style="color: red">*</span>标题</td>
				<td width="80%">
					<input type="text" class="easyui-textbox" required name="survey.title" value="${requirementSurvey.title}"/>
				</td>
			</tr>
			<tr>
				<td><span style="color: red">*</span>描述</td>
				<td>
					<textarea style="width:98%" name="survey.description" class="easyui-validatebox" required>${requirementSurvey.description}</textarea>
				</td>
			</tr>
			<tr>
				<td><span style="color: red">*</span>开始日期</td>
				<td>
					<input type="text" class="easyui-validatebox Wdate" required onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="requirementSurvey.startTime" value="<fmt:formatDate value="${requirementSurvey.startTime}" pattern="yyyy-MM-dd"/>"/>
				</td>
			</tr>
			<tr>
				<td><span style="color: red">*</span>结束日期</td>
				<td>
					<input type="text" class="easyui-validatebox Wdate" required onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" name="requirementSurvey.endTime" value="<fmt:formatDate value="${requirementSurvey.endTime}" pattern="yyyy-MM-dd"/>"/>
				</td>
			</tr>
			<tr>
				<td><span style="color: red">*</span>参与区域</td>
				<td>
					<select id="regionsCodes" name="requirementSurvey.regionsCodes" style="width:300px" required></select> 
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
						    },
						    onDblClick:function(node) {checkCascade('requirementSurveyForm','regionsCodes',node)}
						}); 
						
						function checkCascade(formId,fieldId,node) {
							var checked = !node.checked;
					    	var values = $('#'+formId).find("#"+fieldId).combotree('getValues');
					    	if(''!=values) {
					    		values = ','+values+',';
					    	}
					    	values = getValueCascade(node,values,checked);
					    	/*
					    	 *去重，去掉逗号，去掉空
					    	 */
					    	var valueArr = values.split(',');
					    	var tempArr = [];
					    	var values = "";
					    	for(var i = 0;i<valueArr.length;i++) {
					    		var value = valueArr[i];
					    		if(","!=value&&""!=value&&!tempArr[value]){
					                values += ","+value;
					                tempArr[value]=true;
					            }
					    	}
					    	if(values.length>0) {
					    		values = values.substr(1);
					    	}
				    		$('#'+formId).find("#"+fieldId).combotree('setValues',values);
						}
						
						function getValueCascade(node,values,checked) {
							var children = node.children;
					    	if(undefined != children) {
						    	values = values+'';
						    	//处理当前节点
						    	if(checked) {
						    		values += ','+node.id+',';
						    	} else {
						    		values = values.replace(','+node.id+',',',');
						    	}
						    	//处理子节点
						    	for(var i = 0;i<children.length;i++) {
						    		if(checked) {
						    			values+=','+children[i].id+',';
						    		} else {
						    			values = values.replace(','+children[i].id+',',',');
						    		}
						    		values = getValueCascade(children[i],values,checked);
						    	}
					    	}
					    	return values;
						}
					</script>
					<span style="color: #666666">双击区域节点可级联选择</span>
				</td>
			</tr>
			<%-- <tr>
				<td>参与项目</td>
				<td>
					<select id="projectIds" name="requirementSurvey.projectIds" style="width:195px" class="easyui-combobox" editable="false" multiple="true" checkbox="true">
						<c:forEach items="${projects}" var="project">
							<c:set var="projectId" value="\"${project.id}\""/>
							<option value='${project.id}' <c:if test='${fn:contains(requirementSurvey.projectIds,projectId)}'>selected</c:if>>${project.projectName}</option>
						</c:forEach>
					</select> 
				</td>
			</tr> --%>
		</tbody>
		</table>
	</div>
</form>
<div style="text-align: center;margin:3px">
	<button type="button" onclick="saveRequirementSurvey()" class="easyui-linkbutton" > <i class="fa fa-save"></i>保 存 </button>
</div>
<c:choose>
	<c:when test="${empty requirementSurvey.id }">
		<div style="margin-top: 10px;color: red;font-size: 15px">
			提示：保存问卷的基本信息后可编辑问卷的问题！
		</div>
	</c:when>
	<c:otherwise>
		<%@include file="../../survey/question/list.jsp" %>
	</c:otherwise>
</c:choose>
<script type="text/javascript">
tableVBg('.alter-table-v');
$(document).ready(function (){
	
});
function saveRequirementSurvey() {
	$.messager.confirm('提示', '确定保存？', function(r){
		if(r) {
			
			jQuery('#requirementSurveyForm').form('submit',{
				onSubmit: function(){
					 return $('#requirementSurveyForm').form('validate');
				},
			    success: function(data){
			    	var json=jQuery.parseJSON(data);
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							jQuery.messager.alert("提示信息",message,"error");
						} else if(parseInt(statusCode)==200){
							jQuery.messager.alert("提示信息","保存成功","info",function() {
								jQuery('#'+getCurrentTabId()).panel('refresh');
								$('#showAddDialog').window({
									  title:'编辑问卷',
									  width:900,
									  height:650,
									  href:'${ctx}/train/requirement//editSurvey.do?id='+message
								});
								jQuery("#showAddDialog").window("hcenter");
							});
						}
					}
			    }
			}); 
		}
		
	});
}
</script>
