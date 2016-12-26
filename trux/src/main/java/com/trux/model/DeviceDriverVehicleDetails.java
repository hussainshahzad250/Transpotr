package com.trux.model;

public class DeviceDriverVehicleDetails {

	private String driverFirstName;
	
	private String driverLastName;
	
	private String driverPhoneNumber;
	
	private String vehicleNumber;
	
	private String vehicleType;

	
	public DeviceDriverVehicleDetails(String driverFirstName,
			String driverLastName, String driverPhoneNumber,
			String vehicleNumber, String vehicleType) {
		super();
		this.driverFirstName = driverFirstName;
		this.driverLastName = driverLastName;
		this.driverPhoneNumber = driverPhoneNumber;
		this.vehicleNumber = vehicleNumber;
		this.vehicleType = vehicleType;
	}

	public DeviceDriverVehicleDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getDriverFirstName() {
		return driverFirstName;
	}

	public void setDriverFirstName(String driverFirstName) {
		this.driverFirstName = driverFirstName;
	}

	public String getDriverLastName() {
		return driverLastName;
	}

	public void setDriverLastName(String driverLastName) {
		this.driverLastName = driverLastName;
	}

	public String getDriverPhoneNumber() {
		return driverPhoneNumber;
	}

	public void setDriverPhoneNumber(String driverPhoneNumber) {
		this.driverPhoneNumber = driverPhoneNumber;
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
	
	
}
