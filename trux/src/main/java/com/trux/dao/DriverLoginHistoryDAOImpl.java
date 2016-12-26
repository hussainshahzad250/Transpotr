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

import com.trux.model.DriverLoginHistory; 
import com.trux.utils.DateFormaterUtils;

public class DriverLoginHistoryDAOImpl implements DriverLoginHistoryDAO{

	
	private SessionFactory sessionFactory;	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	 
	@SuppressWarnings("unchecked")
	
	public DriverLoginHistory saveDriverLoginHistory(DriverLoginHistory dto) {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		try{
		session.save(dto);
		DetachedCriteria maxId=DetachedCriteria.forClass(DriverLoginHistory.class).setProjection(Projections.max("loginId"));
		List<DriverLoginHistory> loginList=session.createCriteria(DriverLoginHistory.class).add(Property.forName("loginId").eq(maxId)).list();
		tx.commit();
		session.close();
		if(loginList!=null && loginList.size()>0){
			
			return loginList.get(0);
		}
		 }catch(Exception  er){}
		 
		return null;
	}

	
	public DriverLoginHistory updateDriverLoginHistory(DriverLoginHistory dto) {
		Session session = this.sessionFactory.openSession();
		try{ 
				
		DriverLoginHistory dto1=null;
		Object obj = session.get(DriverLoginHistory.class,dto.getLoginId());
		dto1 = (DriverLoginHistory)obj;
		session.close();   
		dto1.setLoginLat(dto.getLoginLat()); 
		dto1.setLoginLong(dto.getLoginLong()); 
		dto1.setDatetime(dto.getDatetime());
		Session session1 = this.sessionFactory.openSession();
		session1.get(DriverLoginHistory.class, dto.getLoginId());		 
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
	
	public DriverLoginHistory getDriverLoginHistory(DriverLoginHistory dto) {
		Session session=sessionFactory.openSession();
		try{ 
		
		List<DriverLoginHistory> listS=session.createQuery("From DriverLoginHistory where loginId="+dto.getLoginId()).list();
		session.close();
		if(listS!=null && listS.size()>0){
			return listS.get(0);
		}
		 }catch(Exception er){
			 er.printStackTrace();
			 session.close();
		 }
		return null; 
	}

	@SuppressWarnings("unchecked")
	
	public List<DriverLoginHistory> getDriverLoginHistoryListByMobile(DriverLoginHistory dto) {
		Session session=sessionFactory.openSession();
		try{ 
			 List<DriverLoginHistory> list=new ArrayList<DriverLoginHistory>();
		
		String  afterDropStartTimes= DateFormaterUtils.convertGMTToISTWithDatesWithSlash(new Date().toString()).replace(".0", "");
	   String currentDate=afterDropStartTimes.substring(0,10);
		List<Object[]> listS=session.createSQLQuery("SELECT * FROM driver_loginhistory WHERE driver_id='"+dto.getDriverMobile()+"'  AND DATE_FORMAT(DATETIME,'%Y/%m/%d') ='"+currentDate+"'").list();
				//session.createQuery("From DriverLoginHistory where driverMobile='"+dto.getDriverMobile()+"' and datetime='"+currentDate+"'").list();
		session.close();
		if(listS!=null && listS.size()>0){
			for( Object[] obj: listS){
				DriverLoginHistory dtos=new DriverLoginHistory();
				if(obj[0]!=null){
					dtos.setLoginId(Integer.parseInt(obj[0].toString()));
				}
				if(obj[1]!=null){
					dtos.setDriverMobile(obj[1].toString());
				}
				if(obj[2]!=null){
					
				}
				if(obj[3]!=null){
					dtos.setLoginLong(new Double(obj[3].toString()));
				}
				if(obj[4]!=null){
					dtos.setDevice(obj[4].toString());
				}
				if(obj[5]!=null && new Boolean(obj[5].toString())==true){
				dtos.setPunchIngStatus(1);
				}
				if(obj[5]!=null && new Boolean(obj[5].toString())==false){
					
					dtos.setPunchIngStatus(0);
				}
				if(obj[6]!=null){
				//	dtos.setDatetime(new Date(obj[6].toString()));
				}
				if(obj[7]!=null){
					dtos.setLoginLat(new Double(obj[7].toString()));
				}
				list.add(dtos);
			}
			return list;
		}
		 }catch(Exception er){
			 er.printStackTrace();
			 session.close();
		 }
		return null;
	}

	@SuppressWarnings("unchecked")
	
	public List<DriverLoginHistory> getAllDriverLoginHistoryList() {
		Session session=sessionFactory.openSession();
		try{ 
		
		List<DriverLoginHistory> listS=session.createQuery("From DriverLoginHistory").list();
		session.close();
		if(listS!=null && listS.size()>0){
			return listS;
		}
		 }catch(Exception er){
			 er.printStackTrace();
			 session.close();
		 }
		return null;
	}

}
