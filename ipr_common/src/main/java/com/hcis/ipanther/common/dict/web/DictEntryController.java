package com.hcis.ipanther.common.dict.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.dict.service.IDictEntryService;
import com.hcis.ipanther.common.dict.vo.DictEntry;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipr.core.entity.Response;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Controller
@RequestMapping("/common/datadict")
public class DictEntryController extends BaseController {

	@Autowired
	private IDictEntryService dictEntryService;
	/*
	private DictEntry dictEntry;
	
	private File upload;
	
	private String uploadContentType;

	private String uploadFileName;
	
	public DictEntry getDictEntry() {
		return dictEntry;
	}

	public void setDictEntry(DictEntry dictEntry) {
		this.dictEntry = dictEntry;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public IDictEntryService getDictEntryService() {
		return dictEntryService;
	}

	public void setDictEntryService(IDictEntryService dictEntryService) {
		this.dictEntryService = dictEntryService;
	}*/
	
	@RequestMapping("listDictEntry")
	public ModelAndView listDictEntry(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		 List<DictEntry> list= dictEntryService.listDictEntry(searchParam);
		 modelAndView.addObject("resultList", list);
		//return SUCCESS;
		 return modelAndView;
	}
	
	@RequestMapping("goImportDictEntry")
	public ModelAndView goImportDictEntry(){
		ModelAndView modelAndView=new ModelAndView();
		return modelAndView;
		//return SUCCESS;
	}
	
	@RequestMapping("importDictEntry")
	public ModelAndView importDictEntry(@RequestParam(value = "upload", required = false) MultipartFile upload,@ModelAttribute("searchParam")SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView();
		 String path=request.getSession().getServletContext().getRealPath("/");
		 String tempFileName=FileUtils.getTempDirectory().getPath()+"/"+upload.getName();
		 File tempFile=new File(tempFileName);
		 try {
			 upload.transferTo(tempFile);
		 } catch (IllegalStateException e) {
			 e.printStackTrace();
		 } catch (IOException e) {
			 e.printStackTrace();
		 }
//		 Map<String,Object> map= dictEntryService.importDictEntry(tempFile, searchParam,path);
		 Map<String,Object> map= dictEntryService.importDictEntry(tempFile);
		 modelAndView.addObject("resultMap", map);
		return modelAndView;
		//requestParam.put("fileName", uploadFileName);
		//resultMap = ;
		//return SUCCESS;
	}
	
	
	@RequestMapping("addDictEntry")
	@ResponseBody
	public void addDictEntry(@FormModel("dictEntry")DictEntry dictEntry, HttpServletResponse response){
		//String statusCode="200";
		String message="操作成功";
//		String navTabId=this.getRequest().getParameter("navTabId");//返回并刷新的navTabId
//		String rel=navTabId;//返回并刷新的navTabId，可不用
//		String callbackType="closeCurrent";//关闭当前页
//		String forwardUrl="";
//		String confirmMsg="";
		int count=0;
		try{
			if(dictEntry!=null&&StringUtils.isNotEmpty(dictEntry.getDictTypeCode())){
				dictEntry.setCreator(LoginUser.loginUser(request).getId());
				dictEntry.setUpdatedby(LoginUser.loginUser(request).getId());
				count=dictEntryService.addOrUpdate(dictEntry, this.getLoginer());
				if(count>0){
					message="添加数据项成功";
				}
				else if(count==-1){
					//statusCode="300";
					message="添加数据项失败";
				}
				else{
					//statusCode="300";
					message="添加数据项失败，请重新操作";
				}
			}
			else{
				//statusCode="300";
				message="参数错误，请重新操作";
			}
		}
		catch(Exception e){
			logger.error(this.getClass().getName(),e);
			//statusCode="300";
			message="操作失败!";
		}
		this.renderText(Response.newDefaultResponse(count, message).toString(),response);
//		DwzAjaxCallback dwzCallback=new DwzAjaxCallback(statusCode, message, navTabId, rel, callbackType, forwardUrl);
//		this.renderText(DwzAjaxCallback.toJSONString(dwzCallback));
	}
	
	/**
	 * 跳转到添加数据字典 dictEntry
	 */
	@RequestMapping("goAddDictEntry")
	public ModelAndView goAddDictEntry(SearchParam searchParam){
		ModelAndView modelAndView=new ModelAndView("/common/datadict/addDictEntry");
		return modelAndView;
		//requestParam.put("navTabId",new String[]{StringUtils.defaultString(request.getParameter("navTabId"))});
		//return SUCCESS;
	}
	/**
	 * 获取需修改数据字典 dictEntry
	 */
	@RequestMapping("goEditDictEntry")
	public ModelAndView goEditDictEntry(SearchParam searchParam,@FormModel("dictEntry")DictEntry dictEntry){
		ModelAndView modelAndView=new ModelAndView("/common/datadict/addDictEntry");
		modelAndView.addObject("dictEntry",dictEntryService.getByPk(dictEntry));
		return modelAndView;
		//requestParam.put("navTabId",new String[]{StringUtils.defaultString(request.getParameter("navTabId"))});
		//dictEntry=dictEntryService.getDictEntry(dictEntry.getId());
		//return SUCCESS;
	}
	
	/**
	 * 修改数据字典 dictEntry
	 */
	@RequestMapping("editDictEntry")
	@ResponseBody
	public void editDictEntry(@FormModel("dictEntry")DictEntry dictEntry, HttpServletResponse response){
		//String statusCode="200";
		String message="操作成功";
//		String navTabId=this.getRequest().getParameter("navTabId");//返回并刷新的navTabId
//		String rel=navTabId;//返回并刷新的navTabId，可不用
//		String callbackType="closeCurrent";//关闭当前页
//		String forwardUrl="";
//		String confirmMsg="";
		int count=0;
		try{
			if(dictEntry!=null&&StringUtils.isNotEmpty(dictEntry.getId())){
				dictEntry.setUpdatedby(LoginUser.loginUser(request).getId());
				count=dictEntryService.addOrUpdate(dictEntry,this.getLoginer());
				if(count>0){
					message="修改数据项成功";
				}
				else if(count==-1){
					//statusCode="300";
					message="修改数据项失败";
				}
				else{
					//statusCode="300";
					message="修改数据项失败，请重新操作";
				}
			}
			else{
				//statusCode="300";
				message="参数错误，请重新操作";
			}
		}
		catch(Exception e){
			logger.error(this.getClass().getName(),e);
			//statusCode="300";
			message="操作失败!";
		}
		this.renderText(Response.newDefaultResponse(count, message).toString(),response);
//		DwzAjaxCallback dwzCallback=new DwzAjaxCallback(statusCode, message, navTabId, rel, callbackType, forwardUrl);
//		this.renderText(DwzAjaxCallback.toJSONString(dwzCallback));
	}
	
	/**
	 * 删除数据字典 dictEntry
	 */
	@RequestMapping("deleteDictEntry")
	@ResponseBody
	public void deleteDictEntry(@FormModel("dictEntry")DictEntry dictEntry, HttpServletResponse response){
		//String statusCode="200";
		String message="操作成功";
//		String navTabId=this.getRequest().getParameter("navTabId");//返回并刷新的navTabId
//		String rel=navTabId;//返回并刷新的navTabId，可不用
//		String callbackType="";//关闭当前页
//		String forwardUrl="";
//		String confirmMsg="";
		int count=0;
		try{
			if(dictEntry!=null&&StringUtils.isNotEmpty(dictEntry.getId())){
				dictEntry.setUpdatedby(LoginUser.loginUser(request).getId());
			    count=dictEntryService.delete(dictEntry);
				if(count>0){
					message="删除数据项成功";
				}
				else if(count==-1){
					//statusCode="300";
					message="删除数据项失败";
				}
				else{
					//statusCode="300";
					message="删除数据项失败，请重新操作";
				}
			}
			else{
				//statusCode="300";
				message="参数错误，请重新操作";
			}
		}
		catch(Exception e){
			logger.error(this.getClass().getName(),e);
			//statusCode="300";
			message="操作失败!";
		}
		this.renderText(Response.newDefaultResponse(count, message).toString(),response);
//		DwzAjaxCallback dwzCallback=new DwzAjaxCallback(statusCode, message, navTabId, rel, callbackType, forwardUrl);
//		this.renderText(DwzAjaxCallback.toJSONString(dwzCallback));
		
	}

	@RequestMapping("getByTypeCode")
	@ResponseBody
	public List<DictEntry> getByTypeCode(String typeCode){

		return dictEntryService.getByTypeCode(typeCode);
	}

}
