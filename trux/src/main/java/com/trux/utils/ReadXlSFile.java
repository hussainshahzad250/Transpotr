package com.trux.utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadXlSFile {
	 

		public static void readXLSFile() throws IOException
		{
			InputStream ExcelFileToRead = new FileInputStream("G:/Book2.xls");
			HSSFWorkbook wb = new HSSFWorkbook(ExcelFileToRead);

			HSSFSheet sheet=wb.getSheetAt(0);
			HSSFRow row; 
			HSSFCell cell;

			Iterator rows = sheet.rowIterator();

			while (rows.hasNext())
			{
				row=(HSSFRow) rows.next();
				Iterator cells = row.cellIterator();
				
				while (cells.hasNext())
				{
					cell=(HSSFCell) cells.next();
			
					if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
					{
						System.out.print(cell.getStringCellValue()+" ");
					}
					else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
					{
						System.out.print(cell.getNumericCellValue()+" ");
					}
					else
					{
						//U Can Handel Boolean, Formula, Errors
					}
				}
				System.out.println();
			}
		
		}
		
		public static void writeXLSFile() throws IOException {
			
			String excelFileName = "G:/Book1.xls";//name of excel file

			String sheetName = "Sheet1";//name of sheet

			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet = wb.createSheet(sheetName) ;

			//iterating r number of rows
			for (int r=0;r < 5; r++ )
			{
				HSSFRow row = sheet.createRow(r);
		
				//iterating c number of columns
				for (int c=0;c < 5; c++ )
				{
					HSSFCell cell = row.createCell(c);
					
					cell.setCellValue("Cell "+r+" "+c);
				}
			}
			
			FileOutputStream fileOut = new FileOutputStream(excelFileName);
			
			//write this workbook to an Outputstream.
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
		}
		
		public static StringBuilder readXLSXFile() throws IOException
		{
			InputStream ExcelFileToRead = new FileInputStream("G:/Book2.xlsx");
			XSSFWorkbook  wb = new XSSFWorkbook(ExcelFileToRead);
			
			XSSFWorkbook test = new XSSFWorkbook(); 
			
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row; 
			XSSFCell cell;

			Iterator rows = sheet.rowIterator();
            StringBuilder files=new StringBuilder();
			while (rows.hasNext())
			{ try{
				row=(XSSFRow) rows.next();
				Iterator cells = row.cellIterator();
				while (cells.hasNext())
				{
					cell=(XSSFCell) cells.next();
			
					if (cell.getCellType() == XSSFCell.CELL_TYPE_STRING)
					{ String toAddress=cell.getStringCellValue();
					if(toAddress!=null){
						
						double total=	GoogleMapsUtils.getDistance("Village Mandoli New Delhi, Delhi 110093",toAddress);
						
						files.append(" From : Village Mandoli New Delhi, Delhi 110093.   To :"+toAddress+ ", Total Distance: " +total+"\n");
						System.out.print(" From : Village Mandoli New Delhi, Delhi 110093.   To :"+toAddress+ ", Total Distance: " +total);
						
					}
						 
					}
					else if(cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC)
					{
						System.out.print(cell.getNumericCellValue()+" ");
					}
					else
					{
						//U Can Handel Boolean, Formula, Errors
					}
				}
			}catch(Exception er){er.printStackTrace();}
				System.out.println();
			}
		return files;
		}
		
		public static void writeXLSXFile() throws IOException {
			
			String excelFileName = "G:/Book1.xls";//name of excel file

			String sheetName = "Sheet1";//name of sheet

			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet(sheetName) ;

			//iterating r number of rows
			for (int r=0;r < 5; r++ )
			{
				XSSFRow row = sheet.createRow(r);

				//iterating c number of columns
				for (int c=0;c < 5; c++ )
				{
					XSSFCell cell = row.createCell(c);
		
					cell.setCellValue("Cell "+r+" "+c);
				}
			}

			FileOutputStream fileOut = new FileOutputStream(excelFileName);

			//write this workbook to an Outputstream.
			wb.write(fileOut);
			fileOut.flush();
			fileOut.close();
		}

		public static void main(String[] args) throws IOException {
			
			//writeXLSFile();
			//readXLSFile();
			
			//writeXLSXFile();
		//	readXLSXFile();
			WrightFile(readXLSXFile());

		}

	 
		public static void WrightFile(StringBuilder fileCintantas) {
			try {

				 
				File file = new File("G:/AddressDistant2.txt");

				 
				if (!file.exists()) {
					file.createNewFile();
				}

				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(fileCintantas.toString());
				bw.close();

				System.out.println("Done");

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
