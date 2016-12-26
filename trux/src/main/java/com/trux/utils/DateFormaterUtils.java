package com.trux.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormaterUtils {

	@SuppressWarnings("deprecation")
	public static String dateFormate(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date dates = new Date(date);
		String fromDate = format.format(convertGMTToIST(dates));
		return fromDate + " 00:00:00";
	}
	@SuppressWarnings("deprecation")
	public static String dateFormates(String date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date dates = new Date(date);
		String fromDate = format.format(convertGMTToIST(dates));
		return fromDate+ " 00:00:00";
	}
	 
	public static String dateFormateWithDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		Date dates = date;
		String fromDate = format.format(convertGMTToIST(dates));
		return fromDate + " 00:00";
	}

	// get day date month year time
	public static String getDayDateTime(String date) {
		Date dates;

		try {
			dates = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy").parse(date + " ");
			SimpleDateFormat format = new SimpleDateFormat("EEE dd MMM,yyyy HH:mm:ss.S a");
			String fromDate = format.format(convertGMTToIST(dates));
			//System.out.println(fromDate);
			return fromDate;

		} catch (ParseException e) {

			e.printStackTrace();
			return "";
		}
	}

	public static String convertGMTToISTWithDate(String date) {
		Date dates;
		
		try {
			dates = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy").parse(date + " ");
			SimpleDateFormat format = new SimpleDateFormat("EEE dd MMM, yyyy HH:mm:ss.S a");
			String fromDate = format.format(dates);
			//System.out.println(fromDate);
			return fromDate;

		} catch (ParseException e) {

			e.printStackTrace();
			return "";
		}
	}
	public static String convertGMTToISTWithDates(String date) {
		Date dates;
		
		try {
			dates = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy").parse(date + " ");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S ");
			String fromDate = format.format(dates);
			//System.out.println(fromDate);
			return fromDate;

		} catch (ParseException e) {

			e.printStackTrace();
			return "";
		}
	}
	
	public static String convertGMTToISTWithDatesWithSlash(String date) {
		Date dates;
		
		try {
			dates = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy").parse(date + " ");
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.S ");
			String fromDate = format.format(dates);
			//System.out.println(fromDate);
			return fromDate;

		} catch (ParseException e) {

			e.printStackTrace();
			return "";
		}
	}
	
	public static String convertGMTToISTWithDatesWithAttandace(String date) {
		Date dates;
		
		try {
			dates = new SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy").parse(date.replace(".0", "") + " ");
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.S ");
			String fromDate = format.format(dates);
			 
			return fromDate;

		} catch (ParseException e) {
 
			return "";
		}
	}


	public static Date convertGMTToIST(Date date) {
		return new Date(date.getTime());
		//return new Date(date.getTime() + 11 * 1800 * 1000);
	}

	public static String splitDateIntoTime(String date) {
		Date dates;

		try {
			dates = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S ").parse(date+ " ");
			SimpleDateFormat format = new SimpleDateFormat("dd MMM, yyyy HH:mm:ss.S a ");
			String fromDate = format.format(convertGMTToIST(dates));
			String timeval = fromDate.substring(0, 12);
			//System.out.println(fromDate);
			return timeval;

		} catch (ParseException e) {

			e.printStackTrace();
		}

		return "";
	}

	public static String getDateTime(String date) {
		Date dates;

		try {
			dates = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S ").parse(date
					+ " ");
			SimpleDateFormat format = new SimpleDateFormat("dd MMM, yyyy HH:mm:ss.S a ");
			//String fromDate = format.format(convertGMTToIST(dates));
			//System.out.println(fromDate);
			 
			String fromDate = format.format(convertGMTToIST(dates));
			return fromDate;

		} catch (ParseException e) {

			e.printStackTrace();
		}

		return "";
	}

	public static String splitTimeIntoDate(String date) {
		Date dates;

		try {
			dates = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(date+ " ");
			SimpleDateFormat format = new SimpleDateFormat(
					"dd MMM, yyyy HH:mm:ss.S a");
			String fromDate = format.format(convertGMTToIST(dates));
			String timeval = fromDate.substring(12, fromDate.length());
			//System.out.println(fromDate);
			return timeval;

		} catch (ParseException e) {

			e.printStackTrace();
		}

		return "";
	}

	public static Date dateFormateInEdit(String date) {
		SimpleDateFormat formats =null;
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date datesss;
		String fromDates = "";
		try {
			if(date.length()==26){
				date = date.replace(".0 PM", "").replace(".0 AM", "");
				formats = new SimpleDateFormat("dd MMM, yyyy HH:mm:ss");
				datesss = formats.parse(date + " ");
				}else{
				formats = new SimpleDateFormat("yyyy/mm/dd HH:mm");
				date=date+":00";
				datesss = formats.parse(date + " ");
				}			
			fromDates = fmt.format(datesss);
			//System.out.println(fromDates);
			//Date datess=new Date(fromDates);
					
			//System.out.println(fromDates  +"   "+datesss.toString());
			return datesss;
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return null;
	}
	
	
	public static String dateFormateInEdits(String date) {
		SimpleDateFormat formats =null;
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date datesss;
		String fromDates = "";
		try {
			 
				formats = new SimpleDateFormat("yyyy-MM-dd");
				 
				datesss = fmt.parse(date + " ");
							
			fromDates = formats.format(datesss);
			//System.out.println(fromDates);
			//Date datess=new Date(fromDates);
					
			//System.out.println(fromDates  +"   "+datesss.toString());
			return fromDates;
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public static String dateFormateToUpdate(String date) {
		SimpleDateFormat formats =null;
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date datesss;
		String fromDates = "";
		try {
			 
				formats = new SimpleDateFormat("yyyy/MM/dd");
				 
				datesss = fmt.parse(date + " ");
							
			fromDates = formats.format(datesss);
			//System.out.println(fromDates);
			//Date datess=new Date(fromDates);
					
			//System.out.println(fromDates  +"   "+datesss.toString());
			return fromDates;
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
	//System.out.println(dateFormateInEdit("2015/06/17 18:19"));
	String dd=	"Wed Jul 08 13:06:07 IST 2015";
	//"24 Jul, 2015 23:30:00.0 PM :00 ";
	SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
	try {
	System.out.println(dateFormateInEdits("2016-01-15 19:35:00"));
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	//2015-05-03 01:59:58.0
	//System.out.println(convertGMTToISTWithDates(new Date().toString()));
	//System.out.println(getDateTime("2015-06-10 12:21:50.0 "));
	//2015-01-17 06:19:00
		/*
		 * System.out.println(11*1800*1000); Format formatter = new
		 * SimpleDateFormat("EEEE"); String s = formatter.format(new
		 * Date("2015-06-01 23:00:00.0")); System.out.println(s);
		 * System.out.println(getDateTime("2015-06-01 23:00:00.0"));
		 */
		// String str_date = "Fri Jul 06 10:30:00 IST 2012";

		// SimpleDateFormat fmt = new
		// SimpleDateFormat("dd MMM, yyy hh:mm:ss Z ");
		// SimpleDateFormat fmt = new
		// SimpleDateFormat("dd MMM, yyy hh:mm:ss Z ");
		// e1.printStackTrace();
		// }
		// SimpleDateFormat fmt = new
		// SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy");
		// Date dates;

		/*
		 * try { dates = new
		 * SimpleDateFormat("EEE MMM d HH:mm:ss z yyyy").parse(str_date+" ");
		 * SimpleDateFormat format=new
		 * SimpleDateFormat("EEE dd MMM, yyyy HH:mm:ss.S a"); String fromDate =
		 * format.format(dates); System.out.println(fromDate);
		 * 
		 * 
		 * } catch (ParseException e) {
		 * 
		 * e.printStackTrace();
		 */
		}

}
