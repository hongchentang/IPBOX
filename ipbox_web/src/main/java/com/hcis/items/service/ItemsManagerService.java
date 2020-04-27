package com.hcis.items.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.web.multipart.MultipartFile;

import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.train.train.entity.TrainCourse;
import com.hcis.items.entity.ItemsManager;
import com.hcis.items.entity.ItemsLibrary;

public interface ItemsManagerService extends IBaseService<ItemsManager> {

    public ProcessInstance startWorkFlow(ItemsManager itemsManager,HttpServletRequest request);
	
	public String uploadFile(ItemsManager itemsManager,MultipartFile file);
	
	public String uploadImg(ItemsManager itemsManager,MultipartFile file);
	
	public void deleteWorkFlow(String procInstId);
	
	public void sumitProject(ItemsManager itemsManager,HttpServletRequest request);
	
	public void audit(ItemsManager itemsManager,HttpServletRequest request,SearchParam searchParam);
	
	public List<Map<String,Object>> listTodo(SearchParam searchParam,HttpServletRequest request);
	
	public List<Map<String,Object>> listDone(SearchParam searchParam,HttpServletRequest request);
	
	public List<Map<String,Object>> listProjectLitems(SearchParam searchParam);
	
	public List<Map<String,Object>> selectCourseTrain(SearchParam searchParam);
	
	public List<Map<String,Object>> selectTalentCourse(SearchParam searchParam);
	
	public List<Map<String,Object>> selectTrain(SearchParam searchParam);
	
	public String uploadExpertFile(SearchParam searchParam,MultipartFile[] upload,HttpServletRequest request);
	
	public List<Map<String,Object>> listTrainCoure(SearchParam searchParam);
	
	public int deleteExpertFile(SearchParam searchParam,HttpServletRequest request);
	
	public String uploadAvatar(ItemsManager itemsManager, MultipartFile file);
	
	public List<Map<String,Object>> applyListDone(SearchParam searchParam,HttpServletRequest request);
	
	
	/**
	 * 课程审核通过后向报名人员的邮箱定向推送消息
	 * @param course
	 * @return
	 */
	public void sendEmeilByCourse(ItemsManager itemsManager,HttpServletRequest request);
	public List<Map<String,Object>> selectCourseHome(SearchParam searchParam);
	
	// 中心检查资料
	public List<Map<String,Object>> applyListCheck(SearchParam searchParam,HttpServletRequest request );
	
	//专家和机构分配
	public  List<Map<String,Object>> assignList(SearchParam searchParam,HttpServletRequest request );

	public int saveExpertDevide(SearchParam searchParam,LoginUser loginUser);
	
	//专家审计页面
	public List<Map<String,Object>> listExpert(SearchParam searchParam,HttpServletRequest request);
	//专家明细
	public List<Map<String,Object>> listExpertView(SearchParam searchParam,HttpServletRequest request);

	//项目已分配专家列表
	public List<Map<String,Object>> listAssignExpert(SearchParam searchParam);
	//项目已分配机构
	public List<Map<String,Object>> listAssignAgency(SearchParam searchParam);
	
	//第三方机构进行审计
	public List<Map<String,Object>> auditAgencyList(SearchParam searchParam);
	
	public ItemsManager readbyProjectSourceCode(SearchParam searchParam);
	
	
}
