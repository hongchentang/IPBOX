package com.hcis.ipr.wechat.utils;

import org.springframework.stereotype.Component;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.hcis.ipanther.core.utils.AppConfig;
/**
 * 提供接口调用需要获取accessToken 和 jsApiTicket 的具体实现以及获取公众号的配置信息
 * @author wuwentao
 * @date 2016年6月13日
 */
@Component("apiConfig")
public class ApiConfigExt extends ApiConfig {

	@Override
	public String getAccessToken() {
		return AccessTokenUtils.getAccessToken();
	}

	@Override
	public String getJsApiTicket() {
		return AccessTokenUtils.getJsApiTicket();
	}

	@Override
	public String getAppid() {
		return AppConfig.getProperty("wechat","wechat.appid");
	}

	@Override
	public String getSecret() {
		return AppConfig.getProperty("wechat","wechat.secret");
	}

	@Override
	public String getToken() {
		return AppConfig.getProperty("wechat","wechat.token");
	}

	@Override
	public String getAESKey() {
		return AppConfig.getProperty("wechat","wechat.aeskey");
	}
	
}
