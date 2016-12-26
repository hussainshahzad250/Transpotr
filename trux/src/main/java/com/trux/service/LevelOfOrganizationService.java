package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.LevelOfOrganizationDAO;
import com.trux.model.LevelOfOrganization;

@Service
public class LevelOfOrganizationService 
  {
	@Autowired
    private LevelOfOrganizationDAO levelOfOrganizationDAO;
	
	public LevelOfOrganization saveLevelOfOrganization(LevelOfOrganization dto) {
		
		return levelOfOrganizationDAO.saveLevelOfOrganization(dto);
	}

	
	public List<LevelOfOrganization> getLevelOfClientLevelMaster(
			Integer clientLevelMasterId) {
		
		return levelOfOrganizationDAO.getLevelOfClientLevelMaster(clientLevelMasterId);
	}

	
	public List<LevelOfOrganization> getLevelOfClientMaster(
			Integer clientMasterId) {
		
		return levelOfOrganizationDAO.getLevelOfClientMaster(clientMasterId);
	}

}
