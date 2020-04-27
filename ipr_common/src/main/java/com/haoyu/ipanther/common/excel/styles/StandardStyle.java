/**
 * 
 */
package com.haoyu.ipanther.common.excel.styles;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 * @author lianghuahuang
 *
 */
public class StandardStyle extends Style {
	
/*	protected Style newInstance(){
		return new StandardStyle();
	}*/

	/* (non-Javadoc)
	 * @see com.haoyu.ipanther.common.excel.styles.Style#getHeadStyle(org.apache.poi.hssf.usermodel.HSSFWorkbook)
	 */
	@Override
	public CellStyle getHeadStyle(HSSFWorkbook workbook) {
			HSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);  
			cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
			cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
			cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
	        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
	        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  
	        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
	        // 生成字体  
	        HSSFFont font = workbook.createFont();  
	        font.setColor(HSSFColor.VIOLET.index);  
	        font.setFontHeightInPoints((short) 12);  
	        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
	        // 把字体应用到当前的样样式  
	        cellStyle.setFont(font);  
	        return cellStyle;  
	}

	/* (non-Javadoc)
	 * @see com.haoyu.ipanther.common.excel.styles.Style#getBodyStyle(org.apache.poi.hssf.usermodel.HSSFWorkbook)
	 */
	@Override
	public CellStyle getBodyStyle(HSSFWorkbook workbook) {
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);  
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);  
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);  
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        // 生成字体  
        HSSFFont font = workbook.createFont();  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);  
        // 把字体应用到当前的样样式  
        cellStyle.setFont(font);  
        return cellStyle;  
	}

}
