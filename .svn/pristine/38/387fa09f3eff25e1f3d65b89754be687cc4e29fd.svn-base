package com.hcis.ipanther.common.excel.utils;

import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelExport {

	/**
	 * 把list集合里的数据组装成excel表数据
	 * 
	 * @param list
	 *            数据
	 * @param titles
	 *            标题
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@SuppressWarnings("deprecation")
	public static HSSFWorkbook mergeToExcel(List<String[]> list, String[] titles,String mainTitle) {
		HSSFWorkbook reBook = new HSSFWorkbook(); // 创建一个excel表对象
		HSSFSheet sheet = reBook.createSheet(); // 创建一个表格

		short rowN = 0; // 表格的行数计算变量
		short cellN = 0; // 每一行的单元格数计算变量
		HSSFRow row = sheet.createRow(rowN++); // 创建第一行，标题行
		row.setHeight((short) (15.625 * 30));

		HSSFCellStyle cellStyle = reBook.createCellStyle();
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);

		HSSFFont font = reBook.createFont();
		font.setBoldweight((short) 10);
	   /*
		* 填入主标题
	    */
         if(mainTitle!=null){
     	HSSFCellStyle cellStyletemp = reBook.createCellStyle();
     	cellStyletemp.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 左右居中   
		HSSFCell cell = row.createCell(0);
		 CellRangeAddress range = new CellRangeAddress(0, 0, 0, 7);   
		    sheet.addMergedRegion(range);   
		cell.setCellValue(new HSSFRichTextString(mainTitle));
		cellStyletemp.setFillBackgroundColor((short) 0x00bb66);
		cellStyletemp.setFont(font);
     	cellStyletemp.setBorderBottom(HSSFCellStyle.BORDER_THIN);
     	cellStyletemp.setBorderLeft(HSSFCellStyle.BORDER_THIN);
     	cellStyletemp.setBorderRight(HSSFCellStyle.BORDER_THIN);
     	cellStyletemp.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cell.setCellStyle(cellStyletemp);
		sheet.setColumnWidth((short) 0, (short) (35.7 * 128));
		
		row=sheet.createRow(rowN++); // 创建新的一行
         }
         /*
		 * 填入标题
		 */
		for (String s : titles) {
			HSSFCell cell = row.createCell(cellN++);
			cell.setCellValue(new HSSFRichTextString(s));
			cellStyle.setFillBackgroundColor((short) 0x00bb66);
			cellStyle.setFont(font);
			cell.setCellStyle(cellStyle);
			sheet.setColumnWidth((short) 0, (short) (35.7 * 128));

		}
		cellStyle.setFillBackgroundColor((short) 0xFFFFFF);

		for (String[] strArr : list) {
			cellN = 0; // 每一个数据对象占据一行，所以单元格计算变量要初始为0
			row = sheet.createRow(rowN); // 创建新的一行
			row.setHeight((short) (15.625 * 20));
			for (String str : strArr) {
				HSSFCell cell = row.createCell(cellN++);
				cell.setCellValue(new HSSFRichTextString(str));
				cell.setCellStyle(cellStyle);
				sheet.setColumnWidth((short) rowN, (short) (35.7 * 128));
			}
			rowN++;
		}
		return reBook;
	}

	/**
	 * 导出excel表
	 * 
	 * @param list
	 * @param titles
	 * @return
	 * @throws Exception
	 */
	public static void outSuccessExcel(List<String[]> list, String outName,
			String[] titles, HttpServletResponse response,String mainTitle) throws Exception {
		if (list != null && list.size() > 0) {
			HSSFWorkbook book = mergeToExcel(list, titles,mainTitle);
			response.setCharacterEncoding("GBK");
			outName = new String(outName.getBytes("GBK"),"ISO-8859-1");
			response.setContentType("application/xls;charset=GBK");
			response.setHeader("Content-disposition", "attachment; filename="
					+ outName + ".xls");
			OutputStream out = response.getOutputStream();
			book.write(out);
			out.flush();
			out.close();
		}
	}
}
