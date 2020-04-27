function getByteLength(value){
		var length = value.length; 
	    for(var i = 0; i < value.length; i++){      
	        if(value.charCodeAt(i) > 127){      
	        	length++;      
	        }      
	    }
	    return length;
}

function tableBg(alterTableBox){
	var $alterTableBox = $(alterTableBox);
	 $alterTableBox.find('tbody tr:odd').css('background-color','#f9f9f9');
	 $alterTableBox.find('tbody tr').on('mouseover',function(){
		$(this).css('background-color','#eaf2ff');
	});
	 $alterTableBox.find('tbody tr').on('mouseout',function(){
		$(this).parent().find('tr:odd').css('background-color','#f9f9f9');
		$(this).parent().find('tr:even').css('background-color','#fff');
	});
}

//alterTableBox包含-space 则表示是个人端的样式
function tableVBg(alterTableBox){
	var $alterTableBox = $(alterTableBox);
	if(alterTableBox.indexOf('-space')!=-1) {//个人端
		$alterTableBox.find('tbody td:odd').css({'text-align':'left','background-color': '#f9f9f9'});
		$alterTableBox.find('tbody td:even').css({'text-align':'right','background-color': '#FFF6E5'});
	} else {//服务端
		$alterTableBox.find('tbody td:odd').css({'text-align':'left','background-color': '#f9f9f9'});
		$alterTableBox.find('tbody td:even').css({'text-align':'right','background-color': '#eaf2ff'});
	}
	//将表格的只读textarea的表框去掉_wuwentao
	$alterTableBox.find("textarea[readonly='readonly']").css("border-style","solid").css("border-color","#ffffff");
}

function setValidRequired(className, flag){
	if(flag){
		$('.'+className).append('<span class="c-red">*</span>');
		$('.'+className).find('select').validatebox({required: true});
	}else{
		$('.'+className).find('span').remove();
		$('.'+className).find('select').validatebox({required: false});
	}
}

function idArrToJsonStr(arr) {
	var str = '{"ids":[';
	$.each(arr, function (idx, obj) {
		if(idx == 0){
			str += '"' + obj.toString() + '"';
		}else {
			str += ',' + '"' +  obj.toString() + '"';
		}
	});
	str += ']}';

	return str;
}