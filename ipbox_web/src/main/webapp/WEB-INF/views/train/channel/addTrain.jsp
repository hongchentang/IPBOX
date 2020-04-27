<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<form id="train_saveFrom" name="train_saveFrom" action="${ctx}/train/train/saveTrain.do" method="post" enctype="multipart/form-data">
	 <input type="hidden" name="id" value="${train.id }">
	 <input type="hidden" name="projectId" value="${train.projectId }">
	 <input type="hidden" id="courseIds" name="paramMap[courseIds]" value="">
	 <table class="alter-table-v" cellspacing="0" cellpadding="0" border="0">
          <tr>
            <td width="150"><font color="red">*</font>培训班编码</td>
            <td >
			   <input type="text" class="easyui-textbox"  data-options="required:true" id="trainCode" name="trainCode" value="${train.trainCode }"/>
			</td>
			<td width="150"><font color="red">*</font>培训班名称</td>
            <td >
					<input type="text" class="easyui-textbox" data-options="required:true" id="trainName" name="trainName" value="${train.trainName }"/>
			</td>
          </tr>
          
           <tr>
            <td ><font color="red">*</font>选课起始日期</td>
            <td >
			   <input id="startTime" type="text" class="Wdate" name="startTime" value="<fmt:formatDate value="${train.startTime }" type="date" pattern="yyyy-MM-dd"/>"
        onFocus="var startTime=$dp.$('#startTime');WdatePicker({onpicked:function(){endTime.focus();},maxDate:'#F{$dp.$D(\'startTime\')}'})" readonly/>
			</td>
			<td ><font color="red">*</font>选课结束日期</td>
            <td >
			  <input id="endTime" type="text"  class="Wdate" name="endTime" value="<fmt:formatDate value="${train.endTime }" type="date" pattern="yyyy-MM-dd"/>" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" readonly/>
			</td>
          </tr>
          
          <tr>
            <td >缴费起始日期</td>
            <td >
			   <input id="cashStartTime" type="text" class="Wdate" name="cashStartTime" value="<fmt:formatDate value="${train.cashStartTime }" type="date" pattern="yyyy-MM-dd"/>"
        onFocus="var startTime=$dp.$('#cashStartTime');WdatePicker({onpicked:function(){cashEndTime.focus();},maxDate:'#F{$dp.$D(\'cashStartTime\')}'})" readonly/>
			</td>
			<td >缴费结束日期</td>
            <td >
			  <input id="cashEndTime" type="text"  class="Wdate" name="cashEndTime" value="<fmt:formatDate value="${train.cashEndTime }" type="date" pattern="yyyy-MM-dd"/>" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'cashStartTime\')}'})" readonly/>
			</td>
          </tr>
          
          <tr>
            <td >学习起始日期</td>
            <td >
			   <input id="studyStartTime" type="text" class="Wdate" name="studyStartTime" value="<fmt:formatDate value="${train.studyStartTime }" type="date" pattern="yyyy-MM-dd"/>"
        onFocus="var startTime=$dp.$('#studyStartTime');WdatePicker({onpicked:function(){studyEndTime.focus();},maxDate:'#F{$dp.$D(\'studyStartTime\')}'})" readonly/>
			</td>
			<td >学习结束日期</td>
            <td >
			  <input id="studyEndTime" type="text"  class="Wdate" name="studyEndTime" value="<fmt:formatDate value="${train.studyEndTime }" type="date" pattern="yyyy-MM-dd"/>" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'studyStartTime\')}'})" readonly/>
			</td>
          </tr>
          
            <tr>
            <td >成绩登记日期</td>
            <td >
			   <input id="scoreTime" type="text"  class="Wdate" name="scoreTime" value="<fmt:formatDate value="${train.scoreTime }" type="date" pattern="yyyy-MM-dd"/>" onFocus="WdatePicker()" readonly/>
			</td>
			<td >期选课人数</td>
            <td >
			   <input id="countChoose" type="text" class="easyui-numberbox"  data-options="min:0,precision:0" name="countChoose" value="${train.countChoose }" />
			</td>
			
          </tr>
          <tr>
			<td>是否安排住宿</td>
			<td>    
                <input id="isRoomY" name="isRoom" type="radio" onclick="isRoomClick(this);" class="easyui-validatebox" ${train.isRoom eq '01' ?'checked="checked"':''}  value="01"/>是
                <input id="isRoomN" name="isRoom" type="radio" onclick="isRoomClick(this);" class="easyui-validatebox" ${train.isRoom eq '00' or  empty train.isRoom ?'checked="checked"':''} value="00"/>否
            </td>
            <td></td>
            <td></td>
          </tr>
          <tr id="room" ${train.isRoom eq '01' ?'style="display:"";"':'style="display:none;"'}>
	        <td >住宿开始日期</td>
	        <td >
				<input id="roomStartTime" type="text" class="Wdate" name="roomStartTime" value="<fmt:formatDate value="${train.roomStartTime }" type="date" pattern="yyyy-MM-dd"/>"
	       			 onFocus="var roomStartTime=$dp.$('#roomStartTime');WdatePicker({onpicked:function(){roomEndTime.focus();},maxDate:'#F{$dp.$D(\'roomStartTime\')}'})" readonly/>
			</td>
			<td >住宿结束日期</td>
	        <td >
				<input id="roomEndTime" type="text" class="Wdate" name="roomEndTime" value="<fmt:formatDate value="${train.roomEndTime }" type="date" pattern="yyyy-MM-dd"/>" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'roomStartTime\')}'})" readonly/>
			</td>
          </tr>
          <tr>
            <td >培训班简介</td>
            <td colspan="3">
            <textarea rows="5" cols="80" id="trainIntro" name="trainIntro">${train.trainIntro }</textarea>
			</td>
          </tr>
          <tr>
            <td ><font color="red">*</font>操作</td>
            <td colspan="3" width="600px">
           		<a onclick="selectCourse()" href="javascript:void(0)" class="easyui-linkbutton">选择课程</a>
			</td>
          </tr>
          <tr>
            <td >已选课程</td>
            <td colspan="3" width="600px" id="courseContent">
           		<c:forEach items="${courseList }" var="list">
					<span id='course_id_${list.courseId }'><input checked='checked' type='checkbox' name='courseId' value='${list.courseId }' disabled>${list.courseName }
						<button type='button' class='easyui-linkbutton delete-btn' onclick="$('#course_id_${list.courseId }').remove()"> 删除</button>
					 </span>
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

function isRoomClick(event){
	var isRoom = event.value;
	var trObj = $("#room");
	if(isRoom == "00"){
		$("#roomStartTime").val("");
		$("#roomEndTime").val("");
		trObj.attr("style","display:none");
		$("#isRoomN").attr("checked",true);
		$("#isRoomY").attr("checked",false);
	}else{
		trObj.attr("style","display:''");
		$("#isRoomN").attr("checked",false);
		$("#isRoomY").attr("checked",true);
	}
}

function selectCourse(){
	openWindow('selectCourseWindow','选择课程',0,600,'${ctx}/train/train/selectCourse.do?paramMap[paramCourseIds]='+getSelectExpertIds('courseContent'),true);
}
function submitTrainForm(){
	$('#courseIds').val(getSelectExpertIds('courseContent'));
	$.messager.confirm('确认','确认保存吗？',function(result){
		if (result){  
			$('#train_saveFrom').form('submit',{
				onSubmit: function(){
					if($("#courseIds").val()==''){
						$.messager.progress('close');	
						$.messager.alert('提示','请选择课程！');
						return false;
					}
					
					if($("#isRoomY").attr("checked")){
						if($("#roomStartTime").val() == ""){
							$.messager.progress('close');	
							$.messager.alert('提示','请填写住宿开始时间！');
							return false;
						}
						
						if($("#roomEndTime").val() == ""){
							$.messager.progress('close');	
							$.messager.alert('提示','请填写住宿结束时间！');
							return false;
						}
					}
					
					if($("#startTime").val()==''){
						$.messager.progress('close');	
						$.messager.alert('提示','请填写选课起始日期！');
						return false;
					}
					if($("#endTime").val()==''){
						$.messager.progress('close');	
						$.messager.alert('提示','请填写选课结束日期！');
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
							closeWindow('addTrainWindow');
							$('#trainClassWindow').panel('refresh');
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
		checkBoxs=$("input[name='courseId']","#"+range);
	}else{
		checkBoxs=$("input[name='courseId']");
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
