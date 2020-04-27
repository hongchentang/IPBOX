<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<h3>导出数据筛选条件：</h3>
<form id="seniorTemplateFrom" name="seniorTemplateFrom" action="${ctx}/train/courseRegister/exportTemplate.do" method="post" enctype="multipart/form-data">
	<input  type="hidden" name="paramMap[trainId]" value="${searchParam.paramMap.trainId }">
	<input  id="paramTrainName" type="hidden" name="paramMap[trainName]" value="">
	 <table class="alter-table-v" cellspacing="0" cellpadding="0" border="0">
          <tr>
            <td width="100">学员姓名</td>
            <td >
			   <input name="paramMap[realName]" value="">
			</td>
          </tr>
           <tr>
            <td  >课程名称</td>
            <td >
				<input  name="paramMap[courseName]" value="">
			</td>
          </tr>
           <tr>
            <td >部门名称</td>
            <td >
			   <input  name="paramMap[deptName]" value="">
			</td>
          </tr>
       	  <tr>
            <td colspan="4" width="600px" id="courseContent">
           		 <div style="width: 100%;text-align: center;">
           			<a onclick="seniorTemplate()" href="javascript:void(0)" class="easyui-linkbutton">导出</a>
           			<a onclick="closeWindow('seniorTemplateWindow')" href="javascript:void(0)" class="easyui-linkbutton">关闭</a>
				</div>
			</td>
          </tr> 
        </table>
</form>
<font color="red">(根据填写的条件，导出该培训班相应的数据模板)</font>

<script type="text/javascript">
tableVBg('.alter-table-v');
$(document).ready(function (){
});
function seniorTemplate(){
	var objects = $('#listCourseRegisterTable','#listCourseRegisterTrain').datagrid('getSelections');
	if(objects.length == 0){
		$.messager.alert('消息','请选择一条培训班数据');
		return false;
	} 	
	if(objects.length > 1){
		$.messager.alert('消息','不能同时编辑多条数据');
		return false;
	}
	var trainName=objects[0].trainName;
	$("#paramTrainName").val(trainName);
	$.messager.confirm('确认','是否确认导出'+trainName+'班下的学员评分导入模板？',function(result){
		if(result){
			 $("#seniorTemplateFrom").submit();
			// closeWindow('seniorTemplateWindow');
		}
	});
	
}

</script>
