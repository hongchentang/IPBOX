package com.hcis.ipr.alipay.utils;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.hcis.ipr.alipay.config.AlipayConfig;
import com.hcis.ipr.intellectual.cost.entity.PatentCost;

/**
 * @date 2019/10/21
 **/
public class AlipayUtils {

	public String connect(PatentCost cost) throws AlipayApiException {
		// 初始化Alipay客户端
		AlipayClient client = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, AlipayConfig.format, AlipayConfig.charset,
				AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

		// 设置请求参数
		AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
		// 同步通知页面
		request.setReturnUrl(AlipayConfig.return_url);
		// 异步通知页面
		request.setNotifyUrl(AlipayConfig.notify_url);
		// 封装参数

		Map<String, Object> map = new HashMap<>();
		map.put("out_trade_no", cost.getId());
		map.put("product_code", "FAST_INSTANT_TRADE_PAY");
		map.put("total_amount", cost.getFeePayable().doubleValue());
		map.put("subject", cost.getCostName());
		map.put("body", cost.getCostRemark());
		map.put("timeout_express", "5m");

		request.setBizContent(JSON.toJSONString(map));

		// 请求支付宝进行付款，并获取支付结果

		return client.pageExecute(request).getBody();

	}

	public AlipayTradeQueryResponse query(PatentCost cost) throws AlipayApiException {
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
				AlipayConfig.merchant_private_key, AlipayConfig.format, AlipayConfig.charset,
				AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();

		Map<String, Object> map = new HashMap<>();
		map.put("out_trade_no", cost.getId());

		request.setBizContent(JSON.toJSONString(map));
		return alipayClient.execute(request);
	}

	public synchronized  String alipayRefundRequest(String trade_no, String out_trade_no, double refund_amount) {
		String returnStr = null;
		String out_request_no = "BZ35581R88001";// 随机数 不是全额退款，部分退款使用该参数
		try {
			// 初始化Alipay客户端
			AlipayClient client = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
					AlipayConfig.merchant_private_key, AlipayConfig.format, AlipayConfig.charset,
					AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

			AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
			request.setBizContent("{"
					+ "\"out_trade_no\":\""+out_trade_no+"\","
					+ "\"trade_no\":\""+trade_no+"\","
					+ "\"refund_amount\":\""+refund_amount+"\","
					+"\"out_request_no\":\""+out_request_no+"\","
					+ "\"refund_reason\":\"正常退款\"" + " }");
			AlipayTradeRefundResponse response;
			response = client.execute(request);

			if (response.isSuccess()) {
				//System.out.println("支付宝退款成功");
				returnStr="SUCCESS";
			} else {
				returnStr = response.getSubMsg();// 失败会返回错误信息
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnStr;
	}
	/**
	 * 支付宝退款查询接口
	 * @author Mark
	 * @param out_trade_no
	 *            订单支付时传入的商户订单号,不能和支付宝交易号（trade_no）同时为空。
	 * @param trade_no
	 *            支付宝交易号
	 *            需要退款的金额，该金额不能大于订单金额,单位为元，支持两位小数
	 * @return 将提示信息返回
	 */
	public synchronized static String alipayRefundQueryRequest(String out_trade_no, String trade_no) {
		String returnStr = null;
		try {
			// 初始化Alipay客户端
			AlipayClient client = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
					AlipayConfig.merchant_private_key, AlipayConfig.format, AlipayConfig.charset,
					AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
			AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
			request.setBizContent("{"
					+ "\"out_trade_no\":\""+out_trade_no+"\","
					+ "\"trade_no\":\""+trade_no+"\","
					+"\"out_request_no\":\""+out_trade_no+"\""
					+ " }");
			AlipayTradeFastpayRefundQueryResponse response = client.execute(request);
			if (response.isSuccess()) {
				returnStr="SUCCESS";
				// System.out.println("退款成功");
			} else {
				returnStr = response.getSubMsg();// 失败会返回错误信息
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnStr;
	}



}
