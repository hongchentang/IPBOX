<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<div class="easyui-panel" id="tmsAddPanel" data-options="region:'center',title:''">
       <form id="tmsAddFrom">
          	<table border="0" cellspacing="1" cellpadding="1"  class="alter-table-v">
				<tbody>
					<tr>
						<td width="15%">
				          	<label>商标名称</label>  
				        </td>
				        <td width="35%"> 
                       		${tradeMark.name}						
			             </td>
			             <td width="15%">
			             	<label>商标注册号</label> 
			             </td>
			             <td width="35%">
			             	${tradeMark.registeNumber}
			             </td>
                    </tr>
                    <tr>
						<td width="15%">
				          	<label>商标图样</label>  
				        </td>
				        <td colspan="3"> 
                       		<c:if test="${StringUtils:isNotEmpty(tradeMark.image)}"> 
						      <c:set value="${ipanthercore:getJSONMap(tradeMark.image)}" var="map" />
							      <div id="fileSpanAttachmentImg">
							  			<span id="attachmentImgName" style="margin-top: 8px;"><img alt="" style="max-width: 120px; max-height: 160px" src="${ctx}${map.fileId}" id="attachmentName"/></span> 
								   </div>								  
							  </c:if>							
                        </td>
                    </tr>
                    
                    <tr>
						<td width="15%">
				          	<label>是否著名商标</label>  
				        </td>
				        <td width="35%"> 
                       		${tradeMark.registeType}						
			             </td>
			             <td width="15%">
			             	<label>商标类</label> 
			             </td>
			             <td width="35%">
			             	${tradeMark.classfiy}
			             </td>
                    </tr>
                    <tr>
						<td width="15%">
				          	<label>初审公告号</label>  
				        </td>
				        <td width="35%"> 
                       		${tradeMark.firstPublicNumber}						
			             </td>
			             <td width="15%">
			             	<label>初审公告日期</label> 
			             </td>
			             <td width="35%">
			             	<fmt:formatDate value="${tradeMark.firstPublicDate}" pattern="yyyy-MM-dd"/>
			             </td>
                    </tr>
                    <tr>
						<td width="15%">
				          	<label>注册公告号</label>  
				        </td>
				        <td width="35%"> 
                       		${tradeMark.registePublicNumber}						
			             </td>
			             <td width="15%">
			             	<label>注册公告日期</label> 
			             </td>
			             <td width="35%">
			             	<fmt:formatDate value="${tradeMark.registePublicDate}" pattern="yyyy-MM-dd"/>
			             </td>
                    </tr>
                    <tr>
						<td width="15%">
				          	<label>代理人</label>  
				        </td>
				        <td colspan="3"> 
                       		${tradeMark.agency}						
			             </td>
			         </tr>
			         <tr>
			             <td width="15%">
			             	<label>商标注册地址</label> 
			             </td>
			             <td colspan="3">
			             	${tradeMark.registeAdress}
			             </td>
                    </tr>
                    <tr>
						<td width="15%">
				          	<label>注册人中文名</label>  
				        </td>
				        <td width="35%"> 
                       		${tradeMark.tradeMarkRegisterInfo.chineseName}						
			             </td>

			             <td width="15%">
			             	<label>注册人英文名</label> 
			             </td>
			             <td width="35%">
			             	${tradeMark.tradeMarkRegisterInfo.foreignName}
			             </td>
                    </tr>
                    <tr>
						<td width="15%">
				          	<label>中文地址</label>  
				        </td>
				        <td colspan="3"> 
                       		${tradeMark.tradeMarkRegisterInfo.chineseAddress}						
			             </td>
			         </tr>
			         <tr>
						<td width="15%">
							<label>英文地址</label>
				        </td>
				        <td colspan="3"> 
                       		${tradeMark.tradeMarkRegisterInfo.foreignAddress}						
			             </td>
			         </tr> 
		     	</tbody>
		     </table>
		     <table border="0" cellspacing="1" cellpadding="1"  class="alter-table-v">
		     	<tbody>		     	
		          <c:forEach var="list" items="${tradeMark.tradeMarkServiceInfo}" varStatus="status">
		            <tr>
		                <td width="15%">商品服务名称</td>
		                <td width="25%">${list.productName}</td>
		                <td width="15%">类似群</td>
		                <td width="15%">${list.classfiyLike}</td>
		                <td width="15%">序号 </td>
		                <td width="15%">${list.order}</td>
		            </tr>
		           </c:forEach>
    			</tbody>
		    </table>
      </form>
       <div style="text-align:center;padding:5px;">
<!--       	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitFormEdit()">保存</a> -->
       	<a href="javascript:void(0)" class="easyui-linkbutton" onclick="returnList()">关闭</a>
       </div>
</div>

<script type="text/javascript">

 tableVBg('.alter-table-v');

 function returnList(){
	 closeWindow('tmsMsgWindow');
 }
 


</script>
