<%@page import="com.hcis.ipanther.core.utils.CommonConfig"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<form id="siteForm"  action="${ctx}/cms/site/${StringUtils:isNotEmpty(cmsSite.id)?'editSite':'saveSite'}.do" method="post" >
<input  type="hidden" name="cmsSite.parentId" value="${cmsSite.parentId}"/>
<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v" width="90%">
	<tbody>
		<tr>
			<td><label>名称</label></td>
			<td>
			<input id="name" name="cmsSite.name" class="easyui-textbox" 
									data-options="required:true" style="width: 400px" type="text" value="${cmsSite.name}"/>
			&nbsp;
			<a href="${ctx}/cms/site/viweSite.do?cmsSite.id=${cmsSite.id}" target="_blank" class="easyui-linkbutton"><i class="fa fa-search"></i> 站点预览</a>
			</td>
			
		</tr>
		<tr>
			<td><label>源文件目录名</label></td>
			<td>
			<c:if test="${StringUtils:isNotEmpty(cmsSite.id)}">
				<input  class="easyui-textbox" style="width: 400px" type="text" readonly="readonly" value="${cmsSite.sourcePath}"/>
			</c:if>	
			<c:if test="${StringUtils:isEmpty(cmsSite.id)}">
				<input id="sourcePath" name="cmsSite.sourcePath" class="easyui-textbox" 
									data-options="required:true" style="width: 400px" type="text" value="${cmsSite.sourcePath}"/>
			</c:if>				
			</td>
		</tr>
		<tr>
			<td><label>域名</label></td>
			<td>
			<input id="cmsSiteDoMain" name="cmsSite.siteDoMain" class="easyui-textbox" 
			       style="width: 400px" type="text" value="${cmsSite.siteDoMain}"/>
			</td>
		</tr>
		<tr>
			<td><label>排序</label></td>
			<td>
			<input id="orderNum" name="cmsSite.orderNum" class="easyui-numberbox" data-options="min:0,precision:0" 
				style="width: 400px" type="text" value="${cmsSite.orderNum}"/>
			</td>
		</tr>
		<tr>
			<td><label>是否有效</label></td>
			<td>
			<div>
			<input id="isValid" name="cmsSite.isValid" class="easyui-validatebox" 
				data-options="required:true" type="radio"  value="01" ${(cmsSite.isValid eq '01' or empty cmsSite.isValid)?'checked="checked"':''}/>
									<label>否</label>
			&nbsp;&nbsp;
			<input id="isValid" name="cmsSite.isValid" class="easyui-validatebox" 
					data-options="required:true" type="radio" value="02" ${cmsSite.isValid eq '02'?'checked="checked"':''}/>
									<label>是</label>
			</div>
			</td>
		</tr>
		<tr>
			<td><label>外部链接</label></td>
			<td>
			<input id="url" name="cmsSite.url" class="easyui-textbox" 
				 style="width: 400px" type="text" value="${cmsSite.url}"/>
			</td>
		</tr>
		<tr>
			<td><label>LOGO</label></td>
			<td>
				
				<textarea  name="cmsSite.logo" id="attachment"   style="display: none;">${cmsSite.logo}</textarea>
				<c:if test="${StringUtils:isNotEmpty(cmsSite.id)}">
					<c:set var="uuid" value="${cmsSite.id}"/>
				</c:if>
				<c:if test="${StringUtils:isEmpty(cmsSite.id)}">
					<c:set var="uuid" value="${ipanthercore:uuid()}"/>
				</c:if>
				<input name="cmsSite.id"  type="hidden" value="${uuid}"/>
			 	<a  class="easyui-linkbutton"  onclick="uploadLogo('${uuid}','attachmentName','attachment','img')"><span>上传</span></a>
				<input type="hidden" id="attachmentParam_${uuid}" value="${ipanthercommon:getAttachmentParamImage('',uuid,'/WEB-INF/views/common/attachment/callbackImg.jsp','/upload/cms/site/')}">
			 	<c:if test="${StringUtils:isNotEmpty(cmsSite.logo)}"> 
					      <c:set value="${ipanthercore:getJSONMap(cmsSite.logo)}" var="map" />
						      <div id="fileSpanAttachment">
						  		<span id="attachmentName" style="margin-top: 8px;"><img alt="" width="50px;" height="50px;" src="${ctx}${map.downloadUrl}" id="attachmentName"/></span> 
							  	<a href="javascript:void(0)" id="delElemt" class="easyui-linkbutton" onclick="delFile('attachmentName','attachment','delElemt','${ctx}/common/attachment/deleteAttachment.do?attachment.id=')" title="删除文件">删除</a>
							  </div>
							  
				</c:if>
				<c:if test="${StringUtils:isEmpty(cmsSite.logo)}"> 
				      <div id="fileSpanAttachment">
				  		<span id="attachmentName"></span> 
					  </div>
				</c:if>
			</td>
		</tr>
		<tr>
			<td><label>区域</label></td>
			<td>
			 	  <c:choose>
			 	  		<c:when test="${sessionScope.loginUser.deptLevel==1}">
			 	  			 <input value="440000" name="cmsSite.province" type="hidden"/>
							 	省 
							 	<input readonly="readonly" class="easyui-textbox" 
										 style="width: 100px" type="text" value="${ipanthercommon:getRegionsName('440000')}"/>
							 
							 	市 <select name="cmsSite.city" id="city" style="width: 100px">
							 		
							 	  </select>
							 	区 <select name="cmsSite.counties" id="country" style="width: 100px">
							 	
							 	  </select>
			 	  		</c:when> 
			 	  		<c:when test="${sessionScope.loginUser.deptLevel==2}">
			 	  				<input value="440000" name="cmsSite.province" type="hidden"/>
			 	  				<input value="${sessionScope.loginUser.regionsCode}" name="cmsSite.city" type="hidden"/>
							 	省 
							 	<input readonly="readonly" class="easyui-textbox" 
										 style="width: 100px" type="text" value="${ipanthercommon:getRegionsName('440000')}"/>
							 
							 	市
							 	<input readonly="readonly" class="easyui-textbox" 
										 style="width: 100px" type="text" value="${ipanthercommon:getRegionsName(sessionScope.loginUser.regionsCode)}"/>
							 	区 <select name="cmsSite.counties" id="country" style="width: 100px">
							 	  </select>
			 	  		
			 	  		</c:when> 
			 	  		<c:when test="${sessionScope.loginUser.deptLevel==3}">
			 	  				<input value="440000" name="cmsSite.province" type="hidden"/>
			 	  				<c:set value="${ipanthercommon:getRegions(sessionScope.loginUser.regionsCode)}" var="regions"/>
			 	  				<input value="${regions.parentCode}" name="cmsSite.city" type="hidden"/>
			 	  				<input value="${regions.regionsCode}" name="cmsSite.counties" type="hidden"/>
							 	省 
							 	<input readonly="readonly" class="easyui-textbox" 
										 style="width: 100px" type="text" value="${ipanthercommon:getRegionsName('440000')}"/>
							 
							 	市
							 	<input readonly="readonly" class="easyui-textbox" 
										 style="width: 100px" type="text" value="${ipanthercommon:getRegionsName(regions.parentCode)}"/>
							 	区 
							 	<input readonly="readonly" class="easyui-textbox" 
										 style="width: 100px" type="text" value="${regions.regionsName}"/>
			 	  		</c:when> 
			 	  </c:choose>
			</td>
		</tr>
		<tr>
			<td><label>版权</label></td>
			<td>
			<input id="copyright" name="cmsSite.copyright" class="easyui-textbox" 
									 style="width: 400px" type="text" value="${cmsSite.copyright}"/>
			</td>
		</tr>
		<tr>
			<td><label>备案号</label></td>
			<td>
			<input id="recordCode" name="cmsSite.recordCode" class="easyui-textbox" 
						 style="width: 400px" type="text" value="${cmsSite.recordCode}"/>
			</td>
		</tr>
<!-- 		<tr> -->
<!-- 			<td><label>页面模版</label></td> -->
<!-- 			<td> -->
<%-- 			<input type="hidden" value="${cmsSite.indexTemplet}" name="indexTemplet" id="indexTemplet"/> --%>
<!-- 			<input id="indexTempletName" name="indexTempletName" class="easyui-textbox"  -->
<%-- 				 onclick="" readonly="readonly" style="width: 400px" type="text" value="${cmsSite.indexTempletName}"/> --%>
<!-- 			</td> -->
<!-- 		</tr> -->
	</tbody>
</table>
<div style="text-align: center; padding: 5px">
	<button type="button"  class="easyui-linkbutton" onclick="saveSite();">保存</button>
	 <c:if test="${StringUtils:isNotEmpty(cmsSite.id)}">
	 	<button type="button"  class="easyui-linkbutton" onclick="submitDelete();">删除</button>
        <button type="button"  class="easyui-linkbutton" onclick="addSonSite();">添加下级站点</button>
        <button type="button"  class="easyui-linkbutton" onclick="changeParentSite();">改变站点所属站点</button>
        <button type="button"  class="easyui-linkbutton" onclick="setMainSite();">设为主站点</button>
	 </c:if>
</div>
</form>
<script type="text/javascript">
 tableVBg('.alter-table-v');
 function saveSite(){
	 getCurrentTab().find('#siteForm').form('submit', {   
		    success: function(data){    
				var json=jQuery.parseJSON(data);
				if(json){
					var message = json.message;
					var statusCode = json.statusCode;
					if(parseInt(statusCode)==300){
						jQuery.messager.alert("提示信息",message,function(){});
					}else if(parseInt(statusCode)==200){
						jQuery.messager.alert("提示信息",message,function(){});
						easyuiUtils.ajaxClearFormForPanel(getCurrentTabId());
						//closeWindow('siteWindow');
					}
				}else{
					console.error("json is null");
				}
			}    
		}); 
 }
 
 
 function delFile(html,elemt,showElemt,urlValue){
  $.messager.confirm('提示',"确认删除当前文件?",function(result){    
			if (result){  
	  var js= getCurrentTab().find('#siteForm').find("#"+elemt).val();
		 if(js){
			var vo= jQuery.parseJSON(js);
			if(vo){
				urlValue=urlValue+vo.attachmentId;
			 }else{
				 console.error("vo is null");
			 }
		 }else{
			 console.error("js is null");
		 }
	var data= $.ajax({
			url:urlValue,
			//data:$("#"+formId).serialize(),
			type:"post",
			async:false,
			success:function(data){
			}
		}).responseText;
	 if(data){
		 var json=jQuery.parseJSON(data);
		 if(json){
			 var message = json.message;
			 var statusCode = json.statusCode;
			 if(parseInt(statusCode)==300){
				 jQuery.messager.alert("提示信息",message,function(){});
		  	 }else if(parseInt(statusCode)==200){
		  		jQuery.messager.alert("提示信息",message,function(){});
				 getCurrentTab().find('#siteForm').find("#"+html).html("");
				 getCurrentTab().find('#siteForm').find("#"+elemt).html("-1");
				 getCurrentTab().find('#siteForm').find("#"+showElemt).remove();
			 }
		 }else{
			 console.error("data is null");
		 }
	 }else{
		 console.error("json is null");
	 }
	}
 });
}
 
 
 function submitDelete(){
	easyuiUtils.confirmPostUrl('确认删除站点 ${cmsSite.name} 吗？','${ctx}/cms/site/deleteSite.do?cmsSite.id=${cmsSite.id}');
 }
 
 
 function addSonSite(){
	getCurrentTab().find("#editSiteTree").panel('refresh','${ctx}/cms/site/siteAdmin.do?cmsSite.parentId=${cmsSite.id}');
 }
 
  function changeParentSite(){
	 var url="${ctx}/cms/site/changeSite.do?paramMap[cmsSiteId]=${cmsSite.id}&paramMap[siteName]=${cmsSite.name}&paramMap[dialogId]=selectSiteWindow&paramMap[contentId]="+getCurrentTabId()
	 easyuiUtils.openWindow('selectSiteWindow','选择栏目',600,450,url,true); 
	 
 }
 
 function setMainSite(){
		if("${cmsSite.isMainSite}"!="01"){
			var url="${ctx}/cms/site/setMainSite.do?cmsSite.isMainSite=01&cmsSite.id=${cmsSite.id}"
			easyuiUtils.confirmPostUrl('确认设置当前站点为主站点吗？',url);
		}else{
			$.messager.alert('提示','当前站点已经是主站点无需重复设置');
		}
  }
 
 function uploadLogo(uuid,attachmentName,attachment,type){
	var attachParam= getCurrentTab().find('#siteForm').find('#attachmentParam_'+uuid).val();
	 uploadFile(attachParam,attachmentName,attachment,type);
 }
 
	 getCurrentTab().find('#siteForm').find("#attachmentName").bind('DOMNodeInserted', function(e) {
		var elemt= "<a id=\"delElemt\" href=\"javascript:void(0)\"  onclick=\"delFile('attachmentName','attachment','delElemt','${ctx}/common/attachment/deleteAttachment.do?attachment.id=')\" title=\"删除文件\">删除</a>";
		getCurrentTab().find('#siteForm').find("#fileSpanAttachment").append(elemt);
		getCurrentTab().find('#siteForm').find('#delElemt').linkbutton({});  
	 }); 

	 if("${sessionScope.loginUser.deptLevel}"=="1"){
		  	getCurrentTab().find('#siteForm').find('#country').combobox({
		     	valueField:'id',
				    textField:'text',
				 	parentField:'pid',
				    panelWidth:200,
				    onLoadSuccess:function(){
				    	if("${cmsSite.counties}"!=""){
				    		 getCurrentTab().find('#siteForm').find('#country').combobox('setValue',"${ipanthercommon:getRegionsName(cmsSite.counties)}");
				    	}
				    }
		       });
		        getCurrentTab().find('#siteForm').find('#city').combobox({
					url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${ipanthercommon:getGDRegionsCode()}',
				    valueField:'id',
				    textField:'text',
				    parentField:'pid',
				    panelWidth:200,
				    onChange:function(newValue, oldValue) {
				    	if(newValue!=undefined&&""!=newValue) {
				    		var url = '${ctx}/common/regions/getNextRegionsByCode.do?regionsCode='+newValue;
					    	 getCurrentTab().find('#siteForm').find('#country').combobox("reload",url);
					    	 getCurrentTab().find('#siteForm').find('#country').combobox('setValue','');
					    	//getCurrentTab().find('#siteForm').find('#street').combobox('setValue','');
				    	}
				    },
				    onLoadSuccess:function(){
				    	if("${cmsSite.city}"!=""){
				    		 getCurrentTab().find('#siteForm').find('#city').combobox('setValue',"${cmsSite.city}");
				    	}
				    }
				});  
		 
	 }else if("${sessionScope.loginUser.deptLevel}"=="2"){
		 getCurrentTab().find('#siteForm').find('#country').combobox({
		     	valueField:'id',
				    textField:'text',
				 	parentField:'pid',
				    panelWidth:200,
				    onLoadSuccess:function(){
				    	if("${cmsSite.counties}"!=""){
				    		 getCurrentTab().find('#siteForm').find('#country').combobox('setValue',"${ipanthercommon:getRegionsName(cmsSite.counties)}");
				    	}
				    }
		       });
		        getCurrentTab().find('#siteForm').find('#city').combobox({
					url:'${ctx}/common/regions/getNextRegionsByCode.do?regionsCode=${sessionScope.loginUser.regionsCode}',
				    valueField:'id',
				    textField:'text',
				    parentField:'pid',
				    panelWidth:200,
				    onChange:function(newValue, oldValue) {
				    	if(newValue!=undefined&&""!=newValue) {
				    		var url = '${ctx}/common/regions/getNextRegionsByCode.do?regionsCode='+newValue;
					    	 getCurrentTab().find('#siteForm').find('#country').combobox("reload",url);
					    	 getCurrentTab().find('#siteForm').find('#country').combobox('setValue','');
					    	//getCurrentTab().find('#siteForm').find('#street').combobox('setValue','');
				    	}
				    },
				    onLoadSuccess:function(){
				    	if("${cmsSite.city}"!=""){
				    		 getCurrentTab().find('#siteForm').find('#city').combobox('setValue',"${cmsSite.city}");
				    	}
				    }
				});
		 
	 } 
	 
     
</script>
