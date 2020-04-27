<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<form id="divideViewFrom" name="divideViewFrom" action="${ctx}/train/courseTeacher/saveDevideExpert.do" method="post" enctype="multipart/form-data">
	 <input type="hidden" name="paramMap[courseId]" value="${searchParam.paramMap.courseId }">
	 <input id="teacherIds" type="hidden" name="paramMap[teacherIds]" value="">
	 <table class="alter-table-v" cellspacing="0" cellpadding="0" border="0">
          
           <tr>
            <td width="80px">操作</td>
            <td colspan="3" >
           		<a onclick="divide_expert()" href="javascript:void(0)" class="easyui-linkbutton">分配专题专家</a>
			</td>
          </tr>
          <tr>
            <td >已选专家</td>
            <td colspan="3"  id="teacherContent" height="80px">
           		<c:forEach items="${listExpert }" var="list">
           		<c:if test="${not empty list.expertId  }">
					<span id='teacher_id_${list.expertId }'><input checked='checked' type='checkbox' name='teacherId' value='${list.expertId   }' disabled>${list.expertName  }
						<button type='button' class='easyui-linkbutton delete-btn' onclick="$('#teacher_id_${list.expertId  }').remove()"> 删除</button>
					 </span>
					</c:if>
				</c:forEach>
			</td>
          </tr>
           
          <tr style="text-align: center;line-height: 20px">
            <td colspan="4">
            <div style="width: 100%;text-align: center;">
            <button type="button" onclick="javascript:submitTrainForm();" style="float: center; margin-right: 20px" class="easyui-linkbutton">
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

function divide_expert(){
	openWindow('divideTeacherWindow','选择教师',550,450,'${ctx}/train/courseTeacher/divideTeacher.do?paramMap[devideType]=expert&paramMap[teacherIds]='+getSelectExpertIds('teacherContent'),true);
}
function submitTrainForm(){
	$('#teacherIds').val(getSelectExpertIds('teacherContent'));
	$.messager.confirm('确认','确认保存吗？',function(result){
		if (result){  
			$('#divideViewFrom').form('submit',{
				onSubmit: function(){
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
							closeWindow('divideViewWindow');
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
function getSelectExpertIds(range){
	var checkBoxs;
	if(range){
		checkBoxs=$("input[name='teacherId']","#"+range);
	}else{
		checkBoxs=$("input[name='teacherId']");
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
