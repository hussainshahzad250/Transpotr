package com.trux.model;

import java.io.Serializable;
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
@Table(name = "master_freight_rate")
public class ExcelFile implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private Integer id;
	@Column
	private Integer source_state_id;
	@Column
	private Integer source_city_id;
	@Column
	private Integer destination_state_id;
	@Column
	private Integer destination_city_id;
	@Column
	private Double distance;
	@Column(name = "freight_rate")
	private Double rate;
	@Column
	private Integer vehicle_type_id;

	@Transient
	transient String state;

	@Transient
	transient String state1;

	@Transient
	transient String state2;

	@Transient
	transient String state3;

	@Transient
	transient String state4;
	
	public String getState4() {
		return state4;
	}

	public void setState4(String state4) {
		this.state4 = state4;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState1() {
		return state1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	public String getState2() {
		return state2;
	}

	public void setState2(String state2) {
		this.state2 = state2;
	}

	public String getState3() {
		return state3;
	}

	public void setState3(String state3) {
		this.state3 = state3;
	}

	@Column(name = "created_by")
	private Integer createdBy;

	@Column(name = "created_on")
	@Temporal(TemporalType.TIMESTAMP)
	// @JsonFormat(shape=JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm",
	// timezone="IST")
	private Date createdOn;

	@Column(name = "modified_by")
	private Integer modifiedBy;

	@Column(name = "modified_on")
	@Temporal(TemporalType.TIMESTAMP)
	// @JsonFormat(shape=JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm",
	// timezone="IST")
	private Date modifiedOn;

	@Column(name = "is_active")
	private Integer isActive;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSource_state_id() {
		return source_state_id;
	}

	public void setSource_state_id(Integer source_state_id) {
		this.source_state_id = source_state_id;
	}

	public Integer getSource_city_id() {
		return source_city_id;
	}

	public void setSource_city_id(Integer source_city_id) {
		this.source_city_id = source_city_id;
	}

	public Integer getDestination_state_id() {
		return destination_state_id;
	}

	public void setDestination_state_id(Integer destination_state_id) {
		this.destination_state_id = destination_state_id;
	}

	public Integer getDestination_city_id() {
		return destination_city_id;
	}

	public void setDestination_city_id(Integer destination_city_id) {
		this.destination_city_id = destination_city_id;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Integer getVehicle_type_id() {
		return vehicle_type_id;
	}

	public void setVehicle_type_id(Integer vehicle_type_id) {
		this.vehicle_type_id = vehicle_type_id;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

}
