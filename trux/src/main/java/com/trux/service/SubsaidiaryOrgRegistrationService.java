package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.SubsaidiaryOrgRegistrationDAO;
import com.trux.model.SubsidiaryClientOfOrg;
@Service
public class SubsaidiaryOrgRegistrationService  {
@Autowired
 private SubsaidiaryOrgRegistrationDAO subsaidiaryOrgRegistrationDAO;
	
	public SubsidiaryClientOfOrg saveSubsidairyClientOrg(SubsidiaryClientOfOrg dto) {
		return subsaidiaryOrgRegistrationDAO.saveSubsidairyClientOrg(dto);
	}

	
	public List<SubsidiaryClientOfOrg> getListSubsidairyClientOrg(Integer idClientMaster) {
	 	return subsaidiaryOrgRegistrationDAO.getListSubsidairyClientOrg(idClientMaster);
	}
	public List<SubsidiaryClientOfOrg> getListSubsidairyClientOrgByID(Integer idClientSubMaster) {
		return subsaidiaryOrgRegistrationDAO.getListSubsidairyClientOrgByID(idClientSubMaster);

	}
	
	public SubsidiaryClientOfOrg getListSubsidairyClientOrgByid(Integer idClientSubMaster){
		return subsaidiaryOrgRegistrationDAO.getListSubsidairyClientOrgByid(idClientSubMaster);
	}

	
	public List<SubsidiaryClientOfOrg> getListSubsidairyClientOrg(Integer idClientMaster, Integer hubId) {
			return subsaidiaryOrgRegistrationDAO.getListSubsidairyClientOrg(idClientMaster, hubId);
	}
	public List<SubsidiaryClientOfOrg> getListSubsidairyClientByid(Integer idClientMaster) {
		return subsaidiaryOrgRegistrationDAO.getListSubsidairyClientByid(idClientMaster);
	}
	public List<SubsidiaryClientOfOrg> getSubsidairyClientOrg(Integer idClientMaster) {
		return subsaidiaryOrgRegistrationDAO.getSubsidairyClientOrg(idClientMaster);
	}
}
