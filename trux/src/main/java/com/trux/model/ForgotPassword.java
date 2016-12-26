package com.trux.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
//@Table(name="forget_password_details")
@Table(name="forgetpassword")
public class ForgotPassword {
	
	
	@Id
	@Column(name="email")
	private String email;
	
	@Column(name="passwordGenkey")
	private String passwordGenKey;
	
	@Column(name="createdDate")
	private Date createdDate;
	@Transient
	private String errorCode;
	@Transient
	private String errorDescption;
	
	public ForgotPassword() {
	}

	public ForgotPassword(String email, String passwordGenKey, Date createdDate) {
		super();
		this.email = email;
		this.passwordGenKey = passwordGenKey;
		this.createdDate = createdDate;
	}

	public ForgotPassword(String errorCode, String errorDescption) {
		this.errorCode = errorCode;
		this.errorDescption = errorDescption;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswordGenKey() {
		return passwordGenKey;
	}

	public void setPasswordGenKey(String passwordGenKey) {
		this.passwordGenKey = passwordGenKey;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDescption() {
		return errorDescption;
	}

	public void setErrorDescption(String errorDescption) {
		this.errorDescption = errorDescption;
	}
	
	

	
}
