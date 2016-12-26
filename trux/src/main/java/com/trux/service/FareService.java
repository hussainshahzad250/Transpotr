package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.FareDao;
import com.trux.model.FareRates;

@Service
public class FareService {

	@Autowired
	private FareDao fareDao;
	
	public List<FareRates> getFareList(){
		return fareDao.getFareList();
	}
	
	public FareRates getFareByVehicleType(String vehicleType) {
		return fareDao.getFareByVehicleType(vehicleType);
	}
}
