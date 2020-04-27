package com.hcis.ipr.wechat.utils;

import org.apache.http.HttpStatus;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.github.sd4324530.fastweixin.api.response.GetJsApiTicketResponse;
import com.github.sd4324530.fastweixin.api.response.GetTokenResponse;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import com.github.sd4324530.fastweixin.util.NetWorkCenter;
import com.hcis.ipanther.core.utils.AppConfig;
import com.hcis.ipanther.core.utils.BeanLocator;
import com.hcis.ipanther.core.utils.RedisUtils;

/**
 * accessToken和JsApiTicket相关工具类
 * 考虑到项目是部署集群环境下的，这里不能直接使用 com.github.sd4324530.fastweixin.api.config.ApiConfig 刷新accessToken
 * 用quarz（集群）定时刷新 accessToken和jsApiTicket，将刷新成功后的accessToken、jsApiTicket存储于redis中，并设定expire。
 * 如定时器刷新失败，在redis失效后，会再去刷新
 * 如刷新连续失败10次，则抛出异常
 * 刷新jsApiTicket需要用到accessToken，会去刷新accessToken
 * @author wuwentao
 * @date 2016年6月7日
 */
public class AccessTokenUtils extends QuartzJobBean {
	
	protected static final Logger logger = LoggerFactory.getLogger(AccessTokenUtils.class);
	
	private static String accessToken;
	private static String jsApiTicket;
	
	/**
	 * 定时器 定时去刷新accessToken和jsApiTicket
	 * 刷新jsApiTicket需要用到accessToken，会去刷新accessToken
	 */
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		logger.info("AccessTokenUtils Job start");
		AccessTokenUtils.refreshJsApiTicket();
		logger.info("AccessTokenUtils Job end");
	}
	
	/**
	 * 获得accessToken
	 * 如果无法获取到accessToken（定时器跑失败的时候），则直接去刷新
	 * @return
	 */
	public static String getAccessToken() {
		RedisUtils redisUtils = new RedisUtils();
		String accessToken = redisUtils.get(AppConfig.getProperty("wechat","wechat.accessToken.storageKey"));
		if(null==accessToken) {
			accessToken = AccessTokenUtils.refreshAccessToken();
		}
		return accessToken;
	}

	/**
	 * 刷新accessToken
	 * 如连续刷新10次均失败，则跑出异常
	 */
	public static synchronized String refreshAccessToken() throws RuntimeException {
		logger.info("refreshAccessToken start");
		accessToken = null;
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + AppConfig.getProperty("wechat","wechat.appid") + "&secret=" + AppConfig.getProperty("wechat","wechat.secret");
		int count = 0;
		while(null==accessToken) {
			if(count>10) {
				throw new RuntimeException("无法获取到accessToken");
			}
			count ++;
			NetWorkCenter.get(url, null, new NetWorkCenter.ResponseCallback() {
	            @Override
	            public void onResponse(int resultCode, String resultJson) {
	                if (HttpStatus.SC_OK == resultCode) {
	                    GetTokenResponse response = JSONUtil.toBean(resultJson, GetTokenResponse.class);
	                    accessToken = response.getAccessToken();
	                    if(null!=accessToken) {//刷新成功，才往redis中存储
	                    	RedisUtils redisUtils = (RedisUtils) BeanLocator.getBean("redisUtils");
	                        redisUtils.addOrUpdate(AppConfig.getProperty("wechat","wechat.accessToken.storageKey"), accessToken,new Long(7100*1000));//官方给出的有效时间是7200秒,这里提前100秒
	                    }
	                }
	            }
	        });
		}
		logger.info("refreshAccessToken end");
        return accessToken;
	}
	
	//----------------------------------------------------------------
	
	/**
	 * 获得jsApiTicket
	 * 如果无法获取到jsApiTicket（定时器跑失败的时候），则直接去刷新
	 * @return
	 */
	public static String getJsApiTicket() {
		RedisUtils redisUtils = new RedisUtils();
		String jsApiTicket = redisUtils.get(AppConfig.getProperty("wechat","wechat.jsApiTicket.storageKey"));
		if(null==jsApiTicket) {
			jsApiTicket = AccessTokenUtils.refreshJsApiTicket();
		}
		return jsApiTicket;
	}
	
	/**
	 * 刷新jsApiTicket
	 * 如连续刷新10次均失败，则跑出异常
	 */
	public static synchronized String refreshJsApiTicket() throws RuntimeException {
		logger.info("refreshJsApiTicket start");
		jsApiTicket = null;
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + getAccessToken() + "&type=jsapi";
		int count = 0;
		while(null==jsApiTicket) {
			if(count>10) {
				throw new RuntimeException("无法获取到jsApiTicket");
			}
			count ++;
			NetWorkCenter.get(url, null, new NetWorkCenter.ResponseCallback() {
	            @Override
	            public void onResponse(int resultCode, String resultJson) {
	                if (HttpStatus.SC_OK == resultCode) {
	                	GetJsApiTicketResponse response = JSONUtil.toBean(resultJson, GetJsApiTicketResponse.class);
	                	jsApiTicket = response.getTicket();
	                    if(null!=jsApiTicket) {
	                    	//刷新成功，才往redis中存储
	                    	RedisUtils redisUtils = (RedisUtils) BeanLocator.getBean("redisUtils");
							//官方给出的有效时间是7200秒,这里提前100秒
	                        redisUtils.addOrUpdate(AppConfig.getProperty("wechat","wechat.jsApiTicket.storageKey"), jsApiTicket,new Long(7100*1000));
	                    }
	                }
	            }
	        });
		}
		logger.info("refreshJsApiTicket end");
        return jsApiTicket;
	}

}
