<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes')}" var="fileTypes2"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize2"/>
<form id="project_saveFrom" name="project_saveFrom" action="${ctx}/train/project/saveProject.do" method="post" enctype="multipart/form-data">
	 <input type="hidden" name="id" value="${project.id }">
	  <input type="hidden"  id="attachment" name="attachment" value="${project.attachment }"/>
	 <table class="alter-table-v" cellspacing="0" cellpadding="0" border="0">
          <tr>
            <td width="200px"><font color="red">*</font>项目编码</td>
            <td >
			   <input type="text" class="easyui-textbox"  data-options="required:true" id="projectCode" name="projectCode" value="${project.projectCode }"/>
			</td>
			<td width="200px"><font color="red">*</font>项目名称</td>
            <td >
					<input type="text" class="easyui-textbox" data-options="required:true" id="projectName" name="projectName" value="${project.projectName }"/>
			</td>
          </tr>
           <tr>
            <td >首席专家</td>
            <td >
            <input type="text" class="easyui-textbox" id="expertId" name="expertId" value="${project.expertId }">
			</td>
			<td ><font color="red">*</font>项目实施年度</td>
            <td >
            <input id="year" type="text"  class="Wdate" name="year" value="${project.year }" onFocus="WdatePicker({dateFmt:'yyyy'})" readonly/>
			</td>
          </tr>
          
           <tr>
            <td >部门负责人</td>
            <td >
            <input type="text" class="easyui-textbox" id="headId" name="headId" value="${project.headId }">
			</td>
			<td >项目执行部门</td>
            <td >
			   <input type="text" class="easyui-textbox"  id="headUnit" name="headUnit" value="${project.headUnit }"/>
			</td>
          </tr>
          <tr>
            <td>计划参训人数</td>
            <td>
            	<input type="text" class="easyui-numberbox" data-options="min:0,precision:0" id="trainNum" name="trainNum" value="${project.trainNum }">
			</td>
			<td >计划培训学时</td>
            <td >
			   <input type="text" class="easyui-numberbox" data-options="min:0,precision:0" id="planHours" name="planHours" value="${project.planHours }"/>
			</td>
          </tr>
          <tr>
            <td >学科（领域）</td>
            <td >
            <input type="text" class="easyui-textbox" id="projectSubject" name="projectSubject" value="${project.projectSubject }">
			</td>
			<td >产出指标</td>
            <td >
			   <input type="text" class="easyui-textbox"  id="indexOut" name="indexOut" value="${project.indexOut }"/>
			</td>
          </tr>
          
          <tr>
            <td >效益指标</td>
            <td >
            <input type="text" class="easyui-textbox" id="indexBenefit" name="indexBenefit" value="${project.indexBenefit }">
			</td>
			<td >参训对象满意度指标</td>
            <td >
			   <input type="text" class="easyui-textbox"  id="indexSatisfy" name="indexSatisfy" value="${project.indexSatisfy }"/>
			</td>
          </tr>
          
          <tr>
            <td >项目总经费</td>
            <td >
            <input type="text" class="easyui-numberbox" data-options="min:0,precision:2" id="cash" name="cash" value="${project.cash }">
			</td>
			<td >经费来源</td>
            <td >
			   <input type="text" class="easyui-textbox"  id="cashFrom" name="cashFrom" value="${project.cashFrom }"/>
			</td>
          </tr>
          
          
           <tr>
            <td >项目绩效评价表自评结果</td>
            <td >
            <input type="text" class="easyui-numberbox" data-options="min:0,precision:2" id="evalPerform" name="evalPerform" value="${project.evalPerform }"/>
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
			   <input type="text" class="easyui-numberbox" data-options="min:0,precision:2" id="evalScore" name="evalScore" value="${project.evalScore }"/>
			</td>
			<td>上传附件</td>
          		<td>
          			<input type="file"  name="uploadFile">
           			<div>
						     允许上传的文件类型：${fileTypes2}<br/>
						    允许上传文件的大小：${fileMaxSize2}KB 
					</div>
          		</td>
          </tr>
          
          <tr>
            <td >项目自评结论</td>
            <td colspan="3">
            <textarea rows="5" cols="80" id="evalResult" name="evalResult">${project.evalResult }</textarea>
			</td>
          </tr>
          
          <tr>
            <td >项目自评报告</td>
            <td colspan="3">
            <textarea rows="5" cols="80" id="evalReport" name="evalReport">${project.evalReport }</textarea>
			</td>
          </tr>
          
          
           <%-- <tr>
            <td >学员评价</td>
            <td colspan="3">
            <textarea rows="5" cols="80" id="evalStudent" name="evalStudent">${project.evalStudent }</textarea>
			</td>
          </tr>
          
          <tr>
            <td >专家评价</td>
            <td colspan="3">
            <textarea rows="5" cols="80" id="evalExpert" name="evalExpert">${project.evalExpert }</textarea>
			</td>
          </tr> --%>
          
          <tr style="text-align: center;line-height: 20px">
            <td colspan="4">
            <div style="width: 100%;text-align: center;">
            <button type="button" onclick="javascript:submitProjectForm();" style="float: center; margin-right: 20px" class="easyui-linkbutton">
            	保存
            </button>
            </div>
            </td>
          </tr>
        </table>
</form>

<script type="text/javascript">
tableVBg('.alter-table-v');
$(document).ready(function (){
});


function submitProjectForm(){
	$.messager.confirm('确认','确认保存吗？',function(result){    
		if (result){  
			$('#project_saveFrom').form('submit',{
				onSubmit: function(){
					if($("#year").val()==''){
						$.messager.progress('close');	
						$.messager.alert('提示','请填写项目实施年度！');
						return false;
					}
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	
						$.messager.alert('提示','请填写必填项！');
					}
					return isValid;	
				},
				success: function(data){
					var json=jQuery.parseJSON(data);
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							$.messager.alert('提示',message);
						}else if(parseInt(statusCode)==200){
							closeWindow('addProjectWindow');
							$('#${searchParam.paramMap.rePanel }').panel('refresh');
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
