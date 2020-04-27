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
package com.hcis.ipanther.common.excel.convert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import com.hcis.ipanther.common.excel.ExcelFactory;
import com.hcis.ipanther.common.excel.ExcelFactoryBean;
import com.hcis.ipanther.common.excel.config.Configuration;
import com.hcis.ipanther.common.excel.config.ExcelConfig;
import com.hcis.ipanther.common.excel.model.ImportFailModel;
import com.hcis.ipanther.common.excel.model.ModelProperty;
import com.hcis.ipanther.common.excel.model.ModelPropertyType;
import com.hcis.ipanther.common.excel.model.ModelStatement;








import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import com.hcis.ipanther.core.utils.Validator;


public class ExcelTOModelService {

	private ModelStatement modelStatement;

	private List<ModelProperty> fixityList;


	private ExcelFactoryBean excelFactoryBean;
	
	
	private ExcelFactory excelFactory;

	/**
	 * @return the excelFactory
	 */
	public ExcelFactoryBean getExcelFactoryBean() {
		return excelFactoryBean;
	}

	/**
	 * @param excelFactory
	 *            the excelFactory to set
	 */
	public void setExcelFactoryBean(ExcelFactoryBean excelFactoryBean) {
		this.excelFactoryBean = excelFactoryBean;
		try {
			excelFactory = excelFactoryBean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * public ExcelToModell(File excelFile, RuturnConfig excelConfig, Map
	 * valueMap) { this.excelConfig = excelConfig; this.excelFile = excelFile;
	 * this.valueMap = valueMap; }
	 */

	private void setFixity() {
		fixityList = new ArrayList<ModelProperty>();
		Map<String, ModelProperty> pmap = this.modelStatement
				.getModelProperties();
		for (Iterator<ModelProperty> it = pmap.values().iterator(); it
				.hasNext();) {
			ModelProperty modelProperty = it.next();
			if (modelProperty.getFixity().toUpperCase().trim().equals("YES")) {
				fixityList.add(modelProperty);
			}
		}
	}

/*	public List<Object> getModelList(String filePath, String modelId,
			List<ImportFailModel> failList) throws IOException {
		File file = new File(filePath);
		return getModelList(file, modelId, failList);
	}*/
	
	public Workbook getWorkbook(File file,String fileName) throws IOException{
		Workbook  book = null;
		// 将传入的File构造为FileInputStream;
		FileInputStream in = new FileInputStream(file);
	     if (fileName.endsWith(".xls")){
	    	 book = (Workbook) new HSSFWorkbook(in);
	     }
	     else if (fileName.endsWith(".xlsx")){
	    	 book = (Workbook) new XSSFWorkbook(in);
	     }
		// // 得到工作表
		in.close();
		return book;
	}

/*	public List<Object> getModelList(File file, String modelId,
			List<ImportFailModel> failList) throws IOException {
		Configuration configuration = excelFactory.getConfiguration();
		modelStatement = configuration.getModelStatement(modelId);
		this.setFixity();
		List<Object> modelList = new ArrayList<Object>();
		Workbook  book = getWorkbook(file);
		// // 得到第一页
		Sheet sheet = book.getSheetAt(0);

		parseSheet(modelList, sheet, failList);

		String fileName = StringUtils.substringBeforeLast(
				file.getAbsolutePath(), ".")
				+ "result."
				+ StringUtils.substringAfterLast(file.getAbsolutePath(), ".");
		OutputStream output = new FileOutputStream(new File(fileName));
		book.write(output);
		output.close();
		return modelList;
	}*/

	private void parseSheet(List<Object> modelList, Sheet sheet,
			List<ImportFailModel> failList) {
		// Iterator<HSSFRow> row = sheet.rowIterator();
		// 获取标题行 如果没有标题行，此值为-1
		int titleRownum = Integer.parseInt(modelStatement.getTitleRownum());
		int maxColnum = Integer.parseInt(modelStatement.getMaxColnum());
		if (titleRownum >= 0) {
			Row titleRow = sheet.getRow(titleRownum);
			Cell resultCell = titleRow.createCell((short) (titleRow
					.getPhysicalNumberOfCells()));
			resultCell.setCellValue(ExcelConfig.getProperty("operate.result.name"));
		}
		// label
		row: for (int i = titleRownum + 1; i <= sheet.getLastRowNum(); i++) {
			Object obj = this.newInstance(modelStatement.getClassName());
			BeanWrapper bw = new BeanWrapperImpl(obj);
			Row row = sheet.getRow(i);
			for (int j = 0; j < maxColnum; j++) {
				ModelProperty modelProperty = null;
				// 不存在标题行则此属性获取通过列号
				if (titleRownum < 0) {
					modelProperty = modelStatement.getModelProperties().get(
							ObjectUtils.toString(j));
				} else {
					String excelTitleName = sheet.getRow(titleRownum)
							.getCell((short) j).getRichStringCellValue()
							.getString();
					modelProperty = modelStatement.getModelProperties().get(
							excelTitleName);
				}
				String cellValue = null;

				Cell cell = sheet.getRow(i).getCell((short) j);
				String validateStr = validate(modelProperty, cell);
				if (!StringUtils.isEmpty(validateStr)) {
					// 添加验证操作结果
					Cell resultCell = row.createCell((short) (maxColnum));
					resultCell.setCellValue(validateStr);
					ImportFailModel failModel = new ImportFailModel(
							row.getRowNum(), validateStr);
					failList.add(failModel);
					continue row;// 标记跳到外循环
				}
				cellValue = this.getStringCellValue(cell);

				if (modelProperty != null) {
					// //做出判断,是否需要 Text/Value 值转换.
					if (!StringUtils.isEmpty(modelProperty.getCodeTableName())) {
						String valueKey = modelProperty.getCodeTableName()
								.trim() + cellValue;
						Object obj1 = modelStatement.getModelProperties().get(
								valueKey);
						if (obj1 == null) {
							cellValue = "";
						} else {
							cellValue = ObjectUtils.toString(obj1);
						}
					}
					if (cellValue == null || cellValue.isEmpty()) {
						cellValue = modelProperty.getDefaultValue();
						sheet.getRow(i).createCell((short)j).setCellValue(cellValue);
					}
					//对象包含自定义对象属性
					if(StringUtils.contains(modelProperty.getName(), ".")){
						String[] beans = StringUtils.split(modelProperty.getName(), ".");
						Object tmpObj = null;
						BeanWrapper tmpBw = new BeanWrapperImpl(obj) ; 
						for(int k=0;k<beans.length-1;k++){
							if(tmpBw.getPropertyValue(beans[k])==null){
								tmpObj =newInstance(tmpBw.getPropertyType(beans[k]));
								tmpBw.setPropertyValue(beans[k],tmpObj);
								tmpBw = new BeanWrapperImpl(tmpObj) ;
							}else{
								tmpObj = tmpBw.getPropertyValue(beans[k]);
								tmpBw = new BeanWrapperImpl(tmpObj) ;
							}
						}
					}
					bw.setPropertyValue(modelProperty.getName(), cellValue);
				}
			}
			for (Iterator<ModelProperty> it = this.fixityList.iterator(); it
					.hasNext();) {
				ModelProperty modelProperty = it.next();
				Object value = modelStatement.getModelProperties().get(
						modelProperty.getName());
				if (value == null || value.toString().length() < 1) {
					value = modelProperty.getDefaultValue();
				}
				//对象包含自定义对象属性
				if(StringUtils.contains(modelProperty.getName(), ".")){
					String[] beans = StringUtils.split(modelProperty.getName(), ".");
					Object tmpObj = null;
					BeanWrapper tmpBw = new BeanWrapperImpl(obj) ; 
					for(int k=0;k<beans.length-1;k++){
						if(tmpBw.getPropertyValue(beans[k])==null){
							tmpObj =newInstance(tmpBw.getPropertyType(beans[k]));
							tmpBw.setPropertyValue(beans[k],tmpObj);
							tmpBw = new BeanWrapperImpl(tmpObj) ;
						}else{
							tmpObj = tmpBw.getPropertyValue(beans[k]);
							tmpBw = new BeanWrapperImpl(tmpObj) ;
						}
					}
				}
				bw.setPropertyValue(modelProperty.getName(), value);
			}

			// 设置model在excel中的行号，以方便写入操作结果
			bw.setPropertyValue("rownum", i);
			modelList.add(obj);
		}
	}

	public List<Object> getModelList(Workbook book, String modelId,
			List<ImportFailModel> failList) {
		Configuration configuration = excelFactory.getConfiguration();
		modelStatement = configuration.getModelStatement(modelId);
		this.setFixity();
		List<Object> modelList = new ArrayList<Object>();
		parseSheet(modelList, book.getSheetAt(0), failList);
		return modelList;
	}

	private String validate(ModelProperty modelProperty, Cell cell) {
		// 是否必填验证
		boolean required = (modelProperty==null||StringUtils.isEmpty(modelProperty.getRequired())) ? false
				: Boolean.valueOf(modelProperty.getRequired());
		if (required) {
			if (cell == null
					|| StringUtils.isEmpty(this.getStringCellValue(cell))) {
				String[] arr = {""+modelProperty.getExcelTitleName()};
				return ExcelConfig.getFormatProperty("validate.required", arr);
			}
		}else{
			if(cell == null
						|| StringUtils.isEmpty(this.getStringCellValue(cell))){
				return null;
			}
		}
		

		int maxLength = modelProperty.getMaxLength();
		String[] columnNoArr = {""+modelProperty.getColumn()};
		if (maxLength > 0
				&& cell.getCellType() != Cell.CELL_TYPE_NUMERIC
				&& cell.getRichStringCellValue().toString().length() > maxLength) {
			
			String[] maxLengthArr = {""+maxLength};
			return  !StringUtils.isEmpty(modelProperty.getExcelTitleName()) ? modelProperty
					.getExcelTitleName()
					: (ExcelConfig.getFormatProperty("validate.column.no", columnNoArr))
					+ ExcelConfig.getFormatProperty("validate.maxLength", maxLengthArr);
		}
		// 数据类型 String,Date,IDCards,Numeric,Email,MobilePhone
		String dataType = modelProperty.getDataType();
		if (dataType.equalsIgnoreCase(ModelPropertyType.NUMERIC)) {
			if (cell.getCellType() != Cell.CELL_TYPE_NUMERIC
					&& !StringUtils.isNumeric(cell.getRichStringCellValue()
							.toString())) {
				return !StringUtils.isEmpty(modelProperty.getExcelTitleName()) ? modelProperty
						.getExcelTitleName()
						: (ExcelConfig.getFormatProperty("validate.column.no", columnNoArr))
						+ ExcelConfig.getProperty("validate.dataType.numeric");
			}
		} else if (dataType.equalsIgnoreCase(ModelPropertyType.EMAIL)) {
			if (!Validator.checkEmail(getStringCellValue(cell))) {
				return ExcelConfig.getProperty("validate.dataType.email");
			}
		} else if (dataType.equalsIgnoreCase(ModelPropertyType.MOBILEPHONE)) {
			if (!Validator.validateMobile(getStringCellValue(cell))) {
				return ExcelConfig.getProperty("validate.dataType.mobilePhone");
			}
		} else if (dataType.equalsIgnoreCase(ModelPropertyType.IDCARDS)) {
			if (!Validator.validateIdCards(getStringCellValue(cell))) {
				return ExcelConfig.getProperty("validate.dataType.idCard");
			}
		}
		return null;
	}

	private Object newInstance(String className) {
		Object obj = null;
		try {
			obj = Class.forName(className).newInstance();
			// System.out.println("init Class = " + className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	private Object newInstance(Class clazz) {
		Object obj = null;
		try {
			obj = clazz.newInstance();
		}catch (InstantiationException e) {
			e.printStackTrace();
//			throw new ExcelException(this.getClass().getName(),e.getCause());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
//			throw new ExcelException(e);
		}
		return obj;
	}

	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @author
	 * @param cell
	 * @return
	 */
	public String getStringCellValue(Cell cell) {
		String strCell = "";
		if(cell==null)
			return strCell;
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			strCell = cell.getRichStringCellValue().toString();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			NumberFormat nf = NumberFormat.getCurrencyInstance();
			DecimalFormat df = (DecimalFormat) nf;
			df.setDecimalSeparatorAlwaysShown(true);
			df.applyPattern("###############");
			String value = df.format(new Double(cell.getNumericCellValue()));
			strCell = ObjectUtils.toString(value);
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			strCell = ObjectUtils.toString(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		return strCell;
	}
	
}
