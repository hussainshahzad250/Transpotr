package com.trux.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.FileRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

public class Testing {
public static void main(String[] args) {
	String strURL = "https://interface.demo.gta-travel.com/wbsapi/RequestListenerServlet"; //args[0];
    // Get file to be posted
    String strXMLFilename = "/Users/abc/trux/trux-api/trux-api/testrequest1";
    File input = new File(strXMLFilename);
    // Prepare HTTP post
    PostMethod post = new PostMethod(strURL);
    // Request content will be retrieved directly
    // from the input stream
    RequestEntity entity = new FileRequestEntity(input, "text/xml; charset=ISO-8859-1");
    post.setRequestEntity(entity);
    // Get HTTP client
    HttpClient httpclient = new HttpClient();
    // Execute request
    try {
        int result = httpclient.executeMethod(post);
        // Display status code
        System.out.println("Response status code: " + result);
        // Display response
        System.out.println("Response body: ");
     //  InputStream is = post.getResponseBody();
       Header[]  headers = post.getResponseHeaders();
       for (Header header : headers) {
		
    	   System.out.println("header name: "+ header.getName()+ " header value:   "+ header.getValue());
	}

        
       InputStream is = post.getResponseBodyAsStream();
       post.getResponseHeaders();
       File targetFile = new File("/Users/abc/trux/trux-api/trux-api/responselatest_EN.zip");
       OutputStream outStream = new FileOutputStream(targetFile);
       
       byte[] buffer = new byte[1024];
       int len = is.read(buffer);
       while (len != -1) {
    	   outStream.write(buffer, 0, len);
           len = is.read(buffer);
       }
       
     //  System.out.println(buffer.length);
       
    //   is.read(buffer);
       
     
       outStream.flush();
       
      //  System.out.println(post.getResponseBodyAsString());
    }
    
    catch (Exception e ) {
    	e.printStackTrace();
    }
    
    finally {
        // Release current connection to the connection pool once you are done
        post.releaseConnection();
    }
}
}
