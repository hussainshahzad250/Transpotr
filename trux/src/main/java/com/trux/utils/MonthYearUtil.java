package com.trux.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MonthYearUtil {

	public static void main(String[] args) {

		System.out.println(getYear());
		System.out.println(getMonth());

	}

	public static List<Integer> getYear() {
		List<Integer> listOfYear = new ArrayList<Integer>();
		Calendar cc = Calendar.getInstance();
		int year = cc.getWeekYear();
		for (int y = year; y > (year - 12); y--) {
			listOfYear.add(y);
		}
		return listOfYear;
	}

	public static List<String> getMonth() {
		String[] month = new String[] { "Jan", "Feb", "Mar", "Apr", "May",
				"June", "July", "Aug", "Sept", "Oct", "Nov", "Dec" };
		List<String> monthList = new ArrayList<String>();
		for (int i = 0; i < month.length; i++) {
			monthList.add(month[i]);
		}
		return monthList;
	}
	
	//public static List<String> vehicleList(){
		
		
		 
		/*Champion
		Eeco
		Tata Ace
		Tata 407 (10 Ft)
		Tata 709 (14 Ft)
		17 Feet
		19 Feet
		22 Feet
		24 Feet
		28 Feet
		32 Feet
		40 Feet		
		17 Feet	Single Axle
		17 Feet Double Axle	
		19 Feet	Single Axle	
		19 Feet Double Axle	
		19 Feet Multi-Axle
		22 Feet	Single Axle
		22 Feet Double Axle			
		22 Feet Multi-Axle
		24 Feet	Single Axle	
		24 Feet Double Axle	
		24 Feet Multi-Axle
		28 Feet	Single Axle	
		28 Feet Double Axle	
		28 Feet Multi-Axle
		32 Feet	Single Axle	
		32 Feet Double Axle	
		32 Feet Multi-Axle
		40 Feet	Double Axle	
		40 Feet Multi-Axle*/


	//}
}
