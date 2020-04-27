package com.hcis.ipanther.common.excel.utils;

import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;

public class ExcelUtil {
	public static RichTextString getRichTextString(String content,String fileName){
		if (fileName.endsWith(".xls")){
	    	 return new HSSFRichTextString(content);
	     }
	     else if (fileName.endsWith(".xlsx")){
	    	 return new XSSFRichTextString(content);
	     }
		return null;
	}
}
