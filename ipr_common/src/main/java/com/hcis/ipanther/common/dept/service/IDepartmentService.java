package com.hcis.ipanther.common.dept.service;

		import java.util.List;
        import java.util.Map;

        import com.hcis.ipanther.common.dept.entity.Department;
		import com.hcis.ipanther.core.service.IBaseService;
		import com.hcis.ipanther.core.web.vo.SearchParam;

public interface IDepartmentService extends IBaseService<Department> {

	/**
	 * 根据区域代码返回区域下的所有机构
	 *
	 * @param dept
	 * @return
	 */
	public List<Department> getDeptByRegionsCode(Department dept);

	/**
	 * 得到区域下的个人机构
	 * @param regionsCode
	 * @return
	 */
	public Department getVirtualDeptByRegionsCode(String regionsCode);


	/**
	 * 得到企业组织机构的树形结构
	 * @param searchParam
	 * @return
	 */
	public String getDeptTree(SearchParam searchParam);


	/**
	 * 获取当前用户的ids
	 * @param currentDeptId
	 * @param deptLevel
	 * @return
	 */
	String[] getDeptIds(String currentDeptId);

	/**
	 * 查询最高级组织
	 * @param deptId
	 * @return
	 */
	String getCompanyIdByDeptId(String deptId);

	/**
	 * 获取部门树
	 * @param searchParam
	 * @return
	 */
	String getAllDeptTree(SearchParam searchParam);

	/**
	 *
	 * @param unitId
	 * @return
	 */
	List<Department> getDepartmentList(String unitId);

	/**
	 *
	 * @param companyId
	 * @return
	 */
    Map<String, Department> getDepartmentNameMap(String companyId);

	/**
	 *
	 * @param userIds
	 * @return
	 */
	Map<String, List<String>> getUserDeptMap(List<String> userIds);
}
