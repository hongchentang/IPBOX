/*************************************************
*Survey.java
*
*2013-12-9
*
*Copyright 2012-2013 HaoYi Co.Ltd. All Rights Reserved
*************************************************/
package com.hcis.survey.entity;

import java.util.Date;
import java.util.List;

import com.hcis.ipanther.core.entity.BaseEntity;

public class Survey extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String title;
	
	private String description;
	//是否发布 对应字典项：SURVEY_STATE 泛型：SurveyState
	private String state;
	//作者
	private String author;
	
	private List<String> relations;

	private List<SurveyQuestion> questions;
	
	//发布时间
	private Date publishTime;
	
	/*
	 * 非数据库映射字段
	 */
	//用户完成状态
	private String completionStatus;
	//结束时间
	private Date endTime;
	
	public Survey(){
		
	}
	
	public Survey(String id){
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public List<String> getRelations() {
		return relations;
	}

	public void setRelations(List<String> relations) {
		this.relations = relations;
	}

	public List<SurveyQuestion> getQuestions() {
		return questions;
	}

	public void setQuestions(List<SurveyQuestion> questions) {
		this.questions = questions;
	}
	
	public enum SurveyState{
		UNPUBLISHED("1"),//未发布
		PUBLISHED("2");//已发布
		private String state;
		private SurveyState(String state){
			this.state = state;
		}
		
		public String toString(){
			return state;
		}
	}

	public String getCompletionStatus() {
		return completionStatus;
	}

	public void setCompletionStatus(String completionStatus) {
		this.completionStatus = completionStatus;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
