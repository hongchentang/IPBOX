<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<c:set value="${ipanthercore:getProperty('attachment.default.fileTypes.image')}" var="fileTypes"/>
<c:set value="${ipanthercore:getProperty('attachment.default.fileMaxSize')}" var="fileMaxSize"/>
<form id="qualificationForm" name="qualificationForm" action="${ctx}/common/dept/qualificationSave.do" method="post" enctype="multipart/form-data">
	<div class="easyui-panel" title="企业资质" collapsible="true">
		<table cellspacing="0" cellpadding="0" border="0" class="alter-table-v">
		<input type="hidden" name="id" id="id" value="${qualification.id}"/>
		<input type="hidden" name="departmentId" id="id" value="${qualification.departmentId}"/>
		<tbody>
			<tr>
				<td>企业认定情况</td>
<!-- 				<td colspan="3"> -->
<!-- 					<span> -->
<%-- 					<input type="checkbox" id ="departmentIsQualification1" name="departmentIsQualification" value="1" ${"1" eq qualification.departmentIsQualification?"checked":""}/> --%>
<!-- 					高新技术企业 -->
<!-- 					</span> -->
<!-- 					<span> -->
<%-- 					<input type="checkbox" id ="departmentIsQualification2" name="departmentIsQualification" value="2" ${"2" eq qualification.departmentIsQualification?"checked":""}/> --%>
<!-- 					双软认证企业 -->
<!-- 					</span> -->
<!-- 					<span> -->
<%-- 					<input type="checkbox" id ="departmentIsQualification3" name="departmentIsQualification" value="3" ${"3" eq qualification.departmentIsQualification?"checked":""}/> --%>
<!-- 					其他 -->
<!-- 					</span> -->
<!-- 				</td> -->
				<td colspan="3">
					<span>
					<input type="checkbox" id ="departmentIsQualification1" name="departmentIsQualification" />
					高新技术企业
					</span>
					<span>
					<input type="checkbox" id ="departmentIsQualification2" name="departmentIsQualification" />
					双软认证企业
					</span>
					<span>
					<input type="checkbox" id ="departmentIsQualification3" name="departmentIsQualification" />
					其他
					</span>
				</td>
			</tr>
			<tr>
				<td >企业所处园区具体情况</td>
<!-- 				<td colspan="3"> -->
<!-- 					<span> -->
<%-- 					<input type="checkbox" id ="departmentIsDistrict1" name="departmentIsDistrict" value="1" ${"1" eq qualification.departmentIsDistrict?"checked":""}/> --%>
<!-- 					省级以上高新区内企业	 -->
<!-- 					</span> -->
<!-- 					<span> -->
<%-- 					<input type="checkbox" id ="departmentIsDistrict2" name="departmentIsDistrict" value="2" ${"2" eq qualification.departmentIsDistrict?"checked":""}/> --%>
<!-- 					省级以上专业镇区内企业	 -->
<!-- 					</span> -->
<!-- 					<span> -->
<%-- 					<input type="checkbox" id ="departmentIsDistrict3" name="departmentIsDistrict" value="3" ${"3" eq qualification.departmentIsDistrict?"checked":""}/> --%>
<!-- 					省级以上民营科技园区内企业 -->
<!-- 					</span> -->
<!-- 					<span> -->
<%-- 					<input type="checkbox" id ="departmentIsDistrict4" name="departmentIsDistrict" value="3" ${"4" eq qualification.departmentIsDistrict?"checked":""}/> --%>
<!-- 					其他 -->
<!-- 					</span> -->
<!-- 				</td> -->
				<td colspan="3">
					<span>
					<input type="checkbox" id ="departmentIsDistrict1" name="departmentIsDistrict" />
					省级以上高新区内企业	
					</span>
					<span>
					<input type="checkbox" id ="departmentIsDistrict2" name="departmentIsDistrict" />
					省级以上专业镇区内企业	
					</span>
					<span>
					<input type="checkbox" id ="departmentIsDistrict3" name="departmentIsDistrict"/>
					省级以上民营科技园区内企业
					</span>
					<span>
					<input type="checkbox" id ="departmentIsDistrict4" name="departmentIsDistrict" />
					其他
					</span>
				</td>
			</tr>
			<tr>
				<td>其他认定情况（请说明）</td>
				<td colspan="3">
            		<textarea rows="5" cols="120" id="departmentIsOthers" name="projectExplain">${qualification.departmentIsOthers }</textarea>
		  		</td>
			</tr>
		</tbody>
	</table>
	</div>
	

	<div style="text-align: center">
		<button type="button" onclick="save()" class="easyui-linkbutton" > <i class="fa fa-save"></i>保 存 </button>
		<button type="reset"  class="easyui-linkbutton" class="easyui-linkbutton"> 重 置 </button>
	</div>
</form>
<script type="text/javascript">
tableVBg('.alter-table-v');
$(document).ready(function (){
	
});
<c:if test="${not empty dept.id}">
$.parser.onComplete=changeDeptType;
</c:if>

function save() {
	$.messager.confirm('提示', '确定保存？', function(r){
		if(r) {
			jQuery('#qualificationForm').form('submit',{
				onSubmit: function(){
					 return $('#qualificationForm').form('validate');
				},
			    success: function(data){
			    	var json=jQuery.parseJSON(data);
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							jQuery.messager.alert("提示信息",message,"error");
						} else if(parseInt(statusCode)==200){
							jQuery.messager.alert("提示信息",message,"info");
							jQuery('#viewDeptPropertiesTopTab').panel('refresh');
							//closeWindow("showAddDialog");
						}
					}
			    }
			}); 
		}
		
	});
}


</script>
