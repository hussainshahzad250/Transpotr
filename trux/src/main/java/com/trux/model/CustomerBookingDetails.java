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
@Table(name="booking")
public class CustomerBookingDetails  implements Cloneable{

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="bookingId")
	private Integer bookingId;
	
	@Column(name="customerName")
	private String customerName;
	
	@Column(name="custmerPhonenumber")
	private String custmerPhonenumber;
	
	@Column(name="customerEmail")
	private String customerEmail;
	
	@Column(name="fromLong")
	private Double fromLong;
	
	@Column(name="fromLat")
	private Double fromLat;
	
	@Column(name="fromLocation")
	private String fromLocation;
	
	@Column(name="toLat")
	private Double toLat;
	
	@Column(name="toLong")
	private Double toLong;
	
	@Column(name="toLocation")
	private String toLocation;
	
	@Column(name="createdDate")
	private Date createdDate;
	
	@Column(name="updatedDate")
	private Date updatedDate;
	
	@Column(name="bookingStatus")
	private String bookingStatus;
	
	@Column(name="driverName")
	private String driverName;
	
	@Column(name="driverPhonenumber")
	private String driverPhonenumber;
	
	@Column(name="vehicleNumber")
	private String vehicleNumber;
	
	@Column(name="vehicleType")
	private String vehicleType;
	
	@Column(name="driverLat")
	private Double driverLatitude;
	
	@Column(name="driverLong")
	private Double driverLongitude;
	
	@Column(name="driverLocation")
	private String driverLocation;
	
	@Column(name="totalFare")
	private Double totalFare;
	
	@Column(name="totalDistanceTravelled")
	private Double totalDistanceTravelled;
	
	@Column(name="tripDuration")
	private Double tripDuration;
	
	@Column(name="city")
	private String city;
	
	@Column(name="state")
	private String state;
	
	@Column(name="rideTime")
	private Date rideTime;
	
	@Column(name="expectedFare")
	private String expectedFare = "";
	@Column(name="expectedDistance")
	private String expectedDistance = "";

	
	@Column(name="consigneename")
	private String consigneeName = "";
	
	@Column(name="consigneephonenumber")
	private String consigneePhoneNumber = "";

	@Column(name="labourcount")
	private Integer labourCount = 0;
	
	@Column(name="documentuploadurl")
	private String documentuploadurl;
	
	
	@Column(name="dstatus")
	private String dstatus;
	@Column(name="payment_mode")

	private Integer payment_mode;
	
	@Column(name="booking_mode")
	private String bookingMode;
	
	@Column(name="booknow")

	private Integer booknow;


	@Column(name="expectedRideStartTime")
	private Long expectedRideStartTime ;
	
	@Column(name="expectedRideEndTime")
	private Long expectedRideEndTime ;
	
	
	@Column(name="booking_time")
	private Date booking_time;
	
	@Column(name="booking_acceptedtime")
	private Date booking_acceptedtime;
	
	@Column(name="loading_starttime")
	private Date loading_starttime;
	
	@Column(name="loading_completetime")
	private Date loading_completetime;
	
	@Column(name="ride_starttime")
	private Date ride_starttime;
	
	@Column(name="unloading_starttime")
	private Date unloading_starttime;
	
	
	@Column(name="unloading_completetime")
	private Date unloading_completetime;
	
	@Column(name="journeycompletetime")
	private Date journeycompletetime;
	
	
	@Column(name="cancellation_type")
	private Integer cancellationType;
	 
	@Column(name="created_by")
	private Integer createdBy;
	
	@Column(name="cancellation_reason")
	private String cancellationReason;
	 
	@Transient
	private Date driverCallDatetime;
	
	@Transient
	private String errorCode;
	
	@Transient
	private String errorMessage;
	
	@Transient
	private boolean isProcessed;
	
	@Transient
	private Integer estimatedTimeLeft;

	@Transient
	private Date pickUpTime;

	@Transient
	private double Revenue;
	@Transient
	private double totalAmountOfRevenue;
	@Transient
	private Double totalFares;
	@Transient
	private String dates;
	@Transient
	private String times;
	@Transient
	private String tripEndDates;
	@Transient
	private String tripEndTimes;
	@Transient
	private int serialNo;
	@Transient
	private String bookingDateTime;
	@Transient
	private String pickupDateTime;
	@Transient
	private String bookingDurationOfTime;
	@Transient
	private String statusChangeDurationOfTime;
	@Transient
	private String statusChangeDurationOfTimeDateFormate;
	@Transient
	private String updatedDateTime;
	public CustomerBookingDetails() {
	}

	

	public CustomerBookingDetails(String customerName, String custmerPhonenumber,
			String customerEmail,  Double fromLat, Double fromLong,
			String fromLocation, Double toLat, Double toLong,
			String toLocation, Date createdDate, Date updatedDate, String bookingStatus, String vehicleType) {
		super();
		this.customerName = customerName;
		this.custmerPhonenumber = custmerPhonenumber;
		this.customerEmail = customerEmail;
		this.fromLong = fromLong;
		this.fromLat = fromLat;
		this.fromLocation = fromLocation;
		this.toLat = toLat;
		this.toLong = toLong;
		this.toLocation = toLocation;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.bookingStatus = bookingStatus;
		this.vehicleType = vehicleType;
	}
	
	public CustomerBookingDetails(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	
	
	public Date getRideTime() {
		return rideTime;
	}



	public void setRideTime(Date rideTime) {
		this.rideTime = rideTime;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public Double getTotalFare() {
		return totalFare;
	}



	public void setTotalFare(Double totalFare) {
		this.totalFare = totalFare;
	}



	public Double getTotalDistanceTravelled() {
		return totalDistanceTravelled;
	}



	public void setTotalDistanceTravelled(Double totalDistanceTravelled) {
		this.totalDistanceTravelled = totalDistanceTravelled;
	}



	public String getDriverLocation() {
		return driverLocation;
	}



	



	public void setDriverLocation(String driverLocation) {
		this.driverLocation = driverLocation;
	}



	public Double getDriverLatitude() {
		return driverLatitude;
	}

	public void setDriverLatitude(Double driverLatitude) {
		this.driverLatitude = driverLatitude;
	}

	public Double getDriverLongitude() {
		return driverLongitude;
	}

	public void setDriverLongitude(Double driverLongitude) {
		this.driverLongitude = driverLongitude;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getVehicleType() {
		return vehicleType;
	}



	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
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



	public String getBookingStatus() {
		return bookingStatus;
	}



	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
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



	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustmerPhonenumber() {
		return custmerPhonenumber;
	}

	public void setCustmerPhonenumber(String custmerPhonenumber) {
		this.custmerPhonenumber = custmerPhonenumber;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}



	public Double getFromLong() {
		return fromLong;
	}



	public void setFromLong(Double fromLong) {
		this.fromLong = fromLong;
	}



	public Double getFromLat() {
		return fromLat;
	}



	public void setFromLat(Double fromLat) {
		this.fromLat = fromLat;
	}



	public String getFromLocation() {
		return fromLocation;
	}



	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}



	public Double getToLat() {
		return toLat;
	}



	public void setToLat(Double toLat) {
		this.toLat = toLat;
	}



	public Double getToLong() {
		return toLong;
	}



	public void setToLong(Double toLong) {
		this.toLong = toLong;
	}



	public String getToLocation() {
		return toLocation;
	}



	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
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
	
	
	public Long getExpectedRideStartTime() {
		return expectedRideStartTime;
	}



	public void setExpectedRideStartTime(Long expectedRideStartTime) {
		this.expectedRideStartTime = expectedRideStartTime;
	}



	public Long getExpectedRideEndTime() {
		return expectedRideEndTime;
	}



	public void setExpectedRideEndTime(Long expectedRideEndTime) {
		this.expectedRideEndTime = expectedRideEndTime;
	}



	public String getExpectedFare() {
		return expectedFare;
	}



	public void setExpectedFare(String expectedFare) {
		this.expectedFare = expectedFare;
	}



	public String getExpectedDistance() {
		return expectedDistance;
	}



	public void setExpectedDistance(String expectedDistance) {
		this.expectedDistance = expectedDistance;
	}



	public String getConsigneeName() {
		return consigneeName;
	}



	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}



	public String getConsigneePhoneNumber() {
		return consigneePhoneNumber;
	}



	public void setConsigneePhoneNumber(String consigneePhoneNumber) {
		this.consigneePhoneNumber = consigneePhoneNumber;
	}



	public Integer getLabourCount() {
		return labourCount;
	}



	public void setLabourCount(Integer labourCount) {
		this.labourCount = labourCount;
	}



	public String getDocumentuploadurl() {
		return documentuploadurl;
	}



	public void setDocumentuploadurl(String documentuploadurl) {
		this.documentuploadurl = documentuploadurl;
	}



	public Integer getPayment_mode() {
		return payment_mode;
	}



	public void setPayment_mode(Integer payment_mode) {
		this.payment_mode = payment_mode;
	}



	public Double getTripDuration() {
		return tripDuration;
	}



	public void setTripDuration(Double tripDuration) {
		this.tripDuration = tripDuration;
	}



	
	 @Override
	    public Object clone() throws CloneNotSupportedException {
	        return super.clone();
	    }



	public Integer getBooknow() {
		return booknow;
	}



	public void setBooknow(Integer booknow) {
		this.booknow = booknow;
	}



	public boolean isProcessed() {
		return isProcessed;
	}



	public void setProcessed(boolean isProcessed) {
		this.isProcessed = isProcessed;
	}



	public Date getBooking_time() {
		return booking_time;
	}



	public void setBooking_time(Date booking_time) {
		this.booking_time = booking_time;
	}



	public Date getBooking_acceptedtime() {
		return booking_acceptedtime;
	}



	public void setBooking_acceptedtime(Date booking_acceptedtime) {
		this.booking_acceptedtime = booking_acceptedtime;
	}



	public Date getLoading_starttime() {
		return loading_starttime;
	}



	public void setLoading_starttime(Date loading_starttime) {
		this.loading_starttime = loading_starttime;
	}



	public Date getLoading_completetime() {
		return loading_completetime;
	}



	public void setLoading_completetime(Date loading_completetime) {
		this.loading_completetime = loading_completetime;
	}



	public Date getRide_starttime() {
		return ride_starttime;
	}



	public void setRide_starttime(Date ride_starttime) {
		this.ride_starttime = ride_starttime;
	}



	public Date getUnloading_starttime() {
		return unloading_starttime;
	}



	public void setUnloading_starttime(Date unloading_starttime) {
		this.unloading_starttime = unloading_starttime;
	}



	public Date getUnloading_completetime() {
		return unloading_completetime;
	}



	public void setUnloading_completetime(Date unloading_completetime) {
		this.unloading_completetime = unloading_completetime;
	}



	public Date getJourneycompletetime() {
		return journeycompletetime;
	}



	public void setJourneycompletetime(Date journeycompletetime) {
		this.journeycompletetime = journeycompletetime;
	}



	public Integer getCancellationType() {
		return cancellationType;
	}



	public void setCancellationType(Integer cancellationType) {
		this.cancellationType = cancellationType;
	}



	public String getCancellationReason() {
		return cancellationReason;
	}



	public void setCancellationReason(String cancellationReason) {
		this.cancellationReason = cancellationReason;
	}



	public Integer getEstimatedTimeLeft() {
		return estimatedTimeLeft;
	}



	public void setEstimatedTimeLeft(Integer estimatedTimeLeft) {
		this.estimatedTimeLeft = estimatedTimeLeft;
	}



	public Date getPickUpTime() {
		return pickUpTime;
	}



	public void setPickUpTime(Date pickUpTime) {
		this.pickUpTime = pickUpTime;
	}



	public double getRevenue() {
		return Revenue;
	}



	public void setRevenue(double revenue) {
		Revenue = revenue;
	}



	public double getTotalAmountOfRevenue() {
		return totalAmountOfRevenue;
	}



	public void setTotalAmountOfRevenue(double totalAmountOfRevenue) {
		this.totalAmountOfRevenue = totalAmountOfRevenue;
	}



	public Double getTotalFares() {
		return totalFares;
	}



	public void setTotalFares(Double totalFares) {
		this.totalFares = totalFares;
	}



	public String getDates() {
		return dates;
	}



	public void setDates(String dates) {
		this.dates = dates;
	}



	public String getTimes() {
		return times;
	}



	public void setTimes(String times) {
		this.times = times;
	}



	public int getSerialNo() {
		return serialNo;
	}



	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}



	public String getTripEndDates() {
		return tripEndDates;
	}



	public void setTripEndDates(String tripEndDates) {
		this.tripEndDates = tripEndDates;
	}



	public String getTripEndTimes() {
		return tripEndTimes;
	}



	public void setTripEndTimes(String tripEndTimes) {
		this.tripEndTimes = tripEndTimes;
	}



	public String getBookingDateTime() {
		return bookingDateTime;
	}



	public void setBookingDateTime(String bookingDateTime) {
		this.bookingDateTime = bookingDateTime;
	}



	public String getPickupDateTime() {
		return pickupDateTime;
	}



	public void setPickupDateTime(String pickupDateTime) {
		this.pickupDateTime = pickupDateTime;
	}



	public String getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(String updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	} 
	public Date getDriverCallDatetime() {
		return driverCallDatetime;
	}



	public void setDriverCallDatetime(Date driverCallDatetime) {
		this.driverCallDatetime = driverCallDatetime;
	}
	public String getBookingDurationOfTime() {
		return bookingDurationOfTime;
	}
	public void setBookingDurationOfTime(String bookingDurationOfTime) {
		this.bookingDurationOfTime = bookingDurationOfTime;
	}
	public String getStatusChangeDurationOfTime() {
		return statusChangeDurationOfTime;
	}
	public void setStatusChangeDurationOfTime(String statusChangeDurationOfTime) {
		this.statusChangeDurationOfTime = statusChangeDurationOfTime;
	}
	public String getStatusChangeDurationOfTimeDateFormate() {
		return statusChangeDurationOfTimeDateFormate;
	}
	public void setStatusChangeDurationOfTimeDateFormate(
			String statusChangeDurationOfTimeDateFormate) {
		this.statusChangeDurationOfTimeDateFormate = statusChangeDurationOfTimeDateFormate;
	}



	public String getBookingMode() {
		return bookingMode;
	}



	public void setBookingMode(String bookingMode) {
		this.bookingMode = bookingMode;
	}



	public String getDstatus() {
		return dstatus;
	}



	public void setDstatus(String dstatus) {
		this.dstatus = dstatus;
	}



	public Integer getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	 

 
	
}
