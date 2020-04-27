package com.hcis.ipanther.common.log.entity;

import java.util.Date;

import com.hcis.ipanther.core.entity.BaseEntity;

public class CommonLog  extends BaseEntity{

	private static final long serialVersionUID = 7979557774862088125L;

	private String siteId;

    private String channelId;

    private String infoId;

    private String url;

    private Date addTime;

    private String ip;
    
    private String content; 

    private String role;
    
    private String action;
    
    private String  endMachine;
    
    public String getSiteId() {
        return siteId;
    }

    public void setSiteId(String siteId) {
        this.siteId = siteId == null ? null : siteId.trim();
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId == null ? null : infoId.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		 this.content = content == null ? null : content.trim();
	}
	
	public String getRole(){
		return role;
	}
	public void setRole(String role){
		this.role = role;
	}

	public String getAction(){
		return action;
	}
	public void setAction(String action){
		this.action = action;
	}

	public String getEndMachine(){
		return endMachine;
	}
    public void setEndMachine(String endMachine){
    	this.endMachine = endMachine;
    }
}