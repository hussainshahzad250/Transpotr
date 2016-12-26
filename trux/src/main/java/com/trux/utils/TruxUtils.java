package com.trux.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Encoder;

import com.trux.model.CustomerBookingDetails;
import com.trux.model.FareRates;
import com.trux.model.LeadGeneration;

public class TruxUtils {
    static DecimalFormat df2 = new DecimalFormat("#.##");

	public static String computeMD5Hash(String inputString) {
        String hashedString = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(inputString.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashedString = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return hashedString;
	}
	
	public static int getRandomNumber(int min, int max) {
	    return (int) Math.floor(Math.random() * (max - min + 1)) + min;
	}
	
	public static String randomAlphanumberGenerator(){
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}
	
	public static Date convertStringToDate(String dateString) throws ParseException{
		
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
		return date;
	}
	
	
	 public static String generateForgotPaswordCode(String email) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		 
		 String forgot_password_key = new BASE64Encoder().encode(Hash256(email+"."+System.currentTimeMillis()));
		 return forgot_password_key;
	 }

	private static byte[] Hash256(String string) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		try{
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		
		String text = string;
	
		md.update(text.getBytes("UTF-8")); // Change this to "UTF-16" if needed
		byte[] digest = md.digest();
		
		return digest;}catch(Exception er){er.printStackTrace();  return new byte[0];}
	}
	

	public static void pageRedisrection(String pagePath, HttpServletRequest request, HttpServletResponse response){
		
		RequestDispatcher dispatcher= request.getRequestDispatcher(pagePath);
	    try {
			dispatcher.forward(request,  response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param toRecipient
	 * @param textTobeSend
	 * @param mailType (0 for forget password & 1 for payment reciept)
	 * @param customerBookingDetails 
	 */
	
	public static void main(String[] args) {
	/*	CustomerBookingDetails customerBookingDetails = new CustomerBookingDetails();
		customerBookingDetails.setBookingId(123);
		customerBookingDetails.setCustomerName("Krishna Singh");
		customerBookingDetails.setDocumentuploadurl("http://www.yahoo.com");
		customerBookingDetails.setTotalFare(2340.0);
		customerBookingDetails.setTotalDistanceTravelled(49.0);
		customerBookingDetails.setTripDuration(36.0);
		customerBookingDetails.setCustomerEmail("krishna@triyasoft.com"); */
		
		sendMail("krishna@triyasoft.com", "Hi Krishna", 0, null, null);
	}
	
	 public static void sendMail(String toRecipient, String textTobeSend, Integer mailType, CustomerBookingDetails customerBookingDetails, FareRates fareRates){
		   try{
		   String host="smtp.gmail.com";  
		   final String user="noreply@truxapp.com";//change accordingly  
		   final String password="Trux@123";//change accordingly  
		     
		   String to=toRecipient;//change accordingly  
		   
		    //Get the session object  
		    Properties props = new Properties();
		    props.put("mail.smtp.port", "587");
		    props.put("mail.smtp.starttls.enable","true");
		    props.put("mail.smtp.host",host);  
		    props.put("mail.smtp.auth", "true");  
		    
		    Session session = Session.getDefaultInstance(props,  
		     new javax.mail.Authenticator() {  
		       protected PasswordAuthentication getPasswordAuthentication() {  
		     return new PasswordAuthentication(user,password);  
		       }  
		     });  
		   
		    //Compose the message  
		     try {  
		      MimeMessage message = new MimeMessage(session);  
		      message.setFrom(new InternetAddress(user));  
		      message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		      if(mailType == 0){
		    	  message.setSubject("Trux Forgot Password");  
			      message.setText("Please click the URL for reset your password: "+"http://truxapp.com/trux/forgetPassword/resetPassword?passKey="+textTobeSend);
		      }else if(mailType == 1){
		    	  
		    	  message.setSubject("Trux Receipt for CRN "+customerBookingDetails.getBookingId());
		    	  
		    	
		    	//  String htmlText = "<H1>Dear customer: "+customerBookingDetails.getCustomerName()+"</H1> <br /> Your billty details for booking id:"+customerBookingDetails.getBookingId()+
		    		//	  			"<br/> <img src=\""+customerBookingDetails.getDocumentuploadurl()+"\"/>";
		    	 
		    	  String htmlText = "";
				try {

					htmlText = readFile("/home/ec2-user/tomcat/apache-tomcat-7.0.57/webapps/emailtemplate.html");
					
				    double additional_km = Double.valueOf(df2.format((customerBookingDetails.getTotalDistanceTravelled()/1000)-fareRates.getInclusive_of_km()));
				      
				      if(additional_km < 0)
				    	  additional_km  = 0;
				  
				  	double costDueTobaseFare  = Double.valueOf(df2.format(fareRates.getBase_fare()));
					double costDueToextaTravel = Double.valueOf(df2.format((additional_km)*fareRates.getRate_per_km()));
					double costDueToTravelTime  = Double.valueOf(df2.format((customerBookingDetails.getTripDuration()/60)*fareRates.getRate_per_trip_minute()));
					
					double costOfJourney = costDueTobaseFare+costDueToextaTravel+costDueToTravelTime;
					
				   // return Double.valueOf(df2.format(costOfJourney));
				
					
					
					 String[] values = new String[22];
					 values[0] = customerBookingDetails.getBookingId()+"";
					 values[1] = new Date().toString();
					 values[2] = customerBookingDetails.getCustomerName();
					 values[3] = customerBookingDetails.getTotalFare()+"";
					 values[4] =  Double.valueOf(df2.format((customerBookingDetails.getTotalDistanceTravelled()/1000)))+"";
					 values[5] =  Double.valueOf(df2.format((customerBookingDetails.getTripDuration()/60)))+"";
					 values[6] = customerBookingDetails.getTotalFare()+""+"";
					 
					 //base fare for 5 Km
					 values[7] = fareRates.getInclusive_of_km()+"";
					 
					 //
					 values[8] = fareRates.getBase_fare()+"";
					 
					 
					     
					      
					/*      int rate_above_base_fare = (int)((int)(customerBookingDetails.getTotalDistanceTravelled()/1000-fareRates.getInclusive_of_km())*fareRates.getRate_per_km());
					      
					      if(rate_above_base_fare < 0)
					    	  rate_above_base_fare = 0;
					    
					 */
					 
						 
					 values[9] = (additional_km)+"";
					 values[10] = (costDueToextaTravel)+"";
					 
					 
					 values[11] = Double.valueOf(df2.format((customerBookingDetails.getTripDuration()/60)))+"";
					 values[12] = costDueToTravelTime+"";
					 
					 Integer labourCount = customerBookingDetails.getLabourCount();
					 if(labourCount == null)
						 labourCount = 0;
					 
					 values[13] = labourCount*200+"";
					 
					 values[14] = "0.0";

					 //taxes

					  
				//	 values[15] = customerBookingDetails.getTotalFare()*.12+"";
				//	 values[16] = customerBookingDetails.getTotalFare()*.0036+"";
				//	 values[17] = customerBookingDetails.getTotalFare()*.0018+"";
					 
					 values[15] = "0.0";
					 values[16] = "0.0";
					 values[17] = "0.0";
					 
					 values[18] = customerBookingDetails.getVehicleType();
					 values[19] = customerBookingDetails.getCreatedDate().toString();
					 values[20] = new Date(customerBookingDetails.getExpectedRideStartTime()).toString();
					 values[21] = customerBookingDetails.getCustomerEmail();
					htmlText = getMessage(htmlText, values);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				  MimeMultipart multipart = new MimeMultipart();
		    	  BodyPart messageBodyPart = new MimeBodyPart();
		    	  
		    	  messageBodyPart.setContent(htmlText, "text/html");
		    	 
		    	  multipart.addBodyPart(messageBodyPart);
		    	  
		    	  messageBodyPart = new MimeBodyPart();
		    	  String filename = "/tmp/invoice.pdf";
		    	  DataSource source = new FileDataSource(filename);
		    	  messageBodyPart.setDataHandler(new DataHandler(source));
		    	  messageBodyPart.setFileName("TruxInvoice.pdf");
		    	 
		    	 multipart.addBodyPart(messageBodyPart);
		    	 
		    	 if(customerBookingDetails != null && customerBookingDetails.getDocumentuploadurl() != null && !customerBookingDetails.getDocumentuploadurl().isEmpty()){
		    		 try {
						saveImage(customerBookingDetails);
						
						  messageBodyPart = new MimeBodyPart();
				    	  String builtyname = "/tmp/Builty_"+customerBookingDetails.getBookingId()+".jpg";
				    	  DataSource builtysource = new FileDataSource(builtyname);
				    	  messageBodyPart.setDataHandler(new DataHandler(builtysource));
				    	  messageBodyPart.setFileName("Builty_"+customerBookingDetails.getBookingId()+".jpg");
				    	 
				    	 multipart.addBodyPart(messageBodyPart);
					} catch (IOException e) {
						e.printStackTrace();
					}
		    	 }
		    	 
		    	 message.setContent(multipart);
//			      message.setText("Dear customer: " + customerBookingDetails.getConsigneeName()+"/n Your builty details for booking id: "+customerBookingDetails.getBookingId());
		      }
		        
		        
		     //send the message  
		      Transport.send(message);  
		   
		      System.out.println("message sent successfully...");
		      boolean success = (new File("/tmp/invoice.pdf")).delete();
		         if (success) {
		            System.out.println("invoice successfully deleted"); 
		         }if(customerBookingDetails!=null){
		      boolean builty = (new File("/tmp/Builty_"+customerBookingDetails.getBookingId()+".jpg")).delete();
		         if (builty) {
		            System.out.println("builty successfully deleted"); 
		         }}
		    
		      } catch (MessagingException e) {
		    	  e.printStackTrace();
		      }  }catch(Exception er){er.printStackTrace();}
		  }
	 
	 
	 static String getMessage (String template, String ... values)
		{ try{
			if(values !=null ) {
		   for (int i = values.length-1; i >= 0; i--){
			   System.out.println(i);
			   if(values[i]!=null){
		        template = template.replace("$"+(i+1), values[i]);}
		   }
		   return template;
			}
			
			else return template;}catch(Exception er){ return "";}
		}
	 
	 public static void saveImage(CustomerBookingDetails customerBookingDetails) throws IOException {
		 try{
		 	String imageUrl = customerBookingDetails.getDocumentuploadurl();
			String destinationFile = "/tmp/Builty_"+customerBookingDetails.getBookingId()+".jpg";

			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);

			byte[] b = new byte[2048];
			int length;
			System.out.println("SaveImageFromUrl.saveImage()"+b.length);
			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();
		 }catch(Exception er){er.printStackTrace();}
		}
	 
	 
		public static String getIPAddress(HttpServletRequest request){
			try{  String ipAddress = request.getHeader("X-FORWARDED-FOR");  
		       
		        if (ipAddress == null) {  
		     	   ipAddress = request.getRemoteAddr();  
		        }
		        if(ipAddress.contains(",")){
		        	String[] ipaddlist = ipAddress.split(",");
		        	ipAddress = ipaddlist[0];
		        }
			
			return ipAddress;}catch(Exception er){er.printStackTrace(); return "";}
		}
	 
	 
	 private static String readFile( String file ) throws IOException {
		    BufferedReader reader = new BufferedReader( new FileReader (file));
		    String         line = null;
		    StringBuilder  stringBuilder = new StringBuilder();
		    String         ls = System.getProperty("line.separator");

		    while( ( line = reader.readLine() ) != null ) {
		        stringBuilder.append( line );
		        stringBuilder.append( ls );
		    }

		    return stringBuilder.toString();
		}
	 
	 public static double roundTo5Decimals(double val) {
	        DecimalFormat df2 = new DecimalFormat("#.#####");
	        return Double.valueOf(df2.format(val));
		}
	 
	 public static double roundTo1Decimals(double val) {
	        DecimalFormat df2 = new DecimalFormat("#.#");
	      //  System.out.println("Lat Long Val:"+val);
	        return Double.valueOf(df2.format(val));
		}

	public static Date getChangedTimezoneDate(Date date) {
		
		if(date != null)
			return new Date(date.getTime()+1800*1000*11);
		
		
		return null;
	}
	 
	 
	/* public static void main(String[] args) {
		sendMail("dharmendra.singh@timesinternet.in", "abcd", 0, null);
	}*/
	
/*	public static void sendProposalMail(LeadGeneration leadGeneration) {
		try {
			String host = "smtp.gmail.com";
			final String user = "noreply@truxapp.com";// change accordingly
			final String password = "Trux@123";// change accordingly
			String to = leadGeneration.getSendProposal();// change accordingly
			// Get the session object
			Properties props = new Properties();
			props.put("mail.smtp.port", "587");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			Session session = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(user, password);
						}
					});

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject("Proposal for "
					+ leadGeneration.getScheduledMeetingTitle());
			message.setText("Hi,\n Date :"
					+ leadGeneration.getScheduledMeetingDate()
					+ "\n Date Time: "
					+ leadGeneration.getScheduledMeetingTime() + "\n "
					+ leadGeneration.getComments() + "\n");
			String[] imageurl = leadGeneration.getImageOfVisitingCard().split(
					"#");
			StringBuilder urlAdd = new StringBuilder();
			urlAdd.append("<p>Hi,<br> Schedued Meeting Date :"
					+ leadGeneration.getScheduledMeetingDate()
					+ "<br> </p><p> Scheduled Meeting Date Time: "
					+ leadGeneration.getScheduledMeetingTime() + "\n</p><p> "
					+ leadGeneration.getComments() + "\n</p>");
			for (String url : imageurl) {
				urlAdd.append("<div style='margin-bottom:6px;'><img src='"
						+ url
						+ "' style='width:100%;height:50px;'></div><br/> ");
			}
			urlAdd.append("Thanks<br> Truxapp");
			String htmlText = urlAdd.toString();
			MimeMultipart multipart = new MimeMultipart();
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(htmlText, "text/html");
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("message sent successfully...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
