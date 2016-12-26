package com.trux.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.internal.SessionImpl;

import com.trux.model.HubRegistration;
import com.trux.model.HubRegistrationDto;

public class HubRegistrationDAOImpl  implements HubRegistrationDAO{

	public static Map<Integer, String> countryMap=new HashMap<Integer, String>();
	 public static Map<Integer, String> cityMap=new HashMap<Integer, String>();
	 public static  Map<Integer, String> stateMap=new HashMap<Integer, String>(); 
		private SessionFactory sessionFactory;
		
		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
			
		}
		 
		public Map<Integer, String> getAllCountriesMap() {
		if(countryMap.isEmpty()){	
			Session session=sessionFactory.openSession();
			try{ 
		SessionImpl sessionImpl = (SessionImpl) session;
	     java.sql.Connection connection=sessionImpl.connection();	   
	     Statement st= connection.createStatement();
	     ResultSet rs= st.executeQuery("SELECT * FROM countries;");	     
			while(rs.next()){
				countryMap.put(rs.getInt("id"),rs.getString("value"));
			 }
			connection.close();
			session.clear();
			session.close();
		} catch (SQLException e) {	
			
			session.close();
			e.printStackTrace();
		}}
		 return countryMap;
		}
	 
		public Map<Integer, String> getAllCitiesMap() {
			 if(cityMap.isEmpty()){
				try {Session session = sessionFactory.openSession();
				 SessionImpl sessionImpl = (SessionImpl) session;
			     java.sql.Connection connection=sessionImpl.connection();	   
			     Statement st= connection.createStatement();
			     ResultSet rs= st.executeQuery("SELECT * FROM cities;");	     
					while(rs.next()){
						cityMap.put(rs.getInt("city_id"),rs.getString("city"));
					 }
					connection.close();
					session.clear();
					session.close();
				} catch (SQLException e) {			 
					e.printStackTrace();
				}}
				 return cityMap;
			 
		}
	 
		public Map<Integer, String> getAllStatesMap() {
			if(stateMap.isEmpty()){
				try{
				 Session session = sessionFactory.openSession();
				 SessionImpl sessionImpl = (SessionImpl) session;
			     java.sql.Connection connection=sessionImpl.connection();
			    
			     Statement st= connection.createStatement();
			     ResultSet rs= st.executeQuery("select * from states;");
			     while(rs.next()){
			    	 stateMap.put(rs.getInt("state_id"),rs.getString("state_name"));
			     }
			     connection.close();
					session.clear();
					session.close();
				}catch(Exception er){}
			}
				 return stateMap;
		}
		 
		    
  
	public HubRegistration registerHub(HubRegistration dto) {
	   Session session=sessionFactory.openSession();
		try{ 
			Transaction tx = session.beginTransaction();
	session.save(dto);
	tx.commit();
	DetachedCriteria maxId = DetachedCriteria.forClass(HubRegistration.class).setProjection(Projections.max("hubId"));
    @SuppressWarnings("unchecked")
	List<HubRegistration>  idList=session.createCriteria(HubRegistration.class).add(Property.forName("hubId").eq(maxId)).list();
      session.flush();
      session.clear();
      session.close(); 
      if(idList!=null && !idList.isEmpty()){ 
		return idList.get(0);		
	}
}catch(Exception er){
	er.printStackTrace();
	session.close();
}
      return null;}

	@SuppressWarnings("unchecked")
	
	public HubRegistration hubName(HubRegistration dto) {
		Session session=sessionFactory.openSession();
		try{ 
		List<HubRegistration> hubList = session.createQuery("from HubRegistration d where d.hubName like '%"+dto.getHubName()+"%'").list();
        session.close();
        if(hubList!=null && !hubList.isEmpty()){
        	return hubList.get(0);
        }
       }catch(Exception er){
    	   er.printStackTrace();
    	   session.close();
       }
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public HubRegistration clusterName(HubRegistration dto) {
		Session session=sessionFactory.openSession();
		try{ 
		List<HubRegistration> hubList = session.createQuery("from HubRegistration d where d.hubName like '%"+dto.getHubName()+"%'").list();
        session.close();
        if(hubList!=null && !hubList.isEmpty()){
        	return hubList.get(0);
        }
            }catch(Exception er){
            	er.printStackTrace();
            	session.close();
            }
		return null;

	}
@SuppressWarnings("unchecked")
public List<HubRegistration> getHubList() {		
	Session session=sessionFactory.openSession();
	try{ 
		List<HubRegistration> hubList = session.createQuery("From HubRegistration").list();
        session.close();
        if(hubList!=null && !hubList.isEmpty()){
        	return hubList;
        }
        }catch(Exception er){
        	er.printStackTrace();
        	session.close();
        }
		return null;

	}
 
	@SuppressWarnings("unchecked")
	
	public List<HubRegistration> hubList(HubRegistrationDto dto) {		 
		Session session=sessionFactory.openSession();
		try{ 
	   	List<HubRegistration> hubList = session.createQuery("from HubRegistration d where d.countryId='"+dto.getCountryId()+"' and d.stateId='"+dto.getStateId()+"' and d.cityId='"+dto.getCityId()+"'").list();
        session.close();
        if(hubList!=null && hubList.size()>0){
        	return hubList;
        }
        }catch(Exception er){
        	er.printStackTrace();
        	session.close();
        }
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<HubRegistration> clusterList(HubRegistrationDto dto) {
		Session session=sessionFactory.openSession();
		try{ 
		List<HubRegistration> hubList = session.createQuery("from HubRegistration d where d.countryId='"+dto.getCountryId()+"' and d.stateId='"+dto.getStateId()+"' and d.cityId='"+dto.getCityId()+"'").list();
        session.close();
        if(hubList!=null && !hubList.isEmpty()){
        	return hubList;
        }
            }catch(Exception er){
            	er.printStackTrace();
            	session.close();
            }
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public HubRegistration hubNameById(Integer hubId) {
		Session session=sessionFactory.openSession();
		try{ 
		List<HubRegistration> hubList = session.createQuery("from HubRegistration d where d.hubId="+hubId).list();
        session.close();
        if(hubList!=null && !hubList.isEmpty()){
        	return hubList.get(0);
        }
            }catch(Exception er){
            	er.printStackTrace();
            	session.close();
            }
		return null;
	}

}
