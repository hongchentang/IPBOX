package com.hcis.ipanther.common.login.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hcis.ipanther.common.login.utils.LoginConstants;
import com.hcis.ipanther.common.security.entity.Privilege;
import com.hcis.ipanther.common.security.entity.Role;
import com.hcis.ipanther.common.user.entity.UserRegions;
import com.hcis.ipanther.core.security.shiro.ShiroUser;

public class LoginUser extends ShiroUser{
	
	public LoginUser(){
		
	}
	
	public LoginUser(String id, String userName, String paperworkNo,String realName,String roleCode,
			String deptName,BigDecimal deptLevel, String deptType, String regionsCode, List<String> deptIds,
			String avatar,String userRole,String password,String type, String firstDeptId) {
		super();
		this.id = id;
		this.userName = userName;
		this.paperworkNo = paperworkNo;
		this.realName = realName;
		this.roleCode = roleCode;
		this.deptName = deptName;
		this.deptType = deptType;
		this.deptLevel = deptLevel;
		this.regionsCode = regionsCode;
		this.avatar = avatar;
		this.userRole=userRole;
		this.password= password;
		this.type = type;
		this.deptIds = deptIds;
		this.firstDeptId = firstDeptId;
	}
	
	private static final long serialVersionUID = 888239198447609501L;

	private String id;
	private String userName;
	private String password;
	private String roleCode;
	private String userRole;
	private String paperworkNo;
	private String realName;
	//private String deptId;
	private String deptName;
	private BigDecimal deptLevel;
	private String deptType;
	private String parentId;
	private String regionsCode;
	private String avatar;
	private String passwordPlain;
	private String dataPosition;
	
	private String ipAddress;
	private String os;
	private String loginType;
	private String type;
	
	private String wechatId;

	private String phone;

	private List<String> deptIds;

	private String firstDeptId;

	/**
	 * 组织id（最高层级）
	 */
	private String companyId;

	private List<UserRegions> userRegionsList=new ArrayList<UserRegions>(); 
	
	private Map hoursMap=new HashMap();
	
	private List<Role> roleList=new ArrayList<Role>(); 
	
	private String menu;

	/**
	 * 菜单列表
	 */
	private List<Privilege> privileges;

	private Map<String,String> menuMap=new HashMap<String,String>();//moduleId,menu

	public static LoginUser loginUser(HttpServletRequest request){
		LoginUser loginUser  = (LoginUser)request.getSession().getAttribute(LoginConstants.LOGIN_USER);
		return loginUser;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	public String getFirstDeptId() {
		return firstDeptId;
	}

	public void setFirstDeptId(String firstDeptId) {
		this.firstDeptId = firstDeptId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPaperworkNo() {
		return paperworkNo;
	}

	public void setPaperworkNo(String paperworkNo) {
		this.paperworkNo = paperworkNo;
	}

	public String getDeptType() {
		return deptType;
	}

	public void setDeptType(String deptType) {
		this.deptType = deptType;
	}

	public List<String> getDeptIds() {
		return deptIds;
	}

	public void setDeptIds(List<String> deptIds) {
		this.deptIds = deptIds;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public String getPasswordPlain() {
		return passwordPlain;
	}

	public void setPasswordPlain(String passwordPlain) {
		this.passwordPlain = passwordPlain;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public Map getHoursMap() {
		return hoursMap;
	}

	public void setHoursMap(Map hoursMap) {
		this.hoursMap = hoursMap;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public BigDecimal getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(BigDecimal deptLevel) {
		this.deptLevel = deptLevel;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getDataPosition() {
		return dataPosition;
	}

	public void setDataPosition(String dataPosition) {
		this.dataPosition = dataPosition;
	}

	public Map<String, String> getMenuMap() {
		return menuMap;
	}

	public void setMenuMap(Map<String, String> menuMap) {
		this.menuMap = menuMap;
	}

	public String getRegionsCode() {
		return regionsCode;
	}

	public void setRegionsCode(String regionsCode) {
		this.regionsCode = regionsCode;
	}

	public List<UserRegions> getUserRegionsList() {
		return userRegionsList;
	}

	public void setUserRegionsList(List<UserRegions> userRegionsList) {
		this.userRegionsList = userRegionsList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}


}
