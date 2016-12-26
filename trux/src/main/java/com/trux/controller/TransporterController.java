package com.trux.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.trux.model.CRFOrder;
import com.trux.model.Cities;
import com.trux.model.CommunicationSMS;
import com.trux.model.ControllerDAOTracker;
import com.trux.model.ExcelFile;
import com.trux.model.FileBean;
import com.trux.model.GCMContent;
import com.trux.model.RestResponce;
import com.trux.model.States;
import com.trux.model.TransporterClientOrderMapping;
import com.trux.model.TransporterClientOrders;
import com.trux.model.TransporterOrderFollowUp;
import com.trux.model.TransporterRegistration;
import com.trux.model.UserDetail;
import com.trux.model.VehicleType;
import com.trux.service.ModuleService;
import com.trux.service.TransporterRegistrationService;
import com.trux.utils.GoogleMapsUtils;
import com.trux.utils.POST2GCM;
import com.trux.utils.TruxUtils;

@Controller
@RequestMapping(value = "/transport")
public class TransporterController {

	public List<States> allStates = null;
	public List<VehicleType> allVehicleType = null;
	public List<VehicleType> allVehicle = new ArrayList<VehicleType>(8);
	public List<VehicleType> v1 = null;

	public List<CRFOrder> allOrder = null;

	@Autowired
	private ModuleService moduleService;

	@Autowired
	private TransporterRegistrationService transporterRegistrationService;

	@RequestMapping(value = "/createTransporterOrder", method = RequestMethod.GET)
	public ModelAndView createTransporterOrder(HttpServletRequest request, HttpServletResponse response) {
		allStates = null;
		allStates = moduleService.getStatesListForACountry(101);
		HttpSession session = request.getSession();
		if (allStates != null) {
			session.setAttribute("statesList", allStates);
		}

		allVehicleType = null;
		allVehicleType = moduleService.getVehicleType();
		if (allVehicleType != null) {
			session.setAttribute("allVehicleType", allVehicleType);
		}

		return new ModelAndView("backEndCreateTransporterOrder");
	}

	/* ADDED BY SHAHZAD HUSSAIN */

	@RequestMapping(value = "/updateTransporterOrder", method = RequestMethod.GET)
	public ModelAndView updateTransporterOrder(ModelMap model, HttpServletRequest request,
			HttpServletResponse response) {
		model.addAttribute("registrationServiceList", transporterRegistrationService.getAllOrder());
		allStates = null;
		allStates = moduleService.getStatesListForACountry(101);
		HttpSession session = request.getSession();
		if (allStates != null) {
			session.setAttribute("statesList", allStates);
		}
		return new ModelAndView("backEndUpdateTransporterOrder");

	}

	@RequestMapping(value = "/importExcel", method = RequestMethod.GET)
	public ModelAndView importExcel(ModelMap model, HttpServletRequest request,
			@ModelAttribute("fileBean") FileBean fileBean, HttpServletResponse response) {

		return new ModelAndView("import");

	}

	@RequestMapping(value = "/saveExcel", method = RequestMethod.POST)
	public ModelAndView saveExcel(ModelMap model, @ModelAttribute("fileBean") FileBean fileBean,
			HttpServletRequest request) {
		List<ExcelFile> excelFiles = new ArrayList<ExcelFile>();
		// Map<String, String> map = new HashMap<String, String>();
		try {
			HttpSession session = request.getSession();
			UserDetail userDetail = (UserDetail) session.getAttribute("userDetail");
			Integer userId = 0;
			if (userDetail != null) {
				userId = userDetail.getId();
			}
			String saveDirectory = "/home/trux/shahzad/saveDirectory/";
			MultipartFile crunchifyFiles = fileBean.getFileData();
			String fileName = crunchifyFiles.getOriginalFilename();
			System.out.println(saveDirectory + fileName);
			InputStream input = new FileInputStream(saveDirectory + fileName);
			Workbook wb = new XSSFWorkbook(input);
			Sheet sheet = wb.getSheetAt(0);
			System.out.println(sheet.getPhysicalNumberOfRows());
			Row row;
			for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
				row = sheet.getRow(i);
				int id = (int) row.getCell(0).getNumericCellValue();
				String source_state = row.getCell(1).getStringCellValue();
				System.out.println(source_state);
				String source_city = row.getCell(2).getStringCellValue();
				System.out.println(source_city);
				String destination_state = row.getCell(3).getStringCellValue();
				System.out.println(destination_state);
				String destination_city = row.getCell(4).getStringCellValue();
				System.out.println(destination_city);
				int distance = (int) row.getCell(5).getNumericCellValue();
				Double freight_rate = (Double) row.getCell(6).getNumericCellValue();
				Double pickup = (Double) row.getCell(7).getNumericCellValue();
				Double tata407 = (Double) row.getCell(8).getNumericCellValue();
				Double tata709 = (Double) row.getCell(9).getNumericCellValue();
				Double feet_12 = (Double) row.getCell(10).getNumericCellValue();
				Double feet_17 = (Double) row.getCell(11).getNumericCellValue();
				Double feet_19 = (Double) row.getCell(12).getNumericCellValue();
				Double feet_22 = (Double) row.getCell(13).getNumericCellValue();
				Double feet_24 = (Double) row.getCell(14).getNumericCellValue();
				Double feet_32_single = (Double) row.getCell(15).getNumericCellValue();
				Double feet_32_multi = (Double) row.getCell(16).getNumericCellValue();
				Double feet_40_multi = (Double) row.getCell(17).getNumericCellValue();
				Double feet_34_highcube = (Double) row.getCell(18).getNumericCellValue();
				Double feet_36_highcube = (Double) row.getCell(19).getNumericCellValue();
				int a = 0;
				ExcelFile excelFile = new ExcelFile();
				if (id != 0 && id > 0) {
					System.out.println("ID==" + id);
					excelFile.setId(id);
				} else {

				}
				States states = new States();

				if (source_state != null && !source_state.isEmpty()) {
					states.setStateName(source_state);
				} else {
				}
				List<States> states1 = transporterRegistrationService.getStateName(states);
				List<Cities> fromCity = null;
				if (states1 != null && states1.size() > 0 && states1.equals("")) {
					Cities cities = new Cities();
					if (source_city != null && !source_city.isEmpty())
						cities.setCityName(source_city);
					fromCity = transporterRegistrationService.getCityName(cities);

					if (fromCity != null && fromCity.size() > 0) {
						if (fromCity.get(0).getStateId().equals(states1.get(0).getStateId())) {
							excelFile.setSource_state_id(states1.get(0).getStateId());
							excelFile.setSource_city_id(fromCity.get(0).getCityId());
						} else {
							excelFiles.add(excelFile);
						}
					} else {
						excelFiles.add(excelFile);
					}
				} else {
					excelFiles.add(excelFile);
				}

				if (destination_state != null && !destination_state.isEmpty())
					states.setStateName(destination_state);
				states1 = transporterRegistrationService.getStateName(states);
				List<Cities> toCity = null;
				if (states1 != null && states1.size() > 0) {
					Cities cities = new Cities();
					if (destination_city != null && !destination_city.isEmpty())
						cities.setCityName(destination_city);
					toCity = transporterRegistrationService.getCityName(cities);
					if (toCity != null && toCity.size() > 0) {
						if (toCity.get(0).getStateId().equals(states1.get(0).getStateId())) {
							excelFile.setDestination_state_id(states1.get(0).getStateId());
							excelFile.setDestination_city_id(toCity.get(0).getCityId());

							double dist = 0.0;
							if (fromCity != null && toCity != null) {
								dist = GoogleMapsUtils.getDistance(source_city + ", " + source_state + ", India ",
										destination_city + ", " + destination_state + ", India");
								System.out.println("Distance= " + dist);
								excelFile.setDistance(dist);
							} else {
							}

							VehicleType vehicleType = new VehicleType();

							/* For Tata ACE */
							if (freight_rate != null && freight_rate != 0) {
								double d = freight_rate;
								Long L = Math.round(d);
								int x = Integer.valueOf(L.intValue());
								excelFile.setRate((double) x);
								System.out.println("freight_rate" + freight_rate);
								System.out.println("X= " + x);

								vehicleType.setVehicleName("Tata Ace");
								List<VehicleType> v1 = transporterRegistrationService.getVehicleType(vehicleType);
								if (v1 != null && v1.size() > 0) {
									ControllerDAOTracker TR = transporterRegistrationService.getRecoredBy(
											fromCity.get(0).getCityId(), toCity.get(0).getCityId(), v1.get(0).getId());

									if (TR.isSuccess()) {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setModifiedBy(userId);
										excelFile.setModifiedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.updateFile(excelFile);
									} else {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setCreatedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.saveFile(excelFile);
									}
								}
							} else {
							}
							/* Bolero Pickup */
							if (pickup != null && pickup != 0) {
								double d = pickup;
								Long L = Math.round(d);
								int x = Integer.valueOf(L.intValue());
								excelFile.setRate((double) x);
								vehicleType.setVehicleName("Bolero Pickup");
								List<VehicleType> v1 = transporterRegistrationService.getVehicleType(vehicleType);
								if (v1 != null && v1.size() > 0) {
									ControllerDAOTracker TR = transporterRegistrationService.getRecoredBy(
											fromCity.get(0).getCityId(), toCity.get(0).getCityId(), v1.get(0).getId());

									if (TR.isSuccess()) {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setModifiedBy(userId);
										excelFile.setModifiedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.updateFile(excelFile);
									} else {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setCreatedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.saveFile(excelFile);
									}
								} else {

								}
							} else {
							}
							/* FOR taTA 407 */
							if (tata407 != null && tata407 != 0) {
								double d = tata407;
								Long L = Math.round(d);
								int x = Integer.valueOf(L.intValue());
								excelFile.setRate((double) x);
								vehicleType.setVehicleName("Tata 407");
								List<VehicleType> v1 = transporterRegistrationService.getVehicleType(vehicleType);
								if (v1 != null && v1.size() > 0) {
									ControllerDAOTracker TR = transporterRegistrationService.getRecoredBy(
											fromCity.get(0).getCityId(), toCity.get(0).getCityId(), v1.get(0).getId());

									if (TR.isSuccess()) {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setModifiedBy(userId);
										excelFile.setModifiedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.updateFile(excelFile);
									} else {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setCreatedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.saveFile(excelFile);
									}
								}
							} else {
							}

							/* FOR taTA 709 */

							if (tata709 != null && tata709 != 0) {
								double d = tata709;
								Long L = Math.round(d);
								int x = Integer.valueOf(L.intValue());
								excelFile.setRate((double) x);
								vehicleType.setVehicleName("Tata 709");
								List<VehicleType> v1 = transporterRegistrationService.getVehicleType(vehicleType);
								if (v1 != null && v1.size() > 0) {
									ControllerDAOTracker TR = transporterRegistrationService.getRecoredBy(
											fromCity.get(0).getCityId(), toCity.get(0).getCityId(), v1.get(0).getId());

									if (TR.isSuccess()) {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setModifiedBy(userId);
										excelFile.setModifiedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.updateFile(excelFile);
									} else {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setCreatedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.saveFile(excelFile);
									}
								}
							} else {
							}
							/* FOR 12 Feet Single Axle */

							if (feet_12 != null && feet_12 != 0) {
								double d = feet_12;
								Long L = Math.round(d);
								int x = Integer.valueOf(L.intValue());
								excelFile.setRate((double) x);
								vehicleType.setVehicleName("12 Feet Single Axle");
								List<VehicleType> v1 = transporterRegistrationService.getVehicleType(vehicleType);
								if (v1 != null && v1.size() > 0) {
									ControllerDAOTracker TR = transporterRegistrationService.getRecoredBy(
											fromCity.get(0).getCityId(), toCity.get(0).getCityId(), v1.get(0).getId());

									if (TR.isSuccess()) {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setModifiedBy(userId);
										excelFile.setModifiedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.updateFile(excelFile);
									} else {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setCreatedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.saveFile(excelFile);
									}
								}
							} else {

							}

							/* FOR 17 Feet Single Axle */

							if (feet_17 != null && feet_17 != 0) {
								double d = feet_17;
								Long L = Math.round(d);
								int x = Integer.valueOf(L.intValue());
								excelFile.setRate((double) x);

								vehicleType.setVehicleName("17 Feet Single Axle");
								List<VehicleType> v1 = transporterRegistrationService.getVehicleType(vehicleType);
								if (v1 != null && v1.size() > 0) {
									ControllerDAOTracker TR = transporterRegistrationService.getRecoredBy(
											fromCity.get(0).getCityId(), toCity.get(0).getCityId(), v1.get(0).getId());

									if (TR.isSuccess()) {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setModifiedBy(userId);
										excelFile.setModifiedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.updateFile(excelFile);
									} else {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setCreatedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.saveFile(excelFile);
									}
								}
							} else {
							}
							/* for 19 Feet Single Axle */
							if (feet_19 != null && feet_19 != 0) {
								double d = feet_19;
								Long L = Math.round(d);
								int x = Integer.valueOf(L.intValue());
								excelFile.setRate((double) x);

								vehicleType.setVehicleName("19 Feet Single Axle");
								List<VehicleType> v1 = transporterRegistrationService.getVehicleType(vehicleType);
								if (v1 != null && v1.size() > 0) {
									ControllerDAOTracker TR = transporterRegistrationService.getRecoredBy(
											fromCity.get(0).getCityId(), toCity.get(0).getCityId(), v1.get(0).getId());

									if (TR.isSuccess()) {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setModifiedBy(userId);
										excelFile.setModifiedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.updateFile(excelFile);
									} else {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setCreatedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.saveFile(excelFile);
									}
								}
							} else {
							}

							/* for 22 Feet Single Axle */
							if (feet_22 != null && feet_22 != 0) {
								double d = feet_22;
								Long L = Math.round(d);
								int x = Integer.valueOf(L.intValue());
								excelFile.setRate((double) x);
								vehicleType.setVehicleName("22 Feet Single Axle");
								List<VehicleType> v1 = transporterRegistrationService.getVehicleType(vehicleType);
								if (v1 != null && v1.size() > 0) {
									ControllerDAOTracker TR = transporterRegistrationService.getRecoredBy(
											fromCity.get(0).getCityId(), toCity.get(0).getCityId(), v1.get(0).getId());

									if (TR.isSuccess()) {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setModifiedBy(userId);
										excelFile.setModifiedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.updateFile(excelFile);
									} else {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setCreatedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.saveFile(excelFile);
									}
								}
							} else {
							}
							/* for 24 Feet Multi-Axle */
							if (feet_24 != null && feet_12 != 0) {
								double d = feet_24;
								Long L = Math.round(d);
								int x = Integer.valueOf(L.intValue());
								excelFile.setRate((double) x);

								vehicleType.setVehicleName("24 Feet Multi-Axle");
								List<VehicleType> v1 = transporterRegistrationService.getVehicleType(vehicleType);
								if (v1 != null && v1.size() > 0) {
									ControllerDAOTracker TR = transporterRegistrationService.getRecoredBy(
											fromCity.get(0).getCityId(), toCity.get(0).getCityId(), v1.get(0).getId());

									if (TR.isSuccess()) {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setModifiedBy(userId);
										excelFile.setModifiedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.updateFile(excelFile);
									} else {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setCreatedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.saveFile(excelFile);
									}
								}
							} else {
							}
							/* for 32 Feet Single Axle */
							if (feet_32_single != null && feet_32_single != 0) {
								double d = feet_32_single;
								Long L = Math.round(d);
								int x = Integer.valueOf(L.intValue());
								excelFile.setRate((double) x);

								vehicleType.setVehicleName("32 Feet Single Axle");
								List<VehicleType> v1 = transporterRegistrationService.getVehicleType(vehicleType);
								if (v1 != null && v1.size() > 0) {
									ControllerDAOTracker TR = transporterRegistrationService.getRecoredBy(
											fromCity.get(0).getCityId(), toCity.get(0).getCityId(), v1.get(0).getId());

									if (TR.isSuccess()) {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setModifiedBy(userId);
										excelFile.setModifiedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.updateFile(excelFile);
									} else {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setCreatedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.saveFile(excelFile);
									}
								}
							} else {
							}

							/* for 32 Feet Double Axle */
							if (feet_32_multi != null && feet_32_multi != 0) {
								double d = feet_32_multi;
								Long L = Math.round(d);
								int x = Integer.valueOf(L.intValue());
								excelFile.setRate((double) x);
								vehicleType.setVehicleName("32 Feet Double Axle");
								List<VehicleType> v1 = transporterRegistrationService.getVehicleType(vehicleType);
								if (v1 != null && v1.size() > 0) {
									ControllerDAOTracker TR = transporterRegistrationService.getRecoredBy(
											fromCity.get(0).getCityId(), toCity.get(0).getCityId(), v1.get(0).getId());

									if (TR.isSuccess()) {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setModifiedBy(userId);
										excelFile.setModifiedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.updateFile(excelFile);
									} else {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setCreatedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.saveFile(excelFile);
									}
								}
							} else {
							}
							/* for 40 Feet Multi-Axle */
							if (feet_40_multi != null && feet_40_multi != 0) {
								double d = feet_40_multi;
								Long L = Math.round(d);
								int x = Integer.valueOf(L.intValue());
								excelFile.setRate((double) x);
								vehicleType.setVehicleName("40 Feet Multi-Axle");
								List<VehicleType> v1 = transporterRegistrationService.getVehicleType(vehicleType);
								if (v1 != null && v1.size() > 0) {
									ControllerDAOTracker TR = transporterRegistrationService.getRecoredBy(
											fromCity.get(0).getCityId(), toCity.get(0).getCityId(), v1.get(0).getId());

									if (TR.isSuccess()) {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setModifiedBy(userId);
										excelFile.setModifiedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.updateFile(excelFile);
									} else {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setCreatedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.saveFile(excelFile);
									}
								}
							} else {
							}
							/* for Mahindra Champion */
							if (feet_34_highcube != null && feet_34_highcube != 0) {
								double d = feet_34_highcube;
								Long L = Math.round(d);
								int x = Integer.valueOf(L.intValue());
								excelFile.setRate((double) x);

								vehicleType.setVehicleName("Mahindra Champion");
								List<VehicleType> v1 = transporterRegistrationService.getVehicleType(vehicleType);
								if (v1 != null && v1.size() > 0) {
									ControllerDAOTracker TR = transporterRegistrationService.getRecoredBy(
											fromCity.get(0).getCityId(), toCity.get(0).getCityId(), v1.get(0).getId());

									if (TR.isSuccess()) {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setModifiedBy(userId);
										excelFile.setModifiedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.updateFile(excelFile);
									} else {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setCreatedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.saveFile(excelFile);
									}
								}
							} else {
							}
							/* for Maruti Eeco */
							if (feet_36_highcube != null && feet_36_highcube != 0) {
								double d = feet_36_highcube;
								Long L = Math.round(d);
								int x = Integer.valueOf(L.intValue());
								excelFile.setRate((double) x);

								vehicleType.setVehicleName("Maruti Eeco");
								List<VehicleType> v1 = transporterRegistrationService.getVehicleType(vehicleType);
								if (v1 != null && v1.size() > 0) {
									ControllerDAOTracker TR = transporterRegistrationService.getRecoredBy(
											fromCity.get(0).getCityId(), toCity.get(0).getCityId(), v1.get(0).getId());

									if (TR.isSuccess()) {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setModifiedBy(userId);
										excelFile.setModifiedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.updateFile(excelFile);
									} else {
										excelFile.setVehicle_type_id(v1.get(0).getId());
										excelFile.setCreatedBy(userId);
										excelFile.setCreatedOn(new Date());
										excelFile.setIsActive(1);
										transporterRegistrationService.saveFile(excelFile);
									}
								}
							} else {
							}

						} else {
							excelFiles.add(excelFile);
						}
					} else {
						excelFiles.add(excelFile);
					}
				} else {
					excelFiles.add(excelFile);
				}
			}
			model.addAttribute("files", "Your File is Uploaded Succesfully");
		} catch (Exception e) {
			model.addAttribute("files", "Something is wrong");
			e.printStackTrace();
		}
		model.addAttribute("msg", excelFiles);
		return new ModelAndView("importsuccess");

	}

	@RequestMapping(value = "/downloadExcel", method = RequestMethod.GET)
	public ModelAndView downloadExcel(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		allStates = null;
		allStates = moduleService.getStatesListForACountry(101);
		if (allStates != null) {
			transporterRegistrationService.getExcel();
			session.setAttribute("statesList", allStates);
		}
		return new ModelAndView("downloadExcel");
	}

	@ResponseBody
	@RequestMapping(value = "/getVehicleList", method = RequestMethod.POST)
	private String getVehicleList(HttpServletRequest request, HttpServletResponse response) {
		StringBuilder citiOptionsList = new StringBuilder();
		try {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			String[] s1 = request.getParameterValues("vehicle");
			for (String s : s1) {
				out.println("<li>" + s + "</li>");
			}
			// boolean v1 = request.getParameter("vehicle") != null;
			// if(v1 ==true){
			// allVehicle = transporterRegistrationService.getVehicle("Tata
			// Ace");
			// }
			// boolean v2 = request.getParameter("vehicle1") != null;
			// if(v2 ==true){
			// allVehicle = transporterRegistrationService.getVehicle("Bolero
			// Pickup");
			// }
			// boolean v3 = request.getParameter("vehicle2") != null;
			// if(v3 ==true){
			// allVehicle = transporterRegistrationService.getVehicle("Tata
			// 407");
			// }
			//
			//
			//
			//
			// if (allVehicle != null && !allVehicle.isEmpty()) {
			// for (VehicleType city : allVehicle) {
			// System.out.println("vehicleId = "+city.getId());
			// citiOptionsList.append(city.getId());
			// }
			//
			// }
			return citiOptionsList.toString();
		} catch (Exception er) {
			er.printStackTrace();
			return citiOptionsList.toString();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/exportToExcel", method = RequestMethod.POST)
	public RestResponce exportToExcel(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) Integer fromCity, @RequestParam(required = false) Integer toCity,
			@RequestParam(required = false) Boolean vehicle, @RequestParam(required = false) Boolean vehicle1,
			@RequestParam(required = false) Boolean vehicle2, @RequestParam(required = false) Boolean vehicle3,
			@RequestParam(required = false) Boolean vehicle4, @RequestParam(required = false) Boolean vehicle5,
			@RequestParam(required = false) Boolean vehicle6, @RequestParam(required = false) Boolean vehicle7,
			@RequestParam(required = false) Boolean vehicle8, @RequestParam(required = false) Boolean vehicle9,
			@RequestParam(required = false) Boolean vehicle10, @RequestParam(required = false) Boolean vehicle11,
			@RequestParam(required = false) Boolean vehicle12, @RequestParam(required = false) Boolean vehicle13) {

		RestResponce restResponce = new RestResponce();

		int c = 0;

		if (vehicle == true && vehicle != false) {
			allVehicle.add(c, transporterRegistrationService.getVehicle("Tata Ace").get(0));
			c++;
		}
		if (vehicle1 == true && vehicle1 != false) {
			allVehicle.add(c, transporterRegistrationService.getVehicle("Bolero Pickup").get(0));
			c++;
		}
		if (vehicle2 == true && vehicle2 != false) {
			allVehicle.add(c, transporterRegistrationService.getVehicle("Tata 407").get(0));
			c++;
		}
		if (vehicle3 == true && vehicle3 != false) {
			allVehicle.add(c, transporterRegistrationService.getVehicle("Tata 709").get(0));
			c++;
		}
		if (vehicle4 == true) {
			allVehicle.add(c, transporterRegistrationService.getVehicle("12 Feet Single Axle").get(0));
			c++;
		}
		if (vehicle5 == true) {
			allVehicle.add(c, transporterRegistrationService.getVehicle("17 Feet Single Axle").get(0));
			c++;
		}
		if (vehicle6 == true) {
			allVehicle.add(c, transporterRegistrationService.getVehicle("19 Feet Single Axle").get(0));
			c++;
		}
		if (vehicle7 == true) {
			allVehicle.add(c, transporterRegistrationService.getVehicle("22 Feet Single Axle").get(0));
			c++;
		}
		if (vehicle8 == true) {
			allVehicle.add(c, transporterRegistrationService.getVehicle("24 Feet Multi-Axle").get(0));
			c++;
		}
		if (vehicle9 == true) {
			allVehicle.add(c, transporterRegistrationService.getVehicle("32 Feet Single Axle").get(0));
			c++;
		}

		if (vehicle10 == true) {
			allVehicle.add(c, transporterRegistrationService.getVehicle("32 Feet Double Axle").get(0));
			c++;
		}

		if (vehicle11 == true) {
			allVehicle.add(c, transporterRegistrationService.getVehicle("40 Feet Multi-Axle").get(0));
			c++;
		}

		if (vehicle12 == true) {
			allVehicle.add(c, transporterRegistrationService.getVehicle("Mahindra Champion").get(0));
			c++;
		}

		if (vehicle13 == true) {
			allVehicle.add(c, transporterRegistrationService.getVehicle("Maruti Eeco").get(0));
			c++;
		}

		List<?> list = transporterRegistrationService.savToExcel(fromCity, toCity, allVehicle);

		if (list != null && list.size() > 0) {
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
	@RequestMapping(value = "/getAllOrder", method = RequestMethod.POST)
	public RestResponce getAllOrder(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) Integer fromCityId, @RequestParam(required = false) Integer toCityId)
			throws ParseException {
		try {
			RestResponce restResponce = new RestResponce();

			ControllerDAOTracker saveBack = transporterRegistrationService.getOrder(fromCityId, toCityId);

			if (saveBack.isSuccess()) {
				restResponce.setErrorCode(saveBack.getErrorCode());
				restResponce.setErrorMesaage(saveBack.getErrorMessage());
				restResponce.setData(saveBack.getData());
			} else {
				restResponce.setErrorCode(saveBack.getErrorCode());
				restResponce.setErrorMesaage(saveBack.getErrorMessage());
			}

			return restResponce;
		} catch (Exception e) {
			RestResponce restResponcee = new RestResponce();
			restResponcee.setErrorCode("101");
			restResponcee.setErrorMesaage(e.toString());
			return restResponcee;
		}

	}

	@ResponseBody
	@RequestMapping(value = "/saveClientOrder", method = RequestMethod.POST)
	public RestResponce saveClientOrders(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer vehicleTypeId, @RequestParam String vehicleBody, @RequestParam Date deployDateTime,
			@RequestParam(required = false) Integer fromStateId, @RequestParam Integer fromCityId,
			@RequestParam(required = false) Integer toStateId, @RequestParam Integer toCityId,
			@RequestParam Integer price) throws ParseException {

		try {

			RestResponce restResponce = new RestResponce();

			TransporterClientOrders tco = new TransporterClientOrders();

			tco.setVehicleTypeId(vehicleTypeId);
			tco.setVehicleBody(vehicleBody);
			tco.setDeployDateTime(deployDateTime);
			tco.setFromCityId(fromCityId);
			tco.setToCityId(toCityId);
			tco.setPrice(price);

			HttpSession session = request.getSession();
			UserDetail userDetail = (UserDetail) session.getAttribute("userDetail");
			Integer userId = 0;
			if (userDetail != null) {
				userId = userDetail.getId();
			}

			tco.setCreatedBy(userId);
			tco.setCreatedOn(new Date());
			tco.setModifiedBy(userId);
			tco.setModifiedOn(new Date());

			tco.setIsActive(1);

			ControllerDAOTracker TR = transporterRegistrationService.createClientOrder(tco);

			if (TR.isSuccess()) {
				session.setAttribute("saveBack", TR);

				/////////////////// GCM PUSH ////////////////////////

				String apiKey = transporterRegistrationService.getServerApi("trux_transporter");

				GCMContent content = new GCMContent();

				content.createData("Trux Transporter", "New order available");

				List<String> registration_ids = transporterRegistrationService.getGCM();
				content.setRegistration_ids(registration_ids);

				POST2GCM.post(apiKey, content);

				/////////////////// GCM PUSH ////////////////////////

				// allStates=null;
				// allStates=moduleService.getStatesListForACountry(101);
				// if(allStates!=null){
				// session.setAttribute("statesList", allStates);
				// }
				//
				// allVehicleType = null;
				// allVehicleType = moduleService.getVehicleType();
				// if(allVehicleType!=null){
				// session.setAttribute("allVehicleType", allVehicleType);
				// }
				restResponce.setErrorCode(TR.getErrorCode());
				restResponce.setErrorMesaage(TR.getErrorMessage());
				restResponce.setData(TR.getTransporterClientOrders());

				return restResponce;

				// return new ModelAndView("backEndCreateTransporterOrder");
			} else {
				session.setAttribute("saveBack", TR);

				// allStates=null;
				// allStates=moduleService.getStatesListForACountry(101);
				// if(allStates!=null){
				// session.setAttribute("statesList", allStates);
				// }
				//
				// allVehicleType = null;
				// allVehicleType = moduleService.getVehicleType();
				// if(allVehicleType!=null){
				// session.setAttribute("allVehicleType", allVehicleType);
				// }

				restResponce.setErrorCode(TR.getErrorCode());
				restResponce.setErrorMesaage(TR.getErrorMessage());
				// restResponce.setData(TR.getTransporterClientOrders());

				return restResponce;

				// return new ModelAndView("backEndCreateTransporterOrder");
			}
		} catch (Exception e) {
			RestResponce restResponcee = new RestResponce();
			restResponcee.setErrorCode("101");
			restResponcee.setErrorMesaage(e.toString());
			return restResponcee;
		}

	}

	@RequestMapping(value = "/searchTransporterOrder", method = RequestMethod.GET)
	public ModelAndView searchTransporterOrder(HttpServletRequest request, HttpServletResponse response) {
		allStates = null;
		allStates = moduleService.getStatesListForACountry(101);
		HttpSession session = request.getSession();
		if (allStates != null) {
			session.setAttribute("statesList", allStates);
		}

		allVehicleType = null;
		allVehicleType = moduleService.getVehicleType();
		if (allVehicleType != null) {
			session.setAttribute("allVehicleType", allVehicleType);
		}

		return new ModelAndView("backEndSearchTransporterOrder");
	}

	@RequestMapping(value = "/approveTransporterOrder", method = RequestMethod.GET)
	public ModelAndView approveTransporterOrder(HttpServletRequest request, HttpServletResponse response) {
		allStates = null;
		allStates = moduleService.getStatesListForACountry(101);
		HttpSession session = request.getSession();
		if (allStates != null) {
			session.setAttribute("statesList", allStates);
		}

		return new ModelAndView("backEndApproveTransporterOrder");
	}

	@RequestMapping(value = "/publishCRFOrder", method = RequestMethod.GET)
	public ModelAndView publishCRFOrder(HttpServletRequest request, HttpServletResponse response) {
		allStates = null;
		allStates = moduleService.getStatesListForACountry(101);
		HttpSession session = request.getSession();
		if (allStates != null) {
			session.setAttribute("statesList", allStates);
		}

		allVehicleType = null;
		allVehicleType = moduleService.getVehicleType();
		if (allVehicleType != null) {
			session.setAttribute("allVehicleType", allVehicleType);
		}

		allOrder = moduleService.getAllOrders();
		if (allVehicleType != null) {
			session.setAttribute("allOrder", allOrder);
		}

		return new ModelAndView("backEndpublishCRFOrder");
	}

	@ResponseBody
	@RequestMapping(value = "/approveAllCRFOrder", method = RequestMethod.POST)
	public RestResponce approveAllCRFOrder(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(required = false) Integer fromCityId, @RequestParam(required = false) Integer toCityId,
			@RequestParam(required = false) String status) throws ParseException {

		try {

			RestResponce restResponce = new RestResponce();

			ControllerDAOTracker saveBack = transporterRegistrationService.searchOrder(fromCityId, toCityId, status);

			if (saveBack.isSuccess()) {
				restResponce.setErrorCode(saveBack.getErrorCode());
				restResponce.setErrorMesaage(saveBack.getErrorMessage());
				restResponce.setData(saveBack.getData());
			} else {
				restResponce.setErrorCode(saveBack.getErrorCode());
				restResponce.setErrorMesaage(saveBack.getErrorMessage());
			}

			return restResponce;
		} catch (Exception e) {
			RestResponce restResponcee = new RestResponce();
			restResponcee.setErrorCode("101");
			restResponcee.setErrorMesaage(e.toString());
			return restResponcee;
		}

	}

	@ResponseBody
	@RequestMapping(value = "/allOrderByDate", method = RequestMethod.POST)
	public RestResponce allOrderByDate(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String date) {

		try {
			RestResponce restResponce = new RestResponce();

			ControllerDAOTracker cdt = moduleService.getFilterOrder(date);

			if (cdt.isSuccess()) {
				restResponce.setErrorCode("100");
				restResponce.setErrorMesaage("Success");
				restResponce.setData(cdt.getData());
			} else {
				restResponce.setErrorCode("101");
				restResponce.setErrorMesaage("Failure");
			}

			return restResponce;
		} catch (Exception e) {
			RestResponce restResponcee = new RestResponce();
			restResponcee.setErrorCode("101");
			restResponcee.setErrorMesaage(e.toString());
			return restResponcee;
		}

	}

	@ResponseBody
	@RequestMapping(value = "/approveCRFOrder", method = RequestMethod.POST)
	public RestResponce approveCRFOrder(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer orderId, @RequestParam Integer vehicleTypeId, @RequestParam Integer sourceCityId,
			@RequestParam Integer destinationCityId, @RequestParam String deployTime, @RequestParam Integer freightRate)
			throws ParseException {

		try {

			RestResponce restResponce = new RestResponce();

			TransporterClientOrders tco = new TransporterClientOrders();

			tco.setId(orderId);
			tco.setVehicleTypeId(vehicleTypeId);
			tco.setFromCityId(sourceCityId);
			tco.setToCityId(destinationCityId);

			Date from = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH).parse(deployTime);
			String str = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).format(from);
			Date to = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH).parse(str);
			tco.setDeployDateTime(to);
			tco.setPrice(freightRate);
			tco.setIsActive(1);
			tco.setIsPublished(1);

			ControllerDAOTracker cdt = transporterRegistrationService.approveCRFOrder(tco);

			if (cdt.isSuccess()) {
				restResponce.setErrorCode("100");
				restResponce.setErrorMesaage(cdt.getErrorMessage());

			} else {
				restResponce.setErrorCode("101");
				restResponce.setErrorMesaage(cdt.getErrorMessage());
			}

			return restResponce;

		} catch (Exception e) {
			RestResponce restResponcee = new RestResponce();
			restResponcee.setErrorCode("101");
			restResponcee.setErrorMesaage(e.toString());
			return restResponcee;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/saveTransporterRegistration", method = RequestMethod.POST)
	public RestResponce saveTransporterRegistration(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String transportCompanyName, @RequestParam String contactPersonName,
			@RequestParam String mobileNumber, @RequestParam(required = false) String email, @RequestParam Integer city,
			@RequestParam Integer state, @RequestParam(required = false) Integer pincode,
			@RequestParam String vehicleCategory, @RequestParam Integer createdBy) throws ParseException {

		try {

			RestResponce restResponce = new RestResponce();

			TransporterRegistration tr = new TransporterRegistration();

			tr.setTransporterCompanyName(transportCompanyName);
			tr.setContactPersonName(contactPersonName);
			tr.setMobileNumber(mobileNumber.replace(" ", "").trim());
			tr.setPassword(TruxUtils.getRandomNumber(100000, 999999));
			tr.setEmail(email);
			tr.setCity(city);
			tr.setState(state);
			tr.setPincode(pincode);
			tr.setVehicleCategory(vehicleCategory);
			tr.setCreatedBy(createdBy);
			tr.setCreatedOn(new Date());
			tr.setModifiedBy(createdBy);
			tr.setModifiedOn(new Date());
			tr.setGcmId("987654321");
			tr.setIsActive(1);

			ControllerDAOTracker saveBack;

			ControllerDAOTracker TR = transporterRegistrationService
					.getTransporterByMobile(mobileNumber.replace(" ", "").trim());

			if (TR.isSuccess()) {
				restResponce.setErrorCode("101");
				restResponce.setErrorMesaage("Transporter already registered");
				return restResponce;
			} else {

				saveBack = transporterRegistrationService.saveTransporterRegistration(tr);

				if (saveBack.getErrorCode().equals("100")) {
					restResponce.setErrorCode(saveBack.getErrorCode());
					restResponce.setErrorMesaage(saveBack.getErrorMessage());
					restResponce.setData(saveBack.getTransporterRegistration());
				} else if (saveBack.getErrorCode().equals("200")) {
					restResponce.setErrorCode(saveBack.getErrorCode());
					restResponce.setErrorMesaage(saveBack.getErrorMessage());
					restResponce.setData(saveBack.getTransporterRegistration());
				} else {
					restResponce.setErrorCode(saveBack.getErrorCode());
					restResponce.setErrorMesaage(saveBack.getErrorMessage());
					restResponce.setData(saveBack.getTransporterRegistration());
				}

				CommunicationSMS transporterSMS = new CommunicationSMS();

				transporterSMS.setSenderMask("IM");
				transporterSMS.setSmsProvider("GUPSHUP");
				transporterSMS.setMobileNumber(mobileNumber);

				String smsText = "Dear Transporter,Thanks for registering with Trux. Download Android app using http://bit.ly/truxtrnsptr to receive orders. Username "
						+ saveBack.getTransporterRegistration().getMobileNumber() + " / Password "
						+ saveBack.getTransporterRegistration().getPassword();

				transporterSMS.setSmsText(smsText);
				transporterSMS.setRequestDate(new Date());
				transporterSMS.setRequestProcess(new Date());

				transporterRegistrationService.saveSMSRecord(transporterSMS);

				return restResponce;
			}
		} catch (Exception e) {
			RestResponce restResponcee = new RestResponce();
			restResponcee.setErrorCode("101");
			restResponcee.setErrorMesaage(e.toString());
			return restResponcee;
		}

	}

	@ResponseBody
	@RequestMapping("/getTransporterByMobile/{mobileNumber}")
	public RestResponce getTransporterByMobile(@PathVariable("mobileNumber") String mobileNumber) {

		try {
			RestResponce restResponce = new RestResponce();

			ControllerDAOTracker cdt = transporterRegistrationService.getTransporterDetails(mobileNumber);

			if (cdt.isSuccess()) {
				restResponce.setErrorCode(cdt.getErrorCode());
				restResponce.setErrorMesaage(cdt.getErrorMessage());
				restResponce.setData(cdt.getData());
			} else {
				restResponce.setErrorCode(cdt.getErrorCode());
				restResponce.setErrorMesaage(cdt.getErrorMessage());
			}

			return restResponce;
		} catch (Exception e) {
			RestResponce restResponcee = new RestResponce();
			restResponcee.setErrorCode("101");
			restResponcee.setErrorMesaage(e.toString());
			return restResponcee;
		}

	}

	@ResponseBody
	@RequestMapping(value = "/updateTransporterRegistration", method = RequestMethod.POST)
	public RestResponce updateTransporterRegistration(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer id, @RequestParam String transportCompanyName, @RequestParam String contactPersonName,
			@RequestParam String mobileNumber, @RequestParam(required = false) String email, @RequestParam Integer city,
			@RequestParam Integer state, @RequestParam Integer pincode, @RequestParam String vehicleCategory,
			@RequestParam Integer createdBy) throws ParseException {

		try {

			RestResponce restResponce = new RestResponce();

			TransporterRegistration tr = new TransporterRegistration();

			tr.setId(id);
			tr.setTransporterCompanyName(transportCompanyName);
			tr.setContactPersonName(contactPersonName);
			tr.setMobileNumber(mobileNumber.replace(" ", "").trim());
			// tr.setPassword(TruxUtils.getRandomNumber(100000, 999999));
			tr.setEmail(email);
			tr.setCity(city);
			tr.setState(state);
			tr.setPincode(pincode);
			tr.setVehicleCategory(vehicleCategory);
			tr.setCreatedBy(createdBy);
			tr.setCreatedOn(new Date());
			tr.setModifiedBy(createdBy);
			tr.setModifiedOn(new Date());
			tr.setIsActive(1);

			ControllerDAOTracker saveBack;

			saveBack = transporterRegistrationService.updateTransporterRegistration(tr);

			if (saveBack.getErrorCode().equals("100")) {
				restResponce.setErrorCode(saveBack.getErrorCode());
				restResponce.setErrorMesaage(saveBack.getErrorMessage());
				restResponce.setData(saveBack.getData());
			} else if (saveBack.getErrorCode().equals("200")) {
				restResponce.setErrorCode(saveBack.getErrorCode());
				restResponce.setErrorMesaage(saveBack.getErrorMessage());
				restResponce.setData(saveBack.getData());
				restResponce.setData(saveBack.getTransporterRegistration());
			} else {
				restResponce.setErrorCode(saveBack.getErrorCode());
				restResponce.setErrorMesaage(saveBack.getErrorMessage());
				restResponce.setData(saveBack.getData());
			}

			return restResponce;
		} catch (Exception e) {
			RestResponce restResponcee = new RestResponce();
			restResponcee.setErrorCode("101");
			restResponcee.setErrorMesaage(e.toString());
			return restResponcee;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/addTruck", method = RequestMethod.POST)
	public RestResponce addTruck(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String transportCompanyName, @RequestParam String contactPersonName,
			@RequestParam String mobileNumber, @RequestParam(required = false) String email, @RequestParam Integer city,
			@RequestParam Integer state, @RequestParam Integer pincode, @RequestParam String vehicleCategory,
			@RequestParam Integer createdBy) throws ParseException {

		try {

			RestResponce restResponce = new RestResponce();

			TransporterRegistration tr = new TransporterRegistration();

			tr.setTransporterCompanyName(transportCompanyName);
			tr.setContactPersonName(contactPersonName);
			tr.setMobileNumber(mobileNumber.replace(" ", "").trim());
			tr.setPassword(TruxUtils.getRandomNumber(100000, 999999));
			tr.setEmail(email);
			tr.setCity(city);
			tr.setState(state);
			tr.setPincode(pincode);
			tr.setVehicleCategory(vehicleCategory);
			tr.setCreatedBy(createdBy);
			tr.setCreatedOn(new Date());
			tr.setModifiedBy(createdBy);
			tr.setModifiedOn(new Date());
			tr.setIsActive(1);

			ControllerDAOTracker saveBack;

			ControllerDAOTracker TR = transporterRegistrationService
					.getTransporterByMobile(mobileNumber.replace(" ", "").trim());

			if (TR.isSuccess()) {
				restResponce.setErrorCode("101");
				restResponce.setErrorMesaage("Transporter already registered");
				return restResponce;
			} else {

				saveBack = transporterRegistrationService.saveTransporterRegistration(tr);

				if (saveBack.getErrorCode().equals("100")) {
					restResponce.setErrorCode(saveBack.getErrorCode());
					restResponce.setErrorMesaage(saveBack.getErrorMessage());
					restResponce.setData(saveBack.getData());
				} else if (saveBack.getErrorCode().equals("200")) {
					restResponce.setErrorCode(saveBack.getErrorCode());
					restResponce.setErrorMesaage(saveBack.getErrorMessage());
					restResponce.setData(saveBack.getData());
				} else {
					restResponce.setErrorCode(saveBack.getErrorCode());
					restResponce.setErrorMesaage(saveBack.getErrorMessage());
					restResponce.setData(saveBack.getData());
				}

				return restResponce;
			}
		} catch (Exception e) {
			RestResponce restResponcee = new RestResponce();
			restResponcee.setErrorCode("101");
			restResponcee.setErrorMesaage(e.toString());
			return restResponcee;
		}

	}

	/** TO CHANGE IS ACTIVE UPDATE ON CRM */
	@ResponseBody
	@RequestMapping(value = "/changeClientIsActiveUpdate", method = RequestMethod.POST)
	public RestResponce changeClientIsActiveUpdate(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer trsptrClientOrdersId,
			@RequestParam(required = false) Integer trsptrClientOrdersMappingId, @RequestParam String status,
			@RequestParam(required = false) Integer price, @RequestParam(required = false) Integer modifiedBy)
			throws ParseException {

		try {

			HttpSession session = request.getSession();
			UserDetail userDetail = (UserDetail) session.getAttribute("userDetail");
			Integer userId = 0;
			if (userDetail != null) {
				userId = userDetail.getId();
			}

			RestResponce restResponce = new RestResponce();

			TransporterClientOrders tco = new TransporterClientOrders();

			if (status.equals("Confirmed")) {
				tco.setId(trsptrClientOrdersId);
				tco.setIsActive(0);
				tco.setModifiedBy(userId);
				tco.setModifiedOn(new Date());
				tco.setPrice(price);
			} else {
				tco.setId(trsptrClientOrdersId);
				tco.setIsActive(1);
				tco.setModifiedBy(userId);
				tco.setModifiedOn(new Date());
				tco.setPrice(price);
			}

			TransporterClientOrderMapping tcom = new TransporterClientOrderMapping();

			// tcom.setTrsptrClientOrdersId(trsptrClientOrdersId);
			tcom.setId(trsptrClientOrdersMappingId);
			tcom.setStatus(status);
			tcom.setModifiedBy(userId);
			tcom.setModifiedOn(new Date());
			if (status.equals("Cancelled"))
				tcom.setIsActive(0);

			TransporterOrderFollowUp tofu = new TransporterOrderFollowUp();
			if (status.equals("Confirmed")) {
				tofu.setTrsptrClientOrdersId(trsptrClientOrdersId);
				tofu.setModifiedBy(userId);
				tofu.setModifiedOn(new Date());
				tofu.setIsActive(1);
			} else {
				tofu.setTrsptrClientOrdersId(trsptrClientOrdersId);
				tofu.setModifiedBy(userId);
				tofu.setModifiedOn(new Date());
				tofu.setIsActive(0);
			}

			ControllerDAOTracker saveBack;

			saveBack = transporterRegistrationService.transporterClientIsActiveUpdate(tco);

			if (saveBack.isSuccess()) {

				ControllerDAOTracker ctcom = transporterRegistrationService.changeTransporterStatus(tcom);

				ControllerDAOTracker vStatusChange = transporterRegistrationService.trsptrVehicleStatusChange(
						ctcom.getTransporterClientOrderMapping().getTrsptrRegistrationId(),
						ctcom.getTransporterClientOrderMapping().getVehicleNumber());

				if (ctcom.isSuccess() && vStatusChange.isSuccess()) {

					ControllerDAOTracker tfau = transporterRegistrationService.transporterFollowUpActiveUpdate(tofu);

					if (tfau.isSuccess()) {
						restResponce.setErrorCode(tfau.getErrorCode());
						restResponce.setErrorMesaage(tfau.getErrorMessage());
						// restResponce.setData(saveBack.getTransporterClientOrders());
					} else {
						restResponce.setErrorCode(tfau.getErrorCode());
						restResponce.setErrorMesaage(tfau.getErrorMessage());
					}
				} else {
					restResponce.setErrorCode(ctcom.getErrorCode());
					restResponce.setErrorMesaage(ctcom.getErrorMessage());
				}
			} else {
				restResponce.setErrorCode(saveBack.getErrorCode());
				restResponce.setErrorMesaage(saveBack.getErrorMessage());
			}

			return restResponce;
		} catch (Exception e) {
			e.printStackTrace();
			RestResponce restResponcee = new RestResponce();
			restResponcee.setErrorCode("101");
			restResponcee.setErrorMesaage(e.toString());
			return restResponcee;
		}
	}

	/* Added By shahzad HUssain */
	@ResponseBody
	@RequestMapping(value = "/UpdateOrder", method = RequestMethod.POST)
	public RestResponce UpdateOrder(HttpServletRequest request, HttpServletResponse response, @RequestParam Integer id,
			@RequestParam(required = false) Date deployDateTime, @RequestParam(required = false) Integer price)
			throws ParseException {

		try {
			RestResponce restResponce = new RestResponce();

			TransporterClientOrders tco = new TransporterClientOrders();
			tco.setId(id);
			tco.setDeployDateTime(deployDateTime);
			tco.setPrice(price);
			tco.setModifiedOn(new Date());

			/*
			 * HttpSession session = request.getSession(); UserDetail userDetail
			 * = (UserDetail) session.getAttribute("userDetail"); Integer userId
			 * = 0; if (userDetail != null) { userId = userDetail.getId(); }
			 */
			// tco.setCreatedBy(userId);
			// tco.setCreatedOn(new Date());
			// tco.setModifiedBy(userId);

			ControllerDAOTracker TR = transporterRegistrationService.updateClientOrder(tco);

			if (TR.isSuccess()) {
				restResponce.setErrorCode(TR.getErrorCode());
				restResponce.setErrorMesaage(TR.getErrorMessage());
				restResponce.setData(TR.getTransporterClientOrders());
				return restResponce;
			} else {
				restResponce.setErrorCode(TR.getErrorCode());
				restResponce.setErrorMesaage(TR.getErrorMessage());
				return restResponce;
			}
		} catch (Exception e) {
			e.printStackTrace();
			RestResponce restResponcee = new RestResponce();
			restResponcee.setErrorCode("101");
			restResponcee.setErrorMesaage(e.toString());
			return restResponcee;
		}
	}

	// /** TRANSPORTER APP LOGIN/LOGOUT */
	// @ResponseBody
	// @RequestMapping(value = "/getLoginAuthentication", method =
	// RequestMethod.GET)
	// public RestResponce getLoginAuthentication(HttpServletRequest request,
	// HttpServletResponse response,
	// @RequestParam String mobileNumber, @RequestParam(required = false)
	// Integer password,
	// @RequestParam Integer loginStatus, @RequestParam String gcmId,
	// @RequestParam(required = false) String deviceMAC,
	// @RequestParam(required = false) String currentApkVersion) {
	// try {
	//
	// RestResponce restResponce = new RestResponce();
	//
	// TransporterRegistration tr = new TransporterRegistration();
	// tr.setMobileNumber(mobileNumber);
	// tr.setPassword(password);
	// tr.setGcmId(gcmId);
	//
	// if (loginStatus.equals(0)) {
	// ControllerDAOTracker transporterDetail = transporterRegistrationService
	// .getTransporterByMobile(mobileNumber);
	//
	// TransporterRegistration auth =
	// transporterDetail.getTransporterRegistration();
	//
	// TransporterLoginHistory tlh = new TransporterLoginHistory();
	//
	// tlh.setTrsptrRegistrationId(auth.getId());
	// tlh.setLoginDate(new Date());
	// tlh.setDeviceMac(deviceMAC);
	// tlh.setIsLogin(loginStatus);
	// tlh.setIsSuccess(1);
	// tlh.setCurrentAppVersion(currentApkVersion);
	//
	// ControllerDAOTracker cdt =
	// transporterRegistrationService.saveTransporterLoginHistory(tlh);
	//
	// if (cdt.isSuccess()) {
	// restResponce.setErrorCode("100");
	// restResponce.setErrorMesaage("Logged out successfully.");
	//
	// return restResponce;
	// } else {
	// restResponce.setErrorCode(cdt.getErrorCode());
	// restResponce.setErrorMesaage(cdt.getErrorMessage());
	//
	// return restResponce;
	// }
	// }
	//
	// ControllerDAOTracker transporterDetail =
	// transporterRegistrationService.getUserDetailsWithGcmId(tr);
	//
	// if (transporterDetail.isSuccess()) {
	// if (mobileNumber != null && password != null && !mobileNumber.isEmpty())
	// {
	// if (transporterDetail != null && transporterDetail.isSuccess()) {
	//
	// TransporterRegistration auth =
	// transporterDetail.getTransporterRegistration();
	//
	// if (auth.getPassword().equals(password)) {
	// if (auth.getIsActive().equals(1)) {
	//
	// TransporterLoginHistory tlh = new TransporterLoginHistory();
	//
	// tlh.setTrsptrRegistrationId(auth.getId());
	// tlh.setLoginDate(new Date());
	// tlh.setDeviceMac(deviceMAC);
	// tlh.setIsLogin(loginStatus);
	// tlh.setIsSuccess(1);
	// tlh.setCurrentAppVersion(currentApkVersion);
	//
	// ControllerDAOTracker cdt = transporterRegistrationService
	// .saveTransporterLoginHistory(tlh);
	//
	// TransporterRegistration trr = new TransporterRegistration();
	//
	// if (cdt.isSuccess()) {
	// trr.setId(auth.getId());
	// trr.setTransporterCompanyName(auth.getTransporterCompanyName());
	// trr.setContactPersonName(auth.getContactPersonName());
	// trr.setMobileNumber(auth.getMobileNumber());
	// trr.setEmail(auth.getEmail());
	// trr.setVehicleCategory(auth.getVehicleCategory());
	// trr.setCity(auth.getCity());
	// trr.setState(auth.getState());
	// trr.setPincode(auth.getPincode());
	// trr.setCreatedBy(auth.getCreatedBy());
	// trr.setCreatedOn(auth.getCreatedOn());
	// trr.setModifiedBy(auth.getModifiedBy());
	// trr.setModifiedOn(auth.getModifiedOn());
	// trr.setIsActive(auth.getIsActive());
	// trr.setGcmId(auth.getGcmId());
	// trr.setCsc(auth.getCsc());
	//
	// restResponce.setErrorCode(cdt.getErrorCode());
	// restResponce.setErrorMesaage("Logged in successfully.");
	// restResponce.setData(trr);
	//
	// return restResponce;
	// } else {
	// restResponce.setErrorCode(cdt.getErrorCode());
	// restResponce.setErrorMesaage(cdt.getErrorMessage());
	//
	// return restResponce;
	// }
	// } else {
	// restResponce.setErrorCode("101");
	// restResponce.setErrorMesaage("Your account is locked. Please call
	// administrator.");
	//
	// return restResponce;
	// }
	// } else {
	// if (auth.getIsActive().equals(1)) {
	//
	// TransporterLoginHistory tlh = new TransporterLoginHistory();
	//
	// tlh.setTrsptrRegistrationId(auth.getId());
	// tlh.setLoginDate(new Date());
	// tlh.setDeviceMac(deviceMAC);
	// tlh.setIsLogin(0);
	// tlh.setIsSuccess(0);
	// tlh.setCurrentAppVersion(currentApkVersion);
	//
	// ControllerDAOTracker cdt = transporterRegistrationService
	// .saveTransporterLoginHistory(tlh);
	//
	// if (cdt.isSuccess()) {
	// restResponce.setErrorCode("101");
	// restResponce.setErrorMesaage("Invalid Credentials.");
	//
	// return restResponce;
	// } else {
	// restResponce.setErrorCode(cdt.getErrorCode());
	// restResponce.setErrorMesaage(cdt.getErrorMessage());
	//
	// return restResponce;
	// }
	// } else {
	// restResponce.setErrorCode("101");
	// restResponce.setErrorMesaage("Your account is locked. Please call
	// administrator.");
	//
	// return restResponce;
	// }
	// }
	// } else {
	//
	// restResponce.setErrorCode("101");
	// restResponce.setErrorMesaage("Transporter not registered.");
	//
	// return restResponce;
	// }
	//
	// } else {
	// restResponce.setErrorCode("101");
	// restResponce.setErrorMesaage("Credentials missing.");
	//
	// return restResponce;
	// }
	// } else {
	// TransporterLoginHistory tlh = new TransporterLoginHistory();
	//
	// ControllerDAOTracker cdt =
	// transporterRegistrationService.getTransporterByMobile(mobileNumber);
	//
	// if (cdt.isSuccess()) {
	//
	// TransporterRegistration auth = cdt.getTransporterRegistration();
	//
	// tlh.setTrsptrRegistrationId(auth.getId());
	// tlh.setLoginDate(new Date());
	// tlh.setDeviceMac(deviceMAC);
	// tlh.setIsLogin(0);
	// tlh.setIsSuccess(0);
	// tlh.setCurrentAppVersion(currentApkVersion);
	//
	// ControllerDAOTracker cdt1 =
	// transporterRegistrationService.saveTransporterLoginHistory(tlh);
	//
	// if (cdt1.isSuccess()) {
	// restResponce.setErrorCode("101");
	// restResponce.setErrorMesaage("Invalid Credentials.");
	//
	// return restResponce;
	// } else {
	// restResponce.setErrorCode(cdt1.getErrorCode());
	// restResponce.setErrorMesaage(cdt1.getErrorMessage());
	//
	// return restResponce;
	// }
	// } else {
	// restResponce.setErrorCode("101");
	// restResponce.setErrorMesaage("Transporter not registered.");
	//
	// return restResponce;
	// }
	// }
	//
	// } catch (Exception e) {
	// RestResponce restResponcee = new RestResponce();
	// restResponcee.setErrorCode("101");
	// restResponcee.setErrorMesaage(e.toString());
	// return restResponcee;
	// }
	// }
	//
	// /** ADDING TRUCKS TRANSPORTERS ARE HAVING */
	// @ResponseBody
	// @RequestMapping(value = "/addTruck", method = RequestMethod.POST)
	// public RestResponce addTruck(HttpServletRequest request,
	// HttpServletResponse response,
	// @RequestParam Integer trsptrRegistrationId, @RequestParam String
	// vehicleNumber,
	// @RequestParam Integer vehicleTypeId, @RequestParam String vehicleBody,
	// @RequestParam String modelYear,
	// @RequestParam Date insuranceExpiry, @RequestParam String status,
	// @RequestParam Integer createdBy)
	// throws ParseException {
	//
	// try {
	//
	// RestResponce restResponce = new RestResponce();
	//
	// if (trsptrRegistrationId == null || vehicleNumber == null ||
	// vehicleTypeId == null || vehicleBody == null
	// || modelYear == null || insuranceExpiry == null || status == null ||
	// createdBy == null) {
	//
	// restResponce.setErrorCode("101");
	// restResponce.setErrorMesaage("Parameters missing");
	//
	// return restResponce;
	// } else {
	// TransporterVehicleRegistration tvr = new
	// TransporterVehicleRegistration();
	//
	// tvr.setTrsptrRegistrationId(trsptrRegistrationId);
	// vehicleNumber = vehicleNumber.replace(" ", "");
	// tvr.setVehicleNumber(vehicleNumber);
	// tvr.setVehicleTypeId(vehicleTypeId);
	// tvr.setVehicleBody(vehicleBody);
	// tvr.setModelYear(modelYear);
	// tvr.setInsuranceExpiry(insuranceExpiry);
	// tvr.setStatus(status);
	// tvr.setCreatedBy(createdBy);
	// tvr.setCreatedOn(new Date());
	// tvr.setModifiedBy(createdBy);
	// tvr.setModifiedOn(new Date());
	// tvr.setIsActive(1);
	//
	// ControllerDAOTracker saveBack;
	//
	// ControllerDAOTracker TR =
	// transporterRegistrationService.getVehicleNumber(vehicleNumber);
	//
	// if (TR.isSuccess()) {
	// restResponce.setErrorCode("101");
	// restResponce.setErrorMesaage("Vehicle already registered");
	// return restResponce;
	// } else {
	//
	// saveBack = transporterRegistrationService.saveTransporterVehicle(tvr);
	//
	// if (saveBack.getErrorCode().equals("100")) {
	// restResponce.setErrorCode(saveBack.getErrorCode());
	// restResponce.setErrorMesaage(saveBack.getErrorMessage());
	// restResponce.setData(saveBack.getTransporterVehicleRegistration());
	// } else {
	// restResponce.setErrorCode(saveBack.getErrorCode());
	// restResponce.setErrorMesaage(saveBack.getErrorMessage());
	// }
	//
	// return restResponce;
	// }
	// }
	// } catch (Exception e) {
	// RestResponce restResponcee = new RestResponce();
	// restResponcee.setErrorCode("101");
	// restResponcee.setErrorMesaage(e.toString());
	// return restResponcee;
	// }
	//
	// }
	//
	// /** FOR SEARCHING TRANSPORTER VEHICLES */
	// @ResponseBody
	// @RequestMapping("/getTransporterVehicle/{trsptrRegistrationId}")
	// public RestResponce
	// getTransporterVehicle(@PathVariable("trsptrRegistrationId") Integer
	// trsptrRegistrationId) {
	//
	// try {
	// RestResponce restResponce = new RestResponce();
	//
	// ControllerDAOTracker cdt =
	// transporterRegistrationService.getTransporterVehicle(trsptrRegistrationId);
	//
	// if (cdt.isSuccess()) {
	// restResponce.setErrorCode(cdt.getErrorCode());
	// restResponce.setErrorMesaage(cdt.getErrorMessage());
	// restResponce.setData(cdt.getData());
	// } else {
	// restResponce.setErrorCode(cdt.getErrorCode());
	// restResponce.setErrorMesaage(cdt.getErrorMessage());
	// }
	//
	// return restResponce;
	// } catch (Exception e) {
	// RestResponce restResponcee = new RestResponce();
	// restResponcee.setErrorCode("101");
	// restResponcee.setErrorMesaage(e.toString());
	// return restResponcee;
	// }
	//
	// }
	//
	// /** UPDATE REGISTERED TRANSPORTER VEHICLE */
	// @ResponseBody
	// @RequestMapping(value = "/updateTransporterVehicle", method =
	// RequestMethod.POST)
	// public RestResponce updateTransporterVehicle(HttpServletRequest request,
	// HttpServletResponse response,
	// @RequestParam Integer id, @RequestParam Integer trsptrRegistrationId,
	// @RequestParam String vehicleNumber,
	// @RequestParam Integer vehicleTypeId, @RequestParam String vehicleBody,
	// @RequestParam String modelYear,
	// @RequestParam Date insuranceExpiry, @RequestParam String status,
	// @RequestParam Integer modifiedBy)
	// throws ParseException {
	//
	// try {
	//
	// RestResponce restResponce = new RestResponce();
	//
	// TransporterVehicleRegistration tvr = new
	// TransporterVehicleRegistration();
	//
	// tvr.setId(id);
	// tvr.setTrsptrRegistrationId(trsptrRegistrationId);
	// vehicleNumber = vehicleNumber.replace(" ", "");
	// tvr.setVehicleNumber(vehicleNumber);
	// tvr.setVehicleTypeId(vehicleTypeId);
	// tvr.setVehicleBody(vehicleBody);
	// tvr.setModelYear(modelYear);
	// tvr.setInsuranceExpiry(insuranceExpiry);
	// tvr.setStatus(status);
	// tvr.setModifiedBy(modifiedBy);
	// tvr.setModifiedOn(new Date());
	//
	// ControllerDAOTracker saveBack;
	//
	// saveBack = transporterRegistrationService.updateTransporterVehicle(tvr);
	//
	// if (saveBack.getErrorCode().equals("200")) {
	// restResponce.setErrorCode(saveBack.getErrorCode());
	// restResponce.setErrorMesaage(saveBack.getErrorMessage());
	// restResponce.setData(saveBack.getTransporterVehicleRegistration());
	// } else {
	// restResponce.setErrorCode(saveBack.getErrorCode());
	// restResponce.setErrorMesaage(saveBack.getErrorMessage());
	// restResponce.setData(saveBack.getData());
	// }
	//
	// return restResponce;
	// } catch (Exception e) {
	// RestResponce restResponcee = new RestResponce();
	// restResponcee.setErrorCode("101");
	// restResponcee.setErrorMesaage(e.toString());
	// return restResponcee;
	// }
	// }
	//
	// /** FOR GETTING CLIENT ORDER */
	// @ResponseBody
	// @RequestMapping("/getClientOrders/{cityId}/{vehicleCategory}")
	// public RestResponce getClientOrders(@PathVariable("cityId") Integer
	// cityId, @PathVariable("vehicleCategory") String vehicleCategory) {
	//
	// try {
	// RestResponce restResponce = new RestResponce();
	//
	// ControllerDAOTracker cdt =
	// transporterRegistrationService.getClientOrders(cityId,vehicleCategory);
	//
	// if (cdt.isSuccess()) {
	// restResponce.setErrorCode(cdt.getErrorCode());
	// restResponce.setErrorMesaage(cdt.getErrorMessage());
	// restResponce.setData(cdt.getData());
	// } else {
	// restResponce.setErrorCode(cdt.getErrorCode());
	// restResponce.setErrorMesaage(cdt.getErrorMessage());
	// }
	//
	// return restResponce;
	// } catch (Exception e) {
	// RestResponce restResponcee = new RestResponce();
	// restResponcee.setErrorCode("101");
	// restResponcee.setErrorMesaage(e.toString());
	// return restResponcee;
	// }
	//
	// }
	//
	// /** TO ACCEPT CLIENT ORDER (ORDER CONFIRMATION) */
	// @ResponseBody
	// @RequestMapping(value = "/clientOrderConfirmation", method =
	// RequestMethod.POST)
	// public RestResponce clientOrderConfirmation(HttpServletRequest request,
	// HttpServletResponse response,
	// @RequestParam Integer trsptrClientOrdersId, @RequestParam Integer
	// trsptrRegistrationId,
	// @RequestParam String vehicleNumber, @RequestParam String driverName,
	// @RequestParam String driverMobileNumber, @RequestParam Integer createdBy)
	// throws ParseException {
	//
	// try {
	//
	// RestResponce restResponce = new RestResponce();
	//
	// TransporterClientOrderMapping tcom = new TransporterClientOrderMapping();
	//
	// tcom.setTrsptrClientOrdersId(trsptrClientOrdersId);
	// tcom.setTrsptrRegistrationId(trsptrRegistrationId);
	// tcom.setVehicleNumber(vehicleNumber);
	// tcom.setDriverName(driverName);
	// tcom.setDriverMobileNumber(driverMobileNumber);
	// tcom.setStatus("Pending");
	// tcom.setCreatedBy(createdBy);
	// tcom.setCreatedOn(new Date());
	// tcom.setModifiedBy(createdBy);
	// tcom.setModifiedOn(new Date());
	// tcom.setIsActive(1);
	//
	// TransporterClientOrders tco = new TransporterClientOrders();
	//
	// tco.setId(trsptrClientOrdersId);
	// tco.setIsActive(0);
	// tco.setModifiedBy(createdBy);
	// tco.setModifiedOn(new Date());
	//
	// ControllerDAOTracker saveBack;
	//
	// ControllerDAOTracker TR =
	// transporterRegistrationService.clientOrderConfirmation(tcom);
	//
	// if (TR.isSuccess()) {
	// if (TR.getErrorCode().equals("100")) {
	//
	// ControllerDAOTracker TRFU =
	// transporterRegistrationService.transporterClientIsActiveUpdate(tco);
	//
	// if (TRFU.isSuccess()) {
	// restResponce.setErrorCode(TR.getErrorCode());
	// restResponce.setErrorMesaage(TR.getErrorMessage());
	// restResponce.setData(TR.getTransporterClientOrderMapping());
	// } else {
	// restResponce.setErrorCode(TR.getErrorCode());
	// restResponce.setErrorMesaage(TR.getErrorMessage());
	// }
	// } else {
	// restResponce.setErrorCode(TR.getErrorCode());
	// restResponce.setErrorMesaage(TR.getErrorMessage());
	// }
	//
	// return restResponce;
	// } else {
	// restResponce.setErrorCode("101");
	// restResponce.setErrorMesaage(TR.getErrorMessage());
	// return restResponce;
	// }
	// } catch (Exception e) {
	// RestResponce restResponcee = new RestResponce();
	// restResponcee.setErrorCode("101");
	// restResponcee.setErrorMesaage(e.toString());
	// return restResponcee;
	// }
	//
	// }
	//
	// /** TO CHANGE IS ACTIVE UPDATE ON CRM */
	// @ResponseBody
	// @RequestMapping(value = "/changeClientIsActiveUpdate", method =
	// RequestMethod.POST)
	// public RestResponce changeClientIsActiveUpdate(HttpServletRequest
	// request, HttpServletResponse response,
	// @RequestParam Integer trsptrClientOrdersId, @RequestParam String status,
	// @RequestParam Integer modifiedBy)
	// throws ParseException {
	//
	// try {
	//
	// RestResponce restResponce = new RestResponce();
	//
	// TransporterClientOrders tco = new TransporterClientOrders();
	//
	// tco.setId(trsptrClientOrdersId);
	// tco.setIsActive(1);
	// tco.setModifiedBy(modifiedBy);
	// tco.setModifiedOn(new Date());
	//
	// TransporterClientOrderMapping tcom = new TransporterClientOrderMapping();
	//
	// tcom.setTrsptrClientOrdersId(trsptrClientOrdersId);
	// tcom.setStatus(status);
	// tcom.setModifiedBy(modifiedBy);
	// tcom.setModifiedOn(new Date());
	//
	// TransporterOrderFollowUp tofu = new TransporterOrderFollowUp();
	// tofu.setTrsptrClientOrdersId(trsptrClientOrdersId);
	// tofu.setModifiedBy(modifiedBy);
	// tofu.setModifiedOn(new Date());
	// tofu.setIsActive(0);
	//
	// ControllerDAOTracker saveBack;
	//
	// saveBack =
	// transporterRegistrationService.transporterClientIsActiveUpdate(tco);
	//
	// if (saveBack.isSuccess()) {
	//
	// ControllerDAOTracker ctcom =
	// transporterRegistrationService.changeTransporterStatus(tcom);
	//
	// if (ctcom.isSuccess()) {
	//
	// ControllerDAOTracker tfau =
	// transporterRegistrationService.transporterFollowUpActiveUpdate(tofu);
	//
	// if (tfau.isSuccess()) {
	// restResponce.setErrorCode(tfau.getErrorCode());
	// restResponce.setErrorMesaage(tfau.getErrorMessage());
	//// restResponce.setData(saveBack.getTransporterClientOrders());
	// } else {
	// restResponce.setErrorCode(tfau.getErrorCode());
	// restResponce.setErrorMesaage(tfau.getErrorMessage());
	// }
	// } else {
	// restResponce.setErrorCode(ctcom.getErrorCode());
	// restResponce.setErrorMesaage(ctcom.getErrorMessage());
	// }
	// } else {
	// restResponce.setErrorCode(saveBack.getErrorCode());
	// restResponce.setErrorMesaage(saveBack.getErrorMessage());
	// }
	//
	// return restResponce;
	// } catch (Exception e) {
	// RestResponce restResponcee = new RestResponce();
	// restResponcee.setErrorCode("101");
	// restResponcee.setErrorMesaage(e.toString());
	// return restResponcee;
	// }
	// }
	//
	// /** TO SAVE CLIENT FOLLOW UP ORDERS */
	// @ResponseBody
	// @RequestMapping(value = "/saveClientFollowUpOrders", method =
	// RequestMethod.POST)
	// public RestResponce saveClientFollowUpOrders(HttpServletRequest request,
	// HttpServletResponse response,
	// @RequestParam Integer trsptrClientOrdersId, @RequestParam Integer
	// trsptrRegistrationId,
	// @RequestParam Integer createdBy) throws ParseException {
	//
	// try {
	//
	// RestResponce restResponce = new RestResponce();
	//
	// TransporterOrderFollowUp tofu = new TransporterOrderFollowUp();
	//
	// tofu.setTrsptrClientOrdersId(trsptrClientOrdersId);
	// tofu.setTrsptrRegistrationId(trsptrRegistrationId);
	// tofu.setCreatedBy(createdBy);
	// tofu.setCreatedOn(new Date());
	// tofu.setModifiedBy(createdBy);
	// tofu.setModifiedOn(new Date());
	// tofu.setIsActive(1);
	//
	// ControllerDAOTracker saveBack;
	//
	// ControllerDAOTracker TRF =
	// transporterRegistrationService.orderFollowUp(tofu);
	//
	// if (TRF.isSuccess()) {
	//
	// restResponce.setErrorCode(TRF.getErrorCode());
	// restResponce.setErrorMesaage(TRF.getErrorMessage());
	// restResponce.setData(TRF.getTransporterOrderFollowUp());
	// } else {
	// restResponce.setErrorCode(TRF.getErrorCode());
	// restResponce.setErrorMesaage(TRF.getErrorMessage());
	// }
	//
	// return restResponce;
	// } catch (Exception e) {
	// RestResponce restResponcee = new RestResponce();
	// restResponcee.setErrorCode("101");
	// restResponcee.setErrorMesaage(e.toString());
	// return restResponcee;
	// }
	//
	// }
	//
	// /** FOR GETTING CLIENT FOLLOW UP ORDERS */
	// /** FOR SHOWING CLIENT FOLLOW UP ORDERS */
	// @ResponseBody
	// @RequestMapping("/getFollowUpOrders/{trsptrRegistrationId}")
	// public RestResponce
	// getFollowUpOrders(@PathVariable("trsptrRegistrationId") Integer
	// trsptrRegistrationId) {
	//
	// try {
	// RestResponce restResponce = new RestResponce();
	//
	// ControllerDAOTracker cdt =
	// transporterRegistrationService.getFollowUpOrders(trsptrRegistrationId);
	//
	// if (cdt.isSuccess()) {
	// restResponce.setErrorCode(cdt.getErrorCode());
	// restResponce.setErrorMesaage(cdt.getErrorMessage());
	// restResponce.setData(cdt.getData());
	// } else {
	// restResponce.setErrorCode(cdt.getErrorCode());
	// restResponce.setErrorMesaage(cdt.getErrorMessage());
	// }
	//
	// return restResponce;
	// } catch (Exception e) {
	// RestResponce restResponcee = new RestResponce();
	// restResponcee.setErrorCode("101");
	// restResponcee.setErrorMesaage(e.toString());
	// return restResponcee;
	// }
	//
	// }
	//
	// /** FOR GETTING TRANSPORTER ORDER HISTORY */
	//
	// /** FOR SHOWING TRANSPORTER PENDING ORDERS */
	// @ResponseBody
	// @RequestMapping("/getClientOrdersHistory/{trsptrRegistrationId}/{status}/{vehicleCategory}")
	// public RestResponce
	// getClientOrdersHistory(@PathVariable("trsptrRegistrationId") Integer
	// trsptrRegistrationId, @PathVariable("status") String status,
	// @PathVariable("vehicleCategory") String vehicleCategory) {
	//
	// try {
	// RestResponce restResponce = new RestResponce();
	//
	// ControllerDAOTracker cdt =
	// transporterRegistrationService.getClientOrdersHistory(trsptrRegistrationId,
	// status, vehicleCategory);
	//
	// if (cdt.isSuccess()) {
	// restResponce.setErrorCode(cdt.getErrorCode());
	// restResponce.setErrorMesaage(cdt.getErrorMessage());
	// restResponce.setData(cdt.getData());
	// } else {
	// restResponce.setErrorCode(cdt.getErrorCode());
	// restResponce.setErrorMesaage(cdt.getErrorMessage());
	// }
	//
	// return restResponce;
	// } catch (Exception e) {
	// RestResponce restResponcee = new RestResponce();
	// restResponcee.setErrorCode("101");
	// restResponcee.setErrorMesaage(e.toString());
	// return restResponcee;
	// }
	//
	// }
	//
	// /** FOR SHOWING TRANSPORTER FREIGHT CHART */
	// @ResponseBody
	// @RequestMapping("/getTransporterFreightChart/{trsptrRegistrationId}/{sourceCityId}/{destinationCityId}/{vehicleTypeId}/{bodyType}")
	// public RestResponce
	// getTransporterFreightChart(@PathVariable("trsptrRegistrationId") Integer
	// trsptrRegistrationId, @PathVariable("sourceCityId") Integer sourceCityId,
	// @PathVariable("destinationCityId") Integer destinationCityId,
	// @PathVariable("vehicleTypeId") Integer vehicleTypeId,
	// @PathVariable("bodyType") String bodyType) {
	//
	// try {
	// RestResponce restResponce = new RestResponce();
	//
	// TransporterFreightChart tfc = new TransporterFreightChart();
	// tfc.setTrsptrRegistrationId(trsptrRegistrationId);
	// tfc.setSourceCityId(sourceCityId);
	// tfc.setDestinationCityId(destinationCityId);
	// tfc.setVehicleTypeId(vehicleTypeId);
	// tfc.setBodyType(bodyType);
	//
	// ControllerDAOTracker cdt =
	// transporterRegistrationService.getTransporterFreightChart(tfc);
	//
	// if (cdt.isSuccess()) {
	// restResponce.setErrorCode(cdt.getErrorCode());
	// restResponce.setErrorMesaage(cdt.getErrorMessage());
	// restResponce.setData(cdt.getTransporterFreightChart());
	// } else {
	// restResponce.setErrorCode(cdt.getErrorCode());
	// restResponce.setErrorMesaage(cdt.getErrorMessage());
	// }
	//
	// return restResponce;
	// } catch (Exception e) {
	// RestResponce restResponcee = new RestResponce();
	// restResponcee.setErrorCode("101");
	// restResponcee.setErrorMesaage(e.toString());
	// return restResponcee;
	// }
	//
	// }
	//
	// /** TO SAVE FREIGHT RATE CORRESPONDING TRANSPORTER */
	// @ResponseBody
	// @RequestMapping(value = "/saveTransporterFreightRate", method =
	// RequestMethod.POST)
	// public RestResponce saveTransporterFreightRate(HttpServletRequest
	// request, HttpServletResponse response,
	// @RequestParam Integer trsptrRegistrationId,
	// @RequestParam Integer sourceCityId,
	// @RequestParam Integer destinationCityId,
	// @RequestParam Integer vehicleTypeId,
	// @RequestParam String bodyType,
	// @RequestParam Integer freightRate,
	// @RequestParam Integer createdBy) throws ParseException {
	//
	// try {
	//
	// RestResponce restResponce = new RestResponce();
	//
	// TransporterFreightChart tr = new TransporterFreightChart();
	//
	// tr.setTrsptrRegistrationId(trsptrRegistrationId);
	// tr.setSourceCityId(sourceCityId);
	// tr.setDestinationCityId(destinationCityId);
	// tr.setVehicleTypeId(vehicleTypeId);
	// tr.setBodyType(bodyType);
	// tr.setFreightRate(freightRate);
	// tr.setCreatedBy(createdBy);
	// tr.setCreatedOn(new Date());
	// tr.setModifiedBy(createdBy);
	// tr.setModifiedOn(new Date());
	// tr.setIsActive(1);
	//
	//
	// ControllerDAOTracker TR =
	// transporterRegistrationService.saveTransporterFreightRate(tr);
	//
	// if (TR.isSuccess()) {
	// restResponce.setErrorCode(TR.getErrorCode());
	// restResponce.setErrorMesaage(TR.getErrorMessage());
	// restResponce.setData(TR.getData());
	// return restResponce;
	// } else {
	//
	// restResponce.setErrorCode(TR.getErrorCode());
	// restResponce.setErrorMesaage(TR.getErrorMessage());
	//
	// return restResponce;
	// }
	// } catch (Exception e) {
	// RestResponce restResponcee = new RestResponce();
	// restResponcee.setErrorCode("101");
	// restResponcee.setErrorMesaage(e.toString());
	// return restResponcee;
	// }
	//
	// }
	//
	// /** FOR GETTING CLIENT ORDER CITY LIST*/
	// @ResponseBody
	// @RequestMapping("/getOrderCityList")
	// public RestResponce getOrderCityList() {
	//
	// try {
	// RestResponce restResponce = new RestResponce();
	//
	// ControllerDAOTracker cdt =
	// transporterRegistrationService.getOrderCityList();
	//
	// if (cdt.isSuccess()) {
	// restResponce.setErrorCode(cdt.getErrorCode());
	// restResponce.setErrorMesaage(cdt.getErrorMessage());
	// restResponce.setData(cdt.getData());
	// } else {
	// restResponce.setErrorCode(cdt.getErrorCode());
	// restResponce.setErrorMesaage(cdt.getErrorMessage());
	// }
	//
	// return restResponce;
	// } catch (Exception e) {
	// RestResponce restResponcee = new RestResponce();
	// restResponcee.setErrorCode("101");
	// restResponcee.setErrorMesaage(e.toString());
	// return restResponcee;
	// }
	//
	// }

}
