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

import com.trux.model.DriverDeviceVehicleMapping;

public class VehicleLoginReportExcelBuilderFactoryService extends AbstractExcelView {
	@SuppressWarnings("unchecked")
	protected void buildExcelDocument(Map<String, Object> model,HSSFWorkbook workbook, HttpServletRequest request,HttpServletResponse response) throws Exception {

		List<DriverDeviceVehicleMapping> driverDeviceVehicleMappingList = (List<DriverDeviceVehicleMapping>) model.get("driverDeviceVehicleMappingList");
		// create a new Excel sheet
		HSSFSheet sheet = workbook.createSheet("LggedIn LoggedOut Reports");
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

		header.createCell(0).setCellValue("Driver Name");
		header.getCell(0).setCellStyle(style);

		header.createCell(1).setCellValue("Mobile no");
		header.getCell(1).setCellStyle(style);

		header.createCell(2).setCellValue("Vehicle type");
		header.getCell(2).setCellStyle(style);

		header.createCell(3).setCellValue("Status");
		header.getCell(3).setCellStyle(style);
	
		header.createCell(4).setCellValue("Last login Date");
		header.getCell(4).setCellStyle(style);

		header.createCell(5).setCellValue("Last login time");
		header.getCell(5).setCellStyle(style);

		header.createCell(6).setCellValue("Logout Date");
		header.getCell(6).setCellStyle(style);

		header.createCell(7).setCellValue("Logout time");
		header.getCell(7).setCellStyle(style);
		
		header.createCell(8).setCellValue("Login Days Hours:Minuts:Seconds");
		header.getCell(8).setCellStyle(style);
		
		int rowCount = 1;

		for (DriverDeviceVehicleMapping cbdDto : driverDeviceVehicleMappingList) {
			HSSFRow aRow = sheet.createRow(rowCount++);
			if (cbdDto.getDriverName() != null) {
				aRow.createCell(0).setCellValue(cbdDto.getDriverName());
			} else {
				aRow.createCell(0).setCellValue("");
			}
			if (cbdDto.getDriverPhoneNumber() != null) {
				aRow.createCell(1).setCellValue(cbdDto.getDriverPhoneNumber());
			} else {
				aRow.createCell(1).setCellValue("");
			}
			if (cbdDto.getVehicleType() != null) {
				aRow.createCell(2).setCellValue(cbdDto.getVehicleType());
			} else {
				aRow.createCell(2).setCellValue("");
			}
			if (cbdDto.getDriverMessage() != null) {
				aRow.createCell(3).setCellValue(cbdDto.getDriverMessage());
			} else {
				aRow.createCell(3).setCellValue("");
			}
			if (cbdDto.getDriverLoginDate() != null) {
				aRow.createCell(4).setCellValue(cbdDto.getDriverLoginDate());
			} else {
				aRow.createCell(4).setCellValue("");
			}
			if (cbdDto.getDriverLoginTime() != null) {
				aRow.createCell(5).setCellValue(cbdDto.getDriverLoginTime());
			} else {
				aRow.createCell(5).setCellValue("");
			}
			
			if (cbdDto.getDriverLogoutDate() != null) {
				aRow.createCell(6).setCellValue(cbdDto.getDriverLogoutDate());
			} else {
				aRow.createCell(6).setCellValue("");
			}
			if (cbdDto.getDriverLogoutTime() != null) {
				aRow.createCell(7).setCellValue(cbdDto.getDriverLogoutTime());
			} else {
				aRow.createCell(7).setCellValue("");
			}
			if (cbdDto.getDriverLoginDurationTime() != null) {
				aRow.createCell(8).setCellValue(cbdDto.getDriverLoginDurationTime());
			} else {
				aRow.createCell(8).setCellValue("");
			}
			 

		}
	}
}