package com.trux.dao;

import java.util.List;

import com.trux.model.LeadGeneration;

public interface LeadGenerationDao {
public void saveLeadGeneration(LeadGeneration dto);
public List<LeadGeneration> getAllLeadGeneration();
public LeadGeneration getLeadGenerationById(Integer angentId);
public LeadGeneration leadGeneration(LeadGeneration lg);
}
