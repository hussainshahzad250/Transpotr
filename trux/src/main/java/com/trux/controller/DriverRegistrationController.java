package com.trux.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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

import com.trux.model.Cities;
import com.trux.model.ClusterRegistration;
import com.trux.model.Countries;
import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.DriverRegistration;
import com.trux.model.HubRegistration;
import com.trux.model.HubRegistrationDto;
import com.trux.model.OrganizationMasterRegistration;
import com.trux.model.StandRegistration;
import com.trux.model.States;
import com.trux.model.SubsidiaryClientOfOrg;
import com.trux.model.UploadDocumentModel;
import com.trux.model.UserDetail;
import com.trux.model.VehicleRegistration;
import com.trux.model.VehicleType;
import com.trux.service.ClusterRegistrationService;
import com.trux.service.DriverCollectionService;
import com.trux.service.HubRegistrationService;
import com.trux.service.ModuleService;
import com.trux.service.OrganizationMasterService;
import com.trux.service.RegistrationService;
import com.trux.service.StandRegistrationService;
import com.trux.service.SubsaidiaryOrgRegistrationService;
import com.trux.service.SubsidiaryClientUserService;
import com.trux.service.TruxStartUpService;
import com.trux.service.VehicleTypeService;
import com.trux.utils.AWSS3Uploader;
import com.trux.utils.MonthYearUtil;
@Controller
@RequestMapping(value="/reg")
public class DriverRegistrationController {
	public  List<Cities> allCities = null;
	public  List<VehicleType> allVehicle = null;
	public  List<States> allStates = null;
	public  List<Countries> allCountries = null;
	public  List<HubRegistration> allHubter = null; 
 	@Autowired
	private ModuleService moduleService;
 	@Autowired
	private VehicleTypeService vehicleTypeService;
	@Autowired
	private HubRegistrationService hubRegistrationService;
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	DriverCollectionService  driverCollectionService;
	@Autowired
	ClusterRegistrationService clusterRegistrationService;
	@Autowired
	StandRegistrationService standRegistrationService;
	@Autowired
	private OrganizationMasterService organizationMasterService;
	@Autowired
	private TruxStartUpService truxStartUpService;
	@Autowired
	private SubsaidiaryOrgRegistrationService subsaidiaryOrgRegistrationService;
	

	@Autowired
	SubsidiaryClientUserService  subsidiaryClientUserService;
	HttpSession session;
	List<OrganizationMasterRegistration> list = new ArrayList<OrganizationMasterRegistration>();
	 
	@ResponseBody  
	@RequestMapping(value = "/organization", method = RequestMethod.GET)
	public List<OrganizationMasterRegistration> consumerBookAutoSuggetion(HttpServletRequest request,HttpServletResponse response, @RequestParam String orgName) {
	 try{
		List<OrganizationMasterRegistration> result = new ArrayList<OrganizationMasterRegistration>();
		 session=request.getSession();
		 UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
		 Integer userId=0;
		   if(userDetail!=null){
			   userId=userDetail.getId();
		} 
	  List<OrganizationMasterRegistration>  list=subsidiaryClientUserService.getOrgClient(userId);//organizationMasterService.getOrganizationMasterRegistration();
		 
		
	//	list=organizationMasterService.getOrganizationMasterRegistration();	
		if(list!=null){
		for (OrganizationMasterRegistration tag : list) {
			if (tag.getName().contains(orgName)) {
				result.add(tag);
			}
		}
		 return result;
		}return new ArrayList<OrganizationMasterRegistration>();
	 }catch(Exception er){er.printStackTrace(); return new ArrayList<OrganizationMasterRegistration>();}
     }
	
	@ResponseBody  
	@RequestMapping(value = "/getOrganization", method = RequestMethod.GET)
	public List<OrganizationMasterRegistration> getOrganization(HttpServletRequest request,HttpServletResponse response, @RequestParam String orgName) {
	 try{
	 	//list=organizationMasterService.getOrganizationMasterRegistration();
		session= request.getSession();
		 UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
		 Integer userId=0;
		   if(userDetail!=null){
			   userId=userDetail.getId();
		} 
	  List<OrganizationMasterRegistration>  list=subsidiaryClientUserService.getOrgClient(userId);//organizationMasterService.getOrganizationMasterRegistration();
		 
		 return list;  
	 }catch(Exception er){er.printStackTrace(); return new ArrayList<OrganizationMasterRegistration>();}
     }
	
	@ResponseBody
	@RequestMapping(value = "/searchExistDriver", method = RequestMethod.GET)
	public DriverRegistration searchExistDriver(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String driverPhone) {
		try {
			DriverRegistration driver = new DriverRegistration();
			HttpSession sessionn = request.getSession();

			try {
				driver = registrationService.getDriverDetail(driverPhone);
				sessionn.setAttribute("driver", driver);
			} catch (Exception e) {
				e.printStackTrace();
			}
			SubsidiaryClientOfOrg subClientDto = null;
			StandRegistration sndlist = null;
			ClusterRegistration clusertdtoss = null;
			HubRegistration hubdto = null;
			Cities citydto = null;
			OrganizationMasterRegistration clientDto = null;
			Countries countryDto = null;
			States stateDto = null;
			if (driver != null) {
				if (driver.getCountry() != null && !driver.getCountry().equals("")) {
					countryDto = moduleService.getAllCountryById(new Integer(driver.getCountry()));
				}
				if (driver.getState() != null && !driver.getState().equals("")) {
					stateDto = moduleService.getAllStateById(new Integer(driver.getState()));
				}

				if (countryDto != null) {
					driver.setCountryName(countryDto.getValue());
				}
				if (stateDto != null) {
					driver.setStateName(stateDto.getStateName());
				}

				if (driver.getAssosiatedBy() != null && !driver.getAssosiatedBy().equals("")) {
					Integer clientIds = Integer.parseInt(driver.getAssosiatedBy());
					clientDto = organizationMasterService.getClientNameById(clientIds);
				}
				if (driver.getSubOrgClient() != null && !driver.getSubOrgClient().equals("")) {
					Integer subClientId = Integer.parseInt(driver.getSubOrgClient());
					subClientDto = subsaidiaryOrgRegistrationService.getListSubsidairyClientOrgByid(subClientId);
				}
				if (driver.getStandDetails() != null && !driver.getStandDetails().equals("")) {
					Integer standId = Integer.parseInt(driver.getStandDetails());
					sndlist = standRegistrationService.getStandByID(standId);
				}
				if (driver.getCluster() != null && !driver.getCluster().equals("")) {
					Integer clusertId = Integer.parseInt(driver.getCluster());
					clusertdtoss = clusterRegistrationService.getClusterById(clusertId);
				}
				if (driver.getCity() != null && !driver.getCity().equals("")) {
					Integer cityId = Integer.parseInt(driver.getCity());
					citydto = moduleService.getAllCityById(cityId);
				}

				if (driver.getHubId() != null && !driver.getHubId().equals("0")) {
					Integer hubId = Integer.parseInt(driver.getHubId());
					hubdto = hubRegistrationService.hubNameById(hubId);
				}

				if (clientDto != null) {
					driver.setAssosiatedBy(clientDto.getName());
				}
				if (hubdto != null) {
					driver.setHubId(hubdto.getHubName());
				}
				if (subClientDto != null) {
					driver.setSubOrgClient(subClientDto.getSubName());
				}
				if (clusertdtoss != null) {
					driver.setCluster(clusertdtoss.getClusterName());
				}
				if (sndlist != null) {
					driver.setStandDetails(sndlist.getStandName());
				}
				if (citydto != null) {
					driver.setCity(citydto.getCityName());
				}
				
				return driver;
				
			} else {
				///
				return new DriverRegistration();
			}

		} catch (Exception er) {
			er.printStackTrace();
			return new DriverRegistration();
		}

	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/driver", method=RequestMethod.GET)
	private ModelAndView driverRegistration(HttpServletRequest request, HttpServletResponse response){
		try{
		session=request.getSession();
		if(session.getAttribute("countriesList")!=null){
			allCountries=((List<Countries>)session.getAttribute("countriesList"));
		}
		if(allCountries==null){
			allCountries=moduleService.getAllCountriesList();
		}
		if(allCountries!=null){
			session.setAttribute("countriesList", allCountries);
			}
		
		
		if( session.getAttribute("orgList")==null){  
			
		    UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
		     Integer userId=0;
		     if(userDetail!=null){
			   userId=userDetail.getId();
		     } 
	  List<OrganizationMasterRegistration>  list=subsidiaryClientUserService.getOrgClient(userId);//
	  //organizationMasterService.getOrganizationMasterRegistration();
		  session.setAttribute("orgList", list);
		}
		if( session.getAttribute("orgList")!=null){
			 List<OrganizationMasterRegistration>  list=(List<OrganizationMasterRegistration>) session.getAttribute("orgList");
			 session.setAttribute("orgList", list);
		}
		}catch(Exception er){er.printStackTrace();}
		return new ModelAndView("backEndDriverRegistration");		
	}
	 
	@ResponseBody
	@RequestMapping(value="/drivers", method=RequestMethod.POST)
	public ModelAndView registerDriver(MultipartHttpServletRequest request,HttpServletResponse response,
			@RequestParam String driverName,
			@RequestParam String gender,
			@RequestParam String driverContactNumber,
			@RequestParam String drivingExperience,
			@RequestParam String localAddress,
			@RequestParam String permanentAddress ,  
			@RequestParam("imagesOfDH") MultipartFile imagesOfDH,
			@RequestParam("imagesOfDL") MultipartFile imagesOfDL,			
			@RequestParam("iOPVReports") MultipartFile iOPVReports,
			@RequestParam Integer createdBy
			){
	/*	@RequestParam("ioPanCard") MultipartFile ioPanCard,*/
		 DriverRegistration regs=new DriverRegistration();
 
	try {
		
		    allCountries=null;
		    if(allCountries!=null){	
		    allCountries=moduleService.getAllCountriesList();
		    }
			session=request.getSession();
			if(allCountries!=null){			
			session.setAttribute("countriesList", allCountries);
			}
			 UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
			 Integer userId=0;
			   if(userDetail!=null){
				   userId=userDetail.getId();
			} 
		  List<OrganizationMasterRegistration>  list=subsidiaryClientUserService.getOrgClient(userId);//organizationMasterService.getOrganizationMasterRegistration();
			  if(list!=null && list.size()>0){
			 session.setAttribute("orgList", list);
			}
			
			  UploadDocumentModel documentModelDrPhoto = new UploadDocumentModel();
				 UploadDocumentModel documentModelDrDl = new UploadDocumentModel();
				/* UploadDocumentModel documentModelDrPan = new UploadDocumentModel();*/
				 UploadDocumentModel documentModelDrPvr = new UploadDocumentModel();
			        
			     if (!imagesOfDL.isEmpty()) {	
			    	 try {
			            	String name = imagesOfDL.getOriginalFilename();
			            	documentModelDrDl=AWSS3Uploader.uploadDriverFile(name,imagesOfDL.getContentType(),imagesOfDL.getSize(), imagesOfDL.getInputStream(), documentModelDrDl);
			                 byte[] bytes = imagesOfDL.getBytes();
			                 BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File(name)));
			                stream.write(bytes);
			                stream.close();
			                documentModelDrDl.setUploadStatus("SUCCESS");
			             } catch (Exception e) {
			            	 documentModelDrDl.setUploadStatus("Failed");
			            	 documentModelDrDl.setErrorMessage(e.getMessage());
			               
			            }
			                      
				 }
				/* if (!ioPanCard.isEmpty()) {
					 try {
			            	String name = ioPanCard.getOriginalFilename();
			            	documentModelDrPan=	AWSS3Uploader.uploadDriverFile(name,ioPanCard.getContentType(),ioPanCard.getSize(), ioPanCard.getInputStream(), documentModelDrPan);
			                byte[] bytes = ioPanCard.getBytes();
			                BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File(name)));
			                stream.write(bytes);
			                 stream.close();
			                documentModelDrPan.setUploadStatus("SUCCESS");
			             } catch (Exception e) {
			            	 documentModelDrPan.setUploadStatus("Failed");
			            	 documentModelDrPan.setErrorMessage(e.getMessage());
			               
			            }
			               
			           
				 }*/
				 if (!iOPVReports.isEmpty()) {
					 try {
			            	String name = iOPVReports.getOriginalFilename();
			            	documentModelDrPvr=AWSS3Uploader.uploadDriverFile(name,iOPVReports.getContentType(),iOPVReports.getSize(), iOPVReports.getInputStream(), documentModelDrPvr);
			                byte[] bytes = iOPVReports.getBytes();
			               BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File(name)));
			                stream.write(bytes);
			                stream.close();
			                documentModelDrPvr.setUploadStatus("SUCCESS");           
			            } catch (Exception e) {
			            	documentModelDrPvr.setUploadStatus("Failed");
			            	documentModelDrPvr.setErrorMessage(e.getMessage());
			            }
		            
			     }
				 if (!imagesOfDH.isEmpty()) {
					 
					 try {
			            	String name = imagesOfDH.getOriginalFilename();
			              	documentModelDrPhoto=AWSS3Uploader.uploadDriverFile(name,imagesOfDH.getContentType(),imagesOfDH.getSize(), imagesOfDH.getInputStream(),documentModelDrPhoto);
			                byte[] bytes = imagesOfDH.getBytes();
			                BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File(name)));
			                stream.write(bytes);
			                stream.close();
			                documentModelDrPhoto.setUploadStatus("SUCCESS");           
			            } catch (Exception e) {
			            	documentModelDrPhoto.setUploadStatus("Failed");
			            	documentModelDrPhoto.setErrorMessage(e.getMessage());
			            }
			             
				     }
				  
		 	 DriverRegistration dto=new DriverRegistration();
		 	dto.setDriverPhoto(documentModelDrPhoto.getUploadURL());
		 	dto.setImageOfDL(documentModelDrDl.getUploadURL());
		 	/*dto.setImageOfpanCard(documentModelDrPan.getUploadURL());*/
		 	dto.setImageOfpoliceVerificationReport(documentModelDrPvr.getUploadURL());
             dto.setGender(gender);
             dto.setDriverName(driverName);
             dto.setDrivingExperience(drivingExperience);
             dto.setLocalAddress(localAddress); 
             dto.setPermanentAddress(permanentAddress);  
             dto.setIsActive(1);
			 dto.setPhoneNumber(driverContactNumber.trim());
		 HttpSession session=request.getSession();
		   userDetail=(UserDetail)session.getAttribute("userDetail");
		   if(userDetail!=null){
			   if(createdBy.equals(0)){
				  dto.setAgentId(userDetail.getId());
				 }else{
					 dto.setAgentId(createdBy);
				 }
		   } else{
					  dto.setAgentId(createdBy);
				 }
			DriverRegistration driver=null;
			try{
				driver=	 registrationService.getDriverDetail(driverContactNumber.trim());
			
		        }catch(Exception e){
		        	e.printStackTrace();	        	
		    }
		if(driver==null){			 
			 DriverRegistration reg= registrationService.registrationDriverByAPI(dto);
			 request.setAttribute("reg", reg);
		 if(reg!=null && reg.getId()!=null){
		 DriverDeviceVehicleMapping driverDeviceVehicleMapping = new DriverDeviceVehicleMapping(); 
		  if(reg.getPhoneNumber()!=null){
		   driverDeviceVehicleMapping = registrationService.getDriverDeviceDetail(reg.getPhoneNumber());
		 }	
			try {
				if(reg.getId()!=0){
					if(driverDeviceVehicleMapping==null){
						driverDeviceVehicleMapping = new DriverDeviceVehicleMapping(); 
				   } 
				 driverDeviceVehicleMapping.setDriverId(reg.getId());
				 driverDeviceVehicleMapping.setDriverName(reg.getDriverName());
				 driverDeviceVehicleMapping.setDriverPhoneNumber(driverContactNumber);
				 driverDeviceVehicleMapping.setDriverStatus(0);
				 driverDeviceVehicleMapping.setLoginStatus(0); 
				registrationService.saveOrUpdateDDVM(driverDeviceVehicleMapping);
				truxStartUpService.driverRegistrationMap.put(driverContactNumber.trim(), driverDeviceVehicleMapping);
				}
			}catch(Exception er){er.printStackTrace();
			regs.setErrorMesaage(er.getMessage());
			}
		 }
		 }else{
			
			 regs.setId(0);
			
			 request.setAttribute("reg", regs);
			 return new ModelAndView("backEndDriverRegistration");
		 }
		   return new ModelAndView("backEndDriverRegistration");
	} catch (Exception e) {
		 regs.setErrorMesaage(e.getMessage());
		 request.setAttribute("reg", regs);
        	 return new ModelAndView("backEndDriverRegistration");
           }
		 
	}
	
	
	@ResponseBody
	@RequestMapping(value="/regDriverApi", method=RequestMethod.POST)
	public DriverRegistration regDriverApi(HttpServletRequest request,HttpServletResponse response,
			@RequestParam String driverName, 
			@RequestParam String driverContactNumber,
			@RequestParam String drivingExperience,
			@RequestParam String localAddress,
			@RequestParam String permanentAddress , 
			@RequestParam(required =false) String imagesUrlOfDH,
			@RequestParam(required =false) String imagesUrlOfDL,
			@RequestParam(required =false) String  imagesUrlPanCard,
			@RequestParam(required =false) String imagesUrlPVReports,
			@RequestParam  Integer userId,
			@RequestParam (required=false) String dstatus,
			@RequestParam(required=false) String driver_photo_thum
			
			){
		
		 DriverRegistration reg=new DriverRegistration();
		 DriverRegistration driver=new DriverRegistration();
	try {
		  DriverRegistration dto=new DriverRegistration();
		   dto.setGender("Male");
             dto.setDriverName(driverName);
             dto.setDrivingExperience(drivingExperience);
             dto.setLocalAddress(localAddress); 
             dto.setPermanentAddress(permanentAddress); 
            dto.setDriverPhoto(imagesUrlOfDH);
 		 	dto.setImageOfDL(imagesUrlOfDL);
 		 	dto.setImageOfpanCard(imagesUrlPanCard);
 		 	dto.setImageOfpoliceVerificationReport(imagesUrlPVReports);
 		    dto.setPhoneNumber(driverContactNumber.trim());
 		    dto.setAgentId(userId);
 		    dto.setDriver_photo_thum(driver_photo_thum);
 		   dto.setIsActive(1);
			try{
				driver=	 registrationService.getDriverDetail(driverContactNumber.trim());
			  }catch(Exception e){
		        	e.printStackTrace();	        	
		    }
		if(driver==null){		
			if(driverContactNumber!=null && !driverContactNumber.equals("")){
			  reg= registrationService.registrationDriverByAPI(dto);
			}else{
				reg.setId(0);
				  reg.setErrorCode("400");
				  reg.setErrorMesaage("Driver not register !");
				  return reg;
			}
			reg.setErrorCode("200");
			reg.setErrorMesaage("Successfully Saved");
		 if(reg!=null && reg.getId()!=null){
		 DriverDeviceVehicleMapping driverDeviceVehicleMapping = new DriverDeviceVehicleMapping();
		  if(reg.getPhoneNumber()!=null){
		   driverDeviceVehicleMapping = registrationService.getDriverDeviceDetail(reg.getPhoneNumber());
		 }			 
		 if(driverDeviceVehicleMapping!=null){
			 driverDeviceVehicleMapping.setId(driverDeviceVehicleMapping.getId());
		  }else{
			 driverDeviceVehicleMapping = new DriverDeviceVehicleMapping(); 
		 }
			try {if(reg.getId()!=0){
			 		driverDeviceVehicleMapping.setDriverName(reg.getDriverName());
			 		driverDeviceVehicleMapping.setDriverPhoneNumber(driverContactNumber);
			 		driverDeviceVehicleMapping.setDriverId(reg.getId()); 
					 driverDeviceVehicleMapping.setDriverStatus(0);
					 driverDeviceVehicleMapping.setLoginStatus(0); 
					if(reg.getSubOrgClient()!=null && !reg.getSubOrgClient().equals("")){
					 	if(reg.getSubOrgClient()!="0"){
						driverDeviceVehicleMapping.setSubClientId(new Integer(reg.getSubOrgClient())); 
						}
					}
			registrationService.saveOrUpdateDDVM(driverDeviceVehicleMapping);
			truxStartUpService.driverRegistrationMap.put(driverContactNumber, driverDeviceVehicleMapping);
			}
		 /*
			try {
				if(reg.getId()!=0){
				 driverDeviceVehicleMapping.setDriverId(reg.getId());
				 driverDeviceVehicleMapping.setDriverName(driverName);
				 driverDeviceVehicleMapping.setDriverPhoneNumber(driverContactNumber);
				 driverDeviceVehicleMapping.setDriverStatus(0);
					driverDeviceVehicleMapping.setLoginStatus(0);
					driverDeviceVehicleMapping.setDstatus(dstatus);
					
					if(reg.getSubOrgClient()!=null && !reg.getSubOrgClient().equals("")){
						driverDeviceVehicleMapping.setSubClientId(new Integer(reg.getSubOrgClient())); 
						}
					if(reg.getSubOrgClient()!=null &&  !reg.getSubOrgClient().equals("")){
                	driverDeviceVehicleMapping.setSubClientId(new Integer(reg.getSubOrgClient()));
					}
				registrationService.saveOrUpdateDDVM(driverDeviceVehicleMapping);
				truxStartUpService.driverRegistrationMap.put(driverContactNumber.trim(), driverDeviceVehicleMapping);
				}*/
			}catch(Exception er){er.printStackTrace();
			reg.setErrorMesaage(er.getMessage());
			}
		 }
		 }else{
			  reg.setId(0);
			  reg.setErrorCode("400");
			  reg.setErrorMesaage("Driver not register. its already exist or not valide!");
			  return reg;
		 }
		   return reg;
	} catch (Exception e) {
		reg.setErrorMesaage(e.getMessage()); 
        	 return reg;
     }
		 
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/editDriver", method=RequestMethod.GET)
	private ModelAndView editDriver(HttpServletRequest request, HttpServletResponse response){
		try{
		session=request.getSession();
		if(session.getAttribute("countriesList")!=null){
			allCountries=((List<Countries>)session.getAttribute("countriesList"));
		}
		if(allCountries==null){
			allCountries=moduleService.getAllCountriesList();
		}
		if(allCountries!=null){
			session.setAttribute("countriesList", allCountries);
			}
		if( session.getAttribute("orgList")==null){
			 UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
			 Integer userId=0;
			   if(userDetail!=null){
				   userId=userDetail.getId();
			} 
		  List<OrganizationMasterRegistration>  list=subsidiaryClientUserService.getOrgClient(userId);//organizationMasterService.getOrganizationMasterRegistration();
			  session.setAttribute("orgList", list); 
		}
		if( session.getAttribute("orgList")!=null){
			 List<OrganizationMasterRegistration>  list=(List<OrganizationMasterRegistration>) session.getAttribute("orgList");
			 session.setAttribute("orgList", list);
		}
		}catch(Exception er){er.printStackTrace();}
		return new ModelAndView("backEndDriverEditRegistration");		
	}
 	
	@RequestMapping(value="/searchEditDrivers", method=RequestMethod.POST)
	public ModelAndView searchEditDrivers(MultipartHttpServletRequest request,HttpServletResponse response,
			@RequestParam String driverName,
			@RequestParam String gender,
			@RequestParam(required=false) String driverContactNumber,
			@RequestParam String drivingExperience,
			@RequestParam String localAddress,
			@RequestParam String permanentAddress,
			@RequestParam Integer driverId,
			@RequestParam("imagesOfDH") MultipartFile imagesOfDH,
			@RequestParam("imagesOfDL") MultipartFile imagesOfDL,
			/*@RequestParam("ioPanCard") MultipartFile ioPanCard,*/
			@RequestParam("iOPVReports") MultipartFile iOPVReports
			){
		 DriverRegistration regs=new DriverRegistration();
 
	try {
		
		    allCountries=null;
		    if(allCountries!=null){	
		    allCountries=moduleService.getAllCountriesList();
		    }
			session=request.getSession();
			if(allCountries!=null){			
			session.setAttribute("countriesList", allCountries);
			}
			 UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
			 Integer userId=0;
			if(userDetail!=null){
				   userId=userDetail.getId();
			} 
			 List<OrganizationMasterRegistration>  list=new ArrayList<OrganizationMasterRegistration>();
			if(userDetail.getUserRole().equals("Admin")){
				   list=organizationMasterService.getOrganizationMasterRegistration();
					
			}else{
				   list=subsidiaryClientUserService.getOrgClient(userId);//organizationMasterService.getOrganizationMasterRegistration();
					
			}
		 	 
			if(list!=null && list.size()>0){
			 session.setAttribute("orgList", list);
			}
			UploadDocumentModel documentModelDrPhoto = new UploadDocumentModel();
			 UploadDocumentModel documentModelDrDl = new UploadDocumentModel();
			/* UploadDocumentModel documentModelDrPan = new UploadDocumentModel();*/
			 UploadDocumentModel documentModelDrPvr = new UploadDocumentModel();
		        
		     if (!imagesOfDL.isEmpty()) {	
		    	 try {
		            	String name = imagesOfDL.getOriginalFilename();
		            	documentModelDrDl=AWSS3Uploader.uploadDriverFile(name,imagesOfDL.getContentType(),imagesOfDL.getSize(), imagesOfDL.getInputStream(), documentModelDrDl);
		                 byte[] bytes = imagesOfDL.getBytes();
		                 BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File(name)));
		                stream.write(bytes);
		                stream.close();
		                documentModelDrDl.setUploadStatus("SUCCESS");
		             } catch (Exception e) {
		            	 documentModelDrDl.setUploadStatus("Failed");
		            	 documentModelDrDl.setErrorMessage(e.getMessage());
		               
		            }
		                      
			 }
			/* if (!ioPanCard.isEmpty()) {
				 try {
		            	String name = ioPanCard.getOriginalFilename();
		            	documentModelDrPan=	AWSS3Uploader.uploadDriverFile(name,ioPanCard.getContentType(),ioPanCard.getSize(), ioPanCard.getInputStream(), documentModelDrPan);
		                byte[] bytes = ioPanCard.getBytes();
		                BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File(name)));
		                stream.write(bytes);
		                 stream.close();
		                documentModelDrPan.setUploadStatus("SUCCESS");
		             } catch (Exception e) {
		            	 documentModelDrPan.setUploadStatus("Failed");
		            	 documentModelDrPan.setErrorMessage(e.getMessage());
		               
		            }
		               
		           
			 }*/
			 if (!iOPVReports.isEmpty()) {
				 try {
		            	String name = iOPVReports.getOriginalFilename();
		            	documentModelDrPvr=AWSS3Uploader.uploadDriverFile(name,iOPVReports.getContentType(),iOPVReports.getSize(), iOPVReports.getInputStream(), documentModelDrPvr);
		                byte[] bytes = iOPVReports.getBytes();
		               BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File(name)));
		                stream.write(bytes);
		                stream.close();
		                documentModelDrPvr.setUploadStatus("SUCCESS");           
		            } catch (Exception e) {
		            	documentModelDrPvr.setUploadStatus("Failed");
		            	documentModelDrPvr.setErrorMessage(e.getMessage());
		            }
	            
		     }
			 if (!imagesOfDH.isEmpty()) {
				 
				 try {
		            	String name = imagesOfDH.getOriginalFilename();
		              	documentModelDrPhoto=AWSS3Uploader.uploadDriverFile(name,imagesOfDH.getContentType(),imagesOfDH.getSize(), imagesOfDH.getInputStream(),documentModelDrPhoto);
		                byte[] bytes = imagesOfDH.getBytes();
		                BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File(name)));
		                stream.write(bytes);
		                stream.close();
		                documentModelDrPhoto.setUploadStatus("SUCCESS");           
		            } catch (Exception e) {
		            	documentModelDrPhoto.setUploadStatus("Failed");
		            	documentModelDrPhoto.setErrorMessage(e.getMessage());
		            }
		             
			     }
			  
	 	 DriverRegistration dto=new DriverRegistration();
	 	dto.setDriverPhoto(documentModelDrPhoto.getUploadURL());
	 	dto.setImageOfDL(documentModelDrDl.getUploadURL());
	 	/*dto.setImageOfpanCard(documentModelDrPan.getUploadURL());*/
	 	dto.setImageOfpoliceVerificationReport(documentModelDrPvr.getUploadURL());
			dto.setId(driverId);   
             dto.setGender(gender);
             dto.setDriverName(driverName);
             dto.setDrivingExperience(drivingExperience);
             dto.setLocalAddress(localAddress); 
             dto.setPermanentAddress(permanentAddress);  
			 dto.setPhoneNumber(driverContactNumber);
			 dto.setIsActive(1);
//		 HttpSession session=request.getSession();
//		     userDetail=(UserDetail)session.getAttribute("userDetail");
//		   if(userDetail!=null){
//				  dto.setAgentId(userDetail.getId());
//				 }else{
//					 dto.setAgentId(0);
//				 }
			DriverRegistration driver=null;
			try{
				driver=	 registrationService.getDriverDetail(driverContactNumber);
			
		        }catch(Exception e){
		        	e.printStackTrace();	        	
		        	 
		    }
		if(driver!=null){			 
			 DriverRegistration reg= registrationService.updateRegistrationDriverById(dto);
			 request.setAttribute("reg", reg);
		 if(reg!=null && reg.getId()!=null){
			 DriverDeviceVehicleMapping driverDeviceVehicleMapping =null;
			 if(reg.getPhoneNumber()!=null){
			   driverDeviceVehicleMapping = registrationService.getDriverDeviceDetail(reg.getPhoneNumber());
			 }			 
			 if(driverDeviceVehicleMapping!=null){
				 driverDeviceVehicleMapping.setId(driverDeviceVehicleMapping.getId());
			  }else{
				 driverDeviceVehicleMapping = new DriverDeviceVehicleMapping(); 
			 }
				try {
				 		driverDeviceVehicleMapping.setDriverName(driverName);
				 		driverDeviceVehicleMapping.setDriverPhoneNumber(driverContactNumber);
				 		driverDeviceVehicleMapping.setDriverId(reg.getId()); 
						 driverDeviceVehicleMapping.setDriverStatus(0);
						 driverDeviceVehicleMapping.setLoginStatus(0); 
						 
				registrationService.saveOrUpdateDDVM(driverDeviceVehicleMapping);
				truxStartUpService.driverRegistrationMap.put(driverContactNumber, driverDeviceVehicleMapping);
				
			}catch(Exception er){er.printStackTrace();
			regs.setErrorMesaage(er.getMessage());
			}
			 }
		 
		 }else{
			
			 regs.setId(0);
			 
			 request.setAttribute("reg", regs);
			 return new ModelAndView("backEndDriverEditRegistration");
		 }
		   return new ModelAndView("backEndDriverEditRegistration");
	} catch (Exception e) {
		 regs.setErrorMesaage(e.getMessage());
		 request.setAttribute("reg", regs);
        	 return new ModelAndView("backEndDriverEditRegistration");
           }
		 
	}
	
	//update Driver API
	@ResponseBody
	@RequestMapping(value="/updateDriverApi", method=RequestMethod.POST)
	public DriverRegistration updateDriverApi(HttpServletRequest request,HttpServletResponse response,			 
			@RequestParam String driverName, 
			@RequestParam String driverContactNumber,
			@RequestParam String drivingExperience,
			@RequestParam String localAddress,
			@RequestParam String permanentAddress , 
			@RequestParam(required =false) String imagesUrlOfDH,
			@RequestParam(required =false) String imagesUrlOfDL,
			@RequestParam(required =false) String  imagesUrlPanCard,
			@RequestParam(required =false) String imagesUrlPVReports ,
			@RequestParam  Integer userId,
			@RequestParam  Integer driverId,
			@RequestParam(required=false) String dstatus,
			@RequestParam(required=false) String driver_photo_thum
			  
			){
		 DriverRegistration regs=new DriverRegistration();
		 DriverRegistration reg=null;
 
	try {
		
	 
	 	 DriverRegistration dto=new DriverRegistration();
	 	  
	 	dto.setDriverPhoto(imagesUrlOfDH);
	 	dto.setImageOfDL(imagesUrlOfDL);
	 	dto.setImageOfpanCard(imagesUrlPanCard);
	 	dto.setImageOfpoliceVerificationReport(imagesUrlPVReports);
	 	dto.setDriver_photo_thum(driver_photo_thum);
			dto.setId(driverId);   
             dto.setGender("Male");
             dto.setDriverName(driverName);
             dto.setDrivingExperience(drivingExperience);
             dto.setLocalAddress(localAddress); 
             dto.setPermanentAddress(permanentAddress);  
			 dto.setPhoneNumber(driverContactNumber);
		  dto.setAgentId(userId);
			 
			DriverRegistration driver=null;
			try{
				driver=	 registrationService.getDriverDetail(driverContactNumber);
			
		        }catch(Exception e){
		        	e.printStackTrace();	        	
		   }
		if(driver!=null){			 
			   reg= registrationService.updateRegistrationDriverById(dto);
			   reg.setErrorCode("200");
			   reg.setErrorMesaage("Successfully updated");
		 
		 if(reg!=null && reg.getId()!=null){
			 DriverDeviceVehicleMapping driverDeviceVehicleMappings =null;
			 if(reg.getPhoneNumber()!=null){
			   driverDeviceVehicleMappings = registrationService.getDriverDeviceDetail(reg.getPhoneNumber());
			 }
			 if(driverDeviceVehicleMappings!=null){
		   DriverDeviceVehicleMapping driverDeviceVehicleMapping = new DriverDeviceVehicleMapping();
		 	try {
		 		driverDeviceVehicleMapping.setDriverName(driverName);
		 		driverDeviceVehicleMapping.setDriverPhoneNumber(driverContactNumber);
		 		driverDeviceVehicleMapping.setDriverId(reg.getId());
		 		 driverDeviceVehicleMapping.setId(driverDeviceVehicleMappings.getId());
				 driverDeviceVehicleMapping.setDriverId(reg.getId());
//				 driverDeviceVehicleMapping.setDriverStatus(0);
//				 driverDeviceVehicleMapping.setLoginStatus(0); 
				if(reg.getSubOrgClient()!=null && !reg.getSubOrgClient().equals("")){
					driverDeviceVehicleMapping.setSubClientId(new Integer(reg.getSubOrgClient())); 
				}
				registrationService.saveOrUpdateDDVM(driverDeviceVehicleMapping);
				truxStartUpService.driverRegistrationMap.put(driverContactNumber, driverDeviceVehicleMapping);
				 reg.setErrorCode("200");
				 reg.setErrorMesaage("Driver is updated successfully!");
			  return reg;
			}catch(Exception er){er.printStackTrace();
			//regs.setErrorMesaage(er.getMessage());
			reg.setErrorCode("400");
			 reg.setErrorMesaage("Driver does not updated !");
			 regs.setId(0); 
			 return reg;
			}
			 }
			 reg.setErrorCode("200");
			 reg.setErrorMesaage("Driver is updated successfully!");
			 
			 return reg; 
		 }
		 }else{
			 reg=new DriverRegistration();
			 reg.setErrorCode("400");
			 reg.setErrorMesaage("Driver does not updated !");
			 regs.setId(0); 
			 return reg;
		 }
		 
		 reg.setErrorCode("200");
		 reg.setErrorMesaage("Driver is updated successfully!");
		 regs.setId(0); 
		 return reg; 
	} catch (Exception e) {
		 reg=new DriverRegistration();
		 reg.setErrorCode("400");
		 reg.setErrorMesaage("Driver does not updated !");
		 regs.setId(0); 
		 return reg;
	 }
		 
	}
	
	//
	@RequestMapping(value = "/hubRegister", method=RequestMethod.GET)
	private ModelAndView hubRegister(HttpServletRequest request, HttpServletResponse response){
		allCountries=moduleService.getAllCountriesList();
		if(allCountries!=null){
		session=request.getSession();
		session.setAttribute("countriesList", allCountries);
		}
		return new ModelAndView("backEndHubRegistration");	
	}
	@RequestMapping(value = "/hubRegister", method=RequestMethod.POST)
	private ModelAndView hubRegisters(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("dto") HubRegistrationDto dto ,BindingResult result){
		try{
		allCountries=moduleService.getAllCountriesList();
		HubRegistration dtos=new HubRegistration(dto.getCountryId(), dto.getStateId(), dto.getCityId(), dto.getHubName());
		HubRegistration saveHubBack=hubRegistrationService.registerHub(dtos);
		if(allCountries!=null){
		session=request.getSession();
		session.setAttribute("countriesList", allCountries);		
		}
		if(saveHubBack!=null && saveHubBack.getHubId()!=null){
			request.setAttribute("saveHubBack", saveHubBack);
		}
		}catch(Exception er){er.printStackTrace();}
		return new ModelAndView("backEndHubRegistration");	
	}
	
	@RequestMapping(value = "/clusterModule", method=RequestMethod.GET)
	private ModelAndView clusterModule(HttpServletRequest request, HttpServletResponse response){
		try{
		List<HubRegistration> list=hubRegistrationService.getHubList();
		if(list!=null){
		session=request.getSession();
		session.setAttribute("hublist", list);
		}
	}catch(Exception er){er.printStackTrace();}
		return new ModelAndView("backEndClusterRegistration");	
	}
	@RequestMapping(value = "/clusterRegister", method=RequestMethod.POST)
	private ModelAndView clusterRegister(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer hubId,
			@RequestParam String clusterName){
		try{
		List<HubRegistration> list=hubRegistrationService.getHubList();
		if(list!=null){
		session=request.getSession();
		session.setAttribute("hublist", list);
		}		 
		ClusterRegistration dto =new ClusterRegistration();
		dto.setHubId(hubId);
		dto.setClusterName(clusterName);
		ClusterRegistration fidback=clusterRegistrationService.saveCluster(dto);
		if(fidback!=null && fidback.getClusterId()!=null){
			request.setAttribute("clusterSave", fidback);
		}		
		}catch(Exception er){er.printStackTrace();}
		return new ModelAndView("backEndClusterRegistration");	
	}
	
	@RequestMapping(value = "/standModule", method=RequestMethod.GET)
	private ModelAndView standModule(HttpServletRequest request, HttpServletResponse response){
try{
		List<HubRegistration> list=hubRegistrationService.getHubList();
		if(list!=null){
		session=request.getSession();
		session.setAttribute("hublist", list);
		}	
}catch(Exception er){er.printStackTrace();}
		return new ModelAndView("backEndStandRegistration");	
	}
	//hubListAPI 
	@ResponseBody
	@RequestMapping(value = "/hubListAPI", method=RequestMethod.GET)
	public List<HubRegistration> hubListAPI(HttpServletRequest request, HttpServletResponse response){
		List<HubRegistration> list=null;
		try{
		  list=hubRegistrationService.getHubList();
		if(list!=null){
		return list;
		}else{
			list=new ArrayList<HubRegistration>();
			HubRegistration dto=new HubRegistration();
			dto.setErrorCode("400");
			dto.setErrorMessage("Not any hub exist !");
			list.add(dto);
			return list;
		}	
       }catch(Exception er){er.printStackTrace();}
		list=new ArrayList<HubRegistration>();
		HubRegistration dto=new HubRegistration();
		dto.setErrorCode("400");
		dto.setErrorMessage("Not any hub exist !");
		list.add(dto);
		return list;
	}
	
	
	@RequestMapping(value = "/standRegister", method=RequestMethod.POST)
	private ModelAndView standRegister(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer clusterId,
			@RequestParam String standName){
		try{
		List<HubRegistration> list=hubRegistrationService.getHubList();
		if(list!=null){
		session=request.getSession();
		session.setAttribute("hublist", list);
		}	
		StandRegistration dto=new StandRegistration();
		dto.setClusterId(clusterId);
		dto.setStandName(standName);
		StandRegistration saveStandBack=standRegistrationService.saveStand(dto);
		if(saveStandBack!=null && saveStandBack.getStandId()!=null){
			request.setAttribute("saveStandBack", saveStandBack);
		}
		}catch(Exception er){er.printStackTrace();}
		return new ModelAndView("backEndStandRegistration");	
	}
	@ResponseBody
	@RequestMapping(value = "/standRegistrationAPI", method=RequestMethod.POST)
	private StandRegistration standRegistrationAPI(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required=false) Integer hubId,
			@RequestParam Integer clusterId,
			@RequestParam String standName){
		try{
		StandRegistration dto=new StandRegistration();
		dto.setClusterId(clusterId);
		dto.setStandName(standName);
		StandRegistration saveStandBack=standRegistrationService.saveStand(dto);
		if(saveStandBack!=null && saveStandBack.getStandId()!=null){
			saveStandBack.setErrorCode("200");	
			saveStandBack.setErrorMessage("Stand is registered successfully !.");
			return saveStandBack;
		}else{
			StandRegistration dd=new StandRegistration();
			dd.setErrorCode("400");	
			dd.setErrorMessage("Stands does not register successfully !.");
			return dd;
		}
		}catch(Exception er){er.printStackTrace();
		StandRegistration dd=new StandRegistration();
		dd.setErrorCode("400");	
		dd.setErrorMessage("Stands does not register successfully !."+er.getMessage());
		return dd;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getClusterByHub", method = RequestMethod.POST)
	private String getClusterByHub(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer hubId) {
		StringBuilder clusterOptionsList = new StringBuilder();
		try{
			List<ClusterRegistration> crlist=null;
			if(hubId!=null && hubId!=0){
		    ClusterRegistration dtos=new ClusterRegistration();
		    dtos.setHubId(hubId);
		     crlist=clusterRegistrationService.getClusterList(dtos);
			}
 		clusterOptionsList.append("<option value=\"\">--Select Cluster--</option>");
	     if (crlist != null && !crlist.isEmpty()) {
			for (ClusterRegistration hub : crlist) {
				clusterOptionsList.append("<option value=\"" + hub.getClusterId()+ "\">" + hub.getClusterName() + "</option>");
			}

		}
		return clusterOptionsList.toString();
		}catch(Exception er){er.printStackTrace();
		clusterOptionsList.append("<option value=\"\">"+er.getMessage()+"</option>");
		return clusterOptionsList.toString();}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getClusterByHubId", method = RequestMethod.GET)
	private List<ClusterRegistration> getClusterByHubId(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer hubId) {
		List<ClusterRegistration> crlist=null;
		try{
			if(hubId!=null && hubId!=0){
		    ClusterRegistration dtos=new ClusterRegistration();
		    dtos.setHubId(hubId);
		     crlist=clusterRegistrationService.getClusterList(dtos);
			}
 		if(crlist!=null && crlist.size()>0){
		 return crlist;
 		}else{
		 ClusterRegistration dt=new ClusterRegistration();
		 dt.setErrorCode("400");
		 dt.setErrorMessage("Cluster Does not Exist");
		 crlist =new ArrayList<ClusterRegistration>();
		// crlist.add(dt);
		 return crlist;
		 }
		}catch(Exception er){er.printStackTrace();
		 ClusterRegistration dt=new ClusterRegistration();
		 dt.setErrorCode("400");
		 dt.setErrorMessage("Cluster Does not Exist");
		 crlist =new ArrayList<ClusterRegistration>();
		 crlist.add(dt);
		 return crlist;}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getClusterByHubToEdit", method = RequestMethod.POST)
	private String getClusterByHubToEdit(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer hubId) {
		StringBuilder clusterOptionsList = new StringBuilder();
		try{
			List<ClusterRegistration> crlist=null;
			if(hubId!=null && hubId!=0){
		    ClusterRegistration dtos=new ClusterRegistration();
		    dtos.setHubId(hubId);
		     crlist=clusterRegistrationService.getClusterList(dtos);
			}
 		clusterOptionsList.append("<option value=\"\">--Select Cluster--</option>");
	     if (crlist != null && !crlist.isEmpty()) {
			for (ClusterRegistration hub : crlist) {
				clusterOptionsList.append("<option value=\"" + hub.getClusterId()+ "\">" + hub.getClusterName() + "</option>");
			}

		}
		return clusterOptionsList.toString();
		}catch(Exception er){er.printStackTrace();
		clusterOptionsList.append("<option value=\"\">"+er.getMessage()+"</option>");
		return clusterOptionsList.toString();}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getClusterById", method = RequestMethod.POST)
	private ClusterRegistration getClusterById(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer clusterId) {
		 	try{ 
		    ClusterRegistration dtos=null;
		    dtos=clusterRegistrationService.getClusterById(clusterId);
	        return dtos;
		}catch(Exception er){er.printStackTrace();
		 ClusterRegistration dtos=new ClusterRegistration() ;
		 dtos.setClusterName("");
		  return dtos;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getSubsidiaryOrg", method = RequestMethod.POST)
	private String getSubsidiaryOrg(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer hubId,@RequestParam Integer idClientMaster) {
		StringBuilder clusterOptionsList = new StringBuilder();
		try{
			session=request.getSession();
			 UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
			 Integer userId=0;
			   if(userDetail!=null){
				   userId=userDetail.getId();
			} 
		
		  	List<SubsidiaryClientOfOrg> crlist=subsidiaryClientUserService.getSubsidiaryClientOfOrgListByHubId(userId, idClientMaster, hubId);//subsaidiaryOrgRegistrationService.getListSubsidairyClientOrg(idClientMaster, hubId);
 		  clusterOptionsList = new StringBuilder();
 		clusterOptionsList.append("<option value=\"\">--Select Sub Org--</option>");
	if (crlist != null && !crlist.isEmpty()) {
			for (SubsidiaryClientOfOrg hub : crlist) {
				clusterOptionsList.append("<option value=\"" + hub.getIdClientSubMaster()+ "\">" + hub.getSubName() + "</option>");
			}

		}
		return clusterOptionsList.toString();
		}catch(Exception er){
			clusterOptionsList.append("<option value=\"\">"+er.getMessage()+"</option>");
			er.printStackTrace();
			return clusterOptionsList.toString();}
	}
	// getSubsidiaryAPI
	@ResponseBody
	@RequestMapping(value = "/getSubsidiaryAPI", method = RequestMethod.POST)
	public List<SubsidiaryClientOfOrg> getSubsidiaryAPI(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer hubId, @RequestParam Integer idClientMaster,
			@RequestParam Integer userId) {
		try {
			List<SubsidiaryClientOfOrg> crlists = subsidiaryClientUserService.getSubsidiaryClientOfOrgListByHubId(userId,idClientMaster, hubId);
			// subsaidiaryOrgRegistrationService.getListSubsidairyClientOrg(idClientMaster,hubId);
			if (crlists != null && crlists.size() > 0) {
				return crlists;
			} else {
				List<SubsidiaryClientOfOrg> crlist = new ArrayList<SubsidiaryClientOfOrg>();
				/*
				 * SubsidiaryClientOfOrg dd=new SubsidiaryClientOfOrg();
				 * dd.setErrorCode("400"); dd.setErrorMessage(
				 * "Given details by user,hub,client related records does not exist ! "
				 * ); crlist.add(dd);
				 */
				return crlist;
			}

		} catch (Exception er) {
			List<SubsidiaryClientOfOrg> crlist = new ArrayList<SubsidiaryClientOfOrg>();
			SubsidiaryClientOfOrg dd = new SubsidiaryClientOfOrg();
			dd.setErrorCode("400");
			dd.setErrorMessage("Given details by user,hub,client related records does not exist !");
			crlist.add(dd);
			return crlist;
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getSubsidiaryOrgByMasterId", method = RequestMethod.POST)
	private String getSubsidiaryOrgByMasterId(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer idClientMaster) {
		StringBuilder clusterOptionsList = new StringBuilder();
		try{
			session =request.getSession();
			 UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
			 Integer userId=0;
			   if(userDetail!=null){
				   userId=userDetail.getId();
			} 
		List<SubsidiaryClientOfOrg> crlist=//subsidiaryClientUserService.getSubsidiaryClientOfOrgListByUserId(userId, idClientMaster);//
		subsaidiaryOrgRegistrationService.getSubsidairyClientOrg(idClientMaster);
 		  clusterOptionsList = new StringBuilder();
 		clusterOptionsList.append("<option value=\"\">--Select Sub Org--</option>");
	if (crlist != null && !crlist.isEmpty()) {
			for (SubsidiaryClientOfOrg hub : crlist) {
				clusterOptionsList.append("<option value=\"" + hub.getIdClientSubMaster()+ "\">" + hub.getSubName() + "</option>");
			}
         }
		return clusterOptionsList.toString();
		}catch(Exception er){
			clusterOptionsList.append("<option value=\"\">"+er.getMessage()+"</option>");
			er.printStackTrace();
			return clusterOptionsList.toString();}
	}
	
	
	@ResponseBody 
	@RequestMapping(value = "/getSubsidiary", method = RequestMethod.POST)
	private String getSubsidiary(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer idClientMaster) {
		StringBuilder clusterOptionsList = new StringBuilder();
		try{
			session =request.getSession();
			 UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
			 Integer userId=0;
			   if(userDetail!=null){
				   userId=userDetail.getId();
			} 
		List<SubsidiaryClientOfOrg> crlist=//subsidiaryClientUserService.getSubsidiaryClientOfOrgListByUserId(userId, idClientMaster);//
		subsaidiaryOrgRegistrationService.getSubsidairyClientOrg(idClientMaster);
 		  clusterOptionsList = new StringBuilder();
 		  
 		 clusterOptionsList.append("<div class='mutliSelect1' style='width:100%; height:200px;padding: 6px; margin: 0px;border-radius: 6px;'> <ul style='overflow: auto; display: block;'>");
 		clusterOptionsList.append("<li>--Select Subsidiary--</li>");
	if (crlist != null && !crlist.isEmpty()) {
			for (SubsidiaryClientOfOrg hub : crlist) {
		 	clusterOptionsList.append("<li><input type='checkbox' name='subsidiaryId"+ hub.getIdClientSubMaster()+"' id='subsidiaryId"+ hub.getIdClientSubMaster()+"' onClick=\"collectCheckVal('subsidiaryId"+hub.getIdClientSubMaster()+"');\" value=" + hub.getIdClientSubMaster()+ " />" + hub.getSubName() +"</li>");
         	}

		}
	clusterOptionsList.append("</ul></div>");
		return clusterOptionsList.toString();
		}catch(Exception er){
			 clusterOptionsList.append("<div class='mutliSelect1' style='width:100%; height:200px;padding: 6px; margin: 0px;border-radius: 6px;'> <ul style='overflow: auto; display: block;'>");
		 		clusterOptionsList.append("<li>--Select Subsidiary--</li>"); 
		 		clusterOptionsList.append("</ul></div>");
			er.printStackTrace();
			return clusterOptionsList.toString();}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getSubsidiaryOrgByMasterIdWithUser", method = RequestMethod.POST)
	private String getSubsidiaryOrgByMasterIdWithUser(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer idClientMaster) {
		StringBuilder clusterOptionsList = new StringBuilder();
		try{
			session =request.getSession();
			 UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
			 Integer userId=0;
			   if(userDetail!=null){
				   userId=userDetail.getId();
			} 
		List<SubsidiaryClientOfOrg> crlist=subsidiaryClientUserService.getSubsidiaryClientOfOrgListByUserId(userId, idClientMaster);//
		//subsaidiaryOrgRegistrationService.getSubsidairyClientOrg(idClientMaster);
 		  clusterOptionsList = new StringBuilder();
 		clusterOptionsList.append("<option value=\"\">--Select Sub Org--</option>");
	if (crlist != null && !crlist.isEmpty()) {
			for (SubsidiaryClientOfOrg hub : crlist) {
				clusterOptionsList.append("<option value=\"" + hub.getIdClientSubMaster()+ "\">" + hub.getSubName() + "</option>");
			}

		}
		return clusterOptionsList.toString();
		}catch(Exception er){
			clusterOptionsList.append("<option value=\"\">"+er.getMessage()+"</option>");
			er.printStackTrace();
			return clusterOptionsList.toString();}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getSubsidiaryDetailsByClientId", method = RequestMethod.GET)
	public List<SubsidiaryClientOfOrg>  getSubsidiaryDetailsByClientId(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer idClientMaster) {
		List<SubsidiaryClientOfOrg> crlist=new ArrayList<SubsidiaryClientOfOrg>();
		try{
	   crlist=subsaidiaryOrgRegistrationService.getSubsidairyClientOrg(idClientMaster);
	  
	   if(crlist!=null && crlist.size()>0){
	   return crlist;
	   }else{
	     SubsidiaryClientOfOrg dd=new SubsidiaryClientOfOrg();
		dd.setErrorCode("200");
		dd.setErrorMessage("Records does not exist");
		crlist=new ArrayList<SubsidiaryClientOfOrg>();
		crlist.add(dd);
	   return crlist;
	   }
		}catch(Exception er){
			SubsidiaryClientOfOrg dd=new SubsidiaryClientOfOrg();
			dd.setErrorCode("200");
			dd.setErrorMessage(er.getMessage());
			crlist=new ArrayList<SubsidiaryClientOfOrg>();
			crlist.add(dd);
		 return crlist;
		 }
		 
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getSubsidiaryByClientIdHubId", method = RequestMethod.GET)
	private List<SubsidiaryClientOfOrg>  getSubsidiaryByClientIdHubId(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer hubId,@RequestParam Integer idClientMaster) {
		List<SubsidiaryClientOfOrg> crlist=null; 	
		try{
	      crlist=subsaidiaryOrgRegistrationService.getListSubsidairyClientOrg(idClientMaster, hubId);
 	 if(crlist!=null && crlist.size()>0){
 
		return crlist;
 	 }else{
 		crlist=new ArrayList<SubsidiaryClientOfOrg>();
 		SubsidiaryClientOfOrg sc=new SubsidiaryClientOfOrg();
 		sc.setErrorCode("400");
 		sc.setErrorMessage("Subsidiary client dooes not exist.");
 		 return crlist;
 	 }
		}catch(Exception er){
			crlist=new ArrayList<SubsidiaryClientOfOrg>();
	 		SubsidiaryClientOfOrg sc=new SubsidiaryClientOfOrg();
	 		sc.setErrorCode("400");
	 		sc.setErrorMessage("Subsidiary client dooes not exist.");
	 		 return crlist;
	}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getSubsidiaryClient", method = RequestMethod.POST)
	private String getSubsidiaryClient(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer idClientMaster) {
		StringBuilder clusterOptionsList = new StringBuilder();
		try{
		
		List<SubsidiaryClientOfOrg> crlist=subsaidiaryOrgRegistrationService.getListSubsidairyClientByid(idClientMaster);
 		  clusterOptionsList = new StringBuilder();
 		clusterOptionsList.append("<option value=\"\">--Select Sub client--</option>");
	if (crlist != null && !crlist.isEmpty()) {
			for (SubsidiaryClientOfOrg hub : crlist) {
				clusterOptionsList.append("<option value=\"" + hub.getIdClientSubMaster()+ "\">" + hub.getSubName() + "</option>");
			}

		}
		return clusterOptionsList.toString();
		}catch(Exception er){
			clusterOptionsList.append("<option value=\"\">"+er.getMessage()+"</option>");
			er.printStackTrace();
			return clusterOptionsList.toString();}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getSubsidiaryOrgNameById", method = RequestMethod.POST)
	private SubsidiaryClientOfOrg getSubsidiaryOrgNameById(HttpServletRequest request, HttpServletResponse response ,@RequestParam Integer idClientMaster) {
	 	try{		
		 SubsidiaryClientOfOrg crlist=subsaidiaryOrgRegistrationService.getListSubsidairyClientOrgByid(idClientMaster);
 		 if(crlist!=null){
		 return crlist;
 		 }
 		 else{
 		 crlist=new  SubsidiaryClientOfOrg();
		 return crlist;
 		 }
		}catch(Exception er){
		 	er.printStackTrace();
			 SubsidiaryClientOfOrg crlist=new  SubsidiaryClientOfOrg();
	 return crlist;
	 }
	}
	
	@ResponseBody
	@RequestMapping(value = "/getSubsidiaryOrgToEdit", method = RequestMethod.POST)
	private String getSubsidiaryOrgToEdit(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer hubId,@RequestParam Integer idClientMaster) {
		StringBuilder clusterOptionsList = new StringBuilder();
		try{
		
		List<SubsidiaryClientOfOrg> crlist=subsaidiaryOrgRegistrationService.getListSubsidairyClientOrg(idClientMaster, hubId);
 		  clusterOptionsList = new StringBuilder();
 		clusterOptionsList.append("<option value=\"\">--Select Sub Org--</option>");
	if (crlist != null && !crlist.isEmpty()) {
			for (SubsidiaryClientOfOrg hub : crlist) {
				clusterOptionsList.append("<option value=\"" + hub.getIdClientSubMaster()+ "\">" + hub.getSubName() + "</option>");
			}

		}
		return clusterOptionsList.toString();
		}catch(Exception er){
			clusterOptionsList.append("<option value=\"\">"+er.getMessage()+"</option>");
			er.printStackTrace();
			return clusterOptionsList.toString();}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getStandByCluster", method = RequestMethod.POST)
	private String getStandByCluster(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer clusterId) {
		StringBuilder standOptionsList = new StringBuilder();
		try{
			List<StandRegistration> sndlist=null;
			if(clusterId!=null && clusterId!=0){
		    sndlist=standRegistrationService.getStandListByCluster(clusterId);
			}
 		standOptionsList.append("<option value=\"\">--Select Stand--</option>");
	if (sndlist != null && !sndlist.isEmpty()) {
			for (StandRegistration hub : sndlist) {
				standOptionsList.append("<option value=\"" + hub.getStandId()+ "\">" + hub.getStandName() + "</option>");
			}
      }
		return standOptionsList.toString();
		}catch(Exception er){
			standOptionsList.append("<option value=\"\">"+er.getMessage()+"</option>");
			er.printStackTrace();
			return standOptionsList.toString();
			}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getStandByClusterId", method = RequestMethod.GET)
	private List<StandRegistration> getStandByClusterId(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer clusterId) {
		List<StandRegistration> sndlist=null; 
		try{
		 if(clusterId!=null && clusterId!=0){
		    sndlist=standRegistrationService.getStandListByCluster(clusterId);
			}
 		 
		 if(sndlist!=null && sndlist.size()>0){
			 return sndlist;
		 }else{
			 sndlist=new ArrayList<StandRegistration>();
			/* StandRegistration s=new StandRegistration();
			 s.setErrorCode("400");
			 s.setErrorMessage("Stand does not exist");
			 sndlist.add(s);*/
			 return sndlist;
		 }
		}catch(Exception er){
			 sndlist=new ArrayList<StandRegistration>();
			 StandRegistration s=new StandRegistration();
			 s.setErrorCode("400");
			 s.setErrorMessage("Stand does not exist");
			 sndlist.add(s);
			 return sndlist;
			}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getStandById", method = RequestMethod.GET)
	private StandRegistration getStandById(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer standDetailsId) {
		 StandRegistration sndlist=null;
		 try{
		    sndlist=standRegistrationService.getStandByID(standDetailsId);
			if(sndlist!=null){
			return sndlist;
			}else{
			sndlist=new StandRegistration();
			sndlist.setStandName("");
			return sndlist;
			}
 	 }catch(Exception er){
		 er.printStackTrace();
		 sndlist=new StandRegistration();
		 sndlist.setStandName("");
			return sndlist;
			}
	}
	@ResponseBody
	@RequestMapping(value = "/getStandByClusterToEdit", method = RequestMethod.POST)
	private String getStandByClusterToEdit(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer clusterId) {
		StringBuilder standOptionsList = new StringBuilder();
		try{
			List<StandRegistration> sndlist=null;
			if(clusterId!=null && clusterId!=0){
		    sndlist=standRegistrationService.getStandListByCluster(clusterId);
			}
 		standOptionsList.append("<option value=\"\">--Select Stand--</option>");
	if (sndlist != null && !sndlist.isEmpty()) {
			for (StandRegistration hub : sndlist) {
				standOptionsList.append("<option value=\"" + hub.getStandId()+ "\">" + hub.getStandName() + "</option>");
			}
      }
		return standOptionsList.toString();
		}catch(Exception er){
			standOptionsList.append("<option value=\"\">"+er.getMessage()+"</option>");
			er.printStackTrace();
			return standOptionsList.toString();
			}
	}
	@ResponseBody
	@RequestMapping(value = "/getCitiesByState", method = RequestMethod.POST)
	private String getCitiesList(HttpServletRequest request,HttpServletResponse response) {
		StringBuilder citiOptionsList = new StringBuilder();
		try{
		String stateId = request.getParameter("state");
		if(stateId!=null && stateId!=""){
		 if( stateId!="0"){
		 allCities = moduleService.getAllCitiesForAState(Integer.parseInt(stateId));
		 }
		}
		System.out.println("Stateid:- " + stateId);
		
		citiOptionsList.append("<option value=\"\">--Select City--</option>");
	if (allCities != null && !allCities.isEmpty()) {
			for (Cities city : allCities) {
			citiOptionsList.append("<option value=\"" + city.getCityId()+ "\">" + city.getCityName() + "</option>");
			}

		}
		return citiOptionsList.toString();
    }catch(Exception er){
    	citiOptionsList.append("<option value=\"\">"+er.getMessage()+"</option>");
    	 er.printStackTrace();
    	return citiOptionsList.toString();}
	}
	
	
	
	/*Added For Vehicle Type*/
	@ResponseBody
	@RequestMapping(value = "/getVehicleList", method = RequestMethod.POST)
	private String getVehicleList(HttpServletRequest request, HttpServletResponse response) {
		StringBuilder citiOptionsList = new StringBuilder();
		try {
			boolean vehicleId = request.getParameter("vehicle") != null;
			if(vehicleId ==true){
				allVehicle = moduleService.getVehicle("Tata Ace");	
			}
			/*String vehicleId1 = request.getParameter("vehicle1");
			String vehicleId2 = request.getParameter("vehicle2");
			String vehicleId3 = request.getParameter("vehicle3");
			String vehicleId4 = request.getParameter("vehicle4");
			String vehicleId5 = request.getParameter("vehicle5");
			String vehicleId6= request.getParameter("vehicle6");
			String vehicleId7 = request.getParameter("vehicle7");*/
			
			
//			if (myCheckBox != null && vehicleId != "") {
//				if (vehicleId != "0") {
//					allVehicle = moduleService.getAllVehicle(Integer.parseInt(vehicleId));
//				}
//			}
		/*	if (vehicleId1 != null && vehicleId1 != "") {
				if (vehicleId1 != "0") {
					allVehicle = moduleService.getAllVehicle(Integer.parseInt(vehicleId1));
				}
			}
			if (vehicleId2 != null && vehicleId2 != "") {
				if (vehicleId2 != "0") {
					allVehicle = moduleService.getAllVehicle(Integer.parseInt(vehicleId2));
				}
			}
			if (vehicleId3 != null && vehicleId3 != "") {
				if (vehicleId3 != "0") {
					allVehicle = moduleService.getAllVehicle(Integer.parseInt(vehicleId3));
				}
			}
			if (vehicleId4 != null && vehicleId4 != "") {
				if (vehicleId4 != "0") {
					allVehicle = moduleService.getAllVehicle(Integer.parseInt(vehicleId4));
				}
			}
			if (vehicleId5 != null && vehicleId5 != "") {
				if (vehicleId5 != "0") {
					allVehicle = moduleService.getAllVehicle(Integer.parseInt(vehicleId5));
				}
			}
			if (vehicleId6 != null && vehicleId6 != "") {
				if (vehicleId6 != "0") {
					allVehicle = moduleService.getAllVehicle(Integer.parseInt(vehicleId6));
				}
			}
			if (vehicleId7 != null && vehicleId7 != "") {
				if (vehicleId7 != "0") {
					allVehicle = moduleService.getAllVehicle(Integer.parseInt(vehicleId7));
				}
			}*/
			

			
			if (allVehicle != null && !allVehicle.isEmpty()) {
				for (VehicleType city : allVehicle) {
					System.out.println("vehicleId = "+city.getId());
					citiOptionsList
							.append(city.getId());
				}

			}
			return citiOptionsList.toString();
		} catch (Exception er) {
			citiOptionsList.append("<option value=\"\">" + er.getMessage() + "</option>");
			er.printStackTrace();
			return citiOptionsList.toString();
		}
	}
	
	/*Added By Shahzad Hussain*/
	@ResponseBody
	@RequestMapping(value = "/getCitiesByStates", method = RequestMethod.POST)
	private String getCitieList(HttpServletRequest request,HttpServletResponse response) {
		StringBuilder citiOptionsList = new StringBuilder();
		try{
			String stateId = request.getParameter("state");
			if(stateId!=null && stateId!=""){
				if( stateId!="0"){
					allCities = moduleService.getAllCitiesForAState(Integer.parseInt(stateId));
				}
			}
			System.out.println("Stateid:- " + stateId);
			
			citiOptionsList.append("<option value=\"\">--Select City--</option>");
			if (allCities != null && !allCities.isEmpty()) {
				for (Cities city : allCities) {
					citiOptionsList.append("<option value=\"" + city.getCityId()+ "\">" + city.getCityName() + "</option>");
				}
				
			}
			return citiOptionsList.toString();
		}catch(Exception er){
			citiOptionsList.append("<option value=\"\">"+er.getMessage()+"</option>");
			er.printStackTrace();
			return citiOptionsList.toString();}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getCitiesByStateId", method = RequestMethod.GET)
	public  List<Cities>  getCitiesByStateId(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer stateId) {
		 try{
		  if( stateId!=0){
		   allCities = moduleService.getAllCitiesForAState(stateId);
		   }
		  if(allCities!=null && allCities.size()>0){
		 return allCities;
		  }else{
			  
			  allCities =new ArrayList<Cities>();
			/*  Cities dto=new Cities();
			  dto.setErrorCode("400");
			  dto.setErrorMessage("City Not Exist");
			  allCities.add(dto);*/
			  return allCities;
				  
		  }
           }catch(Exception er){
    	allCities =new ArrayList<Cities>();
		  Cities dto=new Cities();
		  dto.setErrorCode("400");
		  dto.setErrorMessage("City Not Exist");
		  allCities.add(dto);
		  return allCities;
    	}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getCitiesById", method = RequestMethod.GET)
	private Cities getCitiesById(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer cityId) {
		Cities dto=null;
		try{
    	dto = moduleService.getAllCityById(cityId);
		 if(dto!=null){
		return dto;
		 }else{
	 	dto=new Cities();
	 	dto.setCityName("");
	    return dto;
	   }
		 
    }catch(Exception er){
    	 	 er.printStackTrace();
    	 	dto=new Cities();
    	 	dto.setCityName("");
    	return dto;}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getHubNameById", method = RequestMethod.GET)
	private HubRegistration getHubNameById(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer HubId) {
		HubRegistration dto=null;
		try{
    	dto = hubRegistrationService.hubNameById(HubId);
		 if(dto!=null){
		return dto;
		 }else{
	 	dto=new HubRegistration();
	 	dto.setHubName("");
	    return dto;
	   }
		 
    }catch(Exception er){
    	 	 er.printStackTrace();
    	 	dto=new HubRegistration();
    		dto.setHubName("");
    	return dto;}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getCitiesByStateToEdit", method = RequestMethod.POST)
	private String getCitiesByStateToEdit(HttpServletRequest request,HttpServletResponse response) {
		StringBuilder citiOptionsList = new StringBuilder();
		try{
		String stateId = request.getParameter("state");
		if(stateId!=null && stateId!=""){
		 if( stateId!="0"){
		 allCities = moduleService.getAllCitiesForAState(Integer.parseInt(stateId));
		 }
		}
		System.out.println("Stateid:- " + stateId);
		
		citiOptionsList.append("<option value=\"\">--Select City--</option>");
	if (allCities != null && !allCities.isEmpty()) {
			for (Cities city : allCities) {
			citiOptionsList.append("<option value=\"" + city.getCityId()+ "\">" + city.getCityName() + "</option>");
			}

		}
		return citiOptionsList.toString();
    }catch(Exception er){
    	citiOptionsList.append("<option value=\"\">"+er.getMessage()+"</option>");
    	 er.printStackTrace();
    	return citiOptionsList.toString();}
	}

	@ResponseBody
	@RequestMapping(value = "/getClusterByCSC", method = RequestMethod.POST)
	private String getCluster(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("dto") HubRegistrationDto dto ,BindingResult result) {
		StringBuilder citiOptionsList = new StringBuilder();
		try{
			allHubter = hubRegistrationService.clusterList(dto);
 		 
		citiOptionsList.append("<option value=\"\">--Select Hub--</option>");
	if (allHubter != null && !allHubter.isEmpty()) {
			for (HubRegistration hub : allHubter) {
			citiOptionsList.append("<option value=\"" + hub.getHubId()+ "\">" + hub.getHubName() + "</option>");
			}

		}
		return citiOptionsList.toString();
		}catch(Exception er){
			citiOptionsList.append("<option value=\"\">"+er.getMessage()+"</option>");
			return citiOptionsList.toString();
		}
	}
	@ResponseBody
	@RequestMapping(value = "/getHubByCountryStateCity", method = RequestMethod.GET)
	private List<HubRegistration> getHubByCountryStateCity(HttpServletRequest request, HttpServletResponse response,@RequestParam Integer countryId,@RequestParam Integer stateId,@RequestParam Integer cityId) {
		 HubRegistrationDto dto=new HubRegistrationDto();
		try{
			 dto.setCityId(cityId);
			 dto.setCountryId(countryId);
			 dto.setStateId(stateId);
			  allHubter = hubRegistrationService.hubList(dto);
 		     if(allHubter!=null && allHubter.size()>0){
		      return allHubter;
 		     }else{
 			 allHubter=new ArrayList<HubRegistration>();
 			/* HubRegistration hub=new HubRegistration();	 
 		     hub.setErrorCode("400");
 		     hub.setErrorMessage("Hub not exist");
 		     allHubter.add(hub);*/ 
 			 return allHubter;  
 		  }
		}catch(Exception er){
         	 allHubter=new ArrayList<HubRegistration>();
 			/* HubRegistration hub=new HubRegistration();	 
 		     hub.setErrorCode("400");
 		     hub.setErrorMessage("Hub not exist");
 		     allHubter.add(hub);*/
 			 return allHubter; 
		}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getClusterByCSCToEdit", method = RequestMethod.POST)
	private String getClusterByCSCToEdit(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("dto") HubRegistrationDto dto ,BindingResult result) {
		StringBuilder citiOptionsList = new StringBuilder();
		try{
			allHubter = hubRegistrationService.clusterList(dto);
 		 
		citiOptionsList.append("<option value=\"\">--Select Hub--</option>");
	if (allHubter != null && !allHubter.isEmpty()) {
			for (HubRegistration hub : allHubter) {
			citiOptionsList.append("<option value=\"" + hub.getHubId()+ "\">"+hub.getHubName() + "</option>");
			}

		}
		return citiOptionsList.toString();
		}catch(Exception er){
			citiOptionsList.append("<option value=\"\">"+er.getMessage()+"</option>");
			return citiOptionsList.toString();
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getCountry", method = RequestMethod.GET)
	private  List<Countries> getCountry(HttpServletRequest request,HttpServletResponse response) {
		 try{ 
        allCountries=new ArrayList<Countries>();
		allCountries=moduleService.getAllCountriesList();
	   if (allCountries != null && allCountries.size()>0) {
        	return  allCountries;
		}else
		{
			allCountries=new ArrayList<Countries>();
			Countries ct=new Countries();
			ct.setErrorCode("400");
			ct.setErrorMessage("Country does not exist");
			allCountries.add(ct);
			return  allCountries;
		}
		
}catch(Exception er	){
	allCountries=new ArrayList<Countries>();
	Countries ct=new Countries();
	ct.setErrorCode("400");
	ct.setErrorMessage("Country does not exist");
	allCountries.add(ct);
	return  allCountries;
}
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/getCountryStates", method = RequestMethod.POST)
	private String getStatesList(HttpServletRequest request,HttpServletResponse response) {
		StringBuilder stateOptionsList = new StringBuilder();
		try{
		String countryId = request.getParameter("country");
		List<States> stateList = null;
		if(countryId!=null && !countryId.equals(0)){
			if(countryId!="" || countryId!="0"){
		stateList = moduleService.getStatesListForACountry(Integer.parseInt(countryId));
			}
		}
        stateOptionsList.append("<option value=\"\">-- Select State-- </option>");
		if (stateList != null && !stateList.isEmpty()) {
			for (States states : stateList) {
				stateOptionsList.append("<option value=\""+ states.getStateId() + "\">" + states.getStateName()+ "</option>");
			}
		}
		return stateOptionsList.toString();
}catch(Exception er	){
	stateOptionsList.append("<option value=\"\">"+er.getMessage()+"</option>");
	return stateOptionsList.toString();
	
}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getUserList", method = RequestMethod.GET)
	private String getUserList(HttpServletRequest request,HttpServletResponse response) {
		StringBuilder userOptionsList = new StringBuilder();
		try{
//		String countryId = request.getParameter("country");
		List<UserDetail> userList = null;
		userList = moduleService.getUserList();
		userOptionsList.append("<option value=\"0\">-- Select User-- </option>");
		if (userList != null && !userList.isEmpty()) {
			for (UserDetail user : userList) {
				userOptionsList.append("<option value="+ user.getId() + ">" + user.getFirstname()+ " " + user.getLastname() + "</option>");
			}
		}
		return userOptionsList.toString();
}catch(Exception er	){
	userOptionsList.append("<option value=\"\">"+er.getMessage()+"</option>");
	return userOptionsList.toString();
	
}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getStatesByCountryId", method = RequestMethod.GET)
	private  List<States> getStatesByCountryId(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer countryId) {
		 List<States> stateList = null;
		try{ 
		if(countryId!=null && !countryId.equals(0)){
			 
		stateList = moduleService.getStatesListForACountry(countryId);
			 
		}
        if (stateList != null && stateList.size()>0) {
        	return  stateList;
		}else
		{
			stateList=new ArrayList<States>();
		    States ds=new States();
			ds.setErrorCode("400");
			ds.setErrorMessage("State does not exist");
			stateList.add(ds);
			return  stateList;
		}
		
}catch(Exception er	){
	stateList=new ArrayList<States>();
	States ds=new States();
	ds.setErrorCode("400");
	ds.setErrorMessage("State does not exist");
	stateList.add(ds);
	return  stateList;
}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getCountryByStates", method = RequestMethod.GET)
	private  List<States> getCountryByStates(HttpServletRequest request,HttpServletResponse response,@RequestParam String countryId) {
		 List<States> stateList = null;
			
		try{
		if(countryId!=null && !countryId.equals(0)){
			if(countryId!="" || countryId!="0"){
		stateList = moduleService.getStatesListForACountry(Integer.parseInt(countryId));
			}
		}
         if(stateList!=null){
		return stateList;
         }else{
        	  stateList = new ArrayList<States>();
        	  States st=new States();
        	  st.setErrorCode("400");
        	  st.setErrorMessage("State Not Exist");
        	 return stateList;
         }
}catch(Exception er	){
	 stateList = new ArrayList<States>();
	  States st=new States();
	  st.setErrorCode("400");
	  st.setErrorMessage("State Not Exist");
	 return stateList; 
	
}
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getCountryStatesToEdit", method = RequestMethod.POST)
	private String getCountryStatesToEdit(HttpServletRequest request,HttpServletResponse response) {
		StringBuilder stateOptionsList = new StringBuilder();
		try{
		String countryId = request.getParameter("country");
		List<States> stateList = null;
		if(countryId!=null && !countryId.equals(0)){
			if(countryId!="" || countryId!="0"){
		stateList = moduleService.getStatesListForACountry(Integer.parseInt(countryId));
			}
		}
        stateOptionsList.append("<option value=\"\">-- Select State-- </option>");
		if (stateList != null && !stateList.isEmpty()) {
			for (States states : stateList) {
				stateOptionsList.append("<option value=\""+ states.getStateId() + "\">" + states.getStateName()+ "</option>");
			}
		}
		return stateOptionsList.toString();
}catch(Exception er	){
	stateOptionsList.append("<option value=\"\">"+er.getMessage()+"</option>");
	return stateOptionsList.toString();
	
}
	}

	@RequestMapping(value="/registerVehicle", method=RequestMethod.GET)
	public ModelAndView registrationsVehicle(HttpServletRequest request,HttpServletResponse response){
		List<Integer> yearList=MonthYearUtil.getYear();
		List<String> monthList=MonthYearUtil.getMonth();
		request.setAttribute("yearList", yearList);
		request.setAttribute("monthList", monthList);
		try{
			session=request.getSession();
			if(session.getAttribute("countriesList")!=null){
				allCountries=((List<Countries>)session.getAttribute("countriesList"));
			}
			if(allCountries==null){
				allCountries=moduleService.getAllCountriesList();
			}
			if(allCountries!=null){
				session.setAttribute("countriesList", allCountries);
				}
			
			
			if( session.getAttribute("orgList")==null){  
				
			    UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
			     Integer userId=0;
			     if(userDetail!=null){
				   userId=userDetail.getId();
			     } 
		  List<OrganizationMasterRegistration>  list=subsidiaryClientUserService.getOrgClient(userId);//
		  //organizationMasterService.getOrganizationMasterRegistration();
			  session.setAttribute("orgList", list);
			}
			if( session.getAttribute("orgList")!=null){
				 List<OrganizationMasterRegistration>  list=(List<OrganizationMasterRegistration>) session.getAttribute("orgList");
				 session.setAttribute("orgList", list);
			}
			}catch(Exception er){er.printStackTrace();}
		   return new ModelAndView("backEndVehicleRegistration");
	}
		
	@RequestMapping(value="/updateVehicle", method=RequestMethod.GET)
	public ModelAndView updateVehicle(HttpServletRequest request,HttpServletResponse response){
		List<Integer> yearList=MonthYearUtil.getYear();
		List<String> monthList=MonthYearUtil.getMonth();
		request.setAttribute("yearList", yearList);
		request.setAttribute("monthList", monthList);
		   return new ModelAndView("backEndUpdateVehicleRegistration");
	}
	
	
	 
	
	@ResponseBody 
	@RequestMapping(value="/backEndvehicleRegistrations", method=RequestMethod.POST)
	public ModelAndView backEndvehicleRegistrations(HttpServletRequest request,HttpServletResponse response,
			@RequestParam String ownerName,
			@RequestParam String ownerAddress,
			@RequestParam String ownerPhoneNumber,
			@RequestParam String vehicleType, 
			@RequestParam String vehicleNumber,
			@RequestParam String vehicleModel,
			@RequestParam String vehicleStatus,
		    @RequestParam(required=false) String accountHolderName, 
		    @RequestParam(required=false) String bankName, 		    
			@RequestParam(required=false) String panNumber,
			@RequestParam(required=false) String accountNumber,
			@RequestParam(required=false) String ifscCode, 
			@RequestParam String  kmReading,
			@RequestParam String fuelType,
			@RequestParam String driverMobile,
			@RequestParam String vehicleBody,
			@RequestParam Integer standDetails,
			@RequestParam Integer country,
			@RequestParam Integer state,
			@RequestParam Integer city,
			@RequestParam Integer hubId,
			@RequestParam Integer clusterId,
			@RequestParam(required=false) Integer assosiatedBy,			
			@RequestParam(required=false) Integer subOrgClient ,
			@RequestParam(required=false) Integer isBarcodeIssued,
			@RequestParam(required=false) String insuranceExpiryDate,
			@RequestParam Integer createdBy
			){
		try {
            List<Integer> yearList=MonthYearUtil.getYear();
			List<String> monthList=MonthYearUtil.getMonth();
			request.setAttribute("yearList", yearList);
			request.setAttribute("monthList", monthList);
				 Integer driverId= driverCollectionService.getDriverID(driverMobile);	
				 VehicleRegistration dto=new VehicleRegistration();
				 if(vehicleNumber!=null){
				 dto.setVehicleNumber(vehicleNumber.toUpperCase());
				 }
				 dto.setCountryId(country);
				 dto.setStateId(state);
				 dto.setCityId(city);
				 dto.setClientId(assosiatedBy);
				 dto.setClusterId(clusterId);
				 dto.setHubId(hubId);
				 dto.setStandId(standDetails);
				 dto.setSubclientId(subOrgClient);
				 
				 dto.setVehicleType(vehicleType);
				 dto.setVehicleModel(vehicleModel);
				 dto.setOwnerName(ownerName);
				 dto.setOwnerPhoneNumber(ownerPhoneNumber);
				 
				 dto.setAccountHolderName(accountHolderName);
				 dto.setBankName(bankName);
				 dto.setPanNumber(panNumber);
				 dto.setAccountNumber(accountNumber);
				 dto.setIfscCode(ifscCode);
				 if(isBarcodeIssued==null){
				 dto.setIsBarcodeIssued(0);
				 }else{
					 dto.setIsBarcodeIssued(isBarcodeIssued);
				 }
				 dto.setOwnerAddress(ownerAddress); 
				 dto.setVehicleStatus(vehicleStatus); 
				 dto.setKmReading(kmReading); 
				 dto.setFuelType(fuelType); 
				 dto.setDriverId(driverId);
				 dto.setVehicleBody(vehicleBody);
				 dto.setCreatedDate(new Date());
				 dto.setIsActive(1);
				 
				 if(insuranceExpiryDate!=null && !insuranceExpiryDate.equals("")){
					 dto.setInsuranceExpiryDate(new Date(insuranceExpiryDate));
				 }
				 
				 if(driverMobile!=null && !driverMobile.equals("")){
					 dto.setDriverMobiles(driverMobile);
					 }
				 HttpSession session=request.getSession();
				   UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
				   if(userDetail!=null){
					   if(createdBy.equals(0)){
						   dto.setAgentId(""+userDetail.getId());
							  dto.setCreatedBy(userDetail.getId());
					   }else{
						  dto.setAgentId(""+createdBy);
						  dto.setCreatedBy(createdBy);
					   }
						 }else{
							 dto.setAgentId(""+createdBy);
						 }
				   dto.setIsActive(1);
				  
				   if(driverId!=0){
				 VehicleRegistration saveVehicaleBack= registrationService.registrationVehicleByAPI(dto);
				 
                 request.setAttribute("saveVehicaleBack", saveVehicaleBack);
				 if(saveVehicaleBack!=null && saveVehicaleBack.getId()!=null){
					 DriverDeviceVehicleMapping driverDeviceVehicleMapping = registrationService.getDriverDeviceDetail(driverMobile);
					 if(driverDeviceVehicleMapping==null){
						 driverDeviceVehicleMapping=new DriverDeviceVehicleMapping();
					    }
					 	driverDeviceVehicleMapping.setIsValidDriver(true);
						driverDeviceVehicleMapping.setLoginStatus(1);
						driverDeviceVehicleMapping.setLastLoginTime(new Date());
						driverDeviceVehicleMapping.setDriverStatus(0); 
					    driverDeviceVehicleMapping.setDriverId(dto.getDriverId());
						driverDeviceVehicleMapping.setDriverPhoneNumber(driverMobile);//driverMobile
						driverDeviceVehicleMapping.setVehicleId(saveVehicaleBack.getId());
						 if(vehicleNumber!=null){
						 driverDeviceVehicleMapping.setVehicleNumber(vehicleNumber.toUpperCase());
						 }
						driverDeviceVehicleMapping.setVehicleType(vehicleType);
						driverDeviceVehicleMapping.setLoginStatus(0);	
						driverDeviceVehicleMapping.setDstatus(vehicleStatus);
						driverDeviceVehicleMapping.setSubClientId(subOrgClient);
						 dto.setIsBarcodeIssued(isBarcodeIssued);
					try {
						registrationService.saveOrUpdateDDVM(driverDeviceVehicleMapping);
					 } catch (Exception e) {
						e.printStackTrace();
					}
				   }}
				      return new ModelAndView("backEndVehicleRegistration");
		           } catch (Exception e) {
		        	  return new ModelAndView("backEndVehicleRegistration");
		           }
		
	}
 
	// vehicle  RG API
	@ResponseBody 
	@RequestMapping(value="/vehicleRegistrationApi", method=RequestMethod.POST)
	public VehicleRegistration vehicleRegistrationApi(HttpServletRequest request,HttpServletResponse response,
			@RequestParam String ownerName,
			@RequestParam String ownerAddress,
			@RequestParam String ownerPhoneNumber,
			@RequestParam String vehicleType, 
			@RequestParam String vehicleNumber,
			@RequestParam String vehicleModel,
			@RequestParam String vehicleStatus, 
			@RequestParam String  kmReading,
			@RequestParam String fuelType,
			@RequestParam String driverMobile,
			@RequestParam String vehicleBody,
			@RequestParam Integer standDetails,
			@RequestParam Integer country,
			@RequestParam Integer state,
			@RequestParam Integer city,
			@RequestParam Integer hubId,
			@RequestParam Integer clusterId,
			@RequestParam Integer userId,
			@RequestParam(required=false) Integer assosiatedBy,			
			@RequestParam(required=false) Integer subOrgClient ,
            @RequestParam(required=false) String accountHolderName, 
            @RequestParam(required=false) String bankName,             
			@RequestParam(required=false) String panNumber,
			@RequestParam(required=false) String accountNumber,
			@RequestParam(required=false) String ifscCode ,
			@RequestParam(required=false) Integer isBarcodeIssued,
			@RequestParam(required=false) String image_of_permit_vehicle ,
			@RequestParam(required=false) String image_of_rc ,
			@RequestParam(required=false) String image_of_insurance ,
			@RequestParam(required=false) String image_of_pollution 
			



			){
		 VehicleRegistration saveVehicaleBack=new  VehicleRegistration();
		try { 
			if(userId!=null){
			
			   Integer driverId= driverCollectionService.getDriverID(driverMobile);	
				 VehicleRegistration dto=new VehicleRegistration();
				 if(vehicleNumber!=null){
				 dto.setVehicleNumber(vehicleNumber.toUpperCase());
				 }
				 dto.setCountryId(country);
				 dto.setStateId(state);
				 dto.setCityId(city);
				 dto.setClientId(assosiatedBy);
				 dto.setClusterId(clusterId);
				 dto.setHubId(hubId);
				 dto.setStandId(standDetails);
				 dto.setSubclientId(subOrgClient);
				 

                 dto.setAccountHolderName(accountHolderName);
				 dto.setPanNumber(panNumber);
				 dto.setAccountNumber(accountNumber);
				 dto.setBankName(bankName);
				 dto.setIfscCode(ifscCode);
				 dto.setIsBarcodeIssued(isBarcodeIssued);
				 dto.setVehicleType(vehicleType);
				 dto.setVehicleModel(vehicleModel);
				 dto.setOwnerName(ownerName);
				 dto.setOwnerPhoneNumber(ownerPhoneNumber);
				 dto.setOwnerAddress(ownerAddress); 
				 dto.setVehicleStatus(vehicleStatus); 
				 dto.setKmReading(kmReading); 
				 dto.setFuelType(fuelType); 
				 dto.setDriverId(driverId);
				 dto.setVehicleBody(vehicleBody);
				 dto.setIsActive(1);
				 dto.setImage_of_insurance(image_of_insurance);
				 dto.setImage_of_permit_vehicle(image_of_permit_vehicle);
				 dto.setImage_of_pollution(image_of_pollution);
				 dto.setImage_of_rc(image_of_rc);
				 if(driverMobile!=null && !driverMobile.equals("")){
					 dto.setDriverMobiles(driverMobile);
				}
				 if(userId!=null){
					  dto.setAgentId(""+userId);
					  dto.setCreatedBy(userId);
					 } 
				 dto.setCreatedDate(new Date());
			  if(driverId!=0){
				   saveVehicaleBack= registrationService.registrationVehicleByAPI(dto);
				   saveVehicaleBack.setErrorCode("200");
			       saveVehicaleBack.setErrorMessage("Driver Vehicle register successfully!");
	        
				 if(saveVehicaleBack!=null && saveVehicaleBack.getId()!=null){
					 DriverDeviceVehicleMapping driverDeviceVehicleMapping = registrationService.getDriverDeviceDetail(driverMobile);
					 if(driverDeviceVehicleMapping==null){
						 driverDeviceVehicleMapping=new DriverDeviceVehicleMapping();
					    }
					 	driverDeviceVehicleMapping.setIsValidDriver(true);
						driverDeviceVehicleMapping.setLoginStatus(1);
						driverDeviceVehicleMapping.setLastLoginTime(new Date());
						driverDeviceVehicleMapping.setDriverStatus(0); 
					    driverDeviceVehicleMapping.setDriverId(dto.getDriverId());
						driverDeviceVehicleMapping.setDriverPhoneNumber(driverMobile);//driverMobile
						driverDeviceVehicleMapping.setVehicleId(saveVehicaleBack.getId());
						 if(vehicleNumber!=null){
						 driverDeviceVehicleMapping.setVehicleNumber(vehicleNumber.toUpperCase());
						 }
						driverDeviceVehicleMapping.setVehicleType(vehicleType);
						driverDeviceVehicleMapping.setLoginStatus(0);	
						driverDeviceVehicleMapping.setDstatus(vehicleStatus);
						driverDeviceVehicleMapping.setSubClientId(subOrgClient);
						
						
					try {
						registrationService.saveOrUpdateDDVM(driverDeviceVehicleMapping);
					 } catch (Exception e) {
						e.printStackTrace();
					}
					return saveVehicaleBack;
				   } }  if(saveVehicaleBack.getId()!=0){
						  saveVehicaleBack=new VehicleRegistration();
						   saveVehicaleBack.setErrorCode("400");
					       saveVehicaleBack.setErrorMessage("Driver Vehicle does not register !");
					       return saveVehicaleBack;
					  }else{
						  saveVehicaleBack.setErrorCode("200");
					       saveVehicaleBack.setErrorMessage("Driver Vehicle register successfully!");
			         return saveVehicaleBack;
					  }
			}else{
				saveVehicaleBack=new VehicleRegistration();
				   saveVehicaleBack.setErrorCode("400");
			       saveVehicaleBack.setErrorMessage("Driver Vehicle does not register !");
			       return saveVehicaleBack;
			}
		           } catch (Exception e) {
		        	   
		        	   saveVehicaleBack=new VehicleRegistration();
					   saveVehicaleBack.setErrorCode("400");
				       saveVehicaleBack.setErrorMessage("Driver Vehicle does not register. "+e.getMessage());
				       return saveVehicaleBack;
		           }
		 
		
	}
 
	
	
	//update vehicle API
	@ResponseBody 
	@RequestMapping(value="/updateVehicleAPI", method=RequestMethod.POST)
	public VehicleRegistration updateVehicleAPI(HttpServletRequest request,HttpServletResponse response,
			@RequestParam String ownerName,
			@RequestParam String ownerAddress,
			@RequestParam String ownerPhoneNumber, 
			@RequestParam String vehicleType,
			@RequestParam String vehicleNumber,
			@RequestParam String vehicleModel,
			@RequestParam String vehicleStatus,
			@RequestParam String  kmReading,			
			@RequestParam String fuelType,
			@RequestParam String driverMobile,
			@RequestParam Integer vehicleId,
			@RequestParam String vehicleBody,
			@RequestParam Integer standDetails,
			@RequestParam Integer country,
			@RequestParam Integer state,
			@RequestParam Integer city,
			@RequestParam Integer hubId,
			@RequestParam Integer clusterId,
			@RequestParam(required=false) String accountHolderName, 
			@RequestParam(required=false) String bankName,
			@RequestParam(required=false) String panNumber,
			@RequestParam(required=false) String accountNumber,
			@RequestParam(required=false) String ifscCode,
			@RequestParam(required=false) Integer assosiatedBy,			
			@RequestParam(required=false) Integer subOrgClient ,
			@RequestParam(required=false) String image_of_permit_vehicle ,
			@RequestParam(required=false) String image_of_rc ,
			@RequestParam(required=false) String image_of_insurance ,
			@RequestParam(required=false) String image_of_pollution,
			@RequestParam Integer userId,
			@RequestParam(required=false) Integer isBarcodeIssued
			){
		 VehicleRegistration saveVehicaleBack=null;
		try {
			if(userId!=null){
			List<Integer> yearList=MonthYearUtil.getYear();
			List<String> monthList=MonthYearUtil.getMonth();
			request.setAttribute("yearList", yearList);
			request.setAttribute("monthList", monthList);
				 Integer driverId= driverCollectionService.getDriverID(driverMobile);	
				 VehicleRegistration dto=new VehicleRegistration();
				 dto.setId(vehicleId);
				if(vehicleNumber!=null ){
				 dto.setVehicleNumber(vehicleNumber.toUpperCase());
				}
				 dto.setCountryId(country);
				 dto.setStateId(state);
				 dto.setCityId(city);
				 dto.setClientId(assosiatedBy);
				 dto.setClusterId(clusterId);
				 dto.setHubId(hubId);
				 dto.setStandId(standDetails);
				 dto.setSubclientId(subOrgClient);
				 dto.setVehicleType(vehicleType);
				 dto.setVehicleModel(vehicleModel.replace(" ", ","));
				 dto.setOwnerName(ownerName);
				 dto.setOwnerPhoneNumber(ownerPhoneNumber);
				 dto.setAccountHolderName(accountHolderName);
				 dto.setPanNumber(panNumber);
				 dto.setAccountNumber(accountNumber);
				 dto.setBankName(bankName);
				 dto.setIfscCode(ifscCode);
				 dto.setOwnerAddress(ownerAddress); 
				 dto.setVehicleStatus(vehicleStatus); 
				 dto.setKmReading(kmReading); 
				 dto.setFuelType(fuelType); 
				 dto.setVehicleBody(vehicleBody);
				 dto.setDriverId(driverId);
				 dto.setIsBarcodeIssued(isBarcodeIssued);
				 dto.setIsActive(1);
				 dto.setImage_of_insurance(image_of_insurance);
				 dto.setImage_of_permit_vehicle(image_of_permit_vehicle);
				 dto.setImage_of_pollution(image_of_pollution);
				 dto.setImage_of_rc(image_of_rc);
				 dto.setModifiedBy(userId);
				 
				 if(driverMobile!=null && !driverMobile.equals("")){
					 dto.setDriverMobiles(driverMobile);
					}
				   dto.setAgentId(""+userId);
				  
						 
			    saveVehicaleBack= registrationService.updateVehicleByAPI(dto);
				   saveVehicaleBack.setErrorCode("200");
			       saveVehicaleBack.setErrorMessage("Vehicle updated successfully!");
                 request.setAttribute("saveVehicaleBack", saveVehicaleBack);
				 if(saveVehicaleBack!=null && saveVehicaleBack.getId()!=null){
					 DriverDeviceVehicleMapping driverDeviceVehicleMapping = registrationService.getDriverDeviceDetail(driverMobile);
					 if(driverDeviceVehicleMapping==null){
						 driverDeviceVehicleMapping=new DriverDeviceVehicleMapping();
					    }
					 	driverDeviceVehicleMapping.setIsValidDriver(true);
						driverDeviceVehicleMapping.setLoginStatus(1);
						driverDeviceVehicleMapping.setLastLoginTime(new Date());
						driverDeviceVehicleMapping.setDriverStatus(0); 
					    driverDeviceVehicleMapping.setDriverId(dto.getDriverId());
						driverDeviceVehicleMapping.setDriverPhoneNumber(driverMobile);//driverMobile
						driverDeviceVehicleMapping.setVehicleId(saveVehicaleBack.getId());
						 if(vehicleNumber!=null){
							 driverDeviceVehicleMapping.setVehicleNumber(vehicleNumber.toUpperCase());
						}
						driverDeviceVehicleMapping.setVehicleType(vehicleType);
						driverDeviceVehicleMapping.setLoginStatus(0);	
						driverDeviceVehicleMapping.setDstatus(vehicleStatus);
						driverDeviceVehicleMapping.setSubClientId(subOrgClient);
						
					try { 
						registrationService.saveOrUpdateDDVM(driverDeviceVehicleMapping);
					 } catch (Exception e) {
						e.printStackTrace();
					}
				   }
				      return saveVehicaleBack;
			}else{
				  saveVehicaleBack=new VehicleRegistration();
	        	   saveVehicaleBack.setErrorCode("400");
			       saveVehicaleBack.setErrorMessage("Vehicle is not updated successfully!");
	         return saveVehicaleBack;
			}
		           } catch (Exception e) {
		        	   saveVehicaleBack=new VehicleRegistration();
		        	   saveVehicaleBack.setErrorCode("400");
				       saveVehicaleBack.setErrorMessage("Vehicle is not updated successfully!");
		         return saveVehicaleBack;
		        	 
		           }
		
	}
	
	
	@ResponseBody 
	@RequestMapping(value="/updateVehicleRegistrations", method=RequestMethod.POST)
	public ModelAndView updateVehicleRegistrations(HttpServletRequest request,HttpServletResponse response,
			@RequestParam String ownerName,
			@RequestParam String ownerAddress,
			@RequestParam String ownerPhoneNumber, 
			@RequestParam String vehicleType,
			@RequestParam String vehicleNumber,
			@RequestParam String vehicleModel,
			@RequestParam String vehicleStatus,
			@RequestParam String  kmReading,			
			@RequestParam String fuelType,
			@RequestParam String driverMobile,
			@RequestParam Integer vehicleId,
			@RequestParam String vehicleBody,
			@RequestParam Integer standDetails,
			@RequestParam Integer country,
			@RequestParam Integer state,
			@RequestParam Integer city,
			@RequestParam Integer hubId,
			@RequestParam Integer clusterId,
			@RequestParam(required=false) Integer assosiatedBy,			
			@RequestParam(required=false) Integer subOrgClient,
            @RequestParam(required=false) String accountHolderName, 
			@RequestParam(required=false) String panNumber,
			@RequestParam(required=false) String bankName,			
			@RequestParam(required=false) String accountNumber,
			@RequestParam(required=false) String ifscCode,
			@RequestParam(required=false) Integer isBarcodeIssued,
			@RequestParam(required=false) String insuranceExpiryDate
			){
		try {
			List<Integer> yearList=MonthYearUtil.getYear();
			List<String> monthList=MonthYearUtil.getMonth();
			request.setAttribute("yearList", yearList);
			request.setAttribute("monthList", monthList);
				 Integer driverId= driverCollectionService.getDriverID(driverMobile);	
				 VehicleRegistration dto=new VehicleRegistration();
				 dto.setId(vehicleId);
				if(vehicleNumber!=null ){
				 dto.setVehicleNumber(vehicleNumber.toUpperCase());
				}
				 dto.setCountryId(country);
				 dto.setStateId(state);
				 dto.setCityId(city);
				 dto.setClientId(assosiatedBy);
				 dto.setClusterId(clusterId);
				 dto.setHubId(hubId);
				 dto.setStandId(standDetails);
				 dto.setSubclientId(subOrgClient);
				 dto.setVehicleType(vehicleType);
				 dto.setVehicleModel(vehicleModel.replace(" ", ","));
				 dto.setOwnerName(ownerName);
				 dto.setOwnerPhoneNumber(ownerPhoneNumber);
				 dto.setOwnerAddress(ownerAddress); 
				 dto.setVehicleStatus(vehicleStatus); 
				 dto.setKmReading(kmReading); 
				 dto.setFuelType(fuelType); 
				 dto.setVehicleBody(vehicleBody);
				 dto.setDriverId(driverId);
                 dto.setAccountHolderName(accountHolderName);
                 dto.setBankName(bankName);
				 dto.setPanNumber(panNumber);
				 dto.setAccountNumber(accountNumber);
				 dto.setIfscCode(ifscCode);
				 dto.setIsBarcodeIssued(isBarcodeIssued);
				if (insuranceExpiryDate != null && !insuranceExpiryDate.equals("")) {
					dto.setInsuranceExpiryDate(new Date(insuranceExpiryDate));
				}
				 dto.setIsActive(1);
				 if(driverMobile!=null && !driverMobile.equals("")){
					 dto.setDriverMobiles(driverMobile);
					}
				 HttpSession session=request.getSession();
				   UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
				   if(userDetail!=null){
//						  dto.setAgentId(""+userDetail.getId());
						  dto.setModifiedBy(userDetail.getId());
						 }else{
//							 dto.setAgentId("0");
						 }
				 VehicleRegistration saveVehicaleBack= registrationService.updateVehicleByAPI(dto);
				 
                 request.setAttribute("saveVehicaleBack", saveVehicaleBack);
				 if(saveVehicaleBack!=null && saveVehicaleBack.getId()!=null){
					 DriverDeviceVehicleMapping driverDeviceVehicleMapping = registrationService.getDriverDeviceDetail(driverMobile);
					 if(driverDeviceVehicleMapping==null){
						 driverDeviceVehicleMapping=new DriverDeviceVehicleMapping();
					    }
					 	driverDeviceVehicleMapping.setIsValidDriver(true);
						driverDeviceVehicleMapping.setLoginStatus(1);
						driverDeviceVehicleMapping.setLastLoginTime(new Date());
						driverDeviceVehicleMapping.setDriverStatus(0); 
					    driverDeviceVehicleMapping.setDriverId(dto.getDriverId());
						driverDeviceVehicleMapping.setDriverPhoneNumber(driverMobile);//driverMobile
						driverDeviceVehicleMapping.setVehicleId(saveVehicaleBack.getId());
						 if(vehicleNumber!=null){
							 driverDeviceVehicleMapping.setVehicleNumber(vehicleNumber.toUpperCase());
						}
						driverDeviceVehicleMapping.setVehicleType(vehicleType);
						driverDeviceVehicleMapping.setLoginStatus(0);	
						driverDeviceVehicleMapping.setDstatus(vehicleStatus);
						driverDeviceVehicleMapping.setSubClientId(subOrgClient);
						
					try { 
						registrationService.saveOrUpdateDDVM(driverDeviceVehicleMapping);
					 } catch (Exception e) {
						e.printStackTrace();
					}
				   }
				      return new ModelAndView("backEndUpdateVehicleRegistration");
		           } catch (Exception e) {
		        	  return new ModelAndView("backEndUpdateVehicleRegistration");
		           }
		
	}
	
	@ResponseBody
	@RequestMapping(value="/validateDriverMobile", method=RequestMethod.GET)
	private String validateDriverMobile(@RequestParam("mobile") String mobile ){		
		DriverRegistration driver=null;
		try{
		  driver=registrationService.getDriverDetail(mobile);
		  if(driver!=null){
		  return driver.getPhoneNumber() ;
		  }else{
			  return "";
		  }
	        }catch(Exception e){
	        	e.printStackTrace();	        	
	        	return "";
	    }		
	}
	
	@ResponseBody
	@RequestMapping(value = "/validateVehicleNumber", method = RequestMethod.GET)
	private VehicleRegistration validateVehicleNumber(@RequestParam("vehicleNumber") String vehicleNumber) {
		VehicleRegistration driverVehicle = new VehicleRegistration();
		try {
			driverVehicle = registrationService.getVehicleDetailsByVehicleNumber(vehicleNumber);
			SubsidiaryClientOfOrg subClientDto = null;
			StandRegistration sndlist = null;
			ClusterRegistration clusertdtoss = null;
			HubRegistration hubdto = null;
			Cities citydto = null;
			OrganizationMasterRegistration clientDto = null;
			Countries countryDto = null;
			States stateDto = null;
			if (driverVehicle != null) {
				if (driverVehicle.getCountryId() != null && !driverVehicle.getCountryId().equals("")) {
					countryDto = moduleService.getAllCountryById(new Integer(driverVehicle.getCountryId()));
				}
				if (driverVehicle.getStateId() != null && !driverVehicle.getStateId().equals("")) {
					stateDto = moduleService.getAllStateById(new Integer(driverVehicle.getStateId()));
				}

				if (countryDto != null) {
					driverVehicle.setCountry(countryDto.getValue());
				}
				if (stateDto != null) {
					driverVehicle.setState(stateDto.getStateName());
				}

				if (driverVehicle.getClientId() != null) {
					clientDto = organizationMasterService.getClientNameById(driverVehicle.getClientId());
				}
				if (driverVehicle.getSubclientId() != null && !driverVehicle.getSubclientId().equals("")) {
					subClientDto = subsaidiaryOrgRegistrationService
							.getListSubsidairyClientOrgByid(driverVehicle.getSubclientId());
				}
				if (driverVehicle.getStandId() != null && !driverVehicle.getStandId().equals("")) {
					sndlist = standRegistrationService.getStandByID(driverVehicle.getStandId());
				}
				if (driverVehicle.getClusterId() != null && !driverVehicle.getClusterId().equals("")) {

					clusertdtoss = clusterRegistrationService.getClusterById(driverVehicle.getClusterId());
				}
				if (driverVehicle.getCityId() != null && !driverVehicle.getCityId().equals("")) {
					citydto = moduleService.getAllCityById(driverVehicle.getCityId());
				}

				if (driverVehicle.getHubId() != null && !driverVehicle.getHubId().equals("0")) {
					hubdto = hubRegistrationService.hubNameById(driverVehicle.getHubId());
				}

				if (clientDto != null) {
					driverVehicle.setAssosiatedBy(clientDto.getName());
				}
				if (hubdto != null) {
					driverVehicle.setHubName(hubdto.getHubName());
				}
				if (subClientDto != null) {
					driverVehicle.setSubOrgClient(subClientDto.getSubName());
				}
				if (clusertdtoss != null) {
					driverVehicle.setCluster(clusertdtoss.getClusterName());
				}
				if (sndlist != null) {
					driverVehicle.setStandDetails(sndlist.getStandName());
				}
				if (citydto != null) {
					driverVehicle.setCity(citydto.getCityName());
				}
				return driverVehicle;
			} else {
				return new VehicleRegistration();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new VehicleRegistration();
		}

	}
	
	@RequestMapping(value="/uploadDriverimage", method=RequestMethod.GET)
	public ModelAndView uploadDriverimage(HttpServletRequest request,HttpServletResponse response){
		 UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
		 Integer userId=0;
		   if(userDetail!=null){
			   userId=userDetail.getId();
		} 
	  List<OrganizationMasterRegistration>  list=subsidiaryClientUserService.getOrgClient(userId);//organizationMasterService.getOrganizationMasterRegistration();
	 // List<OrganizationMasterRegistration>  list= organizationMasterService.getOrganizationMasterRegistration();
		 request.setAttribute("orgList", list);
		   return new ModelAndView("backEndUploadDriverImage");
	}
	
	
	
	@RequestMapping(value = "/uploadDriverImage", method = RequestMethod.POST)
	public @ResponseBody String uploadDriverImage(MultipartHttpServletRequest request, HttpServletResponse response) {                 
	   	 UploadDocumentModel documentModel = new UploadDocumentModel();
		 Iterator<String> itr = request.getFileNames();	 
	     MultipartFile files = request.getFile(itr.next());
	        if (!files.isEmpty()) {
	            try {
	            	String name = files.getOriginalFilename();
	            	AWSS3Uploader.uploadDriverFile(name,files.getContentType(),files.getSize(), files.getInputStream(), documentModel);
	                byte[] bytes = files.getBytes();
	                BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File(name)));
	                 stream.write(bytes);
	                 stream.close();
	                documentModel.setUploadStatus("SUCCESS");	                
	                return  documentModel.getUploadURL();
	            } catch (Exception e) {
	            	documentModel.setUploadStatus("Failed");
	            	documentModel.setErrorMessage(e.getMessage());
	               return documentModel.getUploadStatus();
	               }
	            }else{
	            	return documentModel.getUploadStatus();
	            }
	  }
	
	@ResponseBody 
	@RequestMapping(value = "/vehicleTypeApi", method = RequestMethod.GET)
	public List<VehicleType> vehicleTypeApi(HttpServletRequest request, HttpServletResponse response) { 
	 	 
		return vehicleTypeService.vehicleTypeList();
	   	 
	  }
	
	@RequestMapping(value = "/uploadVehicleImage", method = RequestMethod.POST)
	public @ResponseBody String uploadVehicleImage(MultipartHttpServletRequest request, HttpServletResponse response) {                 
	   	 UploadDocumentModel documentModel = new UploadDocumentModel();
		 Iterator<String> itr = request.getFileNames();	 
	     MultipartFile files = request.getFile(itr.next());
	        if (!files.isEmpty()) {
	            try {
	            	String name = files.getOriginalFilename();
	            	AWSS3Uploader.uploadDriverFile(name,files.getContentType(),files.getSize(), files.getInputStream(), documentModel);
	                byte[] bytes = files.getBytes();
	                BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(new File(name)));
	               stream.write(bytes);
	                 stream.close();
	                documentModel.setUploadStatus("SUCCESS");	                
	                return  documentModel.getUploadURL();
	            } catch (Exception e) {
	            	documentModel.setUploadStatus("Failed");
	            	documentModel.setErrorMessage(e.getMessage());
	               return documentModel.getUploadStatus();
	               }
	            }else{
	            	return documentModel.getUploadStatus();
	            }
	  }
	
	
	@RequestMapping(value = "/uploadDriverDocuments", method = RequestMethod.POST)
	   public @ResponseBody String uploadDriverDocuments(MultipartHttpServletRequest request, HttpServletResponse response) {                 
	     Iterator<String> itr =  request.getFileNames();
	     MultipartFile mpf = request.getFile(itr.next());
	     System.out.println(mpf.getOriginalFilename() +" uploaded!");
	     String uploadPath =uploadImage(mpf);
	     return uploadPath;
	 
	  }
	public static String uploadImage(MultipartFile file){
		String imageUrl="";
		try {
			String fileName = file.getName();
			String fileContentType=file.getContentType();
	        long size=file.getSize();
			InputStream stream = file.getInputStream();
			UploadDocumentModel documentModel = new UploadDocumentModel();
			AWSS3Uploader.uploadDriverFile(fileName,  fileContentType, size,  stream) ;
	    	documentModel = AWSS3Uploader.uploadFile(fileName, stream);
	    	imageUrl = documentModel.getUploadURL();
	    	return imageUrl;
		} catch (IOException e) {
		 e.printStackTrace();
		} 
	 
    	return imageUrl;
	}

	 
	
	
}
