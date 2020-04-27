package com.hcis.ipr.intellectual.trademark.entity;

import java.util.Calendar;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.hcis.ipanther.core.entity.BaseEntity;

public class Trademark extends  BaseEntity{

	private static final long serialVersionUID = -7073982047513142691L;

	private String registeredNumber;	//申请注册号
	
	private String registeredName;		//商标名称
	
	private String registeredImage;     //商标图
	 
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date applyDate;            //申请日期
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date registerDate;         //注册日期
	
	private String classify;           //国际分类

	private String classifyLike;       //类似群
	
	private String trademarkState;     //商标状态
	
	private String firstPublicNumber;  //初审公告号

	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date firstPublicDate;     //初审公告日期

	private String registerPublicNumber;//注册公开号
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date registerPublicDate;	//注册公告日期

	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date beginDate;				//专用期开始日期

	private String isOwner;				//是否为共有
		
	private String trademarkType;		//商标类型
	
	private String registerType;		//注册类型
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date validDate;				//有效日期

	/**
	 * 无数据库自动
	 * 有效结束日期
	 */
	private Date validDateEnd;

	private String registerCity;        //注册城市
	
	private String registerRole;        //注册人

	private String registerAddress;     //注册人地址
	
	private String agencyCode;	 		//代理机构编码
	
	private String registorInfo;        //注册人信息

	private String trademarkFile;		//商标listTMS附件

	public Date getValidDateEnd() {
		Date date = this.validDate;
		if(date != null){
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int year = calendar.get(Calendar.YEAR) + 10;
			calendar.set(Calendar.YEAR, year);

			return calendar.getTime();
		}
		return null;
	}

	public void setFirstPublicDate(Date firstPublicDate) {
		this.firstPublicDate = firstPublicDate;
	}

	public void setValidDateEnd(Date validDateEnd) {
		this.validDateEnd = validDateEnd;
	}

	public String getRegisteredNumber(){
		return this.registeredNumber;
	}
	public void setRegisteredNumber(String registeredNumber){
		this.registeredNumber = registeredNumber;
	}
	
	public String getRegisteredName(){
		return this.registeredName;
	}
	public void setRegisteredName(String registeredName){
		this.registeredName = registeredName;
	}
	
	public String getRegisteredImage(){
		return this.registeredImage;
	}
	public void setRegisteredImage(String registeredImage){
		this.registeredImage = registeredImage;
	}
	
	public Date getApplyDate(){
		return this.applyDate;
	}
	public void setApplyDate(Date applyDate){
		this.applyDate = applyDate;
	}
	
	public Date getRegisterDate(){
		return this.registerDate;
	}
	public void setRegisterDate(Date registerDate){
		this.registerDate = registerDate;
	}
	
	public String getClassify(){
		return classify;
	}	
	public void setClassify(String classify){
		this.classify = classify;
	}
	
	public String getClassifyLike(){
		return classifyLike;
	}	
	public void setClassifyLike(String classifyLike){
		this.classifyLike = classifyLike;
	}
	
	public String  getTrademarkState(){
		return this.trademarkState;
	}
	public void  setTrademarkState(String trademarkState){
		this.trademarkState = trademarkState;
	}
	
	public String  getFirstPublicNumber(){
		return this.firstPublicNumber;
	}
	public void  setFirstPublicNumber(String firstPublicNumber){
		this.firstPublicNumber = firstPublicNumber;
	}
	
	public Date  getFirstPublicDate(){
		return this.firstPublicDate;
	}
	public void  setFirstPublicNumber(Date firstPublicDate){
		this.firstPublicDate = firstPublicDate;
	}

	public String  getRegisterPublicNumber(){
		return this.registerPublicNumber;
	}
	public void  setRegisterPublicNumber(String registerPublicNumber){
		this.registerPublicNumber = registerPublicNumber;
	}
	
	public Date  getRegisterPublicDate(){
		return this.registerPublicDate;
	}
	public void  setRegisterPublicDate(Date registerPublicDate){
		this.registerPublicDate = registerPublicDate;
	}
	
	public Date  getBeginDate(){
		return this.beginDate;
	}
	public void  setBeginDate(Date beginDate){
		this.beginDate = beginDate;
	}
	
	public String  getIsOwner(){
		return this.isOwner;
	}
	public void  setIsOwner(String isOwner){
		this.isOwner = isOwner;
	}
	
	public String  getTrademarkType(){
		return this.trademarkType;
	}
	public void  setTrademarkType(String trademarkType){
		this.trademarkType = trademarkType;
	}
	
	public String  getRegisterType(){
		return this.registerType;
	}
	public void  setRegisterType(String registerType){
		this.registerType = registerType;
	}
	
	public Date  getValidDate(){
		return this.validDate;
	}
	public void  setValidDate(Date validDate){
		this.validDate = validDate;
	}
	
	
	public String  getRegisterCity(){
		return this.registerCity;
	}
	public void  setRegisterCity(String registerCity){
		this.registerCity = registerCity;
	}
	
	public String  getRegisterRole(){
		return this.registerRole;
	}
	public void  setRegisterRole(String registerRole){
		this.registerRole = registerRole;
	}
	
	public String  getRegisterAddress(){
		return this.registerAddress;
	}
	public void  setRegisterAddress(String registerAddress){
		this.registerAddress = registerAddress;
	}
	
	public String  getAgencyCode(){
		return this.agencyCode;
	}
	public void  setAgencyCode(String agencyCode){
		this.agencyCode = agencyCode;
	}
	
	public String  getRegistorInfo(){
		return this.registorInfo;
	}
	public void  setRegistorInfo(String registorInfo){
		this.registorInfo = registorInfo;
	}
	
	public String  getTrademarkFile(){
		return this.trademarkFile;
	}
	public void  setTrademarkFile(String trademarkFile){
		this.trademarkFile = trademarkFile;
	}
}