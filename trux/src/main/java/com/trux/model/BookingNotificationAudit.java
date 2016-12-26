package com.trux.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="booking_notification_audit")
public class BookingNotificationAudit {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="bookingid")
	private Integer bookingid;
	
	
	@Column(name="deviceId")
	private String deviceId;
	
	@Column(name="driverPhonenumber")
	private String driverPhonenumber;
	
	@Column(name="driverName")
	private String driverName;
	
	@Column(name="consumerDistanceFromTruck")
	private Double consumerDistanceFromTruck;
	
	@Column(name="created_dttm")
	private Date createdDate;
	
	@Column(name="updated_dttm")
	private Date updatedDate;
	
	@Column(name="driver_status")
	private String driverStatus;
	
	@Column(name="driver_apk_version")
	private String driverApkVersion;
	
	@Column(name="vehicle_type")
	private String vehicleType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBookingid() {
		return bookingid;
	}

	public void setBookingid(Integer bookingid) {
		this.bookingid = bookingid;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDriverPhonenumber() {
		return driverPhonenumber;
	}

	public void setDriverPhonenumber(String driverPhonenumber) {
		this.driverPhonenumber = driverPhonenumber;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public Double getConsumerDistanceFromTruck() {
		return consumerDistanceFromTruck;
	}

	public void setConsumerDistanceFromTruck(Double consumerDistanceFromTruck) {
		this.consumerDistanceFromTruck = consumerDistanceFromTruck;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getDriverStatus() {
		return driverStatus;
	}

	public void setDriverStatus(String driverStatus) {
		this.driverStatus = driverStatus;
	}

	public String getDriverApkVersion() {
		return driverApkVersion;
	}

	public void setDriverApkVersion(String driverApkVersion) {
		this.driverApkVersion = driverApkVersion;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public BookingNotificationAudit() {
		super();
	}
	
	
	
	
}
