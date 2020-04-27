package com.hcis.ipr.cms.freemarker.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hcis.ipanther.core.utils.BeanLocator;
import com.hcis.ipr.cms.common.utils.CmsCommonDirective;
import com.hcis.ipr.cms.site.entity.CmsSite;
import com.hcis.ipr.cms.site.service.ICmsSiteService;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.StringModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

 
/**
 * 获取 版权
 * 有参数ID 查询获取
 * 没有从 session中获取
 * 
 * The Class CopyrightDirective.
 */
public class CopyrightDirective extends CmsCommonDirective implements TemplateDirectiveModel{

	protected static ICmsSiteService cmsSiteService=(ICmsSiteService) BeanLocator.getBean("cmsSiteService");
	
	public void execute(Environment env, Map params, TemplateModel[] loopVars, 
			TemplateDirectiveBody body)throws TemplateException, IOException {
		
		//获取参数
		String siteId=getParam(params, "siteId");
 
		Writer out =env.getOut();
		if (body!=null) {
			//设置循环变量
			if (loopVars!=null && loopVars.length>0 ) {
				//查询信息
				CmsSite cmsSite=null;
				if(StringUtils.isNotEmpty(siteId)){
					cmsSite= cmsSiteService.read(siteId);
				}else{
					HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
					cmsSite=(CmsSite) request.getSession().getAttribute("manageSite");
				}
				if(cmsSite!=null){
					loopVars[0]=new StringModel(cmsSite.getCopyright(),new BeansWrapper());  
					body.render(env.getOut()); 
				}
			}
		}
	}
}
