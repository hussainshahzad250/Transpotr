package com.trux.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="desboardInfo")
public class DesboardInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="infoId")
	private Integer infoId;
	
	@Column(name="desboardInfo")
	private String desboardInfo;
	
	@Transient
	private String statusMessage;
	
	public DesboardInfo(Integer infoId, String desboardInfo) {
		super();
		this.infoId = infoId;
		this.desboardInfo = desboardInfo;
	}
	public DesboardInfo(Integer infoId) {
		super();
		this.infoId = infoId;
	}
	public DesboardInfo() {
		super();
	}
	public Integer getInfoId() {
		return infoId;
	}
	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}
	public String getDesboardInfo() {
		return desboardInfo;
	}
	public void setDesboardInfo(String desboardInfo) {
		this.desboardInfo = desboardInfo;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	

}
