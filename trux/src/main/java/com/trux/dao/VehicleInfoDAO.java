package com.trux.dao;

import java.util.List;

import com.trux.model.CurrentVehicleLocation;
import com.trux.model.VehicleLocation;

public interface VehicleInfoDAO {
	
	public void saveOrUpdate(Object object);
	
	public void save(Object object);

	public List<VehicleLocation> list();
	
	public List<VehicleLocation> getNearByVehicleLists(Integer latitudeLeft, Integer longitudeLeft);

	public CurrentVehicleLocation fetchLatestLocationByBookingId(String bookingId);
	
	public List<CurrentVehicleLocation> fetchAllVehicleAvailableCurrentLocation();
	
	public CurrentVehicleLocation fetchVehicleLocation(String driverPhoneNumber) ;

	public VehicleLocation getVehicleLocationDetails(String driverPhoneNumber);
   public void saveOrUpdateCurrentVehicleLocation(CurrentVehicleLocation object);
   public void saveVehicleLocation(VehicleLocation object);
		

}
