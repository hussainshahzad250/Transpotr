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

@Entity
@Table(name = "user_loginhistory")
public class UserLoginHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "histId")
	private Integer histId;
	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "is_login")
	private Integer islogin;
	@Column(name = "location_lat")
	private String locationLat;
	@Column(name = "location_long")
	private String locationLong;
	@Column(name = "mac")
	private String deviceMac;
	@Column(name = "app_version")
	private String appversion;
	@Column(name = "createddate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name = "isMobile")
	private Integer isMobile;
	
	@Column(name = "loginLocation")
	private String loginLocation;
	
	
	public UserLoginHistory() {
		super();
	}
	public UserLoginHistory(Integer histId, Integer userId, Integer islogin,
			Integer locationLat, Integer loginLong, Date createdDate,
			Integer isMobile) {
		super();
		this.histId = histId;
		this.userId = userId;
		this.islogin = islogin;
		 
		this.createdDate = createdDate;
		this.isMobile = isMobile;
	}
	public UserLoginHistory(Integer histId) {
		super();
		this.histId = histId;
	}
	public Integer getHistId() {
		return histId;
	}
	public void setHistId(Integer histId) {
		this.histId = histId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getIslogin() {
		return islogin;
	}
	public void setIslogin(Integer islogin) {
		this.islogin = islogin;
	}
	 
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getIsMobile() {
		return isMobile;
	}
	public void setIsMobile(Integer isMobile) {
		this.isMobile = isMobile;
	}
	public String getLocationLat() {
		return locationLat;
	}
	public void setLocationLat(String locationLat) {
		this.locationLat = locationLat;
	}
	public String getLocationLong() {
		return locationLong;
	}
	public void setLocationLong(String locationLong) {
		this.locationLong = locationLong;
	}
	public String getLoginLocation() {
		return loginLocation;
	}
	public void setLoginLocation(String loginLocation) {
		this.loginLocation = loginLocation;
	}
	public String getDeviceMac() {
		return deviceMac;
	}
	public void setDeviceMac(String deviceMac) {
		this.deviceMac = deviceMac;
	}
	public String getAppversion() {
		return appversion;
	}
	public void setAppversion(String appversion) {
		this.appversion = appversion;
	}
	
	
}
