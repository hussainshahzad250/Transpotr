package com.trux.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trux.model.GCMContent;


public class POST2GCM {

	
	public static void post(String apiKey, GCMContent content){
		
		try{
		URL url = new URL("https://android.googleapis.com/gcm/send");
					
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization", "key="+apiKey);
		
		conn.setDoOutput(true);

			ObjectMapper mapper = new ObjectMapper();
	    	DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			mapper.writeValue(wr, content);
			wr.flush();
			wr.close();
			 
			int responseCode = conn.getResponseCode();
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Response Code : " + responseCode);
	 
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	 
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	 
			// 7. Print result
			System.out.println(response.toString());
					
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
}

