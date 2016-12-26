package com.trux.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.trux.model.CustomerBookingDetails;
import com.trux.model.FareRates;

public class JasperUtils {
    static DecimalFormat df2 = new DecimalFormat("#.##");

public static void main(String[] args) throws Exception{
	
	CustomerBookingDetails customerBookingDetails = new CustomerBookingDetails();
	customerBookingDetails.setCustomerName("Krishna Singh");
	customerBookingDetails.setBookingId(105);
	customerBookingDetails.setTotalFare(9990.0);
	customerBookingDetails.setTripDuration(3690.0);
	customerBookingDetails.setTotalDistanceTravelled(11200.0);
	
	customerBookingDetails.setVehicleType("Tata 407");
	customerBookingDetails.setCreatedDate(new Date());
	customerBookingDetails.setRideTime(new Date(new Date().getTime()+7200*1000));
	customerBookingDetails.setCustomerEmail("krishna@triyasoft.com");
	
   FareRates fareRates = new FareRates();

   fareRates.setInclusive_of_km(5);
   fareRates.setBase_fare(250.0);
   fareRates.setRate_per_trip_minute(3);;
	generateInvoiceReport(customerBookingDetails, fareRates);
	
}

public static void generateInvoiceReport(CustomerBookingDetails customerBookingDetails, FareRates fareRates) throws Exception{
               try{
	
	      String sourceFileName =  "/home/ec2-user/tomcat/apache-tomcat-7.0.57/webapps/trux/resources/reports/TruxInvoice.jasper";

		   //   DataBeanList DataBeanList = new DataBeanList();
		    //  ArrayList<DataBean> dataList = DataBeanList.getDataBeanList();
	        ArrayList dataList = new ArrayList();

		      JRBeanCollectionDataSource beanColDataSource =
		      new JRBeanCollectionDataSource(dataList);

		      Map parameters = new HashMap();
		      /**
		       * Passing ReportTitle and Author as parameters
		       */
		      parameters.put("UserName", "Dear "+customerBookingDetails.getCustomerName());
		      parameters.put("order_id", customerBookingDetails.getBookingId()+"");
		      parameters.put("servicetype", customerBookingDetails.getVehicleType());
		      parameters.put("bookingdate", customerBookingDetails.getCreatedDate().toString());
		      parameters.put("pickupdate", customerBookingDetails.getRideTime().toString());
		      parameters.put("emailid", customerBookingDetails.getCustomerEmail());
		   //   parameters.put("servicetax", customerBookingDetails.getTotalFare()*.12+"");
		    //  parameters.put("educess", customerBookingDetails.getTotalFare()*.0036+"");
		      
		      parameters.put("servicetax", "0.0");
		      parameters.put("educess", "0.0");
		   
			 
		      
		      
		      
		      parameters.put("base_fare_inclusive_km", fareRates.getInclusive_of_km()+"");
		      parameters.put("base_fare", fareRates.getBase_fare()+"");
		      
		       
		     
		      
		      double additional_km = Double.valueOf(df2.format((customerBookingDetails.getTotalDistanceTravelled()/1000)-fareRates.getInclusive_of_km()));
		      
		      if(additional_km < 0)
		    	  additional_km  = 0;
		   
		      
		      
		    
		      
		      
		  	double costDueTobaseFare  = Double.valueOf(df2.format(fareRates.getBase_fare()));
			double costDueToextaTravel = Double.valueOf(df2.format((additional_km)*fareRates.getRate_per_km()));
			double costDueToTravelTime  = Double.valueOf(df2.format((customerBookingDetails.getTripDuration()/60)*fareRates.getRate_per_trip_minute()));
			
			double costOfJourney = costDueTobaseFare+costDueToextaTravel+costDueToTravelTime;
			
		   // return Double.valueOf(df2.format(costOfJourney));
		
		  
			  parameters.put("wait_time", (Double.valueOf(df2.format((customerBookingDetails.getTripDuration()/60))))+" mins");
		      parameters.put("waiting_charges", costDueToTravelTime+"");
		    
		      
		       
		      parameters.put("additional_km", (additional_km)+"");
		      parameters.put("rate_above_base_fare",  (costDueToextaTravel)+"");
		      
		      
		      parameters.put("totalfare", costOfJourney+"");
		      parameters.put("totaldistance", Double.valueOf(df2.format((customerBookingDetails.getTotalDistanceTravelled()/1000)))+"");
		      parameters.put("totaltime",Double.valueOf(df2.format((customerBookingDetails.getTripDuration()/60)))+" mins");
		 
		      
		      //base_fare_inclusive_km
		      //base_fare
		      //wait_time
		      //rate_above_base_fare
		      //waiting_charges


		      parameters.put("rupee", "http://truxapp.com/trux/resources/reports/rupee-symbol.png");
		      parameters.put("logo", "http://truxapp.com/trux/resources/reports/truklogo.png");
		      
		   //   parameters.put("Author", "Prepared By Manisha");

		      try {
		    	  String  printFileName = JasperFillManager.fillReportToFile(sourceFileName, parameters, new JREmptyDataSource());
		    	 JasperExportManager.exportReportToPdfFile(printFileName, "/tmp/invoice.pdf");
		    	  //JasperExportManager.exportReportToHtmlFile(printFileName, "/Users/abc/Downloads/Win32_TrackInstallation_DLL_Project/sample_report.html");

		      
		      } catch (JRException e) {
		         e.printStackTrace();
		      }
	
	//compileReport("/Users/abc/Downloads/Win32_TrackInstallation_DLL_Project/TruxBill.jrxml", "/Users/abc/Downloads/Win32_TrackInstallation_DLL_Project/TruxBill.jasper");
               }catch(Exception er){er.printStackTrace();}
}
}
