package com.hcis.ipanther.common.dept.utils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;

import org.apache.commons.lang3.StringUtils;
import com.hcis.ipanther.common.dept.entity.Department;
import com.hcis.ipanther.common.dept.service.IDepartmentService;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.user.utils.UserRegionsUtils;
import com.hcis.ipanther.core.utils.BeanLocator;
import com.hcis.ipanther.core.utils.JSONUtils;
import com.hcis.ipanther.core.web.vo.SearchParam;


public class DepartmentUtils {
	
	
	/**
	 * 判断机构类型是否为主管机构
	 * @param orgType 
	 * @return
	 */
	public static boolean isAdminCompetent(String orgType){
		if (StringUtils.isNotEmpty(orgType)&&DepartmentConstants.DEPT_TYPE_ADMIN_COMPETENT.equals(orgType)) {
				return true;
		}
		return false;
	}
	
	/**
	 * 判断机构类型是否为培训机构
	 * @param orgType
	 * @return
	 */
	public static boolean isTrainOrg(String orgType) {
		if (StringUtils.isNotEmpty(orgType)&&DepartmentConstants.DEPT_TYPE_TRAIN_ORG.equals(orgType)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断机构是否是个人机构
	 * @param deptId
	 * @return
	 */
	public static boolean isVirtualOrg(String deptId) {
		Department dept = getDepartment(deptId);
		if("1".equals(dept.getIsVirtual())) {
			return true;
		}
		return false;
	}
	
	public static void setSearchParamByLoginUserOrg(LoginUser loginUser, SearchParam searchParam){
		if(!searchParam.getParamMap().containsKey("regionsCode")){
			searchParam.getParamMap().put("regionsCodes",UserRegionsUtils.getRegionCodes(loginUser.getRegionsCode(), loginUser.getUserRegionsList()));
		}else {
			searchParam.getParamMap().put("regionsCodes",new String[]{(String)searchParam.getParamMap().get("regionsCode")});
		}
	}
	
	/**
	 * 获取机构
	 * @param orgId
	 * @return
	 */
	public static Department getDepartment(String orgId){
		IDepartmentService departmentService = (IDepartmentService)BeanLocator.getBean("departmentService");
		return departmentService.read(orgId);
	}
	
	/**
	 * 根据机构的ID获得机构的名称
	 * @param deptId
	 * @return
	 */
	public static String getDeptName(String deptId) {
		Department dept = getDepartment(deptId);
		return null==dept?"":dept.getDeptName();
	}
	
	/**
	 * 返回EasyUI的tree使用的data
	 * @param departmentList
	 * @param checkedMap
	 * @return
	 */
	public static final String getDeptsJson(List<Department> departmentList,Map<String,String> checkedMap,BigDecimal closedLevel){
		if(CollectionUtils.isNotEmpty(departmentList)){
			List<Map<String,Object>> mapList=new ArrayList<Map<String,Object>>();
			for(Department r:departmentList){
				Map<String,Object> nodeMap=new HashMap<String,Object>();
				nodeMap.put("id",r.getId()); 
				nodeMap.put("pid",r.getParentId());
				nodeMap.put("text",r.getDeptName());
				nodeMap.put("iconCls","");
				nodeMap.put("level", r.getDeptLevel());
				mapList.add(nodeMap);
			}
			return JSONUtils.getJSONString(mapList); 
		}
		return null;
	}
	
}
