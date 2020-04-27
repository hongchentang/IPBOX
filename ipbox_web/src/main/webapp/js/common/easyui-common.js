function openWindow(id,title,width,height,href,model,onCloseFunction){
	if(width == 0){
		width = $(window).width() * 75 / 100;
	}
	if(height == 0){
		height = $(window).height() * 95 / 100;
	}
	var top=10;
	if($(window).height()>height){
		top=($(window).height() - height)*0.5;
	}
	if(onCloseFunction == null || onCloseFunction == ''){
		onCloseFunction = function(){$(this).window('destroy');};
	}
	
	$("<div id='"+id+"'/>").window({    
		title:title,
	    width: width,    
	    height: height,
		//top: top,    
	    href: href,    
	    modal: model,
	    collapsible: false,
	    minimizable: false,
	    draggable:true,
	    onClose:onCloseFunction,
		method:'post'
	});
	 
}
function closeWindow(id){
	$("#"+id).window('close');   
}

function ajaxSubmitUrl(){
	
}

function getListElem(dictTypeCode,dictValue){
	var rData=$.ajax({
			url:'/tms/dict/getListElemtEntry.do',
			type:'post',
			async:false,
			data:'dictTypeCode='+dictTypeCode+'&dictValue='+dictValue,
			success:function(d){
			}
		}).responseText;
		return rData;;
}

function getHeight(id,percent){
	return $('#'+id).height() * percent / 100;
}

function getWidth(id,percent){
	return $('#'+id).width() * percent / 100;
}

function easyuiQuery(formId, divId){
	/*$('#'+divId).panel({
		href:$('#'+formId).attr('action'),
		queryParams:serializeObject($('#'+formId)),
		method:'post'
	});
	$('#'+divId).panel('refresh');*/
	
	$('#'+divId).panel('options').queryParams=serializeObject($('#'+formId,'#'+divId));
	$('#'+divId).panel('options').method='post';
	$('#'+divId).panel('refresh',$('#'+formId,'#'+divId).attr('action'));
}

var easyuiUtils={
	
	//打开窗口
	openWindow:function(id,title,width,height,href,model,onCloseFunction){
		this.openWindowWithParams(id,title,width,height,href,model,{},onCloseFunction);
	},
	
	//打开窗口
	openWindowWithParams:function(id,title,width,height,href,model,queryParams,onCloseFunction){
		if(width == 0){
			width = $(window).width() * 75 / 100;
		}
		if(height == 0){
			height = $(window).height() * 85 / 100;
		}
		var top=10;
		if($(window).height()>height){
			top=($(window).height() - height)*0.5;
		}
		if(onCloseFunction == null || onCloseFunction == ''){
			onCloseFunction = function(){$(this).window('destroy');};
		}
		
		$("<div id='"+id+"'/>").window({    
			title:title,
			width: width,    
			height: height,
			//top: top,    
			href: href,    
			modal: model,
			collapsible: false,
			minimizable: false,
			draggable:true,
			queryParams:queryParams,
			onClose:onCloseFunction,
			method:'post'
		});
	},
	
	//关闭ID窗口
	closeWindow:function(id){
		if(id!=undefined&&$.trim(id)!=''){
			$("#"+id).window('close');
		}
	},
	
	/*
	 * 提交formId，并将返回结果刷新到panelId
	 * 常用场景：列表页中的查询按钮
	 */
	ajaxPostFormForPanel:function(formId,panelId){
		/*$('#'+panelId).panel({
			href:$('#'+formId).attr('action'),
			queryParams:serializeObject($('#'+formId)),
			method:'post'
		});
		$('#'+panelId).panel('refresh');*/
		$('#'+panelId).panel('options').queryParams=serializeObject($('#'+formId));
		$('#'+panelId).panel('options').method='post';
		$('#'+panelId).panel('refresh',$('#'+formId).attr('action'));
	},
	
	/*
	 * 由于查询后，重新刷新此panel时，会缓存查询条件，所以需要清空查询条件然后刷新
	 * 清空查询条件，并重新查询结果刷新到panelId
	 * 常用场景：列表页中的清空查询条件按钮
	 */
	ajaxClearFormForPanel:function(panelId){
		/*$('#'+panelId).panel({
			queryParams:{},
		});
		//$('#'+panelId).panel('options').queryParams={};
		$('#'+panelId).panel('refresh');*/
		$('#'+panelId).panel('options').queryParams={};
		$('#'+panelId).panel('refresh');
	},
	
	/**
	 * 调用panel('refresh',url)方法刷新panelId，panelId可以是tabId
	 * 如果panelId,loadUrl为空，则调用 getCurrentTab().panel('refresh')
	 * 如果loadUrl为空,则调用 $('#'+panelId).panel('refresh')
	 */
	refreshPanel:function(panelId,loadUrl){
		if(panelId!=undefined){
			if(loadUrl!=undefined){
				$('#'+panelId).panel('refresh',loadUrl);
			}
			else{
				$('#'+panelId).panel('refresh');
			}
		}
		else{
			getCurrentTab().panel('refresh');
		}
	},
	
	/*
	 * 提示message后，用ajax提交postUrl，然后调用panel('refresh',url)方法刷新panelId，panelId可以是tabId
	 * 如果panelId,loadUrl为空，则调用 getCurrentTab().panel('refresh')
	 * 如果loadUrl为空,则调用 $('#'+panelId).panel('refresh')
	 * 常用场景：列表中的删除功能
	 */
	confirmPostUrl:function (message,postUrl,panelId,loadUrl){
		$.messager.confirm('提示',message,function(result){    
			if (result){  
				var data= jQuery.ajaxSubmitValue(postUrl);
				if(!jQuery.isEmptyObject(data)){
						var json=jQuery.parseJSON(data);
						if(!jQuery.isEmptyObject(json)){
							var msg = json.message;
							var statusCode = json.statusCode;
							//Response转为AjaxReturnObject
							if(json.responseCode!=undefined){
								msg=json.responseMsg;
								statusCode=(json.responseCode=='00'?200:300);
							}
							if(parseInt(statusCode)==300){
								$.messager.alert('提示',msg,'error');
							}
							else if(parseInt(statusCode)==200){
								$.messager.alert('提示',msg,'info',function(){
									if(panelId!=undefined){
										if(loadUrl!=undefined){
											$('#'+panelId).panel('refresh',loadUrl);
										}
										else{
											$('#'+panelId).panel('refresh');
										}
									}
									else{
										getCurrentTab().panel('refresh');
									}
								});
							}
						}else{
								$.messager.alert('提示','返回数据解析错误！','error');
						}
				 }
				 else{
					$.messager.alert('提示','返回数据错误！','error');
				}
			}
		});
	},
	/*
	 * 1.提示message
	 * 2.用ajax提交formId，
	 * 3.关闭windowId,windowId可以是要关闭的panelId，如果windowId为空,或不存在，则不执行此操作
	 * 4.调用panel('refresh',loadUrl)方法刷新panelId，panelId可以是tabId
	 * 注：如果panelId,loadUrl为空，则调用 getCurrentTab().panel('refresh')
	 * 注：如果loadUrl为空,则调用 $('#'+panelId).panel('refresh')
	 * 常用场景：弹出表单的保存或修改窗口，点击保存并且后台操作成功，关闭此窗口，然后
	 */
	confirmPostForm:function (message,formId,windowId,panelId,loadUrl){
		$.messager.confirm('提示',message,function(result){    
			if (result){  
				var data= jQuery.ajaxSubmit(formId);
				if(!jQuery.isEmptyObject(data)){
						var json=jQuery.parseJSON(data);
						if(!jQuery.isEmptyObject(json)){
							var msg = json.message;
							var statusCode = json.statusCode;
							//Response转为AjaxReturnObject
							if(json.responseCode!=undefined){
								msg=json.responseMsg;
								statusCode=(json.responseCode=='00'?200:300);
							}
							if(parseInt(statusCode)==300){
								$.messager.alert('提示',msg,'error',function(){
									closeWindow(windowId);
								});
							}else if(parseInt(statusCode)==200){
								$.messager.alert('提示',msg,'info',function(){
									closeWindow(windowId);
									if(panelId!=undefined){
										if(loadUrl!=undefined){
											$('#'+panelId).panel('refresh',loadUrl);
										}
										else{
											$('#'+panelId).panel('refresh');
										}
									}
									else{
										getCurrentTab().panel('refresh');
									}
								});
							}
						}else{
								$.messager.alert('提示','返回数据解析错误！','error');
						}
				 }
				 else{
					$.messager.alert('提示','返回数据错误！','error');
				}
			}
		});
	},
	/*
	 * 返回表格中选择的一条记录
	 * 如果没有选择或者选择多条，提示用户，返回null
	 * tableId表格div的ID
	 * isMulti是否可多选
	 * 一般tabId可不穿，当table不是在主页面的tab下时，需要将tabId传过来
	 */
	getSelectedData:function(tableId,isMulti,tabId) {
		var tab;
		if(undefined!=tabId&&''!=tabId) {
			tab = $('#'+tabId);
		} else {
			tab = getCurrentTab();
		}
		var selectedDatas = tab.find('#'+tableId).datagrid('getSelections');
		if(isMulti) {
			if(selectedDatas.length==0) {
				$.messager.alert('消息','请至少选择一条记录','warning');
				return null;
			} else {
				return selectedDatas;
			}
		} else {
			if(selectedDatas.length != 1){
				$.messager.alert('消息','请选择一条记录','warning');
				return null;
			} else {
				return selectedDatas[0];
			}
		}
	}
}