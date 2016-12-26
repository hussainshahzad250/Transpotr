package com.trux.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trux.model.DriverRegistratioModel;
import com.trux.model.DriverRegistration;
import com.trux.model.UserDetail;
import com.trux.service.ClusterRegistrationService;
import com.trux.service.DriverCollectionService;
import com.trux.service.HubRegistrationService;
import com.trux.service.OrganizationMasterService;
import com.trux.service.RegistrationService;
import com.trux.service.StandRegistrationService;
import com.trux.service.SubsaidiaryOrgRegistrationService;
import com.trux.service.TruxStartUpService;

@Controller
@RequestMapping(value="/cdvr")
public class JTableReportClientDriverVehicleController {
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
	
	
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();
	private ModelMap map=new ModelMap();
	  @ResponseBody
		@RequestMapping(value = "/dReports", method = RequestMethod.GET)
		protected ModelAndView loggedInDriverReports(HttpServletRequest request,HttpServletResponse response,@ModelAttribute(value="driverFilter") DriverRegistratioModel driverFilter ,BindingResult result)throws javax.servlet.ServletException, IOException {
			 map.put("driverFilter", driverFilter);
			return new ModelAndView("backEndDriverReports");
		}
	  
	@ResponseBody
	@RequestMapping(value = "/driverReports")
	protected void driverReports(HttpServletRequest request,HttpServletResponse response) throws javax.servlet.ServletException, IOException {
	try{
		UserDetail 	userDetail 	=(UserDetail) request.getSession().getAttribute("userDetail");
		if(userDetail!=null && !userDetail.getUserRole().equals("") && userDetail.getUserRole().equals("Admin") ||userDetail.getUserRole().equals("Agent")  ){
    	
		String action = request.getParameter("action");
		List<DriverRegistration> allPendingBookingList = new ArrayList<DriverRegistration>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
		if (action != null) {
			try {
				if (action.equals("list")) {
					int startPageIndex = Integer.parseInt(request
							.getParameter("jtStartIndex"));
					int recordsPerPage = Integer.parseInt(request
							.getParameter("jtPageSize"));

					//allPendingBookingList = registrationService.getRegisteredDriverList(byOrganization)//.getPendingBookings(startPageIndex, recordsPerPage);
					if(allPendingBookingList!=null){
					//int totalCount = bookingService.getPendingBookings().size();
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Records", allPendingBookingList);
					//JSONROOT.put("TotalRecordCount", totalCount);
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
				//	bookingService.removeDriverDeviceVehicleMapping(byid);
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
		}}else{
			response.getWriter().print("Your request is not valide !");
		}}catch(Exception er){er.printStackTrace();}

	}
}
