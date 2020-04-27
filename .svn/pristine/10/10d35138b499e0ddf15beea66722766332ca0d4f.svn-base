package com.hcis.items.service.impl;

import java.io.File;
import java.io.IOException;
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

import com.hcis.ipanther.common.attachment.entity.ReceiveParam;
import com.hcis.ipanther.common.attachment.entity.SendParam;
import com.hcis.ipanther.common.attachment.service.IAttachmentService;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.security.entity.Role;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.utils.CommonConfig;
import com.hcis.ipanther.core.utils.JsonUtil;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.train.entity.TrainCourse;
import com.hcis.items.entity.ItemsLibrary;
import com.hcis.items.entity.ItemsManager;
import com.hcis.items.service.ItemsManagerService;
import com.hcis.items.dao.ItemsManagerDao;


@Service("itemsManagerService")
public  class ItemsManagerServiceImpl extends BaseServiceImpl<ItemsManager> implements ItemsManagerService{
	private final static Log log=LogFactory.getLog(ItemsManagerServiceImpl.class);
	
	@Autowired
	private ItemsManagerDao itemsManagerDao;
	
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
	public ProcessInstance startWorkFlow(ItemsManager itemsManager,HttpServletRequest request) {
		return null;
	}
	
	@Override
	public String uploadFile(ItemsManager itemsManager,MultipartFile file) {
		return upload( itemsManager,  file, "file","attachment.default.fileTypes","course/file/");
	}
	
	
	@Override
	public String uploadImg(ItemsManager projectItem,MultipartFile file) {
		return null;
	}
	@Override
	public void deleteWorkFlow(String procInstId) {
		;
	}
	
	@Override
	public void sumitProject(ItemsManager itemsManager,HttpServletRequest request) {		
		itemsManager.setFlowStatus("1");
		int updatecheck = itemsManagerDao.updateByPrimaryKeySelective(itemsManager);
	}
	
	@Override
	public void audit(ItemsManager projectItem,HttpServletRequest request,SearchParam searchParam) {
		;
	}
	
	@Override
	public List<Map<String,Object>> listTodo(SearchParam searchParam,HttpServletRequest request){
		LoginUser loginUser=LoginUser.loginUser(request);
		String userId=loginUser.getId();
		searchParam.getParamMap().put("creator", userId);
		return itemsManagerDao.listProjectItem(searchParam);
	}
	@Override
	public List<Map<String,Object>> listDone(SearchParam searchParam,HttpServletRequest request){
		return itemsManagerDao.listProjectItem(searchParam);
	}
	
	public List<Map<String,Object>> applyListDone(SearchParam searchParam,HttpServletRequest request){
		return itemsManagerDao.listProjectItem(searchParam);
	}

	
	@Override
	public List<Map<String,Object>> listProjectLitems(SearchParam searchParam){
		return itemsManagerDao.listProjectItem(searchParam);
	}
	
	@Override
	public List<Map<String,Object>> applyListCheck(SearchParam searchParam,HttpServletRequest request ){
		return itemsManagerDao.checkProjectItems(searchParam);
		
	}
	
	@Override
	public  List<Map<String,Object>> assignList(SearchParam searchParam,HttpServletRequest request ){
		return itemsManagerDao.checkProjectItems(searchParam);
	}

	
	@Override
	public List<Map<String,Object>> selectCourseTrain(SearchParam searchParam){
		return null;
	}
	@Override
	public List<Map<String,Object>> selectTalentCourse(SearchParam searchParam){
		return null;
	}
	@Override
	public List<Map<String,Object>> selectTrain(SearchParam searchParam){
		return null;
	}
	@Override
	public String uploadExpertFile(SearchParam searchParam,MultipartFile[] upload,HttpServletRequest request) {
		return null;
	}
	@Override
	public List<Map<String,Object>> listTrainCoure(SearchParam searchParam){
		return null;
	}
	@Override
	public int deleteExpertFile(SearchParam searchParam,HttpServletRequest request) {
		return 1;
	}
	
	
	
	@Override
	public String uploadAvatar(ItemsManager projectItem, MultipartFile file) {
		return null;
	}
	/**
	 * 课程审核通过后向报名人员的邮箱定向推送消息
	 * @param course
	 * @return
	 */
	@Override
	public void sendEmeilByCourse(ItemsManager projectItem,HttpServletRequest request) {
		;
	}
	@Override
	public List<Map<String,Object>> selectCourseHome(SearchParam searchParam){
		return null;
	}
	

	
	@Override
	public MyBatisDao getBaseDao() {
		return itemsManagerDao;
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
	
	public String getRolesStr(LoginUser loginUser){
		StringBuffer sb=new StringBuffer();
		sb.append("'");
		List<Role> list=loginUser.getRoleList();
		if(list.size()>0){
			for(int i=0;i<list.size();i++){
				if(i<list.size()-1){
					sb.append(list.get(i).getRoleCode()).append("','");
				}else{
					sb.append(list.get(i).getRoleCode()).append("'");
				}
			}
		}else{
			sb.append("'");
		}
		return sb.toString();
	}
	
	
	public String upload(ItemsManager itemsManager, MultipartFile file,String type,String fileTypes,String fileDir) {
		Map<String, String> map=new HashMap<String, String>();
		String info=null;
		info=this.saveFile(map, file, fileTypes, fileDir);
		if((!map.isEmpty())){
			try {
				 if(type.equals("file")){
					 if(null !=itemsManager.getProjectAttacherFile() ) {
						 itemsManager.setOthersCheckFile(JsonUtil.toJson(map));
					 }
					 else {
						 itemsManager.setProjectAttacherFile(JsonUtil.toJson(map));
					 }
				}
			} catch (Exception e) {
				info = "上传附件失败";
				log.error(this.getClass(), e);
			}
		}
		return info;
	}
	
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
	@Override
	public int saveExpertDevide(SearchParam searchParam,LoginUser loginUser) {
		int count=0;
		String trainCourseId=searchParam.getParamMap().get("trainCourseId").toString();
		//多个教师ID 则以逗号分隔
		String teacherIds=searchParam.getParamMap().get("teacherIds")==null?"":searchParam.getParamMap().get("teacherIds").toString();
		ItemsManager tc=new ItemsManager();
		tc.setId(trainCourseId);
		tc.setExpertName(teacherIds);
		return this.update(tc, loginUser.getId());
	}
	
	@Override
	public List<Map<String,Object>> listExpert(SearchParam searchParam,HttpServletRequest request){
		return itemsManagerDao.listExperts(searchParam);
	}
	@Override
	public List<Map<String,Object>> listExpertView(SearchParam searchParam,HttpServletRequest request){
		return itemsManagerDao.listExpertView(searchParam);
	}
	
	@Override
	public List<Map<String,Object>> listAssignExpert(SearchParam searchParam){
		return itemsManagerDao.listExpertName(searchParam);
	}
	@Override
	public List<Map<String,Object>> listAssignAgency(SearchParam searchParam){
		return itemsManagerDao.listAgencyName(searchParam);
	}
	
	@Override
	public List<Map<String,Object>> auditAgencyList(SearchParam searchParam){
		return itemsManagerDao.auditAgencyList(searchParam);
	}
	@Override
	public ItemsManager readbyProjectSourceCode(SearchParam searchParam) {
		return itemsManagerDao.selectByDoublePrimaryKey(searchParam);
		//return new ItemsManager();
	}
}
