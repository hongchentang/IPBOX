<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<c:set var="downloadUrl" value="${ipanthercore:getProperty('attachment.download.url')}"/>
<%@ page import="java.util.*"%>
<style type="text/css">
table {
	border: 1px  solid #CCCCCC;
	font-size: 13px;
}
table thead th {
	line-height:50px;
	font-weight: bolder;
	font-size: 14px;
	text-align: center
}
table tbody tr {
	font-size: 14px;
	
}
table tbody .project {
	text-align: center
}
table tbody .number {
	text-align: center;
	font-weight: bolder;
}
table tbody .score {
	text-align: center;
	line-height:30px;
}
 

table tbody .score2 {
	text-align: center;
	font-size: 18px;
	line-height:30px;
}
table tbody .score2  input{
	cursor: pointer;
}
table tbody .score2 div{
	margin: 0px 5px;
	display: inline;
	cursor: pointer;
}
table tbody .questionTitle {
	margin-top: 5px;
}

table tbody textarea {
	width: 95%;
	margin: 5px 1px;
	border: 1px  solid #CCCCCC;
	font-size: 15px;
}
table th{
	border: 1px  solid #CCCCCC;
}
table td{
	border: 1px  solid #CCCCCC;
}
.evaluate {
	
}
.evaluate .title {
	font-size: 20px;
	font-weight: bolder;
	text-align: center
}
.evaluate .title2 {
	font-size: 15px;
	font-weight: bolder;
	text-align: center;
	margin: 10px;
}
.evaluate .description {
	font-size: 15px;
	background-color:#ffffee;
	padding:5px;
	border:1px solid #E7ECEE;
	margin: 5px 0px;
	border-radius:5px
}
.evaluate .save {
	text-align: center;
	margin:5px;
}
.evaluate .thanks {
	font-size: 15px;
	margin-top: 5px;
}
</style>
<div class="mod">
	<div class="tit clearfix">
		<h3>学习档案-评价查看</h3>
	</div>
</div>
<div class="evaluate">
	<div class="title">
		教学评价表
	</div>
	<div class="description">
		&nbsp;&nbsp;&nbsp;&nbsp;
		为了切实了解您对本次培训班的评价，获悉您的宝贵建议和意见，以期提高课程设置过程的科学性和针对性，
		并进一步提高教学水平，请您根据个人感受如实填写本表。每项评分的满分是10分，请给出您的分数。
	</div>
	
	<table style="width:99%;border-collapse:collapse" cellspacing="0" cellpadding="0">
	
		<thead>
			<tr align="center" >
				<th width="20%" >评价项目</th>
	 			<th width="40%" colspan="5" class="project">教学内容</th>
	 			<th width="40%" colspan="5" class="project">教师表现</th>
			</tr>
		 </thead>
		 <tbody>
		 	<tr>
			   <th class="project">评分标准</th>
			   <td>教学内容与实际工作需要联系紧密</td>
			   <td>教学内容准确，重点突出，条理清晰</td>
			   <td>案例生动，使用与企业领域相关或通俗易懂的案例，使课程深入浅出</td>
			   <td>理论与案例结合恰到好处，学员印象深刻</td>
			   <td>培训课件清楚，有利于理解课程内容</td>
		 	   <td>教师对所讲授内容非常熟悉，思路清晰，授课过程顺畅</td>
		 	   <td>课堂氛围活跃，与学员有互动</td>
		 	   <td>时间和进度把握得当</td>
		 	   <td>能较好的利用目光、手势、走动等身体语言</td>
		 	   <td>声音清楚，语速、音调变化适当，有吸引力</td>
		 	</tr>
		 	<c:forEach items="${listUser}" var="listUser" varStatus="i">
		 	<tr>
					<td class="score"> ${listUser.realName}</td>
					<td class="score">
						${teachingEvalua[listUser.teacherId].inseparable}
						<%-- <select name="teachingEvalua[${i.index}].inseparable" class="easyui-validatebox" data-options="panelHeight:'auto',required:true">
							<option value="">请选择</option>
							<c:forEach begin="1" end="10" var="score" step="1" ><option>${score}</option></c:forEach>
						</select> --%>
					</td>
					<td class="score">
						${teachingEvalua[listUser.teacherId].accuracy}
						<%-- <select name="teachingEvalua[${i.index}].accuracy" class="easyui-validatebox" data-options="panelHeight:'auto',required:true">
							<option value="">请选择</option>
							<c:forEach begin="1" end="10" var="score" step="1" ><option>${score}</option></c:forEach>
						</select> --%>
					</td>
					<td class="score">
						${teachingEvalua[listUser.teacherId].lively}
						<%-- <select name="teachingEvalua[${i.index}].lively" class="easyui-validatebox" data-options="panelHeight:'auto',required:true">
							<option value="">请选择</option>
							<c:forEach begin="1" end="10" var="score" step="1" ><option>${score}</option></c:forEach>
						</select> --%>
					</td>
					<td class="score">
						${teachingEvalua[listUser.teacherId].deepGoing}
					<%-- 	<select name="teachingEvalua[${i.index}].deepGoing" class="easyui-validatebox" data-options="panelHeight:'auto',required:true">
							<option value="">请选择</option>
							<c:forEach begin="1" end="10" var="score" step="1" ><option>${score}</option></c:forEach>
						</select> --%>
					</td>
					<td class="score">
						${teachingEvalua[listUser.teacherId].clear}
						<%-- <select name="teachingEvalua[${i.index}].clear" class="easyui-validatebox" data-options="panelHeight:'auto',required:true">
							<option value="">请选择</option>
							<c:forEach begin="1" end="10" var="score" step="1" ><option>${score}</option></c:forEach>
						</select> --%>
					</td>
					<td class="score">
						${teachingEvalua[listUser.teacherId].insideDopester}
						<%-- <select name="teachingEvalua[${i.index}].insideDopester" class="easyui-validatebox" data-options="panelHeight:'auto',required:true">
							<option value="">请选择</option>
							<c:forEach begin="1" end="10" var="score" step="1" ><option>${score}</option></c:forEach>
						</select> --%>
					</td>
					<td class="score">
						${teachingEvalua[listUser.teacherId].properly}
						<%-- <select name="teachingEvalua[${i.index}].properly" class="easyui-validatebox" data-options="panelHeight:'auto',required:true">
							<option value="">请选择</option>
							<c:forEach begin="1" end="10" var="score" step="1" ><option>${score}</option></c:forEach>
						</select> --%>
					</td>
					<td class="score">
						${teachingEvalua[listUser.teacherId].bodyLanguage}
						<%-- <select name="teachingEvalua[${i.index}].bodyLanguage" class="easyui-validatebox" data-options="panelHeight:'auto',required:true">
							<option value="">请选择</option>
							<c:forEach begin="1" end="10" var="score" step="1" ><option>${score}</option></c:forEach>
						</select> --%>
					</td>
					<td class="score">
						${teachingEvalua[listUser.teacherId].appropriate}
						<%-- <select name="teachingEvalua[${i.index}].appropriate" class="easyui-validatebox" data-options="panelHeight:'auto',required:true">
							<option value="">请选择</option>
							<c:forEach begin="1" end="10" var="score" step="1" ><option>${score}</option></c:forEach>
						</select> --%>
					</td>
					<td class="score">
						${teachingEvalua[listUser.teacherId].active}
						<%-- <select  name="teachingEvalua[${i.index}].active" class="easyui-validatebox" data-options="panelHeight:'auto',required:true">
							<option value="">请选择</option>
							<c:forEach begin="1" end="10" var="score" step="1" ><option>${score}</option></c:forEach>
						</select> --%>
					</td>
		 	</tr>
		    </c:forEach>
		 </tbody>
	</table>
	
	<br/>
	<div class="mod" style="margin-top: 20px;">
		<div class="tit clearfix">
		</div>
	</div>
 
	<div class="title">
		培训班综合评价
	</div>
	<table style="width:99%;border-collapse:collapse" cellspacing="0" cellpadding="0">
		<thead>
			<tr align="center" >
				<th width="10%" >序号</th>
				<th width="40%" >评价内容</th>
				<th >评价分数</th>
			</tr>
		</thead>
		<tbody>
		<tr>
			<td class="number">1</td>
			<td>课程设置系统、完整</td>
			<td class="score2">
				<div>${courseEffectEvalua.complete}</div>
				<%-- <input value="${sessionScope.loginUser.id}" type="hidden" name="courseEffectEvalua.userId"/>
				<input value="${train.id}" type="hidden" name="courseEffectEvalua.trainId"/>
				<input type="radio" value="2" name="courseEffectEvalua.complete" class="easyui-validatebox" data-options="required:true"/>2</div>
					<c:forEach begin="4" end="10" var="score" step="1" >
						<div><input type="radio"  name="courseEffectEvalua.complete" value="${score}" class="easyui-validatebox" data-options="required:true"/>${score}</div>
					</c:forEach> --%>
			</td>
		</tr>
		<tr>
			<td class="number">2</td>
			<td>课时安排及信息量恰当</td>
			<td class="score2">
				<div>${courseEffectEvalua.appropriate}</div>
				<%-- <input type="radio" value="2" name="courseEffectEvalua.appropriate" class="easyui-validatebox" data-options="required:true"/>2</div>
				<c:forEach begin="4" end="10" var="score" step="1" >
					<div><input type="radio" value="${score}" name="courseEffectEvalua.appropriate" class="easyui-validatebox" data-options="required:true"/>${score}</div>
				</c:forEach> --%>
			</td>
		</tr>
		<tr>
			<td class="number">3</td>
			<td>教学组织形式合适</td>
			<td class="score2">
				<div>${courseEffectEvalua.suitableForm}</div>
				<%-- <input type="radio" value="2" name="courseEffectEvalua.suitableForm" class="easyui-validatebox" data-options="required:true"/>2</div>
				<c:forEach begin="4" end="10" var="score" step="1" >
					<div><input type="radio" value="${score}" name="courseEffectEvalua.suitableForm" class="easyui-validatebox" data-options="required:true"/>${score}</div>
				</c:forEach> --%>
			</td>
		</tr>
		<tr>
			<td class="number">4</td>
			<td>组织管理工作到位</td>
			<td class="score2">
				<div>${courseEffectEvalua.dovewhell}</div>
			<%-- 	<input type="radio" value="2" name="courseEffectEvalua.dovewhell" class="easyui-validatebox" data-options="required:true"/>2</div>
				<c:forEach begin="4" end="10" var="score" step="1" >
					<div><input type="radio" value="${score}" name="courseEffectEvalua.dovewhell" class="easyui-validatebox" data-options="required:true"/>${score}</div>
				</c:forEach> --%>
			</td>
		</tr>
		<tr>
			<td class="number">5</td>
			<td>后勤服务保障工作满意</td>
			<td class="score2">
				<div>${courseEffectEvalua.logistics}
				</div>
				<%-- <input type="radio" value="2" name="courseEffectEvalua.logistics" class="easyui-validatebox" data-options="required:true"/>2</div>
				<c:forEach begin="4" end="10" var="score" step="1" >
					<div><input type="radio" value="${score}" name="courseEffectEvalua.logistics" class="easyui-validatebox" data-options="required:true"/>${score}</div>
				</c:forEach> --%>
			</td>
		</tr>
		<tr>
			<td class="number">6</td>
			<td>对本次培训班的总体评价</td>
			<td class="score2">
				<div>
				${courseEffectEvalua.overall}</div>
				<%-- <input type="radio" value="2"   name="courseEffectEvalua.overall" class="easyui-validatebox" data-options="required:true"/>2</div>
				<c:forEach begin="4" end="10" var="score" step="1" >
					<div><input type="radio" value="${score}" name="courseEffectEvalua.overall" class="easyui-validatebox" data-options="required:true"/>${score}</div>
				</c:forEach> --%>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<div class="questionTitle">1、您认为本次的培训课程中哪些课程最贴近您的需求，最能对您的工作有帮助？</div>
				<div>
					<textarea rows="2" name="courseEffectEvalua.help" readonly="readonly">${courseEffectEvalua.help}</textarea>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<div class="questionTitle">2、下次培训，您还希望开设什么样的课程，请列举？</div>
				<div>
					<textarea rows="2" name="courseEffectEvalua.enumerate"  readonly="readonly">${courseEffectEvalua.enumerate}</textarea>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<div class="questionTitle">3、您在本期培训班的主要收获是什么？</div>
				<div>
					<textarea rows="2" name="courseEffectEvalua.results" readonly="readonly">${courseEffectEvalua.results}</textarea>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<div class="questionTitle">4、您对本次培训班还有哪些建议和意见？</div>
				<div>
					<textarea rows="2"  name="courseEffectEvalua.suggestion" readonly="readonly">${courseEffectEvalua.suggestion}</textarea>
				</div>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<div class="questionTitle">5、培训总结</div>
				<div>
					<c:if test="${not empty courseEffectEvalua.trainingSummary}">
						      <c:set value="${ipanthercore:getJSONMap(courseEffectEvalua.trainingSummary)}" var="trainingSummary" />
						      <div id="fileSpan">
							  	&nbsp;&nbsp;&nbsp;
							  	<span id="attachmentName">
							  		<a href="${downloadUrl}?attachment.id=${trainingSummary.attachmentId}"  target="_blank">${trainingSummary.attachmentName}(点击下载)</a>
							  	</span> 
							  </div>
						    </c:if>
				</div>
			</td>
		</tr>
	
		</tbody>
	</table>
	<!-- <div class="thanks">
		感谢您的配合！
	</div> -->
	<div class="save">
	</div>
</div>
 
 