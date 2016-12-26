package com.trux.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.trux.model.CustomerBookingDetails;
import com.trux.model.CustomerBookingDetailsResponse;
import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.UserDetail;
import com.trux.service.BookingService;
import com.trux.service.RegistrationService;


@Controller
@RequestMapping(value="/reports")
public class BackendReportsController {
	
	
	@Autowired
	private BookingService bookingService;
	@Autowired
	private RegistrationService registrationService;

	@ResponseBody
	@RequestMapping(value="/liveReports", method=RequestMethod.GET)
	private ModelAndView getLiveReports(HttpServletRequest request, HttpServletResponse response){
		
		List<CustomerBookingDetails> allPendingBookingList = bookingService.getPendingBookings();
		request.setAttribute("allPendingBookingList", allPendingBookingList);
		return new ModelAndView("pendingOrders");//TruxUtils.pageRedisrection("/pages/pendingOrders.jsp", request, response);
		
	}
	
	@ResponseBody
	@RequestMapping(value="/bookingCurrentStatus", method=RequestMethod.GET)
	private ModelAndView getBookingCurrentStatus(HttpServletRequest request, HttpServletResponse response){
		 if(request.getSession().getAttribute("userDetail")!=null){
				UserDetail userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
				if(userDetail!=null && !userDetail.getUserRole().equals("") ){
				if(userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
				
		List<CustomerBookingDetails> allCurrentBookingList = bookingService.getAllCurrentBookingStatus();
		if(allCurrentBookingList != null && !allCurrentBookingList.isEmpty()){
			for (CustomerBookingDetails customerBookingDetails : allCurrentBookingList) {
				Long currentTime = new Date().getTime();
				Long startTime = customerBookingDetails.getExpectedRideStartTime();
				Long spentTime = currentTime-startTime; 				
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(customerBookingDetails.getExpectedRideEndTime());
				int min = cal.get(Calendar.MINUTE);
				
				Calendar cal2 = Calendar.getInstance();
				cal2.setTimeInMillis(spentTime);
				int min2 = cal2.get(Calendar.MINUTE);
				
				customerBookingDetails.setEstimatedTimeLeft(min-min2);
			}
		}
		
		request.setAttribute("allCurrentBookingList", allCurrentBookingList);
		return new ModelAndView("bookingCurrentStatus");
				}
				return new ModelAndView("message");
				}
				return new ModelAndView("message");
		 }
		 return new ModelAndView("message");
		//TruxUtils.pageRedisrection("/pages/bookingCurrentStatus.jsp", request, response);
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/bookingCurrentStatuswithinGrid", method=RequestMethod.GET)
	private CustomerBookingDetailsResponse getBookingCurrentStatusGrid(HttpServletRequest request, HttpServletResponse response){
		
		List<CustomerBookingDetails> allCurrentBookingList = bookingService.getAllCurrentBookingStatus();
		if(allCurrentBookingList != null && !allCurrentBookingList.isEmpty()){
			for (CustomerBookingDetails customerBookingDetails : allCurrentBookingList) {
				Long currentTime = new Date().getTime();
				Long startTime = customerBookingDetails.getExpectedRideStartTime();
				Long spentTime = currentTime-startTime; 				
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(customerBookingDetails.getExpectedRideEndTime());
				int min = cal.get(Calendar.MINUTE);
				Calendar cal2 = Calendar.getInstance();
				cal2.setTimeInMillis(spentTime);
				int min2 = cal2.get(Calendar.MINUTE);
				
				customerBookingDetails.setEstimatedTimeLeft(min-min2);
			}
		}
		CustomerBookingDetailsResponse responses=new CustomerBookingDetailsResponse();
		int count =allCurrentBookingList.size();
		int rows = 1;
		int page = 10;		
		
		//System.out.println( allCurrentBookingList);
		responses.setRows(allCurrentBookingList);		
		//int total = count%rows == 0 ? (int)Math.ceil(count/rows) : (int)Math.ceil(count/rows)+1;
		responses.setTotal("10");
		responses.setRecords(String.valueOf(allCurrentBookingList.size())); 
		responses.setPage("1"); 
		//System.out.println(responses.toString() );
		return responses; 	
		 	
	}
	
	
	@ResponseBody
	@RequestMapping(value="/acceptedBookings", method=RequestMethod.GET)
	private ModelAndView getAllAcceptedBooking(HttpServletRequest request, HttpServletResponse response){
		 if(request.getSession().getAttribute("userDetail")!=null){
				UserDetail userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
				if(userDetail!=null && !userDetail.getUserRole().equals("") ){
				if(userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
				
		List<CustomerBookingDetails> acceptedBookings = bookingService.getAllConfirmedBooking();
		if(acceptedBookings != null && !acceptedBookings.isEmpty()){
			for (CustomerBookingDetails customerBookingDetails : acceptedBookings) {
				
				Date pickUptime = new Date(customerBookingDetails.getExpectedRideStartTime());
				customerBookingDetails.setPickUpTime(pickUptime);
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(customerBookingDetails.getExpectedRideStartTime());
				int min = cal.get(Calendar.MINUTE);
				
				Calendar cal2 = Calendar.getInstance();
				cal2.setTimeInMillis(new Date().getTime());
				int min2 = cal2.get(Calendar.MINUTE);
				
				customerBookingDetails.setEstimatedTimeLeft(min-min2);
				
			}
		}
		
		request.setAttribute("acceptedBookings", acceptedBookings);
	return new ModelAndView("acceptedBookings");
				}
				return new ModelAndView("message");
				}
				return new ModelAndView("message");
		 }
		 return new ModelAndView("message"); 
	}
	
	@ResponseBody
	@RequestMapping(value="/acceptedBookingsGrid", method=RequestMethod.GET)
	private CustomerBookingDetailsResponse getAllAcceptedBookingGrid(HttpServletRequest request, HttpServletResponse response){
		
		List<CustomerBookingDetails> acceptedBookings = bookingService.getAllConfirmedBooking();
		if(acceptedBookings != null && !acceptedBookings.isEmpty()){
			for (CustomerBookingDetails customerBookingDetails : acceptedBookings) {
				
				Date pickUptime = new Date(customerBookingDetails.getExpectedRideStartTime());
				customerBookingDetails.setPickUpTime(pickUptime);
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(customerBookingDetails.getExpectedRideStartTime());
				int min = cal.get(Calendar.MINUTE);
				
				Calendar cal2 = Calendar.getInstance();
				cal2.setTimeInMillis(new Date().getTime());
				int min2 = cal2.get(Calendar.MINUTE);
				
				customerBookingDetails.setEstimatedTimeLeft(min-min2);
				
			}
		}
		CustomerBookingDetailsResponse responses=new CustomerBookingDetailsResponse();
		int count =acceptedBookings.size();
		int rows = 1;
		int page = 10;		
		
		//System.out.println( acceptedBookings);
		responses.setRows(acceptedBookings);		
		int total = count%rows == 0 ? (int)Math.ceil(count/rows) : (int)Math.ceil(count/rows)+1;
		responses.setTotal(""+total);
		responses.setRecords(String.valueOf(acceptedBookings.size())); 
		responses.setPage("1"); 
		//System.out.println(responses.toString() );
		return responses; 	
		 }
	
	@ResponseBody
	@RequestMapping(value="/rejectedOrders", method=RequestMethod.GET)
	private ModelAndView getAllRejectedBooking(HttpServletRequest request, HttpServletResponse response){
		 if(request.getSession().getAttribute("userDetail")!=null){
				UserDetail userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
				if(userDetail!=null && !userDetail.getUserRole().equals("") ){
				if(userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
			
		List<CustomerBookingDetails> rejectedBookings = bookingService.getAllCancelledBookings();
		request.setAttribute("rejectedBookings", rejectedBookings);
	return new ModelAndView("rejectedBookings");
				}
				return new ModelAndView("message");
				}
				return new ModelAndView("message");
		 }
		 return new ModelAndView("message"); 
	}
	@ResponseBody
	@RequestMapping(value="/rejectedOrdersGrid", method=RequestMethod.GET)
	private CustomerBookingDetailsResponse getAllRejectedBookingGrid(HttpServletRequest request, HttpServletResponse response){

		List<CustomerBookingDetails> rejectedBookings = bookingService.getAllCancelledBookings();
		CustomerBookingDetailsResponse responses=new CustomerBookingDetailsResponse();
		int count =rejectedBookings.size(); 
		int rows = 1;
		int page = 10;		
		
		//System.out.println( rejectedBookings);
		responses.setRows(rejectedBookings);		
		int total = count%rows == 0 ? (int)Math.ceil(count/rows) : (int)Math.ceil(count/rows)+1;
		responses.setTotal(""+total);
		responses.setRecords(String.valueOf(rejectedBookings.size())); 
		responses.setPage("1"); 
		//System.out.println(responses.toString() );
		return responses; }
	
	@ResponseBody
	@RequestMapping(value="/allLogedInVehicles", method=RequestMethod.GET)
	private ModelAndView getAllLogedInVehicles(HttpServletRequest request, HttpServletResponse response){
		 if(request.getSession().getAttribute("userDetail")!=null){
				UserDetail userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
				if(userDetail!=null && !userDetail.getUserRole().equals("") ){
				if(userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
		
		List<DriverDeviceVehicleMapping> driverDeviceMapList = bookingService.getAllLogedInVehicles();
		request.setAttribute("driverDeviceMapList", driverDeviceMapList);
	return new ModelAndView("logedInVehicle");
	}
	return new ModelAndView("message");
	}
	return new ModelAndView("message");
}
return new ModelAndView("message"); 
	//TruxUtils.pageRedisrection("/pages/logedInVehicle.jsp", request, response);
		
	}
	
	@ResponseBody
	@RequestMapping(value="/allDriverLogedInVehiclesStatus", method=RequestMethod.GET)
	private ModelAndView allLogedInVehicles(HttpServletRequest request, HttpServletResponse response){
		 if(request.getSession().getAttribute("userDetail")!=null){
				UserDetail userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
				if(userDetail!=null && !userDetail.getUserRole().equals("") ){
				if(userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
		
	return new ModelAndView("logedInVehicle");}
				return new ModelAndView("message");
				}
				return new ModelAndView("message");
			}
			return new ModelAndView("message"); 
	
	//TruxUtils.pageRedisrection("/pages/logedInVehicle.jsp", request, response);
		
	}
	@ResponseBody
	@RequestMapping(value="/statusOfDriverVehicles", method=RequestMethod.GET)
	private ModelAndView statusOfDriverVehicles(HttpServletRequest request, HttpServletResponse response,@RequestParam String flag){
		 if(request.getSession().getAttribute("userDetail")!=null){
				UserDetail userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
				if(userDetail!=null && !userDetail.getUserRole().equals("") ){
				if(userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
		
		List<DriverDeviceVehicleMapping> driverDeviceVehicleMappingList = bookingService.getAllLogedInVehicles(flag);
		request.setAttribute("driverDeviceMapList", driverDeviceVehicleMappingList);
	    return new ModelAndView("loggedInVehicleDetails");
	}return new ModelAndView("message");
}
return new ModelAndView("message");
}
return new ModelAndView("message"); 
		
	}
	
	@ResponseBody
	@RequestMapping(value="/removeDriverAndVehicles", method=RequestMethod.GET)
	private String removeDriverAndVehicles(HttpServletRequest request, HttpServletResponse response,@RequestParam String byId,@RequestParam String name){
		 bookingService.removeDriverDeviceVehicleMapping(byId); 
	    return name +" driver removed by trux app. Its Id is "+byId;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/bookingReports", method=RequestMethod.GET)
	private ModelAndView getBookingReports(HttpServletRequest request, HttpServletResponse response){
		 if(request.getSession().getAttribute("userDetail")!=null){
				UserDetail userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
				if(userDetail!=null && !userDetail.getUserRole().equals("") ){
				if(userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
		
		List<CustomerBookingDetails> customerBookingDetailsList = bookingService.getCustomerBookingReports();
		
		request.setAttribute("customerBookingDetailsList", customerBookingDetailsList);

        return new ModelAndView("bookingReports");
				}return new ModelAndView("message");
				}
				return new ModelAndView("message");
				}
				return new ModelAndView("message"); 
		
	}
	@RequestMapping(value = "/bookingXLSReports", method = RequestMethod.GET)
	public ModelAndView loggedInReportdownloadExcel(HttpServletRequest request,HttpServletResponse response) throws IOException {
		 if(request.getSession().getAttribute("userDetail")!=null){
				UserDetail userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
				if(userDetail!=null && !userDetail.getUserRole().equals("") ){
				if(userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
			 List<CustomerBookingDetails> customerBookingDetailsList = new ArrayList<CustomerBookingDetails>();
		 try{ customerBookingDetailsList = bookingService.getCustomerBookingReports();
		request.setAttribute("customerBookingDetailsList", customerBookingDetailsList);
		}catch(Exception er){er.printStackTrace();}
		 
		 return new ModelAndView("bookingReportExcelView", "customerBookingDetailsList", customerBookingDetailsList);
	}return new ModelAndView("message");
	}
	return new ModelAndView("message");
	}
	return new ModelAndView("message"); 
		
	}
	@ResponseBody
	@RequestMapping(value="/bookingGridReports", method=RequestMethod.GET)
	private CustomerBookingDetailsResponse getBookingGridReports(HttpServletRequest request, HttpServletResponse response){
		
		List<CustomerBookingDetails> customerBookingDetailsList = bookingService.getCustomerBookingReports();
		CustomerBookingDetailsResponse responses=new CustomerBookingDetailsResponse();
		int count =customerBookingDetailsList.size(); 
		int rows = 1;
		int page = 10;		
		
		//System.out.println( customerBookingDetailsList);
		responses.setRows(customerBookingDetailsList);		
		int total = count%rows == 0 ? (int)Math.ceil(count/rows) : (int)Math.ceil(count/rows)+1;
		responses.setTotal(""+total);
		responses.setRecords(String.valueOf(customerBookingDetailsList.size())); 
		responses.setPage("1"); 
		//System.out.println(responses.toString() );
		return responses;  
		
	}
	@ResponseBody
	@RequestMapping(value="/bookingVehicleChangeReports", method=RequestMethod.GET)
	private ModelAndView bookingVehicleChangeReports(HttpServletRequest request, HttpServletResponse response){
		
		 if(request.getSession().getAttribute("userDetail")!=null){
				UserDetail userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
				if(userDetail!=null && !userDetail.getUserRole().equals("") ){
				if(userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
		
		List<CustomerBookingDetails> customerBookingDetailsList = bookingService.getCustomerBookingReports();
		request.setAttribute("customerBookingDetailsList", customerBookingDetailsList);
        return new ModelAndView("bookingVehicleChangeReports");
				}return new ModelAndView("message");
				}
				return new ModelAndView("message");
				}
				return new ModelAndView("message"); 
		
	}
	
	@ResponseBody
	@RequestMapping(value="/driverDetailsByVehicleType", method=RequestMethod.GET)
	private String getdriverDetailsByVehicleType(HttpServletRequest request, HttpServletResponse response,@RequestParam String vehicleType){
		List<DriverDeviceVehicleMapping> driverDeviceVehicleMappingList=	registrationService.getAllAvailableDriversByVehicleType(vehicleType);
		if(driverDeviceVehicleMappingList!=null){
		StringBuilder driverDetails=new StringBuilder();
		 driverDetails.append("<option value='0'>---Select Available Driver---</option>");
		 for(DriverDeviceVehicleMapping dv:driverDeviceVehicleMappingList){
			driverDetails.append("<option value='"+dv.getDriverPhoneNumber()+"'>"+dv.getDriverName()+", "+dv.getDriverPhoneNumber()+", "+dv.getVehicleType()+", "+dv.getVehicleNumber()+"</option>");
		 }
		String dirverList=driverDetails.toString();
        return dirverList;
        }else{
        StringBuilder driverDetails=new StringBuilder();
   		driverDetails.append("<option value='0'>---Select Available Driver---</option>");   		 
   		driverDetails.append("<option value='0'>Vehicle is not available</option>");
   		String dirverList=driverDetails.toString();
   	    return dirverList;
        }
		
	}
	
	@ResponseBody
	@RequestMapping(value="/allVehicleType", method=RequestMethod.GET)
	private String getAllVehicleType(HttpServletRequest request, HttpServletResponse response){
		List<DriverDeviceVehicleMapping> driverDeviceVehicleMappingList=registrationService.getAllAvailableVehicle();
		if(driverDeviceVehicleMappingList!=null){
		StringBuilder vehicleDetails=new StringBuilder();
		vehicleDetails.append("<option value='0'>---Select Available Vehicle---</option>");
		 for(DriverDeviceVehicleMapping dv:driverDeviceVehicleMappingList){
			 vehicleDetails.append("<option value='"+dv.getVehicleType()+"'>"+dv.getVehicleType()+"</option>");
		 }
		String dirverList=vehicleDetails.toString();
        return dirverList;
        }else{
        StringBuilder driverDetails=new StringBuilder();
   		driverDetails.append("<option value='0'>---Select Available Driver---</option>");   		 
   		driverDetails.append("<option value='0'>Vehicle is not available</option>");
   		String dirverList=driverDetails.toString();
   	    return dirverList;
        }
		
	}
	
	


}
