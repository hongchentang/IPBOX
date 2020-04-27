/**
 * 
 */
package com.hcis.ipanther.common.user.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hcis.ipanther.common.user.entity.User;
import com.hcis.ipanther.core.persistence.dao.MyBatisDao;
import com.hcis.ipanther.core.web.vo.SearchParam;

/**
 * @author Chaos
 * @date 2013-3-8
 * @time 下午5:38:37
 *
 */
@Repository
public class UserDao extends MyBatisDao {
	
	/**
	 * 按部门读取
	 * @param departmentId
	 * @return
	 */
	public List<User> selectByDepartmentId(String departmentId){
		return this.getSqlSession().selectList(namespace+".selectByDepartmentId", departmentId);
	}
	
	/**
	 * 按身份证更新
	 * @param user
	 * @return
	 */
	public int updateByPaperworkNo(User user){
		return this.getSqlSession().update(namespace + ".updateByPaperworkNo", user);
	}

	/**
	 * 按编号查找下属用户
	 * @param user
	 * @return
	 */
	public List<User> selectByDepartCode(SearchParam searchParam){
		return this.getSqlSession().selectList(namespace + ".selectByDepartCode", searchParam);
	}

	/**
	 * 检查登录名和身份证是否重复
	 * @param searchParam
	 * @return
	 */
	public List<User> checkExistUser(SearchParam searchParam){
		return this.getSqlSession().selectList(namespace + ".checkExistUser", searchParam);
	}
	
	public List<User> selectUserList(SearchParam searchParam){
		return this.getSqlSession().selectList(namespace + ".selectUserList", searchParam);
	}
	
	public Map<String, Object> selectUserByPrimaryKey(User user){
		return (Map<String, Object>) this.selectOne(namespace + ".selectUserByPrimaryKey", user);
	}
	
	/**
	 * 注册用户审核查询
	 * @param searchParam
	 * @return
	 */
	public List<User> selectAuditStudent(SearchParam searchParam){
		return this.selectForList(namespace + ".selectAuditStudent", searchParam);
	}
	
	/**
	 * 教师注册流程待办任务
	 * @param searchParam
	 * @return
	 */
	public List<User> selectTeacherTodo(SearchParam searchParam) {
		return this.selectForList(namespace + ".selectTeacherTodo", searchParam);
	}
	
	/**
	 * 教师注册流程已办任务
	 * @param searchParam
	 * @return
	 */
	public List<User> selectTeacherDone(SearchParam searchParam) {
		return this.selectForList(namespace + ".selectTeacherDone", searchParam);
	}
	
	/**
	 * CMS展示教师信息
	 * @param searchParam
	 * @return
	 */
	public List<User> selectTeacherForCMS(SearchParam searchParam) {
		return this.selectForList(namespace + ".selectTeacherForCMS", searchParam);
	}
	
 
	public List<Map<String,Object>> selectCountRegister(Map<String,Object> map) {
		return this.selectForList(namespace + ".selectCountRegister", map);
	}
	/**
	 * 课程审核通过时，对人才或者行业的人员发送课程推送信息
	 * @param searchParam
	 * @return
	 */
	public List<User> selectUserSendEmail(SearchParam searchParam){
		return this.selectForList(namespace+".selectUserSendEmail",searchParam);
	}
	
	/**
	 * 根据用户id查找出用户
	 * @param map
	 * @return
	 */
	public User selectUserById(Map<String,String> map){
		return (User) this.selectForOneVO(namespace+".selectUserById",map);
	}
	
	/**
	 * 根据传入的邮箱或手机判断该邮箱或手机是否已经注册过（用于注册验证码发送）
	 * @param map
	 * @return
	 */
	public List<String> selectByEmailOrPhone(Map<String,String> map){
		return this.selectForList(namespace + ".selectByEmailOrPhone",map);
	}
	
	/**
	 * 专家列表
	 * @param searchParam
	 * @return
	 */
	public List<User> selectExpertBySearchParam(SearchParam searchParam){
		return this.getSqlSession().selectList(namespace + ".selectExpertBySearchParam", searchParam);
	}

    public User getByUserName(String userName) {
    	return this.getSqlSession().selectOne(namespace + ".selectByUserName", userName);
	}

    public User getByOpenId(String openId) {
    	return this.getSqlSession().selectOne(namespace + ".getByOpenId", openId);
	}

    public User getByPhone(String phone) {
    	return this.getSqlSession().selectOne(namespace + ".getByPhone", phone);
	}

    public List<Map> selectWaitBind(String id) {
    	return this.getSqlSession().selectList(namespace + ".selectWaitBind", id);
	}

    public void updateWxUserStatus(SearchParam searchParam) {
    	this.getSqlSession().update(namespace + ".updateWxUserStatus", searchParam);
	}

}
