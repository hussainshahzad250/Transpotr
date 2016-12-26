package com.trux.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.VehicleTypeDAO;
import com.trux.model.VehicleType;
@Service
public class VehicleTypeService {
@Autowired 
VehicleTypeDAO vehicleTypeDAO;

public VehicleType save(VehicleType dto) {
	 
	return vehicleTypeDAO.save(dto);
}
 
public List<VehicleType> getAllVehicleType() {
	return vehicleTypeDAO.getAllVehicleType();
}

public List<VehicleType> vehicleTypeList(){
List<VehicleType>	vehicleTypeList=new ArrayList<VehicleType>();
	
	vehicleTypeList.add(new VehicleType("Mahindra Champion","Champion"));
	vehicleTypeList.add(new VehicleType("Maruti Eeco","Eco"));
	vehicleTypeList.add(new VehicleType("Tata Ace","Tata Ace"));
	vehicleTypeList.add(new VehicleType("Tata 407","Tata 407 (10 Ft)"));
	vehicleTypeList.add(new VehicleType("Tata 709","Tata 709 (14 Ft)"));
	vehicleTypeList.add(new VehicleType("Bolero Pickup","Bolero Pickup"));
	vehicleTypeList.add(new VehicleType("17 Feet Single Axle","17 Feet - Single Axle"));
	vehicleTypeList.add(new VehicleType("17 Feet Double Axle","17 Feet - Double Axle"));
	vehicleTypeList.add(new VehicleType("19 Feet Single Axle","19 Feet - Single Axle"));
	vehicleTypeList.add(new VehicleType("19 Feet Double Axle","19 Feet - Double Axle"));
	vehicleTypeList.add(new VehicleType("19 Feet Multi-Axle","19 Feet - Multi-Axle"));
	vehicleTypeList.add(new VehicleType("22 Feet Single Axle","22 Feet - Single Axle"));
	vehicleTypeList.add(new VehicleType("22 Feet Double Axle","22 Feet - Double Axle"));
	vehicleTypeList.add(new VehicleType("22 Feet Multi-Axle","22 Feet - Multi-Axle"));
	vehicleTypeList.add(new VehicleType("24 Feet Single Axle","24 Feet - Single Axle"));
	vehicleTypeList.add(new VehicleType("24 Feet Double Axle","24 Feet - Double Axle"));
	vehicleTypeList.add(new VehicleType("24 Feet Multi-Axle","24 Feet - Multi-Axle"));
	vehicleTypeList.add(new VehicleType("28 Feet Single Axle","28 Feet - Single Axle"));
	vehicleTypeList.add(new VehicleType("28 Feet Double Axle","28 Feet - Double Axle"));
	vehicleTypeList.add(new VehicleType("28 Feet Multi-Axle","28 Feet - Multi-Axle"));
	vehicleTypeList.add(new VehicleType("32 Feet Single Axle","32 Feet - Single Axle"));
	vehicleTypeList.add(new VehicleType("32 Feet Double Axle","32 Feet - Double Axle"));
	vehicleTypeList.add(new VehicleType("32 Feet Multi-Axle","32 Feet - Multi-Axle"));
	vehicleTypeList.add(new VehicleType("40 Feet Double Axle","40 Feet - Double Axle"));
	vehicleTypeList.add(new VehicleType("40 Feet Multi-Axle","40 Feet - Multi-Axle")); 
	return vehicleTypeList;
}
}
