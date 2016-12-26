package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.ClientUserMappingDAO;
import com.trux.model.ClientUserMapping;
@Service
public class ClientUserMappingService {
	@Autowired
	ClientUserMappingDAO clientUserMappingDAO;
	
	public ClientUserMapping saveClientUserMapping(ClientUserMapping dto) {
		
		return clientUserMappingDAO.saveClientUserMapping(dto);
	}

	
	public List<ClientUserMapping> getClientUserMappingList(Integer userid) {
		
		return clientUserMappingDAO.getClientUserMappingList(userid);
	}

	
	public List<ClientUserMapping> getClientUserMappingListSubId(Integer subclientid) {
		
		return clientUserMappingDAO.getClientUserMappingListSubId(subclientid);
	}

	
	public List<ClientUserMapping> getClientUserMappingList() {
		
		return clientUserMappingDAO.getClientUserMappingList();
	}

}
