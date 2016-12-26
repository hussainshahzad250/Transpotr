package config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.apache.commons.httpclient.util.URIUtil;

import com.google.maps.model.LatLng;
import com.trux.utils.DateFormaterUtils;
import com.trux.utils.GoogleMapsUtils;
 

public class TestMain {
public static void main(String[] args) {
	
	//Latitude: 28.518068333333336 Longitude: 77.19882333333334
/*String lat=	"Latitude:";
String log=	"Longitude:";

	String latlong="Latitude: 28.517786666666666 Longitude: 77.19914999999999 Address:";
	int indexlat=latlong.indexOf("Latitude:");
    int indexlog=latlong.indexOf("Longitude:");
	 String source=latlong.substring(indexlat+lat.length(),indexlog);
	 System.out.println(source.trim()); 

	 int address=latlong.indexOf("Address:");
    String sourceLog=latlong.substring(indexlog+log.length(),address);
     
    System.out.println(sourceLog.trim()); */
	
	String  afterDropStartTimes= DateFormaterUtils.convertGMTToISTWithDatesWithSlash(new Date().toString()).replace(".0", "");
    System.out.println(afterDropStartTimes.substring(0,10));
 
	
}
}
