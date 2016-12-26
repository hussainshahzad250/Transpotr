package com.trux.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction; 
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

import com.trux.model.OrganizationalUser;

public class OrganizationalUserDDAOImpl implements OrganizationalUserDDAO {

private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	
	public OrganizationalUser saveOrganizationalUser(OrganizationalUser dto) {
		Session session=sessionFactory.openSession();
		try{
		Transaction tx=session.beginTransaction();
		session.save(dto);
		tx.commit();
		DetachedCriteria maxId=DetachedCriteria.forClass(OrganizationalUser.class).setProjection(Projections.max("idClientContactMaster"));
		List<OrganizationalUser> orgUserList=session.createCriteria(OrganizationalUser.class).add(Property.forName("idClientContactMaster").eq(maxId)).list();
		session.close();
		if(orgUserList!=null && orgUserList.size()>0){
			return orgUserList.get(0);
		}
}catch(Exception er){
	er.printStackTrace();
	session.close();
}
		
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<OrganizationalUser> getOrganizationUser(OrganizationalUser dto) {
		Session session=sessionFactory.openSession();
		try{
		List<OrganizationalUser> orgUserList=session.createQuery("From OrganizationalUser where userName="+dto.getUserName()+" nd pssword="+dto.getPassword()).list();
		session.close();	
		if(orgUserList!=null && orgUserList.size()>0)	{
				return orgUserList;
			}
		 }catch(Exception er){
			 er.printStackTrace();
			 session.close();
		 }
		return null;
	}

	
	public List<OrganizationalUser> getAllOrganizationUserList() {
		Session session=sessionFactory.openSession();
		try{
		List<OrganizationalUser> orgUserList=session.createQuery("From OrganizationalUser").list();
		session.close();
			if(orgUserList!=null && orgUserList.size()>0)	{
				return orgUserList;
			}
			 }catch(Exception er){
				 er.printStackTrace();
				 session.close();
			 }
		return null;
	}

}
