package com.hcis.items.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.web.multipart.MultipartFile;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.items.entity.ItemsExpert;


public interface ItemsExpertService extends IBaseService<ItemsExpert> {

    public ProcessInstance startWorkFlow(ItemsExpert itemsExpert,HttpServletRequest request);
	
	public String uploadFile(ItemsExpert itemsExpert,MultipartFile file);
	
	public String uploadImg(ItemsExpert itemsExpert,MultipartFile file);
	
	public String uploadAvatar(ItemsExpert itemsExpert, MultipartFile file);
	
	public void deleteWorkFlow(String procInstId);
	
	public void submitExpert(ItemsExpert itemsExpert,SearchParam searchParam);
	
	public void submitExpertToProject(ItemsExpert itemsExpert,HttpServletRequest request);
	
	public void audit(ItemsExpert itemsExpert,HttpServletRequest request,SearchParam searchParam);
	
	public List<Map<String,Object>> listExpert(SearchParam searchParam,HttpServletRequest request);
	
	public List<Map<String,Object>> listExpertView(SearchParam searchParam,HttpServletRequest request);
	
	public List<Map<String,Object>> listProjectLitems(SearchParam searchParam);
	
	public String uploadExpertFile(SearchParam searchParam,MultipartFile[] upload,HttpServletRequest request);

	public int deleteExpertFile(SearchParam searchParam,HttpServletRequest request);
	
	public int deleteExperts(SearchParam searchParam);
	
	
	/**
	 * 课程审核通过后向报名人员的邮箱定向推送消息
	 * @param course
	 * @return
	 */
	public void sendEmeilByCourse(ItemsExpert itemsExpert,HttpServletRequest request);
	
}
