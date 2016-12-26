package com.trux.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property; 

import com.trux.model.SubsidiaryClientOfOrg;

public class SubsaidiaryOrgRegistrationDAOImpl implements SubsaidiaryOrgRegistrationDAO {

private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	
	public SubsidiaryClientOfOrg saveSubsidairyClientOrg(SubsidiaryClientOfOrg dto) {
		Session session=sessionFactory.openSession();
		try{
		Transaction tx=session.beginTransaction();
		session.saveOrUpdate(dto);
		tx.commit();
		DetachedCriteria maxId = DetachedCriteria.forClass(SubsidiaryClientOfOrg.class).setProjection(Projections.max("idClientSubMaster"));
		@SuppressWarnings("unchecked")
		List<SubsidiaryClientOfOrg>  idList=session.createCriteria(SubsidiaryClientOfOrg.class).add(Property.forName("idClientSubMaster").eq(maxId)).list();
		 session.close();
		if(idList!=null &&  idList.size()>0){
	    	 return idList.get(0);
	     }
		 }catch(Exception er){
			 er.printStackTrace();
			 session.close();
		 }
		return null;
	}

	
	public List<SubsidiaryClientOfOrg> getListSubsidairyClientOrg(Integer idClientMaster) {
		Session session=sessionFactory.openSession();
		try{
		 Query query=session.createQuery("From SubsidiaryClientOfOrg where idClientMaster="+idClientMaster +" Order By subName");
			@SuppressWarnings("unchecked")
			List<SubsidiaryClientOfOrg>  idList=query.list();
		      session.flush();
		      session.clear();
		      session.close(); 
		      if(idList!=null && !idList.isEmpty()){
		      return idList;
		      }
		      }catch(Exception er){
		    	  er.printStackTrace();
		    	  session.close();
		      }
		return null;
	}
	
	
	public SubsidiaryClientOfOrg getListSubsidairyClientOrgByid(Integer idClientMaster) {
		Session session=sessionFactory.openSession();
		try{
		 Query query=session.createQuery("From SubsidiaryClientOfOrg where idClientSubMaster="+idClientMaster +" Order By subName");
			@SuppressWarnings("unchecked")
			List<SubsidiaryClientOfOrg>  idList=query.list();
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
		return null;
	}
	
	
	public List<SubsidiaryClientOfOrg> getListSubsidairyClientByid(Integer idClientMaster) {
		Session session=sessionFactory.openSession();
		try{
		 Query query=session.createQuery("From SubsidiaryClientOfOrg where idClientSubMaster="+idClientMaster +" Order By subName");
			@SuppressWarnings("unchecked")
			List<SubsidiaryClientOfOrg>  idList=query.list();
		      session.flush();
		      session.clear();
		      session.close(); 
		      if(idList!=null && !idList.isEmpty()){
		      return idList;
		      }
		      }catch(Exception er){
		    	  er.printStackTrace();
		    	  session.close();
		      }
		return null;
	}
	public List<SubsidiaryClientOfOrg> getListSubsidairyClientOrgByID(Integer idClientSubMaster) {
		Session session=sessionFactory.openSession();
		try{
		 Query query=session.createQuery("From SubsidiaryClientOfOrg where idClientSubMaster="+idClientSubMaster);
			@SuppressWarnings("unchecked")
			List<SubsidiaryClientOfOrg>  idList=query.list();
		      session.flush();
		      session.clear();
		      session.close(); 
		      if(idList!=null && !idList.isEmpty()){
		      return idList;
		      }
		       }catch(Exception er){
		    	   er.printStackTrace();
		    	   session.close();
		       }
		return null;
	}
	public List<SubsidiaryClientOfOrg> getListSubsidairyClientOrg(Integer idClientMaster,Integer hubId) {
		Session session=sessionFactory.openSession();
		try{ 
		  Query query=session.createQuery("From SubsidiaryClientOfOrg where idClientMaster="+idClientMaster +" and idHub="+hubId +" and isActive=1");
		 		
			@SuppressWarnings("unchecked")
			List<SubsidiaryClientOfOrg>  idList=query.list();
		      session.flush();
		      session.clear();
		      session.close(); 
		      if(idList!=null && idList.size()>0){
		      return idList;
		      }
		     }catch(Exception er){
		    	 er.printStackTrace();		    	 
		    	 session.close();
		     }
		      return null;
	}
	public List<SubsidiaryClientOfOrg> getSubsidairyClientOrg(Integer idClientMaster) {
		Session session=sessionFactory.openSession();
		try{ 
		 Query query=session.createQuery("From SubsidiaryClientOfOrg where idClientMaster="+idClientMaster+" and isActive=1");
			@SuppressWarnings("unchecked")
			List<SubsidiaryClientOfOrg>  idList=query.list();
		      session.flush();
		      session.clear();
		      session.close(); 
		      if(idList!=null && idList.size()>0){
		      return idList;
		      }
		     }catch(Exception er){
		    	 er.printStackTrace();		    	 
		    	 session.close();
		     }
		      return null;
	}

}
