package com.trux.dao;

import java.util.List;

import com.trux.model.DriverCollection;

public interface DriverCollectionDAO {
	public DriverCollection saveDriverCollection(DriverCollection dto);
	public DriverCollection updateDriverCollection(DriverCollection dto);
	public DriverCollection deactivateDriver(int  driverId);
	public DriverCollection deactivateVehicle(int  driverId);	
	public int getDriverID(String driverMobile);
	public int getVehicleID(String vehiclOwnerMobile);
	public DriverCollection driverAmountCollection(DriverCollection  dto);
	public DriverCollection getDriverCollection(DriverCollection  dto);
	public List<DriverCollection> getDriverCollectionAmount(String driverId, String DeviceEmei);
	
}
