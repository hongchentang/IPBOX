package com.hcis.ipanther.common.user.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hcis.ipanther.common.user.dto.RegisterDto;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hcis.ipanther.common.attachment.entity.Attachment;
import com.hcis.ipanther.common.login.vo.LoginUser;
import com.hcis.ipanther.common.security.entity.UserRole;
import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.common.user.entity.UserRegisterFlow;
import com.hcis.ipanther.common.user.entity.UserStaff;
import com.hcis.ipanther.common.dept.entity.Department;
import com.hcis.ipanther.core.service.IBaseService;
import com.hcis.ipanther.core.web.vo.SearchParam;
import com.hcis.ipr.core.entity.Response;

public interface IUserService extends IBaseService<User> {

	/**
	 *
	 * @param searchParam
	 * @return
	 */
	public List<User> listUser(SearchParam searchParam);
	
	/**
	 * Read userview.
	 *
	 * @param user the user view
	 * @return the map
	 */
	public Map<String, Object> readUserview(User user);
	
	public int saveBatchRoleConfig(SearchParam searchParam,String roleId);
	
	public int saveRoleConfig(User user, UserRole userRole);
	
	public int reSetPassword(String userId);

	public int addUserAdminDefault(String deptId,LoginUser loginUser);
	
	
	/**
	 * 查询当前人所在企业及其下属企业人员信息
	 * @param searchParam
	 * @return
	 */
	public List<User> listEmployees(SearchParam searchParam);
	
	/**
	 * 查询当前人所在区域及辖下区域的人才信息
	 * @param searchParam
	 * @return
	 */
	public List<User> listStudent(SearchParam searchParam);
	
	/**
	 * 查询教师列表
	 * @param searchParam
	 * @return
	 */
	public List<User> listTeacher(SearchParam searchParam);
	
	/**
	 * 教师选择用户新增师资列表
	 * @param searchParam
	 * @return
	 */
	public List<User> chooseUserList(SearchParam searchParam);
	
	/**
	 * 教师选择用户新增师资列表确定请求
	 * 将User的type改为教师
	 * @param user
	 * @return
	 */
	public int chooseUserDo(User user);
	
	/**
	 * 查询教师列表
	 * @param searchParam
	 * @param type
	 * @return
	 */
	public List<User> auditTeacherList(SearchParam searchParam,String type);
	
	/**
	 * 查询管理员
	 * @param searchParam
	 * @return
	 */
	public List<User> listAdmin(SearchParam searchParam);
	
	/**
	 * 保存用户信息
	 * @param isAdmin 是否是管理员
	 * @param user
	 * @param roleIds isAdmin时，为管理员所拥有的角色
	 * @return
	 */
	public int save(boolean isAdmin,User user,String roleIds);
	
	/**
	 * 保存学员/教师的基本信息
	 * @param userType student/teacher
	 * @param user
	 * @return 用户的ID
	 * @throws IOException
	 */
	public String save(String userType,User user) throws Exception;

	/**
	 * 保存学员/教师的基本信息
	 * @param userType student/teacher
	 * @param user
	 * @return 用户的ID
	 * @throws Exception
	 */
	String saveV2(String userType,User user) throws Exception;

	/**
	 * 个人端保存用户信息
	 * 如果registerType不为空，则要相对应的发起教师/学员流程
	 * @param user 
	 * @param registerType 注册类型，一般的保存为空，注册为学员为student，注册为教师为teacher
	 * @return
	 * @throws Exception 
	 */
	public String saveUserForSpace(User user, String registerType,String checkType) throws Exception;

	/**
	 * 
	 * 根据用户列表返回完整的用户信息
	 * 字典项需转换为中文
	 * @param users
	 * @return
	 * @throws Exception 
	 */
	void setUserInfosForExport(List<User> users) throws Exception;

	/**
	 * 批量导入人才信息或者教师信息
	 * 用户所在单位为当前导入人所在区域的个人单位
	 * @param userType
	 * @param file
	 * @param request
	 * @return
	 */
	String importUser(String userType,MultipartFile file, HttpServletRequest request);

	/**
	 * 【批量导入用户
	 * @param userType
	 * @param file
	 * @param request
	 * @return
	 */
	String importUserV2(String userType,MultipartFile file, HttpServletRequest request) throws IOException;

	/**
	 * 执行用户注册
	 * 注册成为平台的用户
	 * 待用户验证通过后，增加普通用户的角色
	 * 个人注册的用户全部归属到所在区域的个人单位
	 * @param request
	 * @param user
	 * @param userStaff
	 * @param checkCode
	 * @return
	 */
	Response regist(HttpServletRequest request,User user,UserStaff userStaff,String checkCode,String checkType,String code) throws IOException ;

	/**
	 * 执行机构注册
	 * 注册成为平台的用户
	 * 待用户验证通过后，增加机构的角色
	 * 个人注册的用户全部归属到所在区域的个人单位
	 * @param request
	 * @param user
	 * @param userStaff
	 * @param checkCode
	 * @return
	 */
	Response unitRegist(HttpServletRequest request,User user,UserStaff userStaff,Department department ,String checkCode,String checkType,String code) throws IOException ;

	/**
	 * 用户注册后确认信息
	 * 只有未确定的才执行确定步骤
	 * @param id
	 * @param code
	 * @return 信息提示
	 */
	String activate(String id, String code);

	/**
	 * 列出注册用户列表
	 * type=Todo 待办
	 * type=Done 已办
	 * @param searchParam
	 * @param type
	 * @return
	 */
	List<User> auditStudentList(SearchParam searchParam, String type);

	
	/**
	 * 保存审核人才、教师的结果
	 * @param flow
	 * @return
	 */
	int auditStudent(UserRegisterFlow flow);

	/**
	 * 管理员启动注册成为教师流程
	 * @param userId
	 */
	void registTeacherForDept(String userId);

	/**
	 * 保存审核教师结果
	 * @param flow
	 * @param registerId
	 * @param taskId
	 * @return
	 */
	void auditTeacher(UserRegisterFlow flow, String registerId, String taskId);
	
	/**
	 * 注册教师流程完成后由activiti调用
	 * @param execution
	 */
	void becomeTeacher(DelegateExecution execution);

	/**
	 * 教师注册流程结束监听，结束后调用
	 * @param execution
	 */
	void teacherFlowEndListener(DelegateExecution execution);

	boolean checkExistUser(SearchParam searchParam);

	/**
	 * 选择教师列表，必须拥有教师身份
	 * @param searchParam
	 * @return
	 */
	List<User> chooseTeacherList(SearchParam searchParam);
	
	/**
	 * CMS展示师资信息
	 * @param searchParam
	 * @return
	 */
	List<User> selectTeacherForCMS(SearchParam searchParam);
	
	
	public List<Map<String,Object>> selectCountRegister(Map<String,Object> map);
	/**
	 * 获取忘记密码
	 * @param request
	 * @param user
	 * @param checkCode
	 * @return
	 * @throws IOException
	 */
	public Response getPassword(HttpServletRequest request,User user,String checkCode) throws IOException ;
	/**
	 * 是否设置人才
	 * @param userIds	","拼接的多个用户id
	 */
	public void isSetPerson(String isPerson,String userIds);
	/**
	 * 课程审核通过时，对人才或者行业的人员发送课程推送信息
	 * @param searchParam
	 * @return
	 */
	public List<User> selectUserSendEmail(SearchParam searchParam);

	/**
	 * 根据传入的邮箱或手机判断该邮箱或手机是否已经注册过（用于注册验证码发送）
	 * @param map
	 * @return
	 */
	public List<String> selectByEmailOrPhone(Map<String,String> map);
	
	/**
	 * 获取专家用户列表
	 * @param searchParam
	 * @return
	 */
	public List<User> selectExperts(SearchParam searchParam);

	/**
	 * 发送注册验证邮件
	 * @param code
	 * @param email
	 */
	void sendCheckCodeMail(String code, String email);

	/**
	 * 用户注册
	 * @param register
	 * @param logo
	 * @exception
	 */
    void doRegister(RegisterDto register, MultipartFile logo) throws Exception;

	/**
	 * 查询部门管理员所管理的部门
	 * @param userId
	 * @return
	 */
	List<String> getDeptAdminDeptIds(String userId);

	/**
	 * 查询部门管理员所管理的部门2
	 * @param userId
	 * @return
	 */
	String getDeptAdminDeptIdsStr(String userId);

	/**
	 * 查询登录当前用户的下级部门
	 * @param userId
	 * @return
	 */
	List<String> getDeptIdsByUserId();

    String getRoleUserId();

	/**
	 * 查询用户关联的部门
	 * @param id
	 * @return
	 */
	List<String> getUserDeptIds(String id);

	/**
	 *
	 * @param userName
	 */
	void checkUserName(String userName);

	/**
	 * 根据微信id拿用户
	 * @param openId
	 * @return
	 */
    User getUserByOpenId(String openId);

	/**
	 *
	 * @param phone
	 * @return
	 */
	User getUserByPhone(String phone);

	/**
	 * 查询待审核的微信用户
	 * @param id
	 * @return
	 */
    List<Map> selectWaitBind(String id);

	/**
	 *
	 * @param wxUserId
	 * @param status
	 */
	void updateWxUserStatus(String wxUserId, int status);

	/**
	 *
	 * @param openId
	 * @param wxUserId
	 */
	void bindWx(String openId, String wxUserId);

	/**
	 * 是否能删除本员工
	 * @param loginUser
	 * @param user
	 */
    boolean deleteAble(LoginUser loginUser, User user);
}
