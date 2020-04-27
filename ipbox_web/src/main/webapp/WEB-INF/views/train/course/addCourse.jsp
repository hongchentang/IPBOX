<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes.image')}" var="fileTypes"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes')}" var="fileTypes2"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize2"/>

<form id="course_saveFrom" name="course_saveFrom" action="${ctx}/train/course/saveCourse.do" method="post" enctype="multipart/form-data">
	 <input type="hidden" name="id" value="${course.id }">
	 <input type="hidden" name="courseImg" value='${course.courseImg}'>
	 <input type="hidden" class="easyui-textbox" id="attachment" name="attachment" value='${course.attachment }'/>
	 <table class="alter-table-v" cellspacing="0" cellpadding="0" border="0">
          <tr>
	            <td ><font color="red">*</font>课程编码</td>
	            <td >
				   <input type="text" class="easyui-textbox"  data-options="required:true" id="courseCode" name="courseCode" value="${course.courseCode }"/>
				</td>
				<td rowspan="3">课程图片预览</td>
	            <td rowspan="3">
				   <c:if test="${not empty course.courseImg}">
					     <c:set value="${ipanthercore:getJSONMap(course.courseImg)}" var="map" />
						 <img src="${ctx}${map.downloadUrl}" border="0" width="150px" height="150px">
					</c:if>
					<c:if test="${empty course.courseImg}">
						 <img src="${ctx}/themes/easyui/themes/tms/images/default.jpg" border="0">
					</c:if>
				</td>
          </tr>
          <tr>
	          	<td ><font color="red">*</font>课程名称</td>
	            <td >
						<input type="text" class="easyui-textbox" data-options="required:true" id="courseName" name="courseName" value="${course.courseName }"/>
				</td>
          </tr>
           <tr >
	            <td >课程简介</td>
	            <td >
	            <textarea rows="2" cols="25" id="courseIntro" name="courseIntro">${course.courseIntro }</textarea>
				</td>
          </tr>
          <tr >
	           <td ><font color="red">*</font>学段</td>
	            <td >
	           		<select  id="courseStage" name="courseStage" class="easyui-validatebox" data-options="panelHeight:'auto',required:true" style="width: 158px;">
							<option value="">--请选择--</option>
							${dict:getEntryOptionsSelected('IPR_COURSE_STAGE',course.courseStage) }
						</select>
				</td>
	            <td >上传课程图片</td>
	            <td >
	           		<input type="file" id="upload" name="uploadImg">
	           			<div>
							     允许上传的文件类型：${fileTypes}<br/>
							    允许上传文件的大小：${fileMaxSize}KB 
						</div>
				</td>
          </tr>
           <tr>
	            <td >上线日期</td>
	            <td >
				   <input id="startTime" type="text" class="Wdate" name="startTime" value="<fmt:formatDate value="${course.startTime }" type="date" pattern="yyyy-MM-dd"/>"
	       				 onFocus="var startTime=$dp.$('#startTime');WdatePicker({onpicked:function(){endTime.focus();},maxDate:'#F{$dp.$D(\'startTime\')}'})" readonly/>
				</td>
				<td >下线日期</td>
	            <td >
				  <input id="endTime" type="text"  class="Wdate" name="endTime" value="<fmt:formatDate value="${course.endTime }" type="date" pattern="yyyy-MM-dd"/>" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'startTime\')}'})" readonly/>
				</td>
          </tr>
          <tr>
	            <td ><font color="red">*</font>主讲教师姓名</td>
	            <td >
				   <%-- <input type="text" class="easyui-textbox" id="realName"  value="${course.realName }" readonly="readonly"/> --%>
				   <input type="text" id="teacherName" class="easyui-textbox" data-options="required:true" name="teacherName" value="${course.teacherName }">
				   <!-- <a onclick="selectTeacher()" href="javascript:void(0)" class="easyui-linkbutton">选择</a> -->
				</td>
				<td rowspan="3">主讲教师头像预览</td>
	            <td rowspan="3">
					   <c:if test="${not empty course.teacherAvatar}">
						     <c:set value="${ipanthercore:getJSONMap(course.teacherAvatar)}" var="map" />
							 <img src="${ctx}${map.downloadUrl}" border="0" width="150px" height="150px">
						</c:if>
						<c:if test="${empty course.teacherAvatar}">
							 <img src="${ctx}/themes/easyui/themes/tms/images/default.jpg" border="0">
						</c:if>
				</td>
          </tr>
          <tr>
	          <td ><font color="red">*</font>主讲教师所在单位</td>
	            <td >
						<input type="text" class="easyui-textbox" id="teacherUnit" data-options="required:true" name="teacherUnit" value="${course.teacherUnit }"/>
				</td>
          </tr>
          
           <tr>
	            <td >主讲教师专业技术职务</td>
	            <td >
				   <input type="text" class="easyui-textbox"  id="teacherJob" name="teacherJob" value="${course.teacherJob }"/>
				</td>
          </tr>
          <tr>
	          	<td ><font color="red">*</font>培训形式</td>
	            <td >
					<select  id="trainType" name="trainType" class="easyui-validatebox" data-options="panelHeight:'auto',required:true" style="width: 158px;">
							<option value="">--请选择--</option>
							${dict:getEntryOptionsSelected('STUDY_TYPE',course.trainType) }
						</select>
				</td>
				 <td >上传主讲教师头像</td>
	            <td >
	           		<input type="file" name="uploadAvatar">
	           			<div>
							     允许上传的文件类型：${fileTypes}<br/>
							    允许上传文件的大小：${fileMaxSize}KB 
						</div>
				</td>
          </tr>
          <tr>
	            <td ><font color="red">*</font>学时数</td>
	            <td >
				   <input type="text" class="easyui-numberbox" data-options="min:0,precision:0,required:true" id="studyHours" name="studyHours" value="${course.studyHours }"/>
				</td>
				<td ><font color="red">*</font>学时类型</td>
	            <td >
					<select  id="hoursType" name="hoursType" class="easyui-validatebox" data-options="panelHeight:'auto',required:true" style="width: 158px;">
							<option value="">--请选择--</option>
							${dict:getEntryOptionsSelected('HOURS_TYPE',course.hoursType) }
						</select>
				</td>
          </tr>
          
          <tr>
	            <td ><font color="red">*</font>课程类别</td>
	            <td >
				   <select  id="courseType" name="courseType" class="easyui-validatebox" data-options="panelHeight:'auto',required:true" style="width: 158px;">
							<option value="">--请选择--</option>
							${dict:getEntryOptionsSelected('RKKCLB',course.courseType) }
					</select>
				</td>
				<td ><font color="red">*</font>课程级别</td>
	            <td >
						 <select  id="courseLevel" name="courseLevel" class="easyui-validatebox" data-options="panelHeight:'auto',required:true" style="width: 158px;">
								<option value="">--请选择--</option>
								${dict:getEntryOptionsSelected('IPR_COURSE_LEVEL',course.courseLevel) }
						</select>
				</td>
          </tr>
            
          <tr>
	            <td >适用对象专业技术职务</td>
	            <td >
					 <input type="text" class="easyui-textbox"  id="applyJob" name="applyJob" value="${course.applyJob }"/>
				</td>
				<td ><font color="red">*</font>适用岗位</td>
	            <td >
						<%--  <select  id="applyPosition" name="applyPosition" class="easyui-validatebox" data-options="panelHeight:'auto',required:true" style="width: 200px;">
								<option value="">--请选择--</option>
								${dict:getEntryOptionsSelected('IPR_APPLY_POSITION',course.applyPosition) }
						</select> --%>
						<select name="applyPosition" id="applyPosition" class="easyui-validatebox"  style="width:300px;" required></select>
                             <script type="text/javascript">
                             var isSelectAll = false;
                             jQuery("#course_saveFrom").find("#applyPosition").combotree({
								data: ${dict:getEntryListJsonOpen("IPR_APPLY_POSITION","true")}, 
								id:'dictValue',
								textField:'dictName',
								parentField:'pid',
								panelHeight:'200',
								checkbox:true,
								onlyLeafCheck:true,
								multiple:true,
								onLoadSuccess:function(){
									var value='${course.applyPosition}';
									var ss=value.split(",")
									//value = value.replace(/\"/g,'').replace(/\[/g,'').replace(/\]/g,'');
									if(ss!=''){
										  jQuery("#course_saveFrom").find("#applyPosition").combotree('setValues',ss);
									}
								 },
								 onCheck: function(node, checked){
										if(node.id=='10000'){
											if(!isSelectAll){
												jQuery("#course_saveFrom").find("#applyPosition").combotree('clear');
												selectTreeAll('applyPosition');
												isSelectAll =true;
											}else{
												jQuery("#course_saveFrom").find("#applyPosition").combotree('clear');
												isSelectAll =false;
											}
										}
									}
							}); 
                            </script>
				</td>
          </tr>
          
           <tr>
	            <td >配套教材（学习资料）</td>
	            <td >
				   <input type="text" class="easyui-textbox"  id="hasBook" name="hasBook" value="${course.hasBook }"/>
				</td>
				<td >教材（学习资料）名称</td>
	            <td >
						<input type="text" class="easyui-textbox" id="bookName" name="bookName" value="${course.bookName }"/>
				</td>
          </tr>
           <tr>
	            <td >教材（学习资料）形式</td>
	            <td >
				   <input type="text" class="easyui-textbox"  id="bookForm" name="bookForm" value="${course.bookForm }"/>
				</td>
				<td >出版社</td>
	            <td >
						<input type="text" class="easyui-textbox" id="publication" name="publication" value="${course.publication }"/>
				</td>
          </tr>
           <tr>
	            <td >作者</td>
	            <td >
				   <input type="text" class="easyui-textbox"  id="author" name="author" value="${course.author }"/>
				</td>
				 <td >出版日期</td>
	            <td >
				   <input id="publishTime" type="text"  class="Wdate" name="publishTime" value="<fmt:formatDate value="${course.publishTime }" type="date" pattern="yyyy-MM-dd"/>" onFocus="WdatePicker()" readonly/>
				</td>
          </tr>
          <tr>
	            <td >作者介绍</td>
	            <td >
	            <textarea rows="2" cols="25" id="authorDesc" name="authorDesc">${course.authorDesc }</textarea>
				</td>
				<td></td><td></td>
          </tr>
           <tr>
				  <td >教材（学习资料）费收费方式</td>
	            <td >
				   <input type="text" class="easyui-textbox"  id="bookCashWay" name="bookCashWay" value="${course.bookCashWay }"/>
				</td>
				 <td >教材（学习资料）费</td>
	            <td >
				   <input type="text" class="easyui-numberbox" data-options="min:0,precision:2" id="bookCash" name="bookCash" value="${course.bookCash }"/>
				</td>
          </tr>
          <tr>
	            <td >培训费收费单位</td>
	            <td >
				   <input type="text" class="easyui-textbox" id="trainCashUnit" name="trainCashUnit" value="${course.trainCashUnit }"/>
				</td>
				<td >培训费收费方式</td>
	            <td >
						<input type="text" class="easyui-textbox"  id="trainCashWay" name="trainCashWay" value="${course.trainCashWay }"/>
				</td>
          </tr>
          <tr>
				<td >教材（学习资料）费收费单位</td>
	            <td >
						<input type="text" class="easyui-textbox"  id="bookCashUnit" name="bookCashUnit" value="${course.bookCashUnit }"/>
				</td>
				<td >培训费</td>
	            <td >
						<input type="text" class="easyui-numberbox" data-options="min:0,precision:2" id="trainCash" name="trainCash" value="${course.trainCash }"/>
				</td>
          </tr>
          
            <tr>
            <td >附件</td>
            <td >
					 <c:if test="${not empty course.attachment}"> 
					      <c:set value="${ipanthercore:getJSONMap(course.attachment)}" var="map" />
						      <div id="fileSpanAttachment">
							  	<span id="attachmentName"><a href="${ctx}${map.downloadUrl}" target="download">${map.attachmentName}(点击下载)</a></span> 
							  </div>
					    </c:if>
			</td>
			<td >费用合计</td>
            <td >
					<input type="text" class="easyui-numberbox" data-options="min:0,precision:2" id="totalCash" name="totalCash" value="${course.totalCash }"/>
			</td>
          </tr>
          <tr>
          	<td>学员类别</td>
          	<td>
          	<select name="personType" id="personType" class="easyui-validatebox"  style="width:300px;" required></select>
                             <script type="text/javascript">
                             jQuery("#course_saveFrom").find("#personType").combotree({
								data: ${dict:getEntryListJsonOpen("USER_TYPE","true")}, 
								id:'dictValue',
								textField:'dictName',
								parentField:'pid',
								panelHeight:'200',
								checkbox:true,
								onlyLeafCheck:true,
								multiple:true,
								cascadeCheck:false,
								onLoadSuccess:function(){
									var value='${course.personType}';
									var ss=value.split(",")
									value = value.replace(/\"/g,'').replace(/\[/g,'').replace(/\]/g,'');
									if(ss!=''){
										  jQuery("#course_saveFrom").find("#personType").combotree('setValues',value);
									}
								 }
								,
								 onCheck: function(node, checked){
										if(node.id=='10000'){
											if(!isSelectAll){
												jQuery("#course_saveFrom").find("#personType").combotree('clear');
												selectTreeAll('personType');
												isSelectAll =true;
											}else{
												jQuery("#course_saveFrom").find("#personType").combotree('clear');
												isSelectAll =false;
											}
										}
									}
							}); 
                            </script>
          	</td>
          	<td>行业类别</td>
          	<td>
          	<select name="industryType" id="industryType" class="easyui-validatebox"  style="width:300px;" required></select>
                             <script type="text/javascript">
                             jQuery("#course_saveFrom").find("#industryType").combotree({
								data: ${dict:getEntryListJsonOpen("IPR_INDUSTRY_TYPE","true")}, 
								id:'dictValue',
								textField:'dictName',
								parentField:'pid',
								panelHeight:'200',
								checkbox:true,
								onlyLeafCheck:true,
								multiple:true,
								onLoadSuccess:function(){
									var value='${course.industryType}';
									//alert(value);
									var ss=value.split(",")
									value = value.replace(/\"/g,'').replace(/\[/g,'').replace(/\]/g,'');
									if(ss!=''){
										  jQuery("#course_saveFrom").find("#industryType").combotree('setValues',value);
									}
								 }
								,
								 onCheck: function(node, checked){
										if(node.id=='10000'){
											if(!isSelectAll){
												jQuery("#course_saveFrom").find("#industryType").combotree('clear');
												selectTreeAll('industryType');
												isSelectAll =true;
											}else{
												jQuery("#course_saveFrom").find("#industryType").combotree('clear');
												isSelectAll =false;
											}
										}
									}
							}); 
                            </script>
          	</td>
          </tr>
          <tr>
          		<td>上传附件</td>
          		<td>
          			<input type="file"  name="uploadFile">
           			<div>
						     允许上传的文件类型：${fileTypes2}<br/>
						    允许上传文件的大小：${fileMaxSize2}KB 
					</div>
          		</td>
          	</tr>
          <tr style="text-align: center;line-height: 20px">
            <td colspan="4">
            <div style="width: 100%;text-align: center;">
            <button type="button" onclick="javascript:submitThisForm();" style="float: center; margin-right: 20px" class="easyui-linkbutton">
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

function submitThisForm(){
	$.messager.confirm('确认','确认保存吗？',function(result){    
		if (result){  
			$('#course_saveFrom').form('submit',{
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	
						$.messager.alert('提示','请填写必填项！');
					}			
					return isValid;	
				},
				success: function(data){
					//console.log(data);
					var json = eval('(' + data + ')');  
					//console.log(data);
					//var json=jQuery.parseJSON(data);
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							$.messager.alert('提示',message);
						}else if(parseInt(statusCode)==200){
							closeWindow('addCourseWindow');
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
function selectTeacher(){
	openWindow('divideTeacherWindow','选择教师',550,450,'${ctx}/train/courseTeacher/selectTeacher.do',true);
}

function selectBnt(ObjectId){
	var treeObj=jQuery("#course_saveFrom").find('#'+ObjectId).combotree('tree');
	var node=treeObj.tree('getRoots')[0];
	treeObj.tree('insert', {
			before: node.target,
			data: {
				id: '10000',
				text: '全选',
			}
		});
}
function selectTreeAll(ObjectId){
	var treeObj=jQuery("#course_saveFrom").find('#'+ObjectId).combotree('tree');
	//var rootNodes=treeObj.tree('getRoots');
	var childrens = treeObj.tree('getChildren');
	var ids = "";
	for(var i=0;i<childrens.length;i++){
		if(treeObj.tree('isLeaf',childrens[i].target)){
			if(childrens[i].id!='10000'){
				ids+=","+childrens[i].id;
			}
		};
	}
	ids = ids.substring(1);
	jQuery("#course_saveFrom").find('#'+ObjectId).combotree('setValues',ids);
}
selectBnt("applyPosition");
selectBnt("personType");
selectBnt("industryType");
</script>
