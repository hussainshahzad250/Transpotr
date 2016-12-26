package com.trux.utils;

import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.io.*;

public class MessagePooling {
	
	public static void main(String [] args)
	   {
		
		Timer time = new Timer(); // Instantiate Timer Object
		ScheduledTask st1 = new ScheduledTask(); // Instantiate SheduledTask
		// class
		time.schedule(st1, 0, 1000*15); // Create Repetitively task for every 15
									// secs
		
//	      try
//	      {
////	         URL url = new URL("http://www.amrood.com/index.htm?language=en#j2se");
//	    	  URL url = new URL("http://enterprise.smsgupshup.com/GatewayAPI/rest?method=SendMessage&send_to=917291970813&msg=Dear%20Transporter,Thanks%20for%20registering%20with%20Trux.%20Download%20Android%20app%20using%20http://bit.ly/truxtr%20to%20receive%20orders.%20Username%207838490077%20/%20Password%20231243&msg_type=TEXT&userid=2000156206&auth_scheme=plain&password=trux@123&v=1.1&format=text");
//	         
//	         System.out.println("URL content is " + url.getContent().toString());
//	         System.out.println("URL is " + url.toString());
//	         System.out.println("protocol is " + url.getProtocol());
//	         System.out.println("authority is " + url.getAuthority());
//	         System.out.println("file name is " + url.getFile());
//	         System.out.println("host is " + url.getHost());
//	         System.out.println("path is " + url.getPath());
//	         System.out.println("port is " + url.getPort());
//	         System.out.println("default port is " + url.getDefaultPort());
//	         System.out.println("query is " + url.getQuery());
//	         System.out.println("ref is " + url.getRef());
//	      }catch(IOException e)
//	      {
//	         e.printStackTrace();
//	      }
		
		/*Connection conn = null;
		String url = "jdbc:mysql://54.169.177.19:3306/";
		// jdbc:mysql://54.169.176.165:3306/
		String dbName = "truxdev";
		// trux
		String driver = "com.mysql.jdbc.Driver";
		String userName = "devnewuser";
		// prodnewuser
		String password = "trux#dev";
		// Trux9R0d#2016

		try {
			Class.forName(driver).newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try
	      {
			conn = DriverManager.getConnection(url + dbName, userName, password);
			System.out.println("Connected to the database");
			
			String query = "SELECT id, sender_mask, sms_provider, mobile_number, sms_text, request_date FROM communication_sms;";

			// SELECT bkLsStpId, bookingLeaseId, GROUP_CONCAT(stopLat,' ', stopLong) lat_long, distance_between_stops FROM booking_lease_stop WHERE bkLsStpId > 4979 GROUP BY bookingLeaseId ORDER BY bkLsStpId ASC;
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			if (rs != null) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String sender_mask = rs.getString("sender_mask");
					String sms_provider = rs.getString("sms_provider");
					String mobile_number = rs.getString("mobile_number");
					String sms_text = rs.getString("sms_text");
					Date request_date = rs.getDate("request_date");
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					String format = formatter.format(request_date);
					Date request_process = new Date();
					
					///////////////////////////////GUPSHUP SMS///////////////////////////////////////////
					
					String urlBuilder="";
					
					urlBuilder = "http://enterprise.smsgupshup.com/GatewayAPI/rest?method=SendMessage&send_to=" + mobile_number + "&msg=" + sms_text + "&msg_type=TEXT&userid=2000156206&auth_scheme=plain&password=trux@123&v=1.1&format=text";
					urlBuilder = urlBuilder.replace(" ", "%20");
					Date sent_at = new Date();
					
					URL url2 = new URL(urlBuilder);
					URLConnection urlConnection = url2.openConnection();
					HttpURLConnection connection = null;
					if(urlConnection instanceof HttpURLConnection)
					{
						connection = (HttpURLConnection) urlConnection;
					}
					else
					{
						System.out.println("Please enter an HTTP URL.");
						return;
					}
					BufferedReader in = new BufferedReader(
							new InputStreamReader(connection.getInputStream()));
					String urlString = "";
					String current;
					
					while((current = in.readLine()) != null)
					{
						urlString += current;
					}
					System.out.println(urlString);
					
					urlString = urlString.replace(" ", "");
					urlString = urlString.replace("|", " ");
					String[] parts = urlString.split(" ");
					String part1 = parts[0];
					String part2 = parts[1];
					String part3 = parts[2];
					
					// success | 917291970813 | 3030759802440406370-315558309109910975
					
					///////////////////////////////GUPSHUP SMS///////////////////////////////////////////
					
					
					String insert_query1 = "";	
					
					insert_query1 = "INSERT INTO communication_sms_archive (communication_sms_id, sender_mask, sms_provider, mobile_number, sms_text, request_date, request_process, sent_at, server_response, sms_provider_response) "
							+ "VALUES ("+id+",'"+sender_mask+"','"+sms_provider+"','"+mobile_number+"','"+sms_text+"','"+format+"',NOW(),NOW(),'"+part1+"','"+part3+"')";
							
							Statement st2 = conn.createStatement();
							st2.executeUpdate(insert_query1);
							st2.close();
							
							
							String del_query="DELETE FROM communication_sms WHERE id="+id+";";
							Statement st3 = conn.createStatement();
							st3.executeUpdate(del_query);
							st3.close();
				}
				
				
			}
			st.close();

			
			
	         
	         
	         
	         
	      }catch (SQLException e) {
				System.out.println(e.toString());
			} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {

				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Disconnected from database");*/
	   }


}
