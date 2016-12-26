package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.LeadGenerationDao;
import com.trux.model.LeadGeneration;

@Service
public class LeadGenerationService {
	@Autowired
	private LeadGenerationDao leadGenerationDao;

	public void saveLeadGeneration(LeadGeneration dto) {
		leadGenerationDao.saveLeadGeneration(dto);
	}

	public List<LeadGeneration> getAllLeadGeneration() {
		return leadGenerationDao.getAllLeadGeneration();
	}

	public LeadGeneration getLeadGenerationById(Integer angentId) {
		return leadGenerationDao.getLeadGenerationById(angentId);
	}
	
	public LeadGeneration leadGeneration(LeadGeneration lg){
		return leadGenerationDao.leadGeneration(lg);
	}

}
