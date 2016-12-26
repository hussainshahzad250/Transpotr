package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.OrganizationalUserDDAO;
import com.trux.model.OrganizationalUser;
@Service
public class OrganizationalUserService {
@Autowired
private OrganizationalUserDDAO organizationalUserDDAO;


public OrganizationalUser saveOrganizationalUser(OrganizationalUser dto) {
	
	return organizationalUserDDAO.saveOrganizationalUser(dto);
}


public List<OrganizationalUser> getOrganizationUser(OrganizationalUser dto) {
	
	return organizationalUserDDAO.getOrganizationUser(dto);
}


public List<OrganizationalUser> getAllOrganizationUserList() {
	
	return organizationalUserDDAO.getAllOrganizationUserList();
}
}
