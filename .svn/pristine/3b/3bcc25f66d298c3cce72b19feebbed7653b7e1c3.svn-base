package com.hcis.survey.entity;

import java.util.Map;

import com.google.common.collect.Maps;
import com.hcis.ipanther.core.entity.BaseEntity;

public class SurveyResult extends BaseEntity{

	private static final long serialVersionUID = 36071640148461653L;
	
	private Survey survey;
	
	private SurveyQuestion surveyQuestion;
	
	private SurveyUser surveyUser;
	
	private ChoiceInteraction choiceInteraction;
	
	private TextEntryInteraction textEntryInteraction;
	
	private Map<String, ChoiceInteraction> choiceInteractionMap = Maps.newHashMap();
	
	private Map<String, TextEntryInteraction> textEntryInteractionMap = Maps.newHashMap();

	public SurveyUser getSurveyUser() {
		return surveyUser;
	}

	public void setSurveyUser(SurveyUser surveyUser) {
		this.surveyUser = surveyUser;
	}

	public Map<String, ChoiceInteraction> getChoiceInteractionMap() {
		return choiceInteractionMap;
	}

	public void setChoiceInteractionMap(Map<String, ChoiceInteraction> choiceInteractionMap) {
		this.choiceInteractionMap = choiceInteractionMap;
	}

	public Map<String, TextEntryInteraction> getTextEntryInteractionMap() {
		return textEntryInteractionMap;
	}

	public void setTextEntryInteractionMap(Map<String, TextEntryInteraction> textEntryInteractionMap) {
		this.textEntryInteractionMap = textEntryInteractionMap;
	}

	public Survey getSurvey() {
		return survey;
	}

	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

	public ChoiceInteraction getChoiceInteraction() {
		return choiceInteraction;
	}

	public void setChoiceInteraction(ChoiceInteraction choiceInteraction) {
		this.choiceInteraction = choiceInteraction;
	}

	public TextEntryInteraction getTextEntryInteraction() {
		return textEntryInteraction;
	}

	public void setTextEntryInteraction(TextEntryInteraction textEntryInteraction) {
		this.textEntryInteraction = textEntryInteraction;
	}

	public SurveyQuestion getSurveyQuestion() {
		return surveyQuestion;
	}

	public void setSurveyQuestion(SurveyQuestion surveyQuestion) {
		this.surveyQuestion = surveyQuestion;
	}
	
}
