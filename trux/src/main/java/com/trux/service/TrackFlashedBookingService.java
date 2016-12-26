package com.trux.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.TrackFlashedBookingDAOImpl;

@Service
public class TrackFlashedBookingService {

	@Autowired
	private TrackFlashedBookingDAOImpl trackFlashedBookingDao;

	
	public void trackFlashedBooking(Collection<Object>   obj) {
		
		trackFlashedBookingDao.trackFlashedBooking(obj);
	}
}
