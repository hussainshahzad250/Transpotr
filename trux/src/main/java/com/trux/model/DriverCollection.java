package com.trux.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="driver_collection")
public class DriverCollection {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
	private Integer id;
    @Column(name="driverId")
	private Integer driverId;
    @Column(name="vehicleId")
	private Integer vehicleId;
    @Column(name="driver_status")
	private String driverStatus;
    @Column(name="vehicle_status")
	private String vehicleStatus;
    @Column(name="amount_collection")
	private Float amountCollection; 
    @Column(name="deviceEMEI")
	private String deviceEmei;
    @Column(name="driverActivation")
    private int driverActivation;
    @Column(name="vehicleActivation")
    private int vehicleActivation;    
    
    @Transient
	private String errorCode;	
	@Transient
	private String errorMessage;
    
	public DriverCollection(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public DriverCollection() {
		super();
	}
	
	public DriverCollection(Integer vehicleId, String vehicleStatus,
			String deviceEmei) {
		super();
		this.vehicleId = vehicleId;
		this.vehicleStatus = vehicleStatus;
		this.deviceEmei = deviceEmei;
	}

	public DriverCollection(Integer driverId, String driverStatus) {
		super();
		this.driverId = driverId;
		this.driverStatus = driverStatus;
	}

	public DriverCollection(Integer driverId, Integer vehicleId,
			String driverStatus, String vehicleStatus,
			Float amountCollection, String deviceEmei) {
		super();
		this.driverId = driverId;
		this.vehicleId = vehicleId;
		this.driverStatus = driverStatus;
		this.vehicleStatus = vehicleStatus;
		this.amountCollection = amountCollection;
		this.deviceEmei = deviceEmei;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getDriverId() {
		return driverId;
	}
	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}
	public Integer getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(Integer vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getDriverStatus() {
		return driverStatus;
	}
	public void setDriverStatus(String driverStatus) {
		this.driverStatus = driverStatus;
	}
	public String getVehicleStatus() {
		return vehicleStatus;
	}
	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}
	public Float getAmountCollection() {
		return amountCollection;
	}
	public void setAmountCollection(Float amountCollection) {
		this.amountCollection = amountCollection;
	}
	public String getDeviceEmei() {
		return deviceEmei;
	}
	public void setDeviceEmei(String deviceEmei) {
		this.deviceEmei = deviceEmei;
	}
	
 

	public int getDriverActivation() {
		return driverActivation;
	}

	public void setDriverActivation(int driverActivation) {
		this.driverActivation = driverActivation;
	}

	public int getVehicleActivation() {
		return vehicleActivation;
	}

	public void setVehicleActivation(int vehicleActivation) {
		this.vehicleActivation = vehicleActivation;
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
