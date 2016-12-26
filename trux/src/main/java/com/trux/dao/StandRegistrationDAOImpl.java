package com.trux.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

import com.trux.model.StandRegistration; 

public class StandRegistrationDAOImpl implements StandRegistrationDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	
	public StandRegistration saveStand(StandRegistration dto) {
		Session session=sessionFactory.openSession();
		try{
		Transaction tx = session.beginTransaction();
		session.save(dto);
		DetachedCriteria maxId = DetachedCriteria.forClass(StandRegistration.class).setProjection(Projections.max("id"));
		@SuppressWarnings("unchecked")
		List<StandRegistration> drAtList = session
				.createCriteria(StandRegistration.class)
				.add(Property.forName("id").eq(maxId)).list();
		tx.commit();
		session.close();
		if (drAtList != null && drAtList.size() > 0) {
			return drAtList.get(0);
		}
		 }catch(Exception er){
			 er.printStackTrace();
			 session.close();
		 }
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<StandRegistration> getAllStandList() {
		Session session=sessionFactory.openSession();
		try{
			List<StandRegistration> vehicleLocationList = session.createQuery("from StandRegistration").list();
			session.close();
			if(vehicleLocationList!=null && vehicleLocationList.size()>0){
			return vehicleLocationList;
			}else{
				return null;
			}
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	
	public List<StandRegistration> getStandListByCluster(Integer clusterId) {
		Session session=sessionFactory.openSession();
		try{
			List<StandRegistration> vehicleLocationList = session.createQuery("from StandRegistration where clusterId="+clusterId).list();
			session.close();
			if(vehicleLocationList!=null && vehicleLocationList.size()>0){
			return vehicleLocationList;
			}else{
				return null;
			}
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public StandRegistration  getStandByID(Integer statndId) {
		Session session=sessionFactory.openSession();
		try{
			List<StandRegistration> vehicleLocationList = session.createQuery("from StandRegistration where standId="+statndId).list();
			session.close();
			if(vehicleLocationList!=null && vehicleLocationList.size()>0){
			return vehicleLocationList.get(0);
			}else{
				return null;
			}
		} catch (Exception er) {
			System.out.println(er.getMessage().toString());
			session.close();
			return null;
		}
	}

}
