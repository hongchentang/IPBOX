package com.hcis.ipr.wechat.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipr.common.utils.ApiCode;
import com.hcis.ipr.common.utils.BaseApi;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;
import com.google.common.collect.Lists;
import com.hcis.ipanther.core.utils.AppConfig;
import com.hcis.ipr.wechat.utils.WechatConstants;
import com.hcis.ipr.wechat.utils.WechatUtils;
/**
 * 微信端请求相关权限、登录控制
 * @author wuwentao
 * @date 2016年10月21日
 */
public class WechatSecurityFilter implements Filter{
	
	private static final Logger logger=LoggerFactory.getLogger(WechatSecurityFilter.class);

	/**
	 * 接口请求
	 */
	private String apiUri;
	/**
	 * 绑定账号请求
	 */
	private String bindUri;
	/**
	 * 登录请求
	 */
	private String loginUri;
	/**
	 *
	 */
	private String vCode;
	/**
	 *
	 */
	private String register;
	/**
	 * 无需权限过滤的请求
	 */
	private List<String> ignoreUris;
	/**
	 * 上一次读取过的微信code，防止重复使用这个code导致报错
	 */
	private String preWechatCode="";
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		apiUri = AppConfig.getProperty("wechat", "wechat.uri.api");
		bindUri = AppConfig.getProperty("wechat", "wechat.uri.bind");
		loginUri = AppConfig.getProperty("wechat", "wechat.uri.login");
		vCode = AppConfig.getProperty("wechat", "wechat.uri.code");
		register = AppConfig.getProperty("wechat", "wechat.uri.register");
		ignoreUris = Lists.newArrayList(apiUri, bindUri, loginUri, vCode, register);
		ignoreUris.addAll(Arrays.asList(AppConfig.getProperty("wechat", "wechat.uri.ignore").split(",")));
	}

	@Override
	public void doFilter(ServletRequest _request, ServletResponse _response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) _request;
		HttpServletResponse response = (HttpServletResponse) _response;

		//刷新Session信息
		this.refreshSessionWechatInfo(request, response);

		String uri = request.getRequestURI().toString();
		String url = request.getRequestURL().toString();
		logger.debug("---------------------");
		logger.debug("uri=" + uri);
		logger.debug("url=" + url);
		logger.debug("---------------------");

		//因在jsp中request.getRequestUrl获取的是jsp的路径，因此在在这里存一下当前请求路径，一边在jssdk中获取当前路径进行
		request.setAttribute(WechatConstants.CURRENT_REQUEST_URL, url);
		Object loginUser = WechatUtils.getLoginUser(request);
		if (!ignoreUris.contains(uri) && null == loginUser) {
			//请求要求登录且没有登录，跳转到登录页面

			//记住请求地址，登录后直接访问
			request.getSession().setAttribute(WechatConstants.LAST_URL_BEFORE_LOGIN, url);

			_response.setCharacterEncoding("utf-8");
			_response.setContentType("application/json; charset=utf-8");
			PrintWriter writer = _response.getWriter();

			BaseApi api = new BaseApi();
			api.setCode(ApiCode.WE_UN_LOGIN);
			api.setMsg("用户未登录或者登录已失效");
			writer.write(JsonUtil.toJson(api));
			writer.close();
		} else {
			chain.doFilter(request, response);
		}
	}
	
	/**
	 * 刷新Session中的微信用户信息，以后不在任何地方独立读取微信用户信息
	 * 同一个Code不能重复获取数据，如果Code相同，则不再获取
	 * 判断上一次调用的Code和Session中的Code
	 * @param request
	 */
	private void refreshSessionWechatInfo(HttpServletRequest request, HttpServletResponse response){
		//如果有授权信息，则直接获取，然后保存到session
		if(StringUtils.isNotBlank(request.getParameter("code"))){
			boolean doRefresh=false;
			//对比Code
			String requestCode=request.getParameter("code");
//			logger.info("session:"+request.getSession().getId()+" code:"+requestCode);
			if(request.getSession().getAttribute(WechatConstants.WEIXIN_USER_CODE)!=null){
				String sessionCode=(String) request.getSession().getAttribute(WechatConstants.WEIXIN_USER_CODE);
				if(!StringUtils.equals(sessionCode, requestCode) && !StringUtils.equals(preWechatCode, requestCode)){
					doRefresh=true;
				}
			}
			else{
				doRefresh=true;
			}
			if(doRefresh){
				try{
					GetUserInfoResponse wechatUserInfo = WechatUtils.getUserInfo(requestCode);
					request.getSession().setAttribute(WechatConstants.WEIXIN_USER, wechatUserInfo);
					request.getSession().setAttribute(WechatConstants.WEIXIN_USER_CODE, requestCode);
					preWechatCode=requestCode;
				}
				catch(Exception e){
					logger.error(e.getMessage());
				}
			}
		}
	}

	@Override
	public void destroy() {
		
	}

}
