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
import javax.persistence.Transient;


@Entity
@Table(name="consumer_registration")
public class ConsumerRegistration {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;

	@Column(name="phonenumber")
	private String phoneNumber;
	
	@Column(name="firstname")
	private String firstname;
	
	@Column(name="lastname")
	private String lastname;
	
	@Column(name="email")
	private String email;
	
	
	@Column(name="password")
	private String password;
	
	@Column(name="usertype")
	private Integer usertype;
	
	
	
	
	@Transient
	private String errorCode;
	
	@Transient
	private String errorMessage;
	
	@Transient
	private Boolean userValidationFlag; 
	
	@Column(name = "createDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name = "updateDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	
	@Transient
	private String userTypes;
	@Transient
	private String createConsumerDate;
	@Transient
	private String updatedConsumerDate;
	@Transient
	private String userFistLastName;
	public ConsumerRegistration() {
	}

	public ConsumerRegistration(String email, String firstname, String lastname,
			String phoneNumber, String password, Integer usertype, Boolean userValidationFlag) {
		super();
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.userValidationFlag = userValidationFlag;
		this.usertype = usertype;
		this.createdDate = new Date();
		this.updatedDate = new Date();
		
		
	}
	

	public ConsumerRegistration(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	
	public Integer getUsertype() {
		return usertype;
	}

	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getUserTypes() {
		return userTypes;
	}

	public void setUserTypes(String userTypes) {
		this.userTypes = userTypes;
	}

	public String getCreateConsumerDate() {
		return createConsumerDate;
	}

	public void setCreateConsumerDate(String createConsumerDate) {
		this.createConsumerDate = createConsumerDate;
	}

	public String getUpdatedConsumerDate() {
		return updatedConsumerDate;
	}

	public void setUpdatedConsumerDate(String updatedConsumerDate) {
		this.updatedConsumerDate = updatedConsumerDate;
	}

	public String getUserFistLastName() {
		return userFistLastName;
	}

	public void setUserFistLastName(String userFistLastName) {
		this.userFistLastName = userFistLastName;
	}
	
	
}
