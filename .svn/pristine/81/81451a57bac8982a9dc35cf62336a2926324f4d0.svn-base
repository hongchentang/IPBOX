package com.hcis.ipr.cms.channel.service.impl;


import java.io.File;
import java.io.IOException;
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

import com.hcis.ipanther.common.attachment.entity.ReceiveParam;
import com.hcis.ipanther.common.attachment.entity.SendParam;
import com.hcis.ipanther.common.attachment.service.IAttachmentService;
import com.hcis.ipanther.common.attachment.utils.AttachmentUtils;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.CommonConfig;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.cms.channel.dao.CmsChannelDao;
import com.hcis.ipr.cms.channel.entity.CmsChannel;
import com.hcis.ipr.cms.channel.service.ICmsChannelService;
import com.hcis.ipr.cms.common.utils.FreeMarkerUtil;
import com.hcis.ipanther.common.log.utils.CommonLogUtils;
import com.hcis.ipr.cms.site.entity.CmsSite;
import com.hcis.ipr.cms.site.service.ICmsSiteService;

import freemarker.template.TemplateException;


@Service("cmsChannelService")
public class CmsChannelServiceImpl extends BaseServiceImpl<CmsChannel> implements ICmsChannelService {

	@Autowired
	private CmsChannelDao baseDao;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private ICmsSiteService cmsSiteService;

	@Override
	public String uploadFile(CmsChannel cmsChannel, MultipartFile[] file,String basePath) {
		if(file!=null&&cmsChannel!=null){
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
					
					ReceiveParam receiveParam=  AttachmentUtils.setReceiveParam("/upload/cms/channel/img/");
					String info = attachmentService.validateAttachment(tempFile,file[i].getOriginalFilename(), receiveParam);
		        	if(StringUtils.isNotEmpty(info)){
		        		return info;
		        	}
		    		SendParam sendParam =null;
					sendParam = attachmentService.uploadAttachment(tempFile,file[i].getOriginalFilename(),receiveParam,"");
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
					cmsChannel.setImg(JsonUtil.toJson(list));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(CollectionUtils.isEmpty(list)){
				cmsChannel.setImg("-1");//清空附件数据
			}
		}
		return null;
	}
	
	@Override
	public CmsChannelDao getBaseDao() {
		return baseDao;
	}

	@Override
	public boolean hasPagemark(String siteId, String pageMark) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("siteId",siteId);
		map.put("pageMark",pageMark);
		int count=baseDao.selectHasPagemark(map);
		return count>0?true:false;
	}
	
	@Override
	public Response create(CmsChannel cmsChannel, MultipartFile[] imgUpload, String userId,String basePath) {
		//判断页面标识是否存在
		if(cmsChannel!=null&&StringUtils.isNotEmpty(cmsChannel.getPageMark())&&hasPagemark(cmsChannel.getSiteId(), cmsChannel.getPageMark())){
			return Response.newDefaultResponse(0,"当前页面标识已存在");
		}
		//处理文件
		String meagsse=uploadFile(cmsChannel,imgUpload,basePath);
		if(StringUtils.isNotEmpty(meagsse)){
			Response.newDefaultResponse(0,meagsse);
		}
		int updateRecCount=create(cmsChannel, userId);
		if(updateRecCount>0){
			
		}
		return Response.newDefaultResponse(updateRecCount);
	}

	@Override
	public Response update(CmsChannel cmsChannel, MultipartFile[] imgUpload, String userId,String realPath,CmsSite cmsSite,String basePath) {
		//判断页面标识是否存在
		 CmsChannel oldChannel=read(cmsChannel.getId());
		//如果原来有和现在的pagemark不同则判断新的pagemark是否存在
		 if(cmsChannel!=null&&oldChannel!=null&&(!StringUtils.equals(cmsChannel.getPageMark(), oldChannel.getPageMark()))){
			 if (hasPagemark(cmsChannel.getSiteId(), cmsChannel.getPageMark())) {
					return Response.newDefaultResponse(0,"当前页面标识已存在");
				}
			 //修改栏目静态文件目录
			 String folder="";
			 File folderFile=null;
			 if (StringUtils.isNotEmpty(oldChannel.getPageMark())) {
				folder=oldChannel.getPageMark().trim();
				folderFile=new File(realPath+"/site/"+cmsSite.getSourcePath()+"/"+folder);
			 }
			 if ((folderFile==null || !folderFile.exists()) && oldChannel.getIndexNum()>0) {
				folder=String.valueOf(oldChannel.getIndexNum());
				folderFile=new File(realPath+"/site/"+cmsSite.getSourcePath()+"/"+folder);
			 }
			 if (folderFile==null || !folderFile.exists()) {
				folder=oldChannel.getId();
				folderFile=new File(realPath+"/site/"+cmsSite.getSourcePath()+"/"+folder);
			}
			//判断目录是否存在
			if (folderFile.exists()) {
				//修改目录名
				folderFile.renameTo(new File(realPath
						+"/site/"+cmsSite.getSourcePath()+"/"+cmsChannel.getPageMark().trim()));
			}
		 }
		 
		//如果原来有pagemark，现在删除了
		 if (StringUtils.isNotEmpty(oldChannel.getPageMark())&&
			 StringUtils.isEmpty(cmsChannel.getPageMark())) {
				//修改栏目静态文件目录
				String folder="";
				File folderFile=null;
				folder=oldChannel.getPageMark().trim();
				folderFile=new File(realPath+"/site/"+cmsSite.getSourcePath()+"/"+folder);
				//判断目录是否存在
				if (folderFile.exists()) {
					//修改目录名
					String rename="";
					if (oldChannel.getIndexNum()>0) {
						rename=String.valueOf(oldChannel.getIndexNum());
					}else {
						rename=oldChannel.getId();
					}
					folderFile.renameTo(new File(realPath
							+"/site/"+cmsSite.getSourcePath()+"/"+rename));
				}
		 }
		
		//处理文件
		String meagsse=uploadFile(cmsChannel,imgUpload,basePath);
		if(StringUtils.isNotEmpty(meagsse)){
			Response.newDefaultResponse(0,meagsse);
		}
		
		int updateRecCount=update(cmsChannel, userId);
		if(updateRecCount>0){
			
		}
		return Response.newDefaultResponse(updateRecCount);
	}

	@Override
	public Response html(String siteId, String channelId, ServletContext servletContext,String contextPath,String userId,String ip) throws IOException, TemplateException {
		CmsSite cmsSite=cmsSiteService.read(siteId);
		CmsChannel cmsChannel=read(channelId);
		if(cmsSite!=null&&cmsChannel!=null&&StringUtils.isNotEmpty(cmsSite.getIndexTemplet())){
			
			String templetPath="/templet/"+cmsSite.getIndexTemplet().trim()+"/channel.html";
			if (StringUtils.isNotEmpty(cmsChannel.getTempletId())) {
				templetPath="/templet/"+cmsSite.getIndexTemplet().trim()+"/"+cmsChannel.getTempletId().trim();
			}
			//判断模板文件是否存在
			File templetFile=new File(servletContext.getRealPath("/")+templetPath);
			cmsChannel.setSitePath(servletContext.getRealPath("")+"/site/"+cmsSite.getSourcePath()+"/");
			if (templetFile.exists()) {
				//先生成第一页
				return htmlPage(cmsSite, cmsChannel, servletContext,contextPath,  templetPath, userId,1,ip);
			}
		}
		return Response.failInstance();
	}

	@Override
	public Response htmlPage(CmsSite cmsSite, CmsChannel cmsChannel, ServletContext servletContext,String contextPath, String templetPath,String userId,
			int pageNum,String ip) throws IOException, TemplateException {
		if(cmsSite!=null&&cmsChannel!=null&&StringUtils.isNotEmpty(cmsSite.getIndexTemplet())){
			if (cmsChannel.getMaxPage()==0 || (cmsChannel.getMaxPage()>0 && cmsChannel.getMaxPage()>=pageNum)) {
				//生成静态页面
				Map<String,Object> data=new HashMap<String,Object>();
				//传递site参数
				data.put("site", cmsSite);
				data.put("currChannel", cmsChannel);
				data.put("page", pageNum);
				if (cmsChannel.getMaxPage()>0) { 
					data.put("pagenum", cmsChannel.getMaxPage());
				}
				data.put("contextPath", contextPath);
				data.put("contextPathNo", contextPath);
				String rootPath=servletContext.getRealPath("/")+"/site/"+cmsSite.getSourcePath()+"/"+cmsChannel.getFolder()+"/";
				//判断栏目文件夹是否存在
				File channelFolder=new File(rootPath);
				if (!channelFolder.exists()) {
					channelFolder.mkdirs();
				}
				FreeMarkerUtil.createHTML(servletContext, data, 
						templetPath, 
						rootPath+"index"+(pageNum>1?"_"+(pageNum-1):"")+".html");
				CommonLogUtils.log(cmsSite.getId(),cmsChannel.getId(),"栏目静态化:"+cmsChannel.getName()+" 第"+pageNum+"页",  userId,ip);
				if (cmsChannel.getMaxPage()>1) {
					htmlPage(cmsSite, cmsChannel, servletContext,contextPath, templetPath,userId, pageNum+1,ip);
				}
//				String content = CmsCommonFileUtil.readFile(rootPath+"index"+(pageNum>1?"_"+(pageNum-1):"")+".html");
//				//如果内容里有<!--hasNextPage-->字符串则需要生成下一页
//				if (content.indexOf(hasNextPage)>-1) {
//					htmlPage(site, channel, context, templetPath, page+1);
//				}
			}
		}
		return Response.failInstance();
	}

	@Override
	public Response html(CmsSite cmsSite, CmsChannel cmsChannel, ServletContext servletContext,String contextPath, String userId,
			int pageNum,String ip) throws IOException, TemplateException {
		if(cmsSite!=null&&cmsChannel!=null&&StringUtils.isNotEmpty(cmsSite.getIndexTemplet())){
			
			String templetPath="/templet/"+cmsSite.getIndexTemplet().trim()+"/channel.html";
			if (StringUtils.isNotEmpty(cmsChannel.getTempletId())) {
				templetPath="/templet/"+cmsSite.getIndexTemplet().trim()+"/"+cmsChannel.getTempletId().trim();
			}
			//判断模板文件是否存在
			File templetFile=new File(servletContext.getRealPath("/")+templetPath);
			if (!templetFile.exists()) {
				templetFile=new File(servletContext.getRealPath("/")+"/"+templetPath);
			}
			cmsChannel.setSitePath(servletContext.getContextPath()+"/site/"+cmsSite.getSourcePath()+"/");
			if (templetFile.exists()) {
				//先生成第一页
				return htmlPage(cmsSite, cmsChannel, servletContext,contextPath,  templetPath,userId, 1,ip);
			}
		}
		return Response.failInstance();
	}

	@Override
	public Response htmlChannels(CmsSite cmsSite, SearchParam searchParam, ServletContext servletContext,
			String contextPath, String remoteAddr, String userId) {
		List<CmsChannel> list=new ArrayList<CmsChannel>();
		if(searchParam.getParamMap().containsKey("channelIds")){
			String channelIds= ObjectUtils.toString(searchParam.getParamMap().get("channelIds"));
			if(StringUtils.isNotEmpty(channelIds)){
				String[] ids=channelIds.split(",");
				if(ids!=null&&ids.length>0){
					 searchParam.getParamMap().put("channelIds", ids);
					 list=list(searchParam);
				}
			}else{
				 searchParam.getParamMap().put("channelIds", null);
			}
		}
		
		if(searchParam.getParamMap().containsKey("isAll")){
			String isAll= ObjectUtils.toString(searchParam.getParamMap().get("isAll"));
			if(StringUtils.isNotEmpty(isAll)&&StringUtils.equals(isAll,"01")){
				searchParam.getParamMap().put("siteId",cmsSite.getId());
				 list=list(searchParam);
			}
		}
		if(CollectionUtils.isNotEmpty(list)){
			for (CmsChannel cmsChannel : list) {
				try {
					html(cmsSite, cmsChannel, servletContext,contextPath, userId, 0, remoteAddr);
				} catch (IOException | TemplateException e) {
					e.printStackTrace();
				}
			}
			return Response.newDefaultResponse(1,"静态化成功");
		}
		return Response.newDefaultResponse(0,"静态化失败");
	}

	@Override
	public CmsChannel findBySitePageMark(String siteId, String pageMark) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("siteId", siteId);
		map.put("pageMark", pageMark);
		return baseDao.findBySitePageMark(map);
	}

	@Override
	public boolean hasChildren(String channelId) {
		List<CmsChannel> cmsChannels= baseDao.findHasChildren(channelId);
		if(cmsChannels!=null&&(!cmsChannels.isEmpty())){
			return true;
		}
		return false;
	}

	@Override
	public List<CmsChannel> findByParent(String siteId, String parId, String state, String navigation) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("siteId", siteId);
		if(!StringUtils.equals(parId,"par")){
			map.put("parentId", parId);
		}
		map.put("navigation", navigation);
		map.put("state", state);
		return baseDao.findByParent(map);
	}

	@Override
	public List<CmsChannel> findPath(String id) {
		List<CmsChannel> channelList=new ArrayList<CmsChannel>();
		channelList=findParentPath(id, channelList);
		if (channelList!=null && channelList.size()>0) {
			//把对象倒序，实现栏目级别从父到子
			List<CmsChannel> channelListTemp=new ArrayList<CmsChannel>();
			for (int i =channelList.size()-1; i >=0 ; i--) {
				channelListTemp.add(channelList.get(i));
			}
			channelList=channelListTemp;
		}
		return channelList;
	}
	
	/**
	 * 查询栏目路径(递归方法)
	 * @return
	 */
	public List<CmsChannel> findParentPath(String id,List<CmsChannel> channelList){
		CmsChannel channel=read(id);
		if (channel!=null) {
			channelList.add(channel);
			//如果有父栏目则递归提取
			if (StringUtils.isNotEmpty(channel.getParentId())) {
				findParentPath(channel.getParentId(), channelList);
			}
		}
		return channelList;
	}

	@Override
	public List<CmsChannel> findSon(String siteId, String parId, String state, String navigation) {
		List<CmsChannel> list=new ArrayList<CmsChannel>();
		return findSonPro(list,siteId, parId, state, navigation);
	}
	
	@Override
	public List<CmsChannel> findSonPro(List<CmsChannel> list, String siteId, String parId, String state,
			String navigation) {
		List<CmsChannel> sonlist=findByParent(siteId, parId, state, navigation);
		if (sonlist!=null && sonlist.size()>0) {
			for (int i = 0; i < sonlist.size(); i++) {
				list.add(sonlist.get(i));//添加到总集合中
				//处理子栏目
				findSonPro(list, siteId, sonlist.get(i).getId(), state, navigation);
			}
		}
		return list;
	}
	
}
