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
		<h3>问卷调查</h3>
	</div>
</div>
<div style="margin-left: 10px;margin-top: 10px;" class="survey_div">
	<div>
		<div>
        	<div style="padding:5px;background-color:#ffffee;border:2px solid #E7ECEE;margin: 5px;border-radius:10;border-radius:5px">
				<span style="color:#FF8400;font-weight: bolder;font-size: 18px">调查问卷描述</span>
				<p style="font-size: 15px;margin-top:10px;margin-bottom: 10px;">${survey.description}</p>
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
							<hr style="height:1px;border:none;border-top:1px dashed #E7ECEE;margin: 10px 0px;"/>
							<div class="questionContent" questionId="${question.id }">
								<input type="hidden" name="questionIndex" value="${index.count}">
								<c:choose>
									<c:when test="${question.quesType eq textEntry }">
										<div style="margin-left:15px">
											<textarea name="surveySubmissions[${index.index }].response" style="width:80%;border-radius:5px;font-size: 15px;"></textarea>
										</div>
									</c:when>
									<c:otherwise>
										<ol class=".topic-list">
											<c:forEach items="${choiceInteractionMap[question.id].choices }" var="choice">
												<li type="A">
													<c:choose>
														<c:when test="${question.quesType eq multipleChoice }">
															<span class="choiceSpan more-choice" value="${choice.id }"></span>
															<input type="checkbox" name="surveySubmissions[${index.index }].response" value="${choice.id }" style="cursor:pointer;" />
														</c:when>
														<c:otherwise>
															<span class="choiceSpan alone-choice" value="${choice.id }"></span>
															<input type="radio" name="surveySubmissions[${index.index }].response" value="${choice.id }" style="cursor:pointer;" />
														</c:otherwise>
													</c:choose> 
													<em onclick="$('input[name=\'surveySubmissions[${index.index }].response\'][value=\'${choice.id }\']').click()" style="cursor:pointer;">${choice.content }</em>
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
		
			<div >
				<div >
					<div style="text-align: center;">
						<c:choose>
							<c:when test="${surveyUser.completionStatus ne 'complete'}">
								<button type="button" onclick="submitSaveSurveyUserForm()" class="easyui-linkbutton" ><i class="fa fa-save"></i>提交</button>
							</c:when>
							<c:otherwise>
								<button type="button" onclick="javascript:window.location.href='${ctx}/space/survey/surveyStat.do?id=${survey.id}'" class="easyui-linkbutton" ><i class="fa fa-search"></i>查看问卷结果</button>
							</c:otherwise>
						</c:choose>
					</div>
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
	
	function submitSaveSurveyUserForm(){
		var isReturn = false;
		$('#userSurveyForm').find('.questionContent').each(function(){
			var questionId = $(this).attr('questionId');
			var index = $(this).find(":hidden[name='questionIndex']").val();
			if($(this).find('textarea').length > 0){
				if($(this).find('textarea').val().trim().length == 0){
					isReturn = true;
					$.messager.alert('提示','题目【'+index+'】还没有完成','error');
					return false;
				}else{
					var minWords = $('#minWords_'+questionId).html();
					var maxWords = $('#maxWords_'+questionId).html();
					if(minWords != null && minWords != 0){
						if(getByteLength($(this).find('textarea').val().trim()) < minWords ){
							isReturn = true;
							$.messager.alert('提示','简答题【'+index+'】的字节数不得少于'+minWords+'','error');
							return false;
						}
					}
					if(maxWords != null && maxWords != 0){
						if(getByteLength($(this).find('textarea').val().trim()) > maxWords ){
							isReturn = true;
							$.messager.alert('提示','简答题【'+index+'】的字节数不得多于'+maxWords+'','error');
							return false;
						}
					}
				}
			}else{
				if($(this).find('input:checked').length == 0){
					isReturn = true;
					$.messager.alert('提示','题目【'+index+'】还没有完成','error');
					return false;
				}
				if($(this).find(':checkbox:checked').length > 0){
					var maxChoices = $('#maxChoicesLimit_'+questionId).html();
					if($(this).find(':checkbox:checked').length > maxChoices){
						isReturn = true;
						$.messager.alert('提示','多选题【'+index+'】的选择项数不能大于'+maxChoices,'error');
						return false;
					}
				}
			}
		});
		if(isReturn){
			return false;
		}
		$.messager.confirm('提示', '确定提交？', function(r){
			if(r) {
				$.ajax({
					url:$('#userSurveyForm').attr('action'),
					type:'post',
					data:$('#userSurveyForm').serialize(),
					success:function(data){
						if(data.responseCode == '00'){
							$.messager.alert('提示','提交成功','info',function() {
								window.location.href='${ctx}/space/survey/listSurvey.do';
							});
						}else{
							$.messager.alert('提示','提交失败','error');
						}
					}
				});
			}
		});
	}
	
	<%--初始化数据--%>
	var questionId;
	var response;
	<c:forEach items="${surveyUser.surveySubmissions }" var="ss">
		questionId = "${ss.id}";
		response = $('#response_${ss.id}').val();
		if($('#questionDiv_'+questionId).find('textarea').length != 0){
			$('#questionDiv_'+questionId).find('textarea').val(response);
		}else{ 
			var answer = response.split(",");
			$('#questionDiv_'+questionId).find('.choiceSpan').each(function(){
				for(var i=0; i < answer.length; i++){
					var choiceId = $(this).attr('value');
					if(answer[i] == choiceId){
						$(this).parent().find("input[value='"+choiceId+"']").attr('checked',true);
					}
				}				
			});
		}
	</c:forEach>
</script>
