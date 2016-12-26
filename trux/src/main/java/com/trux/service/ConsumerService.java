package com.trux.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.ConsumerRegistrationDAO;
import com.trux.model.ConsumerRegistration;
import com.trux.model.ForgotPassword;

@Service
public class ConsumerService {

	@Autowired
	private ConsumerRegistrationDAO consumerRegistrationDAO;
	
	public void saveOrUpdate(Object obj){
		consumerRegistrationDAO.saveOrUpdate(obj);
	}
	public void consumerRegistration(ConsumerRegistration object) {
		consumerRegistrationDAO.consumerRegistration(object);
	}

	public ConsumerRegistration getUserDetailsByPhoneNumber(String phoneNumber) {
		
			return consumerRegistrationDAO.getUserDetailsByPhoneNumber(phoneNumber);
		
	}
	
	public ConsumerRegistration getUserDetailsByEmail(String emailAddress) {
		
		return consumerRegistrationDAO.getUserDetailsByEmail(emailAddress);
	
}
	

	public void save(Object forgotPassword) {
		
		List< String> clients = new ArrayList<String>();
		clients.remove("RHB.exe");
		
		 consumerRegistrationDAO.save(forgotPassword);
	}
	
	public List<ConsumerRegistration> getConsumerReports()
	{
		return consumerRegistrationDAO.getConsumerDetails();
	}
	public List<ConsumerRegistration> getConsumerDetailsList(){
		return consumerRegistrationDAO.getConsumerDetailsList();
	}
	public List<ConsumerRegistration> searchByMobile(String mobile){
		return consumerRegistrationDAO.searchByMobile(mobile);
	}
}
