package com.trux.dao;

import java.util.List;

import com.trux.model.ClientUserMapping;

public interface ClientUserMappingDAO {

	public ClientUserMapping saveClientUserMapping(ClientUserMapping dto);
	
	public List<ClientUserMapping> getClientUserMappingList(Integer userid);
	public List<ClientUserMapping> getClientUserMappingListSubId(Integer subclientid);	
	public List<ClientUserMapping> getClientUserMappingList();
}
