package com.trux.model;


public class ApproxFareEstimate {

    private	double approxDistance =  -1 ;
    private double approxfare = -1;
    private String journeyTime = null ;
    private String sourceLocation;
    private String destinationLocation;
    private String vehicleType;
    private String code;
    private String description;
    private String distanceUnit = "km";
	 
	
	
	
	
	public ApproxFareEstimate(double approxDistance, double approxfare,
			String journeyTime, String sourceLocation,
			String destinationLocation, String vehicleType) {
		super();
		this.approxDistance = approxDistance;
		this.approxfare = approxfare;
		this.journeyTime = journeyTime;
		this.sourceLocation = sourceLocation;
		this.destinationLocation = destinationLocation;
		this.vehicleType = vehicleType;
	}
	
	public ApproxFareEstimate(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public double getApproxDistance() {
		return approxDistance;
	}
	public void setApproxDistance(double approxDistance) {
		this.approxDistance = approxDistance;
	}
	public double getApproxfare() {
		return approxfare;
	}
	public void setApproxfare(double approxfare) {
		this.approxfare = approxfare;
	}
	public String getJourneyTime() {
		return journeyTime;
	}
	public void setJourneyTime(String journeyTime) {
		this.journeyTime = journeyTime;
	}
	public String getSourceLocation() {
		return sourceLocation;
	}
	public void setSourceLocation(String sourceLocation) {
		this.sourceLocation = sourceLocation;
	}
	public String getDestinationLocation() {
		return destinationLocation;
	}
	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDistanceUnit() {
		return distanceUnit;
	}

	public void setDistanceUnit(String distanceUnit) {
		this.distanceUnit = distanceUnit;
	}
	
	
}
