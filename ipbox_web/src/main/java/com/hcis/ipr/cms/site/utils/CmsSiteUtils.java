package com.hcis.ipr.cms.site.utils;

import org.apache.commons.lang3.StringUtils;

import com.hcis.ipanther.core.utils.BeanLocator;
import com.hcis.ipr.cms.site.entity.CmsSite;
import com.hcis.ipr.cms.site.service.ICmsSiteService;

public class CmsSiteUtils {

	protected static ICmsSiteService cmsSiteService= (ICmsSiteService) BeanLocator.getBean("cmsSiteService");
	
	public static CmsSite getCmsSite(String siteId){
		if(StringUtils.isNotEmpty(siteId)){
			return cmsSiteService.read(siteId);
		}
		return null;
	}
	
	
	
}
