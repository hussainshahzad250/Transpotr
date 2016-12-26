package com.trux.model;

 

public class OrganizationalClientDto { 
	private Integer orgId;  
	private String clientCode; 
	private String orgName; 
	private String address; 
	private Integer countryId;
	private Integer stateId;
	private Integer cityId;
	private String copyOfAgreement;  
	private int isActive; 
	private String dateCreated; 
	private Integer createdBy; 
	
	private String dateModified; 
	private Integer modifiedBy;
	
	public OrganizationalClientDto() {
		super();
	}

	 
	
	public OrganizationalClientDto(String clientCode, String orgName,
			String address, Integer countryId, Integer stateId, Integer cityId,
			String copyOfAgreement, int isActive ) {
		super();
		this.clientCode = clientCode;
		this.orgName = orgName;
		this.address = address;
		this.countryId = countryId;
		this.stateId = stateId;
		this.cityId = cityId;
		this.copyOfAgreement = copyOfAgreement;
		this.isActive = isActive; 
	}



	public Integer getOrgId() {
		return orgId;
	}



	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}



	public String getClientCode() {
		return clientCode;
	}



	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}



	public String getOrgName() {
		return orgName;
	}



	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
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



	public String getCopyOfAgreement() {
		return copyOfAgreement;
	}



	public void setCopyOfAgreement(String copyOfAgreement) {
		this.copyOfAgreement = copyOfAgreement;
	}



	public int getIsActive() {
		return isActive;
	}



	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}



	public String getDateCreated() {
		return dateCreated;
	}



	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}



	public Integer getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}



	public String getDateModified() {
		return dateModified;
	}



	public void setDateModified(String dateModified) {
		this.dateModified = dateModified;
	}



	public Integer getModifiedBy() {
		return modifiedBy;
	}



	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

 
	
}
