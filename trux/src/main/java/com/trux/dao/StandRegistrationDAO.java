package com.trux.dao;

import java.util.List;

import com.trux.model.StandRegistration;

public interface StandRegistrationDAO {

	public StandRegistration saveStand(StandRegistration dto);
	public List<StandRegistration> getAllStandList();
	public List<StandRegistration> getStandListByCluster(Integer clusterId);
	public StandRegistration  getStandByID(Integer statndId) ;
}
