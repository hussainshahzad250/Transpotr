package com.trux.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

import com.trux.enumerations.TruxErrorCodes;
import com.trux.model.VehicleType;

public class VehicleTypeDAOImpl implements VehicleTypeDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

 
 
	
	public VehicleType save(VehicleType dto) {
	Session session=sessionFactory.openSession();
	try{
	Transaction tx=session.getTransaction();
	session.save(dto);
	tx.begin();
	tx.commit();
	DetachedCriteria maxId = DetachedCriteria.forClass(VehicleType.class).setProjection(Projections.max("id"));
    @SuppressWarnings("unchecked")
	List<VehicleType>  idList=session.createCriteria(VehicleType.class).add(Property.forName("id").eq(maxId)).list();
      session.flush();
      session.clear();
      session.close(); 
      if(idList!=null && !idList.isEmpty()){
		return idList.get(0);
      }else{
    	  return new VehicleType(TruxErrorCodes.VEHICLE_TYPE_MESSAGE.getCode(), TruxErrorCodes.VEHICLE_TYPE_MESSAGE.getDescription());
      }
	}catch(Exception er){er.printStackTrace();
	session.close();
	  return new VehicleType(TruxErrorCodes.VEHICLE_TYPE_MESSAGE.getCode(), TruxErrorCodes.VEHICLE_TYPE_MESSAGE.getDescription());
	    
	}
	}

	@SuppressWarnings("unchecked")
	
	public List<VehicleType> getAllVehicleType() {	 
		Session session=sessionFactory.openSession();
     try{ 
		Query query = session.createQuery("from VehicleType");
		List<VehicleType>  idList=query.list();
	      session.flush();
	      session.clear();
	      session.close(); 
	   if(idList!=null && !idList.isEmpty()){
			return idList;
	     }else{
	    	  List<VehicleType>  list=new ArrayList<VehicleType>();
	    	  list.add(new VehicleType(TruxErrorCodes.VEHICLE_TYPE_MESSAGE.getCode(), TruxErrorCodes.VEHICLE_TYPE_MESSAGE.getDescription()));
	    	  return list;
	    }
	   
     }catch(Exception er){
	    	er.printStackTrace();
	    	session.close();
	    	 List<VehicleType>  list=new ArrayList<VehicleType>();
	    	  list.add(new VehicleType(TruxErrorCodes.VEHICLE_TYPE_MESSAGE.getCode(), TruxErrorCodes.VEHICLE_TYPE_MESSAGE.getDescription()));
	    	  return list;  	
	    }
    
	}

}
