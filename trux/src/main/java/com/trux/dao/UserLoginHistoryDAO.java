package com.trux.dao;

import java.util.List;

import com.trux.model.UserLoginHistory;

public interface UserLoginHistoryDAO {

	public UserLoginHistory saveUserLoginHistory(UserLoginHistory dto);
	
	public List<UserLoginHistory> getUserLoginHistoryList(Integer userId);
	
	public List<UserLoginHistory> getUserLoginHistoryList();
}
