/*
	文件上传
displayName,返回elemtName
displayValue,返回elemtValue
*/
function upload(billId,displayName,displayValue,type,title){
	var windowTitle="附件上传";
	if(title){
		windowTitle=title;
	}
	var attachParam = jQuery("#"+billId).val();
	var url='/common/attachment/goUploadAttachment.do?paramMap[displayName]='+displayName+'&paramMap[displayValue]='+displayValue+'&paramMap[type]='+type+'&paramMap[attachParam]='+attachParam.replace(/\+/g, '%2B');
	//var fileModal=$("#fileModal_"+billId);
	easyuiUtils.openWindow("fileUpload",windowTitle,330,300,url,null,null);
	
/*	var attachParam = $("#attachmentParam_"+billId).val();
	var fileModal=$("#fileModal_"+billId);
	
	//$('body').modalmanager('loading');
	$.get('/common/attachment/goUploadAttachment.do?paramMap[displayName]='+displayName+'&paramMap[displayValue]='+displayValue+'&paramMap[type]='+type+'&paramMap[attachParam]='+attachParam.replace(/\+/g, '%2B'),'',
	  function(data){
		bootbox.dialog({message:data});
	  });*/
}


function uploadFile(attachParam,displayName,displayValue,type,title){
	var windowTitle="附件上传";
	if(title){
		windowTitle=title;
	}
	var url='/common/attachment/goUploadAttachment.do?paramMap[displayName]='+displayName+'&paramMap[displayValue]='+displayValue+'&paramMap[type]='+type+'&paramMap[attachParam]='+attachParam.replace(/\+/g, '%2B');
	//var fileModal=$("#fileModal_"+billId);
	easyuiUtils.openWindow("fileUpload",windowTitle,330,300,url,null,null);
	
/*	var attachParam = $("#attachmentParam_"+billId).val();
	var fileModal=$("#fileModal_"+billId);
	
	//$('body').modalmanager('loading');
	$.get('/common/attachment/goUploadAttachment.do?paramMap[displayName]='+displayName+'&paramMap[displayValue]='+displayValue+'&paramMap[type]='+type+'&paramMap[attachParam]='+attachParam.replace(/\+/g, '%2B'),'',
	  function(data){
		bootbox.dialog({message:data});
	  });*/
}
