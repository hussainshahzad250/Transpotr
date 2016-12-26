package com.trux.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ClientMandateDetailDto implements Serializable{
	
	private Integer clientMandateId;
	private Integer mandateDetailId;
	private Integer clientId;
	private Integer clientSubId;
	private String mandateType;

	private Double boxInvoiceAmount;

	private String fixVehicleType;
	private String fixMandateDayDate;
	private String fixVehicleNumber;
	private String fixMkPerMonth;
	private Double fixInvoiceAmount;
	private String fixHrsPerDay;

	private String weightVehicleType;
	private Double weightInvoiceAmount;
	private String weightVehicleNumber;
	private String weightTripStartKmPerday;

	private String tripStartKmPerday;
	private Double tripInvoiceAmount;
	private String tripEndKmPerday;
	
	
	public ClientMandateDetailDto() {
		super();
	}


	public ClientMandateDetailDto(Integer clientMandateId, Integer clientId,
			Integer clientSubId, String mandateType, Double boxInvoiceAmount,
			String fixVehicleType, String fixMandateDayDate,
			String fixVehicleNumber, String fixMkPerMonth,
			Double fixInvoiceAmount, String fixHrsPerDay,
			String weightVehicleType, Double weightInvoiceAmount,
			String weightVehicleNumber, String weightTripStartKmPerday,
			String tripStartKmPerday, Double tripInvoiceAmount,
			String tripEndKmPerday) {
		super();
		this.clientMandateId = clientMandateId;
		this.clientId = clientId;
		this.clientSubId = clientSubId;
		this.mandateType = mandateType;
		this.boxInvoiceAmount = boxInvoiceAmount;
		this.fixVehicleType = fixVehicleType;
		this.fixMandateDayDate = fixMandateDayDate;
		this.fixVehicleNumber = fixVehicleNumber;
		this.fixMkPerMonth = fixMkPerMonth;
		this.fixInvoiceAmount = fixInvoiceAmount;
		this.fixHrsPerDay = fixHrsPerDay;
		this.weightVehicleType = weightVehicleType;
		this.weightInvoiceAmount = weightInvoiceAmount;
		this.weightVehicleNumber = weightVehicleNumber;
		this.weightTripStartKmPerday = weightTripStartKmPerday;
		this.tripStartKmPerday = tripStartKmPerday;
		this.tripInvoiceAmount = tripInvoiceAmount;
		this.tripEndKmPerday = tripEndKmPerday;
	}


	public Integer getClientMandateId() {
		return clientMandateId;
	}


	public void setClientMandateId(Integer clientMandateId) {
		this.clientMandateId = clientMandateId;
	}


	public Integer getClientId() {
		return clientId;
	}


	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}


	public Integer getClientSubId() {
		return clientSubId;
	}


	public void setClientSubId(Integer clientSubId) {
		this.clientSubId = clientSubId;
	}


	public String getMandateType() {
		return mandateType;
	}


	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
	}


	public Double getBoxInvoiceAmount() {
		return boxInvoiceAmount;
	}


	public void setBoxInvoiceAmount(Double boxInvoiceAmount) {
		this.boxInvoiceAmount = boxInvoiceAmount;
	}


	public String getFixVehicleType() {
		return fixVehicleType;
	}


	public void setFixVehicleType(String fixVehicleType) {
		this.fixVehicleType = fixVehicleType;
	}


	public String getFixMandateDayDate() {
		return fixMandateDayDate;
	}


	public void setFixMandateDayDate(String fixMandateDayDate) {
		this.fixMandateDayDate = fixMandateDayDate;
	}


	public String getFixVehicleNumber() {
		return fixVehicleNumber;
	}


	public void setFixVehicleNumber(String fixVehicleNumber) {
		this.fixVehicleNumber = fixVehicleNumber;
	}


	public String getFixMkPerMonth() {
		return fixMkPerMonth;
	}


	public void setFixMkPerMonth(String fixMkPerMonth) {
		this.fixMkPerMonth = fixMkPerMonth;
	}


	public Double getFixInvoiceAmount() {
		return fixInvoiceAmount;
	}


	public void setFixInvoiceAmount(Double fixInvoiceAmount) {
		this.fixInvoiceAmount = fixInvoiceAmount;
	}


	public String getFixHrsPerDay() {
		return fixHrsPerDay;
	}


	public void setFixHrsPerDay(String fixHrsPerDay) {
		this.fixHrsPerDay = fixHrsPerDay;
	}


	public String getWeightVehicleType() {
		return weightVehicleType;
	}


	public void setWeightVehicleType(String weightVehicleType) {
		this.weightVehicleType = weightVehicleType;
	}


	public Double getWeightInvoiceAmount() {
		return weightInvoiceAmount;
	}


	public void setWeightInvoiceAmount(Double weightInvoiceAmount) {
		this.weightInvoiceAmount = weightInvoiceAmount;
	}


	public String getWeightVehicleNumber() {
		return weightVehicleNumber;
	}


	public void setWeightVehicleNumber(String weightVehicleNumber) {
		this.weightVehicleNumber = weightVehicleNumber;
	}


	public String getWeightTripStartKmPerday() {
		return weightTripStartKmPerday;
	}


	public void setWeightTripStartKmPerday(String weightTripStartKmPerday) {
		this.weightTripStartKmPerday = weightTripStartKmPerday;
	}


	public String getTripStartKmPerday() {
		return tripStartKmPerday;
	}


	public void setTripStartKmPerday(String tripStartKmPerday) {
		this.tripStartKmPerday = tripStartKmPerday;
	}


	public Double getTripInvoiceAmount() {
		return tripInvoiceAmount;
	}


	public void setTripInvoiceAmount(Double tripInvoiceAmount) {
		this.tripInvoiceAmount = tripInvoiceAmount;
	}


	public String getTripEndKmPerday() {
		return tripEndKmPerday;
	}


	public void setTripEndKmPerday(String tripEndKmPerday) {
		this.tripEndKmPerday = tripEndKmPerday;
	}


	public Integer getMandateDetailId() {
		return mandateDetailId;
	}


	public void setMandateDetailId(Integer mandateDetailId) {
		this.mandateDetailId = mandateDetailId;
	} 
	
	
}
