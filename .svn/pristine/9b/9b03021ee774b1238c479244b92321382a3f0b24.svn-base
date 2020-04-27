package com.hcis.ipr.cms.freemarker.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import com.hcis.ipanther.core.utils.BeanLocator;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.cms.channel.entity.CmsChannel;
import com.hcis.ipr.cms.channel.service.ICmsChannelService;
import com.hcis.ipr.cms.common.utils.CmsCommonDateUtil;
import com.hcis.ipr.cms.common.utils.CmsCommonDirective;
import com.hcis.ipr.cms.info.entity.CmsInfo;
import com.hcis.ipr.cms.info.service.ICmsInfoService;
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
 * <p>Title: InfoListDirective.java</p>
 * 
 * <p>Description: 信息列表标签
 * 参数
 * siteid		站点id
 * channelid	栏目id
 * channelParid	栏目parid
 * num			显示数量
 * order		排序类型  
 * 				1 固顶有效并降序,发布时间降序(默认)
 * 				2 固顶有效并降序,发布时间升序
 * 				3 发布时间降序
 * 				4 发布时间升序
 * titleLen		标题显示长度
 * hot			是否按点击热度倒序，1是
 * dateFormat	日期格式
 * channelPagemark	栏目页面标识
 * channelParPagemark	父栏目页面标识
 * img			是否只提取带图片的新闻	1是
 * checkOpenendtime	检查公开时限 默认不检查，1检查
 * newdays		几天内为最新
 * 
 * 返回值
 * info			信息对象
 * index		索引
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
public class InfoListDirective extends CmsCommonDirective implements TemplateDirectiveModel{

	
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
		String channelParId=getParam(params, "channelParId");
		//显示数量
		int num=getParamInt(params, "num", 10);
		//排序
		String order=getParam(params, "order","01");
		//标题长度
		int titleLen=getParamInt(params, "titleLen",0);
		//几天内为最新
		int newdays=getParamInt(params, "newdays",0);
		//是否按点击热度查询
		String hot=getParam(params, "hot");
		//日期格式
		String dateFormat=getParam(params, "dateFormat");
		//栏目页面标识
		String channelPageMark=getParam(params, "channelPageMark");
		String channelParPageMark=getParam(params, "channelParPageMark");
		//是否只提取带图片的信息
		String img=getParam(params, "img");
		//是否发布
		String state=getParam(params, "state","01");
		
		Writer out =env.getOut();
		if (body!=null) {
			//设置循环变量
			if (loopVars!=null && loopVars.length>0 ) {
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
				if (StringUtils.isNotEmpty(img)) {
					if(img.equals("true")){
						info.getParamMap().put("img","true");
					}else{
						info.getParamMap().put("img","false");
					}
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
				List<CmsInfo> infoList=cmsInfoService.find(info, orderSql, 1, num);
				CmsSite site=cmsSiteService.read(siteId);
				if (infoList!=null && infoList.size()>0) {
					for (int i = 0; i < infoList.size(); i++) {
						if (titleLen>0) {
							infoList.get(i).setShowTitleLen(titleLen);
						}
						if (dateFormat.trim().length()>0) {
							infoList.get(i).setDateFormat(dateFormat);
						}
						if (newdays>0 && 
								(CmsCommonDateUtil.differ(infoList.get(i).getAddTime(), new Date())/(1000*60*60*24))<newdays) {
							//判断是否为最新新闻
							infoList.get(i).setIsNew("01");
						}
						//设置sitepath
						if (site!=null) {
							infoList.get(i).setSitePath(env.getDataModel().get("contextPath").toString()
									+"site/"+site.getSourcePath()+"/");
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
						loopVars[0]=new BeanModel(infoList.get(i),new BeansWrapper());  
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
