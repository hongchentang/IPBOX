package com.hcis.ipr.cms.freemarker.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hcis.ipanther.core.utils.BeanLocator;
import com.hcis.ipr.cms.common.utils.CmsCommonDirective;
import com.hcis.ipr.cms.link.entity.CmsLink;
import com.hcis.ipr.cms.link.service.ICmsLinkService;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

 
public class LinkListDirective extends CmsCommonDirective implements TemplateDirectiveModel{

	protected static ICmsLinkService cmsLinkService=(ICmsLinkService) BeanLocator.getBean("cmsLinkService");
	
	public void execute(Environment env, Map params, TemplateModel[] loopVars, 
			TemplateDirectiveBody body)throws TemplateException, IOException {
		
		//获取参数
		//类型
		String type=getParam(params, "type");
		//是否启用
		String isOk=getParam(params, "isOk");
		//是否启用
		String orderNum=getParam(params, "orderNum");
		
		//显示条数
		int num=getParamInt(params, "num",9);
		
		Writer out =env.getOut();
		if (body!=null) {
			//设置循环变量
			if (loopVars!=null && loopVars.length>0 ) {
				//查询信息
				Map<String, Object> map=new HashMap<String, Object>();
				if(StringUtils.isNotEmpty(type)){
					map.put("type", type);
				}
				if(StringUtils.isNotEmpty(isOk)&&isOk.equals("true")){
					map.put("isok", "01");
				}
				if(StringUtils.isNotEmpty(orderNum)&&orderNum.equals("01")){
					map.put("orderSql", "T.ORDER_NUM ASC");
				}else{
					map.put("orderSql", "T.CREATE_TIME DESC");
				}
				List<CmsLink> list=cmsLinkService.listLink(map,num);
				if(list!=null){
					 for (int i = 0; i < list.size(); i++) {
							loopVars[0]=new BeanModel(list.get(i),new BeansWrapper());  
							if(loopVars.length>1){
								loopVars[1]=new SimpleNumber(i);
							}
							body.render(env.getOut());  
					 }
				}
			}
		}
	}
}
