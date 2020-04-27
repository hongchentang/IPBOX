package com.hcis.ipr.cms.common.utils;

import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateModelException;

/**
 * 
 * <p>Title: BaseDirective.java</p>
 * 
 * <p>Description: 自定义标签共同的功能</p>
 * 
 * <p>Date: May 13, 2012</p>
 * 
 * <p>Time: 9:39:01 PM</p>
 * 
 * <p>Copyright: 2012</p>
 * 
 * <p>Company: freeteam</p>
 * 
 * @author freeteam
 * @version 1.0
 * 
 * <p>============================================</p>
 * <p>Modification History
 * <p>Mender: </p>
 * <p>Date: </p>
 * <p>Reason: </p>
 * <p>============================================</p>
 */
public class CmsCommonDirective extends CmsCommonBase{

	/**
	 * 获得参数
	 * @param params
	 * @param name
	 * @return
	 */
	public String getParam(Map<String,Object> params,String name){
		String value="";
		if (params.get(name)!=null && params.get(name).toString().length()>0) {
			value=params.get(name).toString();
		}
		return value;
	}
	
	/**
	 * 获得参数
	 * @param params
	 * @param name
	 * @return
	 */
	public String getParamToString(Map<String,Object> params,String name){
		String value="";
		if (params.get(name)!=null && StringUtils.isNotEmpty(ObjectUtils.toString(params.get(name)))) {
			value= ObjectUtils.toString(params.get(name));
		}
		return value;
	}
	
	/**
	 * 获得参数并传递默认值 
	 * @param params
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public String getParamToString(Map<String,Object> params,String name,String defaultValue){
		String value=defaultValue;
		if (params.get(name)!=null && StringUtils.isNotEmpty(ObjectUtils.toString(params.get(name)))) {
			value= ObjectUtils.toString(params.get(name));
		}
		return value;
	}
	
	
	/**
	 * 获得参数并传递默认值 
	 * @param params
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public String getParam(Map<String,Object> params,String name,String defaultValue){
		String value=defaultValue;
		if (params.get(name)!=null && params.get(name).toString().length()>0) {
			value=params.get(name).toString();
		}
		return value;
	}
	/**
	 * 获得int参数并传递默认值 
	 * @param params
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public int getParamInt(Map<String,Object> params,String name,int defaultValue){
		int value=defaultValue;
		if (params.get(name)!=null && params.get(name).toString().length()>0) {
			try {
				value=Integer.parseInt(params.get(name).toString());
			} catch (Exception e) {
			}
		}
		return value;
	}
	
	/**
	 * 获得int参数并传递默认值 
	 * @param params
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public int getParamToInt(Map<String,Object> params,String name,int defaultValue){
		int value=defaultValue;
		if (params.get(name)!=null && StringUtils.isNotEmpty(ObjectUtils.toString(params.get(name)))) {
			try {
				value=Integer.parseInt(ObjectUtils.toString(params.get(name)));
			} catch (Exception e) {
				logger.error(e.getMessage());
				return value;
			}
		}
		return value;
	}
	
	/**
	 * 获得数据
	 * @param params
	 * @param name
	 * @return
	 * @throws TemplateModelException 
	 */
	public String getData(Environment env,String name) throws TemplateModelException{
		String value="";
		if (env.getDataModel().get(name)!=null && env.getDataModel().get(name).toString().length()>0) {
			value=env.getDataModel().get(name).toString();
		}
		return value;
	}
	
	
	/**
	 * 获得数据
	 * @param params
	 * @param name
	 * @return
	 * @throws TemplateModelException 
	 */
	public String getParamToString(Environment env,String name) throws TemplateModelException{
		String value="";
		if (env.getDataModel().get(name)!=null && StringUtils.isNotEmpty(ObjectUtils.toString(env.getDataModel().get(name)))) {
			try {
				value=ObjectUtils.toString(env.getDataModel().get(name));
			} catch (Exception e) {
				e.printStackTrace();
				return value;
			}
		}
		return value;
	}
	
	
	
	/**
	 * 获得数据并传递默认值 
	 * @param params
	 * @param name
	 * @param defaultValue
	 * @return
	 * @throws TemplateModelException 
	 */
	public String getParamToString(Environment env,String name,String defaultValue) throws TemplateModelException{
		String value=defaultValue;
		if (env.getDataModel().get(name)!=null && StringUtils.isNotEmpty(ObjectUtils.toString(env.getDataModel().get(name)))) {
			try {
				value=ObjectUtils.toString(env.getDataModel().get(name));
			} catch (Exception e) {
				e.printStackTrace();
				return value;
			}
		}
		return value;
	}
	
	/**
	 * 获得数据并传递默认值 
	 * @param params
	 * @param name
	 * @param defaultValue
	 * @return
	 * @throws TemplateModelException 
	 */
	public String getData(Environment env,String name,String defaultValue) throws TemplateModelException{
		String value=defaultValue;
		if (env.getDataModel().get(name)!=null && env.getDataModel().get(name).toString().length()>0) {
			value=env.getDataModel().get(name).toString();
		}
		return value;
	}
}
