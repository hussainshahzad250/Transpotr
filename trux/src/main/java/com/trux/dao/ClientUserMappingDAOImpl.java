package com.trux.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

import com.trux.model.ClientUserMapping; 

public class ClientUserMappingDAOImpl implements ClientUserMappingDAO{

private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	 
	
	public ClientUserMapping saveClientUserMapping(ClientUserMapping dto) {
		
		try{
            Session session=sessionFactory.openSession();
		    Transaction tx=session.beginTransaction();
		    session.save(dto);
		    tx.commit();
		    DetachedCriteria maxId = DetachedCriteria.forClass(ClientUserMapping.class).setProjection(Projections.max("userMappingId"));
		    @SuppressWarnings("unchecked")
			List<ClientUserMapping>  clusterList=session.createCriteria(ClientUserMapping.class).add(Property.forName("userMappingId").eq(maxId)).list();
		    session.close();
		    if(clusterList!=null && clusterList.size()>0){
		    	return	clusterList.get(0);
		    }
		     }catch(Exception er){}
		return null;
	}

	
	public List<ClientUserMapping> getClientUserMappingList(Integer userid) {
		
		return null;
	}

	
	public List<ClientUserMapping> getClientUserMappingListSubId(
			Integer subclientid) {
		
		return null;
	}
	
	
	

	
	public List<ClientUserMapping> getClientUserMappingList() {
		
		return null;
	}

	}
