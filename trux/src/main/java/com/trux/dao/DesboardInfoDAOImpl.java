package com.trux.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

import com.trux.model.DesboardInfo; 

public class DesboardInfoDAOImpl implements DesboardInfoDAO {

	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<DesboardInfo> getDesboardInfo() {
		Session session=sessionFactory.openSession();
		try{
		List<DesboardInfo> list=session.createQuery("From DesboardInfo").list();
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
	public DesboardInfo saveDesboardInfo(DesboardInfo dto) {
		try {
			Session session = sessionFactory.openSession();
			Transaction tx = session.beginTransaction();
			session.save(dto);
			tx.commit();
			DetachedCriteria maxId = DetachedCriteria.forClass(DesboardInfo.class).setProjection(Projections.max("infoId"));
		 	List<DesboardInfo> dsAtList = session.createCriteria(DesboardInfo.class).add(Property.forName("infoId").eq(maxId)).list();
		 	dsAtList.get(0).setStatusMessage("Desboard info Saved successfully !");
			session.close();
 
		} catch (Exception er) {
			er.printStackTrace();

		}
		return null; 
	}

}
