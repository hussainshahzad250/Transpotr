package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.StandRegistrationDAO;
import com.trux.model.StandRegistration;
@Service
public class StandRegistrationService{
	
	@Autowired
	private StandRegistrationDAO standRegistrationDAO;
	
	public StandRegistration saveStand(StandRegistration dto) {
		
		return standRegistrationDAO.saveStand(dto);
	}

	
	public List<StandRegistration> getAllStandList() {
		
		return standRegistrationDAO.getAllStandList();
	}

	
	public List<StandRegistration> getStandListByCluster(Integer clusterId) {
		
		return standRegistrationDAO.getStandListByCluster(clusterId);
	}
	
	public StandRegistration  getStandByID(Integer statndId) {
		
		return standRegistrationDAO.getStandByID(statndId);
	} 

}
