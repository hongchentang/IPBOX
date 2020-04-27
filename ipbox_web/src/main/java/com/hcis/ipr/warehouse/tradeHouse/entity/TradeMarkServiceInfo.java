package com.hcis.ipr.warehouse.tradeHouse.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

public class TradeMarkServiceInfo extends BaseEntity{
	
	private static final long serialVersionUID = 8227341087694462369L;
	
	
	private String registeNumber;
	private String productName;
	private String classfiy;
	private String classfiyLike;
	private String order;
	private String status;
	
	public String getRegisteNumber(){
		return this.registeNumber;
	}
	public void setRegisteNumber(String registeNumber){
		this.registeNumber = registeNumber;
	}
	
	public String getProductName(){
		return this.productName;
	}
	public void setProductName(String productName){
		this.productName = productName;
	}
	
	public String getClassfiy(){
		return this.classfiy;
	}
	public void setClassfiy(String classfiy){
		this.classfiy = classfiy;
	}
	
	public String getClassfiyLike(){
		return this.classfiyLike;
	}
	public void setClassfiyLike(String classfiyLike){
		this.classfiyLike = classfiyLike;
	}
	
	public String getOrder(){
		return this.order;
	}
	public void setOrder(String order){
		this.order = order;
	}
	
	public String getStatus(){
		return this.status;
	}
	public void setStatus(String status){
		this.status = status;
	}

}