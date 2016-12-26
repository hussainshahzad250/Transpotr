package com.trux.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trux.model.CustomerBookingDetails;
import com.trux.model.CustomerBookingDetailsResponse;
import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.OrderFilterReportsDto;
import com.trux.model.UserDetail;
import com.trux.service.BookingService;
import com.trux.service.RegistrationService;
import com.trux.utils.DateFormaterUtils;
/**
 * 
 * @author Mithlesh Kumar 
 * 
 */
@Controller
@RequestMapping(value = "/reportsapi")
public class JTableReportGeneratorApiController  {

	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();
	private HashMap<String, Object> JSONROOTRevenue = new HashMap<String, Object>();
	private ModelMap map=new ModelMap();
	@Autowired
	private BookingService bookingService;
	private OrderFilterReportsDto dto;
	private OrderFilterReportsDto dtos;
	@Autowired
	private RegistrationService registrationService;
	
    @ResponseBody
	@RequestMapping(value = "/loggedInDriverReports", method = RequestMethod.POST)
	protected void loggedInDriverReports(HttpServletRequest request,HttpServletResponse response)throws javax.servlet.ServletException, IOException {
    try{	UserDetail 	userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
    if(userDetail!=null && !userDetail.getUserRole().equals("") ){
    	if(userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
    
    	String action = request.getParameter("action");
		String flag =(String) map.get("flag"); 
		String vehicleType=(String)map.get("vehicleType");		 
		String city=(String)map.get("city"); 
		 
		List<DriverDeviceVehicleMapping> driverDeviceVehicleMappingList = new ArrayList<DriverDeviceVehicleMapping>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		if (action != null) {
			try {
				if (action.equals("list")) {
					int startPageIndex = Integer.parseInt(request.getParameter("jtStartIndex"));
					int recordsPerPage = Integer.parseInt(request.getParameter("jtPageSize"));
					int totalCount=0;
					if(vehicleType!=null && !vehicleType.equals("")){
						totalCount=	bookingService.getAllLogedInVehiclesByVehicle(flag, vehicleType, city).size();
						driverDeviceVehicleMappingList=bookingService.getLogedInVehiclesReportByPaginationByVehicle(startPageIndex, recordsPerPage, flag, vehicleType, city);
					}else{
					    driverDeviceVehicleMappingList = bookingService.getLogedInVehiclesReportByPagination(startPageIndex, recordsPerPage, flag);
						totalCount = bookingService.getAllLogedInVehicles(flag).size();
					}
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Records", driverDeviceVehicleMappingList);
					JSONROOT.put("TotalRecordCount", totalCount);
					String jsonArray = gson.toJson(JSONROOT);
					response.getWriter().print(jsonArray);
				} else if (action.equals("delete")) {
					String byid = request.getParameter("id");
					bookingService.removeDriverDeviceVehicleMapping(byid);
					JSONROOT.put("Result", "OK");
					String jsonArray = gson.toJson(JSONROOT);
					response.getWriter().print(jsonArray);
				}

			} catch (Exception ex) {
				JSONROOT.put("Result", "No Records Available !");
				JSONROOT.put("Message",  "No Records Available ! "+ex.getMessage());
				String error = gson.toJson(JSONROOT);
				response.getWriter().print(error);
			}
		}}}else{
			response.getWriter().print("Your request is not valide !");
		}}catch(Exception er){er.printStackTrace();}
	}
	@ResponseBody
	@RequestMapping(value = "/liveOrderReports")
	protected void liveOrderReports(HttpServletRequest request,
			HttpServletResponse response)
			throws javax.servlet.ServletException, IOException {
	try{UserDetail 	userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
	  if(userDetail!=null && !userDetail.getUserRole().equals("") ){
	    	if(userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
	    	
		String action = request.getParameter("action");
		List<CustomerBookingDetails> allPendingBookingList = new ArrayList<CustomerBookingDetails>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		if (action != null) {
			try {
				if (action.equals("list")) {
					int startPageIndex = Integer.parseInt(request
							.getParameter("jtStartIndex"));
					int recordsPerPage = Integer.parseInt(request
							.getParameter("jtPageSize"));

					allPendingBookingList = bookingService.getPendingBookings(startPageIndex, recordsPerPage);
					if(allPendingBookingList!=null){
					int totalCount = bookingService.getPendingBookings().size();
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Records", allPendingBookingList);
					JSONROOT.put("TotalRecordCount", totalCount);
					String jsonArray = gson.toJson(JSONROOT);
					response.getWriter().print(jsonArray);
					}else{
						JSONROOT.put("Result", "No Records Available !");
						JSONROOT.put("Message", "No Records Found ! !");
						String error = gson.toJson(JSONROOT);
						response.getWriter().print(error);
					}
				} else if (action.equals("delete")) {
					String byid = request.getParameter("id");
					bookingService.removeDriverDeviceVehicleMapping(byid);
					JSONROOT.put("Result", "OK");
					String jsonArray = gson.toJson(JSONROOT);
					response.getWriter().print(jsonArray);
				}

			} catch (Exception ex) {
				JSONROOT.put("Result", "Message");
				JSONROOT.put("Message", ex.getMessage());
				String error = gson.toJson(JSONROOT);
				response.getWriter().print(error);
			}
		}}}else{
			response.getWriter().print("Your request is not valide !");
		}}catch(Exception er){er.printStackTrace();}

	}
    @ResponseBody
	@RequestMapping(value = "/loggedInReports", method = RequestMethod.GET)
	protected ModelAndView loggedInDriverReports(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String flag)
			throws javax.servlet.ServletException, IOException {
		request.setAttribute("flag", flag);
  		map.put("flag", flag);
  		map.put("vehicleType","");
  		map.put("city","");
		return new ModelAndView("loggedInReports");
	}
    
    @ResponseBody
  	@RequestMapping(value = "/loggedInReportsByVehicleType", method = RequestMethod.POST)
  	protected ModelAndView loggedInReportsByVehicleType(HttpServletRequest request,	HttpServletResponse response, @RequestParam String flag, @RequestParam String vehicleType, @RequestParam String city)throws javax.servlet.ServletException, IOException {
  		request.setAttribute("flag", flag);
  		request.setAttribute("vehicleType", vehicleType);
  		request.setAttribute("city", city);
  		map.put("vehicleType",vehicleType);
  		map.put("city",city);
  		map.put("flag", flag);
  		return new ModelAndView("loggedInReports");
  	}
    @ResponseBody
	@RequestMapping(value = "/ordergridReports", method = RequestMethod.GET)
	protected ModelAndView orderReports(HttpServletRequest request,HttpServletResponse response,@ModelAttribute(value="orderFilter") OrderFilterReportsDto orderFilter ,BindingResult result) {
        map.remove("dto");
		return new ModelAndView("orderReports");
	}
    @ResponseBody
	@RequestMapping(value = "orderFilterReports", method = RequestMethod.POST)
	protected ModelAndView orderFilterReports(HttpServletRequest request,HttpServletResponse response,@ModelAttribute(value="orderFilter") OrderFilterReportsDto orderFilter ,BindingResult result) {
 		map.addAttribute("dto", orderFilter);
		return new ModelAndView("orderReports");
	}
	@RequestMapping(value = "/clear", method = RequestMethod.GET)
	protected ModelAndView clear(HttpServletRequest request,HttpServletResponse response,@ModelAttribute(value="orderFilter") OrderFilterReportsDto orderFilter ,BindingResult result) {
		orderFilter=null;
		return new ModelAndView("orderReports");
	}

	@RequestMapping(value = "/orderRequest", method = RequestMethod.POST)
	protected void orderReportsRequest(HttpServletRequest request,HttpServletResponse response) throws IOException {
     try{
		dto=(OrderFilterReportsDto) map.get("dto");
		String action = request.getParameter("action");
		List<CustomerBookingDetails> customerBookingDetailsList = new ArrayList<CustomerBookingDetails>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		 
		if (action != null) {
			try {
				if (action.equals("list")) {
					int startPageIndex = Integer.parseInt(request.getParameter("jtStartIndex"));
					int recordsPerPage = Integer.parseInt(request.getParameter("jtPageSize"));
					map.put("odrStartPageIndex", startPageIndex);
					map.put("odrRrecordsPerPage", recordsPerPage);
					customerBookingDetailsList = bookingService.getBookingDetaisByFilter(startPageIndex,recordsPerPage, dto);
					if(customerBookingDetailsList!=null){
					int totalCount = bookingService.getBookingDetaisByFilter(dto).size();
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Records", customerBookingDetailsList);
					JSONROOT.put("TotalRecordCount", totalCount);
					String jsonArray = gson.toJson(JSONROOT);
					response.getWriter().print(jsonArray);}else{
						JSONROOT.put("Result", "No Any Search Records Found !");
						JSONROOT.put("Message", "No Any Search Records Found !");
						String error = gson.toJson(JSONROOT);
						response.getWriter().print(error);
					}
				}  

			} catch (Exception ex) {
				JSONROOT.put("Result", "Message :"+ex.getMessage());
				JSONROOT.put("Message", ex.getMessage());
				String error = gson.toJson(JSONROOT);
				response.getWriter().print(error);
			}
		}}catch(Exception er){er.printStackTrace();}
	}
	// grid functionality
	@ResponseBody
	@RequestMapping(value = "/orderReports", method = RequestMethod.GET)
	protected ModelAndView ordergridReports(HttpServletRequest request,HttpServletResponse response,@ModelAttribute(value="orderFilter") OrderFilterReportsDto orderFilter ,BindingResult result) {
		try{if(request.getSession().getAttribute("userDetail")!=null){
		UserDetail userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
		if(userDetail!=null && !userDetail.getUserRole().equals("") && userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
      	map.remove("dto");
		return new ModelAndView("orderReportwithstatuscolor");
    	}else{
    		return new ModelAndView("massege");
    	}    	
		}else{
    		return new ModelAndView("massege");
    	}}catch(Exception er){er.printStackTrace(); return new ModelAndView("massege");}
	}
	@ResponseBody
	@RequestMapping(value = "/orderReportOnDemand", method = RequestMethod.GET)
	protected ModelAndView orderReportOnDemand(HttpServletRequest request,HttpServletResponse response,@ModelAttribute(value="orderFilter") OrderFilterReportsDto orderFilter ,BindingResult result) {
		try{if(request.getSession().getAttribute("userDetail")!=null){
		UserDetail userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
		if(userDetail!=null && !userDetail.getUserRole().equals("") && userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
      	map.remove("dto");
		return new ModelAndView("orderReportwithOnDemandStatuscolor");
    	}else{
    		return new ModelAndView("massege");
    	}    	
		}else{
    		return new ModelAndView("massege");
    	}}catch(Exception er){er.printStackTrace(); return new ModelAndView("massege");}
	}
	
	@ResponseBody
	@RequestMapping(value = "/orderReportOnDemandCApp", method = RequestMethod.GET)
	protected ModelAndView orderReportOnDemandCApp(HttpServletRequest request,HttpServletResponse response,@ModelAttribute(value="orderFilter") OrderFilterReportsDto orderFilter ,BindingResult result) {
		try{if(request.getSession().getAttribute("userDetail")!=null){
		UserDetail userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
		if(userDetail!=null && !userDetail.getUserRole().equals("") && userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
      	map.remove("dto");
		return new ModelAndView("orderReportwithOnDemandAppStatuscolor");
    	}else{
    		return new ModelAndView("massege");
    	}    	
		}else{
    		return new ModelAndView("massege");
    	}}catch(Exception er){er.printStackTrace(); return new ModelAndView("massege");}
	}
	
	@ResponseBody
	@RequestMapping(value = "/leasedOrderReport", method = RequestMethod.GET)
	protected ModelAndView leasedOrderReport(HttpServletRequest request,HttpServletResponse response,@ModelAttribute(value="orderFilter") OrderFilterReportsDto orderFilter ,BindingResult result) {
		try{if(request.getSession().getAttribute("userDetail")!=null){
		UserDetail userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
		if(userDetail!=null && !userDetail.getUserRole().equals("") && userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
      	map.remove("dto");
		return new ModelAndView("orderReportwithLeaseStatuscolor");
    	}else{
    		return new ModelAndView("massege");
    	}    	
		}else{
    		return new ModelAndView("massege");
    	}}catch(Exception er){er.printStackTrace(); return new ModelAndView("massege");}
	}
	 
	 @ResponseBody
		@RequestMapping(value = "ordergridFilterReports", method = RequestMethod.POST)
		protected ModelAndView ordergridFilterReports(HttpServletRequest request,HttpServletResponse response,@ModelAttribute(value="orderFilter") OrderFilterReportsDto orderFilter ,BindingResult result) {
		try{
		 if(request.getSession().getAttribute("userDetail")!=null){
				UserDetail userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
				  if(userDetail!=null && !userDetail.getUserRole().equals("") ){
				  if(userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
				    	map.addAttribute("dto", orderFilter);
				    	if(orderFilter.getDstatus().equals("Mapped")){
				    		 return new ModelAndView("orderReportwithLeaseStatuscolor");
				    	}
				    	if(orderFilter.getDstatus().equals("On-DemandCAPP")){
				    		 return new ModelAndView("orderReportwithOnDemandAppStatuscolor");
				    	}
                        if(orderFilter.getDstatus().equals("Free")){
                        	 return new ModelAndView("orderReportwithOnDemandStatuscolor");
				    	}
			   return new ModelAndView("orderReportwithstatuscolor");
			   }else{
				   return new ModelAndView("massege");  
			   }}
				  return new ModelAndView("massege"); 
				  }else{
				   return new ModelAndView("massege"); 
			   }
		}catch(Exception er){er.printStackTrace(); return new ModelAndView("massege");}
		}
		@RequestMapping(value = "/gridclear", method = RequestMethod.GET)
		protected ModelAndView gridclear(@ModelAttribute(value="orderFilter") OrderFilterReportsDto orderFilter ,BindingResult result) {
			orderFilter=null;
			return new ModelAndView("orderReportwithstatuscolor");
		}
	@SuppressWarnings("deprecation")
	@ResponseBody 
	@RequestMapping(value = "/orderGridRequest", method = RequestMethod.POST)
	protected  CustomerBookingDetailsResponse orderReportsGridRequest(HttpServletRequest request,HttpServletResponse response,@ModelAttribute(value="consumer") CustomerBookingDetails consumer ,BindingResult result)  {
		CustomerBookingDetailsResponse bresponse = new CustomerBookingDetailsResponse();
		try{
		if(request.getSession().getAttribute("userDetail")!=null){
		UserDetail userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
		 if(userDetail!=null && !userDetail.getUserRole().equals("") ){
		if(userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
			   String action = request.getParameter("action"); 		
		try{
		if(action.equals("list")){		 
		List<CustomerBookingDetails> customerBookingDetailsList = new ArrayList<CustomerBookingDetails>();		
		dto=(OrderFilterReportsDto) map.get("dto"); 
		if(dto!=null){
		customerBookingDetailsList = bookingService.getBookingDetaisByFilterToGrid(dto);
		}
		if(customerBookingDetailsList!=null){
		bresponse.setRows(customerBookingDetailsList);	
		bresponse.setTotal("10");
		if(customerBookingDetailsList!=null && customerBookingDetailsList.size()>0){
			bresponse.setRecords(String.valueOf(customerBookingDetailsList.size())); 
		}else{
			bresponse.setRecords(String.valueOf(0)); 
		}
		bresponse.setPage("1");  
		}else{
			customerBookingDetailsList = new ArrayList<CustomerBookingDetails>();
			bresponse.setRows(customerBookingDetailsList);	
			bresponse.setTotal("10");
			if(customerBookingDetailsList!=null && customerBookingDetailsList.size()>0){
				bresponse.setRecords(String.valueOf(customerBookingDetailsList.size())); 
			}else{
				bresponse.setRecords(String.valueOf(0)); 
			}
			bresponse.setPage("1");
		}
		//System.out.println(bresponse);
		return bresponse; 			
		} else if(action.equals("edit")){
			bresponse = new CustomerBookingDetailsResponse();
			try{	
			Date rideDate=new Date(consumer.getDates());
		    Date dates=DateFormaterUtils.convertGMTToIST(rideDate);
			if(dates!=null){
			consumer.setRideTime(dates);
			}}catch(Exception er){}
			 DriverDeviceVehicleMapping driverDeviceVehicleMappings =null;
			if(consumer.getDriverPhonenumber()!=null){
				try{
				  String driverMobile=consumer.getDriverPhonenumber();
				    driverDeviceVehicleMappings = registrationService.getDriverDeviceDetail(driverMobile.trim());
				  if(driverDeviceVehicleMappings!=null && driverDeviceVehicleMappings.getDstatus()!=null){
					  consumer.setDstatus(driverDeviceVehicleMappings.getDstatus());
						
				  }}catch(Exception er){er.printStackTrace();}
				if(driverDeviceVehicleMappings!=null && driverDeviceVehicleMappings.getDriverPhoneNumber()!=null){
				bookingService.editCustomerBookingDetails(consumer);
				bresponse.setStatus("Your request is successfully completed !");
				}else{
					if(driverDeviceVehicleMappings==null){
						bresponse=new CustomerBookingDetailsResponse();
			    		bresponse.setStatus("Driver does not exist. Please register it first!");
					} 	return bresponse;
						
				}
				
			}else{
				if(driverDeviceVehicleMappings==null){
					bresponse=new CustomerBookingDetailsResponse();
		    	bresponse.setStatus("Driver does not exist. Please register it first!");
				} 	return bresponse;
					
				}
			
			List<CustomerBookingDetails> customerBookingDetailsList = new ArrayList<CustomerBookingDetails>();		
			dto=(OrderFilterReportsDto) map.get("dto"); 
			customerBookingDetailsList = bookingService.getBookingDetaisByFilterToGrid(dto);
			bresponse.setRows(customerBookingDetailsList);		
			bresponse.setTotal("10");
			if(customerBookingDetailsList!=null && customerBookingDetailsList.size()>0){
				bresponse.setRecords(String.valueOf(customerBookingDetailsList.size())); 
			}else{
				bresponse.setRecords(String.valueOf(0)); 
			}
			bresponse.setPage("1"); 
		 	return bresponse;
			
		}
		return null;
		}catch(Exception e)
		{ 
		e.printStackTrace();
		return null; 
		}

    	}else{
    		bresponse=new CustomerBookingDetailsResponse();
    		bresponse.setTotal("Your request is not valide !");
    		return bresponse;
		}}bresponse=new CustomerBookingDetailsResponse();
		bresponse.setTotal("Your request is not valide !");
		return bresponse;}else{
    		bresponse=new CustomerBookingDetailsResponse();
    		bresponse.setTotal("Your request is not valide !");
    		return bresponse;
		}}catch(Exception er){er.printStackTrace(); return bresponse;}
	}
	
	@ResponseBody
	@RequestMapping(value = "/backendBookingRideReports", method = RequestMethod.GET)
	protected ModelAndView backendBookingRideReports(HttpServletRequest request,HttpServletResponse response,@ModelAttribute(value="orderFilters") OrderFilterReportsDto orderFilters ,BindingResult result) {
		if(request.getSession().getAttribute("userDetail")!=null){
		UserDetail userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
		 if(userDetail!=null && !userDetail.getUserRole().equals("") ){
		if(userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
			map.remove("dtos");
		return new ModelAndView("backEndBookinRideReports");
    	}else{
    		return new ModelAndView("massege");
    	}    	
		 }return new ModelAndView("massege");
		 }else{
    		return new ModelAndView("massege");
    	}
	}
	 @ResponseBody
		@RequestMapping(value = "backendBookingRideFilterReports", method = RequestMethod.POST)
		protected ModelAndView backendBookingRideFilterReports(HttpServletRequest request,HttpServletResponse response,@ModelAttribute(value="orderFilters") OrderFilterReportsDto orderFilters ,BindingResult result) {
		 if(request.getSession().getAttribute("userDetail")!=null){
				UserDetail userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
		 if(userDetail!=null && !userDetail.getUserRole().equals("") ){
			if(userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
							map.addAttribute("dtos", orderFilters);
			   return new ModelAndView("backEndBookinRideReports");
			   }else{
				   return new ModelAndView("massege");  
			   }} return new ModelAndView("massege");  
		 }else{
				   return new ModelAndView("massege"); 
			   }
		    	
		}
		 
	@RequestMapping(value = "/clearBackendBookinRide", method = RequestMethod.GET)
	protected ModelAndView clearBackendBookinRide(HttpServletRequest request,HttpServletResponse response,@ModelAttribute(value="orderFilters") OrderFilterReportsDto orderFilters ,BindingResult result) {
		orderFilters=null;
		return new ModelAndView("backEndBookinRideReports");
	}  
	@SuppressWarnings("deprecation")
	@ResponseBody 
	@RequestMapping(value = "/bookingRideGridRequest", method = RequestMethod.POST)
	protected  CustomerBookingDetailsResponse bookingRideGridRequest(HttpServletRequest request,HttpServletResponse response,@ModelAttribute(value="consumer") CustomerBookingDetails consumer ,BindingResult result)  {
		CustomerBookingDetailsResponse bresponse = new CustomerBookingDetailsResponse();
		try{if(request.getSession().getAttribute("userDetail")!=null){
		UserDetail userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
		 if(userDetail!=null && !userDetail.getUserRole().equals("") ){
		 if(userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
			  String action = request.getParameter("action"); 		
		try{
		if(action.equals("list")){		 
		List<CustomerBookingDetails> customerBookingDetailsList = new ArrayList<CustomerBookingDetails>();		
		dtos=(OrderFilterReportsDto) map.get("dtos"); 
		if(dtos!=null){
		dtos.setBookingMode("B");}
		customerBookingDetailsList = bookingService.getBackEndBookingDetaisByFilterToGrid(dtos);
		if(customerBookingDetailsList!=null){
		bresponse.setRows(customerBookingDetailsList);	
		bresponse.setTotal("10");
		if(customerBookingDetailsList!=null && customerBookingDetailsList.size()>0){
			bresponse.setRecords(String.valueOf(customerBookingDetailsList.size())); 
		}else{
			bresponse.setRecords(String.valueOf(0)); 
		}
		bresponse.setPage("1");  
		}else{
			customerBookingDetailsList = new ArrayList<CustomerBookingDetails>();
			bresponse.setRows(customerBookingDetailsList);	
			bresponse.setTotal("10");
			if(customerBookingDetailsList!=null && customerBookingDetailsList.size()>0){
				bresponse.setRecords(String.valueOf(customerBookingDetailsList.size())); 
			}else{
				bresponse.setRecords(String.valueOf(0)); 
			}
			bresponse.setPage("1");
		}
		//System.out.println(bresponse);
		return bresponse; 			
		} else if(action.equals("edit")){
			bresponse = new CustomerBookingDetailsResponse();
			try{	
				Date rideDate=new Date(consumer.getDates());
			    Date dates=DateFormaterUtils.convertGMTToIST(rideDate);
				if(dates!=null){
				consumer.setRideTime(dates);
				}}catch(Exception er){}
			 
			 DriverDeviceVehicleMapping driverDeviceVehicleMappings =null;
				if(consumer.getDriverPhonenumber()!=null){
					try{
					  String driverMobile=consumer.getDriverPhonenumber();
					    driverDeviceVehicleMappings = registrationService.getDriverDeviceDetail(driverMobile.trim());
					  if(driverDeviceVehicleMappings!=null && driverDeviceVehicleMappings.getDstatus()!=null){
						  consumer.setDstatus(driverDeviceVehicleMappings.getDstatus());
							
					  }}catch(Exception er){er.printStackTrace();}
					if(driverDeviceVehicleMappings!=null && driverDeviceVehicleMappings.getDriverPhoneNumber()!=null){
					bookingService.editCustomerBookingDetails(consumer);
					bresponse.setStatus("Your request is successfully completed !");
					}else{
						if(driverDeviceVehicleMappings==null){
							bresponse=new CustomerBookingDetailsResponse();
				    		bresponse.setStatus("Your requested driver mobile number is not exist Or invalide !");
						} 	return bresponse;
							
					}
					
				}else{
					if(driverDeviceVehicleMappings==null){
						bresponse=new CustomerBookingDetailsResponse();
			    	bresponse.setStatus("Driver does not exist. Please register it first!");
					} 	return bresponse;
						
					}
			List<CustomerBookingDetails> customerBookingDetailsList = new ArrayList<CustomerBookingDetails>();		
			dtos=(OrderFilterReportsDto) map.get("dtos"); 
			customerBookingDetailsList = bookingService.getBookingDetaisByFilterToGrid(dtos);
			bresponse.setRows(customerBookingDetailsList);		
			bresponse.setTotal("10");
			if(customerBookingDetailsList!=null && customerBookingDetailsList.size()>0){
				bresponse.setRecords(String.valueOf(customerBookingDetailsList.size())); 
			}else{
				bresponse.setRecords(String.valueOf(0)); 
			}
			bresponse.setPage("1"); 
			return bresponse;
		}
		return null;
		}catch(Exception e)
		{ 
		e.printStackTrace();
		return null; 
		}

    	}else{
    		bresponse=new CustomerBookingDetailsResponse();
    		bresponse.setTotal("Your request is not valide !");
    		return bresponse;
		}}bresponse=new CustomerBookingDetailsResponse();
		bresponse.setTotal("Your request is not valide !");
		return bresponse;
		 }else{
    		bresponse=new CustomerBookingDetailsResponse();
    		bresponse.setTotal("Your request is not valide !");
    		return bresponse;
		}}catch(Exception er){er.printStackTrace(); return bresponse;}
	}
	
	@RequestMapping(value = "/ordergridXLSReports", method = RequestMethod.GET)
	public ModelAndView ordergridReportdownloadExcel(HttpServletRequest request,HttpServletResponse response){
		try{OrderFilterReportsDto dto=(OrderFilterReportsDto) map.get("dto");		 
		List<CustomerBookingDetails> customerBookingDetailsList = new ArrayList<CustomerBookingDetails>();
		customerBookingDetailsList = bookingService.getBookingDetaisByFilterToGrid(dto);
		return new ModelAndView("orderReportExcelView", "customerBookingDetailsList", customerBookingDetailsList);
		}catch(Exception er){er.printStackTrace(); return new ModelAndView("massege");}
		}
	
	@RequestMapping(value = "/bookingRideXLSReports", method = RequestMethod.GET)
	public ModelAndView bookingRideXLSReports(HttpServletRequest request,HttpServletResponse response){
		try{
		
		OrderFilterReportsDto dto=(OrderFilterReportsDto) map.get("dtos");		 
		List<CustomerBookingDetails> customerBookingDetailsList = new ArrayList<CustomerBookingDetails>();
		customerBookingDetailsList = bookingService.getBackEndBookingDetaisByFilterToGrid(dto);
		return new ModelAndView("orderReportExcelView", "customerBookingDetailsList", customerBookingDetailsList);
		
		}catch(Exception er){er.printStackTrace(); return new ModelAndView("massege");}
		}
	// end grid functionality
	@RequestMapping(value = "/clearRevenueFilter", method = RequestMethod.POST)
	protected ModelAndView clearRevenueFilter(HttpServletRequest request,HttpServletResponse response,@ModelAttribute(value="revenueOrderFilter") OrderFilterReportsDto revenueOrderFilter ,BindingResult result) {
		revenueOrderFilter=null;
		return new ModelAndView("revenueReports");
	}
	
	@RequestMapping(value = "/revenueReports", method = RequestMethod.GET)
	protected ModelAndView revenueReports(HttpServletRequest request,HttpServletResponse response,@ModelAttribute(value="revenueOrderFilter") OrderFilterReportsDto revenueOrderFilter ,BindingResult result) {
		try{
			
			 if(request.getSession().getAttribute("userDetail")!=null){
					UserDetail userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
					if(userDetail!=null && !userDetail.getUserRole().equals("") ){
					if(userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
					 	map.remove("rDto");
						return new ModelAndView("revenueReports");
					}
					 return new ModelAndView("massege");
					}
					 return new ModelAndView("massege");
			 }
			 return new ModelAndView("massege");
		}catch(Exception er){er.printStackTrace();
		 return new ModelAndView("massege");
		}
	
	}
	
	@RequestMapping(value = "/revenueFilterReports", method = RequestMethod.POST)
	protected ModelAndView revenueFilterReports(HttpServletRequest request,HttpServletResponse response,@ModelAttribute(value="revenueOrderFilter") OrderFilterReportsDto revenueOrderFilter ,BindingResult result) {
     try{
    	 if(request.getSession().getAttribute("userDetail")!=null){
				UserDetail userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
				if(userDetail!=null && !userDetail.getUserRole().equals("") ){
				if(userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
					map.addAttribute("rDto", revenueOrderFilter);
					return new ModelAndView("revenueReports");
				}
				 return new ModelAndView("massege");
				}
				 return new ModelAndView("massege");
		 }
		 return new ModelAndView("massege");
		 
		
       }catch(Exception er){er.printStackTrace();
  	    return new ModelAndView("massege");
       } 
	
	}
	
	@RequestMapping(value = "/revenueRequest", method = RequestMethod.POST)
	protected void revenueReportsRequest(HttpServletRequest request,HttpServletResponse response) throws IOException {
	 try{ 	Gson gson = new GsonBuilder().setPrettyPrinting().create();
		 if(request.getSession().getAttribute("userDetail")!=null){
			UserDetail userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
			if(userDetail!=null && !userDetail.getUserRole().equals("") ){
			if(userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
			
		 JSONROOTRevenue=new HashMap<String, Object>();
		OrderFilterReportsDto	dto=(OrderFilterReportsDto) map.get("rDto");
		String action = request.getParameter("action");
		List<CustomerBookingDetails> customerBookingRevenueDetailsList = new ArrayList<CustomerBookingDetails>();
	
		response.setContentType("application/json");
		 
		if (action != null) {
			try {
				if (action.equals("list")) {
					int startPageIndex =0;
					int recordsPerPage =0;
					if(request.getParameter("jtStartIndex")!=null)
						startPageIndex= Integer.parseInt(request.getParameter("jtStartIndex"));
					if(request.getParameter("jtPageSize")!=null)
					  recordsPerPage = Integer.parseInt(request.getParameter("jtPageSize"));
					map.put("startPageIndex", startPageIndex);
					map.put("recordsPerPage", recordsPerPage);
					customerBookingRevenueDetailsList = bookingService.getBookingRevenueDetaisByFilters(startPageIndex,recordsPerPage, dto);
					if(customerBookingRevenueDetailsList!=null){
					int totalCount = bookingService.getBookingRevenueDetaisByFilter(dto).size();
					
					JSONROOTRevenue.put("Result", "OK");
					JSONROOTRevenue.put("Records", customerBookingRevenueDetailsList);
					JSONROOTRevenue.put("TotalRecordCount",totalCount);
					String jsonArray = gson.toJson(JSONROOTRevenue);
					//System.out.println(jsonArray);
					response.getWriter().print(jsonArray);
					}else{
						JSONROOT.put("Result", "No Any Search Records Found !");
						JSONROOT.put("Message", "No Any Search Records Found !");
						String error = gson.toJson(JSONROOT);
						response.getWriter().print(error);
					}
				}  

			} catch (Exception ex) {
				JSONROOTRevenue.put("Result", "Message :"+ex.getMessage());
				JSONROOTRevenue.put("Message", ex.getMessage());
				String error = gson.toJson(JSONROOTRevenue);
				response.getWriter().print(error);
			}
		}}}
		 }else{
			JSONROOT.put("Result", "No Any Search Records Found !");
			JSONROOT.put("Message", "No Any Search Records Found !");
			String error = gson.toJson(JSONROOT);
			response.getWriter().print(error);
		}
		 
		 }catch(Exception er){er.printStackTrace(); }
	}
	
	@RequestMapping(value = "/revenueXLSReports", method = RequestMethod.GET)
	public ModelAndView downloadExcel(HttpServletRequest request,HttpServletResponse response) throws IOException {
	try{
		OrderFilterReportsDto dto=(OrderFilterReportsDto) map.get("rDto");
		//String action = request.getParameter("action");
        //System.out.println("action :"+action);

        Integer startPageIndex = (Integer) map.get("startPageIndex");		 
        Integer recordsPerPage =(Integer) map.get("recordsPerPage");

		List<CustomerBookingDetails> customerBookingRevenueDetailsList = new ArrayList<CustomerBookingDetails>();
		customerBookingRevenueDetailsList = bookingService.getBookingRevenueDetaisByFilters(startPageIndex,recordsPerPage, dto); 
		return new ModelAndView("revenueExcelView", "customerBookingRevenueDetailsList", customerBookingRevenueDetailsList);
	}catch(Exception er){er.printStackTrace(); return new ModelAndView("massege");}}
	@RequestMapping(value = "/orderXLSReports", method = RequestMethod.GET)
	public ModelAndView orderReportdownloadExcel(HttpServletRequest request,HttpServletResponse response) throws IOException {
	try{
		OrderFilterReportsDto dto=(OrderFilterReportsDto) map.get("dto");
		//String action = request.getParameter("action");
        //System.out.println("action :"+action);
		int startPageIndex = (Integer) map.get("odrStartPageIndex");		 
		int recordsPerPage =(Integer) map.get("odrRrecordsPerPage");
		List<CustomerBookingDetails> customerBookingDetailsList = new ArrayList<CustomerBookingDetails>();
		customerBookingDetailsList = bookingService.getBookingDetaisByFilter(startPageIndex,recordsPerPage, dto);
		return new ModelAndView("orderReportExcelView", "customerBookingDetailsList", customerBookingDetailsList);
	}catch(Exception er){er.printStackTrace(); return new ModelAndView("massege");}
	}
	
	public OrderFilterReportsDto getDto() {
		return dto;
	}

	public void setDto(OrderFilterReportsDto dto) {
		this.dto = dto;
	}
	
	@RequestMapping(value = "/clearLoginLogoutDriver", method = RequestMethod.POST)
	protected ModelAndView clearLoginLogoutDriver(HttpServletRequest request,HttpServletResponse response,@ModelAttribute(value="loginLogout") OrderFilterReportsDto loginLogout ,BindingResult result) {
		loginLogout=null;
		return new ModelAndView("driverloginReports");
	}
	
	@RequestMapping(value = "/driverloginLogoutReports", method = RequestMethod.GET)
	protected ModelAndView driverloginLogoutReports(HttpServletRequest request,HttpServletResponse response,@ModelAttribute(value="loginLogout") OrderFilterReportsDto loginLogout ,BindingResult result) {
		map.remove("loginLogout");
		return new ModelAndView("driverloginReports");
	}
	
	@RequestMapping(value = "/searchDriverloginLogoutReports", method = RequestMethod.POST)
	protected ModelAndView searchDriverloginLogoutReports(HttpServletRequest request,HttpServletResponse response,@ModelAttribute(value="loginLogout") OrderFilterReportsDto loginLogout ,BindingResult result) {
 		map.addAttribute("loginLogout", loginLogout);
		return new ModelAndView("driverloginReports");
	
	}
	 @ResponseBody
		@RequestMapping(value = "/loginLogoutDriverReports", method = RequestMethod.POST)
		protected void loginLogoutDriverReports(HttpServletRequest request,HttpServletResponse response)
				throws javax.servlet.ServletException, IOException {
          try{
			String action = request.getParameter("action");
			OrderFilterReportsDto	dto=(OrderFilterReportsDto) map.get("loginLogout");
			List<DriverDeviceVehicleMapping> driverDeviceVehicleMappingList = new ArrayList<DriverDeviceVehicleMapping>();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			response.setContentType("application/json");
			if (action != null) {
				try {
					if (action.equals("list")) {
						int startPageIndex = Integer.parseInt(request.getParameter("jtStartIndex"));
						int recordsPerPage = Integer.parseInt(request.getParameter("jtPageSize"));
						map.put("loggedStartPageIndex", startPageIndex);
						map.put("loggedRecordsPerPage", recordsPerPage);
						driverDeviceVehicleMappingList = bookingService.searchLoginLogoutDriver(startPageIndex, recordsPerPage, dto);
						if(driverDeviceVehicleMappingList!=null){
						int totalCount = bookingService.searchLoginLogoutDriver(dto).size();
						JSONROOT.put("Result", "OK");
						JSONROOT.put("Records", driverDeviceVehicleMappingList);
						JSONROOT.put("TotalRecordCount", totalCount);
						String jsonArray = gson.toJson(JSONROOT);
						//System.out.println(jsonArray);
						response.getWriter().print(jsonArray);
					}  else{
						JSONROOT.put("Result", "No Any Search Records Found !");
						JSONROOT.put("Message", "No Any Search Records Found !");
						String error = gson.toJson(JSONROOT);
						response.getWriter().print(error);
					}
				}

				} catch (Exception ex) {
					ex.printStackTrace();
					JSONROOT.put("Result", "No Records Available !");
					JSONROOT.put("Message", ex.getMessage());
					String error = gson.toJson(JSONROOT);
					response.getWriter().print(error);
				}
			}}catch(Exception er){er.printStackTrace();}
		}
	 
	 @RequestMapping(value = "/LoggedInXLSReports", method = RequestMethod.GET)
		public ModelAndView loggedInReportdownloadExcel(HttpServletRequest request,HttpServletResponse response) throws IOException {
		 try{   OrderFilterReportsDto	dto=(OrderFilterReportsDto) map.get("loginLogout");
		    int startPageIndex = (Integer) map.get("loggedStartPageIndex");		 
			int recordsPerPage =(Integer) map.get("loggedRecordsPerPage");
			List<DriverDeviceVehicleMapping> driverDeviceVehicleMappingList = new ArrayList<DriverDeviceVehicleMapping>();
			driverDeviceVehicleMappingList = bookingService.searchLoginLogoutDriver(startPageIndex, recordsPerPage, dto);
			return new ModelAndView("loggedInLoggedOutReportExcelView", "driverDeviceVehicleMappingList", driverDeviceVehicleMappingList);
		 }catch(Exception er){er.printStackTrace();
		 return new ModelAndView("massege");}}
	public OrderFilterReportsDto getDtos() {
		return dtos;
	}
	public void setDtos(OrderFilterReportsDto dtos) {
		this.dtos = dtos;
	}
	 
}
