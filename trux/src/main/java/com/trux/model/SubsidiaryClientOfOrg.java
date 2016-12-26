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
@Table(name = "client_sub_master")
public class SubsidiaryClientOfOrg implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idClientSubMaster")
	private Integer idClientSubMaster;
	@Column(name = "idClientMaster")
	private Integer idClientMaster;
	@Column(name = "idClientLevelMaster")
	private Integer idClientLevelMaster;
	@Column(name = "subName")
	private String subName;
	@Column(name = "address")
	private String address;
	@Column(name = "idCountry")
	private Integer idCountry;
	@Column(name = "idZone")
	private Integer idZone;
	@Column(name = "idState")
	private Integer idState;
	@Column(name = "idCity")
	private Integer idCity;
	@Column(name = "idHub")
	private Integer idHub;
	@Column(name = "isActive")
	private Integer isActive;
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

	@Column(name = "client_lat")
	private Double clientLat;

	@Column(name = "client_lot")
	private Double clientLong;
	@Column(name = "driver_login_distance")
	private Double driverLoginDistance;

	@Transient
	private String clientName;
	@Transient
	private Integer clientSudIds;

	@Transient
	private String errorCode;
	@Transient
	private String errorMessage;

	public SubsidiaryClientOfOrg() {
		super();
	}

	public SubsidiaryClientOfOrg(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public SubsidiaryClientOfOrg(Integer idClientSubMaster,
			Integer idClientMaster, Integer idClientLevelMaster,
			String subName, String address, Integer idCountry, Integer idZone,
			Integer idState, Integer idCity, Integer idHub, Integer isActive,
			Date dateModified, Integer modifiedBy) {
		super();
		this.idClientSubMaster = idClientSubMaster;
		this.idClientMaster = idClientMaster;
		this.idClientLevelMaster = idClientLevelMaster;
		this.subName = subName;
		this.address = address;
		this.idCountry = idCountry;
		this.idZone = idZone;
		this.idState = idState;
		this.idCity = idCity;
		this.idHub = idHub;
		this.isActive = isActive;
		this.dateModified = dateModified;
		this.modifiedBy = modifiedBy;
	}

	public SubsidiaryClientOfOrg(Integer idClientMaster,
			Integer idClientLevelMaster, String subName, String address,
			Integer idCountry, Integer idZone, Integer idState, Integer idCity,
			Integer idHub, Integer isActive, Integer createdBy) {
		super();
		this.idClientMaster = idClientMaster;
		this.idClientLevelMaster = idClientLevelMaster;
		this.subName = subName;
		this.address = address;
		this.idCountry = idCountry;
		this.idZone = idZone;
		this.idState = idState;
		this.idCity = idCity;
		this.idHub = idHub;
		this.isActive = isActive;
		this.createdBy = createdBy;
	}

	public Integer getIdClientSubMaster() {
		return idClientSubMaster;
	}

	public void setIdClientSubMaster(Integer idClientSubMaster) {
		this.idClientSubMaster = idClientSubMaster;
	}

	public Integer getIdClientMaster() {
		return idClientMaster;
	}

	public void setIdClientMaster(Integer idClientMaster) {
		this.idClientMaster = idClientMaster;
	}

	public Integer getIdClientLevelMaster() {
		return idClientLevelMaster;
	}

	public void setIdClientLevelMaster(Integer idClientLevelMaster) {
		this.idClientLevelMaster = idClientLevelMaster;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getIdCountry() {
		return idCountry;
	}

	public void setIdCountry(Integer idCountry) {
		this.idCountry = idCountry;
	}

	public Integer getIdZone() {
		return idZone;
	}

	public void setIdZone(Integer idZone) {
		this.idZone = idZone;
	}

	public Integer getIdState() {
		return idState;
	}

	public void setIdState(Integer idState) {
		this.idState = idState;
	}

	public Integer getIdCity() {
		return idCity;
	}

	public void setIdCity(Integer idCity) {
		this.idCity = idCity;
	}

	public Integer getIdHub() {
		return idHub;
	}

	public void setIdHub(Integer idHub) {
		this.idHub = idHub;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
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

	public Double getClientLat() {
		return clientLat;
	}

	public void setClientLat(Double clientLat) {
		this.clientLat = clientLat;
	}

	public Double getClientLong() {
		return clientLong;
	}

	public void setClientLong(Double clientLong) {
		this.clientLong = clientLong;
	}

	public Double getDriverLoginDistance() {
		return driverLoginDistance;
	}

	public void setDriverLoginDistance(Double driverLoginDistance) {
		this.driverLoginDistance = driverLoginDistance;
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

	public Integer getClientSudIds() {
		return clientSudIds;
	}

	public void setClientSudIds(Integer clientSudIds) {
		this.clientSudIds = clientSudIds;
	}

}
