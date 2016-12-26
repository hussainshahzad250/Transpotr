package com.trux.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

import com.trux.model.ClientInvoiceDocs;

public class ClientInvoiceDocsDAOImpl implements ClientInvoiceDocsDAO{

private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@SuppressWarnings("unchecked")
	
	public ClientInvoiceDocs saveClientInvoiceDocs(ClientInvoiceDocs dto) {
		Session session=sessionFactory.openSession();
		try{
			Transaction tx=session.beginTransaction();
			session.save(dto);
			tx.commit();
		    DetachedCriteria maxId = DetachedCriteria.forClass(ClientInvoiceDocs.class).setProjection(Projections.max("clientInvoicDocsId"));
			List<ClientInvoiceDocs>  cidList=session.createCriteria(ClientInvoiceDocs.class).add(Property.forName("clientInvoicDocsId").eq(maxId)).list();
			session.clear();
			session.close();
			if(cidList!=null && cidList.size()>0){
			return cidList.get(0);
			}else{
			return null;	
			}
		}catch(Exception er){
			
			er.printStackTrace();}
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<ClientInvoiceDocs> getClientInvoiceDocs() {
		Session session=sessionFactory.openSession();
		try{
		 List<ClientInvoiceDocs>  cidList=session.createQuery("FROM ClientInvoiceDocs").list();
			session.clear();
			session.close();
			if(cidList!=null && cidList.size()>0){
			return cidList;
			}else{
			return null;	
			}
		}catch(Exception er){
			
			er.printStackTrace();}
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<ClientInvoiceDocs> getClientInvoiceDocsBybookingLeaseId(Integer bookingLeaseId) {
		Session session=sessionFactory.openSession();
		try{
		 List<ClientInvoiceDocs>  cidList=session.createQuery("FROM ClientInvoiceDocs where bookingLeaseId="+bookingLeaseId).list();
			session.clear();
			session.close();
			if(cidList!=null && cidList.size()>0){
			return cidList;
			}else{
			return null;	
			}
		}catch(Exception er){
			
			er.printStackTrace();}
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public ClientInvoiceDocs getClientInvoiceDocs(Integer clientInvoicDocsId) {
		Session session=sessionFactory.openSession();
		try{
		 List<ClientInvoiceDocs>  cidList=session.createQuery("FROM ClientInvoiceDocs where clientInvoicDocsId="+clientInvoicDocsId).list();
			session.clear();
			session.close();
			if(cidList!=null && cidList.size()>0){
			return cidList.get(0);
			}else{
			return null;	
			}
		}catch(Exception er){
			
			er.printStackTrace();}
		return null;
	}

}
