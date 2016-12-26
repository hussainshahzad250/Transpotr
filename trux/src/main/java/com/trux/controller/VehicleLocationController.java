package com.trux.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import com.trux.enumerations.TruxErrorCodes;
import com.trux.model.BookedTruxTrackingModel;
import com.trux.model.CurrentVehicleLocation;
import com.trux.model.CustomerBookingDetails;
import com.trux.model.Distance;
import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.RestResponce;
import com.trux.model.UploadDocumentModel;
import com.trux.model.VehicleComparator;
import com.trux.model.VehicleLocation;
import com.trux.model.VehicleTrackingModel;
import com.trux.service.BookingService;
import com.trux.service.RegistrationService;
import com.trux.service.VehicleLocationService;
import com.trux.utils.AWSS3Uploader;
import com.trux.utils.Utils;

@Controller
@RequestMapping(value = "/vehicleLocation")
public class VehicleLocationController {

	@Autowired
	private VehicleLocationService vehicleLocationService;

	@Autowired
	private BookingService bookingService;

	@Autowired
	private RegistrationService registrationService;

	@ResponseBody
	@RequestMapping(value = "/sendVehicleHeartBeat", method = RequestMethod.POST)
	public CurrentVehicleLocation sendVehicleHeartBeat(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String driverPhoneNumber) {

		if (driverPhoneNumber == null)
			return new CurrentVehicleLocation(TruxErrorCodes.INVALID_DRIVER_PHONENUMBER.getCode(),
					TruxErrorCodes.INVALID_DRIVER_PHONENUMBER.getDescription());

		// System.out.println("Driver Phone Number: "+driverPhoneNumber);

		try {
			CurrentVehicleLocation currentVehicleLocation = vehicleLocationService
					.fetchVehicleLocation(driverPhoneNumber);
			if (currentVehicleLocation != null) {
				currentVehicleLocation.setLastHeartbeatDttm(new Date());
				vehicleLocationService.saveOrUpdateCurrentVehicleLocation(currentVehicleLocation);
				return currentVehicleLocation;
			}

			else {
				return new CurrentVehicleLocation(TruxErrorCodes.UNKNOWN_VEHICLE_LOCATION.getCode(),
						TruxErrorCodes.UNKNOWN_VEHICLE_LOCATION.getDescription());

			}
		} catch (Exception e) {
			e.printStackTrace();
			return new CurrentVehicleLocation(TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
					TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());
		}

	}

	@ResponseBody
	@RequestMapping(value = "/saveVehicleLocation", method = RequestMethod.POST)
	public VehicleLocation saveVehicleLocation(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String driverPhoneNumber, @RequestParam Double latitude, @RequestParam Double longitude,
			@RequestParam(required = false) String driverName, @RequestParam String vehicleNumber,
			@RequestParam(required = false) String vehicleType, @RequestParam(required = false) String bookingId,
			@RequestParam(required = false) String accuracy, @RequestParam(required = false) String batteryStatus) {
		try {
			if (driverPhoneNumber == null)
				return new VehicleLocation(TruxErrorCodes.INVALID_DRIVER_PHONENUMBER.getCode(),
						TruxErrorCodes.INVALID_DRIVER_PHONENUMBER.getDescription());

			if (latitude == null)
				return new VehicleLocation(TruxErrorCodes.INVALID_LATITUDE.getCode(),
						TruxErrorCodes.INVALID_LATITUDE.getDescription());

			if (longitude == null)
				return new VehicleLocation(TruxErrorCodes.INVALID_LONGITUDE.getCode(),
						TruxErrorCodes.INVALID_LONGITUDE.getDescription());

			if (vehicleNumber == null)
				return new VehicleLocation(TruxErrorCodes.INVALID_VEHICLE_NUMBER.getCode(),
						TruxErrorCodes.INVALID_VEHICLE_NUMBER.getDescription());

			Integer latitude_left = latitude.intValue();
			Integer longitude_left = longitude.intValue();

			Double latitude_right = latitude - latitude_left;
			Double longitude_right = longitude - longitude_left;

			if (longitude < 0) {
				Double lon_right = longitude - longitude_left;
				longitude_right = ((1 - lon_right) - 1);
			}

			if (latitude < 0) {
				Double lat_right = latitude - latitude_left;
				latitude_right = ((1 - lat_right) - 1);
			}

			String vehicleAvailbilityStatus = null;

			if (bookingId == null || bookingId.trim().length() == 0)
				vehicleAvailbilityStatus = "0";
			else
				vehicleAvailbilityStatus = "1";

			VehicleLocation vehicleLocation = new VehicleLocation(driverPhoneNumber, driverName, longitude, latitude,
					vehicleAvailbilityStatus, latitude_left, latitude_right, longitude_left, longitude_right,
					new Date(), vehicleNumber, vehicleType, accuracy, batteryStatus);
			CurrentVehicleLocation currentVehicleLocation = new CurrentVehicleLocation(driverPhoneNumber, driverName,
					longitude, latitude, vehicleAvailbilityStatus, latitude_left, latitude_right, longitude_left,
					longitude_right, new Date(), vehicleNumber, vehicleType, accuracy, batteryStatus);

			if (bookingId != null && bookingId.trim().length() > 0) {
				vehicleLocation.setBooking_id(Integer.parseInt(bookingId));
				currentVehicleLocation.setBooking_id(Integer.parseInt(bookingId));

			}

			try {
				vehicleLocationService.saveVehicleLocation(vehicleLocation);
				vehicleLocationService.saveOrUpdateCurrentVehicleLocation(currentVehicleLocation);
				return vehicleLocation;
			} catch (Exception e) {
				return new VehicleLocation(TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
						TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());
			}

		} catch (Exception er) {
			er.printStackTrace();
			return new VehicleLocation(TruxErrorCodes.UNKNOWN_EXCEPTION.getCode(),
					TruxErrorCodes.UNKNOWN_EXCEPTION.getDescription());
		}
	}

	@ResponseBody
	@RequestMapping(value = "/deallocateDriver", method = RequestMethod.GET)
	public DriverDeviceVehicleMapping deallocateDriver(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String driverPhoneNumber) {
		try {
			if (driverPhoneNumber == null)
				return new DriverDeviceVehicleMapping(TruxErrorCodes.INVALID_DRIVER_PHONENUMBER.getCode(),
						TruxErrorCodes.INVALID_DRIVER_PHONENUMBER.getDescription());

			DriverDeviceVehicleMapping driverDeviceVehicleMapping = registrationService
					.getDriverDeviceDetail(driverPhoneNumber);

			if (driverDeviceVehicleMapping == null)
				return new DriverDeviceVehicleMapping(TruxErrorCodes.NO_DRIVER_REGISTERED_WITH_PHONE_NUMBER.getCode(),
						TruxErrorCodes.NO_DRIVER_REGISTERED_WITH_PHONE_NUMBER.getDescription());

			driverDeviceVehicleMapping.setDriverStatus(0);

			registrationService.saveOrUpdateDDVM(driverDeviceVehicleMapping);

			return driverDeviceVehicleMapping;

		} catch (Exception er) {
			er.printStackTrace();
			return new DriverDeviceVehicleMapping();
		}
	}

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public @ResponseBody String provideUploadInfo() {
		return "You can upload a file by posting to this same URL.";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody UploadDocumentModel handleFileUpload(@RequestParam("bookingid") Integer bookingid,
			@RequestParam("file") MultipartFile file) {
		UploadDocumentModel documentModel = new UploadDocumentModel();
		try {
			CustomerBookingDetails customerBookingDetails = bookingService.getBookingDetailsById(bookingid);

			if (!file.isEmpty()) {
				try {
					String name = file.getName();
					AWSS3Uploader.uploadVehicleFile(name, file.getContentType(), file.getSize(), file.getInputStream(),
							documentModel);

					byte[] bytes = file.getBytes();
					// BufferedOutputStream stream = new
					// BufferedOutputStream(new FileOutputStream(new
					// File(name)));
					// stream.write(bytes);
					// stream.close();
					documentModel.setUploadStatus("SUCCESS");
					customerBookingDetails.setDocumentuploadurl(documentModel.getUploadURL());
					bookingService.savoOrUpdate(customerBookingDetails);
					return documentModel;
				} catch (Exception e) {
					documentModel.setUploadStatus("Failed");
					documentModel.setErrorMessage(e.getMessage());
					return documentModel;
				}
			} else {
				documentModel.setUploadStatus("Failed");
				documentModel.setErrorMessage("Uploed failed  because the file was empty!");
				return documentModel;
			}
		} catch (Exception er) {
			er.printStackTrace();
			documentModel.setUploadStatus("Failed");
			documentModel.setErrorMessage("Uploed failed  because the file was empty!");
			return documentModel;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/trackVehicleByBookingId", method = RequestMethod.GET)
	public VehicleTrackingModel trackVehicleByBookingId(HttpServletRequest request, HttpServletResponse response,
			Integer booking_id) {

		VehicleTrackingModel vehicleTrackingModel = new VehicleTrackingModel();
		try {

			CustomerBookingDetails customerBookingDetails = bookingService.getBookingDetailsById(booking_id);

			CurrentVehicleLocation currentVehicleLocation = vehicleLocationService
					.fetchLatestLocationByBookingId(booking_id + "");

			if (customerBookingDetails == null) {
				BookedTruxTrackingModel bookedTruxTrackingModel = new BookedTruxTrackingModel(
						TruxErrorCodes.NO_TRACKING_INFO_PRESENT_FOR_THIS_BOOKING_ID.getCode(),
						TruxErrorCodes.NO_TRACKING_INFO_PRESENT_FOR_THIS_BOOKING_ID.getDescription());
				return vehicleTrackingModel;
			}

			if (customerBookingDetails.getBookingStatus().equals("Confirmed")) {
				vehicleTrackingModel.setStatus("SUCCESS");
				vehicleTrackingModel.setRequest_type("SHARE_RIDES");
				vehicleTrackingModel.setState_id("2");
				vehicleTrackingModel.createBookingInstance();
				vehicleTrackingModel.getBooking().setCrn(customerBookingDetails.getBookingId() + "");
				vehicleTrackingModel.getBooking().setBooking_id(customerBookingDetails.getBookingId() + "");
				vehicleTrackingModel.getBooking().setPickup_lat(customerBookingDetails.getFromLat() + "");
				vehicleTrackingModel.getBooking().setPickup_lng(customerBookingDetails.getFromLong() + "");

				vehicleTrackingModel.getBooking().createInnerClasses();

				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setLicense_number(customerBookingDetails.getVehicleNumber());
				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setCar_model(customerBookingDetails.getVehicleType());
				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setDriver_name(customerBookingDetails.getDriverName());
				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setDriver_mobile(customerBookingDetails.getDriverPhonenumber());
				vehicleTrackingModel.getBooking().getAlloted_cab_info().setColor("");

				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setId(customerBookingDetails.getBookingId() + "");
				if (currentVehicleLocation != null) {
					vehicleTrackingModel.getBooking().getAlloted_cab_info()
							.setLat(currentVehicleLocation.getLatitude() + "");
					vehicleTrackingModel.getBooking().getAlloted_cab_info()
							.setLng(currentVehicleLocation.getLongitude() + "");
					double distance = LatLngTool.distance(
							new com.javadocmd.simplelatlng.LatLng(customerBookingDetails.getFromLat(),
									customerBookingDetails.getFromLong()),
							new com.javadocmd.simplelatlng.LatLng(currentVehicleLocation.getLatitude(),
									currentVehicleLocation.getLongitude()),
							LengthUnit.METER);
					double time = distance * 6 / 2500;
					vehicleTrackingModel.getBooking().getAlloted_cab_info().getDuration().setUnit("MINUTE");
					vehicleTrackingModel.getBooking().getAlloted_cab_info().getDuration()
							.setValue(((int) time + 1) + "");
					vehicleTrackingModel.getBooking().getAlloted_cab_info().getDistance().setUnit("METER");
					vehicleTrackingModel.getBooking().getAlloted_cab_info().getDistance()
							.setValue(((int) distance + 1) + "");

				}
				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setPickup_address(customerBookingDetails.getFromLocation());

			}

			if (customerBookingDetails.getBookingStatus().equals("Loading Start")
					|| customerBookingDetails.getBookingStatus().equals("Loading Complete")) {
				vehicleTrackingModel.setStatus("SUCCESS");
				vehicleTrackingModel.setRequest_type("SHARE_RIDES");
				vehicleTrackingModel.setState_id("3");
				vehicleTrackingModel.createBookingInstance();
				vehicleTrackingModel.getBooking().setCrn(customerBookingDetails.getBookingId() + "");
				vehicleTrackingModel.getBooking().setBooking_id(customerBookingDetails.getBookingId() + "");
				vehicleTrackingModel.getBooking().setPickup_lat(customerBookingDetails.getFromLat() + "");
				vehicleTrackingModel.getBooking().setPickup_lng(customerBookingDetails.getFromLong() + "");

				vehicleTrackingModel.getBooking().createInnerClasses();

				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setLicense_number(customerBookingDetails.getVehicleNumber());
				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setCar_model(customerBookingDetails.getVehicleType());
				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setDriver_name(customerBookingDetails.getDriverName());
				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setDriver_mobile(customerBookingDetails.getDriverPhonenumber());
				vehicleTrackingModel.getBooking().getAlloted_cab_info().setColor("");

				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setId(customerBookingDetails.getBookingId() + "");
				if (currentVehicleLocation != null) {
					vehicleTrackingModel.getBooking().getAlloted_cab_info()
							.setLat(currentVehicleLocation.getLatitude() + "");
					vehicleTrackingModel.getBooking().getAlloted_cab_info()
							.setLng(currentVehicleLocation.getLongitude() + "");
				}
				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setPickup_address(customerBookingDetails.getFromLocation());

				/*
				 * 
				 * 
				 * double time = distance*6/2500;
				 * vehicleTrackingModel.getBooking().getAlloted_cab_info().
				 * getDuration().setUnit("MINUTE");
				 * vehicleTrackingModel.getBooking().getAlloted_cab_info().
				 * getDuration().setValue(((int)time+1)+"");
				 * vehicleTrackingModel.getBooking().getAlloted_cab_info().
				 * getDistance().setUnit("METER");
				 * vehicleTrackingModel.getBooking().getAlloted_cab_info().
				 * getDistance().setValue(((int)distance+1)+"");
				 * 
				 */

			}

			if (customerBookingDetails.getBookingStatus().equals("Ride Start")) {
				vehicleTrackingModel.setStatus("SUCCESS");
				vehicleTrackingModel.setRequest_type("SHARE_RIDES");
				vehicleTrackingModel.setState_id("4");
				vehicleTrackingModel.createBookingInstance();
				vehicleTrackingModel.getBooking().setCrn(customerBookingDetails.getBookingId() + "");
				vehicleTrackingModel.getBooking().setBooking_id(customerBookingDetails.getBookingId() + "");
				vehicleTrackingModel.getBooking().setPickup_lat(customerBookingDetails.getFromLat() + "");
				vehicleTrackingModel.getBooking().setPickup_lng(customerBookingDetails.getFromLong() + "");

				vehicleTrackingModel.getBooking().createInnerClasses();

				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setLicense_number(customerBookingDetails.getVehicleNumber());
				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setCar_model(customerBookingDetails.getVehicleType());
				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setDriver_name(customerBookingDetails.getDriverName());
				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setDriver_mobile(customerBookingDetails.getDriverPhonenumber());
				vehicleTrackingModel.getBooking().getAlloted_cab_info().setColor("");

				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setId(customerBookingDetails.getBookingId() + "");
				if (currentVehicleLocation != null) {
					vehicleTrackingModel.getBooking().getAlloted_cab_info()
							.setLat(currentVehicleLocation.getLatitude() + "");
					vehicleTrackingModel.getBooking().getAlloted_cab_info()
							.setLng(currentVehicleLocation.getLongitude() + "");

				}
				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setPickup_address(customerBookingDetails.getFromLocation());

				/*
				 * 
				 * 
				 * double time = distance*6/2500;
				 * vehicleTrackingModel.getBooking().getAlloted_cab_info().
				 * getDuration().setUnit("MINUTE");
				 * vehicleTrackingModel.getBooking().getAlloted_cab_info().
				 * getDuration().setValue(((int)time+1)+"");
				 * vehicleTrackingModel.getBooking().getAlloted_cab_info().
				 * getDistance().setUnit("METER");
				 * vehicleTrackingModel.getBooking().getAlloted_cab_info().
				 * getDistance().setValue(((int)distance+1)+"");
				 * 
				 */

			}

			if (customerBookingDetails.getBookingStatus().equals("Unloading Start")
					|| customerBookingDetails.getBookingStatus().equals("Unloading Complete")
					|| customerBookingDetails.getBookingStatus().equals("Completed")) {
				vehicleTrackingModel.setStatus("SUCCESS");
				vehicleTrackingModel.setRequest_type("SHARE_RIDES");
				vehicleTrackingModel.setState_id("5");
				vehicleTrackingModel.createBookingInstance();
				vehicleTrackingModel.getBooking().setCrn(customerBookingDetails.getBookingId() + "");
				vehicleTrackingModel.getBooking().setBooking_id(customerBookingDetails.getBookingId() + "");
				vehicleTrackingModel.getBooking().setPickup_lat(customerBookingDetails.getFromLat() + "");
				vehicleTrackingModel.getBooking().setPickup_lng(customerBookingDetails.getFromLong() + "");

				vehicleTrackingModel.getBooking().createInnerClasses();

				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setLicense_number(customerBookingDetails.getVehicleNumber());
				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setCar_model(customerBookingDetails.getVehicleType());
				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setDriver_name(customerBookingDetails.getDriverName());
				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setDriver_mobile(customerBookingDetails.getDriverPhonenumber());
				vehicleTrackingModel.getBooking().getAlloted_cab_info().setColor("");

				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setId(customerBookingDetails.getBookingId() + "");
				if (currentVehicleLocation != null) {
					vehicleTrackingModel.getBooking().getAlloted_cab_info()
							.setLat(currentVehicleLocation.getLatitude() + "");
					vehicleTrackingModel.getBooking().getAlloted_cab_info()
							.setLng(currentVehicleLocation.getLongitude() + "");
				}
				vehicleTrackingModel.getBooking().getAlloted_cab_info()
						.setPickup_address(customerBookingDetails.getFromLocation());

				// 2015-04-23T22:25:48+05:30

				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
				// dateFormat.format(customerBookingDetails.getExpectedRideEndTime());

				Date rideStartTime = customerBookingDetails.getRide_starttime();

				if (customerBookingDetails.getRide_starttime() != null)
					vehicleTrackingModel.setStart_at(
							dateFormat.format(new Date(rideStartTime.getTime() + (int) 5.50 * 3600 * 1000)));
				else
					vehicleTrackingModel
							.setStart_at(dateFormat.format(new Date(new Date().getTime() + (int) 5.50 * 3600 * 1000)));

				if (customerBookingDetails.getJourneycompletetime() != null)
					vehicleTrackingModel.setFinished_at(dateFormat.format(new Date(
							customerBookingDetails.getJourneycompletetime().getTime() + (int) 5.50 * 3600 * 1000)));
				else
					vehicleTrackingModel.setFinished_at(
							dateFormat.format(new Date(new Date().getTime() + (int) 5.50 * 3600 * 1000)));

				/*
				 * 
				 * 
				 * double time = distance*6/2500;
				 * vehicleTrackingModel.getBooking().getAlloted_cab_info().
				 * getDuration().setUnit("MINUTE");
				 * vehicleTrackingModel.getBooking().getAlloted_cab_info().
				 * getDuration().setValue(((int)time+1)+"");
				 * vehicleTrackingModel.getBooking().getAlloted_cab_info().
				 * getDistance().setUnit("METER");
				 * vehicleTrackingModel.getBooking().getAlloted_cab_info().
				 * getDistance().setValue(((int)distance+1)+"");
				 * 
				 */

			}

			return vehicleTrackingModel;
		} catch (Exception er) {
			er.printStackTrace();
			return vehicleTrackingModel;
		}
	}

	public static void main(String[] args) {

		// 2015-04-23T22:25:48+05:30
		// 2015-04-25T22:31:48GMT+05:30

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
		// System.out.println(dateFormat.format(new Date()));
	}

	@ResponseBody
	@RequestMapping(value = "/getVehicleLocationByBookingId", method = RequestMethod.GET)
	public BookedTruxTrackingModel getVehicleLocationByBookingId(HttpServletRequest request,
			HttpServletResponse response, Integer bookingId) {

		BookedTruxTrackingModel bookedCabTrackingModel = new BookedTruxTrackingModel();
		try {

			CustomerBookingDetails customerBookingDetails = bookingService.getBookingDetailsById(bookingId);

			CurrentVehicleLocation currentVehicleLocation = vehicleLocationService
					.fetchLatestLocationByBookingId(bookingId + "");

			if (customerBookingDetails == null) {
				BookedTruxTrackingModel bookedTruxTrackingModel = new BookedTruxTrackingModel(
						TruxErrorCodes.NO_TRACKING_INFO_PRESENT_FOR_THIS_BOOKING_ID.getCode(),
						TruxErrorCodes.NO_TRACKING_INFO_PRESENT_FOR_THIS_BOOKING_ID.getDescription());
				return bookedTruxTrackingModel;
			}

			if (customerBookingDetails.getBookingStatus().equals("Completed"))
				bookedCabTrackingModel.setJourneyStatus(1);

			bookedCabTrackingModel.setState_id(customerBookingDetails.getBookingStatus());

			BookedTruxTrackingModel.Booking booking = bookedCabTrackingModel.new Booking();

			booking.setCrn(bookingId + "");
			booking.setBooking_id(bookingId + "");
			booking.setPickup_lat(customerBookingDetails.getFromLat() + "");
			booking.setPickup_lng(customerBookingDetails.getFromLong() + "");
			bookedCabTrackingModel.setBooking(booking);

			BookedTruxTrackingModel.Alloted_trux_info alloted_cab_info = bookedCabTrackingModel.new Alloted_trux_info();
			booking.setAlloted_cab_info(alloted_cab_info);

			if (currentVehicleLocation != null) {
				alloted_cab_info.setLat(currentVehicleLocation.getLatitude() + "");
				alloted_cab_info.setLng(currentVehicleLocation.getLongitude() + "");
			}

			alloted_cab_info.setCar_model(customerBookingDetails.getVehicleType());

			alloted_cab_info.setLicense_number(customerBookingDetails.getVehicleNumber());
			alloted_cab_info.setDriver_mobile(customerBookingDetails.getDriverPhonenumber());
			alloted_cab_info.setDriver_name(customerBookingDetails.getDriverName());

			alloted_cab_info.setPickup_address(customerBookingDetails.getFromLocation());

			return bookedCabTrackingModel;
		} catch (Exception er) {
			er.printStackTrace();
			return bookedCabTrackingModel;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/getNearByVehiclesLocation", method = RequestMethod.GET)
	public List<VehicleLocation> getVehicleDetails(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Double latitude, @RequestParam Double longitude) {

		List<VehicleLocation> vehicleLocationList = new ArrayList<VehicleLocation>();
		try {
			if (latitude == null) {
				vehicleLocationList.add(new VehicleLocation(TruxErrorCodes.INVALID_LATITUDE.getCode(),
						TruxErrorCodes.INVALID_LATITUDE.getCode()));
				return vehicleLocationList;
			}

			if (longitude == null) {
				vehicleLocationList.add(new VehicleLocation(TruxErrorCodes.INVALID_LONGITUDE.getCode(),
						TruxErrorCodes.INVALID_LONGITUDE.getCode()));
				return vehicleLocationList;
			}

			Integer latitude_left = latitude.intValue();
			Integer longitude_left = longitude.intValue();

			Double latitude_right = latitude - latitude_left;
			Double longitude_right = longitude - longitude_left;

			if (longitude < 0) {
				Double lon_right = longitude - longitude_left;
				longitude_right = ((1 - lon_right) - 1);
			}

			if (latitude < 0) {
				Double lat_right = latitude - latitude_left;
				latitude_right = ((1 - lat_right) - 1);
			}

			vehicleLocationList = vehicleLocationService.getNearByVehiclesList(latitude_left, longitude_left);

			LatLng userLocation = new LatLng(latitude, longitude);

			if (vehicleLocationList != null && !vehicleLocationList.isEmpty()) {
				for (VehicleLocation vehicleLocation : vehicleLocationList) {

					LatLng driverLocation = new LatLng(vehicleLocation.getLatitude(), vehicleLocation.getLongitude());
					Double distanceInKM = LatLngTool.distance(userLocation, driverLocation, LengthUnit.KILOMETER);

					vehicleLocation.setDistanceFromUser(distanceInKM.intValue());
				}
			}

			Collections.sort(vehicleLocationList, new VehicleComparator());

			return vehicleLocationList;
		} catch (Exception er) {
			er.printStackTrace();
			return vehicleLocationList;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/calculateDistance", method = RequestMethod.POST, headers = {
			"Content-type=application/json" })
	public RestResponce calculateDistance(@RequestBody List<Distance> distance) {
		RestResponce restRsp = new RestResponce();

		try {
			if (distance != null) {
				// PrintWriter writer = new
				// PrintWriter("D:/code/trux-api-v2/data.txt", "UTF-8");

				double sum = 0.0;
				double data[][] = new double[distance.size()][2];

				for (Distance distanceElement : distance) // or sArray
				{

					double lat1 = Double.parseDouble(distanceElement.getLattitude());
					double long1 = Double.parseDouble(distanceElement.getLogitude());

					data[distance.indexOf(distanceElement)][0] = lat1;
					data[distance.indexOf(distanceElement)][1] = long1;
					//

					// System.out.println((distance.indexOf(distanceElement)+1)
					// + ".\t" + distanceElement.getLattitude() + "," +
					// distanceElement.getLogitude());
				}

				for (int i = 0; i < distance.size() - 1; i++) {
					sum = sum + Utils.haversine(data[i][0], data[i][1], data[i + 1][0], data[i + 1][1]);
				}

				// select * from current_vehicle_location (device_id, loct_time,
				// loct_lattitude, loct_logitude) values (1,
				// '2015-11-01T09:00+1300', 24.9999, 77.234235)
				// writer.println(cls.toString());
				// writer.close();

				restRsp.setErrorCode("100");
				restRsp.setErrorMesaage("sucesss");
				restRsp.setData(sum);
			} else {
				restRsp.setErrorCode("101");
				restRsp.setErrorMesaage("failure! No Data Sent");
			}

		} catch (Exception er) {
			er.printStackTrace();
			return restRsp;
		}
		return restRsp;
	}

}
