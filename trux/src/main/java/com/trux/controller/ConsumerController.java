 
package com.trux.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import com.trux.enumerations.TruxErrorCodes;
import com.trux.ip2location.IP2Location;
import com.trux.ip2location.IPResult;
import com.trux.model.ConsumerRegistration;
import com.trux.model.ConsumerRegistrationResponse;
import com.trux.model.CurrentVehicleLocation;
import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.ForgotPassword;
import com.trux.model.NearbyVehicles;
import com.trux.model.TruckLocation;
import com.trux.service.ConsumerService;
import com.trux.service.RegistrationService;
import com.trux.service.VehicleLocationService;
import com.trux.utils.Click2CallUtils;
import com.trux.utils.TruxUtils;

@Controller
@RequestMapping(value="/consumer")
public class ConsumerController {

	public final static  int DISTANCE_THRESHOLD_LIMIT = 15;
	@Autowired
	private ConsumerService consumerService;
	
	
	@Autowired
	private RegistrationService registrationService;
	
	
	@Autowired
	private VehicleLocationService vehicleLocationService;
	
	
	@ResponseBody
	@RequestMapping(value="/registerconsumer", method=RequestMethod.POST)
	private ConsumerRegistration registration(HttpServletRequest request,HttpServletResponse response, @RequestParam String email,
							@RequestParam String password, @RequestParam Integer usertype,  @RequestParam String phonenumber, @RequestParam(required=false) String firstname, 
							@RequestParam(required=false) String lastname){
		try{
		
		if(email == null)
			return new ConsumerRegistration(TruxErrorCodes.EMAIL_NOT_VALID.getCode(), TruxErrorCodes.EMAIL_NOT_VALID.getDescription());
		
		if(password == null)
			return new ConsumerRegistration(TruxErrorCodes.INVALID_PASSWORD.getCode(), TruxErrorCodes.INVALID_PASSWORD.getDescription());
		
		if(phonenumber == null)
			return new ConsumerRegistration(TruxErrorCodes.INVALID_PHONENUBER.getCode(), TruxErrorCodes.INVALID_PHONENUBER.getDescription());
		
		ConsumerRegistration userDetail = consumerService.getUserDetailsByPhoneNumber(phonenumber);
		
		if(userDetail != null)
        	return new ConsumerRegistration(TruxErrorCodes.PHONE_NO_ALREADY_REGISTERED.getCode(), TruxErrorCodes.PHONE_NO_ALREADY_REGISTERED.getDescription());

		
		String encyptedPassword = TruxUtils.computeMD5Hash(password);
		
		userDetail = new ConsumerRegistration(email, firstname, lastname, phonenumber, encyptedPassword, usertype, null);
		
		
		 try{
			 consumerService.saveOrUpdate(userDetail);
			 try {
			 Click2CallUtils.whiteListNumber(phonenumber);
			 } catch(Exception e) {
				 
				 userDetail.setErrorMessage("Unable to WhiteList Phone Number.");
			 }
	        }catch(Exception e){
	        	e.printStackTrace();
	        	return new ConsumerRegistration(TruxErrorCodes.UNHANDLED_ERROR.getCode(), TruxErrorCodes.UNHANDLED_ERROR.getDescription());
	        }
		
		
		return userDetail;
		}catch(Exception er){er.printStackTrace();return new ConsumerRegistration(TruxErrorCodes.UNHANDLED_ERROR.getCode(), TruxErrorCodes.UNHANDLED_ERROR.getDescription());}
	}
	
	@ResponseBody
	@RequestMapping(value="/updateProfile", method=RequestMethod.POST)
	private ConsumerRegistration updateProfile(HttpServletRequest request,HttpServletResponse response,@RequestParam(required=false) String email,
				@RequestParam(required=false) String phonenumber, 
									@RequestParam(required=false) String firstname, 
							@RequestParam(required=false) String lastname){
		
		
		ConsumerRegistration userDetail = consumerService.getUserDetailsByEmail(email);
		try{
		if(userDetail != null){
			
			if(firstname != null)
				userDetail.setFirstname(firstname);
			
			if(lastname != null)
				userDetail.setLastname(lastname);
			
			if(phonenumber != null)
				userDetail.setPhoneNumber(phonenumber);
		
			try{
				 consumerService.saveOrUpdate(userDetail);
				 return userDetail;
		        }catch(Exception e){
		        	e.printStackTrace();
		        	return new ConsumerRegistration(TruxErrorCodes.FAILED_TO_UPDATE_PROFILE.getCode(), TruxErrorCodes.FAILED_TO_UPDATE_PROFILE.getDescription());
		        }
		}
		
		return new ConsumerRegistration(TruxErrorCodes.FAILED_TO_UPDATE_PROFILE.getCode(), TruxErrorCodes.FAILED_TO_UPDATE_PROFILE.getDescription());
		}catch(Exception er){er.printStackTrace();return new ConsumerRegistration(TruxErrorCodes.FAILED_TO_UPDATE_PROFILE.getCode(), TruxErrorCodes.FAILED_TO_UPDATE_PROFILE.getDescription());}
		}
	
	@ResponseBody
	@RequestMapping(value="/changePassword", method=RequestMethod.POST)
	private ConsumerRegistration changePassword(HttpServletRequest request,HttpServletResponse response,@RequestParam String email,
			@RequestParam String oldpassword, @RequestParam String newpassword){
		try{
		if(email == null)
			return new ConsumerRegistration(TruxErrorCodes.EMAIL_NOT_VALID.getCode(), TruxErrorCodes.EMAIL_NOT_VALID.getDescription());
		
		if(oldpassword == null)
			return new ConsumerRegistration(TruxErrorCodes.INVALID_OLD_PASSWORD.getCode(), TruxErrorCodes.INVALID_OLD_PASSWORD.getDescription());
		
		if(newpassword == null)
			return new ConsumerRegistration(TruxErrorCodes.INVALID_NEW_PASSWORD.getCode(), TruxErrorCodes.INVALID_NEW_PASSWORD.getDescription());
		
		ConsumerRegistration userDetail = consumerService.getUserDetailsByEmail(email);
		
		if(userDetail != null){
			
			if(TruxUtils.computeMD5Hash(oldpassword).equals(userDetail.getPassword())){
				userDetail.setPassword(TruxUtils.computeMD5Hash(newpassword));
				try{
					 consumerService.saveOrUpdate(userDetail);
					 return userDetail;
			        }catch(Exception e){
			        	e.printStackTrace();
			        	return new ConsumerRegistration(TruxErrorCodes.FAILED_TO_RESET_PASSWORD.getCode(), TruxErrorCodes.FAILED_TO_RESET_PASSWORD.getDescription());
			        }
			}else{
				ConsumerRegistration consumer = new ConsumerRegistration();
				consumer.setEmail(email);
				consumer.setErrorCode(TruxErrorCodes.INVALID_OLD_PASSWORD.getCode());
				consumer.setErrorMessage(TruxErrorCodes.INVALID_OLD_PASSWORD.getDescription());
				return consumer;
			}
		}
		
		return new ConsumerRegistration(TruxErrorCodes.INVALID_USER.getCode(), TruxErrorCodes.INVALID_USER.getDescription());
	}catch(Exception er){er.printStackTrace();return new ConsumerRegistration(TruxErrorCodes.INVALID_USER.getCode(), TruxErrorCodes.INVALID_USER.getDescription());}
	}
	
	@ResponseBody
	@RequestMapping(value = "/validateconsumer", method = RequestMethod.POST)
	public ConsumerRegistration validateUser(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam String phonenumber, @RequestParam String password) throws Exception {
		try{
		if(phonenumber == null)
			return new ConsumerRegistration(TruxErrorCodes.PHONE_NOT_VALID.getCode(), TruxErrorCodes.PHONE_NOT_VALID.getDescription());
		
		if(password == null)
			return new ConsumerRegistration(TruxErrorCodes.INVALID_PASSWORD.getCode(), TruxErrorCodes.INVALID_PASSWORD.getDescription());
		
		
		 IP2Location loc = new IP2Location();
    	//  loc.IPDatabasePath = "C:\\Program Files\\Apache Software Foundation\\Tomcat 7.0\\bin\\IP-COUNTRY-REGION-CITY-ISP.BIN";//"E:\\programming-tools\\apache-tomcat-7.0.55\\IP-COUNTRY-REGION-CITY-ISP.BIN";
            loc.IPDatabasePath = "/home/ec2-user/tomcat/apache-tomcat-7.0.57/bin/IP-COUNTRY-REGION-CITY-ISP.BIN";
    	      
            boolean isGeoAllowed =  true;
    	 try{
         String ipAddress = TruxUtils.getIPAddress(request);
         IPResult rec = loc.IPQuery(ipAddress);
         isGeoAllowed = checkIfGeoIsAllowed(rec, ipAddress);
    	 }catch(Exception er){}
          
         
         if(!isGeoAllowed) {
        	 
        	 ConsumerRegistration consumerRegistration = new   ConsumerRegistration(TruxErrorCodes.GEO_NOT_ALLOWED.getCode(), TruxErrorCodes.GEO_NOT_ALLOWED.getDescription());
        	 consumerRegistration.setUserValidationFlag(false);
        	  return consumerRegistration;
 			 //GEO_NOT_ALLOWED
         }
         
         
         
		
		
		ConsumerRegistration userDetail = consumerService.getUserDetailsByPhoneNumber(phonenumber);
		
		boolean isValidUser = false;
		
		
		if(userDetail != null){
			if(userDetail.getPassword().equals(TruxUtils.computeMD5Hash(password))){
				isValidUser = true;
			}
			
			if(isValidUser){
				userDetail.setUserValidationFlag(isValidUser);
				userDetail.setPassword("***********");
				return userDetail;
			}else{
				ConsumerRegistration invaliUserDetail = new ConsumerRegistration();
				invaliUserDetail.setPhoneNumber((phonenumber));
				invaliUserDetail.setUserValidationFlag(false);
				invaliUserDetail.setErrorCode(TruxErrorCodes.INVALID_PASSWORD.getCode());
				invaliUserDetail.setErrorMessage(TruxErrorCodes.INVALID_PASSWORD.getDescription());
				return invaliUserDetail;
			}
			
		}else{
			ConsumerRegistration consumerRegistration =  new ConsumerRegistration(TruxErrorCodes.USER_NOT_EXISTS.getCode(), TruxErrorCodes.USER_NOT_EXISTS.getDescription());
			consumerRegistration.setUserValidationFlag(false);
			return consumerRegistration ;
		}}catch(Exception er){er.printStackTrace();
		ConsumerRegistration consumerRegistration =  new ConsumerRegistration(TruxErrorCodes.USER_NOT_EXISTS.getCode(), TruxErrorCodes.USER_NOT_EXISTS.getDescription());
			consumerRegistration.setUserValidationFlag(false);
			return consumerRegistration ;}

	}
	
	
	private boolean checkIfGeoIsAllowed(IPResult rec, String ipAddress) {
		try{
		String[] allowedCities =  {"DELHI", "NEW DELHI", "GURGAON", "NOIDA", "Faridabad", "Ghaziabad"};
		
        //System.out.println("IP to Location "+"IP Address "+ipAddress+" : "+rec.getCity()+": " + rec.getAreaCode()+" : "+ rec.getISP()+" : "+ rec.getCountryShort());

		if(ipAddress == null)
			return true;
		if(rec == null)
			return true;
		
    	String userCity = rec.getCity();
		
		if(userCity == null )
			return true;
		
		
		for (String city : allowedCities) {
			if(city.equalsIgnoreCase(userCity))
				return true;
		}
		
		
		return false;
		}catch(Exception er){er.printStackTrace();return false;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public ForgotPassword forgotpassword(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam String email) throws Exception {
		try{
		if(email == null)
			return new ForgotPassword(TruxErrorCodes.EMAIL_NOT_VALID.getCode(), TruxErrorCodes.EMAIL_NOT_VALID.getDescription());
		String passwordKey  = "";		
		try {
			passwordKey= TruxUtils.generateForgotPaswordCode(email);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		ForgotPassword forgotPassword = new ForgotPassword(email, URLEncoder.encode(passwordKey,"UTF-8"), new Date());
		ConsumerRegistration consumerRegistration=	consumerService.getUserDetailsByEmail(email);
		TruxUtils.sendMail(email, passwordKey, 0, null, null);
		forgotPassword.setPasswordGenKey("************");		 
	   // SMSTemplates.sendMessageToForgetPassword(consumerRegistration);
	    consumerService.save(forgotPassword);
		return forgotPassword;
		}catch(Exception er){er.printStackTrace();return new ForgotPassword(TruxErrorCodes.EMAIL_NOT_VALID.getCode(), TruxErrorCodes.EMAIL_NOT_VALID.getDescription());}
	
	}
	
	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public ModelAndView consumerReports(HttpServletRequest request,	HttpServletResponse response) {
		try{
		List<ConsumerRegistration> consumerList = consumerService.getConsumerReports();
		
		for (ConsumerRegistration consumerRegistration : consumerList) {
			consumerRegistration.setCreatedDate(TruxUtils.getChangedTimezoneDate(consumerRegistration.getCreatedDate()));
			consumerRegistration.setUpdatedDate(TruxUtils.getChangedTimezoneDate(consumerRegistration.getUpdatedDate()));
		}
		
		request.setAttribute("consumerReport", consumerList);
		}catch(Exception er){er.printStackTrace();}
		return new ModelAndView("consumerReport");	 
	}
	@ResponseBody
	@RequestMapping(value = "/consumerReportsGrid", method = RequestMethod.GET)
	public ConsumerRegistrationResponse consumerReportsGrid(HttpServletRequest request,	HttpServletResponse response) {
		try{
		List<ConsumerRegistration> consumerList = consumerService.getConsumerReports();
		for (ConsumerRegistration consumerRegistration : consumerList) {
			consumerRegistration.setCreatedDate(TruxUtils.getChangedTimezoneDate(consumerRegistration.getCreatedDate()));
			consumerRegistration.setUpdatedDate(TruxUtils.getChangedTimezoneDate(consumerRegistration.getUpdatedDate()));
		}
		ConsumerRegistrationResponse responses=new ConsumerRegistrationResponse();
		int count =consumerList.size(); 
		int rows = 1;
		int page = 10;		
		 
		responses.setRows(consumerList);		
		int total = count%rows == 0 ? (int)Math.ceil(count/rows) : (int)Math.ceil(count/rows)+1;
		responses.setTotal(total);
		responses.setRecords(count); 
		responses.setPage(page); 
		//System.out.println(responses.toString() );
		return responses;   
		}catch(Exception er){er.printStackTrace(); return new ConsumerRegistrationResponse();}
	}
	
	
	
	@RequestMapping(value = "/consumerXlsReports", method = RequestMethod.GET)
	public ModelAndView consumerXlsReports(HttpServletRequest request,HttpServletResponse response) throws IOException {
	try{
		List<ConsumerRegistration> consumerList = consumerService.getConsumerReports();
		return new ModelAndView("consumerReportExcelView", "consumerList", consumerList);
	}catch(Exception er){er.printStackTrace(); 
	return new ModelAndView("massege");}
	}
	
	
	public static void main(String[] args) {
		
		String latitude =   "28.5268983";
		String longitude =   "72.9876345";
		String vehicleType = "tataAce";
		
				
				
				
				
				String firstPartLat = latitude.substring(0, 4);
				String lastPatLat = latitude.substring(5);
				
				
				String firstPartLong = longitude.substring(0, 4);
				String lastPatLong = longitude.substring(5);
				
				
				
			      
			      
			   //   String first
				
				    Random randomGenerator = new Random();
				    int randomInt = 1 + randomGenerator.nextInt(6);
			       
				   NearbyVehicles fetchNearByTrux = new NearbyVehicles();

				   for(int i = 0 ; i < randomInt ; i ++) {
					     Random randomGenerator1 = new Random();
						  TruckLocation location = new TruckLocation();
						  String randomLat = "";
						  String randomLong= "";
					   
						  int randomIntLat =  randomGenerator1.nextInt(10);
						  int randomIntLong =  randomGenerator1.nextInt(10);
						  randomLat = firstPartLat+randomIntLat+lastPatLat;
						  randomLong = firstPartLong+ randomIntLong + lastPatLong;
						  
						
						   
							LatLng firstUserLocation = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
							LatLng secondUserLocation = new LatLng(Double.parseDouble(randomLat), Double.parseDouble(randomLong));
						
					  
							double distanceInKM = TruxUtils.roundTo5Decimals(LatLngTool.distance(firstUserLocation, secondUserLocation, LengthUnit.KILOMETER));
							
							
							location.setLatitude(Double.parseDouble(randomLat));
							location.setLongitude(Double.parseDouble(randomLong));
							location.setTruxType(vehicleType);
							location.setDistance(distanceInKM);
					   
					   
					   fetchNearByTrux.getTruxLocations().add(location);
					   
				   }
				  
				   
				     Gson gson = new Gson();
					String jsonPayload = gson.toJson(fetchNearByTrux);
					//System.out.println(jsonPayload);
				
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/getNearbyTrucks", method = RequestMethod.POST)
	public NearbyVehicles forgotpassword(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam String latitude, @RequestParam String longitude, @RequestParam(required=false) String vehicleType) throws Exception {
			try{
		List<DriverDeviceVehicleMapping> allRegisteredDeviceList = registrationService.getAllAvailableDrivers();
			List<CurrentVehicleLocation>  availableVehicleLocations =	vehicleLocationService.fetchAllVehicleAvailableCurrentLocation();
			Set<String> uniqueVehicleTypes = getUniqueVehicleTypes(allRegisteredDeviceList);
			   NearbyVehicles fetchNearByTrux = new NearbyVehicles();

				  Random randomGenerator1 = new Random();

			   
			for (String vehicleType1 : uniqueVehicleTypes) {
				
				  double distance = getVehicleDistancebyVehicleType(vehicleType1, latitude, longitude, availableVehicleLocations, allRegisteredDeviceList);
				  
				  
				  if(distance <= 0 ){
					  int nextInt = randomGenerator1.nextInt(10);
					  distance = 5+nextInt;
				  }
				  
				  double timeforTrucToReachUser = distance*60/25;
				  String distanceToShow = "";
				  
				  
				  if(distance > 0)
				   distanceToShow = ((int)timeforTrucToReachUser+1)+" mins";
				  else 
					  distanceToShow = "no trucks";	  
				  

				
				if("Tata 709".equals(vehicleType1) && fetchNearByTrux.getDistanceTata709().equals("no trucks"))
					fetchNearByTrux.setDistanceTata709(distanceToShow);
				else if("Tata 407".equals(vehicleType1)  && fetchNearByTrux.getDistanceTata407().equals("no trucks"))
					fetchNearByTrux.setDistanceTata407(distanceToShow);
				else if("Tata Ace".equals(vehicleType1)  && fetchNearByTrux.getDistanceTataAce().equals("no trucks"))
					fetchNearByTrux.setDistanceTataAce(distanceToShow);
				else if("Mahindra Champion".equals(vehicleType1)  && fetchNearByTrux.getDistanceMahindraChampion().equals("no trucks"))
					fetchNearByTrux.setDistanceMahindraChampion(distanceToShow);
				else if("Bolero Pickup".equals(vehicleType1)  && fetchNearByTrux.getDistanceBoleroPickup().equals("no trucks"))
					fetchNearByTrux.setDistanceBoleroPickup(distanceToShow);
				else if("Maruti Eeco".equals(vehicleType1)  && fetchNearByTrux.getDistanceMarutiEeco().equals("no trucks"))
					fetchNearByTrux.setDistanceMarutiEeco(distanceToShow);
							
				
			}
			
			
		
			
			
			
			if(vehicleType == null || vehicleType.trim().length() == 0)
				return fetchNearByTrux;
			
			vehicleType = vehicleType.replace("\n", " ");


		   for(int i = 0 ; i < allRegisteredDeviceList.size() ; i ++) {      
				  TruckLocation location = new TruckLocation();
			
				  boolean flag = false;
				 
				  if(allRegisteredDeviceList!=null ){
					  String vehicleTypes=allRegisteredDeviceList.get(i).getVehicleType();
					  if(vehicleTypes!=null && !vehicleTypes.equals(vehicleType)){
					  flag = true;}
					  }
				  
				  
				  //System.out.println("Value of Flag is "+ flag);
				  
				  if(flag)
					  continue;
				  
				  DriverDeviceVehicleMapping deviceVehicleMapping =  allRegisteredDeviceList.get(i);
				  CurrentVehicleLocation vehicleLocation =   getVehicleLocation(deviceVehicleMapping, availableVehicleLocations);
				  if(vehicleLocation == null)
					  continue;
				    LatLng firstUserLocation = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
					LatLng secondUserLocation = new LatLng(vehicleLocation.getLatitude(), vehicleLocation.getLongitude());
				
			  
					double distanceInKM = TruxUtils.roundTo5Decimals(LatLngTool.distance(firstUserLocation, secondUserLocation, LengthUnit.KILOMETER));
					
					if(distanceInKM > DISTANCE_THRESHOLD_LIMIT)
						continue;
					
					location.setLatitude(secondUserLocation.getLatitude());
					location.setLongitude(secondUserLocation.getLongitude());
					location.setTruxType(allRegisteredDeviceList.get(i).getVehicleType());
					location.setDistance(distanceInKM);
					location.setDrivername(vehicleLocation.getDriverName());
					location.setDriverPhoneNumber(vehicleLocation.getDriverNumber());
					
			      fetchNearByTrux.getTruxLocations().add(location);
			   
		   }
		   
		    addOneFakeTruck(latitude, longitude, vehicleType, fetchNearByTrux);
		    Gson gson = new Gson();
			String jsonPayload = gson.toJson(fetchNearByTrux);
			//System.out.println(jsonPayload);
			
			return fetchNearByTrux;

			}catch(Exception er){er.printStackTrace(); return new NearbyVehicles();}
	
	}

	private void addOneFakeTruck(String latitude, String longitude, String vehicleType,  NearbyVehicles fetchNearByTrux) {
	  try{
		String firstPartLat = latitude.substring(0, 4);
			String lastPatLat = latitude.substring(5);
			
			
			String firstPartLong = longitude.substring(0, 4);
			String lastPatLong = longitude.substring(5);
			
			
			
		      
		      
		   //   String first
			
			    Random randomGenerator = new Random();
			    int randomInt = 1  ;// + randomGenerator.nextInt(6);
		       

			   for(int i = 0 ; i < randomInt ; i ++) {
				     Random randomGenerator1 = new Random();
					  TruckLocation location = new TruckLocation();
					  String randomLat = "";
					  String randomLong= "";
				   
					  int randomIntLat =  randomGenerator1.nextInt(10);
					  int randomIntLong =  randomGenerator1.nextInt(10);
					  randomLat = firstPartLat+randomIntLat+lastPatLat;
					  randomLong = firstPartLong+ randomIntLong + lastPatLong;
					  
					
					   
						LatLng firstUserLocation = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
						LatLng secondUserLocation = new LatLng(Double.parseDouble(randomLat), Double.parseDouble(randomLong));
					
				  
						double distanceInKM = TruxUtils.roundTo5Decimals(LatLngTool.distance(firstUserLocation, secondUserLocation, LengthUnit.KILOMETER));
						
						location.setDrivername("Hanumanto");
						location.setDriverPhoneNumber("9899016402");

						
						location.setLatitude(Double.parseDouble(randomLat));
						location.setLongitude(Double.parseDouble(randomLong));
						location.setTruxType(vehicleType);
						location.setDistance(distanceInKM);
				   
				   
				   fetchNearByTrux.getTruxLocations().add(location);
				   
			   }
	  }catch(Exception er){er.printStackTrace();}
		
		
	}

	private CurrentVehicleLocation getVehicleLocation(DriverDeviceVehicleMapping deviceVehicleMapping, List<CurrentVehicleLocation> availableVehicleLocations) {
		
		for (CurrentVehicleLocation currentVehicleLocation : availableVehicleLocations) {
			if(currentVehicleLocation.getDriverNumber().equals(deviceVehicleMapping.getDriverPhoneNumber()))
				return currentVehicleLocation;
		}
		
		
		return null;
	}

	private double getVehicleDistancebyVehicleType(String vehicleType, String latitude, String longitude, List<CurrentVehicleLocation> availableVehicleLocations, List<DriverDeviceVehicleMapping> allRegisteredDeviceList ) {
		try{
		int counter = 0;
		double distance = 1.0;
		
		for (CurrentVehicleLocation currentVehicleLocation : availableVehicleLocations) {
			if(currentVehicleLocation.getVehicleType().equals(vehicleType)) {
				
				boolean isDevicePresent = getPresenceOfDevice(currentVehicleLocation, allRegisteredDeviceList);
				if(!isDevicePresent)
					continue;
				
				double vehicleLatitude =  currentVehicleLocation.getLatitude();
				double vehicleLongitude = currentVehicleLocation.getLongitude();
				double userLatitude = Double.valueOf(latitude);
				double userLongitude = Double.valueOf(longitude);
				LatLng firstUserLocation = new LatLng(vehicleLatitude, vehicleLongitude);
				LatLng secondUserLocation = new LatLng(userLatitude, userLongitude);  
				double distanceInKM = (LatLngTool.distance(firstUserLocation, secondUserLocation, LengthUnit.KILOMETER));
				 if(distanceInKM > DISTANCE_THRESHOLD_LIMIT)
					 continue;
					 
				distance += distanceInKM;
				counter++;
			}
		}
		
		if(counter == 0)
			return 0;
		else
			return distance/counter;	//calculating mean
		}catch(Exception er){er.printStackTrace();return 0;}
	}

	private boolean getPresenceOfDevice(CurrentVehicleLocation currentVehicleLocation, List<DriverDeviceVehicleMapping> allRegisteredDeviceList) {
		try{
		for (DriverDeviceVehicleMapping driverDeviceVehicleMapping : allRegisteredDeviceList) {
		if(driverDeviceVehicleMapping.getDriverPhoneNumber().equals(currentVehicleLocation.getDriverNumber()))
			return true;
		}
		
		
		return false;
		}catch(Exception er){ er.printStackTrace(); return false;}
	}

	private String getRandomDistanceTravelTime(String latitude, String longitude, String firstPartLat, String lastPatLat, String firstPartLong, String lastPatLong) {
		try{
	       Random randomGenerator1 = new Random();
		  TruckLocation location = new TruckLocation();
		  String randomLat = "";
		  String randomLong= "";
	   
		  int randomIntLat =  randomGenerator1.nextInt(10);
		  int randomIntLong =  randomGenerator1.nextInt(10);
		  randomLat = firstPartLat+randomIntLat+lastPatLat;
		  randomLong = firstPartLong+ randomIntLong + lastPatLong;
		  
		
		   
			LatLng firstUserLocation = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
			LatLng secondUserLocation = new LatLng(Double.parseDouble(randomLat), Double.parseDouble(randomLong));
		
	  
			int distanceInKM = (int)TruxUtils.roundTo5Decimals(LatLngTool.distance(firstUserLocation, secondUserLocation, LengthUnit.KILOMETER)) +1;
			
			return (distanceInKM*3 + 5)+" mins";
			
		}catch(Exception er){er.printStackTrace(); return er.getMessage().toString();}
	
		
	}

	private Set<String> getUniqueVehicleTypes(List<DriverDeviceVehicleMapping> allRegisteredDeviceList) {
		
		Set<String> uniqueVehicleTypes = new HashSet<String>();
		
		for (DriverDeviceVehicleMapping driverDeviceVehicleMapping : allRegisteredDeviceList) {
			if(driverDeviceVehicleMapping.getVehicleType()!=null && !driverDeviceVehicleMapping.getVehicleType().equals("")){
			uniqueVehicleTypes.add(driverDeviceVehicleMapping.getVehicleType());}
		}
		
		return uniqueVehicleTypes;
	}
	
	
	
	
}
