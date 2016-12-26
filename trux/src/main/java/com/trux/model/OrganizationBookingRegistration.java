package com.trux.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="client_booking")
public class OrganizationBookingRegistration {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="bookingId")
	private Integer bookingId;
	@Column(name="parent_org_id")
	private String parentOrgId;
	@Column(name="org_name")
	private String orgName;
	@Column(name="contact_person") 
	private String contactPerson;
	@Column(name="email")
	private String email;
	@Column(name="from_address") 
	private String fromAddress;
	@Column(name="to_address") 
	private String toAddress;
	@Column(name="contact_person_phone")
	private String contactPersonPhone;
	@Transient
	private String errorCode;
	
	@Transient
	private String errorMessage;
	
	public OrganizationBookingRegistration() {
	}
	 
	public OrganizationBookingRegistration(String parentOrgId, String orgName,	String contactPerson, String email, String fromAddress,String toAddress, String contactPersonPhone) {
		super();
		this.parentOrgId = parentOrgId;
		this.orgName = orgName;
		this.contactPerson = contactPerson;
		this.email = email;
		this.fromAddress = fromAddress;
		this.toAddress = toAddress;
		this.contactPersonPhone = contactPersonPhone;
	}

	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public String getParentOrgId() {
		return parentOrgId;
	}
	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
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
	 
	public String getContactPersonPhone() {
		return contactPersonPhone;
	}
	public void setContactPersonPhone(String contactPersonPhone) {
		this.contactPersonPhone = contactPersonPhone;
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
	 
	public OrganizationBookingRegistration(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}
	
}
