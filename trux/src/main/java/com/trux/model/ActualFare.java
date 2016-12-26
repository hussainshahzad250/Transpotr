package com.trux.model;

public class ActualFare {

private Integer  bookingId;	
private String vehicleTyle;
public String getVehicleTyle() {
	return vehicleTyle;
}
public void setVehicleTyle(String vehicleTyle) {
	this.vehicleTyle = vehicleTyle;
}
public double getDistanceTravelled() {
	return distanceTravelled;
}
public void setDistanceTravelled(double distanceTravelled) {
	this.distanceTravelled = distanceTravelled;
}
public double getDuration() {
	return duration;
}
public void setDuration(double duration) {
	this.duration = duration;
}
public double getFare() {
	return fare;
}
public void setFare(double fare) {
	this.fare = fare;
}




public Integer getBookingId() {
	return bookingId;
}
public void setBookingId(Integer bookingId) {
	this.bookingId = bookingId;
}




private double distanceTravelled;
private double duration;
private double fare;

}
