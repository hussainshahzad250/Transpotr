package com.trux.dao;

import java.util.List;

import com.trux.model.SubsidiaryClientOfOrg;

public interface SubsaidiaryOrgRegistrationDAO {

	public SubsidiaryClientOfOrg saveSubsidairyClientOrg(SubsidiaryClientOfOrg dto);	
	public List<SubsidiaryClientOfOrg> getListSubsidairyClientOrg(Integer idClientMaster );
	public List<SubsidiaryClientOfOrg> getListSubsidairyClientOrg(Integer idClientMaster,Integer hubId);
	public List<SubsidiaryClientOfOrg> getListSubsidairyClientOrgByID(Integer idClientSubMaster) ;
	public SubsidiaryClientOfOrg getListSubsidairyClientOrgByid(Integer idClientSubMaster);
	public List<SubsidiaryClientOfOrg> getListSubsidairyClientByid(Integer idClientMaster) ;	
	public List<SubsidiaryClientOfOrg> getSubsidairyClientOrg(Integer idClientMaster) ;
}
