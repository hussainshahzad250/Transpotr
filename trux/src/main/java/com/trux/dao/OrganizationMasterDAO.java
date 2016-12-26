package com.trux.dao;

import java.util.List;

import com.trux.model.ClientMandateRequest;
import com.trux.model.ClientVehicleDeployment;
import com.trux.model.ControllerDAOTracker;
import com.trux.model.OrganizationMasterRegistration;

public interface OrganizationMasterDAO {

	public OrganizationMasterRegistration registerOrg(OrganizationMasterRegistration dto);
	public OrganizationMasterRegistration getOrgMasterDetails(OrganizationMasterRegistration dto);
	public List<OrganizationMasterRegistration> getOrganizationMasterRegistration();
    public OrganizationMasterRegistration getClientNameById(Integer	clientId);
	public List<?> clientAdhocRequestSearch(Integer clientId, Integer clientSubId, String startDate, String endDate, String orderId, Integer haul);
	public ControllerDAOTracker clientAdhocRequestDriverPayment(ClientVehicleDeployment cvd);
	public ControllerDAOTracker updateClientMandateRequestBoxes(ClientMandateRequest cmr);
	
}
