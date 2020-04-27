package com.hcis.ipanther.core.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class CommonConfig {
	/*
	 * 配置文件路径
	 */
	private static final String cfgFile = "/common.properties";
	
	private static final   Logger logger = LoggerFactory.getLogger(CommonConfig.class);
	
	/**
	 * 读出的属性
	 */
	private static Properties properties;
	
	static {
		properties = new Properties();
		InputStream is = CommonConfig.class.getResourceAsStream(cfgFile);
		try {
			properties.load(is);
		} catch (IOException e) {
			logger.error("read common.properties file fail please check you properties file is exists "+e);
			throw new RuntimeException("读取common.propertise属性文件失败，请检查该属性文件是否存在!");
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
			throw new RuntimeException("系统错误：读取common.properties属性失败!");
		} else {
			return properties.getProperty(propertyName);
		}
	}
	
	public static String getFormatProperty(String propertyName,String[] strArr){
		if (properties == null) {
			logger.error("system error,common properties is null!");
			throw new RuntimeException("系统错误：读取common.properties属性失败!");
		} else {			
			return MessageFormat.format(properties.getProperty(propertyName), strArr);
		}
	}
	
	/**
	 * Gets the app name.
	 *
	 * @return the app name
	 */
	public static String getAppName() {		
		return getProperty("app.name");
	}
	
}
