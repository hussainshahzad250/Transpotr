package com.trux.dao;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.trux.model.ConsumerPaymentGateway;
import com.trux.model.ConsumerPaymentStatus;
import com.trux.model.ConsumerWallet;

public class ConsumerPaymentGatewayDAOImpl  implements ConsumerPaymentGatewayDAO {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;

	}

	
	public void saveCP(ConsumerPaymentGateway dto) {
		try{Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(dto);
		tx.commit();
		session.close();}catch(Exception er){System.out.println(er.getMessage().toString());}	

	}

	
	public void saveCW(ConsumerWallet dto) {
		try{Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		dto.setCreatedDate(new Date());
		session.save(dto);
		tx.commit();
		session.close();}catch(Exception er){System.out.println(er.getMessage().toString());}	
	}

	
	public void saveCPS(ConsumerPaymentStatus dto) {
		try{Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(dto);
		tx.commit();
		session.close();}catch(Exception er){System.out.println(er.getMessage().toString());}	

	}

}
