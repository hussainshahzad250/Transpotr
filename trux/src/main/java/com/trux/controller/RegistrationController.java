package com.trux.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.trux.enumerations.TruxErrorCodes;
import com.trux.model.Countries;
import com.trux.model.DesboardInfo;
import com.trux.model.DeviceRegistration;
import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.UploadDocumentModel;
import com.trux.model.UserDetail;
import com.trux.model.UserLogin;
import com.trux.model.UserLoginHistory;
import com.trux.model.VehicleRegistration;
import com.trux.service.DesboardInfoService;
import com.trux.service.ModuleService;
import com.trux.service.RegistrationService;
import com.trux.service.TruxStartUpService;
import com.trux.service.UserLoginHistoryService;
import com.trux.utils.AWSS3Uploader;
import com.trux.utils.EncriptUtil;
import com.trux.utils.GenerateHashWithSaltPasswordUnderMd5Utils;
import com.trux.utils.TruxUtils;

@Controller
@RequestMapping(value = "/admin/register")
public class RegistrationController {
	public List<Countries> allCountries = null;
	@Autowired
	private ModuleService moduleService;
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private TruxStartUpService truxStartUpService;
	@Autowired
	private DesboardInfoService desboardInfoService;
	private List<DesboardInfo> desboardInfo;
	@Autowired
	UserLoginHistoryService userLoginHistoryService;

	@ResponseBody
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	private UserDetail registration(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String email, @RequestParam String password, @RequestParam String phonenumber,
			@RequestParam(required = false) String firstname, @RequestParam(required = false) String lastname) {

		if (email == null)
			return new UserDetail(TruxErrorCodes.EMAIL_NOT_VALID.getCode(),
					TruxErrorCodes.EMAIL_NOT_VALID.getDescription());

		if (password == null)
			return new UserDetail(TruxErrorCodes.INVALID_PASSWORD.getCode(),
					TruxErrorCodes.INVALID_PASSWORD.getDescription());

		if (phonenumber == null)
			return new UserDetail(TruxErrorCodes.INVALID_PHONENUBER.getCode(),
					TruxErrorCodes.INVALID_PHONENUBER.getDescription());

		String encyptedPassword = TruxUtils.computeMD5Hash(password);

		UserDetail userDetail = new UserDetail(email, firstname, lastname, phonenumber, encyptedPassword, null);

		try {
			registrationService.saveOrUpdate(userDetail);
		} catch (Exception e) {
			e.printStackTrace();
			return new UserDetail(TruxErrorCodes.EMAIL_ALREADY_REGISTERED.getCode(),
					TruxErrorCodes.EMAIL_ALREADY_REGISTERED.getDescription());
		}
		return userDetail;
	}

	@ResponseBody
	@RequestMapping(value = "/validate", method = RequestMethod.GET)
	public UserDetail validateUser(HttpServletRequest request, HttpServletResponse response, @RequestParam String email,
			@RequestParam String password) throws Exception {
		if (email == null)
			return new UserDetail(TruxErrorCodes.EMAIL_NOT_VALID.getCode(),
					TruxErrorCodes.EMAIL_NOT_VALID.getDescription());

		if (password == null)
			return new UserDetail(TruxErrorCodes.INVALID_PASSWORD.getCode(),
					TruxErrorCodes.INVALID_PASSWORD.getDescription());

		UserDetail userDetail = registrationService.getUserDetails(email);

		boolean isValidUser = false;

		if (userDetail != null) {
			if (userDetail.getPassword().equals(TruxUtils.computeMD5Hash(password))) {
				isValidUser = true;
			}

			if (isValidUser) {
				userDetail.setUserValidationFlag(isValidUser);
				userDetail.setPassword("***********");
				return userDetail;
			} else {
				UserDetail invaliUserDetail = new UserDetail();
				invaliUserDetail.setEmail(email);
				invaliUserDetail.setUserValidationFlag(false);
				invaliUserDetail.setErrorCode(TruxErrorCodes.INVALID_USER.getCode());
				invaliUserDetail.setErrorMessage(TruxErrorCodes.INVALID_USER.getDescription());
				return invaliUserDetail;
			}

		} else {
			return new UserDetail(TruxErrorCodes.USER_NOT_EXISTS.getCode(),
					TruxErrorCodes.USER_NOT_EXISTS.getDescription());
		}

	}

	@ResponseBody
	@RequestMapping(value = "/creater", method = RequestMethod.GET)
	private ModelAndView creater(@ModelAttribute("login") UserDetail login) {
		return new ModelAndView("createUserRegistration");
	}

	@ResponseBody
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	private ModelAndView createNewUser(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("login") UserDetail login, BindingResult result) {
		String encyptedPassword = TruxUtils.computeMD5Hash(login.getPassword());
		login.setPassword(encyptedPassword);
		login.setGcmId("987654321");
		try {
			login.setIsActive(1);
			UserDetail userDetail = registrationService.saveUserDetail(login);
			request.setAttribute("userDetail", userDetail);
		} catch (Exception e) {
			e.printStackTrace();
			login = new UserDetail(TruxErrorCodes.EMAIL_ALREADY_REGISTERED.getCode(),
					TruxErrorCodes.EMAIL_ALREADY_REGISTERED.getDescription());
		}
		return new ModelAndView("createUserRegistration", "userDetail", login);
	}

	@ResponseBody
	@RequestMapping(value = "/changePass", method = RequestMethod.GET)
	private ModelAndView changePass(@ModelAttribute("login") UserDetail login, BindingResult result) {

		return new ModelAndView("changeUserPassword");
	}

	@ResponseBody
	@RequestMapping(value = "/changePasswords", method = RequestMethod.POST)
	private ModelAndView changePasswords(@ModelAttribute("login") UserDetail login, BindingResult result) {
		String encyptedPassword = TruxUtils.computeMD5Hash(login.getPassword());
		login.setPassword(encyptedPassword);
		try {
			String message = registrationService.changePasswords(login);

			login.setErrorMessage(message);
		} catch (Exception e) {
			e.printStackTrace();
			login = new UserDetail(TruxErrorCodes.EMAIL_ALREADY_REGISTERED.getCode(),
					TruxErrorCodes.EMAIL_ALREADY_REGISTERED.getDescription());
		}
		return new ModelAndView("changeUserPassword", "userDetail", login);
	}

	@ResponseBody
	@RequestMapping(value = "/validateMobile", method = RequestMethod.GET)
	private String validateMobile(@RequestParam("mobile") String mobile) {
		UserDetail user = null;
		try {
			user = registrationService.getUserDetailByMobile(mobile);
			if (user != null) {
				return user.getPhoneNumber();
			} else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	@ResponseBody
	@RequestMapping(value = "/validateEmail", method = RequestMethod.GET)
	private String validateEmail(@RequestParam("email") String email) {
		UserDetail user = null;
		try {
			user = registrationService.getUserDetailByEmail(email);
			if (user != null) {
				return user.getEmail().trim();
			} else {
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logoutUser(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("login") UserDetail login, BindingResult result, ModelMap model) throws Exception {
		try {
			desboardInfo = desboardInfoService.getDesboardInfo();
			request.setAttribute("desboardInfo", desboardInfo);

			session.removeAttribute("userDetail");
			session.invalidate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ModelAndView("login");
	}

	@ResponseBody
	@RequestMapping(value = "/getsalt", method = RequestMethod.POST)
	private String saltVal(HttpServletRequest request, HttpServletResponse response) {
		String saltValue1 = GenerateHashWithSaltPasswordUnderMd5Utils.getSalt();
		return saltValue1;
	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	private ModelAndView login(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@ModelAttribute("login") UserLogin login, BindingResult result) {

		String username = login.getUserName();
		UserDetail userDetails = (UserDetail) session.getAttribute("userDetail");
		if (userDetails != null) {
			if (userDetails.getFirstname().equals(username)) {
				ModelAndView loginUsers = new ModelAndView("driverRegistration");
				loginUsers.addObject("loginUser", username);
				session.setAttribute("userDetail", userDetails);
				return loginUsers;
			}
		}
		String password = TruxUtils.computeMD5Hash(login.getUserPassword());
		login.setUserPassword(password);
		UserDetail userDetail = registrationService.getUserDetails(login);

		if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
			try {
				if (userDetail != null && userDetail.getFirstname().equals(login.getUserName())
						|| userDetail.getEmail().equals(login.getUserName())) {
					if (userDetail.getPassword().equals(password) && userDetail.getIsActive() == 1) {
						if (userDetail != null) {
							UserLoginHistory dtos = new UserLoginHistory();
							dtos.setCreatedDate(new Date());
							dtos.setIslogin(1);
							dtos.setIsMobile(0);
							dtos.setUserId(userDetail.getId());
							// dtos.setLocationLat(locationLat);
							// dtos.setLocationLong(locationLong);
							userLoginHistoryService.saveUserLoginHistory(dtos);
						}
						ModelAndView loginUsers = new ModelAndView("driverRegistration");
						loginUsers.addObject("loginUser", username);
						session.setAttribute("userDetail", userDetail);
						return loginUsers;
					} else if (userDetail.getPassword().equals(password) && userDetail.getIsActive() == 0) {
						request.setAttribute("error", "Your account is locked. Please call Trux CRM admin.");
						return new ModelAndView("login");
					}

					request.setAttribute("error", "Please provide a valid username and password.");
					return new ModelAndView("login");
				} else {
					request.setAttribute("error", "Please provide a valid username and password.");
					return new ModelAndView("login");
				}
			} catch (Exception er) {
				er.printStackTrace();
				request.setAttribute("error", "Please provide a valid username and password.");
				return new ModelAndView("login");
			}
		} else {
			request.setAttribute("error", "Please provide a valid username and password.");
			return new ModelAndView("login");
		}

	}

	@RequestMapping(value = "/userLogin", method = RequestMethod.GET)
	private Object userLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam String authentication) {
		String userName = null;
		String userPassword = null;
		String password = null;
		String username = null;
		UserLogin login = new UserLogin();
		try {
			String[] userToken = authentication.split("[!]");
			String userNames = userToken[0].replace("[", "").replace(" ", "+");
			String userPasswords = userToken[1].replace("]", "").replace(" ", "+");
			userName = EncriptUtil.decrypt(userNames);
			userPassword = EncriptUtil.decrypt(userPasswords);
		} catch (Exception er) {
			er.printStackTrace();
		}
		UserDetail userDetail = null;
		if (userName != null && userPassword != null) {
			username = userName;
			login.setUserName(userName);
			password = TruxUtils.computeMD5Hash(userPassword);
			login.setUserPassword(password);
			userDetail = registrationService.getUserDetails(login);
		}
		if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
			try {
				if (userDetail != null && userDetail.getFirstname().equals(login.getUserName())
						|| userDetail.getEmail().equals(login.getUserName())) {
					if (userDetail.getPassword().equals(password) && userDetail.getIsActive() == 1) {
						ModelAndView loginUsers = new ModelAndView("driverRegistration");
						loginUsers.addObject("loginUser", username);
						session.setAttribute("userDetail", userDetail);
						return loginUsers;
					} else if (userDetail.getPassword().equals(password) && userDetail.getIsActive() == 0) {
						request.setAttribute("error", "Your account is locked. Please call Trux CRM admin.");
						return new ModelAndView("login");
					}

					request.setAttribute("error", "Please provide a valid username and password.");
					return new ModelAndView("login");
				} else {
					request.setAttribute("error", "Please provide a valid username and password.");
					return new ModelAndView("login");
				}
			} catch (Exception er) {
				er.printStackTrace();
				request.setAttribute("error", "Please provide a valid username and password.");
				return new ModelAndView("login");
			}
		} else {
			request.setAttribute("error", "Please provide a valid username and password.");
			return new ModelAndView("login");
		}

	}

	@RequestMapping(value = "/driverRegistration", method = RequestMethod.GET)
	private ModelAndView driverRegistration(HttpServletRequest request, HttpServletResponse response) {

		return new ModelAndView("driverRegistration");

	}

	@RequestMapping(value = "/vehicleRegistration", method = RequestMethod.GET)
	private ModelAndView vehicleRegistration(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("vehicleRegistration");
	}

	@RequestMapping(value = "/deviceRegistration", method = RequestMethod.GET)
	private ModelAndView deviceRegistration(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("deviceRegistration");
	}

	@RequestMapping(value = "/registerDriver", method = RequestMethod.POST)
	private ModelAndView registrationDriver(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("files") MultipartFile files) {

		try {
			String driverName = null;
			String phonenumber = null;
			String vehicleNumber = null;
			String vehicleType = null;

			UploadDocumentModel documentModel = new UploadDocumentModel();

			if (!files.isEmpty()) {
				try {
					String name = files.getOriginalFilename();
					byte[] bytes = files.getBytes();
					if (bytes.length > 0) {
						AWSS3Uploader.uploadDriverFile(name, files.getContentType(), files.getSize(),
								files.getInputStream(), documentModel);
					}
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(name)));
					stream.write(bytes);
					stream.close();
					documentModel.setUploadStatus("SUCCESS");

				} catch (Exception e) {
					documentModel.setUploadStatus("Failed");
					documentModel.setErrorMessage(e.getMessage());

				}
			} else {
				documentModel.setUploadStatus("Failed");
				documentModel.setErrorMessage("Uploed failed  because the file was empty!");

			}

			if (request.getParameter("driverName") != null && !request.getParameter("driverName").trim().isEmpty()) {
				driverName = request.getParameter("driverName").trim();
			}
			if (request.getParameter("phonenumber") != null && !request.getParameter("phonenumber").trim().isEmpty()) {
				phonenumber = request.getParameter("phonenumber").trim();
			}

			if (request.getParameter("vehicleNumber") != null
					&& !request.getParameter("vehicleNumber").trim().isEmpty()) {
				vehicleNumber = request.getParameter("vehicleNumber");
			}
			if (request.getParameter("vehicleType") != null && !request.getParameter("vehicleType").trim().isEmpty()) {
				vehicleType = request.getParameter("vehicleType").trim();
			}

			DriverDeviceVehicleMapping driverDeviceVehicleMapping = new DriverDeviceVehicleMapping(13, 5, 5,
					phonenumber, null, vehicleNumber, vehicleType, driverName, 0, 0);
			driverDeviceVehicleMapping.setDocumentuploadurl(documentModel.getUploadURL());
			try {
				registrationService.saveOrUpdateDDVM(driverDeviceVehicleMapping);
				truxStartUpService.driverRegistrationMap.put(phonenumber, driverDeviceVehicleMapping);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception er) {
			er.printStackTrace();
		}
		return new ModelAndView("driverRegistration");
	}

	@RequestMapping(value = "/registerVehicle", method = RequestMethod.POST)
	private ModelAndView registrationVehicle(HttpServletRequest request, HttpServletResponse response) {
		try {

			String vehicleNumber = request.getParameter("vehicleNumber");
			String ownerName = request.getParameter("ownerName");
			String vehicleType = request.getParameter("vehicleType");
			String ownerPhoneNumber = request.getParameter("ownerPhoneNumber");
			String model = request.getParameter("model");
			String ownerAddress = request.getParameter("ownerAddress");
			VehicleRegistration vehicleRegistration = new VehicleRegistration(vehicleNumber, vehicleType, model,
					ownerName, ownerPhoneNumber, ownerAddress, "free");
			try {
				registrationService.saveOrUpdate(vehicleRegistration);
			} catch (Exception e) {
				request.setAttribute("errorMessage", "Something went wrong. Please contact to administration.");
				return new ModelAndView("vehicleRegistration");
			}
		} catch (Exception er) {
			er.printStackTrace();
		}
		return new ModelAndView("vehicleRegistration");
	}

	@RequestMapping(value = "/registerDevice", method = RequestMethod.POST)
	private ModelAndView registrationDevice(HttpServletRequest request, HttpServletResponse response) {
		try {
			String deviceUnqueId = request.getParameter("deviceId");
			DeviceRegistration deviceRegistration = new DeviceRegistration(deviceUnqueId);
			deviceRegistration.setDeviceStatus("free");
			try {
				registrationService.saveOrUpdate(deviceRegistration);
			} catch (Exception e) {
				request.setAttribute("errorMessage", "Something went wrong. Please contact to administration.");
				return new ModelAndView("deviceRegistration");
			}
		} catch (Exception er) {
			er.printStackTrace();
		}
		return new ModelAndView("deviceRegistration");

	}

	public List<DesboardInfo> getDesboardInfo() {
		return desboardInfo;
	}

	public void setDesboardInfo(List<DesboardInfo> desboardInfo) {
		this.desboardInfo = desboardInfo;
	}

}
