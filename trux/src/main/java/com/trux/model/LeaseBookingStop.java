package com.trux.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="booking_lease_stop")
public class LeaseBookingStop {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="bkLsStpId")
private Integer	bkLsStpId ;
@Column(name="bookingLeaseId")
private Integer	bookingLeaseId;
@Column(name="drop_location_reach_time")
@Temporal(TemporalType.TIMESTAMP)
private Date dropLocationReachTime; 
@Column(name="after_drop_start_time")
@Temporal(TemporalType.TIMESTAMP)
private Date afterDropStartTime ;
@Column(name="stopLat")
private double	stopLat ;
@Column(name="stopLong")
private double	stopLong ;
@Column(name="stopDocUrl")
private String	stopDocUrl;

@Column(name="drop_location")
private String	dropLocation;

@Column(name="createdTime")
@Temporal(TemporalType.TIMESTAMP)
private Date createdTime ;

@Column(name="updatedTime")
@Temporal(TemporalType.TIMESTAMP)
private Date updatedTime ;

@Column(name="total_distance")
private double	totalDistance;

@Transient
private String errorCode;

@Transient
private String errorMessage;


public LeaseBookingStop() {
	super();
}

public LeaseBookingStop(Integer bookingLeaseId, Date dropLocationReachTime,
		Date afterDropStartTime, double stopLat, double stopLong,
		String stopDocUrl, String dropLocation) {
	super();
	this.bookingLeaseId = bookingLeaseId;
	this.dropLocationReachTime = dropLocationReachTime;
	this.afterDropStartTime = afterDropStartTime;
	this.stopLat = stopLat;
	this.stopLong = stopLong;
	this.stopDocUrl = stopDocUrl;
	this.dropLocation = dropLocation;
}

public Integer getBkLsStpId() {
	return bkLsStpId;
}

public void setBkLsStpId(Integer bkLsStpId) {
	this.bkLsStpId = bkLsStpId;
}

public Integer getBookingLeaseId() {
	return bookingLeaseId;
}

public void setBookingLeaseId(Integer bookingLeaseId) {
	this.bookingLeaseId = bookingLeaseId;
}

public Date getDropLocationReachTime() {
	return dropLocationReachTime;
}

public void setDropLocationReachTime(Date dropLocationReachTime) {
	this.dropLocationReachTime = dropLocationReachTime;
}

public Date getAfterDropStartTime() {
	return afterDropStartTime;
}

public void setAfterDropStartTime(Date afterDropStartTime) {
	this.afterDropStartTime = afterDropStartTime;
}

public double getStopLat() {
	return stopLat;
}

public void setStopLat(double stopLat) {
	this.stopLat = stopLat;
}

public double getStopLong() {
	return stopLong;
}

public void setStopLong(double stopLong) {
	this.stopLong = stopLong;
}

public String getStopDocUrl() {
	return stopDocUrl;
}

public void setStopDocUrl(String stopDocUrl) {
	this.stopDocUrl = stopDocUrl;
}

public String getDropLocation() {
	return dropLocation;
}

public void setDropLocation(String dropLocation) {
	this.dropLocation = dropLocation;
}

public Date getCreatedTime() {
	return createdTime;
}

public void setCreatedTime(Date createdTime) {
	this.createdTime = createdTime;
}

public Date getUpdatedTime() {
	return updatedTime;
}

public void setUpdatedTime(Date updatedTime) {
	this.updatedTime = updatedTime;
}

public LeaseBookingStop(String errorCode, String errorMessage) {
	super();
	this.errorCode = errorCode;
	this.errorMessage = errorMessage;
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

public double getTotalDistance() {
	return totalDistance;
}

public void setTotalDistance(double totalDistance) {
	this.totalDistance = totalDistance;
}

 
}
