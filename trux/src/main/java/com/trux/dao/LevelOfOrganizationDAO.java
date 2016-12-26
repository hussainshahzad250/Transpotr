package com.trux.dao;

import java.util.List;

import com.trux.model.LevelOfOrganization;

public interface LevelOfOrganizationDAO {

	public LevelOfOrganization saveLevelOfOrganization(LevelOfOrganization dto);
	
	public List<LevelOfOrganization> getLevelOfClientLevelMaster(Integer clientLevelMasterId);
	
	public List<LevelOfOrganization> getLevelOfClientMaster(Integer clientMasterId);
	
}
