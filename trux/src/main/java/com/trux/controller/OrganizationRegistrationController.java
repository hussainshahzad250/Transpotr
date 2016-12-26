package com.trux.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.maps.model.LatLng;
import com.trux.enumerations.TruxErrorCodes;
import com.trux.model.Cities;
import com.trux.model.ClientMandateRequest;
import com.trux.model.ClientVehicleDeployment;
import com.trux.model.ControllerDAOTracker;
import com.trux.model.Countries;
import com.trux.model.LevelOfOrganization;
import com.trux.model.OrganizationBookingRegistration;
import com.trux.model.OrganizationMasterRegistration;
import com.trux.model.OrganizationalClientDto;
import com.trux.model.OrganizationalUser;
import com.trux.model.RestResponce;
import com.trux.model.States;
import com.trux.model.SubsidiaryClientOfOrg;
import com.trux.model.UploadDocumentModel;
import com.trux.model.UserDetail;
import com.trux.model.Zones;
import com.trux.service.LevelOfOrganizationService;
import com.trux.service.ModuleService;
import com.trux.service.OrgBookingDAOService;
import com.trux.service.OrganizationMasterService;
import com.trux.service.OrganizationalUserService;
import com.trux.service.SubsaidiaryOrgRegistrationService;
import com.trux.service.SubsidiaryClientUserService;
import com.trux.service.ZonesService;
import com.trux.utils.AWSS3Uploader;
import com.trux.utils.Click2CallUtils;
import com.trux.utils.GoogleMapsUtils;
import com.trux.utils.TruxUtils;
@Controller
@RequestMapping(value="/app")
public class OrganizationRegistrationController {

	public  List<Cities> allCities = null;
	public  List<States> allStates = null;
	public  List<Countries> allCountries = null;
	
	@Autowired
	private OrganizationMasterService organizationMasterService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private ZonesService zonesService;
	@Autowired
	SubsaidiaryOrgRegistrationService  subsaidiaryOrgRegistrationService;
	@Autowired
	private LevelOfOrganizationService LevelOfOrganizationService;
	@Autowired
	private OrganizationalUserService OrganizationalUserService;
	@Autowired
	private OrgBookingDAOService orgBookingDAOService;
	@Autowired
	SubsidiaryClientUserService  subsidiaryClientUserService;

	@RequestMapping(value = "/orgRegistration", method = RequestMethod.GET)
	public ModelAndView consumerReports(HttpServletRequest request,	HttpServletResponse response) {
		try{
		    allCountries=null;
			allCountries=moduleService.getAllCountriesList();
		    HttpSession session=request.getSession();
			if(allCountries!=null){			
			session.setAttribute("countriesList", allCountries);
			}
		 return new ModelAndView("backEndOrgRegistration"); 
		}catch(Exception er){er.printStackTrace();
		return new ModelAndView("backEndOrgRegistration");	
		}
		
		//return new ModelAndView("backEndOrgRegistration");	 
	}
	
	
	@RequestMapping(value = "/getCountry", method = RequestMethod.GET)
	public List<Countries> getCountry(HttpServletRequest request,	HttpServletResponse response) {
		try{
		    allCountries=null;
			allCountries=moduleService.getAllCountriesList();
		    HttpSession session=request.getSession();
			if(allCountries!=null){			
			session.setAttribute("countriesList", allCountries);
			}
		 return   allCountries;
		}catch(Exception er){er.printStackTrace();
		return allCountries;	
		}
		
		//return new ModelAndView("backEndOrgRegistration");	 
	}
	
	@ResponseBody
	@RequestMapping(value = "/orgRegistration", method = RequestMethod.POST)
	private ModelAndView orgRegistration(MultipartHttpServletRequest request,
			HttpServletResponse response,@RequestParam("copyOfAgreement") MultipartFile copyOfAgreement,@ModelAttribute("orgReg") OrganizationalClientDto orgReg,BindingResult result) {
	 try{	
			  UploadDocumentModel documentModelOrgContract = new UploadDocumentModel();
		      if (!copyOfAgreement.isEmpty()) {	
		    	 try {
		            	String name = copyOfAgreement.getOriginalFilename();
		            	byte[] bytes = copyOfAgreement.getBytes();
		            	if(bytes.length>0){
		            	documentModelOrgContract=AWSS3Uploader.uploadCompanyFile(name,copyOfAgreement.getContentType(),copyOfAgreement.getSize(), copyOfAgreement.getInputStream(), documentModelOrgContract);
		            	}       
		                documentModelOrgContract.setUploadStatus("SUCCESS");
		             } catch (Exception e) {
		            	 documentModelOrgContract.setUploadStatus("Failed");
		            	 documentModelOrgContract.setErrorMessage(e.getMessage());
		            }
		     }
		   HttpSession session=request.getSession();
		   UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");	 
		  OrganizationMasterRegistration dto=new OrganizationMasterRegistration(orgReg.getClientCode(), orgReg.getOrgName(), orgReg.getAddress(), orgReg.getCityId(), documentModelOrgContract.getUploadURL(), orgReg.getIsActive());
		  if(userDetail!=null){
			  dto.setCreatedBy(userDetail.getId());
			 }else{
				 dto.setCreatedBy(0);
			 }
		  dto.setIsActive(1);		 
		  OrganizationMasterRegistration reg= organizationMasterService.registerOrg(dto);
		 request.setAttribute("reg", reg);
		 return new ModelAndView("backEndOrgRegistration");
		   } catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("backEndOrgRegistration");
		   }
					
	}
	
    @ResponseBody
	@RequestMapping(value="/registerOrganization", method=RequestMethod.POST)
	private OrganizationMasterRegistration registration(HttpServletRequest request,HttpServletResponse response, @RequestParam String orgName,@RequestParam String contactPerson, @RequestParam String address,@RequestParam String email, @RequestParam String phonenumber, @RequestParam String usersName, @RequestParam String passwords, @RequestParam(required=false) String parentOrgId){
		try{
			 allCountries=null;
		     allCountries=moduleService.getAllCountriesList();
			 HttpSession session=request.getSession();
				if(allCountries!=null){			
				session.setAttribute("countriesList", allCountries);
				}
			 OrganizationMasterRegistration dto=new OrganizationMasterRegistration();
			// OrganizationMasterRegistration  reg= organizationMasterService.registerOrg(dto);
			 return dto;
		}catch(Exception er){er.printStackTrace();return new OrganizationMasterRegistration(TruxErrorCodes.UNHANDLED_ERROR.getCode(), TruxErrorCodes.UNHANDLED_ERROR.getDescription());}
	}
    
    
    
    
    @ResponseBody
	@RequestMapping(value="/subsidiaryOrgModule", method=RequestMethod.GET)
	private ModelAndView subsidiaryOrgModule(HttpServletRequest request,HttpServletResponse response){
		try{
		    allCountries=null;
			allCountries=moduleService.getAllCountriesList();
		    HttpSession session=request.getSession();
			if(allCountries!=null){			
			session.setAttribute("countriesList", allCountries);
			}
			 UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
		     Integer userId=0;
		     if(userDetail!=null){
			   userId=userDetail.getId();
		     } 
	 // List<OrganizationMasterRegistration>  list=subsidiaryClientUserService.getOrgClient(userId);
		 
			 List<OrganizationMasterRegistration>  list= organizationMasterService.getOrganizationMasterRegistration();
				if(list!=null && list.size()>0){
				 session.setAttribute("orgList", list);
				}				
				List<Zones> zoneList=zonesService.getAllZones();
				if(zoneList!=null && zoneList.size()>0){
					 session.setAttribute("zoneList", zoneList);
				}
			 return new ModelAndView("subsidiaryRegistration");//reg;
		}catch(Exception er){er.printStackTrace();
		return new ModelAndView("subsidiaryRegistration");	
		}
 }
    
    
    
    @ResponseBody
	@RequestMapping(value="/zoneModule", method=RequestMethod.GET)
	private ModelAndView zoneModule(HttpServletRequest request,HttpServletResponse response){
		try{
		 	 return new ModelAndView("backEndZoneRegistration");
		}catch(Exception er){
			er.printStackTrace();
			return new ModelAndView("backEndZoneRegistration");
 	} }
    
    @ResponseBody
	@RequestMapping(value="/zoneRegistration", method=RequestMethod.POST)
	private ModelAndView zoneRegistration(HttpServletRequest request,HttpServletResponse response,@RequestParam String zoneName,@RequestParam Integer countryId){
		try{
			Zones dto=new Zones(zoneName, countryId);
			Zones saveBack=	zonesService.saveZone(dto);
			request.setAttribute("saveBack", saveBack);
	        return new ModelAndView("backEndZoneRegistration");
		}catch(Exception er){
			er.printStackTrace();
			return new ModelAndView("backEndZoneRegistration");
 	} }
    
    @ResponseBody
   	@RequestMapping(value="/subOrgRegistration", method=RequestMethod.POST)
   	private ModelAndView subOrgRegistration(HttpServletRequest request,HttpServletResponse response,
   			@RequestParam Integer idClientMaster,
   			@RequestParam Integer idClientLevelMaster,
   			@RequestParam String subName,
   			@RequestParam String address,
   			@RequestParam Integer countryId, 
   			@RequestParam Integer zoneId,
   			@RequestParam Integer stateId, 
   			@RequestParam Integer cityId,
   			@RequestParam Integer hubId,
   			@RequestParam (required=false) String  clientLat,
   			@RequestParam (required=false) String clientLong ){
    	 
   		try{
   		   HttpSession session=request.getSession();
   		 UserDetail  userDetail=null;
   		   if(session.getAttribute("userDetail")!=null){
 		      userDetail=(UserDetail)session.getAttribute("userDetail");
   		   }
 		 	LatLng source = null;
 			String fromLocation=""; 
 			try {
 				if(clientLat!=null && clientLong!=null){
 					Double clientLats=new Double(clientLat) ;
 					Double clientLongs=new Double(clientLong) ;
 					source = new LatLng(clientLats, clientLongs);
 				}else{
 				 int indexS=address.indexOf("Address:\n");
 				  if(indexS==-1 || indexS==1){
 						  fromLocation=address;
 						  source = GoogleMapsUtils.getLatLongBasedOnAddress(address);
 					  }else{
 				fromLocation=address.substring(indexS+9).replace("\n", " ");
 		    	source = GoogleMapsUtils.getLatLongBasedOnAddress(fromLocation);
 					  }
 				}
 			} catch (Exception e) {
 				e.printStackTrace();
 				source = new LatLng(28.4863626802915, 77.09126018029151);
 			}	 
 			try{
   			SubsidiaryClientOfOrg dto=new SubsidiaryClientOfOrg(idClientMaster, idClientLevelMaster, subName, address, countryId, zoneId, stateId,cityId, hubId, 1, userDetail.getId());//			idClientMaster,idClientLevelMaster,subName,address, countryId, zoneId, stateId, cityId, hubId,isActive
   			dto.setClientLat(source.lat);
   			dto.setClientLong(source.lng);
   			SubsidiaryClientOfOrg saveBack=	subsaidiaryOrgRegistrationService.saveSubsidairyClientOrg(dto);
   		 	request.setAttribute("subOrgSaveBack", saveBack);   		 	
   			return new ModelAndView("subsidiaryRegistration");
 			}catch(Exception er){
 				er.printStackTrace();
 				SubsidiaryClientOfOrg saveBack=new SubsidiaryClientOfOrg();
 				saveBack.setIdClientSubMaster(0);
 				request.setAttribute("subOrgSaveBack", saveBack);   		 	
 	   			return new ModelAndView("subsidiaryRegistration");
 			}
   		}catch(Exception er){
   			er.printStackTrace();
   			return new ModelAndView("subsidiaryRegistration");
     	}
       
       }
    
    @ResponseBody
 	@RequestMapping(value="/orgLevelRegistration", method=RequestMethod.POST)
 	private String orgLevelRegistration(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer clientMasterId, @RequestParam String levelTitle,@RequestParam Integer precedence){
 		try{
 			LevelOfOrganization dto=new LevelOfOrganization(clientMasterId, levelTitle, precedence);
 			LevelOfOrganization saveBack=LevelOfOrganizationService.saveLevelOfOrganization(dto);
 			return "Organization Level Saved Successfully as id "+ saveBack.getClientLevelMasterId();
 		}catch(Exception er){
 			er.printStackTrace();
 			return "Your request is not procced do you to some exception !";
 	}
     
     }
    
    
    @ResponseBody
	@RequestMapping(value = "/getSubOrgLevelList", method = RequestMethod.POST)
	private String getSubOrgLevelList(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer clientMasterId) {
		 
		List<LevelOfOrganization>  dtoList=LevelOfOrganizationService.getLevelOfClientMaster(clientMasterId);
		StringBuilder levelOptionsList = new StringBuilder();
 		levelOptionsList.append("<option value=\"\">--Select Level--</option>");
	if (dtoList != null && !dtoList.isEmpty()) {
			for (LevelOfOrganization level : dtoList) {
				levelOptionsList.append("<option value=\"" + level.getClientLevelMasterId()+ "\">" + level.getLevelTitle() + "</option>");
			}

		}
		return levelOptionsList.toString();
	}
   
    @ResponseBody
  	@RequestMapping(value = "/getSubOrgList", method = RequestMethod.POST)
  	private String getSubOrgList(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer clientMasterId) {
  		 
  		List<SubsidiaryClientOfOrg>  dtoList=subsaidiaryOrgRegistrationService.getListSubsidairyClientOrg(clientMasterId);
  		StringBuilder subOrgOptionsList = new StringBuilder();
  		subOrgOptionsList.append("<option value=\"\">--Select Sub Cleint--</option>");
  	if (dtoList != null && !dtoList.isEmpty()) {
  			for (SubsidiaryClientOfOrg subOrg : dtoList) {
  				subOrgOptionsList.append("<option value=\"" + subOrg.getIdClientSubMaster()+ "\">" + subOrg.getSubName()+ "</option>");
  			}

  		}
  		return subOrgOptionsList.toString();
  	}
      
    
    @ResponseBody
   	@RequestMapping(value="/organizationUserModule", method=RequestMethod.GET)
   	private ModelAndView organizationUserModule(HttpServletRequest request,HttpServletResponse response ){
   		try{
   			HttpSession session=request.getSession();
   		 UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
	     Integer userId=0;
	     if(userDetail!=null){
		   userId=userDetail.getId();
	     } 
  List<OrganizationMasterRegistration>  list=subsidiaryClientUserService.getOrgClient(userId);

   			//List<OrganizationMasterRegistration>  list= organizationMasterService.getOrganizationMasterRegistration();
			if(list!=null && list.size()>0){
			 session.setAttribute("orgList", list);
			}
   		   	return new ModelAndView("backEndOrgUsersRegistration");
   		}catch(Exception er){
   			er.printStackTrace();
   			return new ModelAndView("backEndOrgUsersRegistration");
     	}
       
       }
    
    @ResponseBody
   	@RequestMapping(value="/organizationUserRegistration", method=RequestMethod.POST)
   	private ModelAndView organizationUserRegistration(HttpServletRequest request,HttpServletResponse response,
   			@RequestParam Integer subName,
   			@RequestParam String contactPerson,
   			@RequestParam String email,
   			@RequestParam String contactPersonPhone,
   			@RequestParam String address,
   			@RequestParam String usersName, 
   			@RequestParam String passwords){
     	try{
     		String encyptedPassword = TruxUtils.computeMD5Hash(passwords);
    		
   		   HttpSession session=request.getSession();
 		   UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");	 		   
   			OrganizationalUser dto=new OrganizationalUser(subName, contactPerson, email, contactPersonPhone, usersName, encyptedPassword, 1, userDetail.getId());
   			dto.setAddress(address);
   			OrganizationalUser saveBack=	OrganizationalUserService.saveOrganizationalUser(dto);
   		 	request.setAttribute("subOrgSaveBack", saveBack);
   			return new ModelAndView("backEndOrgUsersRegistration");
   		}catch(Exception er){
   			er.printStackTrace();
   			return new ModelAndView("backEndOrgUsersRegistration");
     	}
       
       }
    
    
    @RequestMapping(value = "/organizationalBooking", method = RequestMethod.GET)
	public ModelAndView organizationalBooking(HttpServletRequest request,	HttpServletResponse response) {
    	HttpSession session=request.getSession();
    	 UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
	     Integer userId=0;
	     if(userDetail!=null){
		   userId=userDetail.getId();
	     } 
  List<OrganizationMasterRegistration>  list=subsidiaryClientUserService.getOrgClient(userId);

		//	List<OrganizationMasterRegistration>  list= organizationMasterService.getOrganizationMasterRegistration();
		if(list!=null && list.size()>0){
		 session.setAttribute("orgList", list);
		}
		return new ModelAndView("backEndOrgBooking");	 
	}
    
    @ResponseBody
    @RequestMapping(value = "/clientAdhocRequestSearch", method = RequestMethod.POST)
	public RestResponce clientAdhocRequestSearch(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) Integer clientId,
			@RequestParam(required = false) Integer clientSubId,
			@RequestParam(required = false) String startDate,
			@RequestParam(required = false) String endDate,
			@RequestParam(required = false) String orderId,
			@RequestParam(required = false) Integer haul) {

    	RestResponce restResponce = new RestResponce();
    	
    	List<?> list = organizationMasterService.clientAdhocRequestSearch(clientId,clientSubId,startDate,endDate,orderId,haul);
    	
    	if (list != null && list.size()>0){
    		restResponce.setErrorCode("100");
    		restResponce.setErrorMesaage("Success");
    		restResponce.setData(list);
    		
    		return restResponce;
    	} else {
    		restResponce.setErrorCode("101");
    		restResponce.setErrorMesaage("No record found.");
    		
    		return restResponce;
    	}
    	
	}
    
    @ResponseBody
    @RequestMapping(value = "/clientAdhocDriverPayment", method = RequestMethod.POST)
	public RestResponce clientAdhocRequestDriverPayment(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer id,
			@RequestParam(required = false) Double costToDriver,
			@RequestParam(required = false) Double advancePayment,
			@RequestParam(required = false) Double revenueToCompany,
			@RequestParam(required = false) String paymentReferenceNumber,
			@RequestParam(required = false) String remarks,
			@RequestParam(required = false) Integer boxes,
			@RequestParam(required = false) Double balancePayment,
			@RequestParam(required = false) String balanceReferenceNumber,
			@RequestParam(required = false) Double tollPayment,
			@RequestParam(required = false) String tollReferenceNumber,
			@RequestParam(required = false) Double labourPayment,
			@RequestParam(required = false) String labourReferenceNumber) {

    	RestResponce restResponce = new RestResponce();
    	
    	ClientVehicleDeployment cvd = new ClientVehicleDeployment();
    	cvd.setId(id);
    	cvd.setCostToDriver(costToDriver);
    	cvd.setAdvancePayment(advancePayment);
    	cvd.setRevenueToCompany(revenueToCompany);
    	cvd.setPaymentReferenceNumber(paymentReferenceNumber);
    	cvd.setRemarks(remarks);
    	cvd.setBalancePayment(balancePayment);
    	cvd.setBalanceReferenceNumber(balanceReferenceNumber);
    	cvd.setTollPayment(tollPayment);
    	cvd.setTollReferenceNumber(tollReferenceNumber);
    	cvd.setLabourPayment(labourPayment);
    	cvd.setLabourReferenceNumber(labourReferenceNumber);
    	
    	ControllerDAOTracker cdt = organizationMasterService.clientAdhocRequestDriverPayment(cvd);
    	
    	HttpSession session=request.getSession();
    	UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");	 
		  
    	
		if (cdt.isSuccess()) {

			ClientMandateRequest cmr = new ClientMandateRequest();
			cmr.setRequest_id(cdt.getClientVehicleDeployment().getClientRequestId());
			cmr.setBoxWeight(boxes);
			if (userDetail != null) {
				cmr.setModifiedBy(userDetail.getId());
			} else {
				cmr.setModifiedBy(0);
			}
			cmr.setModifiedDate(new Date());

			ControllerDAOTracker cdto = organizationMasterService.updateClientMandateRequestBoxes(cmr);

			if (cdto.isSuccess()) {
				restResponce.setErrorCode(cdt.getErrorCode());
				restResponce.setErrorMesaage(cdt.getErrorMessage());
				restResponce.setData(cdt.getClientVehicleDeployment());
			} else {
				restResponce.setErrorCode(cdto.getErrorCode());
				restResponce.setErrorMesaage(cdto.getErrorMessage());
			}

			return restResponce;
		} else {
			restResponce.setErrorCode(cdt.getErrorCode());
			restResponce.setErrorMesaage(cdt.getErrorMessage());

			return restResponce;
		}
    	
	}
    
    
    @RequestMapping(value = "/clientAdhocRequest", method = RequestMethod.GET)
	public ModelAndView clientAdhocRequest(HttpServletRequest request,	HttpServletResponse response) {
    	HttpSession session=request.getSession();
    	List<OrganizationMasterRegistration> list = organizationMasterService
				.getOrganizationMasterRegistration();
		session.setAttribute("orgList", list);
		return new ModelAndView("backEndClientAdhocRequest");	 
	}
    
	@ResponseBody
	@RequestMapping(value="/backOrgBooking", method=RequestMethod.POST)
	private ModelAndView backOrgBooking(HttpServletRequest request,HttpServletResponse response, 			
			@RequestParam String parentOrgId, 
			@RequestParam String orgName, 
			@RequestParam String contactPerson, 
			@RequestParam String email,
			@RequestParam String fromAddress,
			@RequestParam String toAddress, 
			@RequestParam String contactPersonPhone
			){
		try{		
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
	        	
	        }		
		return new ModelAndView("backEndOrgBooking");
		}catch(Exception er){er.printStackTrace();		
		return  new ModelAndView("backEndOrgBooking");}
	}
	
}
