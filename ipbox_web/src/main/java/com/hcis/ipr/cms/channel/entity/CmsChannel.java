package com.hcis.ipr.cms.channel.entity;

import com.hcis.ipanther.core.entity.BaseEntity;


public class CmsChannel extends BaseEntity{

	private static final long serialVersionUID = 2865921844737439678L;

	private String name;

    private String templetId;

    private String contentTempletId;

    private String img;

    private String description;

    private String parentId;

    private String siteId;

    private String state;

    private Long orderNum;

    private Long clickNum;

    private String navigation;

    private String pageMark;

    private String htmlChannelId;

    private String htmlChannelOldId;

    private String htmlParChannelId;

    private String htmlSiteId;

    private Long indexNum;

    private Long maxPage;
    
    private String siteName;
    
    private String url;
    
    private String sitePath;
    
    private String folder;//静态页面目录
    
    private String hasChildren;//是否有子栏目
    
    private String pageUrl;
 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTempletId() {
        return templetId;
    }

    public void setTempletId(String templetId) {
        this.templetId = templetId == null ? null : templetId.trim();
    }

    public String getContentTempletId() {
        return contentTempletId;
    }

    public void setContentTempletId(String contentTempletId) {
        this.contentTempletId = contentTempletId == null ? null : contentTempletId.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId == null ? null : siteId.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }

    public Long getClickNum() {
        return clickNum;
    }

    public void setClickNum(Long clickNum) {
        this.clickNum = clickNum;
    }

    public String getNavigation() {
        return navigation;
    }

    public void setNavigation(String navigation) {
        this.navigation = navigation == null ? null : navigation.trim();
    }

    public String getPageMark() {
        return pageMark;
    }

    public void setPageMark(String pageMark) {
        this.pageMark = pageMark == null ? null : pageMark.trim();
    }

    public String getHtmlChannelId() {
        return htmlChannelId;
    }

    public void setHtmlChannelId(String htmlChannelId) {
        this.htmlChannelId = htmlChannelId == null ? null : htmlChannelId.trim();
    }

    public String getHtmlChannelOldId() {
        return htmlChannelOldId;
    }

    public void setHtmlChannelOldId(String htmlChannelOldId) {
        this.htmlChannelOldId = htmlChannelOldId == null ? null : htmlChannelOldId.trim();
    }

    public String getHtmlParChannelId() {
        return htmlParChannelId;
    }

    public void setHtmlParChannelId(String htmlParChannelId) {
        this.htmlParChannelId = htmlParChannelId == null ? null : htmlParChannelId.trim();
    }

    public String getHtmlSiteId() {
        return htmlSiteId;
    }

    public void setHtmlSiteId(String htmlSiteId) {
        this.htmlSiteId = htmlSiteId == null ? null : htmlSiteId.trim();
    }

    public Long getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(Long indexNum) {
        this.indexNum = indexNum;
    }

    public Long getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(Long maxPage) {
        this.maxPage = maxPage;
    }

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSitePath() {
		return sitePath;
	}

	public void setSitePath(String sitePath) {
		this.sitePath = sitePath;
	}

	public String getFolder() {
		if (pageMark!=null && pageMark.trim().length()>0) {
			folder=pageMark;
		}else if (indexNum>0) {
			folder=String.valueOf(indexNum);
		}else {
			folder=id;
		}
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

	public String getHasChildren() {
		return hasChildren;
	}

	public void setHasChildren(String hasChildren) {
		this.hasChildren = hasChildren;
	}

	public String getPageUrl() {
		if (url!=null && url.trim().length()>0) {
				pageUrl=url;
		}else {
			pageUrl=(sitePath!=null?sitePath:"")+getFolder()+"/index.html";
		}
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
 
}