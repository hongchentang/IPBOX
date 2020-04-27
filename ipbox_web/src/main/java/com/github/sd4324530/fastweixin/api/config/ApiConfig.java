package com.github.sd4324530.fastweixin.api.config;


/**
 * 覆盖原作者的代码，原因是：
 * 1,原ApiConfig无法满足集群环境下accessToken共享的需求；
 * 2,原ApiConfig被定义为final的，无法继承，无法自定义实现；
 * 
 * 这里为将ApiConfig改为抽象类，具体实现逻辑通过继承该类来实现
 * 增加一些公众号配置信息获取方法
 * @author wuwentao
 * @date 2016年6月13日
 */
public abstract class ApiConfig {
	
	/**
	 * 获取accessToken
	 * @return
	 */
    public abstract String getAccessToken();

    /**
     * 获取jsApiTicket
     * @return
     */
    public abstract String getJsApiTicket();

    /**
     * 获取公众号的appid
     * @return
     */
	public abstract String getAppid();

	/**
	 * 获取公众号的secret
	 * @return
	 */
	public abstract String getSecret();
	
	/**
	 * 获取公众号的token
	 * @return
	 */
	public abstract String getToken();
	
	/**
	 * 获取公众号的aeskey
	 * @return
	 */
	public abstract String getAESKey();

}
