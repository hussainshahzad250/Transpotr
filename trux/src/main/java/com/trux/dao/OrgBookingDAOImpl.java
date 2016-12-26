package com.trux.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

import com.trux.enumerations.TruxErrorCodes;
import com.trux.model.OrganizationBookingRegistration;

public class OrgBookingDAOImpl implements OrgBookingDAO {
   
	private SessionFactory sessionFactory;
   
	public SessionFactory getSessionFactory() {
	return sessionFactory;
}

public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}

	
	public OrganizationBookingRegistration orgBooking(OrganizationBookingRegistration dto) {
		Session session=sessionFactory.openSession();
		try{
		Transaction tx= session.beginTransaction();
		session.save(dto);
		tx.commit();
		DetachedCriteria maxId = DetachedCriteria.forClass(OrganizationBookingRegistration.class).setProjection(Projections.max("bookingId"));
	    @SuppressWarnings("unchecked")
		List<OrganizationBookingRegistration>  imdpList=session.createCriteria(OrganizationBookingRegistration.class).add(Property.forName("bookingId").eq(maxId)).list();
	      session.flush();
	      session.clear();
	      session.close(); 
	    if(imdpList!=null && !imdpList.isEmpty()){ 
			return imdpList.get(0);
	      }else{
	    	  OrganizationBookingRegistration dtos= new OrganizationBookingRegistration(TruxErrorCodes.DRIVER_MULTI_DROP_POINT_MESSAGE.getCode(), TruxErrorCodes.DRIVER_MULTI_DROP_POINT_MESSAGE.getDescription());
	    	  return dtos;
	   } 
	    }catch(Exception er){
	    	er.printStackTrace();
	    	session.close();
	    }
		return null;
	}
	

	@SuppressWarnings("unchecked")
	
	public OrganizationBookingRegistration orgBookingDetails(String mobile) {
		Session session=sessionFactory.openSession();
		try{
		List<OrganizationBookingRegistration>  imdpList= session.createQuery("From OrganizationBookingRegistration where contactPersonPhone='"+mobile+"'").list();
		  session.flush();
	      session.clear();
	      session.close(); 
	    if(imdpList!=null && !imdpList.isEmpty()){ 
			return imdpList.get(0);
	      }else{
	    	  OrganizationBookingRegistration dtos= new OrganizationBookingRegistration(TruxErrorCodes.DRIVER_MULTI_DROP_POINT_MESSAGE.getCode(), TruxErrorCodes.DRIVER_MULTI_DROP_POINT_MESSAGE.getDescription());
	    	  return dtos;
	   } 
	    }catch(Exception er){
	    	er.printStackTrace();
	    	session.close();
	    }
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<OrganizationBookingRegistration> orgBookingDetails() {
		Session session=sessionFactory.openSession();
		try{
		List<OrganizationBookingRegistration>  imdpList= session.createQuery("From OrganizationBookingRegistration").list();
		  session.flush();
	      session.clear();
	      session.close(); 
	    if(imdpList!=null && !imdpList.isEmpty()){ 
			return imdpList;
	     }else{
	    	  List<OrganizationBookingRegistration>  imdpLists=new ArrayList<OrganizationBookingRegistration>();
	    	  OrganizationBookingRegistration dtos= new OrganizationBookingRegistration(TruxErrorCodes.DRIVER_MULTI_DROP_POINT_MESSAGE.getCode(), TruxErrorCodes.DRIVER_MULTI_DROP_POINT_MESSAGE.getDescription());
	    	  imdpLists.add(dtos);
	    return imdpLists;
	   }
	     }catch(Exception er){
	    	 er.printStackTrace();
	    	 session.close();
	     }
	    return null;
	}

}
