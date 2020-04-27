package com.hcis.ipanther.common.validate.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hcis.ipanther.common.login.utils.LoginUtils;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.common.validate.entity.Validate;
import com.hcis.ipanther.common.validate.service.IValidateService;
import com.hcis.ipanther.common.validate.utils.ValicateCodeUtils;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;

@Controller
@RequestMapping("/common/validate")
public class ValidateController extends BaseController{

	@Autowired
	private IValidateService validateService;
	@Autowired
	private IUserService userService;
	
	//用于校验填写的邮箱或手机号是否已经注册过
	@ResponseBody
	@RequestMapping("checkEmailOrPhone")
	public Map<String,Object> checkEmailOrPhone(String checkType,String email,String phone,String userId){
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String,String> paramMap = new HashMap<String,String>();
		
		if("email".equals(checkType))
			paramMap.put("email", email);
		else
			paramMap.put("mobilePhone", phone);
		
		paramMap.put("userId", userId);
		List<String> list = this.userService.selectByEmailOrPhone(paramMap);
		if(list.size()>0){
			resultMap.put("flag", false);
		}
		else{
			resultMap.put("flag", true);
		}
		return resultMap;
	}
	
	//根据选择验证的方式，对应的根据邮箱或者手机保存验证码
	@RequestMapping("saveValidate")
	@ResponseBody
	public Map<String,Object> saveValidateCode(String checkType,String email,String phone,String userId){
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		int count = 0;
		//增加验证码记录并且根据类型发送邮箱或验证码
		count = this.validateService.addValidate(checkType, email,phone, userId, LoginUtils.getLoginUser(request));
		resultMap.put("count", count);
		return resultMap;
	}
	
	//验证传来的验证码是否正确
	@RequestMapping("getValidate")
	@ResponseBody
	public Map<String,Object> getValidate(String checkType,String phoneOrMail,String code){
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		Map<String,String> paramMap = new HashMap<String,String>();
		if("email".equals(checkType))//如果传入的checkType为email，说明选择的是邮箱验证
			paramMap.put("email", phoneOrMail);
		else
			paramMap.put("phone", phoneOrMail);
		
		List<Validate> validateList = this.validateService.selectByMap(paramMap);
		if(validateList.size()==0){//传入的邮箱或手机未有验证码
			resultMap.put("flag", false);
			resultMap.put("msg", "对应邮箱或手机未发送验证码");
		}else{//如果有就比较两者的验证码是否正确
			String curCode = validateList.get(0).getCode();
			if(code.equals(curCode)){
				resultMap.put("flag", true);
			}
			else{
				resultMap.put("flag", false);
				resultMap.put("msg", "验证码错误");
			}

		}
		return resultMap;
	}
	
	//根据传入的email和phone逻辑删除验证码记录
	@ResponseBody
	@RequestMapping("deleteValidate")
	public Map<String,Object> deleteValidate(String checkType,String email,String phone){
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String,String> paramMap = new HashMap<String,String>();
		int count = 0;
		//首先查出所有的数据
		/*if("email".equals(checkType))
			paramMap.put("email", phoneOrMail);
		else
			paramMap.put("phone", phoneOrMail);
		
		List<Validate> list = this.validateService.selectByMap(paramMap);
		Validate validate = list.get(0);*/
		
		Validate validate = new Validate();
		
		//存入
		LoginUser user = LoginUtils.getLoginUser(request);
		if(user!=null)
			validate.setUpdatedby(user.getId());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String curDate = format.format(new Date());
		validate.setUpdateTime(new Date());
		validate.setEndTime(curDate);
		if("email".equals(checkType))
			validate.setEmail(email);
		else
			validate.setPhone(phone);
		
		count = this.validateService.deleteByLogic(validate);
		resultMap.put("count", count);
		return resultMap;
	}
	
	
}
