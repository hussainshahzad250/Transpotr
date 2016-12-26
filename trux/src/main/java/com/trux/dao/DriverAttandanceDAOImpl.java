package com.trux.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

import com.trux.model.DriverAttandance; 
import com.trux.model.DriverRegistration;
import com.trux.utils.DateFormaterUtils;
 
public class DriverAttandanceDAOImpl implements DriverAttandanceDAO{


	private SessionFactory sessionFactory;
		
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@SuppressWarnings("unchecked")
	
	public List<DriverAttandance> saveAttandanceOfDriver(List<DriverAttandance> listOfAttandance) {
		
		try{ 
		
		List<DriverAttandance> saveList=new ArrayList<DriverAttandance>();
		if(listOfAttandance!=null && listOfAttandance.size()>0){
		for(DriverAttandance dtp:listOfAttandance){			
	    if(dtp.getAttendanceDate()!=null){
	 
	    String date=DateFormaterUtils.dateFormateWithDate(dtp.getAttendanceDate());
	    List<DriverAttandance>  drAtList=null;
	    if(date!=null){
	       	Session session=sessionFactory.openSession(); 
	    	List<DriverAttandance> objList=session.createQuery("FROM DriverAttandance where driverId="+dtp.getDriverId()+" AND AttendanceDate='"+date+"'" ).list();
	    	 session.close();
	    	if(objList!=null && objList.size()>0){
	    	for(DriverAttandance at:objList){
	    		if(dtp!=null && dtp.getPunchIn()!=null){
	    			if(!dtp.getPunchIn().equals(""))
	    		 	at.setPunchIn(dtp.getPunchIn());
	    		}
	    		if(dtp!=null && dtp.getPunchOut()!=null){
	    			if(!dtp.getPunchOut().equals(""))
	    		  at.setPunchOut(dtp.getPunchOut());
	    		}
	    		if(dtp!=null && dtp.getOpeningKilometer()!=null){	    			
	    		if(!dtp.getOpeningKilometer().equals(""))
			   	at.setOpeningKilometer(dtp.getOpeningKilometer());
	    		}
	    		if(dtp!=null && dtp.getClosingKilometer()!=null){
	    			if(!dtp.getClosingKilometer().equals(""))
	 			 at.setClosingKilometer(dtp.getClosingKilometer());
	    		}
	    		if(dtp!=null && dtp.getNoofboxes()!=null){
	    			if(!dtp.getNoofboxes().equals(""))
		 		 at.setNoofboxes(dtp.getNoofboxes());
	    		}
	    		if(dtp!=null && dtp.getTolltax()!=null){
	    			if(!dtp.getTolltax().equals(""))
			 	 at.setTolltax(dtp.getTolltax());
	    		} 
               if(dtp.getCreatedBy()!=null){
	    		at.setModifiedBy(dtp.getCreatedBy());
               }
               if(dtp!=null && dtp.getVehicleNumber()!=null){
            	   if(dtp.getVehicleNumber()!=""){
   	    		   at.setVehicleNumber(dtp.getVehicleNumber());
            	   }
                  }
	    		at.setModifiedDate(new Date());
	    		Session session1 = this.sessionFactory.openSession();
	      		session1.get(DriverRegistration.class, at.getId());		 
	      		Transaction txs=session1.beginTransaction();		 
	      		DriverAttandance dts=(DriverAttandance)session1.merge(at);
	      		txs.commit(); 
	      		session1.close();
	      		dts.setStatusMessage("Updated");
	 		    saveList.add(dts);
	    	}
	    }else{
	       	Session sessions=sessionFactory.openSession();
		    Transaction txs=sessions.beginTransaction();
		    dtp.setCreatedDate(new Date());
		    sessions.save(dtp);
		    txs.commit();
		    DetachedCriteria maxId = DetachedCriteria.forClass(DriverAttandance.class).setProjection(Projections.max("id"));
		    drAtList=sessions.createCriteria(DriverAttandance.class).add(Property.forName("id").eq(maxId)).list();
		    drAtList.get(0).setStatusMessage("Saved");
		    saveList.add(drAtList.get(0));
		    sessions.close();
	    }
	    }} 
	    }
		}
		 
		return saveList;
		 }catch(Exception er){
			 er.printStackTrace();
			 
		 } 
		return null;
	}

}
