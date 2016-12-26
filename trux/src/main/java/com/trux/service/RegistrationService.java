package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.RegistrationDAO;
import com.trux.model.DeviceRegistration;
import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.DriverRegistration;
import com.trux.model.ForgetPassword;
import com.trux.model.UserDetail;
import com.trux.model.UserLogin;
import com.trux.model.VehicleRegistration;

@Service
public class RegistrationService {

	@Autowired
	private RegistrationDAO registrationDAO;
	
	public void saveOrUpdate(Object obj){
		registrationDAO.saveOrUpdate(obj);
	}
	public void saveOrUpdateDDVM(DriverDeviceVehicleMapping object) {
		registrationDAO.saveOrUpdateDDVM(object);
	}
	public String changePasswords(UserDetail dto){return registrationDAO.changePasswords(dto);}
	
	public UserDetail saveUserDetail(UserDetail object){
		return registrationDAO.saveUserDetail(object);
	}
	
	public List<VehicleRegistration> getRegisteredVehicleList(){
		return registrationDAO.getRegisteredVehicleList();
	}
	
	public List<DriverRegistration> getRegisteredDriverList(){
		return registrationDAO.getRegisteredDriverList();
	}
	
	public UserDetail getUserDetails(String email){
		return registrationDAO.getUserDetail(email);
	}
	
	public List<VehicleRegistration> getFreeRegisteredVehicleList(){
		return registrationDAO.getFreeRegisteredVehicleList();
	}
	
	public List<DriverRegistration> getFreeRegisteredDriverList(){
		return registrationDAO.getFreeRegisteredDriverList();
	}
	
	public DriverRegistration getDriverDetailsById(Integer driverId){
		return registrationDAO.getDriverDetailsById(driverId);
	}
	
	public VehicleRegistration getVehicleDetailsById(Integer vehicleId){
		return registrationDAO.getVehicleDetailsById(vehicleId);
	}
	
	public VehicleRegistration getVehicleDetailsByVehicleNumber(String vehicleNumber){
		return registrationDAO.getVehicleDetailsByVehicleNumber(vehicleNumber);
	}
	public VehicleRegistration getVehicleDetailsByDriverId(Integer vehicleId){
		return registrationDAO.getVehicleDetailsByDriverId(vehicleId);
	}
	public List<DeviceRegistration> getAllRegisteredDevice() {
		return registrationDAO.getAllRegisteredDevice();
	}
	
	public List<DriverDeviceVehicleMapping> getAllAvailableDrivers() {
		return registrationDAO.getAllAvailableDrivers();
	}
	public List<DriverDeviceVehicleMapping> getAllAvailableDriversByVehicleType(String vehicleType) {
		return registrationDAO.getAllAvailableDriversByVehicleType(vehicleType);
	}
	public List<DriverDeviceVehicleMapping> getAllAvailableVehicle() {
		return registrationDAO.getAllAvailableVehicle();
	}
	public DeviceRegistration getDeviceDetailsById(Integer deviceId){
		return registrationDAO.getDeviceDetailsById(deviceId);
	}
	
	public DriverDeviceVehicleMapping driverValidation(String driverPhoneNumber, String deviceUniqueId){
		return registrationDAO.driverValidation(driverPhoneNumber, deviceUniqueId);
	}

	public DriverDeviceVehicleMapping getDriverDeviceDetail(String driverPhoneNumber) {
		return registrationDAO.getDriverDeviceDetail(driverPhoneNumber);
	}
	
	public ForgetPassword getEmailByPassKey(String passKey){
		return registrationDAO.getEmailByPassKey(passKey);
	}
	
	public UserDetail getUserDetails(UserLogin dto) {
		return registrationDAO.getUserDetails(dto);
	}
	public UserDetail getUserDetailByMobile(String mobile) {
		return registrationDAO.getUserDetailByMobile(mobile);
	}
	
	public UserDetail getUserDetailByEmail(String email) {
		return registrationDAO.getUserDetailByEmail(email.trim());
	}
	public UserDetail getUserDetailsWithGcmId(UserLogin dto){
		return registrationDAO.getUserDetailsWithGcmId(dto);
	}
	
	public DriverRegistration registrationDriverByAPI(DriverRegistration dto){
		return registrationDAO.registrationDriverByAPI(dto);
	}
	public VehicleRegistration registrationVehicleByAPI(VehicleRegistration dto ){
		return registrationDAO.registrationVehicleByAPI(dto);
	}
	
	public UserDetail getUserDetailsByAgentId(String dto) {
		return registrationDAO.getUserDetailsByAgentId(dto);
	}
	public DriverRegistration getDriverDetail(String driverPhoneNumber){
		return registrationDAO.getDriverDetail(driverPhoneNumber);
		
	}
	
	public List<DriverRegistration> getRegisteredDriverList(String bySubClient) {
		return registrationDAO.getRegisteredDriverList(bySubClient);
	}
	

	public List<DriverRegistration> getDriverDetailToEdit(){
		return registrationDAO.getDriverDetailToEdit();
	}	
	
	public DriverRegistration updateRegistrationDriverById(DriverRegistration dto) {
		return registrationDAO.updateRegistrationDriverById(dto);
	}
	
	 public List<DriverRegistration> getRegisteredDriverListBySubOrg(String subOrgClient) { 
		 return registrationDAO.getRegisteredDriverListBySubOrg(subOrgClient);
	 }
	 
	 public VehicleRegistration updateVehicleByAPI(VehicleRegistration dto) {
		 return registrationDAO.updateVehicleByAPI(dto);
	 }
	 
	 public  List<UserDetail>  getUserDetailList() {
		 return registrationDAO.getUserDetailList();
	 }
}
