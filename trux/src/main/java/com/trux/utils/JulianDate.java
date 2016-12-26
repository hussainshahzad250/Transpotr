package com.trux.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
 
/**
 * 
 * @author Mithlesh Kumar
 * 
 */
public class JulianDate
{
	
	public static int JGREG = 15 + 31 * (10 + 12 * 1582);
	public static double HALFSECOND = 0.5;
	
	public static double toJulian(Integer[] ymd)
	{ try{
		int year = ymd[0];
		int month = ymd[1]; // jan=1, feb=2,...
		int day = ymd[2];
		int julianYear = year;
		if (year < 0)
			julianYear++;
		int julianMonth = month;
		if (month > 2)
		{
			julianMonth++;
		}
		else
		{
			julianYear--;
			julianMonth += 13;
		}
		
		double julian = (java.lang.Math.floor(365.25 * julianYear) + java.lang.Math.floor(30.6001 * julianMonth) + day + 1720995.0);
		if (day + 31 * (month + 12 * year) >= JGREG)
		{
			// change over to Gregorian calendar
			int ja = (int)(0.01 * julianYear);
			julian += 2 - ja + (0.25 * ja);
		}
		return java.lang.Math.floor(julian);}catch(Exception er){er.printStackTrace(); return 0;}
	}
	
	
	public static boolean ValidateJulianDate(String fromDates, String toDates)
	{ try{

		String fromDate = formatStringToMMDate(fromDates);
		String toDate = formatStringToMMDate(toDates);
		int fromMonth = Integer.parseInt(fromDate.substring(0, 2));
		int fromDay = Integer.parseInt(fromDate.substring(3, 5));
		int fromYear = Integer.parseInt(fromDate.substring(6, 10));
		

		int toMonth = Integer.parseInt(toDate.substring(0, 2));
		int toDay = Integer.parseInt(toDate.substring(3, 5));
		int toYear = Integer.parseInt(toDate.substring(6, 10));
		
		boolean flag = true;
		
		double fromdate = toJulian(new Integer[] {1900, 01, 01});
		double testFrom = toJulian(new Integer[] {fromYear, fromMonth, fromDay});
		double todate = toJulian(new Integer[] {1900, 01, 01});
		double testTo = toJulian(new Integer[] {toYear, toMonth, toDay});
		////System.out.println(fromdate + "   ==" + todate);
		double fromVal = (fromdate - testFrom);
		double toVal = (todate - testTo);
		String fromday = "" + fromVal;
		String toDays = "" + toVal;
		double from = Double.parseDouble(fromday.substring(1));
		double to = Double.parseDouble(toDays.substring(1));
		//System.out.println("From day " + from);
		//System.out.println("to day " + to);
		
		if (to < from)
		{
			flag = false;
			
		}
		
		return flag;
	}catch(Exception er){er.printStackTrace(); return false;}
	}
	
	public static String formatStringToMMDate(String dateString)
	{
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S a");
		java.text.SimpleDateFormat dateFormat1 = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S a");
		String sqlDate = null;
		try
		{
			
			Date date = dateFormat.parse(dateString+" ");
			sqlDate = dateFormat1.format(date);// dateString+":00.0";
		}
		catch (Exception e)
		{
			sqlDate = "";
		}
		return sqlDate;
	}
	
	public static void main(String args[])
	{
		
		String dateStart = "2015-05-03 01:59:58.0";
		String dateStop = "2015-05-05 12:00:43.0";

		// Custom date format
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S ");  

		Date d1 = null;
		Date d2 = null;
		try {
		    d1 = format.parse(dateStart+" ");
		    d2 = format.parse(dateStop+" ");
		} catch (ParseException e) {
		    e.printStackTrace();
		}    

		// Get msec from each, and subtract. 
		long diff = d2.getTime() - d1.getTime();
		
	//System.out.println("H:"+getDateDiff(d1,d2,TimeUnit.HOURS));
	//System.out.println("M"+getDateDiff(d1,d2,TimeUnit.MINUTES));
	//System.out.println("S"+getDateDiff(d1,d2,TimeUnit.SECONDS));
		long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);
		long hours = TimeUnit.MILLISECONDS.toHours(diff);
		  
		    		
		long diffSeconds = diff / 1000;  
		
		long diffMinutes = diff / (60 * 1000);         
		long diffHours = diff / (60 * 60 * 1000); 
		StringBuilder time=new StringBuilder();
		if(hours==0){
			time.append("00:");
		}else{
			time.append(hours+" Hours :");
		}
		if(minutes==0){
			time.append("00:");
		}else{
			time.append(minutes+" Minuts :");
			}
		if(seconds==0){
			time.append("00");
		}else{
			time.append(seconds+" Secounds");
		}
		//System.out.println("Time now :"+time.toString());
		//System.out.println("Time in seconds: " + diffSeconds + " seconds.");         
		//System.out.println("Time in minutes: " + diffMinutes + " minutes.");         
		//System.out.println("Time in hours: " + diffHours + " hours."); 

		/*DateTime date,dte;
		
		long diff=(new Date("2015-06-01 01:00:00").getTime()-new Date("2015-06-01 23:00:00").getTime())/(60*60 * 1000);
		//System.out.println("diff "+diff);*/
		/*Period p = new Period(new Date("2015-06-01 01:00:00.0"), new Date("2015-06-01 23:00:00.0"));
		long hours = p.getHours();
		long minutes = p.getMinutes();

		String format = String.format("%%0%dd", 2);

		return Long.toString(hours)+":"+String.format(format, minutes);*/
		////System.out.println(ValidateJulianDate("2015-06-01 01:00:00.0", "2015-06-01 23:00:00.0"));
	}
	
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
}