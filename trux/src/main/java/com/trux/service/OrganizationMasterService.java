package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.OrganizationMasterDAO;
import com.trux.model.ClientMandateRequest;
import com.trux.model.ClientVehicleDeployment;
import com.trux.model.ControllerDAOTracker;
import com.trux.model.OrganizationMasterRegistration;
@Service
public class OrganizationMasterService  {

	@Autowired
	private OrganizationMasterDAO organizationMasterDAO;
	 
	public OrganizationMasterRegistration registerOrg(OrganizationMasterRegistration dto) {
		
		return organizationMasterDAO.registerOrg(dto);
	}

	 
	public OrganizationMasterRegistration getOrgMasterDetails(OrganizationMasterRegistration dto) {
		
		return organizationMasterDAO.getOrgMasterDetails(dto);
	}

	 
	public List<OrganizationMasterRegistration> getOrganizationMasterRegistration() {
		
		return organizationMasterDAO.getOrganizationMasterRegistration();
	}
	
	
	public OrganizationMasterRegistration getClientNameById(Integer idClientMaster) {
		return organizationMasterDAO.getClientNameById(idClientMaster);
		
	}


	public List<?> clientAdhocRequestSearch(Integer clientId, Integer clientSubId, String startDate, String endDate, String orderId, Integer haul) {
		return organizationMasterDAO.clientAdhocRequestSearch(clientId,clientSubId,startDate,endDate,orderId,haul);
	}


	public ControllerDAOTracker clientAdhocRequestDriverPayment(ClientVehicleDeployment cvd) {
		return organizationMasterDAO.clientAdhocRequestDriverPayment(cvd);
	}


	public ControllerDAOTracker updateClientMandateRequestBoxes(ClientMandateRequest cmr) {
		return organizationMasterDAO.updateClientMandateRequestBoxes(cmr);
	}

}
