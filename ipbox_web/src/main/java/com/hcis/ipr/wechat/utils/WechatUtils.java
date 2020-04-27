package com.hcis.ipr.wechat.utils;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sd4324530.fastweixin.api.JsAPI;
import com.github.sd4324530.fastweixin.api.OauthAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.enums.OauthScope;
import com.github.sd4324530.fastweixin.api.response.GetSignatureResponse;
import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;
import com.github.sd4324530.fastweixin.api.response.OauthGetTokenResponse;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.utils.AppConfig;
import com.hcis.ipanther.core.utils.BeanLocator;

/**
 * 
 * @author wuwentao
 * @date 2016年10月24日
 */
public class WechatUtils {
	
	private static final Logger LOG  = LoggerFactory.getLogger(WechatUtils.class);

	private static String bindUrl = null;
	/**
	 * 获得绑定用户的地址
	 * @return
	 */
	public static String getBindUrl() {
		if(null==bindUrl) {
			bindUrl = getOauthPageUrl(getUrl("/wechat/bind.do"));
		}
		System.out.println("bindUrl:"+bindUrl);
		return bindUrl;
	}
	private static String loginUrl = null;
	/**
	 * 获取登录链接地址
	 * @return
	 */
	public static String getLoginUrl() {
		if(null==loginUrl) {
			loginUrl = getOauthPageUrl(getUrl("/wechat/login.do"));
		}
		return loginUrl;
	}
	
	/**
	 * 得到uri的请求全路径
	 * @param uri
	 * @return
	 */
	public static String getUrl(String uri) {
		String domain = AppConfig.getProperty("wechat", "wechat.domain");
		return "http://"+domain+uri;
	}
	
	/**
	 * 生成授权回调页面URL
	 * @param url
	 * @param oauthScope
	 * @return
	 */
	public static String getOauthPageUrl(String url,OauthScope oauthScope) {
		ApiConfig apiConfig = (ApiConfig) BeanLocator.getBean("apiConfig");
		OauthAPI oauthApi = new OauthAPI(apiConfig);
		return oauthApi.getOauthPageUrl(url, oauthScope, null);
	}
	
	/**
	 * SNSAPI_BASE 生成授权回调页面URL
	 * @param url
	 * @return
	 */
	public static String getOauthPageUrl(String url) {
		return getOauthPageUrl(url, OauthScope.SNSAPI_USERINFO);
	}
	
	/**
	 * 得到用户的微信账户信息
	 * 调用该方法时，必须保证请求的Oauth为SNSAPI_USERINFO
	 * @param code
	 * @return
	 */
	public static GetUserInfoResponse getUserInfo(String code) {
		ApiConfig apiConfig = (ApiConfig) BeanLocator.getBean("apiConfig");
		OauthAPI oauthApi = new OauthAPI(apiConfig);
		OauthGetTokenResponse oauthToken = oauthApi.getToken(code);
		GetUserInfoResponse userInfo = oauthApi.getUserInfo(oauthToken.getAccessToken(), oauthToken.getOpenid());
		return userInfo;
	}
	
	/**
	 * 得到用户的微信ID
	 * @param code
	 * @return
	 */
	public static String getWechatId(String code) {
		if(StringUtils.isNotEmpty(code)) {
			ApiConfig apiConfig = (ApiConfig) BeanLocator.getBean("apiConfig");
			OauthAPI oauthApi = new OauthAPI(apiConfig);
			OauthGetTokenResponse oauthToken = oauthApi.getToken(code);
			return oauthToken.getOpenid();
		} else {
			return null;
		}
	}
	
	/**
	 * 获得JsSDK的配置信息
	 * @param request
	 * @return
	 */
	public static GetSignatureResponse getJsSDKConfig(HttpServletRequest request) {
		ApiConfig apiConfig = (ApiConfig) BeanLocator.getBean("apiConfig");
		JsAPI api = new JsAPI(apiConfig);
		//不能通过request.getRequestUrl获取路径，因此在过滤器做了设置
		String url = (String)request.getAttribute(WechatConstants.CURRENT_REQUEST_URL);
		return api.getSignature(url);
	}
	
	/**
	 * 获取到当前登录用户
	 * @param request
	 * @return
	 */
	public static LoginUser getLoginUser(HttpServletRequest request) {
		return (LoginUser)request.getSession().getAttribute(WechatConstants.LOGIN_USER);
	}
}
