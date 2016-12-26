package com.trux.enumerations;


public enum TruxErrorCodes {
	 
	  EMAIL_NOT_VALID("E001", "Please enter a valid email."),
	  EMAIL_ALREADY_REGISTERED("E002", "Email Address Already Registered"),
	  USER_NOT_EXISTS("E003","User doesn't exists."),
	  USER_SOURCE_INVALID("E004", "Please provide a valid user source."),
	  INVALID_PASSWORD("E005","Invalid username or password"),
	  INVALID_USER_FB_ID("E006", "Invalid user FB Id."),
	  INVLID_USER_FB_AUTH_TOKEN("E007","Invalid user FB Auth Token."),
	  REGISTARATION_FAILED("E008", "Registration failed."),
	  UNKNOWN_EXCEPTION("E009", "Some thing went wrong please try again later."),
	  INVALID_USER("E010", "Invalid username or password"),
	  INVALID_MAJOR_MINOR_NUMBER("E011", "Invalid major minor number."),
	  NO_RECORDS_FOUND("E012", "No records found."),
	  INVALID_LATITUDE("E013","Invalid Latitude"),
	  INVALID_LONGITUDE("E014","Invalid Longitude"),
	  INVALID_STATUS("E015", "Invalid status"),
	  INVALID_ICON_URL("E016", "Invalid image url"),
	  SUCCESSFULLY_REGISTERED("S001","Successfully Registered."),
	  INVALID_PHONENUBER("E017","Invalid phone number."),
	  BOOKING_ERROR("E018","Something went wrong while booking your ride. Please try again."),
	  INVALID_DEVICE_ID("E019","Invalid Device Id."),
	  INVALID_DRIVER_PHONENUMBER("E020","Invalid driver phone number."),
	  INVALID_FROM_LATITUDE("E021","Invalid From Latitude"),
	  INVALID_FROM_LONGITUDE("E022","Invalid From Longitude"),
	  INVALID_TO_LONGITUDE("E023","Invalid To Longitude"),
	  INVALID_TO_LATITUDE("E024","Invalid TO Latitude"),
	  INVALID_VEHICLE_NUMBER("E025","Invalid Vehicle Number"),
	  INVALID_BOOKING_ID("E026","Invalid booking id."),
	  INVALID_TOTAL_FARE("E027","Invalid total fare."),
	  INVALID_DISTANCE_TRAVELLED("E028","Invalid distance travelled."),
	  INVALID_DRIVER("E029","Invalid Driver"),
	  INVALID_DRIVER_TO_LOGIN("E0E29","ड्राइवर को अपने स्टैंड के पास जाकर ही लागिन करना होगा l"),
	  INVALID_DRIVER_ID("E030","Invalid driver id."),
	  INVALID_VEHICLE_ID("E031","Invalid vehicle id."),
	  INVALID_VEHICLE_TYPE("E032","Invalid vehicle type."),
	  PHONE_NOT_VALID("E033", "Please enter a valid phone number."),
	  PHONE_NO_ALREADY_REGISTERED("E034", "Phone Number is already registered"),
	  UNHANDLED_ERROR("E035", "Unhandled error came, please inform krishna@triyasoft.com"),
	  FAILED_TO_UPDATE_PROFILE("E036","Failed to update profile"),
	  FAILED_TO_RESET_PASSWORD("E037","Failed to reset password"),
	  INVALID_OLD_PASSWORD("E038","Invalid old password"),
	  INVALID_NEW_PASSWORD("E039","Invalid new password"),
	  INVALID_BOOKING("E040","No such booking available for this user."),
	  CANCEL_BOOKING_EXCEPTION("E041","you can cancel only pending bookings."),
	  INVALIDE_FROM_LOCATION("E042", "Please Choose a valid from Location."),
	  INVALIDE_TO_LOCATION("E043", "Please Choose a valid to Location."),
	  INVALIDE_VEHICLE_TYPE("E044", "Please Choose a valid Vehicle Type."),
	  BOOKING_ID_DOES_NOT_EXISTS("E045", "Booking ID does not Exists."),
	  BOOKING_ACCEPTEDBYSOME_OTHER_DRIVER("E044", "यह बुकिंग किसी और ट्रक ड्राइवर ने स्वीकार कर ली है, कृपया अगली बुकिंग की प्रतीक्षा करें |"),
	  NO_TRACKING_INFO_PRESENT_FOR_THIS_BOOKING_ID("E045","No Tracking Info Present for this booking ID."),
	  NO_DRIVER_REGISTERED_WITH_PHONE_NUMBER("E046","No Driver Registered with this phone number."),
	  GEO_NOT_ALLOWED("E047","We are currently not operating in your location. We will be coming to your location soon.... "),
	  UNKNOWN_VEHICLE_LOCATION("E048", "Vehicle Location not Available for this driver"),
	  BOOKING_ALREADY_COMPLETED("E049","Booking Already completed."),
	  REVERSE_COMPUTATION_LESS_THAN_ZERO_TRAVEL_TIME("E050","Reverse computation says travel duration less than zero. Kindly connect krishna@triyasoft.com or 9899016401."),
	  LEAD_REGISTRATION_EXCEPTIOM("E051","Please provied  image of visiting card."),
	  LEAD_AGENTID_EXCEPTIOM("E052","Please provied  lead agent id."),
	  LEAD_AGENTID_LOGIN_EXCEPTION_MESSAGE("E053","You are not valid for login or register to login."),
	  LEAD_AGENTID_CREATION_MESSAGE("E054","Your register to login succesfully."),
	  VEHCLE_REGISTRATION_MESSAGE1("E055","Please provide the valid driver mobile."),
	  VEHCLE_REGISTRATION_MESSAGE2("E056","Please provide the valid image or pdf vehicle permite file."),
	  VEHCLE_REGISTRATION_ERROR_MESSAGE("E057","Your request is not vadil to register."),
	  DRIVER_REGISTRATION_MESSAGE1("E058","Please provide the valid image or pdf DL File."),
	  DRIVER_REGISTRATION_MESSAGE2("E059","Please provide the valid image or pdf Pan Card File."),
	  DRIVER_REGISTRATION_MESSAGE3("E060","Please provide the valid image or pdf Police verification report File."),
	  DRIVER_REGISTRATION_ERROR_MESSAGE("E061","Your request is not vadil to register."),
	  VEHICLE_TYPE_MESSAGE("E062","Your request is not vadil to get vahilce records."),
	  MODULE_RIGHT_TO_ACCESS_MESSAGE("E063","Are you not valid to access the modules"),
	  LEAD_AGENTID_CREATION_ERROR_MESSAGE("E064","Fail your registration. Reason behind duplicate entry for primary key phone number Or email. "),
	  DRIVER_COLLECTION_CREATION_ERROR_MESSAGE("E065","Fail your registration. Reason behind duplicate entry for primary key Driver ID number Or Vehicle ID Number. "),
	  DRIVER_MULTI_DROP_POINT_MESSAGE("E066","Fail your Ride. Reason behind Driver Phone number Or Agent ID Number not match. ");
		  



	 
	  

	  private final String code;
	  private final String description;

	  private TruxErrorCodes(String code, String description) {
	    this.code = code;
	    this.description = description;
	  }


	  public String getCode() {
		return code;
	  }

	  public String getDescription() {
		return description;
	  }

	@Override
	  public String toString() {
	    return code + ": " + description;
	  }
	}
