package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.VehicleInfoDAO;
import com.trux.model.CurrentVehicleLocation;
import com.trux.model.VehicleLocation;

@Service
public class VehicleLocationService {

	@Autowired
	private VehicleInfoDAO vehicleInfoDAO;
	
	public void saveOrUpdate(Object o){
		vehicleInfoDAO.saveOrUpdate(o);
	}
	
	public void save(Object o){
		vehicleInfoDAO.saveOrUpdate(o);
	}
	
	public List<VehicleLocation> getAllVehicleLocationDetails(){
		return vehicleInfoDAO.list();
	}
	
	public List<VehicleLocation> getNearByVehiclesList(Integer latitudeLeft, Integer longitudeLeft){
		return vehicleInfoDAO.getNearByVehicleLists(latitudeLeft, longitudeLeft);
	}

	public CurrentVehicleLocation fetchLatestLocationByBookingId(String bookingId) {
		// TODO Auto-generated method stub
		return  vehicleInfoDAO.fetchLatestLocationByBookingId(bookingId);
	}
	
	public List<CurrentVehicleLocation> fetchAllVehicleAvailableCurrentLocation() {
		return vehicleInfoDAO.fetchAllVehicleAvailableCurrentLocation();
	}

	
	public CurrentVehicleLocation fetchVehicleLocation(String driverPhoneNumber) {
		return vehicleInfoDAO.fetchVehicleLocation(driverPhoneNumber);
	}
	
	public VehicleLocation getVehicleLocationDetails(String driverPhoneNumber){
		return vehicleInfoDAO.getVehicleLocationDetails(driverPhoneNumber);
	}
	 public void saveOrUpdateCurrentVehicleLocation(CurrentVehicleLocation object){
			   vehicleInfoDAO.saveOrUpdateCurrentVehicleLocation(object);
	 }
		 public void saveVehicleLocation(VehicleLocation object){
				  vehicleInfoDAO.saveVehicleLocation(object);
		 }
}



