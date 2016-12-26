package com.trux.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView; 

import com.trux.model.AssignedStands;
import com.trux.model.OrganizationMasterRegistration;
import com.trux.model.RestResponce;
import com.trux.model.UploadDocumentModel;
import com.trux.model.UserDetail;
import com.trux.model.VehicleTripSheet;
import com.trux.model.VehicleTripsheetDrops;
import com.trux.service.OrganizationMasterService;
import com.trux.service.SubsidiaryClientUserService;
import com.trux.service.VehicleTripSheetService;
import com.trux.utils.AWSS3Uploader;


@Controller
@RequestMapping(value="/trip")
public class TripSheetController {
	@Autowired
	private OrganizationMasterService organizationMasterService;
	@Autowired
	SubsidiaryClientUserService  subsidiaryClientUserService;
	@Autowired
	private VehicleTripSheetService vehicleTripSheetService;
	@RequestMapping(value = "/tripDetails", method = RequestMethod.GET)
	private ModelAndView tripDetails(HttpServletRequest request,HttpServletResponse response) throws ParseException {
		HttpSession session=request.getSession(); 
		UserDetail  userDetail=(UserDetail)session.getAttribute("userDetail");
		Integer userId=0;
		 List<OrganizationMasterRegistration>  list=new ArrayList<OrganizationMasterRegistration>();
		if(userDetail!=null){
			   userId=userDetail.getId();
		  
			if(userDetail.getUserRole().equals("Admin")){
	    list=organizationMasterService.getOrganizationMasterRegistration();
		}else{
			   list=subsidiaryClientUserService.getOrgClient(userId);
		}
		}
		if(list!=null && list.size()>0){
		 session.setAttribute("orgList", list);
		}
		 
		return new ModelAndView("backEndTripSheetBooking");

	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/addTripSheet", method = RequestMethod.POST)
	private ModelAndView addTripSheet(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam(required = false) Integer clientId,
			@RequestParam(required = false) Integer subClientId, 
			@RequestParam(required = false) String vehicleNo,
			@RequestParam(required = false) String tripDate,
			@RequestParam(required = false) Integer tripSheetOrderNo,
			@RequestParam(required = false) Integer openingKm, 
			@RequestParam(required = false) Integer closingKm,
			@RequestParam(required = false) String openingDateTime,
			@RequestParam(required = false) String closingDateTime,
			@RequestParam(required = false) String fromDropLocation,
			@RequestParam(required = false) String toDropLocation,
			@RequestParam(required = false) String signSecurityName,
			@RequestParam(required = false) MultipartFile scanTripSheetDocument,
			@RequestParam(required = false) Integer toll,
			@RequestParam(required = false) Integer parking,
			@RequestParam(required = false) Integer challan,
			@RequestParam(required = false) Integer ngt,
			@RequestParam(required = false) Integer other,
			@RequestParam(required = false) boolean checkbox)
			throws ParseException {
		HttpSession session = request.getSession();
		UserDetail userDetail = (UserDetail) session.getAttribute("userDetail");
		Integer userId = 0;
		List<OrganizationMasterRegistration> list = new ArrayList<OrganizationMasterRegistration>();
		if (userDetail != null) {
			userId = userDetail.getId();

			if (userDetail.getUserRole().equals("Admin")) {
				list = organizationMasterService
						.getOrganizationMasterRegistration();
			} else {
				list = subsidiaryClientUserService.getOrgClient(userId);
			}
		}
		if (list != null && list.size() > 0) {
			session.setAttribute("orgList", list);
		}

		String scannedTripSheetS3Url = "";
		UploadDocumentModel tripSheetDoc = new UploadDocumentModel();
		if (scanTripSheetDocument != null && !scanTripSheetDocument.isEmpty()) {
			try {
				String name = scanTripSheetDocument.getOriginalFilename();
				tripSheetDoc = AWSS3Uploader.uploadDriverFile(name,
						scanTripSheetDocument.getContentType(),
						scanTripSheetDocument.getSize(),
						scanTripSheetDocument.getInputStream(), tripSheetDoc);
				byte[] bytes = scanTripSheetDocument.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(name)));
				stream.write(bytes);
				stream.close();
				tripSheetDoc.setUploadStatus("SUCCESS");
			} catch (Exception e) {
				tripSheetDoc.setUploadStatus("Failed");
				tripSheetDoc.setErrorMessage(e.getMessage());
			}
			scannedTripSheetS3Url = tripSheetDoc.getUploadURL();
		}
		try {
			String[] fromLocation = fromDropLocation.split(",");
			String[] toLocation = toDropLocation.split(",");

			VehicleTripSheet dto = new VehicleTripSheet();
			if (!(clientId == null))
				dto.setClientId(clientId);
			if (!(subClientId == null))
				dto.setSubClientId(subClientId);
			if (!(vehicleNo == null)) {
				String[] vehicleDriver = vehicleNo.split(",");
				dto.setVehicleNo(vehicleDriver[0]);
				dto.setDriverName(vehicleDriver[1]);
			}
			if (!(tripSheetOrderNo == null))
				dto.setClientOrderNo(tripSheetOrderNo);
			if (!(tripDate == null))
				dto.setTripDate(new Date(tripDate));
			if (!(tripDate == null))
				dto.setTripDates(tripDate);
			if (!(openingKm == null))
				dto.setOpeningKm(openingKm);
			if (!(closingKm == null))
				dto.setClosingKm(closingKm);
			if (!openingDateTime.isEmpty()) {
				Date from = new SimpleDateFormat("yyyy/MM/dd HH:mm",
						Locale.ENGLISH).parse(openingDateTime);
				String str = new SimpleDateFormat("yyyy-MM-dd HH:mm",
						Locale.ENGLISH).format(from);
				Date to = new SimpleDateFormat("yyyy-MM-dd HH:mm",
						Locale.ENGLISH).parse(str);
				dto.setOpeningDateTime(to);
			}
			if (!closingDateTime.isEmpty()) {
				Date from2 = new SimpleDateFormat("yyyy/MM/dd HH:mm",
						Locale.ENGLISH).parse(closingDateTime);
				String str2 = new SimpleDateFormat("yyyy-MM-dd HH:mm",
						Locale.ENGLISH).format(from2);
				Date to2 = new SimpleDateFormat("yyyy-MM-dd HH:mm",
						Locale.ENGLISH).parse(str2);
				dto.setClosingDateTime(to2);
			}
			if (!(scannedTripSheetS3Url == null) && !scannedTripSheetS3Url.equals(""))
				dto.setScannedTripSheetS3Url(scannedTripSheetS3Url);
			if (!(signSecurityName == null) && !signSecurityName.equals(""))
				dto.setSignSecurityName(signSecurityName);
			if (!(userId == null))
				dto.setCreatedBy(userId);
			if (!(userId == null))
				dto.setModifiedBy(userId);
			dto.setCreatedDate(new Date());
			if (!(toll == null))
				dto.setToll(toll);
			if (!(parking == null))
				dto.setParking(parking);
			if (!(challan == null))
				dto.setChallan(challan);
			if (!(ngt == null))
				dto.setNgt(ngt);
			if (!(other == null))
				dto.setOther(other);
			if (checkbox == true) {
				dto.setFlag(2);
			} else {
				dto.setFlag(1);
			}
			if (dto != null) {
				VehicleTripSheet saveBack = vehicleTripSheetService.saveVehicleTripSheet(dto);
				if (checkbox == false)
					if (saveBack != null && saveBack.getId() != 0)
						for (int i = 0; i < fromLocation.length; i++) {
							VehicleTripsheetDrops vtd = new VehicleTripsheetDrops();
							vtd.setCreatedBy(userId);
							vtd.setModifiedBy(userId);
							vtd.setCreatedDate(new Date());
							vtd.setFromLocation(fromLocation[i]);
							vtd.setToLocation(toLocation[i]);
							vtd.setTripId(saveBack.getId());
							vehicleTripSheetService
									.saveVehicleTripsheetDrops(vtd);
						}
				request.setAttribute("saveBack", saveBack);
			}
		} catch (Exception er) {
			er.printStackTrace();
		}
		return new ModelAndView("backEndTripSheetBooking");

	}
		
	@ResponseBody
	@RequestMapping(value = "/getDriverVehicle", method = RequestMethod.POST)
	private String getDriverVehicle(HttpServletRequest request,HttpServletResponse response, @RequestParam Integer clientId,  @RequestParam Integer subClientId ) throws ParseException {
		List<VehicleTripSheet>  list=vehicleTripSheetService.getVehicleDetailList(clientId, subClientId);
		StringBuilder vehicleDetails=new StringBuilder();
		vehicleDetails.append("<option value=\"\">--Select Vehicle--</option>");
	     if (list != null && !list.isEmpty()) {
			for (VehicleTripSheet vts : list) {
				vehicleDetails.append("<option value=\"" + vts.getVehicleNo()+ ","+vts.getDriverName() +"\">" + vts.getVehicleNo() +"   " +vts.getDriverName() +"  "+vts.getDriverMobile()+ "</option>");
			}
		}
		return vehicleDetails.toString();  
	}
	
	@ResponseBody
	@RequestMapping(value = "/getDriverVehicleApi", method = RequestMethod.GET)
	private List<VehicleTripSheet> getDriverVehicleApi(HttpServletRequest request,HttpServletResponse response, @RequestParam Integer clientId,  @RequestParam Integer subClientId ) throws ParseException {
		List<VehicleTripSheet>  list=vehicleTripSheetService.getVehicleDetailList(clientId, subClientId);
	 return list;
	  
	}
	
	
	 
	@ResponseBody
	@RequestMapping(value = "/getTripSheetIsEixstApi", method = RequestMethod.GET)
	private List<VehicleTripSheet> getTripSheetIsEixstApi(HttpServletRequest request,HttpServletResponse response,
			@RequestParam Integer clientId,
			 @RequestParam Integer subClientId,
			 @RequestParam String vehicleNo,
			 @RequestParam String tripDate,
			 @RequestParam(required=false) Integer tripSheetOrderNo ) throws ParseException {
		VehicleTripSheet dto=new VehicleTripSheet();
		dto.setClientId(clientId);
		dto.setSubClientId(subClientId);
		String[] vehicleDriver=vehicleNo.split(",");
		dto.setVehicleNo(vehicleDriver[0]);
		dto.setDriverName(vehicleDriver[1]);
		dto.setTripDates(tripDate);
		dto.setClientOrderNo(tripSheetOrderNo);
		
		List<VehicleTripSheet>  list=vehicleTripSheetService.getVehicleTripSheet(dto);
	 return list;
	  
	}
	
	@ResponseBody
	@RequestMapping(value = "/getTripSheetIsEixstAPI", method = RequestMethod.GET)
	private List<VehicleTripSheet> getTripSheetIsEixstAPI(HttpServletRequest request,HttpServletResponse response,
			@RequestParam Integer clientId,
			 @RequestParam Integer subClientId,
			 @RequestParam String vehicleNo,
			 @RequestParam String tripDate,
			 @RequestParam(required=false) Integer tripSheetOrderNo ) throws ParseException {
		VehicleTripSheet dto=new VehicleTripSheet();
		dto.setClientId(clientId);
		dto.setSubClientId(subClientId);
		String[] vehicleDriver=vehicleNo.split(",");
		dto.setVehicleNo(vehicleDriver[0]);
		dto.setDriverName(vehicleDriver[1]);
		dto.setTripDates(tripDate);
		dto.setClientOrderNo(tripSheetOrderNo);
		
		
		List<VehicleTripSheet>  list=vehicleTripSheetService.getVehicleTripSheetAPI(dto);
	 return list;
	  
	}
	
	
	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping(value = "/addTripSheetAPI", method = RequestMethod.POST)
	public VehicleTripSheet addTripSheetAPI(HttpServletRequest request,HttpServletResponse response,
			 @RequestParam Integer clientId,
			 @RequestParam Integer subClientId,
			 @RequestParam String vehicleNo,
			 @RequestParam String tripDate,
			 @RequestParam Integer tripSheetOrderNo,
			 @RequestParam Integer openingKm,
			 @RequestParam Integer closingKm,
			 @RequestParam String openingDateTime,
			 @RequestParam String closingDateTime,
			 @RequestParam String fromDropLocation,
			 @RequestParam String toDropLocation,
			 @RequestParam(required=false) String signSecurityName,
			 @RequestParam(required=false) String scanTripSheetDocumentUrl,
			 @RequestParam Integer userId
			 ) throws ParseException {	   
		  
			    String[] fromLocation=fromDropLocation.split(",");
			    String[] toLocation=toDropLocation.split(",");
			    
			    VehicleTripSheet dto=new VehicleTripSheet();
			    dto.setClientId(clientId);
			    dto.setSubClientId(subClientId);
			    String[] vehicleDriver=vehicleNo.split(",");
			    dto.setVehicleNo(vehicleDriver[0]);
			    dto.setDriverName(vehicleDriver[1]);
			    dto.setClientOrderNo(tripSheetOrderNo);
			    dto.setTripDate(new Date(tripDate));
			    dto.setTripDates(tripDate);
			    dto.setOpeningKm(openingKm);
			    dto.setClosingKm(closingKm);
			    dto.setOpeningDateTime(new Date(openingDateTime));
			    dto.setClosingDateTime(new Date(closingDateTime));
			    dto.setScannedTripSheetS3Url(scanTripSheetDocumentUrl);
			    dto.setSignSecurityName(signSecurityName);
			    dto.setCreatedBy(userId);
			    dto.setModifiedBy(userId);
				dto.setCreatedDate(new Date());
				
			    VehicleTripSheet saveBack= vehicleTripSheetService.saveVehicleTripSheet(dto);
			   if(saveBack!=null && saveBack.getId()!=0)
			    for(int i=0;i<fromLocation.length;i++){
			    VehicleTripsheetDrops vtd=new VehicleTripsheetDrops();
			    vtd.setCreatedBy(userId);
			    vtd.setModifiedBy(userId);
			    vtd.setCreatedDate(new Date());
			    vtd.setFromLocation(fromLocation[i]);
			    vtd.setToLocation(toLocation[i]);
			    vtd.setTripId(saveBack.getId());
			    vehicleTripSheetService.saveVehicleTripsheetDrops(vtd);
			    }
	 		 /* 
		    String[] fromLocation=fromDropLocation.split(",");
		    String[] toLocation=toDropLocation.split(",");
		    
		    VehicleTripSheet dto=new VehicleTripSheet();
		    String[] vehicleDriver=vehicleNo.split(",");
		    dto.setVehicleNo(vehicleDriver[0]);
		    dto.setDriverName(vehicleDriver[1]);
		    dto.setClientId(clientId);
		    dto.setSubClientId(subClientId);
		    dto.setClientOrderNo(tripSheetOrderNo);
		    dto.setTripDate(new Date(tripDate));
		    dto.setOpeningKm(openingKm);
		    dto.setClosingKm(closingKm);
		    dto.setOpeningDateTime(new Date(openingDateTime));
		    dto.setClosingDateTime(new Date(closingDateTime));
		    dto.setScannedTripSheetS3Url(scanTripSheetDocumentUrl);
		    dto.setSignSecurityName(signSecurityName);
		   	dto.setCreatedBy(userId);
			dto.setCreatedDate(new Date());
		    VehicleTripSheet saveBack= vehicleTripSheetService.saveVehicleTripSheet(dto);
		   if(saveBack!=null && saveBack.getId()!=0)
		    for(int i=0;i<fromLocation.length;i++){
		    VehicleTripsheetDrops vtd=new VehicleTripsheetDrops();
		    vtd.setCreatedBy(userId);
		    vtd.setCreatedDate(new Date());
		    vtd.setModifiedBy(userId);
		    vtd.setModifiedDate(new Date());
		    vtd.setFromLocation(fromLocation[i]);
		    vtd.setToLocation(toLocation[i]);
		    vtd.setTripId(saveBack.getId());
		    vehicleTripSheetService.saveVehicleTripsheetDrops(vtd);
		    }*/
		return saveBack;
 
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/uploadTripSheet", method = RequestMethod.POST)
	public VehicleTripSheet uploadTripSheet(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam Integer clientId,
			@RequestParam Integer subClientId, 
			@RequestParam String vehicleNo,
			@RequestParam String tripDate,
			@RequestParam String scanTripSheetDocumentUrl,
			@RequestParam Integer userId) throws ParseException {

		VehicleTripSheet dto = new VehicleTripSheet();
		dto.setClientId(clientId);
		dto.setSubClientId(subClientId);
		String[] vehicleDriver=vehicleNo.split(",");
	    dto.setVehicleNo(vehicleDriver[0]);
	    dto.setDriverName(vehicleDriver[1]);
		dto.setTripDate(new Date(tripDate));
		dto.setTripDates(tripDate);
		dto.setScannedTripSheetS3Url(scanTripSheetDocumentUrl);
		dto.setCreatedBy(userId);
		dto.setModifiedBy(userId);
		dto.setCreatedDate(new Date());
		dto.setFlag(1);
		VehicleTripSheet saveBack = vehicleTripSheetService
				.uploadTripSheet(dto);
		return saveBack;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/getAssignedStands", method = RequestMethod.GET)
	public List getAssignedStands(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam Integer userId) throws ParseException {

		List userAssignedStands = vehicleTripSheetService.getAssignedStand(userId);
		
		return userAssignedStands;

	}
	
	@ResponseBody
	@RequestMapping(value = "/getNonClosedTripSheet", method = RequestMethod.GET)
	public RestResponce getNonClosedTripSheet(HttpServletRequest request,
			HttpServletResponse response, 
			@RequestParam Integer userId){
		
		try{
		RestResponce restRsp = new RestResponce();
		
		List vehicleTripSheets = vehicleTripSheetService.getNonClosedTripSheet(userId);
		
		if(vehicleTripSheets!= null && !vehicleTripSheets.isEmpty()){
			restRsp.setErrorCode("100");
			restRsp.setErrorMesaage("sucesss");	  
			restRsp.setData(vehicleTripSheets);
		}
		else{
			restRsp.setErrorCode("101");
			restRsp.setErrorMesaage("All Tripsheets Have Been Uploaded Correctly!!! Good Job!!!");
		}
		
		return restRsp;
		}
		catch(Exception e){
			return null;
		}
		
	}
	
}
