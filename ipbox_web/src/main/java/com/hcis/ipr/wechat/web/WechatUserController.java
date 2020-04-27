package com.hcis.ipr.wechat.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;
import com.hcis.ipanther.common.attachment.utils.AttachmentUtils;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.entity.UserStaff;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.common.user.service.IUserStaffService;
import com.hcis.ipanther.common.validate.entity.Validate;
import com.hcis.ipanther.common.validate.service.IValidateService;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipr.wechat.utils.WechatConstants;
import com.hcis.ipr.wechat.utils.WechatUtils;

@Controller
@RequestMapping("/wechat/user")
public class WechatUserController extends BaseController {

	@Autowired
	private IUserService userService;
	@Autowired
	private IUserStaffService userStaffService;
	@Autowired
	private IValidateService validateService;

	//详情
	@RequestMapping("userInfo")
	public ModelAndView userInfo(String id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		LoginUser loginUser = WechatUtils.getLoginUser(request);
		User user = new User();
		UserStaff userStaff = new UserStaff();
		if (loginUser != null) {
			user = this.userService.read(loginUser.getId());
			userStaff = userStaffService.read(loginUser.getId());
		}
		mv.addObject("user", user);
		mv.addObject("userStaff", userStaff);
		return mv;
	}
	
	//注册
	@RequestMapping("register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView();
		GetUserInfoResponse wechatUserInfo = (GetUserInfoResponse)request.getSession().getAttribute(WechatConstants.WEIXIN_USER);
//		mv.addObject("wechatUserInfo",wechatUserInfo);
		if(wechatUserInfo!=null){
			mv.addObject("wechatId",wechatUserInfo.getOpenid());
			mv.addObject("wechatNickname",wechatUserInfo.getNickname());
		}
		mv.addObject("bindUrl",WechatUtils.getBindUrl());
		return mv;
	}
	
	//修改
	@RequestMapping("edit")
	public ModelAndView edit(String id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		LoginUser loginUser = WechatUtils.getLoginUser(request);
		//LoginUser loginUser = LoginUtils.getLoginUser(request);
		User user = new User();
		UserStaff userStaff = new UserStaff();
		if (loginUser != null) {
			user = this.userService.read(loginUser.getId());
			userStaff = userStaffService.read(loginUser.getId());
		}
		mv.addObject("user", user);
		mv.addObject("userStaff", userStaff);
		return mv;
	}
	
	/**
	 * 保存用户的信息
	 * @param user 用户的信息
	 * @param upload 上传头像
	 * @param registerType 注册类型，一般的保存为空，注册为学员为student，注册为教师为teacher
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	@ResponseBody
	public void save(User user,@RequestParam(value="upload",required=false) MultipartFile upload,String registerType,HttpServletResponse response,String checkType,String code) throws Exception {
		/*
		 * 上传个人头像
		 */
		String info = AttachmentUtils.updateAvatar(user, upload, "avatar");
		Response _response = null;
		if(StringUtils.isNotEmpty(info)){
			_response = new Response(300,info);
		} else {
			/*
			 * 校验数据
			 * 用户名/账号/学号 和 身份证件号 唯一性校验
			 */
			SearchParam searchParam = new SearchParam();
			Map<String, Object> paramMap = searchParam.getParamMap();
			paramMap.put("userId", user.getId());
			paramMap.put("userName", user.getUserName());
			paramMap.put("paperworkNo", user.getPaperworkNo());
			
			
			/*
			 * 验证填写的验证码和邮箱/手机是否对应
			 */
			String checkTypeTitle="邮箱";
			Map<String,String> paramMap2 = new HashMap<String,String>();
			if("email".equals(checkType)){//如果传入的checkType为email，说明选择的是邮箱验证
				paramMap2.put("email", user.getEmail());
				checkTypeTitle="邮箱";
			}
			else{
				paramMap2.put("phone", user.getMobilePhone());
				checkTypeTitle="手机";
			}
			
			List<Validate> validateList = this.validateService.selectByMap(paramMap2);
			if(validateList.size()==0){//传入的邮箱或手机未有验证码
				_response = new Response(300, "未向此"+checkTypeTitle+"发送验证码！请重新获取验证码。");
			}else{//如果有就比较两者的验证码是否正确
				String curCode = validateList.get(0).getCode();
				if(!code.equals(curCode)){
					_response = new Response(300, "填写的"+checkTypeTitle+"验证码错误");
				}else{
					if(userService.checkExistUser(searchParam)) {
						this.userService.saveUserForSpace(user, registerType,checkType);
						//this.wechatService.saveUserForSpace(user, registerType,checkType,request);
						_response =  new Response(200,"保存成功");
					} else {
						_response = new Response(300,"用户名或者证件号已经存在");
					}
				}
			}
		}
		this.renderText(_response.toString(),response);
	}
}
