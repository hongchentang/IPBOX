<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes')}" var="fileTypes2"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize2"/>
<form id="usePatentAp_saveFrom" name="usePatentAp_saveFrom" action="${ctx}/application/usePatent/saveUsePatent.do" method="post" enctype="multipart/form-data">
	 <input type="hidden" name="id" value="${usePatent.id }">
	 <input type="hidden" name="patentId" id="patentId" value="${usePatent.patentId }">	 
	 <table class="alter-table-v" cellspacing="0" cellpadding="0" border="0">         
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
					<a href="javascript:void(0)" class="easyui-linkbutton" onclick="getPatentByNo()" iconCls="fa fa-search">搜索</a>
	
				</td>
				<td align="right" class='l-table-edit-td'>使用类型</td>
	            <td align="left" >
				   <select  id="useType" name="useType" class="easyui-combobox"  data-options="panelHeight:'auto',required:true" style="width: 171px;height:25px; " readonly>
							<option value="">--请选择--</option>
							${dict:getEntryOptionsSelected('IPBOX_APPLICATIN_TYPE',"1") }
					</select>
				</td>
			</tr>

         	<tr>
         		<td align="right" class='l-table-edit-td'>转让类型</td>
				<td align="left">
	         		<input type="text" class="easyui-textbox" data-options="required:true"  id="apType" name="apType" value="${usePatent.apType }">
				</td>
				<td align="right" class='l-table-edit-td'>转让日期</td>
				<td align="left">
	         		<input id="apDate" type="text"  class="Wdate" data-options="required:true" name="apDate" value="<fmt:formatDate value="${usePatent.apDate }" type="date" pattern="yyyy-MM-dd"/>" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly/>
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
	         		<input class="easyui-numberbox" data-options="required:true"  id="apIncome" name="usePatent.apIncome" value="234.56">
				</td>
	       	</tr>
	       	<tr>
	            <td align="right" class='l-table-edit-td'>转让条件</td>
	            <td align="left" colspan="3">
             		<textarea rows="2" style="width:800px;" id="apCondition" name="apCondition">${usePatent.apCondition }</textarea>
	        	</td>
          	</tr>
    
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

    </table>
</form>

<script type="text/javascript">
tableVBg('.alter-table-v');
$(document).ready(function (){
	//转让类型
	$("#apType").combobox({
		url: '${ctx}/common/datadict/getByTypeCode.do?typeCode=IPBOX_PATENT_TRANSFER',
		emptyText: '',
		valueField: 'dictValue',
		textField: 'dictName',
		slide: true,
		valueFieldID: 'apType',
		limitToList: true,
		panelHeight: 'auto'
	});
});

function getPatentByNo(){
	var applyNo = $("#patentPublicNumber").val();
	$.ajax({
		url:'${ctx}/intellectual/patent/getPatentByApplyNo.do?ApplyNo='+applyNo,//地址
		dataType:'json',//数据类型
		type:'POST',//类型
		//请求成功
		success:function(data,status){
			var josn=eval(data);
			if(josn.id !=null && josn.id!="" ){			
				//使用id选择器：可以设置值
	            //$("#patentName").textbox('setValue', josn.patentName);
	            //使用id选择器和setText：可以设置值
	         	$("#patentId").val(josn.id);
	         	$("#patentName").textbox('setValue', josn.patentName);
	         	$("#apFromMan").textbox('setValue', josn.applyer);

			}else{
				
				$("#patentName").textbox('setValue', "");
				alert("未查询到该申请号！");
				
			}
		}
	})
}

function submitProjectForm(){
	$.messager.confirm('确认','确认保存吗？',function(result){    
		if (result){  
			$('#usePatentAp_saveFrom').form('submit',{
				onSubmit: function(){
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
							closeWindow('addWindow');
							$('#viewUsePatentListAp').panel('refresh');
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
