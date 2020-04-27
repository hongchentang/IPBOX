<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<h2>课程信息</h2>
<form id="project_auditFrom" name="project_auditFrom" action="${ctx}/train/project/audit.do" method="post">
	<input type="hidden" name="id" value="${project.id }">
	<input type="hidden" name="procInstId" value="${project.procInstId }">
	 <input type="hidden" name="declareUser" value='${project.declareUser }'/>
	 <input type="hidden" id="status" name="paramMap[status]" value="">
	 <input type="hidden" name="applyCount" value="${project.applyCount }">
	 <table class="alter-table-v" cellspacing="0" cellpadding="0" border="0">
          <tr>
            <td width="200px">项目编码</td>
            <td >
			   ${project.projectCode }
			</td>
			<td width="200px">项目名称</td>
            <td >
				 ${project.projectName }
			</td>
          </tr>
           <tr>
            <td >首席专家</td>
            <td >
            	${project.expertId }
			</td>
			<td >项目实施年度</td>
            <td >
            	${project.year }
			</td>
          </tr>
          
           <tr>
            <td >部门负责人</td>
            <td >
            	${project.headId }
			</td>
			<td >项目执行部门</td>
            <td >
			   ${project.headUnit }
			</td>
          </tr>
           <tr>
            <td>计划参训人数</td>
            <td>
            	${project.trainNum }
			</td>
			<td >计划培训学时</td>
            <td >
			   ${project.planHours }
			</td>
          </tr>
          <tr>
            <td >学科（领域）</td>
            <td >
            	${project.projectSubject }
			</td>
			<td >产出指标</td>
            <td >
			   ${project.indexOut }
			</td>
          </tr>
          
          <tr>
            <td >效益指标</td>
            <td >
           		${project.indexBenefit }
			</td>
			<td >参训对象满意度指标</td>
            <td >
			  	${project.indexSatisfy }
			</td>
          </tr>
          
          <tr>
            <td >项目总经费</td>
            <td >
            	${project.cash }
			</td>
			<td >经费来源</td>
            <td >
			   ${project.cashFrom }
			</td>
          </tr>
          
           <tr>
            <td >项目绩效评价表自评结果</td>
            <td >
            	${project.evalPerform }
			</td>
			<td >附件</td>
            <td >
			   <c:if test="${not empty project.attachment}"> 
			      <c:set value="${ipanthercore:getJSONMap(project.attachment)}" var="map" />
				      <div id="fileSpanAttachment">
					  	<span id="attachmentName"><a href="${ctx}${map.downloadUrl}" target="download">${map.attachmentName}(点击下载)</a></span> 
					  </div>
			    </c:if>
			</td>
          </tr>
          
          <tr>
			<td >项目自评得分</td>
            <td >
			   ${project.evalScore }
			</td>
          </tr>
          
          <tr>
            <td >项目自评结论</td>
            <td colspan="3">
            <textarea rows="5" cols="80" id="evalResult" name="evalResult" readonly="readonly">${project.evalResult }</textarea>
			</td>
          </tr>
          
          <tr>
            <td >项目自评报告</td>
            <td colspan="3">
            <textarea rows="5" cols="80" id="evalReport" name="evalReport" readonly="readonly">${project.evalReport }</textarea>
			</td>
          </tr>
        </table>
        
        <c:if test="${empty viewValue }"><!-- 若为查看，不显示审核 -->
		  <h2>课程审核</h2>
		  <table class="alter-table-v" cellspacing="0" cellpadding="0" border="0">
		  <tr>
		   <td style="width: 100px">当前申请次数</td>
		   <td style="width: 200px">${project.applyCount }</td>
		   <td style="width: 100px">最大申请次数</td>
		   <td ><input type="text" class="easyui-numberbox" data-options="min:1,precision:0,required:true" id="maxApplyCount" name="maxApplyCount" value="${project.maxApplyCount }"/></td>
		  </tr>
		  <tr>
			  <td>审核意见</td>
			  <td colspan="3"><textarea rows="6" cols="65" id="auditContent" name="paramMap[auditContent]"></textarea></td>
		  </tr>
		  
		  <tr>
		  	 <td>审核</td>
		  		<td colspan="3"><div style="width: 70%;text-align: center;">
		  			<button type="button" onclick="javascript:submitAudiForm('01');" style="float: center; margin-right: 20px" class="easyui-linkbutton">
		            	通过
		            </button>
		            <button type="button" onclick="javascript:submitAudiForm('02');" style="float: center; margin-right: 20px" class="easyui-linkbutton">
		            	退回
		            </button>
		            <button type="button" onclick="javascript:submitAudiForm('03');" style="float: center; margin-right: 20px" class="easyui-linkbutton">
		            	不通过
		            </button>
		 		 </div></td>
		  </tr>
		  </table>
 	 </c:if>
  <br>
</form>
<script type="text/javascript">
tableVBg('.alter-table-v');
$(document).ready(function (){
});


function submitAudiForm(status){
	$.messager.confirm('确认','确认审核吗？',function(result){    
		if (result){
			$('#status').val(status);
			$('#project_auditFrom').form('submit',{
				onSubmit: function(){
					if($("#auditContent").val()==''){
						$.messager.alert('提示','请填写审核意见!');
						return false;
					}
					if($("#maxApplyCount").val()==''){
						$.messager.alert('提示','请填写最大申请次数!');
						return false;
					}
					return true;	
				},
				success: function(data){
					var json=jQuery.parseJSON(data);
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							$.messager.alert('提示',message);
						}else if(parseInt(statusCode)==200){
							closeWindow('auditProjectWindow');
							$("#viewProjectListTodo").panel('refresh');
							$.messager.alert('提示',message);
						}
					}else{
						$.messager.alert('提示','json is null');
					}
				}
			});
		}
	});
}
</script>
