package com.trux.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="forgetpassword")
public class ForgetPassword {
	
	@Id
	@Column(name="email")
	private String email;
	
	@Column(name="passwordGenkey")
	private String passwordGenKey;
	
	@Column(name="createdDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	
	public ForgetPassword() {
	}

	public ForgetPassword(String email, String passwordGenKey, Date createdDate) {
		super();
		this.email = email;
		this.passwordGenKey = passwordGenKey;
		this.createdDate = createdDate;
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
	
	

	
}
