package com.trux.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;

import com.trux.model.LeaseBooking;
import com.trux.model.LeaseBookingHandler;
import com.trux.utils.DateFormaterUtils;

public class LeaseBookingDAOImpl implements LeaseBookingDAO {

	
private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	 
	 
	@SuppressWarnings("unchecked")
	
	public LeaseBooking saveLeaseBooking(LeaseBooking dto) {
		Session session=sessionFactory.openSession();
		try{
		Transaction tx=session.beginTransaction();
		session.save(dto);
		DetachedCriteria maxId=DetachedCriteria.forClass(LeaseBooking.class).setProjection(Projections.max("bookingLeaseId"));
		List<LeaseBooking> levelList=session.createCriteria(LeaseBooking.class).add(Property.forName("bookingLeaseId").eq(maxId)).list();
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
	
	public LeaseBooking getLeaseBookingById(Integer leaseBookingId) {
		Session session=sessionFactory.openSession();
		try{
	List<LeaseBooking>	LeaseBookingList=session.createQuery("From LeaseBooking where bookingLeaseId="+leaseBookingId).list();
	session.close();
	if(LeaseBookingList!=null && LeaseBookingList.size()>0)	{
		return LeaseBookingList.get(0);
	}
	 }catch(Exception er){
		 er.printStackTrace();
		 session.close();
	 }
	return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<LeaseBooking> getLeaseBookingList(LeaseBooking dto) {
		Session session=sessionFactory.openSession();
		try{
		List<LeaseBooking>	LeaseBookingList=session.createQuery("From LeaseBooking where bookingLeaseId").list();
		session.close();
		if(LeaseBookingList!=null && LeaseBookingList.size()>0)	{
			return LeaseBookingList;
		}
		 }catch(Exception er){
			 er.printStackTrace();
			 session.close();
		 }
		return null;
	}
	
	@SuppressWarnings("unchecked")
	
	public List<LeaseBooking> getLeaseBookingListByBookingDate(LeaseBookingHandler dto) {
		Session session=sessionFactory.openSession();
		try{
			StringBuilder sql=new StringBuilder();
			if(dto.getBookingDate()!=null){
		    sql.append("SELECT DISTINCT bl.bookingLeaseId,d.driverName,cm.subName  "); 
			sql.append("FROM  driver_registration d INNER JOIN booking_lease bl ON d.id=bl.driverId  "); 
			sql.append("INNER JOIN client_sub_master cm ON cm.idClientSubMaster=bl.companyId  "); 
			sql.append("WHERE DATE(bl.journeyStartDate)='"+DateFormaterUtils.dateFormates(dto.getBookingDate())+"' AND bl.driverId="+dto.getDriver()+" AND bl.companyId="+dto.getOrgName()+" AND bl.subClientId="+dto.getSubOrgClient()); 
	 
			System.out.println(sql.toString());
			List<Object[]>	LeaseBookingList=session.createSQLQuery(sql.toString()).list();
	        session.close();
	        
	        List<LeaseBooking> list=new ArrayList<LeaseBooking>();
		   if(LeaseBookingList!=null && LeaseBookingList.size()>0)	{
		 	for(Object[] dt:LeaseBookingList){
				LeaseBooking d=new LeaseBooking();
				d.setBookingId(dt[0].toString());
			 	d.setDriverName(dt[1].toString());
				d.setClientName(dt[2].toString());
				list.add(d);
			}
			
			return list;
		}
			}}catch(Exception er){
			 er.printStackTrace();
			 session.close();
		 }
		return null;
	}



	
	public LeaseBooking updateLeaseBooking(LeaseBooking dto) {
		Session session=sessionFactory.openSession();
		try{		
		LeaseBooking dto1=null;
		Object obj = session.get(LeaseBooking.class,dto.getBookingLeaseId());
		dto1 = (LeaseBooking)obj;
		session.close(); 
		double totalDistance=0;
		totalDistance=dto1.getTotalDistance()+dto.getTotalDistance();
		dto1.setTotalDistance(totalDistance);
        long totalDuration=	(dto.getJourneyEndDate().getTime()-dto1.getJourneyStartDate().getTime());
        dto1.setToJrLat(dto.getToJrLat());
		dto1.setToJrLong(dto.getToJrLong());
		dto1.setToJrLocation(dto.getToJrLocation());
		dto1.setJourneyEndDate(dto.getJourneyEndDate());
		dto1.setTotalDuration(totalDuration);
		dto1.setUpdatedDateTime(dto.getUpdatedDateTime()); 
		dto1.setBookingLsStatus(dto.getBookingLsStatus());
		Session session1 = this.sessionFactory.openSession();
		session1.get(LeaseBooking.class, dto.getBookingLeaseId());		 
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
	
	
	public LeaseBooking updateLeaseBookingAtResume(LeaseBooking dto) {
		Session session=sessionFactory.openSession();
		try{		
		LeaseBooking dto1=null;
		Object obj = session.get(LeaseBooking.class,dto.getBookingLeaseId());
		dto1 = (LeaseBooking)obj;
		session.close(); 
		double totalDistance=0;
		totalDistance=dto1.getTotalDistance()+dto.getTotalDistance();
		dto1.setTotalDistance(totalDistance);
        long totalDuration=	(dto.getJourneyEndDate().getTime()-dto1.getJourneyStartDate().getTime());
        dto1.setToJrLat(dto.getToJrLat());
		dto1.setToJrLong(dto.getToJrLong());
		dto1.setToJrLocation(dto.getToJrLocation());
		dto1.setJourneyEndDate(dto.getJourneyEndDate());
		dto1.setTotalDuration(totalDuration);
		dto1.setUpdatedDateTime(dto.getUpdatedDateTime()); 
		dto1.setBookingLsStatus(dto.getBookingLsStatus());
		Session session1 = this.sessionFactory.openSession();
		session1.get(LeaseBooking.class, dto.getBookingLeaseId());		 
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


	
	public LeaseBooking updateLeaseBookingAtEnd(LeaseBooking dto) {
		Session session=sessionFactory.openSession();
		try{		
		LeaseBooking dto1=null;
		Object obj = session.get(LeaseBooking.class,dto.getBookingLeaseId());
		dto1 = (LeaseBooking)obj;
		session.close();      
		if(dto.getTotalDistance()!=0){
			double totalDistance=0;
			totalDistance=dto1.getTotalDistance()+dto.getTotalDistance();
			dto1.setTotalDistance(totalDistance);
		}
        long totalDuration=	(dto.getJourneyEndDate().getTime()-dto1.getJourneyStartDate().getTime());
        dto1.setTotalDuration(totalDuration);
        dto1.setToJrLat(dto.getToJrLat());
		dto1.setToJrLong(dto.getToJrLong());
		dto1.setToJrLocation(dto.getToJrLocation());
		dto1.setJourneyEndDate(dto.getJourneyEndDate());
		dto1.setUpdatedDateTime(dto.getUpdatedDateTime()); 
		dto1.setBookingLsStatus(dto.getBookingLsStatus());
		Session session1 = this.sessionFactory.openSession();
		session1.get(LeaseBooking.class, dto.getBookingLeaseId());		 
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

}
