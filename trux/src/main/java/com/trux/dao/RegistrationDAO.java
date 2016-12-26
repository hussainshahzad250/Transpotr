package com.trux.dao;

import java.util.List;

import com.trux.model.DeviceRegistration;
import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.DriverRegistration;
import com.trux.model.ForgetPassword;
import com.trux.model.UserDetail;
import com.trux.model.UserLogin;
import com.trux.model.VehicleRegistration;

public interface RegistrationDAO {
	
	public void saveOrUpdate(Object object);
	public void saveOrUpdateDDVM(DriverDeviceVehicleMapping object);
	public UserDetail saveUserDetail(UserDetail object);
	
	public List<VehicleRegistration> getRegisteredVehicleList();
	
	public List<DriverRegistration> getRegisteredDriverList();
	public List<DriverRegistration> getRegisteredDriverList(String byOrganization);
	
	public List<DeviceRegistration> getAllRegisteredDevice();
	
	public List<VehicleRegistration> getFreeRegisteredVehicleList();
	
	public List<DriverRegistration> getFreeRegisteredDriverList();
	
	public List<DeviceRegistration> getFreeRegisteredDeviceList();
	
	public UserDetail getUserDetail(String email);
	public UserDetail getUserDetailByMobile(String mobile);
	public String changePasswords(UserDetail dto);
	public DriverRegistration getDriverDetailsById(Integer driverId);
	
	public VehicleRegistration getVehicleDetailsById(Integer vehicleId);
	
	public DeviceRegistration getDeviceDetailsById(Integer deviceId);
	
	public DriverDeviceVehicleMapping driverValidation(String driverPhoneNumber, String deviceUniqueId);

	public DriverDeviceVehicleMapping getDriverDeviceDetail(String driverPhoneNumber);

	public List<DriverDeviceVehicleMapping> getAllAvailableDrivers();
	public List<DriverDeviceVehicleMapping> getAllAvailableDriversByVehicleType(String vehicleType);
	
	public ForgetPassword getEmailByPassKey(String passkey);
	
	public List<DriverDeviceVehicleMapping> getAllAvailableVehicle();
	
	public UserDetail getUserDetails(UserLogin dto);
	
	public UserDetail getUserDetailsWithGcmId(UserLogin dto);
	public UserDetail getUserDetailsByAgentId(String dto);

	public DriverRegistration registrationDriverByAPI(DriverRegistration dto);
	public VehicleRegistration registrationVehicleByAPI(VehicleRegistration dto );
	
	public DriverRegistration getDriverDetail(String driverPhoneNumber);
	public VehicleRegistration getVehicleDetailsByDriverId(Integer vehicleId);
	
	public UserDetail getUserDetailByEmail(String email);
	public List<DriverRegistration> getDriverDetailToEdit();
	public DriverRegistration updateRegistrationDriverById(DriverRegistration dto) ;
	
    public List<DriverRegistration> getRegisteredDriverListBySubOrg(String subOrgClient) ;
    public VehicleRegistration getVehicleDetailsByVehicleNumber(String vehicleNumber);
		
	public VehicleRegistration updateVehicleByAPI(VehicleRegistration dto) ;
	public  List<UserDetail>  getUserDetailList() ;
}
