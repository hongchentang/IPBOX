/*************************************************
Copyright (C), 2012
Author:梁华璜 
Version: 
Date: 2012-6-14
Description: // 用于详细说明此程序文件完成的主要功能，与其他模块
// 或函数的接口，输出值、取值范围、含义及参数间的控
// 制、顺序、独立或依赖等关系
Function List: // 主要函数列表，每条记录应包括函数名及功能简要说明
1. ....
History: // 修改历史记录列表，每条修改记录应包括修改日期、修改
// 者及修改内容简述
1. Date:
Author:
Modification:
2. ...
 *************************************************/
package com.hcis.ipanther.common.excel.model;

public class ModelProperty {
	private String name;//model属性名
	private int column;//excel列
	private String excelTitleName;
	private String dataType;
	private int maxLength;
	private String required;//是否必须 true false
	private String fixity;
	private String codeTableName;
	private String defaultValue;//默认值
	

	public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getExcelTitleName() {
		return excelTitleName;
	}

	public void setExcelTitleName(String excelTitleName) {
		this.excelTitleName = excelTitleName;
	}

	public int getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeTableName() {
		return codeTableName;
	}

	public void setCodeTableName(String codeTableName) {
		this.codeTableName = codeTableName;
	}

	public String getFixity() {
		return fixity;
	}

	public void setFixity(String fixity) {
		this.fixity = fixity;
	}

}
