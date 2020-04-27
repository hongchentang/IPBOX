<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<style>
.chose_result_div {
	border:1px solid #EAF2FF;
	width: 100%;
	height:70px;
	overflow:auto;
	margin-top: 5px;
}
.choose_result_tr {
	height: 115px;
	text-align: left;
	vertical-align: top;
}
</style>
<form id="checkForm" name="checkForm" action="${ctx}/train/effect/check/save.do" method="post" >
		<input type="hidden" name="id" value="${check.id}"/>
		<input type="hidden" id="projectIds" name="projectIds" value=""/>
		<input type="hidden" id="teacherIds" name="teacherIds" value=""/>
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<tbody>
			<tr>
				<td width="20%"><span style="color: red">*</span>标题</td>
				<td width="80%">
					<input type="text" class="easyui-textbox" required name="title" value="${check.title}"/>
				</td>
			</tr>
			<tr>
				<td><span style="color: red">*</span>描述</td>
				<td>
					<textarea style="width:98%;" rows="2" name="remark" class="easyui-validatebox" required>${check.remark}</textarea>
				</td>
			</tr>
			<tr>
				<td><span style="color: red">*</span>抽查项目</td>
				<td class="choose_result_tr">
					<button type="button" id="chooseProjectButton" class="easyui-linkbutton" ><i class="fa fa-plus"></i>选择项目</button>
					<div class="chose_result_div" id="project_div">
						
					</div>
				</td>
			</tr>
			<tr>
				<td><span style="color: red">*</span>评估专家</td>
				<td class="choose_result_tr">
					<button type="button" id="chooseTeacherButton" class="easyui-linkbutton" ><i class="fa fa-plus"></i>选择专家</button>
					<div class="chose_result_div" id="teacher_div">
						
					</div>
				</td>
			</tr>
		</tbody>
		</table>
		<div style="text-align: center">
			<button type="button" onclick="save()" class="easyui-linkbutton"  > <i class="fa fa-save"></i>保 存 </button>
		</div>
</form>
<div id="project_template" style="display: none;border-bottom:1px solid #EAF2FF;">
	<div name="project_id" style="display: none"></div>
	<div style="float:right"><a onclick="removeSelected(this)" href="###" class="delete-bnt po-right fa fa-trash-o">删除</a></div>
	<div name="project_name" style="width:400px;font-size: 14px;"></div>
</div>
<div id="teacher_template" style="display: none;border-bottom:1px solid #EAF2FF;">
	<div name="teacher_id" style="display: none"></div>
	<div style="float:right"><a onclick="removeSelected(this)" href="###" class="delete-bnt po-right fa fa-trash-o">删除</a></div>
	<div name="teacher_name" style="width:400px;font-size: 14px;"></div>
</div>
<script type="text/javascript">
tableVBg('.alter-table-v');
$(document).ready(function (){
	//选择项目
	$(document).find('#chooseProjectButton').linkbutton({onClick: function(){
		easyuiUtils.openWindow('chooseProjectWindow','选择项目',900,530,'${ctx}/train/project/chooseProject.do?paramMap[notIds]='+getProjectIds(),true);
	}});
	
	//选择专家/教师
	$(document).find('#chooseTeacherButton').linkbutton({onClick: function(){
		easyuiUtils.openWindow('chooseTeacherWindow','选择专家',900,530,'${ctx}/common/user/teacher/chooseTeacher.do?paramMap[notIds]='+getTeacherIds(),true);
	}});
	
	//初始化项目
	addProject('${check.projectIds}');
	//初始化专家
	addTeacher('${check.teacherIds}');
});
function save() {
	$.messager.confirm('提示', '确定保存？', function(r){
		if(r) {
			$("#teacherIds").val(getTeacherIds());
			$("#projectIds").val(getProjectIds());
			if(""==$("#projectIds").val()) {
				jQuery.messager.alert("提示信息","请至少选择一个抽查项目","error");
				return false;
			}
			if(""==$("#teacherIds").val()) {
				jQuery.messager.alert("提示信息","请至少选择一个评估专家","error");
				return false;
			}
			jQuery('#checkForm').form('submit',{
				onSubmit: function(){
					 return $('#checkForm').form('validate');
				},
			    success: function(data){
			    	var json=jQuery.parseJSON(data);
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							jQuery.messager.alert("提示信息",message,"error");
						} else if(parseInt(statusCode)==200){
							jQuery.messager.alert("提示信息","保存成功","info");
							jQuery('#'+getCurrentTabId()).panel('refresh');
							closeWindow("showAddDialog");
						}
					}
			    }
			}); 
		}
		
	});
}

//增加项目选项
function addProject(projects) {
	var projectArrs = projects.split("★");
	for(var i =0;i<projectArrs.length;i++) {
		if(""!=projectArrs[i]) {
			var projectArr = projectArrs[i].split("☆");
			
			var newProject = $('#project_template').clone();
			newProject.attr("id","");
			newProject.show();
			newProject.find("div[name='project_id']").html(projectArr[0]);
			newProject.find("div[name='project_name']").html(projectArr[1]);
			$('#project_div').append(newProject);
		}
	}
}

//选择项目的ID，多个用逗号隔开
function getProjectIds() {
	var projectIds = "";
	$("#project_div").find("div[name='project_id']").each(function() {
		projectIds+=","+$(this).html();
	});
	if(""!=projectIds) {
		projectIds = projectIds.substr(1);
	}
	return projectIds;
}

//增加专家/教师
function addTeacher(teachers) {
	var teacherArrs = teachers.split("★");
	for(var i =0;i<teacherArrs.length;i++) {
		if(""!=teacherArrs[i]) {
			var teacherArr = teacherArrs[i].split("☆");
			
			var newTeacher = $('#teacher_template').clone();
			newTeacher.attr("id","");
			newTeacher.show();
			newTeacher.find("div[name='teacher_id']").html(teacherArr[0]);
			newTeacher.find("div[name='teacher_name']").html(teacherArr[1]);
			$('#teacher_div').append(newTeacher);
		}
	}
}


//选择教师的ID，多个用逗号隔开
function getTeacherIds() {
	var teacherIds = "";
	$("#teacher_div").find("div[name='teacher_id']").each(function() {
		teacherIds+=","+$(this).html();
	});
	if(""!=teacherIds) {
		teacherIds = teacherIds.substr(1);
	}
	return teacherIds;
}

//删除已选择的内容
function removeSelected(obj) {
	$(obj).parent().parent().remove();
}
</script>
