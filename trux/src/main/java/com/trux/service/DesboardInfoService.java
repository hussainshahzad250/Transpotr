package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.DesboardInfoDAO;
import com.trux.model.DesboardInfo;

@Service
public class DesboardInfoService {
	@Autowired
	private DesboardInfoDAO desboardInfoDAO;

	public List<DesboardInfo> getDesboardInfo() {
		return desboardInfoDAO.getDesboardInfo();
	}

	public DesboardInfo saveDesboardInfo(DesboardInfo dto) {
		return desboardInfoDAO.saveDesboardInfo(dto);
	}

}
