package com.hcis.ipr.cms.channel.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.web.multipart.MultipartFile;

import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.cms.channel.entity.CmsChannel;
import com.hcis.ipr.cms.site.entity.CmsSite;

import freemarker.template.TemplateException;

public interface ICmsChannelService extends IBaseService<CmsChannel> {

	/**
	 * Upload file.文件处理
	 *
	 * @param cmsChannel the cms channel
	 * @param img the img
	 * @return the string
	 */
	String uploadFile(CmsChannel cmsChannel, MultipartFile[] img,String basePath);

	/**
	 * Checks for pagemark. 检查页面标识 (唯一约束)
	 *
	 * @param siteId the site id
	 * @param pageMark the page mark
	 * @return true, if successful
	 */
	boolean hasPagemark(String siteId, String pageMark);

	/**
	 * Creates the.自定义 新增方法
	 *
	 * @param cmsChannel the cms channel
	 * @param imgUpload the img upload
	 * @param userId the user id
	 * @return the response
	 */
	Response create(CmsChannel cmsChannel, MultipartFile[] imgUpload, String userId,String basePath);

	/**
	 * Update.自定义更新方法
	 *
	 * @param cmsChannel the cms channel
	 * @param imgUpload the img upload
	 * @param userId the user id
	 * @param realPath the real path
	 * @param cmsSite the cms site
	 * @return the response
	 */
	Response update(CmsChannel cmsChannel, MultipartFile[] imgUpload, String userId,String realPath,CmsSite cmsSite,String basePath);

	/**
	 * Html.静态化方法 (重载)
	 *
	 * @param siteId the site id
	 * @param channeId the channe id
	 * @param servletContext the servlet context
	 * @param userId the user id
	 * @param ip the ip
	 * @return the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws TemplateException the template exception
	 */
	Response html(String siteId, String channeId, ServletContext servletContext,String contextPath ,String userId , String ip) throws IOException, TemplateException;
	
	/**
	 * Html.静态化方法  (重载)
	 *
	 * @param cmsSite the cms site
	 * @param cmsChannel the cms channel
	 * @param servletContext the servlet context
	 * @param userId the user id
	 * @param pageNum the page num
	 * @param ip the ip
	 * @return the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws TemplateException the template exception
	 */
	Response html(CmsSite cmsSite, CmsChannel cmsChannel, ServletContext servletContext,String contextPath, String userId,
			int pageNum, String ip) throws IOException, TemplateException;
	
	/**
	 * Html page.生成静态页
	 *
	 * @param cmsSite the cms site
	 * @param cmsChannel the cms channel
	 * @param servletContext the servlet context
	 * @param templetPath the templet path
	 * @param userId the user id
	 * @param pageNum the page num
	 * @param ip the ip
	 * @return the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws TemplateException the template exception
	 */
	Response htmlPage(CmsSite cmsSite, CmsChannel cmsChannel, ServletContext servletContext ,String contextPath,String templetPath, String userId,int pageNum, String ip) throws IOException, TemplateException;

	/**
	 * Html channels. 静态化栏目 (支持批量)
	 *
	 * @param cmsSite the cms site
	 * @param searchParam the search param
	 * @param servletContext the servlet context
	 * @param contextPath the context path
	 * @param remoteAddr the remote addr
	 * @param userId the user id
	 * @return the response
	 */
	Response htmlChannels(CmsSite cmsSite, SearchParam searchParam, ServletContext servletContext, String contextPath,
			String remoteAddr, String userId);

	/**
	 * Find by site page mark. 根据站点Id(siteId) 页面标识(pageMark)查询
	 *
	 * @param siteId the site id
	 * @param pageMark the page mark
	 * @return the cms channel
	 */
	CmsChannel findBySitePageMark(String siteId, String pageMark);

	/**
	 * Checks for children. 是否有下级栏目
	 *
	 * @param channelId the channel id
	 * @return true, if successful
	 */
	boolean hasChildren(String channelId);

	/**
	 * Find by parent.
	 *
	 * @param siteId the site id
	 * @param parId the par id
	 * @param state the state
	 * @param navigation the navigation
	 * @return the list
	 */
	List<CmsChannel> findByParent(String siteId, String parId, String state, String navigation);

	/**
	 * Find path.
	 *
	 * @param id the id
	 * @return the list
	 */
	List<CmsChannel> findPath(String id);

	/**
	 * Find son. 查询所有子栏目 (递归)
	 *
	 * @param siteId the site id
	 * @param parId the par id
	 * @param state the state
	 * @param navigation the navigation
	 * @return the list
	 */
	List<CmsChannel> findSon(String siteId, String parId, String state, String navigation);

	/**
	 * Find son pro. 查询所有子栏目
	 *
	 * @param list the list
	 * @param siteId the site id
	 * @param parId the par id
	 * @param state the state
	 * @param navigation the navigation
	 * @return the list
	 */
	List<CmsChannel> findSonPro(List<CmsChannel> list, String siteId, String parId, String state, String navigation);
}
