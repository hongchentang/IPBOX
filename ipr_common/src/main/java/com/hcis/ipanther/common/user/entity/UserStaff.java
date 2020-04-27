package com.hcis.ipanther.common.user.entity;

import java.util.Date;

import com.hcis.ipanther.core.entity.BaseEntity;
/**
 * 用户资料扩展信息
 * 与ipanther_user是一对一的关系
 * @author wuwentao
 * @date 2015年3月10日
 */
public class UserStaff extends BaseEntity{
	
	private static final long serialVersionUID = 46971353766266164L;

	//用户ID
	private String userId;
	//用户分类
	private String userType;
	//最高学历
	private String highDiploma;
	//最高学历专业
	private String highSubject;
	//最高学历毕业院校
	private String highCollege;
	//最高学历毕业时间
	private Date highDt;
	//最高学位
	private String highDegree;
	//最高学位授予单位
	private String highDegreeUnit;
	//最高学位授予时间
	private Date highDegreeDt;
	//外国语种
	private String foreignLanguages;
	//外国熟练程度
	private String foreignDegree;
	//外语水平
	private String foreignLevel;
	//计算机水平
	private String computerLevel;
	//普通话水平
	private String chineseLevel;
	//最高专业技术资格名称
	private String proName;
	//最高专业技术资格评定日期
	private Date proDt;
	//现聘专业技术职务
	private String proJob;
	//专家类别
	private String expertType;
	//专家级别
	private String expertLevel;
	//当前职称
	private String title;
	//职称认定时间
	private Date titleDt;
	//研究领域
	private String researchField;
	//研究专长
	private String researchSpecial;
	//培训领域
	private String trainDomain;
	//培训方向
	private String trainDirection;
	//是否有知识产权教学或实务工作经验 对应字典项：HAVE_EXP
	private String haveExp;
	//从事知识产权教学或授课的相关经历
	private String expDesc;
	//主要学术著作及论文
	private String mainThesis;
	//收款账号
	private String bankAccount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getHighDiploma() {
        return highDiploma;
    }

    public void setHighDiploma(String highDiploma) {
        this.highDiploma = highDiploma == null ? null : highDiploma.trim();
    }

    public String getHighSubject() {
        return highSubject;
    }

    public void setHighSubject(String highSubject) {
        this.highSubject = highSubject == null ? null : highSubject.trim();
    }

    public String getHighCollege() {
        return highCollege;
    }

    public void setHighCollege(String highCollege) {
        this.highCollege = highCollege == null ? null : highCollege.trim();
    }

    public Date getHighDt() {
        return highDt;
    }

    public void setHighDt(Date highDt) {
        this.highDt = highDt;
    }

    public String getHighDegree() {
        return highDegree;
    }

    public void setHighDegree(String highDegree) {
        this.highDegree = highDegree == null ? null : highDegree.trim();
    }

    public String getHighDegreeUnit() {
        return highDegreeUnit;
    }

    public void setHighDegreeUnit(String highDegreeUnit) {
        this.highDegreeUnit = highDegreeUnit == null ? null : highDegreeUnit.trim();
    }

    public Date getHighDegreeDt() {
        return highDegreeDt;
    }

    public void setHighDegreeDt(Date highDegreeDt) {
        this.highDegreeDt = highDegreeDt;
    }

    public String getForeignLanguages() {
        return foreignLanguages;
    }

    public void setForeignLanguages(String foreignLanguages) {
        this.foreignLanguages = foreignLanguages == null ? null : foreignLanguages.trim();
    }

    public String getForeignDegree() {
        return foreignDegree;
    }

    public void setForeignDegree(String foreignDegree) {
        this.foreignDegree = foreignDegree == null ? null : foreignDegree.trim();
    }

    public String getForeignLevel() {
        return foreignLevel;
    }

    public void setForeignLevel(String foreignLevel) {
        this.foreignLevel = foreignLevel == null ? null : foreignLevel.trim();
    }

    public String getComputerLevel() {
        return computerLevel;
    }

    public void setComputerLevel(String computerLevel) {
        this.computerLevel = computerLevel == null ? null : computerLevel.trim();
    }

    public String getChineseLevel() {
        return chineseLevel;
    }

    public void setChineseLevel(String chineseLevel) {
        this.chineseLevel = chineseLevel == null ? null : chineseLevel.trim();
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName == null ? null : proName.trim();
    }

    public Date getProDt() {
        return proDt;
    }

    public void setProDt(Date proDt) {
        this.proDt = proDt;
    }

    public String getProJob() {
        return proJob;
    }

    public void setProJob(String proJob) {
        this.proJob = proJob == null ? null : proJob.trim();
    }

    public String getExpertType() {
        return expertType;
    }

    public void setExpertType(String expertType) {
        this.expertType = expertType == null ? null : expertType.trim();
    }

    public String getExpertLevel() {
        return expertLevel;
    }

    public void setExpertLevel(String expertLevel) {
        this.expertLevel = expertLevel == null ? null : expertLevel.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getTitleDt() {
        return titleDt;
    }

    public void setTitleDt(Date titleDt) {
        this.titleDt = titleDt;
    }

    public String getResearchField() {
        return researchField;
    }

    public void setResearchField(String researchField) {
        this.researchField = researchField == null ? null : researchField.trim();
    }

    public String getResearchSpecial() {
        return researchSpecial;
    }

    public void setResearchSpecial(String researchSpecial) {
        this.researchSpecial = researchSpecial == null ? null : researchSpecial.trim();
    }

	public String getTrainDomain() {
		return trainDomain;
	}

	public void setTrainDomain(String trainDomain) {
		this.trainDomain = trainDomain;
	}

	public String getTrainDirection() {
		return trainDirection;
	}

	public void setTrainDirection(String trainDirection) {
		this.trainDirection = trainDirection;
	}

	public String getHaveExp() {
		return haveExp;
	}

	public void setHaveExp(String haveExp) {
		this.haveExp = haveExp;
	}

	public String getExpDesc() {
		return expDesc;
	}

	public void setExpDesc(String expDesc) {
		this.expDesc = expDesc;
	}

	public String getMainThesis() {
		return mainThesis;
	}

	public void setMainThesis(String mainThesis) {
		this.mainThesis = mainThesis;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
}