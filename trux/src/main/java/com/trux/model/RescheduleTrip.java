package com.trux.model;

public class RescheduleTrip {

	private String bookingId;
	private String pickupTime;
	private String vehicleType;
	private String cancelTrip;
	private String status;
	private String errorCode;
	private String errorMessage;
	
	public RescheduleTrip() {
		super();
	}
	public RescheduleTrip(String bookingId, String pickupTime,
			String vehicleType) {
		super();
		this.bookingId = bookingId;
		this.pickupTime = pickupTime;
		this.vehicleType = vehicleType;
	}
	public RescheduleTrip(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public String getBookingId() {
		return bookingId;
	}
	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}
	public String getPickupTime() {
		return pickupTime;
	}
	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getCancelTrip() {
		return cancelTrip;
	}
	public void setCancelTrip(String cancelTrip) {
		this.cancelTrip = cancelTrip;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
