package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.ModuleDAO;
import com.trux.model.CRFOrder;
import com.trux.model.Cities;
import com.trux.model.ControllerDAOTracker;
import com.trux.model.Countries;
import com.trux.model.Module;
import com.trux.model.States;
import com.trux.model.UserDetail;
import com.trux.model.VehicleType;


@Service
public class ModuleService  {
	@Autowired
	private ModuleDAO moduleDAO;
	 
	public Module save(Module dto) {
		return moduleDAO.save(dto);
	}

	 
	public List<Module> getModule(String accessRightsRole) {
	 	return moduleDAO.getModule(accessRightsRole);
	}
	public List<Countries> getAllCountriesList(){
		return moduleDAO.getAllCountriesList();
	}
	public List<States> getStatesListForACountry(Integer countryId) {return moduleDAO.getStatesListForACountry(countryId);}
	public List<Cities> getAllCitiesForAState(Integer stateId){
		return moduleDAO.getAllCitiesForAState(stateId);
	}

	public  Cities  getAllCityById(Integer cityId){return moduleDAO.getAllCityById(cityId);}
	
	public States getAllStateById(Integer stateId) {
		return moduleDAO.getAllStateById(stateId);
	}
	public Countries getAllCountryById(Integer countryID) {
			return moduleDAO.getAllCountryById(countryID);
	}


	public List<UserDetail> getUserList() {
		return moduleDAO.getUserList();
	}


	public List<VehicleType> getVehicleType() {
		return moduleDAO.getVehicleType();
	}


	public List<CRFOrder> getAllOrders() {
		return moduleDAO.getAllOrders();
	}


	public ControllerDAOTracker getFilterOrder(String date) {
		return moduleDAO.getFilterOrder(date);
	}


	public List<VehicleType> getAllVehicle(int parseInt) {
		return moduleDAO.getAllVehicle(parseInt);
		}

	public List<VehicleType> getVehicle(String string) {
		return moduleDAO.getVehicle(string);
	}





	
}
