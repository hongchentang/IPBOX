/*************************************************
Copyright (C), 2012
Author:����� 
Version: 
Date: 2012-6-3
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
package com.hcis.ipanther.core.page;

import java.io.Serializable;

/**
 * 分页接口,查询参数对象必须实现了该接口才能进行分页查询
 */
public interface IPage extends Serializable {
	
	public Pagination getPagination();

	public void setPagination(Pagination pagination);
}

