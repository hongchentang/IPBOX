<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<%@ page import="java.util.Date" %>
<%@ include file="/themes/skin/ipr/inc.jsp" %>

	<input type="hidden" name="trainId" id="trainId" value="${trainId}"/>
	<input type="hidden" name="roomStartTime" id="roomStartTime" value="${roomStartTime}"/>
	<input type="hidden" name="roomEndTime" id="roomEndTime" value="${roomEndTime}"/>
	<table class="alter-table-v" cellspacing="0" cellpadding="0" border="0">
		<tr>
			<td style="font-size: 16px;">该课程可安排住宿，是否需要住宿呢?</td>
		</tr>
		<tr>
			<td>
				<input id="isRoomY" name="isRoom" type="radio" onclick="isRoomClick(this);" class="easyui-validatebox" 
					style="margin-left: -10px;" value="01"/>是
                <input id="isRoomN" name="isRoom" type="radio" onclick="isRoomClick(this);" class="easyui-validatebox" 
                	style="margin-left: 120px;" checked="checked" value="00"/>否
			</td>
		</tr>
		<tr id="room" style="display:none;">
			<td>住宿时间：<input id="startTime" type="text" class="Wdate" name="startTime" type="date"
	       			 	onFocus="var startTime=$dp.$('#startTime');WdatePicker({onpicked:function(){endTime.focus();},maxDate:'#F{$dp.$D(\'startTime\')}'})" readonly/>
	       				 至
	       				<input id="endTime" type="text" class="Wdate" name="endTime"  type="date" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}',maxDate:'#F{$dp.$D(\'endTime\')}'})" readonly/>	 
	       			 </td>	
		</tr>
		<tr>
			<td><span style="color: red;">注意：该门课程安排的住宿日期为：${roomStartTime}至${roomEndTime}，请选择安排住宿日期内住宿时间。</span></td>
		</tr>
		<tr>
			<td>
				<button type="button" onclick="signUp('${trainId}');" style="float: center; margin-right: 20px" class="easyui-linkbutton">
            	保存
            	</button>
			</td>
		</tr>
	</table>

<script>
	function isRoomClick(event){
		var isRoom = event.value;
		var trObj = $("#room");
		if(isRoom == "00"){
			$("#startTime").val("");
			$("#endTime").val("");
			trObj.attr("style","display:none");
			$("#isRoomN").attr("checked",true);
			$("#isRoomY").attr("checked",false);
		}else{
			trObj.attr("style","display:''");
			$("#isRoomN").attr("checked",false);
			$("#isRoomY").attr("checked",true);
		}
	}
	
	function signUp(trainId){
		var startTime = $("#startTime").val();
		var endTime = $("#endTime").val();
		var isRoom = "00";
		if($("#isRoomY").attr("checked")){
			isRoom = "01";
			if($("#startTime").val() == "" || $("#endTime").val() == ""){
				$.messager.progress('close');	
				$.messager.alert('提示','请填写住宿时间段！');
				return false;
			}
			
			if($("#startTime").val() > $("#endTime").val()){
				$.messager.progress('close');	
				$.messager.alert('提示','住宿开始时间不能大于结束时间！');
				return false;
			}
		}
		if(confirm('是否确定保存？')){
			$.ajax({
				url : "${ctx}/train/talentTrain/signUp.do?paramMap[trainId]="+trainId+"&paramMap[startTime]="+startTime+"&paramMap[endTime]="+endTime+"&paramMap[isRoom]="+isRoom,
				type : "post",
				success : function(data) {
					var json=eval(data);
					if(json.statusCode=="200"){
						alert("报名成功!待管理员审核，审核通过即可参加培训。");
						location.reload();
					}else if(json.statusCode=="300"){
						alert('已经报过名!');
						location.reload();
					}else{
						alert("抱歉！您不属于培训对象！");
						location.reload();
					}
				}
			})
		}
	}
</script>