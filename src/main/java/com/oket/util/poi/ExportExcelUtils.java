package com.oket.util.poi;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.oket.util.StringTools;
import com.oket.util.TimeUtils;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;

import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈poi Excel导出〉
 *
 * @author ysh
 * @create 2019/3/9
 * @since 1.0.0
 */
public class ExportExcelUtils {
	public static void exportExcel(HttpServletResponse response, String fileName, List list, JSONObject requestJson, String language) throws Exception {
		// 告诉浏览器用什么软件可以打开此文件
		response.setHeader("content-Type", "application/vnd.ms-excel");
		// 下载文件的默认名称
		response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
		exportExcel(getExcelData(list, requestJson, language), response.getOutputStream());
	}

	public static void exportExcel(ExcelData data, OutputStream out) throws Exception {

		XSSFWorkbook wb = new XSSFWorkbook();
		try {
			String sheetName = data.getName();
			if (null == sheetName) {
				sheetName = "Sheet1";
			}
			XSSFSheet sheet = wb.createSheet(sheetName);
			writeExcel(wb, sheet, data);
			wb.write(out);
		} finally {
			wb.close();
		}
	}

	private static ExcelData getExcelData(List<JSONObject> list, JSONObject requestJson, String language) throws ParseException {
		ExcelData excelData = new ExcelData();
		// 列名
		String titles = requestJson.getString("excelTitles");
		String fileds = requestJson.getString("excelFileds");
		if (!StringTools.isNullOrEmpty(titles) && !StringTools.isNullOrEmpty(fileds)) {
			String[] columns = titles.split(",");
			String[] filedColumns = fileds.split(",");
			List<String> titleList = new ArrayList<>();
			List<List<Object>> rows = new ArrayList<>();
			for (String column : columns) {
				titleList.add(column);
			}
			excelData.setTitles(titleList);
			// 列数据
			for (Object ob : list) {
				JSONObject object = JSONObject.parseObject(JSON.toJSONStringWithDateFormat(ob, "yyyy-MM-dd HH:mm:ss"));
				List<Object> row = new ArrayList<>();
				for (String column : filedColumns) {
					String value = null;
					if (column.trim().contains(".")) {
						/*
						多层嵌套，迭代的取
						 */
						String[] entities = column.trim().split("\\.");
						JSONObject jsonObject1 = object.getJSONObject(entities[0]);
						if (jsonObject1 != null) {
							for (int i = 1; i < entities.length; i++) {
								if (i == entities.length - 1) {
									value = jsonObject1.getString(entities[i]);
								} else {
									JSONObject jsonObjectTemp = jsonObject1.getJSONObject(entities[i]);
									if (jsonObjectTemp == null) {
										value = "";
										break;
									} else {
										jsonObject1 = jsonObjectTemp;
									}
								}
							}
						} else {
							value = "";
						}

					} else {
						value = object.getString(column.trim());
					}
					/**
					 * 判断是否是英文，如果是英文，那么需要转化一下时间格式
					 */
					if ("en".equals(language)) {
						if (value != null && value.contains("-") && value.contains(":")) {
							value = TimeUtils.getSecondTime(value);
						}
					}
					row.add(value);
				}
				rows.add(row);
			}
			excelData.setRows(rows);
		}
		return excelData;
	}

	private static void writeExcel(XSSFWorkbook wb, Sheet sheet, ExcelData data) {

		int rowIndex = 0;
		rowIndex = writeTitlesToExcel(wb, sheet, data.getTitles());
		writeRowsToExcel(wb, sheet, data.getRows(), rowIndex);
		// 设置之后 列宽会在数据量大得时候受影响
		// autoSizeColumns(sheet, data.getTitles().size() + 1);

	}

	private static int writeTitlesToExcel(XSSFWorkbook wb, Sheet sheet, List<String> titles) {
		int rowIndex = 0;
		int colIndex = 0;
		Font titleFont = wb.createFont();
		titleFont.setFontName("simsun");
		titleFont.setBold(true);
		titleFont.setColor(IndexedColors.BLACK.index);

		XSSFCellStyle titleStyle = wb.createCellStyle();
		titleStyle.setAlignment(HorizontalAlignment.CENTER);
		titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		titleStyle.setFillForegroundColor(new XSSFColor(new Color(182, 184, 192)));
		titleStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		titleStyle.setFont(titleFont);
		setBorder(titleStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));

		Row titleRow = sheet.createRow(rowIndex);
		colIndex = 0;

		for (String field : titles) {
			Cell cell = titleRow.createCell(colIndex);
			cell.setCellValue(field);
			cell.setCellStyle(titleStyle);
			colIndex++;
		}

		rowIndex++;
		return rowIndex;
	}

	private static int writeRowsToExcel(XSSFWorkbook wb, Sheet sheet, List<List<Object>> rows, int rowIndex) {
		int colIndex = 0;

		Font dataFont = wb.createFont();
		dataFont.setFontName("simsun");
		dataFont.setColor(IndexedColors.BLACK.index);

		XSSFCellStyle dataStyle = wb.createCellStyle();
		dataStyle.setAlignment(HorizontalAlignment.CENTER);
		dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		dataStyle.setFont(dataFont);
		setBorder(dataStyle, BorderStyle.THIN, new XSSFColor(new Color(0, 0, 0)));

		for (List<Object> rowData : rows) {
			Row dataRow = sheet.createRow(rowIndex);
			colIndex = 0;
			for (Object cellData : rowData) {
				Cell cell = dataRow.createCell(colIndex);
				if (cellData != null) {
					cell.setCellValue(cellData.toString());
				} else {
					cell.setCellValue("");
				}
				cell.setCellStyle(dataStyle);
				colIndex++;
			}
			rowIndex++;
		}
		return rowIndex;
	}

	private static void autoSizeColumns(Sheet sheet, int columnNumber) {
		for (int i = 0; i < columnNumber; i++) {
			int orgWidth = sheet.getColumnWidth(i);
			sheet.autoSizeColumn(i, true);
			int newWidth = (int) (sheet.getColumnWidth(i) + 100);
			if (newWidth > orgWidth) {
				sheet.setColumnWidth(i, newWidth);
			} else {
				sheet.setColumnWidth(i, orgWidth);
			}
		}
	}

	private static void setBorder(XSSFCellStyle style, BorderStyle border, XSSFColor color) {
		style.setBorderTop(border);
		style.setBorderLeft(border);
		style.setBorderRight(border);
		style.setBorderBottom(border);
		style.setBorderColor(XSSFCellBorder.BorderSide.TOP, color);
		style.setBorderColor(XSSFCellBorder.BorderSide.LEFT, color);
		style.setBorderColor(XSSFCellBorder.BorderSide.RIGHT, color);
		style.setBorderColor(XSSFCellBorder.BorderSide.BOTTOM, color);
	}
}
