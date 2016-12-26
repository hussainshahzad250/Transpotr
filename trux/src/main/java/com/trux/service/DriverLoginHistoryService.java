package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.DriverLoginHistoryDAO;
import com.trux.model.DriverLoginHistory;

@Service
public class DriverLoginHistoryService  {
 @Autowired
	private DriverLoginHistoryDAO driverLoginHistoryDAO;
	
	public DriverLoginHistory saveDriverLoginHistory(DriverLoginHistory dto) {
		
		return driverLoginHistoryDAO.saveDriverLoginHistory(dto);
	}

	
	public DriverLoginHistory updateDriverLoginHistory(DriverLoginHistory dto) {
		
		return driverLoginHistoryDAO.updateDriverLoginHistory(dto);
	}

	
	public DriverLoginHistory getDriverLoginHistory(DriverLoginHistory dto) {
		
		return driverLoginHistoryDAO.getDriverLoginHistory(dto);
	}

	
	public List<DriverLoginHistory> getDriverLoginHistoryListByMobile(DriverLoginHistory dto) {
		
		return driverLoginHistoryDAO.getDriverLoginHistoryListByMobile(dto);
	}

	
	public List<DriverLoginHistory> getAllDriverLoginHistoryList() {
		
		return driverLoginHistoryDAO.getAllDriverLoginHistoryList();
	}

}
