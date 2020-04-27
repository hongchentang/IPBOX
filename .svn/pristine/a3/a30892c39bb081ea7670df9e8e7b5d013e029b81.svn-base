package com.hcis.items.service.impl;

import java.util.Map;
import java.util.UUID;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
import com.hcis.items.dao.ItemsExpertDao;
import com.hcis.items.entity.ItemsExpert;
import com.hcis.items.entity.ItemsManager;
import com.hcis.items.service.ItemsExpertService;

@Service("itemsExpertService")
public class ItemsExpertServiceImpl extends BaseServiceImpl<ItemsExpert> implements ItemsExpertService {

	private final static Log log=LogFactory.getLog(ItemsLibraryServiceImpl.class);
	@Autowired
	private ItemsExpertDao itemsExpertDao;
	
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
	public ProcessInstance startWorkFlow(ItemsExpert itemsExpert,HttpServletRequest request) {
		return null;
	}
	@Override
	public String uploadFile(ItemsExpert itemsExpert,MultipartFile file) {
		return upload( itemsExpert,  file, "file","attachment.default.fileTypes","course/file/");
	}
	@Override
	public String uploadImg(ItemsExpert itemsExpert,MultipartFile file) {
		return null;
	}
	@Override
	public String uploadAvatar(ItemsExpert itemsExpert, MultipartFile file) {
		return null;
	}
	@Override
	public void deleteWorkFlow(String procInstId) {
		
	}
	@Override
	public void submitExpert(ItemsExpert itemsExpert,SearchParam searchParam) {
		searchParam.getParamMap().put("expertCheckStatus", "01");
		//int errorNum = itemsExpertDao.updateByPrimaryKeySelective(searchParam);
		int errno = itemsExpertDao.updateByParam(searchParam);
		
	}
	
	/**
	 * 通过来源单号，及创建人，获取到实类数据
	 */

	@Override
	public void submitExpertToProject(ItemsExpert itemsExpert,HttpServletRequest request) {
		
	}
	@Override
	public void audit(ItemsExpert itemsExpert,HttpServletRequest request,SearchParam searchParam) {
		
	}
	
	@Override
	public List<Map<String,Object>> listExpert(SearchParam searchParam,HttpServletRequest request){	
		return itemsExpertDao.listExpert(searchParam);	
	}
	
	@Override
	public List<Map<String,Object>> listExpertView(SearchParam searchParam,HttpServletRequest request){	
		return itemsExpertDao.listExpert(searchParam);	
	}
	
	@Override
	public int deleteExperts(SearchParam searchParam) {
		return itemsExpertDao.deleteExperts(searchParam);
	
	}

	/**
	 * 课程审核通过后向报名人员的邮箱定向推送消息
	 * @param course
	 * @return
	 */
	@Override
	public void sendEmeilByCourse(ItemsExpert itemsExpert,HttpServletRequest request) {
		
	}

	@Override
	public MyBatisDao getBaseDao() {
		// TODO Auto-generated method stub
		return itemsExpertDao;
	}
	@Override
	public List<Map<String, Object>> listProjectLitems(SearchParam searchParam) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String uploadExpertFile(SearchParam searchParam, MultipartFile[] upload, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int deleteExpertFile(SearchParam searchParam, HttpServletRequest request) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String upload(ItemsExpert itemsExpert, MultipartFile file,String type,String fileTypes,String fileDir) {
		Map<String, String> map=new HashMap<String, String>();
		String info=null;
		info=this.saveFile(map, file, fileTypes, fileDir);
		if((!map.isEmpty())){
			try {
				 if(type.equals("file")){
					 itemsExpert.setExpertCheckFile(JsonUtil.toJson(map));
				}
			} catch (Exception e) {
				info = "上传附件失败";
				log.error(this.getClass(), e);
			}
		}
		return info;
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
}