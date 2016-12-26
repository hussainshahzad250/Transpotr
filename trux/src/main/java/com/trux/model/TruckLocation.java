package com.trux.model;

public class TruckLocation {


	
	 private double latitude;
	 private double longitude;
	 private String truxType;
	 private double distance;
	 private String drivername;
	 private String driverPhoneNumber;
	
	  public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getTruxType() {
		return truxType;
	}
	public void setTruxType(String truxType) {
		this.truxType = truxType;
	}
	public String getDrivername() {
		return drivername;
	}
	public void setDrivername(String drivername) {
		this.drivername = drivername;
	}
	public String getDriverPhoneNumber() {
		return driverPhoneNumber;
	}
	public void setDriverPhoneNumber(String driverPhoneNumber) {
		this.driverPhoneNumber = driverPhoneNumber;
	}
	
	  
	
	
}
