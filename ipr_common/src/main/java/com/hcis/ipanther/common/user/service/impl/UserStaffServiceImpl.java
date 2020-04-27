package com.hcis.ipanther.common.user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hcis.ipanther.common.dept.dao.DepartmentDao;
import com.hcis.ipanther.common.dept.entity.Department;
import com.hcis.ipanther.common.dict.utils.DictUtils;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.security.entity.Role;
import com.hcis.ipanther.common.security.service.IRoleService;
import com.hcis.ipanther.common.security.utils.RoleConstant;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.entity.UserDept;
import com.hcis.ipanther.common.user.service.IUserDeptService;
import com.hcis.ipanther.common.user.utils.UserConstants;
import com.hcis.ipanther.core.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcis.ipanther.common.user.dao.UserStaffDao;
import com.hcis.ipanther.common.user.entity.UserStaff;
import com.hcis.ipanther.common.user.service.IUserStaffService;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.service.impl.mybatis.BaseServiceImpl;
import com.hcis.ipanther.core.web.vo.SearchParam;

@Service
public class UserStaffServiceImpl extends BaseServiceImpl<UserStaff> implements IUserStaffService {
	
	private final static Log log=LogFactory.getLog(UserStaffServiceImpl.class);
	@Autowired
	private UserStaffDao baseDao;
	@Autowired
	private IUserDeptService userDeptService;
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
    private IRoleService roleService;
	
	//打印用户列表
	@Override
	public List<Map<String, Object>> userStaffInfoList(SearchParam searchParam) {

        List<Map<String, Object>> maps = baseDao.userStaffInfoList(searchParam);
        LoginUser loginUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
        Department company = departmentDao.selectByPrimaryKey(loginUser.getCompanyId());

        List<String> userIds = new ArrayList<>();
        for (Map<String, Object> u : maps) {
            userIds.add((String) u.get("userId"));
        }

        Map<String, List<UserDept>> userDeptMap = userDeptService.getUsersDept(userIds);
        for (Map<String, Object> u : maps) {
            String userId = (String) u.get("userId");
            List<UserDept> list = userDeptMap.get(userId);
            //
            u.put("deptNames", userDeptService.getUserDeptNameStr(list));
            u.put("companyName", company.getDeptName());
            u.put("roleCode", convertUserRoleCode((String) u.get("roleCode")));
        }

        return maps;
    }
	
	@Override
	public MyBatisDao getBaseDao() {
		return baseDao;
	}


    private String convertUserRoleCode(String roleCode) {
	    String roleKey = "";
        if (UserConstants.USER_ROLE_CODE_CMS_INFO_ADMIN.equals(roleCode)) {
            roleKey = RoleConstant.CMS_INFO_ADMIN;
        } else if (UserConstants.USER_ROLECODE_ADMIN.equals(roleCode)) {
            roleKey = RoleConstant.UNIT_ADMIN;
        } else if (UserConstants.USER_ROLE_CODE_DEPT_ADMIN.equals(roleCode)) {
            roleKey = RoleConstant.DEPT_ADMIN;
        } else if (UserConstants.USER_ROLE_CODE_TEST.equals(roleCode)) {
            roleKey = RoleConstant.TEST;
        } else {
            roleKey = RoleConstant.EMPLOYEE;
        }
        SearchParam searchParam = new SearchParam();
        List<Role> list = roleService.list(searchParam);

        Role role = new Role();
        role.setRoleCode(roleKey);
        int idx = list.indexOf(role);
        if(idx != -1){
            role = list.get(idx);
            return role.getName();
        }

        return null;
    }

}
