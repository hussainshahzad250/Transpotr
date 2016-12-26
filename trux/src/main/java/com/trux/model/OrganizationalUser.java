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
@Table(name="client_contact_master")
public class OrganizationalUser {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="idClientContactMaster")
private Integer idClientContactMaster;

@Column(name="idClientSubMaster")
private  Integer idClientSubMaster; 

@Column(name="name")
private String name ;
@Column(name="email")
private String email ;
@Column(name="email1")
private String email1 ;
@Column(name="mobile")
private String mobile ;
@Column(name="mobile1")
private String mobile1;

@Column(name="address")
private String address;
@Column(name="username")
private String userName ;
@Column(name="password")
private String password ;
@Column(name="isActive")
private Integer isActivetiny ;
@Column(name="createdBy")
private Integer createdBy ;
@Column(name="dateCreated")
@Temporal(TemporalType.TIMESTAMP)
private Date dateCreated ;
@Column(name="dateModified")
@Temporal(TemporalType.TIMESTAMP)
private Date dateModified ;
@Column(name="modifiedBy")
private Integer modifiedBy;


public OrganizationalUser() {
	super();
}


public OrganizationalUser(Integer idClientContactMaster,
		Integer idClientSubMaster, String name, String email, String email1,
		String mobile, String mobile1, String username, String password,
		Integer isActivetiny, Date dateCreated) {
	super();
	this.idClientContactMaster = idClientContactMaster;
	this.idClientSubMaster = idClientSubMaster;
	this.name = name;
	this.email = email;
	this.email1 = email1;
	this.mobile = mobile;
	this.mobile1 = mobile1;
	this.userName = username;
	this.password = password;
	this.isActivetiny = isActivetiny;
	this.dateCreated = dateCreated;
}


public OrganizationalUser(Integer idClientSubMaster, String name, String email,
		String mobile, String userName, String password, Integer isActivetiny,
		Integer createdBy) {
	super();
	this.idClientSubMaster = idClientSubMaster;
	this.name = name;
	this.email = email;
	this.mobile = mobile;
	this.userName = userName;
	this.password = password;
	this.isActivetiny = isActivetiny;
	this.createdBy = createdBy;
}


public OrganizationalUser(Integer idClientSubMaster, String name, String email,
		String email1, String mobile, String mobile1, String username,
		String password, Integer isActivetiny, Integer createdBy,
		Date dateCreated, Date dateModified, Integer modifiedBy) {
	super();
	this.idClientSubMaster = idClientSubMaster;
	this.name = name;
	this.email = email;
	this.email1 = email1;
	this.mobile = mobile;
	this.mobile1 = mobile1;
	this.userName = username;
	this.password = password;
	this.isActivetiny = isActivetiny;
	this.createdBy = createdBy;
	this.dateCreated = dateCreated;
	this.dateModified = dateModified;
	this.modifiedBy = modifiedBy;
}


public Integer getIdClientContactMaster() {
	return idClientContactMaster;
}


public void setIdClientContactMaster(Integer idClientContactMaster) {
	this.idClientContactMaster = idClientContactMaster;
}


public Integer getIdClientSubMaster() {
	return idClientSubMaster;
}


public void setIdClientSubMaster(Integer idClientSubMaster) {
	this.idClientSubMaster = idClientSubMaster;
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public String getEmail() {
	return email;
}


public void setEmail(String email) {
	this.email = email;
}


public String getEmail1() {
	return email1;
}


public void setEmail1(String email1) {
	this.email1 = email1;
}


public String getMobile() {
	return mobile;
}


public void setMobile(String mobile) {
	this.mobile = mobile;
}


public String getMobile1() {
	return mobile1;
}


public void setMobile1(String mobile1) {
	this.mobile1 = mobile1;
}

public String getAddress() {
	return address;
}

public void setAddress(String address) {
	this.address = address;
}


public String getUserName() {
	return userName;
}


public void setUserName(String userName) {
	this.userName = userName;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


public Integer getIsActivetiny() {
	return isActivetiny;
}


public void setIsActivetiny(Integer isActivetiny) {
	this.isActivetiny = isActivetiny;
}


public Integer getCreatedBy() {
	return createdBy;
}


public void setCreatedBy(Integer createdBy) {
	this.createdBy = createdBy;
}


public Date getDateCreated() {
	return dateCreated;
}


public void setDateCreated(Date dateCreated) {
	this.dateCreated = dateCreated;
}


public Date getDateModified() {
	return dateModified;
}


public void setDateModified(Date dateModified) {
	this.dateModified = dateModified;
}


public Integer getModifiedBy() {
	return modifiedBy;
}


public void setModifiedBy(Integer modifiedBy) {
	this.modifiedBy = modifiedBy;
}


}
