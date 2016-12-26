package com.trux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.MultiDropPointBookingDetailsDAO;
import com.trux.model.OrganizationalClientBookingDetails;
@Service
public class MultiDropPointBookingDetailsService {
	@Autowired
	private MultiDropPointBookingDetailsDAO multiDropPointBookingDetailsDAO;	 
	public OrganizationalClientBookingDetails bookRide(OrganizationalClientBookingDetails dto) {
		
		return multiDropPointBookingDetailsDAO.bookRide(dto);
	}

}
