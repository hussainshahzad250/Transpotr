package com.trux.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.xml.xpath.XPathFactory;

import org.xml.sax.InputSource;

import com.google.maps.GeoApiContext;
import com.google.maps.model.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import com.trux.model.GDistanceMatrix;

public class GoogleMapsUtils {
	public static GeoApiContext context = null;
	private static String API_KEY = "";// "AIzaSyAhrIuvUpKwEU1akjvZ7ASbHvliyNdpUO0";
	private static Map<String, LatLng> locationCache = new HashMap<String, LatLng>();

	public static GeoApiContext getGeoApiContextInstance() {

		if (context == null)
			context = new GeoApiContext().setApiKey(API_KEY);

		return context;
	}

	public static LatLng getLatLongBasedOnAddress(String address) {

		try {
			LatLng latLng1 = locationCache.get(address);
			if (latLng1 != null)
				return latLng1;

			String url = "https://maps.googleapis.com/maps/api/geocode/xml?key=" + API_KEY + "&address="
					+ URLEncoder.encode(address, "UTF-8");
			// System.out.println(url);
			URL website = new URL(url);
			URLConnection connection = website.openConnection();

			InputStream is = connection.getInputStream();

			javax.xml.xpath.XPath xPath = XPathFactory.newInstance().newXPath();
			String location = "/GeocodeResponse/result/geometry/location";
			// String expressionLong =
			// "/GeocodeResponse/result/geometry/location/lng";

			InputSource inputSource = new InputSource(is);

			String lat = xPath.compile(location).evaluate(inputSource);
			// String lng = xPath.compile(expressionLong).evaluate(inputSource);
			String[] latlong = lat.trim().split("\n");

			is.close();

			LatLng latLng = new LatLng(new Double(latlong[0]), new Double(latlong[1]));
			// System.out.println("lat Lang :"+latLng.lat +" Long
			// :"+latLng.lng);
			locationCache.put(address, latLng);

			return latLng;
		} catch (Exception e) {
			new LatLng(28.5971151, 77.3782875);
		}
		return null;

		/*
		 * System.out.println(address); GeocodingResult[] results; try { results
		 * = GeocodingApi.geocode(getGeoApiContextInstance(), address).await();
		 * //GeocodingApi. // results =
		 * GeocodingApi.newRequest(getGeoApiContextInstance()).address(address).
		 * await(); System.out.println(results.toString());
		 * 
		 * System.out.println(results.length);
		 * System.out.println(results[0].geometry); LatLng location =
		 * results[0].geometry.location; return location; } catch (Exception e)
		 * { e.printStackTrace(); }
		 * 
		 * return null;
		 */
	}

	public static double getDistance(LatLng source, LatLng destination) {

		double distanceInKM = TruxUtils
				.roundTo1Decimals(LatLngTool.distance(new com.javadocmd.simplelatlng.LatLng(source.lat, source.lng),
						new com.javadocmd.simplelatlng.LatLng(destination.lat, destination.lng), LengthUnit.KILOMETER));

		return distanceInKM;
	}

	public static double getDistance(String source, String destination) {
		double distanceInKM = 0;
		try {
			LatLng sourceLatLong = getLatLongBasedOnAddress(source);
			LatLng destinationLatLong = getLatLongBasedOnAddress(destination);
			if (sourceLatLong != null && destinationLatLong != null) {
				distanceInKM = TruxUtils.roundTo1Decimals(1.2 * LatLngTool.distance(
						new com.javadocmd.simplelatlng.LatLng(sourceLatLong.lat, sourceLatLong.lng),
						new com.javadocmd.simplelatlng.LatLng(destinationLatLong.lat, destinationLatLong.lng),
						LengthUnit.KILOMETER));
			}
		} catch (Exception er) {
			er.printStackTrace();
		}
		return distanceInKM;

	}

	public static String getText(String url) throws Exception {
		URL website = new URL(url);
		URLConnection connection = website.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

		StringBuilder response = new StringBuilder();
		String inputLine;

		while ((inputLine = in.readLine()) != null)
			response.append(inputLine);

		in.close();

		return response.toString();
	}

	public static void main(String[] args) throws Exception {

		// GDistanceMatrix distanceMatrix=
		// getDistanceMatrix(getLatLongBasedOnAddress("4/98,Block 4, Subhash
		// Nagar,New Delhi, Delhi 110027"), getLatLongBasedOnAddress("Beriwala
		// Bagh, Mahakavi Goswami Tulsidas Marg, Hari Enclave, New Delhi, Delhi,
		// India"));
		// System.out.println(distanceMatrix.getDistanceValue());
		// LatLng sourceLatLng=new LatLng(28.97892, 77.97777);
		// getAddress(sourceLatLng);

		double total = getDistance("Village Mandoli New Delhi, Delhi 110093",
				"Cdc-Delhi-Mundka-Bb,Khasarano85/20/2,Galino7,Mundka,110041,Delhi,Delhi");
		System.out.println(total);
	}

	public static GDistanceMatrix getDistanceMatrix(String source, String destination) throws Exception {
		return getDistanceMatrix(getLatLongBasedOnAddress(source), getLatLongBasedOnAddress(destination));
	}

	public static GDistanceMatrix getDistanceMatrix(LatLng source, LatLng destination) throws Exception {
		try {// String url =
				// "https://maps.googleapis.com/maps/api/geocode/xml?key=AIzaSyAhrIuvUpKwEU1akjvZ7ASbHvliyNdpUO0&address="+URLEncoder.encode(address,
				// "UTF-8");
			String url = "https://maps.googleapis.com/maps/api/distancematrix/xml?key=" + API_KEY
					+ "&avoid=tolls&destinations=" + destination.lat + "%2C" + destination.lng + "&origins="
					+ source.lat + "%2C" + source.lng + "&mode=driving";
			System.out.println(url);
			URL website = new URL(url);
			URLConnection connection = website.openConnection();

			InputStream is = connection.getInputStream();

			javax.xml.xpath.XPath xPath = XPathFactory.newInstance().newXPath();
			String location = "/DistanceMatrixResponse/row/element";
			// String expressionLong =
			// "/GeocodeResponse/result/geometry/location/lng";

			InputSource inputSource = new InputSource(is);

			String xpathContent = xPath.compile(location).evaluate(inputSource);
			// String lng = xPath.compile(expressionLong).evaluate(inputSource);
			// String [] latlong = lat.trim().split("\n");
			String[] contents = xpathContent.split("\n");
			String[] cleanArr = new String[5];
			int counter = 0;
			for (int i = 0; i < contents.length; i++) {
				if (contents[i].trim().length() > 0) {
					cleanArr[counter++] = contents[i].trim();
				}

			}
			if (cleanArr != null) {
				try {
					GDistanceMatrix distanceMatrix = new GDistanceMatrix();
					if (cleanArr[1] != null) {
						distanceMatrix.setDurationValue(Integer.parseInt(cleanArr[1]));
					}
					if (cleanArr[2] != null) {
						distanceMatrix.setDurationText(cleanArr[2]);
					}
					if (cleanArr[3] != null) {
						distanceMatrix.setDistanceValue(Integer.parseInt(cleanArr[3]));
					}
					if (cleanArr[4] != null) {
						distanceMatrix.setDistanceText(cleanArr[4]);
					}

					System.out.println(cleanArr);

					is.close();

					return distanceMatrix;
				} catch (Exception er) {
				}
			}
			return null;
		} catch (Exception er) {
			er.printStackTrace();
			throw er;
		}
	}

	public static String getAddress(LatLng source) {
		try {

			String url = "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + source.lat + "," + source.lng
					+ "&sensor=true";
			System.out.println(url);
			URL website = new URL(url);
			URLConnection connection = website.openConnection();
			/*
			 * InputStream is = connection.getInputStream(); JSONObject res=new
			 * JSONObject(response);
			 * 
			 * JsonArray arrResults=res.getJSONArray("results");
			 */
			javax.xml.xpath.XPath xPath = XPathFactory.newInstance().newXPath();
			String location = "/DistanceMatrixResponse/row/element";
			// String expressionLong =
			// "/GeocodeResponse/result/geometry/location/lng";

			/*
			 * javax.xml.xpath.XPath xPath =
			 * XPathFactory.newInstance().newXPath(); String location =
			 * "/DistanceMatrixResponse/row/element"; // String expressionLong =
			 * "/GeocodeResponse/result/geometry/location/lng";
			 */
			/*
			 * InputSource inputSource = new InputSource(is);
			 * System.out.println(inputSource.getEncoding());
			 */

			return "";
		} catch (Exception er) {
		}

		return null;

	}

}
