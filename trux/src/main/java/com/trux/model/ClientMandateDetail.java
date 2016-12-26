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
@Table(name = "client_mandate_detail")
public class ClientMandateDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "mandatedetailid")
	private Integer mandateDetailId;
	@Column(name = "mandateid")
	private Integer mandateId;
	@Column(name = "vehicle_type")
	private String vehicleType;

	@Column(name = "body_type")
	private String bodyType;
	@Column(name = "no_of_vehicles")
	private int totalVehicle;
	@Column(name = "fix_days")
	private int fixDays;
	@Column(name = "fix_km")
	private int fixKm;
	@Column(name = "fix_hour")
	private int fixHour;
	@Column(name = "moq")
	private int moq;
	@Column(name = "night_holding_charge")
	private Double nightHoldingCharge;
	@Column(name = "extra_mk_charge")
	private Double extraKmCharge;
	@Column(name = "extra_hour_charge")
	private Double extraHourCharge;
	@Column(name = "billing_rate")
	private Double billingRate;
	@Column(name = "is_active")
	private int isActive;

	@Column(name = "createdDate")
	private Date createdDate;

	@Column(name = "createdBy")
	private Integer createdBy;

	@Column(name = "modifiedDate")
	private Date modifiedDate;

	@Column(name = "modifiedBy")
	private Integer modifiedBy;

	@Transient
	private String mandateType;

	public ClientMandateDetail(Integer mandateDetailId) {
		super();
		this.mandateDetailId = mandateDetailId;
	}

	public ClientMandateDetail() {
		super();
	}

	public Integer getMandateDetailId() {
		return mandateDetailId;
	}

	public void setMandateDetailId(Integer mandateDetailId) {
		this.mandateDetailId = mandateDetailId;
	}

	public Integer getMandateId() {
		return mandateId;
	}

	public void setMandateId(Integer mandateId) {
		this.mandateId = mandateId;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}

	public int getTotalVehicle() {
		return totalVehicle;
	}

	public void setTotalVehicle(int totalVehicle) {
		this.totalVehicle = totalVehicle;
	}

	public int getFixDays() {
		return fixDays;
	}

	public void setFixDays(int fixDays) {
		this.fixDays = fixDays;
	}

	public int getFixKm() {
		return fixKm;
	}

	public void setFixKm(int fixKm) {
		this.fixKm = fixKm;
	}

	public int getFixHour() {
		return fixHour;
	}

	public void setFixHour(int fixHour) {
		this.fixHour = fixHour;
	}

	public int getMoq() {
		return moq;
	}

	public void setMoq(int moq) {
		this.moq = moq;
	}

	public Double getNightHoldingCharge() {
		return nightHoldingCharge;
	}

	public void setNightHoldingCharge(Double nightHoldingCharge) {
		this.nightHoldingCharge = nightHoldingCharge;
	}

	public Double getBillingRate() {
		return billingRate;
	}

	public void setBillingRate(Double billingRate) {
		this.billingRate = billingRate;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
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

	public String getMandateType() {
		return mandateType;
	}

	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
	}

	public Double getExtraKmCharge() {
		return extraKmCharge;
	}

	public void setExtraKmCharge(Double extraKmCharge) {
		this.extraKmCharge = extraKmCharge;
	}

	public Double getExtraHourCharge() {
		return extraHourCharge;
	}

	public void setExtraHourCharge(Double extraHourCharge) {
		this.extraHourCharge = extraHourCharge;
	}

}
