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
@Table(name = "client_mandate")
public class ClientMandate {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "clientmandateid")
	private Integer clientMandateId;
	@Column(name = "clientid")
	private Integer clientId;
	@Column(name = "clientsubid")
	private Integer clientSubId;
	@Column(name = "mandate_start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date mandateStartDate;
	@Column(name = "mandate_end_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date mandateEndDate;
	@Column(name = "mandate_type")
	private String mandateType;
	
	@Column(name = "mandate_type_unit")
	private String mandateUnitType;
	
	@Column(name = "service_tax_full")
	private Double fullTax;
	@Column(name = "service_tax_abetted")
	private Double abettedTax;
	
	@Transient
	private Integer totalNoOfVehicle;
	@Column(name= "total_vehicles")
	private Integer totalVehicles;
	
	@Column(name = "createddate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Column(name = "createdby")
	private Integer createdBy;
	@Column(name = "modifieddate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	@Column(name = "modifiedby")
	private Integer modifiedBy;
	@Transient
	private String errorCode;
	@Transient
	private String errorMessage;
	
	@Transient
	private String clientName;
	@Transient
	private String subClientName;
	
	@Transient
	private String mandateStartDates;
	@Transient
	private String mandateEndDates;
	
	@Transient
	private Integer mandateDetailsId;
	@Transient
	private String vehicleType; 
	@Transient
	private String bodyType;
	
	
	public ClientMandate(Integer clientMandateId, Integer clientId,
			Integer clientSubId, Date mandateStartDate, Date mandateEndDate,
			String mandateType, Date createdDate, Integer createdBy,
			Date modifiedDate, Integer modifiedBy) {
		super();
		this.clientMandateId = clientMandateId;
		this.clientId = clientId;
		this.clientSubId = clientSubId;
		this.mandateStartDate = mandateStartDate;
		this.mandateEndDate = mandateEndDate;
		this.mandateType = mandateType;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.modifiedDate = modifiedDate;
		this.modifiedBy = modifiedBy;
	}  

	public ClientMandate(Integer clientMandateId) {
		super();
		this.clientMandateId = clientMandateId;
	}

	public ClientMandate() {
		super();
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

	public Date getMandateStartDate() {
		return mandateStartDate;
	}

	public void setMandateStartDate(Date mandateStartDate) {
		this.mandateStartDate = mandateStartDate;
	}

	public Date getMandateEndDate() {
		return mandateEndDate;
	}

	public void setMandateEndDate(Date mandateEndDate) {
		this.mandateEndDate = mandateEndDate;
	}

	public String getMandateType() {
		return mandateType;
	}

	public void setMandateType(String mandateType) {
		this.mandateType = mandateType;
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

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getSubClientName() {
		return subClientName;
	}

	public void setSubClientName(String subClientName) {
		this.subClientName = subClientName;
	}

	public String getMandateStartDates() {
		return mandateStartDates;
	}

	public void setMandateStartDates(String mandateStartDates) {
		this.mandateStartDates = mandateStartDates;
	}

	public String getMandateEndDates() {
		return mandateEndDates;
	}

	public void setMandateEndDates(String mandateEndDates) {
		this.mandateEndDates = mandateEndDates;
	}

	public String getMandateUnitType() {
		return mandateUnitType;
	}

	public void setMandateUnitType(String mandateUnitType) {
		this.mandateUnitType = mandateUnitType;
	}

	public Double getFullTax() {
		return fullTax;
	}

	public void setFullTax(Double fullTax) {
		this.fullTax = fullTax;
	}

	public Double getAbettedTax() {
		return abettedTax;
	}
	
	public void setAbettedTax(Double abettedTax) {
		this.abettedTax = abettedTax;
	}

	public Integer getTotalNoOfVehicle() {
		return totalNoOfVehicle;
	}

	public void setTotalNoOfVehicle(Integer totalNoOfVehicle) {
		this.totalNoOfVehicle = totalNoOfVehicle;
	}

	public Integer getMandateDetailsId() {
		return mandateDetailsId;
	}

	public void setMandateDetailsId(Integer mandateDetailsId) {
		this.mandateDetailsId = mandateDetailsId;
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

	public Integer getTotalVehicles() {
		return totalVehicles;
	}

	public void setTotalVehicles(Integer totalVehicles) {
		this.totalVehicles = totalVehicles;
	}

	 
	

	 
}
