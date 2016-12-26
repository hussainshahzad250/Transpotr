package com.trux.model;

public class DriverRegistratioModel {

	private Integer driverId;

	private Integer vehicleId;

	private Integer deviceId;

	private String driverPhoneNumber;

	private String deviceUUID;

	private String vehicleNumber;

	private String vehicleType;

	private String driverName;

	private String driverStatand;

	private String driverAssociatedByClient;

	private String driverHub;

	private String driverCluster;

	private Integer loginStatus;

	private Integer driverStatus;

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public Integer getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getDriverPhoneNumber() {
		return driverPhoneNumber;
	}

	public void setDriverPhoneNumber(String driverPhoneNumber) {
		this.driverPhoneNumber = driverPhoneNumber;
	}

	public String getDeviceUUID() {
		return deviceUUID;
	}

	public void setDeviceUUID(String deviceUUID) {
		this.deviceUUID = deviceUUID;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public Integer getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(Integer loginStatus) {
		this.loginStatus = loginStatus;
	}

	public Integer getDriverStatus() {
		return driverStatus;
	}

	public void setDriverStatus(Integer driverStatus) {
		this.driverStatus = driverStatus;
	}

	public String getDriverStatand() {
		return driverStatand;
	}

	public void setDriverStatand(String driverStatand) {
		this.driverStatand = driverStatand;
	}

	public String getDriverAssociatedByClient() {
		return driverAssociatedByClient;
	}

	public void setDriverAssociatedByClient(String driverAssociatedByClient) {
		this.driverAssociatedByClient = driverAssociatedByClient;
	}

	public String getDriverHub() {
		return driverHub;
	}

	public void setDriverHub(String driverHub) {
		this.driverHub = driverHub;
	}

	public String getDriverCluster() {
		return driverCluster;
	}

	public void setDriverCluster(String driverCluster) {
		this.driverCluster = driverCluster;
	}

}
