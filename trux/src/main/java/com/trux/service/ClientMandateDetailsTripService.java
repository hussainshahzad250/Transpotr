package com.trux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.ClientMandateDetailsTripDAO;
import com.trux.model.ClientMandateDetailsTrip;

@Service
public class ClientMandateDetailsTripService {
	@Autowired
	private ClientMandateDetailsTripDAO clientMandateDetailsTripDAO;

	public ClientMandateDetailsTrip saveClientMandateDetailsTrip(ClientMandateDetailsTrip dto) {

		return clientMandateDetailsTripDAO.saveClientMandateDetailsTrip(dto);
	}

	public ClientMandateDetailsTrip updateClientMandateDetailsTrip(
			ClientMandateDetailsTrip dto) {
		return clientMandateDetailsTripDAO.updateClientMandateDetailsTrip(dto);
	}

}
