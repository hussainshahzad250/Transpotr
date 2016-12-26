package com.trux.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client_vehicle_deployment")
public class ClientVehicleDeployment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name = "mandate_detail_id")
	private Integer mandateDetailId;
	@Column(name = "mandate_type")
	private String mandateType;
	@Column(name = "vehicle_type")
	private String vehicleType;
	@Column(name = "body_type")
	private String bodyType;
	@Column(name = "vehicle_no")
	private String vehicleNo;
	@Column(name = "reporting_time")
	private Date reportingTime;
	@Column(name = "createddate")
	private Date createdDate;
	@Column(name = "createdby")
	private Integer createdBy;
	@Column(name = "modifieddate")
	private Date modifiedDate;
	@Column(name = "modifiedby")
	private Integer modifiedBy;
	@Column(name = "is_active")
	private Integer isActive;
	@Column(name = "client_request_id")
	private Integer clientRequestId;
	@Column(name = "costToDriver")
	private Double costToDriver;
	@Column(name = "advancePayment")
	private Double advancePayment;
	@Column(name = "revenueToCompany")
	private Double revenueToCompany;
	@Column(name = "paymentReferenceNumber")
	private String paymentReferenceNumber;
	@Column(name = "balancePayment")
	private Double balancePayment;
	@Column(name = "balanceReferenceNumber")
	private String balanceReferenceNumber;
	@Column(name = "tollPayment")
	private Double tollPayment;
	@Column(name = "tollReferenceNumber")
	private String tollReferenceNumber;
	@Column(name = "labourPayment")
	private Double labourPayment;
	@Column(name = "labourReferenceNumber")
	private String labourReferenceNumber;
	@Column(name = "remarks")
	private String remarks;

	public ClientVehicleDeployment() {
		super();
	}

	public ClientVehicleDeployment(Integer id) {
		super();
		this.id = id;
	}

	public ClientVehicleDeployment(Integer mandateDetailId, String mandateType, String vehicleType, String bodyType,
			String vehicleNo, Date reportingTime, Date createdDate, Integer createdBy, Date modifiedDate,
			Integer modifiedBy, Integer isActive) {
		super();
		this.mandateDetailId = mandateDetailId;
		this.mandateType = mandateType;
		this.vehicleType = vehicleType;
		this.bodyType = bodyType;
		this.vehicleNo = vehicleNo;
		this.reportingTime = reportingTime;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
		this.isActive = isActive;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMandateDetailId() {
		return mandateDetailId;
	}

	public void setMandateDetailId(Integer mandateDetailId) {
		this.mandateDetailId = mandateDetailId;
	}

	public String getMandateType() {
		return mandateType;
	}

	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
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

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public Date getReportingTime() {
		return reportingTime;
	}

	public void setReportingTime(Date reportingTime) {
		this.reportingTime = reportingTime;
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

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Double getCostToDriver() {
		return costToDriver;
	}

	public void setCostToDriver(Double costToDriver) {
		this.costToDriver = costToDriver;
	}

	public Double getAdvancePayment() {
		return advancePayment;
	}

	public void setAdvancePayment(Double advancePayment) {
		this.advancePayment = advancePayment;
	}

	public Double getRevenueToCompany() {
		return revenueToCompany;
	}

	public void setRevenueToCompany(Double revenueToCompany) {
		this.revenueToCompany = revenueToCompany;
	}

	public String getPaymentReferenceNumber() {
		return paymentReferenceNumber;
	}

	public void setPaymentReferenceNumber(String paymentReferenceNumber) {
		this.paymentReferenceNumber = paymentReferenceNumber;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getClientRequestId() {
		return clientRequestId;
	}

	public void setClientRequestId(Integer clientRequestId) {
		this.clientRequestId = clientRequestId;
	}

	public Double getBalancePayment() {
		return balancePayment;
	}

	public void setBalancePayment(Double balancePayment) {
		this.balancePayment = balancePayment;
	}

	public String getBalanceReferenceNumber() {
		return balanceReferenceNumber;
	}

	public void setBalanceReferenceNumber(String balanceReferenceNumber) {
		this.balanceReferenceNumber = balanceReferenceNumber;
	}

	public Double getTollPayment() {
		return tollPayment;
	}

	public void setTollPayment(Double tollPayment) {
		this.tollPayment = tollPayment;
	}

	public String getTollReferenceNumber() {
		return tollReferenceNumber;
	}

	public void setTollReferenceNumber(String tollReferenceNumber) {
		this.tollReferenceNumber = tollReferenceNumber;
	}

	public Double getLabourPayment() {
		return labourPayment;
	}

	public void setLabourPayment(Double labourPayment) {
		this.labourPayment = labourPayment;
	}

	public String getLabourReferenceNumber() {
		return labourReferenceNumber;
	}

	public void setLabourReferenceNumber(String labourReferenceNumber) {
		this.labourReferenceNumber = labourReferenceNumber;
	}
	
	

}
