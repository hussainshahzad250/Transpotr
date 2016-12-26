package com.trux.utils;


import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

public class Click2CallUtils {
	
	private static final String CLICK2CALL_URL = "https://twilix.exotel.in/v1/Accounts/truxapp/Calls/connect";
	private static final String CALLER_ID = "01203843019";
	private static final String CALL_TYPE = "trans";
	private static final String TRUXAPP_SID = "truxapp";
	private static final String TRUXAPP_TOKEN = "f7b537f76164c24f68f5213a3ed3de1054565529";

	public static void main(String[] args) throws Exception{
		initiateCall("09899016401", "09560846172");
		//whiteListNumber("8876557652");
	}

	
	
	
public static void whiteListNumber(String whiteListPhoneNumber) throws Exception {
try{
	  whiteListPhoneNumber = "0"+whiteListPhoneNumber;
	  SSLContext sslContext = SSLContext.getInstance("SSL");
	  
    // set up a TrustManager that trusts everything
    sslContext.init(null, new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                System.out.println("getAcceptedIssuers =============");
                return null;
            }

            public void checkClientTrusted(X509Certificate[] certs,
                                           String authType) {
                System.out.println("checkClientTrusted =============");
            }

            public void checkServerTrusted(X509Certificate[] certs,
                                           String authType) {
                System.out.println("checkServerTrusted =============");
            }

            public boolean isClientTrusted(X509Certificate[] arg0) {
                return false;
            }

            public boolean isServerTrusted(X509Certificate[] arg0) {
                return false;
            }
        } }, new SecureRandom());

    SSLSocketFactory sf = new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
    Scheme httpsScheme = new Scheme("https", sf, 443);
    SchemeRegistry schemeRegistry = new SchemeRegistry();
    schemeRegistry.register(httpsScheme);

    HttpParams params = new BasicHttpParams();
    ClientConnectionManager cm = new SingleClientConnManager(params, schemeRegistry);

    DefaultHttpClient client = new DefaultHttpClient(cm, params);

    //Replace "<Exotel SID>" and "<Exotel Token>" with your SID and Token
    client.getCredentialsProvider().setCredentials(
                                                   new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
                                                   new UsernamePasswordCredentials(TRUXAPP_SID, TRUXAPP_TOKEN)
                                                   );
    HttpPost post = new HttpPost("https://truxapp:f7b537f76164c24f68f5213a3ed3de1054565529@twilix.exotel.in/v1/Accounts/truxapp/CustomerWhitelist/");
    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);

    /* 
	*Replace the text enclosed in < > with your desired values
	*The options for CallType are "trans" for transactional call and "promo" for promotional call
    */
    nameValuePairs.add(new BasicNameValuePair("VirtualNumber", "08088919888"));
    nameValuePairs.add(new BasicNameValuePair("Number", whiteListPhoneNumber));
 
	/* Optional params
	nameValuePairs.add(new BasicNameValuePair("TimeLimit", "<time-in-seconds>"));
    nameValuePairs.add(new BasicNameValuePair("TimeOut", "<time-in-seconds>"));
    nameValuePairs.add(new BasicNameValuePair("StatusCallback", "<http//: your company URL>"));
	*/

    post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
    ResponseHandler<String> responseHandler=new BasicResponseHandler();
    String response = client.execute(post, responseHandler);
    System.out.println(response);
}catch(Exception er){er.printStackTrace(); throw er;}

}	
	
	
public static void initiateCall(String fromNumber, String toNumber)  throws Exception {
	try{
	  SSLContext sslContext = SSLContext.getInstance("SSL");
	  
      // set up a TrustManager that trusts everything
      sslContext.init(null, new TrustManager[] { new X509TrustManager() {
              public X509Certificate[] getAcceptedIssuers() {
                  System.out.println("getAcceptedIssuers =============");
                  return null;
              }

              public void checkClientTrusted(X509Certificate[] certs,
                                             String authType) {
                  System.out.println("checkClientTrusted =============");
              }

              public void checkServerTrusted(X509Certificate[] certs,
                                             String authType) {
                  System.out.println("checkServerTrusted =============");
              }

              public boolean isClientTrusted(X509Certificate[] arg0) {
                  return false;
              }

              public boolean isServerTrusted(X509Certificate[] arg0) {
                  return false;
              }
          } }, new SecureRandom());

      SSLSocketFactory sf = new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      Scheme httpsScheme = new Scheme("https", sf, 443);
      SchemeRegistry schemeRegistry = new SchemeRegistry();
      schemeRegistry.register(httpsScheme);

      HttpParams params = new BasicHttpParams();
      ClientConnectionManager cm = new SingleClientConnManager(params, schemeRegistry);

      DefaultHttpClient client = new DefaultHttpClient(cm, params);

      //Replace "<Exotel SID>" and "<Exotel Token>" with your SID and Token
      client.getCredentialsProvider().setCredentials(
                                                     new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT),
                                                     new UsernamePasswordCredentials(TRUXAPP_SID, TRUXAPP_TOKEN)
                                                     );
      HttpPost post = new HttpPost("https://twilix.exotel.in/v1/Accounts/"+TRUXAPP_SID+"/Calls/connect");
      List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);

      /* 
	*Replace the text enclosed in < > with your desired values
	*The options for CallType are "trans" for transactional call and "promo" for promotional call
      */
      nameValuePairs.add(new BasicNameValuePair("From", fromNumber));
      nameValuePairs.add(new BasicNameValuePair("To", toNumber));
      nameValuePairs.add(new BasicNameValuePair("CallerId", CALLER_ID));
      nameValuePairs.add(new BasicNameValuePair("CallType", CALL_TYPE));
	/* Optional params
	nameValuePairs.add(new BasicNameValuePair("TimeLimit", "<time-in-seconds>"));
      nameValuePairs.add(new BasicNameValuePair("TimeOut", "<time-in-seconds>"));
      nameValuePairs.add(new BasicNameValuePair("StatusCallback", "<http//: your company URL>"));
	*/

      post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
      ResponseHandler<String> responseHandler=new BasicResponseHandler();
      String response = client.execute(post, responseHandler);
      System.out.println(response);
	}catch(Exception er) {er.printStackTrace(); throw er;}
}
}
