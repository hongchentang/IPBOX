package com.hcis.ipr.intellectual.patent.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hcis.ipanther.common.dict.utils.DictionaryUtils;
import com.hcis.ipanther.common.dict.vo.DictEntry;
import com.hcis.ipanther.core.entity.BaseEntity;
import com.hcis.ipanther.core.utils.StringUtils;

/**
 * @author zhw
 */
public class Patent extends BaseEntity {

	private static final long serialVersionUID = -6880895458422477352L;
	private String companyId;

	private String completeUnit;

	private String patentName;

	private String patentEnglishName;

	private String appNumber;

	private Date appDate;

	private String applyer;

	private String applyerNumber;

	private String applyerAddress;

	private String firstInventor;

	private String inventor;

	private String patentType;

	private String legalStatus;

	private String publicationNumber;

	private Date publicationDate;

	private String authorCountry;

	private String isAuthorize;

	private Date authorizeDate;

	private String announcementNumber;

	private Date announcementDate;

	private String certificateNumber;

	private Date expirationDate;

	private Date sseDate;

	private Date openBookDate;

	private Date priorityDate;

	private String priorityNumber;

	private String pctApplyNumber;

	private String pctAnnouncementNumber;

	private Date pctAnnouncementDate;

	private Date entrustDate;

	private Byte entrustStage;

	private String entrustDept;

	private String agency;

	private String entrustHandler;

	private String assistantHandler;

	private String customerName;

	private String customerCode;

	private String customerContacts;

	private String customerSource;

	private String customerAddress;
	private String agencyRemark;

	private String mongoPatentId;

	private Map<String, List<PatentAttachment>> attachments;

	private String depId;
	private String isCost;

	private String appDateStr;
	private String legalStatusStr;

	public String getAppDateStr() {
		if(this.appDate != null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.format(this.getAppDate());
		}
		return appDateStr;
	}

	public void setAppDateStr(String appDateStr) {
		this.appDateStr = appDateStr;
	}

	public String getLegalStatusStr() {
		if(StringUtils.isNotBlank(legalStatus)){
			DictEntry dictEntry = DictionaryUtils.getEntry("IPBOX_LEGAL_STATUS", legalStatus);
			if(dictEntry != null){
				return dictEntry.getDictName();
			}
		}else if(StringUtils.isNotBlank(this.legalStatusStr)){
			return this.legalStatusStr;
		}
		return "未填";
	}

	public void setLegalStatusStr(String legalStatusStr) {
		this.legalStatusStr = legalStatusStr;
	}

	public String getMongoPatentId() {
		return mongoPatentId;
	}

	public void setMongoPatentId(String mongoPatentId) {
		this.mongoPatentId = mongoPatentId;
	}

	public String getIsCost() {
		return isCost;
	}

	public void setIsCost(String isCost) {
		this.isCost = isCost;
	}

	private String deleteAttachmentIds;

	public Map<String, List<PatentAttachment>> getAttachments() {
		return attachments;
	}

	public void setAttachments(Map<String, List<PatentAttachment>> attachments) {
		this.attachments = attachments;
	}

	public String getDeleteAttachmentIds() {
		return deleteAttachmentIds;
	}

	public void setDeleteAttachmentIds(String deleteAttachmentIds) {
		this.deleteAttachmentIds = deleteAttachmentIds;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId == null ? null : companyId.trim();
	}

	public String getCompleteUnit() {
		return completeUnit;
	}

	public void setCompleteUnit(String completeUnit) {
		this.completeUnit = completeUnit == null ? null : completeUnit.trim();
	}

	public String getPatentName() {
		return patentName;
	}

	public void setPatentName(String patentName) {
		this.patentName = patentName == null ? null : patentName.trim();
	}

	public String getPatentEnglishName() {
		return patentEnglishName;
	}

	public void setPatentEnglishName(String patentEnglishName) {
		this.patentEnglishName = patentEnglishName;
	}

	public String getAppNumber() {
		return appNumber;
	}

	public void setAppNumber(String appNumber) {
		this.appNumber = appNumber == null ? null : appNumber.trim();
	}

	@JsonFormat(pattern="yyyy-MM-dd")
	public Date getAppDate() {
		return appDate;
	}

	public void setAppDate(Date appDate) {
		this.appDate = appDate;
	}

	public String getApplyer() {
		return applyer;
	}

	public void setApplyer(String applyer) {
		this.applyer = applyer == null ? null : applyer.trim();
	}

	public String getApplyerNumber() {
		return applyerNumber;
	}

	public void setApplyerNumber(String applyerNumber) {
		this.applyerNumber = applyerNumber == null ? null : applyerNumber.trim();
	}

	public String getApplyerAddress() {
		return applyerAddress;
	}

	public void setApplyerAddress(String applyerAddress) {
		this.applyerAddress = applyerAddress == null ? null : applyerAddress.trim();
	}

	public String getFirstInventor() {
		return firstInventor;
	}

	public void setFirstInventor(String firstInventor) {
		this.firstInventor = firstInventor == null ? null : firstInventor.trim();
	}

	public String getInventor() {
		return inventor;
	}

	public void setInventor(String inventor) {
		this.inventor = inventor == null ? null : inventor.trim();
	}

	public String getPatentType() {
		return patentType;
	}

	public void setPatentType(String patentType) {
		this.patentType = patentType == null ? null : patentType.trim();
	}

	public String getLegalStatus() {
		return legalStatus;
	}

	public void setLegalStatus(String legalStatus) {
		this.legalStatus = legalStatus == null ? null : legalStatus.trim();
	}

	public String getPublicationNumber() {
		return publicationNumber;
	}

	public void setPublicationNumber(String publicationNumber) {
		this.publicationNumber = publicationNumber == null ? null : publicationNumber.trim();
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getAuthorCountry() {
		return authorCountry;
	}

	public void setAuthorCountry(String authorCountry) {
		this.authorCountry = authorCountry == null ? null : authorCountry.trim();
	}

	public String getIsAuthorize() {
		return isAuthorize;
	}

	public void setIsAuthorize(String isAuthorize) {
		this.isAuthorize = isAuthorize == null ? null : isAuthorize.trim();
	}

	@JsonFormat(pattern="yyyy-MM-dd")
	public Date getAuthorizeDate() {
		return authorizeDate;
	}

	public void setAuthorizeDate(Date authorizeDate) {
		this.authorizeDate = authorizeDate;
	}

	public String getAnnouncementNumber() {
		return announcementNumber;
	}

	public void setAnnouncementNumber(String announcementNumber) {
		this.announcementNumber = announcementNumber == null ? null : announcementNumber.trim();
	}

	public Date getAnnouncementDate() {
		return announcementDate;
	}

	public void setAnnouncementDate(Date announcementDate) {
		this.announcementDate = announcementDate;
	}

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber == null ? null : certificateNumber.trim();
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Date getSseDate() {
		return sseDate;
	}

	public void setSseDate(Date sseDate) {
		this.sseDate = sseDate;
	}

	public Date getOpenBookDate() {
		return openBookDate;
	}

	public void setOpenBookDate(Date openBookDate) {
		this.openBookDate = openBookDate;
	}

	public Date getPriorityDate() {
		return priorityDate;
	}

	public void setPriorityDate(Date priorityDate) {
		this.priorityDate = priorityDate;
	}

	public String getPriorityNumber() {
		return priorityNumber;
	}

	public void setPriorityNumber(String priorityNumber) {
		this.priorityNumber = priorityNumber;
	}

	public String getPctApplyNumber() {
		return pctApplyNumber;
	}

	public void setPctApplyNumber(String pctApplyNumber) {
		this.pctApplyNumber = pctApplyNumber == null ? null : pctApplyNumber.trim();
	}

	public String getPctAnnouncementNumber() {
		return pctAnnouncementNumber;
	}

	public void setPctAnnouncementNumber(String pctAnnouncementNumber) {
		this.pctAnnouncementNumber = pctAnnouncementNumber == null ? null : pctAnnouncementNumber.trim();
	}

	public Date getPctAnnouncementDate() {
		return pctAnnouncementDate;
	}

	public void setPctAnnouncementDate(Date pctAnnouncementDate) {
		this.pctAnnouncementDate = pctAnnouncementDate;
	}

	public Date getEntrustDate() {
		return entrustDate;
	}

	public void setEntrustDate(Date entrustDate) {
		this.entrustDate = entrustDate;
	}

	public Byte getEntrustStage() {
		return entrustStage;
	}

	public void setEntrustStage(Byte entrustStage) {
		this.entrustStage = entrustStage;
	}

	public String getEntrustDept() {
		return entrustDept;
	}

	public void setEntrustDept(String entrustDept) {
		this.entrustDept = entrustDept == null ? null : entrustDept.trim();
	}

	public String getAgency() {
		return agency;
	}

	public void setAgency(String agency) {
		this.agency = agency == null ? null : agency.trim();
	}

	public String getEntrustHandler() {
		return entrustHandler;
	}

	public void setEntrustHandler(String entrustHandler) {
		this.entrustHandler = entrustHandler == null ? null : entrustHandler.trim();
	}

	public String getAssistantHandler() {
		return assistantHandler;
	}

	public void setAssistantHandler(String assistantHandler) {
		this.assistantHandler = assistantHandler == null ? null : assistantHandler.trim();
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName == null ? null : customerName.trim();
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode == null ? null : customerCode.trim();
	}

	public String getCustomerContacts() {
		return customerContacts;
	}

	public void setCustomerContacts(String customerContacts) {
		this.customerContacts = customerContacts == null ? null : customerContacts.trim();
	}

	public String getCustomerSource() {
		return customerSource;
	}

	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource == null ? null : customerSource.trim();
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress == null ? null : customerAddress.trim();
	}

	public String getAgencyRemark() {
		return agencyRemark;
	}

	public void setAgencyRemark(String agencyRemark) {
		this.agencyRemark = agencyRemark;
	}

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}
}