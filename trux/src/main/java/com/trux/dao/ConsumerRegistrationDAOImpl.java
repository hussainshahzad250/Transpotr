package com.trux.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.trux.model.ConsumerRegistration;
import com.trux.utils.DateFormaterUtils;

public class ConsumerRegistrationDAOImpl implements ConsumerRegistrationDAO {

	private SessionFactory sessionFactory;
	 
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

	public void saveOrUpdate(Object object) {
		Session session=sessionFactory.openSession();
		try{
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(object);
        tx.commit();
        session.flush();session.clear();session.close();
        }catch(Exception er){System.out.println(er.getMessage().toString());
        session.close();}	
		
	}

	public void consumerRegistration(ConsumerRegistration object) {
		Session session=sessionFactory.openSession();
		try{
        Transaction tx = session.beginTransaction();
        session.saveOrUpdate(object);
        tx.commit();
        session.flush();session.clear();session.close();
       }catch(Exception er){System.out.println(er.getMessage().toString()); 
       session.close();
       }	
		
	}
	@SuppressWarnings("unchecked")
	public ConsumerRegistration getUserDetailsByPhoneNumber(String phoneNumber) {
		Session session=sessionFactory.openSession();
		try{
        List<ConsumerRegistration> consumerRegistrations = session.createQuery("from ConsumerRegistration u where u.phoneNumber='"+phoneNumber+"'").list();
        session.flush();session.clear();session.close();
        
        if(consumerRegistrations != null && !consumerRegistrations.isEmpty()){
        	return consumerRegistrations.get(0);
        }
        return null;
        }catch(Exception er){System.out.println(er.getMessage().toString());
        session.close();
        return null;}	
	
		
	}

	public void save(Object forgotPassword) {
		Session session=sessionFactory.openSession();
		try{
		Transaction tx=session.beginTransaction();
		session.save(forgotPassword);
		tx.commit();
		session.close();}catch(Exception er){System.out.println(er.getMessage().toString());
		session.close();}			
	}
	
	public void saveForm(Object forgotPassword) {
		Session session=sessionFactory.openSession();
		try{
		Transaction tx=session.beginTransaction();
		session.save(forgotPassword);
		tx.commit();
		session.close();}catch(Exception er){System.out.println(er.getMessage().toString());
		session.close();
		}			
	}

	@SuppressWarnings("unchecked")
	
	public ConsumerRegistration getUserDetailsByEmail(String emailAddress) {

		Session session=sessionFactory.openSession();
		try{
        List<ConsumerRegistration> consumerRegistrations = session.createQuery("from ConsumerRegistration u where u.email='"+emailAddress+"'").list();
        session.flush();session.clear();session.close();
        
        if(consumerRegistrations != null && !consumerRegistrations.isEmpty()){
        	return consumerRegistrations.get(0);
        }
        return null;}catch(Exception er){System.out.println(er.getMessage().toString());
        session.close();
        return null;}	
	
		
	
	}

	@SuppressWarnings("unchecked")
	
	public List<ConsumerRegistration> getConsumerDetails()
	{
		Session session=sessionFactory.openSession();
		try{
		List<ConsumerRegistration> consumerRegistrations =	session.createQuery("SELECT u from ConsumerRegistration u order by u.createdDate desc").list();
		session.flush();session.clear();session.close();
		
		if (consumerRegistrations != null && consumerRegistrations.size() > 0)
		{
			for(int i=0;i<consumerRegistrations.size();i++){
				String userName="";
				if(consumerRegistrations.get(i).getFirstname() != null){
					userName=consumerRegistrations.get(i).getFirstname() ;
					}
				if(consumerRegistrations.get(i).getLastname() != null){
					userName=userName +" "+consumerRegistrations.get(i).getLastname() ;
					}
				consumerRegistrations.get(i).setUserFistLastName(userName);
				
			
		   if(consumerRegistrations.get(i).getUsertype() != null && consumerRegistrations.get(i).getUsertype()== 0)
			{
			  consumerRegistrations.get(i).setUserTypes("Business");		 
			}
		   if(consumerRegistrations.get(i).getUsertype() != null && consumerRegistrations.get(i).getUsertype()== 1)
			{
			  consumerRegistrations.get(i).setUserTypes("Individual");		 
			}
		   if(consumerRegistrations.get(i).getCreatedDate()!= null){
			   String createdDatetime=consumerRegistrations.get(i).getCreatedDate().toString();
			  consumerRegistrations.get(i).setCreateConsumerDate(DateFormaterUtils.getDateTime(createdDatetime));
		   }
		   if(consumerRegistrations.get(i).getUpdatedDate()!= null){
			   String updatedDateTime=consumerRegistrations.get(i).getUpdatedDate().toString();
			   consumerRegistrations.get(i).setUpdatedConsumerDate(DateFormaterUtils.getDateTime(updatedDateTime));
		   }
		  } 
			
			return consumerRegistrations;
		}
		else
			return null;}catch(Exception er){System.out.println(er.getMessage().toString());
			session.close();
			return null;}	
	}
 
	@SuppressWarnings("unchecked")
	public List<ConsumerRegistration> getConsumerDetailsList()
	{
		try{Session session = this.sessionFactory.openSession();
		 List<ConsumerRegistration> consumerRegistrations =	session.createQuery("SELECT u from ConsumerRegistration u order by u.createdDate desc").list();
		session.flush();session.clear();session.close();
		
		if (consumerRegistrations != null && consumerRegistrations.size() > 0)
		{ return consumerRegistrations;
		}
		else
			return null;}catch(Exception er){System.out.println(er.getMessage().toString());return null;}	
	}
	@SuppressWarnings("unchecked")
	
	public List<ConsumerRegistration> searchByMobile(String mobile) {
		try{Session session = this.sessionFactory.openSession();
        List<ConsumerRegistration> consumerRegistrations = session.createQuery("from ConsumerRegistration u where u.phoneNumber='"+mobile+"'").list();
        session.flush();session.clear();session.close();
        
        if(consumerRegistrations != null && !consumerRegistrations.isEmpty()){
        	return consumerRegistrations;
        }else{
		return null;}
	}catch(Exception er){System.out.println(er.getMessage().toString());return null;}	
	}
}
