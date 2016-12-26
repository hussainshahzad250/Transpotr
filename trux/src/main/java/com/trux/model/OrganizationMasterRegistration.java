package com.trux.model;

import java.io.Serializable;
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

@SuppressWarnings("serial")
@Entity
@Table(name = "client_master")
public class OrganizationMasterRegistration implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idClientMaster")
	private Integer idClientMaster;

	@Column(name = "clientCode")
	private String clientCode;

	@Column(name = "name")
	private String name;

	@Column(name = "address")
	private String address;

	@Column(name = "cityId")
	private Integer cityId;

	@Column(name = "agrementUrl")
	private String agrementUrl;

	@Column(name = "isActive")
	private int isActive;

	@Column(name = "dateCreated")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@Column(name = "createdBy")
	private Integer createdBy;

	@Column(name = "dateModified")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateModified;

	@Column(name = "modifiedBy")
	private Integer modifiedBy;

	@Transient
	private String errorCode;
	@Transient
	private String errorMessage;

	public OrganizationMasterRegistration() {
		super();
	}

	public OrganizationMasterRegistration(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public OrganizationMasterRegistration(String clientCode, String name,
			String address, Integer cityId, String agrementUrl, int isActive) {
		super();
		this.clientCode = clientCode;
		this.name = name;
		this.address = address;
		this.cityId = cityId;
		this.agrementUrl = agrementUrl;
		this.isActive = isActive;
	}

	public Integer getIdClientMaster() {
		return idClientMaster;
	}

	public void setIdClientMaster(Integer idClientMaster) {
		this.idClientMaster = idClientMaster;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public String getAgrementUrl() {
		return agrementUrl;
	}

	public void setAgrementUrl(String agrementUrl) {
		this.agrementUrl = agrementUrl;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getDateModified() {
		return dateModified;
	}

	public void setDateModified(Date dateModified) {
		this.dateModified = dateModified;
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

}
