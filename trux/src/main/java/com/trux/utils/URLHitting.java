package com.trux.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

public class URLHitting {

	public static void main(String[] args)  throws Exception {
		// String content = URLHitting.getText("https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyDCa7grnfoh0YLdDvkvusaTLMlBrMgEBu8&address=1600+Amphitheatre+Parkway%2C+Mountain+View%2C+CA");
	      //  System.out.println(content);
		
		  final OkHttpClient client = new OkHttpClient();

		 
		    Request request = new Request.Builder()
		        .url("https://maps.googleapis.com/maps/api/geocode/json?key=AIzaSyDCa7grnfoh0YLdDvkvusaTLMlBrMgEBu8&address=1600+Amphitheatre+Parkway%2C+Mountain+View%2C+CA")
		        .build();
		    
		    
		

		    Response response = client.newCall(request).execute();
		    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

		    Headers responseHeaders = response.headers();
		    for (int i = 0; i < responseHeaders.size(); i++) {
		      System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
		    }

		    System.out.println(response.body().string());
		  
		
	}
	 public static String getText(String url) throws Exception {
	     try{   URL website = new URL(url);
	        URLConnection connection = website.openConnection();
	        BufferedReader in = new BufferedReader(
	                                new InputStreamReader(
	                                    connection.getInputStream()));

	        StringBuilder response = new StringBuilder();
	        String inputLine;

	        while ((inputLine = in.readLine()) != null) 
	            response.append(inputLine);

	        in.close();

	        return response.toString();}catch(Exception er){er.printStackTrace();return "";}
	    }

}
