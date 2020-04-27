package com.hcis.ipanther.common.attachment.entity;

import java.io.Serializable;

public class ReceiveParam implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3990511262942733580L;

	private String fileDir;
	
	private String billId;
	
	private String attachmentId;
	
	private String callbackUrl;
	
	private String fileTypes;
	
	private long fileMaxSize;
	
	private String realName;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public long getFileMaxSize() {
		return fileMaxSize;
	}

	public void setFileMaxSize(long fileMaxSize) {
		this.fileMaxSize = fileMaxSize;
	}

	public String getFileDir() {
		return fileDir;
	}

	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public String getAttachmentId() {
		return attachmentId;
	}

	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getFileTypes() {
		return fileTypes;
	}

	public void setFileTypes(String fileTypes) {
		this.fileTypes = fileTypes;
	}

}	
