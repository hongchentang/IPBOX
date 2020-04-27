package com.hcis.ipanther.core.utils;

import java.util.Map;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;



public class BeanLocator implements ApplicationContextAware {

	private static BeanFactory servicesbeanFactory;
	
	// Spring应用上下文环境
	private static ApplicationContext applicationContext;
	
	public static final Logger logger = LoggerFactory.getLogger(BeanLocator.class);

	/** 
     * 
     * 获取spring容器，以访问容器中定义的其他bean 
     * @since MOSTsView 3.0 2009-11-16
     */
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		BeanLocator.applicationContext = applicationContext;
	}

	/**
	 * @return ApplicationContext
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * 获取对象 这里重写了bean方法，起主要作用
	 * 
	 * @param name
	 * @return Object 一个以所给名字注册的bean的实例
	 * @throws BeansException
	 */
	public static Object getBean(String name) throws BeansException {
		return applicationContext.getBean(name);
	}
	
	
	protected BeanLocator() {
	}
	
	/**
	 * context注入
	 * @param servletcontext
	 */
	public static synchronized ApplicationContext initBeanFactory(ServletContext servletcontext){
		if (servicesbeanFactory == null) {
			applicationContext = WebApplicationContextUtils.getWebApplicationContext(servletcontext);
			servicesbeanFactory = (BeanFactory) applicationContext;
		}
		return (ApplicationContext)servicesbeanFactory;
	}
	public static synchronized ApplicationContext initBeanFactory(ApplicationContext applicationContext) {

		if (servicesbeanFactory == null) {
			BeanLocator.applicationContext = applicationContext;
			servicesbeanFactory = (BeanFactory) applicationContext;
		}
		return (ApplicationContext)servicesbeanFactory;
	}


	/**
	 * 获取指定Class类型的所有bean映射
	 * @param <T>
	 * @param c
	 * @return
	 */
	public static <T> Map<String, T> getBeansOfType(Class<T> c){
		if (servicesbeanFactory == null) {
			initServicesBeanFactory();
		}
		try {
			return ((ApplicationContext)servicesbeanFactory).getBeansOfType(c);
		} catch (Exception e) {
			logger.error("获取Bean失败",e);
			return null;
		}
	}
	
	private static synchronized void initServicesBeanFactory(){
		if (servicesbeanFactory == null) {
			try {		
				applicationContext = new ClassPathXmlApplicationContext("classpath*:applicationContext.xml");
				servicesbeanFactory = (BeanFactory) applicationContext;
			} catch (Exception e) {
				logger.error("获取Bean工厂失败", e);
			}
		}
	}
}

