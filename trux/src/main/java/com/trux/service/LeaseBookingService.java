package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.LeaseBookingDAO;
import com.trux.model.LeaseBooking;
import com.trux.model.LeaseBookingHandler;

@Service
public class LeaseBookingService {

	@Autowired
	private LeaseBookingDAO leaseBookingDAO;

	public LeaseBooking saveLeaseBooking(LeaseBooking dto) {

		return leaseBookingDAO.saveLeaseBooking(dto);
	}

	public LeaseBooking getLeaseBookingById(Integer leaseBookingId) {

		return leaseBookingDAO.getLeaseBookingById(leaseBookingId);
	}

	public List<LeaseBooking> getLeaseBookingList(LeaseBooking dto) {
		return leaseBookingDAO.getLeaseBookingList(dto);
	}
	public LeaseBooking updateLeaseBooking(LeaseBooking dto) {
		return leaseBookingDAO.updateLeaseBooking(dto);
	}
	public LeaseBooking updateLeaseBookingAtResume(LeaseBooking dto) {
		return leaseBookingDAO.updateLeaseBookingAtResume(dto);
	}
	public LeaseBooking updateLeaseBookingAtEnd(LeaseBooking dto) {
		return leaseBookingDAO.updateLeaseBookingAtEnd(dto);
		
	}
	
	public List<LeaseBooking> getLeaseBookingListByBookingDate(LeaseBookingHandler dto){
		return leaseBookingDAO.getLeaseBookingListByBookingDate(dto);
	}
}
