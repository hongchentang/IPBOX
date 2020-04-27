package com.hcis.ipr.wechat.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.entity.WechatUser;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.common.user.service.WechatUserService;
import com.hcis.ipanther.core.utils.UUIDUtils;
import com.hcis.ipr.common.utils.ApiCode;
import com.hcis.ipr.common.utils.BaseApi;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;
import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.TextMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import com.github.sd4324530.fastweixin.message.req.BaseReqMsg;
import com.github.sd4324530.fastweixin.message.req.LocationEvent;
import com.github.sd4324530.fastweixin.message.req.MenuEvent;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinControllerSupport;
import com.hcis.ipanther.common.login.service.ILoginService;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipr.wechat.service.IWechatService;
import com.hcis.ipr.wechat.utils.ResponseUtils;
import com.hcis.ipr.wechat.utils.WechatConstants;
import com.hcis.ipr.wechat.utils.WechatUtils;

/**
 * 微信相关请求 微信消息验证的入口，接收服务器发送过来的请求 一些基本的操作，如账号绑定登录的相关请求
 * 
 * @author wuwentao
 * @date 2016年6月7日
 */
@Controller
@RequestMapping("/wechat/")
public class WechatController extends WeixinControllerSupport {

	private static final Logger LOG = LoggerFactory.getLogger(WechatController.class);

	@Autowired
	private ILoginService loginService;
	@Autowired
	private ApiConfig apiConfig;
	@Autowired
	private IWechatService wechatService;
	@Autowired
	private IUserService userService;
	@Autowired
	private WechatUserService wechatUserService;

	@Override
	protected String getToken() {
		return apiConfig.getToken();
	}

	@Override
	protected String getAppId() {
		return apiConfig.getAppid();
	}

	@Override
	protected String getAESKey() {
		return apiConfig.getAESKey();
	}

	/**
	 * 接口调用get请求
	 * 
	 * 用于绑定微信服务器
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "api", method = RequestMethod.GET)
	public void getApi(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ResponseUtils.renderText(super.bind(request), response);
	}

	/**
	 * 接口调用post请求
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "api", method = RequestMethod.POST)
	public void postApi(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		super.process(request, response);
	}

	/**
	 * 关注微信号
	 */
	@Override
	public BaseMsg handleSubscribe(BaseEvent event) {
		return new TextMsg("感谢关注广东省知识产权人才培训服务号。");
	}

	/**
	 * 不再关注微信号 对账号进行解绑
	 */
	@Override
	public BaseMsg handleUnsubscribe(BaseEvent event) {
		wechatService.unbind(event.getFromUserName());
		return null;
	}

	/**
	 * 默认的消息处理
	 */
	@Override
	public BaseMsg handleDefaultMsg(BaseReqMsg msg) {
		return new TextMsg("系统无法识别您发送的信息。如需帮助，请输入help/微笑");
	}

	/**
	 * 默认的菜单点击事件处理
	 */
	@Override
	protected BaseMsg handleDefaultEvent(BaseEvent event) {
		return new TextMsg("系统暂时无法处理该点击事件。如需帮助，请输入help/微笑");
	}

	/**
	 * 对文本消息回复 对应自动回复的功能
	 */
	@Override
	public BaseMsg handleTextMsg(TextReqMsg msg) {
		return null;
	}

	/**
	 * 对菜单点击事件进行处理
	 */
	@Override
	public BaseMsg handleMenuClickEvent(MenuEvent event) {
		return null;
	}

	/**
	 * 处理地理位置事件 不对用户作出任何响应
	 */
	@Override
	protected BaseMsg handleLocationEvent(LocationEvent event) {
		return null;
	}

	/**
	 * 首页
	 * 
	 * @param code
	 * @param state
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping("index")
	public ModelAndView index(String code, String state, HttpServletRequest request) throws IOException, ServletException {
		ModelAndView mv = new ModelAndView();
		// mv.setViewName("redirect:http://www.zsjsjy.com/building.html");
		return mv;
	}

	/**
	 * 执行登录 根据微信号进行登录，如果该微信号未绑定到任何账户上，则跳转到绑定界面
	 * 
	 * @param code
	 * @param state
	 * @param request
	 * @return
	 */
	@RequestMapping("login")
	public ModelAndView login(String code, String state, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		//获取session微信信息
		GetUserInfoResponse wechatUserInfo = (GetUserInfoResponse)request.getSession().getAttribute(WechatConstants.WEIXIN_USER);
		if (wechatUserInfo!=null && wechatService.login(wechatUserInfo.getOpenid(), request)) {
			// 登录成功进入首页
			String url = (String) request.getSession().getAttribute(WechatConstants.LAST_URL_BEFORE_LOGIN);
			if (StringUtils.isNotEmpty(url)) {
				mv.setViewName("redirect:" + url);
				request.getSession().removeAttribute(WechatConstants.LAST_URL_BEFORE_LOGIN);
			}
			else {
				//没有保存要直接转向的页面，则返回到首页
				 mv.setViewName("redirect:/wechat/index.do");
			}
		}
		else {
			// 跳转到账号绑定页面
			mv.setViewName("redirect:" + WechatUtils.getBindUrl());
		}
		return mv;
	}

	@RequestMapping("/loginV2")
	@ResponseBody
	public BaseApi loginV2(@RequestBody Map<String, Object> map, HttpServletRequest request){
		BaseApi api = new BaseApi();
		HttpSession session = request.getSession();
		try {
			GetUserInfoResponse weChatUserInfo = (GetUserInfoResponse)session.getAttribute(WechatConstants.WEIXIN_USER);
			if(weChatUserInfo == null){
				//未登录
				//获取用户信息
				GetUserInfoResponse userInfoResponse = loginService.code2Session((String) map.get("code"));
				String openId = userInfoResponse.getOpenid();
				//后台登录，
				WechatUser user = wechatUserService.getByOpenId(openId);
				String nickName = (String) map.get("nickName");
				if(user != null && StringUtils.isBlank(user.getPhone())){
					//存在的用户,但是没有关联
					user.setNickName(nickName);
					api.setCode(ApiCode.WE_CHAT_UNREGISTER);
					api.setMsg("用户未注册");
					// TODO: 2019/11/13  
					
				}else if(user == null) {
					//不存在的用户存起来
					user = new WechatUser();
					user.setId(UUIDUtils.getUUId());
					user.setNickName(nickName);
					user.setOpenId(openId);
					user.setStatus(0);
					user.setDefaultValue();

					api.setCode(ApiCode.WE_CHAT_UNREGISTER);
					api.setMsg("用户未注册");
					wechatUserService.save(user);
				}

				wechatService.login(openId, request);

				//不给前端openId
				user.setOpenId(null);
				//返回结果
				LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
				Map<String, Object> resultMap = new HashMap<>(2);
				resultMap.put("sessionKey", session.getId());
				resultMap.put("user", user);
				resultMap.put("loginUser", loginUser);
				api.setData(resultMap);
				//保存session数据
				session.setAttribute(WechatConstants.WEIXIN_USER, userInfoResponse);
			}
		}catch (Exception e){
			e.printStackTrace();
			api.setCode(ApiCode.ERROR);
			api.setMsg(e.getMessage());
		}
		return api;
	}

	/**
	 * 跳转到账户绑定的页面
	 * 
	 * @param code
	 * @param state
	 * @return
	 */
	@RequestMapping(value = "bind", method = RequestMethod.GET)
	public ModelAndView bindGet(String code, String state,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		boolean binded = false;// 是否已经绑定
		GetUserInfoResponse wechatUserInfo = (GetUserInfoResponse)request.getSession().getAttribute(WechatConstants.WEIXIN_USER);
//		String wechatId = wechatUserInfo.getOpenid();
		//由于直接是判断过有没有绑定，才来到这个页面的，所以无需再次检查是否已绑定
		
//		LoginUser loginUser = loginService.getLoginUserByWechatId(wechatId);
//		if (null != loginUser) {// 已经绑定的用户，提示用户后需要跳转到登录界面进行登录
//			binded = true;
//		}
		mv.addObject("binded", binded);
		mv.addObject("loginUrl", WechatUtils.getLoginUrl());
//		mv.addObject("loginUser", loginUser);
		mv.addObject("wechatUserInfo", wechatUserInfo);
		return mv;
	}

	/**
	 * 执行账号绑定
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "bind", method = RequestMethod.POST)
	@ResponseBody
	public Response bindPost(String account, String password,HttpServletRequest request) {
		String message="";
		GetUserInfoResponse wechatUserInfo = (GetUserInfoResponse)request.getSession().getAttribute(WechatConstants.WEIXIN_USER);
		if(wechatUserInfo!=null){
			User user=new User();
			user.setWechatId(wechatUserInfo.getOpenid());
			user.setWechatNickname(wechatUserInfo.getNickname());
			message = wechatService.bind(user, account, password);
			if (StringUtils.isEmpty(message)) {
				return Response.successInstance();
			}
		}
		else{
			message="没有获得授权信息！";
		}
		return Response.failInstance().responseMsg(message);
	}

	@RequestMapping("/sendMsg4Register")
	@ResponseBody
	public BaseApi sendMsg4Register(HttpServletRequest request){
		BaseApi api = new BaseApi();
		try {
			request.getSession().setAttribute(WechatConstants.VALIDATE_CODE, "2222");
		}catch (Exception e){
			api.setError(e.getMessage());
		}
		return api;
	}

	@RequestMapping("/register")
	@ResponseBody
	public BaseApi register(@RequestBody Map<String, Object> map, HttpServletRequest request){
		BaseApi api = new BaseApi();
		try {

			String validate = (String) request.getSession().getAttribute(WechatConstants.VALIDATE_CODE);
			if(StringUtils.isNotBlank(validate) && validate.equals(map.get("vcode"))){
				//验证通过，写入手机号
				wechatUserService.register(map);
			}else {
				api.setCode(ApiCode.WE_REGISTER_VAILDATE_CODE_DEFAULT);
				api.setMsg("验证码不正确");
			}
		}catch (Exception e){
			api.setError(e.getMessage());
		}
		return api;
	}
	
}
