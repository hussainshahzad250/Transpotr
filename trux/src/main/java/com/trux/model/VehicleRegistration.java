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
import javax.persistence.Transient;

@Entity
@Table(name="vehicle_registration")
public class VehicleRegistration {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
		
	@Column(name="vehicle_number")
	private String vehicleNumber;
	
	@Column(name="vehicle_type")
	private String vehicleType;
	
	@Column(name="model")
	private String vehicleModel;
	@Column(name="vehicleBody")
	private String vehicleBody;
	
	

	@Column(name="owner_name")
	private String ownerName;
	
	@Column(name="owner_phone_number")
	private String ownerPhoneNumber;
	
	@Column(name="account_holder_name")
	private String accountHolderName;
	@Column(name="bank_name")
	private String bankName;
	
	@Column(name="pan_number")
	private String panNumber;
	@Column(name="account_Number")
	private String accountNumber;
    @Column(name="ifsc_code")
	private String ifscCode;
	@Column(name="owner_addredd")
	private String ownerAddress;
	
	@Column(name="vehicleStatus")
	private String vehicleStatus;
	
	@Column(name="km_reading")
	private String kmReading;
	
	@Column(name="imageOfRC")
	private String imageOfRC ;
	
	@Column(name="imageOfPermitVehicle")
	private String imageOfPermitVehicle;
	
	@Column(name="fuelType")
	private String fuelType;
	
	@Column(name="agentId")
	private String agentId;
	
	@Column(name="driver_id")
	private Integer driverId;
	
	@Column(name="driver_mobile")
	private String driverMobiles;
	
	@Column(name="country_id")
	private Integer countryId;
	@Column(name="state_id")
	private Integer	stateId;;
	@Column(name="city_id")
	private Integer cityId;
	@Column(name="hub_id")
	private Integer hubId;
	@Column(name="cluster_id")
	private Integer	clusterId;
	@Column(name="stand_id")
	private Integer standId;
	@Column(name="client_id")
	private Integer clientId;
	@Column(name="subclient_id")
	private Integer subclientId;
	@Column(name="is_barcode_issued")
	private Integer isBarcodeIssued; 
	@Column(name="is_active")
	private int isActive;
	
	@Column(name="image_of_permit_vehicle")
	private String image_of_permit_vehicle;
	@Column(name="image_of_rc")
	private String image_of_rc;
	@Column(name="image_of_insurance")
	private String image_of_insurance;
	@Column(name="image_of_pollution")
	private String image_of_pollution;
	@Column(name="insuranceExpiryDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date insuranceExpiryDate;
	
	
	@Transient
	private String assosiatedBy;
	
	@Transient
	private String cluster;
	@Transient
	private String subOrgClient;
	@Transient
	private String hubName;
	@Transient
	private String city;
	@Transient
	private String state;
	
	@Transient
	private String country;
	@Transient
	private String standDetails;
 
	@Column(name="created_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	
	@Column(name="created_by")
	private Integer createdBy;
	 
	@Column(name="modified_by")
	private Integer modifiedBy;
	
	@Column(name="modified_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedDate;
	
	
	@Transient
	private String errorCode;
	
	@Transient
	private String errorMessage;
	@Transient
	private String driverMobile;
	
	@Transient
	private String month;
	@Transient
	private String year;
	
	public VehicleRegistration() {
	}

	public VehicleRegistration(String vehicleNumber, String vehicleType,
			String vehicleModel, String ownerName, String ownerPhoneNumber,
			String ownerAddress, String vehicleStatus, String kmReading,
			String imageOfRC, String imageOfPermitVehicle, String fuelType,
			String agentId, Integer driverId) {
		super();
		this.vehicleNumber = vehicleNumber;
		this.vehicleType = vehicleType;
		this.vehicleModel = vehicleModel;
		this.ownerName = ownerName;
		this.ownerPhoneNumber = ownerPhoneNumber;
		this.ownerAddress = ownerAddress;
		this.vehicleStatus = vehicleStatus;
		this.kmReading = kmReading;
		this.imageOfRC = imageOfRC;
		this.imageOfPermitVehicle = imageOfPermitVehicle;
		this.fuelType = fuelType;
		this.agentId = agentId;
		this.driverId = driverId;
	}

	public String getKmReading() {
		return kmReading;
	}

	public void setKmReading(String kmReading) {
		this.kmReading = kmReading;
	}

	public String getImageOfPermitVehicle() {
		return imageOfPermitVehicle;
	}

	public void setImageOfPermitVehicle(String imageOfPermitVehicle) {
		this.imageOfPermitVehicle = imageOfPermitVehicle;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getImageOfRC() {
		return imageOfRC;
	}

	public void setImageOfRC(String imageOfRC) {
		this.imageOfRC = imageOfRC;
	}

	public VehicleRegistration(String vehicleNumber,
			String vehicleType, String vehicleModel, String ownerName, 
			String kmReading, String imageOfRC, String imageOfPermitVehicle,
			String fuelType) {
		super(); 
		this.vehicleNumber = vehicleNumber;
		this.vehicleType = vehicleType;
		this.vehicleModel = vehicleModel;
		this.ownerName = ownerName; 
		this.kmReading = kmReading;
		this.imageOfRC = imageOfRC;
		this.imageOfPermitVehicle = imageOfPermitVehicle;
		this.fuelType = fuelType;
	}

	public VehicleRegistration(String vehicleNumber, String vehicle_type,
			String vehicleModel, String ownerName, String ownerPhoneNumber,
			String ownerAddress, String vehicleStatus) {
		super();
		this.vehicleNumber = vehicleNumber;
		this.vehicleType = vehicle_type;
		this.vehicleModel = vehicleModel;
		this.ownerName = ownerName;
		this.ownerPhoneNumber = ownerPhoneNumber;
		this.ownerAddress = ownerAddress;
		this.vehicleStatus = vehicleStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicle_type) {
		this.vehicleType = vehicle_type;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerPhoneNumber() {
		return ownerPhoneNumber;
	}

	public void setOwnerPhoneNumber(String ownerPhoneNumber) {
		this.ownerPhoneNumber = ownerPhoneNumber;
	}

	public String getOwnerAddress() {
		return ownerAddress;
	}

	public void setOwnerAddress(String ownerAddress) {
		this.ownerAddress = ownerAddress;
	}

	public String getVehicleStatus() {
		return vehicleStatus;
	}

	public void setVehicleStatus(String vehicleStatus) {
		this.vehicleStatus = vehicleStatus;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
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

	public VehicleRegistration(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getDriverMobile() {
		return driverMobile;
	}

	public void setDriverMobile(String driverMobile) {
		this.driverMobile = driverMobile;
	}

	public String getVehicleBody() {
		return vehicleBody;
	}

	public void setVehicleBody(String vehicleBody) {
		this.vehicleBody = vehicleBody;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getHubId() {
		return hubId;
	}

	public void setHubId(Integer hubId) {
		this.hubId = hubId;
	}

	public Integer getClusterId() {
		return clusterId;
	}

	public void setClusterId(Integer clusterId) {
		this.clusterId = clusterId;
	}

	public Integer getStandId() {
		return standId;
	}

	public void setStandId(Integer standId) {
		this.standId = standId;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getSubclientId() {
		return subclientId;
	}

	public void setSubclientId(Integer subclientId) {
		this.subclientId = subclientId;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	 

	public Integer getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getAssosiatedBy() {
		return assosiatedBy;
	}

	public void setAssosiatedBy(String assosiatedBy) {
		this.assosiatedBy = assosiatedBy;
	}

	public String getCluster() {
		return cluster;
	}

	public void setCluster(String cluster) {
		this.cluster = cluster;
	}

	public String getSubOrgClient() {
		return subOrgClient;
	}

	public void setSubOrgClient(String subOrgClient) {
		this.subOrgClient = subOrgClient;
	}

	public String getHubName() {
		return hubName;
	}

	public void setHubName(String hubName) {
		this.hubName = hubName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getStandDetails() {
		return standDetails;
	}

	public void setStandDetails(String standDetails) {
		this.standDetails = standDetails;
	}

	public String getDriverMobiles() {
		return driverMobiles;
	}

	public void setDriverMobiles(String driverMobiles) {
		this.driverMobiles = driverMobiles;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getPanNumber() {
		return panNumber;
	}

	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public Integer getIsBarcodeIssued() {
		return isBarcodeIssued;
	}

	public void setIsBarcodeIssued(Integer isBarcodeIssued) {
		this.isBarcodeIssued = isBarcodeIssued;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getImage_of_permit_vehicle() {
		return image_of_permit_vehicle;
	}

	public void setImage_of_permit_vehicle(String image_of_permit_vehicle) {
		this.image_of_permit_vehicle = image_of_permit_vehicle;
	}

	public String getImage_of_rc() {
		return image_of_rc;
	}

	public void setImage_of_rc(String image_of_rc) {
		this.image_of_rc = image_of_rc;
	}

	public String getImage_of_insurance() {
		return image_of_insurance;
	}

	public void setImage_of_insurance(String image_of_insurance) {
		this.image_of_insurance = image_of_insurance;
	}

	public String getImage_of_pollution() {
		return image_of_pollution;
	}

	public void setImage_of_pollution(String image_of_pollution) {
		this.image_of_pollution = image_of_pollution;
	}

	public Date getInsuranceExpiryDate() {
		return insuranceExpiryDate;
	}

	public void setInsuranceExpiryDate(Date insuranceExpiryDate) {
		this.insuranceExpiryDate = insuranceExpiryDate;
	}
	
	
	
}
