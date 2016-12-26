package com.trux.model;


public class OrderFilterReportsDto {
	private String from;
	private String to;
	private String city;
	private String status;
	private String custMobile;
	private String vehicleType;
	private String driverMobile;
	private String hub;
	private String cluster;
	private String vehicleNo;
	private String bookingMode;
	private String dstatus;
	private int numberOfPage;
	private int pageNumer;

	public OrderFilterReportsDto() {
		super();
	}

	public OrderFilterReportsDto(String from, String to, String status) {
		super();
		this.from = from;
		this.to = to;
		this.status = status;
	}

	public OrderFilterReportsDto(String from, String to) {
		this.from = from;
		this.to = to;
	}

	public OrderFilterReportsDto(String from, String to, String city,
			String status, String custMobile, String vehicleType,
			String driverMobile, String hub, String cluster, String vehicleNo) {
		 
		this.from = from;
		this.to = to;
		this.city = city;
		this.status = status;
		this.custMobile = custMobile;
		this.vehicleType = vehicleType;
		this.driverMobile = driverMobile;
		this.hub = hub;
		this.cluster = cluster;
		this.vehicleNo = vehicleNo;
	}

	 

	 

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCustMobile() {
		return custMobile;
	}

	public void setCustMobile(String custMobile) {
		this.custMobile = custMobile;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getDriverMobile() {
		return driverMobile;
	}

	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}

	public String getHub() {
		return hub;
	}

	public void setHub(String hub) {
		this.hub = hub;
	}

	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public int getNumberOfPage() {
		return numberOfPage;
	}

	public void setNumberOfPage(int numberOfPage) {
		this.numberOfPage = numberOfPage;
	}

	public int getPageNumer() {
		return pageNumer;
	}

	public void setPageNumer(int pageNumer) {
		this.pageNumer = pageNumer;
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

}
