package com.hcis.ipr.space.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.attachment.utils.AttachmentUtils;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.entity.UserRegister;
import com.hcis.ipanther.common.user.entity.UserStaff;
import com.hcis.ipanther.common.user.service.IUserRegisterService;
import com.hcis.ipanther.common.user.service.IUserRewardService;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.common.user.service.IUserStaffService;
import com.hcis.ipanther.common.user.utils.UserUtils;
import com.hcis.ipanther.common.validate.entity.Validate;
import com.hcis.ipanther.common.validate.service.IValidateService;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipr.core.web.controller.BaseController;
import com.hcis.ipr.train.userquestion.entity.UserQuestion;
import com.hcis.ipr.train.userquestion.service.IUserQuestionService;
/**
 * 个人端-用户管理
 * @author wuwentao
 * @date 2015年4月17日
 */
@Controller
@RequestMapping("/space/user")
public class UserController extends BaseController{
	
	@Autowired
	private IUserService userService;
	@Autowired
	private IUserStaffService userStaffService;
	@Autowired
	private IUserRewardService userRewardService;
	@Autowired
	private IUserRegisterService userRegisterService;
	@Autowired
	private IUserQuestionService userQuestionService;
	@Autowired
	private IValidateService validateService;
	
	/**
	 * 用户标签页
	 * @return
	 */
	@RequestMapping("/tabs")
	public ModelAndView tabs() {
		ModelAndView mv = this.newModelAndView();
		String userId = this.getLoginUser().getId();
		User user = userService.read(userId);
		String type = user.getType();
		/*
		 * 如果是教师类型，取最新的注册流程信息
		 */
		if(UserUtils.isTeacher(type)) {
			UserRegister userRegister = userRegisterService.getLatestByUserId(user.getId());
			if(null!=userRegister) {
				mv.addObject("procInstId",userRegister.getProcInstId());
				mv.addObject("registerId",userRegister.getId());
			}
		}
		mv.addObject("user",user);
		return mv;
	}
	
	/**
	 * 跳转到编辑用户信息页面
	 * 无封套
	 * @param user
	 * @return
	 */
	@RequestMapping("/noskin/edit")
	public ModelAndView edit() {
		ModelAndView mv = new ModelAndView("/space/user/edit");
		String userId = this.getLoginUser().getId();
		User user = userService.read(userId);
		mv.addObject("user",user);
		UserStaff userStaff = userStaffService.read(userId);
		
		/*
		 * 查出当前用户的所有在线留言记录
		 */
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("userId", userId);
		
		List<UserQuestion> userQuestionList = this.userQuestionService.selectByMap(paramMap);
		mv.addObject("userQuestionList", userQuestionList);
		mv.addObject("userStaff",userStaff);
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
						userService.saveUserForSpace(user, registerType,checkType);
						_response =  new Response(200,"保存成功");
					} else {
						_response = new Response(300,"用户名或者证件号已经存在");
					}
				}
			}
			
			
			
		}
		this.renderText(_response.toString(),response);
	}
	
	/**
	 * 跳转到修改密码页面
	 * @return
	 */
	@RequestMapping("/changePassword")
	public ModelAndView changePassword() {
		ModelAndView mv = new ModelAndView();
		LoginUser loginUser = LoginUser.loginUser(request);
		User user = userService.read(loginUser.getId());
		mv.addObject("user", user);
		return mv;
	}
}
