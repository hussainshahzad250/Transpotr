package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.ClientMandateDetailDAO;
import com.trux.model.ClientMandateDetail;

@Service
public class ClientMandateDetailService {
	@Autowired
	private ClientMandateDetailDAO clientMandateDetailDAO;

	public ClientMandateDetail saveClientMandateDetail(ClientMandateDetail dto) {

		return clientMandateDetailDAO.saveClientMandateDetail(dto);
	}

	public ClientMandateDetail getClientMandateDetail(Integer mandateDetailId) {

		return clientMandateDetailDAO.getClientMandateDetail(mandateDetailId);
	}

	public List<ClientMandateDetail> getClientMandateDetailList() {

		return clientMandateDetailDAO.getClientMandateDetailList();
	}

	public List<ClientMandateDetail> getClientMandateDetailList(Integer mandateId) {

		return clientMandateDetailDAO.getClientMandateDetailList(mandateId);
	}

	public ClientMandateDetail updateClientMandateDetail(ClientMandateDetail dto) {
		return clientMandateDetailDAO.updateClientMandateDetail(dto);
	}
}
