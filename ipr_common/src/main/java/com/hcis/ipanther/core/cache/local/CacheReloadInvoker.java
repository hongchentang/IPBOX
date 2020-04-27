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
**com.hcis.ipanther.core.cache.local******************/
package com.hcis.ipanther.core.cache.local;

import com.hcis.ipanther.core.utils.BeanLocator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CacheReloadInvoker {
	
	private static Logger logger = LoggerFactory.getLogger(CacheReloadInvoker.class);
	
	protected String cacheReloaderId;//缓存更新的beanId
	
	protected CacheMessageSender cacheMessageSender;//缓存更新的消息生产者
	
	protected void invokeCacheReload(){
		if(!StringUtils.isEmpty(cacheReloaderId)){			
			if(cacheMessageSender!=null){// 利用JMS更新缓存				
				cacheMessageSender.sendMessage(cacheReloaderId);
				logger.debug("缓存更新信息已发送，cacheReloaderId："+cacheReloaderId);
			}else{//采用本地插件式更新缓存
				Object object  = BeanLocator.getBean(cacheReloaderId);
				if(object instanceof ICacheReloader){
					ICacheReloader cacheReloader = (ICacheReloader)object;
					cacheReloader.reloadCache();
				}else{
					//日志记录缓存更新对象未实现ICacheReloader接口
					logger.debug("缓存更新对象未实现ICacheReloader接口,cacheReloaderId："+cacheReloaderId);
				}
			}
		}else{
			//日志记录缓存更新的beanId为空，无法实现缓存更新
			logger.debug("缓存更新的beanId为空，无法实现缓存更新,cacheReloaderId："+cacheReloaderId);
		}
	}

	public String getCacheReloaderId() {
		return cacheReloaderId;
	}

	public void setCacheReloaderId(String cacheReloaderId) {
		this.cacheReloaderId = cacheReloaderId;
	}

	public CacheMessageSender getCacheMessageSender() {
		return cacheMessageSender;
	}

	public void setCacheMessageSender(CacheMessageSender cacheMessageSender) {
		this.cacheMessageSender = cacheMessageSender;
	}
}
