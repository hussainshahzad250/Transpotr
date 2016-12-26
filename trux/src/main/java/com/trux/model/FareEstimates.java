package com.trux.model;

public class FareEstimates {

	private double wait_time;
	private double distance;
	private double approx_travel_time;
	private String travel_time_unit;
	private double fare;
	private double discount;
	private String discount_reason;
	private double surcharges;
	private String surcharge_reason;
	private String truck_category;
	private double pickup_lng;
	private double pickup_lat;
	private double drop_lng;
	private double drop_lat;
	private long   pickup_time;
	
	
	public double getWait_time() {
		return wait_time;
	}
	public void setWait_time(double wait_time) {
		this.wait_time = wait_time;
	}
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	public double getApprox_travel_time() {
		return approx_travel_time;
	}
	public void setApprox_travel_time(double approx_travel_time) {
		this.approx_travel_time = approx_travel_time;
	}
	public String getTravel_time_unit() {
		return travel_time_unit;
	}
	public void setTravel_time_unit(String travel_time_unit) {
		this.travel_time_unit = travel_time_unit;
	}
	public double getFare() {
		return fare;
	}
	public void setFare(double fare) {
		this.fare = fare;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getDiscount_reason() {
		return discount_reason;
	}
	public void setDiscount_reason(String discount_reason) {
		this.discount_reason = discount_reason;
	}
	public double getSurcharges() {
		return surcharges;
	}
	public void setSurcharges(double surcharges) {
		this.surcharges = surcharges;
	}
	public String getSurcharge_reason() {
		return surcharge_reason;
	}
	public void setSurcharge_reason(String surcharge_reason) {
		this.surcharge_reason = surcharge_reason;
	}
	public String getTruck_category() {
		return truck_category;
	}
	public void setTruck_category(String truck_category) {
		this.truck_category = truck_category;
	}
	public double getPickup_lng() {
		return pickup_lng;
	}
	public void setPickup_lng(double pickup_lng) {
		this.pickup_lng = pickup_lng;
	}
	public double getPickup_lat() {
		return pickup_lat;
	}
	public void setPickup_lat(double pickup_lat) {
		this.pickup_lat = pickup_lat;
	}
	public double getDrop_lng() {
		return drop_lng;
	}
	public void setDrop_lng(double drop_lng) {
		this.drop_lng = drop_lng;
	}
	public double getDrop_lat() {
		return drop_lat;
	}
	public void setDrop_lat(double drop_lat) {
		this.drop_lat = drop_lat;
	}
	public long getPickup_time() {
		return pickup_time;
	}
	public void setPickup_time(long pickup_time) {
		this.pickup_time = pickup_time;
	}

	
	public static FareEstimates getHardCodedInstance(String truckCategory) {
		
		FareEstimates  fareEstimates = new FareEstimates();
		fareEstimates.setApprox_travel_time(134.38);
		fareEstimates.setWait_time(140.0);
		fareEstimates.setDistance(40316.9);
		fareEstimates.setApprox_travel_time(134.38);
		fareEstimates.setTravel_time_unit("MINUTE");
		fareEstimates.setFare(699);
		fareEstimates.setDiscount(0);
		fareEstimates.setDiscount_reason("APP BOOKING");
		fareEstimates.setSurcharges(0);
		fareEstimates.setSurcharge_reason("Peak Surcharge");
		fareEstimates.setTruck_category(truckCategory);
		
		return fareEstimates;
	}
	
}
