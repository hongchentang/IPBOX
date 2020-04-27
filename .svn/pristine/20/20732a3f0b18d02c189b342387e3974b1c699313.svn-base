package com.hcis.ipr.cms.templet.web;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.attachment.entity.ReceiveParam;
import com.hcis.ipanther.common.attachment.entity.SendParam;
import com.hcis.ipanther.common.attachment.service.IAttachmentService;
import com.hcis.ipanther.common.attachment.utils.AttachmentUtils;
import com.hcis.ipanther.core.utils.CommonConfig;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.cms.common.utils.CmsCommonFileUtil;
import com.hcis.ipr.cms.common.utils.CmsCommonUtils;
import com.hcis.ipr.cms.html.utils.HtmlCode;
import com.hcis.ipr.cms.site.entity.CmsSite;
import com.hcis.ipr.cms.site.service.ICmsSiteService;
import com.hcis.ipr.cms.templet.utils.TempletUtils;
import com.hcis.ipr.core.entity.Response;

@RequestMapping("/cms/templet")
@Controller
public class TempletController extends BaseController{
		
	@Autowired
	private ICmsSiteService cmsSiteService;
	@Autowired
	private IAttachmentService attachmentService;
	
	@RequestMapping("getTempletList")
	public ModelAndView getTempletList(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		CmsSite cmsSite=null;
		if(searchParam.getParamMap().containsKey("siteId")){
			String siteId=ObjectUtils.toString(searchParam.getParamMap().get("siteId"));
			cmsSite=cmsSiteService.read(siteId);
		}else{
			cmsSite=(CmsSite) getSession().getAttribute("manageSite");
		}
		
		String root =request.getRealPath("/");
		if(StringUtils.contains(root, "\\")){
			root = StringUtils.replace(root, "\\", "/");
		}
		
		if(searchParam.getParamMap().containsKey("realPath")){
			root=ObjectUtils.toString(searchParam.getParamMap().get("realPath"));
		} 
		 String currFolder=root.replace(root+"/templet/", "");
		if (currFolder.indexOf("/")>-1) {
			currFolder=currFolder.substring(currFolder.indexOf("/"));
		}else {
			currFolder="根目录";
		}
		
		modelAndView.addObject("cmsSite",cmsSite);
	
		modelAndView.addObject("paramMap",searchParam.getParamMap());
		return modelAndView;
	}
	
	@RequestMapping("getTempletListJson")
	@ResponseBody
	public String getTempletListJson(SearchParam searchParam,HttpServletResponse response){
		String  json="[]";
		String realPath=null;
		if(searchParam.getParamMap().containsKey("realPath")){
			realPath=ObjectUtils.toString(searchParam.getParamMap().get("realPath"));
		}else{
			realPath =request.getRealPath("/");
			if(StringUtils.contains(realPath, "\\")){
				realPath = StringUtils.replace(realPath, "\\", "/");
			}
		}
		List<Map<String,Object>> list=TempletUtils.getListTempletFile(realPath);
		try {
			json=JsonUtil.toJson(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.renderHtml(json, response);
	}
	
	
	@RequestMapping("getTempletDirectoryJson")
	@ResponseBody
	public String getTempletDirectoryJson(SearchParam searchParam,HttpServletResponse response){
		String  json="[]";
		CmsSite cmsSite=null;
		if(searchParam.getParamMap().containsKey("siteId")){
			String siteId=ObjectUtils.toString(searchParam.getParamMap().containsKey("siteId"));
			cmsSite=cmsSiteService.read(siteId);
		}else{
			cmsSite=(CmsSite) getSession().getAttribute("manageSite");
		}
		String realPath=null;
		if(searchParam.getParamMap().containsKey("realPath")){
			realPath=ObjectUtils.toString(searchParam.getParamMap().get("realPath"));
		}else{
			realPath =request.getRealPath("/");
			if(StringUtils.contains(realPath, "\\")){
				realPath = StringUtils.replace(realPath, "\\", "/");
			}
		}
		
		List<Map<String,Object>> list=TempletUtils.getListTempletDirectory(realPath);
		try {
			json=JsonUtil.toJson(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.renderHtml(json, response);
	}
	
	
	@RequestMapping("listTempletFile")
	public ModelAndView listTempletFile(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		String root =null;
		String realPath=request.getRealPath("/").replace("\\", "/");
		if(searchParam.getParamMap().containsKey("realPath")){
			root=ObjectUtils.toString(searchParam.getParamMap().get("realPath"));
		} 
		 String currFolder=StringUtils.replace(root, realPath+"/templet/", "");
		if (currFolder.indexOf("/")>-1) {
			currFolder=StringUtils.substringAfter(currFolder, "/");
		}else {
			currFolder="根目录";
		}
		modelAndView.addObject("currFolder", currFolder);
		modelAndView.addObject("resultList", CmsCommonUtils.getFiles(root,".ftl"));
		modelAndView.addObject("paramMap",searchParam.getParamMap());
		return modelAndView;
	}
	
	@RequestMapping("listTemplet")
	public ModelAndView listTemplet(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		CmsSite cmsSite=null;
		if(searchParam.getParamMap().containsKey("siteId")){
			String siteId=ObjectUtils.toString(searchParam.getParamMap().get("siteId"));
			cmsSite=cmsSiteService.read(siteId);
		}else{
			cmsSite=(CmsSite) getSession().getAttribute("manageSite");
		}
		modelAndView.addObject("siteId", cmsSite.getId());
		return modelAndView;
	}
	
	
	@RequestMapping("listTempletFileTree")
	@ResponseBody
	public void listTempletFileTree(SearchParam searchParam,HttpServletResponse response){
		String root =ObjectUtils.toString(searchParam.getParamMap().get("root"));
		String realPath=request.getRealPath("/templet/").replace("\\", "/");
		if(searchParam.getParamMap().containsKey("realPath")){
			root=ObjectUtils.toString(searchParam.getParamMap().get("realPath"))+root;
		} 
		if(!realPath.endsWith("/")){
			realPath=realPath+"/";
		}
		String[] fileExt={".ftl",".html",".css","js",".txt"};
		 List<File> list=CmsCommonUtils.getDirectory(realPath+root,fileExt);
		 String json="[]";
		 try {
				json=JsonUtil.toJson(setFileLsit(list, realPath+root,root));
			} catch (IOException e) {
				e.printStackTrace();
			}
		  this.renderHtml(json, response);
	}
	
	private List<Map<String, Object>> setFileLsit(List<File> list,String root,String rootPath){
		List<Map<String, Object>> maps=new ArrayList<Map<String,Object>>();
		String[] fileExt={".ftl",".html",".css","js",".txt"};
		for (File file : list) {
			Map<String, Object> map=new HashMap<String, Object>();
			String parent=null;
			if(StringUtils.isNotEmpty(file.getParent().replace("\\", "/").replace(root, ""))){
				parent=file.getPath().replace("\\", "/").replace(root, "");
			}else{
				parent=rootPath;
			}
			map.put("id", file.getPath().replace("\\", "/").replace(root, ""));
			map.put("text", file.getName());
			map.put("parentField", parent);
			Map<String, Object> temp=new HashMap<String, Object>();
			if(file.isDirectory()){
				map.put("state", "closed");
				
				if(CmsCommonUtils.hasSonFolderAndFilse(file,fileExt)){
					temp.put("hasSon", "01");
				}else{
					temp.put("hasSon", "02");
				}
			}else{
				temp.put("isFile", true);
			}
				map.put("attributes",temp );
			maps.add(map);
		}
		return maps;
	}
	
	@RequestMapping("editTempletFile")
	public ModelAndView editTempletFile(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		String root =ObjectUtils.toString(searchParam.getParamMap().get("root"));
		String realPath=request.getRealPath("/templet/").replace("\\", "/");
		if(searchParam.getParamMap().containsKey("realPath")){
			root=ObjectUtils.toString(searchParam.getParamMap().get("realPath"))+root;
		} 
		if(!realPath.endsWith("/")){
			realPath=realPath+"/";
		}
		
		File file=new File(realPath+root);
		if(file!=null&&file.exists()){
			modelAndView.addObject("content", HtmlCode.encode(CmsCommonFileUtil.readFile(file)));
			modelAndView.addObject("fileName", StringUtils.substringBeforeLast(file.getName(),"."));
			modelAndView.addObject("fileExt", StringUtils.substringAfterLast(file.getName(),"."));
		}
		modelAndView.addObject("root", root);
		return modelAndView;
	}
	
	@RequestMapping("deleteTempletFile")
	@ResponseBody
	public String deleteTempletFile(SearchParam searchParam,HttpServletResponse response){
		String root =ObjectUtils.toString(searchParam.getParamMap().get("root"));
		String realPath=request.getRealPath("/templet/").replace("\\", "/");
		if(searchParam.getParamMap().containsKey("realPath")){
			root=ObjectUtils.toString(searchParam.getParamMap().get("realPath"))+root;
		} 
		if(!realPath.endsWith("/")){
			realPath=realPath+"/";
		}
		File file=new File(realPath+root);
		int count=0;
		String message="删除文件失败";
		if(file!=null&&file.exists()){
			try{
				 boolean returnDel=new File(realPath+root).delete();
				 if(returnDel){
					 count=1;
					 message="删除文件成功";
				 }
			}catch (Exception e) {
				logger.info("删除文件成功 路径为:"+realPath+root);
			}
		}
		return this.renderHtml(Response.newDefaultResponse(count,message).toString(), response);
	}
	
	@RequestMapping("saveTemplet")
	@ResponseBody
	public String saveTemplet(SearchParam searchParam,HttpServletResponse response){
		String isEdit =ObjectUtils.toString(searchParam.getParamMap().get("root"));
		String content=ObjectUtils.toString(searchParam.getParamMap().get("content"));
		String fileName=ObjectUtils.toString(searchParam.getParamMap().get("fileName"));
		String fileExt=ObjectUtils.toString(searchParam.getParamMap().get("fileExt"));
		String oldFileExt=ObjectUtils.toString(searchParam.getParamMap().get("oldFileExt"));
		String oldFileName=ObjectUtils.toString(searchParam.getParamMap().get("oldFileName"));
		String root =ObjectUtils.toString(searchParam.getParamMap().get("root"));
		if(StringUtils.contains(root, "/")){
			root=StringUtils.substringBeforeLast(root, "/");
		}
		String realPath=request.getRealPath("/templet/").replace("\\", "/");
		if(searchParam.getParamMap().containsKey("realPath")){
			root=ObjectUtils.toString(searchParam.getParamMap().get("realPath"))+root;
		} 
		if(!realPath.endsWith("/")){
			realPath=realPath+"/";
		}
		if(!(realPath+root).endsWith("/")){
			realPath=realPath+root+"/";
		}
		String filePath=realPath+fileName+"."+fileExt;
		if(!StringUtils.equals(fileName, oldFileName)||StringUtils.isNotEmpty(isEdit)&&StringUtils.equals(isEdit, "02")){
			
			
			File delfile=new File(realPath+oldFileName+"."+oldFileExt);
			if(delfile.exists()){
				boolean delReturn=CmsCommonFileUtil.deleteFile(delfile);
				logger.info("delete old file is :" +delReturn);
			}
		}
		
		File file=new File(filePath);
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				logger.error(e.getMessage());
			}
		}
		int count=0;
		String message="保存文件失败";
		if(file!=null&&file.exists()){
			 boolean write= CmsCommonFileUtil.writeFile(file,HtmlCode.decode(content));
			 if(write){
				 count=1;
				 message="保存文件成功";
			 }
		}
		return this.renderHtml(Response.newDefaultResponse(count,message).toString(), response);
	}
	
	@RequestMapping("addTemplet")
	public ModelAndView addTemplet(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		CmsSite cmsSite=null;
		if(searchParam.getParamMap().containsKey("siteId")){
			String siteId=ObjectUtils.toString(searchParam.getParamMap().get("siteId"));
			cmsSite=cmsSiteService.read(siteId);
		}else{
			cmsSite=(CmsSite) getSession().getAttribute("manageSite");
		}
		modelAndView.addObject("siteId",cmsSite!=null?cmsSite.getId():null);
		modelAndView.addObject("cmsSite", cmsSite);
		return modelAndView;
	}
	
	@RequestMapping("selectDirectory")
	public ModelAndView templetDirectory(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		CmsSite cmsSite=null;
		if(searchParam.getParamMap().containsKey("siteId")){
			String siteId=ObjectUtils.toString(searchParam.getParamMap().get("siteId"));
			cmsSite=cmsSiteService.read(siteId);
		}else{
			cmsSite=(CmsSite) getSession().getAttribute("manageSite");
		}
		modelAndView.addObject("siteId",cmsSite!=null?cmsSite.getId():null);
		modelAndView.addObject("cmsSite", cmsSite);
		modelAndView.addObject("paramMap",searchParam.getParamMap());
		return modelAndView;
	}
	
	@RequestMapping("templetDirectory")
	@ResponseBody
	public String templetDirectory(SearchParam searchParam,HttpServletResponse response){
		CmsSite cmsSite=null;
		String json=null;
		String root=null;
		if(searchParam.getParamMap().containsKey("siteId")){
			String siteId=ObjectUtils.toString(searchParam.getParamMap().get("siteId"));
			cmsSite=cmsSiteService.read(siteId);
		}else{
			cmsSite=(CmsSite) getSession().getAttribute("manageSite");
		}
		if(cmsSite!=null){
			root=cmsSite.getSourcePath();
		}
	 
		String realPath=null;
		if(searchParam.getParamMap().containsKey("realPath")){
			realPath=ObjectUtils.toString(searchParam.getParamMap().get("realPath"));
		}else{
			realPath =request.getRealPath("/templet/");
			if(StringUtils.contains(realPath, "\\")){
				realPath = StringUtils.replace(realPath, "\\", "/");
			}
		}
		if(!realPath.endsWith("/")){
			realPath=realPath+"/";
		}
		List<Map<String,Object>> list=TempletUtils.getListTempletDirectory(realPath+root,realPath+root);
		try {
			json=JsonUtil.toJson(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.renderHtml(json, response);
	}
	@RequestMapping("upload")
	@ResponseBody
	public String upload(SearchParam searchParam,@RequestParam(value = "upload", required = false) MultipartFile[] upload,HttpServletResponse response){
		CmsSite cmsSite=null;
		String root=null;
		String fileTypes=null;
		Response returneR=Response.newDefaultResponse(1, "文件上传成功");
		if(searchParam.getParamMap().containsKey("fileTypes")){
			fileTypes=ObjectUtils.toString(searchParam.getParamMap().get("fileTypes"));
		}
		if(searchParam.getParamMap().containsKey("path")){
			root=ObjectUtils.toString(searchParam.getParamMap().get("path"));
		}
		if(searchParam.getParamMap().containsKey("siteId")){
			String siteId=ObjectUtils.toString(searchParam.getParamMap().get("siteId"));
			cmsSite=cmsSiteService.read(siteId);
		}else{
			cmsSite=(CmsSite) getSession().getAttribute("manageSite");
		}
		
		if(cmsSite!=null){
			root=cmsSite.getSourcePath()+root;
		}
	 
		String realPath=null;
		if(searchParam.getParamMap().containsKey("realPath")){
			realPath=ObjectUtils.toString(searchParam.getParamMap().get("realPath"));
		}else{
			realPath =request.getRealPath("/templet/");
			if(StringUtils.contains(realPath, "\\")){
				realPath = StringUtils.replace(realPath, "\\", "/");
			}
		}
		if(!realPath.endsWith("/")){
			realPath=realPath+"/";
		}
		if(!(realPath+root).endsWith("/")){
			realPath=realPath+root+"/";
		}
		
		String meagsse=fileUpload(upload, realPath, fileTypes);
		if(StringUtils.isNotEmpty(meagsse)){
			returneR=Response.newDefaultResponse(0,meagsse);
		}
		return this.renderHtml(returneR.toString(), response);
	}
	
	
	private String fileUpload(MultipartFile[] file,String path,String fileTypes){
		if(file!=null){
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
					ReceiveParam receiveParam=  AttachmentUtils.setReceiveParam("cms/templet",fileTypes);
					String info = attachmentService.validateAttachment(tempFile,file[i].getOriginalFilename(), receiveParam);
		        	if(StringUtils.isNotEmpty(info)){
		        		return info;
		        	}
		        	byte[] data;
		    		try {
		    			data = FileUtils.readFileToByteArray(tempFile);
		    			File disFile = new File(path+file[i].getOriginalFilename());
		    			FileUtils.writeByteArrayToFile(disFile, data);
		    		}catch (Exception e) {
						
					}
		        }  
			 }
		}
		return null;
	}
	
}
