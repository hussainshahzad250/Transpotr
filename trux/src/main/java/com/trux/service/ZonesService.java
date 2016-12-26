package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.ZonesDAO;
import com.trux.model.Zones;
@Service
public class ZonesService  {
	@Autowired
	private ZonesDAO zonesDAO;
	
	public Zones saveZone(Zones dto) {		
		return zonesDAO.saveZone(dto);
	}
	
	public List<Zones> getZones(Integer zonedId) {		
		return zonesDAO.getZones(zonedId);
	}	
	public List<Zones> getAllZones() {		
		return zonesDAO.getAllZones();
	}

}
