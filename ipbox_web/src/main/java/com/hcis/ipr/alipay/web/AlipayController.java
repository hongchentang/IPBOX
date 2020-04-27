package com.hcis.ipr.alipay.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipr.common.utils.ApiCode;
import com.hcis.ipr.common.utils.BaseApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.api.AlipayApiException;
import com.hcis.ipr.alipay.service.impl.AlipayService;
import com.hcis.ipr.alipay.utils.AlipayUtils;
import com.hcis.ipr.core.web.controller.BaseController;
import com.hcis.ipr.intellectual.cost.entity.PatentCost;
import com.hcis.ipr.intellectual.cost.service.PatentCostService;

/**
 *
 **/
@RequestMapping("/alipay")
@Controller
public class AlipayController extends BaseController {

	@Resource
	private PatentCostService patentCostService;

	@Resource
	private AlipayService alipayService;

	@RequestMapping("/pay")
	public ModelAndView pay(String id) {
		ModelAndView view = new ModelAndView("/alipay/myPay");
		PatentCost cost = patentCostService.read(id);
		String reStr = "";
		if (cost != null) {
			AlipayUtils utils = new AlipayUtils();
			try {
				reStr = utils.connect(cost);
			} catch (AlipayApiException e) {
				e.printStackTrace();
			}
		}

		view.addObject("reStr", reStr);
		return view;
	}

	@RequestMapping("/success")
	public ModelAndView success(HttpServletRequest request) {
		try {
			alipayService.payCallback(request);
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return new ModelAndView("/alipay/success");
	}
	@RequestMapping("/retu")
	@ResponseBody
	public BaseApi retu(String id) {
		BaseApi api = new BaseApi();
		ModelAndView view = new ModelAndView("/alipay/myPay");
		PatentCost cost = patentCostService.read(id);
		String reStr = "";
		if (cost != null) {
			AlipayUtils utils = new AlipayUtils();
			/*reStr = utils.connect(cost);*/

				reStr=utils.alipayRefundRequest("2020040722001471240511980099","202047161525101",2);
				if(reStr.equals("SUCCESS")){
					api.setCode(ApiCode.SUCCESS);
				}else {
					api.setCode(ApiCode.ERROR);
					api.setMsg(reStr);
				}


		}

		return api;
	}
}
