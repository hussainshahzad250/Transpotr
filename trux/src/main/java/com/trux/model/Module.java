package com.trux.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="module")
public class Module {
	
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="id")
private Integer id;
@Column(name="moduleName")
private String moduleName;
@Column(name="accessRightsRole")
private String accessRightsRole;

@Transient
private String errorCode;

@Transient
private String errorMessage;


public Module() {
	super();
}

public Module(String moduleName, String accessRightsRole) {
	super();
	this.moduleName = moduleName;
	this.accessRightsRole = accessRightsRole;
}

 

public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getModuleName() {
	return moduleName;
}
public void setModuleName(String moduleName) {
	this.moduleName = moduleName;
}
public String getAccessRightsRole() {
	return accessRightsRole;
}
public void setAccessRightsRole(String accessRightsRole) {
	this.accessRightsRole = accessRightsRole;
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
