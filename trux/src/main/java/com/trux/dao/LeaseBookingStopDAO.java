package com.trux.dao;

import java.util.List;

import com.trux.model.LeaseBookingStop;

public interface LeaseBookingStopDAO {

	public LeaseBookingStop saveLeaseBookingStop(LeaseBookingStop dto);

	public LeaseBookingStop updateLeaseBookingStop(LeaseBookingStop dto);
	public LeaseBookingStop getLeaseBookingStop(Integer bkLsStpId);
	public List<LeaseBookingStop> getLeaseBookingStopList(LeaseBookingStop dto);
	public List<LeaseBookingStop> getLeaseBookingStopListByBookingLeaseId(LeaseBookingStop dto);
	public List<LeaseBookingStop> saveLeaseBookingStopByList(List<LeaseBookingStop> dtoList) ;
		
	
	
}
