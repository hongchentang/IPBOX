package com.hcis.items.entity;

import java.util.Date;
import java.util.List;

import com.hcis.ipanther.core.entity.BaseEntity;

public class ItemsManager extends BaseEntity{
	
	private static final long serialVersionUID = 7630449713626795113L;
	private String projectSourceCode;
	private String projectCode;
	private String projectName; 
	private Date   projectExecuteDate;
	private String projectChiefExport; 
	private String projectLeader; 
	private String projectExecuteDept; 
	private String projectDomin; 
	private Date   projectDeadline; 
	private String projectSelfAssessment; 
	private String projectSelfResult;
	private String projectAttacherFile; 
	private String projectRuleFile;
	private String projectType;
	private String projectExplain;
	private String projectNotes;
	private String projectOtherNotes; 
	private String projectApplyer; 
	private String firstChecker; 
	private Date   firstCheckDate;
	private String firstCheckNote; 
	private String    firstCheckStatus; 
	private String firstAgainChecker; 
	private Date   firstAgainCheckDate; 
	private String firstAgainNote; 
	private String    firstAgainCheckStatus;
	private String expertName;
	private String projectIsExport; 
	private String othersChecker; 
	private String othersCheckerName;
	private Date   othersCheckDate; 
	private String othersCheckFile; 
	private String projectIsOthers;
	private String secondChecker; 
	private Date   secondCheckDate; 
	private String secondCheckNote; 
	private String secondCheckStatus;
	private String secondAgainChecker;
	private Date   secondAgainCheckDate; 
	private String secondAgainCheckNote; 
	private String secondAgainCheckStatus;
	private String projectStatus; 
	private String projectPassNotes; 
	private String flowStatus;
	private List<ItemsExpert> itemsExperts;
	
	public String getProjectSourceCode() {
		return this.projectSourceCode;
	}
	public void setProjectSourceCode(String  projectSourceCode) {
		 this.projectSourceCode = projectSourceCode;
	}
	
	public String getProjectCode() {
		return this.projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode =projectCode;
	}
	
	public String getProjectName() {
		return this.projectName; 
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getProjectExecuteDate() {
		return this.projectExecuteDate;
	}
	public void setProjectExecuteDate(Date projectExecuteDate) {
		this.projectExecuteDate = projectExecuteDate;
	}
	
	public String getProjectChiefExport() {
		return this.projectChiefExport; 
	}
	public void  setProjectChiefExport(String projectChiefExport) {
		this.projectChiefExport = projectChiefExport;	
	}
	
	public String getProjectLeader() {
		return this.projectLeader; 
	}
	public void setProjectLeader(String projectLeader) {
		this.projectLeader = projectLeader;		
	}
	
	public String getProjectExecuteDept() {
		return this.projectExecuteDept; 
	}
	public void setProjectExecuteDept(String projectExecuteDept) {
		this.projectExecuteDept = projectExecuteDept;
	}
	
	public String getProjectDomin() {
		return this.projectDomin;
	}
	public void setProjectDomin(String projectDomin) {
		this.projectDomin = projectDomin;
	}
	
	public Date getProjectDeadline() {
		return this.projectDeadline; 
	}
	public void setProjectDeadline(Date projectDeadline) {
		this.projectDeadline = projectDeadline ; 
	}
	
	public String getProjectSelfAssessment() {
		return this.projectSelfAssessment; 
	}
	public void setProjectSelfAssessment(String projectSelfAssessment) {
		this.projectSelfAssessment = projectSelfAssessment ; 
	}
	 
	public String getProjectSelfResult() {
		return this.projectSelfResult; 
	}
	public void setProjectSelfResult(String projectSelfResult) {
		this.projectSelfResult = projectSelfResult; 
	}
	
	public String getProjectRuleFile() {
		return this.projectRuleFile;
	}
	public void setProjectRuleFile(String projectRuleFile) {
		this.projectRuleFile = projectRuleFile;
	}
	public String getProjectAttacherFile() {
		return this.projectAttacherFile; 
	}
	public void setProjectAttacherFile(String projectAttacherFile) {
		this.projectAttacherFile = projectAttacherFile; 
	}
	
	public String getProjectType() {
		return this.projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	public String getProjectExplain() {
		return this.projectExplain;
	}
	public void setProjectExplain(String projectExplain) {
		this.projectExplain = projectExplain;
	}
	
	public String getProjectNotes() {
		return this.projectNotes;
	}
	public void setProjectNotes(String projectNotes) {
		this.projectNotes = projectNotes;
	}
	public String getProjectOtherNotes() {
		return this.projectOtherNotes; 
	}
	public void setProjectOtherNotes(String projectOtherNotes) {
		this.projectOtherNotes = projectOtherNotes; 
	}
		
	public String getProjectApplyer() {
		return this.projectApplyer; 
	}
	public void setProjectApplyer(String projectApplyer) {
		 this.projectApplyer = projectApplyer; 
	}
	
	public String getFirstChecker() {
		return this.firstChecker; 
	}
	public void  setFirstChecker(String  firstChecker) {
		this.firstChecker = firstChecker; 
	}
	
	public Date getFirstCheckDate() {
		return this.firstCheckDate;
	}
	public void setFirstCheckDate(Date firstCheckDate) {
		this.firstCheckDate = firstCheckDate;
	}
	
	public String getFirstCheckNote() {
		return this.firstCheckNote; 
	}
	public void setFirstCheckNote(String firstCheckNote) {
		this.firstCheckNote = firstCheckNote; 
	}
	
	public String getFirstCheckStatus() {
		return this.firstCheckStatus; 
	}
	public void setFirstCheckStatus(String  firstCheckStatus) {
		this.firstCheckStatus = firstCheckStatus; 
	}
	
	public String getFirstAgainChecker() {
		return this.firstAgainChecker; 
	}
	public void setFirstAgainChecker(String firstAgainChecker) {
		this.firstAgainChecker = firstAgainChecker; 
	}
	
	public Date getFirstAgainCheckDate() {
		return firstAgainCheckDate;
	}
	public void setFirstAgainCheckDate(Date firstAgainCheckDate) {
		this.firstAgainCheckDate = firstAgainCheckDate;
	}
	
	public String getFirstAgainNote() {
		return firstAgainNote;
	}
	public void setFirstAgainNote(String firstAgainNote) {
		this.firstAgainNote = firstAgainNote;
	}
	
	public String getFirstAgainCheckStatus() {
		return firstAgainCheckStatus;
	}
	public void setFirstAgainCheckStatus(String firstAgainCheckStatus) {
		this.firstAgainCheckStatus = firstAgainCheckStatus;
	}
	
	
	public String getExpertName() {
		return this.expertName;
	}
	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}
    public String getProjectIsExport() {
		return projectIsExport;
	}
	public void setProjectIsExport(String projectIsExport) {
		this.projectIsExport = projectIsExport;
	}

	public String getOthersChecker() {
		return othersChecker;
	}
	public void setOthersChecker(String othersChecker) {
		this.othersChecker = othersChecker;
	}

	public String getOthersCheckerName() {
		return othersCheckerName;
	}
	public void setOthersCheckerName(String othersCheckerName) {
		this.othersCheckerName = othersCheckerName;
	}
	
	
	public Date getOthersCheckDate() {
		return othersCheckDate;
	}
	public void setOthersCheckDate(Date othersCheckDate) {
		this.othersCheckDate = othersCheckDate;
	}
	
	public String getOthersCheckFile() {
		return othersCheckFile;
	}
	public void setOthersCheckFile(String othersCheckFile) {
		this.othersCheckFile = othersCheckFile;
	}

	public String getPorjectIsOthers() {
		return this.projectIsOthers;
	}
	public void setProjectIsOthers(String projectIsOthers) {
		this.projectIsOthers = projectIsOthers;
	}
	public String getSecondChecker() {
		return secondChecker;
	}
	public void setSecondChecker(String secondChecker) {
		this.secondChecker = secondChecker;
	}
 
	public Date getSecondCheckDate() {
		return secondCheckDate;
	}
	public void setSecondCheckDate(Date secondCheckDate) {
		this.secondCheckDate = secondCheckDate;
	}

	public String getSecondCheckNote() {
		return secondCheckNote;
	}
	public void setSecondCheckNote(String secondCheckNote) {
		this.secondCheckNote = secondCheckNote;
	}
	
	public String getSecondCheckStatus() {
		return secondCheckStatus;
	}
	public void setSecondCheckStatus(String secondCheckStatus) {
		this.secondCheckStatus = secondCheckStatus;
	}
	
	public String getSecondAgainChecker() {
		return secondAgainChecker;
	}
	public void setSecondAgainChecker(String secondAgainChecker) {
		this.secondAgainChecker = secondAgainChecker;
	}
	
	public Date getSecondAgainCheckDate() {
		return secondAgainCheckDate;
	}
	public void setSecondAgainCheckDate(Date secondAgainCheckDate) {
		this.secondAgainCheckDate = secondAgainCheckDate;
	}


	public String getSecondAgainCheckNote() {
		return this.secondAgainCheckNote;	
	}
	public void setSecondAgainCheckNote(String secondAgainCheckNote) {
		this.secondAgainCheckNote = secondAgainCheckNote;
	}
	
	public String getSecondAgainCheckStatus() {
		return this.secondAgainCheckStatus;	
	}
	public void setSecondAgainCheckStatus(String secondAgainCheckStatus) {
		this.secondAgainCheckStatus = secondAgainCheckStatus;
	}
	
	public String getProjectStatus() {
		return this.projectStatus;	
	}
	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	
	public String getProjectPassNotes() {
		return this.projectPassNotes;	
	}
	public void setProjectPassNotes(String projectPassNotes) {
		this.projectPassNotes = projectPassNotes;
	}
	
	public String getFlowStatus() {
		return this.flowStatus;
	}
	public void setFlowStatus(String flowStatus) {
		this.flowStatus = flowStatus;
	}
	
	public List<ItemsExpert> getItemsExperts() {
		return itemsExperts;
	}
	public void setItemsExperts(List<ItemsExpert> itemsExperts) {
		this.itemsExperts = itemsExperts;
		
	}
	
}