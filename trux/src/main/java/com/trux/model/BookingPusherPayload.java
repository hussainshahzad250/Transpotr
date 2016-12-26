package com.trux.model;

import java.util.Date;

public class BookingPusherPayload {
	
	private Integer bookingId;

	private String fromLocation;
	
	private String toLocation;
	
	private Date expectedRideStartTime;
	
	private Date expectedRideEndTime;
	
	private Double expectedFare;
	
	private Double expectedDistance;
	
	private Double fromLatitude;
	
	private Double fromLongitude;
	
	private Double toLatitude;
	
	private Double toLongitude;
	
	

	public BookingPusherPayload(Integer bookingId, String fromLocation, String toLocation,
			Date expectedRideStartTime, Date expectedRideEndTime,
			Double expectedFare, Double expectedDistance, Double fromLatitude, Double fromLongitude, Double toLatitude, Double toLongitude ) {
		super();
		this.bookingId = bookingId;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.expectedRideStartTime = expectedRideStartTime;
		this.expectedRideEndTime = expectedRideEndTime;
		this.expectedFare = expectedFare;
		this.expectedDistance = expectedDistance;
		this.fromLatitude = fromLatitude;
		this.fromLongitude = fromLongitude;
		this.toLatitude = toLatitude;
		this.toLongitude = toLongitude;
	}

	public BookingPusherPayload() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public Double getExpectedDistance() {
		return expectedDistance;
	}

	public void setExpectedDistance(Double expectedDistance) {
		this.expectedDistance = expectedDistance;
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

	public Date getExpectedRideStartTime() {
		return expectedRideStartTime;
	}

	public void setExpectedRideStartTime(Date expectedRideStartTime) {
		this.expectedRideStartTime = expectedRideStartTime;
	}

	public Date getExpectedRideEndTime() {
		return expectedRideEndTime;
	}

	public void setExpectedRideEndTime(Date expectedRideEndTime) {
		this.expectedRideEndTime = expectedRideEndTime;
	}

	public Double getExpectedFare() {
		return expectedFare;
	}

	public void setExpectedFare(Double expectedFare) {
		this.expectedFare = expectedFare;
	}
	
	public Double getFromLatitude() {
		return fromLatitude;
	}

	public void setFromLatitude(Double fromLatitude) {
		this.fromLatitude = fromLatitude;
	}

	public Double getFromLongitude() {
		return fromLongitude;
	}

	public void setFromLongitude(Double fromLongitude) {
		this.fromLongitude = fromLongitude;
	}

	public Double getToLatitude() {
		return toLatitude;
	}

	public void setToLatitude(Double toLatitude) {
		this.toLatitude = toLatitude;
	}

	public Double getToLongitude() {
		return toLongitude;
	}

	public void setToLongitude(Double toLongitude) {
		this.toLongitude = toLongitude;
	}

	
}
