package com.trux.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="vehicle_type")
public class VehicleType {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
	public Integer id;
    @Column(name="vehicleName")
	private String vehicleName;
    
    
    @Transient
   	private String vehicleKey;
    @Transient
   	private String vehicleValue;
    
	@Transient
	private String errorCode;
	
	@Transient
	private String errorMessage;
	

    public VehicleType() {
		super();
	}
	public VehicleType(String vehicleName) {
		super();
		this.vehicleName = vehicleName;
	}
	public VehicleType(Integer id, String vehicleName, String errorCode,
			String errorMessage) {
		super();
		this.id = id;
		this.vehicleName = vehicleName;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVehicleName() {
		return vehicleName;
	}
	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}
	public String getVehicleKey() {
		return vehicleKey;
	}
	public void setVehicleKey(String vehicleKey) {
		this.vehicleKey = vehicleKey;
	}
	public String getVehicleValue() {
		return vehicleValue;
	}
	public void setVehicleValue(String vehicleValue) {
		this.vehicleValue = vehicleValue;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public VehicleType(String vehicleKey, String vehicleValue) {
		super();
		this.vehicleKey = vehicleKey;
		this.vehicleValue = vehicleValue;
	}
    
}
