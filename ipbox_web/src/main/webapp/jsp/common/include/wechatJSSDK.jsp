<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/jsp/common/include/wechatTaglib.jsp"%>
<script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<c:set var="wxConfig" value="${wechat:getJsSDKConfig(pageContext.request)}"/>
<script type="text/javascript">
wx.config({
    debug: false, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
    appId: "${ipanthercore:getProperty('wechat','wechat.appid')}", // 必填，公众号的唯一标识
    timestamp: ${wxConfig.timestamp}, // 必填，生成签名的时间戳
    nonceStr: '${wxConfig.noncestr}', // 必填，生成签名的随机串
    signature: '${wxConfig.signature}',// 必填，签名，见附录1
    jsApiList: [${param.jsApiList}] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
});
wx.ready(function(){
    // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready函数中。
    
	//判断当前客户端版本是否支持指定JS接口
	wx.checkJsApi({
	    jsApiList: [${param.jsApiList}], // 需要检测的JS接口列表，所有JS接口列表见附录2,
	    success: function(res) {
	        // 以键值对的形式返回，可用的api值true，不可用为false
	        // 如：{"checkResult":{"chooseImage":true},"errMsg":"checkJsApi:ok"}
	        var errorMsg = "";
	        var apiList = [${param.jsApiList}];
	        for(var i = 0;i<apiList.length;i++) {
	        	if(!res.checkResult[apiList[i]]) {
		        	errorMsg += ","+apiList[i];
	        	}
	        }
	        if(errorMsg.length>0) {
	        	$.alert("您的设备不支持接口"+errorMsg.substr(1)+"，可能无法正常使用该功能。如有疑问，请联系客服！")
	        }
	    }
	});
});
wx.error(function(res){
    // config信息验证失败会执行error函数，如签名过期导致验证失败，具体错误信息可以打开config的debug模式查看，也可以在返回的res参数中查看，对于SPA可以在这里更新签名。
    
});
</script>