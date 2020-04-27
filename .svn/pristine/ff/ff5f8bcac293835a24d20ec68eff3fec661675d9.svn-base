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
<%--
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
--%>

<table class="${tableClass}" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td style="width: 10%;">注册号/申请号</td>
		<td style="width: 35%;">${trademark.registeredNumber}</td>
		<td style="width: 10%;">商标名称</td>
		<td style="width: 35%;">${trademark.registeredName}</td>
	</tr>
	<tr>
		<td>商标注册类型</td>
		<td>
			${dict:getEntryName('IPBOX_TRATEMARK_RETYPE',trademark.registerType)}
		</td>
		<td>申请注册日期</td>
		<td>
			<fmt:formatDate value="${trademark.applyDate}" type="date" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
	<tr>
		<td>国际分类</td>
		<td>
			${dict:getEntryName('IPBOX_TRATEMARK_CLASS',trademark.classify)}
		</td>
		<td>类似群</td>
		<td>${trademark.classifyLike}</td>
	</tr>
	<tr>
		<td>初审公告号</td>
		<td>${trademark.firstPublicNumber}</td>
		<td>初审公告日期</td>
		<td>
			<fmt:formatDate value="${trademark.firstPublicDate}" type="date" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
	<tr>
		<td>注册公告号</td>
		<td>${trademark.registerPublicNumber}</td>
		<td>注册公告日期</td>
		<td>
			<fmt:formatDate value="${trademark.registerPublicDate}" type="date" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
	<tr>
		<td>商标类型</td>
		<td>
			${dict:getEntryName('IPBOX_TRATEMARK_TYPE',trademark.trademarkType)}
		</td>
		<td>商标生效日期</td>
		<td>
			<fmt:formatDate value="${trademark.validDate}" type="date" pattern="yyyy-MM-dd"/>
		</td>
	</tr>
	<tr>
		<td rows="4">商标图形</td>
		<td rows="4" colspan="3">
			<c:choose>
				<c:when  test="${not empty trademark.registeredImage}">
					<c:set value="${ipanthercore:getJSONMap(trademark.registeredImage)}" var="map" />
					<img src="${ctx}${map.downloadUrl}" border="0" style="max-width: 120px;max-height:160px" >
				</c:when>
				<c:otherwise>
					<img src="${ctx}/themes/easyui/themes/tms/images/default.jpg" border="0">
				</c:otherwise>
			</c:choose>
		</td>
	</tr>

	<tr>
		<td >商标证书</td>
		<td colspan="3">
			<c:if  test="${not empty trademark.trademarkFile}">
				<c:set value="${ipanthercore:getJSONMap(trademark.trademarkFile)}" var="map" />
				<div id="fileSpanAttachment">
					<span id="attachmentName">${map.attachmentName}</span>
				</div>
			</c:if>
		</td>
	</tr>

	<tr>
		<td>商标状态</td>
		<td>${trademark.trademarkState}</td>
	</tr>

	<tr>
		<td>专有权开始日期</td>
		<td><fmt:formatDate value="${trademark.beginDate}" type="date" pattern="yyyy-MM-dd"/></td>
		<td>是否是共有</td>
		<td>
			<c:if test="${trademark.isOwner eq 0}">
				否
			</c:if>
			<c:if test="${trademark.isOwner eq 1}">
				是
			</c:if>
		</td>
	</tr>

	<tr>
		<td>代理机构编码</td>
		<td>${trademark.agencyCode}</td>
		<td>商标注册城市</td>
		<td>${trademark.registerCity}</td>
	</tr>

	<tr>
		<td>商标注册人</td>
		<td colspan="3">${trademark.registerRole}</td>
	</tr>

	<tr>
		<td>注册人地址</td>
		<td colspan="3">${trademark.registerAddress}</td>
	</tr>
</table>

<script type="text/javascript">
tableVBg('.${tableClass}');
$(document).ready(function (){
});ad

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
