package com.trux.model;

public class VehicleRegistrations {
private int id;
private String	vehicleNumber;
private String	vehicleType;
private String	model;
private String	ownerName;
private String	ownerPhoneNumber;
private String	ownerAddress;
private String	vehicleStatus;
private String	kmReading;
private byte[]	imageOfRC;
private byte[]	imageOfPermitVehicle;
private String	fuelType;
private String	agentId;
private int	driverId;//find by driver mobile


public VehicleRegistrations() {
	super();
}
public VehicleRegistrations(String vehicleNumber, String vehicleType,
		String model, String ownerName, String ownerPhoneNumber,
		String ownerAddress, String vehicleStatus, String kmReading,
		byte[] imageOfRC, byte[] imageOfPermitVehicle, String fuelType,
		String agentId, int driverId) {
	super();
	this.vehicleNumber = vehicleNumber;
	this.vehicleType = vehicleType;
	this.model = model;
	this.ownerName = ownerName;
	this.ownerPhoneNumber = ownerPhoneNumber;
	this.ownerAddress = ownerAddress;
	this.vehicleStatus = vehicleStatus;
	this.kmReading = kmReading;
	this.imageOfRC = imageOfRC;
	this.imageOfPermitVehicle = imageOfPermitVehicle;
	this.fuelType = fuelType;
	this.agentId = agentId;
	this.driverId = driverId;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
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
public String getModel() {
	return model;
}
public void setModel(String model) {
	this.model = model;
}
public String getOwnerName() {
	return ownerName;
}
public void setOwnerName(String ownerName) {
	this.ownerName = ownerName;
}
public String getOwnerPhoneNumber() {
	return ownerPhoneNumber;
}
public void setOwnerPhoneNumber(String ownerPhoneNumber) {
	this.ownerPhoneNumber = ownerPhoneNumber;
}
public String getOwnerAddress() {
	return ownerAddress;
}
public void setOwnerAddress(String ownerAddress) {
	this.ownerAddress = ownerAddress;
}
public String getVehicleStatus() {
	return vehicleStatus;
}
public void setVehicleStatus(String vehicleStatus) {
	this.vehicleStatus = vehicleStatus;
}
public String getKmReading() {
	return kmReading;
}
public void setKmReading(String kmReading) {
	this.kmReading = kmReading;
}
public byte[] getImageOfRC() {
	return imageOfRC;
}
public void setImageOfRC(byte[] imageOfRC) {
	this.imageOfRC = imageOfRC;
}
public byte[] getImageOfPermitVehicle() {
	return imageOfPermitVehicle;
}
public void setImageOfPermitVehicle(byte[] imageOfPermitVehicle) {
	this.imageOfPermitVehicle = imageOfPermitVehicle;
}
public String getFuelType() {
	return fuelType;
}
public void setFuelType(String fuelType) {
	this.fuelType = fuelType;
}
public String getAgentId() {
	return agentId;
}
public void setAgentId(String agentId) {
	this.agentId = agentId;
}
public int getDriverId() {
	return driverId;
}
public void setDriverId(int driverId) {
	this.driverId = driverId;
}

}
