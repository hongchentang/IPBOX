function getCityOptions(selectId,parentCode){
	if(parentCode!=''){
		regionsUtil.getCityOptions(parentCode,function callback(data){
			$("#"+selectId).html("<option value=''>请选择</option>"+data); 
			$("#"+selectId).change();
		});
	}else{
		$("#"+selectId).html("<option value=''>请选择</option>"); 
		$("#"+selectId).change();
	}
}

function getCityOptionsSelected(selectId,parentCode,defaultValue){
	if(parentCode!=''){
		regionsUtil.getCityOptions(parentCode,defaultValue,function callback(data){
			$("#"+selectId).html("<option value=''>请选择</option>"+data); 
			$("#"+selectId).change();
		});
	}else{
		$("#"+selectId).html("<option value=''>请选择</option>"); 
		$("#"+selectId).change();	
	}
}

function getCountiesOptions(selectId,parentCode){
	if(parentCode!=''){
		regionsUtil.getCountiesOptions(parentCode,function callback(data){
			$("#"+selectId).html("<option value=''>请选择</option>"+data); 
			$("#"+selectId).change();
		});
	}else{
		$("#"+selectId).html("<option value=''>请选择</option>"); 
		$("#"+selectId).change();
	}
}

function getCountiesOptionsSelected(selectId,parentCode,defaultValue){
	if(parentCode!=''){
		regionsUtil.getCountiesOptions(parentCode,defaultValue,function callback(data){
			$("#"+selectId).html("<option value=''>请选择</option>"+data); 
			$("#"+selectId).change();
		});
	}else{
		$("#"+selectId).html("<option value=''>请选择</option>"); 
		$("#"+selectId).change();
	}
}