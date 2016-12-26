package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.OrgBookingDAO;
import com.trux.model.OrganizationBookingRegistration;
@Service
public class OrgBookingDAOService  {
    
	@Autowired            
	private OrgBookingDAO orgBookingDAO;
	 
	public OrganizationBookingRegistration orgBooking(OrganizationBookingRegistration dto) {
		
		return orgBookingDAO.orgBooking(dto);
	}

	
	public OrganizationBookingRegistration orgBookingDetails(String mobile) {
		
		return orgBookingDAO.orgBookingDetails(mobile);
	}

	
	public List<OrganizationBookingRegistration> orgBookingDetails() {
		
		return orgBookingDAO.orgBookingDetails();
	}

}
