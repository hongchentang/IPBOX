/**
 * 
 */
package com.haoyu.ipanther.common.excel.utils;

import java.text.ParseException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.time.DateUtils;
import com.hcis.ipanther.core.utils.Reflections;
import com.hcis.ipanther.core.utils.StringUtils;
import com.hcis.ipanther.core.utils.Validator;
import com.haoyu.ipanther.common.excel.DataType;
import com.haoyu.ipanther.common.excel.DateStyle;

/**
 * 字段类型校验工具类
 * @author Administrator
 *
 */
public class FieldValidator {
	
	private DataType[] dataTypes;
	
	private DateStyle dateStyle;
	
	private String errorMsg;
	
	private String colName;
	
	private String[] options;
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public FieldValidator(String colName,DataType[] dataTypes,DateStyle dateStyle,String[] options){
		this.colName = colName;
		this.dataTypes = dataTypes;
		this.dateStyle = dateStyle;
		this.options = options;
	}
	
	public boolean validate(int row,int col,String fieldValue){
		boolean flag = true;
		errorMsg = null;
		if(dataTypes!=null){
			StringBuffer sb = new StringBuffer((row+1)+"行"+(col+1)+"列:");
			for(DataType dataType:dataTypes){
				if(dataType==DataType.DATE){
					String[] args = {fieldValue,dateStyle.getStyle()};
					boolean result = (Boolean)(Reflections.invokeMethodByName(this,"validate"+StringUtils.capitalize(dataType.getName()), args));
					if(!result){
						flag = false;
						sb.append(colName+" 不合法  !");
					}
				}else{
					String[] args = {fieldValue};
					boolean result = (Boolean)Reflections.invokeMethodByName(this,"validate"+StringUtils.capitalize(dataType.getName()), args);
					if(!result){
						flag = false;
						if(dataType==DataType.REQURIED){
							sb.append(colName+" 必填！");
							break;
						}else if(dataType==DataType.OPTIONS){
							sb.append(colName+" 必须在选项范围内：").append(ArrayUtils.toString(options));
						}else{
							sb.append(colName+" 不合法  !");
						}
					}
				}
				
			}
			if(!flag){
				errorMsg = sb.toString();
			}
		}
		return flag;
	}
	
	/**
	 * 验证日期
	 * @param date
	 * @param dateStyle
	 * @return
	 */
	public  boolean validateDate(String date,DateStyle dateStyle){
		if(date==null||dateStyle==null){
			return false;
		}else if(date.length()!=dateStyle.getStyle().length()){
			return false;
		}else{
			try {
				DateUtils.parseDate(date, dateStyle.getStyle());
			} catch (ParseException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 验证email地址
	 * @param email
	 * @return
	 */
	public  boolean validateEmail(String email){
		return Validator.checkEmail(email);
	}
	
	/**
	 * 验证身份证号 
	 * @param idCard
	 * @return
	 */
	public boolean validateIdCard(String idCard){
		return Validator.validateIdCards(idCard);
	}
	
	/**
	 * 验证手机号
	 * @param mobilePhone
	 * @return
	 */
	public  boolean validateMobilePhone(String mobilePhone){
		return Validator.validateMobile(mobilePhone);
	}
	
	/**
	 * 验证是否数字
	 * @param numeric
	 * @return
	 */
	public  boolean validateNumeric(String numeric){
		return StringUtils.isNumeric(numeric);
	}
	
	/**
	 * 验证是否必填
	 * @param required
	 * @return
	 */
	public  boolean validateRequired(String required){
		return StringUtils.isNotEmpty(required);
	}
	
	/**
	 * 验证Options
	 * @param option
	 * @return
	 */
	public boolean validateOptions(String option){
		if(option!=null){
			return ArrayUtils.contains(options, option);
		}
		return false;
	}
}
