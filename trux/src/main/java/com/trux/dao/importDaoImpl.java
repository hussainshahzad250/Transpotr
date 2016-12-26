/*package com.trux.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.trux.model.FileBean;
@Repository
public class importDaoImpl implements importDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	public void save(FileBean fileBean)  {
		Session session =sessionFactory.getCurrentSession();
		Transaction tx = null;
		
		

		try{
			String saveDirectory = "/home/trux/shahzad/saveDirectory/";
			MultipartFile crunchifyFiles = fileBean.getFileData();
			String fileName = crunchifyFiles.getOriginalFilename();
			System.out.println(saveDirectory + fileName);
			FileInputStream input = new FileInputStream(saveDirectory + fileName);
			
			XSSFWorkbook wb = new XSSFWorkbook(input);
			XSSFSheet sheet = wb.getSheetAt(0);
			Row row;
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
				int id = (int) row.getCell(0).getNumericCellValue();
				String firstname = row.getCell(1).getStringCellValue();
				String address = row.getCell(2).getStringCellValue();
				String sql = "INSERT INTO excellsheet VALUES('" + id + "','" + firstname + "','" + address + "')";
				
				System.out.println("Import rows " + i);
			}
			
			input.close();
			System.out.println("Your  Excel File is Successfully Imported to MySQL");
		}catch(Exception e){
			
		}
		
	}

}
*/