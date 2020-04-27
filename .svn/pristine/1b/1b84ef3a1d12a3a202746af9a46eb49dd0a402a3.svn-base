<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes')}" var="fileTypes2"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize2"/>
<form id="usePatent_saveFrom" name="usePatent_saveFrom" action="${ctx}/application/usePatent/saveUsePatent.do" method="post" enctype="multipart/form-data">
	 <input type="hidden" name="id" value="${usePatent.id }">	 
	 <table class="alter-table-v" cellspacing="0" cellpadding="0" border="0">         
		<tbody>
			<tr>
				<td align="right" class='l-table-edit-td'>专利标题</td>
	            <td align="left" colspan="3">
					<input type="text" class="easyui-textbox" style="width:600px;" data-options="required:true" id="patentName" name="patentName" value="${usePatent.patentName}"/>
				</td>
	        </tr>
	        <tr>
	            <td align="right" class='l-table-edit-td'>专利申请号</td>
	            <td align="left" >
				   <input type="text" class="easyui-textbox"  data-options="required:true" id="patentPublicNumber" name="patentPublicNumber" value="${usePatent.patentPublicNumber }"/>
				</td>
				<td align="right" class='l-table-edit-td'>使用类型</td>
	            <td align="left" >
				   <select  id="useType" name="useType" class="easyui-combobox" data-options="panelHeight:'auto',required:true" style="width: 171px;height:25px">
							<option value="">--请选择--</option>
							${dict:getEntryOptionsSelected('IPBOX_INDUSTRY',usePatent.useType) }
					</select>
				</td>
			</tr>
		</tbody>
		<tbody id="table1" style="display:none;">
	        <tr>
				<td align="right" class='l-table-edit-td'>许可项目名称</td>
		        <td colspan="3">
		         	<input type="text" class="easyui-textbox" data-options="required:true" style="width:600px;" id="wpTechnologyName" name="wpTechnologyName" value="${usePatent.wpTechnologyName }">
				</td>
			</tr>
			<tr>
				<td align="right" class='l-table-edit-td'>专利许可人</td>
				<td colspan="3">
	         		<input type="text" class="easyui-textbox" data-options="required:true" style="width:600px;" id="wpFromName" name="wpFromName" value="${usePatent.wpFromName }">
				</td>
			</tr>
			<tr>
				<td align="right" class='l-table-edit-td'>专利被许可人</td>
				<td colspan="3">
	         		<input type="text" class="easyui-textbox" data-options="required:true" style="width:600px;" id="wpToName" name="wpToName" value="${usePatent.wpToName }">
				</td>
			</tr>
			<tr>
				<td align="right" class='l-table-edit-td'>许可开始日期</td>
	         	<td>
	         		<input id="wpStartDate" type="text"  class="Wdate" data-options="required:true" name="wpStartDate" value="<fmt:formatDate value="${usePatent.wpStartDate }" type="date" pattern="yyyy-MM-dd"/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly/>
				</td>
				<td align="right" class='l-table-edit-td'>许可结束日期</td>
	         	<td>
	         		<input id="wpEndDate" type="text"  class="Wdate" data-options="required:true" name="wpEndDate" value="<fmt:formatDate value="${usePatent.wpEndDate }" type="date" pattern="yyyy-MM-dd"/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly/>
				</td>
	       	</tr>	          
           	<tr>
	            <td align="right" class='l-table-edit-td'>专利许可类型</td>
	            <td align="left" colspan="3">
				   <select  id="useType" name="useType" class="easyui-validatebox" data-options="panelHeight:'auto',required:true" style="width: 171px;height:25px">
							<option value="">--请选择--</option>
							${dict:getEntryOptionsSelected('IPR_PROJECT_TYPE',usePatent.wpType) }
					</select>
				</td>
          	</tr>          	
          	<tr>
	            <td align="right" class='l-table-edit-td'>许可范围</td>
	            <td align="left" colspan="3">
             		<textarea rows="5" style="width:800px;" id="wpField" name="wpField">${usePatent.wpField }</textarea>
	        	</td>
          	</tr>
         </tbody>
         <tbody id="table2" style="display:none;">
         	<tr>
         		<td align="right" class='l-table-edit-td'>抵押人</td>
				<td align="left">
	         		<input type="text" class="easyui-textbox" data-options="required:true"  id="plepMortgager" name="plepMortgager" value="${usePatent.plepMortgager }">
				</td>
				<td align="right" class='l-table-edit-td'>质押人</td>
				<td align="left">
	         		<input type="text" class="easyui-textbox" data-options="required:true"  id="plepPledgor" name="plepPledgor" value="${usePatent.plepPledgor }">
				</td>
			</tr>
         	<tr>
				<td align="right" class='l-table-edit-td'>抵押开始日期</td>
	         	<td>
	         		<input id="plepCreateDate" type="text"  class="Wdate" data-options="required:true" name="plpeCreateDate" value="${usePatent.plepCreateDate }" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly/>
				</td>
				<td align="right" class='l-table-edit-td'>抵押结束日期</td>
	         	<td>
	         		<input id="plepEndDate" type="text"  class="Wdate" data-options="required:true" name="plepEndDate" value="${usePatent.plepEndDate }" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly/>
				</td>
	       	</tr>
	       	<tr>
				<td align="right" class='l-table-edit-td'>质押金额</td>
				<td align="left">
	         		<input type="text" class="easyui-textbox" data-options="required:true"  id="plepDebtNumber" name="plepDebtNumber" value="${usePatent.plepDebtNumber }">
				</td>
				<td align="right" class='l-table-edit-td'>抵押状态</td>
				<td align="left">
	         		<input type="text" class="easyui-textbox" data-options="required:true"  id="plepStuta" name="plepStuta" value="${usePatent.plepStuta }">
				</td>
	       	</tr>
         </tbody>
         
         <tbody id="table3" style="display:none;">
         	<tr>
         		<td align="right" class='l-table-edit-td'>入股企业</td>
				<td align="left">
	         		<input type="text" class="easyui-textbox" data-options="required:true"  id="spShareName" name="spShareName" value="${usePatent.spShareName }">
				</td>
				<td align="right" class='l-table-edit-td'>入股类型</td>
				<td align="left">
	         		<input type="text" class="easyui-textbox" data-options="required:true"  id="spShareType" name="spShareType" value="${usePatent.spShareType }">
				</td>
			</tr>
         	<tr>
				<td align="right" class='l-table-edit-td'>入股比例</td>
	         	<td>
	         		<input type="text" class="easyui-textbox" data-options="required:true"  id="spShareScale" name="spShareScale" value="${usePatent.spShareScale }">
				</td>
				<td align="right" class='l-table-edit-td'>入股金额</td>
				<td align="left">
	         		<input type="text" class="easyui-textbox" data-options="required:true"  id="spShareMoney" name="spShareMoney" value="${usePatent.spShareMoney }">
				</td>
				
	       	</tr>
	       	<tr>
				<td align="right" class='l-table-edit-td'>入股日期</td>
	         	<td>
	         		<input id="spShareDate" type="text"  class="Wdate" data-options="required:true" name="spShareDate" value="${usePatent.spShareDate }" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly/>
				</td>
				<td align="right" class='l-table-edit-td'>入股联系人</td>
				<td align="left">
	         		<input type="text" class="easyui-textbox" data-options="required:true"  id="spLinkMan" name="spLinkMan" value="${usePatent.spLinkMan }">
				</td>
	       	</tr>
	       	<tr>
	            <td align="right" class='l-table-edit-td'> 入股条件</td>
	            <td align="left" colspan="3">
             		<textarea rows="3" style="width:800px;" id="spShareCondition" name="spShareCondition">${usePatent.spShareCondition }</textarea>
	        	</td>
          	</tr>
         </tbody>  
		 <tbody id="table4">
         	<tr>
         		<td align="right" class='l-table-edit-td'>转让类型</td>
				<td align="left">
	         		<input type="text" class="easyui-textbox" data-options="required:true"  id="apType" name="apType" value="${usePatent.apType }">
				</td>
				<td align="right" class='l-table-edit-td'>转让日期</td>
				<td align="left">
	         		<input id="apDate" type="text"  class="Wdate" data-options="required:true" name="apDate" value="${usePatent.apDate }" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly/>
				</td>
			</tr>
         	<tr>
				<td align="right" class='l-table-edit-td'>转让方</td>
	         	<td>
	         		<input type="text" class="easyui-textbox" data-options="required:true"  id="apFromMan" name="apFromMan" value="${usePatent.apFromMan }">
				</td>
				<td align="right" class='l-table-edit-td'>转让方代表</td>
	         	<td>
	         		<input type="text" class="easyui-textbox" data-options="required:true"  id="apFromName" name="apFromName" value="${usePatent.apFromName }">
				</td>
			</tr>
			<tr>
				<td align="right" class='l-table-edit-td'>转让方地址</td>
				<td align="left" colspan="3">
             		<textarea rows="2" style="width:800px;" id="apFromAddress" name="apFromAddress">${usePatent.apFromAddress }</textarea>
	        	</td>				
	       	</tr>
	       	<tr>
				<td align="right" class='l-table-edit-td'>受让方</td>
	         	<td>
	         		<input type="text" class="easyui-textbox" data-options="required:true"  id="apToMan" name="apToMan" value="${usePatent.apToMan }">
				</td>
				<td align="right" class='l-table-edit-td'>受让方代表</td>
	         	<td>
	         		<input type="text" class="easyui-textbox" data-options="required:true"  id="apToName" name="apToName" value="${usePatent.apToName }">
				</td>
			</tr>
			<tr>
				<td align="right" class='l-table-edit-td'>受让方地址</td>
				<td align="left" colspan="3">
             		<textarea rows="2"  style="width:800px;" id="apToAddress" name="apToAddress">${usePatent.apToAddress }</textarea>
	        	</td>				
	       	</tr>
	       	<tr>
				<td align="right" class='l-table-edit-td'>转让金额</td>
				<td align="left" colspan="3">
	         		<input type="text" class="easyui-textbox" data-options="required:true"  id="apIncome" name="apIncome" value="${usePatent.apIncome }">
				</td>
	       	</tr>
	       	<tr>
	            <td align="right" class='l-table-edit-td'>转让条件</td>
	            <td align="left" colspan="3">
             		<textarea rows="2" style="width:800px;" id="apCondition" name="apCondition">${usePatent.apCondition }</textarea>
	        	</td>
          	</tr>
         </tbody>
         <tbody>     
          	<tr>
	            <td >联系人电话</td>
	            <td >
	            	<input type="text" class="easyui-textbox" id="contractNumber" name="contractNumber" value="${usePatent.contractNumber }">
				</td>
				<td >所属公司</td>
	            <td >
				   <input type="text" class="easyui-textbox"  id="orgDepId" name="orgDepId" value="${usePatent.orgDepId }"/>
				</td>
          	</tr>

          	<tr>			
				<td>上传合同附件</td>
	        	<td colspan="3">
	        		<input type="file"  name="uploadFile">
		        		<div>
						     允许上传的文件类型：${fileTypes2}<br/>
						    允许上传文件的大小：${fileMaxSize2}KB 
						</div>
	        	</td>
          	</tr>
          
          	<tr>
	            <td >备注</td>
	            <td colspan="3">
	            	<textarea rows="3" style="width:800px;" id="remark" name="remark">${usePatent.remark }</textarea>
				</td>
          	</tr>
         
          	<tr style="text-align: center;line-height: 20px">
	            <td colspan="4">
		            <div style="width: 100%;text-align: center;">
			            <button type="button" onclick="javascript:submitProjectForm();" style="float: center; margin-right: 20px" class="easyui-linkbutton">
		            	保存
		            	</button>
	            	</div>
            	</td>
          	</tr>
         </tbody>
    </table>
</form>

<script type="text/javascript">
tableVBg('.alter-table-v');
$(document).ready(function (){
});

$("#useType").combobox({
    onSelect: function () {
        var newPtion = $("#useType").combobox('getText');
        if (newPtion == "化学") {
            $("#table1").attr("style","display:block");
            $("#table2").attr("style","display:none");
            $("#table3").attr("style","display:none");
            $("#table4").attr("style","display:none");
        }
        else if(newPtion == "医学"){
        	$("#table1").attr("style","display:none");
            $("#table2").attr("style","display:block");
            $("#table3").attr("style","display:none");
            $("#table4").attr("style","display:none");
     	}
        else if(newPtion == "建筑"){
        	$("#table1").attr("style","display:none");
            $("#table2").attr("style","display:none");
            $("#table3").attr("style","display:block");
            $("#table4").attr("style","display:none");
        }
//         else{
//         	$("#table1").attr("style","display:none");
//             $("#table2").attr("style","display:none");
//             $("#table3").attr("style","display:none");
//             $("#table4").attr("style","display:block");
//         }
    }
})
function submitProjectForm(){
	$.messager.confirm('确认','确认保存吗？',function(result){    
		if (result){  
			$('#project_saveFrom').form('submit',{
				onSubmit: function(){
					if($("#year").val()==''){
						$.messager.progress('close');	
						$.messager.alert('提示','请填写项目实施年度！');
						return false;
					}
					var isValid = $(this).form('validate');
					if (!isValid){
						$.messager.progress('close');	
						$.messager.alert('提示','请填写必填项！');
					}
					return isValid;	
				},
				success: function(data){
					var json=jQuery.parseJSON(data);
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							$.messager.alert('提示',message);
						}else if(parseInt(statusCode)==200){
							closeWindow('addProjectWindow');
							$('#${searchParam.paramMap.rePanel }').panel('refresh');
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
</script>
