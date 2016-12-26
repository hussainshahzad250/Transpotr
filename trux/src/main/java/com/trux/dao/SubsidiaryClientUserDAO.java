package com.trux.dao;

import java.util.List;

import com.trux.model.DriverDeviceVehicleMapping;
import com.trux.model.OrganizationMasterRegistration;
import com.trux.model.SubsidiaryClientOfOrg;
import com.trux.model.SubsidiaryClientUser;

public interface SubsidiaryClientUserDAO {

	public SubsidiaryClientUser saveSubsidiaryClientUser(SubsidiaryClientUser dto);
	
	public List<SubsidiaryClientUser> getSubsidiaryClientUserList();
	
	public List<SubsidiaryClientUser> getSubsidiaryClientUserListByUserId(Integer userId);
	
	public List<SubsidiaryClientOfOrg> getSubsidiaryClientOfOrgListByUserId(Integer userId);
	public List<SubsidiaryClientOfOrg> getSubsidiaryClientOfOrgListByUserId(Integer userId,Integer clientId);
	List<DriverDeviceVehicleMapping> getDriverModileVehicle(Integer subClientId);
	
	public List<OrganizationMasterRegistration> getOrgClient(Integer userId) ;
	public List<SubsidiaryClientOfOrg> getSubsidiaryClientOfOrgListByHubId(Integer userId,Integer clientId,Integer hubId) ;
	public List<SubsidiaryClientOfOrg> searchSubsidiaryClient(Integer userId,Integer clientId) ;
	public List<SubsidiaryClientOfOrg> searchSubsidiaryAndActiveDeactive(Integer userId,Integer clientId,Integer subClientId,Integer actionFlag) ;
	
}
