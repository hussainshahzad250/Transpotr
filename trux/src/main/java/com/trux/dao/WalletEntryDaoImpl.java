package com.trux.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.trux.model.WalletEntryModel;

public class WalletEntryDaoImpl implements WalletEntryDao {

	private SessionFactory sessionFactory;
	
	public int saveWalletEntry(WalletEntryModel dto) {
		Session session = this.sessionFactory.openSession();
		try{
		Transaction tx = session.beginTransaction();
		Serializable s = session.save(dto);
		tx.commit();
		session.flush();session.clear();session.close();
		return s.hashCode();
		}catch(Exception er){
			System.out.println(er.getMessage().toString());
			session.close();
			return 0;}	
	}

	@SuppressWarnings("unchecked")
	public List<WalletEntryModel> getWalletEntry() {
		Session session = this.sessionFactory.openSession();
		try{
		Query query = session.createQuery("From WalletEntryModel w order by w.weId desc");
		List<WalletEntryModel> list = query.list();
		session.flush();session.clear();session.close();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}}catch(Exception er){System.out.println(er.getMessage().toString());
		session.close(); return null;}	
	}

	@SuppressWarnings("unchecked")
	public List<WalletEntryModel> getWalletEntry(String mobile) {
		Session session = this.sessionFactory.openSession();
		try{
		Query query = session.createQuery("From WalletEntryModel w where w.driverNumber='"+mobile+"'order by w.weId desc");
		List<WalletEntryModel> list = query.list();
		session.flush();session.clear();session.close();
		if (list.size() > 0) {
			return list;
		} else {
			return null;
		}}catch(Exception er){System.out.println(er.getMessage().toString());
		session.close();
		return null;}	
	}

	@SuppressWarnings("unchecked")
	public WalletEntryModel getWalletEntry(int weId) {
		Session session = this.sessionFactory.openSession();
		try{
		Query query = session.createQuery("From WalletEntryModel w where w.weId='"+weId+"'order by w.weId desc");
		List<WalletEntryModel> list = query.list();
		session.flush();session.clear();session.close();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}}catch(Exception er){System.out.println(er.getMessage().toString());
		session.close();
		return null;}	
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
