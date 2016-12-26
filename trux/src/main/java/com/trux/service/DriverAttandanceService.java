package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.DriverAttandanceDAO;
import com.trux.model.DriverAttandance;
@Service
public class DriverAttandanceService implements DriverAttandanceDAO {
	@Autowired
	private DriverAttandanceDAO driverAttandanceDAO;
	public List<DriverAttandance> saveAttandanceOfDriver(List<DriverAttandance> listOfAttandance) {		
		return driverAttandanceDAO.saveAttandanceOfDriver(listOfAttandance);
	}

}
