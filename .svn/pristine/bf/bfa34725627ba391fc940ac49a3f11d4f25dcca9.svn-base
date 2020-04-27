package com.hcis.ipr.cms.freemarker.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
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
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.WrappingTemplateModel;
/**
 * 
 * <p>Title: ChannelListDirective.java</p>
 * 
 * <p>Description: 栏目循环标签
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
  <@channelList siteid="${site.id}" ;channel> 
      <td class="index_menu index_menu_jg">|</td>
      <td class="index_menu index_menu1"><a href="#">${channel.name}</a></td>
  </@channelList>
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
public class ChannelListDirective extends CmsCommonDirective implements TemplateDirectiveModel{

	protected ICmsChannelService cmsChannelService= (ICmsChannelService) BeanLocator.getBean("cmsChannelService");
	
	protected ICmsSiteService cmsSiteService= (ICmsSiteService) BeanLocator.getBean("cmsSiteService");
	
 
	@Override
	public void execute(Environment env,
		            Map params, TemplateModel[] loopVars,
		            TemplateDirectiveBody body)
		            throws TemplateException, IOException {
		if(params==null&&params.isEmpty()) {  
	            throw new TemplateModelException(  
	                    "This directive   parameters isEmpty .");  
	    }  
		//获取参数
		String siteId=getParam(params, "siteId");
		String parId=getParam(params, "parId");// 空字符:所有;"par":一级栏目;"parid":此id下栏目;
		
		String state=getParam(params, "state");
		String navigation=getParam(params, "navigation");//是否导航
		
		Writer out =env.getOut();
		if (body!=null) {
//			    out.write("ssssssssss");  
//	            body.render(out);  
			//设置循环变量
			if (loopVars!=null && loopVars.length>0 && StringUtils.isNotEmpty(siteId)) {
				//查询栏目
				List<CmsChannel> channelList=cmsChannelService.findByParent(siteId,parId,state,navigation);
				if (channelList!=null && channelList.size()>0) {
					CmsSite site=cmsSiteService.read(siteId);
					for (int i = 0; i < channelList.size(); i++) {
						//设置sitepath
						if (site!=null) {
							channelList.get(i).setSitePath(env.getDataModel().get("contextPath").toString()
									+"site/"+site.getSourcePath()+"/");
						}
						loopVars[0]= new BeanModel(channelList.get(i), new BeansWrapper());
								//WrappingTemplateModel.getDefaultObjectWrapper().wrap(channelList.get(i)); 
						if(loopVars.length>1){
							loopVars[1]=new SimpleNumber(i);
						}
						body.render(env.getOut());  
					}
				}
			}
		} else {  
            throw new RuntimeException("missing body");  
        }  
	}
}
