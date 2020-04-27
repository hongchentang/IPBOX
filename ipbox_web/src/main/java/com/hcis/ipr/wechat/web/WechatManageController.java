package com.hcis.ipr.wechat.web;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hcis.ipr.core.web.controller.BaseController;
import com.hcis.ipr.wechat.service.IWechatService;
import com.hcis.ipr.wechat.utils.AccessTokenUtils;
import com.hcis.ipr.wechat.utils.ResponseUtils;
/**
 * 微信后台管理相关请求
 * @author wuwentao
 * @date 2016年10月21日
 */
@Controller
@RequestMapping("/manage/wechat/")
public class WechatManageController extends BaseController {
	
	@Autowired
	private IWechatService weixinService;
	
	/**
	 * 手动去刷新accessToken
	 * @param response
	 */
	@RequestMapping("refreshAccessToken")
	public void refreshAccessToken(HttpServletResponse response) {
		String accessToken = AccessTokenUtils.refreshAccessToken();
		ResponseUtils.renderText(accessToken, response);
	}
	
}
