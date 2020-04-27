package com.hcis.ipanther.common.user.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.hcis.ipanther.common.user.utils.UserConstants;
/**
 * 关于status、roleCode、type、studentStatus、teacherStatus的说明：
 * status：整个系统级别的状态，关于用户是否正常，是否能够使用系统，如锁定不能使用系统，未验证的要经过验证才能使用系统；
 * roleCode：用户的整体角色，必须是已经通过验证的，并在系统中可以使用对应的角色；
 * type：用户类型，只标记是教师还是学生，或者两者都有；如刚申请成为学员，或者刚导入的教师，type有对应的值，但是roleCode为空，审核通过后，roleCode才有值
 * studentStatus teacherStatus：用于type为学生或者教师，但是roleCode不是学生和教师的，即在成为教师审核流程中的状态标记
 * 
 * 用户新增入口包括：
 * 	增加管理员/增加默认管理员
 * 	用户注册-申请成为教师、学员
 * 	导入教师、学员
 * 	新增教师、学员
 * 	选择普通用户/学员成为教师
 * @author wuwentao
 * @date 2015年3月10日
 */
public class User extends UserStaff{
	
	private static final long serialVersionUID = 1569888312062245240L;

	//用户名
	private String userName;
	//密码
	private String password;
	//密码明文
	private String passwordPlain;
	//角色编码 字典项：ROLE_CODE  UserConstants.USER_ROLECODE_*
	private String roleCode;
	/**
	 * 类型，区别是学生还是老师，身份标识
	 * 值与ROLE_CODE类似，只标记教师、学生，教师和学生三种类型
	 * 字典与roleCode相同
	 */
	private String type;
	//学生审核状态 对应字典项：STUDENT_STATUS 对应枚举User.StudentStatus
	private String studentStatus;
	//教师审核状态 对应字典项：TEACHER_STATUS 对应枚举User.TeacherStatus
	private String teacherStatus;
	//姓名
	private String realName;
	//性别 字典项：SEX_TYPE
	private String sex;
	//国籍
	private String country;
	//民族 -作废
	private String nation;
	//籍贯
	private String hometown;
	//籍贯省
	private String hometownProvince;
	//籍贯市
	private String hometownCity;
	//籍贯区
	private String hometownCounties;
	//证件类型
	private String paperworkType;
	//证件号
	private String paperworkNo;
	//婚姻状况 -作废
	private String maritalStatus;
	//政治面貌
	private String politicsRole;
	//密码问题
	private String passwordAsk;
	//密码答案
	private String passwordAnswer;
	//出生年月
	private String bornDate;
	//参加工作日期
	private Date workDate;
	//职务
	private String job;
	//邮箱
	private String email;
	//手机
	private String mobilePhone;
	//工作单位
	private String officeAddress;
	//办公电话
	private String officePhone;
	//家庭电话
	private String homePhone;
	//现在住址
	private String homeAddress;
	//通信地址
	private String corrAddress;
	//邮政编码
	private String postCode;
	//个人主页
	private String homePage;
	//即时通讯号码
	private String imNo;
	//个人头像
	private String avatar;
	//是否是管理员 -作废
	private String isAdmin;
	//管理员级别 -作废
	private String adminLevel;
	//管理员起始日期 -作废
	private Date adminStartDate;
	//管理员终止日期 -作废
	private Date adminEndDate;
	//有效期开始时间
	private Date startTime;
	//有效期结束时间
	private Date endTime;
	//状态 SELECT t.*, ROWID FROM ipanther_dict_entry t WHERE t.dict_type_code ='USER_STATUS'
	private String status;
	//所属部门名称
	private String belongDeptName;
	//当前所属地区-省
	private String currentProvince;
	//当前所属地区-市
	private String currentCity;
	//当前所属地区-区
	private String currentCounties;
	//当前所属地区-街道
	private String currentStreet;
	//个人简介
	private String introduce;
	//传真
	private String faxes;
    //微信公众号的openId
	private String wechatId;
	//微信公众号的昵称
	private String wechatNickname;
	
    /*
     * 无对应数据库字段
     */
    private String deptId;
    private String deptIds;
    private String deptName;

    private String roleName;

    private String regionsCode;
    private String regionsName;
    
    private String province;
    private String city;
    private String counties;
    
    private String taskId;
    private String taskName;
    private String procInstId;
    private String procDefId;
    private String registerId;//对应IPR_USER_REGISTER的ID
    
    private int age;//年龄
    private String isPerson;	//是否人才
    
    private String abbreviateIntroduce;//introduce的简写
    
    private String expertType;	//专家类型
    
    private String unitLevel;

    /**
     * 修改密码修改前的密码
     */
    private String oldPsd;

    public String getOldPsd() {
        return oldPsd;
    }

    public void setOldPsd(String oldPsd) {
        this.oldPsd = oldPsd;
    }

    public String getDeptIds() {
        return deptIds;
    }

    public void setDeptIds(String deptIds) {
        this.deptIds = deptIds;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUnitLevel() {
		return unitLevel;
	}

	public void setUnitLevel(String unitLevel) {
		this.unitLevel = unitLevel;
	}

	public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPasswordPlain() {
        return passwordPlain;
    }

    public void setPasswordPlain(String passwordPlain) {
        this.passwordPlain = passwordPlain == null ? null : passwordPlain.trim();
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public String getHometownProvince() {
        return hometownProvince;
    }

    public void setHometownProvince(String hometownProvince) {
        this.hometownProvince = hometownProvince == null ? null : hometownProvince.trim();
    }

    public String getHometownCity() {
        return hometownCity;
    }

    public void setHometownCity(String hometownCity) {
        this.hometownCity = hometownCity == null ? null : hometownCity.trim();
    }

    public String getHometownCounties() {
        return hometownCounties;
    }

    public void setHometownCounties(String hometownCounties) {
        this.hometownCounties = hometownCounties == null ? null : hometownCounties.trim();
    }

    public String getPaperworkType() {
        return paperworkType;
    }

    public void setPaperworkType(String paperworkType) {
        this.paperworkType = paperworkType == null ? null : paperworkType.trim();
    }

    public String getPaperworkNo() {
        return paperworkNo;
    }

    public void setPaperworkNo(String paperworkNo) {
        this.paperworkNo = paperworkNo == null ? null : paperworkNo.trim();
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus == null ? null : maritalStatus.trim();
    }

    public String getPoliticsRole() {
        return politicsRole;
    }

    public void setPoliticsRole(String politicsRole) {
        this.politicsRole = politicsRole == null ? null : politicsRole.trim();
    }

    public String getPasswordAsk() {
        return passwordAsk;
    }

    public void setPasswordAsk(String passwordAsk) {
        this.passwordAsk = passwordAsk == null ? null : passwordAsk.trim();
    }

    public String getPasswordAnswer() {
        return passwordAnswer;
    }

    public void setPasswordAnswer(String passwordAnswer) {
        this.passwordAnswer = passwordAnswer == null ? null : passwordAnswer.trim();
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate == null ? null : bornDate.trim();
    }

    public Date getWorkDate() {
        return workDate;
    }

    public void setWorkDate(Date workDate) {
        this.workDate = workDate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone == null ? null : mobilePhone.trim();
    }

    public String getOfficeAddress() {
        return officeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        this.officeAddress = officeAddress == null ? null : officeAddress.trim();
    }

    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone == null ? null : officePhone.trim();
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone == null ? null : homePhone.trim();
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress == null ? null : homeAddress.trim();
    }

    public String getCorrAddress() {
        return corrAddress;
    }

    public void setCorrAddress(String corrAddress) {
        this.corrAddress = corrAddress == null ? null : corrAddress.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage == null ? null : homePage.trim();
    }

    public String getImNo() {
        return imNo;
    }

    public void setImNo(String imNo) {
        this.imNo = imNo == null ? null : imNo.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin == null ? null : isAdmin.trim();
    }

    public String getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel == null ? null : adminLevel.trim();
    }

    public Date getAdminStartDate() {
        return adminStartDate;
    }

    public void setAdminStartDate(Date adminStartDate) {
        this.adminStartDate = adminStartDate;
    }

    public Date getAdminEndDate() {
        return adminEndDate;
    }

    public void setAdminEndDate(Date adminEndDate) {
        this.adminEndDate = adminEndDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounties() {
		return counties;
	}

	public void setCounties(String counties) {
		this.counties = counties;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getRegionsName() {
		return regionsName;
	}

	public void setRegionsName(String regionsName) {
		this.regionsName = regionsName;
	}

	public String getHometown() {
		return hometown;
	}

	public void setHometown(String hometown) {
		this.hometown = hometown;
	}

	public String getRegionsCode() {
		return regionsCode;
	}

	public void setRegionsCode(String regionsCode) {
		this.regionsCode = regionsCode;
	}

	public String getBelongDeptName() {
		return belongDeptName;
	}

	public void setBelongDeptName(String belongDeptName) {
		this.belongDeptName = belongDeptName;
	}

	public String getCurrentStreet() {
		return currentStreet;
	}

	public void setCurrentStreet(String currentStreet) {
		this.currentStreet = currentStreet;
	}

	public String getCurrentProvince() {
		return currentProvince;
	}

	public void setCurrentProvince(String currentProvince) {
		this.currentProvince = currentProvince;
	}

	public String getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	public String getCurrentCounties() {
		return currentCounties;
	}

	public void setCurrentCounties(String currentCounties) {
		this.currentCounties = currentCounties;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	public String getProcDefId() {
		return procDefId;
	}

	public void setProcDefId(String procDefId) {
		this.procDefId = procDefId;
	}

	public String getRegisterId() {
		return registerId;
	}

	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getStudentStatus() {
		return studentStatus;
	}

	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
	}

	public String getTeacherStatus() {
		return teacherStatus;
	}

	public void setTeacherStatus(String teacherStatus) {
		this.teacherStatus = teacherStatus;
	}
	
	public enum StudentStatus {
		EDIT("01"),//编辑中
		AUDIT("02"),//审核中
		SUCCESS("03"),//已通过
		REJECT("04");//未通过
		private String studentStatus;
		private StudentStatus(String studentStatus) {
			this.studentStatus = studentStatus;
		}
		@Override
		public String toString() {
			return studentStatus;
		}
	}
	
	public enum TeacherStatus {
		EDIT("01"),//编辑中
		AUDIT("02"),//审核中
		SUCCESS("03"),//已通过
		REJECT("04");//未通过
		private String teacherStatus;
		private TeacherStatus(String teacherStatus) {
			this.teacherStatus = teacherStatus;
		}
		@Override
		public String toString() {
			return teacherStatus;
		}
	}

	public String getIntroduce() {
		return introduce;
	}
	
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getAbbreviateIntroduce() {
		if(null!=introduce) {
			if(introduce.length()>100){
				abbreviateIntroduce = StringUtils.abbreviate(introduce, 100);
			}else{
				abbreviateIntroduce = introduce;
			}
		}
		return abbreviateIntroduce;
	}

	public void setAbbreviateIntroduce(String abbreviateIntroduce) {
		this.abbreviateIntroduce = abbreviateIntroduce;
	}

	public String getExpertType() {
		return expertType;
	}

	public void setExpertType(String expertType) {
		this.expertType = expertType;
	}

	public String getIsPerson() {
		return isPerson;
	}

	public void setIsPerson(String isPerson) {
		this.isPerson = isPerson;
	}

	public String getFaxes() {
		return faxes;
	}

	public void setFaxes(String faxes) {
		this.faxes = faxes;
	}

	public String getWechatId() {
		return wechatId;
	}

	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}

	public String getWechatNickname() {
		return wechatNickname;
	}

	public void setWechatNickname(String wechatNickname) {
		this.wechatNickname = wechatNickname;
	}
	
}