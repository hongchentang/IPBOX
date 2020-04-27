package com.hcis.ipr.train.problem.entity;

import java.math.BigDecimal;
import java.util.Date;

import com.hcis.ipanther.core.entity.BaseEntity;

/**
 * 常见问题实体
 * @author TikDeng
 * @date 2017年11月16日
 */
public class Problem extends BaseEntity{
	
	private static final long serialVersionUID = 1323352671404067460L;

	private String problemCode;
	
	private String problemName;
	
	private String problemContent;
	
	public String getProblemCode() {
		return problemCode;
	}

	public void setProblemCode(String problemCode) {
		this.problemCode = problemCode;
	}

	public String getProblemName() {
		return problemName;
	}

	public void setProblemName(String problemName) {
		this.problemName = problemName;
	}

	public String getProblemContent() {
		return problemContent;
	}

	public void setProblemContent(String problemContent) {
		this.problemContent = problemContent;
	}
}