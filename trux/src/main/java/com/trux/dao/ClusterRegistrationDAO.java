package com.trux.dao;

import java.util.List;

import com.trux.model.ClusterRegistration;

public interface ClusterRegistrationDAO {

	public ClusterRegistration saveCluster(ClusterRegistration dto);
	public ClusterRegistration getCluster(ClusterRegistration dto);
	public List<ClusterRegistration> getAllClusterList();
	public List<ClusterRegistration> getClusterList(ClusterRegistration dto);
	public ClusterRegistration  getClusterById(Integer clusterId);
}
