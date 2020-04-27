package com.hcis.ipr.cms.freemarker.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.htmlparser.Parser;
import org.htmlparser.util.ParserException;
import org.htmlparser.visitors.TextExtractingVisitor;
import org.springframework.beans.factory.annotation.Autowired;

import com.hcis.ipanther.core.utils.BeanLocator;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.cms.channel.entity.CmsChannel;
import com.hcis.ipr.cms.channel.service.ICmsChannelService;
import com.hcis.ipr.cms.common.utils.CmsCommonDirective;
import com.hcis.ipr.cms.common.utils.FreemarkerPager;
import com.hcis.ipr.cms.info.entity.CmsInfo;
import com.hcis.ipr.cms.info.service.ICmsInfoService;
import com.hcis.ipr.cms.site.entity.CmsSite;
import com.hcis.ipr.cms.site.service.ICmsSiteService;

import freemarker.core.Environment;
import freemarker.ext.beans.ArrayModel;
import freemarker.ext.beans.BeanModel;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 
 * <p>Title: InfoPageDirective.java</p>
 * 
 * <p>Description: 信息列表分页标签
 * 参数
 * siteid		站点id
 * channelid	栏目id
 * channelparid	栏目父id
 * num			显示数量
 * order		排序类型  
 * 				1 固顶有效并降序,发布时间降序(默认)
 * 				2 固顶有效并降序,发布时间升序
 * 				3 发布时间降序
 * 				4 发布时间升序
 * titleLen		标题长度
 * hot			是否按点击热度倒序，1是
 * dateFormat	日期格式
 * channelPagemark	栏目页面标识
 * channelParPagemark	父栏目页面标识
 * img			是否只提取带图片的新闻	1是
 * page			当前第几页，默认1			
 * pagenum		最多显示页数
 * checkOpenendtime	检查公开时限 默认不检查，1检查
 * newdays		几天内为最新
 * url 分页url 前缀
 * 
 * 返回值
 * infoList		信息对象列表
 * pager		分页对象
 * 
 * 
 * 使用示例
 * </p>
 * 
 * <p>Date: May 14, 2012</p>
 * 
 * <p>Time: 1:45:03 PM</p>
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
public class InfoPageDirective extends CmsCommonDirective implements TemplateDirectiveModel{
 
	protected static ICmsInfoService cmsInfoService=(ICmsInfoService) BeanLocator.getBean("cmsInfoService");
	protected static ICmsChannelService cmsChannelService=(ICmsChannelService) BeanLocator.getBean("cmsChannelService");
	protected static ICmsSiteService cmsSiteService=(ICmsSiteService) BeanLocator.getBean("cmsSiteService");
	
 
	

	public void execute(Environment env, Map params, TemplateModel[] loopVars, 
			TemplateDirectiveBody body)throws TemplateException, IOException {
		
		//获取参数
		//站点id
		String siteId=getParam(params, "siteId");
		//栏目id
		String channelId=getParam(params, "channelId");
		//栏目id
		String channelParId=getParam(params, "channelParId");
		//显示数量
		int num=getParamInt(params, "num", 10);
		//排序
		String order=getParam(params, "order","01");
		//标题长度
		int titleLen=getParamInt(params, "titleLen",0);
		//是否按点击热度查询
		String hot=getParam(params, "hot");
		//日期格式
		String dateFormat=getParam(params, "dateFormat");
		//栏目页面标识
		String channelPageMark=getParam(params, "channelPageMark");
		String channelParPageMark=getParam(params, "channelParPageMark");
		//是否只提取带图片的信息
		String img=getParam(params, "img");
		//当前第几页
		int page=getParamInt(params, "page", 1);
		//最多显示页数
		int pagenum=getParamInt(params, "pagenum", 0);
		//几天内为最新
		int newdays=getParamInt(params, "newdays",0);
		//分页前缀
		String url=getParam(params, "url");
		
		String capture=getParam(params, "capture");
		int captureLen=getParamInt(params, "captureLen",0);
		//是否发布
		String state=getParam(params, "state","01");
		
		Writer out =env.getOut();
		if (body!=null) {
			//设置循环变量
			if (loopVars!=null && loopVars.length>0 ) {
				//查询信息
				//查询信息
				SearchParam info=new SearchParam();
				if (StringUtils.isNotEmpty(siteId)) {
					info.getParamMap().put("siteId",siteId);
				}
				if (StringUtils.isNotEmpty(channelId)) {
					info.getParamMap().put("channelId",channelId);
				}
				if (StringUtils.isNotEmpty(channelPageMark)) {
					info.getParamMap().put("channelPageMark",channelPageMark);
				}
				if (StringUtils.isNotEmpty(channelParId)) {
					List<String> channelids=new ArrayList<String>();
					channelids.add(channelParId);
					List<CmsChannel> sonList=cmsChannelService.findSon(siteId, channelParId, "01", "");
					if (sonList!=null && sonList.size()>0) {
						for (int i = 0; i < sonList.size(); i++) {
							channelids.add(sonList.get(i).getId());
						}
					}
					info.getParamMap().put("channelIds",channelids);
				}
				if (StringUtils.isNotEmpty(channelParPageMark)) {
					List<String> channelids=new ArrayList<String>();
					CmsChannel channel=cmsChannelService.findBySitePageMark(siteId, channelParPageMark);
					if (channel!=null) {
						channelids.add(channel.getId());
						List<CmsChannel> sonList=cmsChannelService.findSon(siteId, channel.getId(), "01", "");
						if (sonList!=null && sonList.size()>0) {
							for (int i = 0; i < sonList.size(); i++) {
								channelids.add(sonList.get(i).getId());
							}
						}
						info.getParamMap().put("channelIds",channelids);
					}
				}
				if (img.trim().length()>0) {
					info.getParamMap().put("img",img);
				}
				if (StringUtils.isNotEmpty(state)) {
					info.getParamMap().put("state",state);
				}
				
				//info.setCheckOpenendtime(getParam(params, "checkOpenendtime"));
				String orderSql="";
				if ("01".equals(hot)) {
					orderSql=" T.CLICK_NUM DESC,T.ADD_TIME DESC ";
				}else {
					if ("01".equals(order)) {
						//固顶有效并降序,发布时间降序(默认)
						orderSql=" T.IS_TOP DESC,T.ADD_TIME DESC";
					}
					else if ("02".equals(order)) {
						//固顶有效并降序,发布时间升序
						orderSql=" T.IS_TOP DESC,T.ADD_TIME";
					}
					else if ("03".equals(order)) {
						//发布时间倒序
						orderSql=" T.ADD_TIME DESC";
					}
					else if ("04".equals(order)) {
						//发布时间升序
						orderSql="T.ADD_TIME";
					}
				}
				//获取总数
				int count=cmsInfoService.count(info);
				FreemarkerPager pager=new FreemarkerPager();
				pager.setCurrPage(page);
				pager.setTotalCount(count);
				pager.setPageSize(num);
				pager.setUrl("index");
				if(StringUtils.isNotEmpty(url)){
					String[] urlStrings =StringUtils.split(url,".");
					pager.setUrl(urlStrings[0]);
				}
				//如果总页数大于最多显示页数，则设置总页数为最多显示页数
				if (pagenum>0 && pagenum<pager.getTotalPage()) {
					pager.setTotalPage(pagenum);
				}
				List<CmsInfo> infoList=cmsInfoService.find(info, orderSql, page, num);
				CmsSite site=cmsSiteService.read(siteId);
				if (infoList!=null && infoList.size()>0) {
					for (int i = 0; i < infoList.size(); i++) {
//						if (titleLen>0) {
//							infoList.get(i).setShowtitleLen(titleLen);
//						}
						if (dateFormat.trim().length()>0) {
							infoList.get(i).setDateFormat(dateFormat);
						}
//						if (newdays>0 && (DateUtil.differ(infoList.get(i).getAddtime(), new Date())/(1000*60*60*24))<newdays) {
//							//判断是否为最新新闻
//							infoList.get(i).setIsnew("1");
//						}
						//设置sitepath
						if (site!=null) {
							infoList.get(i).setSitePath(env.getDataModel().get("contextPath").toString()
									+"site/"+site.getSourcePath()+"/");
						}
						
						if(StringUtils.isNotEmpty(capture)&&captureLen>0){
							String content= getPlainText2(infoList.get(i).getContent());
							if(content!=null){
								if(content.length()>captureLen){
									infoList.get(i).setContent(StringUtils.substring(content, 0, captureLen)+"...");
								}else{
									infoList.get(i).setContent(StringUtils.substring(content, 0, content.length())+"...");
								}
							}
						}
						
						if(StringUtils.isNotEmpty(infoList.get(i).getImg())){
							try {
								Map<String, Object> temp=JsonUtil.fromJson(infoList.get(i).getImg(), Map.class);
								if(temp!=null&&(!temp.isEmpty())){
									infoList.get(i).setImg(ObjectUtils.toString(temp.get("fileId")));
								}
							} catch (IOException e) {
								e.printStackTrace();
							}
							
						}
					}
				}
				
				
//				//如果有下一页，则输入下一页标识
//				if (pager.getTotalPage()>page ) {
//					env.getOut().write(ChannelService.hasNextPage);
//				}
				loopVars[0]=new ArrayModel(infoList.toArray(),new BeansWrapper()); 
				if(loopVars.length>1){
					loopVars[1]=new BeanModel(pager,new BeansWrapper()); 
				}
				body.render(env.getOut());  
			}
		}
	}
	
	public static String getPlainText2(String str) {
		if(StringUtils.isNotEmpty(str)){
			try {
				Parser parser = new Parser();
				parser.setInputHTML(str);
				TextExtractingVisitor visitor = new TextExtractingVisitor();
				parser.visitAllNodesWith(visitor);
				str = visitor.getExtractedText();
			} catch (ParserException e) {
				e.printStackTrace();
			}
		}
		return str;
	}
}