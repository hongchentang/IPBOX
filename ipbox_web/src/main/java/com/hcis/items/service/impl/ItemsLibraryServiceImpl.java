package com.hcis.items.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.hcis.ipanther.common.attachment.entity.Attachment;
import com.hcis.ipanther.common.attachment.entity.ReceiveParam;
import com.hcis.ipanther.common.attachment.entity.SendParam;
import com.hcis.ipanther.common.attachment.service.IAttachmentService;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.CommonConfig;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.course.entity.Course;
import com.hcis.items.dao.ItemsLibraryDao;
import com.hcis.items.entity.ItemsLibrary;
import com.hcis.items.service.ItemsLibraryService;

import net.sf.json.JSONArray;

@Service("itemsLibraryService")
public class ItemsLibraryServiceImpl extends BaseServiceImpl<ItemsLibrary> implements ItemsLibraryService {
	
	private final static Log log=LogFactory.getLog(ItemsLibraryServiceImpl.class);
	@Autowired
	private ItemsLibraryDao itemsLibraryDao;
	
	private RuntimeService runtimeService;
	@Autowired
	private IdentityService identityService;
	@Autowired
	protected TaskService taskService;
	@Autowired
	private IAttachmentService attachmentService;
	@Autowired
	private HistoryService historyService;
	@Autowired
	private JavaMailSenderImpl mailSender;
	@Resource(name="mailFreeMarkerConfigurer")
	private FreeMarkerConfigurer mailFreeMarkerConfigurer;
	@Autowired
	private IUserService userService;
	
	@Override
    public ProcessInstance startWorkFlow(ItemsLibrary itemsLibrary,HttpServletRequest request) {
    	return null;
    }
	@Override
	public String uploadFile(ItemsLibrary itemsLibrary,MultipartFile file) {
		return upload( itemsLibrary,  file, "file","attachment.default.fileTypes","course/file/");
	}
	
	@Override
	public String uploadImg(ItemsLibrary itemsLibrary,MultipartFile file) {
		return null;
	};
	@Override
	public void deleteWorkFlow(String procInstId) {
		;
	}
	
	public void submitLibrary(ItemsLibrary itemsLibrary,HttpServletRequest request) {
		itemsLibrary.setIsPass("1");
		int errorNum = itemsLibraryDao.updateByPrimaryKeySelective(itemsLibrary);
		
	}
	
	public void sumitLibaryToProject(ItemsLibrary itemsLibrary,HttpServletRequest request) {
		
	}
	
	public void audit(ItemsLibrary itemsLibrary,HttpServletRequest request,SearchParam searchParam) {
		
	}
	
	public List<Map<String,Object>> listLibrary(SearchParam searchParam,HttpServletRequest request){
//		LoginUser loginUser=LoginUser.loginUser(request);
//		String creator=loginUser.getId();
//		searchParam.getParamMap().put("creator", creator);

		return itemsLibraryDao.listLibrary(searchParam);
	}
	

	@Override
	public List<Map<String,Object>> listProjectLitems(SearchParam searchParam){
		return null;
	}
	@Override
	public List<Map<String,Object>> selectCourseTrain(SearchParam searchParam){
		return null;
	}
	
	@Override
	public String uploadAvatar(ItemsLibrary itemsLibrary, MultipartFile file) {
		return null;
	}
	
	/**
	 * 课程审核通过后向报名人员的邮箱定向推送消息
	 * @param course
	 * @return
	 */
	@Override
	public void sendEmeilByCourse(ItemsLibrary itemsLibrary,HttpServletRequest request) {
		
	}

	@Override
	public MyBatisDao getBaseDao() {
		// TODO Auto-generated method stub
		return itemsLibraryDao;
	}
	
	// 附件上传
	public String upload(ItemsLibrary itemsLibrary, MultipartFile file,String type,String fileTypes,String fileDir) {
		Map<String, String> map=new HashMap<String, String>();
		String info=null;
		info=this.saveFile(map, file, fileTypes, fileDir);
		if((!map.isEmpty())){
			try {
				if(type.equals("file")){
					itemsLibrary.setProjectRuleFile(JsonUtil.toJson(map));
				}
			} catch (Exception e) {
				info = "上传附件失败";
				log.error(this.getClass(), e);
			}
		}
		return info;
	}
	//专题多文件上传
//	public String uploadExpertFile(SearchParam searchParam,MultipartFile[] files,HttpServletRequest request){
//		LoginUser loginUser=LoginUser.loginUser(request);
//		ItemsLibrary itemsLibrary =new ItemsLibrary();
//		List list=new ArrayList();
//		Map<String, String> map=null;
//		String info="";
//		JSONArray jsonArray=null;
//		//检测上传文件和已有的文件数量是否大于等于50
//		 Course courseOld=this.read(searchParam.getParamMap().get("courseId").toString());
//		 if(null!=courseOld.getExpertAttachment()&&courseOld.getExpertAttachment().startsWith("[")){
//			 jsonArray=JSONArray.fromObject(courseOld.getExpertAttachment());
//		 }
//		 if(null!=jsonArray&&(jsonArray.size()+files.length)>50){
//			 return "上传附件总数已经超过50个！";
//		 }
//		//循环上传文件
//		if(null!=files&&files.length>0){
//			String fileStr="";
//			for(int i=0;i<files.length;i++){
//				map=new HashMap<String,String>();
//				info=this.saveFile(map, files[i], "attachment.default.fileTypes", "course/expertFile/");
//				if(StringUtils.isNotEmpty(info)){
//	        		return info;
//	        	}else{
//	        		if(map.get("attachmentId")!=null){
//	        			list.add(map.get("attachmentId"));
//	        		}
//	        	}
//			}
//			 try {
//				 if(null!=jsonArray){
//					 for(Object id:list){
//						 jsonArray.add(id.toString());
//					 }
//					 fileStr=jsonArray.toString();
//				 }else{
//					 fileStr=JsonUtil.toJson(list);
//				 }
//			} catch (IOException e) {
//				info = "上传附件失败";
//				log.error(this.getClass(), e);
//			}
//			 //把上传的附件添加到已经上传的json附件数组里
//			 course.setId(searchParam.getParamMap().get("courseId").toString());
//			 course.setExpertAttachment(fileStr);
//			 this.update(course, loginUser.getId());
//		}
//		return info;
//	}
	
	//保存上传的文件
	@Override
	public String saveFile(Map<String, String> map, MultipartFile file,String fileTypes,String fileDir){
		if(file!=null){
	        if(!file.isEmpty()){  
				String tempFileName=FileUtils.getTempDirectory().getPath()+"/"+file.getName();
				File tempFile=new File(tempFileName);
				try {
					file.transferTo(tempFile);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ReceiveParam receiveParam= setReceiveParam(fileTypes,"attachment.default.fileMaxSize",fileDir);
				String info = attachmentService.validateAttachment(tempFile,file.getOriginalFilename(), receiveParam);
	        	if(StringUtils.isNotEmpty(info)){
	        		return info;
	        	}
	    		SendParam sendParam =null;
				sendParam = attachmentService.uploadAttachment(tempFile,file.getOriginalFilename(),receiveParam,"");
				if(sendParam==null){
					return CommonConfig.getProperty("attachment.upload.error");
				}else{
		
					 map.put("attachmentId", sendParam.getAttachmentId());
					 map.put("attachmentName", sendParam.getAttachmentName());
					 map.put("downloadUrl", sendParam.getDownloadUrl());
					 map.put("fileId", sendParam.getFileId());
					 map.put("billId", sendParam.getBillId());
					 map.put("groupId", sendParam.getGroupId());
					 map.put("status", sendParam.getStatus());
				}
	        }  
		 }
		return null;
	}
	
	//删除专题附件
//	public int deleteExpertFile(SearchParam searchParam,HttpServletRequest request){
//		Map<String,Object> param=searchParam.getParamMap();
//		String attachmentId=param.get("attachmentId").toString();
//		String courseId=param.get("courseId").toString();
//		int count=0;
//		//删除附件
//		Attachment attachment=new Attachment();
//		attachment.setId(attachmentId);
//		count=attachmentService.deleteAttachment(attachment, LoginUser.loginUser(request).getId());
//		//移除课程里专家字段的附件ID
//		 Course courseOld=this.read(courseId);
//		 if(null!=courseOld.getExpertAttachment()&&courseOld.getExpertAttachment().startsWith("[")){
//			 JSONArray jsonArray= JSONArray.fromObject(courseOld.getExpertAttachment());
//			 jsonArray.remove(attachmentId);
//			 Course c=new Course();
//			 c.setId(courseId);
//			 c.setExpertAttachment(jsonArray.toString());
//			 this.update(c, LoginUser.loginUser(request).getId());
//		 }
//		return count;
//	}
	
	public static ReceiveParam setReceiveParam(String fileTypesPropertyName,String fileMaxSize,String fileDir) {
		String uuid = ObjectUtils.toString(UUID.randomUUID());
		String id=StringUtils.remove(uuid,"-");
		ReceiveParam param=new ReceiveParam();
		param.setAttachmentId(id);
		param.setFileDir(fileDir);
		param.setFileMaxSize(Integer.parseInt(CommonConfig.getProperty(fileMaxSize)));
		param.setCallbackUrl("callbackUrl");
		param.setBillId(id);
		param.setFileTypes(CommonConfig.getProperty(fileTypesPropertyName));
		return param;
	}

}
