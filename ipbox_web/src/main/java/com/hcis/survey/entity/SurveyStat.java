package com.hcis.survey.entity;

import java.util.Map;

import com.hcis.ipanther.core.entity.BaseEntity;
/**
 * 存放统计信息的实体
 * @author wuwentao
 * @date 2015年4月16日
 */
public class SurveyStat extends BaseEntity{
	
	private static final long serialVersionUID = 3130524360580012203L;
	
	//参与人数
	private int joinCount;
	//被选中的ID，多个用逗号分割
	private String choiceIds;
	/*
	 * 处理selectedChoiceIds得到的数据
	 * key为choiceId
	 * value为总共选择的次数
	 */
	private Map<String,Integer> stat;
	
	public int getJoinCount() {
		return joinCount;
	}
	public void setJoinCount(int joinCount) {
		this.joinCount = joinCount;
	}
	public Map<String, Integer> getStat() {
		return stat;
	}
	public void setStat(Map<String, Integer> stat) {
		this.stat = stat;
	}
	public String getChoiceIds() {
		return choiceIds;
	}
	public void setChoiceIds(String choiceIds) {
		this.choiceIds = choiceIds;
	}
}
