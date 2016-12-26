package com.trux.dao;

import java.util.List;

import com.trux.model.Zones;

public interface ZonesDAO {

	public Zones saveZone(Zones dto);
	public List<Zones> getZones(Integer zonedId);
	public List<Zones> getAllZones();
}
