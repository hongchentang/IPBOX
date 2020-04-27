package com.hcis.ipanther.common.validate.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.notice.entity.Notice;
import com.hcis.ipanther.common.validate.entity.Validate;
import com.hcis.ipanther.core.service.IBaseService;

public interface IValidateService extends IBaseService<Validate>{

	/**
	 * 增加
	 * @param checkType
	 * @param phoneOrMail
	 * @param userId
	 * @param loginUser
	 * @return
	 */
	public int addValidate(String checkType, String email,String phone, String userId, LoginUser loginUser);
	
	/**
	 * 根据传入的email或phone查出 时间最新的验证码数据
	 * @param map
	 * @return
	 */
	public List<Validate> selectByMap(Map map);
	
	/**
	 * 根据用户填写的邮箱发送验证码
	 * @param email
	 * @param Vcode
	 */
	public void sendMail(String email,String Vcode);
	
	/**
	 * 根据传入的email或者phone来逻辑删除记录
	 * @param map
	 * @return
	 */
	public int deleteByLogic(Validate validate);
}
