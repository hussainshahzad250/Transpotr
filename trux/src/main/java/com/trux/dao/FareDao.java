package com.trux.dao;

import java.util.List;

import com.trux.model.FareRates;

public interface FareDao {

	public List<FareRates> getFareList();
	public FareRates getFareByVehicleType(String vehicleType) ;

}
