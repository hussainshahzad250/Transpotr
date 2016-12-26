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

import com.trux.model.ConsumerRegistration;
import com.trux.utils.TruxUtils;

public class ConsumerExcelBuilderFactoryService extends AbstractExcelView {
	@SuppressWarnings("unchecked")
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {

		List<ConsumerRegistration> consumerRegistrationList = (List<ConsumerRegistration>) model.get("consumerList");
		// create a new Excel sheet
		HSSFSheet sheet = workbook.createSheet("Consumer Reports");
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

		header.createCell(0).setCellValue("CID");
		header.getCell(0).setCellStyle(style);

		header.createCell(1).setCellValue("Name");
		header.getCell(1).setCellStyle(style);

		header.createCell(2).setCellValue("E-Mail");
		header.getCell(2).setCellStyle(style);

		header.createCell(3).setCellValue("Phone");
		header.getCell(3).setCellStyle(style);
	
		header.createCell(4).setCellValue("User Type");
		header.getCell(4).setCellStyle(style);

		header.createCell(5).setCellValue("Created Date");
		header.getCell(5).setCellStyle(style);

		header.createCell(6).setCellValue("Updated Date");
		header.getCell(6).setCellStyle(style);
 
		int rowCount = 1;
		 	for (ConsumerRegistration cbdDto : consumerRegistrationList) {
		 		cbdDto.setCreatedDate(TruxUtils.getChangedTimezoneDate(cbdDto.getCreatedDate()));
		 		cbdDto.setUpdatedDate(TruxUtils.getChangedTimezoneDate(cbdDto.getUpdatedDate()));
			HSSFRow aRow = sheet.createRow(rowCount++);
			if (cbdDto.getId() != null) {
				aRow.createCell(0).setCellValue(cbdDto.getId());
			} else {
				aRow.createCell(0).setCellValue("");
			}
			if (cbdDto.getUserFistLastName() != null) {
				aRow.createCell(1).setCellValue(cbdDto.getUserFistLastName());
			} else {
				aRow.createCell(1).setCellValue("");
			}
			if (cbdDto.getEmail() != null) {
				aRow.createCell(2).setCellValue(cbdDto.getEmail());
			} else {
				aRow.createCell(2).setCellValue("");
			}
			if (cbdDto.getPhoneNumber() != null) {
				aRow.createCell(3).setCellValue(cbdDto.getPhoneNumber());
			} else {
				aRow.createCell(3).setCellValue("");
			}
			if (cbdDto.getUserTypes() != null) {
				aRow.createCell(4).setCellValue(cbdDto.getUserTypes());
			} else {
				aRow.createCell(4).setCellValue("");
			}
			if (cbdDto.getCreateConsumerDate()!= null) {
				aRow.createCell(5).setCellValue(cbdDto.getCreateConsumerDate());
			} else {
				aRow.createCell(5).setCellValue("");
			}
			
			if (cbdDto.getUpdatedConsumerDate()!= null) {
				aRow.createCell(6).setCellValue(cbdDto.getUpdatedConsumerDate());
			} else {
				aRow.createCell(6).setCellValue("");
			} 
			 

		}
	}
}