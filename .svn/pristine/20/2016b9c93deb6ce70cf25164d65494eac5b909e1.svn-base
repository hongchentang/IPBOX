/**
 * 
 */
package com.hcis.ipanther.common.user.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springside.modules.utils.Collections3;

import com.hcis.ipanther.common.config.AppConfigConstants;
import com.hcis.ipanther.common.dept.entity.Department;
import com.hcis.ipanther.common.dept.service.IDepartmentService;
import com.hcis.ipanther.common.login.service.ILoginService;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.security.dao.RoleDao;
import com.hcis.ipanther.common.security.utils.RoleConstant;
import com.hcis.ipanther.common.security.utils.SecurityRoleUtils;
import com.hcis.ipanther.common.seq.service.ISeqService;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.service.IUserService;
import com.hcis.ipanther.core.utils.AppConfig;
import com.hcis.ipanther.core.utils.BeanLocator;
import com.hcis.ipanther.core.utils.IdCardUtils;
import com.hcis.ipanther.core.web.vo.SearchParam;

/**
 * @author Chaos
 * @date 2013-3-13
 * @time 下午5:15:37
 *
 */
public class UserUtils {
	
	/**
	 * 获取头像保存路径
	 * 以省份证号的前六位为目录
	 * /avatar/xxxxxx/{idcard}.jpg
	 */
	public final static String getAvatarSavePath(String paperworkNo){
		String path="";
		//按身份证计算路径
		if(IdCardUtils.validateCard(paperworkNo)){
			path="/avatar/"+paperworkNo.substring(0, 6)+"/";
		}
		else{
			path="/avatar/000000/";
		}
		return path;
	}
	
	public static final User readUser(String userId){
		IUserService userService=(IUserService)BeanLocator.getBean("userService");
		return userService.read(userId);
	}
	
	public static final LoginUser readLoginUser(String userId){
		ILoginService loginService=(ILoginService)BeanLocator.getBean("loginService");
		return loginService.getLoginUser(userId);
	}
	
	public static final Department readDepartment(String departId){
		IDepartmentService deptBizService=(IDepartmentService)BeanLocator.getBean("departmentService");
		return deptBizService.read(departId);
	}
	
	/**
	 * 读取用户的真实姓名
	 * @param userId
	 * @return
	 */
	public static final String getUserRealName(String userId) {
		String realName = "";
		if(StringUtils.isNotEmpty(userId)) {
			User user = readUser(userId);
			if(null!=user) {
				realName = user.getRealName();
			}
		}
		return realName;
	}
	
	/**
	 * 生成默认账号,格式为：字符串+序列值
	 * 字符串从app.properties中获取
	 * 序列值从数据库中读取
	 * @param userId 操作人ID
	 * @return
	 */
	public static Map<String,Object> generatDefaultUserInfos(ISeqService seqService,String userId){
		Map<String,Object> userInfos = new HashMap<String,Object>();
		String defaultUserName=null;
		String roleCodes ="";//若有多个角色，则用逗号隔开
		String seqCode=AppConfigConstants.USER_DEFAULTUSERNAME_AREA_ADMIN;
		
		/**
		 * 获得用户编码
		 */
		BigDecimal num=seqService.generatSeq(seqCode, userId);
		if(num!=null){
			DecimalFormat dformat=new DecimalFormat("000000");
			defaultUserName=AppConfig.getFormatProperty(AppConfigConstants.NAME, seqCode, new String[]{dformat.format(num)});
			userInfos.put("defaultUserName", defaultUserName);
		}
		/**
		 * 获取角色
		 */
		roleCodes =  AppConfig.getProperty(AppConfigConstants.NAME,seqCode+".role");
		
		userInfos.put("roleCodes", roleCodes);
		return userInfos;
	}
	
	/**
	 * 根据角色判断用户是否是跳转到个人端
	 * 角色为空的一般为普通用户，不具备任何角色
	 * @param roleCode
	 * @return
	 */
	public static boolean isSpace(String roleCode) {
		if(StringUtils.isEmpty(roleCode)||UserConstants.USER_ROLECODE_STUDENT.equals(roleCode)
				||UserConstants.USER_ROLECODE_TEACHER.equals(roleCode)
				||UserConstants.USER_ROLECODE_STUDENT_TEACHER.equals(roleCode)) {
			return true;
		}
		return false;
	}
	

	/**
	 * 判断用户的身份或者类型是否是教师
	 * 当传入User.roleCode时，返回是否是教师角色
	 * 当传入User.type时，返回是否是教师类型
	 * roleCode和type的区别，请参考 User.java 的注释说明
	 * @param roleCodeOrType 可传入User.roleCode或者User.type
	 * @return
	 */
	public static boolean isTeacher(String roleCodeOrType) {
		if(UserConstants.USER_ROLECODE_TEACHER.equals(roleCodeOrType)||UserConstants.USER_ROLECODE_STUDENT_TEACHER.equals(roleCodeOrType)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断用户的身份或者类型是否是学生
	 * 当传入User.roleCode时，返回是否是学生角色
	 * 当传入User.type时，返回是否是学生类型
	 * roleCode和type的区别，请参考 User.java 的注释说明
	 * @param roleCodeOrType 可传入User.roleCode或者User.type
	 * @return
	 */
	public static boolean isStudent(String roleCodeOrType) {
		if(UserConstants.USER_ROLECODE_STUDENT.equals(roleCodeOrType)||UserConstants.USER_ROLECODE_STUDENT_TEACHER.equals(roleCodeOrType)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 得到角色名称
	 * roleCode是User里的roleCode
	 * @param roleCode
	 * @return
	 */
	public static String getRoleName(String roleCode) {
		String roleName = "普通用户";
		if(StringUtils.isNotEmpty(roleCode)) {
			switch (roleCode) {
			case UserConstants.USER_ROLECODE_SUPER_ADMIN:
				roleName = "超级管理员";
				break;
			case UserConstants.USER_ROLECODE_ADMIN:
				roleName = "管理员";
				break;
			case UserConstants.USER_ROLECODE_STUDENT_TEACHER:
				roleName = "学员和教师";
				break;
			case UserConstants.USER_ROLECODE_STUDENT:
				roleName = "学员";
				break;
			case UserConstants.USER_ROLECODE_TEACHER:
				roleName = "教师";
				break;
			default:
				break;
			}
		}
		return roleName;
	}
	
	/**
	 * 获得【当前】用户可以授权的角色
	 * 学生和教师角色不允许通过界面授权给予；
	 * @return
	 */
	public static final List<String> getCanGrantRoleCodes() {
		LoginUser loginUser=(LoginUser)SecurityUtils.getSubject().getPrincipal();
		boolean isSystemAdmin = SecurityRoleUtils.isSystemAdmin(loginUser.getId());//是否是运维管理员或者超级管理员
		List<String> roles = null;
		if(isSystemAdmin) {//系统管理员--全部角色
			RoleDao roleDao= (RoleDao) BeanLocator.getBean("roleDao");
			SearchParam searchParam = new SearchParam();
			searchParam.setPageAvailable(false);//不分页
			roles = Collections3.extractToList(roleDao.selectBySearchParam(searchParam), "roleCode");
		} else {//自己持有的角色
			roles = Collections3.extractToList(loginUser.getRoleList(), "roleCode");
		}
		roles.remove(RoleConstant.STUDENT);//去掉学生角色
		roles.remove(RoleConstant.TEACHER);//去掉教师角色
		return roles;
	}
	
}