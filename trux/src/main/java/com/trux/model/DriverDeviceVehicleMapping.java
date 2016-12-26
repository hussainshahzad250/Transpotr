package com.trux.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="driver_device_vehicle_mapping")
public class DriverDeviceVehicleMapping {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="driver_id")
	private Integer driverId;
	
	@Column(name="vehicle_id")
	private Integer vehicleId;
	
	@Column(name="device_id")
	private Integer deviceId;
	
	@Column(name="driver_phone_no")
	private String driverPhoneNumber;
	
	@Column(name="device_UUID")
	private String deviceUUID;
	
	@Column(name="vehicle_number")
	private String vehicleNumber;
	
	@Column(name="vehicleType")
	private String vehicleType;
	
	@Column(name="driverName")
	private String driverName;
	
	@Column(name="loginstatus")
	private Integer loginStatus;
	
	@Column(name="driverstatus")
	private Integer driverStatus;
	
	@Column(name="last_login_time")
	private Date lastLoginTime;
	
	@Column(name="last_logout_time")
	private Date lastLogoutTime;
	
	@Column(name="driver_apk_version")
	private String driver_apk_version;
	
	@Column(name="documentuploadurl")
	private String documentuploadurl;
	
	@Column(name="dstatus")
	private String dstatus;
	
	@Column(name="subClientId")
	private Integer subClientId;
	
	@Transient
	private Boolean isValidDriver;
	
	@Transient
	private String errorCode;
	
	@Transient
	private String errorMessage;
	@Transient
	private String driverMessage;
	@Transient
	private String driverLoginDate;
	@Transient
	private String driverLoginTime;
	@Transient
	private String driverLogoutDate;
	@Transient
	private String driverLogoutTime;
	@Transient
	private String driverLoginDurationTime;
	;
	@Transient
	private String latestApkVersion = "1.1.2";
	@Transient
	private String apkUrl = "https://s3-us-west-2.amazonaws.com/trux/app-release.apk";
	
	public DriverDeviceVehicleMapping(Integer driverId, Integer vehicleId,
			Integer deviceId, String driverPhoneNumber, String deviceUUID,
			String vehicleNumber, String vehicleType) {
		super();
		this.driverId = driverId;
		this.vehicleId = vehicleId;
		this.deviceId = deviceId;
		this.driverPhoneNumber = driverPhoneNumber;
		this.deviceUUID = deviceUUID;
		this.vehicleNumber = vehicleNumber;
		this.vehicleType = vehicleType;
	}

	

	public DriverDeviceVehicleMapping(Integer driverId, Integer vehicleId,
			Integer deviceId, String driverPhoneNumber, String deviceUUID,
			String vehicleNumber, String vehicleType, String driverName,
			Integer loginStatus, Integer driverStatus) {
		super();
		this.driverId = driverId;
		this.vehicleId = vehicleId;
		this.deviceId = deviceId;
		this.driverPhoneNumber = driverPhoneNumber;
		this.deviceUUID = deviceUUID;
		this.vehicleNumber = vehicleNumber;
		this.vehicleType = vehicleType;
		this.driverName = driverName;
		this.loginStatus = loginStatus;
		this.driverStatus = driverStatus;
		
	}



	public DriverDeviceVehicleMapping() {
	}

	
	public DriverDeviceVehicleMapping(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}


	
	
	public String getDriver_apk_version() {
		return driver_apk_version;
	}



	public void setDriver_apk_version(String driver_apk_version) {
		this.driver_apk_version = driver_apk_version;
	}



	public String getVehicleType() {
		return vehicleType;
	}



	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}



	public Boolean getIsValidDriver() {
		return isValidDriver;
	}



	public void setIsValidDriver(Boolean isValidDriver) {
		this.isValidDriver = isValidDriver;
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
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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



	public Date getLastLoginTime() {
		return lastLoginTime;
	}



	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}



	public Date getLastLogoutTime() {
		return lastLogoutTime;
	}



	public void setLastLogoutTime(Date lastLogoutTime) {
		this.lastLogoutTime = lastLogoutTime;
	}



	public String getDriverMessage() {
		return driverMessage;
	}



	public void setDriverMessage(String driverMessage) {
		this.driverMessage = driverMessage;
	}




	public String getApkUrl() {
		return apkUrl;
	}



	public void setApkUrl(String apkUrl) {
		this.apkUrl = apkUrl;
	}



	public String getLatestApkVersion() {
		return latestApkVersion;
	}



	public void setLatestApkVersion(String latestApkVersion) {
		this.latestApkVersion = latestApkVersion;
	}



	public String getDocumentuploadurl() {
		return documentuploadurl;
	}



	public void setDocumentuploadurl(String documentuploadurl) {
		this.documentuploadurl = documentuploadurl;
	}



	public String getDriverLoginDate() {
		return driverLoginDate;
	}



	public void setDriverLoginDate(String driverLoginDate) {
		this.driverLoginDate = driverLoginDate;
	}



	public String getDriverLoginTime() {
		return driverLoginTime;
	}



	public void setDriverLoginTime(String driverLoginTime) {
		this.driverLoginTime = driverLoginTime;
	}



	public String getDriverLogoutDate() {
		return driverLogoutDate;
	}



	public void setDriverLogoutDate(String driverLogoutDate) {
		this.driverLogoutDate = driverLogoutDate;
	}



	public String getDriverLogoutTime() {
		return driverLogoutTime;
	}



	public void setDriverLogoutTime(String driverLogoutTime) {
		this.driverLogoutTime = driverLogoutTime;
	}



	public String getDriverLoginDurationTime() {
		return driverLoginDurationTime;
	}



	public void setDriverLoginDurationTime(String driverLoginDurationTime) {
		this.driverLoginDurationTime = driverLoginDurationTime;
	}



	public String getDstatus() {
		return dstatus;
	}



	public void setDstatus(String dstatus) {
		this.dstatus = dstatus;
	}



	public Integer getSubClientId() {
		return subClientId;
	}



	public void setSubClientId(Integer subClientId) {
		this.subClientId = subClientId;
	}


	
}
