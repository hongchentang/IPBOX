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


import com.hcis.ipanther.common.login.vo.LoginLog;
import com.hcis.ipanther.core.web.vo.SearchParam;

/**
 * @author chaos
 *
 */
public interface ILoginLogService {
	
	/**
	 * @param loginLog
	 * @return
	 */
	public int addLoginLog(LoginLog loginLog);

	/**
	 * @param loginLog
	 * @return
	 */
	public int updateLoginLog(LoginLog loginLog);

	/**
	 * @param loginLog
	 * @return
	 */
	public int deleteLoginLog(LoginLog loginLog);

	/**
	 * @param searchParam
	 * @return
	 */
	public List<LoginLog> listLoginLog(SearchParam searchParam);

	/**
	 * @param id
	 * @return
	 */
	public LoginLog getLoginLog(String id);

	/**
	 * @param searchParam
	 * @return
	 */
	public int deleteLoginLogBySearchParam(SearchParam searchParam);

	/**
	 * @param loginLog
	 * @return
	 */
	public int deleteByLogout(LoginLog loginLog);

	/**
	 * @param paperworkNo
	 * @param sessionId
	 * @return
	 */
	public List<LoginLog> checkLoginLog(String paperworkNo, String sessionId);

}
