package com.trux.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.google.maps.model.LatLng;
import com.trux.enumerations.TruxErrorCodes;
import com.trux.model.DriverCollection;
import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.DriverRegistration;
import com.trux.model.LeadGeneration;
import com.trux.model.Module;
import com.trux.model.RescheduleTrip;
import com.trux.model.UploadDocumentModel;
import com.trux.model.UserDetail;
import com.trux.model.UserLogin;
import com.trux.model.UserLoginHistory;
import com.trux.model.VehicleRegistration;
import com.trux.model.VehicleType;
import com.trux.service.BookingService;
import com.trux.service.DriverCollectionService;
import com.trux.service.LeadGenerationService;
import com.trux.service.ModuleService;
import com.trux.service.RegistrationService;
import com.trux.service.TruxStartUpService;
import com.trux.service.UserLoginHistoryService;
import com.trux.service.VehicleTypeService;
import com.trux.utils.AWSS3Uploader;
import com.trux.utils.EncriptUtil;
import com.trux.utils.GenerateHashWithSaltPasswordUnderMd5Utils;
import com.trux.utils.TruxUtils;

@Controller
@RequestMapping(value = "/lgapi")
public class LeadGenerationController {
	@Autowired
	private LeadGenerationService leadGenerationService;
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private VehicleTypeService vehicleTypeService;
	@Autowired
	DriverCollectionService driverCollectionService;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private BookingService bookingService;
	@Autowired
	private TruxStartUpService truxStartUpService;
	@Autowired
	UserLoginHistoryService userLoginHistoryService;

	private Map<String, String> map = new HashMap<String, String>();

	@RequestMapping(value = "/openLG", method = RequestMethod.GET)
	public ModelAndView openLeadGeneration() {
		return new ModelAndView("leadgeneration");
	}

	@RequestMapping(value = "/uploadImage", method = RequestMethod.POST)
	public @ResponseBody String upload(MultipartHttpServletRequest request,
			HttpServletResponse response,
			@RequestParam MultipartFile uploadfiles) {
		UploadDocumentModel documentModel = new UploadDocumentModel();
		if (!uploadfiles.isEmpty()) {
			try {
				String name = uploadfiles.getOriginalFilename();
				AWSS3Uploader.uploadFiles(name, uploadfiles.getContentType(),
						(int) uploadfiles.getSize(),
						uploadfiles.getInputStream(), documentModel);
				byte[] bytes = uploadfiles.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(name)));
				stream.write(bytes);
				stream.close();
				documentModel.setUploadStatus("SUCCESS");
				return documentModel.getUploadURL();
			} catch (Exception e) {
				documentModel.setUploadStatus("Failed");
				documentModel.setErrorMessage(e.getMessage());
				return documentModel.getUploadStatus();
			}
		} else {
			return documentModel.getUploadStatus();
		}
	}

	@RequestMapping(value = "/uploadImages", method = RequestMethod.POST)
	public @ResponseBody String uploadImages(
			MultipartHttpServletRequest request, HttpServletResponse response) {
		UploadDocumentModel documentModel = new UploadDocumentModel();
		Iterator<String> itr = request.getFileNames();
		MultipartFile files = request.getFile(itr.next());
		if (!files.isEmpty()) {
			try {
				String name = files.getOriginalFilename();
				AWSS3Uploader.uploadVehicleFile(name, files.getContentType(),
						files.getSize(), files.getInputStream(), documentModel);
				byte[] bytes = files.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File(name)));
				stream.write(bytes);
				stream.close();
				documentModel.setUploadStatus("SUCCESS");
				return "{imageUrl:" + documentModel.getUploadURL() + "}";
			} catch (Exception e) {
				documentModel.setUploadStatus("Failed");
				documentModel.setErrorMessage(e.getMessage());
				return "{imageUrl:" + documentModel.getUploadStatus() + "}";
			}
		} else {
			return "{imageUrl:" + documentModel.getUploadStatus() + "}";
		}
	}

	/*@SuppressWarnings("deprecation")
	@RequestMapping(value = "/addAgentLG", method = RequestMethod.POST)
	private ModelAndView addLead(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String imageOfVisitingCard = null;
			String scheduledMeetingDate = null;
			String scheduledMeetingTime = null;
			String comments = null;
			String scheduledMeetingTitle = null;
			String sendProposal = null;
			if (request.getParameter("imageOfVisitingCard") != null
					&& !request.getParameter("imageOfVisitingCard").trim()
							.isEmpty()) {
				imageOfVisitingCard = request.getParameter(
						"imageOfVisitingCard").trim();
			}
			if (request.getParameter("scheduledMeetingDate") != null
					&& !request.getParameter("scheduledMeetingDate").isEmpty()) {
				scheduledMeetingDate = request
						.getParameter("scheduledMeetingDate");
			}
			if (request.getParameter("scheduledMeetingTime") != null
					&& !request.getParameter("scheduledMeetingTime").trim()
							.isEmpty()) {
				scheduledMeetingTime = request.getParameter(
						"scheduledMeetingTime").trim();
			}
			if (request.getParameter("sendProposal") != null
					&& !request.getParameter("sendProposal").isEmpty()) {
				sendProposal = request.getParameter("sendProposal");
			}
			if (request.getParameter("scheduledMeetingTitle") != null
					&& !request.getParameter("scheduledMeetingTitle").isEmpty()) {
				scheduledMeetingTitle = request
						.getParameter("scheduledMeetingTitle");
			}

			if (request.getParameter("comments") != null
					&& !request.getParameter("comments").isEmpty()) {
				comments = request.getParameter("comments").trim();
			}
			try {
				LeadGeneration dto = new LeadGeneration(imageOfVisitingCard,
						comments, new Date(scheduledMeetingDate),
						scheduledMeetingTime, scheduledMeetingTitle,
						sendProposal);
				leadGenerationService.saveLeadGeneration(dto);
				TruxUtils.sendProposalMail(dto);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception er) {
			er.printStackTrace();
		}
		return new ModelAndView("leadgeneration");
	}*/

	/*@SuppressWarnings("deprecation")
	@ResponseBody
	@RequestMapping(value = "/visitingCardUpload", method = RequestMethod.POST)
	private LeadGeneration registerLead(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam String imageOfVisitingCard,
			@RequestParam String comments,
			@RequestParam String scheMeetingDate,
			@RequestParam String scheMeetingTime,
			@RequestParam String scheMeetingTitle,
			@RequestParam String sendProposal,
			@RequestParam(required = true) String agentId) {
		if (imageOfVisitingCard == null)
			return new LeadGeneration(
					TruxErrorCodes.LEAD_REGISTRATION_EXCEPTIOM.getCode(),
					TruxErrorCodes.LEAD_REGISTRATION_EXCEPTIOM.getDescription());
		try {
			LeadGeneration dto = new LeadGeneration(imageOfVisitingCard,
					comments, new Date(scheMeetingDate), scheMeetingTime,
					scheMeetingTitle, sendProposal, agentId);
			leadGenerationService.saveLeadGeneration(dto);
			TruxUtils.sendProposalMail(dto);
			return dto;
			return null;
		} catch (Exception er) {
			er.printStackTrace();
			return new LeadGeneration(TruxErrorCodes.UNHANDLED_ERROR.getCode(),
					TruxErrorCodes.UNHANDLED_ERROR.getDescription());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/getLgById", method = RequestMethod.GET)
	public LeadGeneration getLeadByAgentid(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(required = true) String agentId) {
		try {
			if (agentId != null) {
				LeadGeneration dto = leadGenerationService
						.getLeadGenerationById(Integer.parseInt(agentId));
				if (dto != null) {
					return dto;
				} else {
					return new LeadGeneration(
							TruxErrorCodes.LEAD_AGENTID_EXCEPTIOM.getCode(),
							TruxErrorCodes.LEAD_AGENTID_EXCEPTIOM
									.getDescription());
				}
			}
		} catch (Exception er) {
			er.printStackTrace();
			return new LeadGeneration(TruxErrorCodes.UNHANDLED_ERROR.getCode(),
					TruxErrorCodes.UNHANDLED_ERROR.getDescription());
		}
		return new LeadGeneration(
				TruxErrorCodes.LEAD_AGENTID_EXCEPTIOM.getCode(),
				TruxErrorCodes.LEAD_AGENTID_EXCEPTIOM.getDescription());
	}

	@ResponseBody
	@RequestMapping(value = "/getAllLG", method = RequestMethod.GET)
	public List<LeadGeneration> getAllLead(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			List<LeadGeneration> dto = leadGenerationService
					.getAllLeadGeneration();
			return dto;
		} catch (Exception er) {
			er.printStackTrace();
			List<LeadGeneration> list = new ArrayList<LeadGeneration>();
			list.add(new LeadGeneration(TruxErrorCodes.UNHANDLED_ERROR
					.getCode(), TruxErrorCodes.UNHANDLED_ERROR.getDescription()));
			return list;
		}

	}*/

	@ResponseBody
	@RequestMapping(value = "/loginAgent", method = RequestMethod.POST)
	public UserDetail agentLogin(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String username,
			@RequestParam String password, @RequestParam String gcmId) {
		try {
			// String password =
			// GenerateHashWithSaltPasswordUnderMd5Utils.getRightHashPass(login.getUserPassword()).replace("\"",
			// "");
			// String passwords =
			// GenerateHashWithSaltPasswordUnderMd5Utils.getRightHashPassAgent(password).replace("\"",
			// "");
			String encyptedPassword = TruxUtils.computeMD5Hash(password);
			UserLogin login = new UserLogin(username, encyptedPassword, gcmId);
			UserDetail userDetail = registrationService
					.getUserDetailsWithGcmId(login);

			map.put("agentID", "" + userDetail.getId());
			return userDetail;
		} catch (Exception er) {
			er.printStackTrace();
			return new UserDetail(
					TruxErrorCodes.LEAD_AGENTID_LOGIN_EXCEPTION_MESSAGE
							.getCode(),
					TruxErrorCodes.LEAD_AGENTID_LOGIN_EXCEPTION_MESSAGE
							.getDescription()
							+ " becouse "
							+ er.getMessage().toString());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/loginAgents", method = RequestMethod.POST)
	public UserDetail loginAgents(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String username,
			@RequestParam String password, @RequestParam String gcmId,
			@RequestParam String locationLat,
			@RequestParam String locationLong,
			@RequestParam(required = false) Integer loginStatus,
			@RequestParam(required = false) String locationAddress,
			@RequestParam(required = false) String deviceMAC,
			@RequestParam(required = false) String currentApkVersion) {
		try {

			String userNames = EncriptUtil.encryp(username);
			String passwords = EncriptUtil.encryp(password);
			String authenticator = userNames + "[!]" + passwords;
			String encyptedPassword = TruxUtils.computeMD5Hash(password);
			UserLogin login = new UserLogin(username, encyptedPassword, gcmId);
			LatLng source = null;
			if (locationLat != null && locationLong != null) {
				if (locationLat != "" && locationLong != "") {
					source = new LatLng(new Double(locationLat), new Double(
							locationLong));
				}
			}

			UserDetail userDetail = registrationService
					.getUserDetailsWithGcmId(login);

			if (username != null && password != null && !username.isEmpty()
					&& !password.isEmpty()) {
				if (userDetail != null
						&& userDetail.getFirstname()
								.equals(login.getUserName())
						|| userDetail.getEmail().equals(login.getUserName())) {
					if (userDetail.getPassword().equals(encyptedPassword)) {
						if (userDetail.getIsActive() == 1) {
							UserLoginHistory dtos = new UserLoginHistory();
							dtos.setCreatedDate(new Date());
							if (loginStatus == null) {
								dtos.setIslogin(1);
							} else {
								dtos.setIslogin(loginStatus);
							}
							dtos.setIsMobile(1);
							dtos.setUserId(userDetail.getId());
							dtos.setLocationLat(locationLat);
							dtos.setLocationLong(locationLong);
							dtos.setDeviceMac(deviceMAC);
							dtos.setAppversion(currentApkVersion);

							if (source != null) {
								dtos.setLoginLocation(locationAddress);
								// GoogleMapsUtils.
							}
							userLoginHistoryService.saveUserLoginHistory(dtos);
							map.put("agentID", "" + userDetail.getId());
							userDetail.setAuthentication(authenticator);
							userDetail.setPassword("");
							userDetail.setFirstname("");
							return userDetail;
						} else {
							return new UserDetail(
									TruxErrorCodes.LEAD_AGENTID_LOGIN_EXCEPTION_MESSAGE
											.getCode(),
									"Your account is locked. Please call Trux CRM admin.");
						}
					} else {
						return new UserDetail(
								TruxErrorCodes.LEAD_AGENTID_LOGIN_EXCEPTION_MESSAGE
										.getCode(),
								"E-mail or password is not valid please try again.");
					}
				} else {
					return new UserDetail(
							TruxErrorCodes.LEAD_AGENTID_LOGIN_EXCEPTION_MESSAGE
									.getCode(),
							"E-mail or password is not valid please try again.");
				}

			} else {
				return new UserDetail(
						TruxErrorCodes.LEAD_AGENTID_LOGIN_EXCEPTION_MESSAGE
								.getCode(),
						"Your user or password is not valid please try again.");
			}

		} catch (Exception er) {
			return new UserDetail(
					TruxErrorCodes.LEAD_AGENTID_LOGIN_EXCEPTION_MESSAGE
							.getCode(),
					"Your user or password is not valid please try again.");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/createAgent", method = RequestMethod.POST)
	public UserDetail agentCreate(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String email,
			@RequestParam String firstname, @RequestParam String lastname,
			@RequestParam String phoneNumber,
			@RequestParam(required = false) String role,
			@RequestParam String password, @RequestParam String gcmId) {
		String encyptedPassword = TruxUtils.computeMD5Hash(password);
		try {
			String roles = "";
			if (role != null && role.equals("")) {
				roles = role;
			} else {
				roles = "Agent";
			}
			UserDetail userDetails = new UserDetail(email, firstname, lastname,
					phoneNumber, encyptedPassword, roles, gcmId);
			UserDetail userDetail = registrationService
					.saveUserDetail(userDetails);
			if (userDetail != null) {
				return userDetail;
			} else
				return new UserDetail(
						TruxErrorCodes.LEAD_AGENTID_CREATION_MESSAGE.getCode(),
						TruxErrorCodes.LEAD_AGENTID_CREATION_MESSAGE
								.getDescription());
		} catch (Exception e) {
			e.printStackTrace();
			return new UserDetail(
					TruxErrorCodes.EMAIL_ALREADY_REGISTERED.getCode(),
					TruxErrorCodes.EMAIL_ALREADY_REGISTERED.getDescription());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/getSaltValueToAgentLogin", method = RequestMethod.POST)
	private String getSaltValueToAgentLogin(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("passwordVal") String passwordVal) {
		String passValue = TruxUtils.computeMD5Hash(passwordVal);
		String saltValue1 = passValue
				+ GenerateHashWithSaltPasswordUnderMd5Utils.getSalt();
		return "{saltValue:" + saltValue1 + "}";
	}

	@ResponseBody
	@RequestMapping(value = "/moduleAccessRights", method = RequestMethod.GET)
	public List<Module> moduleAccessRights(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String agentId) {
		UserDetail user = registrationService.getUserDetailsByAgentId(agentId);
		if (user != null) {
			List<Module> list = moduleService.getModule(user.getUserRole());
			return list;
		} else {
			List<Module> lists = new ArrayList<Module>();
			lists.add(new Module(TruxErrorCodes.MODULE_RIGHT_TO_ACCESS_MESSAGE
					.getCode(), TruxErrorCodes.MODULE_RIGHT_TO_ACCESS_MESSAGE
					.getDescription()));
			return lists;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/vehicleList", method = RequestMethod.GET)
	public List<VehicleType> vehicleList(HttpServletRequest request,
			HttpServletResponse response) {
		List<VehicleType> list = vehicleTypeService.getAllVehicleType();
		return list;
	}

	@ResponseBody
	@RequestMapping(value = "/vehicleRegistrations", method = RequestMethod.POST)
	public VehicleRegistration vehicleRegistrations(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String ownerName,
			@RequestParam String vehicleType,
			@RequestParam String vehicleNumber,
			@RequestParam String vehicleModel,
			@RequestParam String vehicleStatus, @RequestParam String kmReading,
			@RequestParam("ioRC") MultipartFile ioRC,
			@RequestParam("ioPermitVehicle") MultipartFile ioPermitVehicle,
			@RequestParam String fuelType, @RequestParam String driverMobile) {
		try {
			byte[] bytesIORC = null;
			byte[] bytesIOPermitVehicle = null;
			UploadDocumentModel documentModelVRc = new UploadDocumentModel();
			UploadDocumentModel documentModelVP = new UploadDocumentModel();
			if (!ioRC.isEmpty()) {
				try {
					String name = ioRC.getOriginalFilename();
					documentModelVRc = AWSS3Uploader.uploadVehicleFile(name,
							ioRC.getContentType(), ioRC.getSize(),
							ioRC.getInputStream(), documentModelVRc);
					byte[] bytes = ioRC.getBytes();
					bytesIORC = bytes;
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(new File(name)));
					stream.write(bytes);
					stream.close();
					documentModelVRc.setUploadStatus("SUCCESS");
				} catch (Exception e) {
					documentModelVRc.setUploadStatus("Failed");
					documentModelVRc.setErrorMessage(e.getMessage());

				}

			}
			if (!ioPermitVehicle.isEmpty()) {
				try {
					String name = ioPermitVehicle.getOriginalFilename();
					documentModelVP = AWSS3Uploader.uploadVehicleFile(name,
							ioPermitVehicle.getContentType(),
							ioPermitVehicle.getSize(),
							ioPermitVehicle.getInputStream(), documentModelVP);
					byte[] bytes = ioPermitVehicle.getBytes();
					bytesIOPermitVehicle = bytes;
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(new File(name)));
					stream.write(bytes);
					stream.close();
					documentModelVP.setUploadStatus("SUCCESS");
				} catch (Exception e) {
					documentModelVP.setUploadStatus("Failed");
					documentModelVP.setErrorMessage(e.getMessage());
				}
			}
			String driverId = ""
					+ driverCollectionService.getDriverID(driverMobile);
			if (driverId == null || driverId.equals("0"))
				return new VehicleRegistration(
						TruxErrorCodes.VEHCLE_REGISTRATION_MESSAGE1.getCode(),
						TruxErrorCodes.VEHCLE_REGISTRATION_MESSAGE1
								.getDescription());
			if (bytesIORC == null)
				return new VehicleRegistration(
						TruxErrorCodes.VEHCLE_REGISTRATION_MESSAGE1.getCode(),
						TruxErrorCodes.VEHCLE_REGISTRATION_MESSAGE1
								.getDescription());

			if (bytesIOPermitVehicle == null)
				return new VehicleRegistration(
						TruxErrorCodes.VEHCLE_REGISTRATION_MESSAGE2.getCode(),
						TruxErrorCodes.VEHCLE_REGISTRATION_MESSAGE2
								.getDescription());

			VehicleRegistration dto = new VehicleRegistration(vehicleNumber,
					vehicleType, vehicleModel, ownerName, kmReading,
					documentModelVRc.getUploadURL(),
					documentModelVP.getUploadURL(), fuelType);
			dto.setDriverId(new Integer(driverId));

			VehicleRegistration saveBackVehicle = registrationService
					.registrationVehicleByAPI(dto);

			if (saveBackVehicle != null && saveBackVehicle.getId() != null) {
				DriverDeviceVehicleMapping driverDeviceVehicleMapping = registrationService
						.getDriverDeviceDetail(driverMobile);
				driverDeviceVehicleMapping.setIsValidDriver(true);
				driverDeviceVehicleMapping.setLoginStatus(1);
				driverDeviceVehicleMapping.setLastLoginTime(new Date());
				driverDeviceVehicleMapping.setDriverStatus(0);
				driverDeviceVehicleMapping.setDriverId(dto.getDriverId());
				driverDeviceVehicleMapping.setDriverPhoneNumber(driverMobile);// driverMobile
				driverDeviceVehicleMapping
						.setVehicleId(saveBackVehicle.getId());
				driverDeviceVehicleMapping.setVehicleNumber(vehicleNumber);
				driverDeviceVehicleMapping.setVehicleType(vehicleType);
				driverDeviceVehicleMapping.setLoginStatus(0);
				try {
					registrationService
							.saveOrUpdateDDVM(driverDeviceVehicleMapping);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return saveBackVehicle;
			} else {
				return new VehicleRegistration(
						TruxErrorCodes.VEHCLE_REGISTRATION_ERROR_MESSAGE
								.getCode(),
						TruxErrorCodes.VEHCLE_REGISTRATION_ERROR_MESSAGE
								.getDescription());
			}

		} catch (Exception e) {
			return new VehicleRegistration(
					TruxErrorCodes.VEHCLE_REGISTRATION_ERROR_MESSAGE.getCode(),
					TruxErrorCodes.VEHCLE_REGISTRATION_ERROR_MESSAGE
							.getDescription() + " " + e.getMessage().toString());
		}

	}

	@ResponseBody
	@RequestMapping(value = "/driverRegistrations", method = RequestMethod.POST)
	public DriverRegistration driverRegistrations(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String driverName,
			@RequestParam String vehicleOwnerName,
			@RequestParam String drivingExperience,
			@RequestParam("imagesOfDL") MultipartFile imagesOfDL,
			@RequestParam("ioPanCard") MultipartFile ioPanCard,
			@RequestParam("iOPVReports") MultipartFile iOPVReports,
			@RequestParam String localAddress,
			@RequestParam String permanentAddress,
			@RequestParam String driverContactNumber,
			@RequestParam String standDetails,
			@RequestParam String assosiatedByOrg, @RequestParam String dstatus) {
		try {

			if (imagesOfDL == null)
				return new DriverRegistration(
						TruxErrorCodes.DRIVER_REGISTRATION_MESSAGE1.getCode(),
						TruxErrorCodes.DRIVER_REGISTRATION_MESSAGE1
								.getDescription());

			if (ioPanCard == null)
				return new DriverRegistration(
						TruxErrorCodes.DRIVER_REGISTRATION_MESSAGE2.getCode(),
						TruxErrorCodes.DRIVER_REGISTRATION_MESSAGE2
								.getDescription());

			if (iOPVReports == null)
				return new DriverRegistration(
						TruxErrorCodes.DRIVER_REGISTRATION_MESSAGE3.getCode(),
						TruxErrorCodes.DRIVER_REGISTRATION_MESSAGE3
								.getDescription());

			Integer agentId = 0;
			if (map.get("agentID") != null) {
				agentId = Integer.parseInt(map.get("agentID"));
			}
			UploadDocumentModel documentModelDrPhoto = new UploadDocumentModel();
			UploadDocumentModel documentModelDrDl = new UploadDocumentModel();
			UploadDocumentModel documentModelDrPan = new UploadDocumentModel();
			UploadDocumentModel documentModelDrPvr = new UploadDocumentModel();

			if (!imagesOfDL.isEmpty()) {
				try {
					String name = imagesOfDL.getOriginalFilename();
					documentModelDrDl = AWSS3Uploader.uploadDriverFile(name,
							imagesOfDL.getContentType(), imagesOfDL.getSize(),
							imagesOfDL.getInputStream(), documentModelDrDl);
					byte[] bytes = ioPanCard.getBytes();
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(new File(name)));
					stream.write(bytes);
					stream.close();
					documentModelDrDl.setUploadStatus("SUCCESS");
				} catch (Exception e) {
					documentModelDrDl.setUploadStatus("Failed");
					documentModelDrDl.setErrorMessage(e.getMessage());

				}

			}
			if (!ioPanCard.isEmpty()) {
				try {
					String name = ioPanCard.getOriginalFilename();
					documentModelDrPan = AWSS3Uploader.uploadDriverFile(name,
							ioPanCard.getContentType(), ioPanCard.getSize(),
							ioPanCard.getInputStream(), documentModelDrPan);
					byte[] bytes = ioPanCard.getBytes();
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(new File(name)));
					stream.write(bytes);
					stream.close();
					documentModelDrPan.setUploadStatus("SUCCESS");
				} catch (Exception e) {
					documentModelDrPan.setUploadStatus("Failed");
					documentModelDrPan.setErrorMessage(e.getMessage());

				}

			}
			if (!iOPVReports.isEmpty()) {
				try {
					String name = iOPVReports.getOriginalFilename();
					documentModelDrPvr = AWSS3Uploader.uploadDriverFile(name,
							iOPVReports.getContentType(),
							iOPVReports.getSize(),
							iOPVReports.getInputStream(), documentModelDrPvr);
					byte[] bytes = iOPVReports.getBytes();
					BufferedOutputStream stream = new BufferedOutputStream(
							new FileOutputStream(new File(name)));
					stream.write(bytes);
					stream.close();
					documentModelDrPvr.setUploadStatus("SUCCESS");
				} catch (Exception e) {
					documentModelDrPvr.setUploadStatus("Failed");
					documentModelDrPvr.setErrorMessage(e.getMessage());
				}
			}
			String stand = "0";
			if (standDetails != null && !standDetails.equals("")) {
				stand = standDetails;
			} // documentModelDrDl.getFileName(),
				// documentModelDrPan.getFileName(),
				// documentModelDrPvr.getFileName(),
				// documentModelDrPhoto.getFileName(),
			DriverRegistration dto = new DriverRegistration(driverName,
					vehicleOwnerName, drivingExperience, localAddress,
					permanentAddress, driverContactNumber, stand,
					documentModelDrDl.getUploadURL(),
					documentModelDrPan.getUploadURL(),
					documentModelDrPvr.getUploadURL(), agentId);
			dto.setAssosiatedBy(assosiatedByOrg);
			dto.setDstatus(dstatus);
			DriverRegistration saveBackResponse = registrationService
					.registrationDriverByAPI(dto);

			if (saveBackResponse != null && saveBackResponse.getId() != null) {
				DriverDeviceVehicleMapping driverDeviceVehicleMapping = new DriverDeviceVehicleMapping(
						saveBackResponse.getId(), 5, 5, driverContactNumber,
						null, null, null, driverName, 0, 0);
				driverDeviceVehicleMapping
						.setDocumentuploadurl(documentModelDrPhoto
								.getUploadURL());
				driverDeviceVehicleMapping.setDriverStatus(0);
				driverDeviceVehicleMapping.setLoginStatus(0);
				driverDeviceVehicleMapping.setDstatus(dstatus);
				try {
					registrationService
							.saveOrUpdateDDVM(driverDeviceVehicleMapping);
					truxStartUpService.driverRegistrationMap.put(
							driverContactNumber, driverDeviceVehicleMapping);

				} catch (Exception er) {
					er.printStackTrace();
				}
			}
			if (saveBackResponse == null) {

				dto.setErrorCode(TruxErrorCodes.DRIVER_REGISTRATION_ERROR_MESSAGE
						.getCode());
				dto.setErrorMesaage(TruxErrorCodes.DRIVER_REGISTRATION_ERROR_MESSAGE
						.getDescription());
				return saveBackResponse;// new(TruxErrorCodes.DRIVER_REGISTRATION_ERROR_MESSAGE.getCode(),
										// TruxErrorCodes.DRIVER_REGISTRATION_ERROR_MESSAGE.getDescription());
			} else {
				return saveBackResponse;
			}
		} catch (Exception e) {
			return new DriverRegistration(
					TruxErrorCodes.DRIVER_REGISTRATION_ERROR_MESSAGE.getCode(),
					TruxErrorCodes.DRIVER_REGISTRATION_ERROR_MESSAGE
							.getDescription() + " " + e.getMessage().toString());
		}

	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	@ResponseBody
	@RequestMapping(value = "/driverCollectionAmount", method = RequestMethod.POST)
	public DriverCollection driverCollectionAmount(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String driverMobile,
			@RequestParam(required = false) String vehicleOwnerMobile,
			@RequestParam String depositAmount) {
		DriverCollection dto = new DriverCollection();
		int driverId = 0;
		int vehiclId = 0;
		if (driverMobile != null && driverMobile != "") {
			driverId = driverCollectionService.getDriverID(driverMobile);
			vehiclId = driverCollectionService.getVehicleID(vehicleOwnerMobile);
		} else {
			DriverCollection dtos = new DriverCollection();
			dtos.setErrorCode(TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE
					.getCode());
			dtos.setErrorMessage(TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE
					.getDescription());
			dtos.setDriverStatus("Fail");
			return dtos;
		}
		if (driverId != 0 && depositAmount != "") {
			dto.setDriverId(driverId);
			dto.setVehicleId(vehiclId);
			dto.setAmountCollection(new Float(depositAmount));
			DriverCollection status = driverCollectionService
					.driverAmountCollection(dto);
			return status;
		} else {
			DriverCollection dtos = new DriverCollection();
			dtos.setErrorCode(TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE
					.getCode());
			dtos.setErrorMessage(TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE
					.getDescription());
			dtos.setDriverStatus("Fail");
			return dtos;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/driverHandset", method = RequestMethod.POST)
	public List<DriverCollection> driverHandset(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String driverMobile,
			@RequestParam String deviceImei) {
		DriverCollection dto = new DriverCollection();
		dto.setDeviceEmei(deviceImei.trim());
		int driverId = 0;
		if (driverMobile != null || driverMobile != "") {
			driverId = driverCollectionService.getDriverID(driverMobile);
		}
		List<DriverCollection> status = null;
		if (driverId != 0) {
			dto.setDriverId(driverId);
			if (deviceImei != null && deviceImei != "") {
				dto.setDeviceEmei(deviceImei.trim());
			}
			status = driverCollectionService.getDriverCollectionAmount(
					"" + dto.getDriverId(), dto.getDeviceEmei());
		}
		if (status != null) {
			return status;
		} else {
			List<DriverCollection> lid = new ArrayList<DriverCollection>();
			DriverCollection dtos = new DriverCollection();
			dtos.setDriverStatus("Fail");
			lid.add(dtos);
			return lid;
		}
	}

	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping(value = "/driverDeactivate", method = RequestMethod.GET)
	public DriverCollection driverDeactivate(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String driverMobile) {
		if (driverMobile != null || driverMobile != "") {
			int id = driverCollectionService.getDriverID(driverMobile);
			if (id == 0) {
				DriverCollection dtos = new DriverCollection();
				dtos.setDriverStatus("Fail");
				return dtos;
			} else {
				DriverCollection status = driverCollectionService
						.deactivateDriver(id);
				return status;
			}
		} else {
			DriverCollection dtos = new DriverCollection();
			dtos.setDriverStatus("Fail");
			return dtos;
		}
	}

	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping(value = "/vehicleDeactivate", method = RequestMethod.GET)
	public DriverCollection vehicleDeactivate(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String vehiclOwnerMobile) {

		if (vehiclOwnerMobile != null || vehiclOwnerMobile != "") {
			int id = driverCollectionService.getVehicleID(vehiclOwnerMobile);
			if (id == 0) {
				DriverCollection dtos = new DriverCollection();
				dtos.setVehicleStatus("Fail");
				return dtos;
			} else {
				DriverCollection status = driverCollectionService
						.deactivateVehicle(id);
				return status;
			}
		} else {
			DriverCollection dtos = new DriverCollection();
			dtos.setVehicleStatus("Fail");
			return dtos;
		}
	}

	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping(value = "/rescheduleTrip", method = RequestMethod.POST)
	public RescheduleTrip rescheduleTrip(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String bookingId,
			@RequestParam String pickupTime, @RequestParam String vehicleType) {

		RescheduleTrip dto = new RescheduleTrip();
		dto.setBookingId(bookingId);
		dto.setPickupTime(pickupTime);
		dto.setVehicleType(vehicleType);
		RescheduleTrip dtos = bookingService.rescheduleTirp(dto);
		dtos.setBookingId(bookingId);
		dtos.setPickupTime(pickupTime);
		dtos.setVehicleType(vehicleType);
		if (dtos != null) {
			return dtos;
		} else {
			return new RescheduleTrip("EU01", "Fail");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/cencelTrip", method = RequestMethod.POST)
	public RescheduleTrip cencelTrip(HttpServletRequest request,
			HttpServletResponse response, @RequestParam String bookingId) {
		RescheduleTrip dto = new RescheduleTrip();
		dto.setBookingId(bookingId);
		RescheduleTrip dts = bookingService.cencelledTirp(dto);
		if (dts != null) {
			return dts;
		} else
			return new RescheduleTrip("EU01", "Fail");
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/leadGeneration", method = RequestMethod.POST)
	public LeadGeneration leadGeneration(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam String title,
			@RequestParam String name,
			@RequestParam String client_mobile,
			@RequestParam String client_email,
			@RequestParam int created_by){
		
		LeadGeneration lg = new LeadGeneration();
		lg.setTitle(title);
		lg.setName(name);
		lg.setClient_mobile(client_mobile);
		lg.setClient_email(client_email);
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		lg.setCreated_date(new Date(dateFormat.format(date)));
		
		lg.setCreated_by(created_by);
		LeadGeneration lgu = leadGenerationService.leadGeneration(lg);
		if(lgu != null) {
			
			lgu.setErrorCode("100");
			lgu.setErrorMessage("Created successfully");
		} else {
			lgu = new LeadGeneration("101", "Something went wrong");
		}
		
		return lgu;
	}

}
