package com.trux.model;

import java.util.Date;

public class CustomerBookingVehicle {
private String	firstName;
private String	lastName; 
private String	email; 
private String	phoneNumber; 
private String	fromLocation;
private String	toLocation;
private String	vehicleType; 
private Date	rideTime;
private int	labourCount;
private Integer	paymentMode; 
private String	consigneeName; 
private String	ConsigneePhonenumber;
private String successFullMessage;
public CustomerBookingVehicle() {
	super();
}
public CustomerBookingVehicle(String firstName, String lastName, String email,
		String phoneNumber, String fromLocation, String toLocation,
		String vehicleType, Date rideTime, int labourCount,
		Integer paymentMode, String consigneeName, String consigneePhonenumber) {
	super();
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.phoneNumber = phoneNumber;
	this.fromLocation = fromLocation;
	this.toLocation = toLocation;
	this.vehicleType = vehicleType;
	this.rideTime = rideTime;
	this.labourCount = labourCount;
	this.paymentMode = paymentMode;
	this.consigneeName = consigneeName;
	ConsigneePhonenumber = consigneePhonenumber;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getFromLocation() {
	return fromLocation;
}
public void setFromLocation(String fromLocation) {
	this.fromLocation = fromLocation;
}
public String getToLocation() {
	return toLocation;
}
public void setToLocation(String toLocation) {
	this.toLocation = toLocation;
}
public String getVehicleType() {
	return vehicleType;
}
public void setVehicleType(String vehicleType) {
	this.vehicleType = vehicleType;
}
public Date getRideTime() {
	return rideTime;
}
public void setRideTime(Date rideTime) {
	this.rideTime = rideTime;
}
public int getLabourCount() {
	return labourCount;
}
public void setLabourCount(int labourCount) {
	this.labourCount = labourCount;
}
public Integer getPaymentMode() {
	return paymentMode;
}
public void setPaymentMode(Integer paymentMode) {
	this.paymentMode = paymentMode;
}
public String getConsigneeName() {
	return consigneeName;
}
public void setConsigneeName(String consigneeName) {
	this.consigneeName = consigneeName;
}
public String getConsigneePhonenumber() {
	return ConsigneePhonenumber;
}
public void setConsigneePhonenumber(String consigneePhonenumber) {
	ConsigneePhonenumber = consigneePhonenumber;
}
public String getSuccessFullMessage() {
	return successFullMessage;
}
public void setSuccessFullMessage(String successFullMessage) {
	this.successFullMessage = successFullMessage;
}

}
