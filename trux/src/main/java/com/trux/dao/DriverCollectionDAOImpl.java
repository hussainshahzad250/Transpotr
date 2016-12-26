package com.trux.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.internal.SessionImpl;

import com.trux.enumerations.TruxErrorCodes;
import com.trux.model.DriverCollection;

public class DriverCollectionDAOImpl implements DriverCollectionDAO {

	private SessionFactory sessionFactory;
		
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public DriverCollection saveDriverCollection(DriverCollection dto){
		Session session=sessionFactory.openSession();
		try{ 		
		Transaction tx=session.getTransaction();
		session.saveOrUpdate(dto);
		tx.begin();
		tx.commit();
		DetachedCriteria maxId = DetachedCriteria.forClass(DriverCollection.class).setProjection(Projections.max("id"));
	    @SuppressWarnings("unchecked")
		List<DriverCollection>  idList=session.createCriteria(DriverCollection.class).add(Property.forName("id").eq(maxId)).list();
	      session.flush();
	      session.clear();
	      session.close(); 
	      if(idList!=null && !idList.isEmpty()){
	    	  idList.get(0).setDriverStatus("Success");
	    	  idList.get(0).setVehicleStatus("Success");
			return idList.get(0);
	      }else{
	    	   DriverCollection dtos=  new DriverCollection();
	    	   dtos.setErrorCode(TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE.getCode());
	    	   dtos.setErrorMessage(TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE.getDescription());
	    	   dtos.setDriverStatus("Fail");
	    	  return dtos;
	      }
	      }catch(Exception er){
	    	  er.printStackTrace();
	    	  session.close();
	      }
		DriverCollection dtos=  new DriverCollection();
 	   dtos.setErrorCode(TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE.getCode());
 	   dtos.setErrorMessage(TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE.getDescription());
 	   dtos.setDriverStatus("Fail");
 	  return dtos;
		
	}

	
	public DriverCollection getDriverCollection(DriverCollection dto){
		Session session=sessionFactory.openSession();
		try{ 
		
		 Query query=session.createQuery("Select From DriverCollection where driverId="+dto.getDriverId());
		@SuppressWarnings("unchecked")
		List<DriverCollection>  idList=query.list();
	      session.flush();
	      session.clear();
	      session.close(); 
	      if(idList!=null && !idList.isEmpty()){
	    	  idList.get(0).setDriverStatus("Success");
	    	  idList.get(0).setVehicleStatus("Success");
			return idList.get(0);
	      }
          }catch(Exception er){
        	  er.printStackTrace();
        	  session.close();
          }
	    	   DriverCollection dtos=  new DriverCollection();
	    	   dtos.setErrorCode(TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE.getCode());
	    	   dtos.setErrorMessage(TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE.getDescription());
	    	   dtos.setDriverStatus("Fail");
	    	   dtos.setVehicleStatus("Fail");
	    	  return dtos;
	      
	}
	public List<DriverCollection> getDriverCollectionAmount(String driverId, String DeviceEmei){
		Session session=sessionFactory.openSession();
		try{ 		
		 Query query=session.createQuery("From DriverCollection d where d.driverId="+driverId+" AND d.deviceEmei='"+DeviceEmei+"'");
		@SuppressWarnings("unchecked")
		List<DriverCollection>  idList=query.list();
	      session.flush();
	      session.clear();
	      session.close(); 
	      if(idList!=null && !idList.isEmpty()){
	    	  for(int i=0;i<idList.size();i++){
	    	  idList.get(i).setDriverStatus("Success");
	    	  idList.get(i).setVehicleStatus("Success");}
			return idList;
	      } }catch(Exception er){
	    	  er.printStackTrace();
	    	  session.close();
	      }
	    	  List<DriverCollection> dtosLidst=  new ArrayList<DriverCollection>();
	    	   DriverCollection dtos=  new DriverCollection();
	    	   dtos.setErrorCode(TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE.getCode());
	    	   dtos.setErrorMessage(TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE.getDescription());
	    	   dtos.setDriverStatus("Fail");
	    	   dtos.setVehicleStatus("Fail");
	    	   dtosLidst.add(dtos);
	    	  return dtosLidst;
	      
	}
	
	public DriverCollection updateDriverCollection(DriverCollection dto) {
		Session session = this.sessionFactory.openSession();
		try{		
		DriverCollection dto1=null;
		Object obj = session.get(DriverCollection.class,dto.getId());
		dto1 = (DriverCollection)obj;
		session.close();	
		dto1.setDriverStatus(dto.getDriverStatus());
		dto1.setVehicleStatus(dto.getVehicleStatus());
		Session session1 = this.sessionFactory.openSession();
		session1.get(DriverCollection.class, dto.getId());		 
		Transaction tx=session1.beginTransaction();		 
		session1.merge(dto1);
		tx.commit();
		session1.clear(); 
		session1.close();
		return dto1;
	}catch(Exception er){
		er.printStackTrace();
		session.close();
		 return new DriverCollection(TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE.getCode(), TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE.getDescription() +" "+er.getMessage());}
	}

	
	public DriverCollection deactivateDriver(int driverId) {		 
		Session session = this.sessionFactory.openSession();
		try{
		 SessionImpl sessionImpl = (SessionImpl) session;
	     java.sql.Connection connection=sessionImpl.connection();	   
		 PreparedStatement ps = connection.prepareStatement("update driver_collection  set driverActivation='0' where driverid="+driverId+";");
         boolean flag= ps.execute();
         connection.close();
		 session.flush();
	      session.clear();
	      session.close(); 
	      if(flag==false){
	    	  DriverCollection dtos=  new DriverCollection();
	     	   dtos.setDriverStatus("Success");
	    	  return dtos;
	       } 
	      else{ DriverCollection dtos=  new DriverCollection(); 
	    	   dtos.setDriverStatus("Fail");
	    	  return dtos;
	    	  }
	    }catch(Exception er){er.printStackTrace();
	    session.close();
	   DriverCollection dtos=  new DriverCollection();
   	   dtos.setErrorCode(TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE.getCode());
   	   dtos.setErrorMessage(TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE.getDescription() +er.getMessage().toString());
   	   dtos.setDriverStatus("Fail");
   	  return dtos;
   	  }
	}

	
	public DriverCollection driverAmountCollection(DriverCollection dto) {
		Session session = this.sessionFactory.openSession();
		try{
		Transaction tx=session.getTransaction();
		dto.setDriverActivation(1);
		dto.setVehicleActivation(1);
		session.saveOrUpdate(dto);
		tx.begin();
		tx.commit();
		DetachedCriteria maxId = DetachedCriteria.forClass(DriverCollection.class).setProjection(Projections.max("id"));
	    @SuppressWarnings("unchecked")
		List<DriverCollection>  idList=session.createCriteria(DriverCollection.class).add(Property.forName("id").eq(maxId)).list();
	      session.flush();
	      session.clear();
	      session.close(); 
	      if(idList!=null && !idList.isEmpty()){
	    	  idList.get(0).setDriverStatus("Success");
			return idList.get(0);
	      }
}catch(Exception er){
	er.printStackTrace();
	
	session.close();
}
	    	   DriverCollection dtos=  new DriverCollection();
	    	   dtos.setErrorCode(TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE.getCode());
	    	   dtos.setErrorMessage(TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE.getDescription());
	    	   dtos.setDriverStatus("Fail");
	    	  return dtos;
	      
	}

	
	public DriverCollection deactivateVehicle(int driverId) {
		Session session = this.sessionFactory.openSession();
		try{
			 SessionImpl sessionImpl = (SessionImpl) session;
		     java.sql.Connection connection=sessionImpl.connection();	   
			 PreparedStatement ps = connection.prepareStatement("update driver_collection  set vehicleActivation='0' where driverid="+driverId+";");
	         boolean flag= ps.execute();
	         connection.close();
			 session.flush();
		      session.clear();
		      session.close(); 
		      if(flag==false){
		    	  DriverCollection dtos=  new DriverCollection();
		     	   dtos.setVehicleStatus("Success");
		    	  return dtos;
		       } 
		      else{ DriverCollection dtos=  new DriverCollection(); 
		    	   dtos.setVehicleStatus("Fail");
		    	  return dtos;
		    	  }
		    }catch(Exception er){er.printStackTrace();
		    session.close();
		   DriverCollection dtos=  new DriverCollection();
	   	   dtos.setErrorCode(TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE.getCode());
	   	   dtos.setErrorMessage(TruxErrorCodes.DRIVER_COLLECTION_CREATION_ERROR_MESSAGE.getDescription() +er.getMessage().toString());
	   	   dtos.setVehicleStatus("Fail");
	   	  return dtos;
	   	  }
	}
	
	
	public int getDriverID(String driverMobile) {
		  int driverId=0;
		  Session session = this.sessionFactory.openSession();
			try{
		 SessionImpl sessionImpl = (SessionImpl) session;
	     java.sql.Connection connection=sessionImpl.connection();	   
		 PreparedStatement ps = connection.prepareStatement("SELECT id,phone_number FROM driver_registration WHERE phone_number='"+driverMobile+"';");
         ResultSet rs= ps.executeQuery();	       
         while(rs.next()){
        	 driverId=rs.getInt("id"); 
         }
         connection.close();
		 session.flush();
	      session.clear();
	      session.close();
	      return driverId;
		}catch(Exception er){
			er.printStackTrace();
			session.close();
		} 
		return driverId;
	}

	
	public int getVehicleID(String vehiclOwnerMobile) {
		  int vehiclId=0;
		  Session session = this.sessionFactory.openSession();
			try{
			 SessionImpl sessionImpl = (SessionImpl) session;
		     java.sql.Connection connection=sessionImpl.connection();	   
			 PreparedStatement ps = connection.prepareStatement("SELECT id,owner_phone_number FROM vehicle_registration WHERE owner_phone_number='"+vehiclOwnerMobile+"';");
	         ResultSet rs= ps.executeQuery();	       
	         while(rs.next()){
	        	 vehiclId=rs.getInt("id"); 
	         }
	         connection.close();
			 session.flush();
		     session.clear();
		     session.close();
		     return vehiclId;
			}catch(Exception er){
				er.printStackTrace();
				session.close();
			} 
			return vehiclId;
	}
	
	public String insertDrivers(String fileLocation) {
		Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try{
		session.createSQLQuery("LOAD DATA INFILE :filename INTO TABLE driver_registration FIELDS TERMINATED BY ',' ENCLOSED BY '\"' LINES TERMINATED BY '\r\n' (driverName,phone_number,driverContactNumber)").setString("filename", fileLocation).executeUpdate();
        //session.createSQLQuery("LOAD DATA INFILE :filename INTO TABLE hotel_upload FIELDS TERMINATED BY ',' ENCLOSED BY '\'' ESCAPED BY '' LINES TERMINATED BY '\r\n' (hotel_name, hotel_address, hotel_star_category, cd_authorized_signatory_name,cd_mobile,hotel_email,city,state,country)").setString("filename", fileLocation).executeUpdate();
        tx.commit();
		session.close();
		 
        }catch(Exception er){
         session.close();
        return	er.getMessage();}
		return "upload successfully !";
	}
}
