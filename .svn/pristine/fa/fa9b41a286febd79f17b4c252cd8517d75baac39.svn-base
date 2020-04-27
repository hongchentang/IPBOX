package com.hcis.ipanther.common.excel.config;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class ExcelConfig {
	/*
	 * 配置文件路径
	 */
	private static final String cfgFile = "/excel.properties";
	
	private final static  Log logger = LogFactory.getLog(ExcelConfig.class);
	
	/**
	 * 读出的属性
	 */
	private static Properties properties;
	
	static {
		properties = new Properties();
		InputStream is = ExcelConfig.class.getResourceAsStream(cfgFile);
		try {
			properties.load(is);
		} catch (IOException e) {
			logger.error("read excel.properties file fail please check you properties file is exists "+e);
			throw new RuntimeException("读取excel.propertise属性文件失败，请检查该属性文件是否存在！");
		}
	}

	/**
	 * 返回一个属性值
	 * 
	 * @param propertyName
	 *            属性名
	 * @return 返回指定属性名的值
	 */
	public static String getProperty(String propertyName) {		
		if (properties == null) {
			logger.error("system error,properties is null!");
			throw new RuntimeException("系统错误：读取excel.properties属性失败！");
		} else {
			return properties.getProperty(propertyName);
		}
	}
	
	public static String getFormatProperty(String propertyName,String[] arr){
		if (properties == null) {
			logger.error("system error,properties is null!");
			throw new RuntimeException("系统错误：读取excel.properties属性失败！");
		} else {
			return MessageFormat.format(properties.getProperty(propertyName), arr);
		}
	}
}
