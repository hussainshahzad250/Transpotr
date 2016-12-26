package com.trux.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.trux.model.FareRates;

public class FareDaoImpl implements FareDao{

	private SessionFactory sessionFactory;
	 
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	
	@SuppressWarnings("unchecked")
	
	public List<FareRates> getFareList() {
		Session session = this.sessionFactory.openSession();
		try{
		List<FareRates> fareRates = session.createQuery("from FareRates").list();
        session.flush();session.clear();session.close();
        return fareRates;}catch(Exception er){System.out.println(er.getMessage().toString());
        session.close();
        return null;}	
	
	}
	
	
	@SuppressWarnings("unchecked")
	
	public FareRates getFareByVehicleType(String vehicleType) {
		Session session = this.sessionFactory.openSession();
		try{
		vehicleType = vehicleType.replace("\n", " ");
        
        List<FareRates> fareRates = session.createQuery("from FareRates f where f.truck_category='"+vehicleType+"'").list();
        session.flush();session.clear();session.close();
        if(fareRates!=null){
        return fareRates.get(0);
        }else{
        	FareRates fare=new FareRates();
        	return fare;
        }}catch(Exception er){System.out.println(er.getMessage().toString());
        
        return null;}	}

}
