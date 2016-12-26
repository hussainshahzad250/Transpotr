package com.trux.dao;

import java.util.List;

import com.trux.model.DriverLoginHistory;

public interface DriverLoginHistoryDAO {

	public DriverLoginHistory saveDriverLoginHistory(DriverLoginHistory dto);
	public DriverLoginHistory updateDriverLoginHistory(DriverLoginHistory dto);
	public DriverLoginHistory getDriverLoginHistory(DriverLoginHistory dto);
	public List<DriverLoginHistory> getDriverLoginHistoryListByMobile(DriverLoginHistory dto);
	public List<DriverLoginHistory> getAllDriverLoginHistoryList();
	
	
	
}
