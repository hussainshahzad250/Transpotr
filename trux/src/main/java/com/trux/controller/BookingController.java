package com.trux.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.maps.model.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import com.trux.constants.SMSTemplates;
import com.trux.enumerations.TruxErrorCodes;
import com.trux.model.ActualFare;
import com.trux.model.ApproxFareEstimate;
import com.trux.model.BookingHistory;
import com.trux.model.BookingHistory.BookingDetails;
import com.trux.model.ConsumerRegistration;
import com.trux.model.CurrentVehicleLocation;
import com.trux.model.CustomerBookingDetails;
import com.trux.model.CustomerBookingVehicle;
import com.trux.model.DeviceRegistration;
import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.FareRates;
import com.trux.model.GDistanceMatrix;
import com.trux.model.UserDetail;
import com.trux.pricing.PricingModel;
import com.trux.service.BookingService;
import com.trux.service.ConsumerService;
import com.trux.service.FareService;
import com.trux.service.RegistrationService;
import com.trux.service.TruxStartUpService;
import com.trux.service.VehicleLocationService;
import com.trux.utils.Click2CallUtils;
import com.trux.utils.GoogleMapsUtils;
import com.trux.utils.PusherUtil;
import com.trux.utils.TruxConstant;
import com.trux.utils.TruxUtils;

@Controller
@RequestMapping(value = "/booking")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private ConsumerService consumerService;
	 
	@Autowired
	private TruxStartUpService truxStartUpService;
	List<ConsumerRegistration> list=new ArrayList<ConsumerRegistration>();
	 
	@Autowired
	private VehicleLocationService vehicleLocationService;
	
	@Autowired
	private FareService fareService;

	String[] driverCancellation =  {"कस्टमर ने मना किया|..", " ग़लत गाड़ी ऑर्डर की|..", "गाड़ी पहुँचने में देरी होगी|..","गाड़ी खराब हो गयी|.."," ग़लती से ऑर्डर पिक किया|.."};

	@ResponseBody
	@RequestMapping(value = "/bookride", method = RequestMethod.POST)
	private CustomerBookingDetails bookRide(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String email,
			@RequestParam(required = false) String userName,
			@RequestParam String userPhoneNumber,
			@RequestParam Double fromLatitude,
			@RequestParam Double fromLongitude,
			@RequestParam(required = false) String fromLocation,
			@RequestParam Double toLatitude, @RequestParam Double toLongitude,
			@RequestParam(required = false) String toLocation,
			@RequestParam(required = false) Long rideTime) {
try{
		if (email == null)
			return new CustomerBookingDetails(
					TruxErrorCodes.EMAIL_NOT_VALID.getCode(),
					TruxErrorCodes.EMAIL_NOT_VALID.getDescription());

		if (fromLatitude == null)
			return new CustomerBookingDetails(
					TruxErrorCodes.INVALID_FROM_LATITUDE.getCode(),
					TruxErrorCodes.INVALID_FROM_LATITUDE.getDescription());

		if (fromLongitude == null)
			return new CustomerBookingDetails(
					TruxErrorCodes.INVALID_FROM_LONGITUDE.getCode(),
					TruxErrorCodes.INVALID_FROM_LONGITUDE.getDescription());

		if (toLatitude == null)
			return new CustomerBookingDetails(
					TruxErrorCodes.INVALID_TO_LATITUDE.getCode(),
					TruxErrorCodes.INVALID_TO_LATITUDE.getDescription());

		if (toLongitude == null)
			return new CustomerBookingDetails(
					TruxErrorCodes.INVALID_TO_LONGITUDE.getCode(),
					TruxErrorCodes.INVALID_TO_LONGITUDE.getDescription());

		if (userPhoneNumber == null)
			return new CustomerBookingDetails(
					TruxErrorCodes.INVALID_PHONENUBER.getCode(),
					TruxErrorCodes.INVALID_PHONENUBER.getDescription());

		CustomerBookingDetails customerBookingDetails = new CustomerBookingDetails(
				userName, userPhoneNumber, email, fromLongitude, fromLatitude,
				fromLocation, toLatitude, toLongitude, toLocation, new Date(),
				new Date(), "Pending", null);

		List<DeviceRegistration> allRegisteredDeviceList = registrationService.getAllRegisteredDevice();

		Date rideDate = new Date(rideTime);

		customerBookingDetails.setRideTime(rideDate);

		try {
			bookingService.savoOrUpdate(customerBookingDetails);
			PusherUtil pusherUtil = new PusherUtil();
		//	pusherUtil.notifyTruxs(allRegisteredDeviceList,
	//				customerBookingDetails);
			return customerBookingDetails;
		} catch (Exception e) {
			e.printStackTrace();
			return new CustomerBookingDetails(
					TruxErrorCodes.BOOKING_ERROR.getCode(),
					TruxErrorCodes.BOOKING_ERROR.getDescription());
		}
		}catch(Exception er){er.printStackTrace();return new CustomerBookingDetails(
		TruxErrorCodes.BOOKING_ERROR.getCode(),
		TruxErrorCodes.BOOKING_ERROR.getDescription());}

	}

	
	
	@ResponseBody
	@RequestMapping(value = "/estimatedFare", method = RequestMethod.GET)
	private ApproxFareEstimate estimatedFare(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam String fromLocation,
			@RequestParam String toLocation,
			@RequestParam String vehicleType
			) {
		try{
		
		
		vehicleType = vehicleType.replace("\n", " ");
		
		if (fromLocation == null)
			return new ApproxFareEstimate(
					TruxErrorCodes.INVALIDE_FROM_LOCATION.getCode(),
					TruxErrorCodes.INVALIDE_FROM_LOCATION.getDescription());
		
		if (toLocation == null)
			return new ApproxFareEstimate(
					TruxErrorCodes.INVALIDE_TO_LOCATION.getCode(),
					TruxErrorCodes.INVALIDE_TO_LOCATION.getDescription());
		
		if (vehicleType == null)
			return new ApproxFareEstimate(
					TruxErrorCodes.INVALIDE_VEHICLE_TYPE.getCode(),
					TruxErrorCodes.INVALIDE_VEHICLE_TYPE.getDescription());
		LatLng source = null;
		
		try {
			source = GoogleMapsUtils.getLatLongBasedOnAddress(fromLocation);
			} catch (Exception e) {
				e.printStackTrace();
				source = new LatLng(28.4863626802915, 77.09126018029151);
			}
			
			//System.out.println("Before Goo gle API 2");
			
			LatLng destination = null;
			
			try {
			 destination = GoogleMapsUtils.getLatLongBasedOnAddress(toLocation);
			}
			catch (Exception e) {
				e.printStackTrace();
				destination = new LatLng(28.5903481, 77.3383321);
			}
			 

			GDistanceMatrix distanceMatrix = null;
			 
			 try {
				distanceMatrix = GoogleMapsUtils.getDistanceMatrix(source, destination);
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			
			double approxDistance =  distanceMatrix.getDistanceValue()/1000;
			double approxfare = new PricingModel(approxDistance, 0, vehicleType, distanceMatrix, false, fareService).computeFare();
			
	
			long journeyTime = distanceMatrix.getDurationValue();
			
			int hours = (int)journeyTime/3600;
			int minutes = (int) ((int)journeyTime - hours*3600)/60;

			String timeToReachDestination = hours+" Hr "+minutes+" Min";
			
		
		
		return new ApproxFareEstimate(approxDistance,approxfare,timeToReachDestination,fromLocation, toLocation, vehicleType);
		}catch(Exception er){er.printStackTrace();return new ApproxFareEstimate(0,0,"00:00:00",fromLocation, toLocation, vehicleType);}
	}
	
	

	@ResponseBody
	@RequestMapping(value = "/matchOrderandDriver", method = RequestMethod.GET)
	private CustomerBookingDetails matchOrderandDriver(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Integer bookingId,
			@RequestParam String driverPhoneNumber) {
	try{
		
		CustomerBookingDetails customerBookingDetails = bookingService.getBookingDetailsById(bookingId);
		
		DriverDeviceVehicleMapping deviceVehicleMapping =  registrationService.getDriverDeviceDetail(driverPhoneNumber);
		
		PusherUtil.notifyParticularTruxs(customerBookingDetails, deviceVehicleMapping);
		
		return customerBookingDetails;
	}catch(Exception er){er.printStackTrace();return new CustomerBookingDetails(); }
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/allocateBookings", method = RequestMethod.GET)
	private List<CustomerBookingDetails> allocateBookings(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String bookindids
			) {
		try{
		String[] bookings = bookindids.split(",");
		List<CustomerBookingDetails> customerBookingDetails = new ArrayList<CustomerBookingDetails>();

		for (String bookingid : bookings) {
			try {
			CustomerBookingDetails  customerBookingDetail  =	this.bookingService.getBookingDetailsById(Integer.parseInt(bookingid.trim()));
			if(customerBookingDetail ==null || !customerBookingDetail.getBookingStatus().equalsIgnoreCase("pending"))
				continue;
			
			customerBookingDetails.add(customerBookingDetail);
			truxStartUpService.orderPushingQueue.add(customerBookingDetail);
			} catch (Exception e) {
				e.printStackTrace();
			}
	   
			
			
		}
		
		
		return customerBookingDetails;
		}catch(Exception er){er.printStackTrace(); return  new ArrayList<CustomerBookingDetails>();}
		
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/getBookingDetails", method = RequestMethod.GET)
	private CustomerBookingDetails getBookingDetails(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String bookindId
			) {
		try{
		CustomerBookingDetails  customerBookingDetail  =	this.bookingService.getBookingDetailsById(Integer.parseInt(bookindId.trim()));
		return customerBookingDetail;
		}catch(Exception er){er.printStackTrace(); return new CustomerBookingDetails();}
	}
	
	
		
	@ResponseBody
	@RequestMapping(value = "/bookrideV2", method = RequestMethod.POST)
	private CustomerBookingDetails bookRideV2(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String email,
			@RequestParam String fromLocation,
			@RequestParam String toLocation,
			@RequestParam String vehicleType,
			@RequestParam(required = false) String rideTime,
			@RequestParam(required = false) Integer labourCount,
			@RequestParam(required = false) Integer payment_mode) {
		
		try{
		
		
	

		if (email == null)
			return new CustomerBookingDetails(
					TruxErrorCodes.EMAIL_NOT_VALID.getCode(),
					TruxErrorCodes.EMAIL_NOT_VALID.getDescription());
		
		vehicleType = vehicleType.replace("\n", " ");
		
		//System.out.println(fromLocation);
		//System.out.println(toLocation);
		//System.out.println(vehicleType);

	
		ConsumerRegistration consumerRegistration =  this.consumerService.getUserDetailsByEmail(email.trim());
		
		LatLng source = null;
		
		try {
		source = GoogleMapsUtils.getLatLongBasedOnAddress(fromLocation);
		} catch (Exception e) {
			e.printStackTrace();
			source = new LatLng(28.4863626802915, 77.09126018029151);
		}
		
		
		LatLng destination = null;
		
		try {
		 destination = GoogleMapsUtils.getLatLongBasedOnAddress(toLocation);
		}
		catch (Exception e) {
			e.printStackTrace();
			destination = new LatLng(28.5903481, 77.3383321);
		}
		
		GDistanceMatrix distanceMatrix = null;
		 
		 try {
			distanceMatrix = GoogleMapsUtils.getDistanceMatrix(source, destination);
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		
		double approxDistance =  distanceMatrix.getDistanceValue()/1000;
		double approxfare = new PricingModel(approxDistance, 0, vehicleType, distanceMatrix, false, fareService).computeFare();
		if(labourCount == null)
			 labourCount = 0;
		
		approxfare  = approxfare + labourCount*200;
		
		CustomerBookingDetails customerBookingDetails = new CustomerBookingDetails(
				consumerRegistration.getFirstname()+" "+consumerRegistration.getLastname(), consumerRegistration.getPhoneNumber(), email, 
				source.lat, source.lng,
				fromLocation, destination.lat, destination.lng, toLocation, new Date(),
				new Date(), "Pending", vehicleType);
		
		customerBookingDetails.setExpectedFare(approxfare+"");
		customerBookingDetails.setExpectedDistance(approxDistance+"");


		List<DriverDeviceVehicleMapping> allRegisteredDeviceList = registrationService.getAllAvailableDrivers();
		Date rideDate =  null;
		
		if(rideTime == null || rideTime.equals("0")){
			customerBookingDetails.setBooknow(1);
		 //   rideDate = new Date(new Date().getTime()+1000*3600);
		}
		else {
			customerBookingDetails.setBooknow(0);
			rideDate = new Date(Long.parseLong(rideTime.trim()));
			
		}
		    
		customerBookingDetails.setRideTime(rideDate);
		
		long loadingTime = 1800;
		long unloadingTime = 1800;
		long journeyTime = (new Double(approxDistance*144)).longValue()+ loadingTime + unloadingTime ;
		
		
			
		
		if(rideDate!=null)
		customerBookingDetails.setExpectedRideStartTime(rideDate.getTime());
		else
			customerBookingDetails.setExpectedRideStartTime((long)0);

		if(rideDate!=null)
		customerBookingDetails.setExpectedRideEndTime(rideDate.getTime()+journeyTime*1000);
		else 
		customerBookingDetails.setExpectedRideEndTime((long)0);

		
		if(labourCount != null)
			customerBookingDetails.setLabourCount(labourCount);
		
		
		if(payment_mode!= null)
			customerBookingDetails.setPayment_mode(payment_mode);
		else
			customerBookingDetails.setPayment_mode(0);
        	customerBookingDetails.setBooking_time(new Date());
       try {
    	   customerBookingDetails.setDstatus("Free");
			bookingService.savoOrUpdate(customerBookingDetails);
			PusherUtil pusherUtil = new PusherUtil();
			
			//System.out.println("rideTime: "+rideTime);
			//System.out.println("customerBookingDetails.getBooknow() "+customerBookingDetails.getBooknow());
			if(customerBookingDetails.getBooknow() ==1){
			pusherUtil.notifyTruxs(allRegisteredDeviceList, customerBookingDetails, vehicleLocationService);
			SMSTemplates.sendBookingConfirmationToOps(customerBookingDetails);

			}
			
			if(customerBookingDetails.getBooknow() == 0)
				SMSTemplates.bookLaterMessage(customerBookingDetails);				
				
				

			return customerBookingDetails;
			
		} catch (Exception e) {
			e.printStackTrace();
			return new CustomerBookingDetails(
					TruxErrorCodes.BOOKING_ERROR.getCode(),
					TruxErrorCodes.BOOKING_ERROR.getDescription());
		}
		}catch(Exception er){er.printStackTrace(); return new CustomerBookingDetails(
				TruxErrorCodes.BOOKING_ERROR.getCode(),
				TruxErrorCodes.BOOKING_ERROR.getDescription());}
	}

	@RequestMapping(value = "/consumerBookrideV2V", method = RequestMethod.GET)
	private ModelAndView consumerBookrideV2V(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("cbVehicle") CustomerBookingVehicle cbVehicle,BindingResult result) {
		 
        return new ModelAndView("customerBookingVehicle");
        } 
	 
	@ResponseBody  
	@RequestMapping(value = "/consumerBookAutoSuggetion", method = RequestMethod.GET)
	public List<ConsumerRegistration> consumerBookAutoSuggetion(HttpServletRequest request,HttpServletResponse response,@RequestParam String mobile) {
	 try{
		List<ConsumerRegistration> result = new ArrayList<ConsumerRegistration>();
		 
		list=consumerService.getConsumerDetailsList();
		 
		for (ConsumerRegistration tag : list) {
			if (tag.getFirstname().contains(mobile)|| tag.getPhoneNumber().contains(mobile)) {
				result.add(tag);
			}
		}
		//System.out.println(list +"  "+mobile);
		 return result;
	 }catch(Exception er){er.printStackTrace(); return new ArrayList<ConsumerRegistration>();}
     }
	
	@RequestMapping(value = "/consumerBookrideClear", method = RequestMethod.GET)
	private ModelAndView consumerBookrideClear(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("cbVehicle") CustomerBookingVehicle cbVehicle,BindingResult result) {
		cbVehicle=null;
        return new ModelAndView("customerBookingVehicle");
        }
	@ResponseBody
	@RequestMapping(value = "/bookrideV2V", method = RequestMethod.POST)
	private ModelAndView bookRideV2V(HttpServletRequest request,HttpServletResponse response,@ModelAttribute("cbVehicle") CustomerBookingVehicle cbVehicle,BindingResult result) {
		try{
		if (cbVehicle.getEmail() == null){
			CustomerBookingDetails cbd=	new CustomerBookingDetails(
					TruxErrorCodes.EMAIL_NOT_VALID.getCode(),
					TruxErrorCodes.EMAIL_NOT_VALID.getDescription());
			ModelMap map=new ModelMap();
			map.addAttribute(cbd);
        return new ModelAndView("customerBookingVehicle",map);
        }		
		cbVehicle.setVehicleType(cbVehicle.getVehicleType().replace("\n", " "));
		
		//System.out.println(cbVehicle.getFromLocation());
		//System.out.println(cbVehicle.getToLocation());
		//System.out.println(cbVehicle.getVehicleType());	
		ConsumerRegistration consumerRegistration =  this.consumerService.getUserDetailsByEmail(cbVehicle.getEmail().trim());
		LatLng source = null;		
		try {
		source = GoogleMapsUtils.getLatLongBasedOnAddress(cbVehicle.getFromLocation());
		} catch (Exception e) {
			e.printStackTrace();
			source = new LatLng(28.4863626802915, 77.09126018029151);
		}		
		LatLng destination = null;		
		try {
		 destination = GoogleMapsUtils.getLatLongBasedOnAddress(cbVehicle.getToLocation());
		}
		catch (Exception e) {
			e.printStackTrace();
			destination = new LatLng(28.5903481, 77.3383321);
		}		
		GDistanceMatrix distanceMatrix = null;
		 
		 try {
			distanceMatrix = GoogleMapsUtils.getDistanceMatrix(source, destination);
		} catch (Exception e1) {
		e1.printStackTrace();
		}double approxfare=0;
		double approxDistance =  distanceMatrix.getDistanceValue()/1000;
		try{
		  approxfare = new PricingModel(approxDistance, 0, cbVehicle.getVehicleType(), distanceMatrix, false, fareService).computeFare();
		}catch(Exception er){er.printStackTrace();}
		if(cbVehicle.getLabourCount() == 0){
			cbVehicle.setLabourCount(0);
			}
		int labourCount =cbVehicle.getLabourCount();		
		approxfare  = approxfare + labourCount*200;	
		HttpSession session=request.getSession();
		UserDetail  userDe=(UserDetail)session.getAttribute("userDetail");
		Integer userId=0;
	 	if(userDe!=null){
			   userId=userDe.getId();
	 	}
		CustomerBookingDetails customerBookingDetails = new CustomerBookingDetails(cbVehicle.getFirstName()+" "+cbVehicle.getLastName(), cbVehicle.getPhoneNumber(), cbVehicle.getEmail(),source.lat, source.lng,cbVehicle.getFromLocation(), destination.lat, destination.lng, cbVehicle.getToLocation(), new Date(),	new Date(), "Pending", cbVehicle.getVehicleType());
		customerBookingDetails.setCreatedBy(userId);
		customerBookingDetails.setExpectedFare(approxfare+"");
		customerBookingDetails.setExpectedDistance(approxDistance+"");
		customerBookingDetails.setConsigneeName(cbVehicle.getConsigneeName());
		customerBookingDetails.setConsigneePhoneNumber(cbVehicle.getConsigneePhonenumber());
		customerBookingDetails.setToLocation(cbVehicle.getToLocation());
		customerBookingDetails.setFromLocation(cbVehicle.getFromLocation());
		List<DriverDeviceVehicleMapping> allRegisteredDeviceList = registrationService.getAllAvailableDrivers();
		Date rideDate =  null;		
		if(cbVehicle.getRideTime() == null || cbVehicle.getRideTime().equals("0")){
			customerBookingDetails.setBooknow(1);
		   rideDate = new Date(new Date().getTime()+1000*3600);
		}
		else {
			customerBookingDetails.setBooknow(0);			
			rideDate = new Date(Long.parseLong(""+cbVehicle.getRideTime().getTime()));			
		}		    
		customerBookingDetails.setRideTime(rideDate);
		customerBookingDetails.setRideTime(cbVehicle.getRideTime());
		
		long loadingTime = 1800;
		long unloadingTime = 1800;
		long journeyTime = (new Double(approxDistance*144)).longValue()+ loadingTime + unloadingTime ;		
		if(rideDate!=null)
		customerBookingDetails.setExpectedRideStartTime(rideDate.getTime());
		else
			customerBookingDetails.setExpectedRideStartTime((long)0);

		if(rideDate!=null)
		customerBookingDetails.setExpectedRideEndTime(rideDate.getTime()+journeyTime*1000);
		else 
		customerBookingDetails.setExpectedRideEndTime((long)0);
		
		if(cbVehicle.getLabourCount() != 0)
			customerBookingDetails.setLabourCount(labourCount);
		
		
		if(cbVehicle.getPaymentMode()!= null)
			customerBookingDetails.setPayment_mode(cbVehicle.getPaymentMode());
		else
		customerBookingDetails.setPayment_mode(0);
		customerBookingDetails.setBooking_time(new Date());
		try {
			
			String encyptedPassword = TruxUtils.computeMD5Hash(cbVehicle.getFirstName());
			ConsumerRegistration userDetail = new ConsumerRegistration(cbVehicle.getEmail(), cbVehicle.getFirstName(), cbVehicle.getLastName(), cbVehicle.getPhoneNumber(), encyptedPassword, 1, null);
			 try{
				 ConsumerRegistration ConsumerRegistration=	 consumerService.getUserDetailsByPhoneNumber(cbVehicle.getPhoneNumber());
				 if(ConsumerRegistration==null){
				 consumerService.consumerRegistration(userDetail);				 
				 try {
				  Click2CallUtils.whiteListNumber(cbVehicle.getPhoneNumber());
				 } catch(Exception e) {
					  userDetail.setErrorMessage("Unable to WhiteList Phone Number.");
					  e.printStackTrace();
				 }}
		        }catch(Exception e){
		        	e.printStackTrace();		        	 
		        }
			  try { customerBookingDetails.setBookingMode("B");
					bookingService.savoOrUpdate(customerBookingDetails);					
					PusherUtil pusherUtil = new PusherUtil();					 
					if(customerBookingDetails.getBooknow() ==1){
					pusherUtil.notifyTruxs(allRegisteredDeviceList, customerBookingDetails, vehicleLocationService);
					//SMSTemplates.sendBookingConfirmationToOps(customerBookingDetails);
					}
					/*if(customerBookingDetails.getBooknow() == 0)
						SMSTemplates.bookLaterMessage(customerBookingDetails);	*/			
				 } catch (Exception e) {
					e.printStackTrace();
				 }
				  
			cbVehicle.setSuccessFullMessage("Booked succesfully !");
			request.setAttribute("cbVehicle", cbVehicle);
			ModelAndView model=new ModelAndView("customerBookingVehicle");
			List<CustomerBookingVehicle> list=new ArrayList<CustomerBookingVehicle>();
			list.add(cbVehicle);
			model.addObject("list",list); 
			return model;
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("customerBookingVehicle");
		}
		}catch(Exception er){er.printStackTrace(); return new ModelAndView("massage");}
	}
	
	@ResponseBody
	@RequestMapping(value = "/computeFare", method = RequestMethod.POST)
	private ActualFare computeFare(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam String vehicleType,
			@RequestParam double distanceTravelled,
			@RequestParam double  duration,
			@RequestParam(required = false) Integer bookingId
			
			) {
		try{
		 if(bookingId == null) {
			ActualFare  actualFare = new ActualFare();
			
			vehicleType = vehicleType.replace("\n", " ");
		
			actualFare.setDistanceTravelled(distanceTravelled);
			actualFare.setDuration(duration);
			actualFare.setVehicleTyle(vehicleType);
			actualFare.setBookingId(-1);
			
			PricingModel pricingModel = new PricingModel();
			actualFare.setFare(pricingModel.computeActualFare(vehicleType, distanceTravelled, duration, fareService));
			
			return actualFare;
		 }
		 
		 else {
			 CustomerBookingDetails customerBookingDetails = bookingService.getBookingDetailsById(bookingId);
			 GDistanceMatrix distanceMatrix = null;
			try {
				distanceMatrix = GoogleMapsUtils.getDistanceMatrix(customerBookingDetails.getFromLocation(), customerBookingDetails.getToLocation());
			} catch (Exception e) {
				
				e.printStackTrace();
			}

			

				ActualFare  actualFare = new ActualFare();
				
				vehicleType = vehicleType.replace("\n", " ");
			
				
				actualFare.setDistanceTravelled(Double.valueOf(customerBookingDetails.getExpectedDistance())*1000);
				

				
				actualFare.setDuration(distanceMatrix.getDurationValue());
				
				
				actualFare.setVehicleTyle(vehicleType);
				actualFare.setBookingId(customerBookingDetails.getBookingId());
				
				actualFare.setFare(Double.valueOf(customerBookingDetails.getExpectedFare()));
				
				return actualFare;
			 
			 
		 }}catch(Exception er){er.printStackTrace(); return  new ActualFare();}
				
			}

	
	@ResponseBody
	@RequestMapping(value = "/confirmRide", method = RequestMethod.POST)
	private CustomerBookingDetails bookRide(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Integer bookingId,
			@RequestParam(required = false) String driverName,
			@RequestParam String driverPhoneNumber,
			@RequestParam String vehicleNumber,
			@RequestParam(required = false) String vehicleType,
			@RequestParam Double driverLatitude,
			@RequestParam Double driverLongitude,
			@RequestParam(required = false) String driverLocation) {
		try{
		if (bookingId == null)
			return new CustomerBookingDetails(
					TruxErrorCodes.INVALID_BOOKING_ID.getCode(),
					TruxErrorCodes.INVALID_BOOKING_ID.getDescription());

		if (driverPhoneNumber == null)
			return new CustomerBookingDetails(
					TruxErrorCodes.INVALID_DRIVER_PHONENUMBER.getCode(),
					TruxErrorCodes.INVALID_DRIVER_PHONENUMBER.getDescription());

		if (vehicleNumber == null)
			return new CustomerBookingDetails(
					TruxErrorCodes.INVALID_VEHICLE_NUMBER.getCode(),
					TruxErrorCodes.INVALID_VEHICLE_NUMBER.getDescription());

		if (driverLatitude == null)
			return new CustomerBookingDetails(
					TruxErrorCodes.INVALID_LATITUDE.getCode(),
					TruxErrorCodes.INVALID_LATITUDE.getDescription());

		if (driverLongitude == null)
			return new CustomerBookingDetails(
					TruxErrorCodes.INVALID_LONGITUDE.getCode(),
					TruxErrorCodes.INVALID_LONGITUDE.getDescription());

		CustomerBookingDetails customerBookingDetails = bookingService.getBookingDetailsById(bookingId);
		
		

		if (customerBookingDetails != null) {
			
			if(!customerBookingDetails.getBookingStatus().equals("Pending")) {
				return new CustomerBookingDetails(
						TruxErrorCodes.BOOKING_ACCEPTEDBYSOME_OTHER_DRIVER.getCode(),
						TruxErrorCodes.BOOKING_ACCEPTEDBYSOME_OTHER_DRIVER.getDescription());
			}
			
			
			
			CurrentVehicleLocation currentVehicleLocation = vehicleLocationService.fetchVehicleLocation(driverPhoneNumber);
			
			if(currentVehicleLocation != null) {
			
			double customerLat  = customerBookingDetails.getFromLat();
			double customerLong  = customerBookingDetails.getFromLong();
			
			double driverLat = currentVehicleLocation.getLatitude();
			double driverLong = currentVehicleLocation.getLongitude();
			
			
		

			com.javadocmd.simplelatlng.LatLng firstUserLocation = new com.javadocmd.simplelatlng.LatLng(customerLat, customerLong);

			com.javadocmd.simplelatlng.LatLng secondUserLocation = new com.javadocmd.simplelatlng.LatLng(driverLat, driverLong);  

			double distanceInKM = (LatLngTool.distance(firstUserLocation, secondUserLocation, LengthUnit.KILOMETER));

		     double timeforTrucToReachUser = distanceInKM*60*60*1000/25;
		     
		     long timeToReachCustomerInEpoc = new Date().getTime()+(long)timeforTrucToReachUser;
		     
			
			customerBookingDetails.setRideTime(new Date(timeToReachCustomerInEpoc));
			customerBookingDetails.setExpectedRideStartTime(timeToReachCustomerInEpoc);
			} else {
				
				customerBookingDetails.setRideTime(new Date(new Date().getTime()+60*1000));
				customerBookingDetails.setExpectedRideStartTime(customerBookingDetails.getRideTime().getTime());

			}
			
			customerBookingDetails.setDriverName(driverName);
			customerBookingDetails.setDriverPhonenumber(driverPhoneNumber);
			customerBookingDetails.setVehicleNumber(vehicleNumber);
			//customerBookingDetails.setVehicleType(vehicleType);
			customerBookingDetails.setDriverLatitude(driverLatitude);
			customerBookingDetails.setDriverLongitude(driverLongitude);
			customerBookingDetails.setDriverLocation(driverLocation);
			customerBookingDetails.setBookingStatus("Confirmed");
			customerBookingDetails.setUpdatedDate(new Date());
			
			customerBookingDetails.setBooking_acceptedtime(new Date());
			customerBookingDetails.setBookingId(bookingId);
			try {
				
				DriverDeviceVehicleMapping deviceVehicleMapping = null;
				if(driverPhoneNumber!=null) {
					 deviceVehicleMapping =  registrationService.getDriverDeviceDetail(driverPhoneNumber);
					deviceVehicleMapping.setDriverStatus(1);
					registrationService.saveOrUpdateDDVM(deviceVehicleMapping);
				}
			 
				if(deviceVehicleMapping!=null){
					try{
					customerBookingDetails.setDstatus(deviceVehicleMapping.getDstatus());
					}catch(Exception er){er.printStackTrace();}
				}
				bookingService.savoOrUpdate(customerBookingDetails);
				new PusherUtil().notifyConsumerForBookingAndDriverForCancellation(customerBookingDetails);
				
			
				SMSTemplates.sendBookingConfirmation(customerBookingDetails);
				
			
				
				
				return customerBookingDetails;
			} catch (Exception e) {
				
				e.printStackTrace();
				return new CustomerBookingDetails(
						TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
						TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());
			}

		} else {
			return new CustomerBookingDetails(
					TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
					TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());
		}}catch(Exception er){er.printStackTrace();return new CustomerBookingDetails(
				TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
				TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());}
	}

	
	
	@ResponseBody
	@RequestMapping(value = "/addConsigneeDetails", method = RequestMethod.POST)
	private CustomerBookingDetails addConsigneeDetails(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Integer bookingId,  @RequestParam String consigneeName,  @RequestParam String consigneePhoneNumber) {
try{
		if (bookingId == null)
			return new CustomerBookingDetails(
					TruxErrorCodes.INVALID_BOOKING_ID.getCode(),
					TruxErrorCodes.INVALID_BOOKING_ID.getDescription());

		CustomerBookingDetails customerBookingDetails = bookingService
				.getBookingDetailsById(bookingId);

		if (customerBookingDetails != null) {
			customerBookingDetails.setConsigneeName(consigneeName);
			customerBookingDetails.setConsigneePhoneNumber(consigneePhoneNumber);
			try {
				bookingService.savoOrUpdate(customerBookingDetails);
				
				SMSTemplates.sendTrackingDetailsToConsignee(customerBookingDetails);
				
				if(customerBookingDetails.getPayment_mode() == 3)
					SMSTemplates.sendSMSForConsigneeToPay(customerBookingDetails);
				
				Click2CallUtils.whiteListNumber(consigneePhoneNumber);
				
				return customerBookingDetails;
			} catch (Exception e) {
				return new CustomerBookingDetails(
						TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
						TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());
			}

		} else {
			return new CustomerBookingDetails(
					TruxErrorCodes.BOOKING_ID_DOES_NOT_EXISTS.getCode(),
					TruxErrorCodes.BOOKING_ID_DOES_NOT_EXISTS.getDescription());
		}}catch(Exception er){er.printStackTrace();return new CustomerBookingDetails(
					TruxErrorCodes.BOOKING_ID_DOES_NOT_EXISTS.getCode(),
					TruxErrorCodes.BOOKING_ID_DOES_NOT_EXISTS.getDescription());}
	}

	
	

	
	
	@ResponseBody
	@RequestMapping(value = "/startRide", method = RequestMethod.POST)
	private CustomerBookingDetails startRide(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Integer bookingId) {
try{
		if (bookingId == null)
			return new CustomerBookingDetails(
					TruxErrorCodes.INVALID_BOOKING_ID.getCode(),
					TruxErrorCodes.INVALID_BOOKING_ID.getDescription());

		CustomerBookingDetails customerBookingDetails = bookingService
				.getBookingDetailsById(bookingId);

		if (customerBookingDetails != null) {
			customerBookingDetails.setBookingStatus("On Ride");
			customerBookingDetails.setUpdatedDate(new Date());
			try {
				bookingService.savoOrUpdate(customerBookingDetails);
				return customerBookingDetails;
			} catch (Exception e) {
				return new CustomerBookingDetails(
						TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
						TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());
			}

		} else {
			return new CustomerBookingDetails(
					TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
					TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());
		}}catch(Exception er){er.printStackTrace();
		return new CustomerBookingDetails(
					TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
					TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());}
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/changeBookingStatus", method = RequestMethod.POST)
	private CustomerBookingDetails changeState(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Integer bookingId, @RequestParam String bookingStatus) {
try{
		if (bookingId == null)
			return new CustomerBookingDetails(
					TruxErrorCodes.INVALID_BOOKING_ID.getCode(),
					TruxErrorCodes.INVALID_BOOKING_ID.getDescription());

		CustomerBookingDetails customerBookingDetails = bookingService
				.getBookingDetailsById(bookingId);

		if (customerBookingDetails != null) {
			customerBookingDetails.setBookingStatus(bookingStatus);
			customerBookingDetails.setUpdatedDate(new Date());
			try {
				
				//Need to Audit changes in Booking State
				
				if(bookingStatus.equals("Loading Start")){
					SMSTemplates.vehicleArrived(customerBookingDetails);
					customerBookingDetails.setLoading_starttime(new Date());
				}
				
				else if(bookingStatus.equals("Loading Complete")) {
					customerBookingDetails.setLoading_completetime(new Date());
				}
				
				else if(bookingStatus.equals("Ride Start")) {
					customerBookingDetails.setRide_starttime(new Date());
				}
				
				else if(bookingStatus.equals("Unloading Start")) {
					customerBookingDetails.setUnloading_starttime(new Date());
				}
				
				else if(bookingStatus.equals("Unloading Complete")) {
					customerBookingDetails.setUnloading_completetime(new Date());
				}
				
				
				bookingService.savoOrUpdate(customerBookingDetails);
				return customerBookingDetails;
			} catch (Exception e) {
				return new CustomerBookingDetails(
						TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
						TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());
			}

		} else {
			return new CustomerBookingDetails(
					TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
					TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());
		}
		}catch(Exception er){er.printStackTrace();
		return new CustomerBookingDetails(
		TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
		TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());}
	}
	
	
	

	@ResponseBody
	@RequestMapping(value = "/forcecompleteRide", method = RequestMethod.GET)
	private CustomerBookingDetails forcecompleteRide(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Integer bookingId,  @RequestParam(required = false) String forceComplete) {
		try{
		if (bookingId == null)
			return new CustomerBookingDetails(
					TruxErrorCodes.INVALID_BOOKING_ID.getCode(),
					TruxErrorCodes.INVALID_BOOKING_ID.getDescription());

	

		CustomerBookingDetails customerBookingDetails = bookingService
				.getBookingDetailsById(bookingId);

		if (customerBookingDetails != null) {
			
			
			if("Completed".equals(customerBookingDetails.getBookingStatus()) && (forceComplete != null))
				return new CustomerBookingDetails(TruxErrorCodes.BOOKING_ALREADY_COMPLETED.getCode(), TruxErrorCodes.BOOKING_ALREADY_COMPLETED.getDescription());
			
			customerBookingDetails.setBookingStatus("Completed");
			
			Double totalFare = Double.valueOf(customerBookingDetails.getExpectedFare().trim());
			Double totalDistanceTravelled = Double.valueOf(customerBookingDetails.getExpectedDistance().trim())*1000;
			FareRates fareRates =  fareService.getFareByVehicleType(customerBookingDetails.getVehicleType());
			
			Double travelDuration = computeTravelDuration(totalFare, totalDistanceTravelled, fareRates);
			if(travelDuration <  0)
				return new CustomerBookingDetails(TruxErrorCodes.REVERSE_COMPUTATION_LESS_THAN_ZERO_TRAVEL_TIME.getCode(), TruxErrorCodes.REVERSE_COMPUTATION_LESS_THAN_ZERO_TRAVEL_TIME.getDescription());

			
			customerBookingDetails.setTotalFare(totalFare);
			customerBookingDetails
					.setTotalDistanceTravelled(totalDistanceTravelled);
			customerBookingDetails.setUpdatedDate(new Date());
			
			customerBookingDetails.setJourneycompletetime(new Date());
			
			if(travelDuration != null)
			customerBookingDetails.setTripDuration(travelDuration);
			try {
				customerBookingDetails.setBookingId(bookingId);
				bookingService.savoOrUpdate(customerBookingDetails);
				String driverPhoneNumber = customerBookingDetails.getDriverPhonenumber();
				if(driverPhoneNumber!=null) {
					DriverDeviceVehicleMapping deviceVehicleMapping =  registrationService.getDriverDeviceDetail(driverPhoneNumber);
					deviceVehicleMapping.setDriverStatus(0);
					registrationService.saveOrUpdateDDVM(deviceVehicleMapping);
					
					customerBookingDetails.setProcessed(false);
					
					truxStartUpService.mailingQueue.add(customerBookingDetails);
					
				}
				
				
				return customerBookingDetails;
			} catch (Exception e) {
				return new CustomerBookingDetails(
						TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
						TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());
			}

		} else {
			return new CustomerBookingDetails(
					TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
					TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());
		}}catch(Exception er){er.printStackTrace();return new CustomerBookingDetails(
					TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
					TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());}
	}
	

	private Double computeTravelDuration(Double totalFare, Double totalDistanceTravelled, FareRates fareRates) {
		try{
	//	(882-(199+22*30))*2*60
		
		Double duration = 0.0;
		
		if(totalDistanceTravelled >= fareRates.getInclusive_of_km()*1000) {
			duration = ((totalFare-(fareRates.getBase_fare()+((totalDistanceTravelled-fareRates.getInclusive_of_km()*1000)/1000)*fareRates.getRate_per_km()))*60)/fareRates.getRate_per_trip_minute();
			// duration = ((929.0-(599+((11000-3*1000)/1000)*40))*60)/0.5;
			
		}
		else {
			duration = (totalFare-fareRates.getBase_fare())*60/fareRates.getRate_per_trip_minute();
		}
		
		return duration;
		}catch(Exception er){er.printStackTrace(); return 0.0;}
	}



	@ResponseBody
	@RequestMapping(value = "/completeRide", method = RequestMethod.POST)
	private CustomerBookingDetails completeRide(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Integer bookingId,
			@RequestParam Double totalFare,
			@RequestParam Double totalDistanceTravelled, @RequestParam(required = false) Double travelDuration, @RequestParam(required = false) String forceComplete) {
try{
		if (bookingId == null)
			return new CustomerBookingDetails(
					TruxErrorCodes.INVALID_BOOKING_ID.getCode(),
					TruxErrorCodes.INVALID_BOOKING_ID.getDescription());

		if (totalFare == null)
			return new CustomerBookingDetails(
					TruxErrorCodes.INVALID_TOTAL_FARE.getCode(),
					TruxErrorCodes.INVALID_TOTAL_FARE.getDescription());

		if (totalDistanceTravelled == null)
			return new CustomerBookingDetails(
					TruxErrorCodes.INVALID_DISTANCE_TRAVELLED.getCode(),
					TruxErrorCodes.INVALID_DISTANCE_TRAVELLED.getDescription());

		CustomerBookingDetails customerBookingDetails = bookingService
				.getBookingDetailsById(bookingId);

		if (customerBookingDetails != null) {
			
			if("Completed".equals(customerBookingDetails.getBookingStatus()) && (forceComplete != null))
				return new CustomerBookingDetails(TruxErrorCodes.BOOKING_ALREADY_COMPLETED.getCode(), TruxErrorCodes.BOOKING_ALREADY_COMPLETED.getDescription());
		
			customerBookingDetails.setBookingStatus("Completed");
			customerBookingDetails.setTotalFare(totalFare);
			customerBookingDetails.setTotalDistanceTravelled(totalDistanceTravelled);
			customerBookingDetails.setUpdatedDate(new Date());
			
			customerBookingDetails.setJourneycompletetime(new Date());
			
			if(travelDuration != null)
			customerBookingDetails.setTripDuration(travelDuration);
			try {
				bookingService.savoOrUpdate(customerBookingDetails);
				String driverPhoneNumber = customerBookingDetails.getDriverPhonenumber();
				if(driverPhoneNumber!=null) {
					DriverDeviceVehicleMapping deviceVehicleMapping =  registrationService.getDriverDeviceDetail(driverPhoneNumber);
					deviceVehicleMapping.setDriverStatus(0);
					registrationService.saveOrUpdateDDVM(deviceVehicleMapping);
					
					customerBookingDetails.setProcessed(false);
					
					truxStartUpService.mailingQueue.add(customerBookingDetails);
					
				}
				
				
				return customerBookingDetails;
			} catch (Exception e) {
				return new CustomerBookingDetails(
						TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
						TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());
			}

		} else {
			return new CustomerBookingDetails(
					TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
					TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());
		}
				}catch(Exception er){er.printStackTrace();
				return new CustomerBookingDetails(
					TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
					TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());}
	}

	@ResponseBody
	@RequestMapping(value = "/cancelRide", method = RequestMethod.POST)
	private CustomerBookingDetails cancelRide(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String email, @RequestParam Integer bookingId, @RequestParam(required = false) String cancellationReason) {
		try{
		if(email == null)
			return new CustomerBookingDetails(TruxErrorCodes.EMAIL_NOT_VALID.getCode(), TruxErrorCodes.EMAIL_NOT_VALID.getDescription());
		
		if(bookingId == null)
			return new CustomerBookingDetails(TruxErrorCodes.INVALID_BOOKING_ID.getCode(), TruxErrorCodes.INVALID_BOOKING_ID.getDescription());
		
		CustomerBookingDetails customerBooking = bookingService.getBookingDetailsByEmail(email, bookingId);
		
		if(customerBooking != null){
			if(customerBooking.getBookingStatus().equalsIgnoreCase(TruxConstant.STATUS_PENDING)){
				customerBooking.setBookingStatus(TruxConstant.STATUS_CANCELLED);
				try {
					customerBooking.setCancellationType(1);
					customerBooking.setCancellationReason(cancellationReason);
					bookingService.savoOrUpdate(customerBooking);
					new PusherUtil().notifyTruxForCancellation(customerBooking);
					
					SMSTemplates.cancelBooking(customerBooking);
					
					return customerBooking;
				} catch (Exception e) {
					return new CustomerBookingDetails(
							TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
							TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());
				}
			}else{
				return new CustomerBookingDetails(
						TruxErrorCodes.CANCEL_BOOKING_EXCEPTION.getCode(),
						TruxErrorCodes.CANCEL_BOOKING_EXCEPTION.getDescription());
			}
		}else{
			return new CustomerBookingDetails(TruxErrorCodes.INVALID_BOOKING.getCode(),
			TruxErrorCodes.INVALID_BOOKING.getDescription());
		}}catch(Exception er){er.printStackTrace();
		return new CustomerBookingDetails(
				TruxErrorCodes.CANCEL_BOOKING_EXCEPTION.getCode(),
				TruxErrorCodes.CANCEL_BOOKING_EXCEPTION.getDescription());}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/cancelRideByDriver", method = RequestMethod.POST)
	private CustomerBookingDetails cancelRideByDriver(HttpServletRequest request,
			HttpServletResponse response,  @RequestParam Integer bookingId, @RequestParam(required = false) String cancellationReason, @RequestParam(required = false) Integer cancellationId) {
		try{
		if(bookingId == null)
			return new CustomerBookingDetails(TruxErrorCodes.INVALID_BOOKING_ID.getCode(), TruxErrorCodes.INVALID_BOOKING_ID.getDescription());
		
		CustomerBookingDetails customerBooking = bookingService.getBookingDetailsById(bookingId);
		
		if(customerBooking != null){
			
				customerBooking.setBookingStatus(TruxConstant.STATUS_CANCELLED);
				try {
					customerBooking.setCancellationType(0);
					try {
					if(cancellationId!=null)
						cancellationReason = driverCancellation[cancellationId];
					} catch (Exception e ) {
						e.printStackTrace();
					}
					
					//System.out.println(cancellationReason);
					//System.out.println(cancellationId);
					customerBooking.setCancellationReason(cancellationReason);
					bookingService.savoOrUpdate(customerBooking);
					
					String driverPhoneNumber = customerBooking.getDriverPhonenumber();
					DriverDeviceVehicleMapping driverDeviceVehicleMapping = registrationService.getDriverDeviceDetail(driverPhoneNumber);
					
					if(driverDeviceVehicleMapping == null)
						return new CustomerBookingDetails(
								TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
								TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());		
					
					driverDeviceVehicleMapping.setDriverStatus(0);
					
					registrationService.saveOrUpdateDDVM(driverDeviceVehicleMapping);
					
				
					//Use Correct Template
				//	SMSTemplates.cancelBooking(customerBooking);
					
					return customerBooking;
				} catch (Exception e) {
					return new CustomerBookingDetails(
							TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
							TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());
				}
			}
			
		else{
			return new CustomerBookingDetails(TruxErrorCodes.INVALID_BOOKING.getCode(),
			TruxErrorCodes.INVALID_BOOKING.getDescription());
		}}catch(Exception er){er.printStackTrace();
		return new CustomerBookingDetails(
				TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
				TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());}
	}
	

	
	
	@ResponseBody
	@RequestMapping(value = "/ridedetails", method = RequestMethod.GET)
	private CustomerBookingDetails getRideDetails(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Integer bookingId) {
		try{
		if (bookingId == null)
			return new CustomerBookingDetails(
					TruxErrorCodes.INVALID_BOOKING_ID.getCode(),
					TruxErrorCodes.INVALID_BOOKING_ID.getDescription());

		CustomerBookingDetails customerBookingDetails = bookingService
				.getBookingDetailsById(bookingId);

		if (customerBookingDetails != null) {
			return customerBookingDetails;
		} else {
			return new CustomerBookingDetails(
					TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
					TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());
		}}catch(Exception er){
			er.printStackTrace();
			return new CustomerBookingDetails(
					TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
					TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/ridebooking", method = RequestMethod.POST)
	private void bookUserRide(HttpServletRequest request,
			HttpServletResponse response) throws ParseException {
try{
		String bookingDate = request.getParameter("bookingDate");

		String bookingTime = request.getParameter("bookingTime");

		String city = request.getParameter("city");

		bookingTime = bookingTime.replace("AM", "00").replace("PM", "00")
				.replace(" ", "");
		String rideTiming = bookingDate.trim() + " " + bookingTime.trim();

		Date rideDateTime = TruxUtils.convertStringToDate(rideTiming);

		CustomerBookingDetails customerBookingDetails = new CustomerBookingDetails();

		customerBookingDetails.setRideTime(rideDateTime);
		customerBookingDetails.setCity(city);

		request.getSession().setAttribute("customerBookingDetails",
				customerBookingDetails);

		TruxUtils.pageRedisrection("/pages/book.jsp", request, response);
		}catch(Exception er){er.printStackTrace();TruxUtils.pageRedisrection("/pages/book.jsp", request, response);}

	}

	@ResponseBody
	@RequestMapping(value = "/book", method = RequestMethod.POST)
	private void userRideDetail(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try{
		CustomerBookingDetails customerBookingDetails = (CustomerBookingDetails) request
				.getSession().getAttribute("customerBookingDetails");
		customerBookingDetails.setCreatedDate(new Date());

		if (request.getParameter("username") != null
				&& !request.getParameter("username").isEmpty()) {
			customerBookingDetails.setCustomerName(request
					.getParameter("username"));
		}
		if (request.getParameter("phonenumber") != null
				&& !request.getParameter("phonenumber").isEmpty()) {
			customerBookingDetails.setCustmerPhonenumber(request
					.getParameter("phonenumber"));
		}
		if (request.getParameter("vehicleType") != null
				&& !request.getParameter("vehicleType").isEmpty()) {
			customerBookingDetails.setVehicleType(request
					.getParameter("vehicleType"));
		}
		if (request.getParameter("fromLocation") != null
				&& !request.getParameter("fromLocation").isEmpty()) {
			customerBookingDetails.setFromLocation(request
					.getParameter("fromLocation"));
		}
		if (request.getParameter("toLocation") != null
				&& !request.getParameter("toLocation").isEmpty()) {
			customerBookingDetails.setToLocation(request
					.getParameter("toLocation"));
		}
		if (request.getParameter("email") != null
				&& !request.getParameter("email").isEmpty()) {
			customerBookingDetails.setCustomerEmail(request
					.getParameter("email"));
		}

		request.getSession().removeAttribute("customerBookingDetails");

		bookingService.savoOrUpdate(customerBookingDetails);

		request.setAttribute("bookingId", customerBookingDetails.getBookingId());

		TruxUtils.pageRedisrection("/pages/thankyou.jsp", request, response);
		}catch(Exception er){er.printStackTrace();TruxUtils.pageRedisrection("/pages/thankyou.jsp", request, response);}
	}
	
	@ResponseBody
	@RequestMapping(value = "/booking_history", method = RequestMethod.GET)
	private BookingHistory bookinghistory(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String email) throws Exception {
		try{
		if(email == null)
			return new BookingHistory(TruxConstant.NO_RESULT_FOUND);
		
		List<CustomerBookingDetails> userBookingsHistory = bookingService.getBookingHistory(email);
		
		if(userBookingsHistory != null && !userBookingsHistory.isEmpty()){
			BookingHistory userBookingHistory = new BookingHistory();
			
			userBookingHistory.setStatus(TruxConstant.STATUS_SUCCESS);
			
			List<BookingDetails> all = new ArrayList<BookingHistory.BookingDetails>();
			
			List<BookingDetails> upcoming = new ArrayList<BookingHistory.BookingDetails>();
			
			List<BookingDetails> completed = new ArrayList<BookingHistory.BookingDetails>();
			
			for (CustomerBookingDetails customerBookingDetails : userBookingsHistory) {
				
				BookingDetails bookingDetails = userBookingHistory.new BookingDetails(customerBookingDetails.getBookingId(), customerBookingDetails.getBookingStatus(), 
						customerBookingDetails.getExpectedRideStartTime(), customerBookingDetails.getFromLocation(), "Delhi", customerBookingDetails.getVehicleType());
				
				all.add(bookingDetails);

				if(customerBookingDetails.getBookingStatus() != null){
					if(customerBookingDetails.getBookingStatus().equalsIgnoreCase(TruxConstant.STATUS_PENDING)){
						upcoming.add(bookingDetails);
					}else if(customerBookingDetails.getBookingStatus().equalsIgnoreCase(TruxConstant.STATUS_COMPLETED) 
											|| customerBookingDetails.getBookingStatus().equalsIgnoreCase(TruxConstant.STATUS_CANCELLED)){
						completed.add(bookingDetails);
					}
				}
			}
			
			userBookingHistory.setAll(all);
			userBookingHistory.setCompleted(completed);
			userBookingHistory.setUpcoming(upcoming);
			return userBookingHistory;
		}
		
		return new BookingHistory(TruxConstant.NO_RESULT_FOUND);
		}catch(Exception er){er.printStackTrace();return new BookingHistory(TruxConstant.NO_RESULT_FOUND);}
	}
	@ResponseBody
	@RequestMapping(value = "/updateBookingVehicle", method = RequestMethod.GET)
	private CustomerBookingDetails updateBookingVehicle(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer bookingId,
			@RequestParam String vehicleType) {
	try{
		CustomerBookingDetails customerBookingDetails =	bookingService.updateBookedVehicle(bookingId, vehicleType);
		
		
		//DriverDeviceVehicleMapping deviceVehicleMapping =  registrationService. DriverDeviceDetail(bookingId);
		//PusherUtil.notifyParticularTruxs(customerBookingDetails, deviceVehicleMapping);
		
		return customerBookingDetails;
	}catch(Exception er){er.printStackTrace(); return new CustomerBookingDetails();}
		
	}
	



	public List<ConsumerRegistration> getList() {
		return list;
	}



	public void setList(List<ConsumerRegistration> list) {
		this.list = list;
	}

 

	

 
	
}
