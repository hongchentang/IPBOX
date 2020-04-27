package com.hcis.ipanther.common.dept.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hcis.ipanther.core.entity.BaseEntity;

/**
 * 机构实体
 * @author wuwentao
 * @date 2015年3月10日
 */
public class Department extends BaseEntity{
	
	private static final long serialVersionUID = 1323352671404067460L;

	//父ID
	private String parentId;
    //组织（最高父级）id
	private String unitId;
	//单位类型 对应字典项：DEPT_TYPE
	private String deptType;
	//机构编码
	private String deptNo;
	//机构名称
	private String deptName;
	//单位地址
	private String deptAddress;
	//邮政编码
	private String postCode;
	//区域
	private String regionsCode;
	//省
	private String province;
	//市
	private String city;
	//区县
	private String counties;
	//街镇
	private String street;
	//单位类别 对应字典项：DEPT_CATEGORY
	private String deptCategory;
	//单位性质 对应字典项：DEPT_NATURE_TYPE
	private String deptNature;
	//单位级别
	private BigDecimal deptLevel;
	//组织机构码
	private String deptCode;
	//联系电话
	private String linkPhone;
	//联系传真、传真电话
	private String linkFax;
	//联系邮箱
	private String linkEmail;
	//单位主页
	private String homePage;
	//联系人-暂时没用
	private String linkName;
	//联系地址-暂时没用
	private String linkAddress;
	//法定代表人姓名
	private String legalName;
	//法定代表人办公电话
	private String legalPhone;
	//法定代表人移动电话
	private String legalMobile;
	//法定代表人传真电话
	private String legalFax;
	//法定代表人电子信箱
	private String legalEmail;
	//法定代表人个人主页
	private String legalHomepage;
	//法定代表人即时通讯号
	private String legalIm;
	//单位管理员姓名
	private String adminName;
	//单位管理员办公电话
	private String adminPhone;
	//单位管理员移动电话
	private String adminMobile;
	//单位管理员传真电话
	private String adminFax;
	//单位管理员电子信箱
	private String adminEmail;
	//单位管理员个人主页
	private String adminHomepage;
	//单位管理员即时通讯号
	private String adminIm;
	//状态
	private String status;
	//单位公章
	private String sealImg;
	//单位签名
	private String signImg;
	//排序号
	private BigDecimal sortNo;
	//备注
	private String remark;
	//基地级别 对应字典项：TRAIN_ORG_LEVEL
	private String trainOrgLevel;
	//基地特色
	private String trainOrgFeature;
	//是否是个人机构：0否1是
	private String isVirtual;
	//单位的LOGO
	private String logo;
	
	private String regionsName;
	//行业分类
	private String industryType;
	//技术分类
    private String technologyType;
	//上级单位
	private String deptLeader;
	//企业证书号
	private String deptLegalNo;
	//国税登记号
	private String nationTaxNo;
	//地税登记号
	private String cityTaxNo;
	//注册资金
	private String deptRegisteredFund;
	//注册自己单位
	private String deptRegisteredFundUnit;
	//注册日期
	private Date deptRegisteredDate;

	private List<Department> list;

    public List<Department> getList() {
        return list;
    }

    public void setList(List<Department> list) {
        this.list = list;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getDeptType() {
        return deptType;
    }

    public void setDeptType(String deptType) {
        this.deptType = deptType == null ? null : deptType.trim();
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo == null ? null : deptNo.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public String getDeptAddress() {
        return deptAddress;
    }

    public void setDeptAddress(String deptAddress) {
        this.deptAddress = deptAddress == null ? null : deptAddress.trim();
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode == null ? null : postCode.trim();
    }

    public String getRegionsCode() {
        return regionsCode;
    }

    public void setRegionsCode(String regionsCode) {
        this.regionsCode = regionsCode == null ? null : regionsCode.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCounties() {
        return counties;
    }

    public void setCounties(String counties) {
        this.counties = counties == null ? null : counties.trim();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    public String getDeptCategory() {
        return deptCategory;
    }

    public void setDeptCategory(String deptCategory) {
        this.deptCategory = deptCategory == null ? null : deptCategory.trim();
    }

    public String getDeptNature() {
        return deptNature;
    }

    public void setDeptNature(String deptNature) {
        this.deptNature = deptNature == null ? null : deptNature.trim();
    }

    public BigDecimal getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(BigDecimal deptLevel) {
        this.deptLevel = deptLevel;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode == null ? null : deptCode.trim();
    }

    public String getLinkPhone() {
        return linkPhone;
    }

    public void setLinkPhone(String linkPhone) {
        this.linkPhone = linkPhone == null ? null : linkPhone.trim();
    }

    public String getLinkFax() {
        return linkFax;
    }

    public void setLinkFax(String linkFax) {
        this.linkFax = linkFax == null ? null : linkFax.trim();
    }

    public String getLinkEmail() {
        return linkEmail;
    }

    public void setLinkEmail(String linkEmail) {
        this.linkEmail = linkEmail == null ? null : linkEmail.trim();
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage == null ? null : homePage.trim();
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName == null ? null : linkName.trim();
    }

    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress == null ? null : linkAddress.trim();
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName == null ? null : legalName.trim();
    }

    public String getLegalPhone() {
        return legalPhone;
    }

    public void setLegalPhone(String legalPhone) {
        this.legalPhone = legalPhone == null ? null : legalPhone.trim();
    }

    public String getLegalMobile() {
        return legalMobile;
    }

    public void setLegalMobile(String legalMobile) {
        this.legalMobile = legalMobile == null ? null : legalMobile.trim();
    }

    public String getLegalFax() {
        return legalFax;
    }

    public void setLegalFax(String legalFax) {
        this.legalFax = legalFax == null ? null : legalFax.trim();
    }

    public String getLegalEmail() {
        return legalEmail;
    }

    public void setLegalEmail(String legalEmail) {
        this.legalEmail = legalEmail == null ? null : legalEmail.trim();
    }

    public String getLegalHomepage() {
        return legalHomepage;
    }

    public void setLegalHomepage(String legalHomepage) {
        this.legalHomepage = legalHomepage == null ? null : legalHomepage.trim();
    }

    public String getLegalIm() {
        return legalIm;
    }

    public void setLegalIm(String legalIm) {
        this.legalIm = legalIm == null ? null : legalIm.trim();
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone == null ? null : adminPhone.trim();
    }

    public String getAdminMobile() {
        return adminMobile;
    }

    public void setAdminMobile(String adminMobile) {
        this.adminMobile = adminMobile == null ? null : adminMobile.trim();
    }

    public String getAdminFax() {
        return adminFax;
    }

    public void setAdminFax(String adminFax) {
        this.adminFax = adminFax == null ? null : adminFax.trim();
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail == null ? null : adminEmail.trim();
    }

    public String getAdminHomepage() {
        return adminHomepage;
    }

    public void setAdminHomepage(String adminHomepage) {
        this.adminHomepage = adminHomepage == null ? null : adminHomepage.trim();
    }

    public String getAdminIm() {
        return adminIm;
    }

    public void setAdminIm(String adminIm) {
        this.adminIm = adminIm == null ? null : adminIm.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getSealImg() {
        return sealImg;
    }

    public void setSealImg(String sealImg) {
        this.sealImg = sealImg == null ? null : sealImg.trim();
    }

    public String getSignImg() {
        return signImg;
    }

    public void setSignImg(String signImg) {
        this.signImg = signImg == null ? null : signImg.trim();
    }

    public BigDecimal getSortNo() {
        return sortNo;
    }

    public void setSortNo(BigDecimal sortNo) {
        this.sortNo = sortNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String getTrainOrgLevel() {
		return trainOrgLevel;
	}

	public void setTrainOrgLevel(String trainOrgLevel) {
		this.trainOrgLevel = trainOrgLevel;
	}

	public String getTrainOrgFeature() {
		return trainOrgFeature;
	}

	public void setTrainOrgFeature(String trainOrgFeature) {
		this.trainOrgFeature = trainOrgFeature;
	}

	public String getRegionsName() {
		return regionsName;
	}

	public void setRegionsName(String regionsName) {
		this.regionsName = regionsName;
	}

	public String getIsVirtual() {
		return isVirtual;
	}

	public void setIsVirtual(String isVirtual) {
		this.isVirtual = isVirtual;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}
	
	public String getTechnologyType()
	{
		return technologyType;
	}
	
	public void setTechnologyType(String technologyType)
	{
		this.technologyType=technologyType;
	}
	
	public String getDeptLeader() {
		return deptLeader;
	}
	public void setDeptLeader(String deptLeader) {
		this.deptLeader =deptLeader;
	}

	public String getDeptLegalNo() {
		return deptLegalNo;
	}
	public void setDeptLegalNo(String deptLegalNo) {
		this.deptLegalNo =deptLegalNo;
	}

	public String getNationTaxNo() {
		return nationTaxNo;
	}
	public void setNationTaxNo(String nationTaxNo) {
		this.nationTaxNo =nationTaxNo;
	}
	
	public String getCityTaxNo() {
		return cityTaxNo;
	}
	public void setCityTaxNo(String cityTaxNo) {
		this.cityTaxNo =cityTaxNo;
	}

	public String getDeptRegisteredFund() {
		return deptRegisteredFund;
	}
	public void setDeptRegisteredFund(String deptRegisteredFund) {
		this.deptRegisteredFund =deptRegisteredFund;
	}
	
	public String getDeptRegisteredFundUnit() {
		return deptRegisteredFundUnit;
	}
	public void setDeptRegisteredFundUnit(String deptRegisteredFundUnit) {
		this.deptRegisteredFundUnit =deptRegisteredFundUnit;
	}
	
	public Date getDeptRegisteredDate() {
		return deptRegisteredDate;
	}
	public void setDeptRegisteredDate(Date deptRegisteredDate) {
		this.deptRegisteredDate =deptRegisteredDate;
	}
	
}