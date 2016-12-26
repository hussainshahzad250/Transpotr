package com.trux.constants;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.trux.model.ConsumerRegistration;
import com.trux.model.CustomerBookingDetails;
import com.trux.model.OrganizationalClientBookingDetails;

public class SMSTemplates {

	private static final String BOOKING_CONFIRMATION = "Hello $1, Your vehicle driver is $2 ($3) for TRX$4, $5 $6 to come for the pick up of load @ $7. You can share $8 with the consignee and they can also track the vehicle. Call +91 7827379999 for any assistance.";
	private static final String WELCOME_MESSAGE = "Hello $1, Welcome to TRUX.  India's fisrt mobile app based truck booking service. With TRUX you can book, track and pay per KM with just few clicks. We look forward to your first booking with TRUX";
	private static final String VEHICLE_ARRIVAL_TEMPLATE = "Your TRUX vehicle has arrived. You are provided with 30 minutes of free loading time, and 30 minutes of free unloading time, there after you will be charged Rs 3 per minute";
	private static final String BOOKING_CANCELLATION="Hello!! We regret to inform the cancellation of your booking $1 as we are unable to find a TRUX vehicle near your pickup location. We'd have loved to serve you!";
	private static final String FUTURE_BOOKING_MESSAGE="Hello $1 your booking with $2 has been received for $3. We look forward to have your load transported.";
	private static final String FUTURE_LEASE_BOOKING_MESSAGE="Hello, your drop has been delivered. We look forward to have your load transported.";
	private static final String CONSIGNEE_SMS_TEMPLATE = "Hello $1, You can track the vehicle by clicking on the following Link $2. You can also download our fabulous app by clicking on the following link $3, G15available on google play store";
	private static final String MESSAGE_CONSIGNEE_TO_PAY = "Hello $1, We have received a booking from $2 for $3. Estimated Fare Rs. $4. Please keep the money in cash ready when the vehicle arrives for unloading your goods. Thank You";
	
	private static final String MESSAGE_TO_CONSIGNOR_FOR_TRIP_ENDING_AND_PAYMENT_MADE = "Hello $1, Your goods have been delivered successfully at your given address. The total Billed amount is Rs. $2 and was paid by the consignee. To view this ride detials, please visit the trip history page on your TRUX mobile App. We appreciate your Business. Thank You";
	private static final String MESSAGE_TO_FORGET_PASSWORD = "Your password change successfully. Thank You";
	
	private static final String SMS_USER_NAME = "truxapphttp";
	private static final String SMS_USER_PASSWORD = "truxapp12";
	private static final String SMS_BASE_URL = "http://203.212.70.200/smpp/sendsms?username=";
	private static final String NOTIFICATION_BACKENDTEAM_SMG= "Hello, an order (trx$1) has been placed by $2 ( +91-$3 ) for $4, Pick up Time: $5, From: $6 To: $7 for a freight of Rs. $8 has not been picked up by any driver yet";
	

	
	static String getSMSMessage (String template, String ... values)
	{
		if(values !=null ) {
	   for (int i = 0; i < values.length; i++){
		   System.out.println(i);
		   if(values[i]!=null){
	        template = template.replace("$"+(i+1), values[i]);
		   }
	   }
	   return template;
		}
		
		else return template;
	}
	
	static String getForgetSMSMessage (String template, String ... values)
	{
		if(values !=null ) {
	   for (int i = 0; i < values.length; i++){
		   System.out.println(i);
		   if(values[i]!=null){
	        template = template +values[i];
		   }
	   }
	   return template;
		}else{		
		  return "this is the test message";
		}
	}
	
	
	public static void vehicleArrived(CustomerBookingDetails customerBookingDetails) throws Exception{

      try{

		  String customerPhoneNumber = customerBookingDetails.getCustmerPhonenumber();
		
		 
		 
		   String url = "http://203.212.70.200/smpp/sendsms?username="+SMS_USER_NAME+"&password="+SMS_USER_PASSWORD+"&to=91"+customerPhoneNumber+"&from=TRUXAP&udh=&dlr-mask=19&dlr-url&text="+encode(getSMSMessage(VEHICLE_ARRIVAL_TEMPLATE, null));
		    URL website = new URL(url);
		    System.out.println(url);

		   URLConnection connection = website.openConnection();
		 
		    InputStream is = connection.getInputStream();
		    is.close();
		
      }catch (Exception er ){er.printStackTrace(); throw er;}
		 
	
	
	
	}
	
	public static void welcomeMessage()  throws Exception {
     try{

		
		 String[] values = new String[1];
		 values[0] = "Dhruv Khandelwal";
		 String toUser = "9560846172";

		 
		 
		   String url = "http://203.212.70.200/smpp/sendsms?username="+SMS_USER_NAME+"&password="+SMS_USER_PASSWORD+"&to=91"+toUser+"&from=TRUXAP&udh=&dlr-mask=19&dlr-url&text="+encode(getSMSMessage(WELCOME_MESSAGE, values));
		   System.out.println(url); 
		   URL website = new URL(url);
		    

		   URLConnection connection = website.openConnection();
		 
		    InputStream is = connection.getInputStream();
		    is.close();
		
     }catch (Exception er ){er.printStackTrace(); throw er;}
		 
	
	
	}
	
	
   public static void main(String[] args)  throws Exception{
		
	//	senMessageToConsigerForTripEndingAndPaymentDone(null);
	   CustomerBookingDetails customerBookingDetails = new CustomerBookingDetails();
	   customerBookingDetails.setCustomerName("Krishna Singh");
	   customerBookingDetails.setDriverName("Dharmendra Singh");
	   customerBookingDetails.setDriverPhonenumber("9899016401");
	   customerBookingDetails.setBookingId(123456);
	   customerBookingDetails.setVehicleType("Tata 407");
	   customerBookingDetails.setCreatedDate(new Date());
	   customerBookingDetails.setVehicleNumber("UP 78 BA 3278");
	   customerBookingDetails.setExpectedRideStartTime(new Date().getTime());
	   customerBookingDetails.setConsigneeName("Krishna Singh");
	   customerBookingDetails.setConsigneePhoneNumber("9899016401");
	   customerBookingDetails.setCustmerPhonenumber("9899016401");
	   customerBookingDetails.setExpectedFare("123");
	   customerBookingDetails.setFromLocation("190, Satguru Ram Singh Marg,C-Block, Mansarover Garden,New Delhi, Delhi 110015");
	   customerBookingDetails.setToLocation("Mahipalpur, New Delhi, Delhi, India");
	  // sendBookingConfirmation(customerBookingDetails);
	   
	  // sendTrackingDetailsToConsignee(customerBookingDetails);
	//   welcomeMessage();
	  // vehicleArrived(customerBookingDetails);
	   
	   sendBookingConfirmationToOps(customerBookingDetails);
		
		
	}
	
   
   public static void sendBookingConfirmationToOps(CustomerBookingDetails customerBookingDetails) throws Exception {
	   
	   try {
	   
	    String[] toUserNumbers = {"9810855738","9818079793","9971016567","9899016401", "9931829067"};
	   
	    //String[] toUserNumbers = {"9899016401"};
		
	    Date date = new Date(customerBookingDetails.getCreatedDate().getTime()+11*1800*1000);

	   
	   for (String toUserNumber : toUserNumbers) {
		
	
		
		 String[] values = new String[8];
		 values[0] = customerBookingDetails.getBookingId()+"";
		 values[1] = customerBookingDetails.getCustomerName();
		 values[2] = customerBookingDetails.getCustmerPhonenumber();
		 values[3] = customerBookingDetails.getVehicleType();
		 values[5] = customerBookingDetails.getFromLocation();
		 values[6] = customerBookingDetails.getToLocation();
		 values[7] = customerBookingDetails.getExpectedFare();


		 SimpleDateFormat ft = 
			      new SimpleDateFormat ("hh:mm a");
		 values[4] = ft.format(date);
		// values[7] = "http://truxapp.com/tracking/tracking.jsp?bookingId="+customerBookingDetails.getBookingId();
		 
		 
		String url = SMS_BASE_URL+SMS_USER_NAME+"&password="+SMS_USER_PASSWORD+"&to=91"+toUserNumber+"&from=TRUXAP&udh=&dlr-mask=19&dlr-url&text="+encode(getSMSMessage(NOTIFICATION_BACKENDTEAM_SMG, values));
		   
		System.out.println(url);
		URL website = new URL(url);

		 URLConnection connection = website.openConnection();
		 
		    InputStream is = connection.getInputStream();
		    is.close();
		
	   }
		 
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	
	}
   
 public static void sendBookingConfirmationToOps(OrganizationalClientBookingDetails customerBookingDetails) throws Exception {
	   
	   try {
	   
	    String[] toUserNumbers = {"9810855738","9818079793","9971016567","9899016401", "9931829067"};
	   //String[] toUserNumbers = {"9899016401"};
		 Date date = new Date(customerBookingDetails.getCreatedDate().getTime()+11*1800*1000);

	   
	   for (String toUserNumber : toUserNumbers) {
		
	
		
		 String[] values = new String[8];
		 values[0] = customerBookingDetails.getBookingId()+"";
		 values[1] = customerBookingDetails.getCustomerName();
		 values[2] = customerBookingDetails.getCustmerPhonenumber();
		 values[3] = customerBookingDetails.getVehicleType();
		 values[5] = customerBookingDetails.getFromLocation();
		 values[6] = customerBookingDetails.getToLocation();
		 values[7] = customerBookingDetails.getExpectedFare();


		 SimpleDateFormat ft = 
			      new SimpleDateFormat ("hh:mm a");
		 values[4] = ft.format(date);
		// values[7] = "http://truxapp.com/tracking/tracking.jsp?bookingId="+customerBookingDetails.getBookingId();
		 
		 
		String url = SMS_BASE_URL+SMS_USER_NAME+"&password="+SMS_USER_PASSWORD+"&to=91"+toUserNumber+"&from=TRUXAP&udh=&dlr-mask=19&dlr-url&text="+encode(getSMSMessage(NOTIFICATION_BACKENDTEAM_SMG, values));
		   
		System.out.println(url);
		URL website = new URL(url);

		 URLConnection connection = website.openConnection();
		 
		    InputStream is = connection.getInputStream();
		    is.close();
		
	   }
		 
	   } catch (Exception e) {
		   e.printStackTrace();
	   }
	
	}
   
	public static void sendBookingConfirmation(CustomerBookingDetails customerBookingDetails) throws Exception {

		try{
		 String[] values = new String[8];
		 values[0] = customerBookingDetails.getCustomerName();
		 values[1] = customerBookingDetails.getDriverName();
		 values[2] = customerBookingDetails.getDriverPhonenumber();
		 values[3] = customerBookingDetails.getBookingId()+"";
		 values[4] = customerBookingDetails.getVehicleType();
		 values[5] = customerBookingDetails.getVehicleNumber();
		 Date date = new Date(customerBookingDetails.getExpectedRideStartTime()+11*1800*1000);

		 SimpleDateFormat ft = 
			      new SimpleDateFormat ("hh:mm a, dd MMM");
		 values[6] = ft.format(date);
		 values[7] = "http://truxapp.com/tracking/tracking.jsp?bookingId="+customerBookingDetails.getBookingId();
		 
		 String toUserNumber= customerBookingDetails.getCustmerPhonenumber();
		 
		   String url = SMS_BASE_URL+SMS_USER_NAME+"&password="+SMS_USER_PASSWORD+"&to=91"+toUserNumber+"&from=TRUXAP&udh=&dlr-mask=19&dlr-url&text="+encode(getSMSMessage(BOOKING_CONFIRMATION, values));
		   
		   System.out.println(url);
		   URL website = new URL(url);

		   URLConnection connection = website.openConnection();
		 
		    InputStream is = connection.getInputStream();
		    is.close();
		
		}catch (Exception er ){er.printStackTrace(); throw er;}
		 
	
	}
	
	public static String encode(String input) {
        StringBuilder resultStr = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (isUnsafe(ch)) {
                resultStr.append('%');
                resultStr.append(toHex(ch / 16));
                resultStr.append(toHex(ch % 16));
            } else {
                resultStr.append(ch);
            }
        }
        return resultStr.toString();
    }

    private static char toHex(int ch) {
        return (char) (ch < 10 ? '0' + ch : 'A' + ch - 10);
    }

    private static boolean isUnsafe(char ch) {
        if (ch > 128 || ch < 0)
            return true;
        return " %$&+,/:;=?@<>#%".indexOf(ch) >= 0;
    }
	
	
	 public static void cancelBooking(CustomerBookingDetails customerBookingDetails) throws Exception{
	     try{  String[] values = new String[1];
	        values[0] = "TRX"+customerBookingDetails.getBookingId();
			String toUserNumber= customerBookingDetails.getCustmerPhonenumber();


	        String url = SMS_BASE_URL+SMS_USER_NAME+"&password="+SMS_USER_PASSWORD+"&to=91" + toUserNumber + "&from=TRUXAP&udh=&dlr-mask=19&dlr-url&text=" + encode(getSMSMessage(BOOKING_CANCELLATION, values));
	        URL website = new URL(url);
	        
	        System.out.println(url);

	        URLConnection connection = website.openConnection();

	        InputStream is = connection.getInputStream();
	        is.close();
	     }catch (Exception er ){er.printStackTrace(); throw er;}
	    }

	
	    public static void  bookLaterMessage(CustomerBookingDetails customerBookingDetails) throws Exception{
	    	
	    	try {
	        String[] values = new String[4];
	        values[0] = customerBookingDetails.getCustomerName();
	        values[1] = "TRX"+customerBookingDetails.getBookingId();
	        
	        Date date = new Date(customerBookingDetails.getExpectedRideStartTime()+11*1800*1000);

			 SimpleDateFormat ft = 
				      new SimpleDateFormat ("hh:mm a, dd MMM");
			 values[2] = ft.format(date);
	     
			String toUserNumber= customerBookingDetails.getCustmerPhonenumber();

	        String url =  SMS_BASE_URL+SMS_USER_NAME+"&password="+SMS_USER_PASSWORD+"&to=91" + toUserNumber + "&from=TRUXAP&udh=&dlr-mask=19&dlr-url&text=" + URLEncoder.encode(getSMSMessage(FUTURE_BOOKING_MESSAGE, values));
	        URL website = new URL(url);
	        URLConnection connection = website.openConnection();
	        InputStream is = connection.getInputStream();
	        is.close();
	    	} catch (Exception  e ) {
	    		e.printStackTrace();
	    	}

	    }
	    
  public static void  bookLaterMessage(OrganizationalClientBookingDetails customerBookingDetails) throws Exception{
	    	
	    	try {
	        String[] values = new String[4];
	        values[0] = customerBookingDetails.getCustomerName();
	        values[1] = "TRX"+customerBookingDetails.getBookingId();
	        
	        Date date = new Date(customerBookingDetails.getExpectedRideStartTime()+11*1800*1000);

			 SimpleDateFormat ft = 
				      new SimpleDateFormat ("hh:mm a, dd MMM");
			 values[2] = ft.format(date);
	     
			String toUserNumber= customerBookingDetails.getCustmerPhonenumber();

	        String url =  SMS_BASE_URL+SMS_USER_NAME+"&password="+SMS_USER_PASSWORD+"&to=91" + toUserNumber + "&from=TRUXAP&udh=&dlr-mask=19&dlr-url&text=" + URLEncoder.encode(getSMSMessage(FUTURE_BOOKING_MESSAGE, values));
	        URL website = new URL(url);
	        URLConnection connection = website.openConnection();
	        InputStream is = connection.getInputStream();
	        is.close();
	    	} catch (Exception  e ) {
	    		e.printStackTrace();
	    	}

	    }
  
  public static void  leaseBookLaterMessage(OrganizationalClientBookingDetails customerBookingDetails) throws Exception{
  	
  	try {
      String[] values = new String[4];
      values[0] = customerBookingDetails.getDriverName();
      values[1] = "TRX"+customerBookingDetails.getBookingId();
      
      Date date = new Date(customerBookingDetails.getExpectedRideStartTime()+11*1800*1000);

		 SimpleDateFormat ft = new SimpleDateFormat ("hh:mm a, dd MMM");
		 values[2] = ft.format(date);
   
		String toUserNumber= customerBookingDetails.getCustmerPhonenumber();

      String url =  SMS_BASE_URL+SMS_USER_NAME+"&password="+SMS_USER_PASSWORD+"&to=91" + toUserNumber + "&from=TRUXAP&udh=&dlr-mask=19&dlr-url&text=" + URLEncoder.encode(getSMSMessage(FUTURE_LEASE_BOOKING_MESSAGE, values));
      URL website = new URL(url);
      URLConnection connection = website.openConnection();
      InputStream is = connection.getInputStream();
      is.close();
  	} catch (Exception  e ) {
  		e.printStackTrace();
  	}

  }

		public static void sendTrackingDetailsToConsignee(
				CustomerBookingDetails customerBookingDetails) throws Exception {
			
try{
	        String[] values = new String[3];
	        values[0] = customerBookingDetails.getConsigneeName();
	        values[1] = "http://truxapp.com/tracking/tracking.jsp?bookingId="+customerBookingDetails.getBookingId();
	        values[2] = "https://play.google.com/store/hwjhwhdns";


	        
	      
	     
			String toUserNumber= customerBookingDetails.getConsigneePhoneNumber();

	        String url =  SMS_BASE_URL+SMS_USER_NAME+"&password="+SMS_USER_PASSWORD+"&to=91" + toUserNumber + "&from=TRUXAP&udh=&dlr-mask=19&dlr-url&text=" + encode(getSMSMessage(CONSIGNEE_SMS_TEMPLATE, values));
	        System.out.println(url);
	        URL website = new URL(url);
	        URLConnection connection = website.openConnection();
	        InputStream is = connection.getInputStream();
	        is.close();

}catch (Exception er ){er.printStackTrace(); throw er;}
	    
			
		}


		public static void sendSMSForConsigneeToPay(
				CustomerBookingDetails customerBookingDetails) throws Exception{
			
			//MESSAGE_CONSIGNEE_TO_PAY
			 

			try{

	        String[] values = new String[4];
	        values[0] = customerBookingDetails.getConsigneeName();
	        values[1] = customerBookingDetails.getCustomerName();
	        values[2] = customerBookingDetails.getVehicleType();
	        values[3] = customerBookingDetails.getExpectedFare();
      
	     
			String toUserNumber= customerBookingDetails.getConsigneePhoneNumber();

	        String url =  SMS_BASE_URL+SMS_USER_NAME+"&password="+SMS_USER_PASSWORD+"&to=91" + toUserNumber + "&from=TRUXAP&udh=&dlr-mask=19&dlr-url&text=" + URLEncoder.encode(getSMSMessage(MESSAGE_CONSIGNEE_TO_PAY, values));
	        URL website = new URL(url);
	        URLConnection connection = website.openConnection();
	        InputStream is = connection.getInputStream();
	        is.close();
			}catch (Exception er ){er.printStackTrace(); throw er;}
			
		}
		
		
		
		

		public static void senMessageToConsigerForTripEndingAndPaymentDone(
				CustomerBookingDetails customerBookingDetails) throws Exception{
			
			try{
			

			

	        String[] values = new String[2];
	        values[0] = customerBookingDetails.getCustomerName();
	        values[1] = customerBookingDetails.getTotalFare()+"";
	       
	     
			String toUserNumber= customerBookingDetails.getCustmerPhonenumber();
	        String url =  SMS_BASE_URL+SMS_USER_NAME+"&password="+SMS_USER_PASSWORD+"&to=91" + toUserNumber + "&from=TRUXAP&udh=&dlr-mask=19&dlr-url&text=" + URLEncoder.encode(getSMSMessage(MESSAGE_TO_CONSIGNOR_FOR_TRIP_ENDING_AND_PAYMENT_MADE, values));
	         System.out.println(url);
	        URL website = new URL(url);
	        URLConnection connection = website.openConnection();
	        InputStream is = connection.getInputStream();
	        is.close();
			}catch (Exception er ){er.printStackTrace(); throw er;}
			
		}
		
		public static void sendMessageToForgetPassword(ConsumerRegistration consumerRegistration) throws Exception{
		try{String[] values = new String[2];
	        values[0] = consumerRegistration.getUserFistLastName();
	        values[1] = consumerRegistration.getPassword();     	     
			String toUserNumber= consumerRegistration.getPhoneNumber();
	        String url =  SMS_BASE_URL+SMS_USER_NAME+"&password="+SMS_USER_PASSWORD+"&to=91" + toUserNumber + "&from=TRUXAP&udh=&dlr-mask=19&dlr-url&text=" +  URLEncoder.encode(getForgetSMSMessage(MESSAGE_TO_FORGET_PASSWORD, values));
	         System.out.println(url);
	        URL website = new URL(url);
	        URLConnection connection = website.openConnection();
	        InputStream is = connection.getInputStream();
	        is.close();
		}catch (Exception er ){er.printStackTrace(); throw er;}
			
		}
		
		
		
	 
	 
}
