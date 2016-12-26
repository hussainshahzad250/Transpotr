package com.trux.dao;

import java.util.List;

import com.trux.model.OrganizationBookingRegistration;

public interface OrgBookingDAO {
public OrganizationBookingRegistration orgBooking(OrganizationBookingRegistration dto);
public OrganizationBookingRegistration orgBookingDetails(String  mobile);
public List<OrganizationBookingRegistration> orgBookingDetails();
}
