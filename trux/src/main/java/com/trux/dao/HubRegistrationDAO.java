package com.trux.dao;

import java.util.List;

import com.trux.model.HubRegistration;
import com.trux.model.HubRegistrationDto;

public interface HubRegistrationDAO {

	public HubRegistration registerHub(HubRegistration dto);
	public HubRegistration hubName(HubRegistration dto);
	public HubRegistration clusterName(HubRegistration dto);
	public List<HubRegistration> hubList(HubRegistrationDto dto);
	public List<HubRegistration> clusterList(HubRegistrationDto dto);
	public List<HubRegistration> getHubList();
	
	public HubRegistration hubNameById(Integer hubId);
	
}
