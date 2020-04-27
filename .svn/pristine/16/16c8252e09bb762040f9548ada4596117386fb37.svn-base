package com.hcis.ipr.cms.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;

import com.hcis.ipr.cms.freemarker.directive.AjaxInfoClickDirective;
import com.hcis.ipr.cms.freemarker.directive.AjaxLoadDirective;
import com.hcis.ipr.cms.freemarker.directive.AjaxStoreDirective;
import com.hcis.ipr.cms.freemarker.directive.ChannelDirective;
import com.hcis.ipr.cms.freemarker.directive.ChannelListDirective;
import com.hcis.ipr.cms.freemarker.directive.ChannelPathDirective;
import com.hcis.ipr.cms.freemarker.directive.ChannelSonDirective;
import com.hcis.ipr.cms.freemarker.directive.CopyrightDirective;
import com.hcis.ipr.cms.freemarker.directive.InfoAttchsDirective;
import com.hcis.ipr.cms.freemarker.directive.InfoDirective;
import com.hcis.ipr.cms.freemarker.directive.InfoListDirective;
import com.hcis.ipr.cms.freemarker.directive.InfoPageDirective;
import com.hcis.ipr.cms.freemarker.directive.LinkListDirective;
import com.hcis.ipr.cms.freemarker.directive.RecordCodeDirective;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

/**
 * 
 * <p>Title: FreeMarkerUtil.java</p>
 * 
 * <p>Description: freemarker工具类</p>
 * 
 * <p>Date: Mar 9, 2015</p>
 * 
 * <p>Time: 4:00:48 PM</p>
 * 
 * <p>Copyright: 2015</p>
 * 
 * <p>Company: freeteam</p>
 * 
 * @author freeteam
 * @version 2.0
 * 
 * <p>============================================</p>
 * <p>Modification History
 * <p>Mender: </p>
 * <p>Date: </p>
 * <p>Reason: </p>
 * <p>============================================</p>
 */
public class FreeMarkerUtil {

	 /**     
	  * 生成静态页面主方法     默认编码为UTF-8
	  * @param context ServletContext     
	  * @param data 一个Map的数据结果集     
	  * @param templatePath ftl模版路径     
	  * @param htmlPath 生成静态页面的路径   
	 * @throws TemplateException 
	 * @throws IOException 
	  */    
	public static void createHTML(ServletContext context,Map<String,Object> data,String templatePath,String htmlPath) throws IOException, TemplateException{
		createHTML(context, data, "UTF-8", templatePath, "UTF-8", htmlPath);
	}
	 /**     
	  * 生成静态页面主方法     
	  * @param context ServletContext     
	  * @param data 一个Map的数据结果集     
	  * @param templatePath ftl模版路径     
	  * @param templateEncode ftl模版编码     
	  * @param htmlPath 生成静态页面的路径   
	  * @param htmlEncode 生成静态页面的编码     
	 * @throws IOException 
	 * @throws TemplateException 
	  */    
	public static void createHTML(ServletContext context,Map<String,Object> data,String templetEncode,String templatePath,String htmlEncode,String htmlPath) throws IOException, TemplateException{
		Configuration freemarkerCfg=initCfg(context, templetEncode, StringUtils.substringBeforeLast(templatePath, "/"));
		freemarkerCfg.setEncoding(Locale.CHINA, templetEncode);
		//指定模版路径            
		Template template = freemarkerCfg.getTemplate(StringUtils.substringAfterLast(templatePath, "/"),templetEncode);            
		template.setEncoding(templetEncode);            
		//静态页面路径                      
		File htmlFile = new File(htmlPath);  
		if (!htmlFile.getParentFile().exists()) {
			htmlFile.getParentFile().mkdirs();
		}
		Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(htmlFile), htmlEncode));            
		//处理模版              
		template.process(data, writer);            
		writer.flush();            
		writer.close();        
	}
	/**
	 * 处理页面后，装处理结果放入指定Out
	 * @param context
	 * @param data
	 * @param templatePath
	 * @throws TemplateModelException 
	 * @throws IOException 
	 */
	public static void createWriter(ServletContext context,Map<String,Object> data,String templatePath,Writer writer) throws TemplateModelException, IOException{
		createWriter(context, data, "UTF-8", templatePath, "UTF-8",writer);
	}
	
	public static void createWriter(ServletContext context,Map<String,Object> data,String templetEncode,String templatePath,String htmlEncode,Writer writer) throws TemplateModelException, IOException{
		Configuration freemarkerCfg=initCfg(context, templetEncode);
		try {            
			//指定模版路径            
			Template template = freemarkerCfg.getTemplate(templatePath,templetEncode);            
			template.setEncoding(templetEncode);            
			//处理模版              
			template.process(data, writer);        
		} catch (Exception e) {            
			e.printStackTrace();        
		}
	}
	
	/**
	 * 初始化freemarker配置
	 * @return
	 * @throws TemplateModelException 
	 * @throws IOException 
	 */
	public static Configuration initCfg(ServletContext context,String templetEncode) throws TemplateModelException, IOException{
		Configuration freemarkerCfg=null;
		TemplateLoader templateLoader = null;
		//判断context中是否有freemarkerCfg属性
		if (context.getAttribute("freemarkerCfg")!=null) {
			freemarkerCfg=(Configuration)context.getAttribute("freemarkerCfg");
		}else {
			freemarkerCfg = new Configuration();        
			//加载模版        
			//freemarkerCfg.setServletContextForTemplateLoading(context, "/");     
			freemarkerCfg.setEncoding(Locale.getDefault(), templetEncode);  
			
			freemarkerCfg.setDirectoryForTemplateLoading(new File(CmsCommonUtils.replaceWinSeparator(context.getRealPath("/"))));
			templateLoader = new FileTemplateLoader(new File(CmsCommonUtils.replaceWinSeparator(context.getRealPath("/"))));
			freemarkerCfg.setTemplateLoader(templateLoader);// 设置模版文件对象
			
			//加载自定义标签
			//栏目类
			freemarkerCfg.setSharedVariable("channel", new ChannelDirective());
			freemarkerCfg.setSharedVariable("channelList", new ChannelListDirective());
			freemarkerCfg.setSharedVariable("channelSon", new ChannelSonDirective());
			freemarkerCfg.setSharedVariable("channelPath", new ChannelPathDirective());
			//信息类
			freemarkerCfg.setSharedVariable("infoList", new InfoListDirective());
			freemarkerCfg.setSharedVariable("infoPage", new InfoPageDirective());
			freemarkerCfg.setSharedVariable("infoAttchs", new InfoAttchsDirective());
	 
			freemarkerCfg.setSharedVariable("info", new InfoDirective());
 
			//链接类
//			freemarkerCfg.setSharedVariable("linkClass", new LinkClassDirective());
//			freemarkerCfg.setSharedVariable("link", new LinkDirective());
			freemarkerCfg.setSharedVariable("linkList", new LinkListDirective());
			//Ajax类
			freemarkerCfg.setSharedVariable("ajaxInfoClick", new AjaxInfoClickDirective());
			freemarkerCfg.setSharedVariable("ajaxLoad", new AjaxLoadDirective());
			freemarkerCfg.setSharedVariable("ajaxStore", new AjaxStoreDirective());
			
			freemarkerCfg.setSharedVariable("copyright", new CopyrightDirective());
			freemarkerCfg.setSharedVariable("recordCode", new RecordCodeDirective());
 
			//系统配置
			//freemarkerCfg.setSharedVariable("config", new ConfigDirective());
 
			//其它
//			freemarkerCfg.setSharedVariable("video", new VideoDirective());
//			freemarkerCfg.setSharedVariable("URLEncoder", new URLEncoderDirective());
//			freemarkerCfg.setSharedVariable("URLDecoder", new URLDecoderDirective());
//			freemarkerCfg.setSharedVariable("html", new HtmlDirective());
			
		}
		return freemarkerCfg;
	}
	
	/**
	 * 初始化freemarker配置
	 * @return
	 * @throws TemplateModelException 
	 * @throws IOException 
	 */
	public static Configuration initCfg(ServletContext context,String templetEncode,String template) throws TemplateModelException, IOException{
		Configuration freemarkerCfg=null;
		TemplateLoader templateLoader = null;
		//判断context中是否有freemarkerCfg属性
		if (context.getAttribute("freemarkerCfg")!=null) {
			freemarkerCfg=(Configuration)context.getAttribute("freemarkerCfg");
		}else {
			freemarkerCfg = new Configuration();        
			//加载模版        
			//freemarkerCfg.setServletContextForTemplateLoading(context, "/");     
			freemarkerCfg.setEncoding(Locale.getDefault(), templetEncode);  
			
			String root =context.getRealPath("/");
			if(root.startsWith("/")){
				if(!StringUtils.endsWith(root,"/")){
					root=root+"/";
				}
			}
			freemarkerCfg.setDirectoryForTemplateLoading(new File(CmsCommonUtils.replaceWinSeparator(root+template)));
			templateLoader = new FileTemplateLoader(new File(CmsCommonUtils.replaceWinSeparator(root+template)));
			freemarkerCfg.setTemplateLoader(templateLoader);// 设置模版文件对象
			
			//加载自定义标签
			//栏目类
			freemarkerCfg.setSharedVariable("channel", new ChannelDirective());
			freemarkerCfg.setSharedVariable("channelList", new ChannelListDirective());
			freemarkerCfg.setSharedVariable("channelSon", new ChannelSonDirective());
			freemarkerCfg.setSharedVariable("channelPath", new ChannelPathDirective());
			//信息类
			freemarkerCfg.setSharedVariable("infoList", new InfoListDirective());
			freemarkerCfg.setSharedVariable("infoPage", new InfoPageDirective());
			freemarkerCfg.setSharedVariable("infoAttchs", new InfoAttchsDirective());
	 
			freemarkerCfg.setSharedVariable("info", new InfoDirective());
 
			//链接类
//			freemarkerCfg.setSharedVariable("linkClass", new LinkClassDirective());
			freemarkerCfg.setSharedVariable("linkList", new LinkListDirective());
			//Ajax类
			freemarkerCfg.setSharedVariable("ajaxInfoClick", new AjaxInfoClickDirective());
			freemarkerCfg.setSharedVariable("ajaxLoad", new AjaxLoadDirective());
			freemarkerCfg.setSharedVariable("ajaxStore", new AjaxStoreDirective());
			
			freemarkerCfg.setSharedVariable("copyright", new CopyrightDirective());
			freemarkerCfg.setSharedVariable("recordCode", new RecordCodeDirective());
 
			//系统配置
			//freemarkerCfg.setSharedVariable("config", new ConfigDirective());
 
			//其它
//			freemarkerCfg.setSharedVariable("video", new VideoDirective());
//			freemarkerCfg.setSharedVariable("URLEncoder", new URLEncoderDirective());
//			freemarkerCfg.setSharedVariable("URLDecoder", new URLDecoderDirective());
//			freemarkerCfg.setSharedVariable("html", new HtmlDirective());
		}
		return freemarkerCfg;
	}
}