<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<form id="ask_saveFrom" name="ask_saveFrom" action="${ctx}/train/channel/askMail.do" method="post" enctype="multipart/form-data">
	<input type="hidden" id="studentIds" name="paramMap[studentIds]" value="">
	<input type="hidden" id="projectId" name="paramMap[projectId]" value="${projectId}">
	 <table class="alter-table-v" cellspacing="0" cellpadding="0" border="0">    
          <tr>
            <td ><font color="red">*</font>操作</td>
            <td colspan="3" width="600px">
           		<a onclick="selectStudents()" href="javascript:void(0)" class="easyui-linkbutton">选择学员</a>
			</td>
          </tr>
          <tr>
            <td width="150"  rowspan="2">已选学员</td>
            <td colspan="3" width="600px" id="studentContent" rowspan="2">
           		<c:forEach items="${studentList }" var="list">
					<span id='student_id_${list.studentId }'><input checked='checked' type='checkbox' name='studentId' value='${list.studentId }' disabled>${list.studentName }
						<button type='button' class='easyui-linkbutton delete-btn' onclick="$('#student_id_${list.studentId }').remove()"> 删除</button>
					 </span>
				</c:forEach>
			</td>
          </tr>
          <tr></tr>
          <tr style="text-align: center;line-height: 20px">
            <td colspan="4">
            <div style="width: 100%;text-align: center;">
            <button type="button" onclick="javascript:submitAskForm();" style="float: center; margin-right: 20px" class="easyui-linkbutton">
            	发送
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

function selectStudents(){
	openWindow('selectStudentWindow','选择学员',0,600,'${ctx}/train/channel/selectStudent.do?paramMap[paramStudentIds]='+getSelectExpertIds('studentStudent'),true);
}
function submitAskForm(){
	$('#studentIds').val(getSelectExpertIds('studentContent'));
	$.messager.confirm('确认','确认发送吗？',function(result){
		if (result){  
			$('#ask_saveFrom').form('submit',{
				onSubmit: function(){
					if($("#studentIds").val()==''){
						$.messager.progress('close');	
						$.messager.alert('提示','请选择学员！');
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
							closeWindow('showAskWindow');
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
function getSelectExpertIds(range){
	var checkBoxs;
	if(range){
		checkBoxs=$("input[name='studentId']","#"+range);
	}else{
		checkBoxs=$("input[name='studentId']");
	}
	var ids="";
	if(checkBoxs.length>0){
		$(checkBoxs).each(function (index, checkBox){
			if(checkBoxs.length-1==index){
				ids+=$(checkBox).val();	
			}else{
				ids+=($(checkBox).val()+",");
			}
		})
	}
	return ids;
}
</script>
