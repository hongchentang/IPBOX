package com.hcis.ipanther.common.privilege.config;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrivilegeConfig
{
  private static final String cfgFile = "/privilege.properties";
  private static final Logger logger = LoggerFactory.getLogger(PrivilegeConfig.class);

  private static Properties properties = new Properties();

  static { InputStream is = PrivilegeConfig.class.getResourceAsStream("/privilege.properties");
    try {
      properties.load(is);
    } catch (IOException e) {
      logger.error("read privilege.properties file fail please check you properties file is exists " + e);
      throw new RuntimeException("系统错误：读取privilege.properties属性失败！");
    } }

  public static String getProperty(String propertyName)
  {
    if (properties == null) {
      logger.error("system error,properties is null!");
      return null;
    }
    return properties.getProperty(propertyName);
  }

  public static String getFormatProperty(String propertyName, String[] placeHolderValue)
  {
    if (properties == null) {
      logger.error("system error,properties is null!");
      throw new RuntimeException("系统错误：读取privilege.properties属性失败！");
    }
    return MessageFormat.format(properties.getProperty(propertyName), placeHolderValue);
  }
}