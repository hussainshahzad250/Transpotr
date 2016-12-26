package com.trux.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="client_mandate_detail_trip")
public class ClientMandateDetailsTrip {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "mandatetripid")
	private Integer mandateTripId;
	@Column(name = "mandatedetailid")
	private Integer mandateDetailId;
	@Column(name = "point_a")
	private String pointA;
	@Column(name = "point_b")
	private String pointB;
	@Column(name = "billing_rate")
	private Double billingRate;
	@Column(name = "night_holding_charges")
	private double nightHoldingCharges;
	@Column(name = "createddate")
	private Date createdDate;
	@Column(name = "createdby")
	private Integer createdBy;
	
	@Column(name = "modified_date")
	private Date modifiedDate;
	@Column(name = "modified_by")
	private Integer modifiedBy;
	public ClientMandateDetailsTrip() {
		super();
	}
	 
	public Integer getMandateTripId() {
		return mandateTripId;
	}

	public void setMandateTripId(Integer mandateTripId) {
		this.mandateTripId = mandateTripId;
	}

	public Integer getMandateDetailId() {
		return mandateDetailId;
	}
	public void setMandateDetailId(Integer mandateDetailId) {
		this.mandateDetailId = mandateDetailId;
	}
	public String getPointA() {
		return pointA;
	}
	public void setPointA(String pointA) {
		this.pointA = pointA;
	}
	public String getPointB() {
		return pointB;
	}
	public void setPointB(String pointB) {
		this.pointB = pointB;
	}
	public Double getBillingRate() {
		return billingRate;
	}
	public void setBillingRate(Double billingRate) {
		this.billingRate = billingRate;
	}
	public double getNightHoldingCharges() {
		return nightHoldingCharges;
	}
	public void setNightHoldingCharges(double nightHoldingCharges) {
		this.nightHoldingCharges = nightHoldingCharges;
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
	
	

}
