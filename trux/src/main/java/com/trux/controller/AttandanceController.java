package com.trux.controller;
 
import java.util.ArrayList;
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
import org.springframework.web.servlet.ModelAndView;

import com.trux.model.DriverAttandance;
import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.DriverRegistration;
import com.trux.model.OrganizationMasterRegistration;
import com.trux.model.SubsidiaryClientOfOrg;
import com.trux.model.SubsidiaryClientUser;
import com.trux.model.UserDetail;
import com.trux.service.DriverAttandanceService;
import com.trux.service.OrganizationMasterService;
import com.trux.service.RegistrationService;
import com.trux.service.SubsidiaryClientUserService;

@Controller
@RequestMapping(value = "/attandance")
public class AttandanceController {

	@Autowired
	SubsidiaryClientUserService  subsidiaryClientUserService;
	@Autowired
	private OrganizationMasterService organizationMasterService;
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	DriverAttandanceService driverAttandanceService;
	@ResponseBody
	@RequestMapping(value = "/modules", method = RequestMethod.GET)
	public ModelAndView modules(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session=request.getSession();
		   UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
		 Integer userId=0;
		   if(userDetail!=null){
			   userId=userDetail.getId();
		} 
	  List<OrganizationMasterRegistration>  list=subsidiaryClientUserService.getOrgClient(userId);//organizationMasterService.getOrganizationMasterRegistration();
		 request.setAttribute("orgList", list);
	return new ModelAndView("driverAttandance");
	}
	
	@ResponseBody
	@RequestMapping(value = "/organizationList", method = RequestMethod.GET)
	public List<OrganizationMasterRegistration>  organization(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer userId) {
	List<OrganizationMasterRegistration>  Orglist=null;
	  Orglist=subsidiaryClientUserService.getOrgClient(userId);// organizationMasterService.getOrganizationMasterRegistration();
	  if(Orglist!=null){
	   return  Orglist;
	  }else{
		   Orglist=new ArrayList<OrganizationMasterRegistration>();
		  /* OrganizationMasterRegistration dto=new OrganizationMasterRegistration();
		   dto.setErrorMessage("No any records avalable");
		   Orglist.add(dto);*/
		   return  Orglist;
	  }
	  
	}
	
	 
	@ResponseBody
	@RequestMapping(value = "/getDriverDetailsList", method = RequestMethod.POST)
	private List<DriverRegistration>  getDriverDetailsList(HttpServletRequest request,HttpServletResponse response,	@RequestParam String bySubClient) {
	 
 	List<DriverRegistration> orglist=new ArrayList<DriverRegistration>();
		try{
		 	if(bySubClient!=null)
		   orglist= registrationService.getRegisteredDriverList(""+bySubClient);
		   if(orglist!=null){
			   return orglist;
		   }else{
			   orglist=new ArrayList<DriverRegistration>();
			   DriverRegistration dto=new DriverRegistration();
			   dto.setErrorMesaage("No any records ");
			   orglist.add(dto);
			   return orglist;
		   }
		}catch(Exception er){er.printStackTrace();
		 orglist=new ArrayList<DriverRegistration>();
		 DriverRegistration dto=new DriverRegistration();
		 dto.setErrorMesaage("No any records ");
		 orglist.add(dto);
		 return orglist;
		 }
	}
	
	@ResponseBody
	@RequestMapping(value = "/getDriverDetails", method = RequestMethod.POST)
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
				driverOptionsList.append("<option value=\"" + dr.getDriverId()+ ","+vehicleNumber+"\">" + dr.getDriverName() +" " +dr.getDriverPhoneNumber() +" "+vehicleNumber+"</option>");
				//driverOptionsList.append("<option value=\"" + dr.getId()+ "\">" + dr.getDriverName() +" " +dr.getPhoneNumber() +"</option>");
			}
		}
		return driverOptionsList.toString();
	}
	
	@ResponseBody
	@RequestMapping(value = "/getDriverDetailsBySubsidiary", method = RequestMethod.POST)
	private String getDriverDetailsBySubsidiary(HttpServletRequest request,HttpServletResponse response) {

		String organizationId = request.getParameter("organizationId");
		 List<DriverRegistration> list= registrationService.getRegisteredDriverListBySubOrg(organizationId);
 
		System.out.println("organizationId:- " + organizationId);
		StringBuilder driverOptionsList = new StringBuilder();
		driverOptionsList.append("<option value=\"\">--Select Driver--</option>");
	if (list != null && !list.isEmpty()) {
			for (DriverRegistration dr : list) {
				driverOptionsList.append("<option value=\"" + dr.getId()+ "\">" + dr.getDriverName() +" " +dr.getPhoneNumber() +"</option>");
			}
		}
		return driverOptionsList.toString();
	}
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping(value = "/atDrivers", method = RequestMethod.POST)
	public ModelAndView drivers(HttpServletRequest request,HttpServletResponse response,
			@RequestParam String driverName,
			@RequestParam String orgName,
			@RequestParam Integer clientSubId,
			@RequestParam String attandanceDate,
			@RequestParam String checkin,
			@RequestParam String checkout,
			@RequestParam String opening,
			@RequestParam String closing,
			@RequestParam String tolltax,
			@RequestParam String noofboxes) {
		HttpSession session=request.getSession();
		 UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
	     Integer userId=0;
	     if(userDetail!=null){
		   userId=userDetail.getId();
	     } 
  List<OrganizationMasterRegistration>  list=subsidiaryClientUserService.getOrgClient(userId);

	//	 List<OrganizationMasterRegistration>  list= organizationMasterService.getOrganizationMasterRegistration();
		 request.setAttribute("orgList", list);
		 try{
		 String[] attandanceDateArray=attandanceDate.split(",");
		 String[] checkInArray=checkin.split(",");
		 String[] checkOutArray=checkout.split(",");
		 
		 String[] openingArray=opening.split(",");
		 String[] closingArray=closing.split(",");
		 String[] tolltaxArray=tolltax.split(",");
		 String[] noofBoxesArray=noofboxes.split(",");
		 String[] driverVehicle=driverName.split(",");
		 int orgId=0;
		 int driverId=0;
		 if(orgName!=null && !orgName.equals("")){
			 orgId=Integer.parseInt(orgName); 
		 }
		 String vehicleNumber="";
		 if(driverVehicle!=null && driverVehicle.length<=2){
			 if(driverVehicle[0]!=null && !driverVehicle[0].equals("")){
				 try{
				 if(driverVehicle[0]!=null && !driverVehicle[0].equals("0")){
				 driverId=Integer.parseInt(driverVehicle[0]); 
				 }
				 if(driverVehicle[1]!=null && !driverVehicle[1].equals("N/A")){
					 vehicleNumber =driverVehicle[1]; 
					 }
				 }catch(Exception er){er.printStackTrace();}
			 }  
		 }
		/* if(driverName!=null && !driverName.equals("")){
			 driverId=Integer.parseInt(driverName); 
		 }*/
		// UserDetail userDetail=null;
		   session=request.getSession();
         if(session.getAttribute("userDetail")!=null){
         	userDetail =(UserDetail)session.getAttribute("userDetail");
         	}
		 List<DriverAttandance> driverAttandnceList=new ArrayList<DriverAttandance>();
		 if(attandanceDateArray!=null && attandanceDateArray.length>0){
		 for(int i=0;i<attandanceDateArray.length;i++){
			 DriverAttandance dto=new DriverAttandance();			 
			 dto.setCompanyId(orgId);
			 dto.setDriverId(driverId);
			 dto.setClientSubId(clientSubId);
			 dto.setVehicleNumber(vehicleNumber);
			 dto.setAttendanceDate(new Date(attandanceDateArray[i]));
		  try{ 
			 try{
			  dto.setPunchIn(new Date(checkInArray[i]));
			 }catch(Exception er){}
		     try{
			  dto.setPunchOut(new Date(checkOutArray[i]));
			 }catch(Exception er){}
			 try{
			 if(userDetail.getId()!=null)
			 dto.setCreatedBy(userDetail.getId());
			}catch(Exception er){} 
			 try{
			 Integer openingKm=new Integer(openingArray[i]);
			 dto.setOpeningKilometer(openingKm);
			}catch(Exception er){}
			try{
			 Integer closingKm=new Integer(closingArray[i]);
			 dto.setClosingKilometer(closingKm);
			 }catch(Exception er){}
			
			 
			 try{
				 Integer tollTaxs=new Integer(tolltaxArray[i]);
				 dto.setTolltax(tollTaxs);
			 }catch(Exception er){}
			 try{
				 Integer noofbox=new Integer(noofBoxesArray[i]);
				 dto.setNoofboxes(noofbox); 
			 }catch(Exception er){}
			
			 }catch(Exception er){er.printStackTrace();}
			 driverAttandnceList.add(dto);
		 }
		 }		 
		 List<DriverAttandance> savelists= driverAttandanceService.saveAttandanceOfDriver(driverAttandnceList);
		 if(savelists==null){
			 savelists=new ArrayList<DriverAttandance>();
			 DriverAttandance dto=new DriverAttandance();
			 dto.setStatusMessage("Your Request is not register.");
			 savelists.add(dto);
		 } 
		 request.setAttribute("savelists", savelists); 
		System.out.println(orgName +" "+ driverName+" "+attandanceDate +"" +checkin +""+checkout);
		 }catch(Exception er){
			 
		 }
		return new ModelAndView("driverAttandance");
	}

	
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping(value = "/attandanceDrivers", method = RequestMethod.GET)
	public List<DriverAttandance> attandanceDrivers(HttpServletRequest request,HttpServletResponse response,
			@RequestParam String driverName,
			@RequestParam String orgName,
			@RequestParam Integer clientSubId,
			@RequestParam String attandanceDate,
			@RequestParam String checkin,
			@RequestParam String checkout,
			@RequestParam String openingkm,
			@RequestParam String closingkm,
			@RequestParam String tolltax,
			@RequestParam int userId,
			@RequestParam String noofboxes ) { 
		 List<DriverAttandance> savelists=new ArrayList<DriverAttandance>();
		// if(driverName)
			 
	  List<OrganizationMasterRegistration>  list=subsidiaryClientUserService.getOrgClient(userId);

		// List<OrganizationMasterRegistration>  list= organizationMasterService.getOrganizationMasterRegistration();
		 request.setAttribute("orgList", list);
		 try{
		 String[] attandanceDateArray=attandanceDate.split(",");
		 String[] checkInArray=checkin.split(",");
		 String[] checkOutArray=checkout.split(",");
		 
		 String[] openingArray=openingkm.split(",");
		 String[] closingArray=closingkm.split(",");
		 String[] tolltaxArray=tolltax.split(",");
		  String[] noofBoxesArray=noofboxes.split(","); 
		  String[] driverVehicle=driverName.split(","); 
		 int orgId=0;
		 int driverId=0;
		 if(orgName!=null && !orgName.equals("")){
			 orgId=Integer.parseInt(orgName); 
		 }
		 String vehicleNumber="";
		 if(driverVehicle!=null && driverVehicle.length<=2){
			 if(driverVehicle[0]!=null && !driverVehicle[0].equals("")){
				 try{
				 if(driverVehicle[0]!=null && !driverVehicle[0].equals("0")){
				 driverId=Integer.parseInt(driverVehicle[0]); 
				 }
				 if(driverVehicle[1]!=null && !driverVehicle[1].equals("N/A")){
					 vehicleNumber =driverVehicle[1]; 
					 }
				 }catch(Exception er){er.printStackTrace();}
			 }  
		 }
		/* if(driverName!=null && !driverName.equals("")){
			 driverId=Integer.parseInt(driverName); 
		 }
		 */
		 List<DriverAttandance> driverAttandnceList=new ArrayList<DriverAttandance>();
		 if(attandanceDateArray!=null && attandanceDateArray.length>0){
		 for(int i=0;i<attandanceDateArray.length;i++){
			 DriverAttandance dto=new DriverAttandance();			 
			 dto.setCompanyId(orgId);
			 dto.setDriverId(driverId);
			 dto.setVehicleNumber(vehicleNumber);
			 dto.setClientSubId(clientSubId);
			 dto.setAttendanceDate(new Date(attandanceDateArray[i]));
		  try{ 
			  String datePuching=checkInArray[i];
			  if(datePuching!=null && datePuching!=""){
			 dto.setPunchIn(new Date(datePuching));
			  }
			  String datePunchout=checkOutArray[i];
			  if(datePunchout!=null && datePunchout!=""){
			  dto.setPunchOut(new Date(datePunchout));
			  }
			 Integer openingKm=new Integer(openingArray[i]);
			 Integer closingKm=new Integer(closingArray[i]);
			 Integer tollTaxs=new Integer(tolltaxArray[i]);
			 dto.setOpeningKilometer(openingKm);
			 dto.setClosingKilometer(closingKm);
			 dto.setTolltax(tollTaxs);
			 dto.setCreatedBy(userId);
			  Integer noofbox=new Integer(noofBoxesArray[i]); 
			 dto.setNoofboxes(noofbox); 
			 }catch(Exception er){er.printStackTrace();}
			 driverAttandnceList.add(dto);
		 }
		 }		 
		savelists= driverAttandanceService.saveAttandanceOfDriver(driverAttandnceList);
	 	System.out.println(orgName +" "+ driverName+" "+attandanceDate +"" +checkin +""+checkout);
		 }catch(Exception er){
			  DriverAttandance dto=new DriverAttandance();
				 dto.setStatusMessage("Your Request is not register. " +er.getMessage());
				 savelists.add(dto);
				 return savelists;
		 }
		 if(savelists==null){
			  DriverAttandance dto=new DriverAttandance();
			 dto.setStatusMessage("Your Request is not register.");
			 savelists.add(dto);
			 return savelists;
		 }else{
		return savelists;
		 }
	}

	
	@ResponseBody
	@RequestMapping(value = "/clientMapping", method = RequestMethod.GET)
	public ModelAndView clientMapping(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session=request.getSession();
		 UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
	     Integer userId=0;
	     if(userDetail!=null){
		   userId=userDetail.getId();
	     } 
	     List<OrganizationMasterRegistration>  list= organizationMasterService.getOrganizationMasterRegistration();
		 // List<OrganizationMasterRegistration>  list=subsidiaryClientUserService.getOrgClient(userId);
	     request.setAttribute("orgList", list);
		 
	     List<UserDetail> userList= registrationService.getUserDetailList();
	     request.setAttribute("userList", userList);
		
	  return new ModelAndView("backEndClientUserMapping");
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/saveUserClientMapping", method = RequestMethod.POST)
	public ModelAndView saveUserClientMapping(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer userId,@RequestParam Integer clientId,@RequestParam String clientSubId) {
		HttpSession session=request.getSession();
		 UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
	     Integer userIds=0;
	     if(userDetail!=null){
		   userIds=userDetail.getId();
	     } 
	     String[] clientSubIds=clientSubId.split(",");
	     SubsidiaryClientUser saveDto=null;
	     List<SubsidiaryClientUser> listOfMapping=new ArrayList<SubsidiaryClientUser>();
	     if(clientSubIds.length>0){
	     for(String clientIdd: clientSubIds){
		SubsidiaryClientUser dto=new SubsidiaryClientUser();
		dto.setClientId(clientId);
		dto.setUserId(userId);
		dto.setIsActive(1);
		dto.setCreatedBy(userIds);
		dto.setCreatedDateTime(new Date());
		if(clientIdd!=null && !clientIdd.equals("")){
		dto.setClientSubId(new Integer(clientIdd));
		}
		  saveDto=subsidiaryClientUserService.saveSubsidiaryClientUser(dto);
		  if(saveDto!=null){
			  listOfMapping.add(saveDto);
		  }
		   }}
	    // List<OrganizationMasterRegistration>  list=subsidiaryClientUserService.getOrgClient(userIds);
	     List<OrganizationMasterRegistration>  list= organizationMasterService.getOrganizationMasterRegistration();
		 
	     request.setAttribute("orgList", list);
		 
	     List<UserDetail> userList= registrationService.getUserDetailList();
	     request.setAttribute("userList", userList);
	     request.setAttribute("saveDto", listOfMapping);
	     
	  return new ModelAndView("backEndClientUserMapping");
	}
	
	/*SELECT cm.name,csm.subName,csm.address FROM  client_truxuser_mapping  ctm 
	INNER JOIN client_sub_master csm ON ctm.clientsubid=csm.idClientSubMaster 
	INNER JOIN client_master cm ON cm.idClientMaster=ctm.clientid
	 WHERE ctm.userid=43 AND  ctm.clientid=12 ORDER BY cm.name;*/
	
	
	@ResponseBody
	@RequestMapping(value = "/searchUserClientMapping", method = RequestMethod.GET)
	public List<SubsidiaryClientOfOrg> searchUserClientMapping(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer userId,@RequestParam Integer clientId) {
	 
		  List<SubsidiaryClientOfOrg> listOfMapping=new ArrayList<SubsidiaryClientOfOrg>();
	    
		 listOfMapping=subsidiaryClientUserService.searchSubsidiaryClient(userId,clientId);
		 if(listOfMapping!=null){
			  
		  }
	  return listOfMapping;
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/ActiveDeactive", method = RequestMethod.GET)
	public List<SubsidiaryClientOfOrg> ActiveDeactive(HttpServletRequest request,HttpServletResponse response,@RequestParam Integer userId,@RequestParam Integer clientId,@RequestParam Integer subClientId,@RequestParam Integer isActive) {
	   List<SubsidiaryClientOfOrg> listOfMapping=new ArrayList<SubsidiaryClientOfOrg>();
	     listOfMapping=subsidiaryClientUserService.searchSubsidiaryAndActiveDeactive(userId, clientId, subClientId, isActive);
	   return listOfMapping;
	}
}
