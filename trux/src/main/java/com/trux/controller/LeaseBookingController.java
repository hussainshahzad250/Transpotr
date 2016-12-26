package com.trux.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
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
import com.trux.model.Cities;
import com.trux.model.ClientMandate;
import com.trux.model.ClientMandateDetail;
import com.trux.model.Countries;
import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.LeaseBooking;
import com.trux.model.LeaseBookingHandler;
import com.trux.model.LeaseBookingStop;
import com.trux.model.OrganizationMasterRegistration;
import com.trux.model.States;
import com.trux.model.UserDetail;
import com.trux.service.ClientMandateService;
import com.trux.service.DriverAttandanceService;
import com.trux.service.LeaseBookingService;
import com.trux.service.LeaseBookingStopService;
import com.trux.service.LevelOfOrganizationService;
import com.trux.service.ModuleService;
import com.trux.service.OrgBookingDAOService;
import com.trux.service.OrganizationMasterService;
import com.trux.service.OrganizationalUserService;
import com.trux.service.RegistrationService;
import com.trux.service.SubsaidiaryOrgRegistrationService;
import com.trux.service.SubsidiaryClientUserService;
import com.trux.service.ZonesService;
import com.trux.utils.DateFormaterUtils;
import com.trux.utils.GoogleMapsUtils;

 
@Controller
@MultipartConfig
@RequestMapping("/subclient")
public class LeaseBookingController {

	@Autowired
	private ModuleService moduleService;
	@Autowired
	private ZonesService zonesService;
	@Autowired
	SubsaidiaryOrgRegistrationService subsaidiaryOrgRegistrationService;
	@Autowired
	private LevelOfOrganizationService LevelOfOrganizationService;
	@Autowired
	private OrganizationalUserService OrganizationalUserService;
	@Autowired
	private OrgBookingDAOService orgBookingDAOService;
	@Autowired
	SubsidiaryClientUserService  subsidiaryClientUserService;
	@Autowired
	private OrganizationMasterService organizationMasterService;
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	DriverAttandanceService driverAttandanceService;
	

	@Autowired
	private LeaseBookingService  leaseBookingService;
	@Autowired
	private LeaseBookingStopService leaseBookingStopService;
	
	@Autowired
    private	ClientMandateService clientMandateService;

	public List<Cities> allCities = null;
	public List<States> allStates = null;
	public List<Countries> allCountries = null;

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/leasedbooking", method = RequestMethod.GET)
	public ModelAndView leasedBooking(HttpServletRequest request,HttpServletResponse response,	@ModelAttribute(value = "LGHandler") LeaseBookingHandler LGHandler,
			BindingResult result) {
		try {
			HttpSession session = request.getSession();
			ModelMap mp=new ModelMap();

		    UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
		     Integer userId=0;
		     if(userDetail!=null){
			   userId=userDetail.getId();
		     } 
	  List<OrganizationMasterRegistration>  list=subsidiaryClientUserService.getOrgClient(userId);
		 //	List<OrganizationMasterRegistration> list = organizationMasterService.getOrganizationMasterRegistration();
			request.setAttribute("orgList", list);
			if (session.getAttribute("orgList") == null) {
			 list=subsidiaryClientUserService.getOrgClient(userId);// organizationMasterService.getOrganizationMasterRegistration();
				mp.addAttribute("client", list);
				 
			}
			if (session.getAttribute("orgList") != null) {
				list = (List<OrganizationMasterRegistration>) session.getAttribute("orgList");
				session.setAttribute("orgList", list);
				mp.addAttribute("client", list);
			}
			mp.addAttribute("client", list);
			 
			return new ModelAndView("backEndLeasedBooking", mp);

		} catch (Exception er) {
			er.printStackTrace();
		}
		return new ModelAndView("backEndLeasedBooking");
	}
 @ResponseBody
 @RequestMapping(value = "/searchLeasedBookingAtNow", method = RequestMethod.GET)
	public  List<LeaseBooking> searchLeasedBookingAtNow(HttpServletRequest request,HttpServletResponse response,	@RequestParam  String orgName,@RequestParam  String subOrgClient,@RequestParam  String driver,@RequestParam  String bookingDate){
	 			LeaseBookingHandler LGHandler =new LeaseBookingHandler();
	 			LGHandler.setBookingDate(bookingDate);
	 			String[] driverId=driver.split(",");
	 			LGHandler.setDriver(driverId[0]);
	 			LGHandler.setOrgName(orgName);
	 			LGHandler.setSubOrgClient(subOrgClient); 
 	     List<LeaseBooking> leaseBookingS=new ArrayList<LeaseBooking>();
	 	try{ 
	 	  leaseBookingS=  leaseBookingService.getLeaseBookingListByBookingDate(LGHandler);
	 	  if(leaseBookingS!=null && leaseBookingS.size()>0){
	 	 return leaseBookingS;
	 	  }else{
	 		   leaseBookingS=new ArrayList<LeaseBooking>();
			     LeaseBooking lg=new LeaseBooking();
			    lg.setErrorCode("400");
			    lg.setErrorMessage("Lease booking does not exist");
			     leaseBookingS.add(lg);
			   return leaseBookingS;  
	 	  }
		   }catch(Exception er){
			   er.printStackTrace();
			     leaseBookingS=new ArrayList<LeaseBooking>();
			     LeaseBooking lg=new LeaseBooking();
			    lg.setErrorCode("400");
			    lg.setErrorMessage("Lease booking does not exist");
			     leaseBookingS.add(lg);
			   return leaseBookingS;
		} 
	
	} 
	
 
 @SuppressWarnings("deprecation")
@ResponseBody
 @RequestMapping(value = "/leasedBookingAtNow", method = RequestMethod.POST)
	public  LeaseBooking leasedBookingAtNow(HttpServletRequest request,HttpServletResponse response,
			@RequestParam  Integer orgName,
			@RequestParam  Integer subOrgClient,
			@RequestParam  String driver, 
			@RequestParam  String bookingDate,
			@RequestParam(required=false)  String noofboxes,
			@RequestParam  String tripStop,
			@RequestParam String tripExpType,
			@RequestParam String tripAmount,
			@RequestParam(required=false) String tripStartKm,
			@RequestParam(required=false) String tripEndKm){
	 		
	 String[] tripExpTypeArray=tripExpType.split(",");
	 String[] tripAmountArray=tripAmount.split(",");
	 String[] tripStopArray=tripStop.split(",");
	 String[] driverVehicleDetailsArray=driver.split(",");
	 LeaseBooking dto=new LeaseBooking();
	 if(driverVehicleDetailsArray!=null && driverVehicleDetailsArray.length>0){
		 for(int d=0;d<driverVehicleDetailsArray.length;d++){
			 if(d==0){
				 Integer driverId=new Integer(driverVehicleDetailsArray[d]);
				 dto.setDriverId(driverId);
			 }
			 if(d==1){
				 Integer vehicleId=new Integer(driverVehicleDetailsArray[d]);
				 dto.setVehicleId(vehicleId);
			 }
			 if(d==2){
			  dto.setDriverName(driverVehicleDetailsArray[d]);
			 }
			 if(d==3){
				 dto.setDriverMobile(driverVehicleDetailsArray[d]);
			 }
		 }
		
	 }
	 if(orgName!=0){
	 dto.setCompanyId(subOrgClient);
		 dto.setSubClientId(subOrgClient);
	 }
	 if(subOrgClient!=0){
//		 dto.setSubClientId(null);
		 dto.setCompanyId(subOrgClient);
		 dto.setSubClientId(subOrgClient);
	  }
	 
	 for(int i=0;i<tripExpTypeArray.length;i++){
	 		    String d=tripExpTypeArray[i];
					if(d.equals("Toll Tax")){
					dto.setDriverTollTax(new Double(tripAmountArray[i]));	
					}
					if(d.equals("Bilty")){
						dto.setDriverBilty(new Double(tripAmountArray[i]));
					}
					if(d.equals("Parking")){
						dto.setDriverParkingAmount(new Double(tripAmountArray[i]));
				}
					if(d.equals("Chalan")){
						dto.setDriverChallan(new Double(tripAmountArray[i]));
					}
					if(d.equals("Other")){
						dto.setDriverOtherAmount(new Double(tripAmountArray[i]));
					}
					if(d.equals("OtherDescription")){
						dto.setDriverOtherReason(tripAmountArray[i]);
					}
				}
				 if(tripStopArray!=null && tripStopArray.length>0){
	 	         dto.setFromJrLocation(tripStopArray[0]);
	 	             LatLng destination = null;		
	 			 	  try{ 
	 				   destination = GoogleMapsUtils.getLatLongBasedOnAddress(tripStopArray[0]);
	 				   }catch(Exception er){er.printStackTrace();}
	 			 	  if(destination!=null){
	 			 		  dto.setFromJrLat(destination.lat);
	 			 		  dto.setFromJrLong(destination.lng);
	 			 	  }
				 }
		 dto.setJourneyStartDate(new Date(bookingDate));
 	     LeaseBooking leaseBookingS=null;
	 	try{ 
	 		String createdDate="";
	 		 try{  
				  createdDate= DateFormaterUtils.convertGMTToISTWithDatesWithSlash(new Date().toString()).replace(".0", "");
			        }catch(Exception er){} 
	 		dto.setCreatedDateTime(new Date(createdDate.trim()));
	 		
	 	  leaseBookingS=  leaseBookingService.saveLeaseBooking(dto);
	 	  if(leaseBookingS!=null){
	 		 try{ 
	 			 if(tripStopArray!=null && tripStopArray.length>0){
	 				 List<LeaseBookingStop> leaseBookingStopList=new ArrayList<LeaseBookingStop>();
	 				 for(int i=0;i<tripStopArray.length;i++){
	 					LeaseBookingStop leaseBookingStop=new LeaseBookingStop();
	 					          LatLng destination = null;		
	 			 			 	  try{ 
	 			 				   destination = GoogleMapsUtils.getLatLongBasedOnAddress(tripStopArray[i]);
	 			 				   }catch(Exception er){er.printStackTrace();}
	 			 			 	  if(destination!=null){
	 			 			 		leaseBookingStop.setStopLat(destination.lat);
	 			 			 		leaseBookingStop.setStopLong(destination.lng);
	 			 			 	  }
	 						 
	 					leaseBookingStop.setBookingLeaseId(leaseBookingS.getBookingLeaseId());
	 					leaseBookingStop.setDropLocation(tripStopArray[i]);
	 					leaseBookingStopList.add(leaseBookingStop);
	 				 }
	 			  	if(leaseBookingStopList!=null && leaseBookingStopList.size()>0){
	 			  	   leaseBookingStopList=leaseBookingStopService.saveLeaseBookingStopByList(leaseBookingStopList);
		 			 	}
	 			 }     	
			      }catch(Exception er){}
		 		 
	 	 return leaseBookingS;
	  }else{
	 		 leaseBookingS=new LeaseBooking();
	 		 leaseBookingS.setErrorCode("400");
	 		 leaseBookingS.setErrorMessage("booking does not processed");
			 return leaseBookingS;  
	 	  }
		   }catch(Exception er){
			   er.printStackTrace();
			leaseBookingS=new LeaseBooking();
		 	leaseBookingS.setErrorCode("400");
		 	leaseBookingS.setErrorMessage("booking does not processed");
			return leaseBookingS;  
		} 
	
	} 
 
 
 @ResponseBody
	@RequestMapping(value = "/getDriverDetailsBySubClientId", method = RequestMethod.POST)
	private String getDriverDetails(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer bySubClient) {

	//	  List<DriverRegistration> list= registrationService.getRegisteredDriverList(bySubClient);
		 List<DriverDeviceVehicleMapping> list= subsidiaryClientUserService.getDriverModileVehicle(bySubClient);
			
		System.out.println("organizationId:- " + bySubClient);
		StringBuilder driverOptionsList = new StringBuilder();
		driverOptionsList.append("<option value=\"\">--Select Driver--</option>");
	if (list != null && !list.isEmpty()) {
			for (DriverDeviceVehicleMapping dr : list) {
				String vehicleNumber="N/A";
			if(	dr.getVehicleNumber()!=null){
				vehicleNumber=dr.getVehicleNumber();
			}else{
				vehicleNumber="N/A";
			}
				driverOptionsList.append("<option value=\"" + dr.getDriverId()+ "," + dr.getVehicleId()+ "," + dr.getDriverName()+"," + dr.getDriverPhoneNumber()+"\">" + dr.getDriverName() +" " +dr.getDriverPhoneNumber() +" "+vehicleNumber+"</option>");
		 }
		}
		return driverOptionsList.toString();
	}
	
 @ResponseBody
	@RequestMapping(value = "/getClientMandateType", method = RequestMethod.POST)
	public ClientMandate  getClientMandateType(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer clientId,@RequestParam Integer subClientId) {
	 List<ClientMandate>  mandateList= clientMandateService.getMandateByClientAndSubClient(clientId, subClientId);
	if(mandateList!=null){
	 return mandateList.get(0);
	}else{
		 return new ClientMandate();
	}
 }
 
 @ResponseBody
	@RequestMapping(value = "/getClientMandateDetailType", method = RequestMethod.POST)
	public ClientMandateDetail  getClientMandateDetailType(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer clientId,@RequestParam Integer subClientId) {
	 List<ClientMandateDetail>  mandateList= clientMandateService.getMandateDetailByClientAndSubClient(clientId, subClientId);
	if(mandateList!=null){
	 return mandateList.get(0);
	}else{
		 return new ClientMandateDetail();
	}
 }
 
}
