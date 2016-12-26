package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.HubRegistrationDAO;
import com.trux.model.HubRegistration;
import com.trux.model.HubRegistrationDto;
@Service
public class HubRegistrationService {
@Autowired
private	HubRegistrationDAO hubRegistrationDAO;

 
public HubRegistration registerHub(HubRegistration dto) {
	
	return hubRegistrationDAO.registerHub(dto);
}

 
public HubRegistration hubName(HubRegistration dto) {
	
	return hubRegistrationDAO.hubName(dto);
}

 
public HubRegistration clusterName(HubRegistration dto) {
	
	return hubRegistrationDAO.clusterName(dto);
}


public List<HubRegistration> hubList(HubRegistrationDto dto) {
	
	return hubRegistrationDAO.hubList(dto);
}


public List<HubRegistration> clusterList(HubRegistrationDto dto) {
	
	return hubRegistrationDAO.clusterList(dto);
}
public List<HubRegistration> getHubList() {
	return hubRegistrationDAO.getHubList();
}

public HubRegistration hubNameById(Integer hubId) {
	return hubRegistrationDAO.hubNameById(hubId);
}
}
