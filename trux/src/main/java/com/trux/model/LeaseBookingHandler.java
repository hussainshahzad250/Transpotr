package com.trux.model;

import java.io.Serializable;

public class LeaseBookingHandler implements Serializable{
  
	private static final long serialVersionUID = 1L;
	
	   
	private String orgName;
	private String subOrgClient;
	private String driver;
	private String attCheckMark;
	private String noOfBox;
	private String tollTax;
	private String amount;
	private String bookingDate;	
	private String tripAddress;
	public LeaseBookingHandler() {
		super();
	}
	
	public LeaseBookingHandler(String orgName, String subOrgClient,
			String driver, String attCheckMark, String noOfBox, String tollTax,
			String amount, String bookingDate) {
		super();
		this.orgName = orgName;
		this.subOrgClient = subOrgClient;
		this.driver = driver;
		this.attCheckMark = attCheckMark;
		this.noOfBox = noOfBox;
		this.tollTax = tollTax;
		this.amount = amount;
		this.bookingDate = bookingDate;
	}

	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getSubOrgClient() {
		return subOrgClient;
	}
	public void setSubOrgClient(String subOrgClient) {
		this.subOrgClient = subOrgClient;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getAttCheckMark() {
		return attCheckMark;
	}
	public void setAttCheckMark(String attCheckMark) {
		this.attCheckMark = attCheckMark;
	}
	public String getNoOfBox() {
		return noOfBox;
	}
	public void setNoOfBox(String noOfBox) {
		this.noOfBox = noOfBox;
	}
	public String getTollTax() {
		return tollTax;
	}
	public void setTollTax(String tollTax) {
		this.tollTax = tollTax;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(String bookingDate) {
		this.bookingDate = bookingDate;
	}
	 
}
