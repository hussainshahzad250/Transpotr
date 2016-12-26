package com.trux.model;

import java.io.Serializable;
import java.util.Date; 

public class ClientMandateBinder implements Serializable{
 
	private static final long serialVersionUID = 1L;
	private Integer clientMandateId; 
	private Integer clientId; 
	private Integer clientSubId; 
	private Date mandateStartDate; 
	private Date mandateEndDate; 
	private String mandateType; 
	private Date createdDate; 
	private Integer createdBy; 
	private Date modifiedDate; 
	private Integer modifiedBy;
	 
	private String errorCode;
	 
	private String errorMessage;
	 
	private String clientName;
	 
	private String subClientName;

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
	
	
}
