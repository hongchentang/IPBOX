package com.hcis.ipr.wechat.menu.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

public class Menu extends BaseEntity {
	
	private static final long serialVersionUID = -6062576613614616770L;
	/*菜单menuKey，当MenuType值为CLICK时用于区别菜单*/
	private String menuKey;
	/*父节点ID*/	
    private String parentId;
    /* 菜单上显示的文字*/
    private String name;
    /*  菜单类别*/
    private String type;
   /* 菜单跳转的URL，当MenuType值为VIEW时用*/
    private String url;
   /* 永久素材ID*/
    private String mediaId;
    
    //排序号
    private Integer sortNo;
	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getMenuKey() {
        return menuKey;
    }

    public void setMenuKey(String menuKey) {
        this.menuKey = menuKey == null ? null : menuKey.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId == null ? null : mediaId.trim();
    }
}