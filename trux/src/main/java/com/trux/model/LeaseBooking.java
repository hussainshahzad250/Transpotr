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
@Table(name = "booking_lease")
public class LeaseBooking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bookingLeaseId")
	private Integer bookingLeaseId;

	@Column(name = "driverId")
	private Integer driverId;

	@Column(name = "vehicleId")
	private Integer vehicleId;

	@Column(name = "companyId")
	private Integer companyId;
	
	@Column(name = "subClientId")
	private Integer subClientId;
	

	@Column(name = "journeyStartDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date journeyStartDate;

	@Column(name = "journeyEndDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date journeyEndDate;

	@Column(name = "fromJrLat")
	private Double fromJrLat;

	@Column(name = "fromJrLong")
	private Double fromJrLong;

	@Column(name = "fromJrLocation")
	private String fromJrLocation;

	@Column(name = "toJrLat")
	private Double toJrLat;

	@Column(name = "toJrLong")
	private Double toJrLong;

	@Column(name = "toJrLocation")
	private String toJrLocation;

	@Column(name = "totalDistance")
	private Double totalDistance;

	@Column(name = "totalDuration")
	private Long totalDuration;

	@Column(name = "numberOfDrop")
	private Integer bookingLsStatus;

	@Column(name = "createdDateTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDateTime;

	@Column(name = "updatedDateTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDateTime;

	@Column(name = "driverMobile")
	private String driverMobile;
	
	@Column(name = "startkm")
	private Integer startkm;
	@Column(name = "endkm")
	private Integer endkm;
	@Column(name = "noofboxes_unit")
	private Integer noofboxesUnit;
	@Column(name = "grossweight_kg")
	private Integer grossWeightKg;
	@Column(name = "driver_tolltax")
	private Double driverTollTax;
	@Column(name = "driver_parking_amount")
	private Double driverParkingAmount;
	@Column(name = "driver_bilty")
	private Double driverBilty;
	@Column(name = "driver_challan")
	private Double driverChallan;
	@Column(name = "driver_NGTtax")
	private Double driverNGTtax;
	@Column(name = "driver_other_reason")
	private String driverOtherReason;
	@Column(name = "driver_other_amount")
	private Double driverOtherAmount;
	@Column(name = "createdby")
	private Integer createdBy;
	@Column(name = "modifiedBy")
	private Integer modifiedBy;
	@Transient
	private String driverName;
	@Transient
	private String clientName;
	@Transient
	private String bookingId;
	
	@Transient
	private String errorCode;

	@Transient
	private String errorMessage;

	public LeaseBooking() {
		super();
	}

	public LeaseBooking(Integer driverId, Integer vehicleId, Integer companyId,
			Date journeyStartDate, Date journeyEndDate, Double fromJrLat,
			Double fromJrLong, String fromJrLocation, Double toJrLat,
			Double toJrLong, String toJrLocation, Double totalDistance,
			Long totalDuration, Integer bookingLsStatus) {
		super();
		this.driverId = driverId;
		this.vehicleId = vehicleId;
		this.companyId = companyId;
		this.journeyStartDate = journeyStartDate;
		this.journeyEndDate = journeyEndDate;
		this.fromJrLat = fromJrLat;
		this.fromJrLong = fromJrLong;
		this.fromJrLocation = fromJrLocation;
		this.toJrLat = toJrLat;
		this.toJrLong = toJrLong;
		this.toJrLocation = toJrLocation;
		this.totalDistance = totalDistance;
		this.totalDuration = totalDuration;
		this.bookingLsStatus = bookingLsStatus;
	}

	public LeaseBooking(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public Integer getBookingLeaseId() {
		return bookingLeaseId;
	}

	public void setBookingLeaseId(Integer bookingLeaseId) {
		this.bookingLeaseId = bookingLeaseId;
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

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public Date getJourneyStartDate() {
		return journeyStartDate;
	}

	public void setJourneyStartDate(Date journeyStartDate) {
		this.journeyStartDate = journeyStartDate;
	}

	public Date getJourneyEndDate() {
		return journeyEndDate;
	}

	public void setJourneyEndDate(Date journeyEndDate) {
		this.journeyEndDate = journeyEndDate;
	}

	public Double getFromJrLat() {
		return fromJrLat;
	}

	public void setFromJrLat(Double fromJrLat) {
		this.fromJrLat = fromJrLat;
	}

	public Double getFromJrLong() {
		return fromJrLong;
	}

	public void setFromJrLong(Double fromJrLong) {
		this.fromJrLong = fromJrLong;
	}

	public String getFromJrLocation() {
		return fromJrLocation;
	}

	public void setFromJrLocation(String fromJrLocation) {
		this.fromJrLocation = fromJrLocation;
	}

	public Double getToJrLat() {
		return toJrLat;
	}

	public void setToJrLat(Double toJrLat) {
		this.toJrLat = toJrLat;
	}

	public Double getToJrLong() {
		return toJrLong;
	}

	public void setToJrLong(Double toJrLong) {
		this.toJrLong = toJrLong;
	}

	public String getToJrLocation() {
		return toJrLocation;
	}

	public void setToJrLocation(String toJrLocation) {
		this.toJrLocation = toJrLocation;
	}

	public Double getTotalDistance() {
		return totalDistance;
	}

	public void setTotalDistance(Double totalDistance) {
		this.totalDistance = totalDistance;
	}

	public Long getTotalDuration() {
		return totalDuration;
	}

	public void setTotalDuration(Long totalDuration) {
		this.totalDuration = totalDuration;
	}

	public Integer getBookingLsStatus() {
		return bookingLsStatus;
	}

	public void setBookingLsStatus(Integer bookingLsStatus) {
		this.bookingLsStatus = bookingLsStatus;
	}

	public Date getCreatedDateTime() {
		return createdDateTime;
	}

	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}

	public Date getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(Date updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public String getDriverMobile() {
		return driverMobile;
	}

	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}

	public Integer getStartkm() {
		return startkm;
	}

	public void setStartkm(Integer startkm) {
		this.startkm = startkm;
	}

	public Integer getEndkm() {
		return endkm;
	}

	public void setEndkm(Integer endkm) {
		this.endkm = endkm;
	}

	public Integer getNoofboxesUnit() {
		return noofboxesUnit;
	}

	public void setNoofboxesUnit(Integer noofboxesUnit) {
		this.noofboxesUnit = noofboxesUnit;
	}

	public Integer getGrossWeightKg() {
		return grossWeightKg;
	}

	public void setGrossWeightKg(Integer grossWeightKg) {
		this.grossWeightKg = grossWeightKg;
	}

	public Double getDriverTollTax() {
		return driverTollTax;
	}

	public void setDriverTollTax(Double driverTollTax) {
		this.driverTollTax = driverTollTax;
	}

	public Double getDriverParkingAmount() {
		return driverParkingAmount;
	}

	public void setDriverParkingAmount(Double driverParkingAmount) {
		this.driverParkingAmount = driverParkingAmount;
	}

	public Double getDriverBilty() {
		return driverBilty;
	}

	public void setDriverBilty(Double driverBilty) {
		this.driverBilty = driverBilty;
	}

	public Double getDriverChallan() {
		return driverChallan;
	}

	public void setDriverChallan(Double driverChallan) {
		this.driverChallan = driverChallan;
	}

	public Double getDriverNGTtax() {
		return driverNGTtax;
	}

	public void setDriverNGTtax(Double driverNGTtax) {
		this.driverNGTtax = driverNGTtax;
	}

	public String getDriverOtherReason() {
		return driverOtherReason;
	}

	public void setDriverOtherReason(String driverOtherReason) {
		this.driverOtherReason = driverOtherReason;
	}

	public Double getDriverOtherAmount() {
		return driverOtherAmount;
	}

	public void setDriverOtherAmount(Double driverOtherAmount) {
		this.driverOtherAmount = driverOtherAmount;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
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

	 

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public Integer getSubClientId() {
		return subClientId;
	}

	public void setSubClientId(Integer subClientId) {
		this.subClientId = subClientId;
	}
 
}
