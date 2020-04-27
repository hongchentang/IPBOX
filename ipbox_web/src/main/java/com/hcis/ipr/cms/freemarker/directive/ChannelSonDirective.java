package com.hcis.ipr.cms.freemarker.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hcis.ipanther.core.utils.BeanLocator;
import com.hcis.ipr.cms.channel.entity.CmsChannel;
import com.hcis.ipr.cms.channel.service.ICmsChannelService;
import com.hcis.ipr.cms.common.utils.CmsCommonDirective;
import com.hcis.ipr.cms.site.entity.CmsSite;
import com.hcis.ipr.cms.site.service.ICmsSiteService;

import freemarker.core.Environment;
import freemarker.ext.beans.BeanModel;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
/**
 * 
 * <p>Title: ChannelSonDirective.java</p>
 * 
 * <p>Description: 提取所有子栏目
 * 
 * 参数
 * siteid	站点id
 * parid    空字符:所有;"par":一级栏目;"parid":此id下栏目;
 * navigation  是否导航 空字符串:所有;"1":是;"0":否;
 * state  是否有效 空字符串:所有;"1":是;"0":否;
 * 
 * 返回值
 * channel	栏目对象
 * index		索引
 * 
 * 使用示例
 * 
  <@channelSon siteid="${site.id}" ;channel> 
      <td class="index_menu index_menu_jg">|</td>
      <td class="index_menu index_menu1"><a href="#">${channel.name}</a></td>
  </@channelSon>
 * </p>
 * 
 * <p>Date: May 13, 2012</p>
 * 
 * <p>Time: 10:21:58 PM</p>
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
public class ChannelSonDirective extends CmsCommonDirective implements TemplateDirectiveModel{

	protected static ICmsChannelService cmsChannelService= (ICmsChannelService) BeanLocator.getBean("cmsChannelService");
	
	protected static ICmsSiteService cmsSiteService= (ICmsSiteService) BeanLocator.getBean("cmsSiteService");
	
	public void execute(Environment env, Map params, TemplateModel[] loopVars, 
			TemplateDirectiveBody body)throws TemplateException, IOException {
		
		//获取参数
		String siteId=getParam(params, "siteId");
		// 空字符:所有;"par":一级栏目;"${parId}":此id下所有栏目;
		String parId=getParam(params, "parId");
		
		Writer out =env.getOut();
		if (body!=null) {
			//设置循环变量
			if (loopVars!=null && loopVars.length>0 && StringUtils.isNotEmpty(siteId)) {
				//查询栏目
				List<CmsChannel> channelList=cmsChannelService.findSon(siteId, parId,getParam(params, "state"),getParam(params, "navigation"));
				if (channelList!=null && channelList.size()>0) {
					CmsSite site=cmsSiteService.read(siteId);
					for (int i = 0; i < channelList.size(); i++) {
						//设置sitepath
						if (site!=null) {
							channelList.get(i).setSitePath(env.getDataModel().get("contextPath").toString()
									+"site/"+site.getSourcePath()+"/");
						}
						loopVars[0]=new BeanModel(channelList.get(i),new BeansWrapper());  
						if(loopVars.length>1){
							loopVars[1]=new SimpleNumber(i);
						}
						body.render(env.getOut());  
					}
				}
			}
		}
	}

//	public ChannelService getChannelService() {
//		return channelService;
//	}
//
//	public void setChannelService(ChannelService channelService) {
//		this.channelService = channelService;
//	}
//
//	public SiteService getSiteService() {
//		return siteService;
//	}
//
//	public void setSiteService(SiteService siteService) {
//		this.siteService = siteService;
//	}

}
