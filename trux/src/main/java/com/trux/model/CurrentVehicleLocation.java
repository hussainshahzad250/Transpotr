package com.trux.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="current_vehicle_location")
public class CurrentVehicleLocation {
	
	@Id
	@Column(name="driver_phone_number")
	private String driverNumber;
	
	@Column(name="booking_id")
	private Integer booking_id;
	
	
	@Column(name="driver_name")
	private String driverName;
	
	@Column(name="longitude")
	private Double longitude;
	
	@Column(name="latitude")
	private Double latitude;
	
	@Column(name="availbility_status")
	private String vehicleAvailbilityStatus;
	
	@Column(name="latitude_left")
	private Integer latitudeLeft;
	
	@Column(name="latitude_right")
	private Double latitudeRight;
	
	@Column(name="longitude_left")
	private Integer longitudeLeft;
	
	@Column(name="longitude_right")
	private Double longitudeRight;
	
	@Column(name="vehicle_number")
	private String vehicleNumber;
	
	@Column(name="vehicle_type")
	private String vehicleType;
	
	@Column(name="updated_dttm")
	private Date updatedDate;
	
	@Column(name="last_heartbeat_dttm")
	private Date lastHeartbeatDttm;
	
	@Column(name="accuracy")
	private String accuracy;
	
	@Column(name="battery_status")
	private String battery_status;
	
	@Transient
	private String errorCode;
	
	@Transient
	private String errorMessage;
	
	@Transient
	private Integer distanceFromUser;

	public CurrentVehicleLocation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CurrentVehicleLocation(String driverNumber, String driverName, Double longitude,
			Double latitude, String vehicleAvailbilityStatus,
			Integer latitudeLeft, Double latitudeRight, Integer longitudeLeft,
			Double longitudeRight, Date updatedDate, String vehicleNumber, String vehicleType ,  String accuracy, String batteryStatus) {
		super();
		this.driverNumber = driverNumber;
		this.driverName = driverName;
		this.longitude = longitude;
		this.latitude = latitude;
		this.vehicleAvailbilityStatus = vehicleAvailbilityStatus;
		this.latitudeLeft = latitudeLeft;
		this.latitudeRight = latitudeRight;
		this.longitudeLeft = longitudeLeft;
		this.longitudeRight = longitudeRight;
		this.updatedDate = updatedDate;
		this.vehicleNumber = vehicleNumber;
		this.vehicleType = vehicleType;
		this.lastHeartbeatDttm = new Date();
		this.accuracy = accuracy;
		this.battery_status = batteryStatus;
	}
	
	

	public CurrentVehicleLocation(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	
	public Integer getDistanceFromUser() {
		return distanceFromUser;
	}

	public void setDistanceFromUser(Integer distanceFromUser) {
		this.distanceFromUser = distanceFromUser;
	}

	public String getDriverNumber() {
		return driverNumber;
	}

	public void setDriverNumber(String driverNumber) {
		this.driverNumber = driverNumber;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public String getVehicleAvailbilityStatus() {
		return vehicleAvailbilityStatus;
	}

	public void setVehicleAvailbilityStatus(String vehicleAvailbilityStatus) {
		this.vehicleAvailbilityStatus = vehicleAvailbilityStatus;
	}

	public Integer getLatitudeLeft() {
		return latitudeLeft;
	}

	public void setLatitudeLeft(Integer latitudeLeft) {
		this.latitudeLeft = latitudeLeft;
	}

	public Double getLatitudeRight() {
		return latitudeRight;
	}

	public void setLatitudeRight(Double latitudeRight) {
		this.latitudeRight = latitudeRight;
	}

	public Integer getLongitudeLeft() {
		return longitudeLeft;
	}

	public void setLongitudeLeft(Integer longitudeLeft) {
		this.longitudeLeft = longitudeLeft;
	}

	public Double getLongitudeRight() {
		return longitudeRight;
	}

	public void setLongitudeRight(Double longitudeRight) {
		this.longitudeRight = longitudeRight;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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



	public Integer getBooking_id() {
		return booking_id;
	}

	public void setBooking_id(Integer booking_id) {
		this.booking_id = booking_id;
	}

	public Date getLastHeartbeatDttm() {
		return lastHeartbeatDttm;
	}

	public void setLastHeartbeatDttm(Date lastHeartbeatDttm) {
		this.lastHeartbeatDttm = lastHeartbeatDttm;
	}

	public String getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

	public String getBattery_status() {
		return battery_status;
	}

	public void setBattery_status(String battery_status) {
		this.battery_status = battery_status;
	}
	
	
}
