package com.trux.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.trux.model.ClientMandate;
import com.trux.model.ClientMandateBinder;
import com.trux.model.ClientMandateDetail;
import com.trux.model.ClientMandateDetailDto;
import com.trux.model.ClientMandateDetailsTrip;
import com.trux.model.ClientVehicleDeployment;
import com.trux.model.OrganizationMasterRegistration;
import com.trux.model.TaxType;
import com.trux.model.UserDetail;
import com.trux.service.ClientMandateDetailService;
import com.trux.service.ClientMandateDetailsTripService;
import com.trux.service.ClientMandateService;
import com.trux.service.ClientVehicleDeploymentService;
import com.trux.service.OrganizationMasterService;
import com.trux.service.TaxTypeService;
import com.trux.utils.DateFormaterUtils;

@Controller
@RequestMapping(value = "/clients")
// clients/mandate
public class ClientMandateController {

	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

	@Autowired
	private OrganizationMasterService organizationMasterService;
	@Autowired
	ClientMandateService clientMandateService;
	@Autowired
	ClientMandateDetailService clientMandateDetailService;
	@Autowired
	ClientVehicleDeploymentService clientVehicleDeploymentService;
	@Autowired
	TaxTypeService taxTypeService;
	@Autowired
	ClientMandateDetailsTripService clientMandateDetailsTripService;

	@ResponseBody
	@RequestMapping(value = "/mandate", method = RequestMethod.GET)
	public ModelAndView mandate(HttpServletRequest request,
			HttpServletResponse response) {

		List<OrganizationMasterRegistration> list = organizationMasterService
				.getOrganizationMasterRegistration();
		request.setAttribute("orgList", list);
		return new ModelAndView("backEndMandate");
	}

	@ResponseBody
	@RequestMapping(value = "/editmandate", method = RequestMethod.GET)
	public ModelAndView editmandate(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute(value = "clientMandate") ClientMandateBinder clientMandate,
			BindingResult result) {

		List<OrganizationMasterRegistration> list = organizationMasterService
				.getOrganizationMasterRegistration();
		request.setAttribute("orgList", list);
		return new ModelAndView("backEndUpdateMandate");
	}

	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping(value = "/savemandate", method = RequestMethod.POST)
	public ModelAndView saveMandate(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Integer clientId,
			@RequestParam Integer clientSubId,
			@RequestParam String mandateType,
			@RequestParam String mandateStartDate,
			@RequestParam String mandateEndDate,
			@RequestParam(required = false) String vehicleTypeVal,
			@RequestParam(required = false) String vehicleBodyVal,
			@RequestParam(required = false) String countVal,
			@RequestParam(required = false) String weightVal,
			@RequestParam(required = false) String taxType) {
		try {
			ClientMandate saveBack = new ClientMandate();
			HttpSession session = request.getSession();
			UserDetail userDetail = (UserDetail) session
					.getAttribute("userDetail");
			Integer userId = 0;
			if (userDetail != null) {
				userId = userDetail.getId();
			}
			TaxType tax = taxTypeService.getTaxType();

			ClientMandate dto = new ClientMandate();
			dto.setClientId(clientId);
			dto.setClientSubId(clientSubId);
			dto.setCreatedBy(userId);

			if (taxType != null && taxType.equals("Full")) {
				dto.setFullTax(tax.getFullTax());
			}
			if (taxType != null && taxType.equals("Abated")) {
				dto.setAbettedTax(tax.getAbettedTax());
			}
			dto.setMandateStartDate(new Date(mandateStartDate));
			dto.setMandateEndDate(new Date(mandateEndDate));
			dto.setMandateType(mandateType);
			dto.setCreatedDate(new Date());
			dto.setMandateUnitType(weightVal);
			String[] totalVehicles = null;
			String[] vehicleTypeVals = null;
			String[] vehicleBodyVals = null;
			//if (countVal !=null && countVal.equals(""))
				totalVehicles = countVal.split(",");
			//if (vehicleTypeVal != null && vehicleTypeVal.equals(""))
				vehicleTypeVals = vehicleTypeVal.split(",");
			//if (vehicleBodyVal != null && vehicleBodyVal.equals(""))
				vehicleBodyVals = vehicleBodyVal.split(",");
			Integer totalVehicle = 0;
			if (totalVehicles != null) {
				for (int i = 0; i < totalVehicles.length; i++) {
					if (!totalVehicles[i].equals("")) {
						Integer total = new Integer(totalVehicles[i]);
						totalVehicle += total;
					}
				}
			}
			dto.setTotalVehicles(totalVehicle);

			saveBack = clientMandateService.saveClientMandate(dto);
			if (saveBack != null && saveBack.getClientMandateId() != 0) {
				List<ClientMandateDetail> md = new ArrayList<ClientMandateDetail>();
				// List<ClientVehicleDeployment> mdcvd=new
				// ArrayList<ClientVehicleDeployment>();
				if (totalVehicles != null) {
					for (int i = 0; i < totalVehicles.length; i++) {
						Integer total = null;
						if (mandateType.equals("Box")
								|| mandateType.equals("Weight")) {
							total = -1;
						} else {
							total = new Integer(totalVehicles[i]);
							totalVehicle += total;
						}

						ClientMandateDetail d = new ClientMandateDetail();
						d.setMandateId(saveBack.getClientMandateId());

						if (mandateType.equals("Box")
								|| mandateType.equals("Weight")) {
							d.setTotalVehicle(total);

						} else {
							d.setVehicleType(vehicleTypeVals[i]);
							d.setTotalVehicle(total);
							d.setBodyType(vehicleBodyVals[i]);
						}

						d.setIsActive(1);
						d.setCreatedBy(userId);
						d.setCreatedDate(new Date());
						ClientMandateDetail saveb = clientMandateDetailService
								.saveClientMandateDetail(d);
						StringBuilder mandateDeploy = null;
						if (saveb != null) {
							md.add(saveb);
							mandateDeploy = new StringBuilder();

							for (int t = 0; t < saveb.getTotalVehicle(); t++) {
								mandateDeploy
										.append("INSERT INTO client_vehicle_deployment (mandate_detail_id,mandate_type,vehicle_type,body_type,createddate,createdby,is_active) VALUES("
												+ saveb.getMandateDetailId()
												+ ",'"
												+ mandateType
												+ "','"
												+ saveb.getVehicleType()
												+ "','"
												+ saveb.getBodyType()
												+ "',NOW()," + userId + ",1); ");

								/*
								 * ClientVehicleDeployment cvDe=new
								 * ClientVehicleDeployment();
								 * cvDe.setBodyType(saveb.getBodyType());
								 * cvDe.setMandateDetailId
								 * (saveb.getMandateDetailId());
								 * cvDe.setMandateType(saveb.getMandateType());
								 * cvDe.setIsActive(1);
								 * cvDe.setVehicleType(saveb.getVehicleType());
								 * cvDe.setCreatedBy(userId);
								 * cvDe.setCreatedDate(new Date());
								 * cvDe.setMandateType(mandateType);
								 */
								// String
								// sql=" INSERT INTO client_vehicle_deployment (mandate_detail_id,mandate_type,vehicle_type,body_type,createddate,createdby,is_active) VALUES("+saveb.getMandateDetailId()+",'"+mandateType+"','"+saveb.getVehicleType()+"','"+saveb.getBodyType()+"',NOW(),"+userId+",1); ";

								clientVehicleDeploymentService
										.saveClientVehicleDeployments(mandateDeploy);
								mandateDeploy = new StringBuilder();
								System.out.println(mandateDeploy.toString());
							}

							/*
							 * if(mandateDeploy!=null){
							 * clientVehicleDeploymentService
							 * .saveClientVehicleDeployments(mandateDeploy); }
							 */
						}
					}
				}
			}
			List<OrganizationMasterRegistration> list = // subsidiaryClientUserService.getOrgClient(userId);//

			organizationMasterService.getOrganizationMasterRegistration();
			request.setAttribute("orgList", list);
			request.setAttribute("saveBack", saveBack);
		} catch (Exception er) {
			er.printStackTrace();
			List<OrganizationMasterRegistration> list = // subsidiaryClientUserService.getOrgClient(userId);//
			organizationMasterService.getOrganizationMasterRegistration();
			request.setAttribute("orgList", list);
			return new ModelAndView("backEndMandate");
		}
		return new ModelAndView("backEndMandate");
	}

	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping(value = "/updatemandate", method = RequestMethod.POST)
	public ModelAndView updatemandate(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam Integer clientMandateId,
			@RequestParam Integer clientId, @RequestParam Integer clientSubId,
			@RequestParam String mandateType,
			@RequestParam String mandateStartDate,
			@RequestParam String mandateEndDate) {
		HttpSession session = request.getSession();
		UserDetail userDetail = (UserDetail) session.getAttribute("userDetail");
		Integer userId = 0;
		if (userDetail != null) {
			userId = userDetail.getId();
		}

		ClientMandate dto = new ClientMandate();
		dto.setClientId(clientId);
		dto.setClientSubId(clientSubId);
		dto.setCreatedBy(userId);
		dto.setClientMandateId(clientMandateId);
		dto.setMandateStartDate(new Date(mandateStartDate));
		dto.setMandateEndDate(new Date(mandateEndDate));
		dto.setMandateType(mandateType);
		dto.setCreatedDate(new Date());
		ClientMandate saveBack = clientMandateService.saveClientMandate(dto);
		List<OrganizationMasterRegistration> list = // subsidiaryClientUserService.getOrgClient(userId);//
		organizationMasterService.getOrganizationMasterRegistration();
		request.setAttribute("orgList", list);
		request.setAttribute("saveBack", saveBack);
		return new ModelAndView("backEndUpdateMandate");
	}

	@ResponseBody
	@RequestMapping(value = "/searchtoupdatemandate", method = RequestMethod.POST)
	public ModelAndView searchtoupdatemandate(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Integer clientId,
			@RequestParam Integer clientSubId, @RequestParam String mondateType) {
		List<OrganizationMasterRegistration> list = organizationMasterService
				.getOrganizationMasterRegistration();
		request.setAttribute("orgList", list);

		request.setAttribute("clientId", clientId);
		request.setAttribute("clientSubId", clientSubId);
		request.setAttribute("mondateType", mondateType);

		return new ModelAndView("backEndMandateDetails");
	}

	@ResponseBody
	@RequestMapping(value = "/openSearchMandate", method = RequestMethod.GET)
	public ModelAndView openSearchMandate(HttpServletRequest request,
			HttpServletResponse response) {
		List<OrganizationMasterRegistration> list = organizationMasterService
				.getOrganizationMasterRegistration();
		request.setAttribute("orgList", list);
		return new ModelAndView("backEndUpdateMandate");
	}

	@ResponseBody
	@RequestMapping(value = "/searchMandate", method = RequestMethod.POST)
	public ModelAndView searchMandate(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Integer clientId,
			@RequestParam(required = false) Integer clientSubId,
			@RequestParam(required = false) String mondateType) {
		List<OrganizationMasterRegistration> list = organizationMasterService
				.getOrganizationMasterRegistration();
		request.setAttribute("orgList", list);

		request.setAttribute("clientId", clientId);
		request.setAttribute("clientSubId", clientSubId);
		request.setAttribute("mondateType", mondateType);

		return new ModelAndView("backEndUpdateMandate");
	}

	@ResponseBody
	@RequestMapping(value = "/searchToUodateMandateDetails", method = RequestMethod.POST)
	public ModelAndView searchToUodateMandateDetails(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer clientId, @RequestParam Integer clientSubId,
			@RequestParam String mandateType) {
		List<OrganizationMasterRegistration> list = organizationMasterService
				.getOrganizationMasterRegistration();
		request.setAttribute("orgList", list);

		request.setAttribute("clientId", clientId);
		request.setAttribute("clientSubId", clientSubId);
		request.setAttribute("mandateType", mandateType);

		return new ModelAndView("backEndMandateDetails");
	}

	@ResponseBody
	@RequestMapping(value = "/searchmandate", method = RequestMethod.GET)
	public List<ClientMandate> searchmandate(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Integer clientId,
			@RequestParam Integer clientSubId) {

		List<ClientMandate> saveBackList = clientMandateService
				.getMandateCSUBId(clientId, clientSubId);

		return saveBackList;
	}

	@ResponseBody
	@RequestMapping(value = "/searchByMandateType", method = RequestMethod.GET)
	public List<ClientMandate> searchByMandateType(HttpServletRequest request,
			HttpServletResponse response, @RequestParam Integer clientId,
			@RequestParam Integer clientSubId, @RequestParam String mandateType) {
		List<ClientMandate> saveBackList = clientMandateService
				.getMandateCSUBIdAndMandateType(clientId, clientSubId,
						mandateType);

		return saveBackList;
	}

	@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping(value = "/searchMandateReports")
	protected void searchMandateReports(HttpServletRequest request,
			HttpServletResponse response)
			throws javax.servlet.ServletException, IOException {
		try {
			Integer clientId = null;
			Integer clientSubId = null;
			String clientIds = request.getParameter("clientId");
			try {
				if (clientIds != null && !clientIds.equals("null")) {
					clientId = new Integer(clientIds);
				}

			} catch (Exception er) {
			}
			UserDetail userDetail = (UserDetail) request.getSession()
					.getAttribute("userDetail");
			String action = request.getParameter("action");
			List<ClientMandate> clientMandateList = new ArrayList<ClientMandate>();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			response.setContentType("application/json");
			if (action != null) {
				try {
					if (action.equals("list")) {
						int startPageIndex = Integer.parseInt(request
								.getParameter("jtStartIndex"));
						int recordsPerPage = Integer.parseInt(request
								.getParameter("jtPageSize"));

						if (clientId != null) {
							clientMandateList = clientMandateService
									.getMandateCSUBId(clientId, startPageIndex,
											recordsPerPage);

							if (clientMandateList != null) {
								int totalCount = clientMandateList.size();
								JSONROOT.put("Result", "OK");
								JSONROOT.put("Records", clientMandateList);
								JSONROOT.put("TotalRecordCount", totalCount);
								String jsonArray = gson.toJson(JSONROOT);
								response.getWriter().print(jsonArray);
							} else {
								JSONROOT.put("Result", "No Records Available !");
								JSONROOT.put("Message", "No Records Found !");
								String error = gson.toJson(JSONROOT);
								response.getWriter().print(error);
							}
						}
					} else if (action.equals("update")) {
						
						
						
						
						
						
						String mandateStartDate = request
								.getParameter("mandateStartDates");
						String mandateEndDate = request
								.getParameter("mandateEndDates");
						String mandateType = request
								.getParameter("mandateType");
						Integer clientMandateId = null;
						String id = request.getParameter("clientMandateId");
						String mandateDetailsId = request
								.getParameter("mandateDetailsId");
						String vehicleType = request
								.getParameter("vehicleType");
						String bodyType = request.getParameter("bodyType");
						String totalNoOfVehicle = request
								.getParameter("totalNoOfVehicle");

						if (id != null) {
							clientMandateId = new Integer(id);
						}
						HttpSession session = request.getSession();
						userDetail = (UserDetail) session
								.getAttribute("userDetail");
						Integer userId = 0;
						if (userDetail != null) {
							userId = userDetail.getId();
						}
						try {
							ClientMandate dto = new ClientMandate();
							dto.setClientId(clientId);
							dto.setClientSubId(clientSubId);
							dto.setModifiedBy(userId);
							dto.setClientMandateId(clientMandateId);
							if (totalNoOfVehicle != null
									&& !totalNoOfVehicle.equals("")) {
								dto.setTotalVehicles(new Integer(
										totalNoOfVehicle));
							}
							dto.setMandateStartDate(new Date(DateFormaterUtils
									.dateFormateToUpdate(mandateStartDate)));
							dto.setMandateEndDate(new Date(DateFormaterUtils
									.dateFormateToUpdate(mandateEndDate)));
							dto.setMandateType(mandateType);
							ClientMandate updateMandate = clientMandateService
									.updateMandate(dto);
							if (updateMandate != null
									& updateMandate.getClientMandateId() != 0) {
								clientMandateId = updateMandate
										.getClientMandateId();
							}
						} catch (Exception er) {
							er.printStackTrace();
						}

						try {
							//
							Integer total = new Integer(totalNoOfVehicle);
							// totalVehicle+= total;
							ClientMandateDetail d = new ClientMandateDetail();
							d.setMandateId(clientMandateId);
							if (mandateDetailsId != null
									&& !mandateDetailsId.equals("")) {
								d.setMandateDetailId(new Integer(
										mandateDetailsId));
							} else {
								d.setMandateDetailId(null);
							}
							d.setVehicleType(vehicleType);
							d.setTotalVehicle(total);
							d.setBodyType(bodyType);
							d.setIsActive(1);
							d.setCreatedBy(userId);
							d.setCreatedDate(new Date());
							ClientMandateDetail updateMandateD = clientMandateDetailService.saveClientMandateDetail(d);
							int totalsDeploy = 0;
							if (updateMandateD != null) {
								ClientVehicleDeployment dto = new ClientVehicleDeployment();
								dto.setBodyType(bodyType);
								dto.setVehicleType(vehicleType);
								dto.setMandateType(mandateType);
								dto.setMandateDetailId(updateMandateD.getMandateDetailId());
								totalsDeploy = clientVehicleDeploymentService.totalClientVehicleDeployments(dto);
							}
							StringBuilder mandateDeploy = null;
							if (updateMandateD != null) {
								mandateDeploy = new StringBuilder();
								if (totalsDeploy <= updateMandateD.getTotalVehicle()) {
									int totalGoToInser = updateMandateD.getTotalVehicle() - totalsDeploy;
									for (int t = 0; t < totalGoToInser; t++) {
										mandateDeploy
												.append("INSERT INTO client_vehicle_deployment (mandate_detail_id,mandate_type,vehicle_type,body_type,createddate,createdby,is_active) VALUES("
														+ updateMandateD
																.getMandateDetailId()
														+ ",'"
														+ mandateType
														+ "','"
														+ updateMandateD
																.getVehicleType()
														+ "','"
														+ updateMandateD
																.getBodyType()
														+ "',NOW(),"
														+ userId
														+ ",1); ");
										clientVehicleDeploymentService
												.saveClientVehicleDeployments(mandateDeploy);
										mandateDeploy = new StringBuilder();
										System.out.println(mandateDeploy
												.toString());
									}
								} else {
//									int totalGoToInser = totalsDeploy - updateMandateD.getTotalVehicle();
									
									
									
//									for (int t = 0; t < totalGoToInser; t++) {
//										mandateDeploy
//												.append("INSERT INTO client_vehicle_deployment (mandate_detail_id,mandate_type,vehicle_type,body_type,createddate,createdby,is_active) VALUES("
//														+ updateMandateD
//																.getMandateDetailId()
//														+ ",'"
//														+ mandateType
//														+ "','"
//														+ updateMandateD
//																.getVehicleType()
//														+ "','"
//														+ updateMandateD
//																.getBodyType()
//														+ "',NOW(),"
//														+ userId
//														+ ",1); ");
//										clientVehicleDeploymentService
//												.saveClientVehicleDeployments(mandateDeploy);
//										mandateDeploy = new StringBuilder();
//										System.out.println(mandateDeploy
//												.toString());
//									}
								}
							}
							if (totalsDeploy == 0) {
//								for (int t = 0; t < total; t++) {
//									mandateDeploy
//											.append("INSERT INTO client_vehicle_deployment (mandate_detail_id,mandate_type,vehicle_type,body_type,createddate,createdby,is_active) VALUES("
//													+ updateMandateD
//															.getMandateDetailId()
//													+ ",'"
//													+ mandateType
//													+ "','"
//													+ updateMandateD
//															.getVehicleType()
//													+ "','"
//													+ updateMandateD
//															.getBodyType()
//													+ "',NOW(),"
//													+ userId
//													+ ",1); ");
//									clientVehicleDeploymentService
//											.saveClientVehicleDeployments(mandateDeploy);
//									mandateDeploy = new StringBuilder();
//									System.out
//											.println(mandateDeploy.toString());
//								}
							}
							//
							JSONROOT.put("Result", "OK");
							JSONROOT.put("Message", "Updated !");
							String jsonArray = gson.toJson(JSONROOT);
							response.getWriter().print(jsonArray);
						} catch (Exception er) {
							JSONROOT.put("Result", "OK");
							JSONROOT.put(
									"Message",
									"Your request is not processed at "
											+ er.getMessage());
							String jsonArray = gson.toJson(JSONROOT);
							response.getWriter().print(jsonArray);
						}
						
						
						
						
						
						
						
						
						
					} else if (action.equals("delete")) {
						try {
							Integer clientMandateId = null;
							String id = request.getParameter("clientMandateId");
							if (id != null) {
								clientMandateId = new Integer(id);
							}
							ClientMandate dto = new ClientMandate();
							dto.setClientMandateId(clientMandateId);
							if (clientMandateId != null) {
								clientMandateService.deleteMandate(dto);
								JSONROOT.put("Result", "OK");
								JSONROOT.put("Message", "Deleted !");
								String jsonArray = gson.toJson(JSONROOT);
								response.getWriter().print(jsonArray);
							} else {
								JSONROOT.put("Result", "OK");
								JSONROOT.put("Message",
										"Your request is not processed!");
								String jsonArray = gson.toJson(JSONROOT);
								response.getWriter().print(jsonArray);

							}

						} catch (Exception er) {
							JSONROOT.put("Result", "OK");
							JSONROOT.put(
									"Message",
									"Request not proccessed at "
											+ er.getMessage());
							String jsonArray = gson.toJson(JSONROOT);
							response.getWriter().print(jsonArray);
						}

					}

				} catch (Exception ex) {
					JSONROOT.put("Result", "Message");
					JSONROOT.put("Message", ex.getMessage());
					String error = gson.toJson(JSONROOT);
					response.getWriter().print(error);
				}
			}
		} catch (Exception er) {
			er.printStackTrace();
		}

	}

	@ResponseBody
	@RequestMapping(value = "/searchDetailsByMandateType", method = RequestMethod.GET)
	public List<ClientMandateDetail> searchDetailsByMandateType(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer clientId, @RequestParam Integer clientSubId,
			@RequestParam String mandateType) {

		List<ClientMandateDetail> clientMandateDetailsList = clientMandateDetailsList = clientMandateService
				.getClientMandateDetail(clientId, clientSubId, mandateType);

		return clientMandateDetailsList;
	}

	@ResponseBody
	@RequestMapping(value = "/saveMadateTripDetails", method = RequestMethod.GET)
	public List<ClientMandateDetailsTrip> saveMadateTripDetails(
			HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer mandateDetailId,
			@RequestParam String locationA, @RequestParam String locationB,
			@RequestParam String billingRate,
			@RequestParam String nightHoldingCharge) {
		String[] locationAVal = locationA.split(",");
		String[] locationBVal = locationB.split(",");
		String[] billingRateVal = billingRate.split(",");
		String[] nightHoldingChargeVal = nightHoldingCharge.split(",");
		List<ClientMandateDetailsTrip> clientMandateDetailsList = new ArrayList<ClientMandateDetailsTrip>();
		HttpSession session = request.getSession();
		UserDetail userDetail = (UserDetail) session.getAttribute("userDetail");
		Integer userId = 0;
		if (userDetail != null) {
			userId = userDetail.getId();
		}
		for (int i = 0; i < locationAVal.length; i++) {
			ClientMandateDetailsTrip dto = new ClientMandateDetailsTrip();
			dto.setMandateDetailId(mandateDetailId);
			String locationsA = locationAVal[i].replaceAll("~", ",");
			String locationsB = locationBVal[i].replaceAll("~", ",");
			dto.setPointA(locationsA);
			dto.setPointB(locationsB);
			if (billingRateVal[i] != null && !billingRateVal[i].equals("")) {
				dto.setBillingRate(new Double(billingRateVal[i]));
			}
			if (nightHoldingChargeVal[i] != null
					&& !nightHoldingChargeVal[i].equals("")) {
				dto.setNightHoldingCharges(new Double(nightHoldingChargeVal[i]));
			}

			dto.setCreatedBy(userId);
			dto.setCreatedDate(new Date());
			ClientMandateDetailsTrip dd = clientMandateDetailsTripService
					.saveClientMandateDetailsTrip(dto);
			if (dd != null) {
				clientMandateDetailsList.add(dd);
			}
		}
		return clientMandateDetailsList;
	}

	@ResponseBody
	@RequestMapping(value = "/searchMandateDetailsReports")
	protected void searchMandateDetailsReports(HttpServletRequest request,
			HttpServletResponse response)
			throws javax.servlet.ServletException, IOException {
		try {
			Integer clientId = null;
			Integer clientSubId = null;
			String clientIds = request.getParameter("clientId");
			String clientSubIds = request.getParameter("clientSubId");
			String mondateType = request.getParameter("mandateType");
			try {
				if (clientIds != null && !clientIds.equals("null")) {
					clientId = new Integer(clientIds);
				}
				if (clientSubIds != null && !clientSubIds.equals("null")) {
					clientSubId = new Integer(clientSubIds);
				}

			} catch (Exception er) {
			}
			UserDetail userDetail = (UserDetail) request.getSession()
					.getAttribute("userDetail");
			String action = request.getParameter("action");
			List<ClientMandateDetail> clientMandateList = new ArrayList<ClientMandateDetail>();
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			response.setContentType("application/json");
			if (action != null) {
				try {
					if (action.equals("list")) {
						int startPageIndex = Integer.parseInt(request
								.getParameter("jtStartIndex"));
						int recordsPerPage = Integer.parseInt(request
								.getParameter("jtPageSize"));

						if (clientId != null && clientSubId != null) {
							clientMandateList = clientMandateService
									.getClientMandateDetail(clientId,
											clientSubId, mondateType,
											startPageIndex, recordsPerPage);

							if (clientMandateList != null) {
								int totalCount = clientMandateList.size();
								JSONROOT.put("Result", "OK");
								JSONROOT.put("Records", clientMandateList);
								JSONROOT.put("TotalRecordCount", totalCount);
								String jsonArray = gson.toJson(JSONROOT);
								response.getWriter().print(jsonArray);
							} else {
								JSONROOT.put("Result", "No Records Available !");
								JSONROOT.put("Message", "No Records Found !");
								String error = gson.toJson(JSONROOT);
								response.getWriter().print(error);
							}
						}
					} else if (action.equals("update")) {

						String mandateType = request
								.getParameter("mandateType");
						Integer mandateDetailsId = null;
						Integer fixDays = null;
						Integer fixHours = null;
						Integer fixKms = null;
						Integer moqs = null;
						Double extraMkCharges = null;
						Double extraHourCharges = null;
						Double billingRates = null;
						Double nightHoldingCharges = null;

						String id = request.getParameter("mandateDetailId");
						String fixDay = request.getParameter("fixDays");
						String fixHour = request.getParameter("fixHour");
						String fixKm = request.getParameter("fixKm");
						String moq = request.getParameter("moq");
						String extraHourCharge = request
								.getParameter("extraHourCharge");
						String extraKmCharge = request
								.getParameter("extraKmCharge");
						String billingRate = request
								.getParameter("billingRate");
						String nightHoldingCharge = request
								.getParameter("nightHoldingCharge");
						if (id != null) {
							mandateDetailsId = new Integer(id);
						}
						if (fixDay != null) {
							fixDays = new Integer(fixDay);
						}
						if (fixHour != null) {
							fixHours = new Integer(fixHour);
						}
						if (fixKm != null) {
							fixKms = new Integer(fixKm);
						}

						if (moq != null) {
							moqs = new Integer(moq);
						}
						if (extraHourCharge != null) {
							extraHourCharges = new Double(extraHourCharge);
						}
						if (extraKmCharge != null) {
							extraMkCharges = new Double(extraKmCharge);
						}
						if (billingRate != null) {
							billingRates = new Double(billingRate);
						}
						if (nightHoldingCharge != null) {
							nightHoldingCharges = new Double(nightHoldingCharge);
						}
						HttpSession session = request.getSession();
						userDetail = (UserDetail) session
								.getAttribute("userDetail");
						Integer userId = 0;
						if (userDetail != null) {
							userId = userDetail.getId();
						}
						try {
							ClientMandateDetail dto = new ClientMandateDetail();

							if (mandateDetailsId != null)
								dto.setMandateDetailId(mandateDetailsId);
							if (fixDays != null)
								dto.setFixDays(fixDays);
							if (fixHours != null)
								dto.setFixHour(fixHours);
							if (fixKms != null)
								dto.setFixKm(fixKms);
							if (extraHourCharges != null)
								dto.setExtraHourCharge(extraHourCharges);
							if (extraMkCharges != null)
								dto.setExtraKmCharge(extraMkCharges);
							if (billingRates != null)
								dto.setBillingRate(billingRates);
							if (nightHoldingCharges != null)
								dto.setNightHoldingCharge(nightHoldingCharges);
							if (moqs != null)
								dto.setMoq(moqs);
							if (userId != 0)
								dto.setModifiedBy(userId);
							if (mandateType != null)
								dto.setMandateType(mandateType);

							clientMandateService.updateMandateDetails(dto);
							JSONROOT.put("Result", "OK");
							JSONROOT.put("Message", "Updated !");
							String jsonArray = gson.toJson(JSONROOT);
							response.getWriter().print(jsonArray);
						} catch (Exception er) {
							JSONROOT.put("Result", "OK");
							JSONROOT.put(
									"Message",
									"Your request is not processed at "
											+ er.getMessage());
							String jsonArray = gson.toJson(JSONROOT);
							response.getWriter().print(jsonArray);
						}
					} else if (action.equals("delete")) {
						try {
							Integer clientMandateId = null;
							String id = request.getParameter("clientMandateId");
							if (id != null) {
								clientMandateId = new Integer(id);
							}
							ClientMandate dto = new ClientMandate();
							dto.setClientMandateId(clientMandateId);
							if (clientMandateId != null) {
								clientMandateService.deleteMandate(dto);
								JSONROOT.put("Result", "OK");
								JSONROOT.put("Message", "Deleted !");
								String jsonArray = gson.toJson(JSONROOT);
								response.getWriter().print(jsonArray);
							} else {
								JSONROOT.put("Result", "OK");
								JSONROOT.put("Message",
										"Your request is not processed!");
								String jsonArray = gson.toJson(JSONROOT);
								response.getWriter().print(jsonArray);

							}

						} catch (Exception er) {
							JSONROOT.put("Result", "OK");
							JSONROOT.put(
									"Message",
									"Request not proccessed at "
											+ er.getMessage());
							String jsonArray = gson.toJson(JSONROOT);
							response.getWriter().print(jsonArray);
						}

					}

				} catch (Exception ex) {
					JSONROOT.put("Result", "Message");
					JSONROOT.put("Message", ex.getMessage());
					String error = gson.toJson(JSONROOT);
					response.getWriter().print(error);
				}
			}
		} catch (Exception er) {
			er.printStackTrace();
		}

	}

	@ResponseBody
	@RequestMapping(value = "/mandateDetails", method = RequestMethod.GET)
	public ModelAndView mandateDetails(HttpServletRequest request,
			HttpServletResponse response) {

		List<OrganizationMasterRegistration> list = organizationMasterService
				.getOrganizationMasterRegistration();
		request.setAttribute("orgList", list);
		return new ModelAndView("backEndMandateDetails");
	}

	@ResponseBody
	@RequestMapping(value = "/saveMandateDetails", method = RequestMethod.POST)
	public ModelAndView saveMandateDetails(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute(value = "clientMandateDetails") ClientMandateDetailDto clientMandateDetails,
			BindingResult result) {

		List<OrganizationMasterRegistration> list = organizationMasterService
				.getOrganizationMasterRegistration();
		request.setAttribute("orgList", list);
		HttpSession session = request.getSession();
		UserDetail userDetail = (UserDetail) session.getAttribute("userDetail");
		Integer userId = 0;
		if (userDetail != null) {
			userId = userDetail.getId();
		}
		String mandateType = clientMandateDetails.getMandateType();
		ClientMandateDetail dto = null;
		if (mandateType.trim().equals("Fix")) {
			dto = new ClientMandateDetail();
			dto.setMandateId(clientMandateDetails.getClientMandateId());
			dto.setVehicleType(clientMandateDetails.getFixVehicleType());
			dto.setFixDays(new Integer(clientMandateDetails
					.getFixMandateDayDate()));
			dto.setFixKm(new Integer(clientMandateDetails.getFixMkPerMonth()));
			dto.setFixHour(new Integer(clientMandateDetails.getFixHrsPerDay()));
			// dto.setInvoiceAmount(clientMandateDetails.getFixInvoiceAmount());
			dto.setTotalVehicle(new Integer(clientMandateDetails
					.getFixVehicleNumber()));
			dto.setCreatedBy(userId);
			dto.setCreatedDate(new Date());
			ClientMandateDetail saveBack = clientMandateDetailService
					.saveClientMandateDetail(dto);
			request.setAttribute("saveBack", saveBack);
		}

		if (mandateType.trim().equals("Weight")) {
			dto = new ClientMandateDetail();
			dto.setMandateId(clientMandateDetails.getClientMandateId());
			dto.setVehicleType(clientMandateDetails.getWeightVehicleType());
			// dto.setInvoiceAmount(clientMandateDetails.getWeightInvoiceAmount());
			dto.setTotalVehicle(new Integer(clientMandateDetails
					.getWeightVehicleNumber()));
			dto.setCreatedBy(userId);
			dto.setCreatedDate(new Date());
			ClientMandateDetail saveBack = clientMandateDetailService
					.saveClientMandateDetail(dto);
			request.setAttribute("saveBack", saveBack);
		}
		if (mandateType.trim().equals("Adhoc")) {
			dto = new ClientMandateDetail();
			dto.setMandateId(clientMandateDetails.getClientMandateId());
			dto.setVehicleType(clientMandateDetails.getFixVehicleType());
			dto.setFixDays(new Integer(clientMandateDetails
					.getFixMandateDayDate()));
			dto.setFixKm(new Integer(clientMandateDetails.getFixMkPerMonth()));
			dto.setFixHour(new Integer(clientMandateDetails.getFixHrsPerDay()));
			// dto.setInvoiceAmount(clientMandateDetails.getFixInvoiceAmount());
			dto.setTotalVehicle(new Integer(clientMandateDetails
					.getFixVehicleNumber()));
			dto.setCreatedBy(userId);
			dto.setCreatedDate(new Date());
			ClientMandateDetail saveBack = clientMandateDetailService
					.saveClientMandateDetail(dto);
			request.setAttribute("saveBack", saveBack);
		}

		if (mandateType.trim().equals("Box")) {
			dto = new ClientMandateDetail();
			dto.setMandateId(clientMandateDetails.getClientMandateId());
			// dto.setInvoiceAmount(clientMandateDetails.getBoxInvoiceAmount());
			dto.setCreatedBy(userId);
			dto.setCreatedDate(new Date());
			ClientMandateDetail saveBack = clientMandateDetailService
					.saveClientMandateDetail(dto);
			request.setAttribute("saveBack", saveBack);
		}

		if (mandateType.trim().equals("Trip")) {
			dto = new ClientMandateDetail();
			dto.setMandateId(clientMandateDetails.getClientMandateId());
			/*
			 * dto.setTripKmEnd(clientMandateDetails.getTripEndKmPerday());
			 * dto.setInvoiceAmount
			 * (clientMandateDetails.getTripInvoiceAmount());
			 * dto.setTripKmStart(clientMandateDetails.getTripStartKmPerday());
			 */dto.setCreatedBy(userId);
			dto.setCreatedDate(new Date());
			ClientMandateDetail saveBack = clientMandateDetailService
					.saveClientMandateDetail(dto);
			request.setAttribute("saveBack", saveBack);
		}

		System.out.println(clientMandateDetails.getClientMandateId() + ""
				+ clientMandateDetails.getClientId() + " "
				+ clientMandateDetails.getClientSubId() + " "
				+ clientMandateDetails.getMandateType() + " "
				+ clientMandateDetails.getFixVehicleType() + " "
				+ clientMandateDetails.getBoxInvoiceAmount() + " "
				+ clientMandateDetails.getFixHrsPerDay() + " "
				+ clientMandateDetails.getFixInvoiceAmount() + " "
				+ clientMandateDetails.getFixMandateDayDate() + " "
				+ clientMandateDetails.getFixMkPerMonth() + " "
				+ clientMandateDetails.getFixVehicleNumber() + " "
				+ clientMandateDetails.getTripEndKmPerday() + " "
				+ clientMandateDetails.getTripInvoiceAmount());

		return new ModelAndView("backEndMandateDetails");
	}

	@ResponseBody
	@RequestMapping(value = "/updateMandateDetails", method = RequestMethod.GET)
	public ModelAndView updateMandateDetails(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute(value = "clientMandateDetails") ClientMandateDetailDto clientMandateDetails,
			BindingResult result) {

		List<OrganizationMasterRegistration> list = organizationMasterService
				.getOrganizationMasterRegistration();
		request.setAttribute("orgList", list);
		return new ModelAndView("backEndUpdateMandateDetails");
	}

	@ResponseBody
	@RequestMapping(value = "/updateExistMandateDetails", method = RequestMethod.POST)
	public ModelAndView updateExistMandateDetails(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute(value = "clientMandateDetails") ClientMandateDetailDto clientMandateDetails,
			BindingResult result) {

		List<OrganizationMasterRegistration> list = organizationMasterService
				.getOrganizationMasterRegistration();
		request.setAttribute("orgList", list);
		HttpSession session = request.getSession();
		UserDetail userDetail = (UserDetail) session.getAttribute("userDetail");
		Integer userId = 0;
		if (userDetail != null) {
			userId = userDetail.getId();
		}
		String mandateType = clientMandateDetails.getMandateType();
		ClientMandateDetail dto = null;
		if (mandateType.trim().equals("Fix")) {
			dto = new ClientMandateDetail();
			dto.setMandateDetailId(clientMandateDetails.getMandateDetailId());
			dto.setMandateId(clientMandateDetails.getClientMandateId());
			dto.setVehicleType(clientMandateDetails.getFixVehicleType());
			try {
				dto.setFixDays(new Integer(clientMandateDetails
						.getFixMandateDayDate()));
				dto.setFixKm(new Integer(clientMandateDetails
						.getFixMkPerMonth()));
				dto.setFixHour(new Integer(clientMandateDetails
						.getFixHrsPerDay()));
			} catch (Exception er) {
				er.printStackTrace();
			}
			// dto.setInvoiceAmount(clientMandateDetails.getFixInvoiceAmount());
			dto.setTotalVehicle(new Integer(clientMandateDetails
					.getFixVehicleNumber()));
			dto.setModifiedBy(userId);
			dto.setModifiedDate(new Date());
			ClientMandateDetail saveBack = clientMandateDetailService
					.updateClientMandateDetail(dto);
			request.setAttribute("saveBack", saveBack);
		}

		if (mandateType.trim().equals("Weight")) {
			dto = new ClientMandateDetail();
			dto.setMandateDetailId(clientMandateDetails.getMandateDetailId());
			dto.setMandateId(clientMandateDetails.getClientMandateId());
			dto.setVehicleType(clientMandateDetails.getWeightVehicleType());
			// dto.setInvoiceAmount(clientMandateDetails.getWeightInvoiceAmount());
			dto.setTotalVehicle(new Integer(clientMandateDetails
					.getWeightVehicleNumber()));
			dto.setModifiedBy(userId);
			dto.setModifiedDate(new Date());
			ClientMandateDetail saveBack = clientMandateDetailService
					.updateClientMandateDetail(dto);
			request.setAttribute("saveBack", saveBack);
		}
		if (mandateType.trim().equals("Adhoc")) {
			dto = new ClientMandateDetail();
			dto.setMandateDetailId(clientMandateDetails.getMandateDetailId());
			dto.setMandateId(clientMandateDetails.getClientMandateId());
			dto.setVehicleType(clientMandateDetails.getFixVehicleType());
			try {
				dto.setFixDays(new Integer(clientMandateDetails
						.getFixMandateDayDate()));
				dto.setFixKm(new Integer(clientMandateDetails
						.getFixMkPerMonth()));
				dto.setFixHour(new Integer(clientMandateDetails
						.getFixHrsPerDay()));
			} catch (Exception er) {
				er.printStackTrace();
			}
			// dto.setInvoiceAmount(clientMandateDetails.getFixInvoiceAmount());
			dto.setTotalVehicle(new Integer(clientMandateDetails
					.getFixVehicleNumber()));
			dto.setModifiedBy(userId);
			dto.setModifiedDate(new Date());
			ClientMandateDetail saveBack = clientMandateDetailService
					.updateClientMandateDetail(dto);
			request.setAttribute("saveBack", saveBack);
		}

		if (mandateType.trim().equals("Box")) {
			dto = new ClientMandateDetail();
			dto.setMandateDetailId(clientMandateDetails.getMandateDetailId());
			dto.setMandateId(clientMandateDetails.getClientMandateId());
			// dto.setInvoiceAmount(clientMandateDetails.getBoxInvoiceAmount());
			dto.setModifiedBy(userId);
			dto.setModifiedDate(new Date());
			ClientMandateDetail saveBack = clientMandateDetailService
					.updateClientMandateDetail(dto);
			request.setAttribute("saveBack", saveBack);
		}

		if (mandateType.trim().equals("Trip")) {
			dto = new ClientMandateDetail();
			dto.setMandateDetailId(clientMandateDetails.getMandateDetailId());
			dto.setMandateId(clientMandateDetails.getClientMandateId());
			/*
			 * dto.setTripKmEnd(clientMandateDetails.getTripEndKmPerday());
			 * dto.setInvoiceAmount
			 * (clientMandateDetails.getTripInvoiceAmount());
			 * dto.setTripKmStart(clientMandateDetails.getTripStartKmPerday());
			 */dto.setModifiedBy(userId);
			dto.setModifiedDate(new Date());
			ClientMandateDetail saveBack = clientMandateDetailService
					.updateClientMandateDetail(dto);
			request.setAttribute("saveBack", saveBack);
		}
		System.out.println(clientMandateDetails.getClientMandateId() + ""
				+ clientMandateDetails.getClientId() + " "
				+ clientMandateDetails.getClientSubId() + " "
				+ clientMandateDetails.getMandateType() + " "
				+ clientMandateDetails.getFixVehicleType() + " "
				+ clientMandateDetails.getBoxInvoiceAmount() + " "
				+ clientMandateDetails.getFixHrsPerDay() + " "
				+ clientMandateDetails.getFixInvoiceAmount() + " "
				+ clientMandateDetails.getFixMandateDayDate() + " "
				+ clientMandateDetails.getFixMkPerMonth() + " "
				+ clientMandateDetails.getFixVehicleNumber() + " "
				+ clientMandateDetails.getTripEndKmPerday() + " "
				+ clientMandateDetails.getTripInvoiceAmount());

		return new ModelAndView("backEndUpdateMandateDetails");
	}
}
