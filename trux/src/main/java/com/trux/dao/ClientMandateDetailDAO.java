package com.trux.dao;

import java.util.List;
 
import com.trux.model.ClientMandateDetail;

public interface ClientMandateDetailDAO {

	public ClientMandateDetail saveClientMandateDetail(ClientMandateDetail dto);
	public ClientMandateDetail getClientMandateDetail(Integer mandateDetailId);
	public List<ClientMandateDetail> getClientMandateDetailList();
	public List<ClientMandateDetail> getClientMandateDetailList(Integer mandateId);
	public ClientMandateDetail updateClientMandateDetail(ClientMandateDetail dto) ;
	

	
}
