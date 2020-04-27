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
 * <p>Title: ChannelPathDirective.java</p>
 * 
 * <p>Description: 按上下级顺序提取指定栏目的所属栏目路径
 * 
 * 参数
 * id		栏目id
 * siteid	站点id
 * pagemark	栏目页面标识
 * 
 * 返回值
 * channel	栏目对象
 * index		索引
 * 
 * 使用示例
 * 
  <@channelPath siteid="${site.id}" ;channel> 
      <td class="index_menu index_menu_jg">|</td>
      <td class="index_menu index_menu1"><a href="#">${channel.name}</a></td>
  </@channelPath>
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
public class ChannelPathDirective extends CmsCommonDirective implements TemplateDirectiveModel{


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
				//查询栏目
				CmsChannel channel=null;
				//有channelid参数则根据channelid参数查询
				if (StringUtils.isNotEmpty(channelId)) {
					channel=cmsChannelService.read(channelId);
				}else if (StringUtils.isNotEmpty(siteId)&&StringUtils.isNotEmpty(pageMark)) {
					channel=cmsChannelService.findBySitePageMark(siteId, pageMark);
				}
				if (channel!=null) {
					//查询栏目
					List<CmsChannel> channelList=cmsChannelService.findPath(channel.getId());
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
	}

}
