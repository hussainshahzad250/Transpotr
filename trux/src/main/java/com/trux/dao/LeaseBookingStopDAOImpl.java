package com.trux.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

import com.trux.model.LeaseBookingStop;
import com.trux.utils.DateFormaterUtils;

public class LeaseBookingStopDAOImpl implements LeaseBookingStopDAO{

private SessionFactory sessionFactory;	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	 
	@SuppressWarnings("unchecked")
	
	public LeaseBookingStop saveLeaseBookingStop(LeaseBookingStop dto) {
		Session session=sessionFactory.openSession();
		try{
		Transaction tx=session.beginTransaction();
		session.save(dto);
		DetachedCriteria maxId=DetachedCriteria.forClass(LeaseBookingStop.class).setProjection(Projections.max("bkLsStpId"));
		List<LeaseBookingStop> levelList=session.createCriteria(LeaseBookingStop.class).add(Property.forName("bkLsStpId").eq(maxId)).list();
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
 
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<LeaseBookingStop> saveLeaseBookingStopByList(List<LeaseBookingStop> dtoList) {
		List<LeaseBookingStop> levelList=new ArrayList<LeaseBookingStop>();
		try{
		for(LeaseBookingStop dto:dtoList){
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		try{  
	    dto.setCreatedTime(new Date(DateFormaterUtils.convertGMTToISTWithDatesWithSlash(new Date().toString()).replace(".0", "")));
        }catch(Exception er){} 
		session.save(dto);
		DetachedCriteria maxId=DetachedCriteria.forClass(LeaseBookingStop.class).setProjection(Projections.max("bkLsStpId"));
		List<LeaseBookingStop> levelLists=session.createCriteria(LeaseBookingStop.class).add(Property.forName("bkLsStpId").eq(maxId)).list();
		tx.commit();
		 session.close();
		if(levelLists!=null && levelLists.size()>0){
			levelList.add(levelLists.get(0));
		}
		}
		return levelList;
		 }catch(Exception er){
		 er.printStackTrace();
	}
		return null;
	}
	@SuppressWarnings("unchecked")
	
	public LeaseBookingStop getLeaseBookingStop(Integer bkLsStpId) {
		Session session=sessionFactory.openSession();
		try{
		List<LeaseBookingStop> list=session.createQuery("From LeaseBookingStop where bkLsStpId="+bkLsStpId).list();
		session.close();
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		}catch(Exception er){
			er.printStackTrace();
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<LeaseBookingStop> getLeaseBookingStopList(LeaseBookingStop dto) {
		Session session=sessionFactory.openSession();
		try{
		List<LeaseBookingStop> list=session.createQuery("From LeaseBookingStop").list();
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

	
	public LeaseBookingStop updateLeaseBookingStop(LeaseBookingStop dto) {
		Session session=sessionFactory.openSession();
		try{		
		LeaseBookingStop dto1=null;
		Object obj = session.get(LeaseBookingStop.class,dto.getBkLsStpId());
		dto1 = (LeaseBookingStop)obj;
		session.close();  
		dto1.setTotalDistance(dto.getTotalDistance());
		dto1.setAfterDropStartTime(dto.getAfterDropStartTime());
		dto1.setUpdatedTime(dto.getUpdatedTime());
		Session session1 = this.sessionFactory.openSession();
		session1.get(LeaseBookingStop.class, dto.getBkLsStpId());		 
		Transaction tx=session1.beginTransaction();		 
		session1.merge(dto1);
		tx.commit();
		session1.clear(); 
		session1.close();
		return dto1;
		 }catch(Exception er){
			 er.printStackTrace();
			 session.close();
		 }
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<LeaseBookingStop> getLeaseBookingStopListByBookingLeaseId(	LeaseBookingStop dto) {
		Session session=sessionFactory.openSession();
		try{
		if(dto.getBookingLeaseId()!=null){
		List<LeaseBookingStop> list=session.createQuery("From LeaseBookingStop where bookingLeaseId="+dto.getBookingLeaseId()).list();
		session.close();
		if(list!=null && list.size()>0){
			return list;
		}}
		}catch(Exception er){
			er.printStackTrace();
			session.close();
		}
		return null;
	}

}
