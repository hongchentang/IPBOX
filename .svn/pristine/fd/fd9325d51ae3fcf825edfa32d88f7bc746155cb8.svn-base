String.prototype.trim= function(){  
    return this.replace(/(^\s*)|(\s*$)/g, "");  
}; 
$.extend({
	ajaxQuery:function(formId,divId,callback){
		$.ajax({
			url:$("#"+formId).attr("action"),
			data:$("#"+formId).serialize(),
			type:"post",
			success:function(data){
				$("#"+divId).html(data);
				if(callback!=undefined){
					var $callback = callback;
					if (! $.isFunction($callback)) $callback = eval('(' + callback + ')');
					$callback();
				}
			}
		});		
	},
	ajaxSubmit:function(formId){
		var rData = $.ajax({
			url:$("#"+formId).attr("action"),
			data:$("#"+formId).serialize(),
			type:"post",
			async:false,
			success:function(data){
				
			}
		}).responseText;
		return rData;
		
	},
	search : function($from, contentId) {
			var data = jQuery.ajaxSubmit($from);
			jQuery("#" + contentId).html(data);
	},
	ajaxSubmitValue:function(urlValue){
		var rData = $.ajax({
			url:urlValue,
			//data:$("#"+formId).serialize(),
			type:"post",
			async:false,
			success:function(data){
				
			}
		}).responseText;
		return rData;
		
	},
	
	ajaxPostForm:function(fromName,contentId,loadUrl){
		if (!$("#"+fromName).valid()) {
			return false;
		}else{
			var data= jQuery.ajaxSubmit(fromName);
             if(!jQuery.isEmptyObject(data)){
				 	var json=jQuery.parseJSON(data);
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							bootbox.alert(message);
						}else if(parseInt(statusCode)==200){
							bootbox.alert(message, function() {
							jQuery("#"+contentId).load(loadUrl);
							});
						}
					}else{
						console.error("json is null");
					}
			 }else{
				 	console.error("data is null");
			}
		}
	  }
,
confirmPostForm:function (message,fromId,contentId,loadUrl){
	 bootbox.confirm(message, function(result) {
	      if(result){
	      	jQuery.ajaxPostForm(fromId,contentId,loadUrl);
	      }
     })
},
confirmPost:function (message,postUrl,contentId,loadUrl){
	 bootbox.confirm(message, function(result) {
      if(result){
    		var data= jQuery.ajaxSubmitValue(postUrl);
            if(!jQuery.isEmptyObject(data)){
				 	var json=jQuery.parseJSON(data);
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							bootbox.alert(message);
						}else if(parseInt(statusCode)==200){
							bootbox.alert(message, function() {
							jQuery("#"+contentId).load(loadUrl);
							});
						}
					}else{
						console.error("json is null");
					}
			 }else{
				 	console.error("data is null");
			}
      }
	}); 
  },
	ajaxValidForm:function(fromName){
		if (!$("#"+fromName).valid()) {
			return false;
		}else{
			var data= jQuery.ajaxSubmit(fromName);
            if(!jQuery.isEmptyObject(data)){
				 	var json=jQuery.parseJSON(data);
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							bootbox.alert(message);
						}else if(parseInt(statusCode)==200){
							bootbox.alert(message);
							return true;
						}
					}else{
						console.error("json is null");
					}
			 }else{
				 	console.error("data is null");
			}
		}
	},
	openModalDialog: function (loadUrl,contentId){
		$.ajax({
			url : loadUrl,
			data : '',
			type : "post",
			success : function(data) {
				$("#" + contentId).html(data);
				$("#" + contentId).modal('show');
			}
		});
	},
	confirmBootbox:function (message,postUrl){
		 bootbox.confirm(message, function(result) {
		      if(result){
		    		var data= jQuery.ajaxSubmitValue(postUrl);
		            if(!jQuery.isEmptyObject(data)){
						 	var json=jQuery.parseJSON(data);
							if(!jQuery.isEmptyObject(json)){
								var message = json.message;
								var statusCode = json.statusCode;
								if(parseInt(statusCode)==300){
									bootbox.alert(message);
								}else if(parseInt(statusCode)==200){
									bootbox.alert(message);
									return  true;
								}
							}else{
								console.error("json is null");
							}
					 }else{
						 	console.error("data is null");
					}
		      }
			}); 
		  },
postSubmit:function (postUrl,contentId,loadUrl){
    		var data= jQuery.ajaxSubmitValue(postUrl);
            if(!jQuery.isEmptyObject(data)){
				 	var json=jQuery.parseJSON(data);
					if(!jQuery.isEmptyObject(json)){
						var message = json.message;
						var statusCode = json.statusCode;
						if(parseInt(statusCode)==300){
							console.error("statusCode is 300");
						}else if(parseInt(statusCode)==200){
							console.log("statusCode is 200");
							jQuery("#"+contentId).load(loadUrl);
						}
					}else{
						console.error("json is null");
					}
			 }else{
				 	console.error("data is null");
			}
	}
});

function assignParam(formId,objectId){
	$.each($('#'+formId+' :input'),function(){
		$(this).val($('#'+$(this).attr('id')+'_'+objectId).text());
	});
}

function checkAllBox(id){
	if($("#"+id).prop("checked")){
		$(":checkbox").each(function(){
			$(this).prop("checked",true);			
		});
	}else{
		$(":checkbox").each(function(){
			$(this).prop("checked",false);			
		});
	}
}

function validateCheckbox(msg){
	var isOk = false;
	$(":checkbox").each(function(){
		if($(this).prop("checked")==true){
			isOk = true;
			return isOk;
		}			
	});
	if(!isOk){
		bootbox.alert(msg);	
	}
	return isOk;
}

//鏅�ajax
function aJaxPost(urlStr,valueStr){
	 var	rData = $.ajax({
			url:urlStr ,
			data:valueStr,
			type:"post",
			async:false,
			success:function(data){
			},	
			cache:false, 
		ifModified :true 
		}).responseText;
	 return rData;
}

jQuery.fn.valuechange = function(fn) {     
    return this.bind('valuechange', fn);  
};
jQuery.event.special.valuechange = {        
    setup: function() {         
       jQuery(this).watch('value', function(){  
            jQuery.event.handle.call(this, {type:'valuechange'});  
        });        
     },
     teardown: function() {   
       jQuery(this).unwatch('value'); 
     } 
};
/*
*名称:图片上传本地预览插件 v1.1
*作者:周祥
*时间:2013年11月26日
*介绍:基于JQUERY扩展,图片上传预览插件 目前兼容浏览器(IE 谷歌 火狐) 不支持safari
*参数说明: Img:图片ID;Width:预览宽度;Height:预览高度;ImgType:支持文件类型;Callback:选择文件显示图片后回调方法;
*使用方法: 
*<div>
*<img id="ImgPr" width="120" height="120" /></div>
*<input type="file" id="up" />
*把需要进行预览的IMG标签外 套一个DIV 然后给上传控件ID给予uploadPreview事件
*$("#up").uploadPreview({ Img: "ImgPr", Width: 120, Height: 120, ImgType: ["gif", "jpeg", "jpg", "bmp", "png"], Callback: function () { }});
*@author wuwentao
*@author http://www.cnblogs.com/leejersey/p/3660202.html
*/
jQuery.fn.extend({
    uploadPreview: function (opts) {
        var _self = this,
            _this = $(this);
        opts = jQuery.extend({
            Img: "ImgPr",
            Width: 100,
            Height: 100,
            ImgType: ["JPG", "PNG", "BMP", "GIF", "JPEG"],
            Callback: function () {}
        }, opts || {});
        _self.getObjectURL = function (file) {
            var url = null;
            if (window.createObjectURL != undefined) {
                url = window.createObjectURL(file)
            } else if (window.URL != undefined) {
                url = window.URL.createObjectURL(file)
            } else if (window.webkitURL != undefined) {
                url = window.webkitURL.createObjectURL(file)
            }
            return url
        };
        _this.change(function () {
            if (this.value) {
                if (!RegExp("\.(" + opts.ImgType.join("|") + ")$", "i").test(this.value.toLowerCase())) {
                    alert("选择文件错误,图片类型必须是" + opts.ImgType.join("，") + "中的一种");
                    this.value = "";
                    return false
                }
                if ($.support.msie) {
                    try {
                        $("#" + opts.Img).attr('src', _self.getObjectURL(this.files[0]))
                    } catch (e) {
                        var src = "";
                        var obj = $("#" + opts.Img);
                        var div = obj.parent("div")[0];
                        _self.select();
                        if (top != self) {
                            window.parent.document.body.focus()
                        } else {
                            _self.blur()
                        }
                        src = document.selection.createRange().text;
                        document.selection.empty();
                        obj.hide();
                        obj.parent("div").css({
                            'filter': 'progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)',
                            'width': opts.Width + 'px',
                            'height': opts.Height + 'px'
                        });
                        div.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = src
                    }
                } else {
                    $("#" + opts.Img).attr('src', _self.getObjectURL(this.files[0]))
                }
                opts.Callback()
            }
        })
    }
});