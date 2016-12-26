package com.trux.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.trux.model.CurrentVehicleLocation;
import com.trux.model.VehicleLocation;

public class VehicleInfoDAOImpl implements VehicleInfoDAO {

	private SessionFactory sessionFactory;
	private static int HEART_BEAT_THRESHOLD = 30;
	 
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	
	 public void saveOrUpdate(Object object){
		Session session=sessionFactory.openSession();
		try{
		Transaction tx = session.beginTransaction();
        session.saveOrUpdate(object);
        tx.commit();
        session.close();
        }catch(Exception er){System.out.println(er.getMessage().toString());
        session.close();
        }
	}
	 
	 public void saveOrUpdateCurrentVehicleLocation(CurrentVehicleLocation object){
		 Session session=sessionFactory.openSession();
		try{
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(object);
        tx.commit();
        session.close();}catch(Exception er){System.out.println(er.getMessage().toString());
        session.close();
        }
	}
	 
	 public void saveVehicleLocation(VehicleLocation  object){
		 Session session=sessionFactory.openSession();
	    try{
        Transaction tx = session.beginTransaction();
        session.save(object);
        tx.commit();
        session.close();}catch(Exception er){System.out.println(er.getMessage().toString());
        session.close();
        }
	}
	 
	 public void save(Object object){
		 Session session=sessionFactory.openSession();
		try{
        Transaction tx = session.beginTransaction();
        session.save(object);
        tx.commit();
        session.close();
        }catch(Exception er){
        	System.out.println(er.getMessage().toString());
        	session.close();}
	}
	 
    @SuppressWarnings("unchecked")
    public List<VehicleLocation> list() {
    	Session session=sessionFactory.openSession();
		try{
        List<VehicleLocation> vehicleLocationList = session.createQuery("from VehicleLocation").list();
        session.close();
        return vehicleLocationList;
        }catch(Exception er){System.out.println(er.getMessage().toString());
        session.close();
        return null;}
    }
    
	
	@SuppressWarnings("unchecked")
	public List<VehicleLocation> getNearByVehicleLists(Integer latitudeLeft, Integer longitudeLeft){
		Session session=sessionFactory.openSession();
		try{
	        List<VehicleLocation> vehicleLocationsList = new ArrayList<VehicleLocation>();
	        vehicleLocationsList = session.createQuery("from VehicleLocation v where v.latitudeLeft =" + latitudeLeft+" AND v.longitudeLeft="+longitudeLeft).list();
	        session.close();
	        return vehicleLocationsList;
	       }catch(Exception er){
	    	   er.printStackTrace();
	    	   session.close();
	    	   return null;}
	}


	@SuppressWarnings("unchecked")
	
	public CurrentVehicleLocation fetchLatestLocationByBookingId(String bookingId) {
		Session session=sessionFactory.openSession();
		try{	
		List<CurrentVehicleLocation> vehicleLocation = (List<CurrentVehicleLocation>)session.createQuery("from CurrentVehicleLocation v where v.booking_id =" + bookingId+"  ").list();
		session.close();
		if(vehicleLocation != null && vehicleLocation.size()>0)
        	return vehicleLocation.get(0);
		  return null;
		}catch(Exception er){
			er.printStackTrace();
			session.close();
			return null;}
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<CurrentVehicleLocation> fetchAllVehicleAvailableCurrentLocation() {
		Session session=sessionFactory.openSession();
		try{
		
		Date hearbeatTimeCutoff = new Date(new Date().getTime() - HEART_BEAT_THRESHOLD*60*1000);
     	List<CurrentVehicleLocation> vehicleLocations = (List<CurrentVehicleLocation>)session.createQuery("from CurrentVehicleLocation v where v.vehicleAvailbilityStatus ='" +0+"' AND v.lastHeartbeatDttm > :date").setDate("date", hearbeatTimeCutoff).list();
         session.close();
           if(vehicleLocations!=null)
			//System.out.println("Number of vehicles: "+ vehicleLocations.size());		
        return vehicleLocations;
		else 
        return null;
		}catch(Exception er){
			er.printStackTrace();
			session.close();
			return null;}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public CurrentVehicleLocation fetchVehicleLocation(String driverPhoneNumber) {
		Session session=sessionFactory.openSession();
		try{
		List<CurrentVehicleLocation> vehicleLocations = (List<CurrentVehicleLocation>)session.createQuery("from CurrentVehicleLocation v where v.driverNumber ='" + driverPhoneNumber+"'").list();
        session.close();
      if(vehicleLocations !=null && vehicleLocations.size()>0)
        return vehicleLocations.get(0);
		else return null;
		}catch(Exception er){
			session.close();
			return null;}

		
		
	}


	
	public VehicleLocation getVehicleLocationDetails(String driverPhoneNumber) {
	
		return null;
	}
	
}

