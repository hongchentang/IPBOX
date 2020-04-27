/**
 * 
 */
package com.hcis.ipanther.core.utils;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Administrator
 * 
 * 1.使用文件名作为key，从缓存中读取
 * 2.如果缓存中没有，则根据文件名查找配置文件，并缓存，查找方式 String fileName="/"+propertyFile+".properties";
 * 
 */
public class AppConfig {

	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

	private static Map<String, Properties> propertiesMap = null;

	/**
	 * 初始化加载配置
	 */
	public static Properties getPropertiesMap(String propertyFile){
		if(AppConfig.propertiesMap==null||AppConfig.propertiesMap.isEmpty()){
			propertiesMap=new HashMap<String,Properties>();
		}
		Properties properties=propertiesMap.get(propertyFile);
		if(properties==null){
			try {
				String fileName="/"+propertyFile+".properties";
				InputStream is = AppConfig.class.getResourceAsStream(fileName);
				properties = new Properties();
				properties.load(is);
				propertiesMap.put(fileName, properties);
			}
			catch (Exception e) {
				logger.error("properties files read failed!" + e);
				throw new RuntimeException("properties files read failed!");
			}
		}
		return properties;
	}
	
	/**
	 * 重载配置
	 */
	public static void reload(){
		propertiesMap = null;
	}

	public static String getProperty(String propertyFile,String propertyName) {
		Properties properties=getPropertiesMap(propertyFile);
		if (properties == null) {
			logger.error("system error,properties is null!");
			throw new RuntimeException(propertyFile+".properties not exist ！");
		}
		else {
			return properties.getProperty(propertyName);
		}
		
	}

	public static String getFormatProperty(String propertyFile,String propertyName, String[] strArr) {
		Properties properties=getPropertiesMap(propertyFile);
		if (properties == null) {
			logger.error("system error,properties is null!");
			throw new RuntimeException(propertyFile+".properties not exist ！");
		}
		else {
			return MessageFormat.format(properties.getProperty(propertyName), strArr);
		}
	}

}
