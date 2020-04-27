package com.hcis.ipr.cms.info.service.impl;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springside.modules.utils.Collections3;

import com.hcis.ipanther.common.attachment.entity.ReceiveParam;
import com.hcis.ipanther.common.attachment.entity.SendParam;
import com.hcis.ipanther.common.attachment.service.IAttachmentService;
import com.hcis.ipanther.common.attachment.utils.AttachmentUtils;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.CommonConfig;
import com.hcis.ipanther.core.utils.HtmlUtils;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.cms.channel.entity.CmsChannel;
import com.hcis.ipr.cms.channel.service.ICmsChannelService;
import com.hcis.ipr.cms.common.utils.FreeMarkerUtil;
import com.hcis.ipr.cms.info.dao.CmsInfoDao;
import com.hcis.ipr.cms.info.entity.CmsInfo;
import com.hcis.ipr.cms.info.service.ICmsInfoService;
import com.hcis.ipanther.common.log.utils.CommonLogUtils;
import com.hcis.ipr.cms.site.entity.CmsSite;
import com.hcis.ipr.cms.site.service.ICmsSiteService;
import com.hcis.ipr.core.entity.Response;

import freemarker.template.TemplateException;

@Service("cmsInfoService")
public class CmsInfoServiceImpl extends BaseServiceImpl<CmsInfo> implements ICmsInfoService {

	@Autowired
	private CmsInfoDao baseDao;
	@Autowired
	private  IAttachmentService attachmentService;
	@Autowired
	private ICmsChannelService cmsChannelService;
	@Autowired
	private ICmsSiteService cmsSiteService;
	
	@Override
	public String uploadFile(CmsInfo cmsInfo, MultipartFile[] file,String basePath) {
		
		if(file!=null&&cmsInfo!=null){
			 List<Map<String, String>> list=new  ArrayList<Map<String, String>>();
			 //List<Map<String, String>> oldFile=SHIXJLServiceImpl.updateAttachment(shouxjl!=null?shouxjl.getAttachment():null,attachmentService);
//			 if(oldFile!=null){
//				 list.addAll(oldFile);
//			 }
			 for(int i = 0;i<file.length;i++){  
		        if(!file[i].isEmpty()){  
					String tempFileName=FileUtils.getTempDirectory().getPath()+"/"+file[i].getName();
					File tempFile=new File(tempFileName);
					try {
						file[i].transferTo(tempFile);
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					ReceiveParam receiveParam=  AttachmentUtils.setReceiveParam("/upload/cms/info/attchs/");
					//文件类型免验证-wuwentao
					/*String info = attachmentService.validateAttachment(tempFile,file[i].getOriginalFilename(), receiveParam);
		        	if(StringUtils.isNotEmpty(info)){
		        		return info;
		        	}*/
		    		SendParam sendParam =null;
					sendParam = attachmentService.uploadAttachment(tempFile,file[i].getOriginalFilename(),receiveParam,basePath);
					if(sendParam==null){
						return CommonConfig.getProperty("attachment.upload.error");
					}else{
						Map<String, String> map=new HashMap<String, String>();
						 map.put("attachmentId", sendParam.getAttachmentId());
						 map.put("attachmentName", sendParam.getAttachmentName());
						 map.put("downloadUrl", sendParam.getDownloadUrl());
						 map.put("fileId", sendParam.getFileId());
						 map.put("billId", sendParam.getBillId());
						 map.put("groupId", sendParam.getGroupId());
						 map.put("status", sendParam.getStatus());
						 list.add(map);
					}
		        }  
			 }
			
			if(CollectionUtils.isNotEmpty(list)){
				try {
					cmsInfo.setAttchs(JsonUtil.toJson(list));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if(CollectionUtils.isEmpty(list)){
				cmsInfo.setAttchs("-1");//清空附件数据
			}
		}
		return null;
	}
	
	
	@Override
	public CmsInfoDao getBaseDao() {
		return baseDao;
	}


	@Override
	public Response html(CmsInfo cmsInfo, ServletContext servletContext, String realPath, String remoteAddr,
			String userId) {
		//查询信息
			cmsInfo=read(cmsInfo.getId());
			if (cmsInfo!=null) {
					if(StringUtils.isNotEmpty(cmsInfo.getImg())){
						try {
							Map<String, Object> temp=JsonUtil.fromJson(cmsInfo.getImg(), Map.class);
							if(temp!=null&&(!temp.isEmpty())){
								cmsInfo.setImg(ObjectUtils.toString(temp.get("fileId")));
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
					CmsChannel cmsChannel=cmsChannelService.read(cmsInfo.getChannelId());
					String templet= cmsInfo.getTempletId();
					//判断info是否有信息页模板
					if (StringUtils.isEmpty(templet)&&StringUtils.isNotEmpty(cmsChannel.getContentTempletId())) {
						templet=cmsChannel.getContentTempletId();
					}else if (StringUtils.isEmpty(templet)&&StringUtils.isNotEmpty(cmsChannel.getTempletId())) {
						templet=cmsChannel.getTempletId();
					}
					CmsSite cmsSite=cmsSiteService.read(cmsInfo.getSiteId());
					if (cmsSite!=null && StringUtils.isNotEmpty(cmsSite.getIndexTemplet())) {
						//生成静态页面
						Map<String,Object> data=new HashMap<String,Object>();
						//传递site参数
						data.put("site", cmsSite);
						cmsChannel.setSitePath("/site/"+cmsSite.getSourcePath()+"/");
						data.put("currChannel", cmsChannel);
						data.put("currInfo", cmsInfo);
						data.put("contextPath", realPath);
						data.put("contextPathNo", realPath);
						//生成目录
						SimpleDateFormat yearFormat=new SimpleDateFormat("yyyy");
						SimpleDateFormat mmFormat=new SimpleDateFormat("MM");
						
						String rootFolder=servletContext.getRealPath("/")+"/site/"+cmsSite.getSourcePath()+"/"+cmsInfo.getChannelFolder()+"/info/"+ yearFormat.format(cmsInfo.getAddTime())+"/"+mmFormat.format(cmsInfo.getAddTime())+"/";
						File folder=new File(rootFolder);
						if (!folder.exists()) {
							folder.mkdirs();
						}
						try {
							FreeMarkerUtil.createHTML(servletContext, data, 
									"/templet/"+cmsSite.getIndexTemplet().trim()+"/"+templet, 
									rootFolder+cmsInfo.getHtmlFileName()+".html");
						} catch (IOException | TemplateException e) {
							e.printStackTrace();
						}
						 CommonLogUtils.log(cmsSite.getId() ,cmsChannel.getId(),cmsInfo.getId(),"信息页静态化:"+cmsInfo.getTitle(),userId ,remoteAddr);
						return Response.newDefaultResponse(1,"静态化成功");
					}
				}
		return Response.newDefaultResponse(0,"静态化失败");
	}


	@Override
	public Response create(CmsInfo cmsInfo, MultipartFile[] upload, ServletContext servletContext, String realPath,
		String remoteAddr, String userId) {
		int count=0;
		if(cmsInfo!=null){
			String meagsse=uploadFile(cmsInfo,upload,servletContext.getRealPath("/"));
			if(StringUtils.isNotEmpty(meagsse)){
				return Response.newDefaultResponse(0,meagsse);
			}
			 if(StringUtils.isEmpty(cmsInfo.getDescription())){
				String content =HtmlUtils.getHtmlForText(cmsInfo.getContent());
				 cmsInfo.setDescription(StringUtils.abbreviate(content, 100));
			 }
			 count=create(cmsInfo, userId);
			 if(count>0){
				 //新增后，静态化
				 try {
					 html(cmsInfo, servletContext, realPath, remoteAddr, userId);//静态化信息
					 cmsChannelService.html(cmsInfo.getSiteId(), cmsInfo.getChannelId(), servletContext,realPath, userId, remoteAddr);
					 cmsSiteService.html(cmsInfo.getSiteId(), servletContext, servletContext.getContextPath()+"/",  userId,remoteAddr);
				 } catch (IOException | TemplateException e) {
					e.printStackTrace();
				}
			 }
		}
		return Response.newDefaultResponse(count);
	}


	@Override
	public Response update(CmsInfo cmsInfo, MultipartFile[] upload, ServletContext servletContext, String realPath,
		String remoteAddr, String userId) {
			int count=0;
			if(cmsInfo!=null){
				String meagsse=uploadFile(cmsInfo,upload,servletContext.getRealPath("/"));
				if(StringUtils.isNotEmpty(meagsse)){
					return Response.newDefaultResponse(0,meagsse);
				}
				 if(StringUtils.isEmpty(cmsInfo.getDescription())){
					String content =HtmlUtils.getHtmlForText(cmsInfo.getContent());
					 cmsInfo.setDescription(StringUtils.abbreviate(content, 100));
				 }
				 count=update(cmsInfo, userId);
				 /*if(count>0){
					 //修改后，重新静态化
					 try {
						html(cmsInfo, servletContext, realPath, remoteAddr, userId);//静态化信息
						cmsChannelService.html(cmsInfo.getSiteId(), cmsInfo.getChannelId(), servletContext,realPath, userId, remoteAddr);
						cmsSiteService.html(cmsInfo.getSiteId(), servletContext, servletContext.getContextPath()+"/", remoteAddr, userId);
					} catch (IOException | TemplateException e) {
						e.printStackTrace();
					}
					 
				 }*/
			}
		return Response.newDefaultResponse(count);
	}


	@Override
	public Response htmlInfo(CmsSite cmsSite, SearchParam searchParam, ServletContext servletContext,
			String contextPath, String remoteAddr, String userId) {
		Response response=Response.newDefaultResponse(0,"静态失败");
		if(cmsSite!=null){
			String[] channelIds=null;
			if(!searchParam.getParamMap().containsKey("siteId")){
				searchParam.getParamMap().put("siteId", cmsSite.getId());
			}
			
			if(searchParam.getParamMap().containsKey("channelIds")){
				String ids= ObjectUtils.toString(searchParam.getParamMap().get("channelIds"));
				if(StringUtils.isNotEmpty(ids)){
					channelIds=ids.split(",");
				}
				if(channelIds!=null){
					searchParam.getParamMap().put("channelIds", channelIds);
				}
			}
			if(searchParam.getParamMap().containsKey("isAll")){
				String isAll=ObjectUtils.toString(searchParam.getParamMap().get("isAll"));
				if(StringUtils.equals("02", isAll)){
					List<CmsChannel> channels=cmsChannelService.list(searchParam);
					List<String> list=	Collections3.extractToList(channels, "id");
					searchParam.getParamMap().put("channelIds", list);
				}
			}
			searchParam.setPageAvailable(false);//不分页
			List<CmsInfo> list=list(searchParam);
			if(list!=null&&(!list.isEmpty())){
				for (CmsInfo cmsInfo : list) {
					response=html(cmsInfo, servletContext, contextPath, remoteAddr, userId);
				}
			}
		}
		return response;
	}


	@Override
	public List<CmsInfo> find(SearchParam info, String orderSql, int pageNum, int pageSizeNum) {
		if(info.getPagination()==null){
			Pagination pagination=new Pagination();
			info.setPagination(pagination);
		}
		info.getPagination().setPageSize(pageSizeNum);
		info.getPagination().setCurrentPage(pageNum);
		info.getParamMap().put("orderSql", orderSql);
		return list(info);
	}


	@Override
	public int count(SearchParam info) {
		List<CmsInfo> list= list(info);
		if(list!=null&&(!list.isEmpty())){
			if(info.getPagination()!=null){
				return info.getPagination().getRowCount();
			}
		}
		return 0;
	}


	@Override
	public String updateClick(CmsInfo cmsInfo, String userId) {
		if(cmsInfo!=null){
			CmsInfo info=read(cmsInfo.getId());
			if(info!=null){
				Long i= info.getClickNum();
				if(i==null){
				    i=new Long(0);	
				 }
				 i++;
				 info.setClickNum(i);
				 int count=update(info, userId);
				 if(count>0){
					 return  new String(i+"").trim();
				 }
				
			}
		}
		return "0";
	}


	@Override
	public List<CmsInfo> selectListInform(String siteId) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("siteId", siteId);
		map.put("pageMark", "dynamic");
		List<CmsInfo> infoList=baseDao.selectListCmsInfo(map);
		if(infoList!=null){
			for (int i = 0; i < infoList.size(); i++) {
				infoList.get(i).setShowTitleLen(22);
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
			if (infoList.size()>10) {
				return infoList.subList(0, 9);
			}else{
				return infoList;
			}
		  }
		}
		return null;
	}


	@Override
	public List<CmsInfo> searchForCms(SearchParam searchParam) {
		String type = (String) searchParam.getParamMap().get("type");
		if(StringUtils.isNotEmpty(type)&&!StringUtils.equals(type, "dynamic")&&!StringUtils.equals(type, "inform")&&!StringUtils.equals(type, "course")) {
			return null;
		}
		return baseDao.searchForCms(searchParam);
	}
}
