/**
 * 
 */
package com.hcis.ipanther.common.user.utils;


/**
 * @author Chaos
 * @date 2013-3-18
 * @time 下午3:13:05
 * 
 * 常用代码表
 */
public class UserConstants {
	
	//数据字典名
	public static final String DICT_SEX_TYPE="SEX_TYPE";//性别
	public static final String DICT_ROLE_CODE="SEX_TYPE";//性别

	public static final String DICT_USER_TYPE="USER_TYPE";//用户类型
	public static final String DICT_ZZMM="ZZMM";//种族名称
	public static final String DICT_GJDQ="GJDQ";//国籍地区
	public static final String DICT_MZ="MZ";//民族
	public static final String DICT_PAPERWORK_TYPE="PAPERWORK_TYPE";//证件类型
	public static final String DICT_EXPERT_TYPE="EXPERT_TYPE";//专家类型
	public static final String DICT_EXPERT_LEVEL="EXPERT_LEVEL";//专家等级
	public static final String DICT_DIPLOMA_TYPE="DIPLOMA_TYPE";//学历
	public static final String DICT_DEGREE_TYPE="DEGREE_TYPE";//学位
	public static final String DICT_YZMC="YZMC";//语言名称
	public static final String DICT_WGYZSLCD="WGYZSLCD";//外国语种熟练程度
	public static final String DICT_COMPUTER_LEVEL="COMPUTER_LEVEL";//计算机等级
	public static final String DICT_HAVE_EXP="HAVE_EXP";//是否有知识产权教学或实务工作经验
	
	public static final String USER_REGIONS_HASCHILD="1";//用户状态，有效，已存入数据字典
	public static final String USER_REGIONS_NOCHILD="0";//用户状态，无效，已存入数据字典

	public static final String USER_SELECT_TYPE="selectType";//弹出框选择方式，名称
	public static final String USER_SELECT_TYPE_RADIO="radio";//弹出框选择方式，单选
	public static final String USER_="checkbox";//弹出框选择方式，多选
	
	public static final String USER_TRANSFER_COMPLETE_YES="Y";
	public static final String USER_TRANSFER_COMPLETE_NO="N";

	public static final String CHARSET_GBK="text/html;charset=GBK";
	
	public static final String  USER_PASSWORD_PLAN="888888";
	
	public static final String USER_STATUS_ENABLE="1";//用户状态，有效
	public static final String USER_STATUS_LOCK = "-1";//用户状态，锁定
	public static final String USER_STATUS_NO_CONFIRM="2";//用户状态，待确认、未验证
	
	/**
	 * ipanther_user的role_code 对应值
	 * 对应字典项：ROLE_CODE
	 */
	public static final String USER_ROLECODE_SUPER_ADMIN= "0";//超级管理员角色
	public static final String USER_ROLECODE_ADMIN="1";//管理员角色
	public static final String USER_ROLECODE_STUDENT="2";//人才/学员角色
	public static final String USER_ROLECODE_TEACHER="4";//教师角色
	public static final String USER_ROLECODE_STUDENT_TEACHER="6";//学员兼教师角色
	public static final String USER_ROLECODE_EXPERT="7";
	/**
	 * 企业员工
	 */
	public static final String USER_ROLE_CODE_EMPLOYEE="8";
	/**
	 * 部门管理员
	 */
	public static final String USER_ROLE_CODE_DEPT_ADMIN="9";
	/**
	 * 信息管理员
	 */
	public static final String USER_ROLE_CODE_CMS_INFO_ADMIN="10";
	/**
	 * 测试
	 */
	public static final String USER_ROLE_CODE_TEST="11";

	/**
	 * 教师注册流程定义KEY
	 */
	public static final String TEACHER_FLOW_DEPT_DEF_KEY = "TEACHER_FLOW_DEPT";//管理员发起
	public static final String TEACHER_FLOW_USER_DEF_KEY = "TEACHER_FLOW_USER";//用户发起
	
	/**
	 * 是否是人才
	 */
	public static final String PERSON_UNDEFINED="00";	//未定义
	public static final String PERSON_YES="01";			//是
	public static final String PERSON_NO="02";			//否
	
}
