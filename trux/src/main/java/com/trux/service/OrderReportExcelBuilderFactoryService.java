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

public class OrderReportExcelBuilderFactoryService extends AbstractExcelView {

	@SuppressWarnings("unchecked")
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {

		List<CustomerBookingDetails> customerBookingDetailsList = (List<CustomerBookingDetails>) model.get("customerBookingDetailsList");
		// create a new Excel sheet
		HSSFSheet sheet = workbook.createSheet("Order Reports");
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

		header.createCell(0).setCellValue("S. No");
		header.getCell(0).setCellStyle(style);

		header.createCell(1).setCellValue("Order Id");
		header.getCell(1).setCellStyle(style);

		header.createCell(2).setCellValue("Customer Name");
		header.getCell(2).setCellStyle(style);

		header.createCell(3).setCellValue("Order date");
		header.getCell(3).setCellStyle(style);

		header.createCell(4).setCellValue("Pickup date");
		header.getCell(4).setCellStyle(style);

		header.createCell(5).setCellValue("Pickup time");
		header.getCell(5).setCellStyle(style);

		header.createCell(6).setCellValue("Pickup Point");
		header.getCell(6).setCellStyle(style);

		header.createCell(7).setCellValue("Drop Point");
		header.getCell(7).setCellStyle(style);

		header.createCell(8).setCellValue("Trip end date ");
		header.getCell(8).setCellStyle(style);

		header.createCell(9).setCellValue("Trip end time ");
		header.getCell(9).setCellStyle(style);

		header.createCell(10).setCellValue("Vehicle Type");
		header.getCell(10).setCellStyle(style);

		header.createCell(11).setCellValue("Vehicle No");
		header.getCell(11).setCellStyle(style);

		header.createCell(12).setCellValue("Driver Name");
		header.getCell(12).setCellStyle(style);

		header.createCell(13).setCellValue("Driver	Number");
		header.getCell(13).setCellStyle(style);

		header.createCell(14).setCellValue("Current Status");
		header.getCell(14).setCellStyle(style);

		header.createCell(15).setCellValue("Freight");
		header.getCell(15).setCellStyle(style);

		int rowCount = 1;

		for (CustomerBookingDetails cbdDto : customerBookingDetailsList) {
			HSSFRow aRow = sheet.createRow(rowCount++);
			aRow.createCell(0).setCellValue(cbdDto.getSerialNo());
			if (cbdDto.getBookingId() != null) {
				aRow.createCell(1).setCellValue(cbdDto.getBookingId());
			} else {
				aRow.createCell(1).setCellValue("");
			}
			if (cbdDto.getCustomerName() != null) {
				aRow.createCell(2).setCellValue(cbdDto.getCustomerName());
			} else {
				aRow.createCell(2).setCellValue("");
			}
			if (cbdDto.getBooking_time() != null) {
				aRow.createCell(3).setCellValue(cbdDto.getBooking_time());
			} else {
				aRow.createCell(3).setCellValue("");
			}
			if (cbdDto.getDates() != null) {
				aRow.createCell(4).setCellValue(cbdDto.getDates());
			} else {
				aRow.createCell(4).setCellValue("");
			}
			if (cbdDto.getTimes() != null) {
				aRow.createCell(5).setCellValue(cbdDto.getTimes());
			} else {
				aRow.createCell(5).setCellValue("");
			}
			if (cbdDto.getFromLocation() != null) {
				aRow.createCell(6).setCellValue(cbdDto.getFromLocation());
			} else {
				aRow.createCell(6).setCellValue("");
			}
			if (cbdDto.getToLocation() != null) {
				aRow.createCell(7).setCellValue(cbdDto.getToLocation());
			} else {
				aRow.createCell(7).setCellValue("");
			}
			if (cbdDto.getTripEndDates() != null) {
				aRow.createCell(8).setCellValue(cbdDto.getTripEndDates());
			} else {
				aRow.createCell(8).setCellValue("");
			}
			if (cbdDto.getTripEndTimes() != null) {
				aRow.createCell(9).setCellValue(cbdDto.getTripEndTimes());
			} else {
				aRow.createCell(9).setCellValue("");
			}
			if (cbdDto.getVehicleType() != null) {
				aRow.createCell(10).setCellValue(cbdDto.getVehicleType());
			} else {
				aRow.createCell(10).setCellValue("");
			}
			if (cbdDto.getVehicleNumber() != null) {
				aRow.createCell(11).setCellValue(cbdDto.getVehicleNumber());
			} else {
				aRow.createCell(11).setCellValue("");
			}
			if (cbdDto.getDriverName() != null) {
				aRow.createCell(12).setCellValue(cbdDto.getDriverName());
			} else {
				aRow.createCell(12).setCellValue("");
			}
			if (cbdDto.getDriverPhonenumber() != null) {
				aRow.createCell(13).setCellValue(cbdDto.getDriverPhonenumber());
			} else {
				aRow.createCell(13).setCellValue("");
			}
			if (cbdDto.getBookingStatus() != null) {
				aRow.createCell(14).setCellValue(cbdDto.getBookingStatus());
			} else {
				aRow.createCell(14).setCellValue("");
			}
			if (cbdDto.getTotalFare() != null) {
				aRow.createCell(15).setCellValue(cbdDto.getTotalFare());
			} else {
				aRow.createCell(15).setCellValue("");
			}

		}
	}
}
