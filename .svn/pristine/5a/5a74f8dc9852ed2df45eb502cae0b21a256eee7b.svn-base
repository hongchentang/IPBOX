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
package com.hcis.ipanther.common.excel;


import com.hcis.ipanther.common.excel.ExcelFactory;
import com.hcis.ipanther.common.excel.builder.xml.XMLModelBuilder;
import com.hcis.ipanther.common.excel.config.Configuration;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.Resource;


public class ExcelFactoryBean implements InitializingBean{
	
	private final Log logger = LogFactory.getLog(getClass());

	private Resource configLocation;
	
	private ExcelFactory excelFactory;

	//private Resource[] modelLocations;

	/**
	 * @param excelFactory the excelFactory to set
	 */
	public void setExcelFactory(ExcelFactory excelFactory) {
		this.excelFactory = excelFactory;
	}

	private ExcelFactoryBuilder excelFactoryBuilder = new ExcelFactoryBuilder();

	/**
	 * @param configLocation
	 *            the configLocation to set
	 */
	public void setConfigLocation(Resource configLocation) {
		this.configLocation = configLocation;
	}

	/**
	 * @param modelLocations
	 *            the modelLocations to set
	 */
/*	public void setModelLocations(Resource[] modelLocations) {
		this.modelLocations = modelLocations;
	}*/

	public void setExcelFactoryBuilder(ExcelFactoryBuilder excelFactoryBuilder) {
		this.excelFactoryBuilder = excelFactoryBuilder;
	}

	protected ExcelFactory buildExcelFactory() throws NestedIOException {
		Configuration configuration;
		XMLModelBuilder xmlModelBuilder = null;
		if (this.configLocation != null) {
			try {
				xmlModelBuilder = new XMLModelBuilder(
						this.configLocation.getInputStream());
				configuration = xmlModelBuilder.parse();
			} catch (Exception ex) {
				throw new NestedIOException("Failed to parse config resource: "
						+ this.configLocation, ex);
			} 
		}else {
            if (this.logger.isDebugEnabled()) {
                this.logger.debug("Property 'configLocation' not specified, using default MyBatis Configuration");
            }
            configuration = new Configuration();
        }

		return this.excelFactoryBuilder.build(configuration);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.excelFactory = buildExcelFactory();
		
	}
	
	public ExcelFactory getObject() throws Exception {
        if (this.excelFactory == null) {
            afterPropertiesSet();
        }

        return this.excelFactory;
    }

}
