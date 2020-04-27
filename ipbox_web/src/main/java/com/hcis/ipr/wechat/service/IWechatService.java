package com.hcis.ipr.wechat.service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.entity.WechatUser;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.intellectual.patent.entity.Patent;

/**
 * 微信的一些基础操作
 * @author wuwentao
 * @date 2016年10月20日
 */
public interface IWechatService {
	
	/**
	 * 根据微信号进行登录
	 * @param wechatId
	 * @param request
	 * @return
	 */
	public boolean login(String wechatId,HttpServletRequest request);
	
	/**
	 * 绑定账号
	 * 绑定成功后设置用户的备注信息为：学校名-真实姓名
	 * @param user
	 * @param account 可以是用户名或者身份证号
	 * @param password
	 * @return 错误信息
	 */
	public String bind(User user,String account,String password);
	
	/**
	 * 解绑账号
	 * @param wechatId
	 * @return 错误信息
	 */
	public String unbind(String wechatId);

	/**
	 * 小程序登录
	 * @param user
	 * @param request
	 */
	void loginV2(WechatUser user, HttpServletRequest request);

	/**
	 * 检索专利数据
	 * @param searchParam
	 * @return
	 */
	List<Patent> searchPatents(SearchParam searchParam) throws ParseException;

    /**
	 * 保存学员/教师的基本信息
	 * @param userType student/teacher
	 * @param user
	 * @return 用户的ID
	 * @throws IOException
	 */
	//public String save(String userType,User user,HttpServletRequest request) throws Exception;
	
	/**
	 * 个人端保存用户信息
	 * 如果registerType不为空，则要相对应的发起教师/学员流程
	 * @param user 
	 * @param registerType 注册类型，一般的保存为空，注册为学员为student，注册为教师为teacher
	 * @return
	 * @throws Exception 
	 */
	//public String saveUserForSpace(User user, String registerType,String checkType,HttpServletRequest request) throws Exception;

}
