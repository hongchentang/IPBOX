<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>

<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes.image')}" var="fileTypes"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes')}" var="fileTypes2"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize2"/>

<form id="trademark_saveFrom" name="trademark_saveFrom" action="${ctx}/intellectual/tradeMark/saveTrademark.do" method="post" enctype="multipart/form-data">
	 <input type="hidden" name="id" value="${trademark.id }">
	 <table class="alter-table-v" cellspacing="0" cellpadding="0" border="0">
		<tr>
	            <td ><font color="red">*</font>注册号/申请号</td>
	            <td >
				   <input type="text" class="easyui-textbox"  data-options="required:true" id="registeredNumber" name="registeredNumber"  value="${trademark.registeredNumber}"  />
				</td>
				<td ><font color="red">*</font>商标名称</td>
	            <td >
					<input type="text" class="easyui-textbox" data-options="required:true" id="registeredName" name="registeredName" value="${trademark.registeredName}" />
				</td>
          </tr>
          <tr>
	       		<td ><font color="red">*</font>商标注册类型</td>
				<td>
					<%--<select  id="registerType" name="registerType" class="easyui-combobox" data-options="panelHeight:'auto',required:true" style="width: 171px;">
						a${dict:getEntryOptionsSelected('IPBOX_TRATEMARK_RETYPE',trademark.registerType)}
					</select>--%>
					<input id="registerType" name="registerType" value="${trademark.registerType}" class="easyui-combobox" type="text" title="商标注册类型" data-options="required:true"/>
				</td>
				<td><font color="red">*</font>申请注册日期</td>				
				<td >
					<input id="applyDate" name="applyDate" type="text"  class="Wdate"  value="<fmt:formatDate value="${trademark.applyDate}" type="date" pattern="yyyy-MM-dd"/>" onFocus="WdatePicker()" readonly/> 
				</td>				
 		  </tr> 
 		  <tr>
 		  		<td><font color="red">*</font>国际分类</td>				
				<td >
					<%--<select  id="classify" name="classify" class="easyui-combobox" data-options="panelHeight:'auto',required:true" style="width: 171px;">
						${dict:getEntryOptionsSelected('IPBOX_TRATEMARK_CLASS',trademark.classify)}
					</select>--%>
					<input id="classify" name="classify" value="${trademark.classify}" class="easyui-combobox" type="text" title="国际分类" data-options="required:true"/>
				</td>
 		  		<td ><font color="red">*</font>类似群</td>
 				<td>
 					<input type="text" class="easyui-textbox" data-options="required:true" id="classifyLike" name="classifyLike" value="${trademark.classifyLike}" />
				</td>
 		  </tr>
 		  <tr>
 		  		<td >初审公告号</td>
 				<td>
 					<input type="text" class="easyui-textbox" data-options="required:false" id="firstPublicNumber" name="firstPublicNumber" value="${trademark.firstPublicNumber}" />
				</td>
 				<td>初审公告日期</td>
				<td >
					<input id="firstPublicDate"  name="firstPublicDate" type="text" class="Wdate"  value="<fmt:formatDate value="${trademark.firstPublicDate}" type="date" pattern="yyyy-MM-dd"/>" onFocus="WdatePicker({onpicked:changeDate(1)})" readonly/>
				</td> 		  	
 		  </tr>
 		  <tr>
 		  		<td >注册公告号</td>
 				<td>
 					<input type="text" class="easyui-textbox" data-options="required:false" id="registerPublicNumber" name="registerPublicNumber" value="${trademark.registerPublicNumber}" />
				</td>
 				<td>注册公告日期</td>
				<td >
					<input id="registerPublicDate" name="registerPublicDate" type="text"  class="Wdate"  value="<fmt:formatDate value="${trademark.registerPublicDate}" type="date" pattern="yyyy-MM-dd"/>" onFocus="WdatePicker({onpicked:changeDate(2),oncleared:clearTradeMarkZcAndSxDate})" readonly/>
				</td> 		  	
 		  </tr>
 		  <tr>
 		  		<td><font color="red">*</font>商标类型</td>				
				<td >
					
					<select  id="trademarkType" name="trademarkType" class="easyui-combobox" data-options="panelHeight:'auto',required:true" style="width: 171px;">
						${dict:getEntryOptionsSelected('IPBOX_TRATEMARK_TYPE',trademark.trademarkType)}
					</select>
				</td>
				<td>商标生效日期</td>
				<td >
					<input id="validDate" name="validDate" type="text"  class="Wdate"  value="<fmt:formatDate value="${trademark.validDate}" type="date" pattern="yyyy-MM-dd"/>" onFocus="WdatePicker({onpicked:changeDate(3),oncleared:clearTradeMarkZcAndSxDate})" readonly/>
				</td>
 		  </tr>	  
          <tr>
	          	<td rows="4">商标图形</td>
				<td rows="4" colspan="3" style="vertical-align: bottom;">
					<c:choose>
						<c:when  test="${not empty trademark.registeredImage}"> 
					    	<c:set value="${ipanthercore:getJSONMap(trademark.registeredImage)}" var="map" />
						 	<img src="${ctx}${map.downloadUrl}" border="0" style="max-width: 120px;max-height:160px" >
						</c:when>
						<c:otherwise>
							<img src="${ctx}/themes/easyui/themes/tms/images/default.jpg" border="0">
						</c:otherwise>
					</c:choose>
					<div>
						<input type="file" name="upload" content="<span style='color:red'>尺寸在120px*160px之内<br/>允许上传的文件类型：${fileTypes}<br/>允许上传文件的大小：${fileMaxSize}KB</span>" class="easyui-tooltip" position="left"/>
					</div>										
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
	           		<input type="file" name="uploadFile">
           			<div>
						     允许上传的文件类型：${fileTypes2}<br/>
						    允许上传文件的大小：${fileMaxSize}KB 
					</div>
				</td>
          </tr>
          <tr>
			  	<td>商标状态</td> 
			    <td colspan="3">
	            	<textarea style="width:600px" id="trademarkState" name="trademarkState" >${trademark.trademarkState }</textarea>
			  	</td>
		  </tr>
		  <tr>
				<td>是否是共有</td> 
			    <td >
					<select  id="isOwner" name="isOwner" class="easyui-combobox" data-options="panelHeight:'auto',required:true" style="width: 171px;">
						<option value="0">否</option>
						<option value="1">是</option>
					</select>
				</td>
			  	<td>商标注册城市</td>
			  	<td >
					<input type="text" class="easyui-textbox" data-options="required:true" id="registerCity" name="registerCity" value="${trademark.registerCity}" />
				</td>
		  </tr>
		 <tr>		  
		  		<%--<td>代理机构编码</td>
			    <td >
					<input type="text" class="easyui-textbox" data-options="required:true" id="agencyCode" name="agencyCode" value="${trademark.agencyCode}" />
		  		</td>--%>
				<td>商标注册人</td>
				<td colspan="3">
					<input id="registerRole" name="registerRole" type="text" style="width: 500px;" class="easyui-textbox" data-options="required:true"  value="${trademark.registerRole}" />
				</td>

		  </tr>
		  <%--<tr>
			  	<td>商标注册人</td> 
			    <td colspan="3">
					<input id="registerRole" name="registerRole" type="text"  style="width:500px;" class="easyui-textbox" data-options="required:true"  value="${trademark.registerRole}" />
				</td>				
		  </tr>--%>

		  <tr>
			  	<td>注册人地址</td>
			    <td colspan="3">
					<textarea style="width:600px" id="registerAddress" name="registerAddress"  >${trademark.registerAddress }</textarea>
				</td>
		  </tr>
          <tr style="text-align: center;line-height: 20px">
	            <td colspan="4">
	            	<div style="width: 100%;text-align: center;">
	            		<button type="button" onclick="javascript:submitThisForm();" style="float: center; margin-right: 20px" class="easyui-linkbutton">
	            			保存
	            		</button>
	            		<button type="button" onclick="javascript:closeWindow('addTrademarkWindow');" style="float: center; margin-right: 20px" class="easyui-linkbutton">
	            			关闭
	            		</button>
	            	</div>
	            </td>
          </tr>
        </table>
</form>

<script type="text/javascript">
tableVBg('.alter-table-v');
	$(document).ready(function (){
		//商标注册类型
		$("#registerType").combobox({
			url: '${ctx}/common/datadict/getByTypeCode.do?typeCode=IPBOX_TRATEMARK_RETYPE',
			emptyText: '',
			valueField: 'dictValue',
			textField: 'dictName',
			slide: true,
			valueFieldID: 'registerType',
			limitToList: true,
			panelHeight: 'auto'
		});

		//国际分类
		$("#classify").combobox({
			url: '${ctx}/common/datadict/getByTypeCode.do?typeCode=IPBOX_TRATEMARK_CLASS',
			emptyText: '',
			valueField: 'dictValue',
			textField: 'dictName',
			slide: true,
			valueFieldID: 'classify',
			limitToList: true,
			panelHeight: '300'
		});
});


function submitThisForm(){
	$.messager.confirm('确认','确认保存吗？',function(result){    
		if (result){  
			$('#trademark_saveFrom').form('submit',{
				onSubmit: function(){
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	
						$.messager.alert('提示','请填写必填项！');
					}
					return isValid;	
				},
				success: function(data){
					var json = eval('(' + data + ')');  
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							$.messager.alert('提示',message);
						}else if(parseInt(statusCode)==200){
							closeWindow('addTrademarkWindow');
							var tabId = $("#layout_center_tabs").tabs('getSelected').attr('id');
							$('#' + tabId).panel('refresh');
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

function changeDate(idx) {
	console.log('change' + idx);

	var firstPublicDate = $('#firstPublicDate').val();
	var registerPublicDate = $('#registerPublicDate').val();
	var validDate = $('#validDate').val();

	if(idx == 1 && firstPublicDate != '' && registerPublicDate != ''){
		//如果填入初审公告日而且注册公告日不为空的话
		checkDate(firstPublicDate, registerPublicDate, idx);
	}else if(idx == 2 && registerPublicDate != ''){

		//两个日期相同
		$('#validDate').val(registerPublicDate);
		if(firstPublicDate != ''){
			checkDate(firstPublicDate, registerPublicDate, idx);
		}
	}else if(idx == 3 && validDate != ''){

		//两个日期相同
		$('#registerPublicDate').val(validDate);
		if(firstPublicDate != ''){
			checkDate(firstPublicDate, validDate, idx);
		}
	}

}

function checkDate(date1, date2, idx) {
	var fpd = new Date(date1);
	var rpd = new Date(date2);

	debugger
	if((rpd.getFullYear() < fpd.getFullYear()) || ((rpd.getMonth() - fpd.getMonth()) < 3)){
		if(idx == 1){
			$('#firstPublicDate').val('');
		}else {
			clearTradeMarkZcAndSxDate()
		}

		$.messager.alert('错误','初审公告日不能与注册公告日小于3个月');
	}
}
function clearTradeMarkZcAndSxDate() {

 	$('#registerPublicDate').val('');
	$('#validDate').val('');
}

</script>
