package com.trux.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

import com.trux.model.Zones;

public class ZonesDAOImpl implements ZonesDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	@SuppressWarnings("unchecked")
	
	public Zones saveZone(Zones dto) {
		Session session = this.sessionFactory.openSession();
		try{Transaction tx = session.beginTransaction();
		session.save(dto);
		tx.commit();
		DetachedCriteria maxID = DetachedCriteria.forClass(Zones.class)
				.setProjection(Projections.max("zoneId"));
		List<Zones> zoneList = session.createCriteria(Zones.class)
				.add(Property.forName("zoneId").eq(maxID)).list();
		session.close();
		if (zoneList != null && zoneList.size() > 0) {
			return zoneList.get(0);
		}
	}catch(Exception er){
		er.printStackTrace();
		session.close();
	}
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<Zones> getZones(Integer zonedId) {
		Session session = this.sessionFactory.openSession();
		try{
		List<Zones> zoneList = session.createQuery("From Zones where zoneId="+zonedId).list();
		session.close();
		if(zoneList!=null && zoneList.size()>0){
			return zoneList;
		}
		 }catch(Exception er){
			 er.printStackTrace();
			 session.close();
		 }
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<Zones> getAllZones() {
		Session session = this.sessionFactory.openSession();
		try{
		List<Zones> zoneList = session.createQuery("From Zones").list();
		session.close();
		if(zoneList!=null && zoneList.size()>0){
			return zoneList;
		}
		 }catch(Exception er){
			 er.printStackTrace();
			 session.close();
		 }
		return null;
	}

}
