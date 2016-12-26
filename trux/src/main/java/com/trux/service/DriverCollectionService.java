package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.DriverCollectionDAO;
import com.trux.model.DriverCollection;
@Service
public class DriverCollectionService   {

	@Autowired
	DriverCollectionDAO driverCollectionDAO;
	 
	public DriverCollection saveDriverCollection(DriverCollection dto) {
		return driverCollectionDAO.saveDriverCollection(dto);
	}
	
	public DriverCollection getDriverCollection(DriverCollection  dto){
		return driverCollectionDAO.getDriverCollection(dto);
	}

	 
	public DriverCollection updateDriverCollection(DriverCollection dto) {
	 
		return driverCollectionDAO.updateDriverCollection(dto);
	}
	public List<DriverCollection> getDriverCollectionAmount(String driverId, String DeviceEmei){
		return driverCollectionDAO.getDriverCollectionAmount(driverId, DeviceEmei);
	}
	public DriverCollection driverAmountCollection(DriverCollection dto) {
		return driverCollectionDAO.driverAmountCollection(dto);
	}
	public DriverCollection deactivateDriver(int driverId) {
		return driverCollectionDAO.deactivateDriver(driverId);
	}
	public DriverCollection deactivateVehicle(int driverId) {
		return driverCollectionDAO.deactivateVehicle(driverId);
	}
	
public int getVehicleID(String vehiclOwnerMobile){
	return driverCollectionDAO.getVehicleID(vehiclOwnerMobile);
}
public int getDriverID(String driverMobile) {
	return driverCollectionDAO.getDriverID(driverMobile);
}
}
