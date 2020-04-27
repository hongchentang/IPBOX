package com.hcis.ipanther.core.utils;

import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.ss.usermodel.Workbook;

/**
 * 导出工具类
 * @author wuwentao
 * @date 2015年3月17日
 */
public class ExportUtils {
	
	/**
	 * 以模板的形式导出Excel
	 * @param response
	 * @param templateFileName 类路径文件下的导出模板，如：/excel/dept_export.xls
	 * @param beans 要导出的数据 beans的key不能与属性名类似，如属性为deptName,则key不能为dept
	 * @param ouputName 输出文件名称
	 * @throws Exception
	 */
	public static void exportExcel(HttpServletResponse response,String templateFileName,Map<String,Object> beans,String ouputName) 
			throws Exception {
		XLSTransformer transformer = new XLSTransformer();
		InputStream inputstream =  ExportUtils.class.getResourceAsStream(templateFileName);
		Workbook hssfworkbook = transformer.transformXLS(inputstream, beans);
		response.reset();
		String filename = new String(ouputName.getBytes("UTF-8"), "ISO8859_1");
		response.setHeader("Content-Disposition", "attachment;filename="+filename+".xls");
		response.setContentType("application/vnd.ms-excel");
		hssfworkbook.write(response.getOutputStream());
	}
	
}
