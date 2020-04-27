/**
 * 
 */
package com.haoyu.ipanther.common.excel.styles;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * @author lianghuahuang
 *
 */
public abstract class Style {
	
/*	protected abstract Style newInstance();*/
	
	/**
	 * 获取表头样式
	 * @param workbook
	 * @return
	 */
	public  abstract  CellStyle getHeadStyle(HSSFWorkbook workbook);
	
	/**
	 * 获取表格内容样式
	 * @param workbook
	 * @return
	 */
	public  abstract  CellStyle getBodyStyle(HSSFWorkbook workbook);

}
