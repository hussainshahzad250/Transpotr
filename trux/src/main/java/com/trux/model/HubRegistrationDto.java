package com.trux.model;
 

public class HubRegistrationDto {
	 
	private Integer hubId;
	
	private Integer countryId;
 
	private Integer stateId;
	 
	private Integer cityId;
	 
	private String hubName;
	  private String errorCode;
	  private String errorMessage;

	
	public HubRegistrationDto() {
		super();
	}

	public HubRegistrationDto(Integer countryId, Integer stateId, Integer cityId) {
		super();
		this.countryId = countryId;
		this.stateId = stateId;
		this.cityId = cityId;
	}

	public HubRegistrationDto( Integer countryId,
			Integer stateId, Integer cityId, String hubName) {
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
