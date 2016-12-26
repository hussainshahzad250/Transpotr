package com.trux.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.trux.model.DriverRegistration;
import com.trux.model.VehicleRegistration;
import com.trux.service.RegistrationService;
import com.trux.service.VehicleLocationService;

@Controller
@RequestMapping(value="/admin/truxDetail")
public class DeviceDriverVehicleController {

	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private VehicleLocationService vehicleLocationService;
	
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/registeredDriverList", method=RequestMethod.GET)
	private ModelAndView getRegisteredDriverList(HttpServletRequest request, HttpServletResponse response){
		
		List<DriverRegistration> registeredDriverlist = registrationService.getRegisteredDriverList();
		request.setAttribute("registeredDriverlist", registeredDriverlist);
		return new ModelAndView("driverList");
		
	}
	/**
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/registeredVehicleList", method=RequestMethod.GET)
	private ModelAndView getRegisteredVehicleList(HttpServletRequest request, HttpServletResponse response){
		
		List<VehicleRegistration> registeredVehicleList = registrationService.getRegisteredVehicleList();
		request.setAttribute("registeredVehicleList", registeredVehicleList);
		return new ModelAndView("vehicleList");
		
	}
	/**
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/mapDriverVehicle", method=RequestMethod.GET)
	private ModelAndView mapVehicleAndDriver(HttpServletRequest request, HttpServletResponse response){
		
		List<VehicleRegistration> registeredVehicleList = registrationService.getFreeRegisteredVehicleList();
		List<DriverRegistration> registeredDriverlist = registrationService.getFreeRegisteredDriverList();
		request.setAttribute("registeredDriverlist", registeredDriverlist);
		request.setAttribute("registeredVehicleList", registeredVehicleList);
	return new ModelAndView("driverVehicleMapping");//	pageRedisrection("/pages/driverVehicleMapping.jsp", request, response);
		
	}
	
	@RequestMapping(value="/vehicleDriverMappingView", method=RequestMethod.GET)
	private ModelAndView vehicleDriverMapView(HttpServletRequest request, HttpServletResponse response){
		
		
		/*List<DriverVehicleMapping> driverVehicleMappingList = vehicleLocationService.getAllDriverVehicleMappingList();
		List<DriverVehicleComboDetails> driverVehicleComboDetails = new ArrayList<DriverVehicleComboDetails>();
		
		
		if(driverVehicleMappingList != null && driverVehicleMappingList.size() > 0){
			for (DriverVehicleMapping driverVehicleMapping : driverVehicleMappingList) {
				
				DriverRegistration driverRegistration = registrationService.getDriverDetailsById(driverVehicleMapping.getDriver_id());
				VehicleRegistration vehicleRegistration = registrationService.getVehicleDetailsById(driverVehicleMapping.getVehicle_id());
				
				DriverVehicleComboDetails driverComboDetails = new DriverVehicleComboDetails(driverRegistration.getFirstName(), driverRegistration.getLastName(), driverRegistration.getPhoneNumber(),
										vehicleRegistration.getVehicleNumber(), vehicleRegistration.getVehicle_type());
				driverVehicleComboDetails.add(driverComboDetails);
				
			}
		}*/
		
		request.setAttribute("driverVehicleComboDetails", null);	
		return new ModelAndView("driverVehicleMappingView");//pageRedisrection("/pages/driverVehicleMappingView.jsp", request, response);
	
	}
	
	public static void pageRedisrection(String pagePath, HttpServletRequest request, HttpServletResponse response){
		
		RequestDispatcher dispatcher= request.getRequestDispatcher(pagePath);
	    try {
			dispatcher.forward(request,  response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
