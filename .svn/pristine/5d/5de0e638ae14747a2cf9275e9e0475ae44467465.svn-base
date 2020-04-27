package com.hcis.ipr.cms.site.service;

import java.util.Map;

import javax.servlet.ServletContext;

import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipr.cms.site.entity.CmsSite;

public interface ICmsSiteService extends IBaseService<CmsSite>{

	/**
	 * Select first site.  查询排序首个站点
	 *
	 * @return the cms site
	 */
	CmsSite selectFirstSite();

	/**
	 * Select first by roles.  查询站点 
	 *
	 * @param map the map
	 * @return the cms site
	 */
	CmsSite selectFirstByRoles(Map<String, Object> map);

	/**
	 * Delete main site 删除主站点
	 *
	 * @param cmsSite the cms site
	 * @param updateBy the update by
	 * @return the int
	 */
	int deleteMainSite(CmsSite cmsSite,String updateBy);

	/**
	 * Select main site.  查询主站点
	 *
	 * @return the cms site
	 */
	CmsSite selectMainSite();

	/**
	 * Html. 静态化方法 (重载)
	 *
	 * @param siteId the site id
	 * @param servletContext the servlet context
	 * @param contextPath the context path
	 * @param ip the ip
	 * @param userId the user id
	 * @return the response
	 */
	Response html(String siteId, ServletContext servletContext, String contextPath, String ip,
			String userId);

	/**
	 * Html. 静态化方法 (重载)
	 *
	 * @param site the site
	 * @param servletContext the servlet context
	 * @param contextPath the context path
	 * @param ip the ip
	 * @param userId the user id
	 * @return the response
	 */
	Response html(CmsSite site, ServletContext servletContext, String contextPath, String ip,
			String userId);

	/**
	 * Have source path. 检查 sourcePath(唯一约束)
	 *
	 * @param sourcePath the source path
	 * @return true, if successful
	 */
	boolean haveSourcePath(String sourcePath);

	/**
	 * Select first site.
	 *
	 * @param map the map
	 * @return the cms site
	 */
	CmsSite selectFirstSite(Map<String, Object> map);

}
