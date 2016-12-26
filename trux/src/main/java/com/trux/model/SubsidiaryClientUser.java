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

@Entity
@Table(name = "client_truxuser_mapping")
public class SubsidiaryClientUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_mappingId")
	private Integer userMappingId;
	@Column(name = "clientid")
	private Integer clientId;
	@Column(name = "clientsubid")
	private Integer clientSubId;
	@Column(name = "userid")
	private Integer userId;
	@Column(name = "createddatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDateTime;
	@Column(name = "createdby")
	private Integer createdBy;
	@Column(name = "modifieddatetime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDateTime;
	@Column(name = "modifiedby")
	private Integer modifiedBy;
	@Column(name = "is_active")
	private Integer isActive;
	
	
	public SubsidiaryClientUser() {
		super();
	}
	public SubsidiaryClientUser(Integer userMappingId, Integer clientSubId,
			Integer userId, Date createdDateTime, Integer createdBy,
			Date modifiedDateTime, Integer modifiedBy, Integer isActive) {
		super();
		this.userMappingId = userMappingId;
		this.clientSubId = clientSubId;
		this.userId = userId;
		this.createdDateTime = createdDateTime;
		this.createdBy = createdBy;
		this.modifiedDateTime = modifiedDateTime;
		this.modifiedBy = modifiedBy;
		this.isActive = isActive;
	}
	public SubsidiaryClientUser(Integer userMappingId) {
		super();
		this.userMappingId = userMappingId;
	}
	public Integer getUserMappingId() {
		return userMappingId;
	}
	public void setUserMappingId(Integer userMappingId) {
		this.userMappingId = userMappingId;
	}
	public Integer getClientSubId() {
		return clientSubId;
	}
	public void setClientSubId(Integer clientSubId) {
		this.clientSubId = clientSubId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getCreatedDateTime() {
		return createdDateTime;
	}
	public void setCreatedDateTime(Date createdDateTime) {
		this.createdDateTime = createdDateTime;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getModifiedDateTime() {
		return modifiedDateTime;
	}
	public void setModifiedDateTime(Date modifiedDateTime) {
		this.modifiedDateTime = modifiedDateTime;
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
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	
	
}
