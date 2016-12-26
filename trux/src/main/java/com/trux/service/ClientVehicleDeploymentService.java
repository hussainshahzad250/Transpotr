package com.trux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.ClientVehicleDeploymentDAO;
import com.trux.model.ClientVehicleDeployment;

@Service
public class ClientVehicleDeploymentService {
	@Autowired
	ClientVehicleDeploymentDAO clientVehicleDeploymentDAO;

	public ClientVehicleDeployment saveClientVehicleDeployment(ClientVehicleDeployment dto) {
		 
		return clientVehicleDeploymentDAO.saveClientVehicleDeployment(dto);
	}
	public void saveClientVehicleDeployments(StringBuilder dto) {
		  clientVehicleDeploymentDAO.saveClientVehicleDeployments(dto);
	}
	
	public int totalClientVehicleDeployments(ClientVehicleDeployment dto) {
		return clientVehicleDeploymentDAO.totalClientVehicleDeployments(dto);
	}
}
