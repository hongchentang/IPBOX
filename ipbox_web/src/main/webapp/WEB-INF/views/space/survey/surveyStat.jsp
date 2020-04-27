<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<%@page import="com.hcis.survey.entity.SurveyQuestion"%>
<c:set var="singleChoice" value="<%=SurveyQuestion.QuesType.SINGLECHOICE.toString() %>" />
<c:set var="multipleChoice" value="<%=SurveyQuestion.QuesType.MULTIPLECHOICE.toString() %>" />
<c:set var="trueOrFalse" value="<%=SurveyQuestion.QuesType.TRUEORFALSE.toString() %>" />
<c:set var="textEntry" value="<%=SurveyQuestion.QuesType.TEXTENTRY.toString() %>" />
<style>
.mod .survey_div {
	padding:10px;
}
.survey_div ol li {
	list-style:upper-alpha;
	margin-left:40px;
	padding:10px;
}
</style>
<form id="userSurveyForm" action="${pageContext.request.contextPath }/survey/saveUserSurvey.do">
<input type="hidden" name="id" value="${surveyUser.id }" />
<input type="hidden" name="survey.id" value="${survey.id }" />

<div class="mod">
	<div class="tit clearfix">
		<h3>问卷调查结果</h3>
	</div>
</div>
<div style="margin-left: 10px;margin-top: 10px;" class="survey_div">
	<div>
		<div>
        	<div style="padding:5px;background-color:#ffffee;border:2px solid #E7ECEE;margin: 5px;border-radius:10;border-radius:5px">
				<span style="color: #FF8400;font-weight: bolder;font-size: 18px">调查问卷描述</span>
				<p style="font-size: 15px;margin-top:10px;margin-bottom: 10px;">${survey.description}</p>
				<span style="color: #FF8400;font-weight: bolder;font-size: 18px;">参与人数:${surveyStat.joinCount}</span>
			</div>
		</div>
		<hr style="height:1px;border:none;border-top:1px dashed #ffbb55;margin:20px 0px;"/>
		<div >
			<div style="font-size: 15px">
				<c:forEach items="${survey.questions }" var="question" varStatus="index">
					<div id="questionDiv_${question.id }" style="padding:5px;border:1px solid #E7ECEE;margin: 5px;border-radius:5px"> 
						<input type="hidden" name="surveySubmissions[${index.index }].id" value="${question.id }">
						<div>
							<div >
								<input type="hidden" name="quesBody_${question.id }" value="${question.quesBody }">
								<span style="display: inline;">${index.count }、${question.quesBody }</span>
								<c:if test="${question.quesType eq textEntry and not empty textEntryInteractionMap[question.id]}">
									<span id="wordsLimit_${question.id  }" style="display: inline;">(
									<c:if test="${textEntryInteractionMap[question.id].minWords != 0 }">
										最少字节数:<span id="minWords_${question.id }">${textEntryInteractionMap[question.id].minWords }</span>
									</c:if>
									<c:if test="${textEntryInteractionMap[question.id].maxWords != 0 }">
										最多字节数:<span id="maxWords_${question.id }">${textEntryInteractionMap[question.id].maxWords }</span>
									</c:if>
									<c:if test="${textEntryInteractionMap[question.id].maxWords == 0 and textEntryInteractionMap[question.id].minWords == 0 }">
										<script>$('#wordsLimit_${question.id}').remove()</script>
									</c:if>
									)</span>
								</c:if>
								<c:if test="${question.quesType eq multipleChoice and choiceInteractionMap[question.id].maxChoices != 0}">
									<span style="display: inline;">(最多选择<span id="maxChoicesLimit_${question.id  }">${choiceInteractionMap[question.id].maxChoices }</span>项)</span>
								</c:if>
							</div>
							<hr style="height:1px;border:none;border-top:1px dashed #E7ECEE;margin: 10px 0px"/>
							<div class="questionContent" questionId="${question.id }" >
								<c:choose>
									<c:when test="${question.quesType eq textEntry }">
										<div style="margin-left:15px">
											<textarea style="display: none">标记这里是问答区域</textarea>
											<button type="button" id="viewTextEntryStatButton" onclick="listTextEntry('${index.count }','${question.id}')" class="easyui-linkbutton" ><i class="fa fa-search"></i>查看调查结果</button>
										</div>
									</c:when>
									<c:otherwise>
										<ol class=".topic-list">
											<c:forEach items="${choiceInteractionMap[question.id].choices }" var="choice">
												<li type="A">
													<c:choose>
														<c:when test="${question.quesType eq multipleChoice }">
															<span class="choiceSpan more-choice" value="${choice.id }"></span>
															<input type="checkbox" name="surveySubmissions[${index.index }].response" value="${choice.id }" style="display: none" />
														</c:when>
														<c:otherwise>
															<span class="choiceSpan alone-choice" value="${choice.id }"></span>
															<input type="radio" name="surveySubmissions[${index.index }].response" value="${choice.id }" style="display: none" />
														</c:otherwise>
													</c:choose> 
													<em>${choice.content }</em>
													<em><div id="already_check_${choice.id }" style="color: red;font-size: 20px;display: inline;font-weight: bolder;display: none;cursor:pointer;" title="已选择">√</div></em>
													<br/>
													<em>
														<div id="progressbar_${choice.id }" data-options="value:0" style="width:95%;border-radius:5px" />
													</em>
												</li>
											</c:forEach>
										</ol>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
<%--临时保存答案，避免因为换行符引起js错误 --%>
<div id="response_temp_id" style="display: none">
	<c:forEach items="${surveyUser.surveySubmissions }" var="ss">
		<textarea id="response_${ss.id}">${ss.response}</textarea>
	</c:forEach>
</div>
</form>
<script>
$(document).ready(function(e) {
	//初始化进度条
	$("div[id^='progressbar_']").each(function() {
		$(this).progressbar();
	});
	//设置进度条的数值
	<c:set var="joinCount" value="${surveyStat.joinCount}"/>
	<c:forEach items="${surveyStat.stat}" var="choice">
		var value = '<fmt:formatNumber value='${choice.value/joinCount*100}' maxFractionDigits="2"/>';
		$("#progressbar_${choice.key}").progressbar({value:value,text:value+'%(${choice.value})'});
	</c:forEach>
	//设置当前用户的答案
	
	var questionId;
	var response;
	<c:forEach items="${surveyUser.surveySubmissions }" var="ss">
		questionId = "${ss.id}";
		response = $('#response_${ss.id}').val();
		if($('#questionDiv_'+questionId).find('textarea').length == 0){
			var answer = response.split(",");
			for(var i=0; i < answer.length; i++){
				$('#questionDiv_'+questionId).find("div[id='already_check_"+answer[i]+"']").css("display","inline");
			}
		}
	</c:forEach>
});
//查看简答题的统计信息
function listTextEntry(index,questionId) {
	var title = index+"、"+$(":hidden[name='quesBody_"+questionId+"']").val();
	easyuiUtils.openWindow('showTextEntryDialog',title,800,500,'${ctx}/survey/listTextEntry.do?paramMap[questionId]='+questionId,true);
}
</script>
