/**
 * 微信端常用的一些函数
 */
$.extend({
	/**
	 * 用post方式提交formId
	 * @param  {[type]} formId     [提交的form 的ID]
	 * @param  {[type]} loadingMsg [加载文字，可为空]
	 * @return {[type]}            [返回response对象]
	 */
	ajaxSubmit:function(formId,loadingMsg){
		$.showLoading(loadingMsg);
		var responseData = $.ajax({
			url:$("#"+formId).attr("action"),
			data:$("#"+formId).serialize(),
			type:"post",
			async:false,
			success:function(data){
				
			}
		}).responseText;
		$.hideLoading();
		return $.myParseJSON(responseData);
	},
	/**
	 * 解析json，一些浏览器返回json字符串时会自动解析为json对象，如火狐、谷歌及IE10以上，另一些则不会自动解析，如IE9及以下[myParseJSON description]
	 * @param  {[type]} json [description]
	 * @return {[type]}      [description]
	 */
	myParseJSON:function(json) {
		if(typeof json=="string") {//如果是字符串，则解析为json对象返回回去
    		json = $.parseJSON(json);
    	}
		return json;
	},
	/**
	 * 跳转到URL
	 */
	href:function(url) {
		$.showLoading();
		window.location.href=url;
	}
});
