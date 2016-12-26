package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.LeaseBookingStopDAO;
import com.trux.model.LeaseBookingStop;

@Service
public class LeaseBookingStopService   {

	@Autowired
	private LeaseBookingStopDAO leaseBookingStopDAO;
	
	public LeaseBookingStop saveLeaseBookingStop(LeaseBookingStop dto) {
		
		return leaseBookingStopDAO.saveLeaseBookingStop(dto);
	}

	
	public LeaseBookingStop getLeaseBookingStop(Integer bkLsStpId) {
		
		return leaseBookingStopDAO.getLeaseBookingStop(bkLsStpId);
	}

	
	public List<LeaseBookingStop> getLeaseBookingStopList(LeaseBookingStop dto) {
		
		return leaseBookingStopDAO.getLeaseBookingStopList(dto);
	}
	public LeaseBookingStop updateLeaseBookingStop(LeaseBookingStop dto) {
		return leaseBookingStopDAO.updateLeaseBookingStop(dto);
	}
	public List<LeaseBookingStop> getLeaseBookingStopListByBookingLeaseId(LeaseBookingStop dto) {
		return leaseBookingStopDAO.getLeaseBookingStopListByBookingLeaseId(dto);
	}
	public List<LeaseBookingStop> saveLeaseBookingStopByList(List<LeaseBookingStop> dtoList) {
		return leaseBookingStopDAO.saveLeaseBookingStopByList(dtoList);
	}
}
