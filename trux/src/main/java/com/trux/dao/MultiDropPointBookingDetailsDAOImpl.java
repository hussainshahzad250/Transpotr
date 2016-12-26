package com.trux.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

import com.trux.enumerations.TruxErrorCodes;
import com.trux.model.OrganizationalClientBookingDetails;

public class MultiDropPointBookingDetailsDAOImpl implements MultiDropPointBookingDetailsDAO {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
	return sessionFactory;
}

public void setSessionFactory(SessionFactory sessionFactory) {
	this.sessionFactory = sessionFactory;
}

	
	public OrganizationalClientBookingDetails bookRide(OrganizationalClientBookingDetails dto) {
		Session session=sessionFactory.openSession();
		try{
		Transaction tx= session.beginTransaction();
		session.save(dto);
		tx.commit();
		DetachedCriteria maxId = DetachedCriteria.forClass(OrganizationalClientBookingDetails.class).setProjection(Projections.max("bookingId"));
	    @SuppressWarnings("unchecked")
		List<OrganizationalClientBookingDetails>  imdpList=session.createCriteria(OrganizationalClientBookingDetails.class).add(Property.forName("bookingId").eq(maxId)).list();
	      session.flush();
	      session.clear();
	      session.close(); 
	      if(imdpList!=null && !imdpList.isEmpty()){ 
			return imdpList.get(0);
	      }else{
	    	  OrganizationalClientBookingDetails dtos= new OrganizationalClientBookingDetails(TruxErrorCodes.DRIVER_MULTI_DROP_POINT_MESSAGE.getCode(), TruxErrorCodes.DRIVER_MULTI_DROP_POINT_MESSAGE.getDescription());
	    	  return dtos;
	      } 
	     }catch(Exception er){
	    	 er.printStackTrace();
	    	 session.close();
	     }
		return null;
	}

}
