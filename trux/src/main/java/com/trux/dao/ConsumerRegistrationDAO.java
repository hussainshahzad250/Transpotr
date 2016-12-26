package com.trux.dao;

import java.util.List;

import com.trux.model.ConsumerRegistration;

public interface ConsumerRegistrationDAO {

	public void saveOrUpdate(Object object);

	public ConsumerRegistration getUserDetailsByPhoneNumber(String phoneNumber);

	public void save(Object forgotPassword);

	public ConsumerRegistration getUserDetailsByEmail(String emailAddress);
	
	public List<ConsumerRegistration> getConsumerDetails();
	public List<ConsumerRegistration> getConsumerDetailsList();

	public List<ConsumerRegistration> searchByMobile(String mobile) ;
	public void consumerRegistration(ConsumerRegistration object);
}
