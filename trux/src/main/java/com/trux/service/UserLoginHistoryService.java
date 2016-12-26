package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.UserLoginHistoryDAO;
import com.trux.model.UserLoginHistory;
@Service
public class UserLoginHistoryService   {
@Autowired
	private UserLoginHistoryDAO userLoginHistoryDAO;
	public UserLoginHistory saveUserLoginHistory(UserLoginHistory dto) {
		
		return userLoginHistoryDAO.saveUserLoginHistory(dto);
	}

	
	public List<UserLoginHistory> getUserLoginHistoryList(Integer userId) {
		
		return userLoginHistoryDAO.getUserLoginHistoryList(userId);
	}

	
	public List<UserLoginHistory> getUserLoginHistoryList() {
		
		return userLoginHistoryDAO.getUserLoginHistoryList();
	}

}
