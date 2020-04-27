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
package com.hcis.ipanther.common.excel.builder.xml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.hcis.ipanther.common.excel.config.Configuration;
import com.hcis.ipanther.common.excel.model.ModelProperty;
import com.hcis.ipanther.common.excel.model.ModelPropertyDefine;
import com.hcis.ipanther.common.excel.model.ModelStatement;



import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public class XMLModelBuilder {

	private Configuration configuration;

	private Element root;

	public XMLModelBuilder(InputStream inputStream) {
		this.configuration = new Configuration();
		// this.resource = resource;
		// 解析配置文件
		try {
			root = new SAXReader().read(inputStream).getRootElement();
		} catch (DocumentException e) {
			e.printStackTrace();
			System.out.println("解析导入EXCEL配置文件失败!");
			// TODO 进行自定义异常抛出
		}
	}

	public Configuration parse() {
		bindModel();
		return configuration;
	}

	public ModelStatement getModel(String id) {
		Element model = this.getModelElement(id);
		ModelStatement modelStatement = null;
		if (model != null) {
			modelStatement = new ModelStatement.Builder(configuration, id,
					model.attributeValue(ModelPropertyDefine.MODEL_CLASS),
					model.attributeValue(ModelPropertyDefine.TITLE_ROWNUM),
					model.attributeValue(ModelPropertyDefine.MAX_COLNUM))
					.modelProperties(this.getModelProperties(model)).build();
		}
		return modelStatement;
	}

	private Element getModelElement(String id) {
		List<Element> list = root.elements();
		Element model = null;
		for (Iterator<Element> it = list.iterator(); it.hasNext();) {
			Element elment = it.next();
			if (elment.attributeValue("id").equals(id)) {
				model = elment;
				break;
			}
		}
		return model;
	}

	private Map<String, ModelProperty> getModelProperties(Element model) {
		Map<String, ModelProperty> modelProperties = new HashMap<String, ModelProperty>();
		List<Element> list = model.elements();
		for (Iterator<Element> it = list.iterator(); it.hasNext();) {
			Element property = it.next();
			ModelProperty modelProperty = new ModelProperty();
			modelProperty.setName(property
					.attributeValue(ModelPropertyDefine.PROPERTY_NAME));
			if (!StringUtils.isEmpty(property
					.attributeValue(ModelPropertyDefine.PROPERTY_CLOUMN))) {
				modelProperty.setColumn(Integer.valueOf(property
						.attributeValue(ModelPropertyDefine.PROPERTY_CLOUMN)));
			}
			modelProperty
					.setExcelTitleName(property
							.attributeValue(ModelPropertyDefine.PROPERTY_EXCEL_TITLE_NAME));
			modelProperty.setDataType(property
					.attributeValue(ModelPropertyDefine.PROPERTY_DATA_TYPE));
			if(!StringUtils.isEmpty(property.attributeValue(ModelPropertyDefine.PROPERTY_MAX_LENGTH))){
				if(StringUtils.isNumeric(property.attributeValue(ModelPropertyDefine.PROPERTY_MAX_LENGTH))){
					modelProperty.setMaxLength(Integer.valueOf(property
							.attributeValue(ModelPropertyDefine.PROPERTY_MAX_LENGTH)));	
				}else{
					// TODO 日志记录属性文件配置存在问题
				}
				
			}
			modelProperty.setFixity(property
					.attributeValue(ModelPropertyDefine.PROPERTY_FIXITY));
			modelProperty
					.setCodeTableName(property
							.attributeValue(ModelPropertyDefine.PROPERTY_CODE_TABLE_NAME));
			modelProperty.setDefaultValue(property
					.attributeValue(ModelPropertyDefine.PROPERTY_DEFAULT));
			modelProperty.setRequired(property
					.attributeValue(ModelPropertyDefine.PROPERTY_REQUIRED));
			if (StringUtils.isEmpty(modelProperty.getExcelTitleName())) {
				modelProperties.put(ObjectUtils.toString(modelProperty.getColumn()),
						modelProperty);
			}
			modelProperties.put(modelProperty.getExcelTitleName(),
					modelProperty);

		}
		return modelProperties;
	}

	private void bindModel() {
		List<Element> list = root.elements();
		Element model = null;
		for (Iterator<Element> it = list.iterator(); it.hasNext();) {
			model = it.next();
			ModelStatement modelStatement = new ModelStatement.Builder(
					configuration,
					model.attributeValue(ModelPropertyDefine.MODEL_ID),
					model.attributeValue(ModelPropertyDefine.MODEL_CLASS),
					model.attributeValue(ModelPropertyDefine.TITLE_ROWNUM),
					model.attributeValue(ModelPropertyDefine.MAX_COLNUM))
					.modelProperties(this.getModelProperties(model)).build();

			configuration.addModelStatement(modelStatement);
		}
	}
}
