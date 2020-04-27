package com.hcis.ipr.cms.site.entity;


import java.util.Date;


import com.hcis.ipanther.core.entity.BaseEntity;

public class CmsSite extends  BaseEntity {

	private static final long serialVersionUID = 8917331087694422368L;

	private String name;

    private String siteDoMain;

    private String sourcePath;

    private String copyright;

    private String recordCode;

    private String parentId;

    private String state;

    private String url;

    private String indexTemplet;
    
    private String logo;

    private int orderNum;

    private int clickNum;
    
    private String isValid;
    
    private String indexTempletName;
    
    private String parentName;
    
    private Date endTime;
    
	//省
	private String province;
	//市
	private String city;
	//区县
	private String counties;
	
	private String isMainSite;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSiteDoMain() {
        return siteDoMain;
    }

    public void setSiteDoMain(String siteDoMain) {
        this.siteDoMain = siteDoMain == null ? null : siteDoMain.trim();
    }

    public String getSourcePath() {
        return sourcePath;
    }

    public void setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath == null ? null : sourcePath.trim();
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright == null ? null : copyright.trim();
    }

    public String getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(String recordCode) {
        this.recordCode = recordCode == null ? null : recordCode.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getIndexTemplet() {
        return indexTemplet;
    }

    public void setIndexTemplet(String indexTemplet) {
        this.indexTemplet = indexTemplet == null ? null : indexTemplet.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public int getClickNum() {
        return clickNum;
    }

    public void setClickNum(int clickNum) {
        this.clickNum = clickNum;
    }

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getIndexTempletName() {
		return indexTempletName;
	}

	public void setIndexTempletName(String indexTempletName) {
		this.indexTempletName = indexTempletName;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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

	public String getIsMainSite() {
		return isMainSite;
	}

	public void setIsMainSite(String isMainSite) {
		this.isMainSite = isMainSite;
	}
}