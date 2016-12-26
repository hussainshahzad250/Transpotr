package com.trux.dao;

import java.util.List;

import com.trux.model.AssignedStands;
import com.trux.model.VehicleTripSheet;
import com.trux.model.VehicleTripsheetDrops;

public interface VehicleTripSheetDAO {

	public VehicleTripSheet saveVehicleTripSheet(VehicleTripSheet dto );
	public VehicleTripsheetDrops saveVehicleTripsheetDrops(VehicleTripsheetDrops dto );
	public VehicleTripSheet updateVehicleTripSheet(VehicleTripSheet dto );	
	public List<VehicleTripSheet> getVehicleTripSheet(VehicleTripSheet dt);
	public List<VehicleTripSheet> getVehicleDetailList(int clientId,int subClientId);
	public VehicleTripSheet uploadTripSheet(VehicleTripSheet dto);
	public List<VehicleTripSheet> getVehicleTripSheetAPI(VehicleTripSheet dto);
	public List getAssignedStand(Integer userId);
	public List getNonClosedTripSheet(Integer userId);
}
