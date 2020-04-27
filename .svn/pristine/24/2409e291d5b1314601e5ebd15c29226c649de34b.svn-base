<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<%@ page import="java.util.Date" %>
<%@ include file="/themes/skin/ipr/inc.jsp" %>
<form id="courseTrain" action="${ctx}/train/talentTrain/listCourseTrain.do" method="post">
<input type="hidden" name="paramMap[courseId]" value="${searchParam.paramMap.courseId }">
<table width="100%" border="0" cellspacing="1" cellpadding="1" class="m-table">
	<thead>
	    <tr>
	      <th>项目</th>
	      <th>培训班</th>
	      <th>已报名人数</th>
	      <th>选课开始时间</th>
	      <th>选课结束时间</th>
	      <th>报名</th>
	    </tr>
	  </thead>
	  <jsp:include page="${ctx}/jsp/common/include/manager_page_course.jsp">
	  	<jsp:param name="pageForm" value="courseTrain"/>
		<jsp:param name="divId" value="trainCourseContent"/>
	  </jsp:include>
	  <tbody>
	   <c:if test="${not empty courseTrain}">
		  <c:set value="<%=new Date() %>" var="date"/>
		  <c:forEach items="${courseTrain }" var="ct">
		  	<tr>
		      <td>${ct.projectName }</td>
		      <td>${ct.trainName }</td>
		      <td>${ct.trainCount }人</td>
		      <td><fmt:formatDate value="${ct.trainStartTime }"  pattern="yyyy-MM-dd"/></td>
		      <td><fmt:formatDate value="${ct.trainEndTime }"  pattern="yyyy-MM-dd"/></td>
		      <td>
		      <c:if test="${ct.trainStartTime < date and ct.trainEndTime > date}">
			      <c:if test="${ct.isStopApply ne '1' }">
			     	 	<input type="button" value="+报名" class="u-btn u-btn1" onclick="signUp('${ct.trainId }','${ct.isRoom }','${ct.roomStartTime}','${ct.roomEndTime}')">
			      </c:if>
			      	<c:if test="${ct.isStopApply eq '1' }">
			      		<input type="button" value="名额已满" class="u-btn u-btn1">
			      	</c:if>
		      </c:if>
		      <c:if test="${ct.trainStartTime > date or ct.trainEndTime < date}">
		      	<input type="button" value="+报名" disabled="disabled" class="u-btn u-btn1 disabled">
		      </c:if>
		      </td>
		    </tr>
		    </c:forEach>
		  </c:if>
 		  <c:if test="${empty courseTrain}">
 		  	<tr>
 		  		<td></td>
 		  		<td></td>
 		  		<td></td>
 		  		<td></td>
 		  		<td></td>
 		  		<td></td>
 		  	</tr>
 		  
 		  </c:if>
	  </tbody>
</table>
</form>

<script type="text/javascript" src="${ctx}/js/jquery/plugins/jquery-cookie/jquery.cookie.js"></script>
<script type="text/javascript" >
function signUp(trainId,isRoom,roomStartTime,roomEndTime){
	//alert(roomStartTime);
	var isLogin='${loginUser}';
	var paperworkNo = '${loginUser.paperworkNo}';
	if(isLogin==''){
		if(confirm('您还没有登录，是否先登录？')){
			$.cookie('paramType', '${searchParam.paramMap.courseId}',{expires: 1, path: '/'});
			window.location.href="${ctx}/login.do";
		}
	}else if(paperworkNo == ''){
		if(confirm('您需要完善个人资料后，才能报名，是否现在就去完善个人资料？')){
			window.location.href="/space/user/tabs.do";
		}
	}else{
		if(confirm('是否确定报名？')){
			if(isRoom == "01"){
				openWindow("roomTimeWindow","提示",410,230,"${ctx}/train/talentTrain/wirteRoomTime.do?paramMap[trainId]="+trainId+"&paramMap[roomStartTime]="+roomStartTime+"&paramMap[roomEndTime]="+roomEndTime,true);
			}else{
				$.ajax({
					url : "${ctx}/train/talentTrain/signUp.do?paramMap[trainId]="+trainId+"&paramMap[isRoom]="+isRoom,
					//data : $("#courseForm").serialize(),
					type : "post",
					success : function(data) {
						var json=eval(data);
						if(json.statusCode=="200"){
							alert("报名成功!待管理员审核，审核通过即可参加培训。");
						}else if(json.statusCode=="300"){
							alert('已经报过名!');
						}else{
							alert("抱歉！您不属于培训对象！");
						}
					}
				})
			}
		}
	}
}

</script>