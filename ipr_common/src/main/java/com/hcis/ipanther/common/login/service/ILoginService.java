/*************************************************
Copyright (C), 2012
Author:����� 
Version: 
Date: 20132013-4-2
Description: // ������ϸ˵���˳����ļ���ɵ���Ҫ���ܣ�������ģ��
// ����Ľӿڣ����ֵ��ȡֵ��Χ�����弰�����Ŀ�
// �ơ�˳�򡢶����������ȹ�ϵ
Function List: // ��Ҫ�����б?ÿ����¼Ӧ�����������ܼ�Ҫ˵��
1. ....
History: // �޸���ʷ��¼�б?ÿ���޸ļ�¼Ӧ�����޸����ڡ��޸�
// �߼��޸����ݼ���
1. Date:
Author:
Modification:
2. ...
*************************************************/
package com.hcis.ipanther.common.login.service;


import java.util.List;
import java.util.Map;

import com.github.sd4324530.fastweixin.api.response.GetUserInfoResponse;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.core.service.IBaseService;

/**
 * @author lianghuahuang
 *
 */
public interface ILoginService extends IBaseService{
	
	public LoginUser getLoginUser(LoginUser loginUser);
	
	public LoginUser getLoginUser(String userId);
	
	public LoginUser getLoginUserByAccount(String account);
	//设置菜单
	public void setMenu(LoginUser loginUser);

	public List<LoginUser> selectAllLoginUser();

	public void setRoleList(LoginUser loginUser);
	
	/**
	 * 根据微信ID获取loginUser
	 * @param wechatId
	 * @return
	 */
	public LoginUser getLoginUserByWechatId(String wechatId);

	/**
	 * 获取用户信息
	 * @param code
	 */
	GetUserInfoResponse code2Session(String code);

	/***
	 * 获取用户信息
	 * @param phone
	 * @param weChatId
	 * @return
	 */
    LoginUser getUserByPhoneAndWechatId(String phone, String weChatId);
}
