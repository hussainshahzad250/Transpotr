package com.trux.dao;

import java.util.List;

import com.trux.model.CRFOrder;
import com.trux.model.Cities;
import com.trux.model.ControllerDAOTracker;
import com.trux.model.Countries;
import com.trux.model.Module;
import com.trux.model.States;
import com.trux.model.UserDetail;
import com.trux.model.VehicleType;

public interface ModuleDAO {

	public Module save(Module dto);
	public List<Module> getModule(String accessRightsRole);
	public List<Countries> getAllCountriesList();
	public List<States> getStatesListForACountry(Integer countryId) ;
	public List<Cities> getAllCitiesForAState(Integer stateId);
	public Cities getAllCityById(Integer cityId);
	
	public States getAllStateById(Integer stateId) ;
	public Countries getAllCountryById(Integer countryID) ;
	public List<UserDetail> getUserList();
	public List<VehicleType> getVehicleType();
	public List<CRFOrder> getAllOrders();
	public ControllerDAOTracker getFilterOrder(String date);
	public List<VehicleType> getAllVehicle(int parseInt);
	public List<VehicleType> getVehicle(String string);
	
}
