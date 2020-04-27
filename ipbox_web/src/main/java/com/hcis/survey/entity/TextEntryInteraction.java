/*************************************************
*TextEntryInteraction.java
*
*2013-12-9
*
*Copyright 2012-2013 HaoYi Co.Ltd. All Rights Reserved
*************************************************/
package com.hcis.survey.entity;

import java.io.IOException;
import java.io.Serializable;

import com.hcis.ipanther.core.utils.JsonUtil;

/**
 *
 *
 *@author 梁华璜
 */
public class TextEntryInteraction implements Serializable {

	private static final long serialVersionUID = 1595299590538525076L;

	private int minWords;
	
	private int maxWords;

	public int getMinWords() {
		return minWords;
	}

	public void setMinWords(int minWords) {
		this.minWords = minWords;
	}

	public int getMaxWords() {
		return maxWords;
	}

	public void setMaxWords(int maxWords) {
		this.maxWords = maxWords;
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
