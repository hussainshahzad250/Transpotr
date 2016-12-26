package com.trux.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import com.pusher.rest.Pusher;
import com.trux.controller.ConsumerController;
import com.trux.enumerations.TruxErrorCodes;
import com.trux.model.BookingNotificationAudit;
import com.trux.model.CurrentVehicleLocation;
import com.trux.model.CustomerBookingDetails;
import com.trux.model.DeviceRegistration;
import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.OrganizationalClientBookingDetails;
import com.trux.pricing.PricingModel;
import com.trux.service.VehicleLocationService;

public class PusherUtil {

	private static Map<Integer, List<String>> driverNotifiedMap = new HashMap<Integer, List<String>>();
	private static Map<String, String> driverPhoneNumberAndDeviceUUId = new HashMap<String, String>();

	
	public static void notifyParticularTruxs(CustomerBookingDetails customerBookingDetails, DriverDeviceVehicleMapping deviceVehicleMapping) {
		try{

		Pusher pusher = new Pusher("112710", "cf13f940fb01e32bdc08", "c223979dcc6f58cde8a4");
		pusher.setRequestTimeout(20*1000);
		List<String> channels = new ArrayList<String>();
		String deviceId = deviceVehicleMapping.getDeviceUUID();
		int indexOf_ = deviceId.lastIndexOf("_");
		String deviceUUID = deviceId.substring(indexOf_+1, deviceId.length());
	    channels.add("booking_channel_"+deviceUUID);

		
		Gson gson = new Gson();
		String jsonPayload = gson.toJson(customerBookingDetails);
	//	System.out.println("{\"bookingId\":".replaceAll("\\\\", ""));
	//	System.out.println(jsonPayload);
		pusher.trigger(channels, "booking_event", Collections.singletonMap("message", jsonPayload));

		}catch(Exception er){er.printStackTrace();}
	
		
	}
	
	
	public static void main(String[] args) {
		/*
		Pusher pusher = new Pusher("112710", "cf13f940fb01e32bdc08", "c223979dcc6f58cde8a4");
		pusher.setRequestTimeout(20*1000);
		List<String> channels = new ArrayList<String>();
		
	
	    channels.add("booking_channel_"+"49e3e5a2be8fbf09");

		
		Gson gson = new Gson();
	///	String jsonPayload = gson.toJson(customerBookingDetails);
		String jsonPayload = gson.toJson(new CustomerBookingDetails());

	//	System.out.println("{\"bookingId\":".replaceAll("\\\\", ""));
	//	System.out.println(jsonPayload);
		pusher.trigger(channels, "booking_event", Collections.singletonMap("message", jsonPayload));
		*/
		
		Pusher pusher = new Pusher("112710", "cf13f940fb01e32bdc08", "c223979dcc6f58cde8a4");
		pusher.setRequestTimeout(20*1000);
		List<String> channels = new ArrayList<String>();
      for(int i = 0 ;i < 33; i++) {
    	  channels.add("krishna"+i);
      }
		
	     System.out.println("Channels added: "+channels.size());
		
		int channelBuckets  = channels.size()/10;
		
		for(int i=0; i <=channelBuckets; i++) {
			List<String> channelbucket = new ArrayList<String>();

			for(int j=10*i; (j < 10*(i+1) && j< channels.size()); j++) {
				channelbucket.add(channels.get(j));
			}
			
			System.out.println(channelbucket);
			
			pusher.trigger(channelbucket, "booking_event", Collections.singletonMap("message", "Hi Krishna"));

		}
		
	}

	public void notifyTruxs(List<DriverDeviceVehicleMapping> allRegisteredDeviceList, CustomerBookingDetails customerBookingDetails, VehicleLocationService vehicleLocationService) {
	try{Pusher pusher = new Pusher("112710", "cf13f940fb01e32bdc08", "c223979dcc6f58cde8a4");
		List<String> notifiedDriverList = new ArrayList<String>();
	
	   pusher.setRequestTimeout(20*1000);
		
		
		List<String> channels = new ArrayList<String>();
		
			// create separate channel for each driver who can accept the request
		
		for(int i = 0 ; i< allRegisteredDeviceList.size(); i++) {
			DriverDeviceVehicleMapping deviceVehicleMapping =	allRegisteredDeviceList.get(i);
			System.out.println("--------------------------------------------");
			System.out.println(deviceVehicleMapping.getVehicleType());
			System.out.println(customerBookingDetails.getVehicleType());
			if(deviceVehicleMapping.getVehicleType()!=null && deviceVehicleMapping.getVehicleType().equalsIgnoreCase(customerBookingDetails.getVehicleType())){
			CurrentVehicleLocation currentVehicleLocation =	vehicleLocationService.fetchVehicleLocation(deviceVehicleMapping.getDriverPhoneNumber().trim());
			
			System.out.println("Vehicle Location for Driver "+ deviceVehicleMapping.getDriverPhoneNumber() + " vehicleLocation "+ currentVehicleLocation);
			
			if(currentVehicleLocation == null)
				continue ;
			
			double consumerDistanceFromTruck =  getTruckDistance(currentVehicleLocation, customerBookingDetails);
			
			
			System.out.println("Distance of truck from consumer "+ consumerDistanceFromTruck + " Booking id" + customerBookingDetails.getBookingId());
			
			if(consumerDistanceFromTruck > ConsumerController.DISTANCE_THRESHOLD_LIMIT)
				continue;
			
			
			
			String deviceId = allRegisteredDeviceList.get(i).getDeviceUUID();
			int indexOf_ = deviceId.lastIndexOf("_");
			String deviceUUID = deviceId.substring(indexOf_+1, deviceId.length());
			notifiedDriverList.add(deviceUUID);
			driverPhoneNumberAndDeviceUUId.put(allRegisteredDeviceList.get(i).getDriverPhoneNumber(), deviceUUID);
			System.out.println("Adding device to sending pusher list"+deviceUUID+ " Booking id" + customerBookingDetails.getBookingId());
			
			try {
			BookingNotificationAudit bookingNotificationAudit = new BookingNotificationAudit();
			bookingNotificationAudit.setBookingid(customerBookingDetails.getBookingId());
			bookingNotificationAudit.setDeviceId(deviceId);
			bookingNotificationAudit.setDriverPhonenumber(allRegisteredDeviceList.get(i).getDriverPhoneNumber());
			bookingNotificationAudit.setConsumerDistanceFromTruck(consumerDistanceFromTruck);
			bookingNotificationAudit.setDriverName(allRegisteredDeviceList.get(i).getDriverName());
			bookingNotificationAudit.setCreatedDate(new Date());
			bookingNotificationAudit.setUpdatedDate(new Date());
			bookingNotificationAudit.setDriverStatus(allRegisteredDeviceList.get(i).getDriverStatus()+"");
			bookingNotificationAudit.setVehicleType(allRegisteredDeviceList.get(i).getVehicleType());
			vehicleLocationService.save(bookingNotificationAudit);
			
			} catch (Exception e ) {
				e.printStackTrace();
			}
			
		    channels.add("booking_channel_"+deviceUUID);
			}
		}
			
		
		//BookingPusherPayload bookingPusherPayload = new BookingPusherPayload(customerBookingDetails.getBookingId(), customerBookingDetails.getFromLocation(), customerBookingDetails.getToLocation(), new Date(), new Date(), customerBookingDetails.getTotalFare(), customerBookingDetails.getTotalDistanceTravelled(),customerBookingDetails.getFromLat(), customerBookingDetails.getFromLong(), customerBookingDetails.getToLat(), customerBookingDetails.getToLong() );
		
		Gson gson = new Gson();
		String jsonPayload = gson.toJson(customerBookingDetails);
	//	System.out.println("{\"bookingId\":".replaceAll("\\\\", ""));
	//	System.out.println(jsonPayload);
		
		
		System.out.println("Channels added: "+channels.size());
		
		int channelBuckets  = channels.size()/10;
		
		for(int i=0; i <=channelBuckets;i ++) {
			List<String> channelbucket = new ArrayList<String>();


			for(int j=10*i; (j < 10*(i+1) && j< channels.size()); j++) {
				channelbucket.add(channels.get(j));
			}
			
		
			
			pusher.trigger(channelbucket, "booking_event", Collections.singletonMap("message", jsonPayload));

		}
		
		
		
		driverNotifiedMap.put(customerBookingDetails.getBookingId(), notifiedDriverList);
	}catch(Exception er){er.printStackTrace();}	
	}
	
	
	public void notifyOrgTruxs(List<DriverDeviceVehicleMapping> allRegisteredDeviceList, OrganizationalClientBookingDetails customerBookingDetails, VehicleLocationService vehicleLocationService) {
		try{Pusher pusher = new Pusher("112710", "cf13f940fb01e32bdc08", "c223979dcc6f58cde8a4");
			List<String> notifiedDriverList = new ArrayList<String>();
		
		   pusher.setRequestTimeout(20*1000);
			
			
			List<String> channels = new ArrayList<String>();
			
				// create separate channel for each driver who can accept the request
			
			for(int i = 0 ; i< allRegisteredDeviceList.size(); i++) {
				DriverDeviceVehicleMapping deviceVehicleMapping =	allRegisteredDeviceList.get(i);
				System.out.println("--------------------------------------------");
				System.out.println(deviceVehicleMapping.getVehicleType());
				System.out.println(customerBookingDetails.getVehicleType());
				if(deviceVehicleMapping.getVehicleType().equalsIgnoreCase(customerBookingDetails.getVehicleType())){
				CurrentVehicleLocation currentVehicleLocation =	vehicleLocationService.fetchVehicleLocation(deviceVehicleMapping.getDriverPhoneNumber().trim());
				
				System.out.println("Vehicle Location for Driver "+ deviceVehicleMapping.getDriverPhoneNumber() + " vehicleLocation "+ currentVehicleLocation);
				
				if(currentVehicleLocation == null)
					continue ;
				
				double consumerDistanceFromTruck =  getOrgTruckDistance(currentVehicleLocation, customerBookingDetails);
				
				
				System.out.println("Distance of truck from consumer "+ consumerDistanceFromTruck + " Booking id" + customerBookingDetails.getBookingId());
				
				if(consumerDistanceFromTruck > ConsumerController.DISTANCE_THRESHOLD_LIMIT)
					continue;
				
				
				
				String deviceId = allRegisteredDeviceList.get(i).getDeviceUUID();
				int indexOf_ = deviceId.lastIndexOf("_");
				String deviceUUID = deviceId.substring(indexOf_+1, deviceId.length());
				notifiedDriverList.add(deviceUUID);
				driverPhoneNumberAndDeviceUUId.put(allRegisteredDeviceList.get(i).getDriverPhoneNumber(), deviceUUID);
				System.out.println("Adding device to sending pusher list"+deviceUUID+ " Booking id" + customerBookingDetails.getBookingId());
				
				try {
				BookingNotificationAudit bookingNotificationAudit = new BookingNotificationAudit();
				bookingNotificationAudit.setBookingid(customerBookingDetails.getBookingId());
				bookingNotificationAudit.setDeviceId(deviceId);
				bookingNotificationAudit.setDriverPhonenumber(allRegisteredDeviceList.get(i).getDriverPhoneNumber());
				bookingNotificationAudit.setConsumerDistanceFromTruck(consumerDistanceFromTruck);
				bookingNotificationAudit.setDriverName(allRegisteredDeviceList.get(i).getDriverName());
				bookingNotificationAudit.setCreatedDate(new Date());
				bookingNotificationAudit.setUpdatedDate(new Date());
				bookingNotificationAudit.setDriverStatus(allRegisteredDeviceList.get(i).getDriverStatus()+"");
				bookingNotificationAudit.setVehicleType(allRegisteredDeviceList.get(i).getVehicleType());
				vehicleLocationService.save(bookingNotificationAudit);
				
				} catch (Exception e ) {
					e.printStackTrace();
				}
				
			    channels.add("booking_channel_"+deviceUUID);
				}
			}
				
			
			//BookingPusherPayload bookingPusherPayload = new BookingPusherPayload(customerBookingDetails.getBookingId(), customerBookingDetails.getFromLocation(), customerBookingDetails.getToLocation(), new Date(), new Date(), customerBookingDetails.getTotalFare(), customerBookingDetails.getTotalDistanceTravelled(),customerBookingDetails.getFromLat(), customerBookingDetails.getFromLong(), customerBookingDetails.getToLat(), customerBookingDetails.getToLong() );
			
			Gson gson = new Gson();
			String jsonPayload = gson.toJson(customerBookingDetails);
		//	System.out.println("{\"bookingId\":".replaceAll("\\\\", ""));
		//	System.out.println(jsonPayload);
			
			
			System.out.println("Channels added: "+channels.size());
			
			int channelBuckets  = channels.size()/10;
			
			for(int i=0; i <=channelBuckets;i ++) {
				List<String> channelbucket = new ArrayList<String>();


				for(int j=10*i; (j < 10*(i+1) && j< channels.size()); j++) {
					channelbucket.add(channels.get(j));
				}
				
				pusher.trigger(channelbucket, "booking_event", Collections.singletonMap("message", jsonPayload));

			}
			driverNotifiedMap.put(customerBookingDetails.getBookingId(), notifiedDriverList);
		}catch(Exception er){er.printStackTrace();}	
		}
	private double getTruckDistance(CurrentVehicleLocation currentVehicleLocation, CustomerBookingDetails customerBookingDetails) {
		try{
		LatLng firstUserLocation = new LatLng(currentVehicleLocation.getLatitude(), currentVehicleLocation.getLongitude());
		LatLng secondUserLocation = new LatLng(customerBookingDetails.getFromLat(), customerBookingDetails.getFromLong());
		double distanceInKM = TruxUtils.roundTo5Decimals(LatLngTool.distance(firstUserLocation, secondUserLocation, LengthUnit.KILOMETER));
		return distanceInKM;
		}catch(Exception er){return 0;}	
	}
 
	private double getOrgTruckDistance(CurrentVehicleLocation currentVehicleLocation, OrganizationalClientBookingDetails customerBookingDetails) {
		try{
		LatLng firstUserLocation = new LatLng(currentVehicleLocation.getLatitude(), currentVehicleLocation.getLongitude());
		LatLng secondUserLocation = new LatLng(customerBookingDetails.getFromLat(), customerBookingDetails.getFromLong());
		double distanceInKM = TruxUtils.roundTo5Decimals(LatLngTool.distance(firstUserLocation, secondUserLocation, LengthUnit.KILOMETER));
		return distanceInKM;
		}catch(Exception er){return 0;}	
	}
	public void notifyConsumerForBookingAndDriverForCancellation(CustomerBookingDetails customerBookingDetails) {
		
		// need to fix this
		
		try{
		Pusher pusher = new Pusher("112710", "cf13f940fb01e32bdc08", "c223979dcc6f58cde8a4");
	
		Integer bookingId = customerBookingDetails.getBookingId();
		
	    pusher.setRequestTimeout(10000);
		
		
		List<String> channels = new ArrayList<String>();
		
		String channelName = "consumer_booking_"+bookingId;
				channels.add(channelName);
			
				
		System.out.println("Pushing to channel "+channelName);		
		
	//	BookingPusherPayload bookingPusherPayload = new BookingPusherPayload(customerBookingDetails.getBookingId(), customerBookingDetails.getFromLocation(), customerBookingDetails.getToLocation(), new Date(), new Date(), customerBookingDetails.getTotalFare(), customerBookingDetails.getTotalDistanceTravelled(),customerBookingDetails.getFromLat(), customerBookingDetails.getFromLong(), customerBookingDetails.getToLat(), customerBookingDetails.getToLong() );
		
		Gson gson = new Gson();
		String jsonPayload = gson.toJson(customerBookingDetails);
	//	System.out.println("{\"bookingId\":".replaceAll("\\\\", ""));
	//	System.out.println(jsonPayload);
		pusher.trigger(channels, "booking_event", Collections.singletonMap("message", jsonPayload));
		
		
		
		
		String driverPhoneNumber = customerBookingDetails.getDriverPhonenumber();
		String deviceUUID = driverPhoneNumberAndDeviceUUId.get(driverPhoneNumber);
		
		List<String> notifiedDriversList = driverNotifiedMap.get(customerBookingDetails.getBookingId());
		
		
		if(notifiedDriversList == null)
			return;
		
		List<String> cancellationChannels = new ArrayList<String>();
		for(int i = 0 ; i < notifiedDriversList.size();i++) {
			String currentUUID = notifiedDriversList.get(i);
			if(!deviceUUID.equals(currentUUID)){
				cancellationChannels.add("booking_channel_"+currentUUID);
			}
			else {
				System.out.println("Leaving driver "+driverPhoneNumber+" with Device UUID: "+deviceUUID);
			}
		}
		
		CustomerBookingDetails customerBookingDetails2 = null;
		
		 try {
			customerBookingDetails2 =  (CustomerBookingDetails)customerBookingDetails.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 customerBookingDetails2.setErrorCode(TruxErrorCodes.BOOKING_ACCEPTEDBYSOME_OTHER_DRIVER.getCode());
		 customerBookingDetails2.setErrorMessage(TruxErrorCodes.BOOKING_ACCEPTEDBYSOME_OTHER_DRIVER.getDescription());
		jsonPayload = gson.toJson(customerBookingDetails2);
		
		
		
	int channelBuckets  = cancellationChannels.size()/10;
		
		for(int i=0; i <=channelBuckets;i ++) {
			List<String> channelbucket = new ArrayList<String>();


			for(int j=10*i; (j < 10*(i+1) && j< cancellationChannels.size()); j++) {
				channelbucket.add(cancellationChannels.get(j));
			}
			
	 		pusher.trigger(channelbucket, "cancellation_event", Collections.singletonMap("message", jsonPayload));

			
		//	pusher.trigger(channelbucket, "booking_event", Collections.singletonMap("message", jsonPayload));

		}
		
		
		
		}catch(Exception er){er.printStackTrace();}	
		
	}

	public void notifyTruxForCancellation(CustomerBookingDetails customerBooking) {

    try{
		

		Pusher pusher = new Pusher("112710", "cf13f940fb01e32bdc08", "c223979dcc6f58cde8a4");
	
	    pusher.setRequestTimeout(10000);
		
		
		List<String> channels = new ArrayList<String>();
		
		
				channels.add("cancell_booking");
			
		
	//	BookingPusherPayload bookingPusherPayload = new BookingPusherPayload(customerBookingDetails.getBookingId(), customerBookingDetails.getFromLocation(), customerBookingDetails.getToLocation(), new Date(), new Date(), customerBookingDetails.getTotalFare(), customerBookingDetails.getTotalDistanceTravelled(),customerBookingDetails.getFromLat(), customerBookingDetails.getFromLong(), customerBookingDetails.getToLat(), customerBookingDetails.getToLong() );
		
		Gson gson = new Gson();
		String jsonPayload = gson.toJson(customerBooking);
	//	System.out.println("{\"bookingId\":".replaceAll("\\\\", ""));
	//	System.out.println(jsonPayload);
		pusher.trigger(channels, "cancell_event", Collections.singletonMap("message", jsonPayload));
	
}catch(Exception er){er.printStackTrace();}	
	
	
	}
}
