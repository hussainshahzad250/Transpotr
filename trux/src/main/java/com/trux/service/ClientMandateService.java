package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.ClientMandateDAO;
import com.trux.model.ClientMandate;
import com.trux.model.ClientMandateDetail;

@Service
public class ClientMandateService {
	@Autowired
	private ClientMandateDAO clientMandateDAO;

	public ClientMandate saveClientMandate(ClientMandate dto) {

		return clientMandateDAO.saveClientMandate(dto);
	}

	public List<ClientMandate> getClientMandate() {

		return clientMandateDAO.getClientMandate();
	}

	public ClientMandate getClientMandate(Integer clientMandateId) {

		return clientMandateDAO.getClientMandate(clientMandateId);
	}

	public List<ClientMandate> getMandateByClientAndSubClient(Integer clientId, Integer subClientId){
		return clientMandateDAO.getMandateByClientAndSubClient(clientId, subClientId);
	}
	public List<ClientMandateDetail> getMandateDetailByClientAndSubClient(Integer clientId, Integer subClientId) {
		return clientMandateDAO.getMandateDetailByClientAndSubClient(clientId, subClientId);
	}
	public List<ClientMandate> getMandateCSUBId(Integer clientId, Integer subClientId) {
		return clientMandateDAO.getMandateCSUBId(clientId, subClientId);
		
	}
	
	public List<ClientMandate> getMandateCSUBId(Integer clientId, int pageNumber,int pageSize) {
		return clientMandateDAO.getMandateCSUBId(clientId,pageNumber,pageSize);
		
	}
	
	public List<ClientMandate> getMandateCSUBIdAndMandateType(Integer clientId, Integer subClientId,String mandateType) {
		return clientMandateDAO.getMandateCSUBIdAndMandateType(clientId, subClientId, mandateType);
	}
	
	public ClientMandate updateMandate(ClientMandate dto){
		return clientMandateDAO.updateMandate(dto);
	}
	public void deleteMandate(ClientMandate dto){
		  clientMandateDAO.deleteMandate(dto);
	}
	
	public List<ClientMandateDetail> getClientMandateDetail(Integer clientId,Integer subClientId, String mandateType,int pageNumber,int pageSize){
		return clientMandateDAO.getClientMandateDetail(clientId, subClientId, mandateType, pageNumber, pageSize);
	}
	
	public List<ClientMandateDetail> getClientMandateDetail(Integer clientId,Integer subClientId, String mandateType){
		return clientMandateDAO.getClientMandateDetail(clientId, subClientId, mandateType);
	}
	
	public ClientMandateDetail updateMandateDetails(ClientMandateDetail dto){
		return clientMandateDAO.updateMandateDetails(dto);
	} 
}

