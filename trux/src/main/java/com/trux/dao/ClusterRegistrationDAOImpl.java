package com.trux.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

import com.trux.model.ClusterRegistration;

public class ClusterRegistrationDAOImpl  implements ClusterRegistrationDAO{

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@SuppressWarnings("unchecked")
	
	public ClusterRegistration saveCluster(ClusterRegistration dto) {
		try{
            Session session=sessionFactory.openSession();
		    Transaction tx=session.beginTransaction();
		    session.save(dto);
		    tx.commit();
		    DetachedCriteria maxId = DetachedCriteria.forClass(ClusterRegistration.class).setProjection(Projections.max("clusterId"));
		    List<ClusterRegistration>  clusterList=session.createCriteria(ClusterRegistration.class).add(Property.forName("clusterId").eq(maxId)).list();
		    session.close();
		    if(clusterList!=null && clusterList.size()>0){
		    	return	clusterList.get(0);
		    }
		     }catch(Exception er){}
		return null;
		    
	}

	
	public ClusterRegistration getCluster(ClusterRegistration dto) {
		
		return null;
	}

	
	public List<ClusterRegistration> getAllClusterList() {
		
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<ClusterRegistration> getClusterList(ClusterRegistration dto) {		   
		try{ 
		Session session = sessionFactory.openSession();
			List<ClusterRegistration> leadList = session.createQuery("From ClusterRegistration L where L.hubId="+dto.getHubId()).list();
			if (leadList != null && leadList.size() > 0) {
				return leadList;
			} 
         }catch(Exception er){}
				return null;
			  
	}
	
	@SuppressWarnings("unchecked")
	public ClusterRegistration  getClusterById(Integer clusterId) {		   
		try{ 
		Session session = sessionFactory.openSession();
			List<ClusterRegistration> leadList = session.createQuery("From ClusterRegistration L where L.clusterId="+clusterId).list();
			if (leadList != null && leadList.size() > 0) {
				return leadList.get(0);
			} 
         }catch(Exception er){}
				return null;
			  
	}

}
