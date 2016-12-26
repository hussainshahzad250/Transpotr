package com.trux.dao;

import java.util.List;

import com.trux.model.VehicleType;

public interface VehicleTypeDAO {
public VehicleType save(VehicleType dto);
public List<VehicleType> getAllVehicleType();
}
