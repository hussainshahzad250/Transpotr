package com.trux.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.trux.model.CustomerBookingDetails;

public class RevenueExcelBuilderFactoryService extends AbstractExcelView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {

		List<CustomerBookingDetails> customerBookingDetailsList = (List<CustomerBookingDetails>) model.get("customerBookingRevenueDetailsList");

		// create a new Excel sheet
		HSSFSheet sheet = workbook.createSheet("Revenue Reports");
		sheet.setDefaultColumnWidth(30);

		// create style for header cells
		CellStyle style = workbook.createCellStyle();
		Font font = workbook.createFont();
		font.setFontName("Arial");
		style.setFillForegroundColor(HSSFColor.BLUE.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);

		// create header row
		HSSFRow header = sheet.createRow(0);

		header.createCell(0).setCellValue("Date");
		header.getCell(0).setCellStyle(style);

		header.createCell(1).setCellValue("Number of Order");
		header.getCell(1).setCellStyle(style);

		header.createCell(2).setCellValue("Gross freight");
		header.getCell(2).setCellStyle(style);

		header.createCell(3).setCellValue("Commission Revenue");
		header.getCell(3).setCellStyle(style);

		int rowCount = 1;

		for (CustomerBookingDetails cbdDto : customerBookingDetailsList) {
			HSSFRow aRow = sheet.createRow(rowCount++);
			if (cbdDto.getDates() != null) {
				aRow.createCell(0).setCellValue(cbdDto.getDates());
			} else {
				aRow.createCell(0).setCellValue("");
			}
			if (cbdDto.getState() != null) {
				aRow.createCell(1).setCellValue(cbdDto.getState());
			} else {
				aRow.createCell(1).setCellValue("");
			}
			if (cbdDto.getTotalFares() != null) {
				aRow.createCell(2).setCellValue(cbdDto.getTotalFares());
			} else {
				aRow.createCell(2).setCellValue("");
			}
			if (cbdDto.getRevenue() != 0) {
				aRow.createCell(3).setCellValue(cbdDto.getRevenue());
			} else {
				aRow.createCell(3).setCellValue(cbdDto.getRevenue());
			}
		}

	}

}
