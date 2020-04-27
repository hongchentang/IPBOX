/**
 * 
 */
package com.hcis.ipanther.common.config;

/**
 * @author Chaos
 * @date 2013-3-19
 * @time 下午2:17:41
 * 
 * 对应itlms.properties中的关键字
 *
 */
public class AppConfigConstants {
	
	public final static String NAME="app";
	
	public final static String SERVER_LOCAL_URL="server.local.url";
	public final static String SERVER_INTERNET_URL="server.internet.url";
	
	public final static String USER_DEFAULT_PASSWORD="user.defaultPassword";
	
	public final static String CONTEXT_URL="contextUrl";
	public final static String INDEX_URL="indexUrl";
	public final static String LOGIN_URL="loginUrl";
	public final static String LOGIN_SUBMIT_URL="loginSubmitUrl";
	public final static String LOGOUT_URL="logoutUrl";
	
	public final static String CONTEXT_URL_TEACHER="contextUrl.teacher";
	public final static String INDEX_URL_TEACHER="indexUrl.teacher";
	public final static String LOGIN_URL_TEACHER="loginUrl.teacher";
	public final static String LOGIN_SUBMIT_URL_TEACHER="loginSubmitUrl.teacher";
	public final static String LOGOUT_URL_TEACHER="logoutUrl.teacher";
	
	public final static String IGNORE_URL="ignoreUrl";

	//机构类型为行政主管部门 DepartmentConstants.DEPT_TYPE_ADMIN_COMPETENT
	public final static String USER_DEFAULTUSERNAME_ADMINCOMPETENT="user.defaultUserName.AdminCompetent";
	//机构类型为其他行政部门 DepartmentConstants.DEPT_TYPE_ADMIN_MINOR
	public final static String USER_DEFAULTUSERNAME_ADMINMINOR="user.defaultUserName.AdminMinor";
	//机构类型为培训机构 DepartmentConstants.DEPT_TYPE_TRAIN_ORG
	public final static String USER_DEFAULTUSERNAME_TRAINORG="user.defaultUserName.TrainOrg";
	//机构类型为学校 DepartmentConstants.DEPT_TYPE_SCHOOL
	public final static String USER_DEFAULTUSERNAME_SCHOOL="user.defaultUserName.School";
	//序列重复取值的最大次数限制，0为不限制
	public final static String SEQ_RETRYTIME="seq.RetryTime";
	
	//PDF生成用的密码
	public final static String PDF_FILE_PASSWORD="pdf.file.password";
	public final static String PDF_FILE_PATH="pdf.file.path";
	
	public final static String PDF_USERYEARSTUDYHOURS_URL="pdf.userYearStudyHours.url";
	public final static String FILE_AVATAR_PATH="file.avatar.path";
	
	
	//个人选修SSO
	public final static String PERSONSTUDY_SSO_URL="study.sso.url";
	public final static String PERSONSTUDY_WEBSERVICE_WSDL_URL_GRZX="study.webservice.wsdl.url.grzx";
	
	public final static String ICOMMUNITY_SSO_URL="icommunity.sso.url";
	public final static String ICOMMUNITY_READPOST_URL="icommunity.readPost.url";
	public final static String ICOMMUNITY_WEBSERVICE_WSDL_URL_LISTPOSTINDEX="icommunity.webservice.wsdl.url.listPostIndex";
	
	public final static String ITLMS_WEBSERVICE_CLIENTID="itlms.webservice.clientId";
	public final static String ITLMS_WEBSERVICE_CLIENTPASSWORD="itlms.webservice.clientPassword";
	

	/**
	 * 各单位类型对应的默认名字前缀的序列号
	 */
	public final static String USER_DEFAULTUSERNAME_AREA_ADMIN = "user.defaultUserName.AreaAdmin";//区域管理员
	
}
