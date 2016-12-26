package com.trux.model;

import javax.persistence.Column;
 

public class OrganizationRegDto { 
	private Integer orgId;  
	private String orgName; 
	private String contactPerson; 
	private String email; 
	private String address;
	private String contactPersonPhone;  
	private String parentOrgId; 
	private String usersName; 
	private String passwords;
	public OrganizationRegDto() {
		super();
	}
	public OrganizationRegDto(String orgName,
			String contactPerson, String email, String address,
			String contactPersonPhone, String parentOrgId) {
		 
		this.orgName = orgName;
		this.contactPerson = contactPerson;
		this.email = email;
		this.address = address;
		this.contactPersonPhone = contactPersonPhone;
		this.parentOrgId = parentOrgId;
	}
	public Integer getOrgId() {
		return orgId;
	}
	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactPersonPhone() {
		return contactPersonPhone;
	}
	public void setContactPersonPhone(String contactPersonPhone) {
		this.contactPersonPhone = contactPersonPhone;
	}
	public String getParentOrgId() {
		return parentOrgId;
	}
	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}
	public String getUsersName() {
		return usersName;
	}
	public void setUsersName(String usersName) {
		this.usersName = usersName;
	}
	public String getPasswords() {
		return passwords;
	}
	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}
	
}
