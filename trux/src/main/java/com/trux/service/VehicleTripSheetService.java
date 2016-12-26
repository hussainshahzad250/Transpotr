package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.VehicleTripSheetDAO;
import com.trux.model.AssignedStands;
import com.trux.model.VehicleTripSheet;
import com.trux.model.VehicleTripsheetDrops;

@Service
public class VehicleTripSheetService {
	@Autowired
	private VehicleTripSheetDAO vehicleTripSheetDAO;

	public VehicleTripSheet saveVehicleTripSheet(VehicleTripSheet dto) {

		return vehicleTripSheetDAO.saveVehicleTripSheet(dto);
	}
	
	public VehicleTripSheet uploadTripSheet(VehicleTripSheet dto) {

		return vehicleTripSheetDAO.uploadTripSheet(dto);
	}

	public VehicleTripsheetDrops saveVehicleTripsheetDrops(VehicleTripsheetDrops dto ){
		return vehicleTripSheetDAO.saveVehicleTripsheetDrops(dto);
	}
	public VehicleTripSheet updateVehicleTripSheet(VehicleTripSheet dto) {

		return vehicleTripSheetDAO.updateVehicleTripSheet(dto);
	}

	public List<VehicleTripSheet> getVehicleTripSheet(VehicleTripSheet dt) {

		return vehicleTripSheetDAO.getVehicleTripSheet(dt);
	}
	
	public List<VehicleTripSheet> getVehicleDetailList(int clientId,int subClientId){
		return vehicleTripSheetDAO.getVehicleDetailList(clientId, subClientId);
	}

	public List<VehicleTripSheet> getVehicleTripSheetAPI(VehicleTripSheet dto) {
		return vehicleTripSheetDAO.getVehicleTripSheetAPI(dto);
	}

	public List getAssignedStand(Integer userId) {
		return vehicleTripSheetDAO.getAssignedStand(userId);
	}

	public List getNonClosedTripSheet(Integer userId) {
		return vehicleTripSheetDAO.getNonClosedTripSheet(userId);
	}
}
