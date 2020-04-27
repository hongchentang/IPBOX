package com.hcis.ipr.cms.config.entity;

import com.hcis.ipanther.core.entity.BaseEntity;


public class CmsConfig extends BaseEntity{
 
	private static final long serialVersionUID = 7630449713626795103L;

	private String code;

    private String name;

    private String configValue;

    private Long orderNum;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue == null ? null : configValue.trim();
    }

    public Long getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Long orderNum) {
        this.orderNum = orderNum;
    }
}