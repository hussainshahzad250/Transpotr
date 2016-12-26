package com.trux.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class JulianDayHoursMinuteSecondCount {
	public static void main(String[] args) {
		//System.out.println((12748/12)/12);
		String fromDate = "2015-07-07 10:08:58.0";
		String toDate = "2015-07-07 10:60:43.0";		 
	System.out.println(diffBetweenTwoDate(fromDate, toDate));
	}
	public static Date convertGMTToIST(Date date) {
		return new Date(date.getTime()+11*1800*1000);
	}
	
	public static String diffBetweenTwoDate(String fromDate, String toDate) {
	try{String dateStart = fromDate;// "2015-05-03 01:59:58.0";
		String dateStop = toDate;// "2015-05-05 12:00:43.0";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S ");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(dateStart + " ");
			d2 = format.parse(dateStop + " ");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Map<TimeUnit, Long> result = computeDiff(convertGMTToIST(d1),convertGMTToIST(d2));
 		String difDayTime =  result.get(TimeUnit.DAYS) + " days / "
				+ result.get(TimeUnit.HOURS) + ":"
				+ result.get(TimeUnit.MINUTES) + ":"
				+ result.get(TimeUnit.SECONDS);
		return difDayTime;}catch(Exception er){er.printStackTrace(); return "00:00:00";}	
	}
	public static String diffBetweenTwoDateGetTime(String fromDate, String toDate) {
		try{String dateStart = fromDate;// "2015-05-03 01:59:58.0";
		String dateStop = toDate;// "2015-05-05 12:00:43.0";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S ");
		Date d1 = null;
		Date d2 = null;
		try {
			d1 = format.parse(dateStart + " ");
			d2 = format.parse(dateStop + " ");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Map<TimeUnit, Long> result = computeDiff(convertGMTToIST(d1),convertGMTToIST(d2));
 		String difDayTime =result.get(TimeUnit.DAYS) + " "+ result.get(TimeUnit.HOURS) + ":"
				+ result.get(TimeUnit.MINUTES) + ":"
				+ result.get(TimeUnit.SECONDS);
		return difDayTime;}catch(Exception er){er.printStackTrace(); return "00:00:00";}
	}
	public static Map<TimeUnit, Long> computeDiff(Date date1, Date date2) {
	try{long diffInMillies = date2.getTime() - date1.getTime();
		List<TimeUnit> units = new ArrayList<TimeUnit>(
				EnumSet.allOf(TimeUnit.class));
		Collections.reverse(units);

		Map<TimeUnit, Long> result = new LinkedHashMap<TimeUnit, Long>();
		long milliesRest = diffInMillies;
		for (TimeUnit unit : units) {
			long diff = unit.convert(milliesRest, TimeUnit.MILLISECONDS);
			long diffInMilliesForUnit = unit.toMillis(diff);
			milliesRest = milliesRest - diffInMilliesForUnit;
			result.put(unit, diff);
		}
		return result;}catch(Exception er){er.printStackTrace(); return null;}	
	}
}
