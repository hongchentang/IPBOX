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

<!-- <h2>培训班信息</h2> -->
<form id="train_saveFrom" name="train_saveFrom" action="${ctx}/train/train/saveTrain.do" method="post" enctype="multipart/form-data">
	 <table class="${tableClass}" cellspacing="0" cellpadding="0" border="0">
          <tr>
            <td width="150">培训班编码</td>
            <td >
			   ${train.trainCode }
			</td>
			<td width="150">培训班名称</td>
            <td >
					${train.trainName }
			</td>
          </tr>
          
           <tr>
            <td >选课起始日期</td>
            <td >
            <fmt:formatDate value="${train.startTime }" type="date" pattern="yyyy-MM-dd"/>
			</td>
			<td >选课结束日期</td>
            <td >
			  <fmt:formatDate value="${train.endTime }" type="date" pattern="yyyy-MM-dd"/>
			</td>
          </tr>
          
          <tr>
            <td >缴费起始日期</td>
            <td >
			   <fmt:formatDate value="${train.cashStartTime }" type="date" pattern="yyyy-MM-dd"/>
			</td>
			<td >缴费结束日期</td>
            <td >
			  <fmt:formatDate value="${train.cashEndTime }" type="date" pattern="yyyy-MM-dd"/>
			</td>
          </tr>
          
          <tr>
            <td >学习起始日期</td>
            <td >
			  <fmt:formatDate value="${train.studyStartTime }" type="date" pattern="yyyy-MM-dd"/>
			</td>
			<td >学习结束日期</td>
            <td >
			  <fmt:formatDate value="${train.studyEndTime }" type="date" pattern="yyyy-MM-dd"/>
			</td>
          </tr>
          
            <tr>
            <td >成绩登记日期</td>
            <td >
			   <fmt:formatDate value="${train.scoreTime }" type="date" pattern="yyyy-MM-dd"/>
			</td>
			<td >期选课人数</td>
            <td >
			   ${train.countChoose }(人)
			</td>
			
          </tr>
          <tr>
            <td >培训班简介</td>
            <td colspan="3">
            <textarea rows="5" cols="80" id="trainIntro" name="trainIntro" readonly="readonly">${train.trainIntro }</textarea>
			</td>
          </tr>
          <tr>
            <td >已选课程</td>
            <td colspan="3" width="600px" id="courseContent">
           		<c:forEach items="${courseList }" var="list">
					${list.courseName }
				</c:forEach>
			</td>
          </tr>
       <!--     <tr>
            <td colspan="4" width="600px" id="courseContent">
           		 <div style="width: 100%;text-align: center;">
           			<a onclick="closeWindow('viewTrainWindow')" href="javascript:void(0)" class="easyui-linkbutton">关闭</a>
				</div>
			</td>
          </tr> -->
        </table>
</form>

<script type="text/javascript">
tableVBg('.${tableClass}');
$(document).ready(function (){
});

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
