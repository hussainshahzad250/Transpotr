package com.trux.dao;

import com.trux.model.ClientVehicleDeployment;

public interface ClientVehicleDeploymentDAO {

	public ClientVehicleDeployment saveClientVehicleDeployment(ClientVehicleDeployment dto);
	public void saveClientVehicleDeployments(StringBuilder dto) ;
	public int totalClientVehicleDeployments(ClientVehicleDeployment dto) ;
}
