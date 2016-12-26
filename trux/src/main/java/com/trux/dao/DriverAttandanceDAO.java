package com.trux.dao;

import java.util.List;

import com.trux.model.DriverAttandance;

public interface DriverAttandanceDAO {

	public List<DriverAttandance> saveAttandanceOfDriver(List<DriverAttandance> listOfAttandance);
}
