package com.trux.model;

import java.util.Date;

public class CRFOrder {
	
	private Integer id;
	
	private Integer crfDeploymentId;
	
	private String crfSource;
	
	private String crfDestination;
	
	private String crfSourceVehicleType;
	
	private Date reportingTime;
	
	private String bodyType; 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCrfDeploymentId() {
		return crfDeploymentId;
	}

	public void setCrfDeploymentId(Integer crfDeploymentId) {
		this.crfDeploymentId = crfDeploymentId;
	}

	public String getCrfSource() {
		return crfSource;
	}

	public void setCrfSource(String crfSource) {
		this.crfSource = crfSource;
	}

	public String getCrfDestination() {
		return crfDestination;
	}

	public void setCrfDestination(String crfDestination) {
		this.crfDestination = crfDestination;
	}

	public String getCrfSourceVehicleType() {
		return crfSourceVehicleType;
	}

	public void setCrfSourceVehicleType(String crfSourceVehicleType) {
		this.crfSourceVehicleType = crfSourceVehicleType;
	}

	public Date getReportingTime() {
		return reportingTime;
	}

	public void setReportingTime(Date reportingTime) {
		this.reportingTime = reportingTime;
	}

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}
	
	

}
