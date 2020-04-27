package com.hcis.ipanther.common.dept.service.impl;

import java.math.BigDecimal;
import java.util.*;

import com.hcis.ipanther.common.user.entity.UserDept;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.dept.dao.DepartmentDao;
import com.hcis.ipanther.common.dept.entity.Department;
import com.hcis.ipanther.common.dept.service.IDepartmentService;
import com.hcis.ipanther.common.dept.utils.DepartmentConstants;
import com.hcis.ipanther.common.dept.utils.DepartmentUtils;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.core.cache.local.CacheReloadInvoker;
import com.hcis.ipanther.core.page.Pagination;
import com.hcis.ipanther.core.utils.Identities;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Service("departmentService")
public class DepartmentServiceImpl extends CacheReloadInvoker implements IDepartmentService {
	
	private final static Log logger = LogFactory.getLog(DepartmentServiceImpl.class);
	
	@Autowired
	private DepartmentDao departmentDao;
	
	@Override
	public List<Department> list(SearchParam searchParam) {
		return departmentDao.selectBySearchParam(searchParam);
	}

	@Override
	@Cacheable(value="iprcache:deptcache:deptinfo",condition="#id!=null",key="'iprcache:deptcache:deptinfo:'+#id")
	public Department read(String id) {
		if(StringUtils.isNotEmpty(id)){
			return departmentDao.selectByPrimaryKey(id);
		}
		return null;
	}

	@Override
	public int create(Department obj, String creator) {
		if (obj != null) {
			if (StringUtils.isEmpty(obj.getId())) {
				obj.setId(Identities.uuid2());
			}
			obj.setDefaultValue();
			obj.setCreator(creator);
			return this.getBaseDao().insertSelective(obj);
		}
		return 0;
	}

	@Override
	@CacheEvict(value="iprcache:deptcache:deptinfo",condition="#obj!=null",key="'iprcache:deptcache:deptinfo:'+#obj.id")
	public int update(Department obj, String updateBy) {
		if (obj != null) {
			obj.setUpdatedby(updateBy);
			obj.setUpdateTime(new Date());
			return this.getBaseDao().updateByPrimaryKeySelective(obj);
		}
		return 0;
	}

	@Override
	@CacheEvict(value="iprcache:deptcache:deptinfo",condition="#obj!=null",key="'iprcache:deptcache:deptinfo:'+#obj.id")
	public int delete(Department obj, String updateBy) {
		if (obj != null && obj.getId() != null) {
			obj.setUpdatedby(updateBy);
			obj.setUpdateTime(new Date());
			return this.getBaseDao().deleteByLogic(obj);
		}
		return 0;
	}

	public DepartmentDao getDepartmentDao() {
		return departmentDao;
	}

	public DepartmentDao getBaseDao() {
		return departmentDao;
	}

	@Override
	public List<Department> getDeptByRegionsCode(Department dept) {
		return departmentDao.getDeptByRegionsCode(dept);
	}

	@Override
	public Department getVirtualDeptByRegionsCode(String regionsCode) {
		return departmentDao.getVirtualDeptByRegionsCode(regionsCode);
	}
	
	/**
	 * 通过登入用户，查询到当前用户所属企业，并将该企业及下属企业查询出来
	 * @param searchParam
	 * @author 宗高金  20190829
	 * @return String 
	 */
	@Override
	public String getDeptTree(SearchParam searchParam) {
		//获取登入用户的信息，用以判断当前用户属于哪一个机构
		LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		//根据用户的机构，得到所有期子机构信息（包含其自身）
		List<Department> currentDepts = getDeptIdsByUser(loginUser.getFirstDeptId(), 1);
		return DepartmentUtils.getDeptsJson(currentDepts, null, new BigDecimal(4));
	}
	
	/**
	 * 返回当前dept及其下级单位的id。
	 * @param currentDeptId
	 * @return
	 */
	@Override
	@CacheEvict(value="iprcache:deptcache:deptIds",condition="#currentDeptId!=null",key="'iprcache:deptcache:deptIds:'+#currentDeptId")
	public String[] getDeptIds(String currentDeptId){
		
		List<Department> deptsList=new ArrayList<Department>();		
		Set<Department> deptsSet=new HashSet<Department>();
		Department currentDept = DepartmentUtils.getDepartment(currentDeptId);	
		deptsSet.add(currentDept);
		deptsSet.addAll(getChildDepts(currentDept.getId(),1));		
		deptsList.addAll(deptsSet);
		String ids = "";
		for(int i =0 ;i<deptsList.size();i++){
			ids = ids+","+deptsList.get(i).getId();
		}		
		return ids.split(",");
	}

    @Override
    public String getCompanyIdByDeptId(String deptId) {
		Department department = departmentDao.getPatentById(deptId);
		if(department == null || "0".equals(department.getParentId())){
			//没有最高级了
			return deptId;
		}else if(StringUtils.isBlank(department.getParentId())){
			//当前为最高级
			return department.getId();
		} else{
			return getCompanyIdByDeptId(department.getParentId());
		}
    }

	@Override
	public String getAllDeptTree(SearchParam searchParam) {
		LoginUser loginUser= (LoginUser)SecurityUtils.getSubject().getPrincipal();
		String companyId = loginUser.getCompanyId();

		List<Department> all = departmentDao.getAllDeptByUnitId(companyId);

		return DepartmentUtils.getDeptsJson(all, null,new BigDecimal(4));
	}

	@Override
	public List<Department> getDepartmentList(String unitId) {

		List<Department> reList = new ArrayList<>();
		boolean flag = false;
		if(org.apache.commons.lang.StringUtils.isBlank(unitId)){
			//如果是空的话拿全部，包括公司
			LoginUser loginUser= (LoginUser) SecurityUtils.getSubject().getPrincipal();
			unitId = loginUser.getCompanyId();
			flag = true;
		}

		List<Department> list = departmentDao.getAllDeptByUnitId(unitId);
		for(Department dept : list){
			if(flag && !dept.getId().equals(unitId)){
				reList.add(dept);
			}
		}

		return  list;
	}

    @Override
    public Map<String, Department> getDepartmentNameMap(String companyId) {

		List<Department> list = getDepartmentList(companyId);
		Map<String, Department> map = new HashMap<>(list.size());

		for(Department department : list){
			map.put(department.getDeptName(), department);
		}

        return map;
    }

    @Override
    public Map<String, List<String>> getUserDeptMap(List<String> userIds) {

		List<UserDept> list = this.getBaseDao().getUserDeptMap(userIds);
		Map<String, List<String>> map = new HashMap<>(userIds.size());
		for(String userId : userIds){
			List<String> deptIds = new ArrayList<>();
			for(UserDept dept : list){
				String userIdQuery = dept.getUserId();
				if(userId.equals(userIdQuery)){
					deptIds.add(dept.getDeptId());
				}
			}
			map.put(userId, deptIds);
		}

        return map;
    }

    private List<Department> convertDept(List<Department> list, List<Department> all) {

		if(list != null && list.size() > 0){
			for(Department department : list){
				for(Department dept : all){
					if(dept.getParentId().equals(department.getId())){
						List<Department> child = department.getList();
						if(child == null){
							child = new ArrayList<>();
							department.setList(child);
						}
						child.add(dept);
					}
				}
			}

			for(Department department : list){
				convertDept(department.getList(), all);
			}
		}

		return null;
	}

	/**
	 * 添加当前单位的所有（上级及下级单位）
	 * @author 宗高金 20198029
	 * @param currentDeptId
	 * @param deptLevel
	 * @return
	 */
	public List<Department> getDeptIdsByUser(String currentDeptId,int deptLevel){
		List<Department> deptsList=new ArrayList<Department>();
		//使用Set，避免重复加入，判断方法见Regions中的equals
		Set<Department> deptsSet=new HashSet<Department>();
		//1.不管是不是最高级单位，先获取单位		
		Department currentDepts = getParentDepts(currentDeptId,deptLevel);
		
		//Department currentDepts=DepartmentUtils.getDepartment(currentDeptId);
				
		deptsSet.add(currentDepts);
		//2.加入默认用户组织的下属单位（实现，可能去掉）
		deptsSet.addAll(getChildDepts(currentDepts.getId(),deptLevel));		
		deptsList.addAll(deptsSet);
		
		Collections.sort(deptsList, new Comparator<Department>(){
			@Override
			public int compare(Department o1, Department o2) {
				try{
					int n1=NumberUtils.toInt(o1.getRegionsCode(),0);
					int n2=NumberUtils.toInt(o2.getRegionsCode(),0);
					return n1-n2;//正序
				}
				catch(Exception e){
					
				}
				return 0;
			}});
		return deptsList;	
	}
	
	
	/**
	 * 返回所有的子节点的List
	 * @author 宗高金 20198029
	 * @param deptId	企业或者用户Id
	 * @param deptLevel 加载到树叶的层级
	 * @return
	 */
	public List<Department> getChildDepts(String deptId,int deptLevel){
		//取出所有数据，一层一层循环后处理
		SearchParam searchParam=new SearchParam();
		/*searchParam.setPageAvailable(false);*/
		Pagination pagination =new Pagination();
		pagination.setAvailable(false);
		searchParam.setPagination(pagination);
		
		//假定此时的dept 为原始数据 root 目录
		List<Department> deptsList=new ArrayList<Department>();
		Department dept=DepartmentUtils.getDepartment(deptId);
		if(dept.getDeptLevel().intValue()==DepartmentConstants.DEPT_LEVEL_CLASS){
			searchParam.getParamMap().put("parentId", dept.getId());
			//获取dept 的第一个下级单位
			List<Department> classList=departmentDao.selectBySearchParam(searchParam);
			if(CollectionUtils.isNotEmpty(classList)){
				deptsList.addAll(classList);	
				//获取dept 的第 二个下级单位
				for(Department organize:classList){
					searchParam.getParamMap().put("parentId", organize.getId());
					List<Department> organizesList=departmentDao.selectBySearchParam(searchParam);
					if(CollectionUtils.isNotEmpty(organizesList)){
						deptsList.addAll(organizesList);
						//获取dept 的第 二个下级单位
						for (Department branch : organizesList) {
							searchParam.getParamMap().put("parentId", branch.getId());
							List<Department> branchsList=departmentDao.selectBySearchParam(searchParam);
							if(CollectionUtils.isNotEmpty(branchsList)){
								deptsList.addAll(branchsList);
							}
						}							
					}
				}
			}
			
		}else if(dept.getDeptLevel().intValue()==DepartmentConstants.DEPT_LEVEL_COMPANY){
			searchParam.getParamMap().put("parentId", dept.getId());
			//DEPT_LEVEL_ORGANIZE
			List<Department> companyList=departmentDao.selectBySearchParam(searchParam);
			if(CollectionUtils.isNotEmpty(companyList)){
				deptsList.addAll(companyList);
				//获取第二下级DEPT_LEVEL_BRANCH
				for(Department organize:companyList){
					searchParam.getParamMap().put("parentId", organize.getId());
					List<Department> organizesList=departmentDao.selectBySearchParam(searchParam);
					if(CollectionUtils.isNotEmpty(organizesList)){
						deptsList.addAll(organizesList);
					}
				}
			}
		}else if(dept.getDeptLevel().intValue()==DepartmentConstants.DEPT_LEVEL_ORGANIZE){
			searchParam.getParamMap().put("parentId", dept.getId());
			List<Department> organizesList=departmentDao.selectBySearchParam(searchParam);
			if(CollectionUtils.isNotEmpty(organizesList)){
				deptsList.addAll(organizesList);
			}
		}
		return deptsList;
	}
	/**
	 * 和获取下级单位反向，追击向上追加
	 * @author 宗高金 2018-09-01
	 * @param deptId
	 * @param deptLevel
	 * @return
	 */
	public Department getParentDepts(String deptId,int deptLevel){
		//取出所有数据，一层一层循环后处理
		SearchParam searchParam=new SearchParam();
		/*searchParam.setPageAvailable(false);*/
		Pagination pagination =new Pagination();
		pagination.setAvailable(false);
		searchParam.setPagination(pagination);
		
		//假定此时的dept为最下级单位
		Department deptsList=new Department();
		Department dept=DepartmentUtils.getDepartment(deptId);
		BigDecimal dl = dept.getDeptLevel();
		if(dl.intValue()==DepartmentConstants.DEPT_LEVEL_BRANCH){
			Department origList=DepartmentUtils.getDepartment(dept.getParentId());
			if (origList ==null){
				return dept;
			}
			Department comList=DepartmentUtils.getDepartment(origList.getParentId());
			if (comList ==null){
				return origList;
			}
			deptsList=DepartmentUtils.getDepartment(comList.getParentId());
			if (deptsList ==null){
				return comList;
			}
			
		}else if(dept.getDeptLevel().intValue()==DepartmentConstants.DEPT_LEVEL_ORGANIZE){
			Department comList=DepartmentUtils.getDepartment(dept.getParentId());
			if (comList ==null){
				return dept;
			}
			deptsList=DepartmentUtils.getDepartment(comList.getParentId());
			if (deptsList ==null){
				return comList;
			}
		}else if(dept.getDeptLevel().intValue() == DepartmentConstants.DEPT_LEVEL_COMPANY){
			deptsList=DepartmentUtils.getDepartment(dept.getParentId());
			if (deptsList ==null){
				return dept;
			}
		}else{
			return dept;
		}
		return deptsList;
	}

}
