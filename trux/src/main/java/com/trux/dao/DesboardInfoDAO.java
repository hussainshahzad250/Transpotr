package com.trux.dao;

import java.util.List;

import com.trux.model.DesboardInfo;

public interface DesboardInfoDAO {

	public List<DesboardInfo> getDesboardInfo();
	public DesboardInfo saveDesboardInfo(DesboardInfo dto);
}
