/*************************************************
*ChoiceInteration.java
*
*2013-12-9
*
*Copyright 2012-2013 HaoYi Co.Ltd. All Rights Reserved
*************************************************/
package com.hcis.survey.entity;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import com.hcis.ipanther.core.utils.JsonUtil;

/**
 *
 *
 *@author 梁华璜
 */
public class ChoiceInteraction implements Serializable{

	
	private static final long serialVersionUID = -2366471381063915199L;
	
	private int maxChoices;			//多选题, 最多可选择项数
	
	private List<Choice> choices;

	public int getMaxChoices() {
		return maxChoices;
	}

	public void setMaxChoices(int maxChoices) {
		this.maxChoices = maxChoices;
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}
	
	public String toString(){
		try {
			return JsonUtil.toJson(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
