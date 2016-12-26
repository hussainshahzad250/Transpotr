package com.trux.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trux.dao.ClusterRegistrationDAO;
import com.trux.model.ClusterRegistration;

@Service
public class ClusterRegistrationService  {
	
	@Autowired
	private ClusterRegistrationDAO clusterRegistrationDAO;

	
	public ClusterRegistration saveCluster(ClusterRegistration dto) {
	 
		return clusterRegistrationDAO.saveCluster(dto);
	}

	
	public ClusterRegistration getCluster(ClusterRegistration dto) {
		
		return clusterRegistrationDAO.getCluster(dto);
	}

	
	public List<ClusterRegistration> getAllClusterList() {
		
		return clusterRegistrationDAO.getAllClusterList();
	}

	
	public List<ClusterRegistration> getClusterList(ClusterRegistration dto) {
		
		return clusterRegistrationDAO.getClusterList(dto);
	}
	
	public ClusterRegistration  getClusterById(Integer clusterId){
		return clusterRegistrationDAO.getClusterById(clusterId);
	}

}
