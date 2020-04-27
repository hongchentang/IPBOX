<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/10/22
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>PAY</title>
</head>
<body>
  ${reStr}
  
  
 <form name="punchout_form" method="post" action="https://openapi.alipaydev.com/gateway.do?charset=utf-8&method=alipay.trade.page.pay&sign=EKBBZeZLqNBnneLdmm5%2BdQxGMnxPb%2B8lmUCAxjseVFCh4cmu%2Fi2UlVWuCKauElQEc53fHrevfeja358QYPYM%2Frty2%2FcmB2TJhtji4RYBL9qKXYp0dMoLjWOqPMFAM6VcdFq255uJUt8xShroLslGoTecxWOTb80SwfQ9bDZ2YfaO9PrJ8pGrlTORxJY38Q9flKJDt6xD6U7GpKViO%2BLrQWiafod1kSEmoRvlO5gGZhQeVC%2FdkceALb2d2uH8z%2FXiiOYc7RrwbnFW4Hfbl2QNWYKQv%2FcF%2FSfF5GBL2MLhm2Bga%2F2h3CVwfPQpNvnpEC%2BFpkteqRQQfravjBAZP1sJeQ%3D%3D&return_url=http%3A%2F%2Fx2739277k1.wicp.vip%2Falipay%2Fsuccess.do&notify_url=http%3A%2F%2Fx2739277k1.wicp.vip%2Falipay%2Fsuccess.do&version=1.0&app_id=2016101200666145&sign_type=RSA2&timestamp=2019-10-23+16%3A33%3A10&alipay_sdk=alipay-sdk-java-4.6.0.ALL&format=JSON">
<input type="hidden" name="biz_content" value="{&quot;out_trade_no&quot;:&quot;bb54a3b5c9bc4224a4f04ee6369a7c51&quot;,&quot;total_amount&quot;:900.0,&quot;subject&quot;:&quot;申请费&quot;,&quot;timeout_express&quot;:&quot;1m&quot;,&quot;product_code&quot;:&quot;FAST_INSTANT_TRADE_PAY&quot;}">
<input type="submit" value="立即支付" style="display:none" >
</form>
<script>document.forms[0].submit();</script>
</body>
</html>
