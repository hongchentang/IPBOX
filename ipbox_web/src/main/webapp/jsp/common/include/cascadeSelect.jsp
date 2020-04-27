<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/taglib.jsp"%>
<c:set var="required" value="${StringUtils:isNotEmpty(param.required)?param.required:true}"/>
<script>
function getchildOptions(parentValue,dictTypeCode,uniqueId,selectName,index,dictValue){
	var thisSelect = $('#cascadeSelect_'+uniqueId+'_'+index);
	var thisDiv = $('#cascadeDiv_'+uniqueId+'_'+index);
	if(parentValue == null || parentValue == ''){
		parentValue = thisSelect.val();
	}else{
		thisSelect.find('option[value='+parentValue+']').attr('selected','selected');
	}
	if(dictValue == null){
		dictValue = '';
	}
	$.ajax({
		url:'${ctx}/tms/dict/getEntryOptionsSelectedByParentValue.do',
		type:'post',
		async:false,
		data:'dictTypeCode='+dictTypeCode+'&parentValue='+parentValue+'&dictValue'+dictValue,
		success:function(data){
			if(data != null && data != ''){
				var nextIndex = parseInt(index)+1;
				var nextDivId = 'cascadeDiv_'+uniqueId+'_'+nextIndex;
				var nextSelectId = 'cascadeSelect_'+uniqueId+'_'+nextIndex;
				if($('#'+nextDivId).length == 0){
					thisDiv.after(
						'<div id="'+nextDivId+'">'
							+'<select id="'+nextSelectId+'" onchange="getchildOptions(\'\',\''+dictTypeCode+'\',\''+uniqueId+'\',\''+selectName+'\','+nextIndex+')" style="width: 158px; margin-top:5px">'
								+'<option value="">--请选择--</option>'
								+data
							+'</select>'
						+'</div>'
					); 
					$('#'+nextSelectId).validatebox({    
					    required: true,    
					    panelHeight: 'auto'
					}); 
				}else{
					$('#'+nextSelectId).html('<option value="">--请选择--</option>'+data);
				}
			}else{
				thisDiv.nextAll().remove();
			}
			$('#'+uniqueId).find('select').attr('name','');
			$('#'+uniqueId).find('select').last().attr('name',selectName);
		}
	});
}

$(function(){
	if('${param.dictValue}' != ''){
		$.ajax({
			url:'${ctx}/tms/dict/getAllParentValue.do',
			type:'post',
			async:false,
			data:'dictTypeCode=${param.dictTypeCode }&dictValue=${param.dictValue}',
			success:function(data){
				if(data != null && data != ''){
					var json = $.parseJSON(data);
					if(json != null && json.length > 0){
						for (var i = 0; i < json.length; i++) {
							var parentValue = json[i];
							var dictValue = '';
							if(i == json.length - 1){
								dictValue = '${param.dictValue}';
							}else{
								dictValue = json[i+1];
							}
							getchildOptions(parentValue,'${param.dictTypeCode }','${param.uniqueId }','${param.name}',(i+1),dictValue);
						}
					}
				}
				$('#${param.uniqueId}').find('select').last().val('${param.dictValue}');
			}
		});
	}
});
</script>
<div id="cascadeDiv_${param.uniqueId }_1">
	<select name="${param.uniqueId}" onchange="getchildOptions('','${param.dictTypeCode }','${param.uniqueId}','${param.name }',1)" id="cascadeSelect_${param.uniqueId }_1" class="easyui-validatebox" data-options="panelHeight:'auto',required:${required}" style="width: 158px;">
		<option value="">--请选择--</option>
		${dict:getEntryOptionsSelectedNotParent(param.dictTypeCode,param.dictValue) }
	</select>
<!-- 	<span class="c-red">*</span> -->
</div>
