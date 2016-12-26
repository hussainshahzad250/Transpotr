package com.trux.dao;

import java.util.List;

import com.trux.model.LeaseBooking;
import com.trux.model.LeaseBookingHandler;

public interface LeaseBookingDAO {

	public LeaseBooking saveLeaseBooking(LeaseBooking dto);
	public LeaseBooking updateLeaseBooking(LeaseBooking dto);
	public LeaseBooking updateLeaseBookingAtResume(LeaseBooking dto) ;
	public LeaseBooking updateLeaseBookingAtEnd(LeaseBooking dto);
	public LeaseBooking getLeaseBookingById(Integer leaseBookingId);
	public List<LeaseBooking> getLeaseBookingList(LeaseBooking dto);
	public List<LeaseBooking> getLeaseBookingListByBookingDate(LeaseBookingHandler dto);
}
