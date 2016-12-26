package com.trux.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "client_level_master")
public class LevelOfOrganization {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "clientLevelMasterId")
	private Integer clientLevelMasterId;
	@Column(name = "clientMasterId")
	private Integer clientMasterId;
	@Column(name = "levelTitle")
	private String levelTitle;
	@Column(name = "precedence")
	private Integer precedence;	
	public LevelOfOrganization() {
		super();
	}
	public LevelOfOrganization( 
			Integer clientMasterId, String levelTitle, Integer precedence) {
		super();
		 
		this.clientMasterId = clientMasterId;
		this.levelTitle = levelTitle;
		this.precedence = precedence;
	}



	public Integer getClientLevelMasterId() {
		return clientLevelMasterId;
	}



	public void setClientLevelMasterId(Integer clientLevelMasterId) {
		this.clientLevelMasterId = clientLevelMasterId;
	}



	public Integer getClientMasterId() {
		return clientMasterId;
	}



	public void setClientMasterId(Integer clientMasterId) {
		this.clientMasterId = clientMasterId;
	}



	public String getLevelTitle() {
		return levelTitle;
	}



	public void setLevelTitle(String levelTitle) {
		this.levelTitle = levelTitle;
	}



	public Integer getPrecedence() {
		return precedence;
	}



	public void setPrecedence(Integer precedence) {
		this.precedence = precedence;
	}

	
	
}
