package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.SubsidiaryClientUserDAO;
import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.OrganizationMasterRegistration;
import com.trux.model.SubsidiaryClientOfOrg;
import com.trux.model.SubsidiaryClientUser;

@Service
public class SubsidiaryClientUserService {

	@Autowired
	private SubsidiaryClientUserDAO subsidiaryClientUserDAO;

	public SubsidiaryClientUser saveSubsidiaryClientUser(SubsidiaryClientUser dto) {

		return subsidiaryClientUserDAO.saveSubsidiaryClientUser(dto);
	}

	public List<SubsidiaryClientUser> getSubsidiaryClientUserList() {

		return subsidiaryClientUserDAO.getSubsidiaryClientUserList();
	}

	public List<SubsidiaryClientUser> getSubsidiaryClientUserListByUserId(
			Integer userId) {

		return subsidiaryClientUserDAO
				.getSubsidiaryClientUserListByUserId(userId);
	}

	public List<SubsidiaryClientOfOrg> getSubsidiaryClientOfOrgListByUserId(
			Integer userId) {

		return subsidiaryClientUserDAO.getSubsidiaryClientOfOrgListByUserId(userId);
	}
	
	public List<SubsidiaryClientOfOrg> getSubsidiaryClientOfOrgListByUserId(
			Integer userId,Integer clientId) {

		return subsidiaryClientUserDAO.getSubsidiaryClientOfOrgListByUserId(userId,clientId);
	}
public	List<DriverDeviceVehicleMapping> getDriverModileVehicle(Integer subClientId){
		return subsidiaryClientUserDAO.getDriverModileVehicle(subClientId);
	}

public List<OrganizationMasterRegistration> getOrgClient(Integer userId) {
	return subsidiaryClientUserDAO.getOrgClient(userId);
}

public List<SubsidiaryClientOfOrg> getSubsidiaryClientOfOrgListByHubId(Integer userId,Integer clientId,Integer hubId) {
	return subsidiaryClientUserDAO.getSubsidiaryClientOfOrgListByHubId(userId, clientId, hubId);
}
public List<SubsidiaryClientOfOrg> searchSubsidiaryClient(Integer userId,Integer clientId) {
	return subsidiaryClientUserDAO.searchSubsidiaryClient(userId, clientId);
}

public List<SubsidiaryClientOfOrg> searchSubsidiaryAndActiveDeactive(Integer userId,Integer clientId,Integer subClientId,Integer actionFlag) {
	return subsidiaryClientUserDAO.searchSubsidiaryAndActiveDeactive(userId, clientId, subClientId, actionFlag);
}
}

