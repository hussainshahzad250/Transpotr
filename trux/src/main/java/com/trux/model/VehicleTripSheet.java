package com.trux.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "vehicle_tripsheet")
public class VehicleTripSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name = "clientid")
	private Integer clientId;
	@Column(name = "subclientid")
	private Integer subClientId ;
	@Column(name = "vehicleno")
	private String vehicleNo;
	@Column(name = "driver_name")
	private String driverName;
	@Column(name = "trip_date")
	@Temporal(TemporalType.TIMESTAMP)
//	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone="IST")
	private Date tripDate;
	@Column(name = "client_order_no")
	private Integer clientOrderNo;
	@Column(name = "opening_km")
	private Integer openingKm;
	@Column(name = "opening_datetime")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone="IST")
	private Date openingDateTime;
	@Column(name = "closing_km")
	private Integer closingKm;
	@Column(name = "closing_datetime")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm", timezone="IST")
	private Date closingDateTime;
	@Column(name = "scanned_tripsheet_s3_url")
	private String scannedTripSheetS3Url;
	@Column(name = "sign_security_name")
	private String signSecurityName;
	@Column(name = "created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name = "created_by")
	private Integer createdBy;
	@Column(name = "modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	@Column(name = "modified_by")
	private Integer modifiedBy;
	@Column(name = "toll")
	private Integer toll;
	@Column(name = "parking")
	private Integer parking;
	@Column(name = "challan")
	private Integer challan;
	@Column(name = "ngt")
	private Integer ngt;
	@Column(name = "other")
	private Integer other;
	@Column(name = "flag")
	private Integer flag;
	@Transient
	private String driverMobile;
	@Transient
	private Integer driverId;
	@Transient
	private String tripDates;
	@Transient
	private String clientName;
	@Transient
	private String subClientName;
	@Transient
	private String errorCode;
	@Transient
	private String errorMessage;
	@Transient
	private List<VehicleTripsheetDrops> vehicleTripDropList;
	public VehicleTripSheet() {
		super();
	}
	public VehicleTripSheet(Integer id) {
		super();
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	 
	public Integer getSubClientId() {
		return subClientId;
	}
	public void setSubClientId(Integer subClientId) {
		this.subClientId = subClientId;
	}
	public String getVehicleNo() {
		return vehicleNo;
	}
	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}
	public String getDriverName() {
		return driverName;
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public Date getTripDate() {
		return tripDate;
	}
	public void setTripDate(Date tripDate) {
		this.tripDate = tripDate;
	}
	public Integer getClientOrderNo() {
		return clientOrderNo;
	}
	public void setClientOrderNo(Integer clientOrderNo) {
		this.clientOrderNo = clientOrderNo;
	}
	public Integer getOpeningKm() {
		return openingKm;
	}
	public void setOpeningKm(Integer openingKm) {
		this.openingKm = openingKm;
	}
	public Date getOpeningDateTime() {
		return openingDateTime;
	}
	public void setOpeningDateTime(Date openingDateTime) {
		this.openingDateTime = openingDateTime;
	}
	public Integer getClosingKm() {
		return closingKm;
	}
	public void setClosingKm(Integer closingKm) {
		this.closingKm = closingKm;
	}
	public Date getClosingDateTime() {
		return closingDateTime;
	}
	public void setClosingDateTime(Date closingDateTime) {
		this.closingDateTime = closingDateTime;
	}
	public String getScannedTripSheetS3Url() {
		return scannedTripSheetS3Url;
	}
	public void setScannedTripSheetS3Url(String scannedTripSheetS3Url) {
		this.scannedTripSheetS3Url = scannedTripSheetS3Url;
	}
	public String getSignSecurityName() {
		return signSecurityName;
	}
	public void setSignSecurityName(String signSecurityName) {
		this.signSecurityName = signSecurityName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public String getDriverMobile() {
		return driverMobile;
	}
	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}
	public Integer getDriverId() {
		return driverId;
	}
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
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
	public List<VehicleTripsheetDrops> getVehicleTripDropList() {
		return vehicleTripDropList;
	}
	public void setVehicleTripDropList(
			List<VehicleTripsheetDrops> vehicleTripDropList) {
		this.vehicleTripDropList = vehicleTripDropList;
	}
	public String getTripDates() {
		return tripDates;
	}
	public void setTripDates(String tripDates) {
		this.tripDates = tripDates;
	}
	public Integer getToll() {
		return toll;
	}
	public void setToll(Integer toll) {
		this.toll = toll;
	}
	public Integer getParking() {
		return parking;
	}
	public void setParking(Integer parking) {
		this.parking = parking;
	}
	public Integer getChallan() {
		return challan;
	}
	public void setChallan(Integer challan) {
		this.challan = challan;
	}
	public Integer getNgt() {
		return ngt;
	}
	public void setNgt(Integer ngt) {
		this.ngt = ngt;
	}
	public Integer getOther() {
		return other;
	}
	public void setOther(Integer other) {
		this.other = other;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getSubClientName() {
		return subClientName;
	}
	public void setSubClientName(String subClientName) {
		this.subClientName = subClientName;
	}
 	
}
