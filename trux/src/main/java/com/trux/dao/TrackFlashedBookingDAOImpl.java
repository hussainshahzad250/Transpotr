package com.trux.dao;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

public class TrackFlashedBookingDAOImpl implements TrackFlashedBookingDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
 
	
	public void trackFlashedBooking(Collection<Object>  obj) {
		
		StatelessSession session = sessionFactory.openStatelessSession();
		Transaction tx = session.beginTransaction();
		try{
			for(Object data : obj)
	        {
				session.insert(data);
	        }
		} catch(Exception e){
				e.printStackTrace();
				tx.rollback();
		}
		finally{  
				tx.commit();
			    session.close();
		}
		/*Transaction tx = null;
	    try
	    {
	        Session s = sessionFactory.openSession();
	        s.setCacheMode(CacheMode.IGNORE);
	        s.setFlushMode(FlushMode.NEVER);
	        tx = s.beginTransaction();
	        for(Object data : obj)
	        {
	            s.saveOrUpdate(data);
	        }
	        tx.commit();
	        s.flush();
	        s.clear();
	        s.close();
	    }
	    catch(HibernateException ex)
	    {
	        if(tx != null)
	            tx.rollback();
	    }*/

	}

}
