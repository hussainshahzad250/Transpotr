package com.trux.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.trux.model.CustomerBookingDetails;
import com.trux.model.DriverDeviceVehicleMapping;

@Service
public class TruxStartUpService {
	
	public Queue<CustomerBookingDetails> mailingQueue;
	public Queue<CustomerBookingDetails> orderPushingQueue;

	public Map<String, DriverDeviceVehicleMapping> driverRegistrationMap;

	@PostConstruct
	//@Scheduled(fixedRate=15*60*1000)
	public void init()
	{
		System.out.println("Trux initializing.....");
		mailingQueue = new LinkedList<CustomerBookingDetails>();
		orderPushingQueue = new LinkedList<CustomerBookingDetails>();
		driverRegistrationMap = new HashMap<String, DriverDeviceVehicleMapping>();
		System.out.println("Trux initialized......");
	}
}
