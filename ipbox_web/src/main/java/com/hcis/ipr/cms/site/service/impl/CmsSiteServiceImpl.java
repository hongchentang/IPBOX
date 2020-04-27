package com.hcis.ipr.cms.site.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipr.cms.common.utils.CmsCommonUtils;
import com.hcis.ipr.cms.common.utils.FreeMarkerUtil;
import com.hcis.ipanther.common.log.utils.CommonLogUtils;
import com.hcis.ipr.cms.site.dao.CmsSiteDao;
import com.hcis.ipr.cms.site.entity.CmsSite;
import com.hcis.ipr.cms.site.service.ICmsSiteService;

import freemarker.template.TemplateException;



@Service("cmsSiteService")
public class CmsSiteServiceImpl extends BaseServiceImpl<CmsSite> implements ICmsSiteService {

	
	@Autowired
	private CmsSiteDao baseDao;
	
	@Override
	public int deleteMainSite(CmsSite cmsSite,String updateBy) {
		if(cmsSite!=null){
			if(StringUtils.isNotEmpty(updateBy)){
				cmsSite.setUpdatedby(updateBy);
			}
			cmsSite.setUpdateTime(new Date());
			return baseDao.deleteMainSite(cmsSite);
		}
		return 0;
	}

	@Override
	public CmsSite selectFirstSite() {
		return baseDao.selectFirstSite();
	}

	@Override
	public CmsSite selectMainSite() {
		return baseDao.selectMainSite();
	}
	
	@Override
	public CmsSite selectFirstByRoles(Map<String, Object> map) {
		return baseDao.selectFirstByRoles(map);
	}
	
	@Override
	public CmsSiteDao getBaseDao() {
		return baseDao;
	}

	@Override
	public Response html(String siteId, ServletContext servletContext, String contextPath, String ip,String userId) {
	    CmsSite site=read(siteId);
		if (site!=null && StringUtils.isNotEmpty(site.getIndexTemplet())) {
			//生成静态页面
			Map<String,Object> data=new HashMap<String,Object>();
			//传递site参数
			data.put("site", site);
			data.put("contextPath", contextPath);
			data.put("contextPathNo", contextPath);
			
			try {
				FreeMarkerUtil.createHTML(servletContext, data, 
						"/templet/"+site.getIndexTemplet().trim()+"/index.ftl", 
						servletContext.getRealPath("/")+"/site/"+site.getSourcePath()+"/index.html");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			CommonLogUtils.log(siteId, "首页静态化:"+site.getName(),userId, ip);
		//	OperLogUtil.log(operuser, "首页静态化:"+site.getName(), request);
		}
		return null;
	}
	
	
	@Override
	public Response html(CmsSite site, ServletContext servletContext, String contextPath, String ip,String userId) {
		if (site!=null && StringUtils.isNotEmpty(site.getIndexTemplet())) {
			//生成静态页面
			Map<String,Object> data=new HashMap<String,Object>();
			//传递site参数
			data.put("site", site);
			data.put("contextPath", contextPath);
			data.put("contextPathNo", contextPath);
			try {
				FreeMarkerUtil.createHTML(servletContext, data, 
						"/templet/"+site.getIndexTemplet().trim()+"/index.ftl", 
						CmsCommonUtils.replaceWinSeparator(servletContext.getRealPath("/"))+"/site/"+site.getSourcePath()+"/index.html");
			} catch (IOException e) {
				e.printStackTrace();
				return Response.failInstance();
			} catch (TemplateException e) {
				e.printStackTrace();
				return Response.failInstance();
			}
			CommonLogUtils.log(site.getId(),"首页静态化:"+site.getName(),userId, ip);
			return Response.newDefaultResponse(1, "首页静态化成功");
		//	OperLogUtil.log(operuser, "首页静态化:"+site.getName(), request);
		}
		return Response.failInstance();
	}

	@Override
	public boolean haveSourcePath(String sourcePath) {
		int count= baseDao.selectHaveSourcePath(sourcePath);
		return count>0?true:false;
	}

	@Override
	public CmsSite selectFirstSite(Map<String, Object> map) {
		return baseDao.selectFirstSite(map);
	}

}
