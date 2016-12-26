package com.trux.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "userDetails")
public class UserDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "email")
	private String email;

	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "phonenumber")
	private String phoneNumber;

	@Column(name = "password")
	private String password;

	@Column(name = "userRole")
	private String userRole;

	@Column(name = "gcmId")
	private String gcmId;
	
	@Column(name = "isActive")
	private int isActive;
	@Transient
	private String errorCode;
	@Transient
	private String authentication;

	@Transient
	private String errorMessage;

	@Transient
	private Boolean userValidationFlag;

	public UserDetail() {
	}

	public UserDetail(String email, String firstname, String lastname,
			String phoneNumber, String password, String userRole, String gcmId) {
		super();
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.userRole = userRole;
		this.gcmId = gcmId;
	}

	public UserDetail(String email, String firstname, String lastname,
			String phoneNumber, String password, Boolean userValidationFlag) {
		super();
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.userValidationFlag = userValidationFlag;
	}

	public UserDetail(String firstname, String password, String gcmId) {
		this.firstname = firstname;
		this.password = password;
		this.gcmId = gcmId;
	}

	public UserDetail(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public Boolean getUserValidationFlag() {
		return userValidationFlag;
	}

	public void setUserValidationFlag(Boolean userValidationFlag) {
		this.userValidationFlag = userValidationFlag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getUserRole() {
		return userRole;
	}
	
	

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getGcmId() {
		return gcmId;
	}

	public void setGcmId(String gcmId) {
		this.gcmId = gcmId;
	}

	public String getAuthentication() {
		return authentication;
	}

	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

}
