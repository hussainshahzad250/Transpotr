package com.trux.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

import com.trux.model.LevelOfOrganization;

public class LevelOfOrganizationDAOImpl implements LevelOfOrganizationDAO{
    
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@SuppressWarnings("unchecked")
	
	public LevelOfOrganization saveLevelOfOrganization(LevelOfOrganization dto) {
		Session session=sessionFactory.openSession();
		try{
		Transaction tx=session.beginTransaction();
		session.save(dto);
		DetachedCriteria maxId=DetachedCriteria.forClass(LevelOfOrganization.class).setProjection(Projections.max("clientLevelMasterId"));
		List<LevelOfOrganization> levelList=session.createCriteria(LevelOfOrganization.class).add(Property.forName("clientLevelMasterId").eq(maxId)).list();
		tx.commit();
		session.close();
		if(levelList!=null && levelList.size()>0){
			return levelList.get(0);
		}
		 }catch(Exception er){
			 
			 er.printStackTrace();
			 session.close();
		 }
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<LevelOfOrganization> getLevelOfClientLevelMaster(Integer clientLevelMasterId) {
		Session session=sessionFactory.openSession();
		try{
		 List<LevelOfOrganization> list=session.createQuery("From LevelOfOrganization where clientLevelMasterId="+clientLevelMasterId).list();
		session.close();
		if(list!=null && list.size()>0){
			return list;
		}
		 }catch(Exception er){
			 er.printStackTrace();
			 session.close();
		 }
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<LevelOfOrganization> getLevelOfClientMaster(Integer clientMasterId) {
		Session session=sessionFactory.openSession();
		try{
		List<LevelOfOrganization> list=session.createQuery("From LevelOfOrganization where clientMasterId="+clientMasterId).list();
		session.close();
		if(list!=null && list.size()>0){
			return list;
		}
		 }catch(Exception er){
			 er.printStackTrace();
			 session.close();
		 }
		return null;	
		}

}
