package com.trux.dao;

import java.util.List;

import com.trux.model.OrganizationalUser;

public interface OrganizationalUserDDAO {

	public OrganizationalUser saveOrganizationalUser(OrganizationalUser dto);
	public List<OrganizationalUser> getOrganizationUser(OrganizationalUser dto);
	public List<OrganizationalUser> getAllOrganizationUserList();
}
