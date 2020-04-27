package com.hcis.ipr.warehouse.tradeHouse.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

public class TradeMarkRegisterInfo extends BaseEntity{
	
	private static final long serialVersionUID = 8427341087694462369L;
	
	
	private String registeNumber;
	private String classfiy;
	private String chineseName;
	private String foreignName;
	private String chineseAddress;
	private String foreignAddress;


	public String getRegisteNumber(){
		return this.registeNumber;
	}
	public void setRegisteNumber(String registeNumber){
		this.registeNumber = registeNumber;
	}
		
	public String getClassfiy(){
		return this.classfiy;
	}
	public void setClassfiy(String classfiy){
		this.classfiy = classfiy;
	}
	public String getChineseName(){
		return this.chineseName;
	}
	public void setChineseName(String chineseName){
		this.chineseName = chineseName;
	}
	
	public String getForeignName(){
		return this.foreignName ;
	}
	public void setForeignName(String foreignName){
		this.foreignName = foreignName;
	}
	
	public String getChineseAddress(){
		return this.chineseAddress;
	}
	public void setChineseAddress(String chineseAddress){
		this.chineseAddress = chineseAddress;
	}
	
	public String getForeignAddress(){
		return this.foreignAddress ;
	}
	public void setForeignAddress(String foreignAddress){
		this.foreignAddress = foreignAddress;
	}

}