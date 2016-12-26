package com.trux.service;

import java.util.ArrayList;
import java.util.Date;
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
import com.trux.utils.DateFormaterUtils;

public class BookingReportExcelBuilderFactoryService extends AbstractExcelView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {

		
		List<CustomerBookingDetails> customerBookingDetailsList = new ArrayList<CustomerBookingDetails>();
		 customerBookingDetailsList =(List<CustomerBookingDetails> )model.get("customerBookingDetailsList");
		 
		// create a new Excel sheet
		HSSFSheet sheet = workbook.createSheet("Booking Reports");
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
		 
		header.createCell(0).setCellValue("Booking Id");
		header.getCell(0).setCellStyle(style);

		header.createCell(1).setCellValue("Customer Name");
		header.getCell(1).setCellStyle(style);

		header.createCell(2).setCellValue("Phone Number");
		header.getCell(2).setCellStyle(style);
		
		header.createCell(3).setCellValue("From Location");
		header.getCell(3).setCellStyle(style);
		
		header.createCell(4).setCellValue("To Location");
		header.getCell(4).setCellStyle(style);	
		
		header.createCell(5).setCellValue("Fares");
		header.getCell(5).setCellStyle(style);	
		
		header.createCell(6).setCellValue("Booking Status");
		header.getCell(6).setCellStyle(style);	
		
		header.createCell(7).setCellValue("Loading Time");
		header.getCell(7).setCellStyle(style);
		
		header.createCell(8).setCellValue("Vehicle Type");
		header.getCell(8).setCellStyle(style);
		
		header.createCell(9).setCellValue("Available Driver Details");
		header.getCell(9).setCellStyle(style);
		int rowCount = 1;
		 
		for (CustomerBookingDetails cbdDto : customerBookingDetailsList) {
			HSSFRow aRow = sheet.createRow(rowCount++);
			 if (cbdDto.getBookingId() != null) {
				aRow.createCell(0).setCellValue(cbdDto.getBookingId());
			} else {
				aRow.createCell(0).setCellValue("");
			}
			if (cbdDto.getCustomerName() != null) {
				aRow.createCell(1).setCellValue(cbdDto.getCustomerName());
			} else {
				aRow.createCell(1).setCellValue("");
			}
			if (cbdDto.getCustmerPhonenumber() != null) {
				aRow.createCell(2).setCellValue(cbdDto.getCustmerPhonenumber());
			} else {
				aRow.createCell(2).setCellValue("");
			}
			if (cbdDto.getFromLocation() != null) {
				aRow.createCell(3).setCellValue(cbdDto.getFromLocation());
			} else {
				aRow.createCell(3).setCellValue("");
			}
			
			if (cbdDto.getToLocation() != null) {
				aRow.createCell(4).setCellValue(cbdDto.getToLocation());
			} else {
				aRow.createCell(4).setCellValue("");
			}
			if (cbdDto.getExpectedFare() != null) {
				aRow.createCell(5).setCellValue(cbdDto.getExpectedFare());
			} else {
				aRow.createCell(5).setCellValue("");
			}
			if (cbdDto.getBookingStatus() != null) {
				aRow.createCell(6).setCellValue(cbdDto.getBookingStatus());
			} else {
				aRow.createCell(6).setCellValue("");
			}
			if (cbdDto.getRideTime() != null) {
				String date=DateFormaterUtils.convertGMTToISTWithDate(new Date(cbdDto.getRideTime().getTime()+11*1800*1000).toString());
				aRow.createCell(7).setCellValue(date);
			} else {
				aRow.createCell(7).setCellValue("");
			}
			if (cbdDto.getVehicleType() != null) {
				aRow.createCell(8).setCellValue(cbdDto.getVehicleType());
			} else {
				aRow.createCell(8).setCellValue("");
			}
			if (cbdDto.getDriverName()!= null && cbdDto.getDriverPhonenumber()!=null) {
				aRow.createCell(9).setCellValue(cbdDto.getDriverName() +" ,"+ cbdDto.getDriverPhonenumber() +", "+cbdDto.getVehicleType());
			} else {
				aRow.createCell(9).setCellValue("");
			}
		}

	}

}
