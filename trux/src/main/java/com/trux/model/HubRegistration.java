package com.trux.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
@Table(name="hub_registration")
public class HubRegistration implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO  )
	@Column(name="hub_id")
	private Integer hubId;
	@Column(name="country_id")
	private Integer countryId;
	@Column(name="state_id")
	private Integer stateId;
	@Column(name="city_id")
	private Integer cityId;
	@Column(name="hub_name")
	private String hubName; 
	
	@Transient
	private String errorCode;
	@Transient
	private String errorMessage;
	
	public HubRegistration() {
		super();
	}
	public HubRegistration(Integer countryId, Integer stateId, Integer cityId,
			String hubName) {
		super();
		this.countryId = countryId;
		this.stateId = stateId;
		this.cityId = cityId;
		this.hubName = hubName; 
	}
	public Integer getHubId() {
		return hubId;
	}
	public void setHubId(Integer hubId) {
		this.hubId = hubId;
	}
	public Integer getCountryId() {
		return countryId;
	}
	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}
	public Integer getStateId() {
		return stateId;
	}
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	public Integer getCityId() {
		return cityId;
	}
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	public String getHubName() {
		return hubName;
	}
	public void setHubName(String hubName) {
		this.hubName = hubName;
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
