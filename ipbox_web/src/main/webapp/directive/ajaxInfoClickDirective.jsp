<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="easyui-panel" id="channelAddPanel" data-options="region:'center',title:''">
          	<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
				<tbody>
                <tr>
						<td width="20%">
				          	<label>标签名称</label>  
				        </td>
				        <td width="50%">  
				        	<b>ajaxInfoClick</b> 
                        </td>
                    </tr>
                	<tr>
						<td>
				          	<label>参数</label>  
				        </td>
				        <td>    
				        //信息ID
				        String infoId    
						//点击量显示span的属性
						String spanAttr 
						//是否加载引用的js
						String loadjs    
                        </td>
                    </tr>
                    
                    <tr>
						<td>
				          	<label>页面标识</label>  
				        </td>
				        <td>    
                       		<input name="pageMark" type="text" class="easyui-textbox" data-options="required:true" value="${cmsChannel.pageMark}"/>
                        </td>
                    </tr>
                    <tr>
						<td>
				          	<label>是否有效</label>  
				        </td>
				        <td>    
                       		<input name="state" type="radio" class="easyui-validatebox" ${cmsChannel.state eq '01' or  empty cmsChannel.state ?'checked="checked"':''}  value="01"/>是
                        	<input name="state" type="radio" class="easyui-validatebox" ${cmsChannel.state eq '00'?'checked="checked"':''} value="00"/>否
                        </td>
                    </tr>
                    <tr>
						<td>
				          	<label>是否导航</label>  
				        </td>
				        <td>    
                       		<input name="navigation" type="radio" class="easyui-validatebox" ${cmsChannel.navigation eq '01' or empty cmsChannel.navigation?'checked="checked"':''} value="01"/>是
                        	<input name="navigation" type="radio" class="easyui-validatebox" ${cmsChannel.navigation eq '00'?'checked="checked"':''} value="00"/>否
                        </td>
                    </tr>
                    
                     <tr>
						<td>
				          	<label>序号</label>  
				        </td>
				        <td>   
				       	 	<input name="orderNum" type="text" class="easyui-textbox"  value="${cmsChannel.orderNum}"/> 
                        </td>
                    </tr>
                    
                    <tr>
						<td>
				          	<label>链接地址</label>  
				        </td>
				        <td>   
				       	 	<input name="url" type="text" class="easyui-textbox" value="${cmsChannel.url}"/> 
                        </td>
                    </tr>
                    <tr>
						<td>
				          	<label>栏目模版</label>  
				        </td>
				        <td>   
				        	<input name="templetId" type="hidden" id="cmsChannel_templetId"   value="${cmsChannel.templetId}"/> 
				       	 	<input name="templetName" type="text" id="cmsChannel_templetName" onclick="selectFileUrl('cmsChannel_templetId','cmsChannel_templetName')"  readonly="readonly" value="${cmsChannel.templetId}"/> 
                        </td>
                    </tr>
                    <tr>
						<td>
				          	<label>信息模版</label>  
				        </td>
				        <td>   
				        	<input name="contentTempletId"   type="hidden" id="cmsChannel_contentTempletId" value="${cmsChannel.contentTempletId}"/> 
				       	 	<input name="contentTempletName" type="text"   id="cmsChannel_contentTempletName" onclick="selectFileUrl('cmsChannel_contentTempletId','cmsChannel_contentTempletName')" value="${cmsChannel.contentTempletId}"/> 
                        </td>
                    </tr>
                    
                    <tr>
						<td>
				          	<label>栏目图片</label>  
				        </td>
				        <td>   
				       	 	<input name="imgUpload" type="file"/> 
				       	 	<textarea rows="" cols="" name="img" style="display: none;" id="attachment">${cmsChannel.img}</textarea>
	                        <c:if test="${not empty cmsChannel.img}"> 
							      <c:set value="${ipanthercore:getJSONMap(cmsChannel.img)}" var="map" />
								      <div id="fileSpanAttachment">
								      	<span id="attachmentName">
								      		<img alt="" src="${ctx}${map.downloadUrl}"/>
								      	</span>
								      	<a href="javascript:void(0)" class="easyui-linkbutton" title="删除栏目图片" onclick="fileDelete('attachmentName','attachment','delElemt','${ctx}/common/attachment/deleteAttachment.do?attachment.id=${map.attachmentId}')">删除</a>
<%-- 									  	<span id="attachmentName"><a href="${ctx}${map.downloadUrl}" target="download">${map.attachmentName}(点击下载)</a></span>  --%>
									  </div>
							 </c:if>
                        
                        </td>
                    </tr>
                    
                    <tr>
						<td>
				          	<label>生成静态化最大页数</label>  
				        </td>
				        <td>   
				       	 	<input name="maxPage" type="text" class="easyui-textbox" value="${cmsChannel.maxPage}"/> 
                        </td>
                    </tr>
                    
                    <%-- <tr>
						<td>
				          	<label>当次栏目信息变更后</label>  
				        </td>
				        <td>   
				       	 	<input name="htmlChannelId"    type="checkbox"  ${cmsChannel.htmlChannelId eq '01'?'checked="checked"':''}    value="${cmsChannel.htmlChannelId}"/> 静态化所属栏目页面 <br/>
                       		<input name="htmlChannelOldId" type="checkbox"  ${cmsChannel.htmlChannelOldId eq '01'?'checked="checked"':''} value="${cmsChannel.htmlChannelOldId}"/> 静态化原所属栏目页面<br/>
                       		<input name="htmlParChannelId" type="checkbox"  ${cmsChannel.htmlParChannelId eq '01'?'checked="checked"':''} value="${cmsChannel.htmlParChannelId}"/> 静态化所属栏目的所有父栏目页面<br/>
                       		<input name="htmlSiteId"       type="checkbox"  ${cmsChannel.htmlSiteId eq '01'?'checked="checked"':''}       value="${cmsChannel.htmlSiteId}"/> 静态化首页<br/>
                        </td>
                    </tr> --%>
                    <tr>
						<td>
				          	<label>栏目描述</label> 
				        </td>
				   		<td>
				          	<textarea rows="3" cols="50" name="description">${cmsChannel.description}</textarea>
				        </td>
                    </tr>
		     	</tbody>
		    </table>
</div>