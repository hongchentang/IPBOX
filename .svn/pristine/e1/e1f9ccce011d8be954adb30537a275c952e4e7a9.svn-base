package com.hcis.ipanther.common.attachment.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.attachment.entity.Attachment;
import com.hcis.ipanther.common.attachment.entity.Message;
import com.hcis.ipanther.common.attachment.entity.ReceiveParam;
import com.hcis.ipanther.common.attachment.entity.SendParam;
import com.hcis.ipanther.common.attachment.service.IAttachmentService;
import com.hcis.ipanther.common.login.utils.LoginUtils;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.spring.mvc.bind.annotation.FormModel;
import com.hcis.ipanther.core.utils.CommonConfig;
import com.hcis.ipanther.core.web.controller.BaseController;
import com.hcis.ipanther.core.web.vo.AjaxReturnObject;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Controller
@RequestMapping("/common/attachment")
public class AttachmentController extends BaseController {

	@Resource(name="attachmentService")
	private IAttachmentService attachmentService;
	
	@RequestMapping("goUploadAttachment")
	@ResponseBody
	public ModelAndView goUploadAttachment(@ModelAttribute("searchParam")SearchParam searchParam){
		Message message=new Message();
		ModelAndView view=new ModelAndView();
		Object param=searchParam.getParamMap().get("attachParam");
		ReceiveParam receiveParam = attachmentService.decrypt(ObjectUtils.toString(param));
		view.addObject("receiveParam",receiveParam);
		if(receiveParam==null){
			message.setDecryptFail(CommonConfig.getProperty("attachment.view.illega"));
			view.addObject("message",message);
		}
		view.setViewName("/common/attachment/fileUpload");
		return view;
	}
	
	@RequestMapping("goUploadInput")
	public String goUploadInput(){
		return null;
	}
	
	@RequestMapping("uploadAttachment")
	public ModelAndView uploadAttachment(DefaultMultipartHttpServletRequest request,@ModelAttribute("searchParam")SearchParam searchParam){  
		ModelAndView view=new ModelAndView();
		String realPath =request.getSession().getServletContext().getRealPath("/");//根路径
		view.setViewName("/common/attachment/fileUpload");
		Message message=new Message();
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
			MultipartFile multipartFile = multipartRequest.getFile("upload");
			String tempFileName=FileUtils.getTempDirectory().getPath()+"/"+multipartFile.getName();
			File tempFile=new File(tempFileName);
			multipartFile.transferTo(tempFile);
			ReceiveParam receiveParam= setReceiveParam(searchParam);
			String info = attachmentService.validateAttachment(tempFile, multipartFile.getOriginalFilename(), receiveParam);
			if(StringUtils.isNotEmpty(info)){
				message.setValidateFail(info);
				view.addObject("message",message);
				return view;
			}
			SendParam sendParam =null;
			sendParam = attachmentService.uploadAttachment(tempFile,multipartFile.getOriginalFilename(),receiveParam,realPath);
			if(sendParam==null){
				message.setUploadFail(CommonConfig.getProperty("attachment.upload.error"));
				view.addObject("message",message);
				return view;
			}
			view.addObject("message",message);
			view.addObject("sendParam",sendParam);
			view.addObject("receiveParam",receiveParam);
		}
		catch(IllegalStateException e){
			logger.error(this.getClass().getName(),e);
		}
		catch(IOException e){
			logger.error(this.getClass().getName(),e);
		}
		catch(Exception e){
			logger.error(this.getClass().getName(),e);
		}
		return view;
	}
	
	/**
	 * Sets the receive param.
	 *
	 * @param searchParam the search param
	 * @return the receive param
	 */
	private ReceiveParam setReceiveParam(SearchParam searchParam){
		ReceiveParam receiveParam=new ReceiveParam();
		if(searchParam!=null){
			Map<String, Object> map= searchParam.getParamMap();
			if(map!=null&&(!map.isEmpty())){
				receiveParam.setAttachmentId(ObjectUtils.toString(map.get("attachmentId")));
				receiveParam.setFileTypes(ObjectUtils.toString(map.get("fileTypes")));
				receiveParam.setFileDir(ObjectUtils.toString(map.get("fileDir")));
				receiveParam.setRealName(ObjectUtils.toString(map.get("realName")));
				receiveParam.setCallbackUrl(ObjectUtils.toString(map.get("callbackUrl")));
				receiveParam.setBillId(ObjectUtils.toString(map.get("billId")));
				String fileMaxSize= ObjectUtils.toString(map.get("fileMaxSize"));
				if(fileMaxSize!=null){
					receiveParam.setFileMaxSize(Long.parseLong(fileMaxSize));
				}
			}
		}
		return receiveParam;
	}
	
	@RequestMapping("deleteAttachment")
	@ResponseBody
	public AjaxReturnObject deleteAttachment(@FormModel("attachment")Attachment attachment){
		LoginUser loginUser=LoginUtils.getLoginUser(request);
		String operator=(loginUser==null?null:loginUser.getId());
		int count=attachmentService.deleteAttachment(attachment,operator);
	    return AjaxReturnObject.newDefaultAjaxReturnObject(count);
	}
	
	@RequestMapping("downAttachment")
	@ResponseBody
	public void downAttachment(@FormModel("attachment")Attachment attachment, final HttpServletResponse response) throws IOException{
		String realPath =request.getSession().getServletContext().getRealPath("/");//根路径
		try{
			//String basePath= CommonConfig.getProperty("attachment.root.dir")+"temp/"; //ServletActionContext.getServletContext().getRealPath("/");// 项目根路径
			String basePath= CommonConfig.getProperty("attachment.root.dir");
			InputStream fileinput = attachmentService.downloadAttachment(attachment,basePath,realPath);
			response.setContentType("application/x-download");
			response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(attachment.getFileName().getBytes(), "ISO-8859-1") + "\"");
//			response.setHeader("Content-Disposition", "attachment;filename="+attachment.getFileName());
			ServletOutputStream out = response.getOutputStream();
			if(fileinput!=null){
				org.apache.commons.io.IOUtils.copy(fileinput, out);
			}
	 	}catch (Exception e) {
			logger.error(e.getMessage(),e);
			response.getWriter().write("系统错误");
			response.getWriter().close();
		}
	}
	
	@RequestMapping("downAttachmentById")
	@ResponseBody
	public void downAttachmentById(@RequestParam("id")String id, final HttpServletResponse response) throws IOException{
		String realPath =request.getSession().getServletContext().getRealPath("/");//根路径
		try{
			//	String basePath= CommonConfig.getProperty("attachment.root.dir")+"temp/"; //ServletActionContext.getServletContext().getRealPath("/");// 项目根路径
			String basePath= CommonConfig.getProperty("attachment.root.dir");
			Attachment attachment=new Attachment();
		 	attachment.setId(id);
			InputStream fileinput = attachmentService.downloadAttachment(attachment,basePath,realPath);
			response.setContentType("application/x-download");
			response.setHeader("Content-Disposition", "attachment;filename=\"" + new String(attachment.getFileName().getBytes(), "ISO-8859-1") + "\"");
//			response.setHeader("Content-Disposition", "attachment;filename="+attachment.getFileName());
			ServletOutputStream out = response.getOutputStream();
			org.apache.commons.io.IOUtils.copy(fileinput, out);
		}catch (Exception e) {
			logger.error(e.getMessage());
			response.getWriter().write("系统错误");
			response.getWriter().close();
		}
		
	}

	@RequestMapping("downloadCompressAttachment")
	public void downloadCompressAttachment(@FormModel("attachParam")String attachParam, final HttpServletResponse response){
		attachmentService.downloadCompressAttachment(attachParam, response);
	}
	
	@RequestMapping("updateAttachmentValid")
	public void updateAttachmentValid(@FormModel("attachParam")String attachParam, final HttpServletResponse response){
		try{
		int count = attachmentService.updateAttachmentValid(attachParam);
		this.renderText(count,response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
