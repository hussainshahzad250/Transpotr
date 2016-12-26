package com.trux.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.maps.model.LatLng;
import com.trux.constants.SMSTemplates;
import com.trux.enumerations.TruxErrorCodes;
import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.DriverRegistration;
import com.trux.model.GDistanceMatrix;
import com.trux.model.LeaseBooking;
import com.trux.model.LeaseBookingStop;
import com.trux.model.OrganizationBookingRegistration;
import com.trux.model.OrganizationalClientBookingDetails;
import com.trux.model.VehicleRegistration;
import com.trux.pricing.PricingModel;
import com.trux.service.ConsumerService;
import com.trux.service.DriverLoginHistoryService;
import com.trux.service.FareService;
import com.trux.service.LeaseBookingService;
import com.trux.service.LeaseBookingStopService;
import com.trux.service.MultiDropPointBookingDetailsService;
import com.trux.service.OrgBookingDAOService;
import com.trux.service.OrganizationMasterService;
import com.trux.service.RegistrationService;
import com.trux.service.VehicleLocationService;
import com.trux.utils.Click2CallUtils;
import com.trux.utils.DateFormaterUtils;
import com.trux.utils.GoogleMapsUtils;
import com.trux.utils.PusherUtil;
@Controller    
@RequestMapping(value="/orgapp")
public class MultiDropPointController {
	@Autowired
	private RegistrationService registrationService;
	@Autowired 
	private OrganizationMasterService organizationMasterService;
	@Autowired
	private MultiDropPointBookingDetailsService multiDropPointBookingDetailsService;
	public final static  int DISTANCE_THRESHOLD_LIMIT = 15;
	@Autowired
	private ConsumerService consumerService;
	 	
	@Autowired
	private VehicleLocationService vehicleLocationService;
	@Autowired
	private FareService fareService;
	@Autowired
	private OrgBookingDAOService orgBookingDAOService;
	@Autowired
	private LeaseBookingService  leaseBookingService;
	@Autowired
	private LeaseBookingStopService leaseBookingStopService;
	@Autowired
	DriverLoginHistoryService driverLoginHistoryService;
	 private Integer leaseBookingId;
	 private Integer leaseBookingStopId;
	@ResponseBody
	@RequestMapping(value="/orgBooking", method=RequestMethod.POST)
	private OrganizationBookingRegistration registration(HttpServletRequest request,HttpServletResponse response, 			
			@RequestParam String parentOrgId, 
			@RequestParam String orgName, 
			@RequestParam String contactPerson, 
			@RequestParam String email,
			@RequestParam String fromAddress,
			@RequestParam String toAddress, 
			@RequestParam String contactPersonPhone
			){
		try{		
		if(email == null)
			return new OrganizationBookingRegistration(TruxErrorCodes.EMAIL_NOT_VALID.getCode(), TruxErrorCodes.EMAIL_NOT_VALID.getDescription());
			 	
		if(contactPersonPhone == null)
			return new OrganizationBookingRegistration(TruxErrorCodes.INVALID_PHONENUBER.getCode(), TruxErrorCodes.INVALID_PHONENUBER.getDescription());		
		//OrganizationBookingRegistration userDetail = orgBookingDAOService.orgBookingDetails(contactPersonPhone);		
		//if(userDetail != null)
        //	return new OrganizationBookingRegistration(TruxErrorCodes.PHONE_NO_ALREADY_REGISTERED.getCode(), TruxErrorCodes.PHONE_NO_ALREADY_REGISTERED.getDescription());
		OrganizationBookingRegistration	userDetail = new  OrganizationBookingRegistration(parentOrgId, orgName, contactPerson, email, fromAddress, toAddress, contactPersonPhone);
		 try{
			 orgBookingDAOService.orgBooking(userDetail);
			 try {
			 Click2CallUtils.whiteListNumber(contactPersonPhone);
			 } catch(Exception e) {				 
				 userDetail.setErrorMessage("Unable to WhiteList Phone Number.");
			 }
	        }catch(Exception e){
	        	e.printStackTrace();
	        	return new OrganizationBookingRegistration(TruxErrorCodes.UNHANDLED_ERROR.getCode(), TruxErrorCodes.UNHANDLED_ERROR.getDescription());
	        }		
		return userDetail;
		}catch(Exception er){er.printStackTrace();return new OrganizationBookingRegistration(TruxErrorCodes.UNHANDLED_ERROR.getCode(), TruxErrorCodes.UNHANDLED_ERROR.getDescription());}
	}
	
	
 
	@SuppressWarnings({ "deprecation", "unused" })
	@ResponseBody
	@RequestMapping(value = "/multiDropPointRide", method = RequestMethod.POST)
	private LeaseBooking multiDropPointRide(HttpServletRequest request,HttpServletResponse response,
			@RequestParam String mobile,    
			@RequestParam String startLocation,
			@RequestParam String dropLocation,
			@RequestParam Integer rideFlag,  
			@RequestParam String startEndFlag,
			@RequestParam(required=false) Integer bookingLeaseId) {
		HttpSession session=request.getSession();
		try{
			VehicleRegistration vehicle=null;	
            DriverDeviceVehicleMapping driverDeviceVehicleMappings = registrationService.getDriverDeviceDetail(mobile);
			DriverRegistration dr=registrationService.getDriverDetail(mobile); // get driver details
			if(dr!=null){
			vehicle=registrationService.getVehicleDetailsByDriverId(dr.getId());
		    }
		if (mobile == null)
		return new LeaseBooking(TruxErrorCodes.EMAIL_NOT_VALID.getCode(),TruxErrorCodes.EMAIL_NOT_VALID.getDescription());		
		LatLng source = null;
		String fromLocation="";
		 String toLocation="";
		 System.out.println(startLocation);
		try {
			 int indexS=startLocation.indexOf("Address:\n");
			  if(indexS==-1 || indexS==1){
					  fromLocation=startLocation;
					  source = new LatLng(getLat(startLocation), getLong(startLocation));
					  if(getLat(startLocation)==0 && getLong(startLocation)==0){
						  source = GoogleMapsUtils.getLatLongBasedOnAddress(startLocation);
					  }
				  }else{
			fromLocation=startLocation.substring(indexS+9).replace("\n", " ");
	    	source =  new LatLng(getLat(startLocation), getLong(startLocation));
	    	 if(getLat(startLocation)==0 && getLong(startLocation)==0){
	    		 source = GoogleMapsUtils.getLatLongBasedOnAddress(fromLocation);
	    	 }
			}
		} catch (Exception e) {
			e.printStackTrace();
			source = new LatLng(28.4863626802915, 77.09126018029151);
		}		
		LatLng destination = null;		
		try {
         int indexS=dropLocation.indexOf("Address:\n");
		   
		   if(indexS==-1 || indexS==1){
			  try{ 
			  toLocation=dropLocation;
			  destination = new LatLng(getLat(dropLocation), getLong(dropLocation));
			  if(getLat(dropLocation)==0 && getLong(dropLocation)==0){
			  destination = GoogleMapsUtils.getLatLongBasedOnAddress(dropLocation);
			  }
			  }catch(Exception er){er.printStackTrace();}
		   }else{
			   try{
	     toLocation=dropLocation.substring(indexS+9).replace("\n", " ");
		 destination = new LatLng(getLat(dropLocation), getLong(dropLocation));
		 if(getLat(dropLocation)==0 && getLong(dropLocation)==0){
		 destination = GoogleMapsUtils.getLatLongBasedOnAddress(toLocation);
		 }
		   }catch(Exception er){er.printStackTrace();}
		   }
		}
		catch (Exception e) {
			e.printStackTrace();
			destination = new LatLng(28.5903481, 77.3383321);
		}		
		GDistanceMatrix distanceMatrix = null;	
		if(destination!=null){
		 try {
			distanceMatrix = GoogleMapsUtils.getDistanceMatrix(source, destination);
		} catch (Exception e1) {			
			e1.printStackTrace();
		}
		}
		double approxfare =0;
		double approxDistance =0;
		String vehicleType="";
	if(driverDeviceVehicleMappings!=null && driverDeviceVehicleMappings.getVehicleType()!=null){
		vehicleType=driverDeviceVehicleMappings.getVehicleType();
	}
		if(distanceMatrix!=null){
		  approxDistance =  distanceMatrix.getDistanceValue()/1000;
		  approxfare = new PricingModel(approxDistance, 0, vehicleType, distanceMatrix, false, fareService).computeFare();
		}
		String clientAssosiatedBy="";
		if(dr!=null && dr.getAssosiatedBy()!=null){
			clientAssosiatedBy=dr.getAssosiatedBy();
		}
		OrganizationalClientBookingDetails orgRegistrations= new OrganizationalClientBookingDetails(clientAssosiatedBy, mobile,"", source.lat, source.lng,fromLocation, destination.lat, destination.lng, toLocation, new Date(),	new Date(),  rideFlag, vehicleType);		
		orgRegistrations.setExpectedFare(approxfare+"");
		orgRegistrations.setExpectedDistance(approxDistance+"");
		List<DriverDeviceVehicleMapping> allRegisteredDeviceList = registrationService.getAllAvailableDrivers();
		
	 	OrganizationalClientBookingDetails dpointBooking=null;
	 	if(dr!=null && dr.getAssosiatedBy()!=null&& !dr.getAssosiatedBy().equals("")){
          orgRegistrations.setCustomerName(dr.getAssosiatedBy());
		}
 
	 	orgRegistrations.setDriverLatitude(source.lat);
	 	orgRegistrations.setDriverLongitude(source.lng);
	 	orgRegistrations.setDriverLocation(dropLocation);
	 	if(driverDeviceVehicleMappings!=null && driverDeviceVehicleMappings.getDriverName()!=null){
	 	orgRegistrations.setDriverName(driverDeviceVehicleMappings.getDriverName());
	 	}
	 	if(driverDeviceVehicleMappings!=null && driverDeviceVehicleMappings.getDriverPhoneNumber()!=null){
	 	orgRegistrations.setDriverPhonenumber(driverDeviceVehicleMappings.getDriverPhoneNumber());
	 	}
	 	if(driverDeviceVehicleMappings!=null && driverDeviceVehicleMappings.getVehicleNumber()!=null){
	 	orgRegistrations.setVehicleNumber(driverDeviceVehicleMappings.getVehicleNumber());
	 	}
		if(driverDeviceVehicleMappings!=null && driverDeviceVehicleMappings.getVehicleType()!=null){			 
	 	orgRegistrations.setVehicleType(driverDeviceVehicleMappings.getVehicleType());
		}
	 	
		try {
			Integer companyId=new Integer(0);
			if( dr!=null && dr.getAssosiatedBy()!=null&& !dr.getAssosiatedBy().equals("")){
				companyId=Integer.parseInt(dr.getAssosiatedBy());
			}
			Integer driverID=0;
			if(driverDeviceVehicleMappings!=null&&  driverDeviceVehicleMappings.getDriverId()!=null){					
				driverID= driverDeviceVehicleMappings.getDriverId();
			}
			Integer vehicleID=0;
			if(driverDeviceVehicleMappings!=null&&  driverDeviceVehicleMappings.getVehicleId()!=null){	
				vehicleID= driverDeviceVehicleMappings.getVehicleId();
			}
			
			Date rideDate =  null;
 			String ridTile=  DateFormaterUtils.convertGMTToISTWithDatesWithSlash(new Date().toString()).replace(".0", "");
 			    
 			if(ridTile == null || ridTile.equals("0")){
 				orgRegistrations.setBooknow(1);
 			   rideDate = new Date(new Date().getTime()+1000*3600);
 			} else {
 				orgRegistrations.setBooknow(0);
 				try{
 				rideDate = new Date(ridTile.trim());	
 				}catch(Exception er){er.printStackTrace();}
 			}
 			orgRegistrations.setRideTime(rideDate);		
 			long loadingTime = 0;
 			long unloadingTime = 0;
 			long journeyTime = (new Double(approxDistance*144)).longValue()+ loadingTime + unloadingTime ;		
 			if(rideDate!=null){
 				orgRegistrations.setExpectedRideStartTime(rideDate.getTime());
 			}else{
 				orgRegistrations.setExpectedRideStartTime((long)0);
 			}
 			if(rideDate!=null)
 				orgRegistrations.setExpectedRideEndTime(rideDate.getTime()+journeyTime*1000);
 			else 
 			orgRegistrations.setExpectedRideEndTime((long)0);		
 		 	orgRegistrations.setBooking_time(new Date());
			 String createdDate="";
			 String updatedDate="";
			 String dropLocationReachTimes="";
			 String afterDropStartTimes="";
			 String rideTimes="";
			 try{  
			  createdDate= DateFormaterUtils.convertGMTToISTWithDatesWithSlash(new Date().toString()).replace(".0", "");
		        }catch(Exception er){} try{ 
			  updatedDate= DateFormaterUtils.convertGMTToISTWithDatesWithSlash(new Date().toString()).replace(".0", "");
		         }catch(Exception er){} try{ 
			  dropLocationReachTimes= DateFormaterUtils.convertGMTToISTWithDatesWithSlash(new Date().toString()).replace(".0", "");
		         }catch(Exception er){} try{ 
			  afterDropStartTimes= DateFormaterUtils.convertGMTToISTWithDatesWithSlash(new Date().toString()).replace(".0", "");
		          }catch(Exception er){}
		      try{
			  rideTimes=DateFormaterUtils.convertGMTToISTWithDatesWithSlash(new Date().toString()).replace(".0", "");
			  System.out.println("RideTimes "+rideTimes);
			  }catch(Exception er){}
			LeaseBooking leaseBookingS=null;
			LeaseBookingStop leaseBookingStopS=null;
		 	if(startEndFlag!=null && startEndFlag.equals("start")){
		 	if(rideFlag==0){
		 		try{
		 	    LeaseBooking leaseBooking=new LeaseBooking(driverID, vehicleID,companyId, new Date(rideTimes.trim()), new Date(dropLocationReachTimes.trim()),source.lat, source.lng, fromLocation, destination.lat, destination.lng, toLocation, approxDistance, journeyTime, rideFlag);
		 		leaseBooking.setDriverMobile(mobile);
		 		leaseBooking.setCreatedDateTime(new Date(createdDate.trim()));
		 		long totalDuration=(new Date().getTime() -new Date().getTime());
		 		leaseBooking.setTotalDuration(totalDuration);
		 		leaseBookingS=leaseBookingService.saveLeaseBooking(leaseBooking);
                orgRegistrations.setBookingId(leaseBookingS.getBookingLeaseId());
			 	 leaseBookingId=leaseBookingS.getBookingLeaseId();
			   }catch(Exception er){er.printStackTrace();}
		 		} 
			}
		 	try{
		 		
		 	if(startEndFlag!=null && startEndFlag.equals("resume")){
		 		try{		
		 		  
		 		 if(leaseBookingStopId!=0 && bookingLeaseId!=null ||  bookingLeaseId!=0){
		 			    LeaseBookingStop leaseBookingStop=new LeaseBookingStop(bookingLeaseId,new Date(dropLocationReachTimes.trim()),new Date(afterDropStartTimes.trim()), destination.lat, destination.lng, "",toLocation);
				 		leaseBookingStop.setUpdatedTime(new Date(updatedDate.trim()));
				 	 	leaseBookingStop.setTotalDistance(approxDistance);
				 		List<LeaseBookingStop> listOfLeaseBookingStop=	leaseBookingStopService.getLeaseBookingStopListByBookingLeaseId(leaseBookingStop);
		 			 	if(listOfLeaseBookingStop!=null && listOfLeaseBookingStop.size()>0 ){
		 				int size=listOfLeaseBookingStop.size();
		 				leaseBookingStopS=listOfLeaseBookingStop.get(size-1);
		 				}
		 			 	leaseBookingStop.setBkLsStpId(leaseBookingStopS.getBkLsStpId()); 
		 			    leaseBookingStopS=leaseBookingStopService.updateLeaseBookingStop(leaseBookingStop);
			 			}
		 		if(leaseBookingStopId!=0 && bookingLeaseId!=null ||  bookingLeaseId!=0){	 			  
	 			LatLng innserSource = null;	 			
	 			try {
	 				if(leaseBookingStopS!=null && leaseBookingStopS.getDropLocation()!=null && !leaseBookingStopS.getDropLocation().equals("") ){
				 	 innserSource = GoogleMapsUtils.getLatLongBasedOnAddress(leaseBookingStopS.getDropLocation());}
	 			 } catch (Exception e) {
	 				e.printStackTrace();
	 				innserSource = new LatLng(28.4863626802915, 77.09126018029151);
	 			}	
	 			LatLng innerDestination = null;		
	 			try {
	 				if(toLocation!=null   && !toLocation.equals("") ){
			 		    innerDestination = GoogleMapsUtils.getLatLongBasedOnAddress(toLocation);
	 				}
	 			  } 
	 			catch (Exception e) {
	 				e.printStackTrace();
	 				innerDestination = new LatLng(28.5903481, 77.3383321);
	 			}
	 		    GDistanceMatrix distanceMatrixs = null;	
	 			if(innerDestination!=null && innserSource!=null){
	 			 try {
	 				distanceMatrixs = GoogleMapsUtils.getDistanceMatrix(innserSource, innerDestination);
	 			} catch (Exception e1) {			
	 				e1.printStackTrace();
	 			}
	 			}

	 			double approxDistances=  distanceMatrixs.getDistanceValue()/1000;
		 		 if(leaseBookingStopId!=0 && bookingLeaseId!=null ||  bookingLeaseId!=0){
		 			  LeaseBooking leaseBooking=new LeaseBooking(driverID, vehicleID,companyId,new Date(rideTimes.trim()), new Date(dropLocationReachTimes.trim()), innserSource.lat, innserSource.lng, fromLocation, innerDestination.lat, innerDestination.lng, leaseBookingStopS.getDropLocation(), approxDistances, journeyTime, rideFlag);
			 			leaseBooking.setUpdatedDateTime(new Date(updatedDate.trim()));
			 			leaseBooking.setDriverMobile(mobile);
				 		leaseBooking.setBookingLeaseId(bookingLeaseId);
				 		
				 		leaseBookingS=leaseBookingService.updateLeaseBookingAtResume(leaseBooking);
				 		}
		 		}
		 		}catch(Exception er){er.printStackTrace();}
		 	   }
		 	 
		 	if(startEndFlag!=null && startEndFlag.equals("drop")){
		 		if(bookingLeaseId!=null ||  bookingLeaseId!=0){
		 		try{ 
		 			LeaseBookingStop leaseBookingStop=new LeaseBookingStop(bookingLeaseId,new Date(dropLocationReachTimes.trim()),new Date(afterDropStartTimes.trim()), destination.lat, destination.lng, "",toLocation);
		 			leaseBookingStop.setTotalDistance(approxDistance);
		 			leaseBookingStop.setCreatedTime(new Date(createdDate.trim()));
				 	leaseBookingStopS=leaseBookingStopService.saveLeaseBookingStop(leaseBookingStop);
			    	leaseBookingStopId=leaseBookingStopS.getBkLsStpId();	
			    	orgRegistrations.setBookingId(leaseBookingStopId);	    	
			      }catch(Exception er){}
		 		}
			    	if(bookingLeaseId!=0){
			    		try{ 
				 		 
			    			if(leaseBookingStopId!=0 && bookingLeaseId!=null ||  bookingLeaseId!=0){
			  	 			  
			    	 			LatLng innserSource = null;
			    	 			
			    	 			try {
			    	 				if(fromLocation!=null &&   !fromLocation.equals("") ){
			    	 				 innserSource = GoogleMapsUtils.getLatLongBasedOnAddress(fromLocation);}
			    	 			 } catch (Exception e) {
			    	 				e.printStackTrace();
			    	 				innserSource = new LatLng(28.4863626802915, 77.09126018029151);
			    	 			}	
			    	 			LatLng innerDestination = null;		
			    	 			try {
			    	 				if(leaseBookingStopS!=null && leaseBookingStopS.getDropLocation()!=null && !leaseBookingStopS.getDropLocation().equals("") ){
			    			 		    innerDestination = GoogleMapsUtils.getLatLongBasedOnAddress(leaseBookingStopS.getDropLocation());
			    	 				}
			    	 			  } 
			    	 			catch (Exception e) {
			    	 				e.printStackTrace();
			    	 				innerDestination = new LatLng(28.5903481, 77.3383321);
			    	 			}
			    	 		    GDistanceMatrix distanceMatrixs = null;	
			    	 			if(innerDestination!=null && innserSource!=null){
			    	 			 try {
			    	 				distanceMatrixs = GoogleMapsUtils.getDistanceMatrix(innserSource, innerDestination);
			    	 			} catch (Exception e1) {			
			    	 				e1.printStackTrace();
			    	 			}
			    	 			}
			    	 			double approxDistances= 0;
			    	 			if(distanceMatrixs!=null){
			    	 				approxDistances=  distanceMatrixs.getDistanceValue()/1000;
			    	 			}
			    	 			
			    		 		 if( bookingLeaseId!=null && bookingLeaseId!=0){
			    		 			  LeaseBooking leaseBooking=new LeaseBooking(driverID, vehicleID,companyId,new Date(rideTimes.trim()), new Date(dropLocationReachTimes.trim()), innserSource.lat, innserSource.lng, fromLocation, innerDestination.lat, innerDestination.lng, leaseBookingStopS.getDropLocation(), approxDistance, journeyTime, rideFlag);
			    			 			leaseBooking.setUpdatedDateTime(new Date(updatedDate.trim()));
			    			 			leaseBooking.setDriverMobile(mobile);
			    				 		leaseBooking.setBookingLeaseId(bookingLeaseId);
			    				 		leaseBookingS=leaseBookingService.updateLeaseBookingAtResume(leaseBooking);
			    				 		}}
				 		 }catch(Exception er){}
			    	}
				 		
			    	dpointBooking= multiDropPointBookingDetailsService.bookRide(orgRegistrations);	
			    	if(leaseBookingS!=null && leaseBookingS.getBookingLeaseId()!=null){
			    	orgRegistrations.setBookingId(leaseBookingS.getBookingLeaseId());
			    	}
		 	}
		 	}catch(Exception er){er.printStackTrace();}
		 	if(startEndFlag!=null && startEndFlag.equals("end")){	
		 		try{
		 			if( bookingLeaseId!=null &&  bookingLeaseId!=0){
		 			LeaseBookingStop leaseBookingStop=new LeaseBookingStop(bookingLeaseId,new Date(dropLocationReachTimes.trim()),new Date(afterDropStartTimes.trim()), destination.lat, destination.lng, "",toLocation);
		 			leaseBookingStop.setTotalDistance(approxDistance);
		 			leaseBookingStop.setCreatedTime(new Date(createdDate.trim()));
		 			
		 				List<LeaseBookingStop> listOfLeaseBookingStop=	leaseBookingStopService.getLeaseBookingStopListByBookingLeaseId(leaseBookingStop);
		 			 	if(listOfLeaseBookingStop!=null && listOfLeaseBookingStop.size()>0 ){
		 				int size=listOfLeaseBookingStop.size();
		 				leaseBookingStopS=listOfLeaseBookingStop.get(size-1);
		 				}
		 			LatLng innserSource = null;
		 			
		 			try {
		 				
		 				if(leaseBookingStopS!=null && leaseBookingStopS.getDropLocation()!=null && !leaseBookingStopS.getDropLocation().equals("") ){
					 	 innserSource = GoogleMapsUtils.getLatLongBasedOnAddress(leaseBookingStopS.getDropLocation());}
		 			 } catch (Exception e) {
		 				e.printStackTrace();
		 				innserSource = new LatLng(28.4863626802915, 77.09126018029151);
		 			}	
		 			LatLng innerDestination = null;		
		 			try {
		 				if(toLocation!=null   && !toLocation.equals("") ){
				 		    innerDestination = GoogleMapsUtils.getLatLongBasedOnAddress(toLocation);
		 				}
		 			  } 
		 			catch (Exception e) {
		 				e.printStackTrace();
		 				innerDestination = new LatLng(28.5903481, 77.3383321);
		 			}
		 		    GDistanceMatrix distanceMatrixs = null;	
		 			if(innerDestination!=null && innserSource!=null){
		 			 try {
		 				distanceMatrixs = GoogleMapsUtils.getDistanceMatrix(innserSource, innerDestination);
		 			} catch (Exception e1) {			
		 				e1.printStackTrace();
		 			}
		 			}
		 			double approxDistances=0;
		 			if(distanceMatrixs!=null){
                  approxDistances=distanceMatrixs.getDistanceValue()/1000;
		 			}
		 		 
		 		LeaseBooking leaseBooking=new LeaseBooking(driverID, vehicleID,companyId,new Date(rideTimes.trim()), new Date(dropLocationReachTimes.trim()), innserSource.lat, innserSource.lng, fromLocation, innerDestination.lat, innerDestination.lng, toLocation, approxDistances, journeyTime, rideFlag);
		 		leaseBooking.setDriverMobile(mobile);
		 		leaseBooking.setJourneyEndDate(new Date(updatedDate.trim()));
		 		leaseBooking.setUpdatedDateTime(new Date(updatedDate.trim()));
		 		leaseBooking.setBookingLeaseId(bookingLeaseId);
		 		leaseBookingS=leaseBookingService.updateLeaseBookingAtEnd(leaseBooking);
		 		}
		 		}catch(Exception er){er.printStackTrace();}			 	
		 	}
		 	PusherUtil pusherUtil = new PusherUtil();			 
			if(orgRegistrations.getBooknow() ==1){
			pusherUtil.notifyOrgTruxs(allRegisteredDeviceList, orgRegistrations, vehicleLocationService);
			SMSTemplates.leaseBookLaterMessage(orgRegistrations);
			}
			if(orgRegistrations.getBooknow() == 0)
				if(driverDeviceVehicleMappings!=null && driverDeviceVehicleMappings.getDriverName()!=null){
				orgRegistrations.setDriverName(driverDeviceVehicleMappings.getDriverName());
				}
			//SMSTemplates.bookLaterMessage(orgRegistrations);
			SMSTemplates.leaseBookLaterMessage(orgRegistrations);
		  
			return leaseBookingS;			
		} catch (Exception e) {
			e.printStackTrace();
			return new LeaseBooking(
					TruxErrorCodes.BOOKING_ERROR.getCode(),
					TruxErrorCodes.BOOKING_ERROR.getDescription());
		}
		}catch(Exception er){er.printStackTrace(); return new LeaseBooking(
				TruxErrorCodes.BOOKING_ERROR.getCode(),
				TruxErrorCodes.BOOKING_ERROR.getDescription());}
	}


	public Integer getLeaseBookingId() {
		return leaseBookingId;
	}


	public void setLeaseBookingId(Integer leaseBookingId) {
		this.leaseBookingId = leaseBookingId;
	}


	public Integer getLeaseBookingStopId() {
		return leaseBookingStopId;
	}


	public void setLeaseBookingStopId(Integer leaseBookingStopId) {
		this.leaseBookingStopId = leaseBookingStopId;
	}
	
	
	public Double getLat(String locAddress){
		try{
		System.out.println(locAddress);
		String lat=	"Latitude:"; 
     	int indexlat=locAddress.indexOf("Latitude:");
		int indexlog=locAddress.indexOf("Longitude:");
		String sourceLat="0";
		if(indexlat!=-1){
		  sourceLat=locAddress.substring(indexlat+lat.length(),indexlog);
		}
	    return new Double(sourceLat.trim());
		}catch(Exception er){er.printStackTrace();}
		return new Double("0");
	}
	
	public Double getLong(String logAddress){
		try{
			
		System.out.println(logAddress);
		String log=	"Longitude:"; 
		int indexlog=logAddress.indexOf("Longitude:"); 
	    int addresss=logAddress.indexOf("Address:");
	    String sourceLog="0";
	    if(indexlog!=-1){
		  sourceLog=logAddress.substring(indexlog+log.length(),addresss);
	    }
	    return new Double(sourceLog.trim()); 
		}catch(Exception er){er.printStackTrace();}
		return new Double("0");
	}

}
