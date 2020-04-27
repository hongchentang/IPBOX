package com.hcis.items.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.web.multipart.MultipartFile;

import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.items.entity.ItemsLibrary;


public interface ItemsLibraryService extends IBaseService<ItemsLibrary> {

    public ProcessInstance startWorkFlow(ItemsLibrary itemsLibrary,HttpServletRequest request);
	
	public String uploadFile(ItemsLibrary itemsLibrary,MultipartFile file);
	
	public String uploadImg(ItemsLibrary itemsLibrary,MultipartFile file);
	
	public String uploadAvatar(ItemsLibrary itemsLibrary, MultipartFile file);
	
	public void deleteWorkFlow(String procInstId);
	
	public void submitLibrary(ItemsLibrary itemsLibrary,HttpServletRequest request);
	
	public void sumitLibaryToProject(ItemsLibrary itemsLibrary,HttpServletRequest request);
	
	public void audit(ItemsLibrary itemsLibrary,HttpServletRequest request,SearchParam searchParam);
	
	public List<Map<String,Object>> listLibrary(SearchParam searchParam,HttpServletRequest request);
	
	public List<Map<String,Object>> listProjectLitems(SearchParam searchParam);
	
	public List<Map<String,Object>> selectCourseTrain(SearchParam searchParam);
	

	
//	public String uploadExpertFile(SearchParam searchParam,MultipartFile[] upload,HttpServletRequest request);
	

//	public int deleteExpertFile(SearchParam searchParam,HttpServletRequest request);
	
	
	
	/**
	 * 课程审核通过后向报名人员的邮箱定向推送消息
	 * @param course
	 * @return
	 */
	public void sendEmeilByCourse(ItemsLibrary itemsLibrary,HttpServletRequest request);

	/**
	 *
	 * @param map
	 * @param file
	 * @param fileTypes
	 * @param fileDir
	 * @return
	 */
	public String saveFile(Map<String, String> map, MultipartFile file,String fileTypes,String fileDir);
	
}
