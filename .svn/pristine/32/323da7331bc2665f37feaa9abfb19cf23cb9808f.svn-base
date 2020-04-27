/*************************************************
Copyright (C), 2012
Author:����� 
Version: 
Date: 2012-8-2
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
**com.hcis.ipanther.core.cache.local**************/
package com.hcis.ipanther.core.cache.local;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.hcis.ipanther.core.utils.BeanLocator;

public class CacheMessageListener implements MessageListener{
	private final static Logger logger = Logger.getLogger(CacheMessageListener.class);
	@Override
	public void onMessage(Message message) {
			try {
				String cacheReloaderId = ((TextMessage)message).getText();
				Object object = BeanLocator.getBean(cacheReloaderId);
				if(object!=null){
					if(object instanceof ICacheReloader){
						ICacheReloader cacheReloader = (ICacheReloader)object;
						cacheReloader.reloadCache();
					}
				}else{
					//cacheReloaderId不正确，需要重新配置
					logger.error("cacheReloaderId:"+cacheReloaderId+"配置ID错误或者未在spring中配置该bean类");
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
			
	}
	

}
