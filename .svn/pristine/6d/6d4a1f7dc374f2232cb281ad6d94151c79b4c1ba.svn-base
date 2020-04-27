package com.hcis.ipr.cms.info.service;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.web.multipart.MultipartFile;

import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.cms.info.entity.CmsInfo;
import com.hcis.ipr.cms.site.entity.CmsSite;

public interface ICmsInfoService extends IBaseService<CmsInfo> {

	String uploadFile(CmsInfo cmsInfo, MultipartFile[] upload,String basePath);

	Response html(CmsInfo cmsInfo, ServletContext servletContext, String realPath, String remoteAddr, String userId);

	Response create(CmsInfo cmsInfo, MultipartFile[] upload, ServletContext servletContext, String realPath,
			String remoteAddr, String string);

	Response update(CmsInfo cmsInfo, MultipartFile[] upload, ServletContext servletContext, String realPath,
			String remoteAddr, String string);

	Response htmlInfo(CmsSite cmsSite, SearchParam searchParam, ServletContext servletContext, String contextPath,
			String remoteAddr, String userId);

	List<CmsInfo> find(SearchParam info, String orderSql, int pageNum, int pageSizeNum);

	int count(SearchParam info);

	String updateClick(CmsInfo cmsInfo, String userId);

	List<CmsInfo> selectListInform(String siteId);
	
	/**
	 * 站点搜索
	 * @param searchParam
	 * @return
	 */
	public List<CmsInfo> searchForCms(SearchParam searchParam);
}
