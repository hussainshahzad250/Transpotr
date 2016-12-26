package com.trux.dao;

import java.util.List;

import com.trux.model.ClientMandate;
import com.trux.model.ClientMandateDetail;

public interface ClientMandateDAO {
	
public ClientMandate saveClientMandate(ClientMandate dto);
public List<ClientMandate> getClientMandate();
public ClientMandate getClientMandate(Integer clientMandateId);
public List<ClientMandate> getMandateByClientAndSubClient(Integer clientId,Integer subClientId);
public List<ClientMandate> getMandateCSUBId(Integer clientId, Integer subClientId) ;
//public List<ClientMandate> getMandateCSUBId(Integer clientId, Integer subClientId,int pageNumber,int pageSize) ;
public List<ClientMandate> getMandateCSUBId(Integer clientI,int pageNumber,int pageSize) ;
public ClientMandate updateMandate(ClientMandate dto);
public void deleteMandate(ClientMandate dto);
public List<ClientMandateDetail> getMandateDetailByClientAndSubClient(Integer clientId, Integer subClientId);
public List<ClientMandate> getMandateCSUBIdAndMandateType(Integer clientId, Integer subClientId,String mandateType) ;

public List<ClientMandateDetail> getClientMandateDetail(Integer clientId,Integer subClientId, String mandateType,int pageNumber,int pageSize);

public List<ClientMandateDetail> getClientMandateDetail(Integer clientId,Integer subClientId, String mandateType);
public ClientMandateDetail updateMandateDetails(ClientMandateDetail dto);	
}
