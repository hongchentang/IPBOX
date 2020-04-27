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
package com.hcis.ipanther.common.login.dao;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.login.vo.LoginLog;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;

/**
 * @author chaos
 *
 */
@Repository
public class LoginLogDao extends MyBatisDao {
	
	public int deleteBySearchParam(SearchParam searchParam){
		return this.getSqlSession().delete(namespace+".deleteBySearchParam", searchParam);
	}
	
	public int deleteByLogout(LoginLog loginLog){
		return this.getSqlSession().update(namespace+".deleteByLogout",loginLog);
	}
	
}
