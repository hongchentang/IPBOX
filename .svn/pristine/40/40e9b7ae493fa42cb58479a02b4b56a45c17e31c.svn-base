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

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class CacheMessageSender {
	private final static Logger logger = Logger.getLogger(CacheMessageSender.class);
	//广播形式发送
	private Destination topic;
	
	private JmsTemplate cacheJmsTemplate;
	
    public void sendMessage(final String cacheConsumerId){
        cacheJmsTemplate.send(topic, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
            	logger.info("cacheConsumerId:"+cacheConsumerId+"发送缓存更新消息");
                return session.createTextMessage(cacheConsumerId);
            }
        });
    }

    public void setTopic(Destination topic) {
        this.topic = topic;
    }

	public JmsTemplate getCacheJmsTemplate() {
		return cacheJmsTemplate;
	}

	public void setCacheJmsTemplate(JmsTemplate jmsTemplate) {
		this.cacheJmsTemplate = jmsTemplate;
	}
}
