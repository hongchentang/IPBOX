package com.hcis.ipr.cms.freemarker.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.hcis.ipanther.core.utils.BeanLocator;
import com.hcis.ipr.cms.channel.entity.CmsChannel;
import com.hcis.ipr.cms.channel.service.ICmsChannelService;
import com.hcis.ipr.cms.common.utils.CmsCommonDirective;
import com.hcis.ipr.cms.site.entity.CmsSite;
import com.hcis.ipr.cms.site.service.ICmsSiteService;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 
 * <p>Title: ChannelNameDirective.java</p>
 * 
 * <p>Description:根据id提取栏目对象</p>
 * 参数 
 * id		栏目id
 * siteid	站点id
 * pagemark	栏目页面标识
 * checkHasSon 是否检查有子栏目 1是
 * 
 * 返回值
 * channel 栏目对象
 * 
 * 使用示例
   <@channel id="b7a761e6-8308-472a-9758-1d1d5142a609" ;channel>${channel.name}</@channel>
 * 
 * <p>Date: May 14, 2012</p>
 * 
 * <p>Time: 1:08:48 PM</p>
 * 
 * <p>Copyright: 2012</p>
 * 
 * <p>Company: freeteam</p>
 * 
 * @author freeteam
 * @version 1.0
 * 
 * <p>============================================</p>
 * <p>Modification History
 * <p>Mender: </p>
 * <p>Date: </p>
 * <p>Reason: </p>
 * <p>============================================</p>
 */
public class ChannelDirective extends CmsCommonDirective implements TemplateDirectiveModel{

 
	protected static ICmsChannelService cmsChannelService= (ICmsChannelService) BeanLocator.getBean("cmsChannelService");
	
	protected static ICmsSiteService cmsSiteService= (ICmsSiteService) BeanLocator.getBean("cmsSiteService");
	
	public void execute(Environment env, Map params, TemplateModel[] loopVars, 
			TemplateDirectiveBody body)throws TemplateException, IOException {
		//获取参数
		//栏目id
		String channelId=getParam(params, "id");
		//站点id
		String siteId=getParam(params, "siteId");
		//栏目页面标识
		String pageMark=getParam(params, "pageMark");
		
		
		Writer out =env.getOut();
		if (body!=null) {
			//设置循环变量
			if (loopVars!=null && loopVars.length>0 ) {
				
//				//查询栏目
				CmsChannel channel=null;
				//有channelid参数则根据channelid参数查询
				if (StringUtils.isNotEmpty(channelId)) {
					channel=cmsChannelService.read(channelId);
				}else if (StringUtils.isNotEmpty(siteId)&&StringUtils.isNotEmpty(pageMark)) {
					channel=cmsChannelService.findBySitePageMark(siteId, pageMark);
				}
				if (channel!=null) {
					//设置sitepath
					CmsSite site=cmsSiteService.read(channel.getSiteId());
					if (site!=null) {
						channel.setSitePath(env.getDataModel().get("contextPath").toString()
								+"site/"+site.getSourcePath()+"/");
					}
					if ("01".equals(getParam(params, "checkHasSon"))) {
						//检查是否有子栏目
						channel.setHasChildren(cmsChannelService.hasChildren(channel.getId())?"1":"0");
					}
					loopVars[0]=new BeanModel(channel,new BeansWrapper());  
					body.render(env.getOut());  
				}
			}
		}
	}
}
