<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:choose>
	<c:when test="${ipanthercommon:isSpace(sessionScope.loginUser.roleCode)}">
		<c:set var="tableClass" value="alter-table-v-space"/>
	</c:when>
	<c:otherwise>
		<c:set var="tableClass" value="alter-table-v"/>
	</c:otherwise>
</c:choose>
<h2>课程信息</h2>
<form id="course_auditFrom" name="course_auditFrom" action="${ctx}/train/course/audit.do" method="post">
	 <input type="hidden" name="id" value="${course.id }">
	 <input type="hidden" name="procInstId" value="${course.procInstId }">
	 <input type="hidden" name="declareUser" value='${course.declareUser }'/>
	 <input type="hidden" id="status" name="paramMap[status]" value="">
	 <input type="hidden" name="applyCount" value="${course.applyCount }">
	 <table class="${tableClass}" cellspacing="0" cellpadding="0" border="0">
          <tr>
            <td width="160px">课程编码</td>
            <td >
				${course.courseCode }
			</td>
			<td rowspan="3" width="160px">课程图片预览</td>
            <td rowspan="3" width="300px">
			   <c:if test="${not empty course.courseImg}">
				     <c:set value="${ipanthercore:getJSONMap(course.courseImg)}" var="map" />
					 <img src="${ctx}${map.downloadUrl}" border="0" width="150px" height="150px">
				</c:if>
				<c:if test="${empty course.courseImg}">
				     <c:set value="${ipanthercore:getJSONMap(course.courseImg)}" var="map" />
					 <img src="${ctx}/themes/easyui/themes/tms/images/default.jpg" border="0">
				</c:if>
			</td>
          </tr>
          <tr>
          	<td >课程名称</td>
            <td >
					${course.courseName }
			</td>
          </tr>
           <tr >
            <td >课程简介</td>
            <td >
           		 ${course.courseIntro }
			</td>
          </tr>
          
           <tr>
            <td >上线日期</td>
            <td >
				<fmt:formatDate value="${course.startTime }" type="date" pattern="yyyy-MM-dd"/>
			</td>
			<td >下线日期</td>
            <td >
			  <fmt:formatDate value="${course.endTime }" type="date" pattern="yyyy-MM-dd"/>
			</td>
          </tr>
          
          <tr>
	            <td >主讲教师姓名</td>
	            <td >
				  ${course.teacherName }
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
	          <td >主讲教师所在单位</td>
	            <td >
					${course.teacherUnit }
				</td>
          </tr>
           <tr>
	            <td >主讲教师专业技术职务</td>
	            <td >
				   ${course.teacherJob }
				</td>
          </tr>
           <tr>
            <td >学段</td>
	            <td >
	            ${dict:getEntryName('IPR_COURSE_STAGE',course.courseStage) }
				</td>
			<td >培训形式</td>
            <td >
            	${dict:getEntryName('STUDY_TYPE',course.trainType) }
			</td>
          </tr>
          
          <tr>
            <td >学时数</td>
            <td >
			   ${course.studyHours }
			</td>
			<td >学时类型</td>
            <td >
            	${dict:getEntryName('HOURS_TYPE',course.hoursType) }
			</td>
          </tr>
          
          <tr>
            <td >课程类别</td>
            <td >
            	${dict:getEntryName('RKKCLB',course.courseType) }
			</td>
			<td >课程级别</td>
            <td >
            ${dict:getEntryName('IPR_COURSE_LEVEL',course.courseLevel) }
			</td>
          </tr>
            
          <tr>
            <td >适用对象专业技术职务</td>
            <td >
			   ${course.applyJob }
			</td>
			<td >适用岗位</td>
            <td >
            <span id="applyPositionValue"></span>
             <div style="display: none;">
             <select name="applyPosition" id="applyPosition" class="easyui-validatebox"  style="width:300px;" required></select>
              <script type="text/javascript">
                   jQuery("#course_auditFrom").find("#applyPosition").combotree({
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
								jQuery("#course_auditFrom").find("#applyPosition").combotree('setValues',ss);
							}
						 }
					}); 
                   jQuery("#course_auditFrom").find("#applyPositionValue").html(jQuery("#course_auditFrom").find('#applyPosition').combo('getText'))   
                </script>
             </div>
			</td>
          </tr>
          
           <tr>
            <td >配套教材（学习资料）</td>
            <td >
			   ${course.hasBook }
			</td>
			<td >教材（学习资料）名称</td>
            <td >
					${course.bookName }
			</td>
          </tr>
           <tr>
            <td >教材（学习资料）形式</td>
            <td >
			   ${course.bookForm }
			</td>
			<td >出版社</td>
            <td >
					${course.publication }
			</td>
          </tr>
           <tr>
            <td >作者</td>
            <td >
			   ${course.author }
			</td>
			 <td >出版日期</td>
            <td >
			   <fmt:formatDate value="${course.publishTime }" type="date" pattern="yyyy-MM-dd"/>
			</td>
          </tr>
          <tr>
            <td >作者介绍</td>
            <td >
            ${course.authorDesc }
			</td>
			<td></td><td></td>
          </tr>
           <tr>
			  <td >教材（学习资料）费收费方式</td>
            <td >
			   ${course.bookCashWay }
			</td>
			 <td >教材（学习资料）费</td>
            <td >
			   ${course.bookCash }
			</td>
          </tr>
          <tr>
            <td >培训费收费单位</td>
            <td >
			   ${course.trainCashUnit }
			</td>
			<td >培训费收费方式</td>
            <td >
					${course.trainCashWay }
			</td>
          </tr>
          <tr>
			<td >教材（学习资料）费收费单位</td>
            <td >
					${course.bookCashUnit }
			</td>
			<td >培训费</td>
            <td >
					${course.trainCash }
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
					${course.totalCash }
			</td>
          </tr>
         
        </table>
        <!-- 审核DIV -->
        <c:if test="${empty viewValue }"><!-- 若为查看，不显示审核 -->
		  <h2>课程审核</h2>
		  <table class="alter-table-v" cellspacing="0" cellpadding="0" border="0">
		  <tr>
		   <td style="width: 100px">当前申请次数</td>
		   <td style="width: 200px">${course.applyCount }</td>
		   <td style="width: 100px">最大申请次数</td>
		   <td ><input type="text" class="easyui-numberbox" data-options="min:1,precision:0,required:true" id="maxApplyCount" name="maxApplyCount" value="${course.maxApplyCount }"/></td>
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
</form>
<script type="text/javascript">
tableVBg('.${tableClass}');
$(document).ready(function (){
});


function submitAudiForm(status){
	$.messager.confirm('确认','确认审核吗？',function(result){    
		if (result){
			$('#status').val(status);
			$('#course_auditFrom').form('submit',{
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
							closeWindow('auditCourseWindow');
							$("#viewListTodo").panel('refresh');
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
