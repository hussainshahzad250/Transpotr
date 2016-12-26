package com.trux.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.trux.model.LeadGeneration;

public class LeadGenerationDaoImpl implements LeadGenerationDao {
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	
	public void saveLeadGeneration(LeadGeneration dto) {
		Session session=sessionFactory.openSession();
		try{ 
		Transaction tx = session.beginTransaction();
		session.save(dto);
		tx.commit();
		session.close();
		 }catch(Exception er){
			 er.printStackTrace();
			 session.close();
		 }
	}
 
	@SuppressWarnings("unchecked")
	
	public List<LeadGeneration> getAllLeadGeneration() {
		Session session=sessionFactory.openSession();
		try{ 
		List<LeadGeneration> leadList = session.createQuery("From LeadGeneration L").list();
		session.close();
		if (leadList != null && leadList.size() > 0) {
			return leadList;
		} 
    }catch(Exception er){
    	er.printStackTrace();
    	session.close();
    }
			return null;
		 
	}

	@SuppressWarnings("unchecked")
	
	public LeadGeneration getLeadGenerationById(Integer angentId) {
		Session session=sessionFactory.openSession();
		try{
		List<LeadGeneration> leadList = session.createQuery("From LeadGeneration L where L.agentId="+angentId).list();
		session.close();
		if (leadList != null && leadList.size() > 0) {
			return leadList.get(0);
		}  }catch(Exception er){
			er.printStackTrace();
			session.close();
		}
			return null;
		}

	@SuppressWarnings("unchecked")
	
	public LeadGeneration leadGeneration(LeadGeneration lg) {
		/*Session session = sessionFactory.openSession();
		try {
			List<LeadGeneration> leadList = session.createQuery("FROM LeadGeneration WHERE title='" + lg.getTitle()
					+ "' AND name='" + lg.getName()
					+ "' AND client_mobile='" + lg.getClient_mobile()
					+ "' AND client_email='" + lg.getClient_email()
					+ "' AND created_date='" + lg.getCreated_date()
					+ "' AND created_by=" + lg.getCreated_by()).list();
			session.close();
			if (leadList != null && leadList.size() > 0) {
				return leadList.get(0);
			}
		} catch (Exception er) {
			er.printStackTrace();
			session.close();
		}
		return null;*/
		
		
		
		Session session=sessionFactory.openSession();
		try {
			Transaction tx = session.beginTransaction();
			session.save(lg);
			tx.commit();
			session.close();
			lg.setErrorCode("100");
			lg.setErrorMessage("Created successfully");
			return lg;
		} catch (Exception er) {
			er.printStackTrace();
			session.close();
			return null;
		}
	}
	
	 

}
