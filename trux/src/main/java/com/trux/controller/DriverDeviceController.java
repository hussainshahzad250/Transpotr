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
import com.trux.enumerations.TruxErrorCodes;
import com.trux.model.CustomerBookingDetails;
import com.trux.model.DeviceRegistration;
import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.DriverLoginHistory;
import com.trux.model.DriverRegistration;
import com.trux.model.GDistanceMatrix;
import com.trux.model.SubsidiaryClientOfOrg;
import com.trux.model.VehicleRegistration;
import com.trux.service.BookingService;
import com.trux.service.DriverLoginHistoryService;
import com.trux.service.RegistrationService;
import com.trux.service.SubsaidiaryOrgRegistrationService;
import com.trux.service.TruxStartUpService;
import com.trux.utils.Click2CallUtils;
import com.trux.utils.GoogleMapsUtils;

import config.CassandraConnector;

@Controller
@RequestMapping(value="/deviceDriverMapping")  
public class DriverDeviceController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private TruxStartUpService truxStartUpService;
	@Autowired
	DriverLoginHistoryService driverLoginHistoryService;
	
	
	@Autowired
	SubsaidiaryOrgRegistrationService  subsaidiaryOrgRegistrationService;
	
	@ResponseBody
	@RequestMapping(value="/deviceRegistration", method=RequestMethod.POST)
	private DeviceRegistration deviceRegistration(HttpServletRequest request, HttpServletResponse response, @RequestParam String deviceId){
		
		if(deviceId == null)
			return new DeviceRegistration(TruxErrorCodes.INVALID_DEVICE_ID.getCode(), TruxErrorCodes.INVALID_DEVICE_ID.getDescription());
		
		DeviceRegistration deviceRegistration = new DeviceRegistration(deviceId);
		deviceRegistration.setDeviceStatus("free");
		
		try {
			registrationService.saveOrUpdate(deviceRegistration);
			return deviceRegistration;
		} catch (Exception e) {
			return new DeviceRegistration(TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(), TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value="/validateDriver", method=RequestMethod.GET)
	private DriverDeviceVehicleMapping validateDriver(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String driverPhoneNumber,
			@RequestParam String deviceId,
			@RequestParam(required = false) String driver_apk_version){
		Boolean isValidDriver = false; 
		DriverRegistration driver=null;
		try{
			  driver=registrationService.getDriverDetail(driverPhoneNumber);
		}catch(Exception er){ er.printStackTrace();}
		try{
		if(driverPhoneNumber == null)
			return new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER_PHONENUMBER.getCode(), TruxErrorCodes.INVALID_DRIVER_PHONENUMBER.getDescription());
		
		if(deviceId == null)
			return new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DEVICE_ID.getCode(), TruxErrorCodes.INVALID_DEVICE_ID.getDescription());

		
		
		if(truxStartUpService != null && truxStartUpService.driverRegistrationMap != null 	&& truxStartUpService.driverRegistrationMap.containsKey(driverPhoneNumber)){
			
			DriverDeviceVehicleMapping driverDeviceVehicleMap = truxStartUpService.driverRegistrationMap.get(driverPhoneNumber.trim());
			
			if(driverDeviceVehicleMap != null){
				driverDeviceVehicleMap.setDeviceUUID(deviceId);
				try {
					driverDeviceVehicleMap.setIsValidDriver(true);
					driverDeviceVehicleMap.setLoginStatus(1);
					driverDeviceVehicleMap.setDriverStatus(0);
					driverDeviceVehicleMap.setLastLoginTime(new Date());
					driverDeviceVehicleMap.setDriver_apk_version(driver_apk_version);
					if(driverDeviceVehicleMap !=null && driverDeviceVehicleMap.getDstatus()==null || driverDeviceVehicleMap.getDstatus().equals("")){
						if(driver!=null){
							if(driver.getDstatus()!=null){
								try{
									driverDeviceVehicleMap.setDstatus(driver.getDstatus());
								}catch(Exception er){er.printStackTrace();}
							}
						}	
					}
					registrationService.saveOrUpdateDDVM(driverDeviceVehicleMap);
					truxStartUpService.driverRegistrationMap.remove(driverPhoneNumber);
					return driverDeviceVehicleMap;
				} catch (Exception e) {
					DriverDeviceVehicleMapping invalidDriver = new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER.getCode(), TruxErrorCodes.INVALID_DRIVER.getDescription());
					invalidDriver.setIsValidDriver(isValidDriver);
					return invalidDriver;
				}
			}else{
				DriverDeviceVehicleMapping invalidDriver = new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER.getCode(), TruxErrorCodes.INVALID_DRIVER.getDescription());
				invalidDriver.setIsValidDriver(isValidDriver);
				return invalidDriver;
			}
		}
		
		
		DriverDeviceVehicleMapping driverDeviceVehicleMapping = registrationService.driverValidation(driverPhoneNumber, deviceId);
		if(driverDeviceVehicleMapping==null){
			DriverDeviceVehicleMapping driverDeviceVehicleMappings = registrationService.getDriverDeviceDetail(driverPhoneNumber);
			if(driverDeviceVehicleMappings!=null && driverDeviceVehicleMappings.getDeviceUUID()==null){
				driverDeviceVehicleMappings.setIsValidDriver(true);
				driverDeviceVehicleMappings.setLoginStatus(1);
				driverDeviceVehicleMappings.setLastLoginTime(new Date());
				driverDeviceVehicleMappings.setDeviceUUID(deviceId);
				driverDeviceVehicleMappings.setDriverStatus(0);
				driverDeviceVehicleMappings.setDriver_apk_version(driver_apk_version);
				if(driverDeviceVehicleMappings !=null && driverDeviceVehicleMappings.getDstatus()==null || driverDeviceVehicleMappings.getDstatus().equals("")){
					if(driver!=null){
						if(driver.getDstatus()!=null){
							try{
							driverDeviceVehicleMappings.setDstatus(driver.getDstatus());
							}catch(Exception er){er.printStackTrace();}
						}
					}	
				}
				registrationService.saveOrUpdateDDVM(driverDeviceVehicleMappings);
		 
			 	return driverDeviceVehicleMappings;
			 	} else if(driverDeviceVehicleMappings!=null && driverDeviceVehicleMappings.getDeviceUUID().trim().equals("")){
					driverDeviceVehicleMappings.setIsValidDriver(true);
					driverDeviceVehicleMappings.setLoginStatus(1);
					driverDeviceVehicleMappings.setLastLoginTime(new Date());
					driverDeviceVehicleMappings.setDeviceUUID(deviceId);
					driverDeviceVehicleMappings.setDriverStatus(0);
					driverDeviceVehicleMappings.setDriver_apk_version(driver_apk_version);
					if(driverDeviceVehicleMappings !=null && driverDeviceVehicleMappings.getDstatus()==null || driverDeviceVehicleMappings.getDstatus().equals("")){
						if(driver!=null){
							if(driver.getDstatus()!=null){
								try{
								driverDeviceVehicleMappings.setDstatus(driver.getDstatus());
								}catch(Exception er){er.printStackTrace();}
							}
						}	
					}
					
					registrationService.saveOrUpdateDDVM(driverDeviceVehicleMappings);
			 
				 	return driverDeviceVehicleMappings;
			 	
			}else{
				DriverDeviceVehicleMapping invalidDriver = new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER.getCode(), TruxErrorCodes.INVALID_DRIVER.getDescription());
				invalidDriver.setIsValidDriver(isValidDriver);
				return invalidDriver;
			}
		}
		
		if(driverDeviceVehicleMapping != null && driverDeviceVehicleMapping.getDeviceUUID().equals(deviceId)){
			driverDeviceVehicleMapping.setIsValidDriver(true);
			driverDeviceVehicleMapping.setLoginStatus(1);
			driverDeviceVehicleMapping.setLastLoginTime(new Date());

			driverDeviceVehicleMapping.setDriverStatus(0);
			driverDeviceVehicleMapping.setDriver_apk_version(driver_apk_version);
			if(driverDeviceVehicleMapping !=null && driverDeviceVehicleMapping.getDstatus()==null || driverDeviceVehicleMapping.getDstatus().equals("")){
				if(driver!=null){
					if(driver.getDstatus()!=null){
						try{
							driverDeviceVehicleMapping.setDstatus(driver.getDstatus());
						}catch(Exception er){er.printStackTrace();}
					}
				}	
			}
			registrationService.saveOrUpdateDDVM(driverDeviceVehicleMapping);
			 
			return driverDeviceVehicleMapping;
		}else{
			DriverDeviceVehicleMapping invalidDriver = new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER.getCode(), TruxErrorCodes.INVALID_DRIVER.getDescription());
			invalidDriver.setIsValidDriver(isValidDriver);
			return invalidDriver;
		}}catch(Exception er){er.printStackTrace();
		DriverDeviceVehicleMapping invalidDriver = new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER.getCode(), TruxErrorCodes.INVALID_DRIVER.getDescription());
		invalidDriver.setIsValidDriver(isValidDriver);
		return invalidDriver;}
	}
	
	@ResponseBody
	@RequestMapping(value="/validateLeaseDriver", method=RequestMethod.GET)
	private DriverDeviceVehicleMapping validateLeaseDriver(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String driverPhoneNumber,
							@RequestParam String deviceId, 
							@RequestParam(required = false) String driver_apk_version,
							@RequestParam double driverlat,
							@RequestParam double driverlong,
							@RequestParam int driverLoginStatus){
		Boolean isValidDriver = false;
		HttpSession session=request.getSession();
		DriverRegistration driver=null;
		try{
	       driver=registrationService.getDriverDetail(driverPhoneNumber);
		}catch(Exception er){er.printStackTrace();}
		try{
		if(driverPhoneNumber == null)
			return new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER_PHONENUMBER.getCode(), TruxErrorCodes.INVALID_DRIVER_PHONENUMBER.getDescription());
		
		if(deviceId == null)
			return new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DEVICE_ID.getCode(), TruxErrorCodes.INVALID_DEVICE_ID.getDescription());

	 if(truxStartUpService != null && truxStartUpService.driverRegistrationMap != null && truxStartUpService.driverRegistrationMap.containsKey(driverPhoneNumber)){
			
			DriverDeviceVehicleMapping driverDeviceVehicleMap = truxStartUpService.driverRegistrationMap.get(driverPhoneNumber.trim());
			
			if(driverDeviceVehicleMap != null){
				driverDeviceVehicleMap.setDeviceUUID(deviceId);
				try {
					driverDeviceVehicleMap.setIsValidDriver(true);
					driverDeviceVehicleMap.setLoginStatus(1);
					driverDeviceVehicleMap.setDriverStatus(0);
					driverDeviceVehicleMap.setLastLoginTime(new Date());
					driverDeviceVehicleMap.setDriver_apk_version(driver_apk_version);
					if(driverDeviceVehicleMap !=null && driverDeviceVehicleMap.getDstatus()==null || driverDeviceVehicleMap.getDstatus().equals("")){
						if(driver!=null){
							if(driver.getDstatus()!=null){
								try{
									driverDeviceVehicleMap.setDstatus(driver.getDstatus());
								}catch(Exception er){er.printStackTrace();}
							}
						}	
					}
					registrationService.saveOrUpdateDDVM(driverDeviceVehicleMap);
					truxStartUpService.driverRegistrationMap.remove(driverPhoneNumber);
				 	driverDeviceVehicleMap.setErrorCode("200");
					driverDeviceVehicleMap.setErrorMessage("Success");
					return driverDeviceVehicleMap;
				} catch (Exception e) {
					DriverDeviceVehicleMapping invalidDriver = new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER.getCode(), TruxErrorCodes.INVALID_DRIVER.getDescription());
					invalidDriver.setIsValidDriver(isValidDriver);
					return invalidDriver;
				}
			}else{
				DriverDeviceVehicleMapping invalidDriver = new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER.getCode(), TruxErrorCodes.INVALID_DRIVER.getDescription());
				invalidDriver.setIsValidDriver(isValidDriver);
				return invalidDriver;
			}
		}
		
		
		DriverDeviceVehicleMapping driverDeviceVehicleMapping = registrationService.driverValidation(driverPhoneNumber, deviceId);
		if(driverDeviceVehicleMapping==null){
			try{
			DriverDeviceVehicleMapping driverDeviceVehicleMappings = registrationService.getDriverDeviceDetail(driverPhoneNumber);
			if(driverDeviceVehicleMappings!=null && driverDeviceVehicleMappings.getDeviceUUID()!=null && driverDeviceVehicleMappings.getDeviceUUID().trim().equals("")){
				try{driverDeviceVehicleMappings.setIsValidDriver(true);
				driverDeviceVehicleMappings.setLoginStatus(1);
				driverDeviceVehicleMappings.setLastLoginTime(new Date());
				driverDeviceVehicleMappings.setDeviceUUID(deviceId);
				driverDeviceVehicleMappings.setDriverStatus(0);
				driverDeviceVehicleMappings.setDriver_apk_version(driver_apk_version);
				if(driverDeviceVehicleMappings !=null && driverDeviceVehicleMappings.getDstatus()==null || driverDeviceVehicleMappings.getDstatus().equals("")){
					if(driver!=null){
						if(driver.getDstatus()!=null){
							try{
								driverDeviceVehicleMappings.setDstatus(driver.getDstatus());
							}catch(Exception er){er.printStackTrace();}
						}
					}	
				}
				registrationService.saveOrUpdateDDVM(driverDeviceVehicleMappings);
				DriverLoginHistory dto=new DriverLoginHistory();
				dto.setDatetime(new Date());
				dto.setDevice(deviceId);
				dto.setLoginLat(driverlat);
				dto.setLoginLong(driverlong);
				dto.setPunchIngStatus(1);
				dto.setDriverMobile(driverPhoneNumber);
				List<DriverLoginHistory> previousLogin=driverLoginHistoryService.getDriverLoginHistoryListByMobile(dto);
				DriverLoginHistory saveDto=null;
				if(previousLogin!=null){
					saveDto=previousLogin.get(0);
				}else{
				  saveDto=driverLoginHistoryService.saveDriverLoginHistory(dto);
				}
				if(saveDto!=null && saveDto.getLoginId()!=null){
				session.setAttribute("driverLoginID", saveDto.getLoginId());
				}
				 
			 	return driverDeviceVehicleMappings;
			}catch(Exception er){}
			}else if(driverDeviceVehicleMappings!=null && driverDeviceVehicleMappings.getDeviceUUID()==null){
				driverDeviceVehicleMappings.setIsValidDriver(true);
				driverDeviceVehicleMappings.setLoginStatus(1);
				driverDeviceVehicleMappings.setLastLoginTime(new Date());
				driverDeviceVehicleMappings.setDeviceUUID(deviceId);
				driverDeviceVehicleMappings.setDriverStatus(0);
				driverDeviceVehicleMappings.setDriver_apk_version(driver_apk_version);
				if(driverDeviceVehicleMappings !=null && driverDeviceVehicleMappings.getDstatus()==null || driverDeviceVehicleMappings.getDstatus().equals("")){
					if(driver!=null){
						if(driver.getDstatus()!=null){
							try{
								driverDeviceVehicleMappings.setDstatus(driver.getDstatus());
							}catch(Exception er){er.printStackTrace();}
						}
					}	
				}
				registrationService.saveOrUpdateDDVM(driverDeviceVehicleMappings);
				DriverLoginHistory dto=new DriverLoginHistory();
				dto.setDatetime(new Date());
				dto.setDevice(deviceId);
				dto.setLoginLat(driverlat);
				dto.setLoginLong(driverlong);
				dto.setPunchIngStatus(1);
				dto.setDriverMobile(driverPhoneNumber);
				
				List<DriverLoginHistory> previousLogin=driverLoginHistoryService.getDriverLoginHistoryListByMobile(dto);
				DriverLoginHistory saveDto=null;
				if(previousLogin!=null){
					saveDto=previousLogin.get(0);
				}else{ 
					saveDto=driverLoginHistoryService.saveDriverLoginHistory(dto);
				} 
				if(saveDto!=null && saveDto.getLoginId()!=null){
				session.setAttribute("driverLoginID", saveDto.getLoginId());
				}
				driverDeviceVehicleMappings.setErrorCode("200");
				driverDeviceVehicleMappings.setErrorMessage("Success");
			 	return driverDeviceVehicleMappings;
			}
			else{
				DriverDeviceVehicleMapping invalidDriver = new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER.getCode(), TruxErrorCodes.INVALID_DRIVER.getDescription());
				invalidDriver.setIsValidDriver(isValidDriver);
				return invalidDriver;
			}
			}catch(Exception er){er.printStackTrace();}
		}
		
		if(driverDeviceVehicleMapping != null && driverDeviceVehicleMapping.getDeviceUUID().equals(deviceId)){
 			try{
			driverDeviceVehicleMapping.setIsValidDriver(true);
			driverDeviceVehicleMapping.setLoginStatus(1);
			driverDeviceVehicleMapping.setLastLoginTime(new Date());

			driverDeviceVehicleMapping.setDriverStatus(0);
			driverDeviceVehicleMapping.setDriver_apk_version(driver_apk_version);
			if(driverDeviceVehicleMapping !=null && driverDeviceVehicleMapping.getDstatus()==null || driverDeviceVehicleMapping.getDstatus().equals("")){
				if(driver!=null){
					if(driver.getDstatus()!=null){
						try{
							driverDeviceVehicleMapping.setDstatus(driver.getDstatus());
						}catch(Exception er){er.printStackTrace();}
					}
				}	
			}
			registrationService.saveOrUpdateDDVM(driverDeviceVehicleMapping);
			DriverLoginHistory dto=new DriverLoginHistory();
			dto.setDatetime(new Date());
			dto.setDevice(deviceId);
			dto.setLoginLat(driverlat);
			dto.setLoginLong(driverlong);
			dto.setPunchIngStatus(1);
			dto.setDriverMobile(driverPhoneNumber);
			
			List<DriverLoginHistory> previousLogin= driverLoginHistoryService.getDriverLoginHistoryListByMobile(dto);
			DriverLoginHistory saveDto=null;
			if(previousLogin!=null && previousLogin.get(0).getPunchIngStatus()==1){
				saveDto=previousLogin.get(0);
			}else{ 
				saveDto=driverLoginHistoryService.saveDriverLoginHistory(dto);
			}
			if(saveDto!=null && saveDto.getLoginId()!=null){
			session.setAttribute("driverLoginID", saveDto.getLoginId());
		    }
			SubsidiaryClientOfOrg subClientDetails=null;
		
			
			if(driver!=null && driver.getSubOrgClient()!=null){
				Integer subClientId=Integer.parseInt(driver.getSubOrgClient());
				try{
					if(subClientId!=null){
			  subClientDetails=	subsaidiaryOrgRegistrationService.getListSubsidairyClientOrgByID(subClientId).get(0);
					}
				}catch(Exception er){er.printStackTrace();}
				
			}	
		  
			 String toLocation=""; 
			 String standLocation=""; 
			 if(subClientDetails!=null && subClientDetails.getAddress()!=null){
				 standLocation= subClientDetails.getAddress();
			 }
		if(standLocation!=null && !standLocation.equals("")){
			LatLng destination = null;		
			try {
	         int indexS=standLocation.indexOf("Address:\n");
			   
			   if(indexS==-1 || indexS==1){
				  try{ toLocation=standLocation;
				   destination = GoogleMapsUtils.getLatLongBasedOnAddress(standLocation);
				  }catch(Exception er){er.printStackTrace();}
			   }else{
				   try{
		     toLocation=standLocation.substring(indexS+9).replace("\n", " ");
			 destination = GoogleMapsUtils.getLatLongBasedOnAddress(toLocation);
			   }catch(Exception er){er.printStackTrace();}
			   }
				destination = new LatLng(subClientDetails.getClientLat(),subClientDetails.getClientLong());
				
			}
			catch (Exception e) {
				e.printStackTrace();
				destination = new LatLng(subClientDetails.getClientLat(),subClientDetails.getClientLong());
			}	
			
			
			if(subClientDetails!=null){
				if(subClientDetails.getClientLat()!=0 && subClientDetails.getClientLong()!=0)
			    destination = new LatLng(subClientDetails.getClientLat(),subClientDetails.getClientLong());
			 }
			LatLng source = new LatLng(driverlat, driverlong);
			 		
			GDistanceMatrix distanceMatrix = null;	
			if(destination!=null && source!=null){
			 try {
			 		 
				distanceMatrix = GoogleMapsUtils.getDistanceMatrix(source, destination);
			} catch (Exception e1) {			
				e1.printStackTrace();
			}
			} 
			double approxDistance =0; 
		  if(distanceMatrix!=null){
			  approxDistance =  distanceMatrix.getDistanceValue()/1000;
		    }
		
			 DriverLoginHistory dtos=new DriverLoginHistory();
			 dtos.setDatetime(new Date());
			 dtos.setDevice(deviceId);
			 dtos.setLoginLat(driverlat);
			 dtos.setLoginLong(driverlong);
			 dtos.setPunchIngStatus(1);
			 dtos.setDriverMobile(driverPhoneNumber);
				List<DriverLoginHistory> previousLogins=driverLoginHistoryService.getDriverLoginHistoryListByMobile(dtos);
				DriverLoginHistory saveDtos=null;
				if(previousLogins!=null){
					saveDtos=previousLogins.get(0);
				}
				if(saveDtos!=null && saveDtos.getPunchIngStatus()==1){
					driverDeviceVehicleMapping.setErrorCode("200");
					driverDeviceVehicleMapping.setErrorMessage("Success");
					return driverDeviceVehicleMapping;
				}else{
					  double radiusLocatios=0;
						 if (subClientDetails!=null&&  subClientDetails.getDriverLoginDistance()!=0){
							 radiusLocatios=subClientDetails.getDriverLoginDistance();
						  }else{
							  try{
				             radiusLocatios=CassandraConnector.getRadiusLocations();
							  }catch(Exception er){er.printStackTrace();}
				          }
					if(approxDistance>radiusLocatios && driverLoginStatus==1 ){
						DriverDeviceVehicleMapping invalidDriver = new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER_TO_LOGIN.getCode(), TruxErrorCodes.INVALID_DRIVER_TO_LOGIN.getDescription());
						invalidDriver.setIsValidDriver(isValidDriver);
						return invalidDriver;
					}else if(driverLoginStatus==0){ 
						driverDeviceVehicleMapping.setErrorCode("200");
						driverDeviceVehicleMapping.setErrorMessage("Success");
						return driverDeviceVehicleMapping;
					} }
 	      
 		
 	}
		driverDeviceVehicleMapping.setErrorCode("200");
		driverDeviceVehicleMapping.setErrorMessage("Success");
  return driverDeviceVehicleMapping;
		}catch(Exception er){er.printStackTrace();}
 			return driverDeviceVehicleMapping;
		}else{
			DriverDeviceVehicleMapping invalidDriver = new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER.getCode(), TruxErrorCodes.INVALID_DRIVER.getDescription());
			invalidDriver.setIsValidDriver(isValidDriver);
			return invalidDriver;
		}}catch(Exception er){er.printStackTrace();
		DriverDeviceVehicleMapping invalidDriver = new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER.getCode(), TruxErrorCodes.INVALID_DRIVER.getDescription());
		invalidDriver.setIsValidDriver(isValidDriver);
		return invalidDriver;}
	}
	 
	@ResponseBody
	@RequestMapping(value="/initiateCall", method=RequestMethod.GET)
	private String createCall(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer bookingId, @RequestParam(required = false) String type){
		try{
		CustomerBookingDetails customerBookingDetails = bookingService.getBookingDetailsById(bookingId);
		
		String customerNumber = "";
		
		if("1".equals(type))
			 customerNumber = customerBookingDetails.getConsigneePhoneNumber();
		else
			 customerNumber = customerBookingDetails.getCustmerPhonenumber();		
		String driverPhoneNumber = customerBookingDetails.getDriverPhonenumber();
		System.out.println(customerNumber+":"+driverPhoneNumber);		
		try {
			Click2CallUtils.initiateCall("0"+driverPhoneNumber, "0"+customerNumber);
			customerBookingDetails = bookingService.getBookingDetailsByDriverCall(bookingId);
			return "SUCCESS";
		} catch (Exception e) {
			
			e.printStackTrace();
			return "FAILURE";
		}
		
		}catch(Exception er){er.printStackTrace(); return "FAILURE";}
	}
 
	@ResponseBody
	@RequestMapping(value="/logoutDriver", method=RequestMethod.GET)
	private DriverDeviceVehicleMapping logoutDriver(HttpServletRequest request, HttpServletResponse response, @RequestParam String driverPhoneNumber,
							@RequestParam String deviceId){
		Boolean isValidDriver = false;
		try{
		if(driverPhoneNumber == null)
			return new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER_PHONENUMBER.getCode(), TruxErrorCodes.INVALID_DRIVER_PHONENUMBER.getDescription());
		
		if(deviceId == null)
			return new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DEVICE_ID.getCode(), TruxErrorCodes.INVALID_DEVICE_ID.getDescription());
		
		
		
		DriverDeviceVehicleMapping driverDeviceVehicleMapping = registrationService.driverValidation(driverPhoneNumber, deviceId);
		
		if(driverDeviceVehicleMapping != null){
			driverDeviceVehicleMapping.setIsValidDriver(true);
			driverDeviceVehicleMapping.setLoginStatus(0);
			driverDeviceVehicleMapping.setDriverStatus(0);
			driverDeviceVehicleMapping.setLastLogoutTime(new Date());
			registrationService.saveOrUpdateDDVM(driverDeviceVehicleMapping);
			return driverDeviceVehicleMapping;
		}else{
			DriverDeviceVehicleMapping invalidDriver = new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER.getCode(), TruxErrorCodes.INVALID_DRIVER.getDescription());
			invalidDriver.setIsValidDriver(isValidDriver);
			return invalidDriver;
		}}catch(Exception er){er.printStackTrace(); DriverDeviceVehicleMapping invalidDriver = new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER.getCode(), TruxErrorCodes.INVALID_DRIVER.getDescription());
		invalidDriver.setIsValidDriver(isValidDriver);
		return invalidDriver;}
	}
	
	
	@ResponseBody
	@RequestMapping(value="/logoutLeaseDriver", method=RequestMethod.GET)
	private DriverDeviceVehicleMapping logoutLeaseDriver(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String driverPhoneNumber,
		    @RequestParam String deviceId,
		    @RequestParam double driverlat,
			@RequestParam double driverlong 
		    ){
		Boolean isValidDriver = false;
		try{
		if(driverPhoneNumber == null)
			return new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER_PHONENUMBER.getCode(), TruxErrorCodes.INVALID_DRIVER_PHONENUMBER.getDescription());
		
		if(deviceId == null)
			return new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DEVICE_ID.getCode(), TruxErrorCodes.INVALID_DEVICE_ID.getDescription());
		
		
		
		DriverDeviceVehicleMapping driverDeviceVehicleMapping = registrationService.driverValidation(driverPhoneNumber, deviceId);
		
		if(driverDeviceVehicleMapping != null){
			driverDeviceVehicleMapping.setIsValidDriver(true);
			driverDeviceVehicleMapping.setLoginStatus(0);
			driverDeviceVehicleMapping.setDriverStatus(0);
			driverDeviceVehicleMapping.setLastLogoutTime(new Date());
			
			registrationService.saveOrUpdateDDVM(driverDeviceVehicleMapping);
			DriverLoginHistory dto=new DriverLoginHistory();
			dto.setDatetime(new Date());
			dto.setDevice(deviceId);
			dto.setLoginLat(driverlat);
			dto.setLoginLong(driverlong);
			dto.setDriverMobile(driverPhoneNumber);
			dto.setPunchIngStatus(0);
			try{
			List<DriverLoginHistory> previousLogin= driverLoginHistoryService.getDriverLoginHistoryListByMobile(dto);
			DriverLoginHistory saveDto=null;
			if(previousLogin!=null && previousLogin.size()>1){
			if(previousLogin.get(1).getPunchIngStatus()==0){
				saveDto=previousLogin.get(1);
				dto.setLoginId(saveDto.getLoginId());
				driverLoginHistoryService.updateDriverLoginHistory(dto);
			}else{ 
				saveDto=driverLoginHistoryService.saveDriverLoginHistory(dto);
			}			
			}else{ 
				saveDto=driverLoginHistoryService.saveDriverLoginHistory(dto);
			}}catch(Exception er){er.printStackTrace();}
		  
			return driverDeviceVehicleMapping;
		}else{
			DriverDeviceVehicleMapping invalidDriver = new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER.getCode(), TruxErrorCodes.INVALID_DRIVER.getDescription());
			invalidDriver.setIsValidDriver(isValidDriver);
			return invalidDriver;
		}}catch(Exception er){er.printStackTrace(); DriverDeviceVehicleMapping invalidDriver = new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER.getCode(), TruxErrorCodes.INVALID_DRIVER.getDescription());
		invalidDriver.setIsValidDriver(isValidDriver);
		return invalidDriver;}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/driverDeviceVehicleMapping", method=RequestMethod.POST)
	private DriverDeviceVehicleMapping driverDeviceVehicleMapping(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer driverId,
			@RequestParam Integer deviceId, @RequestParam Integer vehicleId){
		try{
		if(driverId == null)
			return new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER_ID.getCode(), TruxErrorCodes.INVALID_DRIVER_ID.getDescription());
		
		if(deviceId == null)
			return new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DEVICE_ID.getCode(), TruxErrorCodes.INVALID_DEVICE_ID.getDescription());
		
		if(vehicleId == null)
			return new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_VEHICLE_ID.getCode(), TruxErrorCodes.INVALID_VEHICLE_ID.getDescription());
		
	//	if(driverNumber == null)
	//		return new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER_PHONENUMBER.getCode(), TruxErrorCodes.INVALID_DRIVER_PHONENUMBER.getDescription());
		
	//	if(vehicleType == null)
	//		return new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_VEHICLE_TYPE.getCode(), TruxErrorCodes.INVALID_VEHICLE_TYPE.getDescription());
		
	//	if(vehicleNumber ==  null)
	//		return new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_VEHICLE_NUMBER.getCode(), TruxErrorCodes.INVALID_VEHICLE_NUMBER.getDescription());
		
		String driverNumber = null;
		String devieUUID = null;
		String vehicleNumber = null;
		String vehicleType = null;
		
			try {
			
			
			DriverRegistration driverRegistration = registrationService.getDriverDetailsById(driverId);
			if(driverRegistration != null){
				driverRegistration.setDriverStatus("mapped");
				registrationService.saveOrUpdate(driverRegistration);
				driverNumber = driverRegistration.getPhoneNumber();
			}
			
			DeviceRegistration deviceRegistration = registrationService.getDeviceDetailsById(deviceId);
			if(deviceRegistration != null){
				deviceRegistration.setDeviceStatus("mapped");
				registrationService.saveOrUpdate(deviceRegistration);
				devieUUID = deviceRegistration.getDeviceId();
			}
			
			VehicleRegistration vehicleRegistration = registrationService.getVehicleDetailsById(vehicleId);
			if(vehicleRegistration != null){
				vehicleRegistration.setVehicleStatus("mapped");
				registrationService.saveOrUpdate(vehicleRegistration);
				vehicleNumber = vehicleRegistration.getVehicleNumber();
				vehicleType = vehicleRegistration.getVehicleType();
			}
			
			DriverDeviceVehicleMapping driverDeviceVehicleMapping = new DriverDeviceVehicleMapping(driverId, vehicleId, deviceId, driverNumber, 
					devieUUID, vehicleNumber, vehicleType);
			registrationService.saveOrUpdateDDVM(driverDeviceVehicleMapping);


			
			return driverDeviceVehicleMapping;
		} catch (Exception e) {
			e.printStackTrace();
			return new DriverDeviceVehicleMapping(TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(), TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());
		}}catch(Exception er){er.printStackTrace();return new DriverDeviceVehicleMapping(TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(), TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());}
	}
	
	
	
	
	

}
