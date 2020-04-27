/*************************************************
*Question.java
*
*2013-12-9
*
*Copyright 2012-2013 HaoYi Co.Ltd. All Rights Reserved
*************************************************/
package com.hcis.survey.entity;

import com.hcis.ipanther.core.entity.BaseEntity;

public class SurveyQuestion extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String id;
	
	private String title;
	
	private String quesBody;
	
	private String  interaction;
	
	private String quesType;
	
	private int sequenceIndex;
	
	private float score;
	
	private String relationId;

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

	public String getQuesBody() {
		return quesBody;
	}

	public void setQuesBody(String quesBody) {
		this.quesBody = quesBody;
	}

	public String getInteraction() {
		return interaction;
	}

	public void setInteraction(String interaction) {
		this.interaction = interaction;
	}

	public String getQuesType() {
		return quesType;
	}

	public void setQuesType(String quesType) {
		this.quesType = quesType;
	}

	public int getSequenceIndex() {
		return sequenceIndex;
	}

	public void setSequenceIndex(int sequenceIndex) {
		this.sequenceIndex = sequenceIndex;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getRelationId() {
		return relationId;
	}

	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
	
	public enum QuesType{
		SINGLECHOICE("singleChoice"),
		MULTIPLECHOICE("multipleChoice"),
		TRUEORFALSE("trueOrFalse"),
		TEXTENTRY("textEntry");
		private String quesType;
		private QuesType(String quesType){
			this.quesType = quesType;
		}
		
		public String toString(){
			return quesType;
		}
	}
}
