package com.trux.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;  
import com.trux.model.ClientMandateDetail; 

public class ClientMandateDetailDAOImpl implements  ClientMandateDetailDAO {

private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	@SuppressWarnings("unchecked")
	
	public ClientMandateDetail saveClientMandateDetail(ClientMandateDetail dto) {
		
		//
		
		Session session=sessionFactory.openSession();    
    	List<ClientMandateDetail> objList=session.createQuery("FROM ClientMandateDetail WHERE mandateDetailId="+dto.getMandateDetailId()+" AND bodyType='"+dto.getBodyType()+"' AND vehicleType='"+dto.getVehicleType()+"'").list();
    	 session.close(); 
		if (objList != null && objList.size() > 0) {
			for (ClientMandateDetail at : objList) {
				if (dto != null && dto.getBodyType() != null) {
					if (!dto.getBodyType().equals(""))
						at.setBodyType(dto.getBodyType());
				}
				if (dto != null && dto.getVehicleType() != null) {
					if (!dto.getVehicleType().equals(""))
						at.setVehicleType(dto.getVehicleType());
				}
				if (dto != null && dto.getMandateType() != null) {
					if (!dto.getMandateType().equals(""))
						at.setMandateType(dto.getMandateType());
				}
				if (dto != null && dto.getMandateId() != null) {
					if (!dto.getMandateId().equals(""))
						at.setMandateId(dto.getMandateId());
				}
				if (dto != null && dto.getModifiedBy() != null) {
					if (!dto.getModifiedBy().equals(0))
						at.setModifiedBy(dto.getModifiedBy());
				}
				if (dto != null && dto.getTotalVehicle() != 0) {
					at.setTotalVehicle(dto.getTotalVehicle());
				}

				at.setModifiedDate(new Date());
				Session session1 = this.sessionFactory.openSession();
				session1.get(ClientMandateDetail.class, at.getMandateDetailId());
				Transaction txs = session1.beginTransaction();
				ClientMandateDetail dts = (ClientMandateDetail) session1
						.merge(at);
				txs.commit();
				session1.close();
				return dts;
				//
			}
		} else {

			Session sessions = sessionFactory.openSession();
			try {
				Transaction tx = sessions.beginTransaction();
				dto.setMandateDetailId(null);
				sessions.saveOrUpdate(dto);
				tx.commit();
				DetachedCriteria maxId = DetachedCriteria.forClass(
						ClientMandateDetail.class).setProjection(
						Projections.max("mandateDetailId"));
				List<ClientMandateDetail> cmdList = sessions
						.createCriteria(ClientMandateDetail.class)
						.add(Property.forName("mandateDetailId").eq(maxId))
						.list();
				sessions.clear();
				sessions.close();
				if (cmdList != null && cmdList.size() > 0) {
					return cmdList.get(0);
				} else {
					return null;
				}
			} catch (Exception er) {

				er.printStackTrace();
			}
		}
    	 
		return null;
	}

	 
	@SuppressWarnings("unchecked")
	public ClientMandateDetail updateClientMandateDetail(ClientMandateDetail dto) {
	
	Session session=sessionFactory.openSession(); 
	List<ClientMandateDetail> objList=session.createQuery("FROM ClientMandateDetail where mandateDetailId="+dto.getMandateDetailId() +" AND mandateId="+dto.getMandateId()).list();
	 session.close();
	 ClientMandateDetail dts=null;
	if(objList!=null && objList.size()>0){
	for(ClientMandateDetail at:objList){
		if(dto!=null && dto.getFixDays()!=0){
			 
		 	at.setFixDays(dto.getFixDays());
		}
		if(dto!=null && dto.getFixHour()!=0){
			 
		  at.setFixHour(dto.getFixHour());
		}
		if(dto!=null && dto.getMandateType()!=null){	    			
		if(!dto.getMandateType().equals(""))
	   	at.setMandateType(dto.getMandateType());
		}
		 
		if(dto!=null && dto.getFixKm()!=0){	    			
			 
		   	at.setFixKm(dto.getFixKm());
			}
			
		/*if(dto!=null && dto.getInvoiceAmount()!=null){	    			
			if(!dto.getInvoiceAmount().equals(""))
		   	at.setInvoiceAmount(dto.getInvoiceAmount());
			}*/
			
		if(dto!=null && dto.getTotalVehicle()!=0){	    			
			 
		   	at.setTotalVehicle(dto.getTotalVehicle());
			}
			
		/*if(dto!=null && dto.getTripKmEnd()!=null){	    			
			if(!dto.getTripKmEnd().equals(""))
		   	at.setTripKmEnd(dto.getTripKmEnd());
			}
			
		if(dto!=null && dto.getTripKmStart()!=null){	    			
			if(!dto.getTripKmStart().equals(""))
		   	at.setTripKmStart(dto.getTripKmStart());
		}*/
		 
		//
		if(dto!=null && dto.getModifiedBy()!=null){
			if(!dto.getModifiedBy().equals(0))
			 at.setModifiedBy(dto.getModifiedBy());
		}
		 
		at.setModifiedDate(new Date());
		Session session1 = this.sessionFactory.openSession();
  		session1.get(ClientMandateDetail.class, at.getMandateDetailId());		 
  		Transaction txs=session1.beginTransaction();		 
  	    dts=(ClientMandateDetail)session1.merge(at);
  		txs.commit(); 
  		session1.close(); 
  		return dts;
  			}
    	}
	return dts;
	}
	@SuppressWarnings("unchecked")
	
	public ClientMandateDetail getClientMandateDetail(Integer mandateDetailId) {
		Session session=sessionFactory.openSession();
		try{
		 List<ClientMandateDetail> cmdList=session.createQuery("FROM ClientMandateDetail where mandateDetailId="+mandateDetailId).list();
			session.clear();
			session.close();
			if(cmdList!=null && cmdList.size()>0){
			return cmdList.get(0);
			}else{
			return null;	
			}
		}catch(Exception er){
			
			er.printStackTrace();}
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<ClientMandateDetail> getClientMandateDetailList() {
		Session session=sessionFactory.openSession();
		try{
		 List<ClientMandateDetail> cmdList=session.createQuery("FROM ClientMandateDetail").list();
			session.clear();
			session.close();
			if(cmdList!=null && cmdList.size()>0){
			return cmdList ;
			}else{
			return null;	
			}
		}catch(Exception er){
			
			er.printStackTrace();}
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<ClientMandateDetail> getClientMandateDetailList(Integer mandateId) {
		Session session=sessionFactory.openSession();
		try{
		 List<ClientMandateDetail> cmdList=session.createQuery("FROM ClientMandateDetail where mandateId="+mandateId).list();
			session.clear();
			session.close();
			if(cmdList!=null && cmdList.size()>0){
			return cmdList;
			}else{
			return null;	
			}
		}catch(Exception er){
			
			er.printStackTrace();}
		return null;
	}

	 
	

}
