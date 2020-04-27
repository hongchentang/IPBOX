package com.hcis.ipr.train.train.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hcis.ipanther.common.attachment.entity.Attachment;
import com.hcis.ipanther.common.attachment.entity.ReceiveParam;
import com.hcis.ipanther.common.attachment.entity.SendParam;
import com.hcis.ipanther.common.attachment.service.IAttachmentService;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.CommonConfig;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.course.entity.Course;
import com.hcis.ipr.train.train.dao.TrainCourseDao;
import com.hcis.ipr.train.train.entity.TrainCourse;
import com.hcis.ipr.train.train.service.ITrainCourseService;

@Service("trainCourseService")
public class TrainCourseServiceImpl extends BaseServiceImpl<TrainCourse> implements ITrainCourseService{
	private final static Log log=LogFactory.getLog(TrainCourseServiceImpl.class);
	@Autowired
	private TrainCourseDao trainCourseDao;
	@Autowired
	private IAttachmentService attachmentService;
	
	//保存培训班与课程关联
	public int saveTrainCourse(String trainId,String courseIds,LoginUser loginUser){
		int count=0;
		//删除旧关联
		count=trainCourseDao.deleteByTrainId(trainId);
		//新建关联
		if(StringUtils.isNotBlank(courseIds)){
			TrainCourse tc=null;
			String[] courseArray=courseIds.split(",");
			for(String courseId:courseArray){
				tc=new TrainCourse();
				tc.setTrainId(trainId);
				tc.setCourseId(courseId);
				tc.setDefaultValue();
				count+=this.create(tc, loginUser.getId());
			}
		}
		return count;
	}
	
	//保存课程分配教师
	public int saveDivide(SearchParam searchParam, LoginUser loginUser){
		int count=0;
		String trainCourseId=searchParam.getParamMap().get("trainCourseId").toString();
		//多个教师ID 则以逗号分隔
		String teacherIds=searchParam.getParamMap().get("teacherIds")==null?"":searchParam.getParamMap().get("teacherIds").toString();
		TrainCourse tc=new TrainCourse();
		tc.setId(trainCourseId);
		tc.setExpertId(teacherIds);
		return this.update(tc, loginUser.getId());
	}
	//选择教师或专家列表
	public List<Map<String,Object>> listTeacher(SearchParam searchParam){
		return trainCourseDao.listTeacher(searchParam);
	}
	
	//查询培训班已选的课程
	public List<Map<String,Object>> selectCourseByTrain(SearchParam searchParam){
		return trainCourseDao.selectCourseByTrain(searchParam);
	}
	//删除旧关联
	public int deleteByTrainId(String trainId){
	 return trainCourseDao.deleteByTrainId(trainId);
	}
	
	//课程资源上传
	public String uploadCourseFile(SearchParam searchParam,MultipartFile[] files,HttpServletRequest request){
		LoginUser loginUser=LoginUser.loginUser(request);
		TrainCourse trainCourse=new TrainCourse();
		String trainCourseId=searchParam.getParamMap().get("trainCourseId").toString();
		List list=new ArrayList();
		Map<String, String> map=null;
		String info="";
		JSONArray jsonArray=null;
		//检测上传文件和已有的文件数量是否大于等于50
		TrainCourse trainCourseOld=this.read(trainCourseId);
		 if(null!=trainCourseOld.getAttachment()&&trainCourseOld.getAttachment().startsWith("[")){
			 jsonArray=JSONArray.fromObject(trainCourseOld.getAttachment());
		 }
		 if(null!=jsonArray&&(jsonArray.size()+files.length)>50){
			 return "上传附件总数已经超过50个！";
		 }
		//循环上传文件
		if(null!=files&&files.length>0){
			String fileStr="";
			for(int i=0;i<files.length;i++){
				map=new HashMap<String,String>();
				info=this.saveFile(map, files[i], "attachment.default.fileTypes", "course/courseFile/");
				if(StringUtils.isNotEmpty(info)){
	        		return info;
	        	}else{
	        		if(map.get("attachmentId")!=null){
	        			list.add(map.get("attachmentId"));
	        		}
	        	}
			}
			 try {
				 if(null!=jsonArray){
					 for(Object id:list){
						 jsonArray.add(id.toString());
					 }
					 fileStr=jsonArray.toString();
				 }else{
					 fileStr=JsonUtil.toJson(list);
				 }
			} catch (IOException e) {
				info = "上传附件失败";
				log.error(this.getClass(), e);
			}
			 //把上传的附件添加到已经上传的json附件数组里
			 trainCourse.setId(trainCourseId);
			 trainCourse.setAttachment(fileStr);
			 this.update(trainCourse, loginUser.getId());
		}
		return info;
	}
	
	//删除课程附件
	public int deleteCourseFile(SearchParam searchParam,HttpServletRequest request){
		Map<String,Object> param=searchParam.getParamMap();
		String attachmentId=param.get("attachmentId").toString();
		String trainCourseId=param.get("trainCourseId").toString();
		int count=0;
		//删除附件
		Attachment attachment=new Attachment();
		attachment.setId(attachmentId);
		count=attachmentService.deleteAttachment(attachment, LoginUser.loginUser(request).getId());
		//移除附件字段的附件ID
		TrainCourse trainCourseOld=this.read(trainCourseId);
		 if(null!=trainCourseOld.getAttachment()&&trainCourseOld.getAttachment().startsWith("[")){
			 JSONArray jsonArray= JSONArray.fromObject(trainCourseOld.getAttachment());
			 jsonArray.remove(attachmentId);
			 TrainCourse tc=new TrainCourse();
			 tc.setId(trainCourseId);
			 tc.setAttachment(jsonArray.toString());
			 this.update(tc, LoginUser.loginUser(request).getId());
		 }
		return count;
	}
	
	@Override
	public MyBatisDao getBaseDao() {
		return trainCourseDao;
	}
	
	//保存上传的文件
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
