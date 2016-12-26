package com.trux.model;


/**
 * @author Dewesh Kumar
 */
public class Ride {
    public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public void setCustmerPhonenumber(String custmerPhonenumber) {
		this.custmerPhonenumber = custmerPhonenumber;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public void setFromLong(Double fromLong) {
		this.fromLong = fromLong;
	}

	public void setFromLat(Double fromLat) {
		this.fromLat = fromLat;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public void setToLat(Double toLat) {
		this.toLat = toLat;
	}

	public void setToLong(Double toLong) {
		this.toLong = toLong;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}

	public void setUpdatedDate(long updatedDate) {
		this.updatedDate = updatedDate;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public void setDriverPhonenumber(String driverPhonenumber) {
		this.driverPhonenumber = driverPhonenumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public void setDriverLatitude(Double driverLatitude) {
		this.driverLatitude = driverLatitude;
	}

	public void setDriverLongitude(Double driverLongitude) {
		this.driverLongitude = driverLongitude;
	}

	public void setDriverLocation(String driverLocation) {
		this.driverLocation = driverLocation;
	}

	public void setTotalFare(Double totalFare) {
		this.totalFare = totalFare;
	}

	public void setTotalDistanceTravelled(Double totalDistanceTravelled) {
		this.totalDistanceTravelled = totalDistanceTravelled;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setRideTime(long rideTime) {
		this.rideTime = rideTime;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	private int bookingId;
    private String customerName;
    private String custmerPhonenumber;
    private String customerEmail;
    private Double fromLong;
    private Double fromLat;
    private String fromLocation;
    private Double toLat;
    private Double toLong;
    private String toLocation;
    private long createdDate;
    private long updatedDate;
    private String bookingStatus;
    private String driverName;
    private String driverPhonenumber;
    private String vehicleNumber;
    private String vehicleType;
    private Double driverLatitude;
    private Double driverLongitude;
    private String driverLocation;
    private Double totalFare;
    private Double totalDistanceTravelled;
    private String city;
    private String state;
    private long rideTime;
    private String errorCode;
    private String errorMessage;

    public int getBookingId() {
        return bookingId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustmerPhonenumber() {
        return custmerPhonenumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public Double getFromLong() {
        return fromLong;
    }

    public Double getFromLat() {
        return fromLat;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public Double getToLat() {
        return toLat;
    }

    public Double getToLong() {
        return toLong;
    }

    public String getToLocation() {
        return toLocation;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public long getUpdatedDate() {
        return updatedDate;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public String getDriverName() {
        return driverName;
    }

    public String getDriverPhonenumber() {
        return driverPhonenumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public Double getDriverLatitude() {
        return driverLatitude;
    }

    public Double getDriverLongitude() {
        return driverLongitude;
    }

    public String getDriverLocation() {
        return driverLocation;
    }

    public Double getTotalFare() {
        return totalFare;
    }

    public Double getTotalDistanceTravelled() {
        return totalDistanceTravelled;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public long getRideTime() {
        return rideTime;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
