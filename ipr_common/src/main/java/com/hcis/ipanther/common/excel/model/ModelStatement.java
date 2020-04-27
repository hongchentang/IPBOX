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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.hcis.ipanther.common.excel.config.Configuration;


public class ModelStatement {

	private String id;

	private String className;
	
	private String titleRownum;
	
	private String maxColnum;

	private Configuration configuration;

	private Map<String,ModelProperty> modelProperties;

	public String getId() {
		return id;
	}

	/**
	 * @return the titleRow
	 */
	public String getTitleRownum() {
		return titleRownum;
	}

	public String getClassName() {
		return className;
	}

	public Map<String,ModelProperty> getModelProperties() {
		return modelProperties;
	}

	public String getMaxColnum() {
		return maxColnum;
	}

	public static class Builder {
		private ModelStatement modelStatement = new ModelStatement();

		public Builder(Configuration configuration, String id, String className,String titleRow,String maxColnum) {
			modelStatement.configuration = configuration;
			modelStatement.id = id;
			modelStatement.className = className;
			modelStatement.titleRownum = titleRow;
			modelStatement.maxColnum = maxColnum;
			modelStatement.modelProperties = new HashMap<String,ModelProperty>();
		}

		public String id() {
			return modelStatement.id;
		}

		public String className() {
			return modelStatement.className;
		}

		public Builder modelProperties(Map<String,ModelProperty> modelProperties) {
			modelStatement.modelProperties = modelProperties;
			return this;
		}

		public ModelStatement build() {
			assert modelStatement.configuration != null;
			assert modelStatement.id != null;
			modelStatement.modelProperties = Collections
					.unmodifiableMap(modelStatement.modelProperties);
			return modelStatement;
		}

	}
}
